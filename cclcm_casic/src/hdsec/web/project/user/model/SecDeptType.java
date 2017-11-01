package hdsec.web.project.user.model;

import hdsec.web.project.common.model.UserBaseDomain;

/**
 * 部门类型
 * @author renmingfei
 *
 */
public class SecDeptType extends UserBaseDomain {
	
	private String dept_type_code;
	private String dept_type_name;
	private String dept_type_desc;
	private Integer dept_count;
	
	public String getDept_type_code() {
		return dept_type_code;
	}
	
	public void setDept_type_code(String dept_type_code) {
		this.dept_type_code = dept_type_code;
	}
	
	public String getDept_type_name() {
		return dept_type_name;
	}
	
	public void setDept_type_name(String dept_type_name) {
		this.dept_type_name = dept_type_name;
	}
	
	public String getDept_type_desc() {
		return dept_type_desc;
	}
	
	public void setDept_type_desc(String dept_type_desc) {
		this.dept_type_desc = dept_type_desc;
	}
	
	public Integer getDept_count() {
		return dept_count;
	}
	
	public void setDept_count(Integer dept_count) {
		this.dept_count = dept_count;
	}
	
}
