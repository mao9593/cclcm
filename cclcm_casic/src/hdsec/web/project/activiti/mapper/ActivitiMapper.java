package hdsec.web.project.activiti.mapper;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.user.model.ApproverUser;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface ActivitiMapper {

	/**
	 * 流程管理
	 * 
	 * @param process
	 */
	void addProcess(ApproveProcess process);

	void delProcessByProcessId(String process_id);

	String getProcessNameById(String process_id);

	void updateProcess(ApproveProcess process);

	ApproveProcess getProcessById(String process_id);

	List<ApproveProcess> getApproveProcessList(Map<String, Object> map);

	List<ApproverUser> getApproversByDeptRole(Map<String, String> map);

	ApproveProcess getProcessByKey(Map<String, Object> map);

	void addProcessRecord(ProcessRecord record);

	List<ProcessRecord> getProcessRecordList(ProcessRecord record);

	ApproveProcess getProcessByUsageKey(Map<String, Object> map);

}
