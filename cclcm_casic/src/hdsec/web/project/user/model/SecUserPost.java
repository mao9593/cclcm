package hdsec.web.project.user.model;

import hdsec.web.project.common.model.UserBaseDomain;

/**
 * 岗位
 * @author renmingfei
 *
 */
public class SecUserPost extends UserBaseDomain {
	private String post_id;
	private String post_name;
	private String post_desc;
	private Integer post_level;
	private Integer post_class;
	private Integer user_count;
	
	public Integer getPost_class() {
		return post_class;
	}
	
	public void setPost_class(Integer post_class) {
		this.post_class = post_class;
	}
	
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
	
	public Integer getPost_level() {
		return post_level;
	}
	
	public void setPost_level(Integer post_level) {
		this.post_level = post_level;
	}
	
	public Integer getUser_count() {
		return user_count;
	}
	
	public void setUser_count(Integer user_count) {
		this.user_count = user_count;
	}
	
}
