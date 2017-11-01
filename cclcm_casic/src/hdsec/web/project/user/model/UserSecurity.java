package hdsec.web.project.user.model;

import hdsec.web.project.common.model.UserBaseDomain;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.StringUtil;

import java.util.List;

/**
 * 涉密人员类别
 * 
 * @author renmingfei
 * 
 */
public class UserSecurity extends UserBaseDomain {
	private String security_code;
	private String security_name;
	private String security_desc;
	private Integer user_count = null;
	private String print_value = "";
	private String copy_value = "";
	private String import_value = "";
	private String burn_value = "";
	private String device_value = "";
	private String default_value = "";
	private String copy_burn_value = "";
	private String print_name = "";
	private String copy_name = "";
	private String import_name = "";
	private String burn_name = "";
	private String copy_burn_name = "";
	private String device_name = "";
	private String default_name = "";

	public String getSecurity_code() {
		return security_code;
	}

	public void setSecurity_code(String security_code) {
		this.security_code = security_code;
	}

	public String getSecurity_name() {
		return security_name;
	}

	public void setSecurity_name(String security_name) {
		this.security_name = security_name;
	}

	public String getSecurity_desc() {
		return security_desc;
	}

	public void setSecurity_desc(String security_desc) {
		this.security_desc = security_desc;
	}

	public Integer getUser_count() {
		return user_count;
	}

	public void setUser_count(Integer user_count) {
		this.user_count = user_count;
	}

	public String getPrint_value() {
		return print_value;
	}

	public void setPrint_value(String print_value) {
		this.print_value = print_value;
	}

	public List<String> getPrintList() {
		return StringUtil.stringArrayToList(print_value.split(Constants.COMMON_SEPARATOR_REGEX));
	}

	public String getCopy_value() {
		return copy_value;
	}

	public void setCopy_value(String copy_value) {
		this.copy_value = copy_value;
	}

	public List<String> getCopyList() {
		return StringUtil.stringArrayToList(copy_value.split(Constants.COMMON_SEPARATOR_REGEX));
	}

	public String getImport_value() {
		return import_value;
	}

	public void setImport_value(String import_value) {
		this.import_value = import_value;
	}

	public List<String> getImportList() {
		return StringUtil.stringArrayToList(import_value.split(Constants.COMMON_SEPARATOR_REGEX));
	}

	public String getBurn_value() {
		return burn_value;
	}

	public void setBurn_value(String burn_value) {
		this.burn_value = burn_value;
	}

	public List<String> getBurnList() {
		return StringUtil.stringArrayToList(burn_value.split(Constants.COMMON_SEPARATOR_REGEX));
	}

	public String getCopy_burn_value() {
		return copy_burn_value;
	}

	public void setCopy_burn_value(String copy_burn_value) {
		this.copy_burn_value = copy_burn_value;
	}

	public List<String> getCopyBurnList() {
		return StringUtil.stringArrayToList(copy_burn_value.split(Constants.COMMON_SEPARATOR_REGEX));
	}

	public String getDevice_value() {
		return device_value;
	}

	public void setDevice_value(String device_value) {
		this.device_value = device_value;
	}

	public List<String> getDeviceList() {
		return StringUtil.stringArrayToList(device_value.split(Constants.COMMON_SEPARATOR_REGEX));
	}

	public String getDefault_value() {
		return default_value;
	}

	public void setDefault_value(String default_value) {
		this.default_value = default_value;
	}

	public List<String> getDefaultList() {
		return StringUtil.stringArrayToList(default_value.split(Constants.COMMON_SEPARATOR_REGEX));
	}

	public String getPrint_name() {
		return print_name;
	}

	public void setPrint_name(String print_name) {
		this.print_name = print_name;
	}

	public String getCopy_name() {
		return copy_name;
	}

	public void setCopy_name(String copy_name) {
		this.copy_name = copy_name;
	}

	public String getImport_name() {
		return import_name;
	}

	public void setImport_name(String import_name) {
		this.import_name = import_name;
	}

	public String getBurn_name() {
		return burn_name;
	}

	public void setBurn_name(String burn_name) {
		this.burn_name = burn_name;
	}

	public String getCopy_burn_name() {
		return copy_burn_name;
	}

	public void setCopy_burn_name(String copy_burn_name) {
		this.copy_burn_name = copy_burn_name;
	}

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public String getDefault_name() {
		return default_name;
	}

	public void setDefault_name(String default_name) {
		this.default_name = default_name;
	}

	public UserSecurity() {
		super();
	}

	public UserSecurity(String security_code, String security_name, String security_desc) {
		super();
		this.security_code = security_code;
		this.security_name = security_name;
		this.security_desc = security_desc;
	}

	public UserSecurity(String security_code, String security_name, String security_desc, String print_value, String copy_value,
			String import_value, String burn_value, String copy_burn_value, String device_value, String default_value) {
		super();
		this.security_code = security_code;
		this.security_name = security_name;
		this.security_desc = security_desc;
		this.print_value = print_value;
		this.copy_value = copy_value;
		this.import_value = import_value;
		this.burn_value = burn_value;
		this.copy_burn_value = copy_burn_value;
		this.device_value = device_value;
		this.default_value = default_value;
	}

}
