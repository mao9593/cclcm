package hdsec.web.project.basic.model;

import hdsec.web.project.common.BaseDomain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SysConsole extends BaseDomain {
	private String console_code;// 控制台代号
	private String console_name;// 控制台
	private String hardware_type;// 硬件类型
	private String console_type;// 控制台类型
	private String console_type_name;// 控制台类型名称
	private int seclv_code;// 密级
	private String seclv_name;// 密级名称
	private String console_location;// 位置
	private String curr_version;// 当前版本
	private String set_version;// 设定版本
	private String console_mac;// MAC 地址
	private String console_ipaddr;// ip 地址
	private Date install_time;// 安装时间
	private String console_status;// 监控状态
	private Date uninstall_time;// 卸载时间
	private String is_online;// 是否在线
	private Date last_connect_time;// 上次连接时间
	private String is_barcode_print;// 是否出条形码
	private String allowSecLevel;

	public SysConsole(String console_code, String console_name, String hardware_type, String console_type,
			int seclv_code, String console_location, String curr_version, String set_version, String console_mac,
			String console_ipaddr, Date install_time, String console_status, Date uninstall_time, String is_online,
			Date last_connect_time, String is_barcode_print, String allowSecLevel) {
		this.console_code = console_code;
		this.console_ipaddr = console_ipaddr;
		this.console_location = console_location;
		this.console_mac = console_mac;
		this.console_name = console_name;
		this.console_status = console_status;
		this.console_type = console_type;
		this.curr_version = curr_version;
		this.hardware_type = hardware_type;
		this.install_time = install_time;
		this.is_barcode_print = is_barcode_print;
		this.is_online = is_online;
		this.last_connect_time = last_connect_time;
		this.seclv_code = seclv_code;
		this.set_version = set_version;
		this.uninstall_time = uninstall_time;
		this.allowSecLevel = allowSecLevel;

	}

	public String getConsole_type_name() {
		return console_type_name.substring(0, console_type_name.length() - 2);
	}

	public void setConsole_type_name(String console_type_name) {
		this.console_type_name = console_type_name;
	}

	public String getSeclv_name() {
		return seclv_name;
	}

	public void setSeclv_name(String seclv_name) {
		this.seclv_name = seclv_name;
	}

	public String getConsole_code() {
		return console_code;
	}

	public void setConsole_code(String console_code) {
		this.console_code = console_code;
	}

	public String getConsole_name() {
		return console_name;
	}

	public void setConsole_name(String console_name) {
		this.console_name = console_name;
	}

	public String getHardware_type() {
		return hardware_type;
	}

	public void setHardware_type(String hardware_type) {
		this.hardware_type = hardware_type;
	}

	public int getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(int seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getConsole_location() {
		return console_location;
	}

	public void setConsole_location(String console_location) {
		this.console_location = console_location;
	}

	public String getCurr_version() {
		return curr_version;
	}

	public void setCurr_version(String curr_version) {
		this.curr_version = curr_version;
	}

	public String getSet_version() {
		return set_version;
	}

	public void setSet_version(String set_version) {
		this.set_version = set_version;
	}

	public String getConsole_mac() {
		return console_mac;
	}

	public void setConsole_mac(String console_mac) {
		this.console_mac = console_mac;
	}

	public String getConsole_ipaddr() {
		return console_ipaddr;
	}

	public void setConsole_ipaddr(String console_ipaddr) {
		this.console_ipaddr = console_ipaddr;
	}

	public Date getInstall_time() {
		return install_time;
	}

	public String getInstall_time_str() {
		return install_time == null ? "" : getSdf().format(install_time);
	}

	public void setInstall_time(Date install_time) {
		this.install_time = install_time;
	}

	public String getConsole_status() {
		return console_status;
	}

	public String getConsole_status_name() {
		return console_status == "Y" ? "已监控" : "未监控";
	}

	public void setConsole_status(String console_status) {
		this.console_status = console_status;
	}

	public Date getUninstall_time() {
		return uninstall_time;
	}

	public String getUninstall_time_str() {
		return uninstall_time == null ? "" : getSdf().format(uninstall_time);
	}

	public void setUninstall_time(Date uninstall_time) {
		this.uninstall_time = uninstall_time;
	}

	public String getIs_online() {
		return is_online;
	}

	public void setIs_online(String is_online) {
		this.is_online = is_online;
	}

	public String getIs_online_name() {
		return is_online == "Y" ? "是" : "否";
	}

	public Date getLast_connect_time() {
		return last_connect_time;
	}

	public String getLast_connect_time_str() {
		return last_connect_time == null ? "" : getSdf().format(last_connect_time);
	}

	public void setLast_connect_time(Date last_connect_time) {
		this.last_connect_time = last_connect_time;
	}

	public String getIs_barcode_print() {
		return is_barcode_print;
	}

	public void setIs_barcode_print(String is_barcode_print) {
		this.is_barcode_print = is_barcode_print;
	}

	public SysConsole() {
		super(new SimpleDateFormat("yyyy-MM-dd"));
	}

	public SysConsole(String console_code, String console_name, String hardware_type, String console_type,
			int seclv_code, String console_location, String set_version, String is_barcode_print, String allowSecLevel) {
		this.console_code = console_code;
		this.console_name = console_name;
		this.hardware_type = hardware_type;
		this.console_type = console_type;
		this.seclv_code = seclv_code;
		this.console_location = console_location;
		this.set_version = set_version;
		this.is_barcode_print = is_barcode_print;
		this.allowSecLevel = allowSecLevel;
	}

	public String getAllowSecLevel() {
		return allowSecLevel;
	}

	public void setAllowSecLevel(String allowSecLevel) {
		this.allowSecLevel = allowSecLevel;
	}

	public String getConsole_type() {
		return console_type;
	}

	public void setConsole_type(String console_type) {
		this.console_type = console_type;
	}

}
