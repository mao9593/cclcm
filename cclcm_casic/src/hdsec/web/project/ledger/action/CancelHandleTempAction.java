package hdsec.web.project.ledger.action;

import org.springframework.util.StringUtils;

/**
 * 取消其他任务处理申请
 * 
 * @author guojiao
 * 
 */
public class CancelHandleTempAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private String type = "";
	private String event_code = "";

	public void setType(String type) {
		this.type = type;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_code)) {
			ledgerService.cancelHandleTempById(event_code);
		}
		insertCommonLog("取消载体处理申请[" + event_code + "]");
		return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
	}
}