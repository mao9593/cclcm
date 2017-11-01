package hdsec.web.project.burn.service;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.burn.model.BurnEvent;
import hdsec.web.project.burn.model.RiskKeywordsBurn;

import java.util.List;
import java.util.Map;

public interface BurnService {
	/**
	 * 添加刻录作业
	 * 
	 * @param event
	 * @param next_approver
	 * @param output_dept_name
	 * @param output_user_name
	 * @param comment
	 * @param output_undertaker
	 * @param carryout_user_iidds
	 * @param carryout_user_names
	 * @param send_way
	 * @throws Exception
	 */
	void addBurnEvent(BurnEvent event, String next_approver, String output_dept_name, String output_user_name,
			String comment, String output_undertaker, String carryout_user_iidds, String carryout_user_names,
			String send_way) throws Exception;

	/**
	 * 查看刻录作业列表
	 * 
	 * @param map
	 * @return
	 */
	List<BurnEvent> getBurnEventList(Map<String, Object> map);

	/**
	 * 通过刻录流水号查找刻录作业
	 * 
	 * @param event_code
	 * @return
	 */
	BurnEvent getBurnEventByBurnCode(String event_code);

	/**
	 * 通过刻录流水号删除刻录作业
	 * 
	 * @param event_code
	 */
	void delBurnEventByBurnCode(String event_code);

	/**
	 * 通过作业流水号查询任务流水号
	 * 
	 * @param event_code
	 * @return
	 */
	String getJobCodeByEventCode(String event_code);

	/**
	 * 修改刻录作业信息
	 * 
	 * @param event
	 */
	void updateBurnEvent(BurnEvent event);

	/**
	 * 修改刻录作业中关于刻录文件的信息
	 * 
	 * @param event_code
	 * @param fileNum
	 * @param newFileList
	 * @param newFileSec
	 */
	void updateBurnEventFileInfo(String event_code, int fileNum, String newFileList, String newFileSec);

	/**
	 * 查询刻录作业列表 2014-5-15 上午10:28:26
	 * 
	 * @author renmingfei
	 * @param job_code
	 * @return
	 */
	List<BurnEvent> getBurnEventListByJobCode(String job_code);

	/**
	 * 添加刻录申请 2014-5-15 上午11:04:04
	 * 
	 * @author renmingfei
	 * @param user_iidd
	 * @param dept_id
	 * @param seclv_code
	 * @param cycle_type
	 * @param jobType
	 * @param next_approver
	 * @param output_dept_name
	 * @param output_user_name
	 * @param comment
	 * @param stringArrayToList
	 * @param usage_code
	 * @param project_code
	 * @throws Exception
	 */
	void addProcessJob(String user_iidd, String dept_id, Integer seclv_code, String cycle_type, JobTypeEnum jobType,
			String next_approver, String output_dept_name, String output_user_name, String comment,
			List<String> eventCodeList, String usage_code, String project_code) throws Exception;

	void updateBurnEventWithoutUsage(BurnEvent event);

	void updateBurnProxyUseridByEventCode(Map<String, Object> map);

	void AddKeywordBurnEvent(Map<String, Object> map);

	void updateKeywordBurnEvent(Map<String, Object> map);

	void updateKeywordBurnCallresultByTid(Map<String, Object> map);

	void addRisklistBurn(RiskKeywordsBurn temp);

	// 根据文件名称/用户名ID/event_code获取刻录transid
	String getBurnTransID(Map<String, Object> map);

	// 根据transid获取刻录文件检索结果列表
	List<RiskKeywordsBurn> getRisklistBurn(Map<String, Object> map);

	/**
	 * 将本次成功查询是否命中的结果(命中Hit,未命中NoHit)更新至数据库表keyword_burn的字段CHECK_RESULT中 2017-4-28
	 * 
	 * @param tid
	 * @param check_result
	 */
	void updateKeywordBurnCheckresultByTid(String tid, String check_result);
}
