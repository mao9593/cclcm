package hdsec.web.project.user.action;

import hdsec.web.project.user.model.UserSecurity;

/**
 * 查看涉密人员类别详细信息
 * 
 * @author renmingfei
 * 
 */
public class ViewSecurityDetailAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private String security_code = "";
	private UserSecurity security = null;
	
	public UserSecurity getSecurity() {
		return security;
	}
	
	public void setSecurity_code(String security_code) {
		this.security_code = security_code;
	}
	
	@Override
	public String executeFunction() throws Exception {
		security = userService.getSecurityByCode(security_code);
		return SUCCESS;
	}
}
