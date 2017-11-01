package hdsec.web.project.user.action;

import hdsec.web.project.common.util.MD5;

import java.util.HashMap;
import java.util.Map;

/**
 * 重置用户密码
 * 
 * @author renmingfei
 * 
 */
public class ResetUserPwdAction extends UserBaseAction {

	private static final long serialVersionUID = 1L;
	private String user_iidd = "";
	private String resetResult = "";
	private String item_key = "DEFAULT_PASSWORD";

	public String getResetResult() {
		return resetResult;
	}

	public void setResetResult(String resetResult) {
		this.resetResult = resetResult;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getItem_key() {
		return item_key;
	}

	public void setItem_key(String item_key) {
		this.item_key = item_key;
	}

	@Override
	public String executeFunction() throws Exception {
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("user_iidd", user_iidd);
			// map.put("user_ppww", MD5.getMD5Str("12345678a@"));
			String defaultPassword = userService.getSysConfigItemValue(item_key);
			map.put("user_ppww", MD5.getMD5Str(defaultPassword));
			userService.resetUserPwd(map);
			insertAdminLog("重置用户密码:" + user_iidd);
			setResetResult("密码已经重置为" + defaultPassword);
		} catch (Exception e) {
			dealException(e);
			setResetResult("密码重置失败，请重试");
		}
		return SUCCESS;
	}
}
