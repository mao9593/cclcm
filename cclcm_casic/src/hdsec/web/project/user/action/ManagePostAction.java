package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecUserPost;

import java.util.List;

/**
 * 进入岗位列表管理页面
 * @author renmingfei
 *
 */
public class ManagePostAction extends UserBaseAction {
	
	private static final long serialVersionUID = 1L;
	private List<SecUserPost> userPostList = null;
	
	public List<SecUserPost> getUserPostList() {
		return userPostList;
	}
	
	@Override
	public String executeFunction() throws Exception {
		userPostList = userService.getAllUserPostList();
		return SUCCESS;
	}
	
}
