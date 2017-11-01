package hdsec.web.project.user.action;

/**
 * 删除涉密人员类别
 * 
 * @author renmingfei
 * 
 */
public class DelSecurityAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private String security_code = null;
	
	public void setSecurity_code(String security_code) {
		this.security_code = security_code;
	}
	
	@Override
	public String executeFunction() throws Exception {
		String security_name = userService.getSecurityByCode(security_code).getSecurity_name();
		userService.delSecurityByCode(security_code);
		insertAdminLog("删除涉密人员类别:" + security_name);
		return SUCCESS;
	}
}
