package hdsec.web.project.user.action;

import hdsec.web.project.user.model.DeptAdminConfig;

/**
 * 配置部门管理员管理的部门
 * 2014-4-12 上午10:19:35
 * 
 * @author renmingfei
 */
public class ConfigDeptAdminAction extends UserBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String user_iidd = "";
	private String dept_ids = "";
	private String config = "N";
	private String is_inherit = "N";
	private DeptAdminConfig adminConfig = null;
	
	public void setDept_ids(String dept_ids) {
		this.dept_ids = dept_ids.replaceAll(" ", "");
	}
	
	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}
	
	public String getUser_iidd() {
		return user_iidd;
	}
	
	public void setConfig(String config) {
		this.config = config;
	}
	
	public String getConfig() {
		return config;
	}
	
	public DeptAdminConfig getAdminConfig() {
		return adminConfig;
	}
	
	public String getUser_name() {
		return userService.getUserNameByUserId(user_iidd);
	}
	
	public void setIs_inherit(String is_inherit) {
		this.is_inherit = is_inherit;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (config.equalsIgnoreCase("Y")) {
			adminConfig = new DeptAdminConfig(user_iidd, dept_ids, "", is_inherit);
			userService.configDeptAdmin(adminConfig);
			insertAdminLog("配置部门管理员：" + userService.getUserNameByUserId(user_iidd));
		}
		adminConfig = userService.getDeptAdminConfig(user_iidd);
		return SUCCESS;
	}
}
