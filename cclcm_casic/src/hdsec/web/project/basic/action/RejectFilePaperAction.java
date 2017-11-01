package hdsec.web.project.basic.action;

import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.basic.model.RejectRecord;
import hdsec.web.project.ledger.model.EntityPaper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 纸质载体拒绝归档
 * 
 * @author gaoximin 2014-9-18
 */
public class RejectFilePaperAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String paper_barcode_hid;

	private String comment = "";
	private String reject_ok = "N";

	public String getPaper_barcode_hid() {
		return paper_barcode_hid;
	}

	public void setPaper_barcode_hid(String paper_barcode_hid) {
		this.paper_barcode_hid = paper_barcode_hid;
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
			String reject_code = "FILE_PAPER_" + System.currentTimeMillis();
			EntityPaper paper = ledgerService.getPaperByBarcode(paper_barcode_hid);
			ProcessJob job = basicService.getProcessJobByCode(paper.getJob_code());

			RejectRecord record = new RejectRecord();
			record.setReject_code(reject_code);
			record.setEntity_barcode(paper.getPaper_barcode());
			record.setEntity_type("PAPER");
			record.setEntity_name(paper.getFile_title());
			record.setReject_time(new Date());
			record.setSend_user_iidd(job.getUser_iidd());
			record.setSend_dept_id(job.getDept_id());
			record.setReject_user_name(getCurUser().getUser_name());// 拒绝归档时，表示拒绝归档的管理员名称
			record.setReject_dept_name(getCurUser().getDept_name());// 拒绝归档时，表示拒绝归档的管理员所在部门名称
			record.setReject_type("READ");
			record.setComment(comment);
			// 修改文件状态为留用
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("paper_barcode", paper_barcode_hid);
			map.put("paper_state", "0");
			// 记录生命周期用
			map.put("user_name", getCurUser().getUser_name());
			map.put("dept_name", getCurUser().getDept_name());
			// 区别记录生命周期记录，此为归档拒收
			map.put("oper", "FILEREJECT");
			map.put("job_code", job.getJob_code());
			ledgerService.rejectSendPaper(record, map);
			insertCommonLog("管理员[" + getCurUser().getUser_name() + "]拒绝归档纸质载体[" + paper_barcode_hid + "]");
			return "ok";
		} else
			return SUCCESS;

	}
}
