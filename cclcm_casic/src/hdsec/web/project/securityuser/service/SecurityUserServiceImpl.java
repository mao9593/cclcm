package hdsec.web.project.securityuser.service;

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
import hdsec.web.project.carriermanage.model.PersonalFileEvent;
import hdsec.web.project.carriermanage.service.CarrierManageService;
import hdsec.web.project.client.service.ClientService;
import hdsec.web.project.common.bm.BMBarcodeGeneratorImpl;
import hdsec.web.project.common.bm.BMCycleItem;
import hdsec.web.project.common.bm.model.BMSysConfigItem;
import hdsec.web.project.common.bm.model.BmRealUser;
import hdsec.web.project.common.bm.model.ClientTask;
import hdsec.web.project.computer.model.BorrowBookEvent;
import hdsec.web.project.computer.model.ChangeDeviceEvent;
import hdsec.web.project.computer.model.EntityBook;
import hdsec.web.project.computer.model.InfoDeviceEvent;
import hdsec.web.project.computer.service.ComputerService;
import hdsec.web.project.secplace.model.SecplaceEvent;
import hdsec.web.project.secplace.service.SecplaceService;
import hdsec.web.project.securityuser.mapper.SecurityUserMapper;
import hdsec.web.project.securityuser.model.AbroadInfo;
import hdsec.web.project.securityuser.model.BmUserInfoEvent;
import hdsec.web.project.securityuser.model.CompanyContact;
import hdsec.web.project.securityuser.model.ExperienceInfo;
import hdsec.web.project.securityuser.model.FamilyInfo;
import hdsec.web.project.securityuser.model.PostDeptConfig;
import hdsec.web.project.securityuser.model.ResignEvent;
import hdsec.web.project.securityuser.model.SysUserPost;
import hdsec.web.project.securityuser.model.UserAbroadEvent;
import hdsec.web.project.securityuser.model.UserEntrustEvent;
import hdsec.web.project.securityuser.model.UserSeclvChangeEvent;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.service.UserService;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

public class SecurityUserServiceImpl implements SecurityUserService {
	private final Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private SecurityUserMapper securityUserMapper;
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
	@Resource
	protected BMBarcodeGeneratorImpl bMBarcodeGenerator;
	@Resource
	protected SecplaceService secplaceService;
	@Resource
	protected ComputerService computerService;
	@Resource
	protected CarrierManageService carrierManageService;

	@Override
	public void addUserSeclvChangeEvent(UserSeclvChangeEvent event, String next_approver) throws Exception {
		logger.debug("addUserSeclvChangeEvent" + event.getEvent_code());
		securityUserMapper.addUserSeclvChangeEvent(event);
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
		securityUserMapper.updateUSeclvChangeEventJobCode(map);
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
	public List<UserSeclvChangeEvent> getUSeclvChangeEventList(Map<String, Object> map) {
		logger.debug("getUSeclvChangeEventList");
		return securityUserMapper.getUSeclvChangeEventList(map);
	}

	@Override
	public UserSeclvChangeEvent getUSeclvChangeEventByEventCode(String event_code) {
		logger.debug("getUSeclvChangeEventByEventCode");
		return securityUserMapper.getUSeclvChangeEventByEventCode(event_code);

	}

	@Override
	public String getJobCodeByEventCode(String event_code) {
		logger.debug("getJobCodeByEventCode" + event_code);
		return securityUserMapper.getJobCodeByEventCode(event_code);
	}

	@Override
	public void delUSecChangeEventByEventCode(String event_code) {
		logger.debug("delUSecChangeEventByEventCode" + event_code);
		String job_code = securityUserMapper.getJobCodeByEventCode(event_code);
		basicService.cancelJob(job_code, JobTypeEnum.USERSECLV_CHANGE.getJobTypeCode());
		securityUserMapper.delUSecChangeEventByEventCode(event_code);
	}

	@Override
	public void delUSecAddEventByEventCode(String event_code) {
		logger.debug("delUSecAddEventByEventCode" + event_code);
		String job_code = securityUserMapper.getJobCodeByEventCode(event_code);
		basicService.cancelJob(job_code, JobTypeEnum.USERSECLV_ADD.getJobTypeCode());
		securityUserMapper.delUSecChangeEventByEventCode(event_code);
	}

	@Override
	public List<UserSeclvChangeEvent> getUSeclvChangeEventListByJobCode(String job_code) {
		logger.debug("getUSeclvChangeEventListByJobCode");
		return securityUserMapper.getUSeclvChangeEventListByJobCode(job_code);
	}

	@Override
	public void UpdateUSeclvByEventCode(Map<String, Object> map, String change_user_iidd, String seclv_code_after,
			String dept_id_after, String post_id_after) {
		logger.debug("UpdateUSeclvByEventCode");
		securityUserMapper.UpdateUSeclvByEventCode(map);
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("change_user_iidd", change_user_iidd);
		map1.put("seclv_code_after", seclv_code_after);
		map1.put("dept_id_after", dept_id_after);
		map1.put("post_id_after", post_id_after);
		securityUserMapper.UpdateSecUSeclvByUserId(map1);
	}

	@Override
	public void addResignEvent(ResignEvent event, String next_approver) throws Exception {
		logger.debug("addResignEvent" + event.getEvent_code());
		securityUserMapper.addResignEvent(event);
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
		job.setComment(event.getReason());
		// 开启流程
		basicPrcManage.addActivitiApply(job, process);
		// 把任务信息插入数据库
		basicMapper.addProcessJob(job);
		Map<String, String> map = new HashMap<String, String>();
		map.put("event_code", event.getEvent_code());
		map.put("job_code", job_code);
		securityUserMapper.updateUResignEventJobCode(map);
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
			String oper_type = "RESIGN";
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(), message,
						new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		} else {
			securityUserMapper.UpdateUResignByJobCode(job_code);
			// 将离职人员账号置为“禁用”-gj
			Map<String, String> setuser = new HashMap<String, String>();
			setuser.put("status", "1");
			List<ResignEvent> revent = securityUserMapper.getUResignEventListByJobCode(job_code);
			for (ResignEvent tmp : revent) {
				setuser.put("user_iidd", tmp.getResign_user_iidd());
				userService.updateUserStatus(setuser);
			}
		}
	}

	@Override
	public List<ResignEvent> getUResignEventList(Map<String, Object> map) {
		logger.debug("getUResignEventList");
		return securityUserMapper.getUResignEventList(map);
	}

	@Override
	public ResignEvent getUResignEventByEventCode(String event_code) {
		logger.debug("getUResignEventByEventCode");
		return securityUserMapper.getUResignEventByEventCode(event_code);

	}

	@Override
	public String getResignJobCodeByEventCode(String event_code) {
		logger.debug("getResignJobCodeByEventCode" + event_code);
		return securityUserMapper.getResignJobCodeByEventCode(event_code);
	}

	@Override
	public void delUResignEventByEventCode(String event_code) {
		logger.debug("delUResignEventByEventCode" + event_code);
		String job_code = securityUserMapper.getResignJobCodeByEventCode(event_code);
		basicService.cancelJob(job_code, JobTypeEnum.RESIGN.getJobTypeCode());
		securityUserMapper.delUResignEventByEventCode(event_code);
	}

	@Override
	public List<ResignEvent> getUResignEventListByJobCode(String job_code) {
		logger.debug("getUResignEventListByJobCode");
		return securityUserMapper.getUResignEventListByJobCode(job_code);
	}

	@Override
	public void updateResignEventByJobCode(Map<String, Object> map) {
		logger.debug("updateResignEventByJobCode");
		securityUserMapper.updateResignEventByJobCode(map);

	}

	/** 涉密人员因私出国模块 */
	@Override
	public void addUserAbroadEvent(UserAbroadEvent event, String next_approver) throws Exception {
		logger.debug("addUserAbroadEvent" + event.getEvent_code());
		securityUserMapper.addUserAbroadEvent(event);
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
		securityUserMapper.updateUAbroadEventJobCode(map);

		// 若无审批则直接更新出国状态--郭姣
		if (process.getTotal_steps() == 0) {
			securityUserMapper.updateUAbroadStatusByJobCode(job_code);
		}

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
			String oper_type = "SECUSER_ABROAD";
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(), message,
						new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}
	}

	@Override
	public List<UserAbroadEvent> getUAbroadEventList(Map<String, Object> map) {
		logger.debug("getUAbroadEventList");
		return securityUserMapper.getUAbroadEventList(map);
	}

	@Override
	public UserAbroadEvent getUAbroadEventByEventCode(String event_code) {
		logger.debug("getUAbroadEventByEventCode");
		return securityUserMapper.getUAbroadEventByEventCode(event_code);
	}

	@Override
	public String getUAbroadJobCodeByEventCode(String event_code) {
		logger.debug("getUAbroadJobCodeByEventCode" + event_code);
		return securityUserMapper.getUAbroadJobCodeByEventCode(event_code);
	}

	@Override
	public void delUAbroadEventByEventCode(String event_code) {
		logger.debug("delUAbroadEventByEventCode" + event_code);
		String job_code = securityUserMapper.getUAbroadJobCodeByEventCode(event_code);
		basicService.cancelJob(job_code, JobTypeEnum.SECUSER_ABROAD.getJobTypeCode());
		securityUserMapper.delUAbroadEventByEventCode(event_code);
	}

	@Override
	public List<UserAbroadEvent> getUAbroadEventListByJobCode(String job_code) {
		logger.debug("getUAbroadEventListByJobCode");
		return securityUserMapper.getUAbroadEventListByJobCode(job_code);
	}

	@Override
	public void addApproveAboradFile(Map<String, Object> map) {
		logger.debug("addApproveAboradFile");
		securityUserMapper.addApproveAboradFile(map);
	}

	/** 涉密人员委托保密管理 */

	@Override
	public void addUserEntrustEvent(UserEntrustEvent event, String next_approver) throws Exception {
		logger.debug("addUserEntrustEvent" + event.getEvent_code());
		securityUserMapper.addUserEntrustEvent(event);
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
		securityUserMapper.updateUserEntrustEventJobCode(map);

		// 若无审批则直接更新委托状态--郭姣
		if (process.getTotal_steps() == 0) {
			securityUserMapper.updateUEntrustStatusByJobCode(job_code);
		}
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
			String oper_type = "SECUSER_ENTRUST";
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(), message,
						new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}
	}

	@Override
	public List<UserEntrustEvent> getUserEntrustEventList(Map<String, Object> map) {
		logger.debug("getUserEntrustEventList");
		return securityUserMapper.getUserEntrustEventList(map);
	}

	@Override
	public UserEntrustEvent getUserEntrustEventByEventCode(String event_code) {
		logger.debug("getUserEntrustEventByEventCode");
		return securityUserMapper.getUserEntrustEventByEventCode(event_code);
	}

	@Override
	public String getUserEntrustJobCodeByEventCode(String event_code) {
		logger.debug("getUserEntrustJobCodeByEventCode" + event_code);
		return securityUserMapper.getUserEntrustJobCodeByEventCode(event_code);
	}

	@Override
	public void delUserEntrustEventByEventCode(String event_code) {
		logger.debug("delUserEntrustEventByEventCode" + event_code);
		String job_code = securityUserMapper.getUserEntrustJobCodeByEventCode(event_code);
		basicService.cancelJob(job_code, JobTypeEnum.SECUSER_ENTRUST.getJobTypeCode());
		securityUserMapper.delUserEntrustEventByEventCode(event_code);
	}

	@Override
	public List<UserEntrustEvent> getUserEntrustEventListByJobCode(String job_code) {
		logger.debug("getUserEntrustEventListByJobCode");
		return securityUserMapper.getUserEntrustEventListByJobCode(job_code);
	}

	@Override
	public void extendApproveJob(String job_code, ApproverUser user, ApproverUser approver, String approved,
			String opinion, String entitytype) throws Exception {
		if (basicService.getProcessJobByCode(job_code) != null) {
			ProcessJob job = basicService.getProcessJobByCode(job_code);// 查询任务信息
			basicPrcManage.claimtask(job, user.getUser_iidd());// 领取任务，并设置下级审批人及审批状态
			basicMapper.claimJobTask(job);// 更新job信息
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
			String result = "";// 记录更新数据库的返回值，USBKEY时使用
			if (approved.equals(ActivitiCons.APPLY_APPROVED_PASS) && (cur_approval <= total_approval)) {
				job.setJob_status(ActivitiCons.APPLY_APPROVED_UNDER);
				// 如果审批流程未结束，在消息表中添加审批消息
				String message = job.getDept_name() + job.getUser_name() + "有" + job.getJobType().getJobTypeName()
						+ "作业需要您审批";
				String oper_type = "";
				oper_type = String.valueOf(job.getJobType());
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
					// 审批通过-更改状态--郭姣
					if (String.valueOf(job.getJobType()).startsWith("SECUSER_ABROAD")) {
						// 确认申请事由是否为只办理护照，则不更新增加审批通过状态（该状态只用于统计历史出国次数）
						List<UserAbroadEvent> aboradlist = securityUserMapper.getUAbroadEventListByJobCode(job_code);
						if (aboradlist != null) {
							UserAbroadEvent aboradevent = aboradlist.get(0);
							if (!aboradevent.getReason().contains("办理")) {
								securityUserMapper.updateUAbroadStatusByJobCode(job_code);
							}
						}
					} else if (String.valueOf(job.getJobType()).startsWith("SECUSER_ENTRUST")) {
						securityUserMapper.updateUEntrustStatusByJobCode(job_code);
					} else if (String.valueOf(job.getJobType()).startsWith("RESIGN")) {
						// 根据脱密/离职不同,进行不同处理操作
						List<ResignEvent> revent = securityUserMapper.getUResignEventListByJobCode(job_code);
						for (ResignEvent event : revent) {
							if (event.getReason().equals("3")) {
								// 脱密选择调离岗位，审批通过更新岗位和部门
								Map<String, Object> rmap = new HashMap<String, Object>();
								rmap.put("post_id_after", event.getPost_id_after());
								rmap.put("dept_id_after", event.getDept_id_after());
								rmap.put("change_user_iidd", event.getResign_user_iidd());
								securityUserMapper.UpdateSecUSeclvByUserId(rmap);
							} else {
								// 离职/退休/其他：则①禁用账户②离职退休需更新user_personal中人员状态
								Map<String, String> setuser = new HashMap<String, String>();
								setuser.put("user_iidd", event.getResign_user_iidd());
								if (event.getReason().equals("1") || event.getReason().equals("2")) {
									if (event.getReason().equals("1")) {
										setuser.put("user_statue", "2");// 人员状态更新为退休
									} else {
										setuser.put("user_statue", "3");// 人员状态更新为离职
									}
									setuser.put("real_user_id", event.getResign_user_iidd());
									securityUserMapper.updateUserStatueByResignUserId(setuser);
								}
								setuser.put("status", "1");// 1为锁定账户
								userService.updateUserStatus(setuser);
							}
						}
					} else if (String.valueOf(job.getJobType()).startsWith("USER_INFO")) {
						String eventcode = "";
						eventcode = securityUserMapper.getUserInfoEventCodeByJobCode(job_code);// 获取event_code

						Map<String, Object> mmap = new HashMap<String, Object>();
						mmap.put("event_code", eventcode);
						mmap.put("info_type", 1);
						BmRealUser tempuser = securityUserMapper.getUserInfoByEventCode(mmap); // 获取人员信息
						tempuser.setInfo_type(2);// 将信息状态改为真实信息

						Map<String, Object> mm = new HashMap<String, Object>();
						mm.put("real_user_id", tempuser.getReal_user_id());
						mm.put("info_type", 2);
						securityUserMapper.delUserInfoByUserIdAndInfoType(mm);// 删除原有info_type=2的数据

						addUserInfoToTemp(tempuser);// 将人员新信息添加到数据库中

						// 将第一次填写岗位ID更改到用户的sec_user表中（后续仅展示sec_user的post即可）
						Map<String, Object> userpost = new HashMap<String, Object>();
						userpost.put("post_id_after", tempuser.getBmpost_id());
						userpost.put("change_user_iidd", tempuser.getReal_user_id());
						securityUserMapper.UpdateSecUSeclvByUserId(userpost);

						// UpdateSecUSeclvByUserId
						Map<String, Object> map1 = new HashMap<String, Object>();
						map1.put("event_code", eventcode);
						map1.put("confirm_time", new Date());
						securityUserMapper.updateUserInfoConfirmTime(map1);// 添加用户更新确认时间
					} else if (String.valueOf(job.getJobType()).startsWith("INFO_DEVICE")
							|| String.valueOf(job.getJobType()).startsWith("INFO_OTHER")) {// 信息设备新增
						List<InfoDeviceEvent> event = computerService.getInfoDeviceEventListByJobCode(job_code);
						InfoDeviceEvent cevent = event.get(0);

						// 生成保密编号
						String num = computerService.createSecretSerial(cevent.getDevice_type().toString(),
								cevent.getInfo_id(), cevent.getDuty_dept_id());

						cevent.setConf_code_before(num);
						// 新增申请表信息加入台账
						computerService.addInfoDeviceByEvent(cevent);
						// 新增设备其他属性更新到台账
						computerService.updateDeviceEntityByTemp(cevent.getEvent_code());
					} else if (String.valueOf(job.getJobType()).startsWith("DEVICE_CHANGE")
							|| String.valueOf(job.getJobType()).startsWith("CHANGE_OTHER")) {// 信息设备变更
						computerService.updateInfoDeviceChangeByEvent(job_code);

						List<InfoDeviceEvent> eventlist = computerService.getInfoDeviceEventListByJobCode(job_code);
						if (eventlist != null) {
							InfoDeviceEvent event = eventlist.get(0);
							// 信息设备台账状态均置为“在用”
							if (!event.getDevice_barcode().equals("")) {
								Map<String, Object> mapinfo = new HashMap<String, Object>();
								mapinfo.put("device_barcode", event.getDevice_barcode());
								mapinfo.put("device_statues", "1");
								computerService.updateInfoDeviceByBarcode(mapinfo);
							}
						}
					} else if (String.valueOf(job.getJobType()).startsWith("DEVICE_DES")) {// 信息设备报废
						computerService.updateInfoDeviceDestroyByEvent(job_code);
					} else if (String.valueOf(job.getJobType()).startsWith("EVENT_SINCOM")
							|| String.valueOf(job.getJobType()).startsWith("EVENT_INTCOM")) {// 新增计算机网络机、单机
						String computer_barcode = "";
						ChangeDeviceEvent event = computerService.getChangeDeviceEventByJobCode(job_code);
						String dept_id = event.getDept_id();
						if (StringUtils.hasLength(dept_id)) {
							// computer_barcode = basicService.createCETCEntityBarcode(dept_id);
							// 不区分份数，默认为0
							computer_barcode = basicService.createNewCETCBarcode(dept_id, 0);
						} else {
							// computer_barcode = basicService.createCETCEntityBarcode("00");
							// 不区分份数，默认为0
							computer_barcode = basicService.createNewCETCBarcode("", 0);
						}
						// String computer_barcode = basicService.createEntityBarcode("COMPUTER");//通用版本
						computerService.addComputerByEvent(event, computer_barcode, user.getUser_name(),
								user.getUser_iidd());
						// 在event中添加对应的设备条码号
						computerService.addBarcodeInEvent(event.getEvent_code(), computer_barcode);
					} else if (String.valueOf(job.getJobType()).startsWith("EVENT_CHGCOM")
							|| String.valueOf(job.getJobType()).startsWith("EVENT_DESCOM")
							|| String.valueOf(job.getJobType()).startsWith("EVENT_REPCOM")
							|| String.valueOf(job.getJobType()).startsWith("EVENT_REINSTALL")
							|| String.valueOf(job.getJobType()).startsWith("EVENT_QUITINT")
							|| String.valueOf(job.getJobType()).startsWith("EVENT_OPENPORT")
							|| String.valueOf(job.getJobType()).startsWith("EVENT_USBKEY")
							|| String.valueOf(job.getJobType()).startsWith("EVENT_LOCALPRINTER")
							|| String.valueOf(job.getJobType()).startsWith("BOOK_CHANGE")
							|| String.valueOf(job.getJobType()).startsWith("BOOK_REPAIR")
							|| String.valueOf(job.getJobType()).startsWith("BOOK_DES")
							|| String.valueOf(job.getJobType()).startsWith("BOOK_REINSTALL")) {// 计算机变更、报废、维修、重装系统、退网+笔记本变更、维修、报废
						ChangeDeviceEvent event = computerService.getChangeDeviceEventByJobCode(job_code);
						result = computerService.changeComputerByEvent(event, user.getUser_name(), user.getUser_iidd());
					}
					// 涉密场所申请通过后，向entity表中添加对应信息
					if (job.getJobType().equals(JobTypeEnum.EVENT_SECPLACE)) {
						// 通过job_code获得event
						SecplaceEvent event = secplaceService.getSecplaceEventByJobCode(job_code);
						// 通过event添加entity
						String secplace_barcode = createBMEntityBarcode("SECPLACE");
						secplaceService.addSecplaceByEvent(event, secplace_barcode, user.getUser_name(),
								user.getUser_iidd());
					} else if (String.valueOf(job.getJobType()).startsWith("USERSECLV_ADD")
							|| String.valueOf(job.getJobType()).startsWith("USERSECLV_CHANGE")) {
						// 新增、涉密人员密级自动变更
						List<UserSeclvChangeEvent> eventList = securityUserMapper
								.getUSeclvChangeEventListByJobCode(job_code);
						UserSeclvChangeEvent event = eventList.get(0);
						String change_user_iidd = event.getChange_user_iidd();
						String seclv_code_after = event.getSeclv_code_after();
						String dept_id_after = event.getDept_id_after();
						String post_id_after = event.getPost_id_after();
						Map<String, Object> map1 = new HashMap<String, Object>();
						map1.put("event_code", event.getEvent_code());
						map1.put("change_time", new Date());
						map1.put("change_status", 1);
						UpdateUSeclvByEventCode(map1, change_user_iidd, seclv_code_after, dept_id_after, post_id_after);
					} else if (job.getJobType().equals(JobTypeEnum.BORROW_BOOK)
							|| job.getJobType().equals(JobTypeEnum.BORROW_BOOKOUT)) {// 笔记本借用(公司内/公司外)申请审批通过，即用户已归还并检查无误(更改笔记本状态)
						List<BorrowBookEvent> eventList = computerService.getBorrowBookEventListByJobCode(job_code);
						BorrowBookEvent event = eventList.get(0);
						if (!event.getAssociat_entity().equals("")) {
							EntityBook book1 = new EntityBook();
							book1.setBook_status(1);
							book1.setBook_barcode(event.getAssociat_entity());
							computerService.updateEntityBook(book1);

							BMCycleItem cycleitem = new BMCycleItem();
							cycleitem.setBarcode(event.getAssociat_entity());
							cycleitem.setEntity_type("DEVICE");
							cycleitem.setOper_time(new Date());
							cycleitem.setUser_name(event.getUser_name());
							cycleitem.setDept_name(event.getDept_name());
							cycleitem.setOper("RETURN");
							cycleitem.setJob_code(event.getJob_code());
							securityUserMapper.addCycleItem(cycleitem);
						}
					} else if (job.getJobType().equals(JobTypeEnum.PERSONAL_FILE)) {// 个人涉密资料申请通过之后，将台账中审批状态置为1
						List<PersonalFileEvent> personalList = carrierManageService
								.getPersonalFileEventListByJobCode(job_code);
						if (personalList.size() > 0) {
							PersonalFileEvent fevent = personalList.get(0);
							carrierManageService.updateStatueEntityPersonalFile(fevent.getEvent_code());
						}
					}
				} else {
					approveResultValue = 3;
					approveResult = "不通过";
					// 审批通过-更改状态--yangjl
					if (String.valueOf(job.getJobType()).startsWith("RESIGN")) {
						securityUserMapper.DeadUResignByJobCode(job_code);
					} else if (String.valueOf(job.getJobType()).startsWith("EVENT_CHGCOM")
							|| String.valueOf(job.getJobType()).startsWith("EVENT_DESCOM")
							|| String.valueOf(job.getJobType()).startsWith("EVENT_REPCOM")
							|| String.valueOf(job.getJobType()).startsWith("EVENT_REINSTALL")
							|| String.valueOf(job.getJobType()).startsWith("EVENT_QUITINT")) {// 计算机:维修/报废/变更/退网/重装,审批被驳回则更该状态为“在用”

						ChangeDeviceEvent event = computerService.getChangeDeviceEventByJobCode(job_code);
						Map<String, Object> mapr = new HashMap<String, Object>();
						mapr.put("computer_barcode", event.getBarcode());
						mapr.put("computer_status", "1");
						computerService.updateComputerByEvent(mapr);
					} else if (String.valueOf(job.getJobType()).startsWith("DEVICE_CHANGE")
							|| String.valueOf(job.getJobType()).startsWith("CHANGE_OTHER")
							|| String.valueOf(job.getJobType()).startsWith("DEVICE_DES")) {
						List<InfoDeviceEvent> eventlist = computerService.getInfoDeviceEventListByJobCode(job_code);
						if (eventlist != null) {
							InfoDeviceEvent event = eventlist.get(0);
							// 信息设备台账状态均置为“在用”
							if (!event.getDevice_barcode().equals("")) {
								Map<String, Object> mapinfo = new HashMap<String, Object>();
								mapinfo.put("device_barcode", event.getDevice_barcode());
								mapinfo.put("device_statues", "1");
								computerService.updateInfoDeviceByBarcode(mapinfo);
							}
						}
					} else if (String.valueOf(job.getJobType()).startsWith("BOOK_CHANGE")
							|| String.valueOf(job.getJobType()).startsWith("BOOK_REPAIR")
							|| String.valueOf(job.getJobType()).startsWith("BOOK_DES")
							|| String.valueOf(job.getJobType()).startsWith("BOOK_REINSTALL")) {// 笔记本:维修/报废/变更/重装,审批被驳回则更该状态为“在用”
						ChangeDeviceEvent event = computerService.getChangeDeviceEventByJobCode(job_code);
						Map<String, Object> mapr = new HashMap<String, Object>();
						mapr.put("book_barcode", event.getBarcode());
						mapr.put("book_status", "1");
						computerService.updateEntityBookByEvent(mapr);
					}
				}
				String message = "您的" + job.getJobType().getJobTypeName() + "作业已审批：" + approveResult;
				String oper_type = "";
				oper_type = String.valueOf(job.getJobType());
				ClientMsg clientMsg = new ClientMsg(job.getUser_iidd(), job.getUser_name(), oper_type,
						approveResultValue, job_code, message, new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
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

			if (result != "" && result != null) {
				throw new Exception(result);
			}

		} else
			throw new Exception("该审批任务已被用户取消！");
	}

	/** 用户个人信息完善模块 **/

	@Override
	public void addUserInfoToTemp(BmRealUser tempinfo) {
		logger.debug("addUserInfoToTemp");
		securityUserMapper.addUserInfoToTemp(tempinfo);
	}

	@Override
	public void addUserInfoEvent(BmUserInfoEvent event, String next_approver) throws Exception {
		logger.debug("addUserInfoEvent" + event.getEvent_code());
		securityUserMapper.addUserInfoEvent(event);
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
		securityUserMapper.updateUserInfoEventJobCode(map);

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
			String oper_type = "USER_INFO";
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(), message,
						new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}
	}

	@Override
	public List<BmUserInfoEvent> getUserInfoEventList(Map<String, Object> map) {
		logger.debug("getUserInfoEventList");
		return securityUserMapper.getUserInfoEventList(map);
	}

	@Override
	public BmUserInfoEvent getUserInfoEventByEventCode(String event_code) {
		logger.debug("getUserInfoEventByEventCode");
		return securityUserMapper.getUserInfoEventByEventCode(event_code);
	}

	@Override
	public BmRealUser getUserInfoByEventCode(Map<String, Object> map) {
		logger.debug("getUserInfoByEventCode");
		return securityUserMapper.getUserInfoByEventCode(map);
	}

	@Override
	public String getUserInfoJobCodeByEventCode(String event_code) {
		logger.debug("getUserInfoJobCodeByEventCode" + event_code);
		return securityUserMapper.getUserInfoJobCodeByEventCode(event_code);
	}

	@Override
	public void delUserInfoEventByEventCode(String event_code) {
		logger.debug("delUserInfoEventByEventCode" + event_code);
		String job_code = securityUserMapper.getUserInfoJobCodeByEventCode(event_code);
		basicService.cancelJob(job_code, JobTypeEnum.USER_INFO.getJobTypeCode());
		securityUserMapper.delUserInfoEventByEventCode(event_code);
		securityUserMapper.delUserInfoByEventCode(event_code);
		securityUserMapper.delUserExperience(event_code);
		securityUserMapper.delUserAbroad(event_code);
		securityUserMapper.delUserFamily(event_code);
	}

	@Override
	public List<BmUserInfoEvent> getUserInfoEventListByJobCode(String job_code) {
		logger.debug("getUserInfoEventListByJobCode");
		return securityUserMapper.getUserInfoEventListByJobCode(job_code);
	}

	@Override
	public BmRealUser getBmRealInfoByUserId(String user_id) {
		logger.debug("getBmRealInfoByUserId");
		return securityUserMapper.getBmRealInfoByUserId(user_id);
	}

	@Override
	public void updateBMSysConfigItem(BMSysConfigItem sysConfigItem) {
		logger.debug("updateBMSysConfigItem");
		securityUserMapper.updateBMSysConfigItem(sysConfigItem);
	}

	@Override
	public BMSysConfigItem getBMSysConfigItemValue(String item_key) {
		logger.debug("getBMSysConfigItemValue");
		return securityUserMapper.getBMSysConfigItemValue(item_key);
	}

	@Override
	public String createBMEntityBarcode(String entityType) throws Exception {
		return bMBarcodeGenerator.createBMEntityBarcode(entityType);
	}

	@Override
	public List<SysUserPost> getPostList() {
		logger.debug("getPostList");
		return securityUserMapper.getPostList();
	}

	@Override
	public List<SysUserPost> getDeptList() {
		logger.debug("getDeptList");
		return securityUserMapper.getDeptList();
	}

	@Override
	public List<SysUserPost> getAllSysUserPostList() {
		logger.debug("getAllSysUserPostList");
		Map<String, Object> map = new HashMap<String, Object>();
		return securityUserMapper.getSysUserPostList(map);
	}

	@Override
	public List<SysUserPost> getSysUserPostList() {
		logger.debug("getSysUserPostList");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("is_sealed", "N");
		return securityUserMapper.getSysUserPostList(map);
	}

	@Override
	public void addSysUserPost(SysUserPost userpost, String dept_ids) {
		logger.debug("addSysUserPost:" + userpost.getPost_id());
		securityUserMapper.addSysUserPost(userpost);
		Map<String, String> map = new HashMap<String, String>();
		if (!dept_ids.equals("")) {
			for (String dept_id : dept_ids.split(",")) {
				map.put("dept_id", dept_id);
				map.put("post_id", userpost.getPost_id());
				securityUserMapper.addPostDept(map);
			}
		}
	}

	@Override
	public void delUserPostByCode(String post_id) {
		logger.debug("delUserPostByCode:" + post_id);
		securityUserMapper.setUserPostSealedByCode(post_id);
	}

	@Override
	public SysUserPost getUserPostByCode(String post_id) {
		logger.debug("getUserPostByCode:" + post_id);

		return securityUserMapper.getUserPostByCode(post_id);
	}

	@Override
	public List<PostDeptConfig> getPostDeptList(String post_id, String dept_id) {
		logger.debug("getPostDeptList");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("post_id", post_id);
		map.put("dept_id", dept_id);
		return securityUserMapper.getPostDeptList(map);
	}

	@Override
	public void updateUserPost(SysUserPost userpost, String dept_ids) {
		logger.debug("updateUserPost:" + userpost.getPost_id());
		securityUserMapper.updateUserPost(userpost);
		securityUserMapper.delPostDeptByPostid(userpost);
		Map<String, String> map = new HashMap<String, String>();
		if (!dept_ids.equals("")) {
			for (String dept_id : dept_ids.split(",")) {
				map.put("dept_id", dept_id);
				map.put("post_id", userpost.getPost_id());
				securityUserMapper.addPostDept(map);
			}
		}
	}

	@Override
	public List<SysUserPost> getUserPostByUserPostName(String post_name) {
		return securityUserMapper.getUserPostListByUserPostName(post_name);
	}

	@Override
	public void recoverUserPostByCode(String post_id) {
		securityUserMapper.recoverUserPostByCode(post_id);
	}

	@Override
	public List<SysUserPost> getSysUserPostList(Map<String, Object> map) {
		return securityUserMapper.getSysUserPostList(map);
	}

	@Override
	public boolean isUserPostExist(String post_id, String post_name) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("post_id", post_id);
		map.put("post_name", post_name);
		return securityUserMapper.getUserPostCount(map) > 0;
	}

	@Override
	public List<ClientTask> getTask(Map<String, Object> map) {
		logger.debug("getTask");
		return securityUserMapper.getTask(map);
	}

	@Override
	public void addCycleItem(BMCycleItem cycleitem) {
		logger.debug("addCycleItem");
		securityUserMapper.addCycleItem(cycleitem);
	}

	@Override
	public List<BMCycleItem> getCycleItemListByBarcode(String barcode) {
		logger.debug("getCycleItemListByBarcode");
		return securityUserMapper.getCycleItemListByBarcode(barcode);
	}

	@Override
	public List<CompanyContact> getCompanycontact(Map<String, Object> map) {
		logger.debug("Companycontact");
		return securityUserMapper.getCompanyContact(map);
	}

	@Override
	public void addCompanyContact(CompanyContact contact) {
		securityUserMapper.addCompanyContact(contact);
	}

	@Override
	public void updateCompanyContact(CompanyContact contact) {
		securityUserMapper.updateCompanyContact(contact);
	}

	@Override
	public void delCompanyContact(Map<String, Object> map) {
		securityUserMapper.delCompanyContact(map);
	}

	@Override
	public List<BmRealUser> getAllUserInfoList(Map<String, Object> map) {
		logger.debug("getAllUserInfoList");
		return securityUserMapper.getAllUserInfoList(map);
	}

	@Override
	public int getBmUserById(String user_id) {
		logger.debug("getBmUserById");
		return securityUserMapper.getBmUserById(user_id);
	}

	@Override
	public List<BmRealUser> getUserInfoByUserIdAndInfoType(Map<String, Object> map) {
		logger.debug("getUserInfoByUserIdAndInfoType");
		return securityUserMapper.getUserInfoByUserIdAndInfoType(map);
	}

	/*
	 * @Override public List<BmRealUser> getUserInfoByUserIdAndInfoType(Map<String, Object> map) {
	 * logger.debug("getUserInfoByUserIdAndInfoType"); return securityUserMapper.getUserInfoByUserIdAndInfoType(map); }
	 */

	@Override
	public String getUserHistoryAbroadInfo(Map<String, Object> map) {
		logger.debug("getUserHistoryAbroadInfo");
		return securityUserMapper.getUserHistoryAbroadInfo(map);
	}

	@Override
	public void delUserInfoByUserIdAndInfoType(Map<String, Object> map) {
		logger.debug("delUserInfoByUserIdAndInfoType");
		securityUserMapper.delUserInfoByUserIdAndInfoType(map);
	}

	@Override
	public List<ProcessJob> getJobList(Map<String, Object> map) {
		logger.debug("getJobList");
		return securityUserMapper.getJobList(map);
	}

	@Override
	public List<SecUser> getBmSecUserList(Map<String, Object> map, RowBounds rbs) throws Exception {
		logger.debug("getBmSecUserList");
		return securityUserMapper.getBmSecUserList(map, rbs);
	}

	@Override
	public List<SecUser> getAllBmSecUserList(Map<String, Object> map) {
		logger.debug("getBmSecUserList");
		return securityUserMapper.getAllBmSecUserList(map);
	}

	@Override
	public int getBmSecUserSize(Map<String, Object> map) throws Exception {
		logger.debug("getBmSecUserSize");
		return securityUserMapper.getBmSecUserSize(map);
	}

	@Override
	public String getUserEntityPaperTotal(Map<String, Object> map) {
		logger.debug("getUserEntityPaperTotal");
		return securityUserMapper.getUserEntityPaperTotal(map);
	}

	@Override
	public String getUserEntityCdTotal(Map<String, Object> map) {
		logger.debug("getUserEntityCdTotal");
		return securityUserMapper.getUserEntityCdTotal(map);
	}

	@Override
	public void updateUserAbroadConfirmInfo(Map<String, Object> map) {
		logger.debug("updateUserAbroadConfirmInfo");
		securityUserMapper.updateUserAbroadConfirmInfo(map);
	}

	@Override
	public void resubmitApplyJob(String job_code, ApproverUser user, ApproverUser approver) throws Exception {
		logger.debug("resubmitApplyJob");// 重新提交申请
		if (basicService.getProcessJobByCode(job_code) != null) {
			ProcessJob job = basicService.getProcessJobByCode(job_code);// 查询任务信息
			basicPrcManage.applyuserclaimtask(job, user.getUser_iidd());// 领取任务
			// basicMapper.claimJobTask(job);
			job.setNext_approver(approver.getUser_iidd());// 设置下级审批人
			job.setNext_approver_name(approver.getUser_name());
			job.setJob_status(ActivitiCons.APPLY_APPROVED_DEFAULT);// 设置审批状态为待审批
			// 置对应job_code的消息为已读,此处后续添加

			basicPrcManage.resubmitApply(job);// 更新activiti中流程信息
			basicMapper.approveJobTask(job);// 更新job_process数据库状态
			// 记录审批意见
			String dept_name = userService.getDeptNameByUserId(user.getUser_iidd());
			String operation = "重新提交申请";
			ProcessRecord record = new ProcessRecord(job_code, job.getJobType(), user.getUser_iidd(),
					user.getUser_name(), dept_name, operation, "重新提交申请#请审批", new Date());
			activitiService.addProcessRecord(record);

			// 如果有审批流程，在消息表中添加审批消息
			String message = dept_name + user.getUser_name() + "有" + job.getJobType().getJobTypeName() + "作业需要您审批";
			for (String item : approver.getUser_name().split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, job.getJobType().toString(), 1,
						job.getJob_code(), message, new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}

		} else {
			throw new Exception("该任务不存在！");
		}

	}

	@Override
	public void addUserExperiences(ExperienceInfo experiences) {
		logger.debug("addUserExperiences");
		securityUserMapper.addUserExperiences(experiences);
	}

	@Override
	public void addUserAbroad(AbroadInfo abroad) {
		logger.debug("addUserAbroad");
		securityUserMapper.addUserAbroad(abroad);
	}

	@Override
	public void addUserFamily(FamilyInfo family) {
		logger.debug("addUserFamily");
		securityUserMapper.addUserFamily(family);
	}

	@Override
	public List<ExperienceInfo> getUserExperience(Map<String, Object> map) {
		logger.debug("getUserExperience");
		return securityUserMapper.getUserExperience(map);
	}

	@Override
	public List<AbroadInfo> getUserAbroad(Map<String, Object> map) {
		logger.debug("getUserAbroad");
		return securityUserMapper.getUserAbroad(map);
	}

	@Override
	public List<FamilyInfo> getUserFamily(Map<String, Object> map) {
		logger.debug("getUserFamily");
		return securityUserMapper.getUserFamily(map);
	}

	@Override
	public List<ProcessJob> getApprovedJobByUserId(String user_iidd, JobTypeEnum jobType, Map<String, Object> map) {
		List<String> instanceIdList = basicPrcManage.getApprovedInstanceIdList(user_iidd, jobType.getJobTypeCode());
		if (instanceIdList != null && instanceIdList.size() > 0) {
			map.put("instanceIds", instanceIdList);
			return securityUserMapper.getApprovedJobByInstanceIds(map);
		}
		return Collections.emptyList();
	}
}