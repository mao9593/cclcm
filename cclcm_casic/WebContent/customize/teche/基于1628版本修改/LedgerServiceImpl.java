package hdsec.web.project.ledger.service;

import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.basic.mapper.BasicMapper;
import hdsec.web.project.basic.model.ConfirmRecord;
import hdsec.web.project.basic.model.RejectRecord;
import hdsec.web.project.basic.service.BasicPrcManage;
import hdsec.web.project.basic.service.BasicService;
import hdsec.web.project.burn.model.BurnEvent;
import hdsec.web.project.client.service.ClientService;
import hdsec.web.project.device.model.EntityDevice;
import hdsec.web.project.ledger.mapper.LedgerMapper;
import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.ledger.model.CycleOperEnum;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.user.model.SecDept;
import hdsec.web.project.user.model.SecUser;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

public class LedgerServiceImpl implements LedgerService {
	private final Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private LedgerMapper ledgerMapper;
	@Resource
	private BasicService basicService;
	@Resource
	private BasicPrcManage basicPrcManage;
	@Resource
	private ClientService clientService;
	@Resource
	private BasicMapper basicMapper;

	@Override
	public List<EntityCD> getAllCDLedgerList(Map<String, Object> map, RowBounds rbs) throws Exception {
		logger.debug("getAllCDLedgerList");
		String changedBarcode = basicService.changeBarcode((String) map.get("cd_barcode"));
		map.put("cd_barcode", changedBarcode);
		return ledgerMapper.getAllCDLedgerList(map, rbs);
	}

	@Override
	public List<EntityCD> getAllCDLedgerList(Map<String, Object> map) throws Exception {
		logger.debug("getAllCDLedgerList");
		String changedBarcode = basicService.changeBarcode((String) map.get("cd_barcode"));
		map.put("cd_barcode", changedBarcode);
		return ledgerMapper.getAllCDLedgerList(map);
	}

	@Override
	public EntityCD getOneCDLedgerById(int cd_id) {
		logger.debug("getOneCDLedgerById");
		return ledgerMapper.getOneCDLedgerById(cd_id);
	}

	@Override
	public void updateRetrieveCDs(Map<String, Object> map) {
		logger.debug("updateRetrieveCDs");
		ledgerMapper.updateRetrieveCDs(map);
	}

	@Override
	public void destroyRetrievedCDs(Map<String, Object> map) {
		logger.debug("destroyRetrievedCDs");
		ledgerMapper.destroyRetrievedCDs(map);
	}

	@Override
	public void updateHandleCDs(Map<String, Object> map) {
		logger.debug("updateHandleCDs");
		ledgerMapper.updateHandleCDs(map);
	}

	@Override
	public void signCD(String cd_id, String fail_remark) {
		logger.debug("signCD");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cd_id", cd_id);
		map.put("fail_remark", fail_remark);
		ledgerMapper.signCD(map);
	}

	@Override
	public List<EntityPaper> getAllPaperLedgerList(Map<String, Object> map) throws Exception {
		logger.debug("getAllPaperLedgerList");
		String changedBarcode = basicService.changeBarcode((String) map.get("paper_barcode"));
		map.put("paper_barcode", changedBarcode);
		return ledgerMapper.getAllPaperLedgerList(map);
	}

	@Override
	public EntityPaper getOnePaperLedgerById(String paper_id) {
		logger.debug("getOnePaperLedgerById");
		return ledgerMapper.getOnePaperLedgerById(paper_id);
	}

	@Override
	public void updateRetrievePapers(Map<String, Object> map) {
		logger.debug("updateRetrievePapers");
		ledgerMapper.updateRetrievePapers(map);
	}

	@Override
	public void destroyRetrievedPapers(Map<String, Object> map) {
		logger.debug("destroyRetrievedPapers");
		ledgerMapper.destroyRetrievedPapers(map);
	}

	@Override
	public void updateHandlePapers(Map<String, Object> map) {
		logger.debug("updateHandlePapers");
		ledgerMapper.updateHandlePapers(map);
	}

	@Override
	public int getAllCDLedgerSize(Map<String, Object> map) throws Exception {
		logger.debug("getAllCDLedgerSize");
		String changedBarcode = basicService.changeBarcode((String) map.get("cd_barcode"));
		map.put("cd_barcode", changedBarcode);
		return ledgerMapper.getAllCDLedgerSize(map);
	}

	@Override
	public List<EntityPaper> getAllPaperLedgerList(Map<String, Object> map, RowBounds rbs) throws Exception {
		logger.debug("getAllPaperLedgerList");
		String changedBarcode = basicService.changeBarcode((String) map.get("paper_barcode"));
		map.put("paper_barcode", changedBarcode);
		return ledgerMapper.getAllPaperLedgerList(map, rbs);
	}

	@Override
	public int getAllPaperLedgerSize(Map<String, Object> map) throws Exception {
		logger.debug("getAllPaperLedgerSize");
		String changedBarcode = basicService.changeBarcode((String) map.get("paper_barcode"));
		map.put("paper_barcode", changedBarcode);
		return ledgerMapper.getAllPaperLedgerSize(map);
	}

	// @Override
	// public void saveEventTranfer(EventTransfer tranfer) {
	// logger.debug("saveEventTranfer");
	// ledgerMapper.saveEventTranfer(tranfer);
	// }
	//
	// @Override
	// public List<EventTransfer> getTransferEventList(Map<String, Object> map) {
	// logger.debug("getTransferEventList");
	// return ledgerMapper.getTransferEventList(map);
	// }

	@Override
	public List<EntityPaper> getPaperListByJobCode(String job_code) {
		logger.debug("getPaperListByJobCode");
		return ledgerMapper.getPaperListByJobCode(job_code);
	}

	@Override
	public void cancelHandlePaperById(String paper_id) {
		logger.debug("cancelHandlePaperById");
		// 先查询出该paper对应的job_code
		EntityPaper paper = ledgerMapper.getOnePaperLedgerById(paper_id);
		String job_code = paper.getJob_code();
		// 把event对应的job_code设置为null,取消审批；并把paper_state置为0,回到留用状态
		ledgerMapper.cancelHandlePaperById(paper_id);
		// 如果该job_code对应的event数量为空，表示该任务下挂载的作业已经都被取消了，则取消该任务
		if (StringUtils.hasLength(job_code) && ledgerMapper.getHandlePaperCountByJobCode(job_code) == 0) {
			String instance_id = basicService.getProcessJobByCode(job_code).getInstance_id();
			basicPrcManage.suspendProcessInstanceById(instance_id);
			basicService.delJob(job_code);
			clientService.delClientMsgByJobCode(job_code);
		}
	}

	@Override
	public EntityPaper getPaperByBarcode(String barcode) {
		logger.debug("getPaperByBarcode");
		return ledgerMapper.getPaperByBarcode(barcode);
	}

	@Override
	public List<CycleItem> getCycleItemListByBarcode(String barcode) {
		logger.debug("getCycleItemListByBarcode");
		return ledgerMapper.getCycleItemListByBarcode(barcode);
	}

	// @Override
	// public EventTransfer getOneTransferEventByCode(String event_code) {
	// logger.debug("getOneTransferEventByCode");
	// return ledgerMapper.getOneTransferEventByCode(event_code);
	// }

	@Override
	public String getDeptIdByUserId(String accept_user_iidd) {
		logger.debug("getOneTransferEventByCode");
		return ledgerMapper.getDeptIdByUserId(accept_user_iidd);
	}

	@Override
	public void deleteEventTransfer(String event_code, String barcode) {
		logger.debug("deleteEventTransfer");
		ledgerMapper.deleteEventTransfer(event_code);
		ledgerMapper.updatePaperTransferStatus(barcode);
	}

	@Override
	public void updateEventTransfer(Map<String, Object> map) {
		logger.debug("updateEventTransfer");
		ledgerMapper.updateEventTransfer(map);
	}

	//
	// @Override
	// public EventTransfer getTransferEventByTransferId(String id) {
	// logger.debug("getTransferEventByTransferId");
	// return ledgerMapper.getTransferEventByTransferId(id);
	// }
	//
	// @Override
	// public EventTransfer getTransferEventByTransferCode(String event_code) {
	// logger.debug("getTransferEventByTransferCode");
	// return ledgerMapper.getTransferEventByTransferCode(event_code);
	// }

	@Override
	public String getJobCodeByEventCode(String event_code) {
		logger.debug("getJobCodeByEventCode");
		return ledgerMapper.getJobCodeByEventCode(event_code);
	}

	@Override
	public int getAllDeviceLedgerSize(Map<String, Object> map) throws Exception {
		logger.debug("getAllDeviceLedgerSize");
		String changedBarcode = basicService.changeBarcode((String) map.get("device_barcode"));
		map.put("device_barcode", changedBarcode);
		return ledgerMapper.getAllDeviceLedgerSize(map);
	}

	@Override
	public List<EntityDevice> getAllDeviceLedgerList(Map<String, Object> map, RowBounds rbs) throws Exception {
		logger.debug("getAllDeviceLedgerList");
		String changedBarcode = basicService.changeBarcode((String) map.get("device_barcode"));
		map.put("device_barcode", changedBarcode);
		return ledgerMapper.getAllDeviceLedgerList(map, rbs);
	}

	@Override
	public List<EntityDevice> getAllDeviceLedgerList(Map<String, Object> map) throws Exception {
		logger.debug("getAllDeviceLedgerList");
		String changedBarcode = basicService.changeBarcode((String) map.get("device_barcode"));
		map.put("device_barcode", changedBarcode);
		return ledgerMapper.getAllDeviceLedgerList(map);
	}

	@Override
	public void cancelHandleCDById(String cd_id) {
		logger.debug("cancelHandleCDById");
		// 先查询出该cd对应的job_code
		EntityCD cd = ledgerMapper.getOneCDLedgerById(cd_id);
		String job_code = cd.getJob_code();
		// 把event对应的job_code设置为null,取消审批；并把cd_state置为0,回到留用状态
		ledgerMapper.cancelHandleCDById(cd_id);
		// 如果该job_code对应的event数量为空，表示该任务下挂载的作业已经都被取消了，则取消该任务
		if (StringUtils.hasLength(job_code) && ledgerMapper.getHandleCDCountByJobCode(job_code) == 0) {
			String instance_id = basicService.getProcessJobByCode(job_code).getInstance_id();
			basicPrcManage.suspendProcessInstanceById(instance_id);
			basicService.delJob(job_code);
			clientService.delClientMsgByJobCode(job_code);
		}
	}

	@Override
	public List<EntityCD> getCDListByJobCode(String job_code) {
		logger.debug("getPaperListByJobCode");
		return ledgerMapper.getCDListByJobCode(job_code);
	}

	@Override
	public void addCycleItem(CycleItem cycleitem) {
		logger.debug("addCycleItem");
		ledgerMapper.addCycleItem(cycleitem);
	}

	@Override
	public List<EntityDevice> getDeviceListByJobCode(String job_code) {
		logger.debug("getDeviceListByJobCode");
		return ledgerMapper.getDeviceListByJobCode(job_code);
	}

	@Override
	public void signPaper(String paper_id, String fail_remark) {
		logger.debug("signPaper");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paper_id", paper_id);
		map.put("fail_remark", fail_remark);
		ledgerMapper.signPaper(map);
	}

	@Override
	public EntityCD getCDByBarcode(String barcode) {
		logger.debug("getCDByBarcode");
		return ledgerMapper.getCDByBarcode(barcode);
	}

	@Override
	public void confirmSendCD(String cd_barcode, SecUser user, String comment) {
		logger.debug("confirmSendCD");
		EntityCD cd = getCDByBarcode(cd_barcode);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cd_id", cd.getCd_id());
		map.put("duty_user_iidd", cd.getDuty_user_iidd());
		map.put("duty_user_name", cd.getDuty_user_name());
		map.put("duty_dept_id", cd.getDuty_dept_id());
		map.put("duty_dept_name", cd.getDuty_dept_name());
		map.put("retrieve_time", new Date());
		map.put("retrieve_user_iidd", user.getUser_iidd());
		map.put("cd_state", 2);// 已外发
		ledgerMapper.updateEntityCD(map);

		String job_code = cd.getJob_code();
		ProcessJob job = basicService.getProcessJobByCode(job_code);
		String user_name = job.getUser_name();// 用户姓名
		String dept_name = job.getDept_name();// 部门名称
		String output_dept_name = job.getOutput_dept_name();// 接收单位
		String output_user_name = job.getOutput_user_name();// 接收人
		// 生成载体生命周期记录：外发
		CycleItem cycleitem_send = new CycleItem();
		cycleitem_send.setBarcode(cd_barcode);
		cycleitem_send.setEntity_type("CD");
		cycleitem_send.setOper_time(new Date());
		cycleitem_send.setUser_name(user_name);
		cycleitem_send.setDept_name(dept_name);
		cycleitem_send.setOper("SEND");
		ledgerMapper.addCycleItem(cycleitem_send);
		// 生成载体生命周期记录：接收
		CycleItem cycleitem_recv = new CycleItem();
		cycleitem_recv.setBarcode(cd_barcode);
		cycleitem_recv.setEntity_type("CD");
		cycleitem_recv.setOper_time(new Date());
		cycleitem_recv.setUser_name(output_user_name);
		cycleitem_recv.setDept_name(output_dept_name);
		cycleitem_recv.setOper("RECV");
		ledgerMapper.addCycleItem(cycleitem_recv);

		// 更新外发备注
		Map<String, Object> map_update = new HashMap<String, Object>();
		map_update.put("cd_barcode", cd_barcode);
		map_update.put("comment", comment);
		ledgerMapper.updateCDSendCommentByBarcode(map_update);
	}

	@Override
	public void confirmSendPaper(String paper_barcode, SecUser user, String comment) {
		logger.debug("confirmSendPaper");
		EntityPaper paper = getPaperByBarcode(paper_barcode);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paper_id", paper.getPaper_id());
		map.put("duty_user_iidd", paper.getDuty_user_iidd());
		map.put("duty_user_name", paper.getDuty_user_name());
		map.put("duty_dept_id", paper.getDuty_dept_id());
		map.put("duty_dept_name", paper.getDuty_dept_name());
		map.put("retrieve_time", new Date());
		map.put("retrieve_user_iidd", user.getUser_iidd());
		map.put("paper_state", 2);// 已外发
		ledgerMapper.updateEntityPaper(map);

		String job_code = paper.getJob_code();
		ProcessJob job = basicService.getProcessJobByCode(job_code);
		String user_name = job.getUser_name();// 用户姓名
		String dept_name = job.getDept_name();// 部门名称
		String output_dept_name = job.getOutput_dept_name();// 接收单位
		String output_user_name = job.getOutput_user_name();// 接收人
		// 生成载体生命周期记录：外发
		CycleItem cycleitem_send = new CycleItem();
		cycleitem_send.setBarcode(paper_barcode);
		cycleitem_send.setEntity_type("PAPER");
		cycleitem_send.setOper_time(new Date());
		cycleitem_send.setUser_name(user_name);
		cycleitem_send.setDept_name(dept_name);
		cycleitem_send.setOper("SEND");
		ledgerMapper.addCycleItem(cycleitem_send);
		// 生成载体生命周期记录：接收
		CycleItem cycleitem_recv = new CycleItem();
		cycleitem_recv.setBarcode(paper_barcode);
		cycleitem_recv.setEntity_type("PAPER");
		cycleitem_recv.setOper_time(new Date());
		cycleitem_recv.setUser_name(output_user_name);
		cycleitem_recv.setDept_name(output_dept_name);
		cycleitem_recv.setOper("RECV");
		ledgerMapper.addCycleItem(cycleitem_recv);

		// 更新外发备注
		Map<String, Object> map_update = new HashMap<String, Object>();
		map_update.put("paper_barcode", paper_barcode);
		map_update.put("comment", comment);
		ledgerMapper.updatePaperSendCommentByBarcode(map_update);
	}

	@Override
	public void rejectSendCD(RejectRecord record, Map<String, Object> map) {
		logger.debug("rejectSendCD");
		ledgerMapper.addRejectRecord(record);
		ledgerMapper.updateCDStateByBarcode(map);
		// 生成载体生命周期记录：外发拒收
		CycleItem cycleitem_send = new CycleItem();
		cycleitem_send.setBarcode(map.get("cd_barcode").toString());
		cycleitem_send.setEntity_type("CD");
		cycleitem_send.setOper_time(new Date());
		cycleitem_send.setUser_name(map.get("user_name").toString());
		cycleitem_send.setDept_name(map.get("dept_name").toString());
		// 外发拒收、归档拒收调用同一方法，根据MAP传值情况进行区分
		if (map.get("oper") != null && map.get("oper").toString().equals("FILEREJECT")) {
			cycleitem_send.setOper("FILEREJECT");
		} else {
			cycleitem_send.setOper("SENDREJECT");
		}

		ledgerMapper.addCycleItem(cycleitem_send);
	}

	@Override
	public void rejectSendPaper(RejectRecord record, Map<String, Object> map) {
		logger.debug("rejectSendPaper");
		ledgerMapper.addRejectRecord(record);
		ledgerMapper.updatePaperStateByBarcode(map);
		// 生成载体生命周期记录：外发拒收
		CycleItem cycleitem_send = new CycleItem();
		cycleitem_send.setBarcode(map.get("paper_barcode").toString());
		cycleitem_send.setEntity_type("PAPER");
		cycleitem_send.setOper_time(new Date());
		cycleitem_send.setUser_name(map.get("user_name").toString());
		cycleitem_send.setDept_name(map.get("dept_name").toString());
		// 外发拒收、归档拒收调用同一方法，根据MAP传值情况进行区分
		if (map.get("oper") != null && map.get("oper").toString().equals("FILEREJECT")) {
			cycleitem_send.setOper("FILEREJECT");
		} else {
			cycleitem_send.setOper("SENDREJECT");
		}
		ledgerMapper.addCycleItem(cycleitem_send);
	}

	@Override
	public List<SecDept> getDeptListByDeptAdminUserId(String deptadmin_user_iidd) {
		logger.debug("getDeptListByDeptAdminUserId：" + deptadmin_user_iidd);
		return ledgerMapper.getDeptListByDeptAdminUserId(deptadmin_user_iidd);
	}

	@Override
	public EntityPaper getPaperById(String id) {
		logger.debug("getPaperById：" + id);
		return ledgerMapper.getPaperById(id);
	}

	@Override
	public EntityDevice getDeviceByBarcode(String barcode) {
		logger.debug("getDeviceByBarcode：" + barcode);
		return ledgerMapper.getDeviceByBarcode(barcode);
	}

	@Override
	public EntityCD getCDById(String id) {
		logger.debug("getCDById：" + id);
		return ledgerMapper.getCDById(id);
	}

	@Override
	public Boolean checkShowPrintFile(String seclv_code) {
		logger.debug("checkShowPrintFile：" + seclv_code);
		String is_paper_audit = ledgerMapper.checkShowPrintFile(seclv_code);
		if (StringUtils.hasLength(is_paper_audit) && is_paper_audit.equalsIgnoreCase("Y")) {
			return true;
		} else
			return false;
	}

	@Override
	public Boolean checkShowBurnFile(String seclv_code) {
		logger.debug("checkShowBurnFile：" + seclv_code);
		String is_cd_audit = ledgerMapper.checkShowBurnFile(seclv_code);
		if (StringUtils.hasLength(is_cd_audit) && is_cd_audit.equalsIgnoreCase("Y")) {
			return true;
		} else
			return false;
	}

	@Override
	public void submitRetrievePaper(String id, SecUser user) throws Exception {

		if (basicService.isConfirmOpen("RETRIEVE_CONFIRM")) {
			// 交接确认开启
			ConfirmRecord record = new ConfirmRecord();
			record.setConfirm_dept_id(user.getDept_id());
			record.setConfirm_dept_name(user.getDept_name());
			record.setConfirm_user_iidd(user.getUser_iidd());
			record.setConfirm_user_name(user.getUser_name());
			record.setCreate_time(new Date());
			record.setEntity_type("PAPER");
			record.setConfirm_status("N");
			record.setConfirm_type("RETRIEVE");

			EntityPaper paper = ledgerMapper.getOnePaperLedgerById(id);
			if (paper.getPaper_state() == 10) {
				throw new Exception("该文件已经被其他管理员回收了");
			}
			record.setApply_dept_id(paper.getDuty_dept_id());
			record.setApply_dept_name(paper.getDuty_dept_name());
			record.setApply_user_iidd(paper.getDuty_user_iidd());
			record.setApply_user_name(paper.getDuty_user_name());
			record.setEntity_barcode(paper.getPaper_barcode());
			record.setEvent_code(paper.getEvent_code());
			record.setSeclv_name(paper.getSeclv_name());
			record.setEntity_name(paper.getFile_title());
			basicMapper.saveConfirmRecord(record);// 交接确认记录

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("paper_id", id);
			map.put("duty_user_iidd", paper.getDuty_user_iidd());
			map.put("duty_user_name", paper.getDuty_user_name());
			map.put("duty_dept_id", paper.getDuty_dept_id());
			map.put("duty_dept_name", paper.getDuty_dept_name());
			// map.put("retrieve_time", new Date());
			// map.put("retrieve_user_iidd", user.getUser_iidd());
			map.put("paper_state", 10);// 待交接
			ledgerMapper.updateRetrievePaper(map);
			logger.debug("submitRetrievePaper");
		} else {
			// 交接确认关闭
			EntityPaper paper = ledgerMapper.getOnePaperLedgerById(id);
			Date retrieve_time = new Date();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("paper_id", id);
			map.put("duty_user_iidd", user.getUser_iidd());
			map.put("duty_user_name", user.getUser_name());
			map.put("duty_dept_id", user.getDept_id());
			map.put("duty_dept_name", user.getDept_name());
			map.put("retrieve_time", retrieve_time);
			map.put("retrieve_user_iidd", user.getUser_iidd());
			map.put("paper_state", 1);// 已回收
			ledgerMapper.updateRetrievePaper(map);
			// 生成载体生命周期记录：回收
			CycleItem cycleitem_retrieve = new CycleItem();
			cycleitem_retrieve.setBarcode(paper.getPaper_barcode());
			cycleitem_retrieve.setEntity_type("PAPER");
			cycleitem_retrieve.setOper_time(retrieve_time);
			cycleitem_retrieve.setUser_name(user.getUser_name());
			cycleitem_retrieve.setDept_name(user.getDept_name());
			cycleitem_retrieve.setOper("RETRIEVE");
			ledgerMapper.addCycleItem(cycleitem_retrieve);
			// 生成载体生命周期记录：接收
			CycleItem cycleitem_recv = new CycleItem();
			cycleitem_recv.setBarcode(paper.getPaper_barcode());
			cycleitem_recv.setEntity_type("PAPER");
			cycleitem_recv.setOper_time(retrieve_time);
			cycleitem_recv.setUser_name(user.getUser_name());
			cycleitem_recv.setDept_name(user.getDept_name());
			cycleitem_recv.setOper("RECV");
			ledgerMapper.addCycleItem(cycleitem_recv);
			logger.debug("submitRetrievePaper");
		}
	}

	@Override
	public void submitRetrieveCD(String id, SecUser user) throws Exception {

		if (basicService.isConfirmOpen("RETRIEVE_CONFIRM")) {
			// 交接确认开启
			ConfirmRecord record = new ConfirmRecord();
			record.setConfirm_dept_id(user.getDept_id());
			record.setConfirm_dept_name(user.getDept_name());
			record.setConfirm_user_iidd(user.getUser_iidd());
			record.setConfirm_user_name(user.getUser_name());
			record.setCreate_time(new Date());
			record.setEntity_type("CD");
			record.setConfirm_status("N");
			record.setConfirm_type("RETRIEVE");

			EntityCD cd = ledgerMapper.getOneCDLedgerById(Integer.parseInt(id));
			if (cd.getCd_state() == 10) {
				throw new Exception("该光盘已经被其他管理员回收了");
			}
			record.setApply_dept_id(cd.getDuty_dept_id());
			record.setApply_dept_name(cd.getDuty_dept_name());
			record.setApply_user_iidd(cd.getDuty_user_iidd());
			record.setApply_user_name(cd.getDuty_user_name());
			record.setEntity_barcode(cd.getCd_barcode());
			record.setEvent_code(cd.getEvent_code());
			record.setEntity_name(cd.getFile_list());
			record.setSeclv_name(cd.getSeclv_name());
			basicMapper.saveConfirmRecord(record);// 交接确认记录

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cd_id", id);
			map.put("duty_user_iidd", cd.getDuty_user_iidd());
			map.put("duty_user_name", cd.getDuty_user_name());
			map.put("duty_dept_id", cd.getDuty_dept_id());
			map.put("duty_dept_name", cd.getDuty_dept_name());
			// map.put("retrieve_time", new Date());
			// map.put("retrieve_user_iidd", user.getUser_iidd());
			map.put("cd_state", 10);// 待交接
			ledgerMapper.updateRetrieveCD(map);
			logger.debug("submitRetrieveCD");
		} else {
			// 交接确认关闭
			EntityCD cd = ledgerMapper.getOneCDLedgerById(Integer.parseInt(id));
			Date retrieve_time = new Date();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cd_id", id);
			map.put("duty_user_iidd", user.getUser_iidd());
			map.put("duty_user_name", user.getUser_name());
			map.put("duty_dept_id", user.getDept_id());
			map.put("duty_dept_name", user.getDept_name());
			map.put("retrieve_time", retrieve_time);
			map.put("retrieve_user_iidd", user.getUser_iidd());
			map.put("cd_state", 1);// 已回收
			ledgerMapper.updateRetrieveCD(map);
			// 生成载体生命周期记录：回收
			CycleItem cycleitem_file = new CycleItem();
			cycleitem_file.setBarcode(cd.getCd_barcode());
			cycleitem_file.setEntity_type("CD");
			cycleitem_file.setOper_time(retrieve_time);
			cycleitem_file.setUser_name(user.getUser_name());
			cycleitem_file.setDept_name(user.getDept_name());
			cycleitem_file.setOper("RETRIEVE");
			ledgerMapper.addCycleItem(cycleitem_file);
			// 生成载体生命周期记录：接收
			CycleItem cycleitem_recv = new CycleItem();
			cycleitem_recv.setBarcode(cd.getCd_barcode());
			cycleitem_recv.setEntity_type("CD");
			cycleitem_recv.setOper_time(retrieve_time);
			cycleitem_recv.setUser_name(user.getUser_name());
			cycleitem_recv.setDept_name(user.getDept_name());
			cycleitem_recv.setOper("RECV");
			ledgerMapper.addCycleItem(cycleitem_recv);
			logger.debug("submitRetrieveCD");
		}

	}

	@Override
	public void quitHandlePaperById(String paper_id, SecUser user) {
		logger.debug("quitHandlePaperById");
		ledgerMapper.resetPaperState(paper_id);
		EntityPaper paper = ledgerMapper.getPaperById(paper_id);
		CycleItem cycleitem = new CycleItem();
		cycleitem.setBarcode(paper.getPaper_barcode());
		cycleitem.setEntity_type("PAPER");
		cycleitem.setOper_time(new Date());
		cycleitem.setUser_name(user.getUser_name());
		cycleitem.setDept_name(user.getDept_name());
		cycleitem.setOper(CycleOperEnum.RETRIEVEREJECT.getKey());
		ledgerMapper.addCycleItem(cycleitem);
	}

	@Override
	public void quitHandleCDById(String cd_id, SecUser user) {
		logger.debug("quitHandleCDById");
		ledgerMapper.resetCDState(cd_id);
		EntityCD cd = ledgerMapper.getCDById(cd_id);
		CycleItem cycleitem = new CycleItem();
		cycleitem.setBarcode(cd.getCd_barcode());
		cycleitem.setEntity_type("CD");
		cycleitem.setOper_time(new Date());
		cycleitem.setUser_name(user.getUser_name());
		cycleitem.setDept_name(user.getDept_name());
		cycleitem.setOper(CycleOperEnum.RETRIEVEREJECT.getKey());
		ledgerMapper.addCycleItem(cycleitem);
	}

	@Override
	public void rejectSendPaperToRetrieved(RejectRecord record, Map<String, Object> map, SecUser user) {
		logger.debug("rejectSendPaperToRetrieved");
		map.put("duty_user_iidd", user.getUser_iidd());
		map.put("duty_user_name", user.getUser_name());
		map.put("duty_dept_id", user.getDept_id());
		map.put("duty_dept_name", user.getDept_name());
		map.put("retrieve_time", new Date());
		map.put("retrieve_user_iidd", user.getUser_iidd());
		map.put("paper_state", 1);// 已回收
		ledgerMapper.updateRetrievePaper(map);
		ledgerMapper.addRejectRecord(record);
		// 生成载体生命周期记录：外发拒收
		CycleItem cycleitem_send = new CycleItem();
		cycleitem_send.setBarcode(map.get("paper_barcode").toString());
		cycleitem_send.setEntity_type("PAPER");
		cycleitem_send.setOper_time(new Date());
		cycleitem_send.setUser_name(user.getUser_name());
		cycleitem_send.setDept_name(user.getDept_name());
		cycleitem_send.setOper("SENDREJECT");
		ledgerMapper.addCycleItem(cycleitem_send);
		// 生成载体生命周期记录：回收
		CycleItem cycleitem_send_recycle = new CycleItem();
		cycleitem_send_recycle.setBarcode(map.get("paper_barcode").toString());
		cycleitem_send_recycle.setEntity_type("PAPER");
		cycleitem_send_recycle.setOper_time(new Date());
		cycleitem_send_recycle.setUser_name(user.getUser_name());
		cycleitem_send_recycle.setDept_name(user.getDept_name());
		cycleitem_send_recycle.setOper("RETRIEVE");
		ledgerMapper.addCycleItem(cycleitem_send_recycle);
	}

	@Override
	public void rejectSendCDToRetrieved(RejectRecord record, Map<String, Object> map, SecUser user) {
		logger.debug("rejectSendCDToRetrieved");
		map.put("duty_user_iidd", user.getUser_iidd());
		map.put("duty_user_name", user.getUser_name());
		map.put("duty_dept_id", user.getDept_id());
		map.put("duty_dept_name", user.getDept_name());
		map.put("retrieve_time", new Date());
		map.put("retrieve_user_iidd", user.getUser_iidd());
		map.put("cd_state", 1);// 已回收
		ledgerMapper.updateRetrieveCD(map);
		ledgerMapper.addRejectRecord(record);
		// 生成载体生命周期记录：外发拒收
		CycleItem cycleitem_send = new CycleItem();
		cycleitem_send.setBarcode(map.get("cd_barcode").toString());
		cycleitem_send.setEntity_type("CD");
		cycleitem_send.setOper_time(new Date());
		cycleitem_send.setUser_name(user.getUser_name());
		cycleitem_send.setDept_name(user.getDept_name());
		cycleitem_send.setOper("SENDREJECT");
		ledgerMapper.addCycleItem(cycleitem_send);
		// 生成载体生命周期记录：回收
		CycleItem cycleitem_send_recycle = new CycleItem();
		cycleitem_send_recycle.setBarcode(map.get("cd_barcode").toString());
		cycleitem_send_recycle.setEntity_type("CD");
		cycleitem_send_recycle.setOper_time(new Date());
		cycleitem_send_recycle.setUser_name(user.getUser_name());
		cycleitem_send_recycle.setDept_name(user.getDept_name());
		cycleitem_send_recycle.setOper("RETRIEVE");
		ledgerMapper.addCycleItem(cycleitem_send_recycle);
	}

	@Override
	public void updatePaperSendCommentByBarcode(Map<String, Object> map) {
		ledgerMapper.updatePaperSendCommentByBarcode(map);
		logger.debug("updatePaperSendCommentByBarcode");
	}

	@Override
	public void updateCDSendCommentByBarcode(Map<String, Object> map) {
		ledgerMapper.updateCDSendCommentByBarcode(map);
		logger.debug("updateCDSendCommentByBarcode");
	}

	@Override
	public List<RejectRecord> getRejectRecordByBarcode(String entity_barcode) {
		logger.debug("getRejectRecordByBarcode");
		return ledgerMapper.getRejectRecordByBarcode(entity_barcode);
	}

	@Override
	public void giveUpHandleCDById(String cd_id) {
		logger.debug("giveUpHandleCDById");
		ledgerMapper.resetCDState(cd_id);
	}

	@Override
	public void giveUpHandlePaperById(String paper_id) {
		logger.debug("giveUpHandlePaperById");
		ledgerMapper.resetPaperState(paper_id);
	}

	@Override
	public List<BurnEvent> getBurnEventByCdBarcode(String cd_barcode) {
		return ledgerMapper.getBurnEventByCdBarcode(cd_barcode);
	}
}
