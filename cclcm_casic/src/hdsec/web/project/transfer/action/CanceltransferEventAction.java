package hdsec.web.project.transfer.action;

import org.springframework.util.StringUtils;

/**
 * 删除流转作业
 * 
 * @author yy
 * 
 */
public class CanceltransferEventAction extends TransferBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String type;
	private String chkResult;

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_code)) {
			int remainSize = transferService.cancelTransferEventByEventCode(event_code, type);
			if (remainSize >= 1) {
				chkResult = "hasEvents";
				return SUCCESS;
			} else {
				chkResult = "noEvent:";
			}
		}
		insertCommonLog("取消流转作业审批申请[" + event_code + "]");
		if ("cd".equals(type)) {
			chkResult += "cd";
		} else {
			chkResult += "paper";
		}
		return SUCCESS;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getChkResult() {
		return chkResult;
	}

	public void setChkResult(String chkResult) {
		this.chkResult = chkResult;
	}
}
