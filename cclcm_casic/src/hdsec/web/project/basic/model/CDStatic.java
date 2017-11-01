package hdsec.web.project.basic.model;

import hdsec.web.project.common.BaseDomain;

/**
 * 光盘统计类
 * 2014-5-21 上午1:09:37
 * 
 * @author gaoximin
 */
public class CDStatic extends BaseDomain {
	private String dept_id = "";// 部门ID
	private String dept_name = "";// 部门名称
	private Integer seclv_code;// 密级代号
	private String seclv_name;// 密级名称
	private Integer disc_count;// 页数
	
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
	
	public Integer getSeclv_code() {
		return seclv_code;
	}
	
	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}
	
	public String getSeclv_name() {
		return seclv_name;
	}
	
	public void setSeclv_name(String seclv_name) {
		this.seclv_name = seclv_name;
	}
	
	public Integer getDisc_count() {
		return disc_count;
	}
	
	public void setDisc_count(Integer disc_count) {
		this.disc_count = disc_count;
	}
	
	public CDStatic() {
		super();
	}
	
	public CDStatic(String dept_id, String dept_name, Integer seclv_code, String seclv_name, Integer disc_count) {
		super();
		this.dept_id = dept_id;
		this.dept_name = dept_name;
		this.seclv_code = seclv_code;
		this.seclv_name = seclv_name;
		this.disc_count = disc_count;
	}
}
