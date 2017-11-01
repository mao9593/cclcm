package hdsec.web.project.computer.model;

import hdsec.web.project.common.BaseDomain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 信息设备台账
 * 
 * @author guojiao
 */
public class EntityInfoDevice extends BaseDomain {
	private String device_barcode = "";// 设备条码
	private String event_code = "";// 作业编号
	private String user_iidd = "";// 录入员ID
	private String user_name = "";// 录入员姓名
	private String dept_id = "";// 录入员部门ID
	private String dept_name = "";// 录入员部门
	private String duty_user_id = "";// 责任人ID
	private String duty_user_name = "";// 责任人
	private String duty_dept_id = "";// 责任人部门ID
	private String duty_dept_name = "";// 责任人部门
	private String conf_code = "";// 保密编号
	private String device_series = "";// 设备编号
	private String device_version = "";// 型号
	private String brand_type = "";// 品牌类型
	private Integer device_seclv = null;// 设备密级
	private String device_usage = "";// 设备用途
	private Date purchase_time = null;// 采购时间
	private Date use_time = null;// 领用/启用时间
	private Date destroy_time = null;// 报废时间
	private String location = "";// 安装地点
	private String serial_num = "";// 序列号
	private String cert_name = "";// 检测证书名称
	private String cert_num = "";// 证书编号
	private String memory = "";// 内存/容量
	private Integer device_type = null;// 设备类型(1:计算机 2:网络设备 3:外部设备 4:办公自动化设备 5:安全产品 6:介质)
	private String info_id = "";// 详细设备ID
	private Integer device_statues = null;// 状态
	private String is_sealed = "";// 是否封存（Y/N）默认为N
	private String detail = "";// 设备详情
	private String job_code = "";// 外键-所属任务
	private String seclv_name = "";// 设备密级名称
	private String info_type = "";// 子类型名称
	private String oldconf_code = "";// 原保密编号
	private String company = "";// 单位

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getOldconf_code() {
		return oldconf_code;
	}

	public void setOldconf_code(String oldconf_code) {
		this.oldconf_code = oldconf_code;
	}

	public String getInfo_type() {
		return info_type;
	}

	public void setInfo_type(String info_type) {
		this.info_type = info_type;
	}

	public String getSeclv_name() {
		return seclv_name;
	}

	public void setSeclv_name(String seclv_name) {
		this.seclv_name = seclv_name;
	}

	public String getDevice_barcode() {
		return device_barcode;
	}

	public void setDevice_barcode(String device_barcode) {
		this.device_barcode = device_barcode;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
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

	public String getDuty_user_id() {
		return duty_user_id;
	}

	public void setDuty_user_id(String duty_user_id) {
		this.duty_user_id = duty_user_id;
	}

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name;
	}

	public String getDuty_dept_id() {
		return duty_dept_id;
	}

	public void setDuty_dept_id(String duty_dept_id) {
		this.duty_dept_id = duty_dept_id;
	}

	public String getDuty_dept_name() {
		return duty_dept_name;
	}

	public void setDuty_dept_name(String duty_dept_name) {
		this.duty_dept_name = duty_dept_name;
	}

	public String getConf_code() {
		return conf_code;
	}

	public void setConf_code(String conf_code) {
		this.conf_code = conf_code;
	}

	public String getDevice_series() {
		return device_series;
	}

	public void setDevice_series(String device_series) {
		this.device_series = device_series;
	}

	public String getDevice_version() {
		return device_version;
	}

	public void setDevice_version(String device_version) {
		this.device_version = device_version;
	}

	public String getBrand_type() {
		return brand_type;
	}

	public void setBrand_type(String brand_type) {
		this.brand_type = brand_type;
	}

	public String getDevice_usage() {
		return device_usage;
	}

	public void setDevice_usage(String device_usage) {
		this.device_usage = device_usage;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSerial_num() {
		return serial_num;
	}

	public void setSerial_num(String serial_num) {
		this.serial_num = serial_num;
	}

	public String getCert_name() {
		return cert_name;
	}

	public void setCert_name(String cert_name) {
		this.cert_name = cert_name;
	}

	public String getCert_num() {
		return cert_num;
	}

	public void setCert_num(String cert_num) {
		this.cert_num = cert_num;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public Integer getDevice_seclv() {
		return device_seclv;
	}

	public void setDevice_seclv(Integer device_seclv) {
		this.device_seclv = device_seclv;
	}

	public String getPurchase_time_str() {
		return purchase_time == null ? "" : getSdf().format(purchase_time);
	}

	public String getUse_time_str() {
		return use_time == null ? "" : getSdf().format(use_time);
	}

	public String getDestroy_time_str() {
		return destroy_time == null ? "" : getSdf().format(destroy_time);
	}

	public Date getPurchase_time() {
		return purchase_time;
	}

	public void setPurchase_time(Date purchase_time) {
		this.purchase_time = purchase_time;
	}

	public Date getUse_time() {
		return use_time;
	}

	public void setUse_time(Date use_time) {
		this.use_time = use_time;
	}

	public Date getDestroy_time() {
		return destroy_time;
	}

	public void setDestroy_time(Date destroy_time) {
		this.destroy_time = destroy_time;
	}

	public Integer getDevice_type() {
		return device_type;
	}

	public void setDevice_type(Integer device_type) {
		this.device_type = device_type;
	}

	public String getInfo_id() {
		return info_id;
	}

	public void setInfo_id(String info_id) {
		this.info_id = info_id;
	}

	@Override
	public String getIs_sealed() {
		return is_sealed;
	}

	@Override
	public void setIs_sealed(String is_sealed) {
		this.is_sealed = is_sealed;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getJob_code() {
		return job_code;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public Integer getDevice_statues() {
		return device_statues;
	}

	public void setDevice_statues(Integer device_statue) {
		this.device_statues = device_statue;
	}

	public String getDevice_statues_name() {
		if (device_statues == null) {
			return "";
		} else {
			switch (device_statues) {
			case 1:
				return "在用";
			case 2:
				return "停用";
			case 3:
				return "维修";
			case 4:
				return "报废";
			case 5:
				return "已回收";
			case 6:
				return "已销毁";
			case 7:
				return "申请变更";
			case 8:
				return "申请报废";
			default:
				return "";
			}
		}
	}

	public String getDevice_type_name() {
		if (device_type == null) {
			return "";
		} else {
			switch (device_type) {
			case 2:
				return "网络设备";
			case 3:
				return "外部设备";
			case 4:
				return "办公自动化设备";
			case 5:
				return "安全产品";
			case 6:
				return "介质";
			default:
				return "";
			}
		}
	}

	public EntityInfoDevice() {
		super();
	}

	public EntityInfoDevice(SimpleDateFormat sdf) {
		super(sdf);
	}

	public EntityInfoDevice(String device_barcode, String event_code, String user_iidd, String user_name,
			String dept_id, String dept_name, String duty_user_id, String duty_user_name, String duty_dept_id,
			String duty_dept_name, String conf_code, String device_series, String device_version, String brand_type,
			Integer device_seclv, String device_usage, Date purchase_time, Date use_time, Date destroy_time,
			String location, String serial_num, String cert_name, String cert_num, String memory, Integer device_type,
			String info_id, String detail, String oldconf_code, String company, Integer device_statues) {
		super();

		this.device_barcode = device_barcode;
		this.user_iidd = user_iidd;
		this.user_name = user_name;
		this.duty_user_id = duty_user_id;
		this.duty_user_name = duty_user_name;
		this.duty_dept_id = duty_dept_id;
		this.duty_dept_name = duty_dept_name;
		this.conf_code = conf_code;
		this.device_series = device_series;
		this.device_version = device_version;
		this.brand_type = brand_type;
		this.device_seclv = device_seclv;
		this.device_usage = device_usage;
		this.purchase_time = purchase_time;
		this.use_time = use_time;
		this.location = location;
		this.cert_name = cert_name;
		this.cert_num = cert_num;
		this.device_type = device_type;
		this.memory = memory;
		this.info_id = info_id;
		this.detail = detail;
		this.serial_num = serial_num;
		this.dept_id = dept_id;
		this.dept_name = dept_name;
		this.oldconf_code = oldconf_code;
		this.company = company;
		this.device_statues = device_statues;
	}
}