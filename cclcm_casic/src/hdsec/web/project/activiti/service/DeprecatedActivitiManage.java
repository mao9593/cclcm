package hdsec.web.project.activiti.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

public class DeprecatedActivitiManage {
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private TaskService taskService;
	
	public Map<String, Object> getProcessVariables(String instance_id) {
		return runtimeService.getVariables(instance_id);
	}
	
	public ProcessInstance startProcess(Map<String, Object> variables) {
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("approvalRequest", variables);
		return processInstance;
	}
	
	public List<String> getInnerCandidateProcessInstancceIdListByUserId(String user_iidd) {
		List<String> list = new ArrayList<String>();
		List<Task> taskList = taskService.createTaskQuery().taskCandidateUser(user_iidd).taskName("innerApprove")
				.list();
		for (Task item : taskList) {
			list.add(item.getProcessInstanceId());
		}
		return list;
	}
	
	public List<String> getSpecialCandidateProcessInstancceIdListByUserId(String user_iidd) {
		List<String> list = new ArrayList<String>();
		List<Task> taskList = taskService.createTaskQuery().taskCandidateUser(user_iidd).taskName("specialApprove")
				.list();
		for (Task item : taskList) {
			list.add(item.getProcessInstanceId());
		}
		return list;
	}
	
	public List<String> getInnerAssignedProcessInstancceIdListByUserId(String user_iidd) {
		List<String> list = new ArrayList<String>();
		List<Task> taskList = taskService.createTaskQuery().taskAssignee(user_iidd).taskName("innerApprove").list();
		for (Task item : taskList) {
			list.add(item.getProcessInstanceId());
		}
		return list;
	}
	
	public List<String> getSpecialAssignedProcessInstancceIdListByUserId(String user_iidd) {
		List<String> list = new ArrayList<String>();
		List<Task> taskList = taskService.createTaskQuery().taskAssignee(user_iidd).taskName("specialApprove").list();
		for (Task item : taskList) {
			list.add(item.getProcessInstanceId());
		}
		return list;
	}
	
	public void claimTask(String instance_id, String user_iidd) throws Exception {
		List<Task> list = taskService.createTaskQuery().processInstanceId(instance_id).taskCandidateUser(user_iidd)
				.list();
		if (list == null || list.size() != 1) {
			throw new Exception("任务已被认领，请刷新任务列表确认");
		} else {
			taskService.claim(list.get(0).getId(), user_iidd);
		}
	}
	
	public void approveInnerTask(String instance_id, String user_iidd, String approved, String opinion,
			String innerApprover) {
		List<Task> list = taskService.createTaskQuery().processInstanceId(instance_id).taskAssignee(user_iidd).list();
		Map<String, Object> variables = getProcessVariables(instance_id);
		variables.put("innerApproved", approved);
		variables.put("opinion", opinion);
		variables.put("innerApprover", innerApprover);
		taskService.complete(list.get(0).getId(), variables);
	}
	
	public void approveSpecialTask(String instance_id, String user_iidd, String approved, String opinion,
			String specialApprover) {
		List<Task> list = taskService.createTaskQuery().processInstanceId(instance_id).taskAssignee(user_iidd).list();
		Map<String, Object> variables = getProcessVariables(instance_id);
		variables.put("specialApproved", approved);
		variables.put("opinion", opinion);
		variables.put("specialApprover", specialApprover);
		taskService.complete(list.get(0).getId(), variables);
	}
	
	public void endTask(String instance_id, String user_iidd) throws Exception {
		List<Task> list = taskService.createTaskQuery().processInstanceId(instance_id).taskAssignee(user_iidd)
				.taskName("decideUserReApply").list();
		if (list == null || list.size() != 1) {
			throw new Exception("找不到要结束的任务，请重试。");
		} else {
			Map<String, Object> variables = runtimeService.getVariables(instance_id);
			variables.put("userReApply", "false");
			taskService.complete(list.get(0).getId(), variables);
		}
	}
	
	public String getOpinionByInstanceId(String instance_id) {
		return (String) runtimeService.getVariables(instance_id).get("opinion");
	}
	
	private void resendConfirm(String instance_id, String user_iidd) throws Exception {
		List<Task> list = taskService.createTaskQuery().processInstanceId(instance_id).taskAssignee(user_iidd)
				.taskName("decideUserReApply").list();
		if (list == null || list.size() != 1) {
			throw new Exception("找不到要重提的任务，请重试。");
		} else {
			Map<String, Object> variables = runtimeService.getVariables(instance_id);
			variables.put("userReApply", "true");
			taskService.complete(list.get(0).getId(), variables);
		}
	}
	
	public void resendTask(String instance_id, String user_iidd, String innerApprover, String specialApprover,
			String detail) throws Exception {
		resendConfirm(instance_id, user_iidd);
		List<Task> list = taskService.createTaskQuery().processInstanceId(instance_id).taskAssignee(user_iidd)
				.taskName("userApply").list();
		if (list == null || list.size() != 1) {
			throw new Exception("找不到要重提的任务，请重试。");
		} else {
			Map<String, Object> variables = runtimeService.getVariables(instance_id);
			variables.put("innerApprover", innerApprover);
			variables.put("specialApprover", specialApprover);
			taskService.complete(list.get(0).getId(), variables);
		}
	}
	
	public void submitApply(String instance_id, String user_iidd) throws Exception {
		List<Task> list = taskService.createTaskQuery().processInstanceId(instance_id).taskAssignee(user_iidd)
				.taskName("userApply").list();
		if (list == null || list.size() != 1) {
			throw new Exception("无法开启新流程");
		} else {
			taskService.complete(list.get(0).getId());
		}
	}
	
}
