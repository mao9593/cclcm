package hdsec.web.project.user.action;

/**
 * 删除角色
 * @author renmingfei
 *
 */
public class DelSecRoleAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private String role_id = null; //角色ID
	private String subsys_code = "";
	
	public String getRole_id() {
		return role_id;
	}
	
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	
	public String getSubsys_code() {
		return subsys_code;
	}
	
	public void setSubsys_code(String subsys_code) {
		this.subsys_code = subsys_code;
	}
	
	@Override
	public String executeFunction() throws Exception {
		userService.delSecRole(role_id);
		return SUCCESS;
	}
}
