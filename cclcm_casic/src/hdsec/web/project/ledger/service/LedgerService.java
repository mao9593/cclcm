﻿package hdsec.web.project.ledger.service;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.basic.model.RejectRecord;
import hdsec.web.project.burn.model.BurnEvent;
import hdsec.web.project.device.model.EntityDevice;
import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.ledger.model.EventCarryOut;
import hdsec.web.project.ledger.model.EventCarryOutProcessJob;
import hdsec.web.project.ledger.model.EventLog;
import hdsec.web.project.ledger.model.EventModify;
import hdsec.web.project.ledger.model.EventOut;
import hdsec.web.project.ledger.model.EventTemp;
import hdsec.web.project.ledger.model.PaperType;
import hdsec.web.project.ledger.model.SendDestroyEvent;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecDept;
import hdsec.web.project.user.model.SecUser;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

public interface LedgerService {

	static final String DISK = "CD";
	static final String PAPER = "Paper";

	List<EntityCD> getAllCDLedgerList(Map<String, Object> map, RowBounds rbs) throws Exception;

	List<EntityCD> getAllCDLedgerList(Map<String, Object> map) throws Exception;

	EntityCD getOneCDLedgerById(int cd_id);

	void updateRetrieveCDs(Map<String, Object> map);

	void destroyRetrievedCDs(Map<String, Object> map);

	void updateHandleCDs(Map<String, Object> map);

	void signCD(String cd_id, String file_num, String file_list, String fail_remark);

	EntityPaper getOnePaperLedgerById(String paper_id);

	void updateRetrievePapers(Map<String, Object> map);

	void destroyRetrievedPapers(Map<String, Object> map);

	void updateHandlePapers(Map<String, Object> map);

	int getAllCDLedgerSize(Map<String, Object> map) throws Exception;

	List<EntityPaper> getAllPaperLedgerList(Map<String, Object> map, RowBounds rbs) throws Exception;

	List<EntityPaper> getAllPaperLedgerList(Map<String, Object> map) throws Exception;

	int getAllPaperLedgerSize(Map<String, Object> map) throws Exception;

	// void saveEventTranfer(EventTransfer tranfer);

	// List<EventTransfer> getTransferEventList(Map<String, Object> map);

	List<EntityPaper> getPaperListByJobCode(String job_code);

	void cancelHandlePaperById(String paper_id);

	EntityPaper getPaperByBarcode(String barcode);

	List<CycleItem> getCycleItemListByBarcode(String barcode);

	// EventTransfer getOneTransferEventByCode(String event_code);

	String getDeptIdByUserId(String accept_user_iidd);

	void deleteEventTransfer(String event_code, String barcode);

	void updateEventTransfer(Map<String, Object> map);

	// EventTransfer getTransferEventByTransferId(String id);

	// EventTransfer getTransferEventByTransferCode(String event_code);

	String getJobCodeByEventCode(String event_code);

	// void cancelTransferEventByEventCode(String event_code);

	int getAllDeviceLedgerSize(Map<String, Object> map) throws Exception;

	List<EntityDevice> getAllDeviceLedgerList(Map<String, Object> map, RowBounds rbs) throws Exception;

	List<EntityDevice> getAllDeviceLedgerList(Map<String, Object> map) throws Exception;

	void cancelHandleCDById(String cd_id);

	List<EntityCD> getCDListByJobCode(String job_code);

	void addCycleItem(CycleItem cycleitem);

	List<EntityDevice> getDeviceListByJobCode(String job_code);

	void signPaper(String paper_id, String fail_remark, Integer page_count);

	/**
	 * 通过条码查询光盘信息 2014-4-18 下午3:55:24
	 * 
	 * @author renmingfei
	 * @param barcode
	 * @return
	 */
	EntityCD getCDByBarcode(String barcode);

	/**
	 * 
	 * 确认外发光盘，生成两条生命周期记录并修改光盘状态
	 * 
	 * @param cd_barcode
	 * @param user
	 * @param comment
	 * @param output_confidential_num
	 */
	void confirmSendCD(String cd_barcode, SecUser user, String comment, String output_confidential_num,String update_user_name, String update_dept_name);

	/**
	 * 
	 * 确认外发纸质载体，生成两条生命周期记录并修改纸质载体状态
	 * 
	 * @param paper_barcode
	 * @param user
	 * @param comment
	 * @param output_confidential_num
	 */
	void confirmSendPaper(String paper_barcode, SecUser user, String comment, String output_confidential_num, String update_user_name, String update_dept_name);

	/**
	 * 
	 * 拒收外发光盘处理
	 */
	void rejectSendCD(RejectRecord record, Map<String, Object> map);

	/**
	 * 
	 * 拒收外发纸质载体处理
	 */
	void rejectSendPaper(RejectRecord record, Map<String, Object> map);

	/**
	 * 根据部门管理员ID查询管理的部门 2014-4-29 上午3:40:20
	 * 
	 * @author gaoximin
	 * @param job_code
	 * @return
	 */
	List<SecDept> getDeptListByDeptAdminUserId(String deptadmin_user_iidd);

	/**
	 * 用ID查询Paper载体
	 * 
	 * @author yy
	 * @param id
	 */
	EntityPaper getPaperById(String id);

	/**
	 * 通过barcode查询磁介质
	 * 
	 * 2014-5-19 上午2:27:30
	 * 
	 * @author lixiang
	 * @param barcode
	 * @return
	 */
	EntityDevice getDeviceByBarcode(String barcode);

	/**
	 * 用ID查询CD载体
	 * 
	 * @author lixiang
	 * @param id
	 */
	EntityCD getCDById(String id);

	/**
	 * 检测该密级文件是否有权限预览
	 * 
	 * @param seclv_code
	 * @return
	 */
	Boolean checkShowPrintFile(String seclv_code);

	/**
	 * 检测该密级光盘是否有权限预览
	 * 
	 * @param seclv_code
	 * @return
	 */
	Boolean checkShowBurnFile(String seclv_code);

	/**
	 * 回收纸质：更新状态、回收人，全生命周期记录，交接确认
	 * 
	 * @param id
	 * @param user
	 * @throws Exception
	 */
	void submitRetrievePaper(String id, SecUser user) throws Exception;

	/**
	 * 回收光盘：更新状态、回收人，全生命周期记录，交接确认
	 * 
	 * @param id
	 * @param user
	 * @throws Exception
	 */
	void submitRetrieveCD(String id, SecUser user) throws Exception;

	/**
	 * 退回文件回收申请
	 * 
	 * @param paper_id
	 */
	void quitHandlePaperById(String paper_id, SecUser user);

	/**
	 * 退回光盘回收申请
	 * 
	 * @param cd_id
	 */
	void quitHandleCDById(String cd_id, SecUser user);

	/**
	 * 外发拒收paper，拒收后状态为已回收
	 * 
	 * @param record
	 * @param map
	 * @param user
	 */
	void rejectSendPaperToRetrieved(RejectRecord record, Map<String, Object> map, SecUser user);

	/**
	 * 外发拒收cd，拒收后状态为已回收
	 * 
	 * @param record
	 * @param map
	 * @param user
	 */
	void rejectSendCDToRetrieved(RejectRecord record, Map<String, Object> map, SecUser user);

	/**
	 * 根据文件条码更新文件外发确认备注信息
	 * 
	 * @param paper_barcode
	 */
	void updatePaperSendCommentByBarcode(Map<String, Object> map);

	/**
	 * 根据光盘条码更新光盘外发确认备注信息
	 * 
	 * @param cd_barcode
	 */
	void updateCDSendCommentByBarcode(Map<String, Object> map);

	/**
	 * 根据条码号查询载体拒收记录
	 * 
	 * @param entity_barcode
	 * @return
	 */
	List<RejectRecord> getRejectRecordByBarcode(String entity_barcode);

	/**
	 * 撤销光盘闭环申请
	 * 
	 * @param cd_id
	 */
	void giveUpHandleCDById(String cd_id);

	/**
	 * 撤销文件闭环申请
	 * 
	 * @param paper_id
	 */
	void giveUpHandlePaperById(String paper_id);

	/**
	 * 根据父节点获取所有子节点
	 */
	List<String> getDeptIdByParentId(String dept_parent_id);

	List<BurnEvent> getBurnEventByCdBarcode(String cd_barcode);

	void addEntityCD(EntityCD entityCd);

	Integer getSeclv_code(String seclv_name);

	String getUser_iidd(String user_name);

	String getDept_id(String dept_name);

	/**
	 * 修改回收文件的备注
	 * 
	 * @param id
	 * @param comment
	 */
	void updateRetrieveComment(String id, String comment);

	/**
	 * 修改退回文件的备注
	 * 
	 * @param id
	 * @param comment
	 */
	void updateRejectPaperComment(String id, String comment);

	void setPaperRemark(String paper_barcode, String send_id, String send_mode, String box_num, String file_order_num,
			String manage_opinion, String receive_id, String remark);

	/**
	 * 查询文件外发申请列表
	 * 
	 * @param map
	 * @return
	 */
	List<EntityPaper> getSendPaperList(Map<String, Object> map, RowBounds rbs);

	/**
	 * 查询光盘外发申请列表
	 * 
	 * @param map
	 * @return
	 */
	List<EntityCD> getSendCDList(Map<String, Object> map, RowBounds rbs);

	/**
	 * 查询文件归档申请列表
	 * 
	 * @param map
	 * @return
	 */
	List<EntityPaper> getFilePaperList(Map<String, Object> map, RowBounds rbs);

	/**
	 * 查询光盘归档申请列表
	 * 
	 * @param map
	 * @return
	 */
	List<EntityCD> getFileCDList(Map<String, Object> map, RowBounds rbs);

	/**
	 * 查询光盘销毁申请列表
	 * 
	 * @param map
	 * @return
	 */
	List<EntityCD> getDestroyCDList(Map<String, Object> map, RowBounds rbs);

	/**
	 * 查询文件延期留用申请列表
	 * 
	 * @param map
	 * @return
	 */
	List<EntityPaper> getDelayPaperList(Map<String, Object> map, RowBounds rbs);

	/**
	 * 查询光盘延期留用申请列表
	 * 
	 * @param map
	 * @return
	 */
	List<EntityCD> getDelayCDList(Map<String, Object> map, RowBounds rbs);

	/**
	 * 查询文件销毁申请列表
	 * 
	 * @param map
	 * @return
	 */
	List<EntityPaper> getDestroyPaperList(Map<String, Object> map, RowBounds rbs);

	/**
	 * 文件送销任务列表
	 * 
	 * @param map
	 * @return
	 */
	List<ProcessJob> getSendDestroyPaperJobList(Map<String, Object> map);

	/**
	 * 某一任务下的送销作业列表
	 * 
	 * @param job_code
	 * @return
	 */
	List<SendDestroyEvent> getSendDestroyEventListByJobCode(String job_code);

	/**
	 * 某一送销任务下的文件载体列表
	 * 
	 * @param job_code
	 * @return
	 */
	List<EntityPaper> getSendDestroyPaperListByJobCode(String job_code);

	/**
	 * 取消送销任务申请
	 * 
	 * @param job_code
	 */
	void cancelSendDestroyEventByJobCode(String job_code, String type);

	/**
	 * 光盘送销任务列表
	 * 
	 * @param map
	 * @return
	 */
	List<ProcessJob> getSendDestroyCDJobList(Map<String, Object> map);

	/**
	 * 某一送销任务下的光盘载体列表
	 * 
	 * @param job_code
	 * @return
	 */
	List<EntityCD> getSendDestroyCDListByJobCode(String job_code);

	/**
	 * 修改纸质延期留用期限
	 * 
	 * @param map
	 */
	void updateDelayDays(Map<String, Object> map);

	/**
	 * 修改光盘延期留用期限
	 * 
	 * @param map
	 */
	void updateCDDelayDays(Map<String, Object> map);

	/**
	 * 查出纸张类型
	 * 
	 * @return
	 */
	List<PaperType> getPaperConversionRate();

	/**
	 * 删除纸张类型
	 * 
	 * @param type_name
	 */
	void deletePaperConversionRateByTypeName(String type_name);

	/**
	 * 更新纸张折合率
	 * 
	 * @param type_name
	 * @param conversion_rate
	 */
	void updatePaperConversionRateByTypeName(String type_name, double conversion_rate);

	/**
	 * 添加纸张折合率
	 * 
	 * @param type_name
	 * @param conversion_rate
	 */
	void addPaperConversionRateByTypeName(String type_name, double conversion_rate);

	/**
	 * 添加或更新备注
	 * 
	 * @param barcode
	 * @param comment
	 */
	void addOrUpdateFailRemarkByBarcode(String barcode, String comment);

	/**
	 * 
	 * 更新备注和销毁时间
	 * 
	 * @param barcode
	 * @param comment
	 */
	void addOrUpdateFailRemarkByBarcodeAndTime(String barcode, String comment);

	/**
	 * 获得替换页列表
	 * 
	 * @param map
	 * @param rbs
	 * @return
	 */
	List<EntityPaper> getAllReplacePaperList(Map<String, Object> map, RowBounds rbs);

	/**
	 * 获得替换页的数量
	 * 
	 * @param map
	 * @return
	 */
	int getAllReplacePaperSize(Map<String, Object> map);

	/**
	 * 修改替换页中已回收的页
	 * 
	 * @param map
	 */
	void updateRetrievedPage(Map<String, Object> map);

	/**
	 * 获得文件主台账条码信息
	 * 
	 * @return PID_barcode
	 */
	List<String> getPIDBarcode();

	/**
	 * 根据PID_barcode获得paper_barcode
	 * 
	 * @param barcode
	 * @return paper_barcode
	 */
	List<String> getPaperBarcode(String barcode);

	/**
	 * 修改替换页中已销毁的页
	 * 
	 * @param map
	 */
	void updateDestroyedPage(Map<String, Object> map);

	/**
	 * 密级变更流程
	 */
	void addProcessJob(String user_iidd, String dept_id, Integer seclv_code, String event_ids, String entity_type,
			Integer modify_status, Integer trg_seclv, String usage_code, String project_code, String summ,
			Map<String, String> getFileTitleList, String next_approver,String file_titles,String page_counts) throws Exception;

	/**
	 * 
	 * 添加密级变更
	 * 
	 * @param event
	 */
	void addEventModify(EventModify event);

	/**
	 * 根据用户ID查询候选数据交换审批任务列表
	 * 
	 * @param user_iidd
	 * @return
	 */
	List<ProcessJob> getModifyCandidateListByUserId(String user_iidd);

	/**
	 * 查询密级变更作业列表
	 * 
	 * @param job_code
	 * @return
	 */
	List<EventModify> getModifyEventListByJobCode(String job_code);

	/**
	 * 查询密级变更作业列表
	 * 
	 * @param job_code
	 * @return
	 */
	List<EventModify> getModifyEventListByBarcode(String barcode);

	/**
	 * 通过密级变更作业号查找密级变更作业
	 * 
	 * @param event_code
	 * @return
	 */
	EventModify getModifyEventByCode(String event_code);

	/**
	 * 通过密级变更流水号查找密级变更作业
	 * 
	 * @param event_code
	 * @return
	 */
	List<EventModify> getModifyEventByJobCode(String job_code);

	/**
	 * 通过作业流水号查询任务流水号
	 * 
	 * @param event_code
	 * @return
	 */
	String getModifyJobCodeByEventCode(String event_code);

	/**
	 * 确认变更密级
	 * 
	 * 
	 * 
	 * 
	 */
	void updateConfirmModifyEvent(Map<String, Object> map);

	/**
	 * 查询密级变更列表
	 * 
	 * @param map
	 * @return
	 */
	List<EventModify> getModifyEventList(Map<String, Object> map);

	/**
	 * 提交密级变更时，更改状态
	 * 
	 * @param map
	 */
	void updatePaperState(Map<String, Object> map);

	/**
	 * 确认密级后，更改entity表的信息
	 * 
	 * @param map
	 */
	void confirmEntityPaper(Map<String, Object> map);

	/**
	 * 通过event_code查询记录数量
	 * 
	 * @param event_code
	 * @return
	 */
	int getCountModifyEvent();

	/**
	 * 查询event_modify中纸质的size
	 */
	int getAllEventModifySize(Map<String, Object> map) throws Exception;

	/**
	 * 查询event_modify中纸质所有记录
	 */
	List<EventModify> getAllEventModifyList(Map<String, Object> map, RowBounds rbs) throws Exception;

	/**
	 * 通过seclv_code查询获得name
	 * 
	 * @param seclv_code
	 * @return
	 */
	String getSeclvNameByCode(int seclv_code);

	/**
	 * 取消纸质变更记录
	 * 
	 * @param event_code
	 */
	void cancelHandleLedgerById(String event_code);

	/**
	 * 取消光盘变更记录
	 * 
	 * @param event_code
	 */
	void cancelHandleCDLedgerById(String event_code);

	/**
	 * 
	 * 
	 * @param map
	 */
	void updateCDState(Map<String, Object> map);

	/**
	 * 确认密级后，更改entity表的信息
	 * 
	 * @param map
	 */
	void confirmEntityCD(Map<String, Object> map);

	/**
	 * 通过类型查询event_modify记录
	 */
	List<EventModify> getEventModifyByEntityType(String entity_type);

	/**
	 * 查询event_modify中光盘变更的size
	 */
	int getAllEventCDModifySize(Map<String, Object> map) throws Exception;

	/**
	 * 查询event_modify中所有记录
	 */
	List<EventModify> getAllEventCDModifyList(Map<String, Object> map, RowBounds rbs) throws Exception;

	/**
	 * 根据entity_cd的条码更新流程号
	 * 
	 * @param barCode
	 */
	void updateCDJobCodeByBarCode(Map<String, Object> map);

	/**
	 * 根据entity_paper的条码更新流程号
	 * 
	 * @param barCode
	 */
	void updatePaperJobCodeByBarCode(Map<String, Object> map);

	/**
	 * 增加外带文件
	 * 
	 * @param map
	 */

	void addEventCarryOutPaper(EventCarryOut eventCarryOut);

	void addPaperCarryOutProcessJob(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType,
			String next_approver, Date startTime, Date endTime, String carryoutInfo, String[] barcodes,
			String usage_code, String send_way, String carryout_user_iidd, String carryout_dept_id) throws Exception;

	void addCDCarryOutProcessJob(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType,
			String next_approver, Date startTime, Date endTime, String carryoutInfo, String[] barcodes,
			String usage_code, String send_way, String carryout_user_iidd, String carryout_dept_id) throws Exception;

	List<EventCarryOut> getEventCarryOutListByJobCode(String job_code);

	List<EventCarryOutProcessJob> getHandledCarryOutJobByUserId(String user_iidd, String type, String user_name,
			Integer seclv_code, String jobType_code);

	/**
	 * 查询外带记录
	 * 
	 * @param map
	 * @return
	 */
	List<ProcessJob> getCarryOutJobList(Map<String, Object> map, RowBounds rbs);

	/**
	 * 查看通过审批未带回的载体
	 * 
	 * @param map
	 * @return
	 */
	List<EventOut> getCarryOutConfirmEventList(Map<String, Object> map);

	void updateEventCarryOutCarryoutStatusById(Map<String, Object> map);

	/**
	 * 
	 * 根据主键查询
	 * 
	 * @param id
	 * @return
	 */
	EventCarryOut getEventCarryOutById(Integer id);

	/**
	 * 查看外带详情
	 * 
	 * @param id
	 * @return
	 */
	EventOut getEventOutById(Integer id);

	List<Integer> getSysSecLevelN(Map<String, Object> map);

	/**
	 * 检查是否带回
	 * 
	 * @param map
	 * @return
	 */
	int checkOrCarryIn(Map<String, Object> map);

	/**
	 * 检索需要回收的密级
	 * 
	 * @return
	 */
	List<Integer> getRecycleSeclCode();

	/**
	 * 检索回收台账
	 * 
	 * @return
	 */
	List<EntityPaper> getAllPaperRecycleLedgerList(Map<String, Object> map, RowBounds rbs) throws Exception;

	/**
	 * 检索回收数量
	 * 
	 * @return
	 */
	int getAllPaperRecycleLedgerSize(Map<String, Object> map) throws Exception;

	List<EntityPaper> getAllPaperRecycleLedgerList(Map<String, Object> map) throws Exception;

	/**
	 * 补打交接单
	 * 
	 * @param map
	 */
	void reprintsendconfirm(Map<String, Object> map);

	void reprintsendconfirmcd(Map<String, Object> map);

	int getAllSendPaperSize(Map<String, Object> map);

	int getDestroyPaperSize(Map<String, Object> map);

	int getFilePaperSize(Map<String, Object> map);

	int getDelayPaperSize(Map<String, Object> map);

	int getCarryOutJobSize(Map<String, Object> map);

	int getDestroyCDSize(Map<String, Object> map);

	int getSendCDSize(Map<String, Object> map);

	int getFileCDSize(Map<String, Object> map);

	int getDelayCDSize(Map<String, Object> map);

	/**
	 * 办理外发事项
	 * 
	 * @param map
	 */
	void confirmSendCDMatter(Map<String, Object> map);

	void confirmSendPaperMatter(Map<String, Object> map);

	void updatePaperIsBook(Map<String, Object> map);

	void ExpectsignPaperSuccess(Map<String, Object> map);

	void ExpectsignPaperfail(Map<String, Object> map);

	List<EntityPaper> getReplacePaperByPaperBarcode(String paper_barcode);

	void updateRetrievedPageNew(Map<String, Object> map);

	void updateRetrievedPageNewStatus(Map<String, Object> map);

	void updateDestroyedPageNew(Map<String, Object> map);

	void updateDestroyedPageNewStatus(Map<String, Object> map);

	List<EventLog> getEventLogAll(Map<String, Object> map);

	void confimModify(String job_code);

	int getModifyPaperSize(Map<String, Object> map);

	List<EntityPaper> getModifyPaperList(Map<String, Object> map, RowBounds rbs);

	/**
	 * 文件错误台账删除更新状态与结果
	 * 
	 * @author guojiao
	 * */
	void updatePaperDelInfo(EntityPaper paper, ApproverUser user, String job_code);

	void updatePaperModifyInfo(EntityPaper paper, ApproverUser user, String job_code);

	void addTempEvent(String user_iidd, String dept_id, Integer seclv_code, String event_ids, JobTypeEnum jobtype,
			String entity_type, String usage_code, String project_code, String summ, String next_approver,
			String scope_dept_id, String scope_dept_name) throws Exception;

	List<EventTemp> getTempEventList(Map<String, Object> map);

	List<EventTemp> getTempEventListByJobCode(String job_code);

	void cancelHandleTempById(String event_code);

	public List<ProcessJob> getTempJobList(Map<String, Object> map);

	void updateSupervisePaperStateByBarcode(Map<String, Object> map);

	int getSelfDestroyPaperSize(Map<String, Object> map);

	List<EntityPaper> getSelfDestroyPaperList(Map<String, Object> map, RowBounds rbs);

	// 光盘自主销毁
	void updateSuperviseCDStateByBarcode(Map<String, Object> map);

	int getSelfDestroyCDSize(Map<String, Object> map);

	List<EntityCD> getSelfDestroyCDList(Map<String, Object> map, RowBounds rbs);

	/**
	 * 获取个人录入磁介质台账
	 * 
	 * @author haojia
	 * */

	List<EntityDevice> getPersonalDeviceLedgerList(Map<String, Object> map, RowBounds rbs) throws Exception;

	int getPersonalDeviceLedgerSize(Map<String, Object> map) throws Exception;

	/**
	 * 根据条码列表查询纸质台帐列表
	 * 
	 * @param _chk
	 * @return
	 */
	List<EntityDevice> getDeviceListByBarcodes(String barcodes);

	// 个人磁介质自主销毁
	void updateSuperviseDeviceStateByBarcode(Map<String, Object> map);

	int getSelfDestroyDeviceSize(Map<String, Object> map);

	List<EntityDevice> getSelfDestroyDevcieList(Map<String, Object> map, RowBounds rbs);

	/**
	 * 撤销磁介质闭环申请
	 * 
	 * @param device_barcode
	 */
	void giveUpHandleDeviceByBarcode(String device_barcode);

	/**
	 * 查询文件总台账，不与其他查询一起，单独查询
	 * 
	 * @param map
	 * @param rbs
	 * @return
	 * @throws Exception
	 */
	List<EntityPaper> getSecAllPaperLedgerList(Map<String, Object> map, RowBounds rbs) throws Exception;

	/**
	 * 查询文件总台账总数，不与其他查询一起，单独查询
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int getSecAllPaperLedgerSize(Map<String, Object> map) throws Exception;

	void addEntityPaper(EntityPaper entityPaper);

	/**
	 * 查询个人委托打印台账：个数与列表
	 */
	List<EntityPaper> getProxyPaperLedgerList(Map<String, Object> map, RowBounds rbs) throws Exception;

	int getProxyPaperLedgerSize(Map<String, Object> map) throws Exception;

	/**
	 * 查询个人委托刻录台账：个数与列表
	 */
	int getProxyCDLedgerSize(Map<String, Object> map) throws Exception;

	List<EntityCD> getProxyCDLedgerList(Map<String, Object> map, RowBounds rbs) throws Exception;

	/**
	 * 查询光盘总台账，不与其他查询一起，单独查询
	 * 
	 * @param map
	 * @param rbs
	 * @return
	 * @throws Exception
	 */
	List<EntityCD> getSecAllCDLedgerList(Map<String, Object> map, RowBounds rbs) throws Exception;

	/**
	 * 查询光盘总台账总数，不与其他查询一起，单独查询
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int getSecAllCDLedgerSize(Map<String, Object> map) throws Exception;

	void updatePaperOutputConfidentialNumByEventCarryoutId(Map<String, Object> map);

	void updateCDOutputConfidentialNumByEventCarryoutId(Map<String, Object> map);

	void addMergeEntityPaper(String user_iidd, String dept_id, Integer seclv_code, ProcessJob job, String barcodes)
			throws Exception;

	List<EntityPaper> getMergePaperList(Map<String, Object> map);

	int getMergePaperSize(Map<String, Object> map);

	List<EntityPaper> getMergePaperApplyList(Map<String, Object> map, RowBounds rbs);

	int getMergePaperApplySize(Map<String, Object> map);

	void updateMergeCodeState(String paper_barcode);

	List<EntityPaper> getMergeAllPaperList(Map<String, Object> map, RowBounds rbs) throws Exception;

	List<RejectRecord> getAllRejectPaperList(Map<String, Object> map,
			RowBounds rbs) throws Exception;

	int getAllRejectPaperSize(Map<String, Object> map) throws Exception;

	List<EntityCD> getAllRejectCDList(Map<String, Object> map, RowBounds rbs) throws Exception;

	int getAllRejectCDSize(Map<String, Object> map) throws Exception;


}