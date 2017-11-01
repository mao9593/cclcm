package hdsec.web.project.computer.model;

import hdsec.web.project.common.BaseDomain;

import java.util.Date;

public class EntityEventDevice extends BaseDomain {

	private String event_code = "";
	private String computer_barcode = ""; // 条码号
	private String computer_name = "";// 计算机名称
	private String computer_code = "";// 统一编码
	private String computer_model = ""; // 设备型号
	private Integer seclv_code = null; // 设备密级
	private Integer bef_seclv_code = null; // 设备密级
	private String duty_user_id = ""; // 责任人ID
	private String bef_duty_user_id = ""; // 变更前责任人ID
	private String duty_dept_id = ""; // 责任部门ID
	private String bef_duty_dept_id = ""; // 变更前责任部门ID
	private String duty_entp_id = ""; // 责任单位ID
	private String bef_duty_entp_id = ""; // 责任单位ID
	private String conf_code = ""; // 保密编号
	private String bef_conf_code = ""; // 变更前保密编号
	private String hdisk_no = "";// 硬盘序列号
	private String bef_hdisk_no = "";// 变更前硬盘序列号
	private String computer_ip = ""; // ip地址
	private String bef_computer_ip = ""; // 变更前ip地址
	private String computer_mac = ""; // mac地址
	private String bef_computer_mac = ""; // 变更前mac地址
	private Integer internet_type = null; // 网络类型
	private String computer_os = ""; // 计算机操作系统
	private Date install_time = null; // 安装时间
	private String storage_area = ""; // 存放区域
	private String bef_storage_area = ""; // 变更前存放区域
	private String storage_location = ""; // 物理位置
	private String bef_storage_location = ""; // 变更前物理位置
	private String output_point = "";// 输出端口
	private String input_point = "";// 输入端口
	private Date start_time = null;
	private Date end_time = null;
	private Integer apply_type = null;// usbkey申请类型
	private Integer power_type = null;// usbkey权限类别
	private String printer_model = "";
	private String printer_conf_code = "";
	private String printer_seclv_name = "";
	private String other_s = "";
	private String med_type = "";// 设备类型
	private String summ = "";
	private String oldconf_code = ""; // 原保密编号
	private String med_type_name = "";// 设备类型名称

	private String seclv_name = ""; // 设备密级
	private String bef_seclv_name = ""; // 变更前设备密级
	private String duty_user_name = ""; // 责任人名称
	private String bef_user_name = ""; // 变更前责任人名称
	private String duty_dept_name = ""; // 责任部门名称
	private String bef_dept_name = ""; // 变更前责任部门名称

	public String getSeclv_name() {
		return seclv_name;
	}

	public void setSeclv_name(String seclv_name) {
		this.seclv_name = seclv_name;
	}

	public String getBef_seclv_name() {
		return bef_seclv_name;
	}

	public void setBef_seclv_name(String bef_seclv_name) {
		this.bef_seclv_name = bef_seclv_name;
	}

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name;
	}

	public String getBef_user_name() {
		return bef_user_name;
	}

	public void setBef_user_name(String bef_user_name) {
		this.bef_user_name = bef_user_name;
	}

	public String getDuty_dept_name() {
		return duty_dept_name;
	}

	public void setDuty_dept_name(String duty_dept_name) {
		this.duty_dept_name = duty_dept_name;
	}

	public String getBef_dept_name() {
		return bef_dept_name;
	}

	public void setBef_dept_name(String bef_dept_name) {
		this.bef_dept_name = bef_dept_name;
	}

	public String getMed_type_name() {
		return med_type_name;
	}

	public void setMed_type_name(String med_type_name) {
		this.med_type_name = med_type_name;
	}

	public String getOldconf_code() {
		return oldconf_code;
	}

	public void setOldconf_code(String oldconf_code) {
		this.oldconf_code = oldconf_code;
	}

	public String getComputer_code() {
		return computer_code;
	}

	public void setComputer_code(String computer_code) {
		this.computer_code = computer_code;
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public String getMed_type() {
		return med_type;
	}

	public void setMed_type(String med_type) {
		this.med_type = med_type;
	}

	public void setOther_s(String other_s) {
		this.other_s = other_s;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getComputer_barcode() {
		return computer_barcode;
	}

	public void setComputer_barcode(String computer_barcode) {
		this.computer_barcode = computer_barcode;
	}

	public String getComputer_name() {
		return computer_name;
	}

	public void setComputer_name(String computer_name) {
		this.computer_name = computer_name;
	}

	public String getComputer_model() {
		return computer_model;
	}

	public void setComputer_model(String computer_model) {
		this.computer_model = computer_model;
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public Integer getBef_seclv_code() {
		return bef_seclv_code;
	}

	public void setBef_seclv_code(Integer bef_seclv_code) {
		this.bef_seclv_code = bef_seclv_code;
	}

	public String getDuty_user_id() {
		return duty_user_id;
	}

	public void setDuty_user_id(String duty_user_id) {
		this.duty_user_id = duty_user_id;
	}

	public String getBef_duty_user_id() {
		return bef_duty_user_id;
	}

	public void setBef_duty_user_id(String bef_duty_user_id) {
		this.bef_duty_user_id = bef_duty_user_id;
	}

	public String getDuty_dept_id() {
		return duty_dept_id;
	}

	public void setDuty_dept_id(String duty_dept_id) {
		this.duty_dept_id = duty_dept_id;
	}

	public String getBef_duty_dept_id() {
		return bef_duty_dept_id;
	}

	public void setBef_duty_dept_id(String bef_duty_dept_id) {
		this.bef_duty_dept_id = bef_duty_dept_id;
	}

	public String getDuty_entp_id() {
		return duty_entp_id;
	}

	public void setDuty_entp_id(String duty_entp_id) {
		this.duty_entp_id = duty_entp_id;
	}

	public String getBef_duty_entp_id() {
		return bef_duty_entp_id;
	}

	public void setBef_duty_entp_id(String bef_duty_entp_id) {
		this.bef_duty_entp_id = bef_duty_entp_id;
	}

	public String getConf_code() {
		return conf_code;
	}

	public void setConf_code(String conf_code) {
		this.conf_code = conf_code;
	}

	public String getBef_conf_code() {
		return bef_conf_code;
	}

	public void setBef_conf_code(String bef_conf_code) {
		this.bef_conf_code = bef_conf_code;
	}

	public String getHdisk_no() {
		return hdisk_no;
	}

	public void setHdisk_no(String hdisk_no) {
		this.hdisk_no = hdisk_no;
	}

	public String getBef_hdisk_no() {
		return bef_hdisk_no;
	}

	public void setBef_hdisk_no(String bef_hdisk_no) {
		this.bef_hdisk_no = bef_hdisk_no;
	}

	public String getComputer_ip() {
		return computer_ip;
	}

	public void setComputer_ip(String computer_ip) {
		this.computer_ip = computer_ip;
	}

	public String getBef_computer_ip() {
		return bef_computer_ip;
	}

	public void setBef_computer_ip(String bef_computer_ip) {
		this.bef_computer_ip = bef_computer_ip;
	}

	public String getComputer_mac() {
		return computer_mac;
	}

	public void setComputer_mac(String computer_mac) {
		this.computer_mac = computer_mac;
	}

	public String getBef_computer_mac() {
		return bef_computer_mac;
	}

	public void setBef_computer_mac(String bef_computer_mac) {
		this.bef_computer_mac = bef_computer_mac;
	}

	public Integer getInternet_type() {
		return internet_type;
	}

	public void setInternet_type(Integer internet_type) {
		this.internet_type = internet_type;
	}

	public String getComputer_os() {
		return computer_os;
	}

	public void setComputer_os(String computer_os) {
		this.computer_os = computer_os;
	}

	public Date getInstall_time() {
		return install_time;
	}

	public void setInstall_time(Date install_time) {
		this.install_time = install_time;
	}

	public String getStorage_area() {
		return storage_area;
	}

	public void setStorage_area(String storage_area) {
		this.storage_area = storage_area;
	}

	public String getBef_storage_area() {
		return bef_storage_area;
	}

	public void setBef_storage_area(String bef_storage_area) {
		this.bef_storage_area = bef_storage_area;
	}

	public String getStorage_location() {
		return storage_location;
	}

	public void setStorage_location(String storage_location) {
		this.storage_location = storage_location;
	}

	public String getBef_storage_location() {
		return bef_storage_location;
	}

	public void setBef_storage_location(String bef_storage_location) {
		this.bef_storage_location = bef_storage_location;
	}

	public String getOutput_point() {
		return output_point;
	}

	public void setOutput_point(String output_point) {
		this.output_point = output_point;
	}

	public String getInput_point() {
		return input_point;
	}

	public void setInput_point(String input_point) {
		this.input_point = input_point;
	}

	public String getStart_time_str() {
		return start_time == null ? "" : getSdf().format(start_time);
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time_str() {
		return end_time == null ? "" : getSdf().format(end_time);
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public Integer getApply_type() {
		return apply_type;
	}

	public void setApply_type(Integer apply_type) {
		this.apply_type = apply_type;
	}

	public Integer getPower_type() {
		return power_type;
	}

	public void setPower_type(Integer power_type) {
		this.power_type = power_type;
	}

	public String getPrinter_model() {
		return printer_model;
	}

	public void setPrinter_model(String printer_model) {
		this.printer_model = printer_model;
	}

	public String getPrinter_conf_code() {
		return printer_conf_code;
	}

	public void setPrinter_conf_code(String printer_conf_code) {
		this.printer_conf_code = printer_conf_code;
	}

	public String getPrinter_seclv_name() {
		return printer_seclv_name;
	}

	public void setPrinter_seclv_name(String printer_seclv_name) {
		this.printer_seclv_name = printer_seclv_name;
	}

	public String getOther_s() {
		return other_s;
	}

	public void setOthers(String other_s) {
		this.other_s = other_s;
	}

	public EntityEventDevice() {
		super();
	}

	public EntityEventDevice(String event_code, String computer_barcode, Integer seclv_code, Integer bef_seclv_code,
			String duty_user_id, String bef_duty_user_id, String duty_dept_id, String bef_duty_dept_id,
			String duty_entp_id, String bef_duty_entp_id, String computer_code, String bef_conf_code, String hdisk_no,
			String bef_hdisk_no, String computer_ip, String bef_computer_ip, String computer_mac,
			String bef_computer_mac, String storage_area, String bef_storage_area, String storage_location,
			String bef_storage_location, String other_s) {// 计算机变更

		this.event_code = event_code;
		this.computer_barcode = computer_barcode;
		this.seclv_code = seclv_code;
		this.bef_seclv_code = bef_seclv_code;
		this.duty_user_id = duty_user_id;
		this.bef_duty_user_id = bef_duty_user_id;
		this.duty_dept_id = duty_dept_id;
		this.bef_duty_dept_id = bef_duty_dept_id;
		this.duty_entp_id = duty_entp_id;
		this.bef_duty_entp_id = bef_duty_entp_id;
		this.computer_code = computer_code;
		this.bef_conf_code = bef_conf_code;
		this.hdisk_no = hdisk_no;
		this.bef_hdisk_no = bef_hdisk_no;
		this.computer_ip = computer_ip;
		this.bef_computer_ip = bef_computer_ip;
		this.computer_mac = computer_mac;
		this.bef_computer_mac = bef_computer_mac;
		this.storage_area = storage_area;
		this.bef_storage_area = bef_storage_area;
		this.storage_location = storage_location;
		this.bef_storage_location = bef_storage_location;
		this.other_s = other_s;
	}

	public EntityEventDevice(String event_code, String computer_name, Integer seclv_code, String duty_user_id,
			String duty_dept_id, String duty_entp_id, String computer_code, Integer internet_type, String storage_area,
			String storage_location, String hdisk_no, String computer_os, Date install_time, String computer_mac,
			String med_type, String output_point, String oldconf_code, String summ) {// 添加网络计算机
		this.event_code = event_code;
		this.computer_name = computer_name;
		this.seclv_code = seclv_code;
		this.duty_user_id = duty_user_id;
		this.duty_dept_id = duty_dept_id;
		this.duty_entp_id = duty_entp_id;
		this.computer_code = computer_code;
		this.internet_type = internet_type;
		this.storage_area = storage_area;
		this.storage_location = storage_location;
		this.hdisk_no = hdisk_no;
		this.computer_os = computer_os;
		this.install_time = install_time;
		this.computer_mac = computer_mac;
		this.med_type = med_type;
		this.output_point = output_point;
		this.oldconf_code = oldconf_code;
		this.summ = summ;
	}

	public EntityEventDevice(String event_code, String computer_name, Integer seclv_code, String duty_user_id,
			String duty_dept_id, String duty_entp_id, String computer_code, String hdisk_no, String computer_os,
			Date install_time, String med_type, String storage_area, String storage_location, String oldconf_code,
			String summ) {// 添加计算机单机
		this.event_code = event_code;
		this.computer_name = computer_name;
		this.seclv_code = seclv_code;
		this.duty_user_id = duty_user_id;
		this.duty_dept_id = duty_dept_id;
		this.duty_entp_id = duty_entp_id;
		this.computer_code = computer_code;
		this.hdisk_no = hdisk_no;
		this.computer_os = computer_os;
		this.install_time = install_time;
		this.med_type = med_type;
		this.storage_area = storage_area;
		this.storage_location = storage_location;
		this.oldconf_code = oldconf_code;
		this.summ = summ;
	}

	// event_content组成：|打印机型号|打印机密级编号|打印机密级|end|
	public EntityEventDevice(String event_code, String printer_model, String printer_conf_code,
			String printer_seclv_name) {// 保留本地打印机
		this.event_code = event_code;
		this.printer_model = printer_model;
		this.printer_conf_code = printer_conf_code;
		this.printer_seclv_name = printer_seclv_name;
	}

	// event_content组成：|输出端口类型|输入端口类型|开始时间|结束时间|end|
	public EntityEventDevice(String event_code, String output_point, String input_point, Date start_time, Date end_time) {// 开通端口
		this.event_code = event_code;
		this.output_point = output_point;
		this.input_point = input_point;
		this.start_time = start_time;
		this.end_time = end_time;
	}

	// event_content组成：|申请内容|权限类别|end|
	public EntityEventDevice(String event_code, Integer apply_type, Integer power_type) {// USBKEY申请/更新
		this.event_code = event_code;
		this.apply_type = apply_type;
		this.power_type = power_type;
	}

	public EntityEventDevice(String event_code, String computer_barcode, Integer seclv_code, Integer bef_seclv_code,
			String duty_user_id, String bef_duty_user_id, String duty_dept_id, String bef_duty_dept_id,
			String storage_location, String bef_storage_location, String other_s) {// 笔记本变更

		this.event_code = event_code;
		this.computer_barcode = computer_barcode;
		this.seclv_code = seclv_code;
		this.bef_seclv_code = bef_seclv_code;
		this.duty_user_id = duty_user_id;
		this.bef_duty_user_id = bef_duty_user_id;
		this.duty_dept_id = duty_dept_id;
		this.bef_duty_dept_id = bef_duty_dept_id;
		this.storage_location = storage_location;
		this.bef_storage_location = bef_storage_location;
		this.other_s = other_s;
	}
}