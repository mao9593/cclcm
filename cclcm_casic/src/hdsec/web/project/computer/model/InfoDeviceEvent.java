package hdsec.web.project.computer.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.BaseEvent;

/**
 * 信息设备申请表
 * 
 * @author guojiao
 */
public class InfoDeviceEvent extends BaseEvent {
	private String device_barcode = "";// 设备条码
	private String duty_user_id = "";// 责任人ID
	private String duty_user_name = "";// 责任人
	private String duty_dept_id = "";// 责任人部门ID
	private String duty_dept_name = "";// 责任人部门
	private Integer device_type = null;// 设备类型(1:计算机 2:网络设备 3:外部设备 4:办公自动化设备 5:安全产品 6:介质)
	private String info_id = "";// 详细设备ID
	private String info_type = "";// 详细设备
	private String change_dept_id = "";// 变更部门ID
	private String change_dept_name = "";// 变更部门
	private String change_user_id = "";// 变更责任人ID
	private String change_user_name = "";// 变更责任人
	private String change_conf_code = "";// 变更保密编号
	private Integer change_selv = null;// 变更密级
	private String change_selv_name = "";// 变更密级
	private String change_location = "";// 变更使用地点
	private String change_content = "";// 其他变更内容
	private String change_reason = "";// 变更原因
	private String contact_num = "";// 变更原因
	private String his_job_code = "";// 包含该作业的历史任务列表
	private Integer event_type = null;// 申请类型（1、新增2、变更3、报废）
	private String conf_code_before = "";// 原保密编号
	private Integer selv_before = null;// 原设备密级
	private String selv_name_before = "";// 原设备密级名称
	private String location_before = "";// 原使用地点

	public String getChange_selv_name() {
		return change_selv_name;
	}

	public void setChange_selv_name(String change_selv_name) {
		this.change_selv_name = change_selv_name;
	}

	public String getSelv_name_before() {
		return selv_name_before;
	}

	public void setSelv_name_before(String selv_name_before) {
		this.selv_name_before = selv_name_before;
	}

	public Integer getEvent_type() {
		return event_type;
	}

	public void setEvent_type(Integer event_type) {
		this.event_type = event_type;
	}

	public String getEvent_type_str() {
		String name = "";
		if (event_type != null) {
			switch (event_type) {
			case 1:
				name = "新增信息设备";
				break;
			case 2:
				name = "设备变更";
				break;
			case 3:
				name = "设备报废";
				break;
			default:
				name = "";
				break;
			}
		}
		return name;
	}

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

	public String getDevice_barcode() {
		return device_barcode;
	}

	public void setDevice_barcode(String device_barcode) {
		this.device_barcode = device_barcode;
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

	public String getInfo_type() {
		return info_type;
	}

	public void setInfo_type(String info_type) {
		this.info_type = info_type;
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

	public String getHis_job_code() {
		return his_job_code;
	}

	public void setHis_job_code(String his_job_code) {
		this.his_job_code = his_job_code;
	}

	public String getDevice_type_name() {
		String name = "";
		if (device_type != null) {
			switch (device_type) {
			case 2:
				name = "网络设备";
				break;
			case 3:
				name = "外部设备";
				break;
			case 4:
				name = "办公自动化设备";
				break;
			case 5:
				name = "安全产品";
				break;
			case 6:
				name = "介质";
				break;
			default:
				break;

			}
		}

		return name;
	}

	public InfoDeviceEvent() {
		super();
	}

	public InfoDeviceEvent(String device_barcode, String user_iidd, String dept_id, String event_code,
			Integer seclv_code, String usage_code, String project_code, String summ, String duty_user_id,
			String duty_user_name, String duty_dept_id, String duty_dept_name, Integer device_type, String info_id,
			String contact_num, String his_job_code, String change_dept_id, String change_dept_name,
			String change_user_id, String change_user_name, String change_conf_code, Integer change_selv,
			String change_location, String change_content, String change_reason, String conf_code_before,
			Integer selv_before, String location_before) {
		super(JobTypeEnum.INFO_DEVICE, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);

		this.device_barcode = device_barcode;
		this.duty_user_id = duty_user_id;
		this.duty_user_name = duty_user_name;
		this.duty_dept_id = duty_dept_id;
		this.duty_dept_name = duty_dept_name;
		this.device_type = device_type;
		this.info_id = info_id;
		this.contact_num = contact_num;
		this.his_job_code = his_job_code;
		this.change_conf_code = change_conf_code;
		this.change_content = change_content;
		this.change_dept_id = change_dept_id;
		this.change_dept_name = change_dept_name;
		this.change_location = change_location;
		this.change_reason = change_reason;
		this.change_selv = change_selv;
		this.change_user_id = change_user_id;
		this.change_user_name = change_user_name;
		this.conf_code_before = conf_code_before;
		this.selv_before = selv_before;
		this.location_before = location_before;
	}

	// 新增信息设备
	public InfoDeviceEvent(String device_barcode, String user_iidd, String dept_id, String event_code,
			Integer seclv_code, String usage_code, String project_code, String summ, String duty_user_id,
			String duty_user_name, String duty_dept_id, String duty_dept_name, Integer device_type, String info_id,
			String contact_num, String his_job_code) {
		super(JobTypeEnum.INFO_DEVICE, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);

		this.device_barcode = device_barcode;
		this.duty_user_id = duty_user_id;
		this.duty_user_name = duty_user_name;
		this.duty_dept_id = duty_dept_id;
		this.duty_dept_name = duty_dept_name;
		this.device_type = device_type;
		this.info_id = info_id;
		this.contact_num = contact_num;
		this.his_job_code = his_job_code;
	}
}