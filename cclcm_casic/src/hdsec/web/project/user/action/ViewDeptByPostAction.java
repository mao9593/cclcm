package hdsec.web.project.user.action;

import java.util.List;

public class ViewDeptByPostAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private String post_id = null;
	private String post_name = "";
	private List<String> deptNameList = null;
	
	public List<String> getDeptNameList() {
		return deptNameList;
	}
	
	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}
	
	public String getPost_name() {
		return post_name;
	}
	
	@Override
	public String executeFunction() throws Exception {
		deptNameList = userService.getDeptByPost(post_id);
		post_name = userService.getSecUserPostById(post_id).getPost_name();
		return SUCCESS;
	}
	
}
