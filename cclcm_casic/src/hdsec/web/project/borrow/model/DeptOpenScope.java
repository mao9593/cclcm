package hdsec.web.project.borrow.model;

/**
 * 部门借用配置类
 * 2014-4-16 下午10:53:03
 * 
 * @author renmingfei
 */
public class DeptOpenScope {
	private String dept_id = "";
	private String br_dept_id = "";
	
	public String getDept_id() {
		return dept_id;
	}
	
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	
	public String getBr_dept_id() {
		return br_dept_id;
	}
	
	public void setBr_dept_id(String br_dept_id) {
		this.br_dept_id = br_dept_id;
	}
	
	public DeptOpenScope() {
		super();
	}
	
	public DeptOpenScope(String dept_id, String br_dept_id) {
		super();
		this.dept_id = dept_id;
		this.br_dept_id = br_dept_id;
	}
	
}
