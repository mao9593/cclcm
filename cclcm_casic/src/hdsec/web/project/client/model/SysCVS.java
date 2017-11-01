package hdsec.web.project.client.model;

import hdsec.web.project.common.BaseDomain;
/**
 * 系统功能模块
 * @author gaoxm
 *
 */

import java.util.Date;

public class SysCVS extends BaseDomain {
	private String cvs_code = "";// 客户端标识（主键）
	private String user_iidd = "";// 用户账号（外键），USER_IIDD跟机器不一定绑定
	private String computer_name = "";// 计算机名
	private String ip_address = "";// IP地址
	private Integer soft_code = null;// 软件类别
	private String curr_version = "";// 当前版本
	private String install_version = "";// 初次安装软件版本
	private String set_version = "";// 设定版本（强制升级或降级）
	private Date install_time = null;// 安装时间
	private Date update_time = null;// 上次更新时间
	private String os_version = "";// 操作系统版本
	private Date last_time = null;// 上次在线时间
	private String is_online = "";// Y/N是否在线
	private Date uninstall = null;// 卸载时间
	
	private String user_name = "";// 计算机名
	private String dept_name = "";
	
	public String getCvs_code() {
		return cvs_code;
	}
	
	public void setCvs_code(String cvs_code) {
		this.cvs_code = cvs_code;
	}
	
	public String getUser_iidd() {
		return user_iidd;
	}
	
	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}
	
	public String getComputer_name() {
		return computer_name;
	}
	
	public void setComputer_name(String computer_name) {
		this.computer_name = computer_name;
	}
	
	public String getIp_address() {
		return ip_address;
	}
	
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	
	public Integer getSoft_code() {
		return soft_code;
	}
	
	public void setSoft_code(Integer soft_code) {
		this.soft_code = soft_code;
	}
	
	public String getCurr_version() {
		return curr_version;
	}
	
	public void setCurr_version(String curr_version) {
		this.curr_version = curr_version;
	}
	
	public String getInstall_version() {
		return install_version;
	}
	
	public void setInstall_version(String install_version) {
		this.install_version = install_version;
	}
	
	public String getSet_version() {
		return set_version;
	}
	
	public void setSet_version(String set_version) {
		this.set_version = set_version;
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
	
	public Date getUpdate_time() {
		return update_time;
	}
	
	public String getUpdate_time_str() {
		return update_time == null ? "" : getSdf().format(update_time);
	}
	
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
	public String getOs_version() {
		return os_version;
	}
	
	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}
	
	public Date getLast_time() {
		return last_time;
	}
	
	public String getLast_time_str() {
		return last_time == null ? "" : getSdf().format(last_time);
	}
	
	public void setLast_time(Date last_time) {
		this.last_time = last_time;
	}
	
	public String getIs_online() {
		return is_online;
	}
	
	public String getIs_online_name() {
		return is_online.equals("Y") ? "是" : "否";
	}
	
	public void setIs_online(String is_online) {
		this.is_online = is_online;
	}
	
	public Date getUninstall() {
		return uninstall;
	}
	
	public String getUninstall_str() {
		return uninstall == null ? "" : getSdf().format(uninstall);
	}
	
	public void setUninstall(Date uninstall) {
		this.uninstall = uninstall;
	}
	
	public String getUser_name() {
		return user_name;
	}
	
	public String getDept_name() {
		return dept_name;
	}
	
	public SysCVS() {
		super();
	}
	
	public SysCVS(String cvs_code, String user_iidd, String computer_name, String ip_address, Integer soft_code,
			String curr_version, String install_version, String set_version, Date install_time, Date update_time,
			String os_version, Date last_time, String is_online, Date uninstall) {
		this.cvs_code = cvs_code;
		this.user_iidd = user_iidd;
		this.computer_name = computer_name;
		this.ip_address = ip_address;
		this.soft_code = soft_code;
		this.curr_version = curr_version;
		this.install_version = install_version;
		this.set_version = set_version;
		this.install_time = install_time;
		this.update_time = update_time;
		this.os_version = os_version;
		this.last_time = last_time;
		this.is_online = is_online;
		this.uninstall = uninstall;
	}
	
}
