package hdsec.web.project.print.service;

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
import hdsec.web.project.copy.model.CopyEvent;
import hdsec.web.project.copy.service.CopyService;
import hdsec.web.project.enter.mapper.EnterMapper;
import hdsec.web.project.ledger.mapper.LedgerMapper;
import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.print.mapper.PrintMapper;
import hdsec.web.project.print.model.FixAccording;
import hdsec.web.project.print.model.OaPrintEvent;
import hdsec.web.project.print.model.PrintEvent;
import hdsec.web.project.print.model.RefFixAccordingDept;
import hdsec.web.project.print.model.RiskKeywordsPrint;
import hdsec.web.project.print.model.SysPrinter;
import hdsec.web.project.user.service.UserService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

public class PrintServiceImpl implements PrintService {
	private final Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private PrintMapper printMapper;
	@Resource
	private BasicService basicService;
	@Resource
	private BasicPrcManage basicPrcManage;
	@Resource
	private BasicMapper basicMapper;
	@Resource
	private UserService userService;
	@Resource
	private ActivitiService activitiService;
	@Resource
	private LedgerMapper ledgerMapper;
	@Resource
	private EnterMapper enterMapper;
	@Resource
	protected CopyService copyService;

	@Override
	public List<SysPrinter> getPrinterList(Map<String, Object> map) {
		return printMapper.getPrinterList(map);
	}

	@Override
	public List<PrintEvent> getPrintEventList(Map<String, Object> map) {
		return printMapper.getPrintEventList(map);
	}

	@Override
	public PrintEvent getPrintEventByPrintId(String id) {
		return printMapper.getPrintEventByPrintId(id);
	}

	@Override
	public PrintEvent getPrintEventByPrintCode(String event_code) {
		return printMapper.getPrintEventByPrintCode(event_code);
	}

	@Override
	public String getJobCodeByEventCode(String event_code) {
		return printMapper.getJobCodeByEventCode(event_code);
	}

	@Override
	public void delPrintEventByPringId(String id) {
		printMapper.delPrintEventByPringId(id);
	}

	@Override
	public void delPrintEventByPrintCode(String event_code) {
		printMapper.delPrintEventByPrintCode(event_code);
	}

	@Override
	public void updatePrintEvent(PrintEvent event) {
		printMapper.updatePrintEvent(event);
	}

	@Override
	public void updatePrintEvent1(PrintEvent event) {
		printMapper.updatePrintEvent1(event);
	}

	@Override
	public int cancelPrintEventByEventCode(String event_code) {
		// 先查询出该event对应的job_code
		PrintEvent event = printMapper.getPrintEventByPrintCode(event_code);
		String job_code = event.getJob_code();
		// 把event对应的job_code设置为null,取消审批
		printMapper.cancelPrintEventByEventCode(event_code);
		// 复印合并流程，撤销时同步删除复印作业，2017.6.7
		if (event_code != null && event_code != "") {
			copyService.cancelCopyEventByPrintEventCode(event_code);
		}
		// 如果该job_code对应的event数量为空，表示该任务下挂载的作业已经都被取消了，则取消该任务
		if (StringUtils.hasLength(job_code) && printMapper.getPrintEventCountByJobCode(job_code) == 0) {
			basicService.cancelJob(job_code, event.getJobType().getJobTypeCode());
			return 1;
		}
		return 0;
	}

	@Override
	public void configDeptPrinter(String dept_id, String printer_code) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dept_id", dept_id);
		map.put("printer_code", printer_code);
		printMapper.delDeptPrinter(dept_id);
		printMapper.addDeptPrinter(map);
	}

	@Override
	public String getDeptPrinter(String dept_id) {
		return printMapper.getDeptPrinter(dept_id);

	}

	@Override
	public void addProcessJob(String user_iidd, String dept_id, Integer seclv_code, String cycle_type, String period,
			JobTypeEnum jobType, String next_approver, String output_dept_name, String output_user_name,
			String comment, List<String> eventIdList, String usage_code, String project_code,
			Map<String, String> fileSeclvMap, Map<String, String> filePrintCountMap, Map<String, String> fileColorMap,
			Map<String, String> filePrintDoubleMap, Map<String, String> fileTitleMap, Date start_time) throws Exception {
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
		ProcessJob job = new ProcessJob(job_code, user_iidd, dept_id, seclv_code, jobType, start_time, job_status,
				next_approver, next_approver_name, process.getProcess_id());
		job.setOutput_dept_name(output_dept_name);
		job.setOutput_user_name(output_user_name);
		job.setComment(comment);
		// 开启流程
		basicPrcManage.addActivitiApply(job, process);
		// 把任务信息插入数据库
		basicMapper.addProcessJob(job);
		// 更新作业信息
		if (jobType.getJobTypeCode().equals("SPECIAL_PRINT")) {
			addOaEventJobRel(eventIdList, job, usage_code, project_code, seclv_code);
		} else {
			addEventJobRel(user_iidd, eventIdList, cycle_type, period, seclv_code, job, usage_code, project_code,
					comment, fileSeclvMap, filePrintCountMap, fileColorMap, filePrintDoubleMap, fileTitleMap,
					start_time);
		}
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
			} else if (jobType.getJobTypeCode().startsWith("SPECIAL_PRINT_")) {
				oper_type = "PRINT";
			} else {
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
	public void addProcessJob(String user_iidd, String dept_id, Integer seclv_code, String cycle_type, String period,
			JobTypeEnum jobType, String next_approver, String output_dept_name, String output_user_name,
			String comment, List<String> eventIdList, String usage_code, String project_code,
			Map<String, String> fileSeclvMap, Map<String, String> filePrintCountMap, Map<String, String> fileColorMap,
			Map<String, String> filePrintDoubleMap, Map<String, String> fileTitleMap, Date start_time, Integer copy_num,
			String send_way,String output_undertaker,String carryout_user_names)
			throws Exception {
		logger.debug("addProcessJob extend copy");
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
		ProcessJob job = new ProcessJob(job_code, user_iidd, dept_id, seclv_code, jobType, start_time, job_status,
				next_approver, next_approver_name, process.getProcess_id());
		job.setOutput_dept_name(output_dept_name);
		job.setOutput_user_name(output_user_name);
		job.setComment(comment);
		job.setSend_way(send_way);
		job.setOutput_undertaker(output_undertaker);
		job.setCarryout_user_names(carryout_user_names);
		// 开启流程
		basicPrcManage.addActivitiApply(job, process);
		// 把任务信息插入数据库
		basicMapper.addProcessJob(job);
		// 更新作业信息
		if (jobType.getJobTypeCode().equals("SPECIAL_PRINT")) {
			addOaEventJobRel(eventIdList, job, usage_code, project_code, seclv_code);
		} else {
			addEventJobRel(user_iidd, eventIdList, cycle_type, period, seclv_code, job, usage_code, project_code,
					comment, fileSeclvMap, filePrintCountMap, fileColorMap, filePrintDoubleMap, fileTitleMap,
					start_time);
		}
		String user_name = userService.getUserNameByUserId(job.getUser_iidd());
		String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
		String detail = "提交" + jobType.getJobTypeName() + "申请";
		ProcessRecord record = new ProcessRecord(job.getJob_code(), job.getJobType(), job.getUser_iidd(), user_name,
				dept_name, detail, "请审批", new Date());
		activitiService.addProcessRecord(record);
		if ((copy_num != null) && (!copy_num.equals(0))) {
			if (eventIdList.size() != 0) {
				PrintEvent pevent = null;
				for (String event_id : eventIdList) {
					String event_code = user_iidd + "-COPYMERGE-" + event_id + "-" + System.currentTimeMillis();
					pevent = printMapper.getPrintEventByPrintId(event_id);
					if (pevent != null) {
						CopyEvent event = new CopyEvent(user_iidd, dept_id, event_code, seclv_code, usage_code,
								project_code, pevent.getSumm(), "", pevent.getFile_title(), copy_num,
								pevent.getPage_count(), "打印复印合并流程", "", pevent.getPrint_double(),
								pevent.getPage_size(), null, null, pevent.getColor(), 0, pevent.getEvent_code(), "");
						event.setPeriod(period);
						event.setCycle_type("REMAIN");
						event.setCopy_type("merge");
						event.setJob_code(job_code);
						copyService.addCopyEventByMerge(event);
					}
				}
			}
		}
		// 如果有审批流程，在消息表中添加审批消息
		if (process.getTotal_steps() != 0) {
			String message = dept_name + user_name + "有" + jobType.getJobTypeName() + "作业需要您审批";
			String oper_type = "";
			if (jobType.getJobTypeCode().startsWith("PRINT_")) {
				oper_type = "PRINT";
			} else if (jobType.getJobTypeCode().startsWith("SPECIAL_PRINT_")) {
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
		}
	}

	private void addEventJobRel(String user_iidd, List<String> eventIdList, String cycle_type, String period,
			Integer seclv_code, ProcessJob job, String usage_code, String project_code, String comment,
			Map<String, String> fileSeclvMap, Map<String, String> filePrintCountMap, Map<String, String> fileColorMap,
			Map<String, String> filePrintDoubleMap, Map<String, String> fileTitleMap, Date apply_time) {
		logger.debug("addEventJobRel");
		String prefix = user_iidd + "-PRINT-";
		String event_code = "";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("job_code", job.getJob_code());
		map.put("cycle_type", cycle_type);
		map.put("period", period);
		map.put("usage_code", usage_code);
		map.put("project_code", project_code);
		map.put("summ", comment);
		map.put("apply_time", apply_time);
		for (String id : eventIdList) {
			id = id.trim();
			map.put("id", id);
			// 生成一个作业流水号event_code
			event_code = "0000000";
			if (id.length() < 7) {
				event_code = prefix.concat(event_code.substring(id.length()).concat(id));
			} else {
				event_code = prefix.concat(id);
			}
			map.put("event_code", event_code);
			// 查找该打印作业设置的密级
			if (fileSeclvMap.get(id) != null) {
				map.put("seclv_code", Integer.parseInt(fileSeclvMap.get(id)));
			} else {
				map.put("seclv_code", seclv_code);
			}
			// 查找该打印作业设置的打印份数
			map.put("print_count", Integer.parseInt(filePrintCountMap.get(id)));
			// 查找该打印作业设置的色彩
			map.put("color", Integer.parseInt(fileColorMap.get(id)));
			// 查找该打印作业设置的打印单双面
			map.put("print_double", Integer.parseInt(filePrintDoubleMap.get(id)));
			// 查找该打印作业设置的文件名称
			map.put("file_title", fileTitleMap.get(id));
			printMapper.addPrintEventJobRel(map);
		}
	}

	@Override
	public List<PrintEvent> getPrintEventListByJobCode(String job_code) {
		return printMapper.getPrintEventListByJobCode(job_code);
	}

	@Override
	public String delMultiPrintEvents(List<String> event_id_list) {
		String file_names = "";
		PrintEvent event = null;
		for (String id : event_id_list) {
			id = id.trim();
			event = printMapper.getPrintEventByPrintId(id);
			if (event != null) {
				file_names += event.getFile_title() + ",";
				printMapper.delPrintEventByPringId(id);
				logger.debug("delPrintEvent:" + id);
			}
		}
		return file_names;
	}

	@Override
	public void cancelPrintEventByJobCode(String job_code) {
		printMapper.cancelPrintEventByJobCode(job_code);
	}

	@Override
	public int getPrintEventEnterCancel(String job_code) {
		return printMapper.getPrintEventEnterCancel(job_code);
	}

	@Override
	public void addOaPrintEvent(OaPrintEvent event) {
		logger.debug("addOaPrintEvent:" + event.getEvent_code());
		printMapper.addOaPrintEvent(event);
	}

	@Override
	public List<OaPrintEvent> getSpecialEventList(Map<String, Object> map) {
		logger.debug("getSpecialEventList");
		return printMapper.getSpecialEventList(map);
	}

	private void addOaEventJobRel(List<String> eventIdList, ProcessJob job, String usage_code, String project_code,
			Integer seclv_code) {
		logger.debug("addOaEventJobRel");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("job_code", job.getJob_code());
		map.put("usage_code", usage_code);
		map.put("project_code", project_code);
		map.put("seclv_code", seclv_code);
		for (String id : eventIdList) {
			map.put("id", id.trim());
			printMapper.addOaPrintEventJobRel(map);
		}
	}

	@Override
	public List<OaPrintEvent> getSpecialPrintEventListByJobCode(String job_code) {
		logger.debug("getSpecialPrintEventListByJobCode");
		return printMapper.getSpecialPrintEventListByJobCode(job_code);
	}

	@Override
	public OaPrintEvent getSpecialPrintEventByEventCode(String event_code) {
		logger.debug("getSpecialPrintEventByEventCode");
		return printMapper.getSpecialPrintEventByEventCode(event_code);
	}

	@Override
	public OaPrintEvent getSpecialPrintEventById(String id) {
		logger.debug("getSpecialPrintEventById");
		return printMapper.getSpecialPrintEventById(id);
	}

	@Override
	public void delSpecialPrintEventByPringId(String id) {
		logger.debug("delSpecialPrintEventByPringId");
		printMapper.delSpecialPrintEventByPringId(id);
	}

	@Override
	public void querrySpecialOpers(Map<String, Object> map, CycleItem cycleitem, EntityPaper paper) {
		logger.debug("querrySpecialOpers");
		// 更新特殊打印表中状态
		printMapper.updateSpecialEventState(map);
		// 更新到台账中
		enterMapper.addPaperledger(paper);
		// 更新全生命周期记录
		ledgerMapper.addCycleItem(cycleitem);
	}

	@Override
	public void updateSpecialPrintEventByEventCode(Map<String, Object> map) {
		logger.debug("updateSpecialPrintEventByEventCode");
		printMapper.updateSpecialPrintEventByEventCode(map);
	}

	@Override
	public void updateEventFileReadByStFileName(Map<String, Object> map) {
		logger.debug("updateEventFileReadByStFileName");
		printMapper.updateEventFileReadByStFileName(map);
	}

	@Override
	public int getIsAllEventIsRead(String job_code) {
		return printMapper.getIsAllEventIsRead(job_code);
	}

	@Override
	public void updateEventFileReadByJobCode(Map<String, Object> map) {
		logger.debug("updateEventFileReadByJobCode");
		printMapper.updateEventFileReadByJobCode(map);
	}

	@Override
	public void updatePrintProxyUseridByEventCode(Map<String, Object> map) {
		printMapper.updatePrintProxyUseridByEventCode(map);
	}

	@Override
	public void AddKeywordPrintEvent(Map<String, Object> map) {
		logger.debug("AddKeywordPrintEvent");
		printMapper.AddKeywordPrintEvent(map);
	}

	@Override
	public void updateKeywordPrintEvent(Map<String, Object> map) {
		logger.debug("updateKeywordPrintEvent");
		printMapper.updateKeywordPrintEvent(map);
	}

	@Override
	public List<RiskKeywordsPrint> getRisklistPrint(Map<String, Object> map) {
		logger.debug("getRisklistPrint");
		return printMapper.getRisklistPrint(map);
	}

	@Override
	public String getPrintTransID(Map<String, Object> map) {
		logger.debug("getPrintTransID");
		return printMapper.getPrintTransID(map);
	}

	@Override
	public void addRisklistPrint(RiskKeywordsPrint temp) {
		logger.debug("addRisklistPrint");
		printMapper.addRisklistPrint(temp);
	}

	@Override
	public void addRisklistPrintList(List<RiskKeywordsPrint> list) {
		printMapper.addRisklistPrintList(list);
	}

	// 获取keyword_print表中是否包含该id
	@Override
	public String getTransIDfromPrint(Map<String, Object> map) {
		return printMapper.getTransIDfromPrint(map);
	}

	@Override
	public void cancelOaPrintEventByJobCode(String job_code) {
		printMapper.cancelOaPrintEventByJobCode(job_code);
	}

	@Override
	public int getOaPrintEventEnterCancel(String job_code) {
		return printMapper.getOaPrintEventEnterCancel(job_code);
	}

	@Override
	public void remarkPrintEvent(Map<String, Object> map) {
		logger.debug("remarkPrintEvent");
		printMapper.remarkPrintEvent(map);
	}

	@Override
	public int getPrintEventSize(Map<String, Object> map) {
		logger.debug("getPrintEventSize");
		return printMapper.getPrintEventSize(map);
	}

	@Override
	public List<FixAccording> getFixAccordingByType(Map<String, Object> map) {
		logger.debug("getFixAccordingByType");
		return printMapper.getFixAccordingByType(map);
	}

	@Override
	public void updateFixedContent(Map<String, Object> map) {
		logger.debug("updateFixedContent");
		printMapper.updateFixedContent(map);
	}

	@Override
	public void addFixedContent(Map<String, Object> map) {
		logger.debug("addFixedContent");
		printMapper.addFixedContent(map);
	}

	@Override
	public void delFixedAccordingById(String id) {
		logger.debug("delFixedAccordingById");
		printMapper.delFixedAccordingById(id);
	}

	@Override
	public void updateKeywordPrintCheckresultByTid(String tid, String check_result) {
		logger.debug("updateKeywordPrintCheckresultByTid");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tid", tid);
		map.put("check_result", check_result);
		printMapper.updateKeywordPrintCheckresultByTid(map);
	}

	@Override
	public Integer getFixedId(String content) {
		logger.debug("getFixedId");
		return printMapper.getFixedId(content);
	}

	@Override
	public void addRefFixedAccordingDept(RefFixAccordingDept refFixAccordingDept) {
		logger.debug("addRefFixedAccordingDept");
		printMapper.addRefFixedAccordingDept(refFixAccordingDept);
	}
}
