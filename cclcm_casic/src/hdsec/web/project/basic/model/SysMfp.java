package hdsec.web.project.basic.model;

import hdsec.web.project.common.BaseDomain;

import java.util.Date;

/**
 * 一体机
 * @author renmingfei
 *
 */
public class SysMfp extends BaseDomain {
	private String mfp_code = "";//编号，主键（UUID）
	private String mfp_name = "";//名称
	private String mfp_brand = "";//品牌
	private String mfp_model = "";//型号
	private String mfp_location = "";//位置
	private String dept_id = "";//部门
	private Date create_time = null;//创建时间
	private Date delete_time = null;//删除时间
	private String console_code = "";//控制台编号
	private Integer seclv_code = null;//密级
	
	private String dept_name = "";
	private String console_name = "";
	private String seclv_name = "";
	
	public String getMfp_code() {
		return mfp_code;
	}
	
	public void setMfp_code(String mfp_code) {
		this.mfp_code = mfp_code;
	}
	
	public String getMfp_name() {
		return mfp_name;
	}
	
	public void setMfp_name(String mfp_name) {
		this.mfp_name = mfp_name;
	}
	
	public String getMfp_brand() {
		return mfp_brand;
	}
	
	public void setMfp_brand(String mfp_brand) {
		this.mfp_brand = mfp_brand;
	}
	
	public String getMfp_model() {
		return mfp_model;
	}
	
	public void setMfp_model(String mfp_model) {
		this.mfp_model = mfp_model;
	}
	
	public String getMfp_location() {
		return mfp_location;
	}
	
	public void setMfp_location(String mfp_location) {
		this.mfp_location = mfp_location;
	}
	
	public String getDept_id() {
		return dept_id;
	}
	
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	
	public Date getCreate_time() {
		return create_time;
	}
	
	public String getCreate_time_str() {
		return create_time == null ? "" : getSdf().format(create_time);
	}
	
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public Date getDelete_time() {
		return delete_time;
	}
	
	public String getDelete_time_str() {
		return delete_time == null ? "" : getSdf().format(delete_time);
	}
	
	public void setDelete_time(Date delete_time) {
		this.delete_time = delete_time;
	}
	
	public String getConsole_code() {
		return console_code;
	}
	
	public void setConsole_code(String console_code) {
		this.console_code = console_code;
	}
	
	public Integer getSeclv_code() {
		return seclv_code;
	}
	
	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}
	
	public String getDept_name() {
		return dept_name;
	}
	
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	
	public String getConsole_name() {
		return console_name;
	}
	
	public void setConsole_name(String console_name) {
		this.console_name = console_name;
	}
	
	public String getSeclv_name() {
		return seclv_name;
	}
	
	public void setSeclv_name(String seclv_name) {
		this.seclv_name = seclv_name;
	}
	
	public SysMfp() {
		super();
	}
	
	public SysMfp(String mfp_code, String mfp_name, String mfp_brand, String mfp_model, String mfp_location,
			String dept_id, Date create_time, String console_code, Integer seclv_code) {
		this.mfp_code = mfp_code;
		this.mfp_name = mfp_name;
		this.mfp_brand = mfp_brand;
		this.mfp_model = mfp_model;
		this.mfp_location = mfp_location;
		this.dept_id = dept_id;
		this.create_time = create_time;
		this.console_code = console_code;
		this.seclv_code = seclv_code;
	}
	
}
