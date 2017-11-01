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
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.util.StringUtils;

public class ApproveOpenPortJobAction extends InfosystemBaseAction {

	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	// private List<ChangeDeviceEvent> eventList = null;
	private ChangeDeviceEvent event = null;
	private String approved = "";
	private String opinion = "";
	private String next_approver = "";
	private List<ApproverUser> userList = null;
	// private String jobType;
	private EntityComputer computer = null;
	// private String apply_type_name = "";
	// private String power_type_name = "";
	private Integer apply_type = null;
	private Integer power_type = null;
	private String output_port = "";
	private String input_port = "";
	private String start_time = "";
	private String end_time = "";
	// private String printer_model = "";
	// private String printer_conf_code = "";
	// private String printer_seclv_name = "";

	private String history = "";
	private Integer listSize = null;
	private String opinion_all = "";
	private String event_code = "";
	private String current_step = "";
	private String opinion_history = "";

	// private String old_content = "";
	// private String new_content = "";
	// private String user_id = "";
	// private String key_time = "";
	// private Date start_t = null;
	// private Date end_t = null;
	// private String deal_with = "";
	// String key_code = "";
	// private String computer_ip = "";
	// private String switch_num = "";
	// private String switch_point = "";
	// private String software_type = "";// 软件安装
	// private String software_summ = "";
	// private String mark_code = "";
	// private String vlan_num = "";
	// private String computer_gateway = "";
	// private String operation_user_id = "";
	// private Date operation_time = null;
	// private String operation_content = "";
	// private String software_typename = "";
	// private String isrecycle = "";
	// private String content = "";
	private String output_chk = "";

	public String getJob_code() {
		return job_code;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public ProcessJob getJob() {
		return job;
	}

	public void setJob(ProcessJob job) {
		this.job = job;
	}

	public ApproveProcess getProcess() {
		return process;
	}

	public void setProcess(ApproveProcess process) {
		this.process = process;
	}

	public List<ProcessRecord> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<ProcessRecord> recordList) {
		this.recordList = recordList;
	}

	public ChangeDeviceEvent getEvent() {
		return event;
	}

	public void setEvent(ChangeDeviceEvent event) {
		this.event = event;
	}

	public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getNext_approver() {
		return next_approver;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public List<ApproverUser> getUserList() {
		return userList;
	}

	public void setUserList(List<ApproverUser> userList) {
		this.userList = userList;
	}

	public EntityComputer getComputer() {
		return computer;
	}

	public void setComputer(EntityComputer computer) {
		this.computer = computer;
	}

	public Integer getApply_type() {
		return apply_type;
	}

	public void setApply_type(Integer apply_type) {
		this.apply_type = apply_type;
	}

	public Integer getPower_type() {
		return power_type;
	}

	public void setPower_type(Integer power_type) {
		this.power_type = power_type;
	}

	public String getOutput_port() {
		return output_port;
	}

	public void setOutput_port(String output_port) {
		this.output_port = output_port;
	}

	public String getInput_port() {
		return input_port;
	}

	public void setInput_port(String input_port) {
		this.input_port = input_port;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public Integer getListSize() {
		return listSize;
	}

	public void setListSize(Integer listSize) {
		this.listSize = listSize;
	}

	public String getOpinion_all() {
		return opinion_all;
	}

	public void setOpinion_all(String opinion_all) {
		this.opinion_all = opinion_all;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getCurrent_step() {
		return current_step;
	}

	public void setCurrent_step(String current_step) {
		this.current_step = current_step;
	}

	public String getOpinion_history() {
		return opinion_history;
	}

	public void setOpinion_history(String opinion_history) {
		this.opinion_history = opinion_history;
	}

	public String getOutput_chk() {
		return output_chk;
	}

	public void setOutput_chk(String output_chk) {
		this.output_chk = output_chk;
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

	private void getHandle2(Integer history_step) {
		int i = 0;
		for (i = 1; i <= history_step; i++) {
			if (recordList.get(i).getOpinion().contains("请审批")) {
				opinion_history = opinion_history + Constants.COMMON_SEPARATOR + recordList.get(i).getOpinion()
						+ "<br>申请人：" + recordList.get(i).getUser_name() + "<br>重新申请时间："
						+ recordList.get(i).getOp_time_str();
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
			if (recordList.get(i).getOpinion().contains("computeropen")) {// 计算机开通端口20160107
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
		if (event.getEvent_type() == 9) {
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
			// 开通端口
			return "ok_openport";

		} else {// 审批

			if (StringUtils.hasLength(approved)) {// 记录审批结果
				event = computerService.getChangeDeviceEventByJobCode(job_code);
				String next_approver_name = basicService.getApproverName(next_approver);
				ApproverUser user = new ApproverUser(getCurUser().getUser_iidd(), getCurUser().getUser_name());
				ApproverUser approver = new ApproverUser(next_approver, next_approver_name);

				if (event.getEvent_type() == 9) {// 开通计算机端口
					if (listSize == 5) {
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

							// 开通端口
							return "ok_openport";

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
				// 开通端口
				return "ok_openport";

			}
		}
	}
}