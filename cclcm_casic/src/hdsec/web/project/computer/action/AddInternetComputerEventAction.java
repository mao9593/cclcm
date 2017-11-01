package hdsec.web.project.computer.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.computer.model.ChangeDeviceEvent;
import hdsec.web.project.computer.model.EntityEventDevice;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 
 * @author liuyaling 2015-7-6
 */
public class AddInternetComputerEventAction extends ComputerBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String event_code = "";// 事件编号
	private Integer seclv_code;// 作业密级
	private Integer com_seclv_code;// 计算机密级
	private String user_phone = "";// 申请人电话
	private String computer_name = "";// 计算机品牌型号
	private String duty_user_id = "";// 责任人id
	private String conf_code = "";// 保密编号

	private Integer internet_type;// 网络接入类型
	private String storage_area = "";// 安装区域
	private String storage_location = "";// 安装位置
	private String event_reason = "";// 申请理由
	private String next_approver = "";// 下级审批人
	private String hdisk_no = "";// 硬盘序列号
	private String computer_mac = "";// MAC地址
	private String computer_os = "";// 操作系统版本
	private Date install_time = null;// 操作系统安装时间
	private String med_type = "";// 设备类型
	private String duty_entp_id = "";// 责任人单位
	private String computer_code = "";// 统一编号
	private String output_point = null;// 输出端口
	private String oldconf_code = ""; // 原保密编号
	// private String computer_barcode = "";
	private String event_content = "";
	// private Integer event_type;
	private String summ = "";
	private String book_id = "";

	public String getBook_id() {
		return book_id;
	}

	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}

	public void setOldconf_code(String oldconf_code) {
		this.oldconf_code = oldconf_code;
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public Integer getCom_seclv_code() {
		return com_seclv_code;
	}

	public void setCom_seclv_code(Integer com_seclv_code) {
		this.com_seclv_code = com_seclv_code;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getComputer_name() {
		return computer_name;
	}

	public void setComputer_name(String computer_name) {
		this.computer_name = computer_name;
	}

	public String getDuty_user_id() {
		return duty_user_id;
	}

	public void setDuty_user_id(String duty_user_id) {
		this.duty_user_id = duty_user_id;
	}

	public String getConf_code() {
		return conf_code;
	}

	public void setConf_code(String conf_code) {
		this.conf_code = conf_code;
	}

	public Integer getInternet_type() {
		return internet_type;
	}

	public void setInternet_type(Integer internet_type) {
		this.internet_type = internet_type;
	}

	public String getStorage_area() {
		return storage_area;
	}

	public void setStorage_area(String storage_area) {
		this.storage_area = storage_area;
	}

	public String getStorage_location() {
		return storage_location;
	}

	public void setStorage_location(String storage_location) {
		this.storage_location = storage_location;
	}

	public String getEvent_reason() {
		return event_reason;
	}

	public void setEvent_reason(String event_reason) {
		this.event_reason = event_reason;
	}

	public String getNext_approver() {
		return next_approver;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public String getHdisk_no() {
		return hdisk_no;
	}

	public void setHdisk_no(String hdisk_no) {
		this.hdisk_no = hdisk_no;
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

	public String getComputer_mac() {
		return computer_mac;
	}

	public void setComputer_mac(String computer_mac) {
		this.computer_mac = computer_mac;
	}

	public String getMed_type() {
		return med_type;
	}

	public void setMed_type(String med_type) {
		this.med_type = med_type;
	}

	public String getOutput_point() {
		return output_point;
	}

	public void setOutput_point(String output_point) {
		this.output_point = output_point;
	}

	public String getDuty_entp_id() {
		return duty_entp_id;
	}

	public void setDuty_entp_id(String duty_entp_id) {
		this.duty_entp_id = duty_entp_id;
	}

	public String getComputer_code() {
		return computer_code;
	}

	public void setComputer_code(String computer_code) {
		this.computer_code = computer_code;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_code)) {
			String user_iidd = getCurUser().getUser_iidd();
			String dept_id = getCurUser().getDept_id();
			String duty_dept_name = userService.getDeptNameByUserId(duty_user_id);// 获取责任部门ID
			String duty_dept_id = userService.getDeptIdByName(duty_dept_name);

			EntityEventDevice event_device_content = new EntityEventDevice(event_code, computer_name, com_seclv_code,
					duty_user_id, duty_dept_id, duty_entp_id, computer_code, internet_type, storage_area,
					storage_location, hdisk_no, computer_os, install_time, computer_mac, med_type, output_point,
					oldconf_code, summ);
			ChangeDeviceEvent event = new ChangeDeviceEvent(event_code, user_iidd, dept_id, new Date(), seclv_code, "",
					"", user_phone, "", event_reason, event_content, 1, summ);// 1:新增计算机网络机
			String jobType_code = "EVENT_INTCOM";
			JobTypeEnum jobType = JobTypeEnum.valueOf(jobType_code);
			event.setJobType(jobType);
			computerService.addComputerEvent(event, next_approver);// 向数据库添加event
			computerService.addEntityEventDevice(event_device_content);// 向ref_event_device表中添加此event对应的项
			insertCommonLog("添加计算机（网络机）申请[" + event_code + "]");
			return "ok";
		} else {
			book_id = getInfoId("笔记本");
			event_code = getCurUser().getUser_iidd() + "_ADDINTERNETCOMPUTER_" + System.currentTimeMillis();
			return SUCCESS;
		}

	}
}