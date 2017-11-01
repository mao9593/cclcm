package hdsec.web.project.computer.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.computer.model.ChangeDeviceEvent;
import hdsec.web.project.computer.model.EntityComputer;
import hdsec.web.project.computer.model.EntityEventDevice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

public class ViewComputerEventDetailAction extends ComputerBaseAction {

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
	private EntityComputer computer_old = null;

	public EntityComputer getComputer_old() {
		return computer_old;
	}

	public void setComputer_old(EntityComputer computer_old) {
		this.computer_old = computer_old;
	}

	public EntityComputer getComputer() {
		return computer;
	}

	public ChangeDeviceEvent getEvent() {
		return event;
	}

	public void setEvent(ChangeDeviceEvent event) {
		this.event = event;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public ApproveProcess getProcess() {
		return process;
	}

	public void setProcess(ApproveProcess process) {
		this.process = process;
	}

	public ProcessJob getJob() {
		return job;
	}

	public void setJob(ProcessJob job) {
		this.job = job;
	}

	public List<ProcessRecord> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<ProcessRecord> recordList) {
		this.recordList = recordList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getJob_code() {
		return job_code;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	@Override
	public String executeFunction() throws Exception {
		if (type.equalsIgnoreCase("Y")) {
			event_code = computerService.getChangeDeviceEventCodeByJobCode(job_code);
		}

		event = computerService.getChangeDeviceEventByEventCode(event_code);
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
		}

		// String event_content = event.getEvent_content();
		// String[] computer_change = event_content.split("\\|");
		EntityEventDevice event_device_content = computerService.getEntityEventDeviceByEventCode(event_code);

		if (event.getEvent_type() == 1) {// 新增计算机网络机

			String duty_user_name = "";
			String duty_dept_name = "";
			List<String> seclv_name = null;

			if (event_device_content.getSeclv_code() != null) {
				String seclv_codes = Constants.COMMON_SEPARATOR + event_device_content.getSeclv_code()
						+ Constants.COMMON_SEPARATOR;
				String[] codes = seclv_codes.split("\\|");
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("codes", codes);
				seclv_name = basicService.getSeclvNameByCodes(map);
			}

			duty_user_name = userService.getUserNameByUserId(event_device_content.getDuty_user_id());
			duty_dept_name = userService.getDeptNameByDeptId(event_device_content.getDuty_dept_id());
			// duty_entp_name = userService.getDeptNameByDeptId(event_device_content.getDuty_entp_id());

			// event_content组成：|计算机名称|密级代码|责任人ID|责任部门ID|责任单位ID|保密编号|网络类型代码|存放区域|存放位置|硬盘序列号|操作系统|安装时间|MAC地址|end|
			EntityComputer computer_temp = new EntityComputer("", event_device_content.getComputer_name(),
					event_device_content.getSeclv_code(), seclv_name.get(0), event_device_content.getDuty_user_id(),
					duty_user_name, event_device_content.getDuty_dept_id(), duty_dept_name,
					event_device_content.getDuty_entp_id(), event_device_content.getConf_code(),
					event_device_content.getInternet_type(), event_device_content.getHdisk_no(),
					event_device_content.getComputer_os(), event_device_content.getInstall_time(), "",
					event_device_content.getComputer_mac(), event_device_content.getStorage_area(),
					event_device_content.getStorage_location(), "", "", "", event_device_content.getMed_type(),
					event_device_content.getOutput_point(), event_device_content.getOldconf_code(),
					event_device_content.getMed_type_name());
			computer = computer_temp;

		} else if (event.getEvent_type() == 2) {// 新增计算机单机

			// computer_change组成：|计算机名称|密级代码|责任人ID|责任部门ID|责任单位ID|保密编号|硬盘序列号|操作系统|安装时间|存放区域|存放位置|end|
			String duty_user_name = "";
			String duty_dept_name = "";
			List<String> seclv_name = null;

			if (event_device_content.getSeclv_code() != null) {
				String seclv_codes = Constants.COMMON_SEPARATOR + event_device_content.getSeclv_code()
						+ Constants.COMMON_SEPARATOR;
				String[] codes = seclv_codes.split("\\|");
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("codes", codes);
				seclv_name = basicService.getSeclvNameByCodes(map);
			}

			duty_user_name = userService.getUserNameByUserId(event_device_content.getDuty_user_id());
			duty_dept_name = userService.getDeptNameByDeptId(event_device_content.getDuty_dept_id());
			// duty_entp_name = userService.getDeptNameByDeptId(event_device_content.getDuty_entp_id());

			EntityComputer computer_temp = new EntityComputer("", event_device_content.getComputer_name(),
					event_device_content.getSeclv_code(), seclv_name.get(0), event_device_content.getDuty_user_id(),
					duty_user_name, event_device_content.getDuty_dept_id(), duty_dept_name,
					event_device_content.getDuty_entp_id(), event_device_content.getConf_code(), 0,
					event_device_content.getHdisk_no(), event_device_content.getComputer_os(),
					event_device_content.getInstall_time(), "", event_device_content.getComputer_mac(),
					event_device_content.getStorage_area(), event_device_content.getStorage_location(), "", "", "", "",
					null, event_device_content.getOldconf_code(), event_device_content.getMed_type_name());
			computer = computer_temp;

		} else if (event.getEvent_type() == 3) {// 计算机变更
			// computer_change组成：|计算机条码|密级代码|责任人ID|责任部门ID|责任单位ID|保密编号|硬盘序列号|IP|MAC|存放区域|存放位置|others
			// |before_chang|密级代码|责任人ID|责任部门ID|责任单位ID|保密编号|硬盘序列号|IP|MAC|存放区域|存放位置|end|
			String computer_barcode = "";
			Integer seclv_code = null;
			String duty_user_name = "";
			String duty_dept_name = "";
			List<String> seclv_name = null;

			if (event_device_content.getSeclv_code() != null) {
				seclv_code = event_device_content.getSeclv_code();
				String seclv_codes = Constants.COMMON_SEPARATOR + event_device_content.getSeclv_code()
						+ Constants.COMMON_SEPARATOR;
				String[] codes = seclv_codes.split("\\|");
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("codes", codes);
				seclv_name = basicService.getSeclvNameByCodes(map);
			}

			duty_user_name = userService.getUserNameByUserId(event_device_content.getDuty_user_id());
			duty_dept_name = userService.getDeptNameByDeptId(event_device_content.getDuty_dept_id());
			// duty_entp_name = userService.getDeptNameByDeptId(event_device_content.getDuty_entp_id());

			if (seclv_code == null) {
				EntityComputer computer_temp = new EntityComputer(event_device_content.getComputer_barcode(), "", null,
						"", event_device_content.getDuty_user_id(), duty_user_name,
						event_device_content.getDuty_dept_id(), duty_dept_name, event_device_content.getDuty_entp_id(),
						event_device_content.getConf_code(), 0, event_device_content.getHdisk_no(), "", null,
						event_device_content.getComputer_ip(), event_device_content.getComputer_mac(),
						event_device_content.getStorage_area(), event_device_content.getStorage_location(),
						event_device_content.getSumm(), "", "", null, null, event_device_content.getOldconf_code(),
						event_device_content.getMed_type_name());
				computer = computer_temp;
			} else {
				EntityComputer computer_temp = new EntityComputer(event_device_content.getComputer_barcode(), "",
						event_device_content.getSeclv_code(), seclv_name.get(0),
						event_device_content.getDuty_user_id(), duty_user_name, event_device_content.getDuty_dept_id(),
						duty_dept_name, event_device_content.getDuty_entp_id(), event_device_content.getConf_code(), 0,
						event_device_content.getHdisk_no(), "", null, event_device_content.getComputer_ip(),
						event_device_content.getComputer_mac(), event_device_content.getStorage_area(),
						event_device_content.getStorage_location(), event_device_content.getSumm(), "", "", null, null,
						event_device_content.getOldconf_code(), event_device_content.getMed_type_name());
				computer = computer_temp;
			}

			Integer old_seclv_code = null;
			String old_duty_user_name = "";
			String old_duty_dept_name = "";
			// String old_duty_entp_name = "";
			List<String> old_seclv_name = null;

			if (event_device_content.getBef_seclv_code() != null) {
				old_seclv_code = event_device_content.getBef_seclv_code();
				String seclv_codess = Constants.COMMON_SEPARATOR + event_device_content.getBef_seclv_code()
						+ Constants.COMMON_SEPARATOR;
				String[] codess = seclv_codess.split("\\|");
				Map<String, Object> maps = new HashMap<String, Object>();
				maps.put("codes", codess);
				old_seclv_name = basicService.getSeclvNameByCodes(maps);
			}

			old_duty_user_name = userService.getUserNameByUserId(event_device_content.getBef_duty_user_id());
			old_duty_dept_name = userService.getDeptNameByDeptId(event_device_content.getBef_duty_dept_id());
			// old_duty_entp_name = userService.getDeptNameByDeptId(event_device_content.getBef_duty_entp_id());

			Map<String, Object> mapss = new HashMap<String, Object>();
			mapss.put("computer_barcode", computer_barcode);
			computer_old = computerService.getComputerByMap(mapss);

			if (old_seclv_code == null) {
				computer_old.setSeclv_code(null);
				computer_old.setSeclv_name("");
			} else {
				computer_old.setSeclv_code(event_device_content.getBef_seclv_code());
				computer_old.setSeclv_name(old_seclv_name.get(0));
			}
			computer_old.setDuty_user_id(event_device_content.getDuty_user_id());
			computer_old.setDuty_user_name(old_duty_user_name);
			computer_old.setDuty_dept_id(event_device_content.getDuty_dept_id());
			computer_old.setDuty_dept_name(old_duty_dept_name);
			computer_old.setDuty_entp_id(event_device_content.getDuty_entp_id());
			computer_old.setConf_code(event_device_content.getBef_conf_code());
			computer_old.setHdisk_no(event_device_content.getBef_hdisk_no());
			computer_old.setComputer_ip(event_device_content.getBef_computer_ip());
			computer_old.setComputer_mac(event_device_content.getBef_computer_mac());
			computer_old.setStorage_area(event_device_content.getBef_storage_area());
			computer_old.setStorage_location(event_device_content.getBef_storage_location());

		} else if (event.getEvent_type() == 4) {// 计算机报废
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("computer_barcode", event.getBarcode());
			computer = computerService.getComputerByMap(map);
		}

		return SUCCESS;
	}
}