package hdsec.web.project.computer.action;

import hdsec.web.project.computer.model.EntityComputer;
import hdsec.web.project.device.model.DeviceType;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 更新计算机台账
 * 
 * @author liuyaling 2015-5-5
 */
public class UpdateComputerAction extends ComputerBaseAction {

	private static final long serialVersionUID = 1L;
	EntityComputer computer = null;
	private String update = "N";// 是否更改计算机信息标志

	private String computer_barcode = ""; // 条码号
	private String computer_name = "";// 计算机名称
	private String computer_manufacture = "";// 厂商
	private String manufacture_country = ""; // 厂商国别
	private String computer_no = ""; // 出厂编号
	private String computer_model = ""; // 设备型号
	private String med_type="";// 设备型号
	private String computer_detail = ""; // 设备配置
	private String hdisk_no = "";// 硬盘序列号
	private String computer_code = ""; // 设备编号
	private String conf_code = ""; // 保密编号
	private Integer seclv_code = null; // 设备密级
	private String duty_user_id = ""; // 责任人ID
	private String duty_dept_id = ""; // 责任部门ID
	private String duty_entp_id = ""; // 责任单位ID
	private String user_iidd = ""; // 录入人员ID
	private Date enable_time = null; // 启用时间
	private Integer computer_status; // 计算机状态（1在用，2停用，3维修，4报废，5销毁，6申请维修，7申请报废）
	private String host_name = ""; // 主机名
	private String computer_os = ""; // 计算机操作系统
	private Date install_time = null; // 安装时间
	private String computer_ip = ""; // ip地址
	private String computer_mac = ""; // mac地址
	private String computer_gateway = ""; // 网关

	private Integer internet_type = null; // 网络类型
	private String storage_area = ""; // 存放区域
	private String storage_location = ""; // 物理位置
	private String computer_application = ""; // 计算机用途
	private String job_code = ""; // 审批单编号
	private String key_code = "";// key编号
	private String output_point = "";// 输出端口
	private String switch_num = "";// 交换机
	private String switch_point = "";// 端口号
	private String summ = "";
	private String mark_code = "";// 掩码
	private String vlan_num = "";// VLAN
	private String software_type = "";// 安装软件类型
	private String software_summ = "";// 安装软件备注
	private String oldconf_code = ""; // 原保密编号
	private Date enter_time = null;// 录入时间
	private String input_point = "";// 输入端口

	public void setSoftware_type(String software_type) {
		this.software_type = software_type;
	}

	public void setSoftware_summ(String software_summ) {
		this.software_summ = software_summ;
	}

	public void setOldconf_code(String oldconf_code) {
		this.oldconf_code = oldconf_code;
	}

	public void setEnter_time(Date enter_time) {
		this.enter_time = enter_time;
	}

	public void setInput_point(String input_point) {
		this.input_point = input_point;
	}

	public void setDuty_entp_id(String duty_entp_id) {
		this.duty_entp_id = duty_entp_id;
	}

	public void setComputer_status(Integer computer_status) {
		this.computer_status = computer_status;
	}

	public void setStorage_area(String storage_area) {
		this.storage_area = storage_area;
	}

	public void setKey_code(String key_code) {
		this.key_code = key_code;
	}

	public void setOutput_point(String output_point) {
		this.output_point = output_point;
	}

	public void setSwitch_num(String switch_num) {
		this.switch_num = switch_num;
	}

	public void setSwitch_point(String switch_point) {
		this.switch_point = switch_point;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public void setMark_code(String mark_code) {
		this.mark_code = mark_code;
	}

	public void setVlan_num(String vlan_num) {
		this.vlan_num = vlan_num;
	}

	public Integer getInternet_type() {
		return internet_type;
	}

	public void setInternet_type(Integer internet_type) {
		this.internet_type = internet_type;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public List<DeviceType> getComputerTypeList() {
		return computerService.getDeviceTypeList();
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getComputer_name() {
		return computer_name;
	}

	public void setComputer_name(String computer_name) {
		this.computer_name = computer_name;
	}

	public String getComputer_manufacture() {
		return computer_manufacture;
	}

	public void setComputer_manufacture(String computer_manufacture) {
		this.computer_manufacture = computer_manufacture;
	}

	public String getManufacture_country() {
		return manufacture_country;
	}

	public void setManufacture_country(String manufacture_country) {
		this.manufacture_country = manufacture_country;
	}

	public String getComputer_no() {
		return computer_no;
	}

	public void setComputer_no(String computer_no) {
		this.computer_no = computer_no;
	}

	public String getComputer_model() {
		return computer_model;
	}

	public void setComputer_model(String computer_model) {
		this.computer_model = computer_model;
	}

	public String getMed_type() {
		return med_type;
	}

	public void setMed_type(String med_type) {
		this.med_type = med_type;
	}

	public String getComputer_detail() {
		return computer_detail;
	}

	public void setComputer_detail(String computer_detail) {
		this.computer_detail = computer_detail;
	}

	public String getHdisk_no() {
		return hdisk_no;
	}

	public void setHdisk_no(String hdisk_no) {
		this.hdisk_no = hdisk_no;
	}

	public String getComputer_code() {
		return computer_code;
	}

	public void setComputer_code(String computer_code) {
		this.computer_code = computer_code;
	}

	public String getConf_code() {
		return conf_code;
	}

	public void setConf_code(String conf_code) {
		this.conf_code = conf_code;
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getDuty_user_id() {
		return duty_user_id;
	}

	public void setDuty_user_id(String duty_user_id) {
		this.duty_user_id = duty_user_id;
	}

	public String getDuty_dept_id() {
		return duty_dept_id;
	}

	public void setDuty_dept_id(String duty_dept_id) {
		this.duty_dept_id = duty_dept_id;
	}

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public Date getEnable_time() {
		return enable_time;
	}

	public void setEnable_time(Date enable_time) {
		this.enable_time = enable_time;
	}

	public int getComputer_status() {
		return computer_status;
	}

	public void setComputer_status(int computer_status) {
		this.computer_status = computer_status;
	}

	public String getHost_name() {
		return host_name;
	}

	public void setHost_name(String host_name) {
		this.host_name = host_name;
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

	public String getComputer_ip() {
		return computer_ip;
	}

	public void setComputer_ip(String computer_ip) {
		this.computer_ip = computer_ip;
	}

	public String getComputer_mac() {
		return computer_mac;
	}

	public void setComputer_mac(String computer_mac) {
		this.computer_mac = computer_mac;
	}

	public String getComputer_gateway() {
		return computer_gateway;
	}

	public void setComputer_gateway(String computer_gateway) {
		this.computer_gateway = computer_gateway;
	}

	public String getStorage_location() {
		return storage_location;
	}

	public void setStorage_location(String storage_location) {
		this.storage_location = storage_location;
	}

	public String getComputer_application() {
		return computer_application;
	}

	public void setComputer_application(String computer_application) {
		this.computer_application = computer_application;
	}

	public String getJob_code() {
		return job_code;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public EntityComputer getComputer() {
		return computer;
	}

	public void setComputer(EntityComputer computer) {
		this.computer = computer;
	}

	public String getComputer_barcode() {
		return computer_barcode;
	}

	public void setComputer_barcode(String computer_barcode) {
		this.computer_barcode = computer_barcode;
	}

	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("N")) {
			// device = deviceService.getDeviceByCode(device_code);
			if (StringUtils.hasLength(computer_barcode)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("computer_barcode", computer_barcode);
				computer = computerService.getComputerByMap(map);
				return SUCCESS;
			} else {
				throw new Exception("无法查询条码号，请重新尝试。");
			}
		} else {
			computer = new EntityComputer(computer_barcode, computer_code, conf_code, seclv_code, duty_user_id,
					duty_dept_id, duty_entp_id, internet_type, computer_name, computer_manufacture,
					manufacture_country, computer_no, computer_model, med_type, computer_detail, hdisk_no, enable_time,
					computer_status, host_name, computer_os, install_time, key_code, computer_ip, computer_mac,
					computer_gateway, output_point, input_point, switch_num, switch_point, storage_area,
					storage_location, computer_application, user_iidd, enter_time, summ, mark_code, vlan_num,
					software_type, software_summ, oldconf_code);

			computerService.updateComputer(computer);
			insertCommonLog("修改计算机：条码号[" + computer_barcode + "]");
			return "ok";
		}
	}
}