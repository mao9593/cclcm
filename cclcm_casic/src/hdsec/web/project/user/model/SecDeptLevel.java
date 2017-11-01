package hdsec.web.project.user.model;

import hdsec.web.project.common.model.UserBaseDomain;

/**
 * 部门等级
 * @author renmingfei
 *
 */
public class SecDeptLevel extends UserBaseDomain {
	private String dept_level_code;
	private String dept_level_name;
	private String dept_level_desc;
	
	public String getDept_level_code() {
		return dept_level_code;
	}
	
	public void setDept_level_code(String dept_level_code) {
		this.dept_level_code = dept_level_code;
	}
	
	public String getDept_level_name() {
		return dept_level_name;
	}
	
	public void setDept_level_name(String dept_level_name) {
		this.dept_level_name = dept_level_name;
	}
	
	public String getDept_level_desc() {
		return dept_level_desc;
	}
	
	public void setDept_level_desc(String dept_level_desc) {
		this.dept_level_desc = dept_level_desc;
	}
	
}
