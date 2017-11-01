package hdsec.web.project.ledger.action;

import java.util.HashMap;
import java.util.Map;

/**
 * paper归档
 * 
 * @author yueying
 * 
 */
public class HandlePaperAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_codes;
	
	@Override
	public String executeFunction() throws Exception {
		String[] ids = event_codes.split(",");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paper_ids", ids);
		map.put("retrieve_user_id", getCurUser().getUser_iidd());
		ledgerService.updateHandlePapers(map);
		insertCommonLog("归档paper id[" + ids + "]");
		return SUCCESS;
	}
	
	public String getEvent_codes() {
		return event_codes;
	}
	
	public void setEvent_codes(String event_codes) {
		this.event_codes = event_codes;
	}
	
}
