package hdsec.web.project.ledger.action;

import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.basic.model.RejectRecord;
import hdsec.web.project.basic.model.SysConfigItem;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.user.model.SecUser;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 纸质载体拒收外发处理
 * 
 * @author lixiang
 * 
 */
public class RejectSendPaperAction extends LedgerBaseAction {

	private static final long serialVersionUID = 1L;
	private String paper_barcode;
	private String comment = "";
	private String reject_type = "";
	private String reject_ok = "N";

	public String getPaper_barcode() {
		return paper_barcode;
	}

	public void setPaper_barcode(String paper_barcode) {
		this.paper_barcode = paper_barcode.trim();
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
			String reject_code = "send_paper_" + System.currentTimeMillis();
			EntityPaper paper = ledgerService.getPaperByBarcode(paper_barcode);
			ProcessJob job = basicService.getProcessJobByCode(paper.getJob_code());

			RejectRecord record = new RejectRecord();
			record.setReject_code(reject_code);
			record.setEntity_barcode(paper.getPaper_barcode());
			record.setEntity_type("PAPER");
			record.setEntity_name(paper.getFile_title());
			record.setReject_time(new Date());
			record.setSend_user_iidd(job.getUser_iidd());
			record.setSend_dept_id(job.getDept_id());
			record.setReject_user_name(job.getOutput_user_name());
			record.setReject_dept_name(job.getOutput_dept_name());
			record.setReject_type(reject_type);
			record.setComment(comment);

			Map<String, Object> map = new HashMap<String, Object>();
			String paper_state = basicService.getSysConfigItemValue(SysConfigItem.KEY_SEND_REJECT_STATUS)
					.getItem_value();
			if ("0".equals(paper_state)) {// 拒收后留用
				map.put("paper_barcode", paper_barcode);
				map.put("paper_state", "0");
				// 记录生命周期用
				map.put("user_name", getCurUser().getUser_name());
				map.put("dept_name", getCurUser().getDept_name());
				map.put("job_code", job.getJob_code());
				ledgerService.rejectSendPaper(record, map);
			} else {// 拒收后已回收
				map.put("paper_id", paper.getPaper_id());
				// 记录生命周期用
				map.put("paper_barcode", paper_barcode);
				map.put("job_code", job.getJob_code());
				SecUser user = getCurUser();
				ledgerService.rejectSendPaperToRetrieved(record, map, user);
			}

			insertCommonLog("管理员[" + getCurUser().getUser_name() + "]外发拒收纸质载体[" + paper_barcode + "]");
			return "ok";
		} else
			return SUCCESS;

	}
}
