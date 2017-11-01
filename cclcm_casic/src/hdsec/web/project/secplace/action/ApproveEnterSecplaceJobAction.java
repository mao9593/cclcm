package hdsec.web.project.secplace.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.common.CCLCMConstants;
import hdsec.web.project.common.bm.BMPropertyUtil;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.secplace.model.EnterSecplaceEvent;
import hdsec.web.project.secplace.model.EntityVisitor;
import hdsec.web.project.secplace.model.SecplaceFile;
import hdsec.web.project.user.model.ApproverUser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.util.StringUtils;

public class ApproveEnterSecplaceJobAction extends SecplaceBaseAction {

	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	private EnterSecplaceEvent event = null;
	private String approved = "";
	private String opinion = "";
	private String next_approver = "";
	private List<ApproverUser> userList = null;
	private String jobType;
	// private String file_src;
	private List<SecplaceFile> secplaceFileList = null;
	private List<EntityVisitor> visitorList = null;
	private String history = "";
	private Integer listSize = null;
	private String opinion_all = "";

	private String time_start = null;
	private String time_end = null;
	private String user_names = null;
	private String user_ids = null;

	public String getOpinion_all() {
		return opinion_all;
	}

	public void setOpinion_all(String opinion_all) {
		this.opinion_all = opinion_all;
	}

	public Integer getListSize() {
		return listSize;
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

	public EnterSecplaceEvent getEvent() {
		return event;
	}

	public void setEvent(EnterSecplaceEvent event) {
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

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}

	public String getTime_end() {
		return time_end;
	}

	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}

	public String getUser_names() {
		return user_names;
	}

	public void setUser_names(String user_names) {
		this.user_names = user_names;
	}

	public String getUser_ids() {
		return user_ids;
	}

	public void setUser_ids(String user_ids) {
		this.user_ids = user_ids;
	}

	public void setListSize(Integer listSize) {
		this.listSize = listSize;
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

	public List<EntityVisitor> getVisitorList() {
		return visitorList;
	}

	public void setVisitorList(List<EntityVisitor> visitorList) {
		this.visitorList = visitorList;
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
			event = secplaceService.getEnterSecplaceEventByJobCode(job_code);
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
			// 外来人员列表
			visitorList = secplaceService.getVisitorListByEventCode(event.getEvent_code());
			// 流程及审批信息
			job = basicService.getProcessJobByCode(job_code);
			process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
			ProcessRecord record = new ProcessRecord();
			record.setJob_code(job_code);
			recordList = activitiService.getProcessRecordList(record);
			listSize = recordList.size() - 1;
			for (int i = 1; i <= listSize; i++) {
				opinion_all = opinion_all + Constants.COMMON_SEPARATOR + recordList.get(i).getOpinion() + "<br>审批人："
						+ recordList.get(i).getUser_name() + "<br>审批时间：" + recordList.get(i).getOp_time_str();
			}
			return SUCCESS;
		} else {

			if (StringUtils.hasLength(approved)) {// 审批结果

				// 如果审批为第三步，需往数据库中插入脱密期起止时间
				if (listSize == 2) {
					String other_opinion = "";
					other_opinion = other_opinion + "进入时间:" + time_start + "<br>" + "离开时间:" + time_end + "<br>"
							+ "陪同人员:" + user_names;
					opinion = opinion + "<br>" + other_opinion;
					/*
					 * Map<String, Object> mmp = new HashMap<String, Object>(); mmp.put("time_start", time_start);
					 * mmp.put("time_end", time_end); mmp.put("job_code", job_code);
					 * securityUserService.updateResignEventByJobCode(mmp);
					 */
				}

				String next_approver_name = basicService.getApproverName(next_approver);
				ApproverUser user = new ApproverUser(getCurUser().getUser_iidd(), getCurUser().getUser_name());
				ApproverUser approver = new ApproverUser(next_approver, next_approver_name);
				securityUserService.extendApproveJob(job_code, user, approver, approved, opinion, "");
				insertCommonLog("审批流程申请任务[" + job_code + "]");

				return "ok";
			} else {
				event = secplaceService.getEnterSecplaceEventByJobCode(job_code);
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
				// 外来人员列表
				visitorList = secplaceService.getVisitorListByEventCode(event.getEvent_code());
				// 流程及审批信息
				job = basicService.getProcessJobByCode(job_code);
				process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
				ProcessRecord record = new ProcessRecord();
				record.setJob_code(job_code);
				recordList = activitiService.getProcessRecordList(record);
				listSize = recordList.size() - 1;
				for (int i = 1; i <= listSize; i++) {
					opinion_all = opinion_all + Constants.COMMON_SEPARATOR + recordList.get(i).getOpinion()
							+ "<br>审批人：" + recordList.get(i).getUser_name() + "<br>审批时间："
							+ recordList.get(i).getOp_time_str();
				}
				try {
					List<ApproverUser> oriList = basicService.getNextApprover(job_code, job.getDept_id(),
							job.getSeclv_code(), job.getJobType().getJobTypeCode(), "");
					userList = removeDuplicateList(oriList);

					// 流程需要申请人（普通用户角色）确认,声光电BM，
					Map<String, Object> variables = basicPrcManage.getProcessVariables(job.getInstance_id());
					Integer cur_approval = (Integer) variables.get("cur_approval");
					if (process.getTotal_steps() > cur_approval) {
						String[] roles = process.getStep_role().split("#");
						String role_ids = roles[cur_approval];
						// String role_ids = roles[cur_approval - 1];
						// 如果审批人角色有且只有普通用户，则视为申请用户确认
						if (role_ids.equals("11")) {
							String user_iidd = getCurUser().getUser_iidd();
							String user_name = getCurUser().getUser_name();
							if (job != null) {
								user_iidd = job.getUser_iidd();
								user_name = job.getUser_name();
							}
							ApproverUser applyUser = new ApproverUser(user_iidd, user_name);
							userList.removeAll(userList);
							userList.add(applyUser);
							return SUCCESS;
						}
					}

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
					/*
					 * List<ApproverUser> tempList = new ArrayList<ApproverUser>(); for (ApproverUser user : userList) {
					 * List<SecLevel> seclvList = new ArrayList<SecLevel>(); seclvList = userService.getSecLevel(); for
					 * (SecLevel seclv : seclvList) { if (seclv.getSeclv_code() == job.getSeclv_code()) {
					 * tempList.add(user); break; } } } if (userList.size() > 0 && tempList.size() == 0) { throw new
					 * Exception("下级审批人涉密级别低于审批单密级，请联系管理员"); } userList = tempList;
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