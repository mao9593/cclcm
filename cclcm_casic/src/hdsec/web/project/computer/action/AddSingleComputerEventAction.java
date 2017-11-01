package hdsec.web.project.computer.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.computer.model.ChangeDeviceEvent;
import hdsec.web.project.computer.model.EntityEventDevice;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

public class AddSingleComputerEventAction extends ComputerBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String event_code = "";// 事件编号
	private Integer seclv_code;// 作业密级
	private Integer com_seclv_code;// 计算机密级
	private String user_phone = "";
	private String computer_name = "";// 计算机品牌型号
	private String duty_user_id = "";
	private String computer_code = "";
	private String hdisk_no = "";
	private String computer_os = "";
	private Date install_time = null;
	private String storage_area = "";
	private String storage_location = "";
	private String event_reason = "";
	private String next_approver = "";// 下级审批人
	private String event_content = "";
	private String summ = "";
	private String oldconf_code = ""; // 原保密编号
	private String med_type = "";// 设备类型
	private String duty_entp_id = "";// 责任人单位

	public void setOldconf_code(String oldconf_code) {
		this.oldconf_code = oldconf_code;
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

	public String getComputer_code() {
		return computer_code;
	}

	public void setComputer_code(String computer_code) {
		this.computer_code = computer_code;
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

	public String getEvent_content() {
		return event_content;
	}

	public void setEvent_content(String event_content) {
		this.event_content = event_content;
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public String getMed_type() {
		return med_type;
	}

	public void setMed_type(String med_type) {
		this.med_type = med_type;
	}

	public String getDuty_entp_id() {
		return duty_entp_id;
	}

	public void setDuty_entp_id(String duty_entp_id) {
		this.duty_entp_id = duty_entp_id;
	}

	public String getOldconf_code() {
		return oldconf_code;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_code)) {
			String user_iidd = getCurUser().getUser_iidd();
			String dept_id = getCurUser().getDept_id();
			String duty_dept_name = userService.getDeptNameByUserId(duty_user_id);// 获取责任部门ID
			String duty_dept_id = userService.getDeptIdByName(duty_dept_name);

			EntityEventDevice event_device_content = new EntityEventDevice(event_code, computer_name, com_seclv_code,
					duty_user_id, duty_dept_id, duty_entp_id, computer_code, hdisk_no, computer_os, install_time,
					med_type, storage_area, storage_location, oldconf_code, summ);
			ChangeDeviceEvent event = new ChangeDeviceEvent(event_code, user_iidd, dept_id, new Date(), seclv_code, "",
					"", user_phone, "", event_reason, event_content, 2, summ);// 2:新增计算机单机
			String jobType_code = "EVENT_SINCOM";
			JobTypeEnum jobType = JobTypeEnum.valueOf(jobType_code);
			event.setJobType(jobType);
			computerService.addComputerEvent(event, next_approver);// 向数据库添加event
			computerService.addEntityEventDevice(event_device_content);// 向ref_event_device表中添加此event对应的项
			insertCommonLog("添加计算机（单机）申请[" + event_code + "]");
			return "ok";
		} else {
			event_code = getCurUser().getUser_iidd() + "_ADDSINGLECOMPUTER_" + System.currentTimeMillis();
			return SUCCESS;
		}

	}
}