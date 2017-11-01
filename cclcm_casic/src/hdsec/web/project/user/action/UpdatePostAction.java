package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecUserPost;

/**
 * 更改岗位信息
 * @author renmingfei
 *
 */
public class UpdatePostAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private SecUserPost secUserPost = null;
	private String post_id = null;
	private String post_name;
	private String post_desc;
	private String update = "N";
	
	public String getPost_id() {
		return post_id;
	}
	
	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}
	
	public String getPost_name() {
		return post_name;
	}
	
	public void setPost_name(String post_name) {
		this.post_name = post_name;
	}
	
	public String getPost_desc() {
		return post_desc;
	}
	
	public void setPost_desc(String post_desc) {
		this.post_desc = post_desc;
	}
	
	public SecUserPost getSecUserPost() {
		return secUserPost;
	}
	
	public void setUpdate(String update) {
		this.update = update;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("Y")) {//修改操作
			SecUserPost post = new SecUserPost();
			post.setPost_id(post_id);
			post.setPost_name(post_name);
			post.setPost_desc(post_desc);
			userService.updateSecUserPost(post);
			insertAdminLog("修改岗位信息:" + post_id + "," + post_name);
			return "update";
		} else {
			secUserPost = userService.getSecUserPostById(post_id);
		}
		return SUCCESS;
	}
}
