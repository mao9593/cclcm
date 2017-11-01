package hdsec.web.project.asset.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.asset.model.WasteEvent;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;
import hdsec.web.project.common.util.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.util.StringUtils;

/**
 * 审批任务
 * 
 * @author gaoxm
 * 
 */

public class HandleJobAction extends AssetBaseAction {
	private static final long serialVersionUID = 1L;
	private static final JobTypeEnum String = null;
	private String job_code = "";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	private List<WasteEvent> eventList = null;
	private String approved = "";
	private String opinion = "";
	private String next_approver = "";
	private List<ApproverUser> userList = null;
	private String history = "";
	private Integer listSize = null;
	private String opinion_all = "";
	private WasteEvent event = null;

	public WasteEvent getEvent() {
		return event;
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

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
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

	public List<ProcessRecord> getRecordList() {
		return recordList;
	}

	public List<WasteEvent> getEventList() {
		return eventList;
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
			eventList = assetService.getWasteEventListByJobCode(job_code);
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
				basicService.approveJob(job_code, user, approver, approved,
						opinion, "PROPERTY");
				insertCommonLog("审批流程申请任务[" + job_code + "]");
				Map map = new HashMap();
				map.put("property_status", 0);
				if ("false".equals(approved)) {

					eventList = assetService
							.getWasteEventListByJobCode(job_code);
					for (WasteEvent event : eventList) {
						map.put("id", event.getProperty_id());
						assetService.updatePropertyStatusByID(map);
					}
				}
				return "ok";
			} else {
				job = basicService.getProcessJobByCode(job_code);
				ProcessRecord record = new ProcessRecord();
				record.setJob_code(job_code);
				recordList = activitiService.getProcessRecordList(record);
				eventList = assetService.getWasteEventListByJobCode(job_code);
				event = eventList.get(0);
				listSize = recordList.size() - 1;
				for (int i = 1; i <= listSize; i++) {
					opinion_all = opinion_all + Constants.COMMON_SEPARATOR
							+ recordList.get(i).getOpinion() + "<br>审批人："
							+ recordList.get(i).getUser_name() + "<br>审批时间："
							+ recordList.get(i).getOp_time_str();
				}
				process = basicService.getApproveProcessByKey(job.getDept_id(),
						job.getSeclv_code(), job.getJobType().getJobTypeCode(),
						"");

				try {
					List<ApproverUser> oriList = basicService.getNextApprover(
							job_code, job.getDept_id(), job.getSeclv_code(),
							job.getJobType().getJobTypeCode(), "");
					userList = removeDuplicateList(oriList);
					if (!basicService.isSelfApprove()) {// 不允许自审批
						if ((userList != null)
								&& (userList.size() == 1)
								&& userList.get(0).getUser_iidd()
										.equals(job.getUser_iidd())) {
							throw new Exception(
									"唯一的下级审批人与申请用户相同！由于系统不支持自审批，请联系管理员添加可审批用户！");
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
					List<ApproverUser> tempList = new ArrayList<ApproverUser>();
					for (ApproverUser user : userList) {
						List<SecLevel> seclvList = new ArrayList<SecLevel>();
						seclvList = userService.getDeviceSecLevelByUser(user
								.getUser_iidd());
						for (SecLevel seclv : seclvList) {
							if (seclv.getSeclv_code() == job.getSeclv_code()) {
								tempList.add(user);
								break;
							}
						}
					}
					if (userList.size() > 0 && tempList.size() == 0) {
						throw new Exception("下级审批人涉密级别低于审批单密级，请联系管理员");
					}
					userList = tempList;
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
