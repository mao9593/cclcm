package hdsec.web.project.user.action;

import hdsec.web.project.common.util.Constants;
import hdsec.web.project.user.model.SecRole;
import hdsec.web.project.user.model.SecSubsys;

import java.util.List;

/**
 * 添加角色
 * @author renmingfei
 *
 */
public class AddSecRoleAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private String role_name = ""; //角色名称
	private String role_desc = ""; //角色描述
	private String subsys_code = ""; //角色所属子系统编码
	private String roleExist = "N";
	private List<SecSubsys> subsysList = null;
	
	public String getRole_name() {
		return role_name;
	}
	
	public void setRole_name(String role_name) {
		this.role_name = role_name.trim();//去掉名称前后的空白字符
	}
	
	public String getSubsys_code() {
		return subsys_code;
	}
	
	public void setSubsys_code(String subsys_code) {
		this.subsys_code = subsys_code;
	}
	
	public List<SecSubsys> getSubsysList() {
		return subsysList;
	}
	
	public String getRoleExist() {
		return roleExist;
	}
	
	public void setRoleExist(String roleExist) {
		this.roleExist = roleExist;
	}
	
	public String getRole_desc() {
		return role_desc;
	}
	
	public void setRole_desc(String role_desc) {
		this.role_desc = role_desc;
	}
	
	@Override
	public String executeFunction() throws Exception {
		subsysList = userService.getAllSubsysAsCon();
		if (subsysList == null || subsysList.size() == 0) {
			logger.warn("------no subsys exists------");
			return "noSub";
		}
		if (role_name.equals("")) {//role_name为空说明是要进入添加角色的页面
			return "add";
		} else {
			if (userService.getRoleNameCount(role_name) > 0) {//名称已经存在
				setRoleExist("Y");
				return "add";
			}
			SecRole secRole = new SecRole();
			secRole.setRole_id(String.valueOf(System.currentTimeMillis()));
			secRole.setRole_name(role_name);
			secRole.setRole_desc(role_desc);
			secRole.setSubsys_code(subsys_code);
			secRole.setRole_type(Constants.ROLE_TYPE_COMMON);
			insertAdminLog("添加角色:" + role_name);
			userService.addSecRole(secRole);
		}
		return SUCCESS;
	}
}
