package hdsec.web.project.securityuser.action;

/**
 * 撤销用户等级变更申请
 * 
 * @author gaoximin 2015-6-10
 */
public class DelUSeclvChangeEventAction extends SecurityUserBaseAction {
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
		securityUserService.delUSecChangeEventByEventCode(event_code);
		insertCommonLog("删除用户等级变更申请作业[" + event_code);
		return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
	}
}
