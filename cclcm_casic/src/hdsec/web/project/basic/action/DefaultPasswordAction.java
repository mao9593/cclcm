package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysConfigItem;

/**
 * 默认密码设置
 * 
 * @author congxue
 */
public class DefaultPasswordAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;

	private String defaultpassword;// 默认密码设置
	private String update = "N";
	private String done = "N";
	private String msg = "";

	public String getDefaultpassword() {
		return defaultpassword;
	}

	public void setDefaultpassword(String defaultpassword) {
		this.defaultpassword = defaultpassword;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getDone() {
		return done;
	}

	public void setDone(String done) {
		this.done = done;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("Y")) {// 更新操作
			SysConfigItem item_pwd_length = basicService.getSysConfigItemValue(SysConfigItem.KEY_PWD_LENGTH);
			if (item_pwd_length.getStartuse() == 1) {// 如果启用了密码长度的检查
				int pwd_length = Integer.parseInt(item_pwd_length.getItem_value());
				if (defaultpassword.length() < pwd_length) {
					msg = "密码长度小于" + pwd_length + "位";
					return "fail";
				}
			}
			SysConfigItem item_pwd_digit = basicService.getSysConfigItemValue(SysConfigItem.KEY_PWD_DIGIT);
			if (item_pwd_digit.getStartuse() == 1) {// 如果启用了密码包含数字的检查
				if (!defaultpassword.matches(".*\\d+.*")) {
					msg = "根据预设的密码复杂度要求，密码必须要包含数字";
					return "fail";
				}
			}
			SysConfigItem item_pwd_alpha = basicService.getSysConfigItemValue(SysConfigItem.KEY_PWD_ALPHA);
			if (item_pwd_alpha.getStartuse() == 1) {// 如果启用了密码包含字母的检查
				if (!defaultpassword.matches(".*[a-zA-Z]+.*")) {
					msg = "根据预设的密码复杂度要求，密码必须要包含字母";
					return "fail";
				}
			}
			SysConfigItem item_pwd_notation = basicService.getSysConfigItemValue(SysConfigItem.KEY_PWD_NOTATION);
			if (item_pwd_notation.getStartuse() == 1) {// 如果启用了密码特殊字符范围的检查
				String pwd_notation = item_pwd_notation.getItem_value();
				if (!defaultpassword.matches(".*[" + pwd_notation + "]+.*")) {
					msg = "根据预设的密码复杂度要求，密码必须要包含以下特殊字符 " + pwd_notation;
					return "fail";
				}
			}
			basicService
					.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_DEFAULT_PASSWORD,
							SysConfigItem.NAME_DEFAULT_PASSWORD, String.valueOf(defaultpassword),
							SysConfigItem.TYPE_DEFAULT, 1));
			insertAdminLog("设置默认密码");
			done = "Y";
		}
		SysConfigItem item_default_password = basicService.getSysConfigItemValue(SysConfigItem.KEY_DEFAULT_PASSWORD);

		defaultpassword = item_default_password.getItem_value();
		return SUCCESS;

	}
}
