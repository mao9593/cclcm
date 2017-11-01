package hdsec.web.project.ledger.mapper;

import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.basic.model.RejectRecord;
import hdsec.web.project.burn.model.BurnEvent;
import hdsec.web.project.device.model.EntityDevice;
import hdsec.web.project.ledger.model.BarcodeCompare;
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
import hdsec.web.project.user.model.SecDept;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

public interface LedgerMapper {

	List<EntityCD> getAllCDLedgerList(Map<String, Object> map);

	EntityCD getOneCDLedgerById(int cd_id);

	void updateRetrieveCDs(Map<String, Object> map);

	void destroyRetrievedCDs(Map<String, Object> map);

	void updateHandleCDs(Map<String, Object> map);

	void signCD(Map<String, Object> map);

	List<EntityPaper> getAllPaperLedgerList(Map<String, Object> map);

	EntityPaper getOnePaperLedgerById(String paper_id);

	void updateRetrievePapers(Map<String, Object> map);

	void destroyRetrievedPapers(Map<String, Object> map);

	void updateHandlePapers(Map<String, Object> map);

	int getAllCDLedgerSize(Map<String, Object> map);

	List<EntityCD> getAllCDLedgerList(Map<String, Object> map, RowBounds rbs);

	List<EntityPaper> getAllPaperLedgerList(Map<String, Object> map, RowBounds rbs);

	int getAllPaperLedgerSize(Map<String, Object> map);

	// void saveEventTranfer(EventTransfer tranfer);
	//
	// List<EventTransfer> getTransferEventList(Map<String, Object> map);

	List<EntityPaper> getPaperListByJobCode(String job_code);

	void cancelHandleModifyPaperById(String paper_barcode);

	void cancelHandlePaperById(String paper_id);

	int getHandlePaperCountByJobCode(String job_code);

	int getHandleModifyPaperCountByJobCode(String job_code);

	EntityPaper getPaperByBarcode(String barcode);

	List<CycleItem> getCycleItemListByBarcode(String barcode);

	// EventTransfer getOneTransferEventByCode(String event_code);

	String getDeptIdByUserId(String accept_user_iidd);

	void deleteEventTransfer(String event_code);

	void updateEventTransfer(Map<String, Object> map);

	void updatePaperTransferStatus(String barcode);

	// EventTransfer getTransferEventByTransferId(String id);
	//
	// EventTransfer getTransferEventByTransferCode(String event_code);
	//
	String getJobCodeByEventCode(String event_code);

	void cancelTransferEventByEventCode(String event_code);

	int getAllDeviceLedgerSize(Map<String, Object> map);

	List<EntityDevice> getAllDeviceLedgerList(Map<String, Object> map, RowBounds rbs);

	List<EntityDevice> getAllDeviceLedgerList(Map<String, Object> map);

	EntityCD getOneCDLedgerById(String cd_id);

	void cancelHandleModifyCDById(String cd_barcode);

	void cancelHandleCDById(String cd_id);

	int getHandleCDCountByJobCode(String job_code);

	int getHandleModifyCDCountByJobCode(String job_code);

	List<EntityCD> getCDListByJobCode(String job_code);

	void addCycleItem(CycleItem cycleitem);

	List<EntityDevice> getDeviceListByJobCode(String job_code);

	void updatePaperSendNumAndPaperState(Map<String, Object> map);

	void updateCDSendNumAndCDState(Map<String, Object> map);

	void updateCDStateById(Map<String, Object> map);

	void updatePaperStateById(Map<String, Object> map);

	void signPaper(Map<String, Object> map);

	EntityCD getCDByBarcode(String barcode);

	void updatePaperStateByBarcode(Map<String, Object> map);

	void updateCDStateByBarcode(Map<String, Object> map);

	void addRejectRecord(RejectRecord record);

	List<SecDept> getDeptListByDeptAdminUserId(String deptadmin_user_iidd);

	void updateEntityCD(Map<String, Object> map);

	void updateEntityPaper(Map<String, Object> map);

	void resetEntityCDById(String cd_id);

	void resetEntityPaperById(String paper_id);

	EntityPaper getPaperById(String id);

	EntityDevice getDeviceByBarcode(String barcode);

	EntityCD getCDById(String id);

	String checkShowPrintFile(String seclv_code);

	String checkShowBurnFile(String seclv_code);

	void updateRetrievePaper(Map<String, Object> map);

	void updateRetrieveCD(Map<String, Object> map);

	void resetPaperState(String paper_id);

	void resetCDState(String cd_id);

	void resetPaperStateByBarcode(String paper_barcode);

	void resetCDStateByBarcode(String cd_barcode);

	void updatePaperSendCommentByBarcode(Map<String, Object> map);

	void updateCDSendCommentByBarcode(Map<String, Object> map);

	List<RejectRecord> getRejectRecordByBarcode(String entity_barcode);

	List<String> getDeptIdByParentId(String dept_parent_id);

	void updateRetrieveComment(Map<String, Object> map);

	void updateRejectPaperComment(Map<String, Object> map);

	void addSendDestroyEvent(SendDestroyEvent event);

	List<EntityPaper> getDestroyPaperList(Map<String, Object> map, RowBounds rbs);

	List<ProcessJob> getSendDestroyPaperJobList(Map<String, Object> map);

	List<ProcessJob> getSendDestroyCDJobList(Map<String, Object> map);

	List<SendDestroyEvent> getSendDestroyEventListByJobCode(String job_code);

	void cancelSendDestroyPaperEventByEventCode(String event_code);

	void cancelSendDestroyCDEventByEventCode(String event_code);

	void cancelJobByJobCode(String job_code);

	List<EntityPaper> getSendPaperList(Map<String, Object> map, RowBounds rbs);

	List<EntityCD> getSendCDList(Map<String, Object> map, RowBounds rbs);

	List<EntityPaper> getFilePaperList(Map<String, Object> map, RowBounds rbs);

	List<EntityCD> getFileCDList(Map<String, Object> map, RowBounds rbs);

	List<EntityCD> getDestroyCDList(Map<String, Object> map, RowBounds rbs);

	List<EntityPaper> getDelayPaperList(Map<String, Object> map, RowBounds rbs);

	List<EntityCD> getDelayCDList(Map<String, Object> map, RowBounds rbs);

	void updateDelayDays(Map<String, Object> map);

	void updateCDDelayDays(Map<String, Object> map);

	List<PaperType> getPaperConversionRate();

	void deletePaperConversionRateByTypeName(String type_name);

	void updatePaperConversionRateByTypeName(Map<String, Object> map);

	void addPaperConversionRateByTypeName(Map<String, Object> map);

	void addOrUpdateFailRemarkByBarcode(Map<String, Object> map);

	void addOrUpdateFailRemarkByBarcodeAndTime(Map<String, Object> map);

	List<EntityPaper> getAllReplacePaperList(Map<String, Object> map, RowBounds rbs);

	int getAllReplacePaperSize(Map<String, Object> map);

	void updateRetrievedPage(Map<String, Object> map);

	List<String> getPIDBarcode();

	List<String> getPaperBarcode(String barcode);

	void updateDestroyedPage(Map<String, Object> map);

	// 查询外发文件是否撤销完 by chenhong
	String getJobcodeByPaperid(String paper_id);

	int getAllUnprintReceiptSize(String job_code);

	/**
	 * 根据entity_cd的条码更新流程号
	 * 
	 * @param barCode
	 */
	int getAllUncopyReceiptSize(String job_code);

	void updateCDJobCodeByBarCode(Map<String, Object> map);

	int getAllReceiptSize(String job_code);

	void updateReceipInfo(String job_code);

	String getJobcodeByCDid(String cd_id);

	int getAllReceiptCDSize(String job_code);

	int getAllUnCDReceiptSize(String job_code);

	/**
	 * 根据entity_paper的条码更新流程号
	 * 
	 * @param barCode
	 */
	void updatePaperJobCodeByBarCode(Map<String, Object> map);

	/**
	 * 增加外带信息
	 * 
	 * @param map
	 */
	void addEventCarryOutPaper(EventCarryOut eventCarryOut);

	List<EventCarryOut> getEventCarryOutListByJobCode(String job_code);

	List<EventCarryOutProcessJob> getHandleCarryOutJobListByEntityInstanceId(Map<String, Object> map);

	List<ProcessJob> getCarryOutJobList(Map<String, Object> map, RowBounds rbs);

	/**
	 * 得到外带已通过审批的未带回的记录
	 * 
	 * @param map
	 * @return
	 */
	List<EventOut> getCarryOutConfirmEventList(Map<String, Object> map);

	/**
	 * 载体带回处理
	 * 
	 * @param map
	 */
	void updateEventCarryOutCarryoutStatusById(Map<String, Object> map);

	/**
	 * 根据id查询EventCarryOUt
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

	/**
	 * 查询是否可审计
	 * 
	 * @return
	 */
	List<Integer> getSysSecLevelN(Map<String, Object> map);

	/**
	 * 检查是否带回
	 * 
	 * @param map
	 * @return
	 */
	int checkOrCarryIn(Map<String, Object> map);

	void updateEventModifyJobCode(Map<String, String> map);

	List<ProcessJob> getModifyProcessJobListByInstanceId(List<String> instanceIdList);

	List<EventModify> getModifyEventListByJobCode(String job_code);

	List<EventModify> getModifyEventListByBarcode(String barcode);

	EventModify getModifyEventByCode(String event_code);

	String getModifyJobCodeByEventCode(String event_code);

	void updateConfirmModifyEvent(Map<String, Object> map);

	List<EventModify> getModifyEventList(Map<String, Object> map);

	void updatePaperState(Map<String, Object> map);

	void confirmEntityPaper(Map<String, Object> map);

	int getCountModifyEvent();

	int getAllEventModifySize(Map<String, Object> map) throws Exception;

	List<EventModify> getAllEventModifyList(Map<String, Object> map, RowBounds rbs) throws Exception;

	String getSeclvNameByCode(int seclv_code);

	void cancelHandleLedgerById(String paper_id);

	void deleteEventModifyByBarcode(String event_code);

	void updateCDState(Map<String, Object> map);

	void confirmEntityCD(Map<String, Object> map);

	List<EventModify> getEventModifyByEntityType(String entity_type);

	int getAllEventCDModifySize(Map<String, Object> map) throws Exception;

	List<EventModify> getAllEventCDModifyList(Map<String, Object> map, RowBounds rbs) throws Exception;

	void updateModifyPaperState(Map<String, Object> map);

	void updateModifyCDState(Map<String, Object> map);

	void addEventModify(EventModify event);

	List<Integer> getRecycleSeclCode();

	List<EntityPaper> getAllPaperRecycleLedgerList(Map<String, Object> map, RowBounds rbs) throws Exception;

	int getAllPaperRecycleLedgerSize(Map<String, Object> map) throws Exception;

	List<EntityPaper> getAllPaperRecycleLedgerList(Map<String, Object> map) throws Exception;

	void confirmSendCDMatter(Map<String, Object> map);

	void confirmSendPaperMatter(Map<String, Object> map);

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

	void updatePaperIsBook(Map<String, Object> map);

	void ExpectsignPaperSuccess(Map<String, Object> map);

	void ExpectsignPaperfail(Map<String, Object> map);

	List<EntityPaper> getReplacePaperByPaperBarcode(String paper_barcode);

	void updateRetrievedPageNew(Map<String, Object> map);

	void updateRetrievedPageNewStatus(Map<String, Object> map);

	void updateDestroyedPageNew(Map<String, Object> map);

	void updateDestroyedPageNewStatus(Map<String, Object> map);

	List<EventLog> getEventLogAll(Map<String, Object> map);

	List<EventModify> getModifyEventByJobCode(String job_code);

	BarcodeCompare getBarcodeCompareByCode(String barcode);

	void setPaperRemark(Map<String, Object> map);

	List<BurnEvent> getBurnEventByCdBarcode(String cd_barcode);

	void addEntityCD(EntityCD entityCd);

	String getUser_iidd(String user_name);

	String getDept_id(String dept_name);

	Integer getSeclv_code(String seclv_name);

	int getModifyPaperSize(Map<String, Object> map);

	List<EntityPaper> getModifyPaperList(Map<String, Object> map, RowBounds rbs);

	void updateEntityPaperMap(Map<String, Object> map);

	void updateJobProcessInfo(Map<String, Object> map);

	void addEventTemp(EventTemp event);

	List<EventTemp> getTempEventList(Map<String, Object> map);

	List<EventTemp> getTempEventListByJobCode(String job_code);

	EventTemp getTempEventByEventCode(String event_code);

	void deleteEventTempBycode(String event_code);

	int getHandleTempCountByJobCode(String job_code);

	List<ProcessJob> getTempJobList(Map<String, Object> map);

	void updateEntityCdMap(Map<String, Object> map);

	void updateSupervisePaperStateByBarcode(Map<String, Object> map);

	int getSelfDestroyPaperSize(Map<String, Object> map);

	List<EntityPaper> getSelfDestroyPaperList(Map<String, Object> map, RowBounds rbs);

	// 光盘拿自主销毁
	void updateSuperviseCDStateByBarcode(Map<String, Object> map);

	int getSelfDestroyCDSize(Map<String, Object> map);

	List<EntityCD> getSelfDestroyCDList(Map<String, Object> map, RowBounds rbs);

	/**
	 * 获取个人录入磁介质台账
	 * 
	 * @author haojia
	 * */
	List<EntityDevice> getPersonalDeviceLedgerList(Map<String, Object> map, RowBounds rbs);

	int getPersonalDeviceLedgerSize(Map<String, Object> map);

	/**
	 * 根据条码号获取个人录入磁介质台账
	 * 
	 * @author haojia
	 * */
	List<EntityDevice> getDeviceListByBarcodes(Map<String, Object> map);

	// 个人磁介质自主销毁
	void updateSuperviseDeviceStateByBarcode(Map<String, Object> map);

	int getSelfDestroyDeviceSize(Map<String, Object> map);

	List<EntityDevice> getSelfDestroyDeviceList(Map<String, Object> map, RowBounds rbs);

	void resetDeviceState(String device_barcode);

	// 文件总台账的查询，单独使用下二方法

	int getSecAllPaperLedgerSize(Map<String, Object> map) throws Exception;

	List<EntityPaper> getSecAllPaperLedgerList(Map<String, Object> map, RowBounds rbs) throws Exception;

	void addEntityPaper(EntityPaper entityPaper);

	// 个人委托打印台账 查询，单独使用下二方法
	List<EntityPaper> getProxyPaperLedgerList(Map<String, Object> map, RowBounds rbs);

	int getProxyPaperLedgerSize(Map<String, Object> map);

	// 个人委托刻录台账 查询，单独使用下二方法
	List<EntityCD> getProxyCDLedgerList(Map<String, Object> map, RowBounds rbs);

	int getProxyCDLedgerSize(Map<String, Object> map);

	// 光盘总台账的查询，单独使用下二方法

	int getSecAllCDLedgerSize(Map<String, Object> map) throws Exception;

	List<EntityCD> getSecAllCDLedgerList(Map<String, Object> map, RowBounds rbs) throws Exception;

	void updatePaperOutputConfidentialNumByEventCarryoutId(Map<String, Object> map);

	void updateCDOutputConfidentialNumByEventCarryoutId(Map<String, Object> map);

	void updateJobProcessByJobcode(String job_code);

	void updateNeedMergeEntity(Map<String, Object> map);

	List<EntityPaper> getMergePaperList(Map<String, Object> map);

	List<EntityPaper> getMergeAllPaperList(Map<String, Object> map, RowBounds rbs);

	List<EntityPaper> getMergePaperApplyList(Map<String, Object> map, RowBounds rbs);

	int getMergePaperApplySize(Map<String, Object> map);

	void updateMergeCodeState(String paper_barcode);

	int getMergePaperSize(Map<String, Object> map);

	List<RejectRecord> getAllRejectPaperList(Map<String, Object> map,
			RowBounds rbs);

	int getAllRejectPaperSize(Map<String, Object> map);

	List<EntityCD> getAllRejectCDList(Map<String, Object> map, RowBounds rbs);

	int getAllRejectCDSize(Map<String, Object> map);
	
	// 更新接收人和接收单位
	void updateSendByBarcode(Map<String, Object> map);
}
