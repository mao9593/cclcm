package hdsec.web.project.user.action;

import hdsec.web.project.user.model.ConfigItem;

/**
 * 登录设置
 * @author renmingfei
 *
 */
public class ConfigLoginAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private Integer pwd_length;
	private Integer valid_days;
	private Integer fail_times;
	private String pwd_notation = "";
	private String pwd_length_start = "";
	private String valid_days_start = "";
	private String fail_times_start = "";
	private String pwd_digit_start = "";
	private String pwd_alpha_start = "";
	private String pwd_notation_start = "";
	private String update = "N";
	private String done = "N";
	
	public Integer getPwd_length() {
		return pwd_length;
	}
	
	public void setPwd_length(Integer pwd_length) {
		this.pwd_length = pwd_length;
	}
	
	public Integer getValid_days() {
		return valid_days;
	}
	
	public void setValid_days(Integer valid_days) {
		this.valid_days = valid_days;
	}
	
	public Integer getFail_times() {
		return fail_times;
	}
	
	public void setFail_times(Integer fail_times) {
		this.fail_times = fail_times;
	}
	
	public String getPwd_notation() {
		return pwd_notation;
	}
	
	public void setPwd_notation(String pwd_notation) {
		this.pwd_notation = pwd_notation;
	}
	
	public String getPwd_length_start() {
		return pwd_length_start;
	}
	
	public void setPwd_length_start(String pwd_length_start) {
		this.pwd_length_start = pwd_length_start;
	}
	
	public String getValid_days_start() {
		return valid_days_start;
	}
	
	public void setValid_days_start(String valid_days_start) {
		this.valid_days_start = valid_days_start;
	}
	
	public String getFail_times_start() {
		return fail_times_start;
	}
	
	public void setFail_times_start(String fail_times_start) {
		this.fail_times_start = fail_times_start;
	}
	
	public String getPwd_digit_start() {
		return pwd_digit_start;
	}
	
	public void setPwd_digit_start(String pwd_digit_start) {
		this.pwd_digit_start = pwd_digit_start;
	}
	
	public String getPwd_alpha_start() {
		return pwd_alpha_start;
	}
	
	public void setPwd_alpha_start(String pwd_alpha_start) {
		this.pwd_alpha_start = pwd_alpha_start;
	}
	
	public String getPwd_notation_start() {
		return pwd_notation_start;
	}
	
	public void setPwd_notation_start(String pwd_notation_start) {
		this.pwd_notation_start = pwd_notation_start;
	}
	
	public void setUpdate(String update) {
		this.update = update;
	}
	
	public String getDone() {
		return done;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("Y")) {//更新操作
			userService.updateConfigItem(new ConfigItem(ConfigItem.KEY_PWD_LENGTH, String.valueOf(pwd_length),
					pwd_length_start.equals("1") ? 1 : 0));
			userService.updateConfigItem(new ConfigItem(ConfigItem.KEY_VALID_DAYS, String.valueOf(valid_days),
					valid_days_start.equals("1") ? 1 : 0));
			userService.updateConfigItem(new ConfigItem(ConfigItem.KEY_FAIL_TIMES, String.valueOf(fail_times),
					fail_times_start.equals("1") ? 1 : 0));
			userService.updateConfigItem(new ConfigItem(ConfigItem.KEY_PWD_DIGIT, "", pwd_digit_start.equals("1") ? 1
					: 0));
			userService.updateConfigItem(new ConfigItem(ConfigItem.KEY_PWD_ALPHA, "", pwd_alpha_start.equals("1") ? 1
					: 0));
			userService.updateConfigItem(new ConfigItem(ConfigItem.KEY_PWD_NOTATION, pwd_notation, pwd_notation_start
					.equals("1") ? 1 : 0));
			insertAdminLog("修改安全登录参数设置");
			done = "Y";
		}
		ConfigItem item_pwd_length = userService.getConfigItemValue(ConfigItem.KEY_PWD_LENGTH);
		ConfigItem item_valid_days = userService.getConfigItemValue(ConfigItem.KEY_VALID_DAYS);
		ConfigItem item_fail_times = userService.getConfigItemValue(ConfigItem.KEY_FAIL_TIMES);
		ConfigItem item_pwd_digit = userService.getConfigItemValue(ConfigItem.KEY_PWD_DIGIT);
		ConfigItem item_pwd_alpha = userService.getConfigItemValue(ConfigItem.KEY_PWD_ALPHA);
		ConfigItem item_pwd_notation = userService.getConfigItemValue(ConfigItem.KEY_PWD_NOTATION);
		pwd_length = Integer.parseInt(item_pwd_length.getItem_value());
		pwd_length_start = item_pwd_length.getStartuse() == 1 ? "checked" : "";
		valid_days = Integer.parseInt(item_valid_days.getItem_value());
		valid_days_start = item_valid_days.getStartuse() == 1 ? "checked" : "";
		fail_times = Integer.parseInt(item_fail_times.getItem_value());
		fail_times_start = item_fail_times.getStartuse() == 1 ? "checked" : "";
		pwd_digit_start = item_pwd_digit.getStartuse() == 1 ? "checked" : "";
		pwd_alpha_start = item_pwd_alpha.getStartuse() == 1 ? "checked" : "";
		pwd_notation = item_pwd_notation.getItem_value();
		pwd_notation_start = item_pwd_notation.getStartuse() == 1 ? "checked" : "";
		return SUCCESS;
	}
}
