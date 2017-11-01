package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.DeptAdminConfig;
import hdsec.web.project.user.model.SecUser;

import java.util.HashMap;

/**
 * 为用户的某个角色配置管理部门
 * 
 * @author lixiang
 */
public class ConfigDeptByRoleAction extends BasicBaseAction {

	private static final long serialVersionUID = 1L;
	private String user_iidd = "";
	private String role_id = "";
	private String dept_ids = "";
	private DeptAdminConfig adminConfig = null;
	private String result = "";

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getDept_ids() {
		return dept_ids;
	}

	public void setDept_ids(String dept_ids) {
		this.dept_ids = dept_ids.replaceAll(" ", "");
	}

	public DeptAdminConfig getAdminConfig() {
		return adminConfig;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String executeFunction() throws Exception {

		if (role_id.equals("110")) {
			SecUser tempuser = userService.getSecUserByUid(user_iidd);
			String tempusername = tempuser.getUser_name();
			if (!tempusername.contains("DMA")) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("user_name", tempusername + "_DMA");
				map.put("user_iidd", user_iidd);
				userService.updateSecUserName(map);
			}
		}
		adminConfig = new DeptAdminConfig(user_iidd, role_id, dept_ids, "");
		basicService.configDeptAdmin(adminConfig);

		insertAdminLog("为" + userService.getUserNameByUserId(user_iidd) + "的"
				+ userService.getRoleNameByRoleId(role_id) + "角色配置部门");
		setResult("配置完成");
		return SUCCESS;
	}
}
