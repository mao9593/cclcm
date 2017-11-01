package hdsec.web.project.user.action;

import hdsec.web.project.common.util.MD5;
import hdsec.web.project.user.model.ConfigItem;
import hdsec.web.project.user.model.SecUser;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 修改用户密码
 * 
 * @author renmingfei
 * 
 */
public class ChangePwdAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private String user_iidd = "";
	private String pwd_old = "";
	private String pwd_new = "";
	private String done = "N";
	private String msg = "";
	private String enter = "Y";
	private String change = "N";

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getPwd_old() {
		return pwd_old;
	}

	public void setPwd_old(String pwd_old) {
		this.pwd_old = pwd_old;
	}

	public String getPwd_new() {
		return pwd_new;
	}

	public void setPwd_new(String pwd_new) {
		this.pwd_new = pwd_new;
	}

	public SecUser getUser() {
		if (StringUtils.hasLength(user_iidd)) {
			return userService.getSecUserByUid(user_iidd);
		} else {
			return getSecUserFromSession();
		}
	}

	public String getDone() {
		return done;
	}

	public String getMsg() {
		return msg;
	}

	public String getEnter() {
		return enter;
	}

	public void setChange(String change) {
		this.change = change;
	}

	@Override
	public String executeFunction() throws Exception {
		if (change.equalsIgnoreCase("Y")) {
			if (!userService.authenticate(user_iidd, MD5.getMD5Str(pwd_old))) {// 旧密码输入错误
				msg = "原密码输入错误";
				return "fail";
			}
			ConfigItem item_pwd_length = userService.getConfigItemValue(ConfigItem.KEY_PWD_LENGTH);
			if (item_pwd_length.getStartuse() == 1) {// 如果启用了密码长度的检查
				int pwd_length = Integer.parseInt(item_pwd_length.getItem_value());
				if (pwd_new.length() < pwd_length) {
					msg = "密码长度小于" + pwd_length + "位";
					return "fail";
				}
			}
			ConfigItem item_pwd_digit = userService.getConfigItemValue(ConfigItem.KEY_PWD_DIGIT);
			if (item_pwd_digit.getStartuse() == 1) {// 如果启用了密码包含数字的检查
				if (!pwd_new.matches(".*\\d+.*")) {
					msg = "根据预设的密码复杂度要求，密码必须要包含数字";
					return "fail";
				}
			}
			ConfigItem item_pwd_alpha = userService.getConfigItemValue(ConfigItem.KEY_PWD_ALPHA);
			if (item_pwd_alpha.getStartuse() == 1) {// 如果启用了密码包含数字的检查
				if (!pwd_new.matches(".*[a-zA-Z]+.*")) {
					msg = "根据预设的密码复杂度要求，密码必须要包含字母";
					return "fail";
				}
			}
			ConfigItem item_pwd_notation = userService.getConfigItemValue(ConfigItem.KEY_PWD_NOTATION);
			if (item_pwd_notation.getStartuse() == 1) {// 如果启用了密码包含数字的检查
				String pwd_notation = item_pwd_notation.getItem_value();
				if (!pwd_new.matches(".*[" + pwd_notation + "]+.*")) {
					msg = "根据预设的密码复杂度要求，密码必须要包含以下特殊字符 " + pwd_notation;
					return "fail";
				}
			}
			pwd_new = MD5.getMD5Str(pwd_new);
			Map<String, String> map = new HashMap<String, String>();
			map.put("user_iidd", user_iidd);
			map.put("user_pwd", pwd_new);
			userService.changeUserPwd(map);
			done = "Y";
			if (getCurrentUser().is_admin()) {
				insertAdminLog("修改用户[" + user_iidd + "]的登录密码");
			} else {
				insertCommonLog("修改用户[" + user_iidd + "]的登录密码");
			}
		}
		return SUCCESS;
	}
}
