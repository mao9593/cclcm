package hdsec.web.project.user.action;


/**
 * 删除岗位
 * @author renmingfei
 *
 */
public class DelPostAction extends UserBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String post_id = null;
	
	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}
	
	@Override
	public String executeFunction() throws Exception {
		String post_name = userService.getPostByPostId(post_id).getPost_name();
		userService.delPostById(post_id);
		insertAdminLog("删除岗位:" + post_name);
		return SUCCESS;
	}
	
}