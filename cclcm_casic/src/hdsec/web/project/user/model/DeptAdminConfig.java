package hdsec.web.project.user.model;

/**
 * 部门管理员配置结果类
 * 2014-4-12 上午11:02:28
 * 
 * @author renmingfei
 */
public class DeptAdminConfig {
	private String user_iidd;
	private String dept_ids = "";
	private String dept_names = "";
	private String is_inherit = "N";
	
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
	
	public String getIs_inherit() {
		return is_inherit;
	}
	
	public void setIs_inherit(String is_inherit) {
		this.is_inherit = is_inherit;
	}
	
	public DeptAdminConfig() {
		super();
	}
	
	public DeptAdminConfig(String user_iidd, String dept_ids, String dept_names, String is_inherit) {
		super();
		this.user_iidd = user_iidd;
		this.dept_ids = dept_ids;
		this.dept_names = dept_names;
		this.is_inherit = is_inherit;
	}
	
}
