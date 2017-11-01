package hdsec.web.project.user.action;

import hdsec.web.project.common.util.Constants;

import org.springframework.util.StringUtils;

/**
 * 配置特殊角色的部门作用域
 * @author renmingfei
 *
 */
public class ConfigScopeDeptAction extends UserBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String user_iidd = "";
	private String role_id = null;
	private String deptCodeList = "";
	
	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}
	
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	
	public void setDeptCodeList(String deptCodeList) {
		this.deptCodeList = deptCodeList;
	}
	
	@Override
	public String executeFunction() throws Exception {
		String domain = userService.getDomainByUserAndRole(user_iidd, role_id);
		if (!StringUtils.hasLength(domain)) {
			throw new Exception("Domain值为空！");
		} else {
			getRequest().removeAttribute(Constants.XML_HTTP_RESULT_KEY);
			userService.updateScopeMemberCode(domain, deptCodeList);
			getRequest().setAttribute(Constants.XML_HTTP_RESULT_KEY, Boolean.TRUE);
		}
		return SUCCESS;
	}
}
