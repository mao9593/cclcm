package hdsec.web.project.infosystem.action;

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

public class AddLocalPrinterEventAction extends InfosystemBaseAction {

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
	private Integer seclv_code = null;// 作业密级
	private String printer_seclv_name = "";// 打印机密级
	private String next_approver = "";// 下级审批人
	private String summ = "";
	private String printer_model = "";
	private String conf_code = "";
	private String event_content = "";

	public void setPrinter_seclv_name(String printer_seclv_name) {
		this.printer_seclv_name = printer_seclv_name;
	}

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

	public void setPrinter_model(String printer_model) {
		this.printer_model = printer_model;
	}

	public void setConf_code(String conf_code) {
		this.conf_code = conf_code;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getBurnSecLevelByUser(getCurUser().getUser_iidd());
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_code)) {
			String user_iidd = getCurUser().getUser_iidd();
			String dept_id = getCurUser().getDept_id();
			// event_content组成：|打印机型号|打印机密级编号|打印机密级|end|
			// event_content = Constants.COMMON_SEPARATOR + printer_model + Constants.COMMON_SEPARATOR + conf_code
			// / + Constants.COMMON_SEPARATOR + printer_seclv_name + Constants.COMMON_SEPARATOR + "end"
			// + Constants.COMMON_SEPARATOR;

			EntityEventDevice event_device_content = new EntityEventDevice(event_code, printer_model, conf_code,
					printer_seclv_name);

			ChangeDeviceEvent event = new ChangeDeviceEvent(event_code, user_iidd, dept_id, apply_time, seclv_code, "",
					"", user_phone, computer_barcode, event_reason, event_content, 10, summ);// 10:保留本地打印机
			String jobType_code = "EVENT_LOCALPRINTER";
			JobTypeEnum jobType = JobTypeEnum.valueOf(jobType_code);
			event.setJobType(jobType);
			computerService.addComputerEvent(event, next_approver);// 向数据库添加event
			computerService.addEntityEventDevice(event_device_content);// 向ref_event_device表中添加此event对应的项
			insertCommonLog("添加保留本地打印机申请[" + event_code + "]");
			return "ok";
		} else {
			apply_time = new Date();
			event_code = getCurUser().getUser_iidd() + "_ADDLOCALPRINTER_" + System.currentTimeMillis();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("computer_barcode", computer_barcode);
			computer = computerService.getComputerByMap(map);
			return SUCCESS;
		}
	}
}