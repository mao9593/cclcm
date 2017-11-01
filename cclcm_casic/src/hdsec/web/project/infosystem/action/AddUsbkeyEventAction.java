package hdsec.web.project.infosystem.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.computer.model.ChangeDeviceEvent;
import hdsec.web.project.computer.model.EntityComputer;
import hdsec.web.project.computer.model.EntityEventDevice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

public class AddUsbkeyEventAction extends InfosystemBaseAction {

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
	private Integer apply_type = null;// 申请内容：1：首次申请，2：以旧换新，3：退回，4：责任人变更，5：丢失遗失
	private Integer power_type = null;// 权限类别：1：普通用户权限 2：管理员权限
	private String event_content = "";

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

	public String getApply_time_str() {
		return apply_time_str;
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

	public void setApply_type(Integer apply_type) {
		this.apply_type = apply_type;
	}

	public void setPower_type(Integer power_type) {
		this.power_type = power_type;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_code)) {
			String user_iidd = getCurUser().getUser_iidd();
			String dept_id = getCurUser().getDept_id();
			// event_content组成：|申请内容|权限类别|end|
			// event_content = Constants.COMMON_SEPARATOR + apply_type.toString() + Constants.COMMON_SEPARATOR
			// + power_type.toString() + Constants.COMMON_SEPARATOR + "end" + Constants.COMMON_SEPARATOR;
			EntityEventDevice event_device_content = new EntityEventDevice(event_code, apply_type, power_type);

			ChangeDeviceEvent event = new ChangeDeviceEvent(event_code, user_iidd, dept_id, apply_time, seclv_code, "",
					"", user_phone, computer_barcode, event_reason, event_content, 8, summ);// 8:USB-KEY申请/更新
			String jobType_code = "EVENT_USBKEY";
			JobTypeEnum jobType = JobTypeEnum.valueOf(jobType_code);
			event.setJobType(jobType);
			computerService.addComputerEvent(event, next_approver);// 向数据库添加event
			computerService.addEntityEventDevice(event_device_content);// 向ref_event_device表中添加此event对应的项
			insertCommonLog("添加USB-KEY申请/更新申请[" + event_code + "]");
			return "ok";
		} else {
			apply_time = new Date();
			apply_time_str = sdf.format(apply_time);
			event_code = getCurUser().getUser_iidd() + "_ADDUSBKEY_" + System.currentTimeMillis();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("computer_barcode", computer_barcode);
			computer = computerService.getComputerByMap(map);
			if (!computer.getKey_code().equals("")) {
				String[] code = computer.getKey_code().split(",");
				computer.setKey_code(code[0]);
			}
			return SUCCESS;
		}
	}

}