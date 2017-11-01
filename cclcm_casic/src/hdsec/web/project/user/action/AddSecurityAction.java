package hdsec.web.project.user.action;

import hdsec.web.project.user.model.UserSecurity;

import java.util.UUID;

import org.springframework.util.StringUtils;

/**
 * 添加涉密人员类别
 * @author renmingfei
 *
 */
public class AddSecurityAction extends UserBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String security_name = "";
	private String security_desc = "";
	
	public void setSecurity_name(String security_name) {
		this.security_name = security_name.trim();
	}
	
	public void setSecurity_desc(String security_desc) {
		this.security_desc = security_desc;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(security_name)) {
			String security_code = "scrt-" + UUID.randomUUID().toString();
			UserSecurity security = new UserSecurity(security_code, security_name, security_desc);
			userService.addSecurity(security);
			return "ok";
		} else {
			return SUCCESS;
		}
	}
}
