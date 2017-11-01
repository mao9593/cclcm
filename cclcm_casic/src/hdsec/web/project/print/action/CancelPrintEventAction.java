package hdsec.web.project.print.action;

import org.springframework.util.StringUtils;

/**
 * 删除打印作业/草稿
 * 
 * @author renmingfei
 * 
 */
public class CancelPrintEventAction extends PrintBaseAction {
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
			ret = printService.cancelPrintEventByEventCode(event_code);
		}
		setChkResult("ok:" + ret);
		insertCommonLog("取消打印作业审批申请[" + event_code + "]");
		return SUCCESS;
	}
}
