package hdsec.web.project.borrow.model;

public class OpenScopeConfig {
	private String dept_id = "";
	private String dept_ids = "";
	private String dept_names = "";
	
	public String getDept_id() {
		return dept_id;
	}
	
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
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
	
	public OpenScopeConfig() {
		super();
	}
	
	public OpenScopeConfig(String dept_id, String dept_ids) {
		super();
		this.dept_id = dept_id;
		this.dept_ids = dept_ids;
	}
	
	public OpenScopeConfig(String dept_id, String dept_ids, String dept_names) {
		super();
		this.dept_id = dept_id;
		this.dept_ids = dept_ids;
		this.dept_names = dept_names;
	}
	
}
