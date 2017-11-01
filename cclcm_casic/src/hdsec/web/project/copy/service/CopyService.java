package hdsec.web.project.copy.service;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.copy.model.CopyEvent;
import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.transfer.model.EventTransfer;

import java.util.List;
import java.util.Map;

public interface CopyService {

	/**
	 * 复印作业管理
	 */
	// 添加作业
	void addCopyEvent(CopyEvent event);

	// 查看作业列表
	List<CopyEvent> getCopyEventList(Map<String, Object> map);

	// 通过流水号查找作业
	CopyEvent getCopyEventByCopyCode(String event_code);

	// 通过流水号删除作业
	void delCopyEventByCopyCode(String event_code);

	// 通过作业流水号查询任务流水号
	String getJobCodeByEventCode(String event_code);

	// 修改作业信息
	void updateCopyEvent(CopyEvent event);

	// 添加纸质台帐
	void addPaperledger(EntityPaper paper);

	// 更新作业是否已打印条码状态
	void updateIsbarcode(String event_code, Integer is_barcode);

	// 整合复印操作，更新状态、入台帐、生命周期记录、生成流转作业
	void addCopyPaperledger(Map<String, Object> map, EntityPaper paper, CycleItem cycleitem, EventTransfer transfer);

	/**
	 * 添加复印任务并生成复印作业
	 * 
	 * 2014-5-14 上午3:45:41
	 * 
	 * @author lixiang
	 * @param user_iidd
	 * @param dept_id
	 * @param seclv_code
	 * @param cycle_type
	 * @param jobType
	 * @param next_approver
	 * @param output_dept_name
	 * @param output_user_name
	 * @param comment
	 * @param barcodeList
	 * @param usage_code
	 * @param project_code
	 * @param copyNumList
	 * @throws Exception
	 */
	void addProcessJob(String user_iidd, String dept_id, Integer seclv_code, String cycle_type, JobTypeEnum jobType,
			String next_approver, String output_dept_name, String output_user_name, String comment,
			List<String> barcodeList, String usage_code, String project_code, Map<String, String> copyNumList,
			String period, String copy_merge) throws Exception;

	void addMergeProcessJob(String user_iidd, String dept_id, Integer seclv_code, String cycle_type,
			JobTypeEnum jobType, String next_approver, String output_dept_name, String output_user_name,
			String comment, List<String> barcodeList, String usage_code, String project_code, Integer copy_num,
			String period, String copy_merge) throws Exception;

	/**
	 * 通过任务号查找复印作业列表
	 * 
	 * 2014-5-14 上午8:08:41
	 * 
	 * @author lixiang
	 * @param job_code
	 * @return
	 */
	List<CopyEvent> getCopyEventListByJobCode(String job_code);

	/**
	 * 删除复印作业申请
	 * 
	 * 2014-5-14 上午8:33:19
	 * 
	 * @author lixiang
	 * @param event_code
	 * @return
	 */
	int cancelCopyEventByEventCode(String event_code);

	/**
	 * 添加外来文件复印申请
	 * 
	 * @param event
	 */
	void addCopyEventByEnter(CopyEvent event);

	/**
	 * 添加合并复印申请
	 * 
	 * @param event
	 */
	void addCopyEventByMerge(CopyEvent event);

	/**
	 * 添加外来文件复印作业
	 * 
	 * @param eventIdList
	 * @param user_iidd
	 * @param dept_id
	 * @param seclv_code
	 * @param cycle_type
	 * @param jobType
	 * @param next_approver
	 * @param output_dept_name
	 * @param output_user_name
	 * @param comment
	 * @param usage_code
	 */
	void addProcessJobByEnter(List<String> eventIdList, String user_iidd, String dept_id, Integer seclv_code,
			String cycle_type, JobTypeEnum jobType, String next_approver, String output_dept_name,
			String output_user_name, String comment, String usage_code) throws Exception;

	/**
	 * 撤销外来复印作业审批申请
	 * 
	 * @param event_code
	 * @return
	 */
	int cancelEnterCopyEventByEventCode(String event_code);

	/**
	 * 根据打印作业号删除复印作业， 2017.6.7打印副业合并流程添加
	 * 
	 * @param event_code
	 * @return
	 */
	void cancelCopyEventByPrintEventCode(String event_code);

}
