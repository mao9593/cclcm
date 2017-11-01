package hdsec.web.project.infosystem.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.computer.model.ChangeDeviceEvent;
import hdsec.web.project.computer.model.EntityComputer;
import hdsec.web.project.computer.model.EntityEventDevice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

public class ViewInfosystemEventDetailAction extends InfosystemBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String op = "";
	private ChangeDeviceEvent event = null;
	private ApproveProcess process = null;
	private ProcessJob job = null;
	private List<ProcessRecord> recordList = null;
	private String type = "N";
	private String job_code = "";
	private EntityComputer computer = null;
	private String apply_type_name = "";
	private String power_type_name = "";
	private Integer apply_type = null;
	private Integer power_type = null;
	private String output_port = "";
	private String input_port = "";
	private String start_time = "";
	private String end_time = "";
	private String printer_model = "";
	private String printer_conf_code = "";
	private String printer_seclv_name = "";

	public String getPrinter_model() {
		return printer_model;
	}

	public String getPrinter_conf_code() {
		return printer_conf_code;
	}

	public String getPrinter_seclv_name() {
		return printer_seclv_name;
	}

	public String getStart_time() {
		return start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public String getOutput_port() {
		return output_port;
	}

	public String getInput_port() {
		return input_port;
	}

	public String getApply_type_name() {
		String name = "";
		if (apply_type != null) {
			switch (apply_type) {
			case 1:
				name = "首次申请";
				break;
			case 2:
				name = "以旧换新";
				break;
			case 3:
				name = "退回";
				break;
			case 4:
				name = "责任人变更";
				break;
			case 5:
				name = "丢失遗失";
				break;
			default:
				break;

			}
		}
		return name;
	}

	public String getPower_type_name() {
		String name = "";
		if (power_type != null) {
			switch (power_type) {
			case 1:
				name = "普通用户权限";
				break;
			case 2:
				name = "管理员权限";
				break;
			default:
				break;

			}
		}
		return name;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public ChangeDeviceEvent getEvent() {
		return event;
	}

	public ApproveProcess getProcess() {
		return process;
	}

	public ProcessJob getJob() {
		return job;
	}

	public List<ProcessRecord> getRecordList() {
		return recordList;
	}

	public EntityComputer getComputer() {
		return computer;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	@Override
	public String executeFunction() throws Exception {
		if (type.equalsIgnoreCase("Y")) {
			event_code = computerService.getChangeDeviceEventCodeByJobCode(job_code);
		}

		event = computerService.getChangeDeviceEventByEventCode(event_code);
		// String event_content = event.getEvent_content();
		// String[] computer_change = event_content.split("\\|");
		EntityEventDevice event_device_content = computerService.getEntityEventDeviceByEventCode(event.getEvent_code());
		if (event.getEvent_type() == 8) {// USB-KEY申请/更新
			if (StringUtils.hasLength(event_device_content.getApply_type().toString())) {
				apply_type = event_device_content.getApply_type();
			}
			if (StringUtils.hasLength(event_device_content.getPower_type().toString())) {
				power_type = event_device_content.getPower_type();
			}
		} else if (event.getEvent_type() == 9) {
			if (StringUtils.hasLength(event_device_content.getOutput_point())
					&& !event_device_content.getOutput_point().equals("null")) {
				output_port = event_device_content.getOutput_point();
			}

			if (StringUtils.hasLength(event_device_content.getInput_point())
					&& !event_device_content.getInput_point().equals("null")) {
				input_port = event_device_content.getInput_point();
			}

			if (StringUtils.hasLength(event_device_content.getStart_time_str())
					&& !event_device_content.getStart_time_str().equals("null")) {
				start_time = event_device_content.getStart_time_str();
			}

			if (StringUtils.hasLength(event_device_content.getEnd_time_str())
					&& !event_device_content.getEnd_time_str().equals("null")) {
				end_time = event_device_content.getEnd_time_str();
			}
		} else if (event.getEvent_type() == 10) {
			if (StringUtils.hasLength(event_device_content.getPrinter_model())
					&& !event_device_content.getPrinter_model().equals("null")) {
				printer_model = event_device_content.getPrinter_model();
			}

			if (StringUtils.hasLength(event_device_content.getPrinter_conf_code())
					&& !event_device_content.getPrinter_conf_code().equals("null")) {
				printer_conf_code = event_device_content.getPrinter_conf_code();
			}

			if (StringUtils.hasLength(event_device_content.getPrinter_seclv_name())
					&& !event_device_content.getPrinter_seclv_name().equals("null")) {
				printer_seclv_name = event_device_content.getPrinter_seclv_name();
			}
		}

		if (event == null) {
			throw new Exception("查询的作业已经被删除");
		} else {
			// 流程及审批信息
			String job_code = event.getJob_code();
			if (StringUtils.hasLength(job_code)) {
				job = basicService.getProcessJobByCode(job_code);
				process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
				ProcessRecord record = new ProcessRecord();
				record.setJob_code(job_code);
				recordList = activitiService.getProcessRecordList(record);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("computer_barcode", event.getBarcode());
			computer = computerService.getComputerByMap(map);
		}

		return SUCCESS;
	}

}