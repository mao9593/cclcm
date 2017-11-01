package hdsec.web.project.print.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 审批时 审批人退回某条打印作业申请
 * 
 * @author 郝佳
 * 
 */
public class RemarkPrintJobAction extends PrintBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String chkResult = "";
	private String type = "";

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

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	@Override
	public String executeFunction() throws Exception {
		int ret = 0;
		if (StringUtils.hasLength(event_code)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("event_code", event_code);
			map.put("is_cancel", 1);
			printService.remarkPrintEvent(map);
		}
		insertCommonLog("审批人退回打印作业审批申请[" + event_code + "]");

		return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
	}
}
