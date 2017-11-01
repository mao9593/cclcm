package hdsec.web.project.user.model;

public class SecUserDept {
	
	private String user_iidd;
	private String dept_id;
	private String dept_name;
	private String terr_name;
	private String dept_type_name;
	private String dept_level_name;
	
	public String getTerr_name() {
		return terr_name;
	}
	
	public void setTerr_name(String terr_name) {
		this.terr_name = terr_name;
	}
	
	public String getDept_type_name() {
		return dept_type_name;
	}
	
	public void setDept_type_name(String dept_type_name) {
		this.dept_type_name = dept_type_name;
	}
	
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
	
	public String getDept_name() {
		return dept_name;
	}
	
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	
	public String getDept_level_name() {
		return dept_level_name;
	}
	
	public void setDept_level_name(String dept_level_name) {
		this.dept_level_name = dept_level_name;
	}
}