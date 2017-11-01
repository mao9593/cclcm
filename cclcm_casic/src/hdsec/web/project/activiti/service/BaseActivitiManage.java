package hdsec.web.project.activiti.service;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.util.ActivitiCons;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecDept;
import hdsec.web.project.user.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

public class BaseActivitiManage {
	protected Logger logger = Logger.getLogger(this.getClass());
	@Resource
	protected ActivitiService activitiService;
	@Resource
	protected UserService userService;
	@Resource
	protected RuntimeService runtimeService;
	@Resource
	protected TaskService taskService;
	@Resource
	protected HistoryService historyService;

	/**
	 * 添加一个申请，开启一个新的流程实例
	 * 
	 * @param job
	 * @param process
	 * @throws Exception
	 */
	public void addActivitiApply(ProcessJob job, ApproveProcess process) throws Exception {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("proposer", job.getUser_iidd());
		variables.put("job_code", job.getJob_code());
		variables.put("jobType_code", job.getJobType().getJobTypeCode());
		variables.put("total_approval", process.getTotal_steps());
		variables.put("cur_approval", 1);
		variables.put("next_approver", job.getNext_approver());
		variables.put("process", process);
		ProcessInstance instance = runtimeService.startProcessInstanceByKey("approvalRequest", variables);
		job.setInstance_id(instance.getProcessInstanceId());
		List<Task> list = taskService.createTaskQuery().processInstanceId(instance.getProcessInstanceId())
				.taskAssignee(job.getUser_iidd()).taskName("userApply").list();
		if ((list == null) || (list.size() != 1)) {
			logger.error("任务列表为空，无法开启新流程");
			throw new Exception("无法开启新流程");
		} else {
			taskService.complete(list.get(0).getId());
		}
	}

	private String extendStepDept(String dept_cs, int minLength) throws Exception {
		SecDept dept = userService.getSecDeptByDeptCs(dept_cs);
		String result = dept.getDept_id();
		while (dept_cs.length() > minLength) {
			// dept_id = dept_id.substring(0, dept_id.length() - 2);
			dept = userService.getSecDeptById(dept.getDept_parent_id());
			result += "," + dept.getDept_id();
			dept_cs = dept.getDept_cs();
		}
		return result;
	}

	private String extendStepRole(String role_id, int dValue) {
		String result = role_id;
		int num = dValue / 2;
		for (int i = 0; i < num; i++) {
			result += "," + role_id;
		}
		return result;
	}

	/*
	 * public ApproveProcess getApproveProcessByIdAndDept(String process_id, String dept_id) throws Exception {
	 * ApproveProcess process = activitiService.getProcessById(process_id); if (process == null) {
	 * logger.error("无法查询流程定义信息，该流程可能已经被删除。"); throw new Exception("无法查询流程定义信息，该流程可能已经被删除。"); } if
	 * (process.getDept_id().equalsIgnoreCase("default")) { process.setDept_id(dept_id); } handleProcess(process);
	 * return process; }
	 */

	/**
	 * 处理机构代码的转义，和指定级别的流程步骤的展开
	 * 
	 * @param process
	 * @throws Exception
	 */
	private void handleProcess(ApproveProcess process) throws Exception {
		String dept_id = process.getDept_id();
		String step_dept = process.getStep_dept();
		String step_role = process.getStep_role();

		// 如果流程步骤中含有指定最高审批级别的步骤，则需要转换为正常的步骤
		if (StringUtils.hasLength(step_dept) && step_dept.indexOf("L:") != -1) {
			String[] depts = step_dept.split("#");
			String[] roles = step_role.split("#");
			for (int i = 0; i < depts.length; i++) {
				if (depts[i].startsWith("L:")) {
					Integer minLength = Integer.parseInt(depts[i].substring(2).trim()) * 2;
					String dept_cs = userService.getSecDeptById(dept_id).getDept_cs();
					if (dept_cs.length() < minLength) {
						String dept_name = userService.getDeptNameByDeptId(dept_id);
						logger.error("流程适用的部门[" + dept_name + "]级别已高于审批步骤中设定的最高审批级别，请联系管理员");
						throw new Exception("流程适用的部门[" + dept_name + "]级别已高于审批步骤中设定的最高审批级别，请联系管理员");
					} else {
						depts[i] = extendStepDept(dept_cs, minLength);
						roles[i] = extendStepRole(roles[i], dept_cs.length() - minLength);
					}
				}
			}
			String d_tmp = "";
			String r_tmp = "";
			for (String item : depts) {
				d_tmp += item + ",";
			}
			for (String item : roles) {
				r_tmp += item + ",";
			}
			d_tmp = d_tmp.substring(0, d_tmp.length() - 1);
			r_tmp = r_tmp.substring(0, r_tmp.length() - 1);
			process.setStep_dept(d_tmp.replaceAll(" ", "").replaceAll(",", "#"));
			process.setStep_role(r_tmp.replaceAll(" ", "").replaceAll(",", "#"));
			process.setTotal_steps(d_tmp.split(",").length);
		}
		// 展示流程信息
		String step_dept_name = "";
		String step_role_name = "";
		if (StringUtils.hasLength(step_dept)) {
			String[] depts = process.getStep_dept().split("#");
			String[] roles = process.getStep_role().split("#");
			for (int i = 0; i < depts.length; ++i) {
				String dept_tmp = "";
				String role_tmp = "";
				for (String dept_item : depts[i].split("@")) {
					if (dept_item.trim().equals("self"))
						dept_tmp = dept_tmp + "本部门" + ",";
					else if (dept_item.trim().equals("super"))
						dept_tmp = dept_tmp + "上级部门" + ",";
					else {
						dept_tmp = dept_tmp + userService.getDeptNameByDeptId(dept_item) + ",";
					}
				}
				for (String role_id : roles[i].split("@")) {
					role_tmp = role_tmp + userService.getRoleNameByRoleId(role_id) + ",";
				}

				step_dept_name = step_dept_name + dept_tmp.substring(0, dept_tmp.length() - 1) + "#";
				step_role_name = step_role_name + role_tmp.substring(0, role_tmp.length() - 1) + "#";
			}
			if (step_dept_name.length() > 0) {
				step_dept_name = step_dept_name.substring(0, step_dept_name.length() - 1);
				step_role_name = step_role_name.substring(0, step_role_name.length() - 1);
			}
			process.setStep_dept_name(step_dept_name);
			process.setStep_role_name(step_role_name);
		}
	}

	/**
	 * 通过部门、密级、操作、用途四个要素查询流程定义，并且把机构的特殊代码进行转义（特殊代码包括default/self/super）
	 * 
	 * @param dept_id
	 * @param seclv_code
	 * @param jobType_code
	 * @param usage_code
	 * @param inherit
	 *            标志是否递归查询，即当前部门没有查询到流程定义的话，是否递归查询其父部门的流程定义
	 * @return
	 * @throws Exception
	 */
	public ApproveProcess getApproveProcessByKey(String dept_id, String seclv_code, String jobType_code,
			String usage_code, Boolean inherit) throws Exception {
		ApproveProcess process = null;
		process = getDBApproveProcess(dept_id, seclv_code, jobType_code, usage_code, inherit);
		if (process == null) {
			process = getDBApproveProcess("default", seclv_code, jobType_code, usage_code, false);
		}
		if (process == null) {
			logger.error("该用户的部门及上级部门在该密级和动作上没有定义流程，请先联系系统管理员创建流程。");
			throw new Exception("该用户的部门及上级部门在该密级和动作上没有定义流程，请先联系系统管理员创建流程。");
		}
		if (process.getDept_id().equalsIgnoreCase("default")) {
			process.setDept_id(dept_id);
		}
		handleProcess(process);
		return process;
	}

	/**
	 * 根据流程实例ID查询流程定义 2014-4-22 下午11:58:35
	 * 
	 * @author renmingfei
	 * @param instance_id
	 * @return
	 * @throws Exception
	 */
	public ApproveProcess getApproveProcessByInstanceId(String instance_id) throws Exception {
		logger.info("getApproveProcessByInstanceId:" + instance_id);
		Map<String, Object> map = getProcessVariables(instance_id);
		if (map != null) {
			return (ApproveProcess) map.get("process");
		} else {
			Object process = getHisProcessVariable(instance_id, "process");
			if (process != null) {
				return (ApproveProcess) process;
			} else {
				return null;
			}
		}
	}

	/**
	 * 直接从数据库中根据部门、密级、操作、用途查询审批流程定义
	 * 
	 * @param dept_id
	 * @param seclv_code
	 * @param jobType_code
	 * @param usage_code
	 * @param inherit
	 * @return
	 * @throws Exception
	 */
	private ApproveProcess getDBApproveProcess(String dept_id, String seclv_code, String jobType_code,
			String usage_code, Boolean inherit) throws Exception {
		// 查询指定部门是否定义了流程
		ApproveProcess process = null;
		process = activitiService.getProcessByKey(dept_id, seclv_code, jobType_code, usage_code);
		if (process != null) {
			process.setDept_id(dept_id);
			return process;
		}
		if (!dept_id.equals("01") && !dept_id.equals("default") && inherit) {
			// 如果该部门没有设置流程,并且继承查询为true,则查看它的上级部门的流程
			String parent_dept = userService.getSecDeptById(dept_id).getDept_parent_id();
			return getDBApproveProcess(parent_dept, seclv_code, jobType_code, usage_code, inherit);
		}
		return null;
	}

	/**
	 * 根据流程ID查询流程变量
	 * 
	 * @param instance_id
	 * @return
	 */
	public Map<String, Object> getProcessVariables(String instance_id) {
		System.out.println("In getProcessVariables:" + instance_id);
		try {
			return runtimeService.getVariables(instance_id);
		} catch (ActivitiObjectNotFoundException e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	/**
	 * 从已经完成的流程实例中查询指定名称的流程变量值 2014-4-23 上午11:17:07
	 * 
	 * @author renmingfei
	 * @param instance_id
	 * @param variableName
	 * @return
	 */
	public Object getHisProcessVariable(String instance_id, String variableName) {
		List<HistoricVariableInstance> list = historyService.createHistoricVariableInstanceQuery()
				.processInstanceId(instance_id).variableName(variableName).list();
		if (list != null && list.size() > 0) {
			return list.get(0).getValue();
		}
		return null;
	}

	/**
	 * 根据流程ID查询历史流程变量
	 * 
	 * @param instance_id
	 * @return
	 */
	public String getHisJobCodeByInstanceId(String instance_id) {
		return (String) historyService.createHistoricVariableInstanceQuery().processInstanceId(instance_id)
				.variableName("job_code").list().get(0).getValue();
	}

	/**
	 * 根据流程定义和当前审批等级获得下级审批人
	 * 
	 * @param process
	 * @param cur_approval
	 * @return
	 * @throws Exception
	 */
	public List<ApproverUser> getNextApprover(ApproveProcess process, Integer cur_approval) throws Exception {
		if (process.getTotal_steps() >= cur_approval) {
			String[] depts = process.getStep_dept().split("#");
			String[] roles = process.getStep_role().split("#");
			String dept_ids = depts[cur_approval - 1];
			String role_ids = roles[cur_approval - 1];
			List<ApproverUser> approvers = new ArrayList<ApproverUser>();
			// 角色为多个时，以@分隔
			for (String role_id : role_ids.split("@")) {
				// 审批部门为多个时，以@分隔
				for (String dept_id : dept_ids.split("@")) {
					if (dept_id.trim().equals("self")) {
						dept_id = process.getDept_id();
					} else if (dept_id.trim().equals("super")) {
						dept_id = userService.getSecDeptById(process.getDept_id()).getDept_parent_id();
					}
					Map<String, String> map = new HashMap<String, String>();
					map.put("dept_id", dept_id);
					map.put("role_id", role_id);
					approvers.addAll(activitiService.getApproversByDeptRole(map));
				}
			}
			if ((approvers != null) && (approvers.size() > 0)) {
				return approvers;
			} else {
				String dept_name = "";
				String role_name = "";
				for (String dept_id : dept_ids.split("@")) {
					dept_name += userService.getDeptNameByDeptId(dept_id) + ",";
				}
				if (dept_name.endsWith(",")) {
					dept_name = dept_name.substring(0, dept_name.length() - 1);
				}
				for (String role_id : role_ids.split("@")) {
					role_name += userService.getRoleNameByRoleId(role_id) + ",";
				}
				if (role_name.endsWith(",")) {
					role_name = role_name.substring(0, role_name.length() - 1);
				}
				throw new Exception("WARN:部门[" + dept_name + "]内不存在拥有角色[" + role_name + "]的用户，没有符合条件的审批人，请联系管理员。");
			}
		} else {
			return new ArrayList<ApproverUser>();
		}

	}

	/**
	 * 根据流程定义和指定的审批等级查询指定等级审批人
	 * 
	 * @param process
	 * @param cur_approval
	 * @return
	 * @throws Exception
	 */
	public List<ApproverUser> getSpecificApprover(String dept_id, String seclv_code, JobTypeEnum jobType,
			String usage_code, Integer spec_approval) throws Exception {
		// ApproveProcess process = getApproveProcessByIdAndDept(process_id, dept_id);
		ApproveProcess process = getApproveProcessByKey(dept_id, seclv_code, jobType.getJobTypeCode(), usage_code, true);
		String[] depts = process.getStep_dept().split("#");
		String[] roles = process.getStep_role().split("#");
		String dept_ids = depts[spec_approval - 1];
		String role_ids = roles[spec_approval - 1];
		List<ApproverUser> approvers = new ArrayList<ApproverUser>();
		// 角色为多个时，以@分隔
		for (String role_id : role_ids.split("@")) {
			// 审批部门为多个时，以@分隔
			for (String dept_item : dept_ids.split("@")) {
				if (dept_item.trim().equals("self")) {
					dept_item = process.getDept_id();
				} else if (dept_item.trim().equals("super")) {
					dept_item = process.getDept_id().substring(0, process.getDept_id().length() - 2);
				}
				Map<String, String> map = new HashMap<String, String>();
				map.put("dept_id", dept_item);
				map.put("role_id", role_id);
				approvers.addAll(activitiService.getApproversByDeptRole(map));
			}
		}
		if ((approvers != null) && (approvers.size() > 0)) {
			return approvers;
		} else {
			String dept_name = "";
			String role_name = "";
			for (String dept_item : dept_ids.split("@")) {
				dept_name += userService.getDeptNameByDeptId(dept_item) + ",";
			}
			if (dept_name.endsWith(",")) {
				dept_name = dept_name.substring(0, dept_name.length() - 1);
			}
			for (String role_id : role_ids.split("@")) {
				role_name += userService.getRoleNameByRoleId(role_id) + ",";
			}
			if (role_name.endsWith(",")) {
				role_name = role_name.substring(0, role_name.length() - 1);
			}
			throw new Exception("WARN:部门[" + dept_name + "]内不存在拥有角色[" + role_name + "]的用户，没有符合条件的审批人，请联系管理员。");
		}

	}

	/**
	 * 获得审批候选人的流程ID列表
	 * 
	 * @param user_iidd
	 * @param jobType_code
	 * @return
	 */
	public List<String> getCandidateInstanceIdList(String user_iidd, String jobType_code) {
		List<String> list = new ArrayList<String>();
		List<Task> taskList = taskService.createTaskQuery().taskDescription(jobType_code).taskCandidateUser(user_iidd)
				.taskName("approve").active().list();
		for (Task item : taskList) {
			list.add(item.getProcessInstanceId());
		}
		return list;
	}

	/**
	 * 获得审批人的流程ID列表
	 * 
	 * @param user_iidd
	 * @param jobType_code
	 * @return
	 */
	public List<String> getAssignedInstanceIdList(String user_iidd, String jobType_code) {
		List<String> list = new ArrayList<String>();
		List<Task> taskList = taskService.createTaskQuery().taskDescription(jobType_code).taskAssignee(user_iidd)
				.taskName("approve").active().list();
		for (Task item : taskList) {
			list.add(item.getProcessInstanceId());
		}
		return list;
	}

	/**
	 * 根据流程ID和业务类型领取审批任务
	 * 
	 * @param job
	 * @param user_iidd
	 * @throws Exception
	 */
	public void claimtask(ProcessJob job, String user_iidd) throws Exception {
		List<Task> list = taskService.createTaskQuery().processInstanceId(job.getInstance_id())
				.taskCandidateUser(user_iidd).list();
		if ((list == null) || (list.size() != 1)) {
			logger.error("任务已被申请人撤销或者已被其他领导审批！");
			throw new Exception("任务已被申请人撤销或者已被其他领导审批！");
		} else {
			taskService.claim(list.get(0).getId(), user_iidd);
			job.setNext_approver(user_iidd);
			job.setNext_approver_name(userService.getUserNameByUserId(user_iidd));
			job.setJob_status(ActivitiCons.APPLY_APPROVED_UNDER);
		}
	}

	/**
	 * 审批导出申请
	 * 
	 * @param instanceId
	 * @param assignee
	 * @param next_approver
	 * @param approved
	 * @throws Exception
	 */
	public void approveApply(String instanceId, String assignee, String next_approver, String approved)
			throws Exception {
		Map<String, Object> variables = getProcessVariables(instanceId);
		Integer cur_approval = (Integer) variables.get("cur_approval") + 1;
		List<Task> list = taskService.createTaskQuery().processInstanceId(instanceId).taskAssignee(assignee).list();
		// 如果审批结果为同意
		if (approved.equals(ActivitiCons.APPLY_APPROVED_PASS)) {
			variables.put("approved", approved);
			variables.put("cur_approval", cur_approval);
			variables.put("next_approver", next_approver);
		} else if (approved.equals(ActivitiCons.APPLY_APPROVED_REJECT)) {
			variables.put("approved", approved);
		}
		taskService.complete(list.get(0).getId(), variables);
	}

	/**
	 * 确认申请被驳回
	 * 
	 * @param job
	 * @throws Exception
	 */
	public void confirmReject(ProcessJob job) throws Exception {
		List<Task> list = taskService.createTaskQuery().processInstanceId(job.getInstance_id())
				.taskAssignee(job.getUser_iidd()).taskName("decideUserReApply").list();
		if ((list == null) || (list.size() != 1)) {
			logger.error("找不到要结束的任务，请重试。");
			throw new Exception("找不到要结束的任务，请重试。");
		} else {
			Map<String, Object> variables = runtimeService.getVariables(job.getInstance_id());
			variables.put("userReApply", "false");
			taskService.complete(list.get(0).getId(), variables);
			job.setJob_status(ActivitiCons.APPLY_APPROVED_END);
		}
	}

	/**
	 * 修改重提申请
	 * 
	 * @param job
	 * @throws Exception
	 */
	public void resubmitApply(ProcessJob job) throws Exception {
		List<Task> list = taskService.createTaskQuery().processInstanceId(job.getInstance_id())
				.taskAssignee(job.getUser_iidd()).taskName("decideUserReApply").list();
		if ((list == null) || (list.size() != 1)) {
			logger.error("找不到要重提的任务，请重试。");
			throw new Exception("找不到要重提的任务，请重试。");
		} else {
			Map<String, Object> variables = runtimeService.getVariables(job.getInstance_id());
			variables.put("cur_approval", 1);
			variables.put("next_approver", job.getNext_approver());
			variables.put("userReApply", "true");
			taskService.complete(list.get(0).getId(), variables);
			job.setJob_status(ActivitiCons.APPLY_APPROVED_DEFAULT);
		}
	}

	/**
	 * 查询已经审批过的任务列表
	 * 
	 * @param user_iidd
	 * @param jobType_code
	 * @return
	 */
	public List<String> getApprovedInstanceIdList(String user_iidd, String jobType_code) {
		List<String> list = new ArrayList<String>();
		List<HistoricTaskInstance> taskList = historyService.createHistoricTaskInstanceQuery()
				.taskDescription(jobType_code).taskAssignee(user_iidd).taskName("approve").finished()
				.orderByHistoricTaskInstanceEndTime().desc().list();
		for (HistoricTaskInstance item : taskList) {
			list.add(item.getProcessInstanceId());
		}
		return list;
	}
	
	
	/**
	 * 查询已经审批过的任务列表
	 * 
	 * @param user_iidd
	 * @param jobType_code
	 * @return
	 */
	public List<HistoricTaskInstance> getApprovedInstanceIdList(String user_iidd) {
		
		return historyService.createHistoricTaskInstanceQuery()
				.taskAssignee(user_iidd).taskName("approve").finished().orderByHistoricTaskInstanceStartTime().desc().list();
				//.orderByHistoricTaskInstanceEndTime().desc().list();
		
	}

	/**
	 * 申请人领取任务根据流程ID领取任务
	 * 
	 * @param job
	 * @param user_iidd
	 * @throws Exception
	 */
	public void applyuserclaimtask(ProcessJob job, String user_iidd) throws Exception {
		List<Task> list = taskService.createTaskQuery().processInstanceId(job.getInstance_id()).taskAssignee(user_iidd)
				.list();
		if ((list == null) || (list.size() != 1)) {
			logger.error("任务不存在！");
			throw new Exception("任务不存在！");
		} else {
			taskService.claim(list.get(0).getId(), user_iidd);
			// job.setNext_approver(user_iidd);
			// job.setNext_approver_name(userService.getUserNameByUserId(user_iidd));
			// job.setJob_status(ActivitiCons.APPLY_APPROVED_DEFAULT);// 待审批
		}
	}
	/**
	 * 通过审批人ID查询审批人姓名（多个审批人ID，用逗号分隔）
	 * 
	 * @param approver
	 * @return
	 */
	public String getApproverName(String approver) {
		String approver_name = "";
		if (!StringUtils.hasLength(approver)) {
			return "";
		} else {
			for (String one : approver.split(",")) {
				if (!one.trim().isEmpty()) {
					approver_name += userService.getUserNameByUserId(one) + ",";
				}
			}
		}
		if (approver_name.endsWith(",")) {
			approver_name = approver_name.substring(0, approver_name.length() - 1);
		}
		return approver_name;
	}

	/**
	 * 通过流程实例ID挂起一个流程实例
	 * 
	 * @param processInstanceId
	 */
	public void suspendProcessInstanceById(String processInstanceId) {
		if (taskService.createTaskQuery().processInstanceId(processInstanceId).active().list().size() > 0) {
			runtimeService.suspendProcessInstanceById(processInstanceId);
		}
	}

	/**
	 * 更改流程实例的审批人
	 * 
	 * @param instanceId
	 * @param next_approver
	 * @throws Exception
	 */
	public void setProcessInstanceApprover(String instanceId, String next_approver) throws Exception {
		Map<String, Object> variables = getProcessVariables(instanceId);
		int cur_approval = (Integer) variables.get("cur_approval");
		String next_approver_old = (String) variables.get("next_approver");
		if (cur_approval > 1) {
			throw new Exception("该申请的流程实例已经被审批过，不能进行修改");
		}
		// 查询当前审批任务ID
		List<Task> list = taskService.createTaskQuery().processInstanceId(instanceId).taskName("approve").active()
				.list();
		String taskId = list.get(0).getId();
		// 去掉原来的审批人
		for (String candiItem : next_approver_old.split(",")) {
			taskService.deleteCandidateUser(taskId, candiItem.trim());
		}
		// 添加新的审批人
		for (String candiItem : next_approver.split(",")) {
			taskService.addCandidateUser(taskId, candiItem.trim());
		}
		String executionId = runtimeService.createExecutionQuery().processInstanceId(instanceId).list().get(0).getId();
		runtimeService.setVariable(executionId, "next_approver", next_approver);
	}
}
