package hdsec.web.project.securityuser.model;

import hdsec.web.project.common.BaseDomain;

public class SysUserPost extends BaseDomain {
	private String post_id = "";// 岗位ID
	private String post_name = "";// 岗位名称
	private String post_desc = "";// 岗位描述
	private Integer post_level = null;// 岗位排序
	private Integer post_class = null;// 岗位等级
	private String dept_id = "";// 部门ID
	private String dept_name = "";// 部门

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

	public Integer getPost_class() {
		return post_class;
	}

	public void setPost_class(Integer post_class) {
		this.post_class = post_class;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public SysUserPost() {
		super();
	}

	public SysUserPost(String post_id, String post_name, String post_desc, Integer post_level, Integer post_class) {
		super();
		this.post_id = post_id;
		this.post_name = post_name;
		this.post_desc = post_desc;
		this.post_level = post_level;
		this.post_class = post_class;
	}
}
