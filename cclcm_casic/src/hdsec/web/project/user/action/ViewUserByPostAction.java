package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecUser;

import java.util.List;

public class ViewUserByPostAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private String post_id = null;
	private String post_name = "";
	private List<SecUser> secUserList = null;
	
	public List<SecUser> getSecUserList() {
		return secUserList;
	}
	
	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}
	
	public String getPost_name() {
		return post_name;
	}
	
	@Override
	public String executeFunction() throws Exception {
		secUserList = userService.getUserByPost(post_id);
		post_name = userService.getSecUserPostById(post_id).getPost_name();
		return SUCCESS;
	}
	
	public List<SecUser> getSecUser() {
		return secUserList;
	}
}
