package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.DeptAdminConfig;
import hdsec.web.project.basic.model.NewSecRole;
import hdsec.web.project.user.model.SecUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 为用户（所有角色）配置管理部门
 * 
 * @author lixiang
 */
public class ConfigDeptForUserAction extends BasicBaseAction {

	private static final long serialVersionUID = 1L;
	private String user_iidd = "";
	private SecUser user = null;
	private List<NewSecRole> secRoleList = null;
	private String role_idString = "";
	private String urlString = "N";
	private DeptAdminConfig partTimeDept = null;

	public String getRole_idString() {
		return role_idString;
	}

	public void setRole_idString(String role_idString) {
		this.role_idString = role_idString;
	}

	public String getUrlString() {
		return urlString;
	}

	public void setUrlString(String urlString) {
		this.urlString = urlString;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getUser_iidd() {
		return user_iidd;
	}

	public String getUser_name() {
		return userService.getUserNameByUserId(user_iidd);
	}

	public SecUser getUser() {
		return user;
	}

	public List<NewSecRole> getSecRoleList() {
		return secRoleList;
	}

	public DeptAdminConfig getPartTimeDept() {
		return partTimeDept;
	}

	@Override
	public String executeFunction() throws Exception {
		// 获取兼职审批部门
		partTimeDept = basicService.getPartTimeDeptListByUserId(user_iidd);
		if (urlString.equals("Y")) {
			user = userService.getSecUserByUid(user_iidd);
			Map<String, String> map = new HashMap<String, String>();
			map.put("user_iidd", user_iidd);
			secRoleList = basicService.getRoleListByUser(map);
			for (NewSecRole role : secRoleList) {
				String role_id = role.getRole_id();
				DeptAdminConfig deptAdminConfig = basicService.getDeptAdminConfig(user_iidd, role_id);
				role.setDeptAdminConfig(deptAdminConfig);
			}
			List<NewSecRole> tempList = new ArrayList<NewSecRole>();
			for (NewSecRole newSecRole : secRoleList) {
				if (newSecRole.getRole_id().equals(role_idString)) {
					tempList.add(newSecRole);
					break;
				}
			}
			secRoleList = tempList;

			return "ok";
		} else {
			user = userService.getSecUserByUid(user_iidd);
			Map<String, String> map = new HashMap<String, String>();
			map.put("user_iidd", user_iidd);
			secRoleList = basicService.getRoleListByUser(map);
			for (NewSecRole role : secRoleList) {
				String role_id = role.getRole_id();
				DeptAdminConfig deptAdminConfig = basicService.getDeptAdminConfig(user_iidd, role_id);
				role.setDeptAdminConfig(deptAdminConfig);
			}
			return SUCCESS;
		}
	}
}
