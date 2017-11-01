package hdsec.web.project.arch.action;

import org.springframework.util.StringUtils;

/**
 * @author hd304 撤销档案借阅申请 2015-8-3/
 */
public class CancelArchEventAction extends ArchBaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String event_code;
	private String chkResult = "";

	@Override
	public String executeFunction() throws Exception {
		int ret = 0;
		if (StringUtils.hasLength(event_code)) {
			ret = archService.cancelArchEventByEventCode(event_code);
		}
		if (ret == 0) {
			chkResult = "hasEvents";
		} else {
			chkResult = "noEvent";
		}
		insertCommonLog("取消档案借阅审批申请[" + event_code + "]");
		return SUCCESS;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getChkResult() {
		return chkResult;
	}

	public void setChkResult(String chkResult) {
		this.chkResult = chkResult;
	}

}
