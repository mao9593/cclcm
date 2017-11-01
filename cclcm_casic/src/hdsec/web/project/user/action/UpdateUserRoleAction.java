package hdsec.web.project.user.action;

import org.springframework.util.StringUtils;

/**
 * 修改用户角色
 * 
 * @author renmingfei
 * 
 */
public class UpdateUserRoleAction extends UserBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String newCodes = "";
	private String user_iidd = "";
	private String subsys_code = "";
	
	public void setNewCodes(String newCodes) {
		this.newCodes = newCodes.replaceAll(" ", "");
	}
	
	public String getUser_iidd() {
		return user_iidd;
	}
	
	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}
	
	public void setSubsys_code(String subsys_code) {
		this.subsys_code = subsys_code;
	}
	
	@Override
	public String executeFunction() throws Exception {
		// 执行更新
		userService.updateUserRole(user_iidd, subsys_code, newCodes);
		String newNames = "";
		for (String item : newCodes.split(",")) {
			if (StringUtils.hasLength(item.trim())) {
				newNames += "," + userService.getRoleNameByRoleId(item.trim());
			}
		}
		insertAdminLog("分配用户角色:" + user_iidd + newNames);
		return SUCCESS;
	}
	
}
