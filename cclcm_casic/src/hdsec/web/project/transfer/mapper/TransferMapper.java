package hdsec.web.project.transfer.mapper;

import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.transfer.model.EventTransfer;

import java.util.List;
import java.util.Map;

public interface TransferMapper {
	
	int getAllPaperLedgerSize(Map<String, Object> map);
	
	String getDeptIdByUserId(String accept_user_iidd);
	
	void saveEventTranfer(EventTransfer transfer);
	
	List<EventTransfer> getTransferEventList(Map<String, Object> map);
	
	EventTransfer getTransferEventByTransferCode(String event_code);
	
	EventTransfer getTransferEventByTransferId(String id);
	
	String getJobCodeByEventCode(String event_code);
	
	EventTransfer getOneTransferEventByCode(String event_code);
	
	void updateEventTransfer(Map<String, Object> map);
	
	EntityCD getOneCDLedgerById(int id);
	
	void deleteEventTransfer(String event_code);
	
	void updatePaperTransferStatus(String barcode);
	
	void saveCDEventTranfer(EventTransfer transfer);
	
	void updateEventTranfer(EventTransfer transfer);
	
	List<EventTransfer> getTransferEventByJobCode(String job_code);
	
	void cancelCDTransferEventByEventCode(Map<String, Object> map);
	
	void cancelPaperTransferEventByEventCode(Map<String, Object> map);
	
	void updateConfirmTransferEvent(EventTransfer event);
	
	EventTransfer getTransferEvent(Map<String, Object> map);
	
	List<EventTransfer> getTransferEventListByJobCode(String job_code);
	
	void cancelJobByJobCode(String job_code);

	List<ProcessJob> getJobListByPaper(Map<String, Object> map);

	List<ProcessJob> getJobListByCD(Map<String, Object> map);

	void updateEventTransferStatus(Map<String, Object> map);
	
}
