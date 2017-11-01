package hdsec.web.project.basic.model;

/**
 * 用户角色部门配置结果类
 * 
 * @author lixiang
 */
public class DeptAdminConfig {
	private String user_iidd;
	private String role_id;
	private String dept_ids = "";
	private String dept_names = "";
	
	public String getUser_iidd() {
		return user_iidd;
	}
	
	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}
	
	public String getDept_ids() {
		return dept_ids;
	}
	
	public void setDept_ids(String dept_ids) {
		this.dept_ids = dept_ids;
	}
	
	public String getDept_names() {
		return dept_names;
	}
	
	public void setDept_names(String dept_names) {
		this.dept_names = dept_names;
	}
	
	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public DeptAdminConfig() {
		super();
	}
	
	public DeptAdminConfig(String user_iidd, String role_id, String dept_ids, String dept_names) {
		super();
		this.user_iidd = user_iidd;
		this.role_id = role_id;
		this.dept_ids = dept_ids;
		this.dept_names = dept_names;
	}
}
