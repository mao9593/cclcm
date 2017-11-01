package hdsec.web.project.basic.action;

import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.basic.model.RejectRecord;
import hdsec.web.project.ledger.model.EntityCD;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 光盘载体拒绝归档
 * 
 * @author gaoximin 2014-9-18
 */
public class RejectFileCDAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String cd_barcode_hid;
	private String comment = "";
	private String reject_ok = "N";

	public String getCd_barcode_hid() {
		return cd_barcode_hid;
	}

	public void setCd_barcode_hid(String cd_barcode_hid) {
		this.cd_barcode_hid = cd_barcode_hid;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getReject_ok() {
		return reject_ok;
	}

	public void setReject_ok(String reject_ok) {
		this.reject_ok = reject_ok;
	}

	@Override
	public String executeFunction() throws Exception {
		if (reject_ok.equals("Y")) {
			// 拒绝归档记录
			String reject_code = "FILE_CD_" + System.currentTimeMillis();
			EntityCD cd = ledgerService.getCDByBarcode(cd_barcode_hid);
			ProcessJob job = basicService.getProcessJobByCode(cd.getJob_code());

			RejectRecord record = new RejectRecord();
			record.setReject_code(reject_code);
			record.setEntity_barcode(cd.getCd_barcode());
			record.setEntity_type("CD");
			record.setEntity_name(cd.getFile_list());
			record.setReject_time(new Date());
			record.setSend_user_iidd(job.getUser_iidd());
			record.setSend_dept_id(job.getDept_id());
			record.setReject_user_name(getCurUser().getUser_name());// 拒绝归档时，表示拒绝归档的管理员名称
			record.setReject_dept_name(getCurUser().getDept_name());// 拒绝归档时，表示拒绝归档的管理员所在部门名称
			record.setReject_type("READ");
			record.setComment(comment);
			// 修改光盘状态为留用
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cd_barcode", cd_barcode_hid);
			map.put("cd_state", "0");
			// 记录生命周期用
			map.put("user_name", getCurUser().getUser_name());
			map.put("dept_name", getCurUser().getDept_name());
			// 区别记录生命周期记录，此为归档拒收
			map.put("oper", "FILEREJECT");

			ledgerService.rejectSendCD(record, map);
			insertCommonLog("管理员[" + getCurUser().getUser_name() + "]拒绝归档光盘载体[" + cd_barcode_hid + "]");
			return "ok";
		} else
			return SUCCESS;

	}
}
