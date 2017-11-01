package hdsec.web.project.user.model;

/**
 * 简单岗位，多用于查询时候的岗位列表
 * @author renmingfei
 *
 */
public class Post {
	private String post_id;
	private String post_name;
	
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
	
	public Post(String post_id, String post_name) {
		super();
		this.post_id = post_id;
		this.post_name = post_name;
	}
	
	public Post() {
		super();
	}
	
}
