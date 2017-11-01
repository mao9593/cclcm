package hdsec.web.project.device.action;

import org.springframework.util.StringUtils;

/**
 * 取消磁介质借用审批申请
 * 
 * @author lixiang
 * 
 */
public class CancelDeviceEventAction extends DeviceBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String chkResult = "";
	
	public String getChkResult() {
		return chkResult;
	}
	
	public void setChkResult(String chkResult) {
		this.chkResult = chkResult;
	}
	
	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}
	
	@Override
	public String executeFunction() throws Exception {
		int ret = 0;
		if (StringUtils.hasLength(event_code)) {
			ret = deviceService.cancelDeviceEventByEventCode(event_code);
		}
		setChkResult("ok:" + ret);
		insertCommonLog("取消磁介质借用审批申请[" + event_code + "]");
		return SUCCESS;
	}
}
