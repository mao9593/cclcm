package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecUser;

import java.util.List;

/**
 * 通过实际员工ID查询用户列表
 * @author renmingfei
 *
 */
public class ViewSecUserByRealAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private String real_user_id = "";
	private List<SecUser> secUserList = null;
	
	public List<SecUser> getSecUserList() {
		return secUserList;
	}
	
	public void setReal_user_id(String real_user_id) {
		this.real_user_id = real_user_id;
	}
	
	@Override
	public String executeFunction() throws Exception {
		secUserList = userService.getSimpleSecUserByReal(real_user_id);
		return SUCCESS;
	}
}
