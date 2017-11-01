package hdsec.web.project.enter.model;

import hdsec.web.project.user.model.SecDeptAdmin;

public class SubSecDeptAdmin extends SecDeptAdmin {
	private String dept_name;
	
	public String getDept_name() {
		return dept_name;
	}
	
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
}
