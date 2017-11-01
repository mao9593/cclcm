package hdsec.web.project.basic.model;

import hdsec.web.project.common.BaseDomain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 刻录机
 * @author renmingfei
 *
 */
public class SysBurner extends BaseDomain {
	private String burner_code = "";//编号，主键（UUID）
	private String burner_name = "";//名称
	private String burner_path = "";//路径
	private String burner_type = "";//类型 DVD/CD/BLUERAY
	private String burner_brand = "";//品牌
	private String burner_model = "";//型号
	private String burner_location = "";//位置
	private String dept_id = "";//部门
	private Date create_time = null;//创建时间
	private Date delete_time = null;//删除时间
	private String console_code = "";//控制台编号
	private Integer seclv_code = null;//密级
	private Integer burner_usefor = null;//用途 0输出 1输入
	private String mfp_code = "";//关联的一体机code
	
	private String dept_name = "";
	private String console_name = "";
	private String seclv_name = "";
	private String mfp_name = "";
	
	public String getBurner_code() {
		return burner_code;
	}
	
	public void setBurner_code(String burner_code) {
		this.burner_code = burner_code;
	}
	
	public String getBurner_name() {
		return burner_name;
	}
	
	public void setBurner_name(String burner_name) {
		this.burner_name = burner_name;
	}
	
	public String getBurner_path() {
		return burner_path;
	}
	
	public void setBurner_path(String burner_path) {
		this.burner_path = burner_path;
	}
	
	public String getBurner_type() {
		return burner_type;
	}
	
	public void setBurner_type(String burner_type) {
		this.burner_type = burner_type;
	}
	
	public String getBurner_brand() {
		return burner_brand;
	}
	
	public void setBurner_brand(String burner_brand) {
		this.burner_brand = burner_brand;
	}
	
	public String getBurner_model() {
		return burner_model;
	}
	
	public void setBurner_model(String burner_model) {
		this.burner_model = burner_model;
	}
	
	public String getBurner_location() {
		return burner_location;
	}
	
	public void setBurner_location(String burner_location) {
		this.burner_location = burner_location;
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
	
	public Integer getBurner_usefor() {
		return burner_usefor;
	}
	
	public String getBurner_usefor_name() {
		return burner_usefor == 0 ? "输入" : "输出";
	}
	
	public void setBurner_usefor(Integer burner_usefor) {
		this.burner_usefor = burner_usefor;
	}
	
	public String getMfp_code() {
		return mfp_code;
	}
	
	public void setMfp_code(String mfp_code) {
		this.mfp_code = mfp_code;
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
	
	public String getMfp_name() {
		return mfp_name;
	}
	
	public void setMfp_name(String mfp_name) {
		this.mfp_name = mfp_name;
	}
	
	public SysBurner() {
		super();
	}
	
	public SysBurner(SimpleDateFormat sdf) {
		super(sdf);
	}
	
	public SysBurner(String burner_code, String burner_name, String burner_path, String burner_type,
			String burner_brand, String burner_model, String burner_location, String dept_id, Date create_time,
			String console_code, Integer seclv_code, Integer burner_usefor, String mfp_code) {
		this.burner_code = burner_code;
		this.burner_name = burner_name;
		this.burner_path = burner_path;
		this.burner_type = burner_type;
		this.burner_brand = burner_brand;
		this.burner_model = burner_model;
		this.burner_location = burner_location;
		this.dept_id = dept_id;
		this.create_time = create_time;
		this.console_code = console_code;
		this.seclv_code = seclv_code;
		this.burner_usefor = burner_usefor;
		this.mfp_code = mfp_code;
	}
	
}
