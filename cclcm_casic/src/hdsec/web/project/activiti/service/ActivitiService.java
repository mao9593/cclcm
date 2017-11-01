package hdsec.web.project.activiti.service;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.user.model.ApproverUser;

import java.util.List;
import java.util.Map;

public interface ActivitiService {

	/**
	 * 流程管理
	 * 
	 * @param process
	 */
	void addProcess(ApproveProcess process);

	String getProcessNameById(String process_id);

	void delProcessByProcessId(String process_id);

	void updateProcess(ApproveProcess process);

	ApproveProcess getProcessById(String process_id);

	List<ApproveProcess> getApproveProcessList(Map<String, Object> map);

	/**
	 * 通过部门和角色查询审批用户
	 */
	List<ApproverUser> getApproversByDeptRole(Map<String, String> map);

	ApproveProcess getProcessByKey(String dept_id, String seclv_code, String jobType_code, String usage_code);

	void addProcessRecord(ProcessRecord record);

	List<ProcessRecord> getProcessRecordList(ProcessRecord record);

}
