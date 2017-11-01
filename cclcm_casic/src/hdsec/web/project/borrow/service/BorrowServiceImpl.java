package hdsec.web.project.borrow.service;

import hdsec.web.project.basic.mapper.BasicMapper;
import hdsec.web.project.basic.model.ConfirmRecord;
import hdsec.web.project.basic.service.BasicService;
import hdsec.web.project.borrow.mapper.BorrowMapper;
import hdsec.web.project.borrow.model.DeptOpenScope;
import hdsec.web.project.borrow.model.EventBorrow;
import hdsec.web.project.borrow.model.OpenScopeConfig;
import hdsec.web.project.ledger.mapper.LedgerMapper;
import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.service.UserService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;

/**
 * 借阅管理 2014-4-16 上午6:48:28
 * 
 * @author gaoximin
 */
public class BorrowServiceImpl implements BorrowService {
	private final Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private BorrowMapper borrowMapper;
	@Resource
	private UserService userService;
	@Resource
	private BasicMapper basicMapper;
	@Resource
	private LedgerMapper ledgerMapper;
	@Resource
	private BasicService basicService;

	@Override
	public void configOpenScope(OpenScopeConfig scopeConfig) {
		borrowMapper.delDeptOpenScopeByDept(scopeConfig.getDept_id());
		DeptOpenScope openScope = null;
		for (String dept_id : scopeConfig.getDept_ids().split(",")) {
			openScope = new DeptOpenScope(scopeConfig.getDept_id(), dept_id);
			borrowMapper.addDeptOpenScope(openScope);
		}
	}

	@Override
	public OpenScopeConfig getOpenScopeConfig(String dept_id) {
		List<String> dept_id_list = getBrDeptIdListByDept(dept_id);
		if (dept_id_list != null && dept_id_list.size() > 0) {
			String dept_ids = dept_id_list.toString().replace("[", "").replace("]", "").replace(" ", "");
			String dept_names = userService.getDeptNamesByIdList(dept_id_list);
			return new OpenScopeConfig(dept_id, dept_ids, dept_names);
		} else {
			return new OpenScopeConfig(dept_id, "");
		}
	}

	public List<String> getBrDeptIdListByDept(String dept_id) {
		return borrowMapper.getBrDeptIdListByDept(dept_id);
	}

	@Override
	public void addBorrowEvent(EventBorrow event) {
		logger.debug("addBorrowEvent" + event.getEvent_code());
		borrowMapper.addBorrowEvent(event);
	}

	@Override
	public List<EventBorrow> getBorrowEventList(Map<String, Object> map) {
		logger.debug("getBorrowEventList");
		return borrowMapper.getBorrowEventList(map);
	}

	@Override
	public EventBorrow getBorrowEventByEventCode(String event_code) {
		logger.debug("getBorrowEventByEventCode" + event_code);
		return borrowMapper.getBorrowEventByEventCode(event_code);
	}

	@Override
	public String getJobCodeByEventCode(String event_code) {
		logger.debug("getJobCodeByEventCode" + event_code);
		return borrowMapper.getJobCodeByEventCode(event_code);
	}

	@Override
	public int getAllBorrowLedgerSize(Map<String, Object> map) throws Exception {
		logger.debug("getAllBorrowLedgerSize");
		String changedBarcode = basicService.changeBarcode((String) map.get("barcode"));
		map.put("barcode", changedBarcode);
		return borrowMapper.getAllBorrowLedgerSize(map);
	}

	@Override
	public List<EventBorrow> getAllBorrowLedgerList(Map<String, Object> map, RowBounds rbs) throws Exception {
		logger.debug("getAllBorrowLedgerList");
		String changedBarcode = basicService.changeBarcode((String) map.get("barcode"));
		map.put("barcode", changedBarcode);
		return borrowMapper.getAllBorrowLedgerList(map, rbs);
	}

	@Override
	public void updateBorrowStatus(String event_code, String entity_type, String barcode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("event_code", event_code);
		map.put("entity_type", entity_type);
		map.put("barcode", barcode);
		borrowMapper.updateBorrowStatus(map);
	}

	@Override
	public int getPersonPaperBorrowLedgerSize(Map<String, Object> map) {
		logger.debug("getPersonPaperBorrowLedgerSize");
		return borrowMapper.getPersonPaperBorrowLedgerSize(map);
	}

	@Override
	public List<EntityPaper> getPersonPaperBorrowLedgerList(Map<String, Object> map, RowBounds rbs) {
		logger.debug("getPersonPaperBorrowLedgerList");
		return borrowMapper.getPersonPaperBorrowLedgerList(map, rbs);
	}

	@Override
	public int getPersonCDBorrowLedgerSize(Map<String, Object> map) {
		logger.debug("getPersonCDBorrowLedgerSize");
		return borrowMapper.getPersonCDBorrowLedgerSize(map);
	}

	@Override
	public List<EntityCD> getPersonCDBorrowLedgerList(Map<String, Object> map, RowBounds rbs) {
		logger.debug("getPersonCDBorrowLedgerList");
		return borrowMapper.getPersonCDBorrowLedgerList(map, rbs);
	}

	@Override
	public void savePaperConfirmRecord(EntityPaper paper, SecUser user, String confirm_type) {
		logger.debug("savePaperConfirmRecord");
		String paper_barcode = paper.getPaper_barcode();
		EventBorrow event = borrowMapper.getBorrowEventByBarcode(paper_barcode);

		if ("READ_BR".equals(confirm_type)) {
			if (basicService.isConfirmOpen("READ_BR_CONFIRM")) {// 借阅交接确认开启
				ConfirmRecord record = new ConfirmRecord(paper.getApplyuserid(), paper.getApplyusername(),
						paper.getApplydeptid(), paper.getApplydeptname(), user.getUser_iidd(), user.getUser_name(),
						user.getDept_id(), user.getDept_name(), "PAPER", paper.getPaper_barcode(),
						paper.getFile_title(), paper.getSeclv_name(), confirm_type, paper.getApplyeventcode(),
						new Date());
				basicMapper.saveConfirmRecord(record);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("barcode", paper_barcode);
				map.put("state", 10);// 待交接
				borrowMapper.updateEntityPaperStatus(map);
			} else {// 交接确认关闭
				event.setFinish_time(new Date());
				event.setBorrow_status(1);// 已交接借入
				event.setLend_user_iidd(user.getUser_iidd());
				event.setLend_dept_id(paper.getScope_dept_id());
				borrowMapper.updateConfirmBorrowEvent(event);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("paper_id", paper.getPaper_id());
				map.put("duty_user_iidd", paper.getDuty_user_iidd());
				map.put("duty_user_name", paper.getDuty_user_name());
				map.put("duty_dept_id", paper.getDuty_dept_id());
				map.put("duty_dept_name", paper.getDuty_dept_name());
				map.put("paper_state", 6);// 借阅中
				ledgerMapper.updateEntityPaper(map);
				// 生成载体生命周期记录：借入
				CycleItem cycleitem = new CycleItem();
				cycleitem.setBarcode(paper_barcode);
				cycleitem.setEntity_type("PAPER");
				cycleitem.setOper_time(new Date());
				cycleitem.setUser_name(event.getUser_name());
				cycleitem.setDept_name(event.getDept_name());
				// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
				String job_code = getJobCodeByEventCode(event.getEvent_code());
				cycleitem.setJob_code(job_code);
				// add ending
				cycleitem.setOper("BORROW");
				ledgerMapper.addCycleItem(cycleitem);
			}
		}
		if ("READ_RT".equals(confirm_type)) {
			if (basicService.isConfirmOpen("READ_RT_CONFIRM")) {// 归还交接确认开启
				ConfirmRecord record = new ConfirmRecord(paper.getApplyuserid(), paper.getApplyusername(),
						paper.getApplydeptid(), paper.getApplydeptname(), user.getUser_iidd(), user.getUser_name(),
						user.getDept_id(), user.getDept_name(), "PAPER", paper.getPaper_barcode(),
						paper.getFile_title(), paper.getSeclv_name(), confirm_type, paper.getApplyeventcode(),
						new Date());
				basicMapper.saveConfirmRecord(record);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("barcode", paper_barcode);
				map.put("state", 10);// 待交接
				borrowMapper.updateEntityPaperStatus(map);
			} else {// 交接确认关闭
				event.setReturn_time(new Date());
				event.setBorrow_status(2);// 已交接归还
				borrowMapper.updateConfirmBorrowEvent(event);

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("paper_id", paper.getPaper_id());
				map.put("duty_user_iidd", user.getUser_iidd());
				map.put("duty_user_name", user.getUser_name());
				map.put("duty_dept_id", user.getDept_id());
				map.put("duty_dept_name", user.getDept_name());
				map.put("paper_state", 0);// 留用
				ledgerMapper.updateEntityPaper(map);
				// 生成载体生命周期记录：归还
				CycleItem cycleitem = new CycleItem();
				cycleitem.setBarcode(paper_barcode);
				cycleitem.setEntity_type("PAPER");
				cycleitem.setOper_time(new Date());
				cycleitem.setUser_name(event.getUser_name());
				cycleitem.setDept_name(event.getDept_name());
				// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
				// 归还无流程，因此设置为default
				cycleitem.setJob_code("default");
				// add ending
				cycleitem.setOper("RETURN");
				ledgerMapper.addCycleItem(cycleitem);
			}
		}
	}

	@Override
	public void saveCDConfirmRecord(EntityCD cd, SecUser user, String confirm_type) {
		logger.debug("saveCDConfirmRecord");
		String cd_barcode = cd.getCd_barcode();
		EventBorrow event = borrowMapper.getBorrowEventByBarcode(cd_barcode);

		if ("READ_BR".equals(confirm_type)) {
			if (basicService.isConfirmOpen("READ_BR_CONFIRM")) {// 借阅交接确认开启
				ConfirmRecord record = new ConfirmRecord(cd.getApplyuserid(), cd.getApplyusername(),
						cd.getApplydeptid(), cd.getApplydeptname(), user.getUser_iidd(), user.getUser_name(),
						user.getDept_id(), user.getDept_name(), "CD", cd.getCd_barcode(), cd.getFile_list(),
						cd.getSeclv_name(), confirm_type, cd.getApplyeventcode(), new Date());
				basicMapper.saveConfirmRecord(record);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("barcode", cd_barcode);
				map.put("state", 10);// 待交接
				borrowMapper.updateEntityCDStatus(map);
			} else {// 交接确认关闭
				event.setFinish_time(new Date());
				event.setBorrow_status(1);// 已交接借入
				event.setLend_user_iidd(user.getUser_iidd());
				event.setLend_dept_id(cd.getScope_dept_id());
				borrowMapper.updateConfirmBorrowEvent(event);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cd_id", cd.getCd_id());
				map.put("duty_user_iidd", cd.getDuty_user_iidd());
				map.put("duty_user_name", cd.getDuty_user_name());
				map.put("duty_dept_id", cd.getDuty_dept_id());
				map.put("duty_dept_name", cd.getDuty_dept_name());
				map.put("cd_state", 6);// 借阅中
				ledgerMapper.updateEntityCD(map);
				// 生成载体生命周期记录：借入

				CycleItem cycleitem = new CycleItem();
				cycleitem.setBarcode(cd_barcode);
				cycleitem.setEntity_type("CD");
				cycleitem.setOper_time(new Date());
				cycleitem.setUser_name(event.getUser_name());
				cycleitem.setDept_name(event.getDept_name());
				// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
				String job_code = getJobCodeByEventCode(event.getEvent_code());
				cycleitem.setJob_code(job_code);
				// add ending
				cycleitem.setOper("BORROW");
				ledgerMapper.addCycleItem(cycleitem);
			}
		}
		if ("READ_RT".equals(confirm_type)) {
			if (basicService.isConfirmOpen("READ_RT_CONFIRM")) {// 归还交接确认开启
				ConfirmRecord record = new ConfirmRecord(cd.getApplyuserid(), cd.getApplyusername(),
						cd.getApplydeptid(), cd.getApplydeptname(), user.getUser_iidd(), user.getUser_name(),
						user.getDept_id(), user.getDept_name(), "CD", cd.getCd_barcode(), cd.getFile_list(),
						cd.getSeclv_name(), confirm_type, cd.getApplyeventcode(), new Date());
				basicMapper.saveConfirmRecord(record);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("barcode", cd_barcode);
				map.put("state", 10);// 待交接
				borrowMapper.updateEntityCDStatus(map);
			} else {// 交接确认关闭
				event.setReturn_time(new Date());
				event.setBorrow_status(2);// 已交接归还
				borrowMapper.updateConfirmBorrowEvent(event);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cd_id", cd.getCd_id());
				map.put("duty_user_iidd", user.getUser_iidd());
				map.put("duty_user_name", user.getUser_name());
				map.put("duty_dept_id", user.getDept_id());
				map.put("duty_dept_name", user.getDept_name());
				map.put("cd_state", 0);// 留用
				ledgerMapper.updateEntityCD(map);
				// 生成载体生命周期记录：归还
				CycleItem cycleitem = new CycleItem();
				cycleitem.setBarcode(cd_barcode);
				cycleitem.setEntity_type("CD");
				cycleitem.setOper_time(new Date());
				cycleitem.setUser_name(event.getUser_name());
				cycleitem.setDept_name(event.getDept_name());
				// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
				// 归还无流程，因此设置为default
				cycleitem.setJob_code("default");
				// add ending
				cycleitem.setOper("RETURN");
				ledgerMapper.addCycleItem(cycleitem);
			}
		}
	}

	@Override
	public EventBorrow getBorrowEventByJobCode(String job_code) {
		logger.debug("getBorrowEventByJobCode");
		return borrowMapper.getBorrowEventByJobCode(job_code);
	}

	@Override
	public void resetEntityStatus(String entity_type, String barcode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", 0);
		map.put("barcode", barcode);
		if (entity_type.equalsIgnoreCase("PAPER")) {
			borrowMapper.updateEntityPaperStatus(map);
		} else if (entity_type.equalsIgnoreCase("CD")) {
			borrowMapper.updateEntityCDStatus(map);
		}
	}

	@Override
	public void delBorrowEventByJobCode(String job_code) {
		logger.debug("delBorrowEventByJobCode");
		borrowMapper.delBorrowEventByJobCode(job_code);
	}

	@Override
	public void saveFilePaperConfirmRecord(EntityPaper paper, SecUser user, String confirm_type) {
		logger.debug("saveFilePaperConfirmRecord");
		String paper_barcode = paper.getPaper_barcode();
		EventBorrow event = borrowMapper.getBorrowEventByBarcode(paper_barcode);

		if ("READ_BR".equals(confirm_type)) {
			if (basicService.isConfirmOpen("READ_BR_CONFIRM")) {// 借阅交接确认开启
				ConfirmRecord record = new ConfirmRecord(paper.getApplyuserid(), paper.getApplyusername(),
						paper.getApplydeptid(), paper.getApplydeptname(), user.getUser_iidd(), user.getUser_name(),
						user.getDept_id(), user.getDept_name(), "PAPER", paper.getPaper_barcode(),
						paper.getFile_title(), paper.getSeclv_name(), confirm_type, paper.getApplyeventcode(),
						new Date());
				basicMapper.saveConfirmRecord(record);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("barcode", paper_barcode);
				map.put("state", 10);// 待交接
				borrowMapper.updateEntityPaperStatus(map);
			} else {// 交接确认关闭
				event.setFinish_time(new Date());
				event.setBorrow_status(1);// 已交接借入
				event.setLend_user_iidd(user.getUser_iidd());
				event.setLend_dept_id(paper.getScope_dept_id());
				borrowMapper.updateConfirmBorrowEvent(event);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("paper_id", paper.getPaper_id());
				map.put("duty_user_iidd", event.getUser_iidd());
				map.put("duty_user_name", event.getUser_name());
				map.put("duty_dept_id", event.getDept_id());
				map.put("duty_dept_name", event.getDept_name());
				map.put("paper_state", 6);// 借阅中
				ledgerMapper.updateEntityPaper(map);
				// 生成载体生命周期记录：借入
				CycleItem cycleitem = new CycleItem();
				cycleitem.setBarcode(paper_barcode);
				cycleitem.setEntity_type("PAPER");
				cycleitem.setOper_time(new Date());
				cycleitem.setUser_name(event.getUser_name());
				cycleitem.setDept_name(event.getDept_name());
				cycleitem.setOper("BORROW");
				ledgerMapper.addCycleItem(cycleitem);
			}
		}
		if ("READ_RT".equals(confirm_type)) {
			if (basicService.isConfirmOpen("READ_RT_CONFIRM")) {// 归还交接确认开启
				ConfirmRecord record = new ConfirmRecord(paper.getApplyuserid(), paper.getApplyusername(),
						paper.getApplydeptid(), paper.getApplydeptname(), user.getUser_iidd(), user.getUser_name(),
						user.getDept_id(), user.getDept_name(), "PAPER", paper.getPaper_barcode(),
						paper.getFile_title(), paper.getSeclv_name(), confirm_type, paper.getApplyeventcode(),
						new Date());
				basicMapper.saveConfirmRecord(record);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("barcode", paper_barcode);
				map.put("state", 10);// 待交接
				borrowMapper.updateEntityPaperStatus(map);
			} else {// 交接确认关闭
				event.setReturn_time(new Date());
				event.setBorrow_status(2);// 已交接归还
				borrowMapper.updateConfirmBorrowEvent(event);

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("paper_id", paper.getPaper_id());
				map.put("duty_user_iidd", user.getUser_iidd());
				map.put("duty_user_name", user.getUser_name());
				map.put("duty_dept_id", user.getDept_id());
				map.put("duty_dept_name", user.getDept_name());
				map.put("paper_state", 3);// 已归档
				ledgerMapper.updateEntityPaper(map);
				// 生成载体生命周期记录：归还
				CycleItem cycleitem = new CycleItem();
				cycleitem.setBarcode(paper_barcode);
				cycleitem.setEntity_type("PAPER");
				cycleitem.setOper_time(new Date());
				cycleitem.setUser_name(event.getUser_name());
				cycleitem.setDept_name(event.getDept_name());
				cycleitem.setOper("RETURN");
				ledgerMapper.addCycleItem(cycleitem);
			}
		}
	}

	@Override
	public void saveFileCDConfirmRecord(EntityCD cd, SecUser user, String confirm_type) {
		logger.debug("saveCDConfirmRecord");
		String cd_barcode = cd.getCd_barcode();
		EventBorrow event = borrowMapper.getBorrowEventByBarcode(cd_barcode);

		if ("READ_BR".equals(confirm_type)) {
			if (basicService.isConfirmOpen("READ_BR_CONFIRM")) {// 借阅交接确认开启
				ConfirmRecord record = new ConfirmRecord(cd.getApplyuserid(), cd.getApplyusername(),
						cd.getApplydeptid(), cd.getApplydeptname(), user.getUser_iidd(), user.getUser_name(),
						user.getDept_id(), user.getDept_name(), "CD", cd.getCd_barcode(), cd.getFile_list(),
						cd.getSeclv_name(), confirm_type, cd.getApplyeventcode(), new Date());
				basicMapper.saveConfirmRecord(record);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("barcode", cd_barcode);
				map.put("state", 10);// 待交接
				borrowMapper.updateEntityCDStatus(map);
			} else {// 交接确认关闭
				event.setFinish_time(new Date());
				event.setBorrow_status(1);// 已交接借入
				event.setLend_user_iidd(user.getUser_iidd());
				event.setLend_dept_id(cd.getScope_dept_id());
				borrowMapper.updateConfirmBorrowEvent(event);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cd_id", cd.getCd_id());
				map.put("duty_user_iidd", cd.getUser_iidd());
				map.put("duty_user_name", event.getUser_name());
				map.put("duty_dept_id", event.getDept_id());
				map.put("duty_dept_name", event.getDept_name());
				map.put("cd_state", 6);// 借阅中
				ledgerMapper.updateEntityCD(map);
				// 生成载体生命周期记录：借入
				CycleItem cycleitem = new CycleItem();
				cycleitem.setBarcode(cd_barcode);
				cycleitem.setEntity_type("CD");
				cycleitem.setOper_time(new Date());
				cycleitem.setUser_name(event.getUser_name());
				cycleitem.setDept_name(event.getDept_name());
				cycleitem.setOper("BORROW");
				ledgerMapper.addCycleItem(cycleitem);
			}
		}
		if ("READ_RT".equals(confirm_type)) {
			if (basicService.isConfirmOpen("READ_RT_CONFIRM")) {// 归还交接确认开启
				ConfirmRecord record = new ConfirmRecord(cd.getApplyuserid(), cd.getApplyusername(),
						cd.getApplydeptid(), cd.getApplydeptname(), user.getUser_iidd(), user.getUser_name(),
						user.getDept_id(), user.getDept_name(), "CD", cd.getCd_barcode(), cd.getFile_list(),
						cd.getSeclv_name(), confirm_type, cd.getApplyeventcode(), new Date());
				basicMapper.saveConfirmRecord(record);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("barcode", cd_barcode);
				map.put("state", 10);// 待交接
				borrowMapper.updateEntityCDStatus(map);
			} else {// 交接确认关闭
				event.setReturn_time(new Date());
				event.setBorrow_status(2);// 已交接归还
				borrowMapper.updateConfirmBorrowEvent(event);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cd_id", cd.getCd_id());
				map.put("duty_user_iidd", user.getUser_iidd());
				map.put("duty_user_name", user.getUser_name());
				map.put("duty_dept_id", user.getDept_id());
				map.put("duty_dept_name", user.getDept_name());
				map.put("cd_state", 3);// 已归档
				ledgerMapper.updateEntityCD(map);
				// 生成载体生命周期记录：归还
				CycleItem cycleitem = new CycleItem();
				cycleitem.setBarcode(cd_barcode);
				cycleitem.setEntity_type("CD");
				cycleitem.setOper_time(new Date());
				cycleitem.setUser_name(event.getUser_name());
				cycleitem.setDept_name(event.getDept_name());
				cycleitem.setOper("RETURN");
				ledgerMapper.addCycleItem(cycleitem);
			}
		}
	}

	@Override
	public void updatePaperLedger(Map<String, Object> map) {
		borrowMapper.updatePaperLedger(map);
	}

	@Override
	public void updateIsSureByEventCode(Map<String, Object> map) {
		borrowMapper.updateIsSureByEventCode(map);
	}

	@Override
	public void updateCdLedger(Map<String, Object> map) {
		borrowMapper.updateCdLedger(map);

	}
}
