package hdsec.web.project.borrow.service;

import hdsec.web.project.borrow.model.EventBorrow;
import hdsec.web.project.borrow.model.OpenScopeConfig;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.user.model.SecUser;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

/**
 * 借用管理 2014-4-16 上午6:47:45
 * 
 * @author gaoximin
 */
public interface BorrowService {

	/**
	 * 配置部门涉密载体借用范围 2014-4-16 下午10:42:47
	 * 
	 * @author renmingfei
	 * @param scopeConfig
	 */
	void configOpenScope(OpenScopeConfig scopeConfig);

	/**
	 * 查看部门开放信息 2014-4-16 下午10:59:15
	 * 
	 * @author renmingfei
	 * @param dept_id
	 * @return
	 */
	OpenScopeConfig getOpenScopeConfig(String dept_id);

	/**
	 * 增加借阅作业 2014-4-17 上午10:41:41
	 * 
	 * @author gaoximin
	 * @param event
	 */
	void addBorrowEvent(EventBorrow event);

	/**
	 * 查询借用作业 2014-4-18 上午1:00:21
	 * 
	 * @author gaoximin
	 * @param map
	 * @return
	 */
	List<EventBorrow> getBorrowEventList(Map<String, Object> map);

	/**
	 * 通过作业编号查找作业 2014-4-21 上午8:49:14
	 * 
	 * @author gaoximin
	 * @param event_code
	 * @return
	 */
	EventBorrow getBorrowEventByEventCode(String event_code);

	/**
	 * 通过作业编号查找任务号 2014-4-21 上午8:55:37
	 * 
	 * @author gaoximin
	 * @param event_code
	 * @return
	 */
	String getJobCodeByEventCode(String event_code);

	/**
	 * 查找个人借阅作业信息总数 2014-4-21 上午9:39:45
	 * 
	 * @author gaoximin
	 * @param map
	 * @return
	 */
	int getAllBorrowLedgerSize(Map<String, Object> map) throws Exception;

	/**
	 * 查找个人借阅作业信息 2014-4-21 上午9:40:13
	 * 
	 * @author gaoximin
	 * @param map
	 * @param rbs
	 * @return
	 */
	List<EventBorrow> getAllBorrowLedgerList(Map<String, Object> map, RowBounds rbs) throws Exception;

	/**
	 * 修改借阅状态为归还 2014-4-22 上午1:05:18
	 * 
	 * @author gaoximin
	 * @param event_code
	 */
	void updateBorrowStatus(String event_code, String entity_type, String barcode);

	/**
	 * 查找个人纸质借阅台帐总数 2014-4-23 上午9:39:45
	 * 
	 * @author gaoximin
	 * @param map
	 * @return
	 */
	int getPersonPaperBorrowLedgerSize(Map<String, Object> map);

	/**
	 * 查找个人纸质借阅台帐 2014-4-23 上午9:40:13
	 * 
	 * @author gaoximin
	 * @param map
	 * @param rbs
	 * @return
	 */
	List<EntityPaper> getPersonPaperBorrowLedgerList(Map<String, Object> map, RowBounds rbs);

	/**
	 * 查找光盘借阅台帐总数 2014-4-23 上午9:39:45
	 * 
	 * @author gaoximin
	 * @param map
	 * @return
	 */
	int getPersonCDBorrowLedgerSize(Map<String, Object> map);

	/**
	 * 查找个人光盘借阅台帐 2014-4-23 上午9:40:13
	 * 
	 * @author gaoximin
	 * @param map
	 * @param rbs
	 * @return
	 */
	List<EntityCD> getPersonCDBorrowLedgerList(Map<String, Object> map, RowBounds rbs);

	/**
	 * 记录部门管理员借用纸张交接确认记录
	 */
	void savePaperConfirmRecord(EntityPaper paper, SecUser user, String confirm_type);

	/**
	 * 记录部门管理员借用光盘交接确认记录
	 */
	void saveCDConfirmRecord(EntityCD cd, SecUser user, String confirm_type);

	/**
	 * 通过任务号得到借用作业
	 * 
	 * @param job_code
	 * @return
	 */
	EventBorrow getBorrowEventByJobCode(String job_code);

	/**
	 * 恢复载体状态为留用
	 * 
	 * @param entity_type
	 * @param barcode
	 */
	void resetEntityStatus(String entity_type, String barcode);

	/**
	 * 通过任务号删除借用作业
	 * 
	 * @param job_code
	 * @return
	 */
	void delBorrowEventByJobCode(String job_code);

	/**
	 * 记录借用归档文件交接确认记录
	 * 
	 * @param paper
	 * @param user
	 * @param confirm_type
	 */
	void saveFilePaperConfirmRecord(EntityPaper paper, SecUser user, String confirm_type);

	/**
	 * 记录借用归档光盘交接确认记录
	 * 
	 * @param cd
	 * @param user
	 * @param confirm_type
	 */
	void saveFileCDConfirmRecord(EntityCD cd, SecUser user, String confirm_type);

	void updatePaperLedger(Map<String, Object> map);

	void updateIsSureByEventCode(Map<String, Object> map);

	void updateCdLedger(Map<String, Object> map);
}
