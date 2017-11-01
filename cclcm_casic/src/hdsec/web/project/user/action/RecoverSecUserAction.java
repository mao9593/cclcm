package hdsec.web.project.user.action;

import org.springframework.util.StringUtils;

/**
 * 恢复已删除的用户
 * @author lixiang
 *
 */
public class RecoverSecUserAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private String user_iidd = "";
	private String result = "";
	
	public String getUser_iidd() {
		return user_iidd;
	}
	
	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String executeFunction() {
		if (StringUtils.hasLength(user_iidd)) {
			//设置用户状态
			try {
				userService.recoverSecUserById(user_iidd);
				insertAdminLog("恢复用户:" + user_iidd);
				setResult("success");
			} catch (Exception e) {
				dealException(e);
				setResult("exception");
			}
		} else {
			setResult("paramError");
		}
		return SUCCESS;
	}
}
