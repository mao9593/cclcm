package hdsec.web.project.secplace.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.common.CCLCMConstants;
import hdsec.web.project.common.bm.BMPropertyUtil;
import hdsec.web.project.secplace.model.SecplaceEvent;
import hdsec.web.project.secplace.model.SecplaceFile;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.util.StringUtils;

public class ApproveSecplaceJobAction extends SecplaceBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	private SecplaceEvent event = null;
	private String approved = "";
	private String opinion = "";
	private String next_approver = "";
	private List<ApproverUser> userList = null;
	private String jobType;
	// private String file_src;
	private List<SecplaceFile> secplaceFileList = null;

	public String getJob_code() {
		return job_code;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public ProcessJob getJob() {
		return job;
	}

	public void setJob(ProcessJob job) {
		this.job = job;
	}

	public ApproveProcess getProcess() {
		return process;
	}

	public void setProcess(ApproveProcess process) {
		this.process = process;
	}

	public List<ProcessRecord> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<ProcessRecord> recordList) {
		this.recordList = recordList;
	}

	public SecplaceEvent getEvent() {
		return event;
	}

	public void setEvent(SecplaceEvent event) {
		this.event = event;
	}

	public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getNext_approver() {
		return next_approver;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public List<ApproverUser> getUserList() {
		return userList;
	}

	public void setUserList(List<ApproverUser> userList) {
		this.userList = userList;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public List<SecplaceFile> getSecplaceFileList() {
		return secplaceFileList;
	}

	public void setSecplaceFileList(List<SecplaceFile> secplaceFileList) {
		this.secplaceFileList = secplaceFileList;
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
			securityUserService.extendApproveJob(job_code, user, approver, approved, opinion, "");
			insertCommonLog("审批流程申请任务[" + job_code + "]");

			return "ok";
		} else {
			event = secplaceService.getSecplaceEventByJobCode(job_code);
			String[] filelist = event.getFile_list().split(CCLCMConstants.DEVIDE_SYMBOL);
			if (filelist.length > 0 && StringUtils.hasLength(filelist[0])) {
				String storePath = BMPropertyUtil.getSecplaceFileStorePath();
				secplaceFileList = new ArrayList<SecplaceFile>();
				String file_path = "";
				for (int i = 0; i < filelist.length; i++) {
					file_path = storePath + "/" + event.getEvent_code() + "/" + filelist[i];
					secplaceFileList.add(new SecplaceFile(filelist[i], file_path));
				}
			}
			// 流程及审批信息
			job = basicService.getProcessJobByCode(job_code);
			process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
			ProcessRecord record = new ProcessRecord();
			record.setJob_code(job_code);
			recordList = activitiService.getProcessRecordList(record);

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
					List<SecLevel> seclvList = new ArrayList<SecLevel>();
					seclvList = userService.getSecLevel();
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