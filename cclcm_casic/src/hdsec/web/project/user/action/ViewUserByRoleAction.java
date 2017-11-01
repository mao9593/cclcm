package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecUser;

import java.util.List;

/**
 * 查询跟某角色关联的用户
 * @author renmingfei
 *
 */
public class ViewUserByRoleAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private String role_id = null;
	private String role_name = "";
	private List<SecUser> secUserList = null;
	
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	
	public String getRole_id() {
		return role_id;
	}

	public String getRole_name() {
		return role_name;
	}
	
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
	@Override
	public String executeFunction() throws Exception {
		secUserList = userService.getUserByRole(role_id);
		setRole_name(userService.getSecRoleByRoleId(role_id).getRole_name());
		return SUCCESS;
	}
	
	public List<SecUser> getSecUser() {
		return secUserList;
	}
}
