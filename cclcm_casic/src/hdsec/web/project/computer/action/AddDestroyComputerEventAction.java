package hdsec.web.project.computer.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.computer.model.ChangeDeviceEvent;
import hdsec.web.project.computer.model.EntityComputer;
import hdsec.web.project.computer.model.InfoType;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

public class AddDestroyComputerEventAction extends ComputerBaseAction {

	/**
	 * 计算机报废申请
	 */
	private static final long serialVersionUID = 1L;
	private String event_code = "";// 事件编号
	private Integer seclv_code = null;// 作业密级
	private String user_phone = "";
	private String next_approver = "";// 下级审批人
	private String event_reason = "";
	private String summ = "";
	private String computer_barcode = "";// 计算机barcode
	private EntityComputer computer = null;

	public EntityComputer getComputer() {
		return computer;
	}

	public void setComputer(EntityComputer computer) {
		this.computer = computer;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getBurnSecLevelByUser(getCurUser().getUser_iidd());
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

	public String getEvent_reason() {
		return event_reason;
	}

	public void setEvent_reason(String event_reason) {
		this.event_reason = event_reason;
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
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

			ChangeDeviceEvent event = new ChangeDeviceEvent(event_code, user_iidd, dept_id, new Date(), seclv_code, "",
					"", user_phone, computer_barcode, event_reason, "", 4, summ);// 4:计算机报废
			String jobType_code = "EVENT_DESCOM";
			JobTypeEnum jobType = JobTypeEnum.valueOf(jobType_code);
			event.setJobType(jobType);
			computerService.addComputerEvent(event, next_approver);// 向数据库添加event
			insertCommonLog("添加计算机报废申请[" + event_code + "]");
			return "ok";
		} else {
			event_code = getCurUser().getUser_iidd() + "_ADDCHANGECOMPUTER_" + System.currentTimeMillis();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("computer_barcode", computer_barcode);
			computer = computerService.getComputerByMap(map);
			if (!computer.getSoftware_type().equals("")) {
				InfoType medname = null;
				String names = "";
				String med[] = computer.getSoftware_type().split(",");
				for (int i = 0; i < med.length; i++) {
					map.put("info_id", med[i].trim());
					medname = computerService.getInfoTypeByID(map);
					names += medname.getInfo_type() + ",";
				}
				computer.setSoftware_type(names);
			}
			return SUCCESS;
		}
	}

}