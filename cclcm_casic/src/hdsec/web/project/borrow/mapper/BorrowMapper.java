package hdsec.web.project.borrow.mapper;

import hdsec.web.project.basic.model.ConfirmRecord;
import hdsec.web.project.borrow.model.DeptOpenScope;
import hdsec.web.project.borrow.model.EventBorrow;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

/**
 * 借阅管理 2014-4-16 上午6:24:00
 * 
 * @author gaoximin
 */
public interface BorrowMapper {

	void delDeptOpenScopeByDept(String dept_id);

	void addDeptOpenScope(DeptOpenScope openScope);

	List<String> getBrDeptIdListByDept(String dept_id);

	void addBorrowEvent(EventBorrow event);

	List<EventBorrow> getBorrowEventList(Map<String, Object> map);

	EventBorrow getBorrowEventByEventCode(String event_code);

	String getJobCodeByEventCode(String event_code);

	int getAllBorrowLedgerSize(Map<String, Object> map);

	List<EventBorrow> getAllBorrowLedgerList(Map<String, Object> map, RowBounds rbs);

	void updateBorrowStatus(Map<String, Object> map);

	int getPersonPaperBorrowLedgerSize(Map<String, Object> map);

	List<EntityPaper> getPersonPaperBorrowLedgerList(Map<String, Object> map, RowBounds rbs);

	int getPersonCDBorrowLedgerSize(Map<String, Object> map);

	List<EntityCD> getPersonCDBorrowLedgerList(Map<String, Object> map, RowBounds rbs);

	void saveConfirmRecord(ConfirmRecord record);

	void updateEntityPaperStatus(Map<String, Object> map);

	void updateEntityCDStatus(Map<String, Object> map);

	EventBorrow getBorrowEventByBarcode(String barcode);

	void updateConfirmBorrowEvent(EventBorrow event);

	EventBorrow getBorrowEventByJobCode(String job_code);

	void delBorrowEventByJobCode(String job_code);

	void updatePaperIsBook(Map<String, Object> map);

	void updatePaperLedger(Map<String, Object> map);

	void updateIsSureByEventCode(Map<String, Object> map);

	void updateCdLedger(Map<String, Object> map);

}
