package hdsec.web.project.infosystem.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.activiti.util.ActivitiCons;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.computer.model.ChangeDeviceEvent;
import hdsec.web.project.computer.model.EntityComputer;
import hdsec.web.project.computer.model.EntityDeviceOperation;
import hdsec.web.project.computer.model.EntityEventDevice;
import hdsec.web.project.computer.model.EntityHardDisk;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.util.StringUtils;

public class ApproveInfosystemJobAction extends InfosystemBaseAction {

	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	private List<ChangeDeviceEvent> eventList = null;
	private ChangeDeviceEvent event = null;
	private String approved = "";
	private String opinion = "";
	private String next_approver = "";
	private List<ApproverUser> userList = null;
	private String jobType;
	private EntityComputer computer = null;
	// private String apply_type_name = "";
	// private String power_type_name = "";
	private Integer apply_type = null;
	private Integer power_type = null;
	private String output_port = "";
	private String input_port = "";
	private String start_time = "";
	private String end_time = "";
	private String printer_model = "";
	private String printer_conf_code = "";
	private String printer_seclv_name = "";

	private String history = "";
	private Integer listSize = null;
	private String opinion_all = "";
	private String event_code = "";
	private String current_step = "";
	private String opinion_history = "";

	private String old_content = "";
	private String new_content = "";
	private String user_id = "";
	private String key_time = "";
	private Date start_t = null;
	private Date end_t = null;
	private String deal_with = "";
	private String key_code = "";
	private String computer_ip = "";
	private String switch_num = "";
	private String switch_point = "";
	private String software_type = "";// 软件安装
	private String software_summ = "";
	private String mark_code = "";
	private String vlan_num = "";
	private String computer_gateway = "";
	private String operation_user_id = "";
	private Date operation_time = null;
	private String operation_content = "";
	private String software_typename = "";
	private String isrecycle = "";
	private String content = "";
	private String output_chk = "";

	public void setOutput_chk(String output_chk) {
		this.output_chk = output_chk;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setIsrecycle(String isrecycle) {
		this.isrecycle = isrecycle;
	}

	public String getSoftware_typename() {
		return software_typename;
	}

	public void setSoftware_typename(String software_typename) {
		this.software_typename = software_typename;
	}

	public String getOperation_content() {
		return operation_content;
	}

	public void setOperation_content(String operation_content) {
		this.operation_content = operation_content.replaceAll(" 是", "是").replaceAll(" 否", "否");
	}

	public Date getOperation_time() {
		return operation_time;
	}

	public void setOperation_time(Date operation_time) {
		this.operation_time = operation_time;
	}

	public String getOperation_user_id() {
		return operation_user_id;
	}

	public void setOperation_user_id(String operation_user_id) {
		this.operation_user_id = operation_user_id;
	}

	public String getMark_code() {
		return mark_code;
	}

	public void setMark_code(String mark_code) {
		this.mark_code = mark_code;
	}

	public String getComputer_gateway() {
		return computer_gateway;
	}

	public void setComputer_gateway(String computer_gateway) {
		this.computer_gateway = computer_gateway;
	}

	public String getVlan_num() {
		return vlan_num;
	}

	public void setVlan_num(String vlan_num) {
		this.vlan_num = vlan_num;
	}

	public Integer getListSize() {
		return listSize;
	}

	public void setDeal_with(String deal_with) {
		this.deal_with = deal_with;
	}

	public String getHistory() {
		return history;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public void setOld_content(String old_content) {
		this.old_content = old_content;
	}

	public void setNew_content(String new_content) {
		this.new_content = new_content;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public void setKey_time(String key_time) {
		this.key_time = key_time;
	}

	public void setStart_t(Date start_t) {
		this.start_t = start_t;
	}

	public void setEnd_t(Date end_t) {
		this.end_t = end_t;
	}

	public List<ChangeDeviceEvent> getEventList() {
		return eventList;
	}

	public String getOpinion_all() {
		return opinion_all;
	}

	public String getCurrent_step() {
		return current_step;
	}

	public String getOpinion_history() {
		return opinion_history;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public void setListSize(Integer listSize) {
		this.listSize = listSize;
	}

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

	public List<ApproverUser> getUserList() {
		return userList;
	}

	public void setUserList(List<ApproverUser> userList) {
		this.userList = userList;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public ProcessJob getJob() {
		return job;
	}

	public ApproveProcess getProcess() {
		return process;
	}

	public List<ProcessRecord> getRecordList() {
		return recordList;
	}

	public ChangeDeviceEvent getEvent() {
		return event;
	}

	public EntityComputer getComputer() {
		return computer;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public String getKey_code() {
		return key_code;
	}

	public void setKey_code(String key_code) {
		this.key_code = key_code;
	}

	public String getComputer_ip() {
		return computer_ip;
	}

	public void setComputer_ip(String computer_ip) {
		this.computer_ip = computer_ip;
	}

	public String getSwitch_num() {
		return switch_num;
	}

	public void setSwitch_num(String switch_num) {
		this.switch_num = switch_num;
	}

	public String getSwitch_point() {
		return switch_point;
	}

	public void setSwitch_point(String switch_point) {
		this.switch_point = switch_point;
	}

	public String getSoftware_type() {
		return software_type;
	}

	public void setSoftware_type(String software_type) {
		this.software_type = software_type;
	}

	public String getSoftware_summ() {
		return software_summ;
	}

	public void setSoftware_summ(String software_summ) {
		this.software_summ = software_summ;
	}

	private void getHandle2(Integer history_step) {
		int i = 0;
		for (i = 1; i <= history_step; i++) {
			if (recordList.get(i).getOpinion().contains("请审批")) {
				opinion_history = opinion_history + Constants.COMMON_SEPARATOR + recordList.get(i).getOpinion()
						+ "<br>申请人：" + recordList.get(i).getUser_name() + "<br>重新申请时间："
						+ recordList.get(i).getOp_time_str();
			} else if (recordList.get(i).getOpinion().contains("HaveUSBKEYOperation")) {// 操作关联表中有相应的操作记录，读取后放在opinion中展示在页面中
				int endIndex = recordList.get(i).getOpinion().lastIndexOf("HaveUSBKEYOperation");
				String opin = recordList.get(i).getOpinion().substring(0, endIndex);// 去掉HaveUSBKEYOperation
				endIndex = opin.lastIndexOf("#");
				String opinion_name = opin.substring(0, endIndex);// 获取审批步骤名称
				String opinion_content = opin.substring(endIndex + 1);// 获取审批步骤内容
				EntityDeviceOperation operation = computerService.getDeviceOperationDataByEventCode(event
						.getEvent_code());// 此处是USBkey审批过程中的数据
				opinion_history = opinion_history + Constants.COMMON_SEPARATOR + opinion_name + "#旧key号："
						+ operation.getOld_content() + "<br>新key号：" + operation.getNew_content() + " 用户ID："
						+ operation.getUser_id() + "<br>证书时间:" + operation.getKey_time() + " 证书有效期："
						+ operation.getStart_time() + "至" + operation.getEnd_time() + "<br>" + opinion_content
						+ "<br>审批人：" + recordList.get(i).getUser_name() + "<br>审批时间："
						+ recordList.get(i).getOp_time_str();
			} else if (recordList.get(i).getOpinion().contains("quitinternet")) {
				int endIndex = recordList.get(i).getOpinion().lastIndexOf("quitinternet");
				String opin = recordList.get(i).getOpinion().substring(0, endIndex);// 去掉quitinternet
				endIndex = opin.lastIndexOf("#");
				String opinion_name = opin.substring(0, endIndex);// 获取审批步骤名称
				String opinion_content = opin.substring(endIndex + 1);// 获取审批步骤内容
				EntityDeviceOperation operation = computerService.getDeviceOperationDataByEventCode(event
						.getEvent_code());
				String softName = getSoftName(operation.getSoftware_type());
				opinion_history = opinion_history + Constants.COMMON_SEPARATOR + opinion_name + "#Key号:"
						+ operation.getKey_code() + "<br>" + "计算机IP:" + operation.getComputer_ip() + "<br>" + "交换机IP:"
						+ operation.getSwitch_num() + "<br>" + "交换机端口:" + operation.getSwitch_point() + "<br>" + "掩码:"
						+ operation.getMark_code() + "<br>" + "已注销软件:" + softName + "<br>" + "软件注销备注:"
						+ operation.getSoftware_summ() + "<br>" + opinion_content + "<br>审批人："
						+ recordList.get(i).getUser_name() + "<br>审批时间：" + recordList.get(i).getOp_time_str();

			} else if (recordList.get(i).getOpinion().contains("reinstall")) {
				int endIndex = recordList.get(i).getOpinion().lastIndexOf("reinstall");
				String opin = recordList.get(i).getOpinion().substring(0, endIndex);// 去掉reinstall
				endIndex = opin.lastIndexOf("#");
				String opinion_name = opin.substring(0, endIndex);// 获取审批步骤名称
				// String opinion_content = opin.substring(endIndex + 1);// 获取审批步骤内容
				EntityDeviceOperation operation = computerService.getDeviceOperationDataByEventCode(event
						.getEvent_code());// 此处是退网审批过程中的数据

				String softName = getSoftName(operation.getSoftware_type());
				opinion_history = opinion_history + Constants.COMMON_SEPARATOR + opinion_name + "#软件安装情况:" + softName
						+ "<br>" + "未安装原因:" + operation.getSoftware_summ() + "<br>操作系统安装时间："
						+ operation.getOperation_time() + "<br>安装人：" + operation.getOperation_user_id() + "<br>审批人："
						+ recordList.get(i).getUser_name() + "<br>审批时间：" + recordList.get(i).getOp_time_str();
			} else if (recordList.get(i).getOpinion().contains("computerrepair")) {
				int endIndex = recordList.get(i).getOpinion().lastIndexOf("computerrepair");
				String opin = recordList.get(i).getOpinion().substring(0, endIndex);// 去掉computerrepair
				endIndex = opin.lastIndexOf("#");
				String opinion_name = opin.substring(0, endIndex);// 获取审批步骤名称
				// String opinion_content = opin.substring(endIndex + 1);// 获取审批步骤内容
				EntityDeviceOperation operation = computerService.getDeviceOperationDataByEventCode(event
						.getEvent_code());
				String[] splOperation_content = operation.getOperation_content().split(",");
				String softName = getSoftName(operation.getSoftware_type());
				opinion_history = opinion_history + Constants.COMMON_SEPARATOR + opinion_name + "#是否更换硬盘:"
						+ splOperation_content[0] + "&nbsp;&nbsp;新硬盘序列号:" + splOperation_content[1] + "<br>是否增加新硬盘:"
						+ splOperation_content[2] + "&nbsp;&nbsp;新硬盘序列号:" + splOperation_content[3] + "<br>是否更换其他部件:"
						+ splOperation_content[4] + "&nbsp;&nbsp;部件名称:" + splOperation_content[5] + "<br>是否变更MAC地址:"
						+ splOperation_content[6] + "&nbsp;&nbsp;变更后MAC地址:" + splOperation_content[7] + "<br>是否重装操作系统:"
						+ splOperation_content[8] + "&nbsp;&nbsp;具体描述:" + splOperation_content[9] + "<br>解决办法:"
						+ splOperation_content[10] + "&nbsp;&nbsp;维修地点:" + splOperation_content[11] + "<br>"
						+ "软件安装情况:" + softName + "<br>" + "未安装原因:" + operation.getSoftware_summ() + "<br>维修时间："
						+ operation.getOperation_time() + "<br>操作系统时间：" + operation.getStart_time() + "<br>维修人："
						+ operation.getOperation_user_id() + "<br>审批人：" + recordList.get(i).getUser_name()
						+ "<br>审批时间：" + recordList.get(i).getOp_time_str();
			} else if (recordList.get(i).getOpinion().contains("computerisrepair")) {
				int endIndex = recordList.get(i).getOpinion().lastIndexOf("computerisrepair");
				String opinion_name = recordList.get(i).getOpinion().substring(0, endIndex);// 去掉computerisrepair

				opinion_history = opinion_history + Constants.COMMON_SEPARATOR + opinion_name + "#审批人："
						+ recordList.get(i).getUser_name() + "<br>审批时间：" + recordList.get(i).getOp_time_str();
			} else if (recordList.get(i).getOpinion().contains("computeropen")) {// 计算机开通端口20160107
				int endIndex = recordList.get(i).getOpinion().lastIndexOf("computeropen");
				String opinion_name = recordList.get(i).getOpinion().substring(0, endIndex);
				EntityDeviceOperation operation = computerService.getDeviceOperationDataByEventCode(event
						.getEvent_code());

				opinion_history = opinion_history + Constants.COMMON_SEPARATOR + opinion_name + "#开通输入输出端口需求"
						+ operation.getOperation_content() + "<br>审批人：" + recordList.get(i).getUser_name()
						+ "<br>审批时间：" + recordList.get(i).getOp_time_str();
			} else {
				opinion_history = opinion_history + Constants.COMMON_SEPARATOR + recordList.get(i).getOpinion()
						+ "<br>审批人：" + recordList.get(i).getUser_name() + "<br>审批时间："
						+ recordList.get(i).getOp_time_str();
			}
		}
		for (i = history_step + 1; i <= listSize; i++) {
			if (recordList.get(i).getOpinion().contains("HaveUSBKEYOperation")) {// 操作关联表中有相应的操作记录，读取后放在opinion中展示在页面中
				int endIndex = recordList.get(i).getOpinion().lastIndexOf("HaveUSBKEYOperation");
				String opin = recordList.get(i).getOpinion().substring(0, endIndex);// 去掉HaveUSBKEYOperation
				endIndex = opin.lastIndexOf("#");
				String opinion_name = opin.substring(0, endIndex);// 获取审批步骤名称
				String opinion_content = opin.substring(endIndex + 1);// 获取审批步骤内容
				EntityDeviceOperation operation = computerService.getDeviceOperationDataByEventCode(event
						.getEvent_code());// 此处是USBkey审批过程中的数据
				opinion_all = opinion_all + Constants.COMMON_SEPARATOR + opinion_name + "#旧key号："
						+ operation.getOld_content() + "<br>新key号：" + operation.getNew_content() + "<br>用户ID："
						+ operation.getUser_id() + "<br>证书时间:" + operation.getKey_time() + "<br>证书有效期："
						+ operation.getStart_time() + "至" + operation.getEnd_time() + "<br>" + opinion_content
						+ "<br>审批人：" + recordList.get(i).getUser_name() + "<br>审批时间："
						+ recordList.get(i).getOp_time_str();
			} else if (recordList.get(i).getOpinion().contains("quitinternet")) {
				int endIndex = recordList.get(i).getOpinion().lastIndexOf("quitinternet");
				String opin = recordList.get(i).getOpinion().substring(0, endIndex);// 去掉quitinternet
				endIndex = opin.lastIndexOf("#");
				String opinion_name = opin.substring(0, endIndex);// 获取审批步骤名称
				String opinion_content = opin.substring(endIndex + 1);// 获取审批步骤内容

				EntityDeviceOperation operation = computerService.getDeviceOperationDataByEventCode(event
						.getEvent_code());// 此处是USBkey审批过程中的数据
				String softName = getSoftName(operation.getSoftware_type());
				opinion_all = opinion_all + Constants.COMMON_SEPARATOR + opinion_name + "#Key号:"
						+ operation.getKey_code() + "<br>" + "计算机IP:" + operation.getComputer_ip() + "<br>" + "交换机IP:"
						+ operation.getSwitch_num() + "<br>" + "交换机端口:" + operation.getSwitch_point() + "<br>" + "掩码:"
						+ operation.getMark_code() + "<br>" + "已注销软件:" + softName + "<br>" + "软件注销备注:"
						+ operation.getSoftware_summ() + "<br>" + opinion_content + "<br>审批人："
						+ recordList.get(i).getUser_name() + "<br>审批时间：" + recordList.get(i).getOp_time_str();

			} else if (recordList.get(i).getOpinion().contains("reinstall")) {
				int endIndex = recordList.get(i).getOpinion().lastIndexOf("reinstall");
				String opinion_name = recordList.get(i).getOpinion().substring(0, endIndex);// 去掉reinstall

				EntityDeviceOperation operation = computerService.getDeviceOperationDataByEventCode(event
						.getEvent_code());// 此处是重装系统审批过程中的数据

				String softName = getSoftName(operation.getSoftware_type());
				opinion_all = opinion_all + Constants.COMMON_SEPARATOR + opinion_name + "<br>软件安装情况:" + softName
						+ "<br>" + "未安装原因:" + operation.getSoftware_summ() + "<br>操作系统安装时间："
						+ operation.getOperation_time() + "<br>安装人：" + operation.getOperation_user_id() + "<br>审批人："
						+ recordList.get(i).getUser_name() + "<br>审批时间：" + recordList.get(i).getOp_time_str();
			} else if (recordList.get(i).getOpinion().contains("computerrepair")) {
				int endIndex = recordList.get(i).getOpinion().lastIndexOf("computerrepair");
				String opinion_name = recordList.get(i).getOpinion().substring(0, endIndex);// 去掉computerrepair

				EntityDeviceOperation operation = computerService.getDeviceOperationDataByEventCode(event
						.getEvent_code());// 此处是退网审批过程中的数据
				String[] splOperation_content = operation.getOperation_content().split(",");
				String softName = getSoftName(operation.getSoftware_type());
				opinion_all = opinion_all + Constants.COMMON_SEPARATOR + opinion_name + "<br>是否更换硬盘:"
						+ splOperation_content[0] + "&nbsp;&nbsp;新硬盘序列号:" + splOperation_content[1] + "<br>是否增加新硬盘:"
						+ splOperation_content[2] + "&nbsp;&nbsp;新硬盘序列号:" + splOperation_content[3] + "<br>是否更换其他部件:"
						+ splOperation_content[4] + "&nbsp;&nbsp;部件名称:" + splOperation_content[5] + "<br>是否变更MAC地址:"
						+ splOperation_content[6] + "&nbsp;&nbsp;变更后MAC地址:" + splOperation_content[7] + "<br>是否重装操作系统:"
						+ splOperation_content[8] + "&nbsp;&nbsp;具体描述:" + splOperation_content[9] + "<br>解决办法:"
						+ splOperation_content[10] + "&nbsp;&nbsp;维修地点:" + splOperation_content[11] + "<br>"
						+ "软件安装情况:" + softName + "<br>" + "未安装原因:" + operation.getSoftware_summ() + "<br>维修时间："
						+ operation.getOperation_time() + "<br>操作系统时间：" + operation.getStart_time() + "<br>维修人："
						+ operation.getOperation_user_id() + "<br>审批人：" + recordList.get(i).getUser_name()
						+ "<br>审批时间：" + recordList.get(i).getOp_time_str();
			} else if (recordList.get(i).getOpinion().contains("computerisrepair")) {
				int endIndex = recordList.get(i).getOpinion().lastIndexOf("computerisrepair");
				String opinion_name = recordList.get(i).getOpinion().substring(0, endIndex);// 去掉computerisrepair

				opinion_all = opinion_all + Constants.COMMON_SEPARATOR + opinion_name + "#审批人："
						+ recordList.get(i).getUser_name() + "<br>审批时间：" + recordList.get(i).getOp_time_str();
			} else if (recordList.get(i).getOpinion().contains("computeropen")) {// 计算机开通端口20160107
				int endIndex = recordList.get(i).getOpinion().lastIndexOf("computeropen");
				String opinion_name = recordList.get(i).getOpinion().substring(0, endIndex);
				EntityDeviceOperation operation = computerService.getDeviceOperationDataByEventCode(event
						.getEvent_code());

				opinion_all = opinion_all + Constants.COMMON_SEPARATOR + opinion_name + "<br>开通输入输出端口需求:  "
						+ operation.getOperation_content() + "<br>审批人：" + recordList.get(i).getUser_name()
						+ "<br>审批时间：" + recordList.get(i).getOp_time_str();
			} else {
				opinion_all = opinion_all + Constants.COMMON_SEPARATOR + recordList.get(i).getOpinion() + "<br>审批人："
						+ recordList.get(i).getUser_name() + "<br>审批时间：" + recordList.get(i).getOp_time_str();
			}
		}
	}

	private void getHandle1(EntityEventDevice event_device_content) {
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
	}

	private List<ApproverUser> removeDuplicateList(List<ApproverUser> oriList) {
		Set<String> set = new HashSet<String>();
		List<ApproverUser> newList = new ArrayList<ApproverUser>();
		for (ApproverUser item : oriList) {
			if (set.add(item.getUser_iidd())) {
				newList.add(item);
			}
		}
		return newList;
	}

	@Override
	public String executeFunction() throws Exception {
		if (history.equals("Y")) {
			if (job_code.length() != 0) {
				job = basicService.getProcessJobByCode(job_code);
			} else {
				job_code = computerService.getChangeDeviceEventJobCodeByEventCode(event_code);
				job = basicService.getProcessJobByCode(job_code);
			}

			process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
			ProcessRecord record = new ProcessRecord();
			record.setJob_code(job_code);
			recordList = activitiService.getProcessRecordList(record);
			event = computerService.getChangeDeviceEventByJobCode(job_code);

			EntityEventDevice device_content = computerService.getEntityEventDeviceByEventCode(event.getEvent_code());
			getHandle1(device_content);

			listSize = recordList.size() - 1;
			Map<String, Object> variables = basicPrcManage.getProcessVariables(job.getInstance_id());
			Integer list = listSize + 1;
			if (variables == null) {
				current_step = list.toString();
			} else {
				current_step = variables.get("cur_approval").toString();
			}

			Integer history_step = listSize - Integer.parseInt(current_step) + 1;
			if (job.getJob_status().equals(ActivitiCons.APPLY_APPROVED_REJECT)) {
				history_step -= 1;
			}
			// 已审批意见和历史意见查看
			getHandle2(history_step);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("computer_barcode", event.getBarcode());
			computer = computerService.getComputerByMap(map);
			if (!computer.getKey_code().equals("")) {
				String[] code = computer.getKey_code().split(",");
				computer.setKey_code(code[0]);
			}
			if (event.getEvent_type() == 8) {// USB-KEY申请/更新
				return "ok_usbkey";
			} else if (event.getEvent_type() == 9) {// 开通端口
				return "ok_openport";
			} else if (event.getEvent_type() == 10) {// 保留本地打印机
				return "ok_localprinter";
			} else if (event.getEvent_type() == 5) {// 计算机维修
				return "ok_computerrepair";
			} else if (event.getEvent_type() == 6) {// 计算机重装系统
				return "ok_reinstallsystem";
			} else if (event.getEvent_type() == 7) {// 计算机退网
				return "ok_quitinternet";
			} else {
				return SUCCESS;
			}
		} else {// 审批

			if (StringUtils.hasLength(approved)) {// 记录审批结果
				event = computerService.getChangeDeviceEventByJobCode(job_code);
				String next_approver_name = basicService.getApproverName(next_approver);
				ApproverUser user = new ApproverUser(getCurUser().getUser_iidd(), getCurUser().getUser_name());
				ApproverUser approver = new ApproverUser(next_approver, next_approver_name);

				if (event.getEvent_type() == 8 && deal_with.equals("yes")) {// USBKEY信息中心处理时将相关信息记录到操作关联表中
					EntityDeviceOperation operation = new EntityDeviceOperation(event.getEvent_code(),
							event.getBarcode(), 4, getCurUser().getUser_iidd(), new Date(), old_content, new_content,
							user_id, key_time, start_t, end_t);
					computerService.addRefDeviceOperation(operation);
					opinion = opinion + "HaveUSBKEYOperation";// 标示此条审批中有相关操作记录到了操作关联表中
				} else if (event.getEvent_type() == 7 && !key_code.isEmpty()) {// 计算机退网信息中心处理时将相关信息记录到操作关联表中
					Map<String, Object> quit = new HashMap<String, Object>();
					quit.put("event_code", event.getEvent_code());
					quit.put("key_code", key_code);
					quit.put("computer_ip", computer_ip);
					quit.put("switch_num", switch_num);
					quit.put("switch_point", switch_point);
					quit.put("vlan_num", vlan_num);
					quit.put("mark_code", mark_code);
					quit.put("computer_gateway", computer_gateway);
					quit.put("software_type", software_type);
					quit.put("software_summ", software_summ);

					computerService.addDeviceOperationByEventCode(quit);
					opinion = opinion + "quitinternet";// 标示此条审批中有相关操作记录到了操作关联表中
				} else if (event.getEvent_type() == 6 && !software_type.isEmpty()) {// 计算机重装操作系统信息中心处理时将相关信息记录到操作关联表中
					Map<String, Object> reinstall = new HashMap<String, Object>();
					reinstall.put("event_code", event.getEvent_code());
					reinstall.put("software_type", software_type);
					reinstall.put("software_summ", software_summ);
					reinstall.put("operation_time", operation_time);
					reinstall.put("operation_user_id", operation_user_id);

					computerService.addDeviceOperationByEventCode(reinstall);
					opinion = opinion + "reinstall";// 标示此条审批中有相关操作记录到了操作关联表中
				} else if (event.getEvent_type() == 5) {// 计算机维修
					if (!software_type.equals("")) {// 信息中心处理时将相关信息记录到操作关联表中
						Map<String, Object> computerrepair = new HashMap<String, Object>();
						computerrepair.put("event_code", event.getEvent_code());
						computerrepair.put("software_type", software_type);
						computerrepair.put("software_summ", software_summ);
						computerrepair.put("operation_time", operation_time);
						computerrepair.put("operation_user_id", operation_user_id);
						computerrepair.put("operation_content", operation_content);
						computerrepair.put("start_time", start_t);

						computerService.addDeviceOperationByEventCode(computerrepair);
						opinion = opinion + "computerrepair";// 标示此条审批中有相关操作记录到了操作关联表中
					} else if (!isrecycle.equals("")) {// 保密处回收硬盘
						String other_opinion = "";
						if (isrecycle.equals("是")) {
							String newhdisk = "";
							Map<String, Object> map1 = new HashMap<String, Object>();
							map1.put("computer_barcode", event.getBarcode());
							computer = computerService.getComputerByMap(map1);
							if (computer.getHdisk_no().trim().compareTo(content.trim()) != 0) {
								// 修改硬盘序列号则将该字段更新到硬盘表的备注中，添加到硬盘表中
								newhdisk = content.trim();
							}
							EntityHardDisk disk = new EntityHardDisk("computer", computer.getComputer_barcode(),
									computer.getConf_code(), computer.getHdisk_no(), computer.getDuty_user_id(),
									computer.getDuty_dept_id(), computer.getDuty_entp_id(),
									getCurUser().getUser_iidd(), new Date(), newhdisk, event.getJob_code(), 1);
							computerService.addEntityHardDisk(disk);// 将硬盘相关信息更新到硬盘台账中

							other_opinion = "<br>" + "回收硬盘：硬盘序列号为：" + content.trim();
						} else {
							// 不回收硬盘，则仅作为展示即可
							other_opinion = "<br>" + "不回收硬盘";
						}
						opinion = opinion + other_opinion + "computerisrepair";// 标示此条审批中有相关操作记录到了操作关联表中
					}
				} else if (event.getEvent_type() == 9) {// 开通计算机端口
					if (listSize == 4) {
						Map<String, Object> open = new HashMap<String, Object>();
						open.put("event_code", event.getEvent_code());
						if (!output_chk.trim().contains("无")) {
							output_chk = output_chk.trim().substring(1);
						}
						open.put("operation_content", output_chk);

						computerService.addDeviceOperationByEventCode(open);
						opinion = opinion + "computeropen";
					}
				}
				securityUserService.extendApproveJob(job_code, user, approver, approved, opinion, "");
				insertCommonLog("审批流程申请任务[" + job_code + "]");
				return "ok";
			} else {// 展示审批页面
				event = computerService.getChangeDeviceEventByJobCode(job_code);
				EntityEventDevice event_device_content = computerService.getEntityEventDeviceByEventCode(event
						.getEvent_code());
				getHandle1(event_device_content);

				// 流程及审批信息
				job = basicService.getProcessJobByCode(job_code);
				process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
				ProcessRecord record = new ProcessRecord();
				record.setJob_code(job_code);
				recordList = activitiService.getProcessRecordList(record);
				// add by liuyaling
				Map<String, Object> variables = basicPrcManage.getProcessVariables(job.getInstance_id());
				current_step = variables.get("cur_approval").toString();
				listSize = recordList.size() - 1;
				Integer history_step = listSize - Integer.parseInt(current_step) + 1;
				getHandle2(history_step);

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("computer_barcode", event.getBarcode());
				computer = computerService.getComputerByMap(map);

				try {
					List<ApproverUser> oriList = basicService.getNextApprover(job_code, job.getDept_id(),
							job.getSeclv_code(), job.getJobType().getJobTypeCode(), "");
					userList = removeDuplicateList(oriList);

					// 流程需要申请人（普通用户角色）确认,声光电BM，
					// Map<String, Object> variables = basicPrcManage.getProcessVariables(job.getInstance_id());
					Integer cur_approval = (Integer) variables.get("cur_approval");
					// Integer total_steps = process.getTotal_steps() - 1;
					if (process.getTotal_steps() > cur_approval) {// 如果是最后一步
						String[] roles = process.getStep_role().split("#");
						String role_ids = roles[cur_approval];
						// 如果审批人角色有且只有普通用户，则视为申请用户确认
						if (role_ids.equals("11")) {
							String user_iidd = getCurUser().getUser_iidd();
							String user_name = getCurUser().getUser_name();
							if (job != null) {
								user_iidd = job.getUser_iidd();
								user_name = job.getUser_name();
							}
							ApproverUser applyUser = new ApproverUser(user_iidd, user_name);
							userList.removeAll(userList);
							userList.add(applyUser);

							if (event.getEvent_type() == 8) {// USB-KEY申请/更新
								return "ok_usbkey";
							} else if (event.getEvent_type() == 9) {// 开通端口
								return "ok_openport";
							} else if (event.getEvent_type() == 10) {// 保留本地打印机
								return "ok_localprinter";
							} else if (event.getEvent_type() == 5) {// 计算机维修
								return "ok_computerrepair";
							} else if (event.getEvent_type() == 6) {// 计算机重装系统
								return "ok_reinstallsystem";
							} else if (event.getEvent_type() == 7) {// 计算机退网
								return "ok_quitinternet";
							} else {
								return SUCCESS;
							}
						}
					}

					if (!basicService.isSelfApprove()) {// 不允许自审批
						if ((userList != null) && (userList.size() == 1)
								&& userList.get(0).getUser_iidd().equals(job.getUser_iidd())) {
							throw new Exception("唯一的下级审批人与申请用户相同！由于系统不支持自审批，请联系管理员添加可审批用户！");
						} else {
							for (ApproverUser user : userList) {
								if (user.getUser_iidd().equals(job.getUser_iidd())) {
									userList.remove(user);
									logger.debug("审批列表中去掉申请用户");
									break;
								}
							}
							for (ApproverUser user : userList) {
								if (user.getUser_iidd().equals(getCurUser().getUser_iidd())) {
									userList.remove(user);
									logger.debug("审批列表中去掉当前审批人");
									break;
								}
							}
						}
					}

					List<ApproverUser> tempList = new ArrayList<ApproverUser>();
					for (ApproverUser user : userList) {
						List<SecLevel> seclvList = new ArrayList<SecLevel>();
						seclvList = userService.getSecLevel();
						for (SecLevel seclv : seclvList) {
							if (seclv.getSeclv_code() == job.getSeclv_code()) {
								tempList.add(user);
								break;
							}
						}
					}
					if (userList.size() > 0 && tempList.size() == 0) {
						throw new Exception("下级审批人涉密级别低于审批单密级，请联系管理员");
					}
					userList = tempList;
				} catch (Exception e) {
					logger.error("Exception:" + e.getMessage());
					if (e.getCause() instanceof TooManyResultsException) {
						logger.error("基于该部门、密级和操作的流程定义重复，请提醒管理员修改");
						throw new Exception("基于该部门、密级和操作的流程定义重复，请提醒管理员修改");
					} else {
						throw e;
					}
				}
				if (event.getEvent_type() == 8) {// USB-KEY申请/更新
					return "ok_usbkey";
				} else if (event.getEvent_type() == 9) {// 开通端口
					return "ok_openport";
				} else if (event.getEvent_type() == 10) {// 保留本地打印机
					return "ok_localprinter";
				} else if (event.getEvent_type() == 5) {// 计算机维修
					return "ok_computerrepair";
				} else if (event.getEvent_type() == 6) {// 计算机重装系统
					return "ok_reinstallsystem";
				} else if (event.getEvent_type() == 7) {// 计算机退网
					return "ok_quitinternet";
				} else {
					return SUCCESS;
				}

			}
		}
	}
}