package hdsec.web.project.computer.model;

import hdsec.web.project.common.BaseDomain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EntityDeviceOperation extends BaseDomain {

	private String event_code = ""; // 申请号
	private String device_barcode = ""; // 设备号
	private Integer operation_type = null; // 操作类型
	private String operation_user_id = ""; // 操作人ID
	private Date operation_time = null;// 操作时间
	private String old_content = "";
	private String new_content = "";
	private String user_id = "";
	private String key_time = "";
	private Date start_time = null;
	private Date end_time = null;
	private String key_code = "";
	private String computer_ip = "";
	private String switch_num = "";
	private String switch_point = "";
	private String mark_code = "";
	private String vlan_num = "";
	private String computer_gateway = "";
	private String software_type = "";
	private String software_summ = "";
	private String operation_content = "";
	private String hdisk_no = "";
	private String computer_mac = "";
	private String computer_os = "";
	private Date install_time = null;

	public String getHdisk_no() {
		return hdisk_no;
	}

	public void setHdisk_no(String hdisk_no) {
		this.hdisk_no = hdisk_no;
	}

	public String getComputer_mac() {
		return computer_mac;
	}

	public void setComputer_mac(String computer_mac) {
		this.computer_mac = computer_mac;
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

	public String getOperation_content() {
		return operation_content;
	}

	public void setOperation_content(String operation_content) {
		this.operation_content = operation_content;
	}

	public String getOld_content() {
		return old_content;
	}

	public void setOld_content(String old_content) {
		this.old_content = old_content;
	}

	public String getNew_content() {
		return new_content;
	}

	public void setNew_content(String new_content) {
		this.new_content = new_content;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getKey_time() {
		return key_time;
	}

	public void setKey_time(String key_time) {
		this.key_time = key_time;
	}

	public String getStart_time() {
		return start_time == null ? "" : getSdf().format(start_time);
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;

	}

	public String getEnd_time() {
		return end_time == null ? "" : getSdf().format(end_time);
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public String getKey_code() {
		return key_code;
	}

	public void setKey_code(String key_code) {
		this.key_code = key_code;
	}

	public String getComputer_ip() {
		return computer_ip;
	}

	public void setComputer_ip(String computer_ip) {
		this.computer_ip = computer_ip;
	}

	public String getSwitch_num() {
		return switch_num;
	}

	public void setSwitch_num(String switch_num) {
		this.switch_num = switch_num;
	}

	public String getSwitch_point() {
		return switch_point;
	}

	public void setSwitch_point(String switch_point) {
		this.switch_point = switch_point;
	}

	public String getMark_code() {
		return mark_code;
	}

	public void setMark_code(String mark_code) {
		this.mark_code = mark_code;
	}

	public String getVlan_num() {
		return vlan_num;
	}

	public void setVlan_num(String vlan_num) {
		this.vlan_num = vlan_num;
	}

	public String getComputer_gateway() {
		return computer_gateway;
	}

	public void setComputer_gateway(String computer_gateway) {
		this.computer_gateway = computer_gateway;
	}

	public String getSoftware_type() {
		return software_type;
	}

	public void setSoftware_type(String software_type) {
		this.software_type = software_type;
	}

	public String getSoftware_summ() {
		return software_summ;
	}

	public void setSoftware_summ(String software_summ) {
		this.software_summ = software_summ;
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

	public Integer getOperation_type() {
		return operation_type;
	}

	public void setOperation_type(Integer operation_type) {
		this.operation_type = operation_type;
	}

	public String getOperation_user_id() {
		return operation_user_id;
	}

	public void setOperation_user_id(String operation_user_id) {
		this.operation_user_id = operation_user_id;
	}

	public String getOperation_time() {
		return operation_time == null ? "" : getSdf().format(operation_time);
	}

	public void setOperation_time(Date operation_time) {
		this.operation_time = operation_time;
	}

	public EntityDeviceOperation() {
		super();
	}

	public EntityDeviceOperation(SimpleDateFormat sdf) {
		super(sdf);
	}

	public EntityDeviceOperation(String event_code, String device_barcode, Integer operation_type,
			String operation_content, String operation_user_id, Date operation_time) {
		super();
		this.event_code = event_code;
		this.device_barcode = device_barcode;
		this.operation_type = operation_type;
		this.operation_user_id = operation_user_id;
		this.operation_time = operation_time;
	}

	/**
	 * 操作系统重装、硬盘更换使用
	 * 
	 * @param event_code
	 * @param device_barcode
	 * @param operation_type
	 * @param operation_user_id
	 * @param operation_time
	 * @param old_content
	 * @param new_content
	 */
	public EntityDeviceOperation(String event_code, String device_barcode, Integer operation_type,
			String operation_user_id, Date operation_time, String old_content, String new_content) {
		super();
		this.event_code = event_code;
		this.device_barcode = device_barcode;
		this.operation_type = operation_type;
		this.operation_user_id = operation_user_id;
		this.operation_time = operation_time;
		this.old_content = old_content;
		this.new_content = new_content;
	}

	/**
	 * USBKEY使用
	 * 
	 * @param event_code
	 * @param device_barcode
	 * @param operation_type
	 * @param operation_user_id
	 * @param operation_time
	 * @param old_content
	 * @param new_content
	 * @param user_id
	 * @param key_time
	 * @param start_time
	 * @param end_time
	 */
	public EntityDeviceOperation(String event_code, String device_barcode, Integer operation_type,
			String operation_user_id, Date operation_time, String old_content, String new_content, String user_id,
			String key_time, Date start_time, Date end_time) {
		super();
		this.event_code = event_code;
		this.device_barcode = device_barcode;
		this.operation_type = operation_type;
		this.operation_user_id = operation_user_id;
		this.operation_time = operation_time;
		this.old_content = old_content;
		this.new_content = new_content;
		this.user_id = user_id;
		this.key_time = key_time;
		this.start_time = start_time;
		this.end_time = end_time;
	}

	/**
	 * 包含全部字段
	 * 
	 * @param event_code
	 * @param device_barcode
	 * @param operation_type
	 * @param operation_user_id
	 * @param operation_time
	 * @param old_content
	 * @param new_content
	 * @param user_id
	 * @param key_time
	 * @param start_time
	 * @param end_time
	 * @param key_code
	 * @param computer_ip
	 * @param switch_num
	 * @param switch_point
	 * @param mark_code
	 * @param vlan_num
	 * @param computer_gateway
	 * @param software_type
	 * @param software_summ
	 * @param operation_content
	 */
	public EntityDeviceOperation(String event_code, String device_barcode, Integer operation_type,
			String operation_user_id, Date operation_time, String old_content, String new_content, String user_id,
			String key_time, Date start_time, Date end_time, String key_code, String computer_ip, String switch_num,
			String switch_point, String mark_code, String vlan_num, String computer_gateway, String software_type,
			String software_summ, String operation_content) {
		super();
		this.event_code = event_code;
		this.device_barcode = device_barcode;
		this.operation_type = operation_type;
		this.operation_user_id = operation_user_id;
		this.operation_time = operation_time;
		this.old_content = old_content;
		this.new_content = new_content;
		this.user_id = user_id;
		this.key_time = key_time;
		this.start_time = start_time;
		this.end_time = end_time;
		this.key_code = key_code;
		this.computer_ip = computer_ip;
		this.switch_num = switch_num;
		this.switch_point = switch_point;
		this.mark_code = mark_code;
		this.vlan_num = vlan_num;
		this.computer_gateway = computer_gateway;
		this.software_type = software_type;
		this.software_summ = software_summ;
		this.operation_content = operation_content;
	}

}