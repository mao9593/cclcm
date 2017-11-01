package hdsec.web.project.enter.service;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.activiti.service.ActivitiService;
import hdsec.web.project.activiti.util.ActivitiCons;
import hdsec.web.project.basic.mapper.BasicMapper;
import hdsec.web.project.basic.model.ClientMsg;
import hdsec.web.project.basic.model.ConfirmRecord;
import hdsec.web.project.basic.service.BasicPrcManage;
import hdsec.web.project.basic.service.BasicService;
import hdsec.web.project.device.mapper.DeviceMapper;
import hdsec.web.project.device.model.EntityDevice;
import hdsec.web.project.enter.mapper.EnterMapper;
import hdsec.web.project.enter.model.EnterEvent;
import hdsec.web.project.enter.model.EnterProcessJob;
import hdsec.web.project.ledger.mapper.LedgerMapper;
import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.ledger.model.ReprintCD;
import hdsec.web.project.ledger.service.LedgerService;
import hdsec.web.project.transfer.mapper.TransferMapper;
import hdsec.web.project.transfer.model.EventTransfer;
import hdsec.web.project.user.service.UserService;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

public class EnterServiceImpl implements EnterService {
	private final Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private EnterMapper enterMapper;
	@Resource
	private DeviceMapper deviceMapper;
	@Resource
	private LedgerMapper ledgerMapper;
	@Resource
	private TransferMapper transferMapper;
	@Resource
	private BasicPrcManage basicPrcManage;
	@Resource
	private BasicMapper basicMapper;
	@Resource
	private UserService userService;
	@Resource
	private ActivitiService activitiService;
	@Resource
	private BasicService basicService;
	@Resource
	private LedgerService ledgerService;

	/**
	 * 录入作业管理
	 */
	@Override
	public void addEnterEvent(EnterEvent event) {
		logger.debug("addEnterEvent" + event.getEvent_code());
		enterMapper.addEnterEvent(event);
	}

	@Override
	public List<EnterEvent> getEnterEventList(Map<String, Object> map) {
		logger.debug("getEnterEventList");
		return enterMapper.getEnterEventList(map);
	}

	@Override
	public EnterEvent getEnterEventByEnterCode(String event_code) {
		logger.debug("getEnterEventByEnterCode" + event_code);
		return enterMapper.getEnterEventByEnterCode(event_code);
	}

	@Override
	public void delEnterEventByEnterCode(String event_code) {
		logger.debug("delEnterEventByEnterCode" + event_code);
		enterMapper.delEnterEventByEnterCode(event_code);
	}

	@Override
	public String getJobCodeByEventCode(String event_code) {
		logger.debug("getJobCodeByEventCode" + event_code);
		return enterMapper.getJobCodeByEventCode(event_code);
	}

	@Override
	public void updateEnterEvent(EnterEvent event) {
		logger.debug("updateEnterEvent" + event.getEvent_code());
		enterMapper.updateEnterEvent(event);
	}

	@Override
	public void updateEnterEventState(Map<String, Object> map) {
		logger.debug("updateEnterEventState");
		enterMapper.updateEnterEventState(map);
	}

	@Override
	public void addPaperledger(EntityPaper paper) {
		logger.debug("addPaperledger" + paper.getPaper_barcode());
		enterMapper.addPaperledger(paper);

	}

	@Override
	public void addCDledger(EntityCD cd) {
		logger.debug("addCDledger" + cd.getCd_barcode());
		enterMapper.addCDledger(cd);

	}

	@Override
	public void querryEnterOpers(String medium, Map<String, Object> map, EntityPaper paper, EntityCD cd,
			CycleItem cycleitem, EventTransfer transfer, String scope) {
		logger.debug("querryEnterFOpers");
		enterMapper.updateEnterEventState(map);
		if (medium.equals("PAPER")) {
			enterMapper.addPaperledger(paper);
			if (scope.equals("PERSON")) {
				if (String.valueOf(map.get("period")).equals("L")) {
					if (map.get("archive_time") != null && !String.valueOf(map.get("archive_time")).equals("0")) {
						// 更新载体提醒回收时间
						Map<String, Object> map_update = new HashMap<String, Object>();
						map_update.put("paper_barcode", paper.getPaper_barcode());
						map_update.put("expire_time", map.get("expire_time"));
						basicMapper.updatePaperExpireTime(map_update);
					}
				} else if (String.valueOf(map.get("period")).equals("S")) {
					// 更新载体提醒回收时间
					Map<String, Object> map_update = new HashMap<String, Object>();
					map_update.put("paper_barcode", paper.getPaper_barcode());
					map_update.put("expire_time", map.get("expire_time_short"));
					basicMapper.updatePaperExpireTime(map_update);
				}
			}
		} else if (medium.equals("CD")) {
			enterMapper.addCDledger(cd);
			if (scope.equals("PERSON")) {
				if (String.valueOf(map.get("period")).equals("L")) {
					if (map.get("archive_time") != null && !String.valueOf(map.get("archive_time")).equals("0")) {
						// 更新载体提醒回收时间
						Map<String, Object> map_update = new HashMap<String, Object>();
						map_update.put("cd_barcode", cd.getCd_barcode());
						map_update.put("expire_time", map.get("expire_time"));
						basicMapper.updateCDExpireTime(map_update);
					}
				} else if (String.valueOf(map.get("period")).equals("S")) {
					// 更新载体提醒回收时间
					Map<String, Object> map_update = new HashMap<String, Object>();
					map_update.put("cd_barcode", cd.getCd_barcode());
					map_update.put("expire_time", map.get("expire_time_short"));
					basicMapper.updateCDExpireTime(map_update);
				}
			}
		}
		if (transfer != null) {// 需要走流转
			transferMapper.saveEventTranfer(transfer);
			// 如果流转交接确认开启，在交接确认表中增加此次流转的待确认信息
			if (basicService.isConfirmOpen("TRANSFER_CONFIRM")) {
				String entity_name = "";
				if (LedgerService.DISK.equals(transfer.getEntity_type())) {
					entity_name = ledgerService.getCDByBarcode(transfer.getBarcode()).getFile_list();
				} else if (LedgerService.PAPER.equals(transfer.getEntity_type())) {
					entity_name = ledgerService.getPaperByBarcode(transfer.getBarcode()).getFile_title();
				}
				ConfirmRecord record = new ConfirmRecord(transfer.getUser_iidd(), transfer.getUser_name(),
						transfer.getDept_id(), transfer.getDept_name(), transfer.getAccept_user_iidd(),
						transfer.getAccept_user_name(), transfer.getAccept_dept_id(), transfer.getAccept_dept_name(),
						transfer.getEntity_type().toUpperCase(), transfer.getBarcode(), entity_name,
						transfer.getSeclv_name(), "TRANSFER", transfer.getEvent_code(), new Date());
				basicMapper.saveConfirmRecord(record);
			}
		}
		ledgerMapper.addCycleItem(cycleitem);
	}

	@Override
	public int getEnterBarcodeSize(String barcode) {
		logger.debug("getEnterBarcodeSize:" + barcode);
		return enterMapper.getEnterBarcodeSize(barcode);
	}

	@Override
	public void addProcessJob(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType,
			String next_approver, String output_dept_name, String output_user_name, String comment,
			List<String> eventIdList, String usage_code, String project_code) throws Exception {
		logger.debug("addProcessJob");
		ApproveProcess process = basicPrcManage.getApproveProcessByKey(dept_id, String.valueOf(seclv_code),
				jobType.getJobTypeCode(), usage_code, true);
		String job_status = null;
		if (process.getTotal_steps() == 0) {
			job_status = ActivitiCons.APPLY_APPROVED_PASS;
		} else {
			job_status = ActivitiCons.APPLY_APPROVED_DEFAULT;
		}
		String next_approver_name = basicPrcManage.getApproverName(next_approver);
		String job_code = user_iidd + "-" + jobType.getJobTypeCode() + "-" + System.currentTimeMillis();
		ProcessJob job = new ProcessJob(job_code, user_iidd, dept_id, seclv_code, jobType, new Date(), job_status,
				next_approver, next_approver_name, process.getProcess_id());
		job.setOutput_dept_name(output_dept_name);
		job.setOutput_user_name(output_user_name);
		job.setComment(comment);
		// 开启流程
		basicPrcManage.addActivitiApply(job, process);
		// 把任务信息插入数据库
		basicMapper.addProcessJob(job);
		// 更新作业信息
		addEventJobRel(eventIdList, job, usage_code, project_code);
		String user_name = userService.getUserNameByUserId(job.getUser_iidd());
		String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
		String detail = "提交" + jobType.getJobTypeName() + "申请";
		ProcessRecord record = new ProcessRecord(job.getJob_code(), job.getJobType(), job.getUser_iidd(), user_name,
				dept_name, detail, "请审批", new Date());
		activitiService.addProcessRecord(record);
		// 如果有审批流程，在消息表中添加审批消息
		if (process.getTotal_steps() != 0) {
			String message = dept_name + user_name + "有" + jobType.getJobTypeName() + "作业需要您审批";
			String oper_type = "";
			oper_type = jobType.getJobTypeCode();
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(), message,
						new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}
	}

	private void addEventJobRel(List<String> eventIdList, ProcessJob job, String usage_code, String project_code) {
		logger.debug("addEventJobRel");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("job_code", job.getJob_code());
		map.put("usage_code", usage_code);
		map.put("project_code", project_code);
		for (String id : eventIdList) {
			map.put("id", id);
			enterMapper.addEnterEventJobRel(map);
		}
	}

	@Override
	public List<EnterEvent> getEnterEventListByJobCode(String job_code) {
		return enterMapper.getEnterEventListByJobCode(job_code);
	}

	@Override
	public int cancelEnterEventByEventCode(String event_code) {
		// 先查询出该event对应的job_code
		EnterEvent event = enterMapper.getEnterEventByEnterCode(event_code);
		String job_code = event.getJob_code();
		// 把event对应的job_code设置为null,取消审批
		enterMapper.cancelEnterEventByEventCode(event_code);
		// 如果该job_code对应的event数量为空，表示该任务下挂载的作业已经都被取消了，则取消该任务
		if (StringUtils.hasLength(job_code) && enterMapper.getEnterEventCountByJobCode(job_code) == 0) {
			basicService.cancelJob(job_code, event.getJobType().getJobTypeCode());
			return 1;
		}
		return 0;
	}

	@Override
	public boolean checkBarcodeIsUsed(Integer file_type, String barcode) {
		Integer event = 0;
		event = enterMapper.getEnterEventCountByMediumSerial(barcode);
		boolean is_used = false;
		if (event != 0) {
			is_used = true;
		}
		if (file_type == 1) {// 文件
			EntityPaper paper = ledgerService.getPaperByBarcode(barcode);
			if (paper != null) {
				is_used = true;
			}
		} else if (file_type == 2) {// 光盘
			EntityCD cd = ledgerService.getCDByBarcode(barcode);
			if (cd != null) {
				is_used = true;
			}
		}
		return is_used;
	}

	@Override
	public boolean checkBarcodeIsUsedByOthers(Integer file_type, String barcode, String event_code) {
		boolean is_used = false;
		String used_event_code = enterMapper.getEventCodeByBarcode(barcode);
		if (StringUtils.hasLength(used_event_code) && !event_code.equals(used_event_code)) {
			is_used = true;
		}
		if (file_type == 1) {// 文件
			EntityPaper paper = ledgerService.getPaperByBarcode(barcode);
			if (paper != null) {
				is_used = true;
			}
		} else if (file_type == 2) {// 光盘
			EntityCD cd = ledgerService.getCDByBarcode(barcode);
			if (cd != null) {
				is_used = true;
			}
		}
		return is_used;
	}

	@Override
	public void addScanProcessJob(String user_iidd, String dept_id, Integer seclv_code, String next_approver,
			String output_dept_name, String output_user_name, String comment, List<String> eventIdList,
			String usage_code, String project_code) throws Exception {
		logger.debug("addScanProcessJob");
		ApproveProcess process = basicPrcManage.getApproveProcessByKey(dept_id, String.valueOf(seclv_code),
				JobTypeEnum.SCAN_LEADIN.getJobTypeCode(), usage_code, true);
		String job_status = null;
		if (process.getTotal_steps() == 0) {
			job_status = ActivitiCons.APPLY_APPROVED_PASS;
		} else {
			job_status = ActivitiCons.APPLY_APPROVED_DEFAULT;
		}
		String next_approver_name = basicPrcManage.getApproverName(next_approver);
		String job_code = user_iidd + "-" + JobTypeEnum.SCAN_LEADIN.getJobTypeCode() + "-" + System.currentTimeMillis();
		ProcessJob job = new ProcessJob(job_code, user_iidd, dept_id, seclv_code, JobTypeEnum.SCAN_LEADIN, new Date(),
				job_status, next_approver, next_approver_name, process.getProcess_id());
		job.setOutput_dept_name(output_dept_name);
		job.setOutput_user_name(output_user_name);
		job.setComment(comment);
		// 开启流程
		basicPrcManage.addActivitiApply(job, process);
		// 把任务信息插入数据库
		basicMapper.addProcessJob(job);
		// 更新作业信息
		addEventJobRel(eventIdList, job, usage_code, project_code);
		String user_name = userService.getUserNameByUserId(job.getUser_iidd());
		String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
		String detail = "提交" + JobTypeEnum.SCAN_LEADIN.getJobTypeName() + "申请";
		ProcessRecord record = new ProcessRecord(job.getJob_code(), job.getJobType(), job.getUser_iidd(), user_name,
				dept_name, detail, "请审批", new Date());
		activitiService.addProcessRecord(record);
		// 如果有审批流程，在消息表中添加审批消息
		if (process.getTotal_steps() != 0) {
			String message = dept_name + user_name + "有" + JobTypeEnum.SCAN_LEADIN.getJobTypeName() + "作业需要您审批";
			String oper_type = "";
			oper_type = JobTypeEnum.LEADIN.getJobTypeCode();
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(), message,
						new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}
	}

	@Override
	public List<EnterProcessJob> getLeadinCandidateListByUserId(String user_iidd) {
		List<String> instanceIdLeadinList = basicPrcManage.getCandidateInstanceIdList(user_iidd,
				JobTypeEnum.LEADIN.getJobTypeCode());
		List<String> instanceIdScanList = basicPrcManage.getCandidateInstanceIdList(user_iidd,
				JobTypeEnum.SCAN_LEADIN.getJobTypeCode());
		instanceIdLeadinList.addAll(instanceIdScanList);
		if (instanceIdLeadinList != null && instanceIdLeadinList.size() > 0) {
			return enterMapper.getEnterProcessJobListByInstanceId(instanceIdLeadinList);
		}
		return Collections.emptyList();
	}

	@Override
	public List<EnterProcessJob> getleadinApprovedJobByUserId(String user_iidd, String user_name, Integer seclv_code) {
		List<String> instanceIdLeadinList = basicPrcManage.getApprovedInstanceIdList(user_iidd,
				JobTypeEnum.LEADIN.getJobTypeCode());
		List<String> instanceIdScanList = basicPrcManage.getApprovedInstanceIdList(user_iidd,
				JobTypeEnum.SCAN_LEADIN.getJobTypeCode());
		instanceIdLeadinList.addAll(instanceIdScanList);
		if (instanceIdLeadinList != null && instanceIdLeadinList.size() > 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("seclv_code", seclv_code);
			map.put("user_name", user_name);
			map.put("instanceIds", instanceIdLeadinList);
			return enterMapper.getEnterApprovedJobByInstanceIds(map);
		}
		return Collections.emptyList();
	}

	@Override
	public void deleteEnterJob(String id) {
		enterMapper.deleteEnterJob(id);

	}

	@Override
	public List<EnterEvent> getSelfEnterEventList(Map<String, Object> map) {
		logger.debug("getSelfEnterEventList");
		return enterMapper.getSelfEnterEventList(map);
	}

	@Override
	public ReprintCD getEnterEventJoinImportByCDBarcode(String cd_barcode) {
		return enterMapper.getEnterEventJoinImportByCDBarcode(cd_barcode);
	}

	@Override
	public ReprintCD getEnterEventJoinBurnByCDBarcode(String cd_barcode) {
		return enterMapper.getEnterEventJoinBurnByCDBarcode(cd_barcode);
	}

	@Override
	public ReprintCD getEnterEventCDBarcode(String cd_barcode) {
		return enterMapper.getEnterEventCDBarcode(cd_barcode);
	}

	@Override
	public void updateSrccodeByBarcode(String src_code, String barcode, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("barcode", barcode);
		map.put("src_code", src_code);

		if (type.equals("CD")) {
			enterMapper.updateCDSrccode(map);
		} else if (type.equals("DEVICE")) {
			enterMapper.updateDeviceSrccode(map);
		} else {
			enterMapper.updatePaperSrccode(map);
		}
	}

	@Override
	public void addEntityDevice(EntityDevice device, CycleItem cycleitem, Map<String, Object> map) {
		logger.debug("addEntityDevice:" + device.getDevice_code());
		enterMapper.updateEnterEventState(map);
		deviceMapper.addEntityDevice(device);
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("barcode", device.getDevice_barcode());
		map1.put("type", "PERSON");
		deviceMapper.updatePersonalEntityDevice(map1);
		ledgerMapper.addCycleItem(cycleitem);
	}

	@Override
	public EnterEvent getEnterEventByMediumSerial(String medium_serial) {
		logger.debug("getEnterEventByMediumSerial" + medium_serial);
		return enterMapper.getEnterEventByMediumSerial(medium_serial);
	}

	/**
	 * 查询已录入的event的数目
	 * 
	 * @param job_code
	 * @return
	 */
	@Override
	public int getEnterEventEnterCancel(String job_code) {
		return enterMapper.getEnterEventEnterCancel(job_code);
	}

	@Override
	public void updateConfidentialnumByBarcode(String confidential_num, String barcode, String type) {
		logger.debug("updateConfidentialnumByBarcode");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("barcode", barcode);
		map.put("confidential_num", confidential_num);

		if (type.equals("CD")) {
			enterMapper.updateCDConfidentialnum(map);
		} else if (type.equals("DEVICE")) {
			enterMapper.updateDeviceConfidentialnum(map);
		} else {
			enterMapper.updatePaperConfidentialnum(map);
		}
	}
}
