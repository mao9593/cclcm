package hdsec.web.project.ledger.mapper;

import hdsec.web.project.basic.model.RejectRecord;
import hdsec.web.project.burn.model.BurnEvent;
import hdsec.web.project.device.model.EntityDevice;
import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
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

	void cancelHandlePaperById(String paper_id);

	int getHandlePaperCountByJobCode(String job_code);

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

	void cancelHandleCDById(String cd_id);

	int getHandleCDCountByJobCode(String job_code);

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

	List<BurnEvent> getBurnEventByCdBarcode(String cd_barcode);
}
