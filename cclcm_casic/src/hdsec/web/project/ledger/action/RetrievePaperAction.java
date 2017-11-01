package hdsec.web.project.ledger.action;

import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.user.model.SecUser;

import org.springframework.util.StringUtils;

/**
 * 回收纸质：更新状态、回收人，全生命周期记录，交接确认
 * 
 * @author yueying
 */

public class RetrievePaperAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_codes;
	private String comment = "";

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getEvent_codes() {
		return event_codes;
	}

	public void setEvent_codes(String event_codes) {
		this.event_codes = event_codes;
	}

	@Override
	public String executeFunction() throws Exception {
		SecUser user = getCurUser();
		for (String id : event_codes.split(",")) {
			if (StringUtils.hasLength(id)) {
				ledgerService.submitRetrievePaper(id, user);
				ledgerService.updateRetrieveComment(id, comment);
				EntityPaper paper = ledgerService.getPaperById(id);
				insertCommonLog("回收文件：条码[" + paper.getPaper_barcode() + "]");
			}
		}
		return SUCCESS;
	}
}
