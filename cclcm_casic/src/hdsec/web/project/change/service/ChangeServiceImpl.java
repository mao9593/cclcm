package hdsec.web.project.change.service;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.activiti.service.ActivitiService;
import hdsec.web.project.activiti.util.ActivitiCons;
import hdsec.web.project.basic.mapper.BasicMapper;
import hdsec.web.project.basic.model.ClientMsg;
import hdsec.web.project.basic.service.BasicPrcManage;
import hdsec.web.project.basic.service.BasicService;
import hdsec.web.project.change.mapper.ChangeMapper;
import hdsec.web.project.change.model.EventChange;
import hdsec.web.project.ledger.mapper.LedgerMapper;
import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.ledger.model.EntityStateEnum;
import hdsec.web.project.ledger.service.LedgerService;
import hdsec.web.project.user.service.UserService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

public class ChangeServiceImpl implements ChangeService {
	private final Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private LedgerService ledgerService;
	@Resource
	private BasicMapper basicMapper;
	@Resource
	private BasicPrcManage basicPrcManage;
	@Resource
	private UserService userService;
	@Resource
	private ActivitiService activitiService;
	@Resource
	private ChangeMapper changeMapper;
	@Resource
	private LedgerMapper ledgerMapper;
	@Resource
	private BasicService basicService;

	@Override
	public void addProcessJob(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType,
			String next_approver, String comment, List<String> barcodeList, String usage_code, String entity_type,
			String scope_dept_id, String change_type, String accept_user_iidd) throws Exception {

		logger.debug("addChangeProcessJob");
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
		job.setComment(comment);

		job.setAccept_user_iidd(accept_user_iidd);// 设置接收人
		// 开启流程
		basicPrcManage.addActivitiApply(job, process);
		// 把任务信息插入数据库
		basicMapper.addProcessJob(job);
		// 添加event
		int i = 0;
		if (entity_type.equalsIgnoreCase("paper")) {
			for (String barcode : barcodeList) {
				EntityPaper paper = ledgerService.getPaperByBarcode(barcode);

				// 改变entityPaper的job_code
				/*
				 * Map<String, Object> map = new HashMap<String, Object>(); map.put("job_code", job_code);
				 * map.put("barcode", barcode); ledgerService.updatePaperJobCodeByBarCode(map);
				 */

				String event_code = user_iidd + "-CHANGE-" + System.currentTimeMillis() + i++;
				if (change_type.equalsIgnoreCase("toPERSON")) {
					scope_dept_id = paper.getScope_dept_id();
				}
				EventChange event = new EventChange();
				event.setUser_iidd(user_iidd);
				event.setDept_id(dept_id);
				event.setEvent_code(event_code);
				event.setEntity_type(entity_type);
				event.setScope_dept_id(scope_dept_id);
				event.setChange_type(change_type);
				event.setSeclv_code(paper.getSeclv_code());
				event.setBarcode(paper.getPaper_barcode());
				event.setFile_name(paper.getFile_title());
				event.setApply_time(new Date());
				event.setUsage_code(usage_code);
				event.setSumm(comment);
				event.setJob_code(job_code);
				changeMapper.addChangeEvent(event);
				// 申请载体归属转换后，修改载体状态为归属转换中，防止转换中的载体再进行其它操作 mod by rmf 20150502
				Map<String, Object> stateMap = new HashMap<String, Object>();
				stateMap.put("barcode", barcode);
				stateMap.put("state", EntityStateEnum.STATE12.getKey());
				changeMapper.updatePaperStateByBarcode(stateMap);
				// above mod by rmf 20150502
			}
		} else if (entity_type.equalsIgnoreCase("cd")) {
			for (String barcode : barcodeList) {
				EntityCD cd = ledgerService.getCDByBarcode(barcode);

				// 改变entityCD的job_code
				/*
				 * Map<String, Object> map = new HashMap<String, Object>(); map.put("job_code", job_code);
				 * map.put("barcode", barcode); ledgerService.updateCDJobCodeByBarCode(map);
				 */

				String event_code = user_iidd + "-CHANGE-" + System.currentTimeMillis() + i++;
				if (change_type.equalsIgnoreCase("toPERSON")) {
					scope_dept_id = cd.getScope_dept_id();
				}
				EventChange event = new EventChange();
				event.setUser_iidd(user_iidd);
				event.setDept_id(dept_id);
				event.setEvent_code(event_code);
				event.setEntity_type(entity_type);
				event.setScope_dept_id(scope_dept_id);
				event.setChange_type(change_type);
				event.setSeclv_code(cd.getSeclv_code());
				event.setBarcode(cd.getCd_barcode());
				event.setFile_name(cd.getFile_list());
				event.setApply_time(new Date());
				event.setUsage_code(usage_code);
				event.setSumm(comment);
				event.setJob_code(job_code);
				changeMapper.addChangeEvent(event);
				// 申请载体归属转换后，修改载体状态为归属转换中，防止转换中的载体再进行其它操作 mod by rmf 20150502
				Map<String, Object> stateMap = new HashMap<String, Object>();
				stateMap.put("barcode", barcode);
				stateMap.put("state", EntityStateEnum.STATE12.getKey());
				changeMapper.updateCDStateByBarcode(stateMap);
				// above mod by rmf 20150502
			}
		}
		String user_name = userService.getUserNameByUserId(job.getUser_iidd());
		String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
		String detail = "提交" + jobType.getJobTypeName() + "申请";
		ProcessRecord record = new ProcessRecord(job.getJob_code(), job.getJobType(), job.getUser_iidd(), user_name,
				dept_name, detail, "请审批", new Date());
		activitiService.addProcessRecord(record);
		// 如果有审批流程，在消息表中添加审批消息
		if (process.getTotal_steps() != 0) {
			String message = dept_name + user_name + "有归属转换作业需要您审批";
			String oper_type = "CHANGE";
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(), message,
						new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}

	}

	@Override
	public List<ProcessJob> getJobList(Map<String, Object> map) {
		logger.debug("getJobList");
		return changeMapper.getJobList(map);
	}

	@Override
	public List<EventChange> getChangeEventListByJobCode(String job_code) {
		List<EventChange> eventList = changeMapper.getChangeEventListByJobCode(job_code);
		for (EventChange event : eventList) {
			if ("paper".equalsIgnoreCase(event.getEntity_type())) {
				EntityPaper paper = ledgerService.getPaperByBarcode(event.getBarcode());
				if (null != paper) {
					event.setFile_name(paper.getFile_title());
				}
			} else if ("cd".equalsIgnoreCase(event.getEntity_type())) {
				EntityCD cd = ledgerService.getCDByBarcode(event.getBarcode());

				if (null != cd) {
					event.setFile_name(cd.getFile_list());
				}
			}
		}
		return eventList;
	}

	@Override
	public EventChange getChangeEventByCode(String event_code) {
		logger.debug("getChangeEventByCode");
		EventChange event = changeMapper.getChangeEventByCode(event_code);
		if ("paper".equalsIgnoreCase(event.getEntity_type())) {
			EntityPaper paper = ledgerService.getPaperByBarcode(event.getBarcode());
			if (null != paper) {
				event.setFile_name(paper.getFile_title());
			}
		} else if ("cd".equalsIgnoreCase(event.getEntity_type())) {
			EntityCD cd = ledgerService.getCDByBarcode(event.getBarcode());
			if (null != cd) {
				event.setFile_name(cd.getFile_list());
			}
		}
		return event;
	}

	@Override
	public String getJobCodeByEventCode(String event_code) {
		logger.debug("getJobCodeByEventCode");
		return changeMapper.getJobCodeByEventCode(event_code);
	}

	@Override
	public int cancelChangeEventByCode(String event_code) {
		logger.debug("cancelChangeEventByCode");
		int size = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("event_code", event_code);
		EventChange event = changeMapper.getChangeEventByCode(event_code);
		String job_code = event.getJob_code();
		List<EventChange> events = changeMapper.getChangeEventListByJobCode(event.getJob_code());
		changeMapper.cancelChangeEvent(map);
		// 归属转换撤销后，将载体状态更改回留用状态 mod by rmf 20150502
		map.put("barcode", event.getBarcode());
		map.put("state", EntityStateEnum.STATE0.getKey());
		if (event.getEntity_type().equalsIgnoreCase("paper")) {
			changeMapper.updatePaperStateByBarcode(map);
		} else {
			changeMapper.updateCDStateByBarcode(map);
		}
		// above mod by rmf 20150502
		if (null != events && events.size() > 1) {
			size = events.size() - 1;
		} else {
			basicService.cancelJob(job_code, "CHANGE");
		}
		return size;
	}

	@Override
	public List<EventChange> getChangeEventList(Map<String, Object> map) {
		logger.debug("getChangeEventList");
		List<EventChange> eventList = changeMapper.getChangeEventList(map);

		for (EventChange event : eventList) {
			if ("paper".equalsIgnoreCase(event.getEntity_type())) {
				EntityPaper paper = ledgerService.getPaperByBarcode(event.getBarcode());
				if (null != paper) {
					event.setFile_name(paper.getFile_title());
				}
			} else if ("cd".equalsIgnoreCase(event.getEntity_type())) {
				EntityCD cd = ledgerService.getCDByBarcode(event.getBarcode());
				if (null != cd) {
					event.setFile_name(cd.getFile_list());
				}
			}
		}
		return eventList;
	}

	@Override
	public void changeEntityScope(String event_code, String duty_user_iidd, String duty_user_name, String duty_dept_id,
			String duty_dept_name) {
		logger.debug("changeEntityScope");
		// 修改event状态
		EventChange event = changeMapper.getChangeEventByCode(event_code);
		event.setFinish_time(new Date());
		event.setChange_status(1);
		changeMapper.updateChangeEvent(event);

		String entity_type = event.getEntity_type();
		String change_type = event.getChange_type();
		if (entity_type.equalsIgnoreCase("paper")) {
			// 全生命周期记录
			CycleItem cycleitem = new CycleItem();
			cycleitem.setBarcode(event.getBarcode());
			cycleitem.setEntity_type("PAPER");
			cycleitem.setOper_time(new Date());
			cycleitem.setUser_name(event.getUser_name());
			cycleitem.setDept_name(event.getDept_name());
			// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
			String job_code = getJobCodeByEventCode(event.getEvent_code());
			cycleitem.setJob_code(job_code);
			// add ending
			// 修改载体属性
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("paper_barcode", event.getBarcode());
			if (change_type.equalsIgnoreCase("toDEPT")) {

				map.put("duty_user_iidd", duty_user_iidd);

				map.put("duty_user_name", duty_user_name);

				map.put("duty_dept_name", duty_dept_name);

				map.put("duty_dept_id", duty_dept_id);

				map.put("scope", "DEPT");
				map.put("scope_dept_id", event.getScope_dept_id());
				map.put("scope_dept_name", userService.getDeptNameByDeptId(event.getScope_dept_id()));
				cycleitem.setOper("CHANGETODEPT");
			} else if (change_type.equalsIgnoreCase("toPERSON")) {
				map.put("scope", "PERSON");
				map.put("scope_dept_id", "");
				map.put("scope_dept_name", "");

				map.put("duty_user_iidd", duty_user_iidd);

				map.put("duty_user_name", duty_user_name);

				map.put("duty_dept_name", duty_dept_name);

				map.put("duty_dept_id", duty_dept_id);

				cycleitem.setOper("CHANGETOPERSON");
			}
			changeMapper.updateEntityPaperScope(map);
			// 归属转换完成后，将载体状态更改回留用状态 mod by rmf 20150502
			Map<String, Object> stateMap = new HashMap<String, Object>();
			stateMap.put("barcode", event.getBarcode());
			stateMap.put("state", EntityStateEnum.STATE0.getKey());
			changeMapper.updatePaperStateByBarcode(stateMap);
			// above mod by rmf 20150502
			ledgerMapper.addCycleItem(cycleitem);

		} else if (entity_type.equalsIgnoreCase("cd")) {
			// 全生命周期记录
			CycleItem cycleitem = new CycleItem();
			cycleitem.setBarcode(event.getBarcode());
			cycleitem.setEntity_type("CD");
			cycleitem.setOper_time(new Date());
			cycleitem.setUser_name(event.getUser_name());
			cycleitem.setDept_name(event.getDept_name());
			// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
			String job_code = getJobCodeByEventCode(event.getEvent_code());
			cycleitem.setJob_code(job_code);
			// add ending
			// 修改载体属性
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cd_barcode", event.getBarcode());
			if (change_type.equalsIgnoreCase("toDEPT")) {

				map.put("duty_user_iidd", duty_user_iidd);

				map.put("duty_user_name", duty_user_name);

				map.put("duty_dept_name", duty_dept_name);

				map.put("duty_dept_id", duty_dept_id);

				map.put("scope", "DEPT");
				map.put("scope_dept_id", event.getScope_dept_id());
				map.put("scope_dept_name", userService.getDeptNameByDeptId(event.getScope_dept_id()));
				cycleitem.setOper("CHANGETODEPT");
			} else if (change_type.equalsIgnoreCase("toPERSON")) {
				map.put("scope", "PERSON");
				map.put("scope_dept_id", "");
				map.put("scope_dept_name", "");

				map.put("duty_user_iidd", duty_user_iidd);

				map.put("duty_user_name", duty_user_name);

				map.put("duty_dept_name", duty_dept_name);

				map.put("duty_dept_id", duty_dept_id);

				cycleitem.setOper("CHANGETOPERSON");
			}
			changeMapper.updateEntityCDScope(map);
			// 归属转换完成后，将载体状态更改回留用状态 mod by rmf 20150502
			Map<String, Object> stateMap = new HashMap<String, Object>();
			stateMap.put("barcode", event.getBarcode());
			stateMap.put("state", EntityStateEnum.STATE0.getKey());
			changeMapper.updateCDStateByBarcode(stateMap);
			// above mod by rmf 20150502
			ledgerMapper.addCycleItem(cycleitem);
		}
	}

	@Override
	public ProcessJob getProcessJobByJobCode(String job_code) {
		return changeMapper.getProcessJobByJobCode(job_code);
	}

	@Override
	public void refuseChangeEntityScope(String event_code, String barcode) {
		logger.debug("refuseChangeEntityScope");
		// 载体状态更新为留用
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("barcode", barcode);
		map.put("state", EntityStateEnum.STATE0.getKey());
		changeMapper.updatePaperStateByBarcode(map);
		// 修改event状态
		EventChange event = changeMapper.getChangeEventByCode(event_code);
		event.setFinish_time(new Date());
		event.setChange_status(2);
		changeMapper.updateChangeEvent(event);
	}
}
