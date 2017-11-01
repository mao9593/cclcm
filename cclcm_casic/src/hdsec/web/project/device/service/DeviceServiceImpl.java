package hdsec.web.project.device.service;

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
import hdsec.web.project.client.service.ClientService;
import hdsec.web.project.device.mapper.DeviceMapper;
import hdsec.web.project.device.model.DeviceEvent;
import hdsec.web.project.device.model.DeviceStatusEnum;
import hdsec.web.project.device.model.DeviceType;
import hdsec.web.project.device.model.EntityDevice;
import hdsec.web.project.ledger.mapper.LedgerMapper;
import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.service.UserService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

public class DeviceServiceImpl implements DeviceService {
	private final Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private DeviceMapper deviceMapper;
	@Resource
	private BasicMapper basicMapper;
	@Resource
	private LedgerMapper ledgerMapper;
	@Resource
	private BasicService basicService;
	@Resource
	private BasicPrcManage basicPrcManage;
	@Resource
	private UserService userService;
	@Resource
	private ActivitiService activitiService;
	@Resource
	private ClientService clientService;

	/**
	 * 磁介质管理
	 */

	@Override
	public void addEntityDevice(EntityDevice device, CycleItem cycleitem) {
		logger.debug("addEntityDevice:" + device.getDevice_code());
		deviceMapper.addEntityDevice(device);
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("barcode", device.getDevice_barcode());
		map1.put("type", "DEPT");
		deviceMapper.updatePersonalEntityDevice(map1);
		ledgerMapper.addCycleItem(cycleitem);
	}

	@Override
	public void delDeviceByCode(String device_code, CycleItem cycleitem) {
		logger.debug("delDeviceByCode:" + device_code);
		deviceMapper.setDeviceSealedByCode(device_code);
		ledgerMapper.addCycleItem(cycleitem);
	}

	@Override
	public EntityDevice getDeviceByCode(String device_code) {
		logger.debug("getDeviceByCode:" + device_code);
		return deviceMapper.getDeviceByCode(device_code);
	}

	@Override
	public void updateDevice(EntityDevice device) {
		logger.debug("updateDevice:" + device.getDevice_code());
		deviceMapper.updateDevice(device);
	}

	@Override
	public void updateDeviceStatus(String device_code, DeviceStatusEnum status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("device_code", device_code);
		map.put("deviceStauts", status);
		deviceMapper.updateDeviceStatus(map);
	}

	/**
	 * 磁介质借入借出作业管理
	 */
	@Override
	public void addDeviceEvent(DeviceEvent event) {
		logger.debug("addDeviceEvent" + event.getEvent_code());
		deviceMapper.addDeviceEvent(event);
	}

	@Override
	public List<DeviceEvent> getDeviceEventList(Map<String, Object> map) {
		logger.debug("getDeviceEventList");
		return deviceMapper.getDeviceEventList(map);
	}

	@Override
	public DeviceEvent getDeviceEventByDeviceCode(String event_code) {
		logger.debug("getDeviceEventByDeviceCode" + event_code);
		return deviceMapper.getDeviceEventByDeviceCode(event_code);
	}

	@Override
	public void delDeviceEventByDeviceCode(String event_code) {
		logger.debug("delDeviceEventByDeviceCode" + event_code);
		deviceMapper.delDeviceEventByDeviceCode(event_code);
	}

	@Override
	public String getJobCodeByEventCode(String event_code) {
		logger.debug("getJobCodeByEventCode" + event_code);
		return deviceMapper.getJobCodeByEventCode(event_code);
	}

	@Override
	public void updateDeviceEvent(DeviceEvent event) {
		logger.debug("updateDeviceEvent" + event.getEvent_code());
		deviceMapper.updateDeviceEvent(event);
	}

	@Override
	public void cancelHandleDeviceByCode(String device_code) {
		logger.debug("cancelHandleDeviceByCode");
		// 先查询出该磁介质对应的job_code
		EntityDevice device = deviceMapper.getDeviceByCode(device_code);
		String job_code = device.getJob_code();
		// 把event对应的job_code设置为null,取消审批
		deviceMapper.cancelHandleDeviceByCode(device_code);
		// 如果该job_code对应的event数量为空，表示该任务下挂载的作业已经都被取消了，则取消该任务
		if (StringUtils.hasLength(job_code) && deviceMapper.getHandleDeviceCountByJobCode(job_code) == 0) {
			String instance_id = basicService.getProcessJobByCode(job_code).getInstance_id();
			basicPrcManage.suspendProcessInstanceById(instance_id);
			basicService.delJob(job_code);
			clientService.delClientMsgByJobCode(job_code);
		}
	}

	@Override
	public void confirmDeviceBR(String event_code, String device_code, SecUser user) {
		logger.debug("confirmDeviceBR");
		DeviceEvent event = getDeviceEventByDeviceCode(event_code);
		EntityDevice device = getDeviceByCode(device_code);

		if (basicService.isConfirmOpen("DEVICE_BR_CONFIRM")) {
			// 交接确认开启
			ConfirmRecord record = new ConfirmRecord();
			record.setApply_user_iidd(event.getUser_iidd());
			record.setApply_user_name(event.getUser_name());
			record.setApply_dept_id(event.getDept_id());
			record.setApply_dept_name(event.getDept_name());
			record.setConfirm_user_iidd(user.getUser_iidd());
			record.setConfirm_user_name(user.getUser_name());
			record.setConfirm_dept_id(user.getDept_id());
			record.setConfirm_dept_name(user.getDept_name());
			record.setEntity_type("DEVICE");
			record.setEntity_barcode(device.getDevice_barcode());
			record.setEntity_name(device.getDevice_name());
			record.setSeclv_name(device.getSeclv_name());
			record.setConfirm_type("DEVICE_BR");
			record.setEvent_code(event_code);
			record.setCreate_time(new Date());

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("event_code", event_code);
			map.put("device_barcode", device.getDevice_barcode());
			map.put("device_code", device_code);
			map.put("deviceStauts", DeviceStatusEnum.DS7);// 待借出
			basicMapper.saveConfirmRecord(record);
			deviceMapper.updateDeviceEventBarcode(map);
			deviceMapper.updateDeviceStatus(map);
		} else {
			// 交接确认关闭
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("event_code", event_code);
			map.put("device_barcode", device.getDevice_barcode());
			deviceMapper.updateDeviceEventBarcode(map);
			event.setFinish_time(new Date());
			event.setDevice_status(1);// 已交接借入
			deviceMapper.updateConfirmDeviceEvent(event);
			device.setBorrow_user_iidd(event.getUser_iidd());
			device.setDeviceStatus(DeviceStatusEnum.DS1);// 已借出
			deviceMapper.updateConfirmEntityDevice(device);
			// 生成载体生命周期记录：借入
			CycleItem cycleitem = new CycleItem();
			cycleitem.setBarcode(device.getDevice_barcode());
			cycleitem.setEntity_type("DEVICE");
			cycleitem.setOper_time(new Date());
			cycleitem.setUser_name(event.getUser_name());
			cycleitem.setDept_name(event.getDept_name());
			cycleitem.setOper("BORROW");
			// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
			String job_code = getJobCodeByEventCode(event.getEvent_code());
			cycleitem.setJob_code(job_code);
			// add ending
			ledgerMapper.addCycleItem(cycleitem);
		}
	}

	@Override
	public String getEventCodeByBarcode(String device_barcode, String borrow_user_iidd) {
		logger.debug("getEventCodeByBarcode");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("device_barcode", device_barcode);
		map.put("borrow_user_iidd", borrow_user_iidd);
		return deviceMapper.getEventCodeByBarcode(map);
	}

	@Override
	public void confirmDeviceRT(String device_code, SecUser user) {
		logger.debug("confirmDeviceRT");
		EntityDevice device = getDeviceByCode(device_code);
		String device_barcode = device.getDevice_barcode();
		String borrow_user_iidd = device.getBorrow_user_iidd();
		String event_code = getEventCodeByBarcode(device_barcode, borrow_user_iidd);
		DeviceEvent event = getDeviceEventByDeviceCode(event_code);

		if (basicService.isConfirmOpen("DEVICE_RT_CONFIRM")) {
			// 交接确认开启
			ConfirmRecord record = new ConfirmRecord();
			record.setApply_user_iidd(event.getUser_iidd());
			record.setApply_user_name(event.getUser_name());
			record.setApply_dept_id(event.getDept_id());
			record.setApply_dept_name(event.getDept_name());
			record.setConfirm_user_iidd(user.getUser_iidd());
			record.setConfirm_user_name(user.getUser_name());
			record.setConfirm_dept_id(user.getDept_id());
			record.setConfirm_dept_name(user.getDept_name());
			record.setEntity_type("DEVICE");
			record.setEntity_barcode(device_barcode);
			record.setEntity_name(device.getDevice_name());
			record.setSeclv_name(device.getSeclv_name());
			record.setConfirm_type("DEVICE_RT");
			record.setEvent_code(event_code);
			record.setCreate_time(new Date());

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("device_code", device_code);
			map.put("deviceStauts", DeviceStatusEnum.DS2);
			basicMapper.saveConfirmRecord(record);
			deviceMapper.updateDeviceStatus(map);
		} else {
			// 交接确认关闭
			event.setReturn_time(new Date());
			event.setDevice_status(2);// 已交接归还
			deviceMapper.updateConfirmDeviceEvent(event);
			device.setBorrow_user_iidd("");
			device.setDeviceStatus(DeviceStatusEnum.DS3);// 检查中
			deviceMapper.updateConfirmEntityDevice(device);
			// 生成载体生命周期记录：归还
			CycleItem cycleitem = new CycleItem();
			cycleitem.setBarcode(device.getDevice_barcode());
			cycleitem.setEntity_type("DEVICE");
			cycleitem.setOper_time(new Date());
			cycleitem.setUser_name(event.getUser_name());
			cycleitem.setDept_name(event.getDept_name());
			cycleitem.setOper("RETURN");
			// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
			// 此操作无流程，因此设置为default
			cycleitem.setJob_code("default");
			// add ending
			ledgerMapper.addCycleItem(cycleitem);
		}
	}

	@Override
	public void addProcessJob(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType,
			String next_approver, String comment, String hiddens, String usage_code, String project_code)
			throws Exception {
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
		job.setComment(comment);
		// 开启流程
		basicPrcManage.addActivitiApply(job, process);
		// 把任务信息插入数据库
		basicMapper.addProcessJob(job);
		// 添加磁介质借入作业
		addEventJobRel(user_iidd, dept_id, hiddens, job, usage_code, project_code, comment);
		String user_name = userService.getUserNameByUserId(job.getUser_iidd());
		String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
		String detail = "提交磁介质借入申请";
		ProcessRecord record = new ProcessRecord(job.getJob_code(), job.getJobType(), job.getUser_iidd(), user_name,
				dept_name, detail, "请审批", new Date());
		activitiService.addProcessRecord(record);
		// 如果有审批流程，在消息表中添加审批消息
		if (process.getTotal_steps() != 0) {
			String message = dept_name + user_name + "有磁介质借入申请需要您审批";
			String oper_type = jobType.getJobTypeCode();
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(), message,
						new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}
	}

	private void addEventJobRel(String user_iidd, String dept_id, String hiddens, ProcessJob job, String usage_code,
			String project_code, String comment) {
		int i = 0;
		for (String hidden : hiddens.split(",")) {
			String event_code = user_iidd + "-DEVICE-" + System.currentTimeMillis() + i++;
			DeviceEvent event = new DeviceEvent();
			event.setUser_iidd(user_iidd);
			event.setDept_id(dept_id);
			event.setEvent_code(event_code);
			event.setSeclv_code(Integer.parseInt(hidden.split("#")[1]));
			event.setMed_type(Integer.parseInt(hidden.split("#")[0]));
			event.setApply_time(new Date());
			event.setJob_code(job.getJob_code());
			event.setUsage_code(usage_code);
			event.setProject_code(project_code);
			event.setSumm(comment);
			addDeviceEvent(event);
		}
	}

	@Override
	public Integer getSeclvCodeByEventId(String event_id) {
		logger.debug("getSeclvCodeByEventId");
		return deviceMapper.getSeclvCodeByEventId(event_id);
	}

	@Override
	public List<DeviceEvent> getDeviceEventListByJobCode(String job_code) {
		logger.debug("getDeviceEventListByJobCode");
		return deviceMapper.getDeviceEventListByJobCode(job_code);
	}

	@Override
	public int cancelDeviceEventByEventCode(String event_code) {
		// 先查询出该event对应的job_code
		DeviceEvent event = deviceMapper.getDeviceEventByDeviceCode(event_code);
		String job_code = event.getJob_code();
		// 删除对应的event,取消审批
		deviceMapper.delDeviceEventByDeviceCode(event_code);
		// 如果该job_code对应的event数量为空，表示该任务下挂载的作业已经都被取消了，则取消该任务
		if (StringUtils.hasLength(job_code) && deviceMapper.getDeviceEventCountByJobCode(job_code) == 0) {
			basicService.cancelJob(job_code, event.getJobType().getJobTypeCode());
			return 1;
		}
		return 0;
	}

	@Override
	public EntityDevice getDeviceByBarcode(String device_barcode) {
		logger.debug("getDeviceByBarcode:" + device_barcode);
		return deviceMapper.getDeviceByBarcode(device_barcode);
	}

	@Override
	public List<DeviceType> getDeviceTypeList() {
		logger.debug("getDeviceTypeList");
		return deviceMapper.getDeviceTypeList();
	}

	@Override
	public void addDeviceType(DeviceType type) {
		logger.debug("addDeviceType");
		deviceMapper.addDeviceType(type);
	}

	@Override
	public boolean isTypeExistByID(Integer id) {
		return deviceMapper.getTypeCountByID(id) > 0;
	}

	@Override
	public boolean isTypeExistByName(String typename) {
		return deviceMapper.getTypeCountByName(typename) > 0;
	}

	@Override
	public DeviceType getDeviceTypeByID(Integer id) {
		return deviceMapper.getDeviceTypeByID(id);
	}

	@Override
	public void updateDeviceType(DeviceType type) {
		deviceMapper.updateDeviceType(type);
	}

	@Override
	public Integer getTypeIDByName(String typename) {
		return deviceMapper.getTypeIDByName(typename);
	}

	@Override
	public boolean isTypeInUse(Integer id) {
		return deviceMapper.getUseTypeCountByDevice(id) > 0;
	}

	@Override
	public void delDeviceTypeByID(Integer id) {
		deviceMapper.delDeviceTypeByID(id);
	}
}
