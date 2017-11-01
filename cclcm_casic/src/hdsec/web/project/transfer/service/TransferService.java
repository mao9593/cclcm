package hdsec.web.project.transfer.service;

import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.transfer.model.EventTransfer;

import java.util.List;
import java.util.Map;

public interface TransferService {

	int getAllPaperLedgerSize(Map<String, Object> map);

	String getDeptIdByUserId(String accept_user_iidd);

	void savePaperEventTranfer(EventTransfer transfer);

	List<EventTransfer> getTransferEventList(Map<String, Object> map);

	EventTransfer getTransferEventByTransferCode(String event_code);

	EventTransfer getTransferEventByTransferId(String id);

	String getJobCodeByEventCode(String event_code);

	EventTransfer getOneTransferEventByCode(String event_code);

	void updateEventTransfer(Map<String, Object> map);

	int cancelTransferEventByEventCode(String event_code, String type);

	EntityCD getOneCDLedgerById(int id);

	void deleteEventTransfer(String event_code, String barcode);

	void saveCDEventTranfer(EventTransfer transfer);

	void updateEventTranfer(EventTransfer transfer);

	List<EventTransfer> getTransferEventByJobCode(String job_code);

	/**
	 * 
	 * 交接确认关闭时的流转处理
	 * 
	 * @author lixiang
	 * @param event_code
	 * @param type
	 * @param barcode
	 */
	void confirmTransfer(String event_code, String type, String barcode);

	List<EventTransfer> getTransferEventListByJobCode(String job_code, String type);

	void cancelTransferEventByJobCode(String job_code, String type);

	/**
	 * 纸质载体流转记录
	 * 
	 * @param map
	 * @return
	 */
	List<ProcessJob> getJobListByPaper(Map<String, Object> map);

	/**
	 * 光盘载体流转记录
	 * 
	 * @param map
	 * @return
	 */
	List<ProcessJob> getJobListByCD(Map<String, Object> map);

	/**
	 * 添加交接确认记录
	 * 
	 * @param event_code
	 * @param type
	 * @param barcode
	 */
	void addConfirmRecord(String event_code, String type, String barcode);

	/**
	 * 交接拒绝关闭时的流转处理
	 * 
	 * @param event_code
	 * @param type
	 * @param barcode
	 */
	void rejectTransfer(String event_code, String type, String barcode);

}
