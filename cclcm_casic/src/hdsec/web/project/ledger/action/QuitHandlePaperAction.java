package hdsec.web.project.ledger.action;

import org.springframework.util.StringUtils;

/**
 * 退回文件回收申请
 * 
 * @author lixiang
 * 
 */
public class QuitHandlePaperAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private String paper_id = "";
	private String type = "";
	private String event_codes;
	private String comment = "";

	public void setType(String type) {
		this.type = type;
	}

	public void setPaper_id(String paper_id) {
		this.paper_id = paper_id;
	}

	public String getEvent_codes() {
		return event_codes;
	}

	public void setEvent_codes(String event_codes) {
		this.event_codes = event_codes;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String executeFunction() throws Exception {
		for (String id : event_codes.split(",")) {
			if (StringUtils.hasLength(id)) {
				ledgerService.quitHandlePaperById(id, getCurUser());
				ledgerService.updateRejectPaperComment(id, comment);
				insertCommonLog("退回文件回收申请[" + paper_id + "]");
			}
		}
		return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
	}
}
