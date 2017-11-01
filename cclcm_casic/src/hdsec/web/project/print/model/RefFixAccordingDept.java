package hdsec.web.project.print.model;

import hdsec.web.project.common.BaseDomain;

/**
 * 绑定定密依据与部门的关联表
 * @author M
 *
 */
public class RefFixAccordingDept extends BaseDomain {
	private String dept_id = "";//部门ID
	private Integer fix_id =null;//定密依据id
	public String getDept_id() {
		return dept_id;
	}
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	public Integer getFix_id() {
		return fix_id;
	}
	public void setFix_id(Integer fix_id) {
		this.fix_id = fix_id;
	}
	
	public RefFixAccordingDept(String dept_id, Integer fix_id) {
		super();
		this.dept_id = dept_id;
		this.fix_id = fix_id;
	}
	
}
