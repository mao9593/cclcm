package hdsec.web.project.carriermanage.service;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.activiti.service.ActivitiService;
import hdsec.web.project.activiti.util.ActivitiCons;
import hdsec.web.project.basic.mapper.BasicMapper;
import hdsec.web.project.basic.model.ClientMsg;
import hdsec.web.project.basic.service.BasicPrcManage;
import hdsec.web.project.carriermanage.mapper.CarrierManageMapper;
import hdsec.web.project.carriermanage.model.PersonalFileEvent;
import hdsec.web.project.carriermanage.model.PersonalFileInfo;
import hdsec.web.project.user.service.UserService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

public class CarrierManageServiceImpl implements CarrierManageService {
	private final Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private CarrierManageMapper carrierManageMapper;
	@Resource
	private BasicMapper basicMapper;
	@Resource
	private BasicPrcManage basicPrcManage;
	@Resource
	private UserService userService;
	@Resource
	private ActivitiService activitiService;

	@Override
	public List<PersonalFileInfo> getPersonalFileInfo(Map<String, Object> map) {
		logger.debug("getPersonalFileInfo");
		return carrierManageMapper.getPersonalFileInfo(map);
	}

	@Override
	public void addPersonalFileInfo(PersonalFileInfo fileinfo) {
		logger.debug("addPersonalFileInfo");
		carrierManageMapper.addPersonalFileInfo(fileinfo);
	}

	@Override
	public List<PersonalFileEvent> getPersonalFileEventListByJobCode(String job_code) {
		logger.debug("getUserInfoEventListByJobCode");
		return carrierManageMapper.getPersonalFileEventListByJobCode(job_code);
	}

	@Override
	public List<PersonalFileInfo> getFileInfo(Map<String, Object> map) {
		logger.debug("getFileInfo");
		return carrierManageMapper.getFileInfo(map);
	}

	@Override
	public List<PersonalFileEvent> getPersonalFileEventList(Map<String, Object> map) {
		logger.debug("getPersonalFileEventList");
		return carrierManageMapper.getPersonalFileEventList(map);
	}

	@Override
	public void addPersonalFileEvent(PersonalFileEvent event, String next_approver) throws Exception {
		logger.debug("addPersonalFileEvent " + event.getEvent_code());
		carrierManageMapper.addPersonalFileEvent(event);
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
		carrierManageMapper.updatePersonalFileEventJobCode(map);
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
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, event.getJobType().toString(), 1,
						job.getJob_code(), message, new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}
	}

	@Override
	public void updateStatueEntityPersonalFile(String event_code) {
		logger.debug("updateStatueEntityPersonalFile:" + event_code);
		carrierManageMapper.updateStatueEntityPersonalFile(event_code);
	}

	@Override
	public int getFileInfoSize(Map<String, Object> map) throws Exception {
		logger.debug("getFileInfoSize");
		return carrierManageMapper.getFileInfoSize(map);
	}
}
