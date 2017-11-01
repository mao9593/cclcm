package hdsec.web.project.ledger.service;

import hdsec.web.project.basic.model.RejectRecord;
import hdsec.web.project.burn.model.BurnEvent;
import hdsec.web.project.device.model.EntityDevice;
import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.user.model.SecDept;
import hdsec.web.project.user.model.SecUser;

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

	void signCD(String cd_id, String fail_remark);

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

	void signPaper(String paper_id, String fail_remark);

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
	 */
	void confirmSendCD(String cd_barcode, SecUser user, String comment);

	/**
	 * 
	 * 确认外发纸质载体，生成两条生命周期记录并修改纸质载体状态
	 * 
	 * @param user
	 * @param paper_barcode
	 */
	void confirmSendPaper(String paper_barcode, SecUser user, String comment);

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
	 * 根据光盘条码查询刻录申请作业列表
	 * 
	 * @param cd_barcode
	 * @return
	 */
	List<BurnEvent> getBurnEventByCdBarcode(String cd_barcode);
}
