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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

public class ViewOpenPortEventDetailAction extends InfosystemBaseAction {

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
	private String input_chk = "";
	private String output_chk = "";
	private String otherports = "";
	private Date start_time = null;
	private Date end_time = null;
	private String start_time_str = "";
	private String end_time_str = "";

	private String event_content = "";
	private String resubmit = "N";// 是否重新提交
	private final String jobType = JobTypeEnum.EVENT_OPENPORT.getJobTypeCode();

	private String serialport = "";
	private String parallelport = "";
	private String otherport = "";
	private String first = "";
	private String second = "";
	private String third = "";

	public String getStart_time_str() {
		return start_time_str;
	}

	public String getEnd_time_str() {
		return end_time_str;
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

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public String getOp() {
		return op;
	}

	public ChangeDeviceEvent getEvent() {
		return event;
	}

	public ProcessJob getJob() {
		return job;
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

	public String getSerialport() {
		return serialport;
	}

	public String getParallelport() {
		return parallelport;
	}

	public String getOtherport() {
		return otherport;
	}

	public String getFirst() {
		return first;
	}

	public String getSecond() {
		return second;
	}

	public String getThird() {
		return third;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public void setInput_chk(String input_chk) {
		this.input_chk = input_chk;
	}

	public void setOutput_chk(String output_chk) {
		this.output_chk = output_chk;
	}

	public String getOtherports() {
		return otherports;
	}

	public void setOtherports(String otherports) {
		this.otherports = otherports;
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
				String output_point = event_device_content.getOutput_point();
				String input_point = event_device_content.getInput_point();
				if (StringUtils.hasLength(output_point)) {
					if (output_point.contains("串口")) {
						serialport = "yes";
						output_point = output_point.substring("串口".length());
					}
					if (output_point.contains("并口")) {
						parallelport = "yes";
						output_point = output_point.substring(",并口".length());
					}
					if (output_point.length() > 1) {
						otherport = "yes";
						otherports = output_point.substring(1);
					}
				}
				if (StringUtils.hasLength(input_point)) {
					if (input_point.contains("三合一单导盒")) {
						first = "yes";
					}
					if (input_point.contains("只读光驱")) {
						second = "yes";
					}
					if (input_point.contains("虚拟光驱")) {
						third = "yes";
					}
				}
				if (StringUtils.hasLength(event_device_content.getStart_time_str())) {
					start_time_str = event_device_content.getStart_time_str();
				}

				if (StringUtils.hasLength(event_device_content.getEnd_time_str())) {
					end_time_str = event_device_content.getEnd_time_str();
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
				String[] input_port_name = input_chk.split(",");
				String[] output_port_name = output_chk.split(",");
				String input_name = "";
				String output_name = "";
				for (int i = 0; i < output_port_name.length; i++) {
					if (output_port_name[i].contains("serialport")) {
						output_name = output_name + "串口";
					} else if (output_port_name[i].contains("parallelport")) {
						output_name = output_name + ",并口";
					} else if (output_port_name[i].contains("otherport")) {
						output_name = output_name + "," + otherports;
					}
				}
				for (int i = 0; i < input_port_name.length; i++) {
					if (input_port_name[i].contains("first")) {
						input_name = input_name + "三合一单导盒";
					} else if (input_port_name[i].contains("second")) {
						input_name = input_name + ",只读光驱";
					} else if (input_port_name[i].contains("third")) {
						input_name = input_name + ",虚拟光驱";
					}
				}
				// event_content组成：|输出端口类型|输入端口类型|开始时间|结束时间|end|
				// event_content = Constants.COMMON_SEPARATOR + output_name + Constants.COMMON_SEPARATOR + input_name
				// + Constants.COMMON_SEPARATOR + sdf.format(start_time) + Constants.COMMON_SEPARATOR
				// + sdf.format(end_time) + Constants.COMMON_SEPARATOR + "end" + Constants.COMMON_SEPARATOR;

				EntityEventDevice event_device_content = new EntityEventDevice(event_code, output_name, input_name,
						start_time, end_time);

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