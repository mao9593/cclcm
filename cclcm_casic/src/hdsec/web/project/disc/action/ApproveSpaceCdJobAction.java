package hdsec.web.project.disc.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.disc.model.EntitySpaceCD;
import hdsec.web.project.disc.model.SpaceCDEvent;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;
import hdsec.web.project.user.model.SecUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.util.StringUtils;

/**
 * 空白盘 审批2014-5-16 上午2:36:12
 * 
 * @author zp
 */
public class ApproveSpaceCdJobAction extends DiscBaseAction {
	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	private List<SpaceCDEvent> eventList = null;
	private String approved = "";
	private String opinion = "";
	private String next_approver = "";
	private List<ApproverUser> userList = null;

	public List<SpaceCDEvent> getEventList() {
		return eventList;
	}

	public void setEventList(List<SpaceCDEvent> eventList) {
		this.eventList = eventList;
	}

	public String getApproved() {
		return approved;
	}

	public String getOpinion() {
		return opinion;
	}

	public String getNext_approver() {
		return next_approver;
	}

	public void setJob(ProcessJob job) {
		this.job = job;
	}

	public void setProcess(ApproveProcess process) {
		this.process = process;
	}

	public void setRecordList(List<ProcessRecord> recordList) {
		this.recordList = recordList;
	}

	public void setUserList(List<ApproverUser> userList) {
		this.userList = userList;
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
		if (StringUtils.hasLength(approved)) {// 审批结果
			String next_approver_name = basicService.getApproverName(next_approver);
			ApproverUser user = new ApproverUser(getCurUser().getUser_iidd(), getCurUser().getUser_name());
			ApproverUser approver = new ApproverUser(next_approver, next_approver_name);
			basicService.approveJob(job_code, user, approver, approved, opinion, "");
			insertCommonLog("审批打印任务，审批单编号[" + job_code + "]");
			ProcessJob jobdetail = basicService.getProcessJobByCode(job_code);
			if (jobdetail.getJob_status().equals("true")) {
				List<EntitySpaceCD> items = discService.getEntitySpaceCdListByJobCode(job_code);
				SecUser user_ = userService.getSecUserByUid(jobdetail.getUser_iidd());
				for (EntitySpaceCD item : items) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("duty_user_iidd", user_.getUser_iidd());
					map.put("duty_user_name", user_.getUser_name());
					map.put("duty_dept_id", user_.getDept_id());
					map.put("duty_dept_name", user_.getDept_name());
					map.put("id", item.getId());
					discService.updateSpaceCdState(map);
				}

			}
			return "ok";
		} else {
			job = basicService.getProcessJobByCode(job_code);
			ProcessRecord record = new ProcessRecord();
			record.setJob_code(job_code);
			recordList = activitiService.getProcessRecordList(record);
			/* eventList = discService.getEntitySpaceCdListByJobCode(job_code); */
			eventList = discService.getSpaceCDEventListByJobCode(job_code);
			process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
			try {
				List<ApproverUser> oriList = basicService.getNextApprover(job_code, job.getDept_id(),
						job.getSeclv_code(), job.getJobType().getJobTypeCode(), "");
				userList = removeDuplicateList(oriList);
				if (!basicService.isSelfApprove()) {// 不允许自审批
					if ((userList != null) && (userList.size() == 1)
							&& userList.get(0).getUser_iidd().equals(job.getUser_iidd())) {
						throw new Exception("唯一的下级审批人与申请用户相同！由于系统不支持自审批，请联系管理员添加可审批用户！");
					} else {
						for (ApproverUser user : userList) {
							if (user.getUser_iidd().equals(job.getUser_iidd())) {
								userList.remove(user);
								logger.debug("审批列表中去掉申请用户");
								break;
							}
						}
						for (ApproverUser user : userList) {
							if (user.getUser_iidd().equals(getCurUser().getUser_iidd())) {
								userList.remove(user);
								logger.debug("审批列表中去掉当前审批人");
								break;
							}
						}
					}
				}
				List<ApproverUser> tempList = new ArrayList<ApproverUser>();
				for (ApproverUser user : userList) {
					List<SecLevel> seclvList = userService.getBurnSecLevelByUser(user.getUser_iidd());
					for (SecLevel seclv : seclvList) {
						if (seclv.getSeclv_code().intValue() == job.getSeclv_code().intValue()) {
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