package hdsec.web.project.secmanage.service;

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
import hdsec.web.project.client.service.ClientService;
import hdsec.web.project.secmanage.mapper.SecManageMapper;
import hdsec.web.project.secmanage.model.ExchangeMaterialEvent;
import hdsec.web.project.secmanage.model.ExhibitionEvent;
import hdsec.web.project.secmanage.model.FileOutMakeEvent;
import hdsec.web.project.secmanage.model.InternetEmailEvent;
import hdsec.web.project.secmanage.model.PaperPatentEvent;
import hdsec.web.project.secmanage.model.PunishRectifyEvent;
import hdsec.web.project.secmanage.model.ResearchFieldInEvent;
import hdsec.web.project.secmanage.model.SecCheckEvent;
import hdsec.web.project.user.service.UserService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

public class SecManageServiceImpl implements SecManageService {
	private final Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private SecManageMapper secManageMapper;
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
	protected ClientService clientService;

	@Override
	public void addResearchFieldInEvent(ResearchFieldInEvent event, String next_approver) throws Exception {
		logger.debug("addResearchFieldInEvent" + event.getEvent_code());
		secManageMapper.addResearchFieldInEvent(event);
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
		secManageMapper.updateResearchFieldInEventJobCode(map);
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
			// String oper_type = "USERSECLV_CHANGE";
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, event.getJobType().toString(), 1,
						job.getJob_code(), message, new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}
	}

	@Override
	public List<ResearchFieldInEvent> getResearchFieldInEventList(Map<String, Object> map) {
		logger.debug("getResearchFieldInEventList");
		return secManageMapper.getResearchFieldInEventList(map);
	}

	@Override
	public ResearchFieldInEvent getResearchFieldInEventByEventCode(String event_code) {
		logger.debug("getResearchFieldInEventByEventCode");
		return secManageMapper.getResearchFieldInEventByEventCode(event_code);
	}

	@Override
	public String getJobCodeByEventCode(String event_code) {
		logger.debug("getJobCodeByEventCode" + event_code);
		return secManageMapper.getJobCodeByEventCode(event_code);
	}

	@Override
	public void delDelResearchFieldInEventByEventCode(String event_code) {
		logger.debug("delDelResearchFieldInEventByEventCode" + event_code);
		String job_code = secManageMapper.getJobCodeByEventCode(event_code);
		basicService.cancelJob(job_code, JobTypeEnum.FIELDIN.getJobTypeCode());
		secManageMapper.delDelResearchFieldInEventByEventCode(event_code);
	}

	@Override
	public List<ResearchFieldInEvent> getResearchFieldInEventListByJobCode(String job_code) {
		logger.debug("getResearchFieldInEventListByJobCode");
		return secManageMapper.getResearchFieldInEventListByJobCode(job_code);
	}

	/**
	 * 外网电子邮件
	 * 
	 * @author guojiao
	 **/
	@Override
	public void addInternetEmailEvent(InternetEmailEvent event, String next_approver) throws Exception {
		logger.debug("addInternetEmailEvent" + event.getEvent_code());
		secManageMapper.addInternetEmailEvent(event);
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
		secManageMapper.updateInternetEmailEventJobCode(map);
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
	public List<InternetEmailEvent> getInternetEmailEventList(Map<String, Object> map) {
		logger.debug("getInternetEmailEventList");
		return secManageMapper.getInternetEmailEventList(map);
	}

	@Override
	public void delDelInternetEmailEventByEventCode(String event_code) {
		logger.debug("delDelInternetEmailEventByEventCode" + event_code);
		String job_code = secManageMapper.getEmailJobCodeByEventCode(event_code);
		basicService.cancelJob(job_code, JobTypeEnum.INTER_EMAIL.getJobTypeCode());
		secManageMapper.delDelInternetEmailEventByEventCode(event_code);
	}

	@Override
	public String getEmailJobCodeByEventCode(String event_code) {
		logger.debug("getEmailJobCodeByEventCode" + event_code);
		return secManageMapper.getEmailJobCodeByEventCode(event_code);
	}

	@Override
	public InternetEmailEvent getInternetEmailEventByEventCode(String event_code) {
		logger.debug("getInternetEmailEventByEventCode");
		return secManageMapper.getInternetEmailEventByEventCode(event_code);
	}

	@Override
	public List<InternetEmailEvent> getInternetEmailListByJobCode(String job_code) {
		logger.debug("getResearchFieldInEventListByJobCode");
		return secManageMapper.getInternetEmailListByJobCode(job_code);
	}

	@Override
	public void addSecCheckEvent(SecCheckEvent event, String next_approver) throws Exception {
		logger.debug("addSecCheckEvent" + event.getEvent_code());
		secManageMapper.addSecCheckEvent(event);
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
		secManageMapper.updateSecCheckEventJobCode(map);
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
	public List<SecCheckEvent> getSecCheckEventList(Map<String, Object> map) {
		logger.debug("getSecCheckEventList");
		return secManageMapper.getSecCheckEventList(map);
	}

	@Override
	public void delDelSecCheckEventByEventCode(String event_code) {
		logger.debug("delDelSecCheckEventByEventCode " + event_code);
		String job_code = secManageMapper.getSecCheckJobCodeByEventCode(event_code);
		basicService.cancelJob(job_code, JobTypeEnum.SEC_CHECK.getJobTypeCode());
		secManageMapper.delDelSecCheckEventByEventCode(event_code);
	}

	@Override
	public String getSecCheckJobCodeByEventCode(String event_code) {
		logger.debug("getSecCheckJobCodeByEventCode" + event_code);
		return secManageMapper.getSecCheckJobCodeByEventCode(event_code);
	}

	@Override
	public SecCheckEvent getSecCheckEventByEventCode(String event_code) {
		logger.debug("getSecCheckEventByEventCode");
		return secManageMapper.getSecCheckEventByEventCode(event_code);
	}

	@Override
	public List<SecCheckEvent> getSecCheckListByJobCode(String job_code) {
		logger.debug("getSecCheckListByJobCode");
		return secManageMapper.getSecCheckListByJobCode(job_code);
	}

	@Override
	public void addExchangeMaterialEvent(ExchangeMaterialEvent event, String next_approver) throws Exception {
		logger.debug("addExchangeMaterialEvent " + event.getEvent_code());
		secManageMapper.addExchangeMaterialEvent(event);
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
		secManageMapper.updateExchangeMaterialEventJobCode(map);
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
	public List<ExchangeMaterialEvent> getExchangeMaterialEventList(Map<String, Object> map) {
		logger.debug("getExchangeMaterialEventList");
		return secManageMapper.getExchangeMaterialEventList(map);
	}

	@Override
	public void delDelExchangeMaterialEventByEventCode(String event_code) {
		logger.debug("delDelExchangeMaterialEventByEventCode " + event_code);
		String job_code = secManageMapper.getExchangeMaterialJobCodeByEventCode(event_code);
		basicService.cancelJob(job_code, JobTypeEnum.MATERIAL.getJobTypeCode());
		secManageMapper.delDelExchangeMaterialEventByEventCode(event_code);
	}

	@Override
	public String getExchangeMaterialJobCodeByEventCode(String event_code) {
		logger.debug("getExchangeMaterialJobCodeByEventCode" + event_code);
		return secManageMapper.getExchangeMaterialJobCodeByEventCode(event_code);
	}

	@Override
	public ExchangeMaterialEvent getExchangeMaterialEventByEventCode(String event_code) {
		logger.debug("getExchangeMaterialEventByEventCode");
		return secManageMapper.getExchangeMaterialEventByEventCode(event_code);
	}

	@Override
	public List<ExchangeMaterialEvent> getExchangeMaterialListByJobCode(String job_code) {
		logger.debug("getExchangeMaterialListByJobCode");
		return secManageMapper.getExchangeMaterialListByJobCode(job_code);
	}

	@Override
	public void addFileOutMakeEvent(FileOutMakeEvent event, String next_approver) throws Exception {
		logger.debug("addFileOutMakeEvent" + event.getEvent_code());
		secManageMapper.addFileOutMakeEvent(event);
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
		secManageMapper.updateFileOutMakeEventJobCode(map);
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
			// String oper_type = "USERSECLV_CHANGE";
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, event.getJobType().toString(), 1,
						job.getJob_code(), message, new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}
	}

	@Override
	public List<FileOutMakeEvent> getFileOutMakeEventList(Map<String, Object> map) {
		logger.debug("getFileOutMakeEventList");
		return secManageMapper.getFileOutMakeEventList(map);
	}

	@Override
	public FileOutMakeEvent getFileOutMakeEventByEventCode(String event_code) {
		logger.debug("getFileOutMakeEventByEventCode");
		return secManageMapper.getFileOutMakeEventByEventCode(event_code);
	}

	@Override
	public String getJobCodeByFileOutMakeEventCode(String event_code) {
		logger.debug("getJobCodeByFileOutMakeEventCode" + event_code);
		return secManageMapper.getJobCodeByFileOutMakeEventCode(event_code);
	}

	@Override
	public void delDelFileOutMakeEventByEventCode(String event_code) {
		logger.debug("delDelFileOutMakeEventByEventCode:" + event_code);
		String job_code = secManageMapper.getJobCodeByFileOutMakeEventCode(event_code);
		basicService.cancelJob(job_code, JobTypeEnum.FILEOUTMAKE.getJobTypeCode());
		secManageMapper.delDelFileOutMakeEventByEventCode(event_code);
	}

	@Override
	public List<FileOutMakeEvent> getFileOutMakeEventListByJobCode(String job_code) {
		logger.debug("getFileOutMakeEventListByJobCode");
		return secManageMapper.getFileOutMakeEventListByJobCode(job_code);
	}

	@Override
	public void addExhibitionEvent(ExhibitionEvent event, String next_approver) throws Exception {
		logger.debug("addExhibitionEvent" + event.getEvent_code());
		secManageMapper.addExhibitionEvent(event);
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
		secManageMapper.updateExhibitionEventJobCode(map);
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
			// String oper_type = "USERSECLV_CHANGE";
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, event.getJobType().toString(), 1,
						job.getJob_code(), message, new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}

	}

	@Override
	public List<ExhibitionEvent> getExhibitionEventList(Map<String, Object> map) {
		logger.debug("getExhibitionEventList");
		return secManageMapper.getExhibitionEventList(map);
	}

	@Override
	public ExhibitionEvent getExhibitionEventByEventCode(String event_code) {
		logger.debug("getExhibitionEventByEventCode");
		return secManageMapper.getExhibitionEventByEventCode(event_code);
	}

	@Override
	public String getExhibitionJobCodeByEventCode(String event_code) {
		logger.debug("getExhibitionJobCodeByEventCode");
		return secManageMapper.getExhibitionJobCodeByEventCode(event_code);
	}

	@Override
	public List<ExhibitionEvent> getExhibitionListByJobCode(String job_code) {
		logger.debug("getExhibitionListByJobCode");
		return secManageMapper.getExhibitionListByJobCode(job_code);
	}

	@Override
	public void delDelExhibitionEventByEventCode(String event_code) {
		logger.debug("delDelExhibitionEventByEventCode");
		String job_code = secManageMapper.getExhibitionJobCodeByEventCode(event_code);
		basicService.cancelJob(job_code, JobTypeEnum.EXHIBITION.getJobTypeCode());
		secManageMapper.delDelExhibitionEventByEventCode(event_code);
	}

	@Override
	public void addPunishEvent(PunishRectifyEvent event, String next_approver) throws Exception {
		logger.debug("addPunishEvent" + event.getEvent_code());
		secManageMapper.addPunishEvent(event);
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
		secManageMapper.updatePunishEventJobCode(map);
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
			// String oper_type = "USERSECLV_CHANGE";
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, event.getJobType().toString(), 1,
						job.getJob_code(), message, new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}
	}

	@Override
	public List<PunishRectifyEvent> getPunishEventList(Map<String, Object> map) {
		logger.debug("getPunishEventList");
		return secManageMapper.getPunishEventList(map);
	}

	@Override
	public void delDelPunishEventByEventCode(String event_code, String apply_type) {
		logger.debug("delDelPunishEventByEventCode");
		String job_code = secManageMapper.getPunishJobCodeByEventCode(event_code);
		if (apply_type.equals("0")) {
			basicService.cancelJob(job_code, JobTypeEnum.PUNISH_DEPT.getJobTypeCode());
		}
		if (apply_type.equals("1")) {
			basicService.cancelJob(job_code, JobTypeEnum.PUNISH_SECCHECK.getJobTypeCode());
		}
		if (apply_type.equals("2")) {
			basicService.cancelJob(job_code, JobTypeEnum.PUNISH_RECTIFY.getJobTypeCode());
		}
		secManageMapper.delDelPunishEventByEventCode(event_code);
	}

	@Override
	public PunishRectifyEvent getPunishEventByEventCode(String event_code) {
		logger.debug("getPunishEventByEventCode");
		return secManageMapper.getPunishEventByEventCode(event_code);
	}

	@Override
	public String getPunishJobCodeByEventCode(String event_code) {
		logger.debug("getPunishJobCodeByEventCode");
		return secManageMapper.getPunishJobCodeByEventCode(event_code);
	}

	@Override
	public List<PunishRectifyEvent> getPunishEventListByJobCode(String job_code) {
		logger.debug("getPunishEventListByJobCode");
		return secManageMapper.getPunishEventListByJobCode(job_code);
	}

	@Override
	public void addApproveFile(String event_code, String approve_file_list, Integer approve_file_num) {
		logger.debug("addApproveFile");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("event_code", event_code);
		map.put("file_list", approve_file_list);
		map.put("file_num", approve_file_num);
		secManageMapper.addApproveFile(map);

	}

	@Override
	public void updateSecCheckEventFileInfo(String event_code, Integer fileNum, String newFileList) {
		logger.debug("updateSecCheckEventFileInfo");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("event_code", event_code);
		map.put("file_num", fileNum);
		map.put("file_list", newFileList);
		secManageMapper.updateSecCheckEventFileInfo(map);

	}

	@Override
	public void updateSecCheckEvent(String event_code, String contact_num, String check_content, Integer file_num,
			String file_list, Date apply_time) {
		logger.debug("updateSecCheckEvent");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("event_code", event_code);
		map.put("contact_num", contact_num);
		map.put("check_content", check_content);
		map.put("file_num", file_num);
		map.put("file_list", file_list);
		map.put("apply_time", apply_time);
		secManageMapper.updateSecCheckEvent(map);

	}

	@Override
	public void addPaperPatentEvent(PaperPatentEvent event, String next_approver) throws Exception {
		logger.debug("addPaperPatentEvent" + event.getEvent_code());
		secManageMapper.addPaperPatentEvent(event);
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
		secManageMapper.updatePaperPatentEventJobCode(map);
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
			String oper_type = "PAPER_RESEARCH";
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, job.getJobType().getJobTypeCode(), 1,
						job.getJob_code(), message, new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}

	}

	@Override
	public String getPaperPatentJobCodeByEventCode(String event_code) {
		logger.debug("getPaperPatentJobCodeByEventCode");
		return secManageMapper.getPaperPatentJobCodeByEventCode(event_code);
	}

	@Override
	public List<PaperPatentEvent> getPaperPatentListByJobCode(String job_code) {
		logger.debug("getPaperPatentListByJobCode");
		return secManageMapper.getPaperPatentListByJobCode(job_code);
	}

	@Override
	public List<PaperPatentEvent> getPaperPatentEventList(Map<String, Object> map) {
		logger.debug("getPaperPatentEventList");
		return secManageMapper.getPaperPatentEventList(map);
	}

	@Override
	public PaperPatentEvent getPaperPatentEventByEventCode(String event_code) {
		logger.debug("getPaperPatentEventByEventCode");
		return secManageMapper.getPaperPatentEventByEventCode(event_code);
	}

	@Override
	public void delPaperPatentEventByEventCode(String event_code, String file_type) {
		logger.debug("delPaperPatentEventByEventCode" + event_code);
		String job_code = secManageMapper.getPaperPatentJobCodeByEventCode(event_code);

		if (file_type.equals("RESEARCH")) {
			basicService.cancelJob(job_code, JobTypeEnum.PAPER_RESEARCH.getJobTypeCode());
		}
		if (file_type.equals("OTHERS")) {
			basicService.cancelJob(job_code, JobTypeEnum.PAPER_OTHERS.getJobTypeCode());
		}
		if (file_type.equals("PATENT")) {
			basicService.cancelJob(job_code, JobTypeEnum.PAPERPATENT.getJobTypeCode());
		}

		secManageMapper.delPaperPatentEventByEventCode(event_code);
	}
}