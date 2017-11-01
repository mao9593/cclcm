package hdsec.web.project.ledger.action;

import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.basic.model.RejectRecord;
import hdsec.web.project.basic.model.SysConfigItem;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.user.model.SecUser;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 光盘拒收外发处理
 * 
 * @author lixiang
 * 
 */
public class RejectSendCDAction extends LedgerBaseAction {

	private static final long serialVersionUID = 1L;
	private String cd_barcode;
	private String comment = "";
	private String reject_type = "";
	private String reject_ok = "N";

	public String getCd_barcode() {
		return cd_barcode;
	}

	public void setCd_barcode(String cd_barcode) {
		this.cd_barcode = cd_barcode.trim();
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getReject_type() {
		return reject_type;
	}

	public void setReject_type(String reject_type) {
		this.reject_type = reject_type;
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
			// 外发拒收记录
			String reject_code = "send_CD_" + System.currentTimeMillis();
			EntityCD cd = ledgerService.getCDByBarcode(cd_barcode);
			ProcessJob job = basicService.getProcessJobByCode(cd.getJob_code());

			RejectRecord record = new RejectRecord();
			record.setReject_code(reject_code);
			record.setEntity_barcode(cd.getCd_barcode());
			record.setEntity_type("CD");
			record.setEntity_name(cd.getFile_list());
			record.setReject_time(new Date());
			record.setSend_user_iidd(job.getUser_iidd());
			record.setSend_dept_id(job.getDept_id());
			record.setReject_user_name(job.getOutput_user_name());
			record.setReject_dept_name(job.getOutput_dept_name());
			record.setReject_type(reject_type);
			record.setComment(comment);

			Map<String, Object> map = new HashMap<String, Object>();
			String cd_state = basicService.getSysConfigItemValue(SysConfigItem.KEY_SEND_REJECT_STATUS).getItem_value();
			if ("0".equals(cd_state)) {// 拒收后留用
				map.put("cd_barcode", cd_barcode);
				map.put("cd_state", "0");
				// 记录生命周期用
				map.put("user_name", getCurUser().getUser_name());
				map.put("dept_name", getCurUser().getDept_name());
				map.put("job_code", job.getJob_code());
				ledgerService.rejectSendCD(record, map);
			} else {// 拒收后已回收
				map.put("cd_id", cd.getCd_id());
				// 记录生命周期用
				map.put("cd_barcode", cd_barcode);
				SecUser user = getCurUser();
				ledgerService.rejectSendCDToRetrieved(record, map, user);
			}
			insertCommonLog("管理员[" + getCurUser().getUser_name() + "]外发拒收光盘[" + cd_barcode + "]");
			return "ok";
		} else
			return SUCCESS;

	}
}
