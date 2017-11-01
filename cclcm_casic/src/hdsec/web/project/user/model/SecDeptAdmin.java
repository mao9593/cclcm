package hdsec.web.project.user.model;

/**
 * 部门管理员配置类
 * 2014-4-11 下午6:43:28
 * 
 * @author renmingfei
 */
public class SecDeptAdmin {
	private String user_iidd = "";
	private String dept_id = "";
	private String dept_cs = "";
	private String is_inherit = "N";
	
	public String getUser_iidd() {
		return user_iidd;
	}
	
	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}
	
	public String getDept_id() {
		return dept_id;
	}
	
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	
	public String getDept_cs() {
		return dept_cs;
	}
	
	public void setDept_cs(String dept_cs) {
		this.dept_cs = dept_cs;
	}
	
	public String getIs_inherit() {
		return is_inherit;
	}
	
	public void setIs_inherit(String is_inherit) {
		this.is_inherit = is_inherit;
	}
	
	public boolean is_inherit() {
		return is_inherit.equals("Y");
	}
	
	public SecDeptAdmin() {
		super();
	}
	
	public SecDeptAdmin(String user_iidd, String dept_id, String dept_cs, String is_inherit) {
		super();
		this.user_iidd = user_iidd;
		this.dept_id = dept_id;
		this.dept_cs = dept_cs;
		this.is_inherit = is_inherit;
	}
	
	public SecDeptAdmin(String user_iidd, String dept_id, String dept_cs) {
		super();
		this.user_iidd = user_iidd;
		this.dept_id = dept_id;
		this.dept_cs = dept_cs;
		this.is_inherit = "N";
	}
	
}
