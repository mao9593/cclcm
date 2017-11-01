package hdsec.web.project.infosystem.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.computer.model.ChangeDeviceEvent;
import hdsec.web.project.computer.model.EntityComputer;
import hdsec.web.project.computer.model.EntityEventDevice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

public class AddOpenPortEventAction extends InfosystemBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String computer_barcode = "";
	private EntityComputer computer = null;
	private String event_code = "";
	private String event_reason = "";
	private String event_content = "";
	private String user_phone = "";
	private Date apply_time = null;
	private Integer seclv_code = null;// 作业密级
	private String next_approver = "";// 下级审批人
	private String summ = "";
	private String otherports = "";
	private Date start_time = null;
	private Date end_time = null;
	private String input_chk = "";
	private String output_chk = "";

	public String getComputer_barcode() {
		return computer_barcode;
	}

	public void setComputer_barcode(String computer_barcode) {
		this.computer_barcode = computer_barcode;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public EntityComputer getComputer() {
		return computer;
	}

	public void setEvent_reason(String event_reason) {
		this.event_reason = event_reason;
	}

	public void setEvent_content(String event_content) {
		this.event_content = event_content;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public void setOtherports(String otherports) {
		this.otherports = otherports;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public void setInput_chk(String input_chk) {
		this.input_chk = input_chk;
	}

	public void setOutput_chk(String output_chk) {
		this.output_chk = output_chk;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_code)) {
			String user_iidd = getCurUser().getUser_iidd();
			String dept_id = getCurUser().getDept_id();
			apply_time = new Date();
			if (!output_chk.trim().contains("无")) {
				output_chk = output_chk.trim().substring(1);
			}
			EntityEventDevice event_device_content = new EntityEventDevice(event_code, output_chk, "", null, null);

			ChangeDeviceEvent event = new ChangeDeviceEvent(event_code, user_iidd, dept_id, apply_time, seclv_code, "",
					"", user_phone, computer_barcode, event_reason, event_content, 9, summ);// 9:开通计算机端口
			String jobType_code = "EVENT_OPENPORT";
			JobTypeEnum jobType = JobTypeEnum.valueOf(jobType_code);
			event.setJobType(jobType);
			computerService.addComputerEvent(event, next_approver);// 向数据库添加event
			computerService.addEntityEventDevice(event_device_content);// 向ref_event_device表中添加此event对应的项
			insertCommonLog("添加开通计算机端口申请[" + event_code + "]");
			return "ok";
		} else {
			event_code = getCurUser().getUser_iidd() + "_OPENPORT_" + System.currentTimeMillis();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("computer_barcode", computer_barcode);
			computer = computerService.getComputerByMap(map);
			return SUCCESS;
		}
	}
}