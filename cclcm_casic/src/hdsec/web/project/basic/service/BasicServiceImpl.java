package hdsec.web.project.basic.service;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.activiti.service.ActivitiService;
import hdsec.web.project.activiti.util.ActivitiCons;
import hdsec.web.project.basic.mapper.BasicMapper;
import hdsec.web.project.basic.model.CDStatic;
import hdsec.web.project.basic.model.ClientMsg;
import hdsec.web.project.basic.model.ConfirmRecord;
import hdsec.web.project.basic.model.DeptAdminConfig;
import hdsec.web.project.basic.model.FileInfo;
import hdsec.web.project.basic.model.NewSecRole;
import hdsec.web.project.basic.model.PaperStatic;
import hdsec.web.project.basic.model.SysBarcode;
import hdsec.web.project.basic.model.SysBurner;
import hdsec.web.project.basic.model.SysConfigItem;
import hdsec.web.project.basic.model.SysConsole;
import hdsec.web.project.basic.model.SysExchangeBox;
import hdsec.web.project.basic.model.SysKeyword;
import hdsec.web.project.basic.model.SysMfp;
import hdsec.web.project.basic.model.SysPlace;
import hdsec.web.project.basic.model.SysPrinter;
import hdsec.web.project.basic.model.SysPrinterGroup;
import hdsec.web.project.basic.model.SysPrinterUser;
import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysProxy;
import hdsec.web.project.basic.model.SysRecycleBox;
import hdsec.web.project.basic.model.SysSeclevel;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.borrow.model.EventBorrow;
import hdsec.web.project.borrow.service.BorrowService;
import hdsec.web.project.change.model.EventChange;
import hdsec.web.project.change.service.ChangeService;
import hdsec.web.project.client.service.ClientService;
import hdsec.web.project.common.BaseEvent;
import hdsec.web.project.common.PropertyUtil;
import hdsec.web.project.common.model.CopyrightLicense;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.TimeUtil;
import hdsec.web.project.device.mapper.DeviceMapper;
import hdsec.web.project.device.model.EntityDevice;
import hdsec.web.project.enter.model.SubSecDeptAdmin;
import hdsec.web.project.ledger.mapper.LedgerMapper;
import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.ledger.model.EntityStateEnum;
import hdsec.web.project.ledger.model.EventCarryOut;
import hdsec.web.project.ledger.model.EventModify;
import hdsec.web.project.ledger.model.EventTemp;
import hdsec.web.project.ledger.model.SendDestroyEvent;
import hdsec.web.project.ledger.service.LedgerService;
import hdsec.web.project.transfer.model.EventTransfer;
import hdsec.web.project.transfer.service.TransferService;
import hdsec.web.project.user.mapper.UserMapper;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecDept;
import hdsec.web.project.user.model.SecLevel;
import hdsec.web.project.user.model.SecRoleUser;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.service.UserService;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.history.HistoricTaskInstance;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

public class BasicServiceImpl implements BasicService {
	private final Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private BasicMapper basicMapper;
	@Resource
	private UserService userService;
	@Resource
	private ActivitiService activitiService;
	@Resource
	private BasicPrcManage basicPrcManage;
	@Resource
	private LedgerService ledgerService;
	@Resource
	private LedgerMapper ledgerMapper;
	@Resource
	protected BorrowService borrowService;
	@Resource
	protected ClientService clientService;
	@Resource
	protected BarcodeGeneratorImpl barcodeGeneratorImpl;
	@Resource
	private DeviceMapper deviceMapper;
	@Resource
	protected TransferService transferService;
	@Resource
	protected ChangeService changeService;
	@Resource
	private UserMapper userMapper;

	@Override
	public List<SysUsage> getAllSysUsageList() {
		logger.debug("getAllSysUsageList");
		Map<String, Object> map = new HashMap<String, Object>();
		return basicMapper.getSysUsageList(map);
	}

	@Override
	public List<SysUsage> getSysUsageList() {
		logger.debug("getSysUsageList");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("is_sealed", "N");
		return basicMapper.getSysUsageList(map);
	}

	@Override
	public void addSysUsage(SysUsage usage) {
		logger.debug("addSysUsage:" + usage.getUsage_code());
		basicMapper.addSysUsage(usage);
	}

	@Override
	public void delUsageByCode(String usage_code) {
		logger.debug("delUsageByCode:" + usage_code);
		basicMapper.setUsageSealedByCode(usage_code);
	}

	@Override
	public SysUsage getUsageByCode(String usage_code) {
		logger.debug("getUsageByCode:" + usage_code);
		return basicMapper.getUsageByCode(usage_code);
	}

	@Override
	public void updateUsage(SysUsage usage) {
		logger.debug("updateUsage:" + usage.getUsage_code());
		basicMapper.updateUsage(usage);
	}

	public List<SysProject> getAllSysProjectList() {
		logger.debug("getAllSysProjectList");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("is_all", true);
		return basicMapper.getSysProjectList(map);
	}

	@Override
	public List<SysProject> getSysProjectList() {
		logger.debug("getSysProjectList");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("is_all", false);
		return basicMapper.getSysProjectList(map);
	}

	@Override
	public void addSysProject(SysProject project) {
		logger.debug("addSysProject:" + project.getProject_code());
		basicMapper.addSysProject(project);
	}

	@Override
	public void delProjectByCode(String project_code) {
		logger.debug("delProjectByCode:" + project_code);
		basicMapper.setProjectSealedByCode(project_code);
	}

	@Override
	public SysProject getProjectByCode(String project_code) {
		logger.debug("getProjectByCode:" + project_code);
		return basicMapper.getProjectByCode(project_code);
	}

	@Override
	public void updateProject(SysProject project) {
		logger.debug("updateProject:" + project.getProject_code());
		basicMapper.updateProject(project);
	}

	public List<SysConsole> getAllSysConsoleList() {
		logger.debug("getAllSysConsoleList");
		Map<String, Object> map = new HashMap<String, Object>();
		return basicMapper.getAllSysConsoleList(map);
	}

	@Override
	public SysConsole getConsoleByCode(String console_code) {
		logger.debug("getConsoleByCode:" + console_code);
		return basicMapper.getConsoleByCode(console_code);
	}

	@Override
	public void updateConsole(SysConsole console) {
		logger.debug("updateConsole:" + console.getConsole_code());
		basicMapper.updateConsole(console);

	}

	@Override
	public void delConsoleByCode(String console_code) {
		logger.debug("delConsoleByCode:" + console_code);
		basicMapper.setConsoleSealedByCode(console_code);
	}

	@Override
	public void addConsole(SysConsole console) throws Exception {
		if (getLastHSNumCount() == null) {
			logger.debug("控制台个数不受限制");
		} else if (getLastHSNumCount() <= 0) {
			throw new Exception("控制台个数添加已达到上限，如需添加控制台，请继续购买控制台数！");
		}
		logger.debug("addConsole:" + console.getConsole_code());
		basicMapper.addConsole(console);
	}

	@Override
	public List<SysConsole> getSysConsoleList() {
		logger.debug("getSysConsoleList");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("is_sealed", "N");
		return basicMapper.getAllSysConsoleList(map);
	}

	public List<SysMfp> getAllSysMfpList() {
		logger.debug("getAllSysMfpList");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("is_all", true);
		return basicMapper.getSysMfpList(map);
	}

	@Override
	public List<SysMfp> getSysMfpList() {
		logger.debug("getSysMfpList");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("is_all", false);
		return basicMapper.getSysMfpList(map);
	}

	@Override
	public void addSysMfp(SysMfp mfp) {
		logger.debug("addSysMfp:" + mfp.getMfp_code());
		basicMapper.addSysMfp(mfp);
	}

	@Override
	public void delMfpByCode(String mfp_code) {
		logger.debug("delProjectByCode:" + mfp_code);
		basicMapper.setMfpSealedByCode(mfp_code);
	}

	@Override
	public SysMfp getMfpByCode(String mfp_code) {
		logger.debug("getMfpByCode:" + mfp_code);
		return basicMapper.getMfpByCode(mfp_code);
	}

	@Override
	public void updateMfp(SysMfp mfp) {
		logger.debug("updateMfp:" + mfp.getMfp_code());
		basicMapper.updateMfp(mfp);
	}

	public List<SysBurner> getAllSysBurnerList() {
		logger.debug("getAllSysBurnerList");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("is_all", true);
		return basicMapper.getSysBurnerList(map);
	}

	@Override
	public List<SysBurner> getSysBurnerList() {
		logger.debug("getSysBurnerList");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("is_all", false);
		return basicMapper.getSysBurnerList(map);
	}

	@Override
	public void addSysBurner(SysBurner burner) {
		logger.debug("addSysBurner:" + burner.getBurner_code());
		basicMapper.addSysBurner(burner);
	}

	@Override
	public void delBurnerByCode(String burner_code) {
		logger.debug("delProjectByCode:" + burner_code);
		basicMapper.setBurnerSealedByCode(burner_code);
	}

	@Override
	public SysBurner getBurnerByCode(String burner_code) {
		logger.debug("getBurnerByCode:" + burner_code);
		return basicMapper.getBurnerByCode(burner_code);
	}

	@Override
	public void updateBurner(SysBurner burner) {
		logger.debug("updateBurner:" + burner.getBurner_code());
		basicMapper.updateBurner(burner);
	}

	public List<SysPrinter> getAllSysPrinterList() {
		logger.debug("getAllSysPrinterList");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("is_all", true);
		return basicMapper.getSysPrinterList(map);
	}

	@Override
	public List<SysPrinter> getSysPrinterList() {
		logger.debug("getSysPrinterList");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("is_all", false);
		return basicMapper.getSysPrinterList(map);
	}

	@Override
	public void addSysPrinter(SysPrinter printer) {
		logger.debug("addSysPrinter:" + printer.getPrinter_code());
		basicMapper.addSysPrinter(printer);
	}

	@Override
	public void delPrinterByCode(String printer_code) {
		logger.debug("delPrinterByCode:" + printer_code);
		basicMapper.setPrinterSealedByCode(printer_code);
	}

	@Override
	public SysPrinter getPrinterByCode(String printer_code) {
		logger.debug("getPrinterByCode:" + printer_code);
		return basicMapper.getPrinterByCode(printer_code);
	}

	@Override
	public void updatePrinter(SysPrinter printer) {
		logger.debug("updatePrinter:" + printer.getPrinter_code());
		basicMapper.updatePrinter(printer);
	}

	@Override
	public List<SysPrinterGroup> getPrinterGroupList(String printer_code, String dept_id) {
		logger.debug("getPrinterGroupList");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("printer_code", printer_code);
		map.put("dept_id", dept_id);
		return basicMapper.getPrinterGroupList(map);
	}

	@Override
	public void updatePrinterGroup(String printer_code, String dept_ids) {
		logger.debug("updatePrinterGroup:printer_code[" + printer_code + "],dept_ids[" + dept_ids + "]");
		basicMapper.delPrinterGroupByPrinterCode(printer_code);
		for (String dept_id : dept_ids.split(",")) {
			if (StringUtils.hasLength(dept_id.trim())) {
				basicMapper.addPrinterGroup(new SysPrinterGroup(dept_id, printer_code));
			}
		}
	}

	@Override
	public void addPrinterGroup(SysPrinterGroup printerGroup) {
		logger.debug("addPrinterGroup:" + printerGroup.getPrinter_code());
		basicMapper.addPrinterGroup(printerGroup);
	}

	@Override
	public List<BaseEvent> getEventListByEventCodes(JobTypeEnum jobType, String event_codes) throws Exception {
		logger.debug("getEventListByEventCodes:" + event_codes);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("event_codes", event_codes.split(":"));

		if (jobType.getEventTableName() == null) {
			throw new Exception("该类型作业无法查询到对应的数据库表:" + jobType.getJobTypeCode());
		}
		map.put("table_name", jobType.getEventTableName());
		map.put("jobType_code", jobType.getJobTypeCode());
		return basicMapper.getEventListByEventCodes(map);
	}

	@Override
	public SecLevel getHighestSeclvByCodeList(List<Integer> seclvCodeList) {
		logger.debug("getHighestSeclvByCodeList");
		Integer seclv_rank = null;
		SecLevel seclv = null;
		Integer rank_tmp = null;
		SecLevel seclv_tmp = null;
		for (Integer item : seclvCodeList) {
			seclv_tmp = userService.getSecLevelByCode(item);
			rank_tmp = seclv_tmp.getSeclv_rank();
			if (seclv_rank == null || rank_tmp < seclv_rank) {
				seclv_rank = rank_tmp;
				seclv = seclv_tmp;
			}
		}
		return seclv;
	}

	@Override
	public ApproveProcess getApproveProcessByKey(String dept_id, Integer seclv_code, String jobType_code,
			String usage_code) throws Exception {
		logger.debug("getApproveProcessByKey:dept_id[" + dept_id + "],seclv_code[" + seclv_code + "],jobType_code["
				+ jobType_code + "],usage_code[" + usage_code + "]");
		return basicPrcManage.getApproveProcessByKey(dept_id, String.valueOf(seclv_code), jobType_code, usage_code,
				true);
	}

	@Override
	public List<ApproverUser> getNextApprover(String job_code, String dept_id, Integer seclv_code, String jobType_code,
			String usage_code) throws Exception {
		logger.debug("getNextApprover:job_code[" + job_code + "],dept_id[" + dept_id + "],seclv_code[" + seclv_code
				+ "],jobType_code[" + jobType_code + "],usage_code[" + usage_code + "]");
		ApproveProcess process = null;
		ProcessJob job = null;
		List<ApproverUser> userList = null;
		job = getProcessJobByCode(job_code);
		// 申请信息为null,说明是处于申请阶段
		if (job == null) {
			logger.info("getNextApprover()-->job[null]-->getApproveProcessByKey()");
			process = getApproveProcessByKey(dept_id, seclv_code, jobType_code, usage_code);
			userList = basicPrcManage.getNextApprover(process, 1);
		} else {
			logger.info("getNextApprover()-->job[not null]-->getApproveProcessByInstanceId()");
			process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
			Map<String, Object> variables = basicPrcManage.getProcessVariables(job.getInstance_id());
			Integer cur_approval = (Integer) variables.get("cur_approval") + 1;
			userList = basicPrcManage.getNextApprover(process, cur_approval);
		}
		return userList;
	}

	@Override
	public List<ProcessJob> getJobList(Map<String, Object> map, RowBounds rbs) {
		logger.debug("getJobList");
		return basicMapper.getJobList(map, rbs);
	}

	@Override
	public ProcessJob getProcessJobByCode(String job_code) {
		logger.debug("getProcessJobByCode:job_code[" + job_code + "]");
		return basicMapper.getProcessJobByCode(job_code);
	}

	@Override
	public String getApproverName(String next_approver) {
		logger.debug("getApproverName[" + next_approver + "]");
		return basicPrcManage.getApproverName(next_approver);
	}

	@Override
	public void addMultiProcessJob(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType,
			String next_approver, String output_dept_name, String output_user_name, String comment, String event_codes,
			String usage_code, String project_code) throws Exception {
		logger.debug("addMultiProcessJob");
		ApproveProcess process = getApproveProcessByKey(dept_id, seclv_code, jobType.getJobTypeCode(), usage_code);
		String job_status = null;
		if (process.getTotal_steps() == 0) {
			job_status = ActivitiCons.APPLY_APPROVED_PASS;
		} else {
			job_status = ActivitiCons.APPLY_APPROVED_DEFAULT;
		}
		String next_approver_name = getApproverName(next_approver);
		for (String event_code : event_codes.split(":")) {
			if (StringUtils.hasLength(event_code.trim())) {
				// String job_code = event_code.trim() + "-" +
				// jobType.getJobTypeCode() + "-" +
				// System.currentTimeMillis();
				String job_code = user_iidd + "-" + jobType.getJobTypeCode() + "-" + System.currentTimeMillis();
				ProcessJob job = new ProcessJob(job_code, user_iidd, dept_id, seclv_code, jobType, new Date(),
						job_status, next_approver, next_approver_name, process.getProcess_id());
				job.setOutput_dept_name(output_dept_name);
				job.setOutput_user_name(output_user_name);
				job.setComment(comment);
				// 开启流程
				basicPrcManage.addActivitiApply(job, process);
				// 把任务信息插入数据库
				basicMapper.addProcessJob(job);
				// 更新作业信息
				addEventJobRel(event_code, job, usage_code, project_code);
				String user_name = userService.getUserNameByUserId(job.getUser_iidd());
				String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
				String detail = "提交" + jobType.getJobTypeName() + "申请";
				ProcessRecord record = new ProcessRecord(job.getJob_code(), job.getJobType(), job.getUser_iidd(),
						user_name, dept_name, detail, "请审批", new Date());
				activitiService.addProcessRecord(record);
				// 如果有审批流程，在消息表中添加审批消息
				if (process.getTotal_steps() != 0) {
					String message = dept_name + user_name + "有" + jobType.getJobTypeName() + "作业需要您审批";
					String oper_type = "";
					if (jobType.getJobTypeCode().startsWith("PRINT_")) {
						oper_type = "PRINT";
					} else if (jobType.getJobTypeCode().startsWith("BURN_")) {
						oper_type = "BURN";
					} else {
						oper_type = jobType.getJobTypeCode();
					}
					for (String item : next_approver.split(",")) {
						String nextApproverName = basicPrcManage.getApproverName(item);
						ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(),
								message, new Date(), 0);
						basicMapper.addClientMsg(clientMsg);
					}
				}
			}
		}

	}

	private void addEventJobRel(String event_codes, ProcessJob job, String usage_code, String project_code) {
		logger.debug("addEventJobRel");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("event_codes", event_codes.split(":"));
		map.put("table_name", job.getJobType().getEventTableName());
		map.put("job_code", job.getJob_code());
		map.put("usage_code", usage_code);
		map.put("project_code", project_code);
		basicMapper.updateEventStatus(map);
	}

	@Override
	public void cancelJob(String job_code, String jobType_code) {
		logger.debug("cancelJob");
		String instance_id = getProcessJobByCode(job_code).getInstance_id();
		JobTypeEnum jobType = JobTypeEnum.valueOf(jobType_code);
		if (jobType == JobTypeEnum.BORROW) {
			EventBorrow event = borrowService.getBorrowEventByJobCode(job_code);
			borrowService.resetEntityStatus(event.getEntity_type(), event.getBarcode());
			basicPrcManage.suspendProcessInstanceById(instance_id);
			borrowService.delBorrowEventByJobCode(job_code);
			basicMapper.delJob(job_code);
			clientService.delClientMsgByJobCode(job_code);
		} else if (jobType == JobTypeEnum.SPACECD_BORROW) {
			basicPrcManage.suspendProcessInstanceById(instance_id);
			basicMapper.delJob(job_code);
			clientService.delClientMsgByJobCode(job_code);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("table_name", jobType.getEventTableName());
			map.put("job_code", job_code);
			basicPrcManage.suspendProcessInstanceById(instance_id);
			basicMapper.delEventJobRelByJobCode(map);
			basicMapper.delJob(job_code);
			clientService.delClientMsgByJobCode(job_code);
		}
	}

	@Override
	public void closeJob(String job_code, String jobType_code) throws Exception {
		logger.debug("closeJob");
		ProcessJob job = getProcessJobByCode(job_code);
		// 如果申请处于被驳回状态，则在流程引擎中关闭它，使流程结束
		if (job.getJob_status().equalsIgnoreCase(ActivitiCons.APPLY_APPROVED_REJECT)) {
			basicPrcManage.confirmReject(job);
		} else {
			job.setJob_status(ActivitiCons.APPLY_APPROVED_END);
		}
		basicMapper.closeJobTask(job);
	}

	@Override
	public String getJobTypeCodeByJobCode(String job_code) {
		logger.debug("getJobTypeCodeByJobCode");
		return basicMapper.getJobTypeCodeByJobCode(job_code);
	}

	@Override
	public JobTypeEnum getJobTypeEnumByJobCode(String job_code) {
		logger.debug("getJobTypeEnumByJobCode");
		String type = basicMapper.getJobTypeCodeByJobCode(job_code);
		return type == null ? null : JobTypeEnum.valueOf(type);
	}

	@Override
	public List<BaseEvent> getEventListByJobCode(String job_code) throws Exception {
		JobTypeEnum jobType = getJobTypeEnumByJobCode(job_code);
		Map<String, Object> map = new HashMap<String, Object>();
		if (jobType.getEventTableName() == null) {
			throw new Exception("该类型作业无法查询到对应的数据库表:" + jobType.getJobTypeCode());
		}
		map.put("table_name", jobType.getEventTableName());
		map.put("jobType_code", jobType.getJobTypeCode());
		map.put("job_code", job_code);
		return basicMapper.getEventListByJobCode(map);
	}

	@Override
	public List<ProcessJob> getCandidateListByUserId(String user_iidd, JobTypeEnum jobType) {
		List<String> instanceIdList = basicPrcManage.getCandidateInstanceIdList(user_iidd, jobType.getJobTypeCode());
		if (instanceIdList != null && instanceIdList.size() > 0) {
			return basicMapper.getProcessJobListByInstanceId(instanceIdList);
		}
		return Collections.emptyList();
	}

	@Override
	public List<ProcessJob> getAssignedListByUserId(String user_iidd, JobTypeEnum jobType) {
		List<String> instanceIdList = basicPrcManage.getAssignedInstanceIdList(user_iidd, jobType.getJobTypeCode());
		if (instanceIdList != null && instanceIdList.size() > 0) {
			return basicMapper.getProcessJobListByInstanceId(instanceIdList);
		}
		return Collections.emptyList();
	}

	private void claimExTask(ProcessJob job, String user_iidd) throws Exception {
		basicPrcManage.claimtask(job, user_iidd);
		basicMapper.claimJobTask(job);
	}

	private void addConfirmRecord(ProcessJob job) { // 如果任务类型为流转，且审批完成通过了，则需要在交接确认表中增加此次流转的待确认信息
		if (job.getJobType() == JobTypeEnum.TRANSFER) {
			List<EventTransfer> eventList = transferService.getTransferEventByJobCode(job.getJob_code());
			for (EventTransfer item : eventList) {
				String entity_name = "";
				if (LedgerService.DISK.equals(item.getEntity_type())) {
					EntityCD cd = ledgerService.getCDByBarcode(item.getBarcode());
					entity_name = cd.getFile_list();
				} else if (LedgerService.PAPER.equals(item.getEntity_type())) {
					EntityPaper paper = ledgerService.getPaperByBarcode(item.getBarcode());
					entity_name = paper.getFile_title();
				}
				ConfirmRecord record = new ConfirmRecord(item.getUser_iidd(), item.getUser_name(), item.getDept_id(),
						item.getDept_name(), item.getAccept_user_iidd(), item.getAccept_user_name(),
						item.getAccept_dept_id(), item.getAccept_dept_name(), item.getEntity_type().toUpperCase(),
						item.getBarcode(), entity_name, item.getSeclv_name(), "TRANSFER", item.getEvent_code(),
						new Date());
				basicMapper.saveConfirmRecord(record);
			}
		}
	}

	@Override
	public void approveJob(String job_code, ApproverUser user, ApproverUser approver, String approved, String opinion,
			String entitytype) throws Exception {
		if (getProcessJobByCode(job_code) != null) {
			ProcessJob job = getProcessJobByCode(job_code);// 查询任务信息
			claimExTask(job, user.getUser_iidd());// 领取任务（activiti要求的操作）
			job.setNext_approver(approver.getUser_iidd());// 设置下级审批人
			job.setNext_approver_name(approver.getUser_name());
			// 根据流程变量中保存的审批级数的状态，判断审批是否已经结束
			Map<String, Object> variables = basicPrcManage.getProcessVariables(job.getInstance_id());
			Integer total_approval = (Integer) variables.get("total_approval");
			Integer cur_approval = (Integer) variables.get("cur_approval") + 1;
			// 在插入下级审批消息或审批完成消息之前，置对应job_code的消息为已读
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("job_code", job_code);
			map.put("is_read", 1);
			map.put("read_time", new Date());
			// basicMapper.updateClientMsg(map);
			clientService.delClientMsgByJobCode(job_code);
			if (approved.equals(ActivitiCons.APPLY_APPROVED_PASS) && (cur_approval <= total_approval)) {
				job.setJob_status(ActivitiCons.APPLY_APPROVED_UNDER);

				// 如果审批流程未结束，在消息表中添加审批消息
				String message = job.getDept_name() + job.getUser_name() + "有" + job.getJobType().getJobTypeName()
						+ "作业需要您审批";
				String oper_type = "";
				if (String.valueOf(job.getJobType()).startsWith("PRINT_")) {
					oper_type = "PRINT";
				} else if (String.valueOf(job.getJobType()).startsWith("SPECIAL_PRINT_")) {
					oper_type = "PRINT";
				} else if (String.valueOf(job.getJobType()).startsWith("BURN_")) {
					oper_type = "BURN";
				} else if (String.valueOf(job.getJobType()).startsWith("SPECIAL_BURN_")) {
					oper_type = "BURN";
				} else if (String.valueOf(job.getJobType()).startsWith("CARRYOUT_PAPER")) {
					oper_type = "CARRYOUT_PAPER";
				} else if (String.valueOf(job.getJobType()).startsWith("CARRYOUT_CD")) {
					oper_type = "CARRYOUT_CD";

				} else if (String.valueOf(job.getJobType()).startsWith("COPY")) {
					oper_type = "COPY";
				} else if (String.valueOf(job.getJobType()).startsWith("OUTCOPY_")) {
					oper_type = "OUTCOPY";
				} else if (String.valueOf(job.getJobType()).equals("SCAN_LEADIN")) {
					oper_type = "LEADIN";
				} else if (String.valueOf(job.getJobType()).startsWith("SENDES_PAPER")) {
					oper_type = "SENDES_PAPER";
				} else if (String.valueOf(job.getJobType()).startsWith("SENDES_CD")) {
					oper_type = "SENDES_CD";
				} else if (String.valueOf(job.getJobType()).equals("SEND")
						|| String.valueOf(job.getJobType()).equals("DESTROY")
						|| String.valueOf(job.getJobType()).equals("FILE")
						|| String.valueOf(job.getJobType()).equals("DELAY")) {
					oper_type = job.getJobType() + entitytype;
				} else {
					oper_type = String.valueOf(job.getJobType());
				}
				for (String item : approver.getUser_iidd().split(",")) {
					String nextApproverName = basicPrcManage.getApproverName(item);
					ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job_code, message,
							new Date(), 0);
					basicMapper.addClientMsg(clientMsg);
				}

			} else {
				job.setJob_status(approved);
				// 审批流程结束，在消息表中添加审批完成消息
				Integer approveResultValue = 0;
				String approveResult = "";
				if (approved.equals(ActivitiCons.APPLY_APPROVED_PASS)) {
					approveResultValue = 2;
					approveResult = "通过";
					if (isConfirmOpen("TRANSFER_CONFIRM")) { // 流转交接确认开启，则在交接确认表中增加此次流转的待确认信息
						addConfirmRecord(job);
					}

					// 密级变更流程通过，直接将文件密级修改为申请中的目标密级
					if (String.valueOf(job.getJobType()).contains("MODIFY_SECLV")) {
						ledgerService.confimModify(job_code);
					}

					// 延期回收流程审批结束且通过后， 根据密级查询回收期限，以当前时间加回收期限得出回收提醒时间。
					// 0说明不需要回收，回收提醒时间为默认空值
					if (String.valueOf(job.getJobType()).contains("DELAY")) {
						// SysSeclevel sysSeclevel = basicMapper.getSysSecLevelByCode(job.getSeclv_code());
						// 更新文件、光盘载体提醒回收时间
						if (entitytype.contains("PAPER")) {
							List<EntityPaper> paperList = ledgerMapper.getPaperListByJobCode(job_code);
							Map<String, Object> map_update = new HashMap<String, Object>();
							for (EntityPaper paper : paperList) {
								SysSeclevel sysSeclevel = basicMapper.getSysSecLevelByCode(paper.getSeclv_code());
								if (sysSeclevel.getArchive_time() > 0) {
									map_update.put("paper_barcode", paper.getPaper_barcode());
									// 如果存在回收提醒时间，则按此延期；否则按照当前时间延期
									// if (paper.getExpire_time_original() != null) {
									// map_update.put("expire_time", TimeUtil.getAfterXDayByTransferDay(
									// paper.getExpire_time_original(), sysSeclevel.getArchive_time()));
									// } else {
									// map_update.put("expire_time",
									// TimeUtil.getAfterXDay(sysSeclevel.getArchive_time()));
									// }
									if (paper.getDelay_days() <= sysSeclevel.getArchive_time()) {
										// 如果延期留用时间小于或等于回收提醒时间，并且存在回收提醒时间，则按延期留用时间；否则按照当前时间延期
										if (paper.getExpire_time_original() != null) {
											map_update.put("expire_time", TimeUtil.getAfterXDayByTransferDay(
													paper.getExpire_time_original(), paper.getDelay_days()));
										} else {
											map_update.put("expire_time", TimeUtil.getAfterXDay(paper.getDelay_days()));
										}
									}
									basicMapper.updatePaperExpireTime(map_update);
								}
							}
						} else if (entitytype.contains("CD")) {
							List<EntityCD> cdList = ledgerMapper.getCDListByJobCode(job_code);
							Map<String, Object> map_update = new HashMap<String, Object>();
							for (EntityCD cd : cdList) {
								SysSeclevel sysSeclevel = basicMapper.getSysSecLevelByCode(cd.getSeclv_code());
								if (sysSeclevel.getArchive_time() > 0) {
									map_update.put("cd_barcode", cd.getCd_barcode());
									// 如果存在回收提醒时间，则按此延期；否则按照当前时间延期
									// if (cd.getExpire_time_original() != null) {
									// map_update.put("expire_time", TimeUtil.getAfterXDayByTransferDay(
									// cd.getExpire_time_original(), sysSeclevel.getArchive_time()));
									// } else {
									// map_update.put("expire_time",
									// TimeUtil.getAfterXDay(sysSeclevel.getArchive_time()));
									// }
									if (cd.getDelay_days() <= sysSeclevel.getArchive_time()) {
										// 如果延期留用时间小于或等于回收提醒时间，并且存在回收提醒时间，则按延期留用时间；否则按照当前时间延期
										if (cd.getExpire_time_original() != null) {
											map_update.put(
													"expire_time",
													TimeUtil.getAfterXDayByTransferDay(cd.getExpire_time_original(),
															cd.getDelay_days()));
										} else {
											map_update.put("expire_time", TimeUtil.getAfterXDay(cd.getDelay_days()));
										}
									}
									basicMapper.updateCDExpireTime(map_update);
								}
							}
						}
					} else if (String.valueOf(job.getJobType()).contains("PAPER_DEL")) {
						// 文件错误台账进行删除-结果置为“失败”状态为“已销毁”--九部需求20160319
						List<EntityPaper> paperlist = ledgerMapper.getPaperListByJobCode(job_code);
						if (paperlist != null) {
							for (EntityPaper paper : paperlist) {
								ledgerService.updatePaperDelInfo(paper, user, job_code);
							}
						}
					} else if (String.valueOf(job.getJobType()).contains("PAPER_MODIFY")) {
						// 文件错误台账进行修改页数，至更改页数字段--九部需求20160319
						List<EntityPaper> paperlist = ledgerMapper.getPaperListByJobCode(job_code);
						if (paperlist != null) {
							for (EntityPaper paper : paperlist) {
								ledgerService.updatePaperModifyInfo(paper, user, job_code);
							}

							// 将流程信息中暂时占用外发单位字段更新为空
							map.put("output_dept_name", "");
							ledgerMapper.updateJobProcessInfo(map);
						}
					} else if (String.valueOf(job.getJobType()).equals("FILE_DESTROY")
							|| String.valueOf(job.getJobType()).equals("FILECD_DESTROY")) {
						// 已归档载体的归档、销毁处理--20160331-guojiao
						List<EventTemp> eventlist = ledgerMapper.getTempEventListByJobCode(job_code);
						if (eventlist != null) {
							Map<String, Object> mapevent = new HashMap<String, Object>();
							for (EventTemp event : eventlist) {
								mapevent.put("scope_dept_id", event.getScope_dept_id());
								mapevent.put("scope_dept_name", event.getScope_dept_name());
								mapevent.put("scope", "DEPT");
								// 更新台账中该载体类型为部门
								if (event.getEntity_type().equals("paper")) {
									mapevent.put("paper_barcode", event.getBarcode());
									ledgerMapper.updatePaperState(mapevent);
								} else {
									mapevent.put("cd_barcode", event.getBarcode());
									ledgerMapper.updateCDState(mapevent);
								}
							}
						}
					} else if (String.valueOf(job.getJobType()).startsWith("CARRYOUT_PAPER")) {// 审批结束后，载体变为外带中
						List<EventCarryOut> events = ledgerService.getEventCarryOutListByJobCode(job.getJob_code());
						for (EventCarryOut event : events) {
							Map<String, Object> maps = new HashMap<String, Object>();
							maps.put("paper_barcode", event.getBarcode());
							maps.put("paper_state", 18);// 外带中
							ledgerMapper.updatePaperStateByBarcode(maps);
						}
					} else if (String.valueOf(job.getJobType()).startsWith("CARRYOUT_CD")) {// 审批结束后，载体变为外带中
						List<EventCarryOut> events = ledgerService.getEventCarryOutListByJobCode(job.getJob_code());
						for (EventCarryOut event : events) {
							Map<String, Object> maps = new HashMap<String, Object>();
							maps.put("cd_barcode", event.getBarcode());
							maps.put("cd_state", 18);// 外带中
							ledgerMapper.updateCDStateByBarcode(maps);
						}
					} else if (String.valueOf(job.getJobType()).contains("SEND_PAPER")) {
						List<EntityPaper> paperList = ledgerMapper.getPaperListByJobCode(job_code);
						for (EntityPaper event : paperList) {
							Map<String, Object> maps = new HashMap<String, Object>();
							maps.put("paper_barcode", event.getPaper_barcode());
							maps.put("paper_state", 16);// 外发中
							ledgerMapper.updatePaperStateByBarcode(maps);
						}
					} else if (String.valueOf(job.getJobType()).contains("SEND_CD")) {
						List<EntityCD> cdList = ledgerMapper.getCDListByJobCode(job_code);
						for (EntityCD event : cdList) {
							Map<String, Object> maps = new HashMap<String, Object>();
							maps.put("cd_barcode", event.getCd_barcode());
							maps.put("cd_state", 16);// 外发中
							ledgerMapper.updateCDStateByBarcode(maps);
						}
					} else if (String.valueOf(job.getJobType()).equals("MERGE_ENTITY")) {
						List<EntityPaper> paperList = ledgerMapper.getPaperListByJobCode(job_code);

						String barcodes = "";
						if (paperList != null) {
							for (EntityPaper temp : paperList) {
								barcodes += temp.getPaper_barcode() + ",";
							}
							EntityPaper paper = paperList.get(0);

							ledgerService.addMergeEntityPaper(paper.getUser_iidd(), paper.getDept_id(),
									job.getSeclv_code(), job, barcodes);
						} else {
							throw new Exception("获取该任务的作业列表失败，请重试或联系管理员！");
						}
					}
				} else {
					approveResultValue = 3;
					approveResult = "不通过";
				}
				String message = "您的" + job.getJobType().getJobTypeName() + "作业已审批：" + approveResult;
				String oper_type = "";
				if (String.valueOf(job.getJobType()).startsWith("PRINT_")) {
					oper_type = "PRINT";
				} else if (String.valueOf(job.getJobType()).startsWith("SPECIAL_PRINT_")) {
					oper_type = "PRINT";
				} else if (String.valueOf(job.getJobType()).startsWith("BURN_")) {
					oper_type = "BURN";
				} else if (String.valueOf(job.getJobType()).startsWith("SPECIAL_BURN_")) {
					oper_type = "BURN";
				} else if (String.valueOf(job.getJobType()).startsWith("COPY")) {
					oper_type = "COPY";
				} else if (String.valueOf(job.getJobType()).startsWith("CARRYOUT_PAPER")) {
					oper_type = "CARRYOUT_PAPER";
				} else if (String.valueOf(job.getJobType()).startsWith("CARRYOUT_CD")) {
					oper_type = "CARRYOUT_CD";

				} else if (String.valueOf(job.getJobType()).startsWith("OUTCOPY_")) {
					oper_type = "OUTCOPY";
				} else if (String.valueOf(job.getJobType()).equals("SCAN_LEADIN")) {
					oper_type = "LEADIN";

				} else if (String.valueOf(job.getJobType()).startsWith("CARRYOUT_")) {
					oper_type = "CARRYOUT";

				} else if (String.valueOf(job.getJobType()).startsWith("SENDES_PAPER")) {
					oper_type = "SENDES_PAPER";
				} else if (String.valueOf(job.getJobType()).startsWith("SENDES_CD")) {
					oper_type = "SENDES_CD";
				} else if (String.valueOf(job.getJobType()).equals("SEND")
						|| String.valueOf(job.getJobType()).equals("DESTROY")
						|| String.valueOf(job.getJobType()).equals("FILE")
						|| String.valueOf(job.getJobType()).equals("DELAY")) {
					oper_type = job.getJobType() + entitytype;
				} else {
					oper_type = String.valueOf(job.getJobType());
				}
				ClientMsg clientMsg = new ClientMsg(job.getUser_iidd(), job.getUser_name(), oper_type,
						approveResultValue, job_code, message, new Date(), 0);
				basicMapper.addClientMsg(clientMsg);

				// 审批通过后， 获取打印委托人或刻录委托人,通知委托人，有要打印的作业需要打印。
				if (approved.equals(ActivitiCons.APPLY_APPROVED_PASS)) {
					if (oper_type.contains("PRINT")) {
						String proxyprint_user_iidd = basicMapper.getPrintProxyUserIdByJobcode(job_code);
						String message_proxy = "委托给您的" + job.getJobType().getJobTypeName() + "作业已审批通过，请及时打印";
						String oper_type_proxy = "PROXY_PRINT";
						if (proxyprint_user_iidd != null && !proxyprint_user_iidd.equals("")) {
							String proxyprint_user_name = userService.getUserNameByUserId(proxyprint_user_iidd);
							ClientMsg clientMsg_proxy = new ClientMsg(proxyprint_user_iidd, proxyprint_user_name,
									oper_type_proxy, approveResultValue, job_code, message_proxy, new Date(), 0);
							basicMapper.addClientMsg(clientMsg_proxy);
						}
					} else if (oper_type.contains("BURN")) {
						String proxyburn_user_iidd = basicMapper.getBurnProxyUserIdByJobcode(job_code);
						String message_proxy = "委托给您的" + job.getJobType().getJobTypeName() + "作业已审批通过，请及时刻录";
						String oper_type_proxy = "PROXY_BURN";
						if (proxyburn_user_iidd != null && !proxyburn_user_iidd.equals("")) {
							String proxypburn_user_name = userService.getUserNameByUserId(proxyburn_user_iidd);
							ClientMsg clientMsg_proxy = new ClientMsg(proxyburn_user_iidd, proxypburn_user_name,
									oper_type_proxy, approveResultValue, job_code, message_proxy, new Date(), 0);
							basicMapper.addClientMsg(clientMsg_proxy);
						}
					}
				}

			}
			// 更新流程中的审批状态
			basicPrcManage.approveApply(job.getInstance_id(), user.getUser_iidd(), approver.getUser_iidd(), approved);
			// 更新数据库状态
			basicMapper.approveJobTask(job);
			// 记录审批意见
			String dept_name = userService.getDeptNameByUserId(user.getUser_iidd());
			String operation = "审批";
			if (approved.equals(ActivitiCons.APPLY_APPROVED_PASS)) {
				operation += "：同意";
			} else {
				operation += "：不同意";
			}
			ProcessRecord record = new ProcessRecord(job_code, job.getJobType(), user.getUser_iidd(),
					user.getUser_name(), dept_name, operation, opinion, new Date());
			activitiService.addProcessRecord(record);

			// 如果审批不同意，修改载体状态为留用
			if (approved.equals(ActivitiCons.APPLY_APPROVED_REJECT)) {
				// 如果驳回流转， 文件状态置为 留用， job_code 置为null
				String entity_type = null;
				Map<String, Object> resetmap = new HashMap<String, Object>();
				if (String.valueOf(job.getJobType()).contains("TRANSFER")) {
					List<EventTransfer> transfers = transferService.getTransferEventByJobCode(job_code);
					for (EventTransfer transfer : transfers) {
						if (null == entity_type) {
							entity_type = transfers.get(0).getEntity_type();
						}
						if (entity_type.equalsIgnoreCase("PAPER")) {
							resetmap.put("barcode", transfer.getBarcode());
							basicMapper.resetEntityPaperStatus(resetmap);
							basicMapper.setPaperJobCodeNull(resetmap);
						} else if (entity_type.equalsIgnoreCase("CD")) {
							resetmap.put("barcode", transfer.getBarcode());
							basicMapper.resetEntityCDStatus(resetmap);
							basicMapper.setCDJobCodeNull(resetmap);
						}
					}
				}
				// 密级变更不通过，状态置为留用，且job_code置为null
				if (String.valueOf(job.getJobType()).contains("MODIFY_SECLV")) {
					List<EventModify> eventModifys = ledgerService.getModifyEventListByJobCode(job_code);
					for (EventModify eventModify : eventModifys) {
						if (null == entity_type) {
							entity_type = eventModifys.get(0).getEntity_type();
						}
						if (entity_type.equalsIgnoreCase("Paper")) {
							Map<String, Object> modify_map = new HashMap<String, Object>();
							modify_map.put("paper_barcode", eventModify.getBarcode());
							modify_map.put("paper_state", "0");
							ledgerMapper.updateModifyPaperState(modify_map);
						}
						if (entity_type.equalsIgnoreCase("CD")) {
							Map<String, Object> modify_map = new HashMap<String, Object>();
							modify_map.put("cd_barcode", eventModify.getBarcode());
							modify_map.put("cd_state", "0");
							ledgerMapper.updateModifyCDState(modify_map);
						}
					}
				}
				// 载体归属转换 mod by rmf 20150502
				if (String.valueOf(job.getJobType()).contains("CHANGE")) {
					List<EventChange> changeList = changeService.getChangeEventListByJobCode(job_code);
					for (EventChange item : changeList) {
						resetmap.put("barcode", item.getBarcode());
						if (null == entity_type) {
							entity_type = changeList.get(0).getEntity_type();
						}
						if (entity_type.equalsIgnoreCase("PAPER")) {
							basicMapper.resetEntityPaperStatus(resetmap);
							basicMapper.setPaperJobCodeNull(resetmap);
						} else if (entity_type.equalsIgnoreCase("CD")) {
							basicMapper.resetEntityCDStatus(resetmap);
							basicMapper.setCDJobCodeNull(resetmap);
						}
					}
				}
				// 判断借用文件、光盘的情况
				int borrow_type = 0;
				EventBorrow event = borrowService.getBorrowEventByJobCode(job_code);
				if (event != null) {
					if (String.valueOf(event.getEntity_type()).equals("PAPER")) {
						borrow_type = 1;
					} else if (String.valueOf(event.getEntity_type()).equals("CD")) {
						borrow_type = 2;
					}
					if (borrow_type == 1) {
						resetmap.put("barcode", event.getBarcode());
						basicMapper.resetEntityPaperStatus(resetmap);
					}
					if (borrow_type == 2) {
						resetmap.put("barcode", event.getBarcode());
						basicMapper.resetEntityCDStatus(resetmap);
					}
				}
				// 文件送销
				if (String.valueOf(job.getJobType()).equals("SENDES_PAPER")) {
					List<EntityPaper> paperList = ledgerService.getSendDestroyPaperListByJobCode(job_code);
					for (EntityPaper paper : paperList) {
						resetmap.put("paper_barcode", paper.getPaper_barcode());
						resetmap.put("paper_state", 1);// 已回收
						ledgerMapper.updatePaperStateByBarcode(resetmap);
					}
				}
				// 光盘送销
				if (String.valueOf(job.getJobType()).equals("SENDES_CD")) {
					List<EntityCD> cdList = ledgerService.getSendDestroyCDListByJobCode(job_code);
					for (EntityCD cd : cdList) {
						resetmap.put("cd_barcode", cd.getCd_barcode());
						resetmap.put("cd_state", 1);// 已回收
						ledgerMapper.updateCDStateByBarcode(resetmap);
					}
				}

				if (String.valueOf(entitytype).equalsIgnoreCase("_PAPER")) {
					List<EntityPaper> paperList = ledgerService.getPaperListByJobCode(job_code);
					for (EntityPaper paper : paperList) {
						resetmap.put("barcode", paper.getPaper_barcode());
						basicMapper.resetEntityPaperStatus(resetmap);
						basicMapper.setPaperJobCodeNull(resetmap);
					}

				} else if (String.valueOf(entitytype).equalsIgnoreCase("_CD")) {
					List<EntityCD> cdList = ledgerService.getCDListByJobCode(job_code);
					for (EntityCD cd : cdList) {
						resetmap.put("barcode", cd.getCd_barcode());
						basicMapper.resetEntityCDStatus(resetmap);
						basicMapper.setCDJobCodeNull(resetmap);
					}
				} else if (String.valueOf(entitytype).equalsIgnoreCase("_DEVICE")) {
					List<EntityDevice> deviceList = ledgerService.getDeviceListByJobCode(job_code);
					for (EntityDevice device : deviceList) {
						resetmap.put("barcode", device.getDevice_barcode());
						basicMapper.resetEntityDeviceStatus(resetmap);
					}
				}
				if (job_code.contains("CARRYOUT_PAPER")) {
					List<EntityPaper> paperList = ledgerService.getPaperListByJobCode(job_code);
					for (EntityPaper paper : paperList) {
						resetmap.put("barcode", paper.getPaper_barcode());
						basicMapper.resetEntityPaperStatus(resetmap);
						basicMapper.setPaperJobCodeNull(resetmap);
					}

				} else if (job_code.contains("CARRYOUT_CD")) {
					List<EntityCD> cdList = ledgerService.getCDListByJobCode(job_code);
					for (EntityCD cd : cdList) {
						resetmap.put("barcode", cd.getCd_barcode());
						basicMapper.resetEntityCDStatus(resetmap);
						basicMapper.setCDJobCodeNull(resetmap);
					}
				}
				// 监销流程不通过重置entity_paper表
				if (String.valueOf(job.getJobType()).equals("DESTROY_PAPER_BYSELF")) {
					List<EntityPaper> paperList = ledgerService.getPaperListByJobCode(job_code);
					for (EntityPaper paper : paperList) {
						resetmap.put("barcode", paper.getPaper_barcode());
						resetmap.put("paper_state", 0);// 留用
						resetmap.put("supervise_user_iidd", "无");
						ledgerMapper.updateSupervisePaperStateByBarcode(resetmap);// 需重写
					}
				}
				// 监销流程不通过重置entity_cd表
				if (String.valueOf(job.getJobType()).equals("DESTROY_CD_BYSELF")) {
					List<EntityCD> cdList = ledgerService.getCDListByJobCode(job_code);
					for (EntityCD cd : cdList) {
						resetmap.put("barcode", cd.getCd_barcode());
						resetmap.put("cd_state", 0);// 留用
						resetmap.put("supervise_user_iidd", "无");
						ledgerMapper.updateSuperviseCDStateByBarcode(resetmap);// 需重写
					}
				}
			}
		} else
			throw new Exception("该审批任务已被用户取消！");
	}

	@Override
	public List<ProcessJob> getApprovedJobByUserId(String user_iidd, JobTypeEnum jobType, String user_name,
			Integer seclv_code) {
		List<String> instanceIdList = new ArrayList<String>();
		List<String> tempList = new ArrayList<String>();
		if (jobType.getJobTypeCode().equals("CARRYOUT")) {
			tempList = basicPrcManage.getApprovedInstanceIdList(user_iidd, JobTypeEnum.CARRYOUT_CD.getJobTypeCode());
			instanceIdList.addAll(tempList);
			tempList = basicPrcManage.getApprovedInstanceIdList(user_iidd, JobTypeEnum.CARRYOUT_PAPER.getJobTypeCode());
			instanceIdList.addAll(tempList);
		} else {
			instanceIdList = basicPrcManage.getApprovedInstanceIdList(user_iidd, jobType.getJobTypeCode());
		}
		if (instanceIdList != null && instanceIdList.size() > 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("seclv_code", seclv_code);
			map.put("user_name", user_name);
			map.put("instanceIds", instanceIdList);
			return basicMapper.getApprovedJobByInstanceIds(map);
		}
		return Collections.emptyList();
	}

	@Override
	public List<SecLevel> getHigherSeclvList(SecLevel seclv) {
		List<SecLevel> allSec = userService.getSecLevel();
		List<SecLevel> list = new ArrayList<SecLevel>();
		for (SecLevel item : allSec) {
			if (item.getSeclv_rank() <= seclv.getSeclv_rank()) {
				list.add(item);
			}
		}
		return list.size() == 0 ? null : list;
	}

	@Override
	public void addProcessJob(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType,
			String next_approver, String output_dept_name, String output_user_name, String comment, String event_codes,
			String usage_code, String project_code) throws Exception {
		logger.debug("addProcessJob");
		ApproveProcess process = getApproveProcessByKey(dept_id, seclv_code, jobType.getJobTypeCode(), usage_code);
		String job_status = null;
		if (process.getTotal_steps() == 0) {
			job_status = ActivitiCons.APPLY_APPROVED_PASS;
		} else {
			job_status = ActivitiCons.APPLY_APPROVED_DEFAULT;
		}
		String next_approver_name = getApproverName(next_approver);
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
		addEventJobRel(event_codes, job, usage_code, project_code);
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
			if (jobType.getJobTypeCode().startsWith("PRINT_")) {
				oper_type = "PRINT";
			} else if (jobType.getJobTypeCode().startsWith("BURN_")) {
				oper_type = "BURN";
			} else {
				oper_type = jobType.getJobTypeCode();
			}
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(), message,
						new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		} else {// 如果不需要审批，且需要交接确认，则在交接确认表中增加此次流转的待确认信息
			if (isConfirmOpen("TRANSFER_CONFIRM")) {
				addConfirmRecord(job);
			}
			if (String.valueOf(job.getJobType()).contains("TRANSFER")) {
				List<EventTransfer> transfers = transferService.getTransferEventByJobCode(job_code);
				if (transfers.size() > 0) {
					ClientMsg clientMsg = new ClientMsg(transfers.get(0).getAccept_user_iidd(), transfers.get(0)
							.getAccept_user_name(), "TRANSFER_CONFIRM", 8, transfers.get(0).getJob_code(), "流转确认",
							new Date(), 0);
					basicMapper.addClientMsg(clientMsg);
				}
			}
		}
	}

	@Override
	public SysSeclevel getSysSecLevelByCode(Integer seclv_code) {
		logger.debug("getSysSecLevelByCode");
		return basicMapper.getSysSecLevelByCode(seclv_code);
	}

	@Override
	public void configSeclv(SysSeclevel seclv) {
		logger.debug("configSeclv");
		basicMapper.delSysSeclevelByCode(seclv.getSeclv_code());
		basicMapper.addSysSeclevel(seclv);
	}

	@Override
	public List<EntityPaper> getPaperListByBarcodes(String barcodes) {
		logger.debug("getPaperListByBarcodes");
		List<String> list = new ArrayList<String>();
		for (String item : barcodes.split(",")) {
			if (StringUtils.hasLength(item.trim())) {
				list.add(item.trim());
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("barcodes", list);
		return basicMapper.getPaperListByBarcodes(map);
	}

	@Override
	public void handlePaperJob(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType,
			String next_approver, String barcodes, String output_dept_name, String output_user_name, String reason,
			String supervise_user_iidd) throws Exception {
		logger.debug("handlePaperJob");
		ApproveProcess process = getApproveProcessByKey(dept_id, seclv_code, jobType.getJobTypeCode(), "");
		String job_status = null;
		if (process.getTotal_steps() == 0) {
			job_status = ActivitiCons.APPLY_APPROVED_PASS;
		} else {
			job_status = ActivitiCons.APPLY_APPROVED_DEFAULT;
		}
		String next_approver_name = getApproverName(next_approver);
		String job_code = user_iidd + "-" + jobType.getJobTypeCode() + "-" + System.currentTimeMillis();
		ProcessJob job = new ProcessJob(job_code, user_iidd, dept_id, seclv_code, jobType, new Date(), job_status,
				next_approver, next_approver_name, process.getProcess_id());
		job.setOutput_dept_name(output_dept_name);// 录入台账修改删除模块暂时占用该字段，用完更新为空201603郭姣。
		job.setOutput_user_name(output_user_name);
		job.setComment(reason);// 修改将该参数作为用户原因2016-03 郭姣
		job.setAccept_user_iidd(supervise_user_iidd);// 借用该参数临时存储监销人属性
		// 开启流程
		basicPrcManage.addActivitiApply(job, process);
		// 把任务信息插入数据库
		basicMapper.addProcessJob(job);
		// 更新作业信息
		addEntityJobRel(barcodes, job, jobType);
		String user_name = userService.getUserNameByUserId(job.getUser_iidd());
		String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
		String detail = "提交" + jobType.getJobTypeName() + "申请";
		ProcessRecord record = new ProcessRecord(job.getJob_code(), job.getJobType(), job.getUser_iidd(), user_name,
				dept_name, detail, "请审批", new Date());
		activitiService.addProcessRecord(record);
		if (process.getTotal_steps() != 0) {
			String message = "";
			String oper_type = "";
			if (jobType.getJobTypeCode().equals("PAPER_DEL") || jobType.getJobTypeCode().equals("PAPER_MODIFY")
					|| jobType.getJobTypeCode().equals("MERGE_ENTITY")) {
				message = dept_name + user_name + "有" + jobType.getJobTypeName() + "申请需要您审批";
				oper_type = jobType.getJobTypeCode();
			} else if (jobType.getJobTypeCode().contains("_PAPER")) {
				message = dept_name + user_name + "有" + jobType.getJobTypeName() + "申请需要您审批";
				oper_type = jobType.getJobTypeCode();
			} else {
				message = dept_name + user_name + "有文件" + jobType.getJobTypeName() + "申请需要您审批";
				oper_type = jobType.getJobTypeCode() + "_PAPER";
			}
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(), message,
						new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}
		// 提交延期留用申请，没有审批流程时，更新载体提醒回收时间
		if (process.getTotal_steps() == 0) {
			if (jobType.getJobTypeCode().contains("DELAY")) {
				// 更新文件载体提醒回收时间
				List<EntityPaper> paperList = ledgerMapper.getPaperListByJobCode(job_code);
				Map<String, Object> map_update = new HashMap<String, Object>();
				for (EntityPaper paper : paperList) {
					SysSeclevel sysSeclevel = basicMapper.getSysSecLevelByCode(paper.getSeclv_code());
					if (sysSeclevel.getArchive_time() > 0) {
						map_update.put("paper_barcode", paper.getPaper_barcode());
						// 如果存在回收提醒时间，则按此延期；否则按照当前时间延期
						// if (paper.getExpire_time_original() != null) {
						// map_update.put(
						// "expire_time",
						// TimeUtil.getAfterXDayByTransferDay(paper.getExpire_time_original(),
						// sysSeclevel.getArchive_time()));
						// } else {
						// map_update.put("expire_time", TimeUtil.getAfterXDay(sysSeclevel.getArchive_time()));
						// }
						if (paper.getDelay_days() <= sysSeclevel.getArchive_time()) {
							// 如果延期留用时间小于或等于回收提醒时间，并且存在回收提醒时间，则按延期留用时间；否则按照当前时间延期
							if (paper.getExpire_time_original() != null) {
								map_update.put(
										"expire_time",
										TimeUtil.getAfterXDayByTransferDay(paper.getExpire_time_original(),
												paper.getDelay_days()));
							} else {
								map_update.put("expire_time", TimeUtil.getAfterXDay(paper.getDelay_days()));
							}
						}
						basicMapper.updatePaperExpireTime(map_update);
					}
				}
			} else if (jobType.getJobTypeCode().equals("MERGE_ENTITY")) {
				// ①新总台账载体产生 ②分台账载体状态更新，更新与新台账条码的关联
				ledgerService.addMergeEntityPaper(user_iidd, dept_id, seclv_code, job, barcodes);
			}
		}
	}

	private void addEntityJobRel(String barcodes, ProcessJob job, JobTypeEnum jobType) {
		logger.debug("addEntityJobRel");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("barcodes", barcodes.split(","));
		map.put("job_code", job.getJob_code());
		map.put("supervise_user_iidd", job.getAccept_user_iidd());
		map.put("output_undertaker", job.getOutput_undertaker());
		switch (jobType.getJobTypeCode()) {
		case "DESTROY_PAPER":
			int recover = getSysConfigItemValue(SysConfigItem.KEY_Recover_On_Off).getStartuse();
			int destroy = getSysConfigItemValue(SysConfigItem.KEY_SendDestroy_On_Off).getStartuse();
			if (recover == 0 && destroy == 0) {
				map.put("paper_state", 11);
			} else if (recover == 0 && destroy == 1) {
				map.put("paper_state", 1);
			} else {
				map.put("paper_state", 7);
			}
			break;
		case "SEND_PAPER":
			map.put("paper_state", 8);
			break;
		case "FILE_PAPER":
			map.put("paper_state", 9);
			break;
		case "DELAY_PAPER":
			map.put("paper_state", 0);
			break;
		case "DESTROY":
			map.put("paper_state", 7);
			break;
		case "SEND":
			map.put("paper_state", 8);
			break;
		case "FILE":
			map.put("paper_state", 9);
			break;
		case "DELAY":
			map.put("paper_state", 0);
			break;
		case "PAPER_DEL":
			map.put("paper_state", 14);
			break;
		case "PAPER_MODIFY":
			map.put("paper_state", 14);
			break;
		case "DESTROY_PAPER_BYSELF":
			map.put("paper_state", 15);
			break;
		case "MERGE_ENTITY":
			map.put("paper_state", 20);
			break;
		default:
			break;
		}
		basicMapper.updateEntityStatus(map);
	}

	@Override
	public List<ProcessJob> getHandleJobListByEntity(String user_iidd, String type) {
		List<ProcessJob> jobList = null;
		List<String> instanceIdList = new ArrayList<String>();
		List<String> tempList = new ArrayList<String>();
		// 销毁申请
		tempList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd, JobTypeEnum.DESTROY.getJobTypeCode()));
		tempList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd, JobTypeEnum.DESTROY_PAPER.getJobTypeCode()));
		tempList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd, JobTypeEnum.DESTROY_CD.getJobTypeCode()));
		tempList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd,
				JobTypeEnum.DESTROY_DEVICE.getJobTypeCode()));
		tempList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd,
				JobTypeEnum.DESTROY_PAPER_BYSELF.getJobTypeCode()));
		// 光盘自主销毁
		tempList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd,
				JobTypeEnum.DESTROY_CD_BYSELF.getJobTypeCode()));
		if (tempList != null && tempList.size() > 0) {
			instanceIdList.addAll(tempList);
			tempList.clear();
		}
		// 外发申请
		tempList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd, JobTypeEnum.SEND.getJobTypeCode()));
		tempList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd, JobTypeEnum.SEND_PAPER.getJobTypeCode()));
		tempList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd, JobTypeEnum.SEND_CD.getJobTypeCode()));
		if (tempList != null && tempList.size() > 0) {
			instanceIdList.addAll(tempList);
			tempList.clear();
		}
		// 外带申请
		tempList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd, JobTypeEnum.CARRYOUT.getJobTypeCode()));
		tempList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd,
				JobTypeEnum.CARRYOUT_PAPER.getJobTypeCode()));
		tempList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd, JobTypeEnum.CARRYOUT_CD.getJobTypeCode()));
		if (tempList != null && tempList.size() > 0) {
			instanceIdList.addAll(tempList);
			tempList.clear();
		}
		// 归档申请
		tempList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd, JobTypeEnum.FILE.getJobTypeCode()));
		tempList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd, JobTypeEnum.FILE_PAPER.getJobTypeCode()));
		tempList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd, JobTypeEnum.FILE_CD.getJobTypeCode()));
		if (tempList != null && tempList.size() > 0) {
			instanceIdList.addAll(tempList);
			tempList.clear();
		}
		// 留用申请
		tempList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd, JobTypeEnum.DELAY.getJobTypeCode()));
		tempList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd, JobTypeEnum.DELAY_PAPER.getJobTypeCode()));
		tempList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd, JobTypeEnum.DELAY_CD.getJobTypeCode()));
		if (tempList != null && tempList.size() > 0) {
			instanceIdList.addAll(tempList);
			tempList.clear();
		}
		// 已归档载体二次处理申请
		tempList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd,
				JobTypeEnum.FILECD_DESTROY.getJobTypeCode()));
		tempList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd, JobTypeEnum.FILE_DESTROY.getJobTypeCode()));
		if (tempList != null && tempList.size() > 0) {
			instanceIdList.addAll(tempList);
			tempList.clear();
		}

		// 文件台账合并申请
		tempList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd, JobTypeEnum.MERGE_ENTITY.getJobTypeCode()));
		if (tempList != null && tempList.size() > 0) {
			instanceIdList.addAll(tempList);
			tempList.clear();
		}
		// 文件送销申请
		if (type.equalsIgnoreCase("PAPER")) {
			tempList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd,
					JobTypeEnum.SENDES_PAPER.getJobTypeCode()));
			if (tempList != null && tempList.size() > 0) {
				instanceIdList.addAll(tempList);
				tempList.clear();
			}
		}
		// 光盘送销申请
		if (type.equalsIgnoreCase("CD")) {
			tempList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd, JobTypeEnum.SENDES_CD.getJobTypeCode()));
			if (tempList != null && tempList.size() > 0) {
				instanceIdList.addAll(tempList);
				tempList.clear();
			}
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
	public List<ProcessJob> getHandledJobByUserId(String user_iidd, String type, String user_name, Integer seclv_code,
			String jobType_code) {
		List<ProcessJob> jobList = null;
		List<String> instanceIdList = new ArrayList<String>();
		List<String> tempList = new ArrayList<String>();
		if (StringUtils.hasLength(jobType_code)) {
			instanceIdList = basicPrcManage.getApprovedInstanceIdList(user_iidd, jobType_code);
		} else {
			// 销毁申请
			tempList.addAll(basicPrcManage.getApprovedInstanceIdList(user_iidd, JobTypeEnum.DESTROY.getJobTypeCode()));
			tempList.addAll(basicPrcManage.getApprovedInstanceIdList(user_iidd,
					JobTypeEnum.DESTROY_PAPER.getJobTypeCode()));
			tempList.addAll(basicPrcManage.getApprovedInstanceIdList(user_iidd, JobTypeEnum.DESTROY_CD.getJobTypeCode()));
			tempList.addAll(basicPrcManage.getApprovedInstanceIdList(user_iidd,
					JobTypeEnum.DESTROY_DEVICE.getJobTypeCode()));
			tempList.addAll(basicPrcManage.getApprovedInstanceIdList(user_iidd,
					JobTypeEnum.DESTROY_PAPER_BYSELF.getJobTypeCode()));
			// 光盘自主销毁
			tempList.addAll(basicPrcManage.getApprovedInstanceIdList(user_iidd,
					JobTypeEnum.DESTROY_CD_BYSELF.getJobTypeCode()));
			if (tempList != null && tempList.size() > 0) {
				instanceIdList.addAll(tempList);
				tempList.clear();
			}
			// 外发申请
			tempList.addAll(basicPrcManage.getApprovedInstanceIdList(user_iidd, JobTypeEnum.SEND.getJobTypeCode()));
			tempList.addAll(basicPrcManage.getApprovedInstanceIdList(user_iidd, JobTypeEnum.SEND_PAPER.getJobTypeCode()));
			tempList.addAll(basicPrcManage.getApprovedInstanceIdList(user_iidd, JobTypeEnum.SEND_CD.getJobTypeCode()));
			if (tempList != null && tempList.size() > 0) {
				instanceIdList.addAll(tempList);
				tempList.clear();
			}
			// 归档申请
			tempList.addAll(basicPrcManage.getApprovedInstanceIdList(user_iidd, JobTypeEnum.FILE.getJobTypeCode()));
			tempList.addAll(basicPrcManage.getApprovedInstanceIdList(user_iidd, JobTypeEnum.FILE_PAPER.getJobTypeCode()));
			tempList.addAll(basicPrcManage.getApprovedInstanceIdList(user_iidd, JobTypeEnum.FILE_CD.getJobTypeCode()));
			if (tempList != null && tempList.size() > 0) {
				instanceIdList.addAll(tempList);
				tempList.clear();
			}
			// 留用申请
			tempList.addAll(basicPrcManage.getApprovedInstanceIdList(user_iidd, JobTypeEnum.DELAY.getJobTypeCode()));
			tempList.addAll(basicPrcManage.getApprovedInstanceIdList(user_iidd,
					JobTypeEnum.DELAY_PAPER.getJobTypeCode()));
			tempList.addAll(basicPrcManage.getApprovedInstanceIdList(user_iidd, JobTypeEnum.DELAY_CD.getJobTypeCode()));
			if (tempList != null && tempList.size() > 0) {
				instanceIdList.addAll(tempList);
				tempList.clear();
			}
			// 已归档载体二次处理申请
			tempList.addAll(basicPrcManage.getApprovedInstanceIdList(user_iidd,
					JobTypeEnum.FILECD_DESTROY.getJobTypeCode()));
			tempList.addAll(basicPrcManage.getApprovedInstanceIdList(user_iidd,
					JobTypeEnum.FILE_DESTROY.getJobTypeCode()));
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
	public void delJob(String job_code) {
		basicMapper.delJob(job_code);
	}

	@Override
	public List<SysProxy> getUserProxys(String user_iidd) {
		return basicMapper.getUserProxys(user_iidd);
	}

	@Override
	public void addUserProxy(Map<String, Object> map) {
		basicMapper.addUserProxy(map);

	}

	@Override
	public void deleteUserProxy(Map<String, Object> map) {
		basicMapper.deleteUserProxy(map);
	}

	public List<SysRecycleBox> getAllRecycleBoxList() {
		logger.debug("getAllRecycleBoxList");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("is_all", true);
		return basicMapper.getSysRecycleBoxList(map);
	}

	@Override
	public List<SysRecycleBox> getSysRecycleBoxList() {
		logger.debug("getSysRecycleBoxList");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("is_all", false);
		return basicMapper.getSysRecycleBoxList(map);
	}

	@Override
	public void addSysRecycleBox(SysRecycleBox recycleBox) {
		logger.debug("addSysRecycleBox:" + recycleBox.getBox_code());
		basicMapper.addSysRecycleBox(recycleBox);
	}

	@Override
	public void delSysRecycleBoxByCode(String box_code) {
		logger.debug("delSysRecycleBoxByCode:" + box_code);
		basicMapper.setRecycleBoxSealedByCode(box_code);
	}

	@Override
	public SysRecycleBox getRecycleBoxByCode(String box_code) {
		logger.debug("getRecycleBoxByCode:" + box_code);
		return basicMapper.getRecycleBoxByCode(box_code);
	}

	@Override
	public void updateRecycleBox(SysRecycleBox recycleBox) {
		logger.debug("updateRecycleBox:" + recycleBox.getBox_code());
		basicMapper.updateRecycleBox(recycleBox);
	}

	@Override
	public List<SysPrinterUser> getPrintUserList(String printer_code, String user_iidd) {
		logger.debug("getPrintUserList");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("printer_code", printer_code);
		map.put("user_iidd", user_iidd);
		return basicMapper.getPrintUserList(map);
	}

	@Override
	public void addPrinterUsers(String user_iidds, String printer_code) {
		logger.debug("addPrinterUsers");
		if (!StringUtils.hasLength(user_iidds) || ",".equals(user_iidds)) {
			return;
		}
		String[] users = user_iidds.split(",");
		for (String user_iidd : users) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("printer_code", printer_code);
			map.put("user_iidd", user_iidd);
			basicMapper.addPrinterUser(map);
		}
	}

	@Override
	public void delPrinterUser(String printer_code, String user_iidd) {
		logger.debug("delPrinterUser");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("printer_code", printer_code);
		map.put("user_iidd", user_iidd);
		basicMapper.delPrinterUser(map);

	}

	public List<SysExchangeBox> getAllExchangeBoxList() {
		logger.debug("getAllExchangeBoxList");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("is_all", true);
		return basicMapper.getSysExchangeBoxList(map);
	}

	@Override
	public List<SysExchangeBox> getSysExchangeBoxList() {
		logger.debug("getSysExchangeBoxList");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("is_all", false);
		return basicMapper.getSysExchangeBoxList(map);
	}

	@Override
	public void addSysExchangeBox(SysExchangeBox exchangeBox) {
		logger.debug("addSysExchangeBox:" + exchangeBox.getBox_code());
		basicMapper.addSysExchangeBox(exchangeBox);
	}

	@Override
	public void delSysExchangeBoxByCode(String box_code) {
		logger.debug("delSysExchangeBoxByCode:" + box_code);
		basicMapper.setExchangeBoxSealedByCode(box_code);
	}

	@Override
	public SysExchangeBox getExchangeBoxByCode(String box_code) {
		logger.debug("getRecycleBoxByCode:" + box_code);
		return basicMapper.getExchangeBoxByCode(box_code);
	}

	@Override
	public void updateExchangeBox(SysExchangeBox exchangeBox) {
		logger.debug("updateRecycleBox:" + exchangeBox.getBox_code());
		basicMapper.updateExchangeBox(exchangeBox);
	}

	@Override
	public SysConfigItem getSysConfigItemValue(String item_key) {
		return basicMapper.getSysConfigItemValue(item_key);
	}

	@Override
	public void updateSysConfigItem(SysConfigItem sysConfigItem) {
		basicMapper.updateSysConfigItem(sysConfigItem);
	}

	@Override
	public void addSysConfigItem(SysConfigItem sysConfigItem) {
		basicMapper.addSysConfigItem(sysConfigItem);
	}

	@Override
	public List<SysProxy> getUserAprvProxys(String user_iidd) {
		return basicMapper.getUserAprvProxys(user_iidd);
	}

	@Override
	public void addUserAprvProxy(Map<String, Object> map) {
		basicMapper.addUserAprvProxy(map);
	}

	@Override
	public void deleteUserAprvProxy(Map<String, Object> map) {
		basicMapper.deleteUserAprvProxy(map);
	}

	@Override
	public String getParentDeptIdByCurrentId(String curr_user_dept) {
		// TODO Auto-generated method stub
		return basicMapper.getParentDeptIdByCurrentId(curr_user_dept);
	}

	@Override
	public List<SysPlace> getAllPlaces() {
		// TODO Auto-generated method stub
		return basicMapper.getAllPlaces();
	}

	@Override
	public void addPlace(Map<String, Object> map) {
		basicMapper.addPlace(map);
	}

	@Override
	public void delPlace(String place_code) {
		basicMapper.delPlace(place_code);
	}

	@Override
	public void updatePlace(Map<String, Object> map) {
		basicMapper.updatePlace(map);
	}

	@Override
	public SysPlace getPlaceByCode(String place_code) {
		return basicMapper.getPlaceByCode(place_code);
	}

	@Override
	public List<EntityCD> getCDListByBarcodes(String barcodes) {
		logger.debug("getCDListByBarcodes");
		List<String> list = new ArrayList<String>();
		for (String item : barcodes.split(",")) {
			if (StringUtils.hasLength(item.trim())) {
				list.add(item.trim());
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("barcodes", list);
		return basicMapper.getCDListByBarcodes(map);
	}

	@Override
	public void handleCDJob(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType,
			String next_approver, String barcodes, String output_dept_name, String output_user_name, String usage_code,
			String supervise_user_iidd) throws Exception {
		logger.debug("handleCDJob");
		ApproveProcess process = getApproveProcessByKey(dept_id, seclv_code, jobType.getJobTypeCode(), usage_code);
		String job_status = null;
		if (process.getTotal_steps() == 0) {
			job_status = ActivitiCons.APPLY_APPROVED_PASS;
		} else {
			job_status = ActivitiCons.APPLY_APPROVED_DEFAULT;
		}
		String next_approver_name = getApproverName(next_approver);
		String job_code = user_iidd + "-" + jobType.getJobTypeCode() + "-" + System.currentTimeMillis();
		ProcessJob job = new ProcessJob(job_code, user_iidd, dept_id, seclv_code, jobType, new Date(), job_status,
				next_approver, next_approver_name, process.getProcess_id());
		job.setOutput_dept_name(output_dept_name);
		job.setOutput_user_name(output_user_name);
		// 光盘自主销毁 借用该参数临时存储监销人属性
		job.setAccept_user_iidd(supervise_user_iidd);
		//
		basicPrcManage.addActivitiApply(job, process);
		// 把任务信息插入数据库
		basicMapper.addProcessJob(job);
		// 更新作业信息
		addEntityCDJobRel(barcodes, job, jobType);
		String user_name = userService.getUserNameByUserId(job.getUser_iidd());
		String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
		String detail = "提交" + jobType.getJobTypeName() + "申请";
		ProcessRecord record = new ProcessRecord(job.getJob_code(), job.getJobType(), job.getUser_iidd(), user_name,
				dept_name, detail, "请审批", new Date());
		activitiService.addProcessRecord(record);
		if (process.getTotal_steps() != 0) {
			String message = "";
			String oper_type = "";
			if (jobType.getJobTypeCode().contains("_CD")) {
				message = dept_name + user_name + "有" + jobType.getJobTypeName() + "申请需要您审批";
				oper_type = jobType.getJobTypeCode();
			} else {
				message = dept_name + user_name + "有光盘" + jobType.getJobTypeName() + "申请需要您审批";
				oper_type = jobType.getJobTypeCode() + "_CD";
			}
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(), message,
						new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}
		// 提交延期留用申请，没有审批流程时，更新载体提醒回收时间
		if (process.getTotal_steps() == 0) {
			if (jobType.getJobTypeCode().contains("DELAY")) {
				// 更新光盘载体提醒回收时间
				List<EntityCD> cdList = ledgerMapper.getCDListByJobCode(job_code);
				Map<String, Object> map_update = new HashMap<String, Object>();
				for (EntityCD cd : cdList) {
					SysSeclevel sysSeclevel = basicMapper.getSysSecLevelByCode(cd.getSeclv_code());
					if (sysSeclevel.getArchive_time() > 0) {
						map_update.put("cd_barcode", cd.getCd_barcode());
						// 如果存在回收提醒时间，则按此延期；否则按照当前时间延期
						// if (cd.getExpire_time_original() != null) {
						// map_update.put(
						// "expire_time",
						// TimeUtil.getAfterXDayByTransferDay(cd.getExpire_time_original(),
						// sysSeclevel.getArchive_time()));
						// } else {
						// map_update.put("expire_time", TimeUtil.getAfterXDay(sysSeclevel.getArchive_time()));
						// }
						if (cd.getDelay_days() <= sysSeclevel.getArchive_time()) {
							// 如果延期留用时间小于或等于回收提醒时间，并且存在回收提醒时间，则按延期留用时间；否则按照当前时间延期
							if (cd.getExpire_time_original() != null) {
								map_update.put(
										"expire_time",
										TimeUtil.getAfterXDayByTransferDay(cd.getExpire_time_original(),
												cd.getDelay_days()));
							} else {
								map_update.put("expire_time", TimeUtil.getAfterXDay(cd.getDelay_days()));
							}
						}
						basicMapper.updateCDExpireTime(map_update);
					}
				}
			}
		}
	}

	private void addEntityCDJobRel(String barcodes, ProcessJob job, JobTypeEnum jobType) {
		logger.debug("addEntityCDJobRel");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("barcodes", barcodes.split(","));
		map.put("job_code", job.getJob_code());
		// 光盘自主销毁
		map.put("supervise_user_iidd", job.getAccept_user_iidd());
		map.put("output_undertaker", job.getOutput_undertaker());
		switch (jobType.getJobTypeCode()) {
		case "DESTROY_CD":
			int recover = getSysConfigItemValue(SysConfigItem.KEY_Recover_On_Off).getStartuse();
			int destroy = getSysConfigItemValue(SysConfigItem.KEY_SendDestroy_On_Off).getStartuse();
			if (recover == 0 && destroy == 0) {
				map.put("cd_state", 11);
			} else if (recover == 0 && destroy == 1) {
				map.put("cd_state", 1);
			} else {
				map.put("cd_state", 7);
			}
			break;
		case "SEND_CD":
			map.put("cd_state", 8);
			break;
		case "FILE_CD":
			map.put("cd_state", 9);
			break;
		case "DELAY_CD":
			map.put("cd_state", 0);
			break;
		case "DESTROY":
			map.put("cd_state", 7);
			break;
		case "SEND":
			map.put("cd_state", 8);
			break;
		case "FILE":
			map.put("cd_state", 9);
			break;
		case "DELAY":
			map.put("cd_state", 0);
			break;
		case "DESTROY_CD_BYSELF":
			map.put("cd_state", 15);
			break;
		default:
			break;
		}
		basicMapper.updateEntityCDStatus(map);
	}

	@Override
	public void handleDeviceJob(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType,
			String next_approver, String device_code, String usage_code, String supervise_user_iidd) throws Exception {
		logger.debug("handleDeviceJob");
		ApproveProcess process = getApproveProcessByKey(dept_id, seclv_code, jobType.getJobTypeCode(), usage_code);
		String job_status = null;
		if (process.getTotal_steps() == 0) {
			job_status = ActivitiCons.APPLY_APPROVED_PASS;
		} else {
			job_status = ActivitiCons.APPLY_APPROVED_DEFAULT;
		}
		String next_approver_name = getApproverName(next_approver);
		String job_code = user_iidd + "-" + jobType.getJobTypeCode() + "-" + System.currentTimeMillis();
		ProcessJob job = new ProcessJob(job_code, user_iidd, dept_id, seclv_code, jobType, new Date(), job_status,
				next_approver, next_approver_name, process.getProcess_id());
		// 将监销人字段插入流程表 haojia 20160906
		job.setAccept_user_iidd(supervise_user_iidd);// 借用该参数临时存储监销人属性

		// 开启流程
		basicPrcManage.addActivitiApply(job, process);
		// 把任务信息插入数据库
		basicMapper.addProcessJob(job);
		// 更新作业信息
		addEntityDeviceJobRel(device_code, job);
		String user_name = userService.getUserNameByUserId(job.getUser_iidd());
		String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
		String detail = "提交" + jobType.getJobTypeName() + "申请";
		ProcessRecord record = new ProcessRecord(job.getJob_code(), job.getJobType(), job.getUser_iidd(), user_name,
				dept_name, detail, "请审批", new Date());
		activitiService.addProcessRecord(record);
		// 如果有审批流程，在消息表中添加审批消息
		if (process.getTotal_steps() != 0) {
			String message = "";
			String oper_type = "";
			if (jobType.getJobTypeCode().contains("_DEVICE")) {
				message = dept_name + user_name + "有" + jobType.getJobTypeName() + "申请需要您审批";
				oper_type = jobType.getJobTypeCode();
			} else {
				message = dept_name + user_name + "有磁介质" + jobType.getJobTypeName() + "作业需要您审批";
				oper_type = jobType.getJobTypeCode() + "_DEVICE";
			}
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(), message,
						new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}
	}

	private void addEntityDeviceJobRel(String device_code, ProcessJob job) {
		logger.debug("addEntityDeviceJobRel");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("device_code", device_code);
		map.put("job_code", job.getJob_code());
		map.put("supervise_user_iidd", job.getAccept_user_iidd());

		if (job.getJobType().getJobTypeCode() == "DESTROY_DEVICE_BYSELF") {
			map.put("device_status", 8); // 个人磁介质申请自主销毁
		}

		basicMapper.updateEntityDeviceStatus(map);
	}

	@Override
	public synchronized void submitApplyFile(String item_id, String type, SecUser user) throws Exception {
		if (isConfirmOpen("FILE_CONFIRM")) {
			// 交接确认开启
			ConfirmRecord record = new ConfirmRecord();
			record.setConfirm_dept_id(user.getDept_id());
			record.setConfirm_dept_name(user.getDept_name());
			record.setConfirm_user_iidd(user.getUser_iidd());
			record.setConfirm_user_name(user.getUser_name());
			record.setCreate_time(new Date());
			record.setEntity_type(type);
			record.setConfirm_status("N");
			record.setConfirm_type("FILE");

			if ("CD".equals(type)) {
				EntityCD cd = ledgerService.getOneCDLedgerById(Integer.parseInt(item_id));
				if (cd.getCd_state() == 10) {
					throw new Exception("该光盘已经被其他档案管理员回收了");
				}
				record.setApply_dept_id(cd.getDuty_dept_id());
				record.setApply_dept_name(cd.getDuty_dept_name());
				record.setApply_user_iidd(cd.getDuty_user_iidd());
				record.setApply_user_name(cd.getDuty_user_name());
				record.setEntity_barcode(cd.getCd_barcode());
				record.setEvent_code(cd.getEvent_code());
				record.setEntity_name(cd.getFile_list());
				record.setSeclv_name(cd.getSeclv_name());

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cd_id", cd.getCd_id());
				map.put("duty_user_iidd", cd.getDuty_user_iidd());
				map.put("duty_user_name", cd.getDuty_user_name());
				map.put("duty_dept_id", cd.getDuty_dept_id());
				map.put("duty_dept_name", cd.getDuty_dept_name());
				// map.put("retrieve_time", new Date());
				// map.put("retrieve_user_iidd", user.getUser_iidd());
				map.put("cd_state", 10);// 待交接
				ledgerMapper.updateEntityCD(map);
			}
			if ("PAPER".equals(type)) {
				EntityPaper paper = ledgerService.getOnePaperLedgerById(item_id);
				if (paper.getPaper_state() == 10) {
					throw new Exception("该文件已经被其他档案管理员回收了");
				}
				record.setApply_dept_id(paper.getDuty_dept_id());
				record.setApply_dept_name(paper.getDuty_dept_name());
				record.setApply_user_iidd(paper.getDuty_user_iidd());
				record.setApply_user_name(paper.getDuty_user_name());
				record.setEntity_barcode(paper.getPaper_barcode());
				record.setEvent_code(paper.getEvent_code());
				record.setSeclv_name(paper.getSeclv_name());
				record.setEntity_name(paper.getFile_title());

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("paper_id", paper.getPaper_id());
				map.put("duty_user_iidd", paper.getDuty_user_iidd());
				map.put("duty_user_name", paper.getDuty_user_name());
				map.put("duty_dept_id", paper.getDuty_dept_id());
				map.put("duty_dept_name", paper.getDuty_dept_name());
				// map.put("retrieve_time", new Date());
				// map.put("retrieve_user_iidd", user.getUser_iidd());
				map.put("paper_state", 10);// 待交接
				ledgerMapper.updateEntityPaper(map);
			}
			logger.debug("submitApplyFile");
			basicMapper.saveConfirmRecord(record);// 交接确认记录
		} else {
			// 交接确认关闭
			if ("CD".equals(type)) {
				EntityCD cd = ledgerService.getOneCDLedgerById(Integer.parseInt(item_id));
				Date retrieve_time = new Date();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cd_id", cd.getCd_id());
				map.put("duty_user_iidd", user.getUser_iidd());
				map.put("duty_user_name", user.getUser_name());
				map.put("duty_dept_id", user.getDept_id());
				map.put("duty_dept_name", user.getDept_name());
				map.put("retrieve_time", retrieve_time);
				map.put("retrieve_user_iidd", user.getUser_iidd());
				map.put("cd_state", 3);// 已归档
				ledgerMapper.updateEntityCD(map);
				// 生成载体生命周期记录：归档
				CycleItem cycleitem_file = new CycleItem();
				cycleitem_file.setBarcode(cd.getCd_barcode());
				cycleitem_file.setEntity_type("CD");
				cycleitem_file.setOper_time(retrieve_time);
				cycleitem_file.setUser_name(user.getUser_name());
				cycleitem_file.setDept_name(user.getDept_name());
				cycleitem_file.setOper("FILE");
				// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
				// 此时entity_cd的job_code记录的就是归档时的job_code
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
				// 此时entity_cd的job_code记录的就是归档时的job_code
				cycleitem_recv.setJob_code(cd.getJob_code());
				ledgerMapper.addCycleItem(cycleitem_recv);
			}
			if ("PAPER".equals(type)) {
				EntityPaper paper = ledgerService.getOnePaperLedgerById(item_id);
				Date retrieve_time = new Date();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("paper_id", paper.getPaper_id());
				map.put("duty_user_iidd", user.getUser_iidd());
				map.put("duty_user_name", user.getUser_name());
				map.put("duty_dept_id", user.getDept_id());
				map.put("duty_dept_name", user.getDept_name());
				map.put("retrieve_time", retrieve_time);
				map.put("retrieve_user_iidd", user.getUser_iidd());
				map.put("paper_state", 3);// 已归档
				ledgerMapper.updateEntityPaper(map);
				// 生成载体生命周期记录：归档
				CycleItem cycleitem_file = new CycleItem();
				cycleitem_file.setBarcode(paper.getPaper_barcode());
				cycleitem_file.setEntity_type("PAPER");
				cycleitem_file.setOper_time(retrieve_time);
				cycleitem_file.setUser_name(user.getUser_name());
				cycleitem_file.setDept_name(user.getDept_name());
				cycleitem_file.setOper("FILE");
				// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
				// 此时entity_paper的job_code记录的就是归档时的job_code
				cycleitem_file.setJob_code(paper.getJob_code());
				ledgerMapper.addCycleItem(cycleitem_file);
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
			}
		}
	}

	@Override
	public List<SysBarcode> getAllSysBarcodes() {
		return basicMapper.getAllSysBarcodes();
	}

	@Override
	public SysBarcode getOneBarcodeByCode(String barcode_code) {
		return basicMapper.getOneBarcodeByCode(barcode_code);
	}

	@Override
	public void saveOneBarcode(SysBarcode barcode) {
		basicMapper.saveOneBarcode(barcode);
	}

	@Override
	public void addBorrowProcessJob(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType,
			String next_approver, String output_dept_name, String output_user_name, String comment, String event_codes,
			String usage_code, String project_code, String entity_type, String barcodemedia, String entity_name,
			Date limitTime) throws Exception {
		logger.debug("addBorrowProcessJob");
		ApproveProcess process = getApproveProcessByKey(dept_id, seclv_code, jobType.getJobTypeCode(), usage_code);
		String job_status = null;
		if (process.getTotal_steps() == 0) {
			job_status = ActivitiCons.APPLY_APPROVED_PASS;
		} else {
			job_status = ActivitiCons.APPLY_APPROVED_DEFAULT;
		}
		String next_approver_name = getApproverName(next_approver);
		for (String event_code : event_codes.split(":")) {
			if (StringUtils.hasLength(event_code.trim())) {
				// String job_code = event_code.trim() + "-" +
				// jobType.getJobTypeCode() + "-" +
				// System.currentTimeMillis();
				String job_code = user_iidd + "-" + jobType.getJobTypeCode() + "-" + System.currentTimeMillis();
				ProcessJob job = new ProcessJob(job_code, user_iidd, dept_id, seclv_code, jobType, new Date(),
						job_status, next_approver, next_approver_name, process.getProcess_id());
				job.setOutput_dept_name(output_dept_name);
				job.setOutput_user_name(output_user_name);
				job.setComment(comment);
				// 开启流程
				basicPrcManage.addActivitiApply(job, process);
				// 把任务信息插入数据库
				basicMapper.addProcessJob(job);
				// 更新作业信息
				addEventJobRel(event_code, job, usage_code, project_code);
				String user_name = userService.getUserNameByUserId(job.getUser_iidd());
				String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
				String detail = "提交" + jobType.getJobTypeName() + "申请";
				ProcessRecord record = new ProcessRecord(job.getJob_code(), job.getJobType(), job.getUser_iidd(),
						user_name, dept_name, detail, "请审批", new Date());
				activitiService.addProcessRecord(record);
				// 借用
				String barcode = barcodemedia;
				EventBorrow event = new EventBorrow(event_codes, user_iidd, dept_id, seclv_code, usage_code,
						project_code, comment, entity_type, barcode, "", "", 0, job_code, entity_name);
				event.setLimit_Time(limitTime);
				borrowService.addBorrowEvent(event);
				// 如果有审批流程，在消息表中添加审批消息
				if (process.getTotal_steps() != 0) {
					String message = dept_name + user_name + "有" + jobType.getJobTypeName() + "作业需要您审批";
					String oper_type = jobType.getJobTypeCode();
					for (String item : next_approver.split(",")) {
						String nextApproverName = basicPrcManage.getApproverName(item);
						ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(),
								message, new Date(), 0);
						basicMapper.addClientMsg(clientMsg);
					}
				}
			}
		}

	}

	@Override
	public List<SubSecDeptAdmin> getAdminGroupList(String user_iidd) {
		logger.debug("getAdminGroupList");
		return basicMapper.getAdminGroupList(user_iidd);
	}

	@Override
	public List<String> getSeclvNameByCodes(Map<String, Object> map) {
		return basicMapper.getSeclvNameByCodes(map);
	}

	@Override
	public List<String> getConsoleNameByCodes(Map<String, Object> map) {
		return basicMapper.getConsoleNameByCodes(map);
	}

	@Override
	public List<String> getProjectNameByCodes(Map<String, Object> map) {
		return basicMapper.getProjectNameByCodes(map);
	}

	@Override
	public List<String> getUsageNameByCodes(Map<String, Object> map) {
		return basicMapper.getUsageNameByCodes(map);
	}

	@Override
	public void delBarcodeByCode(String barcode_code) {
		basicMapper.delBarcodeByCode(barcode_code);
	}

	@Override
	public void updateBarcode(SysBarcode barcode) {
		basicMapper.updateBarcode(barcode);
	}

	@Override
	public SysBarcode getBarcodeByCode(String barcode_code) {
		return basicMapper.getBarcodeByCode(barcode_code);
	}

	@Override
	public String createEntityBarcode(String entityType) throws Exception {
		return barcodeGeneratorImpl.createEntityBarcode(entityType);
	}

	@Override
	public String createEntityBarcodeCAEP(String entityType, Integer seclv_code, String dept_id) throws Exception {
		return barcodeGeneratorImpl.createEntityBarcodeCAEP(entityType, seclv_code, dept_id);
	}

	@Override
	public void fileToUse(String item_id, String type, SecUser user) {
		CycleItem cycleitem = new CycleItem();
		cycleitem.setEntity_type(type);
		cycleitem.setOper_time(new Date());
		cycleitem.setUser_name(user.getUser_name());
		cycleitem.setDept_name(user.getDept_name());
		cycleitem.setOper("RESET");
		// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
		// 此处不用启动流程，job_code设置为default
		cycleitem.setJob_code("default");

		if ("CD".equals(type)) {
			EntityCD cd = ledgerService.getOneCDLedgerById(Integer.parseInt(item_id));
			cycleitem.setBarcode(cd.getCd_barcode());
			ledgerMapper.resetEntityCDById(item_id);
		}
		if ("PAPER".equals(type)) {
			EntityPaper paper = ledgerService.getOnePaperLedgerById(item_id);
			cycleitem.setBarcode(paper.getPaper_barcode());
			ledgerMapper.resetEntityPaperById(item_id);
		}
		logger.debug("fileToUse");
		ledgerMapper.addCycleItem(cycleitem);// 全生命周期记录
	}

	@Override
	public void saveFileInfo(String fileName, String relStorePath, String user_iidd, String comment, String type) {
		String[] infos = fileName.split(SEP_STR);
		String prod_num = infos[0].replaceAll("HD", "");
		int to = fileName.lastIndexOf(".");
		String version = fileName.substring(0, to);
		FileInfo fileInfo = new FileInfo(fileName, prod_num, version, relStorePath + File.separator + fileName,
				new Date(), user_iidd, comment, type);
		logger.debug("saveFileInfo");
		FileInfo info = basicMapper.getOneFileInfoByName(fileName);
		if (null == info) {
			basicMapper.saveFileInfo(fileInfo);
		} else {
			info.setUpdate_time(new Date());
			info.setUpdate_user_iidd(user_iidd);
			info.setComment(comment);
			info.setType(type);
			basicMapper.updateFileInfo(info);
		}
	}

	@Override
	public List<PaperStatic> getPaperStaticList(Map<String, Object> map) {
		// 当前部门纸张统计
		List<PaperStatic> finalList = basicMapper.getPaperStaticList(map);
		// 获得一级子部门
		String dept_id = String.valueOf(map.get("dept_id"));
		List<String> firstChildList = basicMapper.getFirstChildList(dept_id);
		Iterator<String> first_child_iter = firstChildList.iterator();
		List<String> tempList = new ArrayList<String>();
		tempList.addAll(firstChildList);
		while (first_child_iter.hasNext()) {
			String first_child_id = first_child_iter.next();
			map.put("dept_id", first_child_id);// 一级子部门id
			// 获得每个一级子部门下的所有子部门纸张统计结果
			List<PaperStatic> childPaperStaticList = basicMapper.getAllChildPaperStaticList(map);
			Iterator<PaperStatic> child_iter = childPaperStaticList.iterator();
			while (child_iter.hasNext()) {
				PaperStatic paper_static = child_iter.next();
				paper_static.setDept_id(first_child_id);// 一级子部门id
				String dept_name = userService.getDeptNameByDeptId(first_child_id);
				paper_static.setDept_name(dept_name);// 一级子部门名称
				finalList.add(paper_static);
				if (tempList.contains(first_child_id)) {
					tempList.remove(first_child_id);
				}
			}
		}
		if (tempList != null && tempList.size() > 0) {
			Integer seclv_code = null;
			String seclv_name = "所有";
			String printer_code = "";
			String printer_name = "所有";
			if (StringUtils.hasLength((String) map.get("seclv_code"))) {
				seclv_code = Integer.parseInt((String) map.get("seclv_code"));
				seclv_name = userService.getSecLevelByCode(seclv_code).getSeclv_name();
			}
			if (StringUtils.hasLength((String) map.get("printer_code"))) {
				printer_code = (String) map.get("printer_code");
				printer_name = getPrinterByCode(printer_code).getPrinter_name();
			}
			for (String item : tempList) {
				finalList.add(new PaperStatic(item, userService.getDeptNameByDeptId(item), printer_code, printer_name,
						seclv_code, seclv_name, 0));
			}
		}
		return finalList;
	}

	@Override
	public void addClientMsg(ClientMsg clientMsg) {
		logger.debug("addClientMsg");
		basicMapper.addClientMsg(clientMsg);
	}

	@Override
	public void updateClientMsg(String accept_user_iidd, String job_code, Integer is_read, Date read_time) {
		logger.debug("updateClientMsg:" + accept_user_iidd);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accept_user_iidd", accept_user_iidd);
		map.put("job_code", job_code);
		map.put("is_read", is_read);
		map.put("read_time", read_time);
		basicMapper.updateClientMsg(map);
	}

	@Override
	public boolean isUsageExist(String usage_code, String usage_name) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("usage_code", usage_code);
		map.put("usage_name", usage_name);
		return basicMapper.getUsageCount(map) > 0;
	}

	@Override
	public boolean checkProject(String project_code, String project_name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("project_code", project_code);
		map.put("project_name", project_name);
		int count = basicMapper.checkProject(map);
		if (1 <= count) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkConsole(String console_code, String console_name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("console_code", console_code);
		map.put("console_name", console_name);
		int count = basicMapper.checkConsole(map);
		if (1 <= count) {
			return true;
		}
		return false;
	}

	@Override
	public List<FileInfo> getFileInfosByType(String type) {
		return basicMapper.getFileInfosByType(type);
	}

	@Override
	public void updateConsoleVersion(String console_code, String hidSet_version) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("console_code", console_code);
		map.put("set_version", hidSet_version);
		basicMapper.updateConsoleVersion(map);
	}

	@Override
	public void setClientMsgRead(String user_iidd, String oper_type, int message_type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accept_user_iidd", user_iidd);
		map.put("oper_type", oper_type);
		map.put("message_type", message_type);
		map.put("read_time", new Date());
		basicMapper.setClientMsgRead(map);
	}

	/*
	 * @Override public String createTwoDimenosionalBarcode(String seclv_id, String version, String user_name, String
	 * seclv_code, String medium, String from_src, String papge_count, String page_index, String file_name, String
	 * dept_id, String entityType) throws Exception { return barcodeGeneratorImpl.createTwoDimenosionalBarcode(seclv_id,
	 * version, user_name, seclv_code, medium, from_src, papge_count, page_index, file_name, dept_id, entityType,"",1);
	 * 
	 * }
	 */
	@Override
	public String buildTwoDimenosionalBarcode(String seclv_id, String version, String user_name, String seclv_code,
			String medium, String from_src, String papge_count, String page_index, String file_name, String dept_id,
			String entity_barcode) throws Exception {
		return barcodeGeneratorImpl.createTwoDimenosionalBarcode(seclv_id, version, user_name, seclv_code, medium,
				from_src, papge_count, page_index, file_name, dept_id, "", entity_barcode, 2);

	}

	@Override
	public SysBarcode getSysBarcode(Integer seclv_code, String usage_code, String project_code) {
		List<SysBarcode> sysBarcodeList = basicMapper.getSysBarcodeList();
		SysBarcode finalSysBarcode = basicMapper.getDefaultSysBarcode();
		for (SysBarcode sysBarcode : sysBarcodeList) {
			String[] seclv_codes = null;
			String[] usage_codes = null;
			String[] project_codes = null;
			if (null != sysBarcode.getSeclv_code()) {
				seclv_codes = sysBarcode.getSeclv_code().split(Constants.COMMON_SEPARATOR_REGEX);
			}
			if (null != sysBarcode.getUsage_code()) {
				usage_codes = sysBarcode.getUsage_code().split(Constants.COMMON_SEPARATOR_REGEX);
			}
			if (null != sysBarcode.getProject_code()) {
				project_codes = sysBarcode.getProject_code().split(Constants.COMMON_SEPARATOR_REGEX);
			}
			if (null != seclv_codes) {
				for (String seclv_code_s : seclv_codes) {
					if (null != seclv_code_s && StringUtils.hasLength(seclv_code_s)) {
						if (seclv_code == Integer.parseInt(seclv_code_s)) {
							for (String usage_code_s : usage_codes) {
								if (usage_code_s.equals(usage_code)) {
									if (StringUtils.hasLength(project_code)) {
										for (String project_code_s : project_codes) {
											if (project_code_s.equals(project_code))
												finalSysBarcode = sysBarcode;
										}
									} else
										finalSysBarcode = sysBarcode;
								}
							}
						}
					}
				}
			}
		}
		return finalSysBarcode;
	}

	@Override
	public List<CDStatic> getCDStaticList(Map<String, Object> map) {
		// 当前光盘数量统计
		List<CDStatic> finalList = basicMapper.getCDStaticList(map);
		// 获得一级子部门
		String dept_id = String.valueOf(map.get("dept_id"));
		List<String> firstChildList = basicMapper.getFirstChildList(dept_id);
		Iterator<String> first_child_iter = firstChildList.iterator();
		List<String> tempList = new ArrayList<String>();
		tempList.addAll(firstChildList);
		while (first_child_iter.hasNext()) {
			String first_child_id = first_child_iter.next();
			map.put("dept_id", first_child_id);// 一级子部门id
			// 获得每个一级子部门下的所有子部门光盘统计结果
			List<CDStatic> childCDStaticList = basicMapper.getAllChildCDStaticList(map);
			Iterator<CDStatic> child_iter = childCDStaticList.iterator();
			while (child_iter.hasNext()) {
				CDStatic cd_static = child_iter.next();
				cd_static.setDept_id(first_child_id);// 一级子部门id
				String dept_name = userService.getDeptNameByDeptId(first_child_id);
				cd_static.setDept_name(dept_name);// 一级子部门名称
				finalList.add(cd_static);
				if (tempList.contains(first_child_id)) {
					tempList.remove(first_child_id);
				}
			}
		}
		if (tempList != null && tempList.size() > 0) {
			Integer seclv_code = null;
			String seclv_name = "所有";
			if (StringUtils.hasLength((String) map.get("seclv_code"))) {
				seclv_code = Integer.parseInt((String) map.get("seclv_code"));
				seclv_name = userService.getSecLevelByCode(seclv_code).getSeclv_name();
			}
			for (String item : tempList) {
				finalList.add(new CDStatic(item, userService.getDeptNameByDeptId(item), seclv_code, seclv_name, 0));
			}
		}
		return finalList;
	}

	@Override
	public List<SysUsage> getUsageByUsageName(String usage_name) {
		return basicMapper.getUsageListByUsageName(usage_name);
	}

	@Override
	public SysBarcode getDefaultSysBarcode() {
		return basicMapper.getDefaultSysBarcode();
	}

	@Override
	public boolean isConfirmOpen(String module) {
		Integer start_use = basicMapper.getConfirmStartUse(module);
		if (start_use != null) {
			if (start_use == 1) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	@Override
	public String changeBarcode(String barcode) throws Exception {
		if (StringUtils.hasLength(barcode) && barcode.length() >= 34) {
			barcode = new String(new sun.misc.BASE64Decoder().decodeBuffer(barcode));
			logger.info("barcode is :" + barcode);
			return barcodeGeneratorImpl.getOriBarcode(barcode);
		} else {
			return barcode == null ? "" : barcode;
		}
	}

	@Override
	public List<SysConsole> getSysConsoleList(Map<String, Object> map) {
		logger.debug("getSysConsoleList");
		map.put("is_sealed", "N");
		return basicMapper.getAllSysConsoleList(map);
	}

	@Override
	public List<SysPrinter> getSysPrinterList(Map<String, Object> map) {
		logger.debug("getSysPrinterList");
		map.put("is_all", false);
		return basicMapper.getSysPrinterList(map);
	}

	@Override
	public void recoverConsoleByCode(String console_code) {
		basicMapper.recoverConsoleByCode(console_code);
	}

	@Override
	public List<SysConsole> getAllSysConsoleList(Map<String, Object> map) {
		return basicMapper.getAllSysConsoleList(map);
	}

	@Override
	public void recoverUsageByCode(String usage_code) {
		basicMapper.recoverUsageByCode(usage_code);
	}

	@Override
	public List<SysUsage> getSysUsageList(Map<String, Object> map) {
		return basicMapper.getSysUsageList(map);
	}

	@Override
	public void closeWrongJob(String job_code) {
		logger.debug("closeWrongJob");
		ProcessJob job = getProcessJobByCode(job_code);
		String instance_id = job.getInstance_id();
		JobTypeEnum jobType = job.getJobType();

		if (jobType == JobTypeEnum.DELAY || jobType == JobTypeEnum.DELAY_PAPER || jobType == JobTypeEnum.DELAY_CD
				|| jobType == JobTypeEnum.SEND || jobType == JobTypeEnum.SEND_PAPER || jobType == JobTypeEnum.SEND_CD
				|| jobType == JobTypeEnum.FILE || jobType == JobTypeEnum.FILE_PAPER || jobType == JobTypeEnum.FILE_CD
				|| jobType == JobTypeEnum.DESTROY || jobType == JobTypeEnum.DESTROY_PAPER
				|| jobType == JobTypeEnum.DESTROY_CD || jobType == JobTypeEnum.DESTROY_DEVICE) {// 载体闭环申请，重置载体状态
			List<EntityPaper> paperList = ledgerService.getPaperListByJobCode(job_code);
			List<EntityCD> cdList = ledgerService.getCDListByJobCode(job_code);
			List<EntityDevice> deviceList = ledgerService.getDeviceListByJobCode(job_code);
			if (paperList != null && paperList.size() != 0) {
				for (EntityPaper paper : paperList) {
					ledgerMapper.resetPaperState(Integer.toString(paper.getPaper_id()));
				}
			} else if (cdList != null && cdList.size() != 0) {
				for (EntityCD cd : cdList) {
					ledgerMapper.resetCDState(Integer.toString(cd.getCd_id()));
				}
			} else if (deviceList != null && deviceList.size() != 0) {
				for (EntityDevice device : deviceList) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("device_status", 0);
					map.put("device_code", device.getDevice_code());
					deviceMapper.updateDeviceStatus(map);
				}
			}
		} else if (jobType == JobTypeEnum.BORROW) {// 部门载体借阅，重置载体状态
			EventBorrow event = borrowService.getBorrowEventByJobCode(job_code);
			borrowService.resetEntityStatus(event.getEntity_type(), event.getBarcode());
		} else if (jobType == JobTypeEnum.TRANSFER) {// 流转，重置载体状态
			List<EventTransfer> transferList = transferService.getTransferEventByJobCode(job_code);
			for (EventTransfer transfer : transferList) {
				String entity_type = transfer.getEntity_type();
				String barcode = transfer.getBarcode();
				if (entity_type.equalsIgnoreCase("Paper")) {
					ledgerMapper.resetPaperStateByBarcode(barcode);
				} else if (entity_type.equalsIgnoreCase("CD")) {
					ledgerMapper.resetCDStateByBarcode(barcode);
				}
			}
		} else if (jobType == JobTypeEnum.SENDES_PAPER) {// 文件送销
			List<SendDestroyEvent> eventList = ledgerMapper.getSendDestroyEventListByJobCode(job_code);
			for (SendDestroyEvent event : eventList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("paper_barcode", event.getBarcode());
				map.put("paper_state", 1);
				ledgerMapper.updatePaperStateByBarcode(map);
			}
		} else if (jobType == JobTypeEnum.SENDES_CD) {// 光盘送销
			List<SendDestroyEvent> eventList = ledgerMapper.getSendDestroyEventListByJobCode(job_code);
			for (SendDestroyEvent event : eventList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cd_barcode", event.getBarcode());
				map.put("cd_state", 1);
				ledgerMapper.updateCDStateByBarcode(map);
			}
		}
		basicPrcManage.suspendProcessInstanceById(instance_id);
		job.setJob_status(ActivitiCons.APPLY_APPROVED_END);
		basicMapper.closeJobTask(job);
		clientService.delClientMsgByJobCode(job_code);

	}

	@Override
	public boolean isSelfApprove() {
		Integer is_selfApprove = basicMapper.getSelfApproveStartUse();
		if (is_selfApprove == 1) {
			return true;
		} else
			return false;
	}

	@Override
	public DeptAdminConfig getDeptAdminConfig(String user_iidd, String role_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", user_iidd);
		map.put("role_id", role_id);
		List<String> dept_id_list = basicMapper.getDeptIdListByUserRole(map);
		if (dept_id_list != null && dept_id_list.size() > 0) {
			String dept_ids = dept_id_list.toString().replace("[", "").replace("]", "").replace(" ", "");
			String dept_names = userService.getDeptNamesByIdList(dept_id_list);
			return new DeptAdminConfig(user_iidd, role_id, dept_ids, dept_names);
		} else {
			return new DeptAdminConfig(user_iidd, role_id, "", "");
		}
	}

	@Override
	public List<NewSecRole> getRoleListByUser(Map<String, String> map) {
		return basicMapper.getRoleListByUser(map);
	}

	@Override
	public void configDeptAdmin(DeptAdminConfig adminConfig) {
		String user_iidd = adminConfig.getUser_iidd();
		String role_id = adminConfig.getRole_id();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", user_iidd);
		map.put("role_id", role_id);
		basicMapper.delSecDeptAdminByUserRole(map);
		if (!adminConfig.getDept_ids().toString().equals("")) {
			SecDept dp = null;
			for (String dept_id : adminConfig.getDept_ids().split(",")) {
				dp = userMapper.getSecDeptById(dept_id);
				map.put("dept_id", dept_id);
				map.put("dept_cs", dp.getDept_cs());
				basicMapper.addSecDeptAdmin(map);
			}
		}
	}

	@Override
	public List<SecDept> getDeptAdminList(String user_iidd, String web_url) {
		String oper_code = basicMapper.getOperCodeByURL(web_url);
		List<String> role_ids = basicMapper.getRoleIdByOperCode(oper_code);
		List<String> dept_ids = new ArrayList<String>();
		for (String role_id : role_ids) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_iidd", user_iidd);
			map.put("role_id", role_id);
			List<String> dept_id_list = basicMapper.getDeptIdListByUserRole(map);
			dept_ids.addAll(dept_id_list);
		}
		// 去重
		HashSet<String> set = new HashSet<String>(dept_ids);
		dept_ids.clear();
		dept_ids.addAll(set);

		List<SecDept> dept_list = new ArrayList<SecDept>();
		for (String dept_id : dept_ids) {
			SecDept dept = userMapper.getSecDeptById(dept_id);
			dept_list.add(dept);
		}
		return dept_list;
	}

	@Override
	public List<String> getAdminDeptIdList(String user_iidd, String web_url) {
		String oper_code = basicMapper.getOperCodeByURL(web_url);
		List<String> role_ids = basicMapper.getRoleIdByOperCode(oper_code);
		List<String> dept_ids = new ArrayList<String>();
		for (String role_id : role_ids) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_iidd", user_iidd);
			map.put("role_id", role_id);
			List<String> dept_id_list = basicMapper.getDeptIdListByUserRole(map);
			dept_ids.addAll(dept_id_list);
		}
		// 去重
		HashSet<String> set = new HashSet<String>(dept_ids);
		dept_ids.clear();
		dept_ids.addAll(set);
		return dept_ids;
	}

	@Override
	public List<PaperStatic> getPaperStaticListBySeclv(Map<String, Object> map) {
		List<PaperStatic> finalList = new ArrayList<PaperStatic>();
		String dept_id = String.valueOf(map.get("dept_id"));
		List<SecLevel> seclvList = userService.getSecLevel();
		// 当前部门纸张统计
		for (SecLevel seclv : seclvList) {
			map.put("seclv_code", seclv.getSeclv_code());
			List<PaperStatic> paperStaticList = basicMapper.getPaperStaticListBySeclv(map);

			if (paperStaticList != null && paperStaticList.size() > 0) {
				finalList.add(new PaperStatic(dept_id, userService.getDeptNameByDeptId(dept_id), "", "", seclv
						.getSeclv_code(), seclv.getSeclv_name(), paperStaticList.get(0).getPage_count()));
			} else {
				finalList.add(new PaperStatic(dept_id, userService.getDeptNameByDeptId(dept_id), "", "", seclv
						.getSeclv_code(), seclv.getSeclv_name(), 0));
			}
		}
		// 获得一级子部门
		List<String> firstChildList = basicMapper.getFirstChildList(dept_id);
		for (String first_child_id : firstChildList) {
			map.put("dept_id", first_child_id);
			for (SecLevel seclv : seclvList) {
				map.put("seclv_code", seclv.getSeclv_code());
				// 获得每个一级子部门下的所有子部门纸张统计结果
				List<PaperStatic> childPaperStaticList = basicMapper.getAllChildPaperStaticListBySeclv(map);
				if (childPaperStaticList != null && childPaperStaticList.size() > 0) {
					finalList.add(new PaperStatic(first_child_id, userService.getDeptNameByDeptId(first_child_id), "",
							"", seclv.getSeclv_code(), seclv.getSeclv_name(), childPaperStaticList.get(0)
									.getPage_count()));
				} else {
					finalList.add(new PaperStatic(first_child_id, userService.getDeptNameByDeptId(first_child_id), "",
							"", seclv.getSeclv_code(), seclv.getSeclv_name(), 0));
				}
			}
		}
		return finalList;
	}

	@Override
	public List<PaperStatic> getPaperStaticListByColor(Map<String, Object> map) {
		List<PaperStatic> finalList = new ArrayList<PaperStatic>();
		String dept_id = String.valueOf(map.get("dept_id"));
		// 当前部门纸张统计
		for (int color = 1; color <= 2; color++) {
			map.put("color", color);
			List<PaperStatic> paperStaticList = basicMapper.getPaperStaticListByColor(map);
			PaperStatic paper_static = new PaperStatic();
			if (paperStaticList != null && paperStaticList.size() > 0) {
				paper_static = paperStaticList.get(0);
				paper_static.setColor_name(paper_static.getColor_name());
				finalList.add(paper_static);
			} else {
				paper_static.setDept_id(dept_id);
				paper_static.setDept_name(userService.getDeptNameByDeptId(dept_id));
				paper_static.setColor(color);
				paper_static.setColor_name(paper_static.getColor_name());
				paper_static.setPage_count(0);
				finalList.add(paper_static);
			}
		}
		// 获得一级子部门
		List<String> firstChildList = basicMapper.getFirstChildList(dept_id);
		for (String first_child_id : firstChildList) {
			map.put("dept_id", first_child_id);
			for (int color = 1; color <= 2; color++) {
				map.put("color", color);
				// 获得每个一级子部门下的所有子部门纸张统计结果
				List<PaperStatic> childPaperStaticList = basicMapper.getAllChildPaperStaticListByColor(map);
				PaperStatic paper_static = new PaperStatic();
				if (childPaperStaticList != null && childPaperStaticList.size() > 0) {
					paper_static.setDept_id(first_child_id);
					paper_static.setDept_name(userService.getDeptNameByDeptId(first_child_id));
					paper_static.setColor(color);
					paper_static.setColor_name(paper_static.getColor_name());
					paper_static.setPage_count(childPaperStaticList.get(0).getPage_count());
					finalList.add(paper_static);
				} else {
					paper_static.setDept_id(first_child_id);
					paper_static.setDept_name(userService.getDeptNameByDeptId(first_child_id));
					paper_static.setColor(color);
					paper_static.setColor_name(paper_static.getColor_name());
					paper_static.setPage_count(0);
					finalList.add(paper_static);
				}
			}
		}
		return finalList;
	}

	@Override
	public List<PaperStatic> getPaperStaticListByDouble(Map<String, Object> map) {
		List<PaperStatic> finalList = new ArrayList<PaperStatic>();
		String dept_id = String.valueOf(map.get("dept_id"));
		// 当前部门纸张统计
		for (int i = 1; i <= 2; i++) {
			List<Integer> print_double = new ArrayList<Integer>();
			if (i == 1) {// 单面print_double=1
				print_double.add(1);
			} else {// 双面print_double=2,3
				print_double.add(2);
				print_double.add(3);
			}
			map.put("print_double", print_double);
			List<PaperStatic> paperStaticList = basicMapper.getPaperStaticListByDouble(map);
			PaperStatic paper_static = new PaperStatic();
			if (paperStaticList != null && paperStaticList.size() == 1) {
				paper_static = paperStaticList.get(0);
				paper_static.setPrint_double(i);
				paper_static.setPrint_double_name(paper_static.getPrint_double_name());
				finalList.add(paper_static);
			} else if (paperStaticList != null && paperStaticList.size() == 2) {
				paper_static = paperStaticList.get(0);
				paper_static.setPrint_double(i);
				paper_static.setPrint_double_name(paper_static.getPrint_double_name());
				paper_static.setPage_count(paperStaticList.get(0).getPage_count()
						+ paperStaticList.get(1).getPage_count());
				finalList.add(paper_static);
			} else {
				paper_static.setDept_id(dept_id);
				paper_static.setDept_name(userService.getDeptNameByDeptId(dept_id));
				paper_static.setPrint_double(i);
				paper_static.setPrint_double_name(paper_static.getPrint_double_name());
				paper_static.setPage_count(0);
				finalList.add(paper_static);
			}
		}

		// 获得一级子部门
		List<String> firstChildList = basicMapper.getFirstChildList(dept_id);
		for (String first_child_id : firstChildList) {
			map.put("dept_id", first_child_id);
			for (int i = 1; i <= 2; i++) {
				List<Integer> print_double = new ArrayList<Integer>();
				if (i == 1) {// 单面print_double=1
					print_double.add(1);
				} else {// 双面print_double=2,3
					print_double.add(2);
					print_double.add(3);
				}
				map.put("print_double", print_double);
				// 获得每个一级子部门下的所有子部门纸张统计结果
				List<PaperStatic> childPaperStaticList = basicMapper.getAllChildPaperStaticListByDouble(map);
				PaperStatic paper_static = new PaperStatic();
				if (childPaperStaticList != null && childPaperStaticList.size() == 1) {
					paper_static.setDept_id(first_child_id);
					paper_static.setDept_name(userService.getDeptNameByDeptId(first_child_id));
					paper_static.setPrint_double(i);
					paper_static.setPrint_double_name(paper_static.getPrint_double_name());
					paper_static.setPage_count(childPaperStaticList.get(0).getPage_count());
					finalList.add(paper_static);
				} else if (childPaperStaticList != null && childPaperStaticList.size() == 2) {
					paper_static.setDept_id(first_child_id);
					paper_static.setDept_name(userService.getDeptNameByDeptId(first_child_id));
					paper_static.setPrint_double(i);
					paper_static.setPrint_double_name(paper_static.getPrint_double_name());
					paper_static.setPage_count(childPaperStaticList.get(0).getPage_count()
							+ childPaperStaticList.get(1).getPage_count());
					finalList.add(paper_static);
				} else {
					paper_static.setDept_id(first_child_id);
					paper_static.setDept_name(userService.getDeptNameByDeptId(first_child_id));
					paper_static.setPrint_double(i);
					paper_static.setPrint_double_name(paper_static.getPrint_double_name());
					paper_static.setPage_count(0);
					finalList.add(paper_static);
				}
			}
		}
		return finalList;
	}

	@Override
	public List<PaperStatic> getPaperStaticListBySize(Map<String, Object> map) {
		List<PaperStatic> finalList = new ArrayList<PaperStatic>();
		String dept_id = String.valueOf(map.get("dept_id"));
		List<String> sizeList = basicMapper.getPageSizeList();
		// 当前部门纸张统计
		for (String page_size : sizeList) {
			map.put("page_size", page_size);
			List<PaperStatic> paperStaticList = basicMapper.getPaperStaticListBySize(map);
			PaperStatic paper_static = new PaperStatic();
			if (paperStaticList != null && paperStaticList.size() > 0) {
				paper_static = paperStaticList.get(0);
				finalList.add(paper_static);
			} else {
				paper_static.setDept_id(dept_id);
				paper_static.setDept_name(userService.getDeptNameByDeptId(dept_id));
				paper_static.setPage_size(page_size);
				paper_static.setPage_count(0);
				finalList.add(paper_static);
			}
		}
		// 获得一级子部门
		List<String> firstChildList = basicMapper.getFirstChildList(dept_id);
		for (String first_child_id : firstChildList) {
			map.put("dept_id", first_child_id);
			for (String page_size : sizeList) {
				map.put("page_size", page_size);
				// 获得每个一级子部门下的所有子部门纸张统计结果
				List<PaperStatic> childPaperStaticList = basicMapper.getAllChildPaperStaticListBySize(map);
				PaperStatic paper_static = new PaperStatic();
				if (childPaperStaticList != null && childPaperStaticList.size() > 0) {
					paper_static = childPaperStaticList.get(0);
					paper_static.setDept_id(first_child_id);
					paper_static.setDept_name(userService.getDeptNameByDeptId(first_child_id));
					finalList.add(paper_static);
				} else {
					paper_static.setDept_id(first_child_id);
					paper_static.setDept_name(userService.getDeptNameByDeptId(first_child_id));
					paper_static.setPage_size(page_size);
					paper_static.setPage_count(0);
					finalList.add(paper_static);
				}
			}
		}
		return finalList;
	}

	@Override
	public List<PaperStatic> getPaperStaticListByAll(Map<String, Object> map) {
		List<PaperStatic> finalList = new ArrayList<PaperStatic>();
		String dept_id = String.valueOf(map.get("dept_id"));
		// 当前部门纸张统计
		List<String> sizeList = basicMapper.getPageSizeList();
		for (String page_size : sizeList) {
			for (int color = 1; color <= 2; color++) {
				for (int i = 1; i <= 2; i++) {
					List<Integer> print_double = new ArrayList<Integer>();
					if (i == 1) {// 单面print_double=1
						print_double.add(1);
					} else {// 双面print_double=2,3
						print_double.add(2);
						print_double.add(3);
					}
					map.put("page_size", page_size);
					map.put("color", color);
					map.put("print_double", print_double);
					List<PaperStatic> paperStaticList = basicMapper.getPaperStaticListByAll(map);
					PaperStatic paper_static = new PaperStatic();
					if (paperStaticList != null && paperStaticList.size() == 1) {
						paper_static = paperStaticList.get(0);
						paper_static.setPrint_double(i);
						paper_static.setPrint_double_name(paper_static.getPrint_double_name());
						paper_static.setColor_name(paper_static.getColor_name());
						finalList.add(paper_static);
					} else if (paperStaticList != null && paperStaticList.size() == 2) {
						paper_static = paperStaticList.get(0);
						paper_static.setPrint_double(i);
						paper_static.setPrint_double_name(paper_static.getPrint_double_name());
						paper_static.setColor_name(paper_static.getColor_name());
						paper_static.setPage_count(paperStaticList.get(0).getPage_count()
								+ paperStaticList.get(1).getPage_count());
						finalList.add(paper_static);
					} else {
						paper_static.setDept_id(dept_id);
						paper_static.setDept_name(userService.getDeptNameByDeptId(dept_id));
						paper_static.setPrint_double(i);
						paper_static.setPrint_double_name(paper_static.getPrint_double_name());
						paper_static.setColor(color);
						paper_static.setColor_name(paper_static.getColor_name());
						paper_static.setPage_size(page_size);
						paper_static.setPage_count(0);
						finalList.add(paper_static);
					}
				}
			}
		}
		// 获得一级子部门
		List<String> firstChildList = basicMapper.getFirstChildList(dept_id);
		for (String first_child_id : firstChildList) {
			map.put("dept_id", first_child_id);
			for (String page_size : sizeList) {
				for (int color = 1; color <= 2; color++) {
					for (int i = 1; i <= 2; i++) {
						List<Integer> print_double = new ArrayList<Integer>();
						if (i == 1) {// 单面print_double=1
							print_double.add(1);
						} else {// 双面print_double=2,3
							print_double.add(2);
							print_double.add(3);
						}
						map.put("page_size", page_size);
						map.put("color", color);
						map.put("print_double", print_double);
						// 获得每个一级子部门下的所有子部门纸张统计结果
						List<PaperStatic> childPaperStaticList = basicMapper.getAllChildPaperStaticListByAll(map);
						PaperStatic paper_static = new PaperStatic();
						if (childPaperStaticList != null && childPaperStaticList.size() == 1) {
							paper_static.setDept_id(first_child_id);
							paper_static.setDept_name(userService.getDeptNameByDeptId(first_child_id));
							paper_static.setPrint_double(i);
							paper_static.setPrint_double_name(paper_static.getPrint_double_name());
							paper_static.setColor(color);
							paper_static.setColor_name(paper_static.getColor_name());
							paper_static.setPage_size(page_size);
							paper_static.setPage_count(childPaperStaticList.get(0).getPage_count());
							finalList.add(paper_static);
						} else if (childPaperStaticList != null && childPaperStaticList.size() == 2) {
							paper_static.setDept_id(first_child_id);
							paper_static.setDept_name(userService.getDeptNameByDeptId(first_child_id));
							paper_static.setPrint_double(i);
							paper_static.setPrint_double_name(paper_static.getPrint_double_name());
							paper_static.setColor(color);
							paper_static.setColor_name(paper_static.getColor_name());
							paper_static.setPage_size(page_size);
							paper_static.setPage_count(childPaperStaticList.get(0).getPage_count()
									+ childPaperStaticList.get(1).getPage_count());
							finalList.add(paper_static);
						} else {
							paper_static.setDept_id(first_child_id);
							paper_static.setDept_name(userService.getDeptNameByDeptId(first_child_id));
							paper_static.setPrint_double(i);
							paper_static.setPrint_double_name(paper_static.getPrint_double_name());
							paper_static.setColor(color);
							paper_static.setColor_name(paper_static.getColor_name());
							paper_static.setPage_size(page_size);
							paper_static.setPage_count(0);
							finalList.add(paper_static);
						}
					}
				}
			}
		}
		return finalList;
	}

	@Override
	public List<String> getFirstChildList(String dept_id) {
		return basicMapper.getFirstChildList(dept_id);
	}

	@Override
	public List<String> getPageSizeList() {
		return basicMapper.getPageSizeList();
	}

	@Override
	public List<SysKeyword> getSysKeywordList(Map<String, Object> map) {
		return basicMapper.getSysKeywordList(map);
	}

	@Override
	public void addSysKeyword(SysKeyword keyword) {
		basicMapper.addSysKeyword(keyword);
	}

	@Override
	public boolean isKeywordExist(String keyword_content) {
		return basicMapper.getKeywordCount(keyword_content) > 0;
	}

	@Override
	public SysKeyword getKeywordByCode(String keyword_code) {
		return basicMapper.getKeywordByCode(keyword_code);
	}

	@Override
	public void updateKeyword(SysKeyword keyword) {
		basicMapper.updateKeyword(keyword);
	}

	@Override
	public String getKeywordCodeByContent(String keyword_content) {
		return basicMapper.getKeywordCodeByContent(keyword_content);
	}

	@Override
	public void delKeywordByCode(String keyword_code) {
		basicMapper.delKeywordByCode(keyword_code);
	}

	@Override
	public boolean isKeywordEnable() {
		int start = 0;
		start = getSysConfigItemValue(SysConfigItem.KEY_KEYWORD).getStartuse();
		if (start == 1) {
			return true;
		} else
			return false;
	}

	@Override
	public int getPrinterNumByConsole(String console_code) {
		return basicMapper.getPrinterNumByConsole(console_code);
	}

	@Override
	public Integer getKeywordCount() {
		return basicMapper.getAllKeywordCount();
	}

	@Override
	public void updatePaperExpireTime(Map<String, Object> map) {
		basicMapper.updatePaperExpireTime(map);
	}

	@Override
	public void updateCDExpireTime(Map<String, Object> map) {
		basicMapper.updateCDExpireTime(map);
	}

	@Override
	public List<SysUsage> getSysUsageListByModule(String module_code) {
		logger.debug("getSysUsageListByModule");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("is_sealed", "N");
		List<SysUsage> tempList = basicMapper.getSysUsageList(map);
		List<SysUsage> usageList = new ArrayList<SysUsage>();
		for (SysUsage usage : tempList) {
			if (usage.getModule_code().contains("," + module_code + ",")) {
				usageList.add(usage);
			}
		}
		return usageList;
	}

	@Override
	public int getUserNumByPrinter(String printer_code) {
		return basicMapper.getUserNumByPrinter(printer_code);
	}

	@Override
	public int getNumByUserPrinter(Map<String, Object> map) {
		return basicMapper.getNumByUserPrinter(map);
	}

	// @Override
	// public Map<String, String> getDomainMapping() {
	// return null;
	// }

	@Override
	public String getUserIDByExtCode(String user_code) {
		return basicMapper.getUserIDByExtCode(user_code);
	}

	@Override
	public void handlePaperSendDestroy(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType,
			String next_approver, List<String> barcodeList) throws Exception {
		logger.debug("handlePaperSendDestroy");
		ApproveProcess process = basicPrcManage.getApproveProcessByKey(dept_id, String.valueOf(seclv_code),
				jobType.getJobTypeCode(), "", true);
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
		// 开启流程
		basicPrcManage.addActivitiApply(job, process);
		// 把任务信息插入数据库
		basicMapper.addProcessJob(job);
		// 添加送销event
		int i = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paper_state", EntityStateEnum.STATE11.getKey());
		for (String barcode : barcodeList) {
			EntityPaper paper = ledgerService.getPaperByBarcode(barcode);
			String event_code = user_iidd + "-SENDES-" + System.currentTimeMillis() + i++;
			SendDestroyEvent event = new SendDestroyEvent();
			event.setUser_iidd(user_iidd);
			event.setDept_id(dept_id);
			event.setEvent_code(event_code);
			event.setBarcode(paper.getPaper_barcode());
			event.setSeclv_code(paper.getSeclv_code());
			event.setSeclv_name(paper.getSeclv_name());
			event.setApply_time(new Date());
			event.setEntity_type("PAPER");
			event.setJob_code(job_code);
			event.setSenddestroy_status(0);
			ledgerMapper.addSendDestroyEvent(event);
			map.put("paper_barcode", barcode);
			ledgerMapper.updatePaperStateByBarcode(map);
		}
		String user_name = userService.getUserNameByUserId(job.getUser_iidd());
		String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
		String detail = "提交文件送销申请";
		ProcessRecord record = new ProcessRecord(job.getJob_code(), job.getJobType(), job.getUser_iidd(), user_name,
				dept_name, detail, "请审批", new Date());
		activitiService.addProcessRecord(record);
		// 如果有审批流程，在消息表中添加审批消息
		if (process.getTotal_steps() != 0) {
			String message = dept_name + user_name + "有文件送销申请需要您审批";
			String oper_type = "SENDES_PAPER";
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(), message,
						new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}
	}

	@Override
	public void handleCDSendDestroy(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType,
			String next_approver, List<String> barcodeList) throws Exception {
		logger.debug("handleCDSendDestroy");
		ApproveProcess process = basicPrcManage.getApproveProcessByKey(dept_id, String.valueOf(seclv_code),
				jobType.getJobTypeCode(), "", true);
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
		// 开启流程
		basicPrcManage.addActivitiApply(job, process);
		// 把任务信息插入数据库
		basicMapper.addProcessJob(job);
		// 添加送销event
		int i = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cd_state", EntityStateEnum.STATE11.getKey());
		for (String barcode : barcodeList) {
			EntityCD cd = ledgerService.getCDByBarcode(barcode);
			String event_code = user_iidd + "-SENDES-" + System.currentTimeMillis() + i++;
			SendDestroyEvent event = new SendDestroyEvent();
			event.setUser_iidd(user_iidd);
			event.setDept_id(dept_id);
			event.setEvent_code(event_code);
			event.setBarcode(cd.getCd_barcode());
			event.setSeclv_code(cd.getSeclv_code());
			event.setSeclv_name(cd.getSeclv_name());
			event.setApply_time(new Date());
			event.setEntity_type("CD");
			event.setJob_code(job_code);
			event.setSenddestroy_status(0);
			ledgerMapper.addSendDestroyEvent(event);
			map.put("cd_barcode", barcode);
			ledgerMapper.updateCDStateByBarcode(map);
		}
		String user_name = userService.getUserNameByUserId(job.getUser_iidd());
		String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
		String detail = "提交光盘送销申请";
		ProcessRecord record = new ProcessRecord(job.getJob_code(), job.getJobType(), job.getUser_iidd(), user_name,
				dept_name, detail, "请审批", new Date());
		activitiService.addProcessRecord(record);
		// 如果有审批流程，在消息表中添加审批消息
		if (process.getTotal_steps() != 0) {
			String message = dept_name + user_name + "有光盘送销申请需要您审批";
			String oper_type = "SENDES_CD";
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(), message,
						new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}
	}

	public Integer getLastHSNumCount() throws Exception {
		int consoleCount = basicMapper.getAllConsoleCount();
		if (CopyrightLicense.hsNum == null) {
			return null;
		} else {
			int lastHSNumber = CopyrightLicense.hsNum - consoleCount;
			if (lastHSNumber > 0) {
				return lastHSNumber;
			} else {
				logger.warn("当前用户数[" + consoleCount + "]已经超出可用用户数[" + CopyrightLicense.hsNum + "]");
				throw new Exception("当前用户数[" + consoleCount + "]已经超出可用用户数[" + CopyrightLicense.hsNum + "]");
			}
		}
	}

	@Override
	public String buildCETCTwoDimenosionalBarcode(String parent_dept_name, String entity_barcode,
			String curr_dept_name, String duty_name, String type, String file_name, String seclv_name, String src_from,
			String apply_time) throws Exception {
		StringBuffer sb = new StringBuffer();
		// 版本标识 14
		sb.append(PropertyUtil.getUnitBarcode());
		sb.append("^");
		// 条码编号
		sb.append(entity_barcode);
		sb.append("^");
		// 成员单位
		sb.append(new String(parent_dept_name.getBytes("ISO-8859-1"), "UTF-8"));
		sb.append("^");
		// 部门
		sb.append(curr_dept_name);
		sb.append("^");
		// 申请人
		sb.append(duty_name);
		sb.append("^");
		// 载体类型 含纸介质，光盘，磁介质，其他介质
		sb.append(type);
		sb.append("^");
		// 文件名称
		if (file_name.length() > 50) {
			file_name = file_name.substring(0, 50) + "...等";
		}
		sb.append(file_name);
		sb.append("^");
		// 密级4 绝密，机密，秘密，内部，非密
		sb.append(seclv_name);
		sb.append("^");
		// 制作时间
		sb.append(apply_time);
		sb.append("^");
		// 来源
		sb.append(src_from);
		sb.append("^|");
		return sb.toString();
	}

	@Override
	public void handleSubmitAllPaperJob(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType,
			String next_approver, List<EntityPaper> paperList, String usage_code) throws Exception {
		logger.debug("handleSubmitAllPaperJob");
		ApproveProcess process = getApproveProcessByKey(dept_id, seclv_code, jobType.getJobTypeCode(), usage_code);
		String job_status = null;
		if (process.getTotal_steps() == 0) {
			job_status = ActivitiCons.APPLY_APPROVED_PASS;
		} else {
			job_status = ActivitiCons.APPLY_APPROVED_DEFAULT;
		}
		String next_approver_name = getApproverName(next_approver);
		String job_code = user_iidd + "-" + jobType.getJobTypeCode() + "-" + System.currentTimeMillis();
		ProcessJob job = new ProcessJob(job_code, user_iidd, dept_id, seclv_code, jobType, new Date(), job_status,
				next_approver, next_approver_name, process.getProcess_id());
		// 开启流程
		basicPrcManage.addActivitiApply(job, process);
		// 把任务信息插入数据库
		basicMapper.addProcessJob(job);
		int i = 0;
		for (i = 0; i < paperList.size(); i++) {
			addEntityJobRel(paperList.get(i).getPaper_barcode() + ",", job, jobType);
		}
		String user_name = userService.getUserNameByUserId(job.getUser_iidd());
		String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
		String detail = "提交" + jobType.getJobTypeName() + "申请";
		ProcessRecord record = new ProcessRecord(job.getJob_code(), job.getJobType(), job.getUser_iidd(), user_name,
				dept_name, detail, "请审批", new Date());
		activitiService.addProcessRecord(record);
		if (process.getTotal_steps() != 0) {
			String message = "";
			String oper_type = "";
			if (jobType.getJobTypeCode().contains("_PAPER")) {
				message = dept_name + user_name + "有" + jobType.getJobTypeName() + "申请需要您审批";
				oper_type = jobType.getJobTypeCode();
			} else {
				message = dept_name + user_name + "有文件" + jobType.getJobTypeName() + "申请需要您审批";
				oper_type = jobType.getJobTypeCode() + "_PAPER";
			}
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(), message,
						new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}

	}

	@Override
	public void handleSubmitAllCDJob(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType,
			String next_approver, List<EntityCD> cdList, String usage_code) throws Exception {
		logger.debug("handleSubmitAllCDJob");
		ApproveProcess process = getApproveProcessByKey(dept_id, seclv_code, jobType.getJobTypeCode(), usage_code);
		String job_status = null;
		if (process.getTotal_steps() == 0) {
			job_status = ActivitiCons.APPLY_APPROVED_PASS;
		} else {
			job_status = ActivitiCons.APPLY_APPROVED_DEFAULT;
		}
		String next_approver_name = getApproverName(next_approver);
		String job_code = user_iidd + "-" + jobType.getJobTypeCode() + "-" + System.currentTimeMillis();
		ProcessJob job = new ProcessJob(job_code, user_iidd, dept_id, seclv_code, jobType, new Date(), job_status,
				next_approver, next_approver_name, process.getProcess_id());
		// 开启流程
		basicPrcManage.addActivitiApply(job, process);
		// 把任务信息插入数据库
		basicMapper.addProcessJob(job);

		int i = 0;
		for (i = 0; i < cdList.size(); i++) {
			addEntityCDJobRel(cdList.get(i).getCd_barcode() + ",", job, jobType);
		}
		String user_name = userService.getUserNameByUserId(job.getUser_iidd());
		String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
		String detail = "提交" + jobType.getJobTypeName() + "申请";
		ProcessRecord record = new ProcessRecord(job.getJob_code(), job.getJobType(), job.getUser_iidd(), user_name,
				dept_name, detail, "请审批", new Date());
		activitiService.addProcessRecord(record);
		if (process.getTotal_steps() != 0) {
			String message = "";
			String oper_type = "";
			if (jobType.getJobTypeCode().contains("_CD")) {
				message = dept_name + user_name + "有" + jobType.getJobTypeName() + "申请需要您审批";
				oper_type = jobType.getJobTypeCode();
			} else {
				message = dept_name + user_name + "有光盘" + jobType.getJobTypeName() + "申请需要您审批";
				oper_type = jobType.getJobTypeCode() + "_CD";
			}
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(), message,
						new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}

	}

	@Override
	public int getCountbyApproveProxy(Map<String, Object> map) {
		return basicMapper.getCountbyApproveProxy(map);
	}

	@Override
	public List<SysProxy> getApproveProxy(Map<String, Object> map) {
		return basicMapper.getApproveProxy(map);
	}

	@Override
	public List<SysConfigItem> getSysConfigItems(String item_type) {
		return basicMapper.getSysConfigItems(item_type);
	}

	@Override
	public void addUserRoleRel(SecRoleUser secroleuser) {
		basicMapper.addUserRoleRel(secroleuser);
	}

	@Override
	public int checkRole(Map<String, Object> map) {
		return basicMapper.checkRole(map);
	}

	@Override
	public String createCETCEntityBarcode(String child_dept_id) throws Exception {
		return createEntityBarcode("DEVICE");
	}

	@Override
	public DeptAdminConfig getPartTimeDeptListByUserId(String user_iidd) {
		List<String> dept_id_list = basicMapper.getPartTimeDeptListByUserId(user_iidd);
		if (dept_id_list != null && dept_id_list.size() > 0) {
			String dept_ids = dept_id_list.toString().replace("[", "").replace("]", "").replace(" ", "");
			String dept_names = userService.getDeptNamesByIdList(dept_id_list);
			return new DeptAdminConfig(user_iidd, "", dept_ids, dept_names);
		} else {
			return new DeptAdminConfig(user_iidd, "", "", "");
		}
	}

	@Override
	public void configPartTimeDept(DeptAdminConfig adminConfig) {
		String user_iidd = adminConfig.getUser_iidd();
		basicMapper.delPartTimeDeptByUserId(user_iidd);
		if (!adminConfig.getDept_ids().toString().equals("")) {
			SecDept dp = null;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_iidd", user_iidd);
			for (String dept_id : adminConfig.getDept_ids().split(",")) {
				dp = userMapper.getSecDeptById(dept_id);
				map.put("dept_id", dept_id);
				map.put("dept_cs", dp.getDept_cs());
				// 兼职审批配置与管理员分配部门配置公用一张表，该表角色字段非空，而兼职审批暂时不用，因此默认插入角色为审批角色10
				basicMapper.addPartTimeDept(map);
			}
		}

	}

	@Override
	public void addSpecialPaperType(SysUsage usage) {
		logger.debug("addSpecialPaperType:" + usage.getUsage_code());
		basicMapper.addSpecialPaperType(usage);

	}

	@Override
	public List<SysUsage> getSpecialPaperTypeList(Map<String, Object> map) {
		logger.debug("getSpecialPaperTypeList1");
		return basicMapper.getSpecialPaperTypeList(map);
	}

	@Override
	public List<SysUsage> getSpecialPaperTypeList() {
		logger.debug("getSpecialPaperTypeList2");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("is_sealed", "N");
		map.put("type", "N");
		List<SysUsage> tempList = basicMapper.getSpecialPaperTypeList(map);
		return tempList;
	}

	@Override
	public String getSeclvNameByCode(int seclv_code) {
		logger.debug("getSeclvNameByCode:" + seclv_code);
		return basicMapper.getSeclvNameByCode(seclv_code);
	}

	@Override
	public int getJobSize(Map<String, Object> map) {
		logger.debug("getJobSize");
		return basicMapper.getJobSize(map);
	}

	@Override
	public String getBarcodeCompare(String barcode) {
		logger.debug("getBarcodeCompare");
		return basicMapper.getBarcodeCompare(barcode);
	}

	@Override
	public String createNewCETCBarcode(String dept_id, Integer copies) throws Exception {
		String curYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		String barcode = getCreateBarcode();
		String unitcode = PropertyUtil.getUnitCode();
		String count = "000";
		String ext_code = "";
		if (dept_id != null && dept_id != "") {
			ext_code = basicMapper.getDeptExtCodeByDeptId(dept_id);
			if (ext_code != null && ext_code != "" && ext_code.length() == 3) {
				unitcode = ext_code;
			}
		}

		/*
		 * if (copies < 10) { count = "00" + copies.toString(); } else if (copies < 100) { count = "0" +
		 * copies.toString(); } else if (copies < 1000) { count = copies.toString(); } else { count = "999"; }
		 */

		logger.info("barcode in BasicServiceImpl:" + barcode + ":" + ext_code);
		return "E" + unitcode + "20" + curYear.substring(2) + fillStringWithZero(barcode, 7) + count;
	}

	@Override
	public String getCreateBarcode() {
		Map<String, Object> map = new HashMap<String, Object>();
		basicMapper.getCreateBarcode(map);
		String barcode = String.valueOf(map.get("outValue"));
		String prefix = "0000000";
		return prefix.substring(barcode.length()).concat(barcode);
	}

	public int getChineseCount(String str) {
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			if (isChinese(str.charAt(i))) {
				count++;
			}
		}
		return count;
	}

	public boolean isChinese(char c) {
		int v = c;
		return (v >= 19968 && v < 171941);
	}

	private String fillStringWithZero(String str, int total_length) {
		if (null == str || "".equals(str)) {
			return INIT_STR.substring(0, total_length);
		}
		int chineseCount = getChineseCount(str);
		if (str.length() + chineseCount >= total_length) {
			return str.substring(0, str.length());
		} else {
			return INIT_STR.substring(0, total_length - str.length() - chineseCount) + str;
		}

	}

	@Override
	public void updateJobProcessFileRead(Map<String, Object> map) {
		basicMapper.updateJobProcessFileRead(map);
	}

	@Override
	public int getHandledJobByUserIdSize(String user_iidd, String type, String user_name, Integer seclv_code,
			String jobType_code) {
		int jobSize = 0;
		List<String> instanceIdList = new ArrayList<String>();
		if (StringUtils.hasLength(jobType_code)) {
			instanceIdList = basicPrcManage.getApprovedInstanceIdList(user_iidd, jobType_code);
		} else {
			List<HistoricTaskInstance> hiTaskList = basicPrcManage.getApprovedInstanceIdList(user_iidd);
			for (HistoricTaskInstance item : hiTaskList) {
				if (item.getDescription().equals(JobTypeEnum.DESTROY.getJobTypeCode())
						|| item.getDescription().equals(JobTypeEnum.DESTROY_CD.getJobTypeCode())
						|| item.getDescription().equals(JobTypeEnum.DESTROY_PAPER.getJobTypeCode())
						|| item.getDescription().equals(JobTypeEnum.DESTROY_DEVICE.getJobTypeCode())
						|| item.getDescription().equals(JobTypeEnum.DESTROY_PAPER_BYSELF.getJobTypeCode())
						|| item.getDescription().equals(JobTypeEnum.SEND.getJobTypeCode())
						|| item.getDescription().equals(JobTypeEnum.SEND_PAPER.getJobTypeCode())
						|| item.getDescription().equals(JobTypeEnum.SEND_CD.getJobTypeCode())
						|| item.getDescription().equals(JobTypeEnum.FILE.getJobTypeCode())
						|| item.getDescription().equals(JobTypeEnum.FILE_PAPER.getJobTypeCode())
						|| item.getDescription().equals(JobTypeEnum.FILE_CD.getJobTypeCode())
						|| item.getDescription().equals(JobTypeEnum.DELAY.getJobTypeCode())
						|| item.getDescription().equals(JobTypeEnum.DELAY_PAPER.getJobTypeCode())
						|| item.getDescription().equals(JobTypeEnum.DELAY_CD.getJobTypeCode())
						|| item.getDescription().equals(JobTypeEnum.FILECD_DESTROY.getJobTypeCode())
						|| item.getDescription().equals(JobTypeEnum.FILE_DESTROY.getJobTypeCode())
						|| item.getDescription().equals(JobTypeEnum.MERGE_ENTITY.getJobTypeCode())) {

					instanceIdList.add(item.getProcessInstanceId());
				}
			}

		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (instanceIdList != null && !instanceIdList.isEmpty() && instanceIdList.size() > 0) {
			map.put("type", type);
			map.put("seclv_code", seclv_code);
			map.put("user_name", user_name);
			map.put("instanceIdList", instanceIdList);
			jobSize = basicMapper.getHandleJobListByEntityInstanceIdSize(map);
		}
		return jobSize;
	}

	@Override
	public List<ProcessJob> getHandledJobByUserId(String user_iidd, String type, String user_name, Integer seclv_code,
			String jobType_code, RowBounds rbs) {
		List<ProcessJob> jobList = null;
		List<String> instanceIdList = new ArrayList<String>();
		if (StringUtils.hasLength(jobType_code)) {
			instanceIdList = basicPrcManage.getApprovedInstanceIdList(user_iidd, jobType_code);
		} else {

			List<HistoricTaskInstance> hiTaskList = basicPrcManage.getApprovedInstanceIdList(user_iidd);
			for (HistoricTaskInstance item : hiTaskList) {
				if (item.getDescription().equals(JobTypeEnum.DESTROY.getJobTypeCode())
						|| item.getDescription().equals(JobTypeEnum.DESTROY_CD.getJobTypeCode())
						|| item.getDescription().equals(JobTypeEnum.DESTROY_PAPER.getJobTypeCode())
						|| item.getDescription().equals(JobTypeEnum.DESTROY_DEVICE.getJobTypeCode())
						|| item.getDescription().equals(JobTypeEnum.DESTROY_PAPER_BYSELF.getJobTypeCode())
						|| item.getDescription().equals(JobTypeEnum.SEND.getJobTypeCode())
						|| item.getDescription().equals(JobTypeEnum.SEND_PAPER.getJobTypeCode())
						|| item.getDescription().equals(JobTypeEnum.SEND_CD.getJobTypeCode())
						|| item.getDescription().equals(JobTypeEnum.FILE.getJobTypeCode())
						|| item.getDescription().equals(JobTypeEnum.FILE_PAPER.getJobTypeCode())
						|| item.getDescription().equals(JobTypeEnum.FILE_CD.getJobTypeCode())
						|| item.getDescription().equals(JobTypeEnum.DELAY.getJobTypeCode())
						|| item.getDescription().equals(JobTypeEnum.DELAY_PAPER.getJobTypeCode())
						|| item.getDescription().equals(JobTypeEnum.DELAY_CD.getJobTypeCode())
						|| item.getDescription().equals(JobTypeEnum.FILECD_DESTROY.getJobTypeCode())
						|| item.getDescription().equals(JobTypeEnum.FILE_DESTROY.getJobTypeCode())
						|| item.getDescription().equals(JobTypeEnum.MERGE_ENTITY.getJobTypeCode())) {

					instanceIdList.add(item.getProcessInstanceId());
				}
			}

		}
		if (instanceIdList != null && !instanceIdList.isEmpty() && instanceIdList.size() > 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("type", type);
			map.put("seclv_code", seclv_code);
			map.put("user_name", user_name);
			map.put("instanceIdList", instanceIdList);
			jobList = basicMapper.getHandleJobListByEntityInstanceId(map, rbs);
		}
		return jobList;
	}

	@Override
	public String getPrintProxyUserIdByJobcode(String job_code) {
		logger.debug("getPrintProxyUserIdByJobcode");
		return basicMapper.getPrintProxyUserIdByJobcode(job_code);
	}

	@Override
	public String getBurnProxyUserIdByJobcode(String job_code) {
		logger.debug("getBurnProxyUserIdByJobcode");
		return basicMapper.getBurnProxyUserIdByJobcode(job_code);
	}

	@Override
	public void handleReplacePaperJob(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType,
			String next_approver, String barcodes, String output_dept_name, String output_user_name, String reason,
			String supervise_user_iidd) throws Exception {
		logger.debug("handleReplacePaperJob");
		ApproveProcess process = getApproveProcessByKey(dept_id, seclv_code, jobType.getJobTypeCode(), "");
		String job_status = null;
		if (process.getTotal_steps() == 0) {
			job_status = ActivitiCons.APPLY_APPROVED_PASS;
		} else {
			job_status = ActivitiCons.APPLY_APPROVED_DEFAULT;
		}
		String next_approver_name = getApproverName(next_approver);
		String job_code = user_iidd + "-" + jobType.getJobTypeCode() + "-" + System.currentTimeMillis();
		ProcessJob job = new ProcessJob(job_code, user_iidd, dept_id, seclv_code, jobType, new Date(), job_status,
				next_approver, next_approver_name, process.getProcess_id());

		job.setAccept_user_iidd(supervise_user_iidd);// 借用该参数临时存储监销人属性
		// 开启流程
		basicPrcManage.addActivitiApply(job, process);
		// 把任务信息插入数据库
		basicMapper.addProcessJob(job);
		// 更新作业信息
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("barcodes", barcodes.split(","));
		map.put("job_code", job.getJob_code());
		map.put("supervise_user_iidd", job.getAccept_user_iidd());
		basicMapper.updateReplacePageEntityStatus(map);

		String user_name = userService.getUserNameByUserId(job.getUser_iidd());
		String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
		String detail = "提交替换页" + jobType.getJobTypeName() + "申请";
		ProcessRecord record = new ProcessRecord(job.getJob_code(), job.getJobType(), job.getUser_iidd(), user_name,
				dept_name, detail, "请审批", new Date());
		activitiService.addProcessRecord(record);
		if (process.getTotal_steps() != 0) {
			String message = "";
			String oper_type = "";
			if (jobType.getJobTypeCode().contains("_PAPER")) {
				message = dept_name + user_name + "有替换页" + jobType.getJobTypeName() + "申请需要您审批";
				oper_type = jobType.getJobTypeCode();
			}
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(), message,
						new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}
	}

	@Override
	public List<SecUser> getSuperviseUserList() {
		logger.debug("getSuperviseUserList");
		return basicMapper.getSuperviseUserList();
	}

	@Override
	public List<SecUser> getOutputUndertakersByDeptId(Map<String, Object> map) {
		logger.debug("getOutputUndertakersByDeptId");
		return basicMapper.getOutputUndertakersByDeptId(map);
	}

	@Override
	public void handleSendPaperJob(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType,
			String next_approver, String barcodes, String output_dept_name, String output_user_name, String reason,
			String send_way, String carryout_user_iidds, String carryout_user_names, String output_undertaker)
			throws Exception {
		logger.debug("handleSendPaperJob");
		ApproveProcess process = getApproveProcessByKey(dept_id, seclv_code, jobType.getJobTypeCode(), "");
		String job_status = null;
		if (process.getTotal_steps() == 0) {
			job_status = ActivitiCons.APPLY_APPROVED_PASS;
		} else {
			job_status = ActivitiCons.APPLY_APPROVED_DEFAULT;
		}
		String next_approver_name = getApproverName(next_approver);
		String job_code = user_iidd + "-" + jobType.getJobTypeCode() + "-" + System.currentTimeMillis();
		ProcessJob job = new ProcessJob(job_code, user_iidd, dept_id, seclv_code, jobType, new Date(), job_status,
				next_approver, next_approver_name, process.getProcess_id(), send_way, carryout_user_iidds,
				carryout_user_names, output_undertaker);
		job.setOutput_dept_name(output_dept_name);
		job.setOutput_user_name(output_user_name);
		job.setIs_receipt("Y");

		// 开启流程
		basicPrcManage.addActivitiApply(job, process);
		// 把任务信息插入数据库
		basicMapper.addProcessJob(job);
		// 更新作业信息
		addEntityJobRel(barcodes, job, jobType);
		String user_name = userService.getUserNameByUserId(job.getUser_iidd());
		String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
		String detail = "提交" + jobType.getJobTypeName() + "申请";
		ProcessRecord record = new ProcessRecord(job.getJob_code(), job.getJobType(), job.getUser_iidd(), user_name,
				dept_name, detail, "请审批", new Date());
		activitiService.addProcessRecord(record);
		if (process.getTotal_steps() != 0) {
			String message = "";
			String oper_type = "";
			if (jobType.getJobTypeCode().contains("_PAPER")) {
				message = dept_name + user_name + "有" + jobType.getJobTypeName() + "申请需要您审批";
				oper_type = jobType.getJobTypeCode();
			} else {
				message = dept_name + user_name + "有文件" + jobType.getJobTypeName() + "申请需要您审批";
				oper_type = jobType.getJobTypeCode() + "_PAPER";
			}
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(), message,
						new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		} else {
			// 无审批流程时，外发状态置为外发中
			for (String barcode : barcodes.split(",")) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("paper_barcode", barcode);
				map.put("paper_state", 16);// 外发中
				ledgerMapper.updatePaperStateByBarcode(map);
			}
		}
	}

	@Override
	public void handleSendCDJob(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType,
			String next_approver, String barcodes, String output_dept_name, String output_user_name, String reason,
			String send_way, String carryout_user_iidds, String carryout_user_names, String output_undertaker)
			throws Exception {
		logger.debug("handleSendCDJob");
		ApproveProcess process = getApproveProcessByKey(dept_id, seclv_code, jobType.getJobTypeCode(), "");
		String job_status = null;
		if (process.getTotal_steps() == 0) {
			job_status = ActivitiCons.APPLY_APPROVED_PASS;
		} else {
			job_status = ActivitiCons.APPLY_APPROVED_DEFAULT;
		}
		String next_approver_name = getApproverName(next_approver);
		String job_code = user_iidd + "-" + jobType.getJobTypeCode() + "-" + System.currentTimeMillis();
		ProcessJob job = new ProcessJob(job_code, user_iidd, dept_id, seclv_code, jobType, new Date(), job_status,
				next_approver, next_approver_name, process.getProcess_id(), send_way, carryout_user_iidds,
				carryout_user_names, output_undertaker);
		job.setOutput_dept_name(output_dept_name);
		job.setOutput_user_name(output_user_name);
		job.setIs_receipt("Y");
		//
		basicPrcManage.addActivitiApply(job, process);
		// 把任务信息插入数据库
		basicMapper.addProcessJob(job);
		// 更新作业信息
		addEntityCDJobRel(barcodes, job, jobType);
		String user_name = userService.getUserNameByUserId(job.getUser_iidd());
		String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
		String detail = "提交" + jobType.getJobTypeName() + "申请";
		ProcessRecord record = new ProcessRecord(job.getJob_code(), job.getJobType(), job.getUser_iidd(), user_name,
				dept_name, detail, "请审批", new Date());
		activitiService.addProcessRecord(record);
		if (process.getTotal_steps() != 0) {
			String message = "";
			String oper_type = "";
			if (jobType.getJobTypeCode().contains("_CD")) {
				message = dept_name + user_name + "有" + jobType.getJobTypeName() + "申请需要您审批";
				oper_type = jobType.getJobTypeCode();
			} else {
				message = dept_name + user_name + "有光盘" + jobType.getJobTypeName() + "申请需要您审批";
				oper_type = jobType.getJobTypeCode() + "_CD";
			}
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(), message,
						new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		} else {
			// 无审批流程时，外发状态置为外发中
			for (String barcode : barcodes.split(",")) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cd_barcode", barcode);
				map.put("cd_state", 16);// 外发中
				ledgerMapper.updateCDStateByBarcode(map);
			}
		}
	}

	@Override
	public List<ProcessJob> getApprovedJobByUserIdPrint(String user_iidd,
			JobTypeEnum jobType, String user_name, Integer seclv_code,
			String file_scope, String seclv_accord, String secret_time)
			throws Exception {
		List<String> instanceIdList = new ArrayList<String>();
		List<String> tempList = new ArrayList<String>();
		if (jobType.getJobTypeCode().equals("CARRYOUT")) {
			tempList = basicPrcManage.getApprovedInstanceIdList(user_iidd, JobTypeEnum.CARRYOUT_CD.getJobTypeCode());
			instanceIdList.addAll(tempList);
			tempList = basicPrcManage.getApprovedInstanceIdList(user_iidd, JobTypeEnum.CARRYOUT_PAPER.getJobTypeCode());
			instanceIdList.addAll(tempList);
		} else {
			instanceIdList = basicPrcManage.getApprovedInstanceIdList(user_iidd, jobType.getJobTypeCode());
		}
		if (instanceIdList != null && instanceIdList.size() > 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("seclv_code", seclv_code);
			map.put("user_name", user_name);
			map.put("instanceIds", instanceIdList);
			map.put("file_scope", file_scope);
			map.put("secret_time", secret_time);
			map.put("seclv_accord", seclv_accord);
			return basicMapper.getApprovedJobByInstanceIds(map);
		}
		return Collections.emptyList();
	}

}