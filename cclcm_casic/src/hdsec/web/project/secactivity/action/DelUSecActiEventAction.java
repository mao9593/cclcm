package hdsec.web.project.secactivity.action;

/**
 * 撤销用户涉密活动申请
 * 
 * @author gj
 */
public class DelUSecActiEventAction extends SecuActiBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String type = "";

	public void setType(String type) {
		this.type = type;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	@Override
	public String executeFunction() throws Exception {
		secactivityservice.delUSecActiEventByEventCode(event_code);
		insertCommonLog("删除涉密活动申请作业[" + event_code);
		return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
	}
}
