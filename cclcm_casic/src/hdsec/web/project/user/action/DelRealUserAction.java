package hdsec.web.project.user.action;

import hdsec.web.project.user.model.RealUser;

/**
 * 删除员工信息
 * @author renmingfei
 *
 */
public class DelRealUserAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private String real_user_id = "";
	
	public void setReal_user_id(String real_user_id) {
		this.real_user_id = real_user_id;
	}
	
	@Override
	public String executeFunction() throws Exception {
		RealUser realUser = userService.getRealUserById(real_user_id);
		if (realUser.getSecUser_count() > 0) {
			dealException(new Exception("该员工存在绑定的安全用户，无法删除"));
			return "exception";
		}
		userService.delRealUserById(real_user_id);
		insertAdminLog("删除员工:" + real_user_id);
		return SUCCESS;
	}
}
