package hdsec.web.project.computer.model;

import hdsec.web.project.common.BaseDomain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 计算机台账
 * 
 * @author liuyaling 2015-4-29
 */
public class EntityComputer extends BaseDomain {

	private int computer_id; // 主键
	private String computer_barcode = ""; // 条码号
	private String computer_code = ""; // 资产编号
	private String conf_code = ""; // 保密编号
	private Integer seclv_code = null; // 设备密级
	private String duty_user_id = ""; // 责任人ID
	private String duty_dept_id = ""; // 责任部门ID
	private String duty_entp_id = ""; // 责任单位ID
	private Integer internet_type = null; // 网络类型
	private String computer_name = "";// 计算机名称
	private String computer_manufacture = "";// 厂商
	private String manufacture_country = ""; // 厂商国别
	private String computer_no = ""; // 出厂编号
	private String computer_model = ""; // 设备型号
	private String med_type = "";// 设备型号
	private String computer_detail = ""; // 设备配置
	private String hdisk_no = "";// 硬盘序列号
	private Date enable_time = null; // 启用时间
	private Integer computer_status = null; // 计算机状态（1在用，2停用，3维修，4报废，5销毁，6申请维修，7申请报废，8申请变更，9申请退网，10申请重装）
	private String host_name = ""; // 主机名
	private String computer_os = ""; // 计算机操作系统
	private Date install_time = null; // 安装时间
	private String key_code = "";// key编号
	private String computer_ip = ""; // ip地址
	private String computer_mac = ""; // mac地址
	private String computer_gateway = ""; // 网关
	private String output_point = "";// 输出端口
	private String input_point = "";// 输入端口
	private String switch_num = "";// 交换机
	private String switch_point = "";// 交换机端口
	private String storage_area = ""; // 存放区域
	private String storage_location = ""; // 物理位置
	private String computer_application = ""; // 计算机用途
	private String user_iidd = ""; // 录入人员ID
	private Date enter_time = null;// 录入时间
	private String summ = "";// 备注
	private String job_code = ""; // 审批单编号
	private String mark_code = "";// 掩码
	private String vlan_num = "";// VLAN
	private String software_type = "";// 安装软件类型
	private String software_summ = "";// 安装软件备注
	private String med_type_name = "";// 设备名称
	// private String duty_entp_name = ""; // 责任单位名称
	private String duty_dept_name = ""; // 责任部门名称
	private String duty_seclv_name = ""; // 责任人密级
	private String duty_user_name = ""; // 责任人名称
	private String seclv_name = ""; // 密级名称
	private String oldconf_code = ""; // 原保密编号

	public String getMed_type_name() {
		return med_type_name;
	}

	public void setMed_type_name(String med_type_name) {
		this.med_type_name = med_type_name;
	}

	public String getOutput_point_name() {
		String name = "";
		switch (output_point) {
		case "1":
			name = "打印机";
			break;
		case "2":
			name = "扫描仪";
			break;
		case "3":
			name = "单导盒";
			break;
		case "4":
			name = "条码枪";
			break;
		case "5":
			name = "USB口";
			break;
		default:
			name = "未输入";
			break;
		}

		return name;
	}

	public String getOutput_point() {
		return output_point;
	}

	public void setOutput_point(String output_point) {
		this.output_point = output_point;
	}

	public void setComputer_status(Integer computer_status) {
		this.computer_status = computer_status;
	}

	public String getOldconf_code() {
		return oldconf_code;
	}

	public void setOldconf_code(String oldconf_code) {
		this.oldconf_code = oldconf_code;
	}

	public String getDuty_entp_name() {
		if (duty_entp_id.equals("")) {
			return "";
		} else {
			switch (duty_entp_id) {
			case "1":
				return "总部";
			case "2":
				return "24所";
			case "3":
				return "26所";
			case "4":
				return "44所";
			default:
				return "";
			}
		}
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

	public String getInput_point() {
		return input_point;
	}

	public void setInput_point(String input_point) {
		this.input_point = input_point;
	}

	public String getComputer_status_name() {
		String name = "";
		if (computer_status != null) {
			switch (computer_status) {
			case 1:
				name = "在用";
				break;
			case 2:
				name = "停用";
				break;
			case 3:
				name = "维修";
				break;
			case 4:
				name = "报废";
				break;
			case 5:
				name = "销毁";
				break;
			case 6:
				name = "申请维修";
				break;
			case 7:
				name = "申请报废";
				break;
			case 8:
				name = "申请变更";
				break;
			case 9:
				name = "申请退网";
				break;
			case 10:
				name = "申请重装";
				break;
			default:
				name = "";
				break;
			}
		}
		return name;
	}

	// 涉密网、科研网、市政网、单机、互联网、单机、其他
	public String getInternet_type_name() {
		String name = "";
		if (internet_type != null) {
			switch (internet_type) {
			case 1:
				name = "涉密网";
				break;
			case 2:
				name = "科研网";
				break;
			case 3:
				name = "市政网";
				break;
			case 4:
				name = "单机";
				break;
			case 5:
				name = "互联网";
				break;
			case 6:
				name = "单机";
				break;
			default:
				name = "其他";
				break;
			}
		}

		return name;
	}

	public String getDuty_entp_id() {
		return duty_entp_id;
	}

	public void setDuty_entp_id(String duty_entp_id) {
		this.duty_entp_id = duty_entp_id;
	}

	public Integer getInternet_type() {
		return internet_type;
	}

	public void setInternet_type(Integer internet_type) {
		this.internet_type = internet_type;
	}

	public String getKey_code() {
		return key_code;
	}

	public void setKey_code(String key_code) {
		this.key_code = key_code;
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

	public String getStorage_area() {
		return storage_area;
	}

	public void setStorage_area(String storage_area) {
		this.storage_area = storage_area;
	}

	public Date getEnter_time() {
		return enter_time;
	}

	public void setEnter_time(Date enter_time) {
		this.enter_time = enter_time;
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public int getComputer_id() {
		return computer_id;
	}

	public void setComputer_id(int computer_id) {
		this.computer_id = computer_id;
	}

	public String getComputer_detail() {
		return computer_detail;
	}

	public void setComputer_detail(String computer_detail) {
		this.computer_detail = computer_detail;
	}

	public String getComputer_code() {
		return computer_code;
	}

	public void setComputer_code(String computer_code) {
		this.computer_code = computer_code;
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

	public String getComputer_application() {
		return computer_application;
	}

	public void setComputer_application(String computer_application) {
		this.computer_application = computer_application;
	}

	public String getSeclv_name() {
		return seclv_name;
	}

	public void setSeclv_name(String seclv_name) {
		this.seclv_name = seclv_name;
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

	public String getHdisk_no() {
		return hdisk_no;
	}

	public void setHdisk_no(String hdisk_no) {
		this.hdisk_no = hdisk_no;
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

	public String getEnable_time() {
		return enable_time == null ? "" : getSdf().format(enable_time);
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

	public String getEnter_time_str() {
		return enter_time == null ? "" : getSdf().format(enter_time);
	}

	public String getInstall_time_str() {
		return install_time == null ? "" : getSdf().format(install_time);
	}

	public Date getInstall_time() {
		return install_time;
	}

	public void setInstall_time(Date install_time) {
		this.install_time = install_time;
	}

	public String getStorage_location() {
		return storage_location;
	}

	public void setStorage_location(String storage_location) {
		this.storage_location = storage_location;
	}

	public String getDuty_dept_name() {
		return duty_dept_name;
	}

	public void setDuty_dept_name(String duty_dept_name) {
		this.duty_dept_name = duty_dept_name;
	}

	public String getDuty_seclv_name() {
		return duty_seclv_name;
	}

	public void setDuty_seclv_name(String duty_seclv_name) {
		this.duty_seclv_name = duty_seclv_name;
	}

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name;
	}

	public String getJob_code() {
		return job_code;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
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

	public EntityComputer() {
		super();
	}

	public EntityComputer(SimpleDateFormat sdf) {
		super(sdf);
	}

	public EntityComputer(String computer_barcode, String computer_name, String computer_model, String computer_no,
			String computer_detail, String hdisk_no, String computer_code, String conf_code, Integer seclv_code,
			String duty_user_id, String duty_dept_id, String duty_entp_id, String computer_os, Date install_time,
			String key_code, String computer_ip, String computer_mac, String output_point, String switch_num,
			String switch_point, Integer internet_type, String storage_area, String storage_location,
			Integer computer_status, String computer_application, Date enable_time, String summ, String user_iidd,
			String job_code, String mark_code, String vlan_num, String computer_gateway, String med_type,
			String software_type, String software_summ, String oldconf_code) {

		super();

		this.computer_barcode = computer_barcode;
		this.computer_name = computer_name;
		this.computer_model = computer_model;
		this.computer_no = computer_no;
		this.computer_detail = computer_detail;
		this.hdisk_no = hdisk_no;
		this.computer_code = computer_code;
		this.conf_code = conf_code;
		this.seclv_code = seclv_code;
		this.duty_user_id = duty_user_id;
		this.duty_dept_id = duty_dept_id;
		this.duty_entp_id = duty_entp_id;
		this.computer_os = computer_os;
		this.install_time = install_time;
		this.key_code = key_code;
		this.computer_ip = computer_ip;
		this.computer_mac = computer_mac;
		this.output_point = output_point;
		this.switch_num = switch_num;
		this.switch_point = switch_point;
		this.internet_type = internet_type;
		this.storage_area = storage_area;
		this.storage_location = storage_location;
		this.computer_status = computer_status;
		this.computer_application = computer_application;
		this.enable_time = enable_time;
		this.summ = summ;
		this.job_code = job_code;
		this.user_iidd = user_iidd;
		this.mark_code = mark_code;
		this.vlan_num = vlan_num;
		this.computer_gateway = computer_gateway;
		this.med_type = med_type;
		this.software_summ = software_summ;
		this.software_type = software_type;
		this.oldconf_code = oldconf_code;
	}

	public EntityComputer(String computer_barcode, String computer_name, Integer seclv_code, String duty_user_id,
			String duty_dept_id, String duty_entp_id, String computer_code, Integer internet_type, String storage_area,
			String storage_location, String summ) {

		super();
		this.computer_barcode = computer_barcode;
		this.computer_name = computer_name;
		this.seclv_code = seclv_code;
		this.duty_dept_id = duty_dept_id;
		this.duty_user_id = duty_user_id;
		this.duty_entp_id = duty_entp_id;
		this.computer_code = computer_code;
		this.internet_type = internet_type;
		this.storage_area = storage_area;
		this.storage_location = storage_location;
		this.summ = summ;
	}

	public EntityComputer(String computer_barcode, String computer_name, Integer seclv_code, String seclv_name,
			String duty_user_id, String duty_user_name, String duty_dept_id, String duty_dept_name,
			String duty_entp_id, String computer_code, Integer internet_type, String hdisk_no, String computer_os,
			Date install_time, String computer_ip, String computer_mac, String storage_area, String storage_location,
			String summ, String software_type, String software_summ, String med_type, String output_point,
			String oldconf_code, String med_type_name) {

		super();
		this.computer_barcode = computer_barcode;
		this.computer_name = computer_name;
		this.seclv_code = seclv_code;
		this.seclv_name = seclv_name;
		this.duty_dept_id = duty_dept_id;
		this.duty_dept_name = duty_dept_name;
		this.duty_user_id = duty_user_id;
		this.duty_user_name = duty_user_name;
		this.duty_entp_id = duty_entp_id;
		this.computer_code = computer_code;
		this.internet_type = internet_type;
		this.hdisk_no = hdisk_no;
		this.computer_os = computer_os;
		this.computer_ip = computer_ip;
		this.computer_mac = computer_mac;
		this.install_time = install_time;
		this.storage_area = storage_area;
		this.storage_location = storage_location;
		this.summ = summ;
		this.software_type = software_type;
		this.software_summ = software_summ;
		this.med_type = med_type;
		this.output_point = output_point;
		this.oldconf_code = oldconf_code;
		this.med_type_name = med_type_name;
	}

	public EntityComputer(String computer_barcode, String computer_code, String conf_code, Integer seclv_code,
			String duty_user_id, String duty_dept_id, String duty_entp_id, Integer internet_type, String computer_name,
			String computer_manufacture, String manufacture_country, String computer_no, String computer_model,
			String med_type, String computer_detail, String hdisk_no, Date enable_time, Integer computer_status,
			String host_name, String computer_os, Date install_time, String key_code, String computer_ip,
			String computer_mac, String computer_gateway, String output_point, String input_point, String switch_num,
			String switch_point, String storage_area, String storage_location, String computer_application,
			String user_iidd, Date enter_time, String summ, String mark_code, String vlan_num, String software_type,
			String software_summ, String oldconf_code) {

		this.computer_barcode = computer_barcode;
		this.computer_code = computer_code;
		this.conf_code = conf_code;
		this.seclv_code = seclv_code;
		this.duty_user_id = duty_user_id;
		this.duty_dept_id = duty_dept_id;
		this.duty_entp_id = duty_entp_id;
		this.internet_type = internet_type;
		this.computer_name = computer_name;
		this.computer_manufacture = computer_manufacture;
		this.manufacture_country = manufacture_country;
		this.computer_no = computer_no;
		this.computer_model = computer_model;
		this.med_type = med_type;
		this.computer_detail = computer_detail;
		this.hdisk_no = hdisk_no;
		this.enable_time = enable_time;
		this.computer_status = computer_status;
		this.host_name = host_name;
		this.computer_os = computer_os;
		this.install_time = install_time;
		this.key_code = key_code;
		this.computer_ip = computer_ip;
		this.computer_mac = computer_mac;
		this.computer_gateway = computer_gateway;
		this.output_point = output_point;
		this.input_point = input_point;
		this.switch_num = switch_num;
		this.switch_point = switch_point;
		this.storage_area = storage_area;
		this.storage_location = storage_location;
		this.computer_application = computer_application;
		this.user_iidd = user_iidd;
		this.enter_time = enter_time;
		this.summ = summ;
		this.mark_code = mark_code;
		this.vlan_num = vlan_num;
		this.software_type = software_type;
		this.software_summ = software_summ;
		this.oldconf_code = oldconf_code;
	}
}
