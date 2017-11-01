package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecRole;
import hdsec.web.project.user.model.SecSubsys;

import java.util.HashMap;
import java.util.Map;

/**
 * 修改角色
 * @author renmingfei
 *
 */
public class UpdateSecRoleAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private String role_id = null;//角色ID
	private String role_name = ""; //角色名称
	private String role_desc = ""; //角色描述
	private SecRole secRole = null;
	private String o_role_name = "";//原角色名称，用于与修改后的角色名称比较
	private String roleExist = "N";
	private String subsys_name = "";//子系统名称，用于展示，不可修改
	
	public void setRole_name(String role_name) {
		this.role_name = role_name.trim();//去掉名称前后的空白字符
	}
	
	public String getRole_name() {
		return role_name;
	}
	
	public void setRole_desc(String role_desc) {
		this.role_desc = role_desc;
	}
	
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	
	public SecRole getSecRole() {
		return secRole;
	}
	
	public void setRoleExist(String roleExist) {
		this.roleExist = roleExist;
	}
	
	public String getRoleExist() {
		return roleExist;
	}
	
	public void setO_role_name(String o_role_name) {
		this.o_role_name = o_role_name;
	}
	
	public String getSubsys_name() {
		return subsys_name;
	}
	
	public void setSubsys_name(String subsys_name) {
		this.subsys_name = subsys_name;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (role_name.equals("")) {//role_name为空说明是要进入修改角色的页面
			secRole = userService.getSecRoleByRoleId(role_id);
			SecSubsys subsys = userService.getSubsysBySubsysCode(secRole.getSubsys_code());
			setSubsys_name(subsys.getSubsys_name());
			return "update";
		} else {
			if (!o_role_name.equals(role_name) && userService.getRoleNameCount(role_name) > 0) {//如果名称被修改过，且名称已经存在
				secRole = userService.getSecRoleByRoleId(role_id);
				setRoleExist("Y");
				return "update";
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("role_id", String.valueOf(role_id));
			map.put("role_name", role_name);
			map.put("role_desc", role_desc);
			userService.updateSecRole(map);
		}
		return SUCCESS;
	}
}
