package hdsec.web.project.user.action;

import hdsec.web.project.user.model.UserSecurity;

import java.util.List;

/**
 * 查询并管理涉密人员类别列表
 * 
 * @author renmingfei
 * 
 */
public class ManageSecurityAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private List<UserSecurity> securityList = null;
	
	public List<UserSecurity> getSecurityList() {
		return securityList;
	}
	
	@Override
	public String executeFunction() throws Exception {
		securityList = userService.getSecurityList();
		return SUCCESS;
	}
	
}
