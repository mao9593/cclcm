package hdsec.web.project.user.action;

import hdsec.web.project.common.util.Constants;
import hdsec.web.project.user.model.SecLevel;
import hdsec.web.project.user.model.UserSecurity;

import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 配置涉密人员类别
 * 
 * @author renmingfei
 * 
 */
public class ConfigSecurityAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private String security_code = "";
	private UserSecurity security = null;
	private String security_name = "";
	private String security_desc = "";
	private String print_value = "";
	private String copy_value = "";
	private String import_value = "";
	private String burn_value = "";
	private String copy_burn_value = "";
	private String device_value = "";
	private String default_value = "";
	private String config = "N";

	public void setSecurity_name(String security_name) {
		this.security_name = security_name;
	}

	public void setSecurity_desc(String security_desc) {
		this.security_desc = security_desc;
	}

	public void setPrint_value(String print_value) {
		if (StringUtils.hasLength(print_value)) {
			this.print_value = Constants.COMMON_SEPARATOR + print_value.replaceAll(" ", "").replaceAll(",", Constants.COMMON_SEPARATOR)
					+ Constants.COMMON_SEPARATOR;
		}
	}

	public void setCopy_value(String copy_value) {
		if (StringUtils.hasLength(copy_value)) {
			this.copy_value = Constants.COMMON_SEPARATOR + copy_value.replaceAll(" ", "").replaceAll(",", Constants.COMMON_SEPARATOR)
					+ Constants.COMMON_SEPARATOR;
		}
	}

	public void setImport_value(String import_value) {
		if (StringUtils.hasLength(import_value)) {
			this.import_value = Constants.COMMON_SEPARATOR + import_value.replaceAll(" ", "").replaceAll(",", Constants.COMMON_SEPARATOR)
					+ Constants.COMMON_SEPARATOR;
		}
	}

	public void setBurn_value(String burn_value) {
		if (StringUtils.hasLength(burn_value)) {
			this.burn_value = Constants.COMMON_SEPARATOR + burn_value.replaceAll(" ", "").replaceAll(",", Constants.COMMON_SEPARATOR)
					+ Constants.COMMON_SEPARATOR;
		}
	}

	public void setCopy_burn_value(String copy_burn_value) {
		if (StringUtils.hasLength(copy_burn_value)) {
			this.copy_burn_value = Constants.COMMON_SEPARATOR
					+ copy_burn_value.replaceAll(" ", "").replaceAll(",", Constants.COMMON_SEPARATOR) + Constants.COMMON_SEPARATOR;
		}
	}

	public void setDevice_value(String device_value) {
		if (StringUtils.hasLength(device_value)) {
			this.device_value = Constants.COMMON_SEPARATOR + device_value.replaceAll(" ", "").replaceAll(",", Constants.COMMON_SEPARATOR)
					+ Constants.COMMON_SEPARATOR;
		}
	}

	public void setDefault_value(String default_value) {
		if (StringUtils.hasLength(default_value)) {
			this.default_value = Constants.COMMON_SEPARATOR + default_value.replaceAll(" ", "").replaceAll(",", Constants.COMMON_SEPARATOR)
					+ Constants.COMMON_SEPARATOR;
		}
	}

	public UserSecurity getSecurity() {
		return security;
	}

	public void setSecurity_code(String security_code) {
		this.security_code = security_code;
	}

	public void setConfig(String config) {
		this.config = config;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	@Override
	public String executeFunction() throws Exception {
		if (config.equalsIgnoreCase("Y")) {
			security = new UserSecurity(security_code, security_name, security_desc, print_value, copy_value, import_value, burn_value,
					copy_burn_value, device_value, default_value);
			userService.updateSecurity(security);
			return "ok";
		} else {
			security = userService.getSecurityByCode(security_code);
			return SUCCESS;
		}
	}
}
