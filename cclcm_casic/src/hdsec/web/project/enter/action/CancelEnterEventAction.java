package hdsec.web.project.enter.action;

import org.springframework.util.StringUtils;

/**
 * 取消录入作业审批申请 2014-5-15 上午8:51:10
 * 
 * @author gaoximin
 */
public class CancelEnterEventAction extends EnterBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String chkResult = "";
	private String type = "";

	public String getChkResult() {
		return chkResult;
	}

	public void setChkResult(String chkResult) {
		this.chkResult = chkResult;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String executeFunction() throws Exception {
		int ret = 0;
		if (StringUtils.hasLength(event_code)) {
			ret = enterService.cancelEnterEventByEventCode(event_code);
		}
		if (ret == 0) {
			chkResult = "hasEvents";
		} else {
			if (type.equalsIgnoreCase("SCAN")) {
				chkResult = "noEvent:SCAN";
			} else {
				chkResult = "noEvent:LEADIN";
			}
		}
		insertCommonLog("取消录入作业审批申请[" + event_code + "]");
		return SUCCESS;
	}
}
