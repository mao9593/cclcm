package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecUser;

/**
 * 检验用户登录名是否可用，并返回提示信息
 * @author renmingfei
 *
 */
public class CheckUserIdAction extends UserBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String user_iidd = "";
	private String chkResult = "";
	
	public String getUser_iidd() {
		return user_iidd;
	}
	
	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}
	
	public String getChkResult() {
		return chkResult;
	}
	
	public void setChkResult(String chkResult) {
		this.chkResult = chkResult;
	}
	
	@Override
	public String executeFunction() {
		//判断登录ID是否为空
		if (user_iidd.trim().equals("")) {
			setChkResult("blank");
			return SUCCESS;
		}
		//判断是否包含非法字符
		String[] chars = { "　", " ", "~", "!", "@", "$", "%", "^", "&", "*", "|", "\\", "/", "?", "'", "\"" };
		for (String item : chars) {
			if (user_iidd.trim().indexOf(item) != -1) {
				setChkResult("syntaxErr");
				return SUCCESS;
			}
		}
		//判断是否已经使用
		try {
			SecUser secUser = userService.getSecUserByUid(user_iidd);
			if (secUser != null) {
				setChkResult("used");
			} else {
				setChkResult("available");
			}
		} catch (Exception e) {
			dealException(e);
			setChkResult("exception");
		}
		return SUCCESS;
	}
}
