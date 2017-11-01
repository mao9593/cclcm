package hdsec.web.project.user.model;

/**
 * 说明:对应部门树
 * @author renmingfei
 */
//TODO:html/user/getdepttree.jsp
public class DeptTree {
	private String dept_id = "";
	private String dept_name = "";
	private String dept_parent_id = "";
	private String dept_desc = "";
	private int dept_level_code;
	
	public int getDept_level_code() {
		return dept_level_code;
	}
	
	public void setDept_level_code(int dept_level_code) {
		this.dept_level_code = dept_level_code;
	}
	
	public String getDept_name() {
		return dept_name;
	}
	
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	
	
	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getDept_parent_id() {
		return dept_parent_id;
	}

	public void setDept_parent_id(String dept_parent_id) {
		this.dept_parent_id = dept_parent_id;
	}

	public String getDept_desc() {
		return dept_desc;
	}
	
	public void setDept_desc(String dept_desc) {
		this.dept_desc = dept_desc;
	}
}