package hdsec.web.project.infosystem.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.computer.model.ChangeDeviceEvent;
import hdsec.web.project.computer.model.EntityComputer;
import hdsec.web.project.computer.model.EntityDeviceOperation;
import hdsec.web.project.computer.model.EntityEventDevice;
import hdsec.web.project.secmanage.model.ApproveHistory;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

public class ViewLocalPrinterEventDetailAction extends InfosystemBaseAction {

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
	private EntityComputer computer = null;
	private List<ApproveHistory> approvehistoryList = null;

	private String event_reason = "";
	private String user_phone = "";
	private Date apply_time = null;
	// private String apply_time_str = "";
	private Integer seclv_code = null;// 作业密级
	private String next_approver = "";// 下级审批人
	private String summ = "";
	private String event_content = "";
	private String resubmit = "N";// 是否重新提交
	private final String jobType = JobTypeEnum.EVENT_LOCALPRINTER.getJobTypeCode();
	private String printer_seclv_name = "";// 打印机密级
	private String printer_model = "";
	private String conf_code = "";

	public List<SecLevel> getSeclvList() {
		return userService.getBurnSecLevelByUser(getCurUser().getUser_iidd());
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getEvent_reason() {
		return event_reason;
	}

	public void setEvent_reason(String event_reason) {
		this.event_reason = event_reason;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public String getPrinter_seclv_name() {
		return printer_seclv_name;
	}

	public void setPrinter_seclv_name(String printer_seclv_name) {
		this.printer_seclv_name = printer_seclv_name;
	}

	public String getPrinter_model() {
		return printer_model;
	}

	public void setPrinter_model(String printer_model) {
		this.printer_model = printer_model;
	}

	public String getConf_code() {
		return conf_code;
	}

	public void setConf_code(String conf_code) {
		this.conf_code = conf_code;
	}

	public String getOp() {
		return op;
	}

	public ChangeDeviceEvent getEvent() {
		return event;
	}

	public EntityComputer getComputer() {
		return computer;
	}

	public List<ApproveHistory> getApprovehistoryList() {
		return approvehistoryList;
	}

	public Date getApply_time() {
		return apply_time;
	}

	public String getJobType() {
		return jobType;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public void setResubmit(String resubmit) {
		this.resubmit = resubmit;
	}

	@Override
	public String executeFunction() throws Exception {
		event = computerService.getChangeDeviceEventByEventCode(event_code);
		if (event == null) {
			throw new Exception("查询的作业已经被删除");
		} else {
			if (resubmit.equals("N")) {// 查看信息,展示页面
				// String event_content = event.getEvent_content();
				// String[] computer_change = event_content.split("\\|");
				EntityEventDevice event_device_content = computerService.getEntityEventDeviceByEventCode(event_code);
				if (StringUtils.hasLength(event_device_content.getPrinter_model())
						&& !event_device_content.getPrinter_model().equals("null")) {
					printer_model = event_device_content.getPrinter_model();
				}

				if (StringUtils.hasLength(event_device_content.getPrinter_conf_code())
						&& !event_device_content.getPrinter_conf_code().equals("null")) {
					conf_code = event_device_content.getPrinter_conf_code();
				}

				if (StringUtils.hasLength(event_device_content.getPrinter_seclv_name())
						&& !event_device_content.getPrinter_seclv_name().equals("null")) {
					printer_seclv_name = event_device_content.getPrinter_seclv_name();
				}
				// 流程及审批信息
				String job_code = event.getJob_code();
				if (StringUtils.hasLength(job_code)) {
					job = basicService.getProcessJobByCode(job_code);
					process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
					ProcessRecord record = new ProcessRecord();
					record.setJob_code(job_code);
					recordList = activitiService.getProcessRecordList(record);
					approvehistoryList = new ArrayList<ApproveHistory>();
					for (int i = 1; i < recordList.size(); i++) {
						String[] approvehistory = recordList.get(i).getOpinion().split("#");
						String approvecontent = "";
						if (approvehistory[1].contains("请审批")) {
							approvecontent = approvehistory[1] + "<br>申请人：" + recordList.get(i).getUser_name()
									+ "<br>重新申请时间：" + recordList.get(i).getOp_time_str();
						} else if (approvehistory[1].contains("HaveUSBKEYOperation")) {
							int endIndex = approvehistory[1].lastIndexOf("HaveUSBKEYOperation");
							String opin = approvehistory[1].substring(0, endIndex);// 去掉HaveUSBKEYOperation
							EntityDeviceOperation operation = computerService.getDeviceOperationDataByEventCode(event
									.getEvent_code());// 此处是USBkey审批过程中的数据

							approvecontent = "旧key号：" + operation.getOld_content() + "<br>新key号："
									+ operation.getNew_content() + " 用户ID：" + operation.getUser_id() + "<br>证书时间:"
									+ operation.getKey_time() + " 证书有效期：" + operation.getStart_time() + "至"
									+ operation.getEnd_time() + "<br>" + opin + "<br>审批人："
									+ recordList.get(i).getUser_name() + "<br>审批时间："
									+ recordList.get(i).getOp_time_str();
						} else {
							approvecontent = approvehistory[1] + "<br>审批人：" + recordList.get(i).getUser_name()
									+ "<br>审批时间：" + recordList.get(i).getOp_time_str();
						}
						approvehistoryList.add(new ApproveHistory(approvehistory[0], approvecontent));
					}
				}
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("computer_barcode", event.getBarcode());
				computer = computerService.getComputerByMap(map);

				return SUCCESS;
			} else {// 重新提交申请
				String user_iidd = getCurUser().getUser_iidd();
				String user_name = getCurUser().getUser_name();
				String job_code = computerService.getChangeDeviceEventJobCodeByEventCode(event_code);

				// event_content组成：|打印机型号|打印机密级编号|打印机密级|end|
				// event_content = Constants.COMMON_SEPARATOR + printer_model + Constants.COMMON_SEPARATOR + conf_code
				// + Constants.COMMON_SEPARATOR + printer_seclv_name + Constants.COMMON_SEPARATOR + "end"
				// + Constants.COMMON_SEPARATOR;

				EntityEventDevice event_device_content = new EntityEventDevice(event_code, printer_model, conf_code,
						printer_seclv_name);

				Map<String, String> map = new HashMap<String, String>();
				map.put("event_code", event_code);
				map.put("event_content", event_content);
				map.put("event_reason", event_reason);
				computerService.updateChangeDeviceEvent(map);// 更新申请
				computerService.updateEntityEventDevice(event_device_content);// 向ref_event_device表中更新此event对应的项
				// 2.更新流程中的信息
				ApproverUser user = new ApproverUser(user_iidd, user_name);
				String next_approver_userid = next_approver;
				String next_approver_username = "";
				for (String item_id : next_approver_userid.split(",")) {
					String username = userService.getUserNameByUserId(item_id);
					next_approver_username = next_approver_username + "," + username;
				}
				ApproverUser approver = new ApproverUser(next_approver_userid, next_approver_username);
				securityUserService.resubmitApplyJob(job_code, user, approver);
				insertCommonLog("USBKEY重新提交申请[" + event_code + "]");
				return "ok";
			}
		}
	}

}