package hdsec.web.project.copy.service;

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
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.copy.mapper.CopyMapper;
import hdsec.web.project.copy.model.CopyEvent;
import hdsec.web.project.ledger.mapper.LedgerMapper;
import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.ledger.service.LedgerService;
import hdsec.web.project.transfer.mapper.TransferMapper;
import hdsec.web.project.transfer.model.EventTransfer;
import hdsec.web.project.user.service.UserService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

public class CopyServiceImpl implements CopyService {
	private final Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private CopyMapper copyMapper;
	@Resource
	private LedgerMapper ledgerMapper;
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
	@Resource
	private TransferMapper transferMapper;

	/**
	 * 复印作业管理
	 */
	@Override
	public void addCopyEvent(CopyEvent event) {
		logger.debug("addCopyEvent" + event.getEvent_code());
		copyMapper.addCopyEvent(event);
	}

	@Override
	public List<CopyEvent> getCopyEventList(Map<String, Object> map) {
		logger.debug("getCopyEventList");
		return copyMapper.getCopyEventList(map);
	}

	@Override
	public CopyEvent getCopyEventByCopyCode(String event_code) {
		logger.debug("getCopyEventByCopyCode" + event_code);
		return copyMapper.getCopyEventByCopyCode(event_code);
	}

	@Override
	public void delCopyEventByCopyCode(String event_code) {
		logger.debug("delCopyEventByCopyCode" + event_code);
		copyMapper.delCopyEventByCopyCode(event_code);
	}

	@Override
	public String getJobCodeByEventCode(String event_code) {
		logger.debug("getJobCodeByEventCode" + event_code);
		return copyMapper.getJobCodeByEventCode(event_code);
	}

	@Override
	public void updateCopyEvent(CopyEvent event) {
		logger.debug("updateCopyEvent" + event.getEvent_code());
		copyMapper.updateCopyEvent(event);
	}

	@Override
	public void addPaperledger(EntityPaper paper) {
		logger.debug("addPaperledger" + paper.getPaper_barcode());
		copyMapper.addPaperledger(paper);

	}

	@Override
	public void updateIsbarcode(String event_code, Integer is_barcode) {
		logger.debug("updateIsbarcode" + event_code);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("event_code", event_code);
		map.put("is_barcode", is_barcode);
		copyMapper.updateIsbarcode(map);
	}

	@Override
	public void addCopyPaperledger(Map<String, Object> map, EntityPaper paper, CycleItem cycleitem,
			EventTransfer transfer) {
		logger.debug("addCopyPaperledger");
		copyMapper.updateCopystatus(map);
		copyMapper.addPaperledger(paper);
		if (map.get("archive_time") != null && !String.valueOf(map.get("archive_time")).equals("0")) {
			// 更新载体提醒回收时间
			Map<String, Object> map_update = new HashMap<String, Object>();
			map_update.put("paper_barcode", paper.getPaper_barcode());
			map_update.put("expire_time", paper.getExpire_time());
			basicMapper.updatePaperExpireTime(map_update);
		}
		ledgerMapper.addCycleItem(cycleitem);
		if (transfer != null) {// 需要走流转时
			transferMapper.saveEventTranfer(transfer);
			// 如果流转交接确认开启，在交接确认表中增加此次流转的待确认信息
			if (basicService.isConfirmOpen("TRANSFER_CONFIRM")) {
				ConfirmRecord record = new ConfirmRecord(transfer.getUser_iidd(), transfer.getUser_name(),
						transfer.getDept_id(), transfer.getDept_name(), transfer.getAccept_user_iidd(),
						transfer.getAccept_user_name(), transfer.getAccept_dept_id(), transfer.getAccept_dept_name(),
						transfer.getEntity_type().toUpperCase(), transfer.getBarcode(), transfer.getFile_name(),
						transfer.getSeclv_name(), "TRANSFER", transfer.getEvent_code(), new Date());
				basicMapper.saveConfirmRecord(record);
			}
		}
	}

	@Override
	public void addProcessJob(String user_iidd, String dept_id, Integer seclv_code, String cycle_type,
			JobTypeEnum jobType, String next_approver, String output_dept_name, String output_user_name,
			String comment, List<String> barcodeList, String usage_code, String project_code,
			Map<String, String> copyNumList, String period, String copy_merge) throws Exception {
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
		// 添加复印event

		int i = 0;
		for (String barcode : barcodeList) {
			EntityPaper paper = ledgerService.getPaperByBarcode(barcode);
			String event_code = user_iidd + "-COPY-" + System.currentTimeMillis() + i++;

			CopyEvent event = new CopyEvent();
			event.setUser_iidd(user_iidd);
			event.setDept_id(dept_id);
			event.setEvent_code(event_code);
			event.setSeclv_code(paper.getSeclv_code());
			event.setSeclv_name(paper.getSeclv_name());
			event.setOriginalid(paper.getPaper_barcode());
			event.setFile_name(paper.getFile_title());
			event.setPage_num(paper.getPage_count());
			event.setIs_double(paper.getPrint_double());
			event.setPage_type(paper.getPage_size());
			event.setOrientation(paper.getPrint_direct());
			event.setApply_time(new Date());
			event.setCopy_num(Integer.parseInt(copyNumList.get(barcode)));
			event.setUsage_code(usage_code);
			event.setProject_code(project_code);
			event.setSumm(comment);
			event.setJob_code(job_code);
			event.setCycle_type(cycle_type);
			event.setPeriod(period);
			event.setCopy_type("internal");
			copyMapper.addCopyEvent(event);
		}

		String user_name = userService.getUserNameByUserId(job.getUser_iidd());
		String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
		String detail = "提交复印申请";
		ProcessRecord record = new ProcessRecord(job.getJob_code(), job.getJobType(), job.getUser_iidd(), user_name,
				dept_name, detail, "请审批", new Date());
		activitiService.addProcessRecord(record);
		// 如果有审批流程，在消息表中添加审批消息
		if (process.getTotal_steps() != 0) {
			String message = dept_name + user_name + "有复印作业需要您审批";
			String oper_type = "COPY";
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(), message,
						new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}
	}

	@Override
	public void addMergeProcessJob(String user_iidd, String dept_id, Integer seclv_code, String cycle_type,
			JobTypeEnum jobType, String next_approver, String output_dept_name, String output_user_name,
			String comment, List<String> barcodeList, String usage_code, String project_code, Integer copy_num,
			String period, String copy_merge) throws Exception {
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
		// 添加复印event
		String originalid = "";
		String file_name = "";
		Integer page_num = 0;
		String page_type = "";
		String seclv_name = "";
		Integer is_double = 0;
		Integer orientation = 0;
		for (String barcode : barcodeList) {
			EntityPaper paper = ledgerService.getPaperByBarcode(barcode);
			originalid = paper.getPaper_barcode() + Constants.COMMON_SEPARATOR + originalid;
			file_name = paper.getFile_title() + Constants.COMMON_SEPARATOR + file_name;
			if (paper.getPage_count() != null) {
				page_num = page_num + paper.getPage_count();

			}
			page_type = page_type + Constants.COMMON_SEPARATOR + paper.getPage_size();
			if (paper.getSeclv_code() < seclv_code) {
				seclv_code = paper.getSeclv_code();
			}
			seclv_name = seclv_name + Constants.COMMON_SEPARATOR + paper.getSeclv_name();
			if (paper.getPrint_double() != null) {
				is_double = is_double + paper.getPrint_double();
			}
			if (paper.getPrint_direct() != null) {
				orientation = orientation + paper.getPrint_direct();
			}
		}
		String event_code = user_iidd + "-COPY-" + System.currentTimeMillis() /* + i++ */;
		CopyEvent event = new CopyEvent();
		event.setUser_iidd(user_iidd);
		event.setDept_id(dept_id);
		event.setEvent_code(event_code);
		event.setSeclv_code(seclv_code);
		event.setSeclv_name(seclv_name);
		event.setOriginalid(originalid);
		event.setFile_name(file_name);
		event.setPage_num(page_num);
		event.setIs_double(is_double);
		event.setPage_type(page_type);
		event.setOrientation(orientation);
		event.setApply_time(new Date());
		event.setCopy_num(copy_num);
		event.setUsage_code(usage_code);
		event.setProject_code(project_code);
		event.setSumm(comment);
		event.setJob_code(job_code);
		event.setCycle_type(cycle_type);
		event.setPeriod(period);
		event.setCopy_type("internal");
		event.setCopy_merge(copy_merge);
		copyMapper.addCopyEvent(event);

		String user_name = userService.getUserNameByUserId(job.getUser_iidd());
		String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
		String detail = "提交复印申请";
		ProcessRecord record = new ProcessRecord(job.getJob_code(), job.getJobType(), job.getUser_iidd(), user_name,
				dept_name, detail, "请审批", new Date());
		activitiService.addProcessRecord(record);
		// 如果有审批流程，在消息表中添加审批消息
		if (process.getTotal_steps() != 0) {
			String message = dept_name + user_name + "有复印作业需要您审批";
			String oper_type = "COPY";
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(), message,
						new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}
	}

	@Override
	public List<CopyEvent> getCopyEventListByJobCode(String job_code) {
		logger.debug("getCopyEventListByJobCode");
		return copyMapper.getCopyEventListByJobCode(job_code);
	}

	@Override
	public int cancelCopyEventByEventCode(String event_code) {
		// 先查询出该event对应的job_code
		CopyEvent event = copyMapper.getCopyEventByCopyCode(event_code);
		String job_code = event.getJob_code();
		// 把event对应的job_code设置为null,取消审批
		// copyMapper.cancelCopyEventByEventCode(event_code);
		// 删除对应event
		copyMapper.delCopyEventByCopyCode(event_code);
		// 如果该job_code对应的event数量为空，表示该任务下挂载的作业已经都被取消了，则取消该任务
		if (StringUtils.hasLength(job_code) && copyMapper.getCopyEventCountByJobCode(job_code) == 0) {
			basicService.cancelJob(job_code, event.getJobType().getJobTypeCode());
			return 1;
		}
		return 0;
	}

	@Override
	public void addCopyEventByEnter(CopyEvent event) {
		logger.debug("addCopyEventByEnter" + event.getEvent_code());
		copyMapper.addCopyEventByEnter(event);
	}

	@Override
	public void addCopyEventByMerge(CopyEvent event) {
		logger.debug("addCopyEventByMerge" + event.getEvent_code());
		copyMapper.addCopyEventByMerge(event);
	}

	@Override
	public void addProcessJobByEnter(List<String> eventIdList, String user_iidd, String dept_id, Integer seclv_code,
			String cycle_type, JobTypeEnum jobType, String next_approver, String output_dept_name,
			String output_user_name, String comment, String usage_code) throws Exception {
		logger.debug("addProcessJobByEnter");
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
		addEventJobRel(eventIdList, job, usage_code, cycle_type);
		String user_name = userService.getUserNameByUserId(job.getUser_iidd());
		String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
		String detail = "提交" + jobType.getJobTypeName() + "申请";
		ProcessRecord record = new ProcessRecord(job.getJob_code(), job.getJobType(), job.getUser_iidd(), user_name,
				dept_name, detail, "请审批", new Date());
		activitiService.addProcessRecord(record);
		// 如果有审批流程，在消息表中添加审批消息
		if (process.getTotal_steps() != 0) {
			String message = dept_name + user_name + "有" + jobType.getJobTypeName() + "作业需要您审批";
			String oper_type = "OUTCOPY";
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(), message,
						new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}

	}

	private void addEventJobRel(List<String> eventIdList, ProcessJob job, String usage_code, String cycle_type) {
		logger.debug("addEventJobRel");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("job_code", job.getJob_code());
		map.put("usage_code", usage_code);
		map.put("cycle_type", cycle_type);
		for (String id : eventIdList) {
			map.put("id", id);
			copyMapper.addCopyEventJobRel(map);
		}
	}

	@Override
	public int cancelEnterCopyEventByEventCode(String event_code) {
		// 先查询出该event对应的job_code
		CopyEvent event = copyMapper.getCopyEventByCopyCode(event_code);
		String job_code = event.getJob_code();
		// 把event对应的job_code设置为null,取消审批
		copyMapper.cancelCopyEventByEventCode(event_code);
		// 删除对应event
		// copyMapper.delCopyEventByCopyCode(event_code);
		// 如果该job_code对应的event数量为空，表示该任务下挂载的作业已经都被取消了，则取消该任务
		if (StringUtils.hasLength(job_code) && copyMapper.getCopyEventCountByJobCode(job_code) == 0) {
			basicService.cancelJob(job_code, event.getJobType().getJobTypeCode());
			return 2;
		}
		return 0;
	}

	@Override
	public void cancelCopyEventByPrintEventCode(String print_eventcode) {
		logger.debug("cancelCopyEventByPrintEventCode" + print_eventcode);
		copyMapper.cancelCopyEventByPrintEventCode(print_eventcode);
	}
}
