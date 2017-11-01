package hdsec.web.project.asset.service;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.activiti.service.ActivitiService;
import hdsec.web.project.activiti.util.ActivitiCons;
import hdsec.web.project.asset.mapper.AssetMapper;
import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.asset.model.EntityProperty;
import hdsec.web.project.asset.model.PropertyKind;
import hdsec.web.project.asset.model.PurchaseEvent;
import hdsec.web.project.asset.model.StorageEvent;
import hdsec.web.project.asset.model.WasteEvent;
import hdsec.web.project.basic.mapper.BasicMapper;
import hdsec.web.project.basic.model.ClientMsg;
import hdsec.web.project.basic.service.BasicPrcManage;
import hdsec.web.project.basic.service.BasicService;
import hdsec.web.project.client.service.ClientService;
import hdsec.web.project.ledger.model.EntityPaper;
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

public class AssetServiceImpl implements AssetService {
	private final Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private AssetMapper assetMapper;
	@Resource
	private BasicMapper basicMapper;
	@Resource
	private BasicPrcManage basicPrcManage;
	@Resource
	private UserService userService;
	@Resource
	private ActivitiService activitiService;
	@Resource
	private BasicService basicService;
	@Resource
	private ClientService clientService;

	@Override
	public void addPurchaseEvent(PurchaseEvent event) {
		logger.debug("addPurchaseEvent" + event.getEvent_code());
		assetMapper.addPurchaseEvent(event);
	}

	@Override
	public List<PurchaseEvent> getPurchaseEventList(Map<String, Object> map) {
		logger.debug("getPurchaseEventList");
		return assetMapper.getPurchaseEventList(map);
	}

	@Override
	public int getSelfPropertyLedgerSize(Map<String, Object> map) {
		logger.debug("getSelfPropertyLedgerSize");
		return assetMapper.getSelfPropertyLedgerSize(map);
	}

	@Override
	public void addProcessJob(String user_iidd, String dept_id,
			Integer seclv_code, JobTypeEnum jobType, String next_approver,
			String comment, List<String> eventIdList, String usage_code,
			String project_code) throws Exception {
		logger.debug("addProcessJob");
		ApproveProcess process = basicPrcManage.getApproveProcessByKey(dept_id,
				String.valueOf(seclv_code), jobType.getJobTypeCode(),
				usage_code, true);
		String job_status = null;
		if (process.getTotal_steps() == 0) {
			job_status = ActivitiCons.APPLY_APPROVED_PASS;
		} else {
			job_status = ActivitiCons.APPLY_APPROVED_DEFAULT;
		}
		String next_approver_name = basicPrcManage
				.getApproverName(next_approver);
		String job_code = user_iidd + "-" + jobType.getJobTypeCode() + "-"
				+ System.currentTimeMillis();
		ProcessJob job = new ProcessJob(job_code, user_iidd, dept_id,
				seclv_code, jobType, new Date(), job_status, next_approver,
				next_approver_name, process.getProcess_id());
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
		ProcessRecord record = new ProcessRecord(job.getJob_code(),
				job.getJobType(), job.getUser_iidd(), user_name, dept_name,
				detail, "请审批", new Date());
		activitiService.addProcessRecord(record);
		// 如果有审批流程，在消息表中添加审批消息
		if (process.getTotal_steps() != 0) {
			String message = dept_name + user_name + "有"
					+ jobType.getJobTypeName() + "作业需要您审批";
			String oper_type = "";
			oper_type = jobType.getJobTypeCode();
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName,
						oper_type, 1, job.getJob_code(), message, new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}
	}

	private void addEventJobRel(List<String> eventIdList, ProcessJob job,
			String usage_code, String project_code) {
		logger.debug("addEventJobRel");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("job_code", job.getJob_code());
		map.put("usage_code", usage_code);
		map.put("project_code", project_code);
		for (String event_code : eventIdList) {
			map.put("event_code", event_code);
			assetMapper.addPurchaseEventJobRel(map);
		}
	}

	@Override
	public PurchaseEvent getPurchaseEventByCode(String event_code) {
		logger.debug("getPurchaseEventByCode" + event_code);
		return assetMapper.getPurchaseEventByCode(event_code);
	}

	@Override
	public void addPropertyledger(Map<String, Object> map,
			Map<String, Object> mapP, EntityProperty property,
			CycleItem cycleitem) {
		logger.debug("addPropertyledger");
		assetMapper.updatePurchasestatus(mapP);
		assetMapper.updateStoragestatus(map);
		assetMapper.addPropertyledger(property);
		assetMapper.addCycleItem(cycleitem);
		assetMapper.updateKindSerialNo(map);
	}

	@Override
	public List<EntityProperty> getPropertyList(Map<String, Object> map) {
		logger.debug("getPropertyList");
		return assetMapper.getPropertyList(map);
	}

	/*
	 * @Override public List<EntityProperty>
	 * getSelfPropertyLedgerList(Map<String, Object> map, RowBounds rbs) {
	 * logger.debug("getSelfPropertyLedgerList"); return
	 * assetMapper.getSelfPropertyLedgerList(map, rbs); }
	 */
	@Override
	public List<WasteEvent> getWasteEventList(Map<String, Object> map) {
		logger.debug("getWasteEventList");
		return assetMapper.getWasteEventList(map);
	}

	@Override
	public List<PurchaseEvent> getPurchaseEventListByJobCode(String job_code) {
		logger.debug("getPurchaseEventListByJobCode");
		return assetMapper.getPurchaseEventListByJobCode(job_code);
	}

	@Override
	public String getJobCodeByEventCode(String event_code) {
		logger.debug("getJobCodeByEventCode" + event_code);
		return assetMapper.getJobCodeByEventCode(event_code);
	}

	@Override
	public void delPurchaseEventByEnterCode(String event_code, String eventtype) {
		logger.debug("delPurchaseEventByEnterCode" + event_code);
		if (!eventtype.equals("del")) {
			String job_code = assetMapper.getJobCodeByEventCode(event_code);
			if (!job_code.isEmpty()) {
				assetMapper.delJob(job_code);
			}
		}
		assetMapper.delPurchaseEventByEnterCode(event_code);
	}

	@Override
	public EntityProperty getPropertyByIDBarcode(Map<String, String> map) {
		logger.debug("getPropertyByIDBarcode");
		return assetMapper.getPropertyByIDBarcode(map);
	}

	@Override
	public List<CycleItem> getCycleItemListByBarcode(String barcode) {
		logger.debug("getCycleItemListByBarcode");
		return assetMapper.getCycleItemListByBarcode(barcode);
	}

	@Override
	public void handlePropertyJob(String user_iidd, String dept_id,
			Integer seclv_code, JobTypeEnum jobType, String next_approver,
			String property_barcode, String usage_code) throws Exception {
		logger.debug("handleDeviceJob");
		ApproveProcess process = basicService.getApproveProcessByKey(dept_id,
				seclv_code, jobType.getJobTypeCode(), usage_code);
		String job_status = null;
		if (process.getTotal_steps() == 0) {
			job_status = ActivitiCons.APPLY_APPROVED_PASS;
		} else {
			job_status = ActivitiCons.APPLY_APPROVED_DEFAULT;
		}
		String next_approver_name = basicService.getApproverName(next_approver);
		String job_code = user_iidd + "-" + jobType.getJobTypeCode() + "-"
				+ System.currentTimeMillis();
		ProcessJob job = new ProcessJob(job_code, user_iidd, dept_id,
				seclv_code, jobType, new Date(), job_status, next_approver,
				next_approver_name, process.getProcess_id());
		// 开启流程
		basicPrcManage.addActivitiApply(job, process);
		// 把任务信息插入数据库
		basicMapper.addProcessJob(job);
		// 更新作业信息
		addEntityPropertyJobRel(property_barcode, job, jobType.getJobTypeCode());
		String user_name = userService.getUserNameByUserId(job.getUser_iidd());
		String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
		String detail = "提交" + jobType.getJobTypeName() + "申请";
		ProcessRecord record = new ProcessRecord(job.getJob_code(),
				job.getJobType(), job.getUser_iidd(), user_name, dept_name,
				detail, "请审批", new Date());
		activitiService.addProcessRecord(record);
		// 如果有审批流程，在消息表中添加审批消息
		if (process.getTotal_steps() != 0) {
			String message = "";
			String oper_type = "";
			message = dept_name + user_name + "有" + jobType.getJobTypeName()
					+ "申请需要您审批";
			oper_type = jobType.getJobTypeCode();
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName,
						oper_type, 1, job.getJob_code(), message, new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}
	}

	private void addEntityPropertyJobRel(String property_barcode,
			ProcessJob job, String jobtype_code) {
		logger.debug("addEntityPropertyJobRel");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("property_barcode", property_barcode);
		map.put("job_code", job.getJob_code());
		if (jobtype_code.equals("PROPERTYOUT")) {// 出库
			map.put("property_status", '1');
		} else if (jobtype_code.equals("STORE")) {// 入库
			map.put("property_status", '5');
		} else {// 默认报废
			map.put("property_status", '3');
		}
		assetMapper.updateEntityPropertyStatus(map);
	}

	@Override
	public void cancelHandlePropertyByCode(String property_barcode) {
		logger.debug("cancelHandlePropertyByCode");
		Map<String, String> map = new HashMap<String, String>();
		map.put("property_barcode", property_barcode);
		EntityProperty property = assetMapper.getPropertyByIDBarcode(map);
		String job_code = property.getJob_code();
		// 把event对应的job_code设置为null,取消审批
		assetMapper.cancelHandlePropertyByCode(property_barcode);
		// 如果该job_code对应的event数量为空，表示该任务下挂载的作业已经都被取消了，则取消该任务
		if (StringUtils.hasLength(job_code)
				&& assetMapper.getHandlePropertyCountByJobCode(job_code) == 0) {
			String instance_id = basicService.getProcessJobByCode(job_code)
					.getInstance_id();
			basicPrcManage.suspendProcessInstanceById(instance_id);
			basicService.delJob(job_code);
			clientService.delClientMsgByJobCode(job_code);
		}
	}

	@Override
	public void addCycleItem(CycleItem cycleitem) {
		logger.debug("addCycleItem");
		assetMapper.addCycleItem(cycleitem);
	}

	@Override
	public void updatePropertyStatus(String property_barcode,
			Integer property_status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("property_barcode", property_barcode);
		map.put("property_status", property_status);
		assetMapper.updatePropertyStatus(map);
	}

	@Override
	public List<ProcessJob> getHandleJobListByEntity(String user_iidd,
			String type) {
		List<ProcessJob> jobList = null;
		List<String> instanceIdList = new ArrayList<String>();
		List<String> tempList = new ArrayList<String>();
		tempList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd,
				JobTypeEnum.PROPERTYOUT.getJobTypeCode()));
		tempList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd,
				JobTypeEnum.WASTE.getJobTypeCode()));
		if (tempList != null && tempList.size() > 0) {
			instanceIdList.addAll(tempList);
			tempList.clear();
		}
		if (instanceIdList != null && instanceIdList.size() > 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("type", type);
			map.put("instanceIdList", instanceIdList);
			jobList = basicMapper.getHandleJobListByEntityInstanceId(map);
		}
		if (jobList != null && jobList.size() > 0) {
			return jobList;
		}
		return Collections.emptyList();
	}

	@Override
	public List<ProcessJob> getHandleInJobListByEntity(String user_iidd,
			String type) {
		List<ProcessJob> jobList = null;
		List<String> instanceIdList = new ArrayList<String>();
		List<String> tempList = new ArrayList<String>();
		tempList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd,
				JobTypeEnum.STORE.getJobTypeCode()));
		if (tempList != null && tempList.size() > 0) {
			instanceIdList.addAll(tempList);
			tempList.clear();
		}
		if (instanceIdList != null && instanceIdList.size() > 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("type", type);
			map.put("instanceIdList", instanceIdList);
			jobList = basicMapper.getHandleJobListByEntityInstanceId(map);
		}
		if (jobList != null && jobList.size() > 0) {
			return jobList;
		}
		return Collections.emptyList();
	}

	@Override
	public List<ProcessJob> getHandledJobByUserId(String user_iidd,
			String type, String user_name, Integer seclv_code,
			String jobType_code) {
		List<ProcessJob> jobList = null;
		List<String> instanceIdList = new ArrayList<String>();
		List<String> tempList = new ArrayList<String>();
		if (StringUtils.hasLength(jobType_code)) {
			instanceIdList = basicPrcManage.getApprovedInstanceIdList(
					user_iidd, jobType_code);
		} else {
			tempList.addAll(basicPrcManage.getApprovedInstanceIdList(user_iidd,
					JobTypeEnum.PROPERTYOUT.getJobTypeCode()));
			tempList.addAll(basicPrcManage.getApprovedInstanceIdList(user_iidd,
					JobTypeEnum.WASTE.getJobTypeCode()));
			if (tempList != null && tempList.size() > 0) {
				instanceIdList.addAll(tempList);
				tempList.clear();
			}
		}
		if (instanceIdList != null && instanceIdList.size() > 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("type", type);
			map.put("seclv_code", seclv_code);
			map.put("user_name", user_name);
			map.put("instanceIdList", instanceIdList);
			jobList = basicMapper.getHandleJobListByEntityInstanceId(map);
		}
		if (jobList != null && jobList.size() > 0) {
			return jobList;
		}
		return Collections.emptyList();
	}

	@Override
	public List<ProcessJob> getHandledInJobByUserId(String user_iidd,
			String type, String user_name, Integer seclv_code,
			String jobType_code) {
		List<ProcessJob> jobList = null;
		List<String> instanceIdList = new ArrayList<String>();
		List<String> tempList = new ArrayList<String>();
		if (StringUtils.hasLength(jobType_code)) {
			instanceIdList = basicPrcManage.getApprovedInstanceIdList(
					user_iidd, jobType_code);
		} else {
			tempList.addAll(basicPrcManage.getApprovedInstanceIdList(user_iidd,
					JobTypeEnum.STORE.getJobTypeCode()));
			if (tempList != null && tempList.size() > 0) {
				instanceIdList.addAll(tempList);
				tempList.clear();
			}
		}
		if (instanceIdList != null && instanceIdList.size() > 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("type", type);
			map.put("seclv_code", seclv_code);
			map.put("user_name", user_name);
			map.put("instanceIdList", instanceIdList);
			jobList = basicMapper.getHandleJobListByEntityInstanceId(map);
		}
		if (jobList != null && jobList.size() > 0) {
			return jobList;
		}
		return Collections.emptyList();
	}

	@Override
	public List<EntityProperty> getPropertyListByJobCode(String job_code) {
		logger.debug("getPropertyListByJobCode");
		return assetMapper.getPropertyListByJobCode(job_code);
	}

	@Override
	public List<PropertyKind> getKindList() {
		logger.debug("getKindList");
		return assetMapper.getKindList();
	}

	@Override
	public void addKindType(PropertyKind type) {
		logger.debug("addKindType");
		assetMapper.addKindType(type);
	}

	@Override
	public PropertyKind getKindTypeByID(Integer id) {
		return assetMapper.getKindTypeByID(id);
	}

	@Override
	public void delKindTypeByID(Integer id) {
		assetMapper.delKindTypeByID(id);
	}

	@Override
	public void updateKindType(PropertyKind type) {
		assetMapper.updateKindType(type);
	}

	@Override
	public void addStorageEvent(StorageEvent event, String next_approver)
			throws Exception {
		logger.debug("addStorageEvent" + event.getEvent_code());
		assetMapper.addStorageEvent(event);
		ApproveProcess process = basicPrcManage.getApproveProcessByKey(
				event.getDept_id(), String.valueOf(event.getSeclv_code()),
				event.getJobType().getJobTypeCode(), event.getUsage_code(),
				true);
		String job_status = null;
		if (process.getTotal_steps() == 0) {
			job_status = ActivitiCons.APPLY_APPROVED_PASS;
		} else {
			job_status = ActivitiCons.APPLY_APPROVED_DEFAULT;
		}
		String next_approver_name = basicPrcManage
				.getApproverName(next_approver);
		String job_code = event.getUser_iidd() + "-" + event.getJobType() + "-"
				+ System.currentTimeMillis();
		ProcessJob job = new ProcessJob(job_code, event.getUser_iidd(),
				event.getDept_id(), event.getSeclv_code(), event.getJobType(),
				new Date(), job_status, next_approver, next_approver_name,
				process.getProcess_id());
		job.setComment(event.getSumm());
		// 开启流程
		basicPrcManage.addActivitiApply(job, process);
		// 把任务信息插入数据库
		basicMapper.addProcessJob(job);
		Map<String, String> map = new HashMap<String, String>();
		map.put("event_code", event.getEvent_code());
		map.put("job_code", job_code);
		assetMapper.updateStorageEventJobCode(map);
		// 更新采购表，已提起入库申请
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("event_code", event.getEvent_code_purchase());
		map1.put("store_status", 2);
		assetMapper.updatePurchasestatus(map1);
		// 更新作业信息
		String user_name = userService.getUserNameByUserId(job.getUser_iidd());
		String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
		String detail = "提交" + event.getJobType().getJobTypeName() + "申请";
		ProcessRecord record = new ProcessRecord(job.getJob_code(),
				job.getJobType(), job.getUser_iidd(), user_name, dept_name,
				detail, "请审批", new Date());
		activitiService.addProcessRecord(record);
		// 如果有审批流程，在消息表中添加审批消息
		if (process.getTotal_steps() != 0) {
			String message = dept_name + user_name + "有"
					+ event.getJobType().getJobTypeName() + "作业需要您审批";
			// String oper_type = "USERSECLV_CHANGE";
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName,
						event.getJobType().toString(), 1, job.getJob_code(),
						message, new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}
	}

	@Override
	public List<StorageEvent> getStorageEventList(Map<String, Object> map) {
		logger.debug("getStorageEventList");
		return assetMapper.getStorageEventList(map);
	}

	@Override
	public void delStorageEventByEnterCode(String event_code) {
		logger.debug("delStorageEventByEnterCode" + event_code);
		// String job_code =
		// assetMapper.getStorageJobCodeByEventCode(event_code);
		StorageEvent event = assetMapper.getStorageEventByCode(event_code);
		if (!event.getJob_code().isEmpty()) {
			assetMapper.delJob(event.getJob_code());
		}
		assetMapper.delStorageEventByEventCode(event_code);
		// 更新采购作业状态为0
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("event_code", event.getEvent_code_purchase());
		map.put("store_status", 0);
		assetMapper.updatePurchasestatus(map);
	}

	@Override
	public String getStorageJobCodeByEventCode(String event_code) {
		logger.debug("getStorageJobCodeByEventCode" + event_code);
		return assetMapper.getStorageJobCodeByEventCode(event_code);
	}

	@Override
	public StorageEvent getStorageEventByCode(String event_code) {
		logger.debug("getStorageEventByCode" + event_code);
		return assetMapper.getStorageEventByCode(event_code);
	}

	@Override
	public List<StorageEvent> getStorageEventListByJobCode(String job_code) {
		logger.debug("getStorageEventListByJobCode");
		return assetMapper.getStorageEventListByJobCode(job_code);
	}

	@Override
	public void updatePropertyNoByCode(Map<String, Object> map) {
		logger.debug("updatePropertyNoByCode");
		assetMapper.updatePropertyNoByCode(map);
	}

	@Override
	public void updateVoucherNoByCode(Map<String, Object> map) {
		logger.debug("updateVoucherNoByCode");
		assetMapper.updateVoucherNoByCode(map);
	}

	@Override
	public void addWasteEvent(WasteEvent waste_event, String next_approver,
			String handleType) throws Exception {
		logger.debug("addWasteEvent" + waste_event.getEvent_code());
		assetMapper.addWasteEvent(waste_event);
		ApproveProcess process = basicPrcManage.getApproveProcessByKey(
				waste_event.getDept_id(), String.valueOf(waste_event
						.getSeclv_code()), waste_event.getJobType()
						.getJobTypeCode(), waste_event.getUsage_code(), true);
		String job_status = null;
		if (process.getTotal_steps() == 0) {
			job_status = ActivitiCons.APPLY_APPROVED_PASS;
		} else {
			job_status = ActivitiCons.APPLY_APPROVED_DEFAULT;
		}
		String next_approver_name = basicPrcManage
				.getApproverName(next_approver);
		String job_code = waste_event.getUser_iidd() + "-"
				+ waste_event.getJobType() + "-" + System.currentTimeMillis();
		ProcessJob job = new ProcessJob(job_code, waste_event.getUser_iidd(),
				waste_event.getDept_id(), waste_event.getSeclv_code(),
				waste_event.getJobType(), new Date(), job_status,
				next_approver, next_approver_name, process.getProcess_id());
		job.setComment(waste_event.getSumm());
		// 开启流程
		basicPrcManage.addActivitiApply(job, process);
		// 把任务信息插入数据库
		basicMapper.addProcessJob(job);
		Map<String, String> map = new HashMap<String, String>();
		map.put("event_code", waste_event.getEvent_code());
		map.put("job_code", job_code);
		/*
		 * if (waste_event.getJobType() == JobTypeEnum.WASTE) {
		 * map.put("waste_status", "3"); } else { map.put("waste_status", "6");
		 * }
		 */
		assetMapper.updateWasteEventStatus(map);
		// 置资产状态
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("id", waste_event.getProperty_id());
		if ("WASTE".endsWith(handleType)) {// 报废
			map1.put("property_status", 3);
		} else {// 变更
			map1.put("property_status", 6);
		}

		assetMapper.updatePropertyStatusByID(map1);

		// 更新作业信息
		String user_name = userService.getUserNameByUserId(job.getUser_iidd());
		String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
		String detail = "提交" + waste_event.getJobType().getJobTypeName() + "申请";
		ProcessRecord record = new ProcessRecord(job.getJob_code(),
				job.getJobType(), job.getUser_iidd(), user_name, dept_name,
				detail, "请审批", new Date());
		activitiService.addProcessRecord(record);
		// 如果有审批流程，在消息表中添加审批消息
		if (process.getTotal_steps() != 0) {
			String message = dept_name + user_name + "有"
					+ waste_event.getJobType().getJobTypeName() + "作业需要您审批";
			// String oper_type = "USERSECLV_CHANGE";
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName,
						waste_event.getJobType().toString(), 1,
						job.getJob_code(), message, new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}
		map.put("event_code", waste_event.getEvent_code());
		map.put("job_code", job_code);
		// assetMapper.updateUSeclvChangeEventJobCode(map);
		// 更新作业信息
		// user_name = userService.getUserNameByUserId(job.getUser_iidd());
		// dept_name = userService.getDeptNameByDeptId(job.getDept_id());
		// detail = "提交" + waste_event.getJobType().getJobTypeName() + "申请";
		// ProcessRecord records = new ProcessRecord(job.getJob_code(),
		// job.getJobType(), job.getUser_iidd(), user_name,
		// dept_name, detail, "请审批", new Date());
		// activitiService.addProcessRecord(records);
		// // 如果有审批流程，在消息表中添加审批消息
		// if (process.getTotal_steps() != 0) {
		// String message = dept_name + user_name + "有" +
		// waste_event.getJobType().getJobTypeName() + "作业需要您审批";
		// // String oper_type = "USERSECLV_CHANGE";
		// for (String item : next_approver.split(",")) {
		// String nextApproverName = basicPrcManage.getApproverName(item);
		// ClientMsg clientMsg = new ClientMsg(item, nextApproverName,
		// waste_event.getJobType().toString(), 1,
		// job.getJob_code(), message, new Date(), 0);
		// basicMapper.addClientMsg(clientMsg);
		// }
		// }

	}

	@Override
	public List<WasteEvent> getWasteEventListByJobCode(String job_code) {
		logger.debug("getWasteEventListByJobCode");
		return assetMapper.getWasteEventListByJobCode(job_code);
	}

	@Override
	public String getWasteJobCodeByEventCode(String event_code) {
		logger.debug("getWasteJobCodeByEventCode" + event_code);
		return assetMapper.getWasteJobCodeByEventCode(event_code);
	}

	@Override
	public WasteEvent getWasteEventByCode(String event_code) {
		logger.debug("getWasteEventByCode" + event_code);
		return assetMapper.getWasteEventByCode(event_code);
	}

	@Override
	public List<WasteEvent> getWasteChangeEventList(Map<String, Object> map) {
		logger.debug("getWasteChangeEventList");
		return assetMapper.getWasteChangeEventList(map);
	}

	@Override
	public void delWasteChangeEventByEnterCode(String event_code) {
		logger.debug("delWasteChangeEventByEnterCode" + event_code);
		WasteEvent event = assetMapper.getWasteEventByCode(event_code);
		if (!event.getJob_code().isEmpty()) {
			assetMapper.delJob(event.getJob_code());
		}
		if (!event.getProperty_barcode().isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("property_barcode", event.getProperty_barcode());
			map.put("property_status", 0);
			map.put("job_code", null);
			assetMapper.updateEntityPropertyStatus(map);
		}
		assetMapper.delWasteChangeEventByEnterCode(event_code);
	}

	@Override
	public void updatePropertyStatusByID(Map<String, Object> map) {
		logger.debug("updatePropertyStatusByID");
		assetMapper.updatePropertyStatusByID(map);
	}

	@Override
	public void updateWasteStatus(String event_code, Integer waste_status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("event_code", event_code);
		map.put("waste_status", waste_status);
		map.put("waste_time", new Date());
		assetMapper.updateWasteStatus(map);
	}

	@Override
	public PropertyKind getSerialNoByKindId(int kind_id) {
		logger.debug("getSerialNoByKindId");
		return assetMapper.getSerialNoByKindId(kind_id);
	}

	@Override
	public void addUrgentPropertyEvent(PurchaseEvent event, String next_approver)
			throws Exception {
		logger.debug("addUrgentPropertyEvent" + event.getEvent_code());
		assetMapper.addPurchaseEvent(event);
		ApproveProcess process = basicPrcManage.getApproveProcessByKey(
				event.getDept_id(), String.valueOf(event.getSeclv_code()),
				event.getJobType().getJobTypeCode(), event.getUsage_code(),
				true);
		String job_status = null;
		if (process.getTotal_steps() == 0) {
			job_status = ActivitiCons.APPLY_APPROVED_PASS;
		} else {
			job_status = ActivitiCons.APPLY_APPROVED_DEFAULT;
		}
		String next_approver_name = basicPrcManage
				.getApproverName(next_approver);
		String job_code = event.getUser_iidd() + "-" + event.getJobType() + "-"
				+ System.currentTimeMillis();
		ProcessJob job = new ProcessJob(job_code, event.getUser_iidd(),
				event.getDept_id(), event.getSeclv_code(), event.getJobType(),
				new Date(), job_status, next_approver, next_approver_name,
				process.getProcess_id());
		job.setComment(event.getSumm());
		// 开启流程
		basicPrcManage.addActivitiApply(job, process);
		// 把任务信息插入数据库
		basicMapper.addProcessJob(job);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("event_code", event.getEvent_code());
		map.put("job_code", job_code);
		assetMapper.addPurchaseEventJobRel(map);
		// 更新作业信息
		String user_name = userService.getUserNameByUserId(job.getUser_iidd());
		String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
		String detail = "提交" + event.getJobType().getJobTypeName() + "申请";
		ProcessRecord record = new ProcessRecord(job.getJob_code(),
				job.getJobType(), job.getUser_iidd(), user_name, dept_name,
				detail, "请审批", new Date());
		activitiService.addProcessRecord(record);
		// 如果有审批流程，在消息表中添加审批消息
		if (process.getTotal_steps() != 0) {
			String message = dept_name + user_name + "有"
					+ event.getJobType().getJobTypeName() + "作业需要您审批";
			// String oper_type = "USERSECLV_CHANGE";
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName,
						event.getJobType().toString(), 1, job.getJob_code(),
						message, new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}
	}
}
