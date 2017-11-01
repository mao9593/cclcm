package hdsec.web.project.user.action;

import hdsec.web.project.common.util.Constants;
import hdsec.web.project.user.model.SecTerritory;

import java.util.List;

/**
 * 配置特殊角色的地区作用域
 * @author renmingfei
 *
 */
public class ConfigScopeTerrAction extends UserBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String user_iidd = "";
	private String role_id = null;
	private String domain = "";
	private String config = "N";
	private String terrCodes = "";
	private List<SecTerritory> secTerrList = null;
	private List<String> cfgedTerrCodeList = null;
	
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
	
	public List<SecTerritory> getSecTerrList() {
		return secTerrList;
	}
	
	public List<String> getCfgedTerrCodeList() {
		return cfgedTerrCodeList;
	}
	
	public String getSubsys_name() {
		return userService.getSubsysNameByRoleId(role_id);
	}
	
	public String getRole_name() {
		return userService.getSecRoleByRoleId(role_id).getRole_name();
	}
	
	public String getUser_name() {
		return userService.getSecUserByUid(user_iidd).getUser_name();
	}
	
	public String getDomain() {
		return domain;
	}
	
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	public void setConfig(String config) {
		this.config = config;
	}
	
	public void setTerrCodes(String terrCodes) {
		this.terrCodes = terrCodes;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (config.equalsIgnoreCase("Y")) {
			getRequest().removeAttribute(Constants.XML_HTTP_RESULT_KEY);
			userService.updateScopeMemberCode(domain, terrCodes);
			getRequest().setAttribute(Constants.XML_HTTP_RESULT_KEY, Boolean.TRUE);
			return "config";
		} else {
			domain = userService.getDomainByUserAndRole(user_iidd, role_id);
			cfgedTerrCodeList = userService.getScopeMemberCode(domain);
			secTerrList = userService.getAllSecTerritory();
			return SUCCESS;
		}
	}
}
