package hdsec.web.project.transfer.service;

import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.basic.mapper.BasicMapper;
import hdsec.web.project.basic.model.ConfirmRecord;
import hdsec.web.project.basic.service.BasicService;
import hdsec.web.project.common.util.TimeUtil;
import hdsec.web.project.copy.service.CopyService;
import hdsec.web.project.ledger.mapper.LedgerMapper;
import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.ledger.service.LedgerService;
import hdsec.web.project.transfer.mapper.TransferMapper;
import hdsec.web.project.transfer.model.EventTransfer;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

public class TransferServiceImpl implements TransferService {
	private final Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private TransferMapper transferMapper;
	@Resource
	private LedgerMapper ledgerMapper;
	@Resource
	private LedgerService ledgerService;
	@Resource
	protected BasicService basicService;
	@Resource
	private BasicMapper basicMapper;
	@Resource
	protected CopyService copyService;

	@Override
	public int getAllPaperLedgerSize(Map<String, Object> map) {
		logger.debug("getAllPaperLedgerSize");
		return transferMapper.getAllPaperLedgerSize(map);
	}

	@Override
	public String getDeptIdByUserId(String accept_user_iidd) {
		logger.debug("getOneTransferEventByCode");
		return transferMapper.getDeptIdByUserId(accept_user_iidd);
	}

	@Override
	public void savePaperEventTranfer(EventTransfer transfer) {
		logger.debug("saveEventTranfer");
		transferMapper.saveEventTranfer(transfer);

	}

	@Override
	public List<EventTransfer> getTransferEventList(Map<String, Object> map) {
		logger.debug("getTransferEventList");
		return transferMapper.getTransferEventList(map);
	}

	@Override
	public EventTransfer getTransferEventByTransferCode(String event_code) {
		logger.debug("getTransferEventByTransferCode");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("event_code", event_code);
		return transferMapper.getTransferEvent(map);
	}

	@Override
	public EventTransfer getTransferEventByTransferId(String id) {
		logger.debug("getTransferEventByTransferId");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return transferMapper.getTransferEvent(map);
	}

	@Override
	public String getJobCodeByEventCode(String event_code) {
		logger.debug("getJobCodeByEventCode");
		return transferMapper.getJobCodeByEventCode(event_code);
	}

	@Override
	public EventTransfer getOneTransferEventByCode(String event_code) {
		logger.debug("getOneTransferEventByCode");
		return transferMapper.getOneTransferEventByCode(event_code);
	}

	@Override
	public void updateEventTransfer(Map<String, Object> map) {
		logger.debug("updateEventTransfer");
		transferMapper.updateEventTransfer(map);
	}

	@Override
	public int cancelTransferEventByEventCode(String event_code, String type) {
		logger.debug("cancelTransferEventByEventCode");
		int size = 0;
		EventTransfer transfer = transferMapper.getTransferEventByTransferCode(event_code);
		String job_code = transfer.getJob_code();
		List<EventTransfer> transfers = transferMapper.getTransferEventByJobCode(job_code);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("event_code", event_code);
		if ("cd".equals(type)) {
			transferMapper.cancelCDTransferEventByEventCode(map);
		} else if ("paper".equals(type)) {
			transferMapper.cancelPaperTransferEventByEventCode(map);
		}
		if (null != transfers && transfers.size() > 1) {
			size = transfers.size() - 1;
		} else {
			basicService.cancelJob(job_code, "TRANSFER");
		}
		return size;
	}

	@Override
	public EntityCD getOneCDLedgerById(int id) {
		logger.debug("getOneCDLedgerById");
		return transferMapper.getOneCDLedgerById(id);
	}

	@Override
	public void deleteEventTransfer(String event_code, String barcode) {
		logger.debug("deleteEventTransfer");
		transferMapper.deleteEventTransfer(event_code);
		transferMapper.updatePaperTransferStatus(barcode);
	}

	@Override
	public void saveCDEventTranfer(EventTransfer transfer) {
		logger.debug("saveCDEventTranfer");
		transferMapper.saveCDEventTranfer(transfer);
	}

	@Override
	public void updateEventTranfer(EventTransfer transfer) {
		logger.debug("updateEventTranfer");
		transferMapper.updateEventTranfer(transfer);

	}

	@Override
	public List<EventTransfer> getTransferEventByJobCode(String job_code) {
		logger.debug("getTransferEventByJobCode");
		return transferMapper.getTransferEventByJobCode(job_code);
	}

	@Override
	public void confirmTransfer(String event_code, String type, String barcode) {
		if ("CD".equals(type)) {
			EventTransfer event = transferMapper.getTransferEventByTransferCode(event_code);
			event.setFinish_time(new Date());
			event.setTransfer_status(1);// 已完成
			transferMapper.updateConfirmTransferEvent(event);

			EntityCD cd = ledgerService.getCDByBarcode(barcode);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cd_id", cd.getCd_id());
			map.put("duty_user_iidd", event.getAccept_user_iidd());
			map.put("duty_user_name", event.getAccept_user_name());
			map.put("duty_dept_id", event.getAccept_dept_id());
			map.put("duty_dept_name", event.getAccept_dept_name());
			map.put("cd_state", 0);// 留用
			map.put("expire_time", TimeUtil.getAfterXDay(7));// 到期时间为系统时间加7天
			ledgerMapper.updateEntityCD(map);
			// 生成载体生命周期记录：流转出
			CycleItem cycleitem_out = new CycleItem();
			cycleitem_out.setBarcode(cd.getCd_barcode());
			cycleitem_out.setEntity_type("CD");
			cycleitem_out.setOper_time(new Date());
			cycleitem_out.setUser_name(event.getUser_name());
			cycleitem_out.setDept_name(event.getDept_name());
			// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
			String job_code = getJobCodeByEventCode(event.getEvent_code());
			cycleitem_out.setJob_code(job_code);
			// add ending
			cycleitem_out.setOper("TRANSOUT");
			ledgerMapper.addCycleItem(cycleitem_out);
			// 生成载体生命周期记录：流转入
			CycleItem cycleitem_in = new CycleItem();
			cycleitem_in.setBarcode(cd.getCd_barcode());
			cycleitem_in.setEntity_type("CD");
			cycleitem_in.setOper_time(new Date());
			cycleitem_in.setUser_name(event.getAccept_user_name());
			cycleitem_in.setDept_name(event.getAccept_dept_name());
			// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
			// String job_code = getJobCodeByEventCode(event.getEvent_code());
			cycleitem_in.setJob_code(job_code);
			// add ending
			cycleitem_in.setOper("TRANSIN");
			ledgerMapper.addCycleItem(cycleitem_in);
			logger.debug("confirmTransfer");
		}
		if ("Paper".equals(type)) {
			EventTransfer event = transferMapper.getTransferEventByTransferCode(event_code);
			event.setFinish_time(new Date());
			event.setTransfer_status(1);// 已完成
			transferMapper.updateConfirmTransferEvent(event);

			EntityPaper paper = ledgerService.getPaperByBarcode(barcode);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("paper_id", paper.getPaper_id());
			map.put("duty_user_iidd", event.getAccept_user_iidd());
			map.put("duty_user_name", event.getAccept_user_name());
			map.put("duty_dept_id", event.getAccept_dept_id());
			map.put("duty_dept_name", event.getAccept_dept_name());
			map.put("paper_state", 0);// 留用
			map.put("expire_time", TimeUtil.getAfterXDay(7));// 到期时间为系统时间加7天
			ledgerMapper.updateEntityPaper(map);
			// 生成载体生命周期记录：流转出
			CycleItem cycleitem_out = new CycleItem();
			cycleitem_out.setBarcode(paper.getPaper_barcode());
			cycleitem_out.setEntity_type("PAPER");
			cycleitem_out.setOper_time(new Date());
			cycleitem_out.setUser_name(event.getUser_name());
			cycleitem_out.setDept_name(event.getDept_name());
			// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
			String job_code = getJobCodeByEventCode(event.getEvent_code());
			cycleitem_out.setJob_code(job_code);
			// add ending
			cycleitem_out.setOper("TRANSOUT");
			ledgerMapper.addCycleItem(cycleitem_out);
			// 生成载体生命周期记录：流转入
			CycleItem cycleitem_in = new CycleItem();
			cycleitem_in.setBarcode(paper.getPaper_barcode());
			cycleitem_in.setEntity_type("PAPER");
			cycleitem_in.setOper_time(new Date());
			cycleitem_in.setUser_name(event.getAccept_user_name());
			cycleitem_in.setDept_name(event.getAccept_dept_name());
			// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
			// String job_code = getJobCodeByEventCode(event.getEvent_code());
			cycleitem_in.setJob_code(job_code);
			// add ending
			cycleitem_in.setOper("TRANSIN");
			ledgerMapper.addCycleItem(cycleitem_in);
			logger.debug("confirmTransfer");
		}
	}

	@Override
	public List<EventTransfer> getTransferEventListByJobCode(String job_code, String type) {
		for (EventTransfer event : transferMapper.getTransferEventListByJobCode(job_code)) {
			if ("paper".equalsIgnoreCase(type)) {
				EntityPaper paper = ledgerService.getPaperByBarcode(event.getBarcode());
				if (null != paper) {
					event.setFile_name(paper.getFile_title());
				}
			} else {
				EntityCD cd = ledgerService.getCDByBarcode(event.getBarcode());
				if (null != cd) {
					event.setFile_name(cd.getFile_list());
				}
			}
		}
		return transferMapper.getTransferEventListByJobCode(job_code);
	}

	@Override
	public void cancelTransferEventByJobCode(String job_code, String type) {
		// TODO Auto-generated method stub
		List<EventTransfer> events = transferMapper.getTransferEventListByJobCode(job_code);
		if (null == events || events.size() == 0) {
			transferMapper.cancelJobByJobCode(job_code);
			return;
		}
		for (EventTransfer event : events) {
			cancelTransferEventByEventCode(event.getEvent_code(), type);
		}
	}

	@Override
	public List<ProcessJob> getJobListByPaper(Map<String, Object> map) {
		logger.debug("getJobListByPaper");
		return transferMapper.getJobListByPaper(map);
	}

	@Override
	public List<ProcessJob> getJobListByCD(Map<String, Object> map) {
		logger.debug("getJobListByCD");
		return transferMapper.getJobListByCD(map);
	}

	@Override
	public void addConfirmRecord(String event_code, String type, String barcode) {
		EventTransfer event = transferMapper.getTransferEventByTransferCode(event_code);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("event_code", event_code);
		map.put("transfer_status", 2);// 待交接
		transferMapper.updateEventTransferStatus(map);
		String entity_name = "";
		if ("CD".equals(type)) {
			EntityCD cd = ledgerService.getCDByBarcode(barcode);
			entity_name = cd.getFile_list();
		} else if ("Paper".equals(type)) {
			EntityPaper paper = ledgerService.getPaperByBarcode(barcode);
			entity_name = paper.getFile_title();
		}
		ConfirmRecord record = new ConfirmRecord(event.getUser_iidd(), event.getUser_name(), event.getDept_id(),
				event.getDept_name(), event.getAccept_user_iidd(), event.getAccept_user_name(),
				event.getAccept_dept_id(), event.getAccept_dept_name(), event.getEntity_type().toUpperCase(),
				event.getBarcode(), entity_name, event.getSeclv_name(), "TRANSFER", event.getEvent_code(), new Date());
		basicMapper.saveConfirmRecord(record);
	}

	@Override
	public void rejectTransfer(String event_code, String type, String barcode) {
		if ("CD".equals(type)) {
			EventTransfer event = transferMapper.getTransferEventByTransferCode(event_code);
			event.setFinish_time(new Date());
			event.setTransfer_status(3);// 已拒绝
			transferMapper.updateConfirmTransferEvent(event);

			EntityCD cd = ledgerService.getCDByBarcode(barcode);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cd_id", cd.getCd_id());
			map.put("duty_user_iidd", event.getUser_iidd());
			map.put("duty_user_name", event.getUser_name());
			map.put("duty_dept_id", event.getDept_id());
			map.put("duty_dept_name", event.getDept_name());
			map.put("cd_state", 0);// 留用

			ledgerMapper.updateEntityCD(map);

			// 生成载体生命周期记录：拒绝接受
			String job_code = getJobCodeByEventCode(event.getEvent_code());
			CycleItem cycleitem_in = new CycleItem();
			cycleitem_in.setBarcode(cd.getCd_barcode());
			cycleitem_in.setEntity_type("CD");
			cycleitem_in.setOper_time(new Date());
			cycleitem_in.setUser_name(event.getAccept_user_name());
			cycleitem_in.setDept_name(event.getAccept_dept_name());
			// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
			// String job_code = getJobCodeByEventCode(event.getEvent_code());
			cycleitem_in.setJob_code(job_code);
			// add ending
			cycleitem_in.setOper("TRANSIN_REJECT");
			ledgerMapper.addCycleItem(cycleitem_in);
			logger.debug("rejectTransfer");
		}
		if ("Paper".equals(type)) {
			EventTransfer event = transferMapper.getTransferEventByTransferCode(event_code);
			event.setFinish_time(new Date());
			event.setTransfer_status(3);// 已完成
			transferMapper.updateConfirmTransferEvent(event);

			EntityPaper paper = ledgerService.getPaperByBarcode(barcode);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("paper_id", paper.getPaper_id());
			map.put("duty_user_iidd", event.getUser_iidd());
			map.put("duty_user_name", event.getUser_name());
			map.put("duty_dept_id", event.getDept_id());
			map.put("duty_dept_name", event.getDept_name());
			map.put("paper_state", 0);// 留用
			ledgerMapper.updateEntityPaper(map);

			// 生成载体生命周期记录：拒绝接受
			String job_code = getJobCodeByEventCode(event.getEvent_code());
			CycleItem cycleitem_in = new CycleItem();
			cycleitem_in.setBarcode(paper.getPaper_barcode());
			cycleitem_in.setEntity_type("PAPER");
			cycleitem_in.setOper_time(new Date());
			cycleitem_in.setUser_name(event.getAccept_user_name());
			cycleitem_in.setDept_name(event.getAccept_dept_name());
			// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
			// String job_code = getJobCodeByEventCode(event.getEvent_code());
			cycleitem_in.setJob_code(job_code);
			// add ending
			cycleitem_in.setOper("TRANSIN_REJECT");
			ledgerMapper.addCycleItem(cycleitem_in);
			logger.debug("rejectTransfer");
		}

	}
}
