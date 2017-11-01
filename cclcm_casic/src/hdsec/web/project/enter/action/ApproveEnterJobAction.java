package hdsec.web.project.enter.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.basic.model.SysProxy;
import hdsec.web.project.enter.model.EnterEvent;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.util.StringUtils;

/**
 * 录入审批任务 2014-5-16 上午2:36:12
 * 
 * @author gaoximin
 */
public class ApproveEnterJobAction extends EnterBaseAction {
	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	private List<EnterEvent> eventList = null;
	private String approved = "";
	private String opinion = "";
	private String next_approver = "";
	private List<ApproverUser> userList = null;

	public String getJob_code() {
		return job_code;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public ProcessJob getJob() {
		return job;
	}

	public List<EnterEvent> getEventList() {
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
			ProcessJob jobdetail = basicService.getProcessJobByCode(job_code);
			ApproverUser user = null;
			if (jobdetail.getNext_approver().contains(getCurUser().getUser_iidd())) {
				user = new ApproverUser(getCurUser().getUser_iidd(), getCurUser().getUser_name());
			} else {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("proxy_user_iidd", getCurUser().getUser_iidd());
				map.put("proxy_type", "APPROVE");
				List<SysProxy> approvers = basicService.getApproveProxy(map);
				for (SysProxy sysProxy : approvers) {
					if (jobdetail.getNext_approver().contains(sysProxy.getUser_iidd())) {
						user = new ApproverUser(sysProxy.getUser_iidd(), userService.getUserNameByUserId(sysProxy
								.getUser_iidd()));
						insertCommonLog(getCurUser().getUser_name() + "代理" + sysProxy.getUser_iidd() + "审批作业");
						break;
					}
				}
			}
			ApproverUser approver = new ApproverUser(next_approver, next_approver_name);
			basicService.approveJob(job_code, user, approver, approved, opinion, "");
			insertCommonLog("审批打印任务，审批单编号[" + job_code + "]");
			return "ok";
		} else {
			job = basicService.getProcessJobByCode(job_code);
			ProcessRecord record = new ProcessRecord();
			record.setJob_code(job_code);
			recordList = activitiService.getProcessRecordList(record);
			eventList = enterService.getEnterEventListByJobCode(job_code);
			String usage_code = eventList.get(0).getUsage_code();
			process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
			try {
				List<ApproverUser> oriList = basicService.getNextApprover(job_code, job.getDept_id(),
						job.getSeclv_code(), job.getJobType().getJobTypeCode(), usage_code);
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
					List<SecLevel> seclvList = userService.getImportSecLevelByUser(user.getUser_iidd());
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