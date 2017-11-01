package hdsec.web.project.computer.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.computer.model.ChangeDeviceEvent;
import hdsec.web.project.computer.model.EntityComputer;
import hdsec.web.project.computer.model.EntityEventDevice;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

public class AddChangeComputerEventAction extends ComputerBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String event_code = "";// 事件编号
	private Integer seclv_code = null;// 作业密级
	private String user_phone = "";
	private String next_approver = "";// 下级审批人
	private String _chk = "";
	private String conf_code = "";
	private String computer_barcode = "";
	private EntityComputer computer = null;

	private Integer com_seclv_code = null;// 计算机密级
	private String duty_dept_id = "";
	private String duty_user_id = "";
	private String com_conf_code = "";
	private String computer_ip = "";
	private String computer_mac = "";
	private String hdisk_no = "";
	private String storage_area = "";
	private String storage_location = "";
	private String others = "";
	private String event_reason = "";
	private String event_content = "";
	private String summ = "";
	private String type = "N";

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

	public void setType(String type) {
		this.type = type;
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public String getConf_code() {
		return conf_code;
	}

	public void setConf_code(String conf_code) {
		this.conf_code = conf_code;
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

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getNext_approver() {
		return next_approver;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public String get_chk() {
		return _chk;
	}

	public void set_chk(String _chk) {
		this._chk = _chk;
	}

	public Integer getCom_seclv_code() {
		return com_seclv_code;
	}

	public void setCom_seclv_code(Integer com_seclv_code) {
		this.com_seclv_code = com_seclv_code;
	}

	public String getDuty_dept_id() {
		return duty_dept_id;
	}

	public void setDuty_dept_id(String duty_dept_id) {
		this.duty_dept_id = duty_dept_id;
	}

	public String getDuty_user_id() {
		return duty_user_id;
	}

	public void setDuty_user_id(String duty_user_id) {
		this.duty_user_id = duty_user_id;
	}

	public String getCom_conf_code() {
		return com_conf_code;
	}

	public void setCom_conf_code(String com_conf_code) {
		this.com_conf_code = com_conf_code;
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

	public String getHdisk_no() {
		return hdisk_no;
	}

	public void setHdisk_no(String hdisk_no) {
		this.hdisk_no = hdisk_no;
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

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public String getEvent_reason() {
		return event_reason;
	}

	public void setEvent_reason(String event_reason) {
		this.event_reason = event_reason;
	}

	public String getEvent_content() {
		return event_content;
	}

	public void setEvent_content(String event_content) {
		this.event_content = event_content;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_code)) {
			String user_iidd = getCurUser().getUser_iidd();
			String dept_id = getCurUser().getDept_id();
			// 获取责任单位ID
			String duty_entp_id = "";

			Map<String, Object> map = new HashMap<String, Object>();
			if (type.equals("Y")) {
				map.put("computer_barcode", computer_barcode);
			} else {
				map.put("conf_code", conf_code);
			}
			EntityComputer computer = computerService.getComputerByMap(map);

			EntityEventDevice event_device_content = new EntityEventDevice(event_code, computer.getComputer_barcode(),
					com_seclv_code, computer.getSeclv_code(), duty_user_id, computer.getDuty_user_id(), duty_dept_id,
					computer.getDuty_dept_id(), duty_entp_id, computer.getDuty_entp_id(), com_conf_code,
					computer.getConf_code(), hdisk_no, computer.getHdisk_no(), computer_ip, computer.getComputer_ip(),
					computer_mac, computer.getComputer_mac(), storage_area, computer.getStorage_area(),
					storage_location, computer.getStorage_location(), others);
			ChangeDeviceEvent event = new ChangeDeviceEvent(event_code, user_iidd, dept_id, new Date(), seclv_code, "",
					"", user_phone, computer.getComputer_barcode(), event_reason, event_content, 3, summ);// 3:计算机变更
			String jobType_code = "EVENT_CHGCOM";
			JobTypeEnum jobType = JobTypeEnum.valueOf(jobType_code);
			event.setJobType(jobType);
			computerService.addComputerEvent(event, next_approver);// 向数据库添加event
			computerService.addEntityEventDevice(event_device_content);// 向ref_event_device表中添加此event对应的项
			insertCommonLog("添加计算机变更申请[" + event_code + "]");
			return "ok";
		} else {
			event_code = getCurUser().getUser_iidd() + "_ADDCHANGECOMPUTER_" + System.currentTimeMillis();
			if (type.equals("Y")) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("computer_barcode", computer_barcode);
				computer = computerService.getComputerByMap(map);
				return "ok_success";
			} else {
				return SUCCESS;
			}

		}
	}
}