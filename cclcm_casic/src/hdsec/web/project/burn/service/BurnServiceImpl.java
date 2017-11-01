package hdsec.web.project.burn.service;

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
import hdsec.web.project.burn.mapper.BurnMapper;
import hdsec.web.project.burn.model.BurnEvent;
import hdsec.web.project.burn.model.RiskKeywordsBurn;
import hdsec.web.project.user.service.UserService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

public class BurnServiceImpl implements BurnService {
	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private BurnMapper burnMapper;
	@Resource
	private BasicPrcManage basicPrcManage;
	@Resource
	private UserService userService;
	@Resource
	private BasicService basicService;
	@Resource
	private BasicMapper basicMapper;
	@Resource
	private ActivitiService activitiService;

	@Override
	public void addBurnEvent(BurnEvent event, String next_approver, String output_dept_name, String output_user_name,
			String comment) throws Exception {
		logger.debug("addBurnEvent" + event.getEvent_code());
		burnMapper.addBurnEvent(event);
		ApproveProcess process = basicPrcManage
				.getApproveProcessByKey(event.getDept_id(), String.valueOf(event.getSeclv_code()), event.getJobType()
						.getJobTypeCode(), event.getUsage_code(), true);
		String job_status = null;
		if (process.getTotal_steps() == 0) {
			job_status = ActivitiCons.APPLY_APPROVED_PASS;
		} else {
			job_status = ActivitiCons.APPLY_APPROVED_DEFAULT;
		}
		String next_approver_name = basicPrcManage.getApproverName(next_approver);
		String job_code = event.getUser_iidd() + "-" + event.getJobType().getJobTypeCode() + "-"
				+ System.currentTimeMillis();
		ProcessJob job = new ProcessJob(job_code, event.getUser_iidd(), event.getDept_id(), event.getSeclv_code(),
				event.getJobType(), new Date(), job_status, next_approver, next_approver_name, process.getProcess_id());
		job.setOutput_dept_name(output_dept_name);
		job.setOutput_user_name(output_user_name);
		job.setComment(comment);
		// 开启流程
		basicPrcManage.addActivitiApply(job, process);
		// 把任务信息插入数据库
		basicMapper.addProcessJob(job);
		Map<String, String> map = new HashMap<String, String>();
		map.put("event_code", event.getEvent_code());
		map.put("job_code", job_code);
		burnMapper.updateBurnEventJobCode(map);
		// 更新作业信息
		String user_name = userService.getUserNameByUserId(job.getUser_iidd());
		String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
		String detail = "提交" + event.getJobType().getJobTypeName() + "申请";
		ProcessRecord record = new ProcessRecord(job.getJob_code(), job.getJobType(), job.getUser_iidd(), user_name,
				dept_name, detail, "请审批", new Date());
		activitiService.addProcessRecord(record);
		// 如果有审批流程，在消息表中添加审批消息
		if (process.getTotal_steps() != 0) {
			String message = dept_name + user_name + "有" + event.getJobType().getJobTypeName() + "作业需要您审批";
			String oper_type = "BURN";
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(), message,
						new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}
	}

	@Override
	public List<BurnEvent> getBurnEventList(Map<String, Object> map) {
		logger.debug("getBurnEventList");
		return burnMapper.getBurnEventList(map);
	}

	@Override
	public BurnEvent getBurnEventByBurnCode(String event_code) {
		logger.debug("getBurnEventByBurnCode" + event_code);
		return burnMapper.getBurnEventByBurnCode(event_code);
	}

	@Override
	public void delBurnEventByBurnCode(String event_code) {
		logger.debug("delBurnEventByBurnCode" + event_code);
		String job_code = burnMapper.getJobCodeByEventCode(event_code);
		basicService.cancelJob(job_code, JobTypeEnum.BURN_FILE.getJobTypeCode());
		burnMapper.delBurnEventByBurnCode(event_code);
	}

	@Override
	public String getJobCodeByEventCode(String event_code) {
		logger.debug("getJobCodeByEventCode" + event_code);
		return burnMapper.getJobCodeByEventCode(event_code);
	}

	@Override
	public void updateBurnEvent(BurnEvent event) {
		logger.debug("updateBurnEvent" + event.getEvent_code());
		burnMapper.updateBurnEvent(event);
		burnMapper.updateBurnComment(event);
	}

	@Override
	public void updateBurnEventFileInfo(String event_code, int fileNum, String newFileList, String newFileSec) {
		logger.debug("updateBurnEvent" + event_code);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("event_code", event_code);
		map.put("file_num", fileNum);
		map.put("file_list", newFileList);
		map.put("file_seclevel", newFileSec);
		burnMapper.updateBurnEventFileInfo(map);
	}

	@Override
	public List<BurnEvent> getBurnEventListByJobCode(String job_code) {
		return burnMapper.getBurnEventListByJobCode(job_code);
	}

	@Override
	public void addProcessJob(String user_iidd, String dept_id, Integer seclv_code, String cycle_type,
			JobTypeEnum jobType, String next_approver, String output_dept_name, String output_user_name,
			String comment, List<String> eventCodeList, String usage_code, String project_code) throws Exception {
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
		addEventJobRel(eventCodeList, cycle_type, job, usage_code, project_code, comment);
		String user_name = userService.getUserNameByUserId(job.getUser_iidd());
		String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
		String detail = "提交" + jobType.getJobTypeName() + "申请";
		ProcessRecord record = new ProcessRecord(job.getJob_code(), job.getJobType(), job.getUser_iidd(), user_name,
				dept_name, detail, "请审批", new Date());
		activitiService.addProcessRecord(record);
		// 如果有审批流程，在消息表中添加审批消息
		if (process.getTotal_steps() != 0) {
			String message = dept_name + user_name + "有" + jobType.getJobTypeName() + "作业需要您审批";
			String oper_type = "BURN";
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(), message,
						new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}
	}

	private void addEventJobRel(List<String> eventCodeList, String cycle_type, ProcessJob job, String usage_code,
			String project_code, String comment) {
		logger.debug("addEventJobRel");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("job_code", job.getJob_code());
		map.put("cycle_type", cycle_type);
		map.put("usage_code", usage_code);
		map.put("project_code", project_code);
		map.put("summ", comment);
		for (String code : eventCodeList) {
			map.put("event_code", code);
			burnMapper.addBurnEventJobRel(map);
		}
	}

	@Override
	public void updateBurnEventWithoutUsage(BurnEvent event) {
		logger.debug("updateBurnEventWithoutUsage" + event.getEvent_code());
		burnMapper.updateBurnEventWithoutUsage(event);
	}

	@Override
	public void updateBurnProxyUseridByEventCode(Map<String, Object> map) {
		burnMapper.updateBurnProxyUseridByEventCode(map);
	}

	@Override
	public void AddKeywordBurnEvent(Map<String, Object> map) {
		logger.debug("AddKeywordBurnEvent");
		burnMapper.AddKeywordBurnEvent(map);
	}

	@Override
	public void updateKeywordBurnEvent(Map<String, Object> map) {
		logger.debug("updateKeywordBurnEvent");
		burnMapper.updateKeywordBurnEvent(map);
	}

	@Override
	public void updateKeywordBurnCallresultByTid(Map<String, Object> map) {
		logger.debug("updateKeywordBurnCallresultByTid");
		burnMapper.updateKeywordBurnCallresultByTid(map);
	}

	@Override
	public void addRisklistBurn(RiskKeywordsBurn temp) {
		logger.debug("addRisklistBurn");
		burnMapper.addRisklistBurn(temp);
	}

	// 根据文件名称/用户名ID/event_code获取刻录transid
	@Override
	public String getBurnTransID(Map<String, Object> map) {
		logger.debug("getBurnTransID");
		return burnMapper.getBurnTransID(map);
	}

	// 根据transid获取刻录文件检索结果列表
	@Override
	public List<RiskKeywordsBurn> getRisklistBurn(Map<String, Object> map) {
		logger.debug("getRisklistBurn");
		return burnMapper.getRisklistBurn(map);
	}

	@Override
	public void updateKeywordBurnCheckresultByTid(String tid, String check_result) {
		logger.debug("updateKeywordBurnCheckresultByTid");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tid", tid);
		map.put("check_result", check_result);
		burnMapper.updateKeywordBurnCheckresultByTid(map);
	}
}
