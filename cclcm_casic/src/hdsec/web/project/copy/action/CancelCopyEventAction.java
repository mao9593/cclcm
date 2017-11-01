package hdsec.web.project.copy.action;

import org.springframework.util.StringUtils;

/**
 * 取消复印作业审批申请
 * 
 * @author lixiang
 * 
 */
public class CancelCopyEventAction extends CopyBaseAction {
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
			ret = copyService.cancelCopyEventByEventCode(event_code);
		}
		setChkResult("ok:" + ret);
		insertCommonLog("取消复印作业审批申请[" + event_code + "]");
		return SUCCESS;
	}
}
