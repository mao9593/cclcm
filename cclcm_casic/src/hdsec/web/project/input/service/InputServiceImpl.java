package hdsec.web.project.input.service;

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
import hdsec.web.project.input.mapper.InputMapper;
import hdsec.web.project.input.model.InputEvent;
import hdsec.web.project.user.service.UserService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

public class InputServiceImpl implements InputService {
	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private InputMapper inputMapper;
	@Resource
	private BasicPrcManage basicPrcManage;
	@Resource
	private UserService userService;
	@Resource
	private BasicService basicService;
	@Resource
	private ActivitiService activitiService;
	@Resource
	private BasicMapper basicMapper;

	@Override
	public void addInputEvent(InputEvent event, String next_approver) throws Exception {
		logger.debug("addInputEvent" + event.getEvent_code());
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
		// 开启流程
		basicPrcManage.addActivitiApply(job, process);
		// 把任务信息插入数据库
		basicMapper.addProcessJob(job);
		// 把任务信息插入数据库
		event.setApply_time(new Date());
		event.setJob_code(job_code);
		inputMapper.addInputEvent(event);
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
			String oper_type = "MSG_INPUT";
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(), message,
						new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}

	}

	@Override
	public List<InputEvent> getInputEventList(Map<String, Object> map) {
		return inputMapper.getInputEventList(map);
	}

	@Override
	public String getJobCodeByEventCode(String event_code) {
		return inputMapper.getJobCodeByEventCode(event_code);
	}

	@Override
	public String getEventCodeByJobCode(String job_code) {
		return inputMapper.getEventCodeByJobCode(job_code);
	}

	@Override
	public InputEvent getInputEventByCode(String event_code) {
		return inputMapper.getInputEventByCode(event_code);
	}

	@Override
	public void cancelInputEventByCode(String event_code) {
		logger.debug("cancelInputEventBy" + event_code);
		String job_code = inputMapper.getJobCodeByEventCode(event_code);
		basicService.cancelJob(job_code, JobTypeEnum.MSG_INPUT.getJobTypeCode());
		inputMapper.cancelInputEventByCode(event_code);
	}

	@Override
	public List<InputEvent> getInputEventListByJobCode(String job_code) {
		logger.debug("getInputEventListByJobCode" + job_code);
		return inputMapper.getInputEventListByJobCode(job_code);
	}

	@Override
	public void updateInputEventState(Map<String, Object> map) {
		logger.debug("updateInputEventState");
		inputMapper.updateInputEventState(map);
	}

	@Override
	public void updateInputEventFileInfo(String event_code, int fileNum, String newFileList, String newFileSec) {
		logger.debug("updateInputEvent" + event_code);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("event_code", event_code);
		map.put("file_num", fileNum);
		map.put("file_list", newFileList);
		map.put("file_seclevel", newFileSec);
		inputMapper.updateInputEventState(map);

	}

	@Override
	public List<InputEvent> getEfileInputEventList(Map<String, Object> map) {
		return inputMapper.getEfileInputEventList(map);
	}

	@Override
	public int getEfileInputEventListSize(Map<String, Object> map) {
		return inputMapper.getEfileInputEventListSize(map);
	}
}
