package hdsec.web.project.disc.service;

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
import hdsec.web.project.common.PropertyUtil;
import hdsec.web.project.disc.mapper.DiscMapper;
import hdsec.web.project.disc.model.EntitySpaceCD;
import hdsec.web.project.disc.model.SpaceCDEvent;
import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.service.UserService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

/**
 * 
 * 
 * @author lidepeng 2015-8-11
 */
public class DiscServiceImpl implements DiscService {
	private final Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private DiscMapper discMapper;
	@Resource
	private BasicService basicService;
	@Resource
	private UserService userService;
	@Resource
	private ActivitiService activitiService;
	@Resource
	private BasicPrcManage basicPrcManage;
	@Resource
	private ClientService clientService;
	@Resource
	private BasicMapper basicMapper;

	// 空白盘条码输出
	private List<String> createBarcode_1(int count) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", count);
		discMapper.getCreateBarcode_1(map);
		String barcode = String.valueOf(map.get("outValue"));
		String prefix = "0000000000000";
		List<String> barcodes = new ArrayList<String>();
		int barcodeInt = Integer.parseInt(barcode);
		for (int i = 0; i < count; i++) {
			barcodes.add(prefix.substring(barcode.length()).concat(String.valueOf(barcodeInt)));
			barcodeInt--;
		}
		return barcodes;
	}

	private List<String> createBarcode_spacecd(String dept_id, int seclv_code, int year, int count) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("inDeptID", dept_id);
		map.put("inSeclvCode", seclv_code);
		map.put("inYear", year);
		map.put("count", count);
		discMapper.getCreateBarcode_spacecd(map);
		String barcode = String.valueOf(map.get("outValue"));
		String prefix = "0000";
		List<String> barcodes = new ArrayList<String>();
		int barcodeInt = Integer.parseInt(barcode);
		for (int i = 0; i < count; i++) {
			barcodes.add(prefix.substring(barcode.length()).concat(String.valueOf(barcodeInt)));
			barcodeInt--;
		}
		return barcodes;
	}

	@Override
	public void addEnterSpaceCD(EntitySpaceCD item) {
		logger.debug("addEnterSpaceCD");
		discMapper.addEnterSpaceCD(item);
	}

	@Override
	public void addEnterSpaceCDEvent(SpaceCDEvent item) {
		logger.debug("addEnterSpaceCDEvent");
		discMapper.addEnterSpaceCDEvent(item);
	}

	@Override
	public List<SpaceCDEvent> getEventSpaceCdList(Map<String, Object> map) {
		logger.debug("getEventSpaceCdList");
		return discMapper.getEventSpaceCdList(map);
	}

	@Override
	public List<String> createEntityBarcode_1(String entityType, int count) throws Exception {
		String unitCode = PropertyUtil.getUnitCode();
		String barcodeType = PropertyUtil.getEntityTypeKey(entityType);
		String curYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		List<String> barcodes = createBarcode_1(count);
		List<String> entity_barcodes = new ArrayList<String>();
		for (String string : barcodes) {
			entity_barcodes.add(unitCode + barcodeType + curYear + string + "00");
		}
		return entity_barcodes;
	}

	/*
	 * @Override public List<String> createEntityBarcode_spacecd(String entityType, String dept_id, int seclv_code, int
	 * count) throws Exception { // entityType暂时无用，后续添加纸质载体后，如果需要根据不同载体产生不同条码时可使用 String seclv = ""; String dept_code =
	 * basicMapper.getExtCodeByDeptId(dept_id); Integer curYear = Calendar.getInstance().get(Calendar.YEAR);
	 * List<String> barcodes = createBarcode_spacecd(dept_id, seclv_code, curYear, count); switch (seclv_code) { case 1:
	 * seclv = "J"; break; case 2: seclv = "AA"; break; case 3: seclv = "M"; break; case 4: seclv = "A"; break; case 5:
	 * seclv = "N"; break; case 6: seclv = "F"; break;
	 * 
	 * } List<String> entity_barcodes = new ArrayList<String>(); for (String serial_barcode : barcodes) {
	 * entity_barcodes.add("C" + seclv + dept_code + serial_barcode + "-" + String.valueOf(curYear)); } return
	 * entity_barcodes; }
	 */

	@Override
	public List<EntitySpaceCD> getDeptEntitySpaceCdList(Map<String, Object> map) {
		logger.debug("getDeptEntitySpaceCdList");
		return discMapper.getDeptEntitySpaceCdList(map);
	}

	@Override
	public List<EntitySpaceCD> getSelfEntitySpaceCdList(Map<String, Object> map) {
		logger.debug("getSelfEntitySpaceCdList");
		return discMapper.getSelfEntitySpaceCdList(map);
	}

	@Override
	public List<EntitySpaceCD> getDeptSpaceCdStock(Map<String, Object> map) {
		logger.debug("getDeptSpaceCdStock");
		return discMapper.getDeptSpaceCdStock(map);
	}

	@Override
	public List<SpaceCDEvent> getSpaceCDEventListByJobCode(String job_code) {
		return discMapper.getSpaceCDEventListByJobCode(job_code);
	}

	@Override
	public void updateSpaceCdById(Map<String, Object> map) {
		logger.debug("updateSpaceCdById");
		discMapper.updateSpaceCdById(map);
	}

	@Override
	public void updateSpaceCDEventByEventCode(Map<String, Object> map) {
		logger.debug("updateSpaceCDEventByEventCode");
		discMapper.updateSpaceCDEventByEventCode(map);
	}

	@Override
	public EntitySpaceCD getEntitySpaceCdById(String id) {
		logger.debug("getEntitySpaceCdById");
		return discMapper.getEntitySpaceCdById(id);
	}

	@Override
	public void addSpaceCdProcessJob(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType,
			String next_approver, String comment, List<String> eventIdList, String usage_code, String project_code)
			throws Exception {

		logger.debug("addSpaceCdProcessJob");
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
		// 更新作业
		addSpacecdEventJobRel(eventIdList, job, usage_code, project_code);
		String user_name = userService.getUserNameByUserId(job.getUser_iidd());
		String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
		String detail = "提交" + jobType.getJobTypeName() + "申请";
		ProcessRecord record = new ProcessRecord(job.getJob_code(), job.getJobType(), job.getUser_iidd(), user_name,
				dept_name, detail, "请审批", new Date());
		activitiService.addProcessRecord(record);
		// 如果有审批流程，在消息表中添加审批消息
		if (process.getTotal_steps() != 0) {
			String message = dept_name + user_name + "有" + jobType.getJobTypeName() + "作业需要您审批";
			String oper_type = jobType.getJobTypeCode();
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(), message,
						new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
			/*
			 * updateEntitySpaceCdJobRel(user_iidd, eventIdList, seclv_code, job, usage_code, project_code, comment,
			 * fileSeclvMap); } else { updateEntitySpaceCdJobRelPass(user_iidd, eventIdList, seclv_code, job,
			 * usage_code, project_code, comment, fileSeclvMap);
			 */
		}

	}

	private void addSpacecdEventJobRel(List<String> eventIdList, ProcessJob job, String usage_code, String project_code) {
		logger.debug("addSpaceCDEventJobRel");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("job_code", job.getJob_code());
		map.put("usage_code", usage_code);
		map.put("project_code", project_code);
		for (String id : eventIdList) {
			map.put("id", id);
			discMapper.addSpaceCDEventJobRel(map);
		}
	}

	private void updateEntitySpaceCdJobRel(String user_iidd, List<String> event_ids, Integer seclv_code,
			ProcessJob job, String usage_code, String project_code, String comment, Map<String, String> fileSeclvMap) {
		logger.debug("updateEntitySpaceCdJobRel");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("job_code", job.getJob_code());
		SecUser user = userService.getSecUserByUid(user_iidd);
		for (String id : event_ids) {
			map.put("id", id);
			if (fileSeclvMap.get(id) != null) {
				map.put("seclv_code", Integer.parseInt(fileSeclvMap.get(id)));
			} else {
				map.put("seclv_code", seclv_code);
			}
			map.put("duty_user_iidd", user.getUser_iidd());
			map.put("duty_user_name", user.getUser_name());
			map.put("duty_dept_id", user.getDept_id());
			map.put("duty_dept_name", user.getDept_name());
			discMapper.updateSpaceCdJobById(map);
		}
	}

	private void updateEntitySpaceCdJobRelPass(String user_iidd, List<String> ids, Integer seclv_code, ProcessJob job,
			String usage_code, String project_code, String comment, Map<String, String> fileSeclvMap) {
		logger.debug("updateEntitySpaceCdJobRelPass");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("job_code", job.getJob_code());
		SecUser user = userService.getSecUserByUid(user_iidd);
		for (String id : ids) {
			map.put("id", id);
			if (fileSeclvMap.get(id) != null) {
				map.put("seclv_code", Integer.parseInt(fileSeclvMap.get(id)));
			} else {
				map.put("seclv_code", seclv_code);
			}
			map.put("duty_user_iidd", user.getUser_iidd());
			map.put("duty_user_name", user.getUser_name());
			map.put("duty_dept_id", user.getDept_id());
			map.put("duty_dept_name", user.getDept_name());
			discMapper.updateSpaceCdJobByIdPass(map);
		}
	}

	@Override
	public List<EntitySpaceCD> getSendEntitySpaceCdList(Map<String, Object> map) {
		logger.debug("getSendEntitySpaceCdList");
		return discMapper.getSendEntitySpaceCdList(map);
	}

	@Override
	public void cancelHandleSpaceCDById(String id) {
		logger.debug("cancelHandleSpaceCDById");
		// 先查询出该spacecd对应的job_code
		EntitySpaceCD cd = discMapper.getEntitySpaceCdById(id);
		String job_code = cd.getJob_code();
		// 把event对应的job_code设置为null,取消审批；并把cd_state置为0,回到留用状态
		discMapper.cancelHandleSpaceCDById(id);
		// 如果该job_code对应的event数量为空，表示该任务下挂载的作业已经都被取消了，则取消该任务
		if (StringUtils.hasLength(job_code) && discMapper.getHandleSpaceCDCountByJobCode(job_code) == 0) {
			String instance_id = basicService.getProcessJobByCode(job_code).getInstance_id();
			basicPrcManage.suspendProcessInstanceById(instance_id);
			basicService.delJob(job_code);
			clientService.delClientMsgByJobCode(job_code);
		}
	}

	@Override
	public List<EntitySpaceCD> getEntitySpaceCdListByJobCode(String job_code) {
		logger.debug("getEntitySpaceCdListByJobCode");
		return discMapper.getEntitySpaceCdListByJobCode(job_code);
	}

	@Override
	public void updateSpaceCdState(Map<String, Object> map) {
		logger.debug("updateSpaceCdState");
		discMapper.updateSpaceCdState(map);
	}

	@Override
	public void updateSpaceCdChangeSelf(Map<String, Object> map) {
		logger.debug("updateSpaceCdChangeSelf");
		discMapper.updateSpaceCdChangeSelf(map);
	}

	@Override
	public void addCycleItem(CycleItem cycleitem) {
		logger.debug("addCycleItem");
		discMapper.addCycleItem(cycleitem);
	}

	@Override
	public void addCDledger(EntityCD cd) {
		logger.debug("addCDledger" + cd.getCd_barcode());
		discMapper.addCDledger(cd);

	}

	@Override
	public void updateSpaceCdReturn(Map<String, Object> map) {
		logger.debug("updateSpaceCdReturn");
		discMapper.updateSpaceCdReturn(map);
	}

	@Override
	public List<SpaceCDEvent> getSpaceCDEventList(Map<String, Object> map) {
		logger.debug("getSpaceCDEventList");
		return discMapper.getSpaceCDEventList(map);
	}

	@Override
	public void cancelSpaceEventByJobCode(String job_code) {
		discMapper.cancelSpaceEventByJobCode(job_code);
	}

	@Override
	public int getSpaceCDEventEnterCancel(String job_code) {
		return discMapper.getSpaceCDEventEnterCancel(job_code);
	}

	@Override
	public SpaceCDEvent getSpaceCDEventByEventCode(String event_code) {
		logger.debug("getSpaceCDEventByEventCode" + event_code);
		return discMapper.getSpaceCDEventByEventCode(event_code);
	}

	@Override
	public void addSpaceCDEvent(SpaceCDEvent event) {
		logger.debug("addSpaceCDEvent" + event.getEvent_code());
		discMapper.addSpaceCDEvent(event);
	}

	@Override
	public void delSpaceCDEventByEventCode(String event_code) {
		logger.debug("delSpaceCDEventByEventCode" + event_code);
		discMapper.delSpaceCDEventByEventCode(event_code);
	}

	@Override
	public void delSpaceCDEventById(String id) {
		logger.debug("delSpaceCDEventById" + id);
		discMapper.delSpaceCDEventById(id);
	}

	// 更新空白盘作业表中数据
	@Override
	public void updateSpaceCdEvent(Map<String, Object> map) {
		logger.debug("updateSpaceCdEvent");
		discMapper.updateSpaceCdEvent(map);
	}

	/*
	 * 未分配未喷绘未使用空白盘盘台账退回
	 * 
	 * @param event_code
	 */
	@Override
	public void deleteSpaceCdEntityById(String id) {
		logger.debug("deleteSpaceCdEntityById" + id);
		discMapper.deleteSpaceCdEntityById(id);
	}
}
