package hdsec.web.project.user.action;

import hdsec.web.project.common.util.Constants;
import hdsec.web.project.user.model.SecRole;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 进入用户角色配置列表页面
 * @author renmingfei
 *
 */
public class ManageUserRoleAction extends UserBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String user_iidd = "";
	private String subsys_code = "";
	private List<SecRole> cfgedRoleList = null;
	private List<SecRole> allRoleList = null;
	
	public String getUser_iidd() {
		return user_iidd;
	}
	
	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}
	
	public String getSubsys_code() {
		return subsys_code;
	}
	
	public void setSubsys_code(String subsys_code) {
		this.subsys_code = subsys_code;
	}
	
	public String getSubsys_name() {
		return userService.getSubsysBySubsysCode(subsys_code).getSubsys_name();
	}
	
	public List<SecRole> getCfgedRoleList() {
		return cfgedRoleList;
	}
	
	public List<SecRole> getAllRoleList() {
		return allRoleList;
	}
	
	@Override
	public String executeFunction() throws Exception {
		//生成角色选择
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_iidd", user_iidd);
		map.put("subsys_code", subsys_code);
		map.put("role_type", String.valueOf(Constants.ROLE_TYPE_COMMON));
		cfgedRoleList = userService.getRoleListByUserAndSubsys(map);
		allRoleList = userService.getSecRoleBySubsys(map);
		//cfgedRoleList = userService.getRoleListByUser(map);
		return SUCCESS;
	}
}
