package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecUserPost;

import java.util.UUID;

import org.springframework.util.StringUtils;

/**
 * 添加岗位
 * @author renmingfei
 *
 */
public class AddPostAction extends UserBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String post_name;
	private String post_desc;
	
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
	
	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(post_name)) {
			SecUserPost post = new SecUserPost();
			post.setPost_id(String.valueOf(UUID.randomUUID().getMostSignificantBits()));
			post.setPost_name(post_name);
			post.setPost_desc(post_desc);
			userService.addSecUserPost(post);
			insertAdminLog("添加岗位:" + post_name);
			return "insert";
		}
		return SUCCESS;
	}
	
}
