package hdsec.web.project.arch.service;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.activiti.service.ActivitiService;
import hdsec.web.project.activiti.util.ActivitiCons;
import hdsec.web.project.arch.mapper.ArchMapper;
import hdsec.web.project.arch.model.ArchDict;
import hdsec.web.project.arch.model.ArchKey;
import hdsec.web.project.arch.model.ArchStatus;
import hdsec.web.project.arch.model.ArchTypeName;
import hdsec.web.project.arch.model.ArchValue;
import hdsec.web.project.arch.model.BorrowLen;
import hdsec.web.project.arch.model.Dossier;
import hdsec.web.project.arch.model.EventArchBrw;
import hdsec.web.project.arch.model.EventStatus;
import hdsec.web.project.arch.model.Item;
import hdsec.web.project.basic.mapper.BasicMapper;
import hdsec.web.project.basic.model.ClientMsg;
import hdsec.web.project.basic.service.BasicPrcManage;
import hdsec.web.project.basic.service.BasicService;
import hdsec.web.project.borrow.service.BorrowService;
import hdsec.web.project.client.service.ClientService;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.service.UserService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

/**
 * @author handouwang
 * 
 *         2015-7-26/
 */
public class ArchServiceImpl implements ArchService {
	@Resource
	private ArchMapper archMapper;
	@Resource
	protected BasicService basicService;
	@Resource
	private ActivitiService activitiService;
	@Resource
	private BasicPrcManage basicPrcManage;
	@Resource
	private BasicMapper basicMapper;
	@Resource
	private UserService userService;
	@Resource
	private ClientService clientService;
	@Resource
	protected BorrowService borrowService;
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void addArchKey(Map<String, Object> map) {
		archMapper.addArchKey(map);
	}

	@Override
	public List<ArchTypeName> getTypeList(Map<String, String> map) {
		return archMapper.getTypeList(map);
	}

	@Override
	public void addType(Map<String, Object> map) {
		archMapper.addType(map);
	}

	@Override
	public void updateArchType(Map<String, Object> map) {
		archMapper.updateArchType(map);
	}

	@Override
	public ArchKey getTemplateBySystemCode(String template_id) {
		return archMapper.getTemplateBySystemCode(template_id);
	}

	@Override
	public List<ArchDict> getAllArchDictList() {
		logger.debug("getAllArchDictList");
		return archMapper.getAllArchDictList();
	}

	@Override
	public boolean isDictExistByName(String dict_name) {
		logger.debug("isDictExistByName:" + dict_name);
		return archMapper.getDictCountByName(dict_name) > 0;
	}

	@Override
	public void addDict(ArchDict dict) {
		logger.debug("addDict:" + dict.getDict_name());
		archMapper.addDict(dict);
	}

	@Override
	public void updateDict(ArchDict dict) {
		logger.debug("updateDict:" + dict.getDict_name());
		archMapper.updateDict(dict);
	}

	@Override
	public ArchDict getDictById(int id) {
		logger.debug("getDictById:" + id);
		return archMapper.getDictById(id);
	}

	@Override
	public void setDictUsed(String id, int is_used) {
		logger.debug("setDictUsed:" + id + "/" + is_used);
		ArchDict dict = new ArchDict();
		dict.setId(id);
		dict.setIs_used(is_used);
		archMapper.setDictUsed(dict);
	}

	@Override
	public List<BorrowLen> getUsedSeclvList() {
		return archMapper.getUsedSeclvList();
	}

	@Override
	public void setBorrowLen(int seclv_code, int length) {
		logger.debug("setBorrowLen:" + seclv_code + "/" + length);
		BorrowLen len = new BorrowLen();
		len.setSeclv_code(seclv_code);
		len.setLength(length);
		if (archMapper.getBorrowLenCount(seclv_code) == 0) {
			archMapper.insertBorrowLen(len);
		} else {
			archMapper.updateBorrowLen(len);
		}
	}

	@Override
	public void updateArchPropertyValue(Map<String, Object> map) {
		archMapper.updateArchPropertyValue(map);
	}

	@Override
	public int getArchListSize(Map<String, Object> map) {
		return archMapper.getArchListSize(map);
	}

	@Override
	public List<ArchValue> getArchList(Map<String, Object> map, RowBounds rbs) {
		return archMapper.getArchList(map);
	}

	@Override
	public ArchValue getArchValueById(String id) {
		return archMapper.getArchValueById(Integer.parseInt(id.trim()));
	}

	@Override
	public List<ArchTypeName> getArchTypeTree() {
		List<ArchTypeName> typeList = getTypeList(null);
		for (ArchTypeName type : typeList) {
			type.setItemList(getItemListByTypeId(type.getID()));
		}
		return typeList;
	}

	private List<Item> getItemListByTypeId(int typeId) {
		List<Item> itemList = archMapper.getItemListByTypeId(typeId);
		for (Item it : itemList) {
			it.setDosList(getDosListByItemId(it.getId()));
		}
		return itemList;
	}

	private List<Dossier> getDosListByItemId(int itemId) {
		return archMapper.getDosListByItemId(itemId);
	}

	@Override
	public ArchTypeName getArchTypeNameById(int id) {
		return archMapper.getArchTypeNameById(id);
	}

	@Override
	public boolean isItemExistByCode(String item_code) {
		return archMapper.getItemCountByCode(item_code) > 0;
	}

	@Override
	public void addItem(Item item) {
		archMapper.addItem(item);
	}

	@Override
	public Item getItemById(int id) {
		return archMapper.getItemById(id);
	}

	@Override
	public boolean isDosExistByCode(String dos_code) {
		return archMapper.getDosCountByCode(dos_code) > 0;
	}

	@Override
	public void addDossier(Dossier dos) {
		archMapper.addDossier(dos);

	}

	@Override
	public Dossier getDosById(int id) {
		return archMapper.getDosById(id);
	}

	@Override
	public String getTemplateIdByDosId(int dos_id) {
		return archMapper.getTemplateIdByDosId(dos_id);
	}

	@Override
	public void saveArchProperty(Map<String, Object> archValueMap) {
		archMapper.saveArchProperty(archValueMap);
	}

	@Override
	public void updateDossier(Dossier dos) {
		archMapper.updateDossier(dos);
	}

	@Override
	public int getArchNumByDos(int id) {
		return archMapper.getArchNumByDos(id);
	}

	@Override
	public void deleteDosById(int id) {
		archMapper.deleteDosById(id);
	}

	@Override
	public List<ArchTypeName> getValidTypeList() {
		return archMapper.getValidTypeList();
	}

	@Override
	public void addBorrowProcessJob(String user_iidd, String dept_id,
			Integer seclv_code, JobTypeEnum jobType, String next_approver,
			String comment, String event_codes, String usage_code,
			String entity_type, String barcode, String filename)
			throws Exception {
		logger.debug("addBorrowProcessJob");
		ApproveProcess process = basicService.getApproveProcessByKey(dept_id,
				seclv_code, jobType.getJobTypeCode(), usage_code);
		String job_status = null;
		if (process.getTotal_steps() == 0) {
			job_status = ActivitiCons.APPLY_APPROVED_PASS;
			Map<String, Object> propertyMap = new HashMap<String, Object>();
			propertyMap.put("property_name", "STATUS");
			propertyMap.put("propertyValue", ArchStatus.WAITING);
			propertyMap.put("barcode", barcode);
			updateArchValuePropertyValue(propertyMap);
		} else {
			job_status = ActivitiCons.APPLY_APPROVED_DEFAULT;
		}
		String next_approver_name = basicService.getApproverName(next_approver);
		for (String event_code : event_codes.split(":")) {
			if (StringUtils.hasLength(event_code.trim())) {
				String job_code = user_iidd + "-" + jobType.getJobTypeCode()
						+ "-" + System.currentTimeMillis();
				ProcessJob job = new ProcessJob(job_code, user_iidd, dept_id,
						seclv_code, jobType, new Date(), job_status,
						next_approver, next_approver_name,
						process.getProcess_id());
				job.setComment(comment);
				// 开启流程
				basicPrcManage.addActivitiApply(job, process);
				// 把任务信息插入数据库
				basicMapper.addProcessJob(job);
				// 更新作业信息插入 event表==============================================

				String arch_record_id = getTemplateIdByBarcode(barcode);
				int id = getRecordIdByBarcode(barcode);
				int arch_type_id = getTypeNameIdByTemplateId(arch_record_id);
				int dos_id = getDosIdByBarcode(barcode);
				int item_id = getItemIdByDosId(dos_id);
				addEventJobRel(event_code, user_iidd, dept_id, seclv_code,
						barcode, filename, usage_code, new Date(), 0, job_code,
						arch_type_id, id, item_id, dos_id);
				String user_name = userService.getUserNameByUserId(job
						.getUser_iidd());
				String dept_name = userService.getDeptNameByDeptId(job
						.getDept_id());
				String detail = "提交" + jobType.getJobTypeName() + "申请";
				ProcessRecord record = new ProcessRecord(job.getJob_code(),
						job.getJobType(), job.getUser_iidd(), user_name,
						dept_name, detail, "请审批", new Date());
				activitiService.addProcessRecord(record);
				if (process.getTotal_steps() != 0) {
					Map<String, Object> propertyMap = new HashMap<String, Object>();
					propertyMap.put("property_name", "STATUS");
					propertyMap.put("propertyValue", ArchStatus.LOCKED);
					propertyMap.put("barcode", barcode);
					updateArchValuePropertyValue(propertyMap);
					String message = dept_name + user_name + "有"
							+ jobType.getJobTypeName() + "作业需要您审批";
					String oper_type = jobType.getJobTypeCode();
					for (String item : next_approver.split(",")) {
						String nextApproverName = basicPrcManage
								.getApproverName(item);
						ClientMsg clientMsg = new ClientMsg(item,
								nextApproverName, oper_type, 1,
								job.getJob_code(), message, new Date(), 0);
						basicMapper.addClientMsg(clientMsg);
					}
				}
			}
		}
	}

	@Override
	public void addRenewProcessJob(String user_iidd, String dept_id,
			Integer seclv_code, JobTypeEnum jobType, String next_approver,
			String comment, String event_codes, String usage_code,
			String entity_type, String barcode, String filename)
			throws Exception {
		logger.debug("addRenewProcessJob");
		ApproveProcess process = basicService.getApproveProcessByKey(dept_id,
				seclv_code, jobType.getJobTypeCode(), usage_code);
		String job_status = null;
		if (process.getTotal_steps() == 0) {
			job_status = ActivitiCons.APPLY_APPROVED_PASS;
		} else {
			job_status = ActivitiCons.APPLY_APPROVED_DEFAULT;
		}
		String next_approver_name = basicService.getApproverName(next_approver);
		for (String event_code : event_codes.split(":")) {
			if (StringUtils.hasLength(event_code.trim())) {
				String job_code = user_iidd + "-" + jobType.getJobTypeCode()
						+ "-" + System.currentTimeMillis();
				ProcessJob job = new ProcessJob(job_code, user_iidd, dept_id,
						seclv_code, jobType, new Date(), job_status,
						next_approver, next_approver_name,
						process.getProcess_id());
				job.setComment(comment);
				// 开启流程
				basicPrcManage.addActivitiApply(job, process);
				// 把任务信息插入数据库
				basicMapper.addProcessJob(job);
				// 更新作业信息插入 event表==============================================

				String arch_record_id = getTemplateIdByBarcode(barcode);
				int id = getRecordIdByBarcode(barcode);
				int arch_type_id = getTypeNameIdByTemplateId(arch_record_id);
				int dos_id = getDosIdByBarcode(barcode);
				int item_id = getItemIdByDosId(dos_id);
				addEventJobRel(event_code, user_iidd, dept_id, seclv_code,
						barcode, filename, usage_code, new Date(),
						EventStatus.UNRENEW, job_code, arch_type_id, id,
						item_id, dos_id);
				String user_name = userService.getUserNameByUserId(job
						.getUser_iidd());
				String dept_name = userService.getDeptNameByDeptId(job
						.getDept_id());
				String detail = "提交" + jobType.getJobTypeName() + "申请";
				ProcessRecord record = new ProcessRecord(job.getJob_code(),
						job.getJobType(), job.getUser_iidd(), user_name,
						dept_name, detail, "请审批", new Date());
				activitiService.addProcessRecord(record);
				if (process.getTotal_steps() != 0) {
					String message = dept_name + user_name + "有"
							+ jobType.getJobTypeName() + "作业需要您审批";
					String oper_type = jobType.getJobTypeCode();
					for (String item : next_approver.split(",")) {
						String nextApproverName = basicPrcManage
								.getApproverName(item);
						ClientMsg clientMsg = new ClientMsg(item,
								nextApproverName, oper_type, 1,
								job.getJob_code(), message, new Date(), 0);
						basicMapper.addClientMsg(clientMsg);
					}
				}
			}
		}
	}

	public int getItemIdByDosId(int dos_id) {
		return archMapper.getItemIdByDosId(dos_id);
	}

	public int getDosIdByBarcode(String barcode) {
		return archMapper.getDosIdByBarcode(barcode);
	}

	@Override
	public List<EventArchBrw> getArchBrwEventList(Map<String, Object> map) {
		return archMapper.getArchBrwEventList(map);
	}

	@Override
	public void setArchLent(int event_id, String user_iidd) throws Exception {
		// 查询event
		EventArchBrw event = archMapper.getArchBrwEventById(event_id);
		// 查询对应的档案
		ArchValue arch = getArchValueById(event.getAt_record_id() + "");
		Integer days = archMapper.getBorrowLenBySeclvName(arch.getSeclv_code());
		if (days == null) {// 未设置借用期限
			throw new Exception("密级[" + arch.getSeclv_code() + "]未设置借用期限。");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", event_id);
		map.put("lend_user_iidd", user_iidd);
		map.put("days", days);
		// 更改event中status的状态1
		archMapper.setEventStatusLent(map);
		// 更改档案状态为LENT,保管人user_iidd和dept_id
		archMapper.setArchStatusLent(event);
		// 记录借出记录
		archMapper.addArchBrwRecord(event);
	}

	@Override
	public void setArchRenew(int event_id, String user_iidd) throws Exception {
		// 查询event
		EventArchBrw event = archMapper.getArchBrwEventById(event_id);
		// 查询对应的档案
		ArchValue arch = getArchValueById(event.getAt_record_id() + "");
		Integer days = archMapper.getBorrowLenBySeclvName(arch.getSeclv_code());
		if (days == null) {// 未设置借用期限
			throw new Exception("密级[" + arch.getSeclv_code() + "]未设置借用期限。");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", event_id);
		map.put("lend_user_iidd", user_iidd);
		map.put("days", days);
		archMapper.setEventStatusRenew(map);
		// 更新原来记录的时间
		archMapper.setEventDateRenew(map);
		// 记录借出记录
		archMapper.addArchBrwRecordRenew(event);
	}

	@Override
	public void setArchReturn(int event_id) {
		// 更改event中status的状态为2
		archMapper.setEventStatusReturn(event_id);
		// 更改档案状态为AVAILABLE,保管人user_iidd和dept_id置为空
		EventArchBrw event = archMapper.getArchBrwEventById(event_id);
		archMapper.setArchStatusReturn(event);
		// 记录归还记录
		archMapper.addArchReturnRecord(event);
	}

	@Override
	public String getDosIdByCode(String dos_code) throws Exception {
		List<Integer> ids = archMapper.getDosIdByCode(dos_code.trim());
		if (ids != null && ids.size() > 0) {
			return String.valueOf(ids.get(0));
		} else {
			throw new Exception("No dossier with dos_code[" + dos_code
					+ "] is available");
		}
	}

	@Override
	public String getTemplateIdByBarcode(String barcode) {
		return archMapper.getTemplateIdByBarcode(barcode);
	}

	@Override
	public int getTypeNameIdByTemplateId(String templateId) {
		return archMapper.trgetTypeNameIdByTemplateId(templateId);
	}

	@Override
	public int getRecordIdByBarcode(String barcode) {
		return archMapper.getRecordIdByBarcode(barcode);
	}

	@Override
	public int getItemIdByTypeId(int typeId) {
		return archMapper.getItemIdByTypeId(typeId);
	}

	@Override
	public int getDosIdByItemId(int itemId) {
		return archMapper.getDosIdByItemId(itemId);
	}

	@Override
	public void addEnventArch(Map<String, Object> map) {
		archMapper.addEnventArch(map);
	}

	@Override
	public void updateArchValuePropertyValue(Map<String, Object> map) {
		archMapper.updateArchValuePropertyValue(map);
	}

	@Override
	public List<EventArchBrw> getEventArchListByJobCode(String job_code) {
		return archMapper.getEventArchListByJobCode(job_code);
	}

	// =========================================================================================

	@Override
	public void approveJob(String job_code, ApproverUser user,
			ApproverUser approver, String approved, String opinion,
			String entitytype) throws Exception {
		if (basicService.getProcessJobByCode(job_code) != null) {
			ProcessJob job = basicService.getProcessJobByCode(job_code);// 查询任务信息
			claimExTask(job, user.getUser_iidd());// 领取任务（activiti要求的操作）
			job.setNext_approver(approver.getUser_iidd());// 设置下级审批人
			job.setNext_approver_name(approver.getUser_name());
			// 根据流程变量中保存的审批级数的状态，判断审批是否已经结束
			Map<String, Object> variables = basicPrcManage
					.getProcessVariables(job.getInstance_id());
			Integer total_approval = (Integer) variables.get("total_approval");
			Integer cur_approval = (Integer) variables.get("cur_approval") + 1;
			// 在插入下级审批消息或审批完成消息之前，置对应job_code的消息为已读
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("job_code", job_code);
			map.put("is_read", 1);
			map.put("read_time", new Date());
			clientService.delClientMsgByJobCode(job_code);
			int tag = 1;
			Integer approveResultValue = 0;
			if (approved.equals(ActivitiCons.APPLY_APPROVED_PASS)
					&& (cur_approval <= total_approval)) {
				job.setJob_status(ActivitiCons.APPLY_APPROVED_UNDER);
				// 如果审批流程未结束，在消息表中添加审批消息
				String message = job.getDept_name() + job.getUser_name() + "有"
						+ job.getJobType().getJobTypeName() + "作业需要您审批";
				String oper_type = "BRWARCH";
				for (String item : approver.getUser_iidd().split(",")) {
					String nextApproverName = basicPrcManage
							.getApproverName(item);
					ClientMsg clientMsg = new ClientMsg(item, nextApproverName,
							oper_type, 1, job_code, message, new Date(), 0);
					basicMapper.addClientMsg(clientMsg);
				}

			} else {
				// 两种可能 一是通过了并且没有审批了
				// 没有通过
				job.setJob_status(approved);
				// 审批流程结束，在消息表中添加审批完成消息

				String approveResult = "";

				if (approved.equals(ActivitiCons.APPLY_APPROVED_PASS)) {
					approveResultValue = 2;
					approveResult = "通过";
					tag = ArchStatus.WAITING;
				} else {
					approveResultValue = 3;
					approveResult = "不通过";
					tag = ArchStatus.AVAILABLE;
				}
				String message = "您的" + job.getJobType().getJobTypeName()
						+ "作业已审批：" + approveResult;
				String oper_type = "BRWARCH";
				ClientMsg clientMsg = new ClientMsg(job.getUser_iidd(),
						job.getUser_name(), oper_type, approveResultValue,
						job_code, message, new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
			// 更新流程中的审批状态
			basicPrcManage.approveApply(job.getInstance_id(),
					user.getUser_iidd(), approver.getUser_iidd(), approved);
			// 更新数据库状态
			basicMapper.approveJobTask(job);
			// 更新event_archbrw
			String barcode = getBarcodeByJobCodeFromEventArchBrw(job_code);
			EventArchBrw eventArchBrw = archMapper
					.getArchEventByjobCode(job_code);
			// 借阅电子 则不需要更新状态
			String type = eventArchBrw.getArche_type();
			// if("1".equals(type) && 3 == approveResultValue){
			Map<String, Object> propertyMap = new HashMap<String, Object>();
			propertyMap.put("property_name", "STATUS");
			propertyMap.put("propertyValue", tag);
			propertyMap.put("barcode", barcode);
			updateArchValuePropertyValue(propertyMap);
			// }

			// 记录审批意见
			String dept_name = userService.getDeptNameByUserId(user
					.getUser_iidd());
			String operation = "审批";
			if (approved.equals(ActivitiCons.APPLY_APPROVED_PASS)) {
				operation += "：同意";
			} else {
				operation += "：不同意";
			}
			ProcessRecord record = new ProcessRecord(job_code,
					job.getJobType(), user.getUser_iidd(), user.getUser_name(),
					dept_name, operation, opinion, new Date());
			activitiService.addProcessRecord(record);
		} else
			throw new Exception("该审批任务已被用户取消！");
	}

	@Override
	public String getBarcodeByJobCodeFromEventArchBrw(String job_code) {
		return archMapper.getBarcodeByJobCodeFromEventArchBrw(job_code);
	}

	// =================================================================================================
	private void addEventJobRel(String event_code, String user_iidd,
			String dept_id, int seclv_code, String barcode, String filename,
			String usage_code, Date apply_time, int borrow_status,
			String job_code, int arch_type_id, int arch_record_id, int item_id,
			int dos_id) {
		logger.debug("addArchEventJobRel");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("event_code", event_code);
		map.put("user_iidd", user_iidd);
		map.put("dept_id", dept_id);
		map.put("seclv_code", seclv_code);
		map.put("usage_code", usage_code);
		map.put("barcode", barcode);
		map.put("file_title", filename);
		map.put("apply_time", apply_time);
		map.put("borrow_status", borrow_status);
		map.put("job_code", job_code);
		map.put("arch_type_id", arch_type_id);
		map.put("arch_record_id", arch_record_id);
		map.put("item_id", item_id);
		map.put("dos_id", dos_id);
		addEnventArch(map);
	}

	private void addEventJobRel(String event_code, String user_iidd,
			String dept_id, int seclv_code, String barcode, String filename,
			String usage_code, Date apply_time, int borrow_status,
			String job_code, int arch_type_id, int arch_record_id, int item_id,
			int dos_id, String arche_type) {
		logger.debug("addArchEventJobRel");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("event_code", event_code);
		map.put("user_iidd", user_iidd);
		map.put("dept_id", dept_id);
		map.put("seclv_code", seclv_code);
		map.put("usage_code", usage_code);
		map.put("barcode", barcode);
		map.put("file_title", filename);
		map.put("apply_time", apply_time);
		map.put("borrow_status", borrow_status);
		map.put("job_code", job_code);
		map.put("arch_type_id", arch_type_id);
		map.put("arch_record_id", arch_record_id);
		map.put("item_id", item_id);
		map.put("dos_id", dos_id);
		map.put("arche_type", arche_type);
		addEnventArch(map);
	}

	private void claimExTask(ProcessJob job, String user_iidd) throws Exception {
		basicPrcManage.claimtask(job, user_iidd);
		basicMapper.claimJobTask(job);
	}

	@Override
	public ArchValue getLastInsertArchByTemplate(String template_id) {
		return archMapper.getLastInsertArchByTemplate(template_id);
	}

	@Override
	public int getCountArchBarcodeByArchbarcode(String arch_barcode) {
		return archMapper.getCountArchBarcodeByArchbarcode(arch_barcode);
	}

	@Override
	public int cancelArchEventByEventCode(String event_code) {
		// 先查询出该event对应的job_code
		EventArchBrw event = archMapper.getArchEventByEnterCode(event_code);
		String job_code = event.getJob_code();
		// 把event对应的job_code设置为null,取消审批
		// archMapper.cancelArchEventByEventCode(event_code);
		// 通过任务号删除作业
		delArchEventByJobCode(event.getJob_code());
		// 如果该job_code对应的event数量为空，表示该任务下挂载的作业已经都被取消了，则取消该任务
		if (StringUtils.hasLength(job_code)
				&& archMapper.getArchEventCountByJobCode(job_code) == 0) {
			cancelJob(event, event.getJobType().getJobTypeCode());
			return 1;
		}
		return 0;
	}

	private void cancelJob(EventArchBrw event, String jobType_code) {
		logger.debug("cancelJob");
		String instance_id = basicService.getProcessJobByCode(
				event.getJob_code()).getInstance_id();
		// 将档案置为在档可借
		if (event.getBorrow_status() != EventStatus.UNRENEW) {
			resetArchStatus(event.getAt_record_id());
		}
		// 挂起流程
		basicPrcManage.suspendProcessInstanceById(instance_id);
		// 删除作业
		basicMapper.delJob(event.getJob_code());
		// 删除消息
		clientService.delClientMsgByJobCode(event.getJob_code());
	}

	private void delArchEventByJobCode(String job_code) {
		archMapper.delArchEventByJobCode(job_code);
	}

	private void resetArchStatus(Integer at_record_id) {
		archMapper.resetArchStatus(at_record_id);
	}

	@Override
	public void approveRenewJob(String job_code, ApproverUser user,
			ApproverUser approver, String approved, String opinion,
			String entitytype) throws Exception {
		if (basicService.getProcessJobByCode(job_code) != null) {
			ProcessJob job = basicService.getProcessJobByCode(job_code);// 查询任务信息
			claimExTask(job, user.getUser_iidd());// 领取任务（activiti要求的操作）
			job.setNext_approver(approver.getUser_iidd());// 设置下级审批人
			job.setNext_approver_name(approver.getUser_name());
			// 根据流程变量中保存的审批级数的状态，判断审批是否已经结束
			Map<String, Object> variables = basicPrcManage
					.getProcessVariables(job.getInstance_id());
			Integer total_approval = (Integer) variables.get("total_approval");
			Integer cur_approval = (Integer) variables.get("cur_approval") + 1;
			// 在插入下级审批消息或审批完成消息之前，置对应job_code的消息为已读
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("job_code", job_code);
			map.put("is_read", 1);
			map.put("read_time", new Date());
			clientService.delClientMsgByJobCode(job_code);
			if (approved.equals(ActivitiCons.APPLY_APPROVED_PASS)
					&& (cur_approval <= total_approval)) {
				job.setJob_status(ActivitiCons.APPLY_APPROVED_UNDER);
				// 如果审批流程未结束，在消息表中添加审批消息
				String message = job.getDept_name() + job.getUser_name() + "有"
						+ job.getJobType().getJobTypeName() + "作业需要您审批";
				String oper_type = "BRWARCH";
				for (String item : approver.getUser_iidd().split(",")) {
					String nextApproverName = basicPrcManage
							.getApproverName(item);
					ClientMsg clientMsg = new ClientMsg(item, nextApproverName,
							oper_type, 1, job_code, message, new Date(), 0);
					basicMapper.addClientMsg(clientMsg);
				}

			} else {
				// 两种可能 一是通过了并且没有审批了
				// 没有通过
				job.setJob_status(approved);
				// 审批流程结束，在消息表中添加审批完成消息
				Integer approveResultValue = 0;
				String approveResult = "";

				if (approved.equals(ActivitiCons.APPLY_APPROVED_PASS)) {
					approveResultValue = 2;
					approveResult = "通过";
				} else {
					approveResultValue = 3;
					approveResult = "不通过";
				}
				String message = "您的" + job.getJobType().getJobTypeName()
						+ "作业已审批：" + approveResult;
				String oper_type = "BRWARCH";
				ClientMsg clientMsg = new ClientMsg(job.getUser_iidd(),
						job.getUser_name(), oper_type, approveResultValue,
						job_code, message, new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
			// 更新流程中的审批状态
			basicPrcManage.approveApply(job.getInstance_id(),
					user.getUser_iidd(), approver.getUser_iidd(), approved);
			// 更新数据库状态
			basicMapper.approveJobTask(job);
			// 记录审批意见
			String dept_name = userService.getDeptNameByUserId(user
					.getUser_iidd());
			String operation = "审批";
			if (approved.equals(ActivitiCons.APPLY_APPROVED_PASS)) {
				operation += "：同意";
			} else {
				operation += "：不同意";
			}
			ProcessRecord record = new ProcessRecord(job_code,
					job.getJobType(), user.getUser_iidd(), user.getUser_name(),
					dept_name, operation, opinion, new Date());
			activitiService.addProcessRecord(record);
		} else
			throw new Exception("该审批任务已被用户取消！");
	}

	@Override
	public void deleteArchById(String id) {
		archMapper.deleteArchById(id);
	}

	@Override
	public int getArchDeleteListSize(Map<String, Object> map) {
		return archMapper.getArchDeleteListSize(map);
	}

	@Override
	public List<ArchValue> getArchDeleteList(Map<String, Object> map,
			RowBounds rbs) {
		return archMapper.getArchDeleteList(map);
	}

	@Override
	public void deleteThoroughArchById(String id) {
		archMapper.deleteThoroughArchById(id);
	}

	@Override
	public void updateArchProperty(Map<String, Object> archValueMap) {
		archMapper.updateArchProperty(archValueMap);
	}

	@Override
	public void addArcAskingToReturnClientMsg(Map<String, Object> map) {
		archMapper.addArcAskingToReturnClientMsg(map);
	}

	@Override
	public void delAskToReturnArchClientMsg(Map<String, Object> map) {
		archMapper.delAskToReturnArchClientMsg(map);
	}

	/*
	 * 重载一下 增加借阅类型arche_type
	 * 
	 * @see
	 * hdsec.web.project.arch.service.ArchService#addBorrowProcessJob(java.lang
	 * .String, java.lang.String, java.lang.Integer,
	 * hdsec.web.project.activiti.model.JobTypeEnum, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */

	@Override
	public void addBorrowProcessJob(String user_iidd, String dept_id,
			Integer seclv_code, JobTypeEnum jobType, String next_approver,
			String comment, String event_codes, String usage_code,
			String entity_type, String barcode, String filename,
			String arche_type) throws Exception {
		logger.debug("addBorrowProcessJob");
		ApproveProcess process = basicService.getApproveProcessByKey(dept_id,
				seclv_code, jobType.getJobTypeCode(), usage_code);
		String job_status = null;
		if (process.getTotal_steps() == 0) {
			job_status = ActivitiCons.APPLY_APPROVED_PASS;
			Map<String, Object> propertyMap = new HashMap<String, Object>();
			propertyMap.put("property_name", "STATUS");
			propertyMap.put("propertyValue", ArchStatus.WAITING);
			propertyMap.put("barcode", barcode);
			updateArchValuePropertyValue(propertyMap);
		} else {
			job_status = ActivitiCons.APPLY_APPROVED_DEFAULT;
		}
		String next_approver_name = basicService.getApproverName(next_approver);
		for (String event_code : event_codes.split(":")) {
			if (StringUtils.hasLength(event_code.trim())) {
				String job_code = user_iidd + "-" + jobType.getJobTypeCode()
						+ "-" + System.currentTimeMillis();
				ProcessJob job = new ProcessJob(job_code, user_iidd, dept_id,
						seclv_code, jobType, new Date(), job_status,
						next_approver, next_approver_name,
						process.getProcess_id());
				job.setComment(comment);
				// 开启流程
				basicPrcManage.addActivitiApply(job, process);
				// 把任务信息插入数据库
				basicMapper.addProcessJob(job);
				// 更新作业信息插入 event表==============================================

				String arch_record_id = getTemplateIdByBarcode(barcode);
				int id = getRecordIdByBarcode(barcode);
				int arch_type_id = getTypeNameIdByTemplateId(arch_record_id);
				int dos_id = getDosIdByBarcode(barcode);
				int item_id = getItemIdByDosId(dos_id);
				addEventJobRel(event_code, user_iidd, dept_id, seclv_code,
						barcode, filename, usage_code, new Date(), 0, job_code,
						arch_type_id, id, item_id, dos_id, arche_type);
				String user_name = userService.getUserNameByUserId(job
						.getUser_iidd());
				String dept_name = userService.getDeptNameByDeptId(job
						.getDept_id());
				String detail = "提交" + jobType.getJobTypeName() + "申请";
				ProcessRecord record = new ProcessRecord(job.getJob_code(),
						job.getJobType(), job.getUser_iidd(), user_name,
						dept_name, detail, "请审批", new Date());
				activitiService.addProcessRecord(record);
				if (process.getTotal_steps() != 0) {
					// 借用电子档案，不需要锁定档案
					if (!"1".equals(arche_type)) {
						Map<String, Object> propertyMap = new HashMap<String, Object>();
						propertyMap.put("property_name", "STATUS");
						propertyMap.put("propertyValue", ArchStatus.LOCKED);
						propertyMap.put("barcode", barcode);
						updateArchValuePropertyValue(propertyMap);
					}
					String message = dept_name + user_name + "有"
							+ jobType.getJobTypeName() + "作业需要您审批";
					String oper_type = jobType.getJobTypeCode();
					for (String item : next_approver.split(",")) {
						String nextApproverName = basicPrcManage
								.getApproverName(item);
						ClientMsg clientMsg = new ClientMsg(item,
								nextApproverName, oper_type, 1,
								job.getJob_code(), message, new Date(), 0);
						basicMapper.addClientMsg(clientMsg);
					}
				}
			}
		}

	}
}
