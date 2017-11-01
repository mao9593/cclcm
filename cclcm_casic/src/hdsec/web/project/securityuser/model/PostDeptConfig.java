package hdsec.web.project.securityuser.model;

/**
 * 岗位部门配置结果类
 * 
 * @author yangjl
 */
public class PostDeptConfig {
	private String post_id;
	private String dept_id = "";
	private String dept_name = "";

	public String getPost_id() {
		return post_id;
	}

	public void setPost_id(String post_id) {
		this.post_id = post_id;
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

	public PostDeptConfig() {
		super();
	}

	public PostDeptConfig(String post_id, String dept_id) {
		super();
		this.post_id = post_id;
		this.dept_id = dept_id;
	}
}
