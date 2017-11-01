package hdsec.web.project.user.model;

import hdsec.web.project.common.model.UserBaseDomain;

/**
 * 子系统
 * @author renmingfei
 *
 */
public class SecSubsys extends UserBaseDomain {
	
	private String subsys_code = "";
	private String subsys_name = "";
	private String subsys_desc = "";
	private String oper_code_prefix = "";
	private Integer role_count;
	private Integer oper_count;
	private Integer dept_count;
	
	public String getSubsys_code() {
		return subsys_code;
	}
	
	public void setSubsys_code(String subsys_code) {
		this.subsys_code = subsys_code;
	}
	
	public String getSubsys_name() {
		return subsys_name;
	}
	
	public void setSubsys_name(String subsys_name) {
		this.subsys_name = subsys_name;
	}
	
	public String getSubsys_desc() {
		return subsys_desc;
	}
	
	public void setSubsys_desc(String subsys_desc) {
		this.subsys_desc = subsys_desc;
	}
	
	public String getOper_code_prefix() {
		return oper_code_prefix;
	}
	
	public void setOper_code_prefix(String oper_code_prefix) {
		this.oper_code_prefix = oper_code_prefix;
	}
	
	public Integer getRole_count() {
		return role_count;
	}
	
	public void setRole_count(Integer role_count) {
		this.role_count = role_count;
	}
	
	public Integer getOper_count() {
		return oper_count;
	}
	
	public void setOper_count(Integer oper_count) {
		this.oper_count = oper_count;
	}
	
	public Integer getDept_count() {
		return dept_count;
	}
	
	public void setDept_count(Integer dept_count) {
		this.dept_count = dept_count;
	}
	
}
