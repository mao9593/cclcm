package hdsec.web.project.secactivity.service;

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
import hdsec.web.project.secactivity.mapper.SecActivityMapper;
import hdsec.web.project.secactivity.model.SecOutExchangeEvent;
import hdsec.web.project.secactivity.model.UserSecActiEvent;
import hdsec.web.project.user.service.UserService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

public class SecActivityServiceImpl implements SecActivityService {
	private final Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private SecActivityMapper secactivitymapper;
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

	@Override
	public void AddUserSecActiEvent(UserSecActiEvent event, String next_approver) throws Exception {
		logger.debug("AddUserSecActiEvent" + event.getEvent_code());
		secactivitymapper.AddUserSecActiEvent(event);
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
		String job_code = event.getUser_iidd() + "-" + event.getJobType() + "-" + System.currentTimeMillis();
		ProcessJob job = new ProcessJob(job_code, event.getUser_iidd(), event.getDept_id(), event.getSeclv_code(),
				event.getJobType(), new Date(), job_status, next_approver, next_approver_name, process.getProcess_id());
		job.setComment(event.getSumm());
		// 开启流程
		basicPrcManage.addActivitiApply(job, process);
		// 把任务信息插入数据库
		basicMapper.addProcessJob(job);
		Map<String, String> map = new HashMap<String, String>();
		map.put("event_code", event.getEvent_code());
		map.put("job_code", job_code);
		// 更新job_code字段
		secactivitymapper.updateSecActiEventJobCode(map);
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
			String oper_type = "USERSEC_ACTIVITY";
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(), message,
						new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}
	}

	@Override
	public UserSecActiEvent getSecActiEvent(String event_code) {
		logger.debug("getSecActiEvent ByEventCode");
		return secactivitymapper.getSecActiEventByEventCode(event_code);
	}

	@Override
	public String getJobCodeByEventCode(String event_code) {
		logger.debug("getJobCodeByEventCode");
		return secactivitymapper.getJCodeByECode(event_code);
	}

	@Override
	public List<UserSecActiEvent> getUSecActiEventList(Map<String, Object> map) {
		logger.debug("getUSecActiEventList");
		return secactivitymapper.getUSecActiEventList(map);
	}

	@Override
	public List<UserSecActiEvent> getUSecActiEventListByJobCode(String job_code) {
		return secactivitymapper.getUSecActiEventListByJobCode(job_code);
	}

	@Override
	public void delUSecActiEventByEventCode(String event_code) {
		logger.debug("delUSecActiEventByEventCode " + event_code);
		String job_code = secactivitymapper.getJCodeByECode(event_code);
		basicService.cancelJob(job_code, JobTypeEnum.SECUSER_ABROAD.getJobTypeCode());
		secactivitymapper.delUSecActiEventByEventCode(event_code);
	}

	/** 涉外交流保密工作 **/

	@Override
	public void AddSecOutExchangeEvent(SecOutExchangeEvent event, String next_approver) throws Exception {
		logger.debug("AddSecOutExchangeEvent" + event.getEvent_code());
		secactivitymapper.AddSecOutExchangeEvent(event);
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
		String job_code = event.getUser_iidd() + "-" + event.getJobType() + "-" + System.currentTimeMillis();
		ProcessJob job = new ProcessJob(job_code, event.getUser_iidd(), event.getDept_id(), event.getSeclv_code(),
				event.getJobType(), new Date(), job_status, next_approver, next_approver_name, process.getProcess_id());
		job.setComment(event.getSumm());
		// 开启流程
		basicPrcManage.addActivitiApply(job, process);
		// 把任务信息插入数据库
		basicMapper.addProcessJob(job);
		Map<String, String> map = new HashMap<String, String>();
		map.put("event_code", event.getEvent_code());
		map.put("job_code", job_code);
		// 更新job_code字段
		secactivitymapper.updateSecOutExchangeEventJobCode(map);
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
			String oper_type = "OUT_EXCHANGE";
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(), message,
						new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}
	}

	@Override
	public List<SecOutExchangeEvent> getSecOutExchangeEventList(Map<String, Object> map) {
		logger.debug("getSecOutExchangeEventList");
		return secactivitymapper.getSecOutExchangeEventList(map);
	}

	@Override
	public SecOutExchangeEvent getSecOutExchangeByEventCode(String event_code) {
		logger.debug("getSecOutExchangeByEventCode");
		return secactivitymapper.getSecOutExchangeByEventCode(event_code);
	}

	@Override
	public String getOutExchangeJobCodeByEventCode(String event_code) {
		logger.debug("getOutExchangeJobCodeByEventCode");
		return secactivitymapper.getOutExchangeJobCodeByEventCode(event_code);
	}

	@Override
	public void delSecOutExchangeEventByEventCode(String event_code) {
		logger.debug("delSecOutExchangeEventByEventCode " + event_code);
		String job_code = secactivitymapper.getOutExchangeJobCodeByEventCode(event_code);
		basicService.cancelJob(job_code, JobTypeEnum.OUT_EXCHANGE.getJobTypeCode());
		secactivitymapper.delSecOutExchangeEventByEventCode(event_code);
	}

	@Override
	public List<SecOutExchangeEvent> getOutExchangeEventListByJobCode(String job_code) {
		return secactivitymapper.getOutExchangeEventListByJobCode(job_code);
	}
}
