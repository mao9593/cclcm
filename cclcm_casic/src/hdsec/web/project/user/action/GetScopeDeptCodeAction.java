package hdsec.web.project.user.action;

import hdsec.web.project.common.util.Constants;

import org.springframework.util.StringUtils;

/**
 * 为特殊角色配置部门的时候，查询已经配置的部门CODE列表
 * @author renmingfei
 *
 */
public class GetScopeDeptCodeAction extends UserBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String user_iidd = "";
	private String role_id = null;
	private String deptCodes = "";
	
	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}
	
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	
	@Override
	public String executeFunction() throws Exception {
		String domain = userService.getDomainByUserAndRole(user_iidd, role_id);
		if (!StringUtils.hasLength(domain)) {
			throw new Exception("Domain值为空！");
		} else {
			getRequest().removeAttribute(Constants.XML_HTTP_RESULT_KEY);
			deptCodes = StringUtils.collectionToCommaDelimitedString(userService.getScopeMemberCode(domain));
			getRequest().setAttribute(Constants.XML_HTTP_RESULT_KEY, deptCodes);
		}
		return SUCCESS;
	}
}
