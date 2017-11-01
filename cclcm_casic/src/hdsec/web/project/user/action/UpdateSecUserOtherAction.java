package hdsec.web.project.user.action;

import hdsec.web.project.basic.model.DeptAdminConfig;
import hdsec.web.project.basic.model.NewSecRole;
import hdsec.web.project.user.model.SecSubsys;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.model.SecUserDept;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 修改用户时用于展示用户部门和角色信息
 * @author renmingfei
 *
 */
public class UpdateSecUserOtherAction extends UserBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String user_iidd = "";
	private SecUserDept userDept = null;
	private List<NewSecRole> roleList = null;
	private List<SecSubsys> secSubsyslist = null;
	private List<Map<String, Object>> secRoleMap = null;
	private SecUser user;
	private List<NewSecRole> secRoleList = null;
	
	public List<NewSecRole> getSecRoleList() {
		return secRoleList;
	}

	public SecUser getUser() {
		return user;
	}

	public void setUser(SecUser user) {
		this.user = user;
	}

	public String getUser_iidd() {
		return user_iidd;
	}
	
	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}
	
	public SecUserDept getUserDept() {
		return userDept;
	}
	
	public List<NewSecRole> getRoleList() {
		return roleList;
	}
	
	public List<SecSubsys> getSecSubsyslist() {
		return secSubsyslist;
	}
	
	public List<Map<String, Object>> getSecRoleMap() {
		return secRoleMap;
	}
	
	public String getCurUser() {
		return getSecUserFromSession().getUser_iidd();
	}
	
	@Override
	public String executeFunction() throws Exception {
		//查询用户的主部门
		userDept = userService.getUserMainDeptByUser(user_iidd);
		secSubsyslist = userService.getAllSubsysAsCon();
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_iidd", user_iidd);
		//map.put("role_type", String.valueOf(Constants.ROLE_TYPE_COMMON));
		secRoleList = basicService.getRoleListByUser(map);
		secRoleMap = new ArrayList<Map<String, Object>>();
		for (SecSubsys secSubsys : secSubsyslist) {
			Map<String, Object> tempMap = new HashMap<String, Object>();
			List<NewSecRole> tempRoleList = new ArrayList<NewSecRole>();
			for (NewSecRole secRole : secRoleList) {
				if (secRole.getSubsys_code().equalsIgnoreCase(secSubsys.getSubsys_code())) {
					tempRoleList.add(secRole);
				}
			}
			tempMap.put("subsys_code", secSubsys.getSubsys_code());
			tempMap.put("secRoleList", tempRoleList);
			secRoleMap.add(tempMap);
		}
		user = userService.getSecUserByUid(user_iidd);
		for(NewSecRole role : secRoleList){
			String role_id = role.getRole_id();
			DeptAdminConfig deptAdminConfig = basicService.getDeptAdminConfig(user_iidd, role_id);
			role.setDeptAdminConfig(deptAdminConfig);
		}
		return SUCCESS;
	}
}
