package hdsec.web.project.user.action;

import org.springframework.util.StringUtils;

/**
 * 删除安全用户
 * @author renmingfei
 *
 */
public class DelSecUserAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private String user_iidd = "";
	private String delResult = "";
	
	public String getUser_iidd() {
		return user_iidd;
	}
	
	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}
	
	public String getDelResult() {
		return delResult;
	}
	
	public void setDelResult(String delResult) {
		this.delResult = delResult;
	}
	
	@Override
	public String executeFunction() {
		if (StringUtils.hasLength(user_iidd)) {
			//设置用户状态
			try {
				userService.delSecUserById(user_iidd);
				insertAdminLog("删除用户:" + user_iidd);
				setDelResult("success");
			} catch (Exception e) {
				dealException(e);
				setDelResult("exception");
			}
		} else {
			setDelResult("paramError");
		}
		return SUCCESS;
	}
}
