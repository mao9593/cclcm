package hdsec.web.project.change.service;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.change.model.EventChange;

import java.util.List;
import java.util.Map;

public interface ChangeService {
	/**
	 * 添加转换作业
	 * 
	 * @param user_iidd
	 * @param dept_id
	 * @param seclv_code
	 * @param jobType
	 * @param next_approver
	 * @param comment
	 * @param barcodeList
	 * @param usage_code
	 * @param entity_type
	 * @param scope_dept_id
	 * @param change_type
	 */
	void addProcessJob(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType, String next_approver,
			String comment, List<String> barcodeList, String usage_code, String entity_type, String scope_dept_id,
			String change_type, String accept_user_iidd) throws Exception;

	/**
	 * 载体归属转换任务列表
	 * 
	 * @param map
	 * @return
	 */
	List<ProcessJob> getJobList(Map<String, Object> map);

	/**
	 * 查询某一任务下的所有作业
	 * 
	 * @param job_code
	 * @return
	 */
	List<EventChange> getChangeEventListByJobCode(String job_code);

	/**
	 * 通过作业号查询载体归属转换作业
	 * 
	 * @param event_code
	 * @return
	 */
	EventChange getChangeEventByCode(String event_code);

	/**
	 * 通过载体归属转换作业号查询其任务号
	 * 
	 * @param event_code
	 * @return
	 */
	String getJobCodeByEventCode(String event_code);

	/**
	 * 在任务详情里取消载体归属转换作业
	 * 
	 * @param event_code
	 * @return
	 */
	int cancelChangeEventByCode(String event_code);

	/**
	 * 载体归属转换作业列表
	 * 
	 * @param map
	 * @return
	 */
	List<EventChange> getChangeEventList(Map<String, Object> map);

	/**
	 * 变更载体归属
	 * 
	 * @param event_code
	 */

	void changeEntityScope(String event_code, String duty_user_iidd, String duty_user_name, String duty_dept_id,
			String duty_dept_name);

	ProcessJob getProcessJobByJobCode(String job_code);

	void refuseChangeEntityScope(String event_code, String barcode);
}
