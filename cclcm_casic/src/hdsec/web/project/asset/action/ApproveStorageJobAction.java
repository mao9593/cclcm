package hdsec.web.project.asset.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.asset.model.StorageEvent;
import hdsec.web.project.asset.model.WasteEvent;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.user.model.ApproverUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.util.StringUtils;

/**
 * 资产入库审批
 * 
 * @author gaoximin 2015-7-29
 */
public class ApproveStorageJobAction extends AssetBaseAction {
	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	private List<StorageEvent> eventList = null;
	private String approved = "";
	private String opinion = "";
	private String next_approver = "";
	private List<ApproverUser> userList = null;
	private String history = "";
	private StorageEvent event = null;
	private Integer listSize = null;
	private String opinion_all = "";
	private String identity_code = "";// 识别码
	private String property_no = "";// 资产编号
	private String voucher_no = "";// 凭证号
	private String event_code = "";

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getEvent_code() {
		return event_code;
	}

	public String getIdentity_code() {
		return identity_code;
	}

	public void setIdentity_code(String identity_code) {
		this.identity_code = identity_code;
	}

	public String getProperty_no() {
		return property_no;
	}

	public void setProperty_no(String property_no) {
		this.property_no = property_no;
	}

	public String getVoucher_no() {
		return voucher_no;
	}

	public void setVoucher_no(String voucher_no) {
		this.voucher_no = voucher_no;
	}

	public Integer getListSize() {
		return listSize;
	}

	public void setListSize(Integer listSize) {
		this.listSize = listSize;
	}

	public String getOpinion_all() {
		return opinion_all;
	}

	public void setOpinion_all(String opinion_all) {
		this.opinion_all = opinion_all;
	}

	public StorageEvent getEvent() {
		return event;
	}

	public String getJob_code() {
		return job_code;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public ProcessJob getJob() {
		return job;
	}

	public List<StorageEvent> getEventList() {
		return eventList;
	}

	public List<ProcessRecord> getRecordList() {
		return recordList;
	}

	public List<ApproverUser> getUserList() {
		return userList;
	}

	public ApproveProcess getProcess() {
		return process;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion + " ";
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver.replaceAll(" ", "");
	}

	public String getType() {
		return basicService.getJobTypeCodeByJobCode(job_code);
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	/**
	 * 去重
	 * 
	 * @param oriList
	 * @return
	 */
	private List<ApproverUser> removeDuplicateList(List<ApproverUser> oriList) {
		Set<String> set = new HashSet<String>();
		List<ApproverUser> newList = new ArrayList<ApproverUser>();
		for (ApproverUser item : oriList) {
			if (set.add(item.getUser_iidd())) {
				newList.add(item);
			}
		}
		return newList;
	}

	@Override
	public String executeFunction() throws Exception {
		if (history.equals("Y")) {
			job = basicService.getProcessJobByCode(job_code);
			process = basicPrcManage.getApproveProcessByInstanceId(job
					.getInstance_id());
			ProcessRecord record = new ProcessRecord();
			record.setJob_code(job_code);
			recordList = activitiService.getProcessRecordList(record);
			eventList = assetService.getStorageEventListByJobCode(job_code);
			event = eventList.get(0);
			listSize = recordList.size() - 1;
			for (int i = 1; i <= listSize; i++) {
				opinion_all = opinion_all + Constants.COMMON_SEPARATOR
						+ recordList.get(i).getOpinion() + "<br>审批人："
						+ recordList.get(i).getUser_name() + "<br>审批时间："
						+ recordList.get(i).getOp_time_str();
			}
			return SUCCESS;
		} else {
			if (StringUtils.hasLength(approved)) {// 审批结果
				String next_approver_name = basicService
						.getApproverName(next_approver);
				ApproverUser user = new ApproverUser(getCurUser()
						.getUser_iidd(), getCurUser().getUser_name());
				ApproverUser approver = new ApproverUser(next_approver,
						next_approver_name);
				Map<String, Object> map = new HashMap<String, Object>();
				if (listSize == 0) {
					String other_opinion = "";
					if (property_no != "") {
						other_opinion = "资产编号:" + property_no;
					}
					if (identity_code != "") {
						other_opinion = other_opinion + "<br>" + "识别码:"
								+ identity_code;
					}
					opinion = opinion + "<br>" + other_opinion;
					map.put("event_code", event_code);
					map.put("property_no", property_no);
					map.put("identity_code", identity_code);
					assetService.updatePropertyNoByCode(map);
				}
				if (listSize == 1) {
					String other_opinion = "";
					if (voucher_no != "") {
						other_opinion = "凭证号:" + voucher_no;
					}
					opinion = opinion + "<br>" + other_opinion;
					map.put("event_code", event_code);
					map.put("voucher_no", voucher_no);
					assetService.updatePropertyNoByCode(map);
				}

				basicService.approveJob(job_code, user, approver, approved,
						opinion, "");
				insertCommonLog("审批资产入库任务，审批单编号[" + job_code + "]");
				return "ok";
			} else {
				job = basicService.getProcessJobByCode(job_code);
				ProcessRecord record = new ProcessRecord();
				record.setJob_code(job_code);
				recordList = activitiService.getProcessRecordList(record);
				eventList = assetService.getStorageEventListByJobCode(job_code);
				event = eventList.get(0);
				event_code = event.getEvent_code();
				identity_code = event.getIdentity_code();// 识别码
				property_no = event.getProperty_no();// 资产编号
				voucher_no = event.getVoucher_no();// 凭证号
				listSize = recordList.size() - 1;
				for (int i = 1; i <= listSize; i++) {
					opinion_all = opinion_all + Constants.COMMON_SEPARATOR
							+ recordList.get(i).getOpinion() + "<br>审批人："
							+ recordList.get(i).getUser_name() + "<br>审批时间："
							+ recordList.get(i).getOp_time_str();
				}
				String usage_code = eventList.get(0).getUsage_code();
				process = basicPrcManage.getApproveProcessByInstanceId(job
						.getInstance_id());
				try {
					List<ApproverUser> oriList = basicService.getNextApprover(
							job_code, job.getDept_id(), job.getSeclv_code(),
							job.getJobType().getJobTypeCode(), usage_code);
					userList = removeDuplicateList(oriList);
					if (!basicService.isSelfApprove()) {// 不允许自审批
						if ((userList != null)
								&& (userList.size() == 1)
								&& userList.get(0).getUser_iidd()
										.equals(job.getUser_iidd())) {
							throw new Exception(
									"唯一的下级审批人与申请用户相同！由于系统不支持自审批，请先联系管理员添加可审批用户！");
						} else {
							for (ApproverUser user : userList) {
								if (user.getUser_iidd().equals(
										job.getUser_iidd())) {
									userList.remove(user);
									logger.debug("审批列表中去掉申请用户");
									break;
								}
							}
							for (ApproverUser user : userList) {
								if (user.getUser_iidd().equals(
										getCurUser().getUser_iidd())) {
									userList.remove(user);
									logger.debug("审批列表中去掉当前审批人");
									break;
								}
							}
						}
					}
					/*
					 * List<ApproverUser> tempList = new
					 * ArrayList<ApproverUser>(); for (ApproverUser user :
					 * userList) { List<SecLevel> seclvList =
					 * userService.getCopySecLevelByUser(user.getUser_iidd());
					 * for (SecLevel seclv : seclvList) { if
					 * (seclv.getSeclv_code() == job.getSeclv_code()) {
					 * tempList.add(user); break; } } } if (userList.size() > 0
					 * && tempList.size() == 0) { throw new
					 * Exception("下级审批人涉密级别低于审批单密级，请联系管理员"); } userList =
					 * tempList;
					 */
				} catch (Exception e) {
					logger.error("Exception:" + e.getMessage());
					if (e.getCause() instanceof TooManyResultsException) {
						logger.error("基于该部门、密级和操作的流程定义重复，请提醒管理员修改");
						throw new Exception("基于该部门、密级和操作的流程定义重复，请提醒管理员修改");
					} else {
						throw e;
					}
				}
				return SUCCESS;
			}
		}

	}
}
