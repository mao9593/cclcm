package hdsec.web.project.infosystem.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.computer.model.ChangeDeviceEvent;
import hdsec.web.project.computer.model.EntityComputer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

public class AddComputerRepairEventAction extends InfosystemBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String computer_barcode = "";
	private EntityComputer computer = null;
	private String event_code = "";
	private String event_reason = "";
	private String user_phone = "";
	private Date apply_time = null;
	private String apply_time_str = "";
	private Integer seclv_code = null;// 作业密级
	private String next_approver = "";// 下级审批人
	private String summ = "";

	public String getApply_time_str() {
		return apply_time_str;
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

	public void setEvent_reason(String event_reason) {
		this.event_reason = event_reason;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public Date getApply_time() {
		return apply_time;
	}

	public void setApply_time(Date apply_time) {
		this.apply_time = apply_time;
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

	public String getComputer_barcode() {
		return computer_barcode;
	}

	public void setComputer_barcode(String computer_barcode) {
		this.computer_barcode = computer_barcode;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_code)) {
			String user_iidd = getCurUser().getUser_iidd();
			String dept_id = getCurUser().getDept_id();

			ChangeDeviceEvent event = new ChangeDeviceEvent(event_code, user_iidd, dept_id, apply_time, seclv_code, "",
					"", user_phone, computer_barcode, event_reason, "", 5, summ);// 5:计算机维修申请
			String jobType_code = "EVENT_REPCOM";
			JobTypeEnum jobType = JobTypeEnum.valueOf(jobType_code);
			event.setJobType(jobType);
			computerService.addComputerEvent(event, next_approver);// 向数据库添加event
			insertCommonLog("添加计算机维修申请[" + event_code + "]");
			return "ok";
		} else {
			apply_time = new Date();
			apply_time_str = sdf.format(apply_time);
			event_code = getCurUser().getUser_iidd() + "_ADDCOMPUTERREPAIR_" + System.currentTimeMillis();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("computer_barcode", computer_barcode);
			computer = computerService.getComputerByMap(map);
			return SUCCESS;
		}
	}

}