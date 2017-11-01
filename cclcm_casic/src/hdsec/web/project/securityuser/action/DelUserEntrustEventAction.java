package hdsec.web.project.securityuser.action;

/**
 * 撤销用户委托保密管理申请
 * 
 * @author gj
 */
public class DelUserEntrustEventAction extends SecurityUserBaseAction {
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
		securityUserService.delUserEntrustEventByEventCode(event_code);
		insertCommonLog("删除用户委托保密管理申请作业[" + event_code);
		return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
	}
}
