package hdsec.web.project.ledger.action;

import org.springframework.util.StringUtils;

/**
 * 取消载体变更处理申请
 * 
 * @author congxue 2015-4-30
 */

public class CancelHandleLedgerAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private String type = "";
	private String event_code = "";
	private String entity_type = "";

	public void setType(String type) {
		this.type = type;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getEntity_type() {
		return entity_type;
	}

	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_code)) {
			if (entity_type.equals("Paper")) {
				ledgerService.cancelHandleLedgerById(event_code);
			} else {
				ledgerService.cancelHandleCDLedgerById(event_code);
			}
		}
		insertCommonLog("取消纸质载体处理申请[" + event_code + "]");
		return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
	}
}
