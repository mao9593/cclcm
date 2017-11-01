package hdsec.web.project.ledger.action;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 
 * @author yueying
 * 
 */
public class HandleCDAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_codes;
	
	@Override
	public String executeFunction() throws Exception {
		String[] ids = event_codes.split(",");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cd_ids", ids);
		map.put("retrieve_user_id", getCurUser().getUser_iidd());
		ledgerService.updateHandleCDs(map);
		insertCommonLog("归档光盘id[" + ids + "]");
		return SUCCESS;
	}
	
	public String getEvent_codes() {
		return event_codes;
	}
	
	public void setEvent_codes(String event_codes) {
		this.event_codes = event_codes;
	}
	
}
