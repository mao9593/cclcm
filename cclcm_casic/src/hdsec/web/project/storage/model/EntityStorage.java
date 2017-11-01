package hdsec.web.project.storage.model;

import hdsec.web.project.common.BaseDomain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 固定存储介质类
 * 2014-4-22 上午12:05:06
 * 
 * @author renmingfei
 */
public class EntityStorage extends BaseDomain {
	private String storage_code = "";// 编号
	private String storage_name = "";// 名称
	private String input_user_iidd = "";// 录入员ID
	private String input_user_name = "";// 录入员姓名
	private String dept_id = "";// 设备所属部门ID
	private String dept_name = "";// 设备所属部门名称
	private String storage_series = "";// 设备编号
	private String storage_barcode = "";// 条码号
	private Date input_time = null;// 登记时间
	private Integer med_type = null;// 介质类型(1硬盘,2存储卡,3其他)
	private Integer seclv_code = null;// 密级
	private String seclv_name = "";// 密级名称
	private String duty_user_iidd = "";// 责任人ID
	private String duty_user_name = "";// 责任人姓名
	private String use_user_iidd = "";// 使用人ID
	private String use_user_name = "";// 使用人姓名
	private StorageStatusEnum storage_status = null;// 状态（0未分配1已分配，2送修处理中3报废处理中）默认0
	private String storage_model = "";// 型号 (例如PC机　DELL OPTIPLEX 360)
	private String storage_detail = "";// 设备配置：比如U盘 1G\2G\4G 是否加密PC CPU/内存/硬盘
	
	public String getStorage_code() {
		return storage_code;
	}
	
	public void setStorage_code(String storage_code) {
		this.storage_code = storage_code;
	}
	
	public String getStorage_name() {
		return storage_name;
	}
	
	public void setStorage_name(String storage_name) {
		this.storage_name = storage_name;
	}
	
	public String getInput_user_iidd() {
		return input_user_iidd;
	}
	
	public void setInput_user_iidd(String input_user_iidd) {
		this.input_user_iidd = input_user_iidd;
	}
	
	public String getInput_user_name() {
		return input_user_name;
	}
	
	public void setInput_user_name(String input_user_name) {
		this.input_user_name = input_user_name;
	}
	
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
	
	public String getStorage_series() {
		return storage_series;
	}
	
	public void setStorage_series(String storage_series) {
		this.storage_series = storage_series;
	}
	
	public String getStorage_barcode() {
		return storage_barcode;
	}
	
	public void setStorage_barcode(String storage_barcode) {
		this.storage_barcode = storage_barcode;
	}
	
	public Date getInput_time() {
		return input_time;
	}
	
	public String getInput_time_str() {
		return input_time == null ? "" : getSdf().format(input_time);
	}
	
	public void setInput_time(Date input_time) {
		this.input_time = input_time;
	}
	
	public Integer getMed_type() {
		return med_type;
	}
	
	public String getMed_type_name() {
		if (med_type == null) {
			return "";
		} else {
			switch (med_type) {
				case 1:
					return "硬盘";
				case 2:
					return "存储卡";
				default:
					return "其他";
			}
		}
	}
	
	public void setMed_type(Integer med_type) {
		this.med_type = med_type;
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
	
	public String getDuty_user_iidd() {
		return duty_user_iidd;
	}
	
	public void setDuty_user_iidd(String duty_user_iidd) {
		this.duty_user_iidd = duty_user_iidd;
	}
	
	public String getDuty_user_name() {
		return duty_user_name;
	}
	
	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name;
	}
	
	public String getUse_user_iidd() {
		return use_user_iidd;
	}
	
	public void setUse_user_iidd(String use_user_iidd) {
		this.use_user_iidd = use_user_iidd;
	}
	
	public String getUse_user_name() {
		return use_user_name;
	}
	
	public void setUse_user_name(String use_user_name) {
		this.use_user_name = use_user_name;
	}
	
	public StorageStatusEnum getStorage_status() {
		return storage_status;
	}
	
	public String getStorage_status_name() {
		return storage_status.getName();
	}
	
	public void setStorage_status(Integer storage_status) {
		this.storage_status = StorageStatusEnum.valueOf("SS" + storage_status);
	}
	
	public String getStorage_model() {
		return storage_model;
	}
	
	public void setStorage_model(String storage_model) {
		this.storage_model = storage_model;
	}
	
	public String getStorage_detail() {
		return storage_detail;
	}
	
	public void setStorage_detail(String storage_detail) {
		this.storage_detail = storage_detail;
	}
	
	public EntityStorage() {
		super();
	}
	
	public EntityStorage(SimpleDateFormat sdf) {
		super(sdf);
	}
	
	public EntityStorage(String storage_name, String input_user_iidd, String input_user_name, String dept_id,
			String dept_name, String storage_series, String storage_barcode, Date input_time, Integer med_type,
			Integer seclv_code, String seclv_name, String duty_user_iidd, String duty_user_name, String use_user_iidd,
			String use_user_name, String storage_model, String storage_detail) {
		super();
		this.storage_name = storage_name;
		this.input_user_iidd = input_user_iidd;
		this.input_user_name = input_user_name;
		this.dept_id = dept_id;
		this.dept_name = dept_name;
		this.storage_series = storage_series;
		this.storage_barcode = storage_barcode;
		this.input_time = input_time;
		this.med_type = med_type;
		this.seclv_code = seclv_code;
		this.seclv_name = seclv_name;
		this.duty_user_iidd = duty_user_iidd;
		this.duty_user_name = duty_user_name;
		this.use_user_iidd = use_user_iidd;
		this.use_user_name = use_user_name;
		this.storage_status = StorageStatusEnum.SS0;
		this.storage_model = storage_model;
		this.storage_detail = storage_detail;
	}
	
	public EntityStorage(String storage_code, String storage_name, String input_user_iidd, String input_user_name,
			String dept_id, String dept_name, String storage_series, String storage_barcode, Integer med_type,
			Integer seclv_code, String seclv_name, String duty_user_iidd, String duty_user_name, String storage_model,
			String storage_detail) {
		super();
		this.storage_code = storage_code;
		this.storage_name = storage_name;
		this.input_user_iidd = input_user_iidd;
		this.input_user_name = input_user_name;
		this.dept_id = dept_id;
		this.dept_name = dept_name;
		this.storage_series = storage_series;
		this.storage_barcode = storage_barcode;
		this.med_type = med_type;
		this.seclv_code = seclv_code;
		this.seclv_name = seclv_name;
		this.duty_user_iidd = duty_user_iidd;
		this.duty_user_name = duty_user_name;
		this.storage_model = storage_model;
		this.storage_detail = storage_detail;
		this.input_time = new Date();
		this.storage_status = StorageStatusEnum.SS0;
	}
	
}
