package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecRole;
import hdsec.web.project.user.model.SecSubsys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 查询并管理角色列表
 * 
 * @author renmingfei
 * 
 */
public class ManageSecRoleAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private String role_name = ""; // 角色名称
	private String subsys_code = ""; // 角色所属子系统编码
	private List<SecSubsys> subsysList = null;
	private List<SecRole> secRoleList = null;
	
	public String getRole_name() {
		return role_name;
	}
	
	public void setRole_name(String role_name) {
		this.role_name = role_name;
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
	
	@Override
	public String executeFunction() throws Exception {
		subsysList = userService.getAllSubsysAsCon();
		if (subsysList == null || subsysList.size() == 0) {
			logger.warn("------no subsys exists------");
			return "noSub";
		}
		if (!StringUtils.hasLength(subsys_code)) {
			subsys_code = subsysList.get(0).getSubsys_code();
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("role_name", role_name);
		map.put("subsys_code", subsys_code);
		secRoleList = userService.getSecRole(map);
		return SUCCESS;
	}
	
	public List<SecRole> getSecRole() {
		return secRoleList;
	}
}
