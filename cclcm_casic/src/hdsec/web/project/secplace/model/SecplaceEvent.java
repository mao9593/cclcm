package hdsec.web.project.secplace.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.BaseEvent;

import java.util.Date;

public class SecplaceEvent extends BaseEvent {

	// private String event_code = "";// 事件号（唯一）
	// private int seclv_code;// 要害密级
	// private String user_iidd = "";// 申请人ID
	// private String dept_id = "";// 申请部门ID
	// private String seclv_name = "";// 场所名称
	// private Date apply_time = null;// 录入时间
	private String secplace_name = "";// 场所名称
	private String secplace_code = "";// 场所编号（唯一）
	private String secplace_location = "";// 物理位置
	private String duty_user_id = "";// 责任人id
	private String duty_user_name = "";// 责任人姓名
	private String duty_dept_id = "";// 责任部门id
	private String duty_dept_name = "";// 责任人名称
	private Integer secplace_type;// 要害类别（1：部门，2：部位）
	private String conf_code = "";// 保密编号
	private String user_number;// 涉密人员数量
	private Date found_time = null;// 成立时间
	private String secplace_application = "";// 用途
	private String people_protect = "";// 人防措施
	private String physical_protect = "";// 物防措施
	private String technology_protect = "";// 技防措施
	private String leader_id = "";// 主管领导
	private Integer file_num;// 文件数量
	private String file_list;// 文件列表
	private String secplace_corporation;// 法人

	public String getSecplace_corporation() {
		return secplace_corporation;
	}

	public void setSecplace_corporation(String secplace_corporation) {
		this.secplace_corporation = secplace_corporation;
	}

	public String getSecplace_name() {
		return secplace_name;
	}

	public void setSecplace_name(String secplace_name) {
		this.secplace_name = secplace_name;
	}

	public String getSecplace_code() {
		return secplace_code;
	}

	public void setSecplace_code(String secplace_code) {
		this.secplace_code = secplace_code;
	}

	public String getSecplace_location() {
		return secplace_location;
	}

	public void setSecplace_location(String secplace_location) {
		this.secplace_location = secplace_location;
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

	public Integer getSecplace_type() {
		return secplace_type;
	}

	public void setSecplace_type(Integer secplace_type) {
		this.secplace_type = secplace_type;
	}

	public String getConf_code() {
		return conf_code;
	}

	public void setConf_code(String conf_code) {
		this.conf_code = conf_code;
	}

	public String getUser_number() {
		return user_number;
	}

	public void setUser_number(String user_number) {
		this.user_number = user_number;
	}

	public String getFound_time() {
		return found_time == null ? "" : getSdf().format(found_time);
	}

	public Date getFound_time_time() {
		return found_time;
	}

	public void setFound_time(Date found_time) {
		this.found_time = found_time;
	}

	public String getSecplace_application() {
		return secplace_application;
	}

	public void setSecplace_application(String secplace_application) {
		this.secplace_application = secplace_application;
	}

	public String getPeople_protect() {
		return people_protect;
	}

	public void setPeople_protect(String people_protect) {
		this.people_protect = people_protect;
	}

	public String getPhysical_protect() {
		return physical_protect;
	}

	public void setPhysical_protect(String physical_protect) {
		this.physical_protect = physical_protect;
	}

	public String getTechnology_protect() {
		return technology_protect;
	}

	public void setTechnology_protect(String technology_protect) {
		this.technology_protect = technology_protect;
	}

	public String getLeader_id() {
		return leader_id;
	}

	public void setLeader_id(String leader_id) {
		this.leader_id = leader_id;
	}

	public Integer getFile_num() {
		return file_num;
	}

	public void setFile_num(Integer file_num) {
		this.file_num = file_num;
	}

	public String getFile_list() {
		return file_list;
	}

	public void setFile_list(String file_list) {
		this.file_list = file_list;
	}

	public SecplaceEvent() {
		super(JobTypeEnum.EVENT_SECPLACE);
	}

	public SecplaceEvent(String event_code, Integer seclv_code, String user_iidd, String dept_id, Date apply_time,
			String secplace_name, String secplace_code, String secplace_location, String duty_user_id,
			String duty_user_name, String duty_dept_id, String duty_dept_name, Integer secplace_type, String conf_code,
			String user_number, Date found_time, String secplace_application, String people_protect,
			String physical_protect, String technology_protect, String leader_id, Integer file_num, String file_list,
			String summ, String secplace_corporation) {
		super(JobTypeEnum.EVENT_SECPLACE, event_code, user_iidd, dept_id, seclv_code, "", "", summ);
		// this.seclv_name = seclv_name;
		this.secplace_name = secplace_name;
		this.secplace_code = secplace_code;
		this.secplace_location = secplace_location;
		this.duty_user_id = duty_user_id;
		this.duty_user_name = duty_user_name;
		this.duty_dept_id = duty_dept_id;
		this.duty_dept_name = duty_dept_name;
		this.secplace_type = secplace_type;
		this.conf_code = conf_code;
		this.user_number = user_number;
		this.found_time = found_time;
		this.secplace_application = secplace_application;
		this.people_protect = people_protect;
		this.physical_protect = physical_protect;
		this.technology_protect = technology_protect;
		this.leader_id = leader_id;
		this.file_num = file_num;
		this.file_list = file_list;
		this.secplace_corporation = secplace_corporation;

	}

}