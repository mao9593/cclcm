package hdsec.web.project.change.action;

import org.springframework.util.StringUtils;

/**
 * 删除载体归属转换作业
 * 
 * @author lixiang
 * 
 */
public class CancelChangeEventAction extends ChangeBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String chkResult;

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getChkResult() {
		return chkResult;
	}

	public void setChkResult(String chkResult) {
		this.chkResult = chkResult;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_code)) {
			int remainSize = changeService.cancelChangeEventByCode(event_code);
			if (remainSize >= 1) {
				chkResult = "hasEvents";
				return SUCCESS;
			} else {
				chkResult = "noEvent";
			}
		}
		insertCommonLog("取消载体归属转换作业审批申请[" + event_code + "]");
		return SUCCESS;
	}
}
