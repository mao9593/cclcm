package hdsec.web.project.enter.service;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.device.model.EntityDevice;
import hdsec.web.project.enter.model.EnterEvent;
import hdsec.web.project.enter.model.EnterProcessJob;
import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.ledger.model.ReprintCD;
import hdsec.web.project.transfer.model.EventTransfer;

import java.util.List;
import java.util.Map;

public interface EnterService {
	/**
	 * 添加录入作业
	 * 
	 * @param event
	 */
	void addEnterEvent(EnterEvent event);

	/**
	 * 查看录入作业列表
	 * 
	 * @param map
	 */
	List<EnterEvent> getEnterEventList(Map<String, Object> map);

	/**
	 * 查看个人录入作业列表
	 * 
	 * @param map
	 * @return
	 */
	List<EnterEvent> getSelfEnterEventList(Map<String, Object> map);

	/**
	 * 通过作业编号查找作业
	 * 
	 * @param event_code
	 */
	EnterEvent getEnterEventByEnterCode(String event_code);

	/**
	 * 通过作业编号删除作业
	 * 
	 * @param event_code
	 */
	void delEnterEventByEnterCode(String event_code);

	/**
	 * 通过作业编号查找任务号
	 * 
	 * @param event_code
	 */
	String getJobCodeByEventCode(String event_code);

	/**
	 * 修改录入作业信息
	 * 
	 * @param event
	 */
	void updateEnterEvent(EnterEvent event);

	/**
	 * 介质录入，更新录入申请表，入台帐、流转作业、生命周期记录
	 * 
	 * @param event
	 */
	void updateEnterEventState(Map<String, Object> map);

	/**
	 * 添加纸质台帐
	 * 
	 * @param event
	 */
	void addPaperledger(EntityPaper paper);

	/**
	 * 添加光盘台帐
	 * 
	 * @param event
	 */
	void addCDledger(EntityCD cd);

	/**
	 * 整合录入操作，更新状态、入台帐、生命周期记录、流转作业 2014-4-17 上午1:50:37
	 * 
	 * @author gaoximin
	 */
	void querryEnterOpers(String medium, Map<String, Object> map, EntityPaper paper, EntityCD cd, CycleItem cycleitem,
			EventTransfer transfer, String scope);

	/**
	 * 预录入条码时，查询数据库中是否已存在该条码 2014-5-7 上午3:15:12
	 * 
	 * @author gaoximin
	 * @param map
	 * @return
	 */
	int getEnterBarcodeSize(String barcode);

	/**
	 * 添加录入审批流程 2014-5-14 上午12:48:48
	 * 
	 * @author gaoximin
	 * @param user_iidd
	 * @param dept_id
	 * @param seclv_code
	 * @param jobType
	 * @param next_approver
	 * @param output_dept_name
	 * @param output_user_name
	 * @param comment
	 * @param event_codes
	 * @param usage_code
	 * @param project_code
	 * @throws Exception
	 */
	void addProcessJob(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType, String next_approver,
			String output_dept_name, String output_user_name, String comment, List<String> eventIdList,
			String usage_code, String project_code) throws Exception;

	/**
	 * 通过任务编号查询录入作业列表 2014-5-15 上午8:37:36
	 * 
	 * @author gaoximin
	 * @param job_code
	 * @return
	 */
	List<EnterEvent> getEnterEventListByJobCode(String job_code);

	/**
	 * 根据录入申请编号取消作业 2014-5-15 上午8:52:38
	 * 
	 * @author gaoximin
	 * @param event_code
	 * @return
	 */
	int cancelEnterEventByEventCode(String event_code);

	/**
	 * 检测预发条码是否存在
	 * 
	 * @param file_type
	 * @param barcode
	 * @return
	 */
	boolean checkBarcodeIsUsed(Integer file_type, String barcode);

	/**
	 * 检测预发条码是否存在或被其他event使用
	 * 
	 * @param file_type
	 * @param barcode
	 * @param event_code
	 * @return
	 */
	boolean checkBarcodeIsUsedByOthers(Integer file_type, String barcode, String event_code);

	/**
	 * 添加扫描流程，与录入流程不同，审批消息、提示等相同
	 * 
	 * @param user_iidd
	 * @param dept_id
	 * @param seclv_code
	 * @param jobType
	 * @param next_approver
	 * @param output_dept_name
	 * @param output_user_name
	 * @param comment
	 * @param eventIdList
	 * @param usage_code
	 * @param project_code
	 * @throws Exception
	 */
	void addScanProcessJob(String user_iidd, String dept_id, Integer seclv_code, String next_approver,
			String output_dept_name, String output_user_name, String comment, List<String> eventIdList,
			String usage_code, String project_code) throws Exception;

	/**
	 * 根据用户ID查询候选录入、扫描录入审批任务列表
	 * 
	 * @param user_iidd
	 * @param jobType
	 * @return
	 */
	List<EnterProcessJob> getLeadinCandidateListByUserId(String user_iidd);

	/**
	 * 查询用户自己的录入、扫描录入审批历史记录
	 * 
	 * @param user_iidd
	 * @param jobType
	 * @param user_name
	 * @param seclv_code
	 * @return
	 */
	List<EnterProcessJob> getleadinApprovedJobByUserId(String user_iidd, String user_name, Integer seclv_code);

	/**
	 * 批量删除录入作业
	 * 
	 * @param id
	 */
	void deleteEnterJob(String id);

	/**
	 * 通过barcode查找出台账
	 * 
	 * @param cd_barcode
	 * @return
	 */
	ReprintCD getEnterEventJoinImportByCDBarcode(String cd_barcode);

	ReprintCD getEnterEventJoinBurnByCDBarcode(String cd_barcode);

	ReprintCD getEnterEventCDBarcode(String cd_barcode);

	void updateSrccodeByBarcode(String src_code, String barcode, String type);

	void addEntityDevice(EntityDevice device, CycleItem cycleitem, Map<String, Object> map);

	/**
	 * 通过medium_serial查找录入申请记录
	 * 
	 * @param medium_serial
	 * @return
	 */
	public EnterEvent getEnterEventByMediumSerial(String medium_serial);

	/**
	 * 查询已录入的event的数目
	 * 
	 * @param job_code
	 * @return
	 */
	int getEnterEventEnterCancel(String job_code);

	void updateConfidentialnumByBarcode(String src_code, String barcode, String type);
}
