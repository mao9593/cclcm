package hdsec.web.project.basic.action;

import hdsec.web.project.user.model.SecRoleUser;

import java.util.HashMap;
import java.util.Map;


public class ConfigUserForRoleAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String update="N";
	private String proxy_user_iidd;
	private String role_id;
	
	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getProxy_user_iidd() {
		return proxy_user_iidd;
	}

	public void setProxy_user_iidd(String proxy_user_iidd) {
		this.proxy_user_iidd = proxy_user_iidd;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	@Override
	public String executeFunction() throws Exception {
		if (update.equals("Y")) {
			SecRoleUser secRoleUser = new SecRoleUser(proxy_user_iidd, role_id);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_iidd", proxy_user_iidd);
			map.put("role_id", role_id);
			if (basicService.checkRole(map)==0) {
				basicService.addUserRoleRel(secRoleUser);
			}
			return "ok";
		}else {
			return SUCCESS;
		}
	}
	
}
