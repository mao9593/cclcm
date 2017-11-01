package hdsec.web.project.user.model;

import hdsec.web.project.common.model.UserBaseDomain;

/**
 * 部门
 * 
 * @author renmingfei
 * 
 */
public class SecDept extends UserBaseDomain {

	private String dept_id;
	private String dept_cs;
	private String dept_name;
	private String dept_desc;
	private String dept_parent_id;
	private String terr_code;
	private String dept_type_code;
	private String dept_level_code;
	private String ext_code;
	private String dept_rank; // 部门排序值

	public String getDept_rank() {
		return dept_rank;
	}

	public void setDept_rank(String dept_rank) {
		this.dept_rank = dept_rank;
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

	public String getDept_parent_id() {
		return dept_parent_id;
	}

	public void setDept_parent_id(String dept_parent_id) {
		this.dept_parent_id = dept_parent_id;
	}

	public String getDept_type_code() {
		return dept_type_code;
	}

	public void setDept_type_code(String dept_type_code) {
		this.dept_type_code = dept_type_code;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getTerr_code() {
		return terr_code;
	}

	public void setTerr_code(String terr_code) {
		this.terr_code = terr_code;
	}

	public String getDept_level_code() {
		return dept_level_code;
	}

	public void setDept_level_code(String dept_level_code) {
		this.dept_level_code = dept_level_code;
	}

	public String getDept_desc() {
		return dept_desc;
	}

	public void setDept_desc(String dept_desc) {
		this.dept_desc = dept_desc;
	}

	public String getExt_code() {
		return ext_code;
	}

	public void setExt_code(String ext_code) {
		this.ext_code = ext_code;
	}

	/*
	 * 重载Object基本方法
	 */

	@Override
	public int hashCode() {
		return this.dept_id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SecDept) {
			return ((SecDept) obj).getDept_id().equalsIgnoreCase(this.dept_id);
		}
		return false;
	}

	@Override
	public String toString() {
		return this.dept_id + "\t" + this.dept_name;
	}
}
