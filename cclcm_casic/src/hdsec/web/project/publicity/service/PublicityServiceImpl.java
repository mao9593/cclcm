package hdsec.web.project.publicity.service;

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
import hdsec.web.project.publicity.mapper.PublicityMapper;
import hdsec.web.project.publicity.model.ReportEvent;
import hdsec.web.project.user.service.UserService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

public class PublicityServiceImpl implements PublicityService {
	private final Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private PublicityMapper publicitymapper;
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
	public void addReportEvent(ReportEvent event, String next_approver) throws Exception {
		logger.debug("addReportEvent" + event.getEvent_code());
		publicitymapper.addReportEvent(event);
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
		publicitymapper.updateReportEventJobCode(map);
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
			String oper_type = "EVENT_REPORT";
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(), message,
						new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}
	}

	@Override
	public ReportEvent getReportEvent(String event_code) {
		logger.debug("getReportEvent ByEventCode");
		return publicitymapper.getReportEventByEventCode(event_code);
	}

	@Override
	public String getJobCodeByEventCode(String event_code) {
		logger.debug("getJobCodeByEventCode");
		return publicitymapper.getJCodeByECode(event_code);
	}

	@Override
	public List<ReportEvent> getPublReportEventList(Map<String, Object> map) {
		logger.debug("getPublReportEventList");
		return publicitymapper.getPublReportEventList(map);
	}

	@Override
	public List<ReportEvent> getPublReportEventListByJobCode(String job_code) {
		return publicitymapper.getPublReportEventListByJobCode(job_code);
	}

	@Override
	public void delUPublReportEventByEventCode(String event_code, String apply_type) {
		logger.debug("delUPublReportEventByEventCode " + event_code);
		String job_code = publicitymapper.getJCodeByECode(event_code);
		if (apply_type.equals("1")) {
			basicService.cancelJob(job_code, JobTypeEnum.EVENT_REPORT.getJobTypeCode());
		}
		if (apply_type.equals("2")) {
			basicService.cancelJob(job_code, JobTypeEnum.EVENT_DEPTREPORT.getJobTypeCode());
		}
		if (apply_type.equals("3")) {
			basicService.cancelJob(job_code, JobTypeEnum.EVENT_INTRAPUBL.getJobTypeCode());
		}
		if (apply_type.equals("4")) {
			basicService.cancelJob(job_code, JobTypeEnum.EVENT_INTERPUBL.getJobTypeCode());
		}
		publicitymapper.delUPublReportEventByEventCode(event_code);
	}
}
