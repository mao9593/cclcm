package hdsec.web.project.user.action;

import hdsec.web.project.common.util.Constants;
import hdsec.web.project.user.model.SecRole;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAdminRoleAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private List<SecRole> adminRoleList = null;
	private String subsys_code = "";
	private String user_iidd = "";
	private Integer cfgedAdminRoleId = null;
	
	public String getSubsys_code() {
		return subsys_code;
	}
	
	public void setSubsys_code(String subsys_code) {
		this.subsys_code = subsys_code;
	}
	
	public String getUser_iidd() {
		return user_iidd;
	}
	
	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}
	
	public List<SecRole> getAdminRoleList() {
		return adminRoleList;
	}
	
	public String getSubsys_name() {
		return userService.getSubsysBySubsysCode(subsys_code).getSubsys_name();
	}
	
	public Integer getCfgedAdminRoleId() {
		return cfgedAdminRoleId;
	}
	
	@Override
	public String executeFunction() throws Exception {
		//查询子系统内的三员角色
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subsys_code", subsys_code);
		map.put("role_type", Constants.ROLE_TYPE_READONLY);
		adminRoleList = userService.getAdminRoleBySubsys(map);
		map.put("user_iidd", user_iidd);
		//查询该用户已经分配的三员角色
		cfgedAdminRoleId = userService.getAdminRoleIdByUser(map);
		return SUCCESS;
	}
}
