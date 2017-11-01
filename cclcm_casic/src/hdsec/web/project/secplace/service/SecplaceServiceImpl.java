package hdsec.web.project.secplace.service;

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
import hdsec.web.project.common.bm.BMCycleItem;
import hdsec.web.project.ledger.mapper.LedgerMapper;
import hdsec.web.project.secplace.mapper.SecplaceMapper;
import hdsec.web.project.secplace.model.EnterSecplaceEvent;
import hdsec.web.project.secplace.model.EntitySecplace;
import hdsec.web.project.secplace.model.EntityVisitor;
import hdsec.web.project.secplace.model.SecplaceEvent;
import hdsec.web.project.securityuser.mapper.SecurityUserMapper;
import hdsec.web.project.securityuser.service.SecurityUserService;
import hdsec.web.project.user.service.UserService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

public class SecplaceServiceImpl implements SecplaceService {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Resource
	private LedgerMapper ledgerMapper;
	@Resource
	private SecplaceMapper secplaceMapper;
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
	private SecurityUserService securityUserService;
	@Resource
	private SecurityUserMapper securityUserMapper;

	@Override
	public void addEntitySecplace(EntitySecplace secplace, BMCycleItem cycleitem) {
		System.out.println(secplace);
		logger.debug("addEntitySecplacer:" + secplace.getSecplace_code());
		secplaceMapper.addEntitySecplace(secplace);
		securityUserMapper.addCycleItem(cycleitem);

	}

	@Override
	public List<EntitySecplace> getAllSecplaceList(Map<String, Object> map) throws Exception {
		logger.debug("getAllSecplaceList");
		String changedBarcode = basicService.changeBarcode((String) map.get("computer_barcode"));
		map.put("computer_barcode", changedBarcode);
		return secplaceMapper.getAllSecplaceList(map);
	}

	@Override
	public EntitySecplace getSecplaceByBarcode(String secplace_barcode) {
		logger.debug("getSecplaceByBarcode:" + secplace_barcode);
		return secplaceMapper.getSecplaceByBarcode(secplace_barcode);
	}

	@Override
	public void updateSecplace(EntitySecplace secplace) {
		logger.debug("UpdateSecplace:" + secplace.getSecplace_code());
		secplaceMapper.updateSecplace(secplace);

	}

	@Override
	public List<EntitySecplace> getFuzzySecplaceList(String fuzzy) {
		logger.debug("getFuzzySecplaceList:" + fuzzy);
		return secplaceMapper.getFuzzySecplaceList(fuzzy);
	}

	@Override
	public void addEnterSecplaceEvent(EnterSecplaceEvent event, String next_approver) throws Exception {
		logger.debug("addEnterSecplaceEvent" + event.getEvent_code());
		secplaceMapper.addEnterSecplaceEvent(event);// 插入event事件

		ApproveProcess process = basicPrcManage
				.getApproveProcessByKey(event.getDept_id(), String.valueOf(event.getSeclv_code()), event.getJobType()
						.getJobTypeCode(), event.getUsage_code(), true);// 获取审批流程
		String job_status = null;// 流程状态
		if (process.getTotal_steps() == 0) {
			job_status = ActivitiCons.APPLY_APPROVED_PASS;// 审批流程已通过
		} else {
			job_status = ActivitiCons.APPLY_APPROVED_DEFAULT;// 待审批
		}

		String next_approver_name = basicPrcManage.getApproverName(next_approver);// 通过审批人ID查询审批人姓名
		String job_code = event.getUser_iidd() + "-" + event.getJobType().getJobTypeCode() + "-"
				+ System.currentTimeMillis();// 生成job_code

		ProcessJob job = new ProcessJob(job_code, event.getUser_iidd(), event.getDept_id(), event.getSeclv_code(),
				event.getJobType(), new Date(), job_status, next_approver, next_approver_name, process.getProcess_id());
		// 新的任务
		// job.setComment(comment);
		// 开启流程
		basicPrcManage.addActivitiApply(job, process);
		// 把任务信息插入数据库
		basicMapper.addProcessJob(job);
		// 向event_io_secplace的event中添加job_code信息
		Map<String, String> map = new HashMap<String, String>();
		map.put("event_code", event.getEvent_code());
		map.put("job_code", job_code);
		secplaceMapper.updateEnterSecplaceEventJobCode(map);

		// 更新作业信息
		String user_name = userService.getUserNameByUserId(job.getUser_iidd());
		String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
		String detail = "提交" + event.getJobType().getJobTypeName() + "申请";
		ProcessRecord record = new ProcessRecord(job.getJob_code(), job.getJobType(), job.getUser_iidd(), user_name,
				dept_name, detail, "请审批", new Date());
		activitiService.addProcessRecord(record);// 记录流程

		// 如果有审批流程，在消息表中添加审批消息
		if (process.getTotal_steps() != 0) {
			String message = dept_name + user_name + "有" + event.getJobType().getJobTypeName() + "作业需要您审批";
			String oper_type = "ENTER_SECPLACE";
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(), message,
						new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}
	}

	@Override
	public void addEntityVisitor(EntityVisitor visitor) {
		System.out.println(visitor);
		logger.debug("addEntityVisitor:" + visitor.getVisitor_name());
		secplaceMapper.addEntityVisitor(visitor);

	}

	@Override
	public List<EnterSecplaceEvent> getEnterSecplaceEventList(Map<String, Object> map) throws Exception {
		logger.debug("getEnterSecplaceEventList");
		return secplaceMapper.getEnterSecplaceEventList(map);
	}

	@Override
	public EnterSecplaceEvent getEnterSecplaceEventByEventCode(String event_code) {
		logger.debug("getEnterSecplaceEventByEventCode:" + event_code);
		return secplaceMapper.getEnterSecplaceEventByEventCode(event_code);
	}

	@Override
	public String getEnterSecplaceEventJobCodeByEventCode(String event_code) {
		logger.debug("getEnterSecplaceEventJobCodeByEventCode" + event_code);
		return secplaceMapper.getEnterSecplaceEventJobCodeByEventCode(event_code);
	}

	@Override
	public List<EntityVisitor> getVisitorListByEventCode(String event_code) {
		logger.debug("getVisitorListByEventCode" + event_code);
		return secplaceMapper.getVisitorListByEventCode(event_code);
	}

	@Override
	public EnterSecplaceEvent getEnterSecplaceEventByJobCode(String job_code) {
		logger.debug("getEnterSecplaceEventListByJobCode" + job_code);
		return secplaceMapper.getEnterSecplaceEventListByJobCode(job_code);
	}

	@Override
	public String getEnterSecplaceEventCodeByJobCode(String job_code) {
		logger.debug("getEventCodeByJobCode" + job_code);
		return secplaceMapper.getEnterSecplaceEventCodeByJobCode(job_code);
	}

	@Override
	public void updateEnterSecplaceEventFileInfo(String event_code, int file_num, String file_list) {
		logger.debug("updateEnterSecplaceEventFileInfo:" + event_code);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("event_code", event_code);
		map.put("file_num", file_num);
		map.put("file_list", file_list);
		secplaceMapper.updateEnterSecplaceEventFileInfo(map);
	}

	@Override
	public void addSecplaceEvent(SecplaceEvent event, String next_approver) throws Exception {
		logger.debug("addSecplaceEvent" + event.getEvent_code());
		secplaceMapper.addSecplaceEvent(event);// 插入event事件

		ApproveProcess process = basicPrcManage
				.getApproveProcessByKey(event.getDept_id(), String.valueOf(event.getSeclv_code()), event.getJobType()
						.getJobTypeCode(), event.getUsage_code(), true);// 获取审批流程
		String job_status = null;// 流程状态
		if (process.getTotal_steps() == 0) {
			job_status = ActivitiCons.APPLY_APPROVED_PASS;// 审批流程已通过
		} else {
			job_status = ActivitiCons.APPLY_APPROVED_DEFAULT;// 待审批
		}

		String next_approver_name = basicPrcManage.getApproverName(next_approver);// 通过审批人ID查询审批人姓名
		String job_code = event.getUser_iidd() + "-" + event.getJobType().getJobTypeCode() + "-"
				+ System.currentTimeMillis();// 生成job_code

		ProcessJob job = new ProcessJob(job_code, event.getUser_iidd(), event.getDept_id(), event.getSeclv_code(),
				event.getJobType(), new Date(), job_status, next_approver, next_approver_name, process.getProcess_id());
		// 新的任务
		// job.setComment(comment);
		// 开启流程
		basicPrcManage.addActivitiApply(job, process);
		// 把任务信息插入数据库
		basicMapper.addProcessJob(job);
		// 向event_secplace的event中添加job_code信息
		Map<String, String> map = new HashMap<String, String>();
		map.put("event_code", event.getEvent_code());
		map.put("job_code", job_code);
		secplaceMapper.updateSecplaceEventJobCode(map);

		// 更新作业信息
		String user_name = userService.getUserNameByUserId(job.getUser_iidd());
		String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
		String detail = "提交" + event.getJobType().getJobTypeName() + "申请";
		ProcessRecord record = new ProcessRecord(job.getJob_code(), job.getJobType(), job.getUser_iidd(), user_name,
				dept_name, detail, "请审批", new Date());
		activitiService.addProcessRecord(record);// 记录流程

		// 如果有审批流程，在消息表中添加审批消息
		if (process.getTotal_steps() != 0) {
			String message = dept_name + user_name + "有" + event.getJobType().getJobTypeName() + "作业需要您审批";
			String oper_type = "EVENT_SECPLACE";
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(), message,
						new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		} else {// 没有审批流程，则直接添加的entity中
			String secplace_barcode = securityUserService.createBMEntityBarcode("SECPLACE");
			addSecplaceByEvent(event, secplace_barcode, user_name, event.getUser_iidd());
		}
	}

	@Override
	public List<SecplaceEvent> getSecplaceEventList(Map<String, Object> map) throws Exception {
		logger.debug("getSecplaceEventList");
		return secplaceMapper.getSecplaceEventList(map);
	}

	@Override
	public SecplaceEvent getSecplaceEventByEventCode(String event_code) {
		logger.debug("getSecplaceEventByEventCode:" + event_code);
		return secplaceMapper.getSecplaceEventByEventCode(event_code);
	}

	@Override
	public SecplaceEvent getSecplaceEventByJobCode(String job_code) {
		logger.debug("getSecplaceEventByJobCode" + job_code);
		return secplaceMapper.getSecplaceEventByJobCode(job_code);
	}

	@Override
	public String getSecplaceEventCodeByJobCode(String job_code) {
		logger.debug("getSecplaceEventCodeByJobCode" + job_code);
		return secplaceMapper.getSecplaceEventCodeByJobCode(job_code);
	}

	@Override
	public void addSecplaceByEvent(SecplaceEvent event, String secplace_barcode, String user_name, String user_id) {

		EntitySecplace secplace = new EntitySecplace(secplace_barcode, event.getSecplace_name(),
				event.getSecplace_code(), event.getSecplace_type(), event.getConf_code(), event.getSeclv_code(),
				event.getSecplace_location(), event.getLeader_id(), 1, event.getSecplace_application(),
				event.getFound_time_time(), event.getDuty_user_id(), event.getUser_number(), event.getPeople_protect(),
				event.getPhysical_protect(), event.getTechnology_protect(), event.getApply_time(), event.getSumm(),
				event.getUser_iidd(), event.getDuty_dept_id());
		// 添加entity
		String dept_name = userService.getDeptNameByUserId(user_id);
		// 生成载体生命周期记录
		BMCycleItem cycleitem = new BMCycleItem();
		cycleitem.setBarcode(secplace_barcode);
		cycleitem.setEntity_type("SECPLACE");
		cycleitem.setOper_time(new Date());
		cycleitem.setUser_name(user_name);
		cycleitem.setDept_name(dept_name);
		cycleitem.setOper("ADD");

		addEntitySecplace(secplace, cycleitem);

	}

	@Override
	public void deleteEnterSecplaceEvent(String event_code) {
		logger.debug("deleteEnterSecplaceEvent" + event_code);
		String job_code = secplaceMapper.getEnterSecplaceEventJobCodeByEventCode(event_code);
		basicService.cancelJob(job_code, JobTypeEnum.ENTER_SECPLACE.getJobTypeCode());
		secplaceMapper.deleteEnterSecplaceEvent(event_code);

	}

	@Override
	public void deleteSecplaceEvent(String event_code) {
		logger.debug("deleteSecplaceEvent" + event_code);
		String job_code = secplaceMapper.getSecplaceEventJobCodeByEventCode(event_code);
		basicService.cancelJob(job_code, JobTypeEnum.EVENT_SECPLACE.getJobTypeCode());
		secplaceMapper.deleteSecplaceEvent(event_code);

	}

	@Override
	public String getSecplaceEventJobCodeByEventCode(String event_code) {
		logger.debug("getSecplaceEventJobCodeByEventCode" + event_code);
		return secplaceMapper.getSecplaceEventJobCodeByEventCode(event_code);
	}

	@Override
	public int getSecplaceCountBySecplaceCode(String secplace_code) {
		logger.debug("getSecplaceCountBySecplaceCode" + secplace_code);
		return secplaceMapper.getSecplaceCountBySecplaceCode(secplace_code);
	}

}