package hdsec.web.project.publicity.action;

/**
 * 撤销宣传报道审批申请
 * 
 * @author LS
 */
public class DelReportEventAction extends PublicityBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String type = "";
	private String apply_type = "";

	public void setType(String type) {
		this.type = type;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getApply_type() {
		return apply_type;
	}

	public void setApply_type(String apply_type) {
		this.apply_type = apply_type;
	}

	@Override
	public String executeFunction() throws Exception {
		publicityService.delUPublReportEventByEventCode(event_code, apply_type);
		insertCommonLog("删除宣传报道申请作业[" + event_code);
		return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
	}
}
