package hdsec.web.project.computer.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.computer.model.EntityInfoDevice;
import hdsec.web.project.computer.model.InfoDeviceEvent;
import hdsec.web.project.user.model.SecLevel;
import hdsec.web.project.user.model.UserSecurity;

import java.util.List;

import org.springframework.util.StringUtils;

public class ChangeInfoDeviceAction extends ComputerBaseAction {

	private static final long serialVersionUID = 1L;
	private String device_barcode = "";// 设备条码编号
	private String event_code = "";// 作业流水号
	private Integer seclv_code = null;// 作业密级
	private String project_code = "";// 所属项目编号
	private String usage_code = "";// 用途编号
	private String summ = "";// 备注
	private String user_iidd = "";//
	private String dept_id = "";//
	private String duty_user_id = "";
	private String duty_user_name = "";
	private String duty_dept_id = "";
	private String duty_dept_name = "";
	private Integer device_type = null;
	private String info_id = "";
	private String change_dept_id = "";
	private String change_dept_name = "";
	private String change_user_id = "";
	private String change_user_name = "";
	private String change_conf_code = "";
	private Integer change_selv = null;
	private String change_location = "";
	private String change_content = "";
	private String change_reason = "";
	private String contact_num = "";
	private String his_job_code = "";
	private String conf_code_before = "";// 原保密编号
	private Integer selv_before = null;// 原设备密级
	private String location_before = "";// 原使用地点
	private String next_approver = "";// 下级审批人
	private EntityInfoDevice eventList = null;
	private String typeChange = JobTypeEnum.DEVICE_CHANGE.getJobTypeCode();
	private String typeOther = JobTypeEnum.CHANGE_OTHER.getJobTypeCode();
	private String jobType = "";

	public String getTypeChange() {
		return typeChange;
	}

	public String getTypeOther() {
		return typeOther;
	}

	private String change_selv_name = "";

	public String getConf_code_before() {
		return conf_code_before;
	}

	public void setConf_code_before(String conf_code_before) {
		this.conf_code_before = conf_code_before;
	}

	public Integer getSelv_before() {
		return selv_before;
	}

	public void setSelv_before(Integer selv_before) {
		this.selv_before = selv_before;
	}

	public String getLocation_before() {
		return location_before;
	}

	public void setLocation_before(String location_before) {
		this.location_before = location_before;
	}

	public String getChange_selv_name() {
		return change_selv_name;
	}

	public void setChange_selv_name(String change_selv_name) {
		this.change_selv_name = change_selv_name;
	}

	public String getChange_dept_id() {
		return change_dept_id;
	}

	public void setChange_dept_id(String change_dept_id) {
		this.change_dept_id = change_dept_id;
	}

	public String getChange_dept_name() {
		return change_dept_name;
	}

	public void setChange_dept_name(String change_dept_name) {
		this.change_dept_name = change_dept_name;
	}

	public String getChange_user_id() {
		return change_user_id;
	}

	public void setChange_user_id(String change_user_id) {
		this.change_user_id = change_user_id;
	}

	public String getChange_user_name() {
		return change_user_name;
	}

	public void setChange_user_name(String change_user_name) {
		this.change_user_name = change_user_name;
	}

	public String getChange_conf_code() {
		return change_conf_code;
	}

	public void setChange_conf_code(String change_conf_code) {
		this.change_conf_code = change_conf_code;
	}

	public Integer getChange_selv() {
		return change_selv;
	}

	public void setChange_selv(Integer change_selv) {
		this.change_selv = change_selv;
	}

	public String getChange_location() {
		return change_location;
	}

	public void setChange_location(String change_location) {
		this.change_location = change_location;
	}

	public String getChange_content() {
		return change_content;
	}

	public void setChange_content(String change_content) {
		this.change_content = change_content;
	}

	public String getChange_reason() {
		return change_reason;
	}

	public void setChange_reason(String change_reason) {
		this.change_reason = change_reason;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public EntityInfoDevice getEventList() {
		return eventList;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getProject_code() {
		return project_code;
	}

	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}

	public String getHis_job_code() {
		return his_job_code;
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

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getUsage_code() {
		return usage_code;
	}

	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code;
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
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

	public String getContact_num() {
		return contact_num;
	}

	public void setContact_num(String contact_num) {
		this.contact_num = contact_num;
	}

	// 获取所有密级
	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public List<SysUsage> getUsageList() {
		return basicService.getSysUsageList();
	}

	public List<SysProject> getProjectList() {
		return basicService.getSysProjectList();
	}

	public List<UserSecurity> getSecurityList() {
		return userService.getSecurityList();
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(device_barcode)) {
			eventList = computerService.getInfoDeviceByBarcode(device_barcode);
			if (eventList.getDevice_type() == 6) {
				jobType = typeOther;
			} else {
				jobType = typeChange;
			}
		}
		if (StringUtils.hasLength(event_code)) {
			// 查询变更用户信息
			String user_iidd = getCurUser().getUser_iidd();
			String dept_id = getCurUser().getDept_id();

			InfoDeviceEvent device = new InfoDeviceEvent(device_barcode, user_iidd, dept_id, event_code, seclv_code,
					usage_code, project_code, summ, eventList.getDuty_user_id(), eventList.getDuty_user_name(),
					eventList.getDuty_dept_id(), eventList.getDuty_dept_name(), eventList.getDevice_type(),
					eventList.getInfo_id(), contact_num, his_job_code, change_dept_id, change_dept_name,
					change_user_id, change_user_name, change_conf_code, change_selv, change_location, change_content,
					change_reason, eventList.getConf_code(), eventList.getDevice_seclv(), eventList.getLocation());
			if (eventList.getDevice_type() == 6) {
				device.setJobType(JobTypeEnum.valueOf(typeOther));
			} else
				device.setJobType(JobTypeEnum.valueOf(jobType));

			device.setEvent_type(2);
			computerService.addInfoDeviceEvent(device, next_approver);
			insertCommonLog("添加信息设备变更申请[" + event_code + "]");
			return "ok";
		} else {
			event_code = getCurUser().getUser_iidd() + "_DEVICECHANGE_" + System.currentTimeMillis();
			return SUCCESS;
		}
	}
}