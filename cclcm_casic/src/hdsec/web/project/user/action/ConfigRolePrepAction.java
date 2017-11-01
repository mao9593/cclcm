package hdsec.web.project.user.action;

import hdsec.web.project.common.util.Constants;
import hdsec.web.project.user.model.SecOperation;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.session.SessionACL;
import hdsec.web.project.user.session.SessionOper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

/**
 * 说明:角色预配置操作action
 * 
 * @author renmingfei
 * 
 */
public class ConfigRolePrepAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private String role_id = null;
	private String role_name = "";
	private List<SecOperation> allOper = null;
	private SessionACL sessionACL = null;
	private List<String> configedOperCode = null;
	private SecUser user = null;

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public List<SecOperation> getAllOper() {
		return allOper;
	}

	public void setAllOper(List<SecOperation> allOper) {
		this.allOper = allOper;
	}

	public SessionACL getSessionACL() {
		return sessionACL;
	}

	public void setSessionACL(SessionACL sessionACL) {
		this.sessionACL = sessionACL;
	}

	public List<String> getConfigedOperCode() {
		return configedOperCode;
	}

	public void setConfigedOperCode(List<String> configedOperCode) {
		this.configedOperCode = configedOperCode;
	}

	@Override
	public String executeFunction() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();
		user = (SecUser) request.getSession().getAttribute(Constants.SESSION_USER_KEY);
		String subsys_code = userService.getSubsysCodeByRoleId(role_id);
		if (user.getUser_iidd().equals("admin")) {
			allOper = userService.getSecOperBySubsysAll(subsys_code);
		} else {
			allOper = userService.getSecOperBySubsys(subsys_code);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("subsys_code", subsys_code);
		map.put("role_id", String.valueOf(role_id));
		configedOperCode = userService.getConfigedOperByRoleAndSubsys(map);
		setRole_name(userService.getSecRoleByRoleId(role_id).getRole_name());
		sessionACL = new SessionACL(subsys_code, role_id, role_name);
		for (SecOperation item : allOper) {
			SessionOper sOper = new SessionOper(item);
			sOper.setAuthoritedAtInit(configedOperCode.contains(sOper.getOperCode()));
			sessionACL.addOperation(sOper);
		}
		// HttpServletRequest request = getRequest();
		request.getSession().setAttribute(Constants.SESSION_ACL_KEY, sessionACL);
		return SUCCESS;
	}
}
