package hdsec.web.project.ledger.service;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.activiti.service.ActivitiService;
import hdsec.web.project.activiti.util.ActivitiCons;
import hdsec.web.project.basic.mapper.BasicMapper;
import hdsec.web.project.basic.model.ClientMsg;
import hdsec.web.project.basic.model.ConfirmRecord;
import hdsec.web.project.basic.model.RejectRecord;
import hdsec.web.project.basic.model.SysConfigItem;
import hdsec.web.project.basic.model.SysSeclevel;
import hdsec.web.project.basic.service.BasicPrcManage;
import hdsec.web.project.basic.service.BasicService;
import hdsec.web.project.burn.model.BurnEvent;
import hdsec.web.project.client.service.ClientService;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.PropertiesFileUtil;
import hdsec.web.project.common.util.TimeUtil;
import hdsec.web.project.device.model.EntityDevice;
import hdsec.web.project.enter.service.EnterService;
import hdsec.web.project.ledger.mapper.LedgerMapper;
import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.ledger.model.CycleOperEnum;
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
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecDept;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.service.UserService;

import java.util.ArrayList;
import java.util.Collections;
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
	private UserService userService;
	@Resource
	private ActivitiService activitiService;
	@Resource
	private BasicPrcManage basicPrcManage;
	@Resource
	private ClientService clientService;
	@Resource
	private BasicMapper basicMapper;
	@Resource
	private EnterService enterService;

	@Override
	public List<EntityCD> getAllCDLedgerList(Map<String, Object> map, RowBounds rbs) throws Exception {
		logger.debug("getAllCDLedgerList");
		String changedBarcode = basicService.changeBarcode((String) map.get("cd_barcode"));
		map.put("cd_barcode", changedBarcode);
		int totalSize = getAllCDLedgerSize(map);
		int beginIndex = rbs.getOffset();
		if (beginIndex > totalSize) {
			rbs = new RowBounds(0, 15);

		}
		return ledgerMapper.getAllCDLedgerList(map, rbs);
	}

	@Override
	public List<EntityCD> getAllCDLedgerList(Map<String, Object> map) throws Exception {
		logger.debug("getAllCDLedgerList");
		String changedBarcode = basicService.changeBarcode((String) map.get("cd_barcode"));
		map.put("cd_barcode", changedBarcode);
		String barcodes = "";
		String[] cd_barcodes = (String[]) map.get("barcodes");
		if (!"".equals(cd_barcodes) && cd_barcodes != null) {
			for (int i = 0; i < cd_barcodes.length; i++) {
				String changedBarcodes = basicService.changeBarcode(cd_barcodes[i]);
				barcodes = barcodes + "," + changedBarcodes;
			}
			map.put("barcodes", barcodes.split(","));
		}
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
	public void signCD(String cd_id, String file_num, String file_list, String fail_remark) {
		logger.debug("signCD");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cd_id", cd_id);
		map.put("file_num", file_num);
		map.put("file_list", file_list);
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
		String barcodes = "";
		String[] cd_barcodes = (String[]) map.get("barcodes");
		if (!"".equals(cd_barcodes) && cd_barcodes != null) {
			for (int i = 0; i < cd_barcodes.length; i++) {
				String changedBarcodes = basicService.changeBarcode(cd_barcodes[i]);
				barcodes = barcodes + "," + changedBarcodes;
			}
			map.put("barcodes", barcodes.split(","));
		}
		return ledgerMapper.getAllCDLedgerSize(map);
	}

	@Override
	public List<EntityPaper> getAllPaperLedgerList(Map<String, Object> map, RowBounds rbs) throws Exception {
		logger.debug("getAllPaperLedgerList");
		String changedBarcode = basicService.changeBarcode((String) map.get("paper_barcode"));
		map.put("paper_barcode", changedBarcode);
		int totalSize = getAllPaperLedgerSize(map);
		int beginIndex = rbs.getOffset();
		if (beginIndex > totalSize) {
			rbs = new RowBounds(0, 15);

		}
		String barcodes = "";
		String[] paper_barcodes = (String[]) map.get("barcodes");
		if (!"".equals(paper_barcodes) && paper_barcodes != null) {
			for (int i = 0; i < paper_barcodes.length; i++) {
				String changedBarcodes = basicService.changeBarcode(paper_barcodes[i]);
				barcodes = barcodes + "," + changedBarcodes;
			}
			map.put("barcodes", barcodes.split(","));
		}
		return ledgerMapper.getAllPaperLedgerList(map, rbs);
	}

	@Override
	public int getAllPaperLedgerSize(Map<String, Object> map) throws Exception {
		logger.debug("getAllPaperLedgerSize");
		String changedBarcode = basicService.changeBarcode((String) map.get("paper_barcode"));
		map.put("paper_barcode", changedBarcode);
		String barcodes = "";
		String[] paper_barcodes = (String[]) map.get("barcodes");
		if (!"".equals(paper_barcodes) && paper_barcodes != null) {
			for (int i = 0; i < paper_barcodes.length; i++) {
				String changedBarcodes = basicService.changeBarcode(paper_barcodes[i]);
				barcodes = barcodes + "," + changedBarcodes;
			}
			map.put("barcodes", barcodes.split(","));
		}
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
	public void signPaper(String paper_id, String fail_remark, Integer page_count) {
		logger.debug("signPaper");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paper_id", paper_id);
		map.put("fail_remark", fail_remark);
		map.put("page_count", page_count);
		ledgerMapper.signPaper(map);
	}

	@Override
	public EntityCD getCDByBarcode(String barcode) {
		logger.debug("getCDByBarcode");
		return ledgerMapper.getCDByBarcode(barcode);
	}

	@Override
	public void confirmSendCD(String cd_barcode, SecUser user, String comment, String output_confidential_num) {
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
		map.put("output_confidential_num", output_confidential_num);// 外发机要号
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
		// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
		cycleitem_send.setJob_code(job_code);
		ledgerMapper.addCycleItem(cycleitem_send);
		// 生成载体生命周期记录：接收
		CycleItem cycleitem_recv = new CycleItem();
		cycleitem_recv.setBarcode(cd_barcode);
		cycleitem_recv.setEntity_type("CD");
		cycleitem_recv.setOper_time(new Date());
		cycleitem_recv.setUser_name(output_user_name);
		cycleitem_recv.setDept_name(output_dept_name);
		cycleitem_recv.setOper("RECV");
		// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
		// 此时entity_cd的job_code记录的就是外发时的job_code
		cycleitem_recv.setJob_code(job_code);
		ledgerMapper.addCycleItem(cycleitem_recv);

		// 更新外发备注
		Map<String, Object> map_update = new HashMap<String, Object>();
		map_update.put("cd_barcode", cd_barcode);
		map_update.put("comment", comment);
		ledgerMapper.updateCDSendCommentByBarcode(map_update);
	}

	@Override
	public void confirmSendPaper(String paper_barcode, SecUser user, String comment, String output_confidential_num) {
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
		map.put("output_confidential_num", output_confidential_num);// 外发机要号
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
		// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
		cycleitem_send.setJob_code(job_code);
		ledgerMapper.addCycleItem(cycleitem_send);
		// 生成载体生命周期记录：接收
		CycleItem cycleitem_recv = new CycleItem();
		cycleitem_recv.setBarcode(paper_barcode);
		cycleitem_recv.setEntity_type("PAPER");
		cycleitem_recv.setOper_time(new Date());
		cycleitem_recv.setUser_name(output_user_name);
		cycleitem_recv.setDept_name(output_dept_name);
		cycleitem_recv.setOper("RECV");
		// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
		cycleitem_recv.setJob_code(job_code);
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
		ledgerMapper.updateJobProcessByJobcode(map.get("job_code").toString());
		EntityCD cd = ledgerMapper.getCDByBarcode(map.get("cd_barcode").toString());
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
		// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
		// 此时entity_cd的job_code记录的就是归档时的job_code
		cycleitem_send.setJob_code(cd.getJob_code());
		ledgerMapper.addCycleItem(cycleitem_send);
	}

	@Override
	public void rejectSendPaper(RejectRecord record, Map<String, Object> map) {
		logger.debug("rejectSendPaper");
		ledgerMapper.addRejectRecord(record);
		ledgerMapper.updatePaperStateByBarcode(map);
		ledgerMapper.updateJobProcessByJobcode(map.get("job_code").toString());
		EntityPaper paper = ledgerMapper.getPaperByBarcode(map.get("paper_barcode").toString());
		// 生成载体生命周期记录：外发拒收
		CycleItem cycleitem_send = new CycleItem();
		cycleitem_send.setBarcode(map.get("paper_barcode").toString());
		cycleitem_send.setEntity_type("PAPER");
		cycleitem_send.setOper_time(new Date());
		cycleitem_send.setUser_name(map.get("user_name").toString());
		cycleitem_send.setDept_name(map.get("dept_name").toString());
		// cycleitem_send.setJob_code(map.get("job_code").toString());
		// 外发拒收、归档拒收调用同一方法，根据MAP传值情况进行区分
		if (map.get("oper") != null && map.get("oper").toString().equals("FILEREJECT")) {
			cycleitem_send.setOper("FILEREJECT");
		} else {
			cycleitem_send.setOper("SENDREJECT");
		}
		// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
		// 此时entity_paper的job_code记录的就是归档时的job_code
		cycleitem_send.setJob_code(paper.getJob_code());
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
			map.put("retrieve_time", retrieve_time);
			map.put("retrieve_user_iidd", user.getUser_iidd());
			map.put("duty_user_iidd", user.getUser_iidd());
			map.put("duty_user_name", user.getUser_name());
			map.put("duty_dept_id", user.getDept_id());
			map.put("duty_dept_name", user.getDept_name());
			int recover = basicService.getSysConfigItemValue(SysConfigItem.KEY_Recover_On_Off).getStartuse();
			int destroy = basicService.getSysConfigItemValue(SysConfigItem.KEY_SendDestroy_On_Off).getStartuse();
			if (recover == 1 && destroy == 0) {
				map.put("paper_state", 11);
			} else {
				map.put("paper_state", 1);
			}

			ledgerMapper.updateRetrievePaper(map);
			// 生成载体生命周期记录：回收
			CycleItem cycleitem_retrieve = new CycleItem();
			cycleitem_retrieve.setBarcode(paper.getPaper_barcode());
			cycleitem_retrieve.setEntity_type("PAPER");
			cycleitem_retrieve.setOper_time(retrieve_time);
			cycleitem_retrieve.setUser_name(user.getUser_name());
			cycleitem_retrieve.setDept_name(user.getDept_name());
			cycleitem_retrieve.setOper("RETRIEVE");
			// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
			// 此时entity_paper的job_code记录的就是归档时的job_code
			cycleitem_retrieve.setJob_code(paper.getJob_code());
			ledgerMapper.addCycleItem(cycleitem_retrieve);
			// 生成载体生命周期记录：接收
			CycleItem cycleitem_recv = new CycleItem();
			cycleitem_recv.setBarcode(paper.getPaper_barcode());
			cycleitem_recv.setEntity_type("PAPER");
			cycleitem_recv.setOper_time(retrieve_time);
			cycleitem_recv.setUser_name(user.getUser_name());
			cycleitem_recv.setDept_name(user.getDept_name());
			cycleitem_recv.setOper("RECV");
			// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
			// 此时entity_paper的job_code记录的就是归档时的job_code
			cycleitem_recv.setJob_code(paper.getJob_code());
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
			map.put("retrieve_time", retrieve_time);
			map.put("retrieve_user_iidd", user.getUser_iidd());
			map.put("duty_user_iidd", user.getUser_iidd());
			map.put("duty_user_name", user.getUser_name());
			map.put("duty_dept_id", user.getDept_id());
			map.put("duty_dept_name", user.getDept_name());
			int recover = basicService.getSysConfigItemValue(SysConfigItem.KEY_Recover_On_Off).getStartuse();
			int destroy = basicService.getSysConfigItemValue(SysConfigItem.KEY_SendDestroy_On_Off).getStartuse();
			if (recover == 1 && destroy == 0) {
				map.put("cd_state", 11);
			} else {
				map.put("cd_state", 1);
			}

			ledgerMapper.updateRetrieveCD(map);
			// 生成载体生命周期记录：回收
			CycleItem cycleitem_file = new CycleItem();
			cycleitem_file.setBarcode(cd.getCd_barcode());
			cycleitem_file.setEntity_type("CD");
			cycleitem_file.setOper_time(retrieve_time);
			cycleitem_file.setUser_name(user.getUser_name());
			cycleitem_file.setDept_name(user.getDept_name());
			cycleitem_file.setOper("RETRIEVE");
			// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
			// 此时entity_paper的job_code记录的就是归档时的job_code
			cycleitem_file.setJob_code(cd.getJob_code());
			ledgerMapper.addCycleItem(cycleitem_file);
			// 生成载体生命周期记录：接收
			CycleItem cycleitem_recv = new CycleItem();
			cycleitem_recv.setBarcode(cd.getCd_barcode());
			cycleitem_recv.setEntity_type("CD");
			cycleitem_recv.setOper_time(retrieve_time);
			cycleitem_recv.setUser_name(user.getUser_name());
			cycleitem_recv.setDept_name(user.getDept_name());
			cycleitem_recv.setOper("RECV");
			// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
			// 此时entity_paper的job_code记录的就是归档时的job_code
			cycleitem_recv.setJob_code(cd.getJob_code());
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
		// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
		// 此处不用启动流程，job_code设置为default
		cycleitem.setJob_code("default");
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
		// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
		// 此处不用启动流程，job_code设置为default
		cycleitem.setJob_code("default");
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
		// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
		// 此处不用启动流程，job_code设置为default
		cycleitem_send.setJob_code("default");
		ledgerMapper.addCycleItem(cycleitem_send);
		// 生成载体生命周期记录：回收
		CycleItem cycleitem_send_recycle = new CycleItem();
		cycleitem_send_recycle.setBarcode(map.get("paper_barcode").toString());
		cycleitem_send_recycle.setEntity_type("PAPER");
		cycleitem_send_recycle.setOper_time(new Date());
		cycleitem_send_recycle.setUser_name(user.getUser_name());
		cycleitem_send_recycle.setDept_name(user.getDept_name());
		cycleitem_send_recycle.setOper("RETRIEVE");
		// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
		// 此处不用启动流程，job_code设置为default
		cycleitem_send_recycle.setJob_code("default");
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
		// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
		// 此处不用启动流程，job_code设置为default
		cycleitem_send.setJob_code("default");
		ledgerMapper.addCycleItem(cycleitem_send);
		// 生成载体生命周期记录：回收
		CycleItem cycleitem_send_recycle = new CycleItem();
		cycleitem_send_recycle.setBarcode(map.get("cd_barcode").toString());
		cycleitem_send_recycle.setEntity_type("CD");
		cycleitem_send_recycle.setOper_time(new Date());
		cycleitem_send_recycle.setUser_name(user.getUser_name());
		cycleitem_send_recycle.setDept_name(user.getDept_name());
		cycleitem_send_recycle.setOper("RETRIEVE");
		// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
		// 此处不用启动流程，job_code设置为default
		cycleitem_send_recycle.setJob_code("default");
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
		// 判断文件是否撤销完毕 by chenhong
		String job_code = ledgerMapper.getJobcodeByCDid(cd_id);
		// ①判断event表
		int nCountBurn = ledgerMapper.getAllUnCDReceiptSize(job_code);
		if (0 == nCountBurn) {
			// ②判断台账表
			int nCountCD = ledgerMapper.getAllReceiptCDSize(job_code);
			if (1 == nCountCD) {
				ledgerMapper.updateReceipInfo(job_code);
			}
		}
		ledgerMapper.resetCDState(cd_id);
	}

	@Override
	public void giveUpHandlePaperById(String paper_id) {
		logger.debug("giveUpHandlePaperById");
		// 判断文件是否撤销完毕 by chenhong
		String job_code = ledgerMapper.getJobcodeByPaperid(paper_id);
		// ①判断event表
		int nCountprint = ledgerMapper.getAllUnprintReceiptSize(job_code);
		int nCountcopy = ledgerMapper.getAllUncopyReceiptSize(job_code);
		nCountprint = nCountprint + nCountcopy;
		if (0 == nCountprint) {
			// ②判断台账表
			int nCountPaper = ledgerMapper.getAllReceiptSize(job_code);
			if (1 == nCountPaper) {
				ledgerMapper.updateReceipInfo(job_code);
			}
		}
		ledgerMapper.resetPaperState(paper_id);
	}

	@Override
	public List<String> getDeptIdByParentId(String dept_parent_id) {
		return ledgerMapper.getDeptIdByParentId(dept_parent_id);
	}

	@Override
	public void updateRetrieveComment(String paper_id, String comment) {
		// 更新文件回收备注
		Map<String, Object> map_update = new HashMap<String, Object>();
		map_update.put("paper_id", paper_id);
		map_update.put("comment", comment);
		ledgerMapper.updateRetrieveComment(map_update);
	}

	@Override
	public void updateRejectPaperComment(String id, String comment) {
		// 更新退回文件备注
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paper_id", id);
		map.put("comment", comment);
		ledgerMapper.updateRejectPaperComment(map);
	}

	@Override
	public List<EntityPaper> getDestroyPaperList(Map<String, Object> map, RowBounds rbs) {
		return ledgerMapper.getDestroyPaperList(map, rbs);
	}

	@Override
	public List<EntityPaper> getFilePaperList(Map<String, Object> map, RowBounds rbs) {
		return ledgerMapper.getFilePaperList(map, rbs);
	}

	@Override
	public List<EntityPaper> getSendPaperList(Map<String, Object> map, RowBounds rbs) {
		return ledgerMapper.getSendPaperList(map, rbs);
	}

	@Override
	public List<EntityPaper> getDelayPaperList(Map<String, Object> map, RowBounds rbs) {
		return ledgerMapper.getDelayPaperList(map, rbs);
	}

	@Override
	public List<EntityCD> getDestroyCDList(Map<String, Object> map, RowBounds rbs) {
		return ledgerMapper.getDestroyCDList(map, rbs);
	}

	@Override
	public List<EntityCD> getFileCDList(Map<String, Object> map, RowBounds rbs) {
		return ledgerMapper.getFileCDList(map, rbs);
	}

	@Override
	public List<EntityCD> getSendCDList(Map<String, Object> map, RowBounds rbs) {
		return ledgerMapper.getSendCDList(map, rbs);
	}

	@Override
	public List<EntityCD> getDelayCDList(Map<String, Object> map, RowBounds rbs) {
		return ledgerMapper.getDelayCDList(map, rbs);
	}

	@Override
	public List<ProcessJob> getSendDestroyPaperJobList(Map<String, Object> map) {
		logger.debug("getSendDestroyPaperJobList");
		return ledgerMapper.getSendDestroyPaperJobList(map);
	}

	@Override
	public List<SendDestroyEvent> getSendDestroyEventListByJobCode(String job_code) {
		logger.debug("getSendDestroyEventListByJobCode");
		return ledgerMapper.getSendDestroyEventListByJobCode(job_code);
	}

	@Override
	public List<EntityPaper> getSendDestroyPaperListByJobCode(String job_code) {
		logger.debug("getSendDestroyPaperListByJobCode");
		List<EntityPaper> paperList = new ArrayList<EntityPaper>();
		List<SendDestroyEvent> eventList = ledgerMapper.getSendDestroyEventListByJobCode(job_code);
		for (SendDestroyEvent event : eventList) {
			EntityPaper paper = ledgerMapper.getPaperByBarcode(event.getBarcode());
			if (paper != null) {
				paperList.add(paper);
			}
		}
		return paperList;
	}

	@Override
	public void cancelSendDestroyEventByJobCode(String job_code, String type) {
		List<SendDestroyEvent> eventList = ledgerMapper.getSendDestroyEventListByJobCode(job_code);
		for (SendDestroyEvent event : eventList) {
			if (type.equals("paper")) {
				ledgerMapper.cancelSendDestroyPaperEventByEventCode(event.getEvent_code());
			} else if (type.equals("cd")) {
				ledgerMapper.cancelSendDestroyCDEventByEventCode(event.getEvent_code());
			}
		}
		String instance_id = basicService.getProcessJobByCode(job_code).getInstance_id();
		basicPrcManage.suspendProcessInstanceById(instance_id);
		ledgerMapper.cancelJobByJobCode(job_code);
		clientService.delClientMsgByJobCode(job_code);
	}

	@Override
	public List<ProcessJob> getSendDestroyCDJobList(Map<String, Object> map) {
		logger.debug("getSendDestroyCDJobList");
		return ledgerMapper.getSendDestroyCDJobList(map);
	}

	@Override
	public List<EntityCD> getSendDestroyCDListByJobCode(String job_code) {
		logger.debug("getSendDestroyCDListByJobCode");
		List<EntityCD> cdList = new ArrayList<EntityCD>();
		List<SendDestroyEvent> eventList = ledgerMapper.getSendDestroyEventListByJobCode(job_code);
		for (SendDestroyEvent event : eventList) {
			EntityCD cd = ledgerMapper.getCDByBarcode(event.getBarcode());
			if (cd != null) {
				cdList.add(cd);
			}
		}
		return cdList;
	}

	@Override
	public void updateDelayDays(Map<String, Object> map) {
		logger.debug("updateDelayDays");
		ledgerMapper.updateDelayDays(map);
	}

	@Override
	public void updateCDDelayDays(Map<String, Object> map) {
		logger.debug("updateCDDelayDays");
		ledgerMapper.updateCDDelayDays(map);

	}

	@Override
	public List<PaperType> getPaperConversionRate() {
		return ledgerMapper.getPaperConversionRate();
	}

	@Override
	public void deletePaperConversionRateByTypeName(String type_name) {
		ledgerMapper.deletePaperConversionRateByTypeName(type_name);
	}

	@Override
	public void updatePaperConversionRateByTypeName(String type_name, double conversion_rate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type_name", type_name);
		map.put("conversion_rate", conversion_rate);
		ledgerMapper.updatePaperConversionRateByTypeName(map);
	}

	@Override
	public void addPaperConversionRateByTypeName(String type_name, double conversion_rate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type_name", type_name);
		map.put("conversion_rate", conversion_rate);
		ledgerMapper.addPaperConversionRateByTypeName(map);
	}

	@Override
	public void addOrUpdateFailRemarkByBarcode(String barcode, String comment) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("barcode", barcode);
		map.put("commnet", comment);
		ledgerMapper.addOrUpdateFailRemarkByBarcode(map);
	}

	@Override
	public void addOrUpdateFailRemarkByBarcodeAndTime(String barcode, String comment) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("barcode", barcode);
		map.put("commnet", comment);
		ledgerMapper.addOrUpdateFailRemarkByBarcodeAndTime(map);
	}

	@Override
	public List<EntityPaper> getAllReplacePaperList(Map<String, Object> map, RowBounds rbs) {
		logger.debug("getAllReplacePaperList");
		return ledgerMapper.getAllReplacePaperList(map, rbs);
	}

	@Override
	public int getAllReplacePaperSize(Map<String, Object> map) {
		logger.debug("getAllReplacePaperSize");
		return ledgerMapper.getAllReplacePaperSize(map);
	}

	@Override
	public void updateRetrievedPage(Map<String, Object> map) {
		logger.debug("updateRetrievedPage");
		ledgerMapper.updateRetrievedPage(map);
	}

	@Override
	public List<String> getPIDBarcode() {
		logger.debug("getPIDBarcode");
		return ledgerMapper.getPIDBarcode();
	}

	@Override
	public List<String> getPaperBarcode(String barcode) {
		logger.debug("getPaperBarcode");
		return ledgerMapper.getPaperBarcode(barcode);
	}

	@Override
	public void updateDestroyedPage(Map<String, Object> map) {
		logger.debug("updateDestroyedPage");
		ledgerMapper.updateDestroyedPage(map);
	}

	@Override
	public void addProcessJob(String user_iidd, String dept_id, Integer seclv_code, String event_ids,
			String entity_type, Integer modify_status, Integer trg_seclv, String usage_code, String project_code,
			String summ, Map<String, String> getFileTitleList, String next_approver) throws Exception {
		// 密级变更添加流程和event
		ApproveProcess process = basicPrcManage.getApproveProcessByKey(dept_id, String.valueOf(seclv_code),
				JobTypeEnum.MODIFY_SECLV.getJobTypeCode(), usage_code, true);
		String job_status = null;
		if (process.getTotal_steps() == 0) {
			job_status = ActivitiCons.APPLY_APPROVED_PASS;
		} else {
			job_status = ActivitiCons.APPLY_APPROVED_DEFAULT;
		}
		String next_approver_name = basicPrcManage.getApproverName(next_approver);
		String job_code = user_iidd + "-" + JobTypeEnum.MODIFY_SECLV.getJobTypeCode() + "-"
				+ System.currentTimeMillis();
		ProcessJob job = new ProcessJob(job_code, user_iidd, dept_id, trg_seclv, JobTypeEnum.MODIFY_SECLV, new Date(),
				job_status, next_approver, next_approver_name, process.getProcess_id());
		job.setComment(summ);
		// 开启流程
		basicPrcManage.addActivitiApply(job, process);
		// 把任务信息插入数据库
		basicMapper.addProcessJob(job);
		for (String barcode : event_ids.split(":")) {
			if (entity_type.equals("Paper")) {
				EntityPaper entity = ledgerMapper.getPaperByBarcode(barcode);
				String event_code = user_iidd + entity.getPaper_id() + System.currentTimeMillis();
				EventModify event = new EventModify(event_code, entity_type, entity.getPaper_barcode(), modify_status,
						entity.getSeclv_code(), trg_seclv, new Date(), null, entity.getUser_iidd(),
						entity.getDept_id(), trg_seclv, usage_code, project_code, summ, getFileTitleList);
				event.setJob_code(job_code);
				ledgerMapper.addEventModify(event);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("paper_barcode", barcode);
				map.put("paper_state", 13);// 将状态更新为“变更中”
				// 更新entity表中的paper_state;
				ledgerMapper.updatePaperState(map);
			} else {
				EntityCD entity = ledgerMapper.getCDByBarcode(barcode);
				String event_code = user_iidd + entity.getCd_id() + System.currentTimeMillis();
				EventModify event = new EventModify(event_code, entity_type, entity.getCd_barcode(), modify_status,
						entity.getSeclv_code(), trg_seclv, new Date(), null, entity.getUser_iidd(),
						entity.getDept_id(), trg_seclv, usage_code, project_code, summ, getFileTitleList);
				event.setJob_code(job_code);
				ledgerMapper.addEventModify(event);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cd_barcode", barcode);
				map.put("cd_state", 13);// 将状态更新为“变更中”
				// 更新entity表中的paper_state;
				ledgerMapper.updateCDState(map);
			}
		}
		// 更新作业信息
		String user_name = userService.getUserNameByUserId(job.getUser_iidd());
		String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
		String detail = "提交" + JobTypeEnum.MODIFY_SECLV.getJobTypeName() + "申请";
		ProcessRecord record = new ProcessRecord(job.getJob_code(), job.getJobType(), job.getUser_iidd(), user_name,
				dept_name, detail, "请审批", new Date());
		activitiService.addProcessRecord(record);
		// 如果有审批流程，在消息表中添加审批消息
		if (process.getTotal_steps() != 0) {
			String message = dept_name + user_name + "有" + JobTypeEnum.MODIFY_SECLV.getJobTypeName() + "作业需要您审批";
			String oper_type = "MODIFY_SECLV";
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(), message,
						new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		} else {// 流程步骤为0时直接变更密级
			confimModify(job.getJob_code());
		}

	}

	@Override
	public void confimModify(String job_code) {// 密级变更确认
		logger.debug("confimModify:");
		List<EventModify> eventList = new ArrayList<EventModify>();
		eventList = getModifyEventByJobCode(job_code);
		for (EventModify event : eventList) {
			Map<String, Object> updmap = new HashMap<String, Object>();
			updmap.put("event_code", event.getEvent_code());
			updmap.put("finish_time", new Date());
			updmap.put("modify_status", "1");
			updateConfirmModifyEvent(updmap);
			if (event.getEntity_type().equals("Paper")) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("paper_barcode", event.getBarcode());
				map.put("seclv_code", event.getTrg_seclv());
				map.put("paper_state", "0");// 状态改为留用
				// 确认密级变更后更改载体信息
				confirmEntityPaper(map);
			} else {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cd_barcode", event.getBarcode());
				map.put("seclv_code", event.getTrg_seclv());
				map.put("cd_state", "0");// 状态改为留用
				// 确认密级变更后更改载体信息
				confirmEntityCD(map);
			}
		}
	}

	@Override
	public void addEventModify(EventModify event) {
		ledgerMapper.addEventModify(event);

	}

	@Override
	public List<ProcessJob> getModifyCandidateListByUserId(String user_iidd) {
		List<String> instanceIdList = basicPrcManage.getCandidateInstanceIdList(user_iidd,
				JobTypeEnum.MODIFY_SECLV.getJobTypeCode());
		if (instanceIdList != null && instanceIdList.size() > 0) {
			return ledgerMapper.getModifyProcessJobListByInstanceId(instanceIdList);
		}
		return Collections.emptyList();
	}

	@Override
	public List<EventModify> getModifyEventListByJobCode(String job_code) {
		logger.debug("getModifyEventListByJobCode" + job_code);
		return ledgerMapper.getModifyEventListByJobCode(job_code);
	}

	@Override
	public EventModify getModifyEventByCode(String event_code) {
		logger.debug("getModifyEventByCode" + event_code);
		return ledgerMapper.getModifyEventByCode(event_code);
	}

	@Override
	public String getModifyJobCodeByEventCode(String event_code) {
		logger.debug("getModifyEventByCode" + event_code);
		return ledgerMapper.getModifyJobCodeByEventCode(event_code);
	}

	@Override
	public void updateConfirmModifyEvent(Map<String, Object> map) {
		logger.debug("getModifyEventByCode");
		ledgerMapper.updateConfirmModifyEvent(map);
	}

	@Override
	public List<EventModify> getModifyEventList(Map<String, Object> map) {
		logger.debug("getModifyEventList");
		return ledgerMapper.getModifyEventList(map);
	}

	@Override
	public void updatePaperState(Map<String, Object> map) {
		logger.debug("updatePaperState");
		ledgerMapper.updatePaperState(map);
	}

	@Override
	public void confirmEntityPaper(Map<String, Object> map) {
		logger.debug("confirmEntityPaper");
		ledgerMapper.confirmEntityPaper(map);

	}

	@Override
	public int getCountModifyEvent() {
		logger.debug("getCountModifyEvent");
		return ledgerMapper.getCountModifyEvent();
	}

	@Override
	public int getAllEventModifySize(Map<String, Object> map) throws Exception {
		logger.debug("getAllEventModifySize");
		String changedBarcode = basicService.changeBarcode((String) map.get("paper_barcode"));
		map.put("paper_barcode", changedBarcode);
		return ledgerMapper.getAllEventModifySize(map);
	}

	@Override
	public List<EventModify> getAllEventModifyList(Map<String, Object> map, RowBounds rbs) throws Exception {
		logger.debug("getAllEventModifyList");
		String changedBarcode = basicService.changeBarcode((String) map.get("paper_barcode"));
		map.put("paper_barcode", changedBarcode);
		return ledgerMapper.getAllEventModifyList(map, rbs);
	}

	@Override
	public String getSeclvNameByCode(int seclv_code) {
		logger.debug("getSeclvNameByCode");
		return ledgerMapper.getSeclvNameByCode(seclv_code);
	}

	@Override
	public void cancelHandleLedgerById(String event_code) {
		logger.debug("cancelHandleLedgerById" + event_code);
		// 通过event_code查出密级变更记录
		EventModify modify = ledgerMapper.getModifyEventByCode(event_code);
		String job_code = modify.getJob_code();
		String paper_barcode = modify.getBarcode();
		// 通过event_code删除event_modify记录
		ledgerMapper.deleteEventModifyByBarcode(event_code);
		// 把event对应的job_code设置为null,取消审批；并把paper_state置为0,回到留用状态
		ledgerMapper.cancelHandleModifyPaperById(paper_barcode);
		// 如果该job_code对应的event数量为空，表示该任务下挂载的作业已经都被取消了，则取消该任务
		if (StringUtils.hasLength(job_code) && ledgerMapper.getHandleModifyPaperCountByJobCode(job_code) == 0) {
			String instance_id = basicService.getProcessJobByCode(job_code).getInstance_id();
			basicPrcManage.suspendProcessInstanceById(instance_id);
			basicService.delJob(job_code);
			clientService.delClientMsgByJobCode(job_code);
		}
	}

	@Override
	public void cancelHandleCDLedgerById(String event_code) {
		logger.debug("cancelHandleCDLedgerById" + event_code);
		// 通过event_code查出密级变更记录
		EventModify modify = ledgerMapper.getModifyEventByCode(event_code);
		String job_code = modify.getJob_code();
		String cd_barcode = modify.getBarcode();
		// 通过event_code删除event_modify记录
		ledgerMapper.deleteEventModifyByBarcode(event_code);
		// 把event对应的job_code设置为null,取消审批；并把cd_state置为0,回到留用状态
		ledgerMapper.cancelHandleModifyCDById(cd_barcode);
		// 如果该job_code对应的event数量为空，表示该任务下挂载的作业已经都被取消了，则取消该任务
		if (StringUtils.hasLength(job_code) && ledgerMapper.getHandleModifyCDCountByJobCode(job_code) == 0) {
			String instance_id = basicService.getProcessJobByCode(job_code).getInstance_id();
			basicPrcManage.suspendProcessInstanceById(instance_id);
			basicService.delJob(job_code);
			clientService.delClientMsgByJobCode(job_code);
		}

	}

	@Override
	public void updateCDState(Map<String, Object> map) {
		logger.debug("updateCDState");
		ledgerMapper.updateCDState(map);

	}

	@Override
	public void confirmEntityCD(Map<String, Object> map) {
		logger.debug("confirmEntityCD");
		ledgerMapper.confirmEntityCD(map);

	}

	@Override
	public List<EventModify> getEventModifyByEntityType(String entity_type) {
		logger.debug("confirmEntityCD");
		return ledgerMapper.getEventModifyByEntityType(entity_type);
	}

	@Override
	public int getAllEventCDModifySize(Map<String, Object> map) throws Exception {
		logger.debug("getAllEventCDModifySize");
		String changedBarcode = basicService.changeBarcode((String) map.get("cd_barcode"));
		map.put("cd_barcode", changedBarcode);
		return ledgerMapper.getAllEventCDModifySize(map);
	}

	@Override
	public List<EventModify> getAllEventCDModifyList(Map<String, Object> map, RowBounds rbs) throws Exception {
		logger.debug("getAllEventCDModifyList");
		String changedBarcode = basicService.changeBarcode((String) map.get("cd_barcode"));
		map.put("cd_barcode", changedBarcode);
		return ledgerMapper.getAllEventCDModifyList(map, rbs);
	}

	@Override
	public List<EventModify> getModifyEventByJobCode(String job_code) {
		logger.debug("getModifyEventByJobCode" + job_code);
		return ledgerMapper.getModifyEventByJobCode(job_code);
	}

	@Override
	public List<EventModify> getModifyEventListByBarcode(String barcode) {
		logger.debug("getModifyEventListByBarcode" + barcode);
		return ledgerMapper.getModifyEventListByBarcode(barcode);
	}

	@Override
	public void updateCDJobCodeByBarCode(Map<String, Object> map) {
		ledgerMapper.updateCDJobCodeByBarCode(map);
	}

	@Override
	public void updatePaperJobCodeByBarCode(Map<String, Object> map) {
		ledgerMapper.updatePaperJobCodeByBarCode(map);

	}

	@Override
	public void addEventCarryOutPaper(EventCarryOut eventCarryOut) {
		ledgerMapper.addEventCarryOutPaper(eventCarryOut);
	}

	@Override
	public void addPaperCarryOutProcessJob(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType,
			String next_approver, Date startTime, Date endTime, String carryoutInfo, String[] barcodes,
			String usage_code, String send_way, String carryout_user_iidds, String carryout_user_names)
			throws Exception {

		logger.debug("addPaperCarryOutProcessJob");
		ApproveProcess process = basicService.getApproveProcessByKey(dept_id, seclv_code, jobType.getJobTypeCode(),
				usage_code);
		String job_status = null;
		if (process.getTotal_steps() == 0) {
			job_status = ActivitiCons.APPLY_APPROVED_PASS;
		} else {
			job_status = ActivitiCons.APPLY_APPROVED_DEFAULT;
		}
		String next_approver_name = basicService.getApproverName(next_approver);
		String job_code = user_iidd + "-" + jobType.getJobTypeCode() + "-" + System.currentTimeMillis();

		ProcessJob job = new ProcessJob(job_code, user_iidd, dept_id, seclv_code, jobType, new Date(), job_status,
				next_approver, next_approver_name, process.getProcess_id(), send_way, carryout_user_iidds,
				carryout_user_names, "");
		job.setIs_receipt("Y");
		// 开启流程
		basicPrcManage.addActivitiApply(job, process);
		// 把任务信息插入数据库
		basicMapper.addProcessJob(job);
		// 更新作业信息

		Map<String, Object> map0 = new HashMap<String, Object>();
		map0.put("barcodes", barcodes);
		map0.put("job_code", job.getJob_code());
		map0.put("supervise_user_iidd", "");
		map0.put("output_undertaker", "");
		map0.put("paper_state", 17);
		basicMapper.updateEntityStatus(map0);

		String user_name = userService.getUserNameByUserId(job.getUser_iidd());
		String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
		String detail = "提交" + jobType.getJobTypeName() + "申请";
		ProcessRecord record = new ProcessRecord(job.getJob_code(), job.getJobType(), job.getUser_iidd(), user_name,
				dept_name, detail, "请审批", new Date());
		activitiService.addProcessRecord(record);
		// 备注信息
		String summ = "";
		if (Integer.valueOf(send_way) == 0) {
			summ = job.getSend_way_str() + "," + "-携带人:" + carryout_user_names;
		} else {
			summ = job.getSend_way_str() + ",";
		}
		for (String barcode : barcodes) {
			if (StringUtils.hasLength(barcode.trim())) {

				// 全生命周期记录没有定义流程直接审批通过
				if (job.getJob_status().equals("true")) {
					CycleItem cycleitem = new CycleItem();
					cycleitem.setBarcode(barcode);
					cycleitem.setEntity_type("PAPER");
					cycleitem.setOper_time(new Date());
					cycleitem.setUser_name(user_name);
					cycleitem.setDept_name(dept_name);
					cycleitem.setOper("CARRYOUT");
					// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
					cycleitem.setJob_code(job_code);
					ledgerMapper.addCycleItem(cycleitem);
				}

				// 外带
				EntityPaper paper = getPaperByBarcode(barcode);
				EventCarryOut eventCarryOut = new EventCarryOut(paper.getEvent_code(), user_iidd, dept_id, PAPER,
						barcode, "", carryoutInfo, "", "", startTime, endTime, paper.getSeclv_code(), usage_code, summ,
						0, job_code, "");
				addEventCarryOutPaper(eventCarryOut);

				// 更改载体状态
				// Map<String, Object> map = new HashMap<String, Object>();
				// map.put("paper_barcode", barcode);
				// map.put("paper_state", 17);// 申请外带
				// ledgerMapper.updatePaperStateByBarcode(map);

			}
		}
		// 如果有审批流程，在消息表中添加审批消息
		if (process.getTotal_steps() != 0) {
			String message = dept_name + user_name + "有" + jobType.getJobTypeName() + "作业需要您审批";
			String oper_type = jobType.getJobTypeCode();
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(), message,
						new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		} else {
			for (String barcode : barcodes) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("paper_barcode", barcode);
				map.put("paper_state", 18);// 外带中
				ledgerMapper.updatePaperStateByBarcode(map);
			}
		}
	}

	@Override
	public List<EventCarryOut> getEventCarryOutListByJobCode(String job_code) {
		logger.debug("getEventCarryOutListByJobCode");
		return ledgerMapper.getEventCarryOutListByJobCode(job_code);
	}

	@Override
	public void addCDCarryOutProcessJob(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType,
			String next_approver, Date startTime, Date endTime, String carryoutInfo, String[] barcodes,
			String usage_code, String send_way, String carryout_user_iidds, String carryout_user_names)
			throws Exception {
		logger.debug("addCDCarryOutProcessJob");
		ApproveProcess process = basicService.getApproveProcessByKey(dept_id, seclv_code, jobType.getJobTypeCode(),
				usage_code);
		String job_status = null;
		if (process.getTotal_steps() == 0) {
			job_status = ActivitiCons.APPLY_APPROVED_PASS;
		} else {
			job_status = ActivitiCons.APPLY_APPROVED_DEFAULT;
		}
		String next_approver_name = basicService.getApproverName(next_approver);

		String job_code = user_iidd + "-" + jobType.getJobTypeCode() + "-" + System.currentTimeMillis();

		ProcessJob job = new ProcessJob(job_code, user_iidd, dept_id, seclv_code, jobType, new Date(), job_status,
				next_approver, next_approver_name, process.getProcess_id(), send_way, carryout_user_iidds,
				carryout_user_names, "");
		job.setIs_receipt("Y");

		// 开启流程
		basicPrcManage.addActivitiApply(job, process);
		// 把任务信息插入数据库
		basicMapper.addProcessJob(job);
		// 更新作业信息

		Map<String, Object> map0 = new HashMap<String, Object>();
		map0.put("barcodes", barcodes);
		map0.put("job_code", job.getJob_code());
		map0.put("supervise_user_iidd", "");
		map0.put("output_undertaker", "");
		map0.put("cd_state", 17);
		basicMapper.updateEntityCDStatus(map0);

		String user_name = userService.getUserNameByUserId(job.getUser_iidd());
		String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
		String detail = "提交" + jobType.getJobTypeName() + "申请";
		ProcessRecord record = new ProcessRecord(job.getJob_code(), job.getJobType(), job.getUser_iidd(), user_name,
				dept_name, detail, "请审批", new Date());
		activitiService.addProcessRecord(record);

		// 备注信息
		String summ = "";
		if (Integer.valueOf(send_way) == 0) {
			summ = job.getSend_way_str() + "," + "-携带人:" + carryout_user_names;
		} else {
			summ = job.getSend_way_str() + ",";
		}
		for (String barcode : barcodes) {
			if (StringUtils.hasLength(barcode.trim())) {

				// 全生命周期记录没有定义流程直接审批通过
				if (job.getJob_status().equals("true")) {
					CycleItem cycleitem = new CycleItem();
					cycleitem.setBarcode(barcode);
					cycleitem.setEntity_type("CD");
					cycleitem.setOper_time(new Date());
					cycleitem.setUser_name(user_name);
					cycleitem.setDept_name(dept_name);
					cycleitem.setOper("CARRYOUT");
					// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
					cycleitem.setJob_code(job_code);
					ledgerMapper.addCycleItem(cycleitem);
				}

				// 外带
				EntityCD cd = getCDByBarcode(barcode);
				EventCarryOut eventCarryOut = new EventCarryOut(cd.getEvent_code(), user_iidd, dept_id, DISK, barcode,
						"", carryoutInfo, "", "", startTime, endTime, cd.getSeclv_code(), usage_code, summ, 0,
						job_code, "");
				addEventCarryOutPaper(eventCarryOut);

				// 更改载体状态
				/*
				 * Map<String, Object> map = new HashMap<String, Object>(); map.put("cd_barcode", barcode);
				 * map.put("cd_state", 17);// 申请外带 ledgerMapper.updateCDStateByBarcode(map);
				 */
			}
		}
		// 如果有审批流程，在消息表中添加审批消息
		if (process.getTotal_steps() != 0) {
			String message = dept_name + user_name + "有" + jobType.getJobTypeName() + "作业需要您审批";
			String oper_type = jobType.getJobTypeCode();
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(), message,
						new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		} else {
			for (String barcode : barcodes) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cd_barcode", barcode);
				map.put("cd_state", 18);// 外带中
				ledgerMapper.updateCDStateByBarcode(map);
			}
		}
	}

	@Override
	public List<EventCarryOutProcessJob> getHandledCarryOutJobByUserId(String user_iidd, String type, String user_name,
			Integer seclv_code, String jobType_code) {

		List<EventCarryOutProcessJob> jobList = null;
		List<String> instanceIdList = new ArrayList<String>();
		instanceIdList = basicPrcManage.getApprovedInstanceIdList(user_iidd, jobType_code);
		if (instanceIdList != null && instanceIdList.size() > 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("entity_type", type);
			map.put("seclv_code", seclv_code);
			map.put("user_name", user_name);
			map.put("instanceIdList", instanceIdList);
			jobList = ledgerMapper.getHandleCarryOutJobListByEntityInstanceId(map);
		}
		if (jobList != null && jobList.size() > 0) {
			return jobList;
		}
		return Collections.emptyList();
	}

	@Override
	public List<ProcessJob> getCarryOutJobList(Map<String, Object> map, RowBounds rbs) {
		logger.debug("getCarryOutJobList");
		return ledgerMapper.getCarryOutJobList(map, rbs);
	}

	@Override
	public List<EventOut> getCarryOutConfirmEventList(Map<String, Object> map) {
		logger.debug("getCarryOutConfirmEventList");
		return ledgerMapper.getCarryOutConfirmEventList(map);
	}

	@Override
	public void updateEventCarryOutCarryoutStatusById(Map<String, Object> map) {
		logger.debug("updateEventCarryOutCarryoutStatusById");
		ledgerMapper.updateEventCarryOutCarryoutStatusById(map);

	}

	@Override
	public EventCarryOut getEventCarryOutById(Integer id) {
		logger.debug("getEventCarryOutById");
		return ledgerMapper.getEventCarryOutById(id);
	}

	@Override
	public EventOut getEventOutById(Integer id) {
		logger.debug("getEventOutById");
		return ledgerMapper.getEventOutById(id);
	}

	@Override
	public List<Integer> getSysSecLevelN(Map<String, Object> map) {
		return ledgerMapper.getSysSecLevelN(map);
	}

	@Override
	public int checkOrCarryIn(Map<String, Object> map) {
		return ledgerMapper.checkOrCarryIn(map);
	}

	@Override
	public List<Integer> getRecycleSeclCode() {
		logger.debug("getRecycleSeclCode");
		return ledgerMapper.getRecycleSeclCode();
	}

	@Override
	public List<EntityPaper> getAllPaperRecycleLedgerList(Map<String, Object> map, RowBounds rbs) throws Exception {
		logger.debug("getAllPaperRecycleLedgerList");
		String changedBarcode = basicService.changeBarcode((String) map.get("paper_barcode"));
		map.put("paper_barcode", changedBarcode);
		int totalSize = getAllPaperRecycleLedgerSize(map);
		int beginIndex = rbs.getOffset();
		if (beginIndex > totalSize) {
			rbs = new RowBounds(0, 15);

		}
		String barcodes = "";
		String[] paper_barcodes = (String[]) map.get("barcodes");
		if (!"".equals(paper_barcodes) && paper_barcodes != null) {
			for (int i = 0; i < paper_barcodes.length; i++) {
				String changedBarcodes = basicService.changeBarcode(paper_barcodes[i]);
				barcodes = barcodes + "," + changedBarcodes;
			}
			map.put("barcodes", barcodes.split(","));
		}
		return ledgerMapper.getAllPaperRecycleLedgerList(map, rbs);
	}

	@Override
	public int getAllPaperRecycleLedgerSize(Map<String, Object> map) throws Exception {
		logger.debug("getAllPaperRecycleLedgerSize");
		String changedBarcode = basicService.changeBarcode((String) map.get("paper_barcode"));
		map.put("paper_barcode", changedBarcode);
		String barcodes = "";
		String[] paper_barcodes = (String[]) map.get("barcodes");
		if (!"".equals(paper_barcodes) && paper_barcodes != null) {
			for (int i = 0; i < paper_barcodes.length; i++) {
				String changedBarcodes = basicService.changeBarcode(paper_barcodes[i]);
				barcodes = barcodes + "," + changedBarcodes;
			}
			map.put("barcodes", barcodes.split(","));
		}
		return ledgerMapper.getAllPaperRecycleLedgerSize(map);
	}

	@Override
	public List<EntityPaper> getAllPaperRecycleLedgerList(Map<String, Object> map) throws Exception {
		logger.debug("getAllPaperRecycleLedgerList");
		String changedBarcode = basicService.changeBarcode((String) map.get("paper_barcode"));
		map.put("paper_barcode", changedBarcode);
		return ledgerMapper.getAllPaperRecycleLedgerList(map);
	}

	@Override
	public void confirmSendCDMatter(Map<String, Object> map) {
		logger.debug("confirmSendCDMatter");
		ledgerMapper.confirmSendCDMatter(map);
	}

	@Override
	public void confirmSendPaperMatter(Map<String, Object> map) {
		logger.debug("confirmSendPaperMatter");
		ledgerMapper.confirmSendPaperMatter(map);
	}

	@Override
	public void reprintsendconfirm(Map<String, Object> map) {
		logger.debug("reprintsendconfirm");
		ledgerMapper.reprintsendconfirm(map);
	}

	@Override
	public void reprintsendconfirmcd(Map<String, Object> map) {
		logger.debug("reprintsendconfirmcd");
		ledgerMapper.reprintsendconfirmcd(map);
	}

	@Override
	public int getAllSendPaperSize(Map<String, Object> map) {
		logger.debug("getAllSendPaperSize");
		return ledgerMapper.getAllSendPaperSize(map);
	}

	@Override
	public int getDestroyPaperSize(Map<String, Object> map) {
		logger.debug("getDestroyPaperSize");
		return ledgerMapper.getDestroyPaperSize(map);
	}

	@Override
	public int getFilePaperSize(Map<String, Object> map) {
		logger.debug("getFilePaperSize");
		return ledgerMapper.getFilePaperSize(map);
	}

	@Override
	public int getDelayPaperSize(Map<String, Object> map) {
		logger.debug("getDelayPaperSize");
		return ledgerMapper.getDelayPaperSize(map);
	}

	@Override
	public int getCarryOutJobSize(Map<String, Object> map) {
		logger.debug("getCarryOutJobSize");
		return ledgerMapper.getCarryOutJobSize(map);
	}

	@Override
	public int getDestroyCDSize(Map<String, Object> map) {
		logger.debug("getDestroyCDSize");
		return ledgerMapper.getDestroyCDSize(map);
	}

	@Override
	public int getSendCDSize(Map<String, Object> map) {
		logger.debug("getSendCDSize");
		return ledgerMapper.getSendCDSize(map);
	}

	@Override
	public int getFileCDSize(Map<String, Object> map) {
		logger.debug("getFileCDSize");
		return ledgerMapper.getFileCDSize(map);
	}

	@Override
	public int getDelayCDSize(Map<String, Object> map) {
		logger.debug("getDelayCDSize");
		return ledgerMapper.getDelayCDSize(map);
	}

	@Override
	public void updatePaperIsBook(Map<String, Object> map) {
		logger.debug("updatePaperIsBook");
		ledgerMapper.updatePaperIsBook(map);
	}

	@Override
	public void ExpectsignPaperSuccess(Map<String, Object> map) {
		logger.debug("ExpectsignPaperSuccess");
		ledgerMapper.ExpectsignPaperSuccess(map);
	}

	@Override
	public void ExpectsignPaperfail(Map<String, Object> map) {
		logger.debug("ExpectsignPaperfail");
		ledgerMapper.ExpectsignPaperfail(map);
	}

	@Override
	public List<EntityPaper> getReplacePaperByPaperBarcode(String paper_barcode) {
		logger.debug("getReplacePaperByPaperBarcode");
		return ledgerMapper.getReplacePaperByPaperBarcode(paper_barcode);
	}

	@Override
	public void updateRetrievedPageNew(Map<String, Object> map) {
		logger.debug("updateRetrievedPageNew");
		ledgerMapper.updateRetrievedPageNew(map);
	}

	@Override
	public void updateRetrievedPageNewStatus(Map<String, Object> map) {
		logger.debug("updateRetrievedPageNewStatus");
		ledgerMapper.updateRetrievedPageNewStatus(map);
	}

	@Override
	public void updateDestroyedPageNew(Map<String, Object> map) {
		logger.debug("updateDestroyedPageNew");
		ledgerMapper.updateDestroyedPageNew(map);
	}

	@Override
	public void updateDestroyedPageNewStatus(Map<String, Object> map) {
		logger.debug("updateDestroyedPageNewStatus");
		ledgerMapper.updateDestroyedPageNewStatus(map);
	}

	@Override
	public List<EventLog> getEventLogAll(Map<String, Object> map) {
		logger.debug("getEventLogAll");
		return ledgerMapper.getEventLogAll(map);
	}

	@Override
	public void setPaperRemark(String paper_barcode, String send_id, String send_mode, String box_num,
			String file_order_num, String manage_opinion, String receive_id, String remark) {
		logger.debug("setPaperRemark");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paper_barcode", paper_barcode);
		map.put("send_id", send_id);
		map.put("send_mode", send_mode);
		map.put("box_num", box_num);
		map.put("file_order_num", file_order_num);
		map.put("manage_opinion", manage_opinion);
		map.put("receive_id", receive_id);
		map.put("remark", remark);
		ledgerMapper.setPaperRemark(map);
	}

	@Override
	public List<BurnEvent> getBurnEventByCdBarcode(String cd_barcode) {
		logger.debug("getBurnEventByCdBarcode");
		return ledgerMapper.getBurnEventByCdBarcode(cd_barcode);
	}

	@Override
	public String getDept_id(String dept_name) {
		logger.debug("getDept_id");
		return ledgerMapper.getDept_id(dept_name);
	}

	@Override
	public String getUser_iidd(String user_name) {
		logger.debug("getUser_iidd");
		return ledgerMapper.getUser_iidd(user_name);
	}

	@Override
	public void addEntityCD(EntityCD entityCd) {
		logger.debug("addEntityCD");
		ledgerMapper.addEntityCD(entityCd);
	}

	@Override
	public Integer getSeclv_code(String seclv_name) {
		logger.debug("getSeclv_code");
		return ledgerMapper.getSeclv_code(seclv_name);
	}

	@Override
	public int getModifyPaperSize(Map<String, Object> map) {
		logger.debug("getModifyPaperSize");
		return ledgerMapper.getModifyPaperSize(map);
	}

	@Override
	public List<EntityPaper> getModifyPaperList(Map<String, Object> map, RowBounds rbs) {
		logger.debug("getModifyPaperList");
		return ledgerMapper.getModifyPaperList(map, rbs);
	}

	@Override
	public void updatePaperDelInfo(EntityPaper paper, ApproverUser user, String job_code) {
		logger.debug("updatePaperDelInfo");
		ProcessJob job = basicService.getProcessJobByCode(job_code);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paper_id", paper.getPaper_id());
		map.put("paper_state", 4);// 已销毁
		map.put("print_result", "0");
		map.put("retrieve_time", new Date());
		map.put("retrieve_user_iidd", job.getUser_iidd());
		ledgerMapper.updateEntityPaperMap(map);

		// 生成载体生命周期记录：文件错误台账已销毁
		CycleItem cycleitem = new CycleItem();
		cycleitem.setBarcode(paper.getPaper_barcode());
		cycleitem.setEntity_type("PAPER");
		cycleitem.setOper_time(new Date());
		cycleitem.setUser_name(job.getUser_name());
		cycleitem.setDept_name(job.getDept_name());
		cycleitem.setOper("ERRORINFO");
		cycleitem.setJob_code(job_code);
		ledgerMapper.addCycleItem(cycleitem);
	}

	@Override
	public void updatePaperModifyInfo(EntityPaper paper, ApproverUser user, String job_code) {
		logger.debug("updatePaperModifyInfo");
		ProcessJob job = basicService.getProcessJobByCode(job_code);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paper_id", paper.getPaper_id());
		map.put("paper_state", 0);// 留用
		map.put("page_count", Integer.valueOf(job.getOutput_dept_name()));
		ledgerMapper.updateEntityPaperMap(map);

		// 生成载体生命周期记录：文件错误台账变更修改
		CycleItem cycleitem = new CycleItem();
		cycleitem.setBarcode(paper.getPaper_barcode());
		cycleitem.setEntity_type("PAPER");
		cycleitem.setOper_time(new Date());
		cycleitem.setUser_name(job.getUser_name());
		cycleitem.setDept_name(job.getDept_name());
		cycleitem.setOper("PAGEMODIFY");
		cycleitem.setJob_code(job_code);
		ledgerMapper.addCycleItem(cycleitem);
	}

	@Override
	public void addTempEvent(String user_iidd, String dept_id, Integer seclv_code, String event_ids,
			JobTypeEnum jobtype, String entity_type, String usage_code, String project_code, String summ,
			String next_approver, String scope_dept_id, String scope_dept_name) throws Exception {
		logger.debug("addTempEvent");
		ApproveProcess process = basicPrcManage.getApproveProcessByKey(dept_id, String.valueOf(seclv_code),
				jobtype.getJobTypeCode(), usage_code, true);
		String job_status = null;
		if (process.getTotal_steps() == 0) {
			job_status = ActivitiCons.APPLY_APPROVED_PASS;
		} else {
			job_status = ActivitiCons.APPLY_APPROVED_DEFAULT;
		}
		String next_approver_name = basicPrcManage.getApproverName(next_approver);
		String job_code = user_iidd + "-" + jobtype.getJobTypeCode() + "-" + System.currentTimeMillis();
		ProcessJob job = new ProcessJob(job_code, user_iidd, dept_id, seclv_code, jobtype, new Date(), job_status,
				next_approver, next_approver_name, process.getProcess_id());
		job.setComment(summ);
		// 开启流程
		basicPrcManage.addActivitiApply(job, process);
		// 把任务信息插入数据库
		basicMapper.addProcessJob(job);
		String user_name = userService.getUserNameByUserId(job.getUser_iidd());
		String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
		for (String barcode : event_ids.split(",")) {
			if (entity_type.equals("paper")) {
				EntityPaper entity = ledgerMapper.getPaperByBarcode(barcode);
				String event_code = user_iidd + entity.getPaper_id() + System.currentTimeMillis();
				EventTemp event = new EventTemp(jobtype, event_code, user_iidd, dept_id, seclv_code, usage_code,
						project_code, summ, "paper", entity.getPaper_barcode(), entity.getFile_title());
				if (scope_dept_id.equals("")) {// 根据用户填写归属部门，为空则默认归属本部门
					event.setScope_dept_id(dept_id);
					event.setScope_dept_name(dept_name);
				} else {
					event.setScope_dept_id(scope_dept_id);
					event.setScope_dept_name(scope_dept_name);
				}
				event.setJob_code(job_code);
				ledgerMapper.addEventTemp(event);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("paper_barcode", barcode);
				map.put("job_code", job_code);
				map.put("paper_state", 7);// 将状态更新为“申请销毁”
				ledgerMapper.updatePaperState(map);
			} else {
				EntityCD entity = ledgerMapper.getCDByBarcode(barcode);
				String event_code = user_iidd + entity.getCd_id() + System.currentTimeMillis();
				EventTemp event = new EventTemp(jobtype, event_code, user_iidd, dept_id, seclv_code, usage_code,
						project_code, summ, "cd", entity.getCd_barcode(), entity.getFile_list());
				if (scope_dept_id.equals("")) {
					event.setScope_dept_id(dept_id);
					event.setScope_dept_name(dept_name);
				} else {
					event.setScope_dept_id(scope_dept_id);
					event.setScope_dept_name(scope_dept_name);
				}
				event.setJob_code(job_code);
				ledgerMapper.addEventTemp(event);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cd_barcode", barcode);
				map.put("job_code", job_code);
				map.put("cd_state", 7);// 将状态更新为“申请销毁”
				ledgerMapper.updateCDState(map);
			}
		}
		// 更新作业信息
		String detail = "提交" + jobtype.getJobTypeName() + "申请";
		ProcessRecord record = new ProcessRecord(job.getJob_code(), job.getJobType(), job.getUser_iidd(), user_name,
				dept_name, detail, "请审批", new Date());
		activitiService.addProcessRecord(record);
		// 如果有审批流程，在消息表中添加审批消息
		if (process.getTotal_steps() != 0) {
			String message = dept_name + user_name + "有" + jobtype.getJobTypeName() + "作业需要您审批";
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, jobtype.getJobTypeCode(), 1,
						job.getJob_code(), message, new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		} else {// 流程步骤为0时直接更新
			for (String barcode : event_ids.split(",")) {
				Map<String, Object> mapevent = new HashMap<String, Object>();
				if (scope_dept_id.equals("")) {
					mapevent.put("scope_dept_id", dept_id);
					mapevent.put("scope_dept_name", dept_name);
				} else {
					mapevent.put("scope_dept_id", scope_dept_id);
					mapevent.put("scope_dept_name", scope_dept_name);
				}
				mapevent.put("scope", "DEPT");
				if (entity_type.equals("paper")) {
					mapevent.put("paper_barcode", barcode);
					ledgerMapper.updatePaperState(mapevent);
				} else {
					mapevent.put("cd_barcode", barcode);
					ledgerMapper.updateCDState(mapevent);
				}
			}
		}
	}

	@Override
	public List<EventTemp> getTempEventList(Map<String, Object> map) {
		logger.debug("getTempEventList");
		return ledgerMapper.getTempEventList(map);
	}

	@Override
	public List<EventTemp> getTempEventListByJobCode(String job_code) {
		logger.debug("getTempEventListByJobCode");
		return ledgerMapper.getTempEventListByJobCode(job_code);
	}

	@Override
	public void cancelHandleTempById(String event_code) {
		logger.debug("cancelHandleTempById" + event_code);
		EventTemp temp = ledgerMapper.getTempEventByEventCode(event_code);
		String job_code = temp.getJob_code();
		String barcode = temp.getBarcode();
		// 通过event_code删除event_temp记录
		ledgerMapper.deleteEventTempBycode(event_code);
		// 把event对应的job_code设置为null,取消审批；并把paper_state置为0,回到留用状态
		Map<String, Object> map = new HashMap<String, Object>();
		if (temp.getEntity_type().equals("paper")) {
			map.put("paper_barcode", barcode);
			map.put("cancel", true);
			map.put("paper_state", 3);// 将状态更新为“已归档”
			ledgerMapper.updatePaperState(map);
		} else {
			map.put("cd_barcode", barcode);
			map.put("cancel", true);
			map.put("cd_state", 3);// 将状态更新为“已归档”
			ledgerMapper.updateCDState(map);
		}

		if (StringUtils.hasLength(job_code) && ledgerMapper.getHandleTempCountByJobCode(job_code) == 0) {
			String instance_id = basicService.getProcessJobByCode(job_code).getInstance_id();
			basicPrcManage.suspendProcessInstanceById(instance_id);
			basicService.delJob(job_code);
			clientService.delClientMsgByJobCode(job_code);
		}
	}

	@Override
	public List<ProcessJob> getTempJobList(Map<String, Object> map) {
		logger.debug("getTempJobList");
		return ledgerMapper.getTempJobList(map);
	}

	@Override
	public void updateSupervisePaperStateByBarcode(Map<String, Object> map) {
		logger.debug("updateSupervisePaperStateByBarcode");
		ledgerMapper.updateSupervisePaperStateByBarcode(map);

		String barcode = map.get("barcode").toString();
		EntityPaper paper = ledgerMapper.getPaperByBarcode(barcode);
		String job_code = paper.getJob_code();
		ProcessJob job = basicService.getProcessJobByCode(job_code);
		CycleItem cycleitem = new CycleItem();
		cycleitem.setBarcode(paper.getPaper_barcode());
		cycleitem.setEntity_type("PAPER");
		cycleitem.setOper_time(new Date());
		cycleitem.setUser_name(job.getUser_name());
		cycleitem.setDept_name(job.getDept_name());
		cycleitem.setOper("DESTROY_PAPER_BYSELF");
		cycleitem.setJob_code(job_code);
		ledgerMapper.addCycleItem(cycleitem);
	}

	@Override
	public int getSelfDestroyPaperSize(Map<String, Object> map) {
		logger.debug("getSelfDestroyPaperSize");
		return ledgerMapper.getSelfDestroyPaperSize(map);
	}

	@Override
	public List<EntityPaper> getSelfDestroyPaperList(Map<String, Object> map, RowBounds rbs) {
		return ledgerMapper.getSelfDestroyPaperList(map, rbs);
	}

	@Override
	public void updateSuperviseCDStateByBarcode(Map<String, Object> map) {
		logger.debug("updateSuperviseCDStateByBarcode");
		ledgerMapper.updateSuperviseCDStateByBarcode(map);

		String barcode = map.get("barcode").toString();
		EntityCD cd = ledgerMapper.getCDByBarcode(barcode);
		String job_code = cd.getJob_code();
		ProcessJob job = basicService.getProcessJobByCode(job_code);
		CycleItem cycleitem = new CycleItem();
		cycleitem.setBarcode(cd.getCd_barcode());
		cycleitem.setEntity_type("CD");
		cycleitem.setOper_time(new Date());
		cycleitem.setUser_name(job.getUser_name());
		cycleitem.setDept_name(job.getDept_name());
		cycleitem.setOper("DESTROY_CD_BYSELF");
		cycleitem.setJob_code(job_code);
		ledgerMapper.addCycleItem(cycleitem);
	}

	@Override
	public int getSelfDestroyCDSize(Map<String, Object> map) {
		logger.debug("getSelfDestroyCDSize");
		return ledgerMapper.getSelfDestroyCDSize(map);
	}

	@Override
	public List<EntityCD> getSelfDestroyCDList(Map<String, Object> map, RowBounds rbs) {
		return ledgerMapper.getSelfDestroyCDList(map, rbs);
	}

	@Override
	public List<EntityDevice> getPersonalDeviceLedgerList(Map<String, Object> map, RowBounds rbs) throws Exception {
		logger.debug("getPersonalDeviceLedgerList");
		String changedBarcode = basicService.changeBarcode((String) map.get("device_barcode"));
		map.put("device_barcode", changedBarcode);

		int totalSize = getPersonalDeviceLedgerSize(map);
		int beginIndex = rbs.getOffset();
		if (beginIndex > totalSize) {
			rbs = new RowBounds(0, 15);

		}
		return ledgerMapper.getPersonalDeviceLedgerList(map, rbs);
	}

	@Override
	public int getPersonalDeviceLedgerSize(Map<String, Object> map) throws Exception {
		logger.debug("getPersonalDeviceLedgerSize");
		String changedBarcode = basicService.changeBarcode((String) map.get("device_barcode"));
		map.put("device_barcode", changedBarcode);
		String barcodes = "";
		String[] temp_barcodes = (String[]) map.get("device_barcodes");
		if (!"".equals(temp_barcodes) && temp_barcodes != null) {
			for (int i = 0; i < temp_barcodes.length; i++) {
				String changedBarcodes = basicService.changeBarcode(temp_barcodes[i]);
				barcodes = barcodes + "," + changedBarcodes;
			}
			map.put("device_barcodes", barcodes.split(","));
		}
		return ledgerMapper.getPersonalDeviceLedgerSize(map);
	}

	@Override
	public List<EntityDevice> getDeviceListByBarcodes(String barcodes) {
		logger.debug("getDeviceListByBarcodes");
		List<String> list = new ArrayList<String>();
		for (String item : barcodes.split(",")) {
			if (StringUtils.hasLength(item.trim())) {
				list.add(item.trim());
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("barcodes", list);
		return ledgerMapper.getDeviceListByBarcodes(map);
	}

	// 个人磁介质自主销毁
	@Override
	public void updateSuperviseDeviceStateByBarcode(Map<String, Object> map) {
		logger.debug("updateSuperviseDeviceStateByBarcode");
		ledgerMapper.updateSuperviseDeviceStateByBarcode(map);

		String barcode = map.get("barcode").toString();
		EntityDevice device = ledgerMapper.getDeviceByBarcode(barcode);
		String job_code = device.getJob_code();
		ProcessJob job = basicService.getProcessJobByCode(job_code);
		CycleItem cycleitem = new CycleItem();
		cycleitem.setBarcode(device.getDevice_barcode());
		cycleitem.setEntity_type("DEVICE");
		cycleitem.setOper_time(new Date());
		cycleitem.setUser_name(job.getUser_name());
		cycleitem.setDept_name(job.getDept_name());
		cycleitem.setOper("DESTROY_DEVICE_BYSELF");
		cycleitem.setJob_code(job_code);
		ledgerMapper.addCycleItem(cycleitem);
	}

	@Override
	public int getSelfDestroyDeviceSize(Map<String, Object> map) {
		logger.debug("getSelfDestroyDeviceSize");
		return ledgerMapper.getSelfDestroyDeviceSize(map);
	}

	@Override
	public List<EntityDevice> getSelfDestroyDevcieList(Map<String, Object> map, RowBounds rbs) {
		return ledgerMapper.getSelfDestroyDeviceList(map, rbs);
	}

	@Override
	public void giveUpHandleDeviceByBarcode(String device_barcode) {
		logger.debug("giveUpHandleDeviceByBarcode");
		ledgerMapper.resetDeviceState(device_barcode);
	}

	@Override
	public int getSecAllPaperLedgerSize(Map<String, Object> map) throws Exception {
		logger.debug("getSecAllPaperLedgerSize");
		String changedBarcode = basicService.changeBarcode((String) map.get("paper_barcode"));
		map.put("paper_barcode", changedBarcode);
		return ledgerMapper.getSecAllPaperLedgerSize(map);
	}

	@Override
	public List<EntityPaper> getSecAllPaperLedgerList(Map<String, Object> map, RowBounds rbs) throws Exception {
		logger.debug("getAllPaperLedgerList");
		String changedBarcode = basicService.changeBarcode((String) map.get("paper_barcode"));
		map.put("paper_barcode", changedBarcode);
		return ledgerMapper.getSecAllPaperLedgerList(map, rbs);
	}

	@Override
	public void addEntityPaper(EntityPaper entityPaper) {
		logger.debug("addEntityPaper");
		ledgerMapper.addEntityPaper(entityPaper);
	}

	// 个人委托打印台账 查询，单独使用下二方法
	@Override
	public int getProxyPaperLedgerSize(Map<String, Object> map) throws Exception {
		logger.debug("getProxyPaperLedgerSize");
		String changedBarcode = basicService.changeBarcode((String) map.get("paper_barcode"));
		map.put("paper_barcode", changedBarcode);
		String barcodes = "";
		String[] paper_barcodes = (String[]) map.get("barcodes");
		if (!"".equals(paper_barcodes) && paper_barcodes != null) {
			for (int i = 0; i < paper_barcodes.length; i++) {
				String changedBarcodes = basicService.changeBarcode(paper_barcodes[i]);
				barcodes = barcodes + "," + changedBarcodes;
			}
			map.put("barcodes", barcodes.split(","));
		}
		return ledgerMapper.getProxyPaperLedgerSize(map);
	}

	@Override
	public List<EntityPaper> getProxyPaperLedgerList(Map<String, Object> map, RowBounds rbs) throws Exception {
		logger.debug("getProxyPaperLedgerList");
		String changedBarcode = basicService.changeBarcode((String) map.get("paper_barcode"));
		map.put("paper_barcode", changedBarcode);
		int totalSize = getProxyPaperLedgerSize(map);
		int beginIndex = rbs.getOffset();
		if (beginIndex > totalSize) {
			rbs = new RowBounds(0, 15);

		}
		String barcodes = "";
		String[] paper_barcodes = (String[]) map.get("barcodes");
		if (!"".equals(paper_barcodes) && paper_barcodes != null) {
			for (int i = 0; i < paper_barcodes.length; i++) {
				String changedBarcodes = basicService.changeBarcode(paper_barcodes[i]);
				barcodes = barcodes + "," + changedBarcodes;
			}
			map.put("barcodes", barcodes.split(","));
		}
		return ledgerMapper.getProxyPaperLedgerList(map, rbs);
	}

	@Override
	public int getProxyCDLedgerSize(Map<String, Object> map) throws Exception {
		logger.debug("getProxyCDLedgerSize");
		String changedBarcode = basicService.changeBarcode((String) map.get("cd_barcode"));
		map.put("cd_barcode", changedBarcode);
		String barcodes = "";
		String[] cd_barcodes = (String[]) map.get("barcodes");
		if (!"".equals(cd_barcodes) && cd_barcodes != null) {
			for (int i = 0; i < cd_barcodes.length; i++) {
				String changedBarcodes = basicService.changeBarcode(cd_barcodes[i]);
				barcodes = barcodes + "," + changedBarcodes;
			}
			map.put("barcodes", barcodes.split(","));
		}
		return ledgerMapper.getProxyCDLedgerSize(map);
	}

	@Override
	public List<EntityCD> getProxyCDLedgerList(Map<String, Object> map, RowBounds rbs) throws Exception {
		logger.debug("getProxyCDLedgerList");
		String changedBarcode = basicService.changeBarcode((String) map.get("cd_barcode"));
		map.put("cd_barcode", changedBarcode);
		int totalSize = getProxyCDLedgerSize(map);
		int beginIndex = rbs.getOffset();
		if (beginIndex > totalSize) {
			rbs = new RowBounds(0, 15);

		}
		return ledgerMapper.getProxyCDLedgerList(map, rbs);
	}

	@Override
	public int getSecAllCDLedgerSize(Map<String, Object> map) throws Exception {
		logger.debug("getSecAllCDLedgerSize");
		String changedBarcode = basicService.changeBarcode((String) map.get("cd_barcode"));
		map.put("cd_barcode", changedBarcode);
		return ledgerMapper.getSecAllCDLedgerSize(map);
	}

	@Override
	public List<EntityCD> getSecAllCDLedgerList(Map<String, Object> map, RowBounds rbs) throws Exception {
		logger.debug("getAllCDLedgerList");
		String changedBarcode = basicService.changeBarcode((String) map.get("cd_barcode"));
		map.put("cd_barcode", changedBarcode);
		return ledgerMapper.getSecAllCDLedgerList(map, rbs);
	}

	@Override
	public void updatePaperOutputConfidentialNumByEventCarryoutId(Map<String, Object> map) {
		logger.debug("updatePaperOutputConfidentialNumByEventCarryoutId");
		ledgerMapper.updatePaperOutputConfidentialNumByEventCarryoutId(map);

	}

	@Override
	public void updateCDOutputConfidentialNumByEventCarryoutId(Map<String, Object> map) {
		logger.debug("updateCDOutputConfidentialNumByEventCarryoutId");
		ledgerMapper.updateCDOutputConfidentialNumByEventCarryoutId(map);

	}

	@Override
	public void addMergeEntityPaper(String user_iidd, String dept_id, Integer seclv_code, ProcessJob job,
			String barcodes) throws Exception {
		logger.debug("addMergeEntityPaper");
		// 产生新台账并添加载体全生命周期记录
		String unitCode = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "unit.code");// 5S
		String strCompayType = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "unit.CompanyType");
		String paper_barcode = null;
		String user_name = userService.getSecUserByUid(user_iidd).getUser_name();
		String dept_name = userService.getDeptNameByDeptId(dept_id);

		if (paper_barcode == null || paper_barcode.equals("")) {
			if (unitCode != "" && strCompayType.equalsIgnoreCase("CAEP")) {
				paper_barcode = basicService.createEntityBarcodeCAEP("PRINTPAPER", seclv_code, dept_id);
			} else {
				paper_barcode = basicService.createEntityBarcode("PRINTPAPER");
			}
		}
		// 生成载体生命周期记录
		CycleItem cycleitem = new CycleItem();
		cycleitem.setBarcode(paper_barcode);
		cycleitem.setOper_time(new Date());
		cycleitem.setEntity_type("PAPER");
		cycleitem.setUser_name(user_name);
		cycleitem.setDept_name(dept_name);
		cycleitem.setOper("MERGE_ENTITY");
		cycleitem.setJob_code(job.getJob_code());
		ledgerMapper.addCycleItem(cycleitem);

		// 根据密级查询回收期限，以当前时间加回收期限得出回收提醒时间。如果为0说明不需要回收，回收提醒时间为默认空值
		SysSeclevel sysSeclevel = basicService.getSysSecLevelByCode(seclv_code);
		Date expire_time = null;
		if (sysSeclevel != null) {
			expire_time = TimeUtil.getAfterXDay(sysSeclevel.getArchive_time());
		}

		EntityPaper paper = new EntityPaper(paper_barcode, "", user_iidd, user_name, dept_id, dept_name, user_iidd,
				user_name, dept_id, dept_name, seclv_code, "", new Date(), "", "1", job.getOutput_user_name(), "", "",
				"", Integer.valueOf(job.getOutput_dept_name()), null, null, null, null, 0, "", "", "", null, null, "",
				"", null, "", "", null, "", "", job.getJob_code(), "MERGE_ENTITY", "PERSON", "", "", "", "", "", "",
				"", "", job.getComment());
		paper.setExpire_time(expire_time);
		paper.setMerge_state("2");// 合并总台账状态
		enterService.addPaperledger(paper);

		// 更新分台账的状态值和关联新台账
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("barcodes", barcodes.split(","));
		map.put("merge_code", paper_barcode);
		map.put("merge_state", "1");
		map.put("paper_state", "0");
		ledgerMapper.updateNeedMergeEntity(map);

		// 流程表中暂存的外发接收人和外发部门字段置空
		map.put("job_code", job.getJob_code());
		map.put("output_dept_name", "");
		map.put("output_user_name", "");
		ledgerMapper.updateJobProcessInfo(map);
	}

	@Override
	public List<EntityPaper> getMergePaperList(Map<String, Object> map) {
		logger.debug("getMergePaperList");
		return ledgerMapper.getMergePaperList(map);
	}

	@Override
	public List<EntityPaper> getMergePaperApplyList(Map<String, Object> map, RowBounds rbs) {
		logger.debug("getMergePaperApplyList");
		return ledgerMapper.getMergePaperApplyList(map, rbs);
	}

	@Override
	public int getMergePaperApplySize(Map<String, Object> map) {
		logger.debug("getMergePaperApplySize");
		return ledgerMapper.getMergePaperApplySize(map);
	}

	@Override
	public void updateMergeCodeState(String paper_barcode) {
		logger.debug("updateMergeCodeState");
		ledgerMapper.updateMergeCodeState(paper_barcode);
	}

	@Override
	public List<EntityPaper> getMergeAllPaperList(Map<String, Object> map, RowBounds rbs) throws Exception {
		logger.debug("getMergeAllPaperList");
		return ledgerMapper.getMergeAllPaperList(map, rbs);
	}

	@Override
	public int getMergePaperSize(Map<String, Object> map) {
		logger.debug("getMergePaperSize");
		return ledgerMapper.getMergePaperSize(map);
	}

	@Override
	public List<RejectRecord> getAllRejectPaperList(Map<String, Object> map,
			RowBounds rbs) throws Exception{
		logger.debug("getAllRejectPaperList");
		return ledgerMapper.getAllRejectPaperList(map,rbs);
	}

	@Override
	public int getAllRejectPaperSize(Map<String, Object> map) throws Exception{
		logger.debug("getAllRejectPaperSize");
		String changedBarcode = basicService.changeBarcode((String) map.get("paper_barcode"));
		map.put("paper_barcode", changedBarcode);
		String barcodes = "";
		String[] paper_barcodes = (String[]) map.get("barcodes");
		if (!"".equals(paper_barcodes) && paper_barcodes != null) {
			for (int i = 0; i < paper_barcodes.length; i++) {
				String changedBarcodes = basicService.changeBarcode(paper_barcodes[i]);
				barcodes = barcodes + "," + changedBarcodes;
			}
			map.put("barcodes", barcodes.split(","));
		}
		return ledgerMapper.getAllRejectPaperSize(map);
	}

	@Override
	public List<EntityCD> getAllRejectCDList(Map<String, Object> map,
			RowBounds rbs) throws Exception{
		logger.debug("getAllRejectCDList");
		return ledgerMapper.getAllRejectCDList(map,rbs);
	}

	@Override
	public int getAllRejectCDSize(Map<String, Object> map)throws Exception{
		logger.debug("getAllRejectCDSize");
		String changedBarcode = basicService.changeBarcode((String) map.get("cd_barcode"));
		map.put("cd_barcode", changedBarcode);
		String barcodes = "";
		String[] cd_barcodes = (String[]) map.get("barcodes");
		if (!"".equals(cd_barcodes) && cd_barcodes != null) {
			for (int i = 0; i < cd_barcodes.length; i++) {
				String changedBarcodes = basicService.changeBarcode(cd_barcodes[i]);
				barcodes = barcodes + "," + changedBarcodes;
			}
			map.put("barcodes", barcodes.split(","));
		}
		return ledgerMapper.getAllRejectCDSize(map);
	}

	
}