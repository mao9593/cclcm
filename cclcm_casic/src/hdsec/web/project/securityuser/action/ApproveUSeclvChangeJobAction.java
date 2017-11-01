package hdsec.web.project.securityuser.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.burn.model.BurnFile;
import hdsec.web.project.common.CCLCMConstants;
import hdsec.web.project.common.bm.BMPropertyUtil;
import hdsec.web.project.common.bm.model.BmRealUser;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.securityuser.model.AbroadInfo;
import hdsec.web.project.securityuser.model.ExperienceInfo;
import hdsec.web.project.securityuser.model.FamilyInfo;
import hdsec.web.project.securityuser.model.UserSeclvChangeEvent;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.util.StringUtils;

public class ApproveUSeclvChangeJobAction extends SecurityUserBaseAction {
	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	private List<UserSeclvChangeEvent> eventList = null;
	private String approved = "";
	private String opinion = "";
	private String next_approver = "";
	private List<ApproverUser> userList = null;
	private String history = "";
	private String types = "";

	private Integer listSize = null;
	private String opinion_all = "";
	private UserSeclvChangeEvent event = null;
	private String a_chk = "";
	private String c_chk = "";
	private String p_chk = "";
	private String s_chk = "";
	private String b_chk = "";
	private String f_chk = "";
	private String o_chk = "";
	private String ez_chk = "";
	private String ey_chk = "";
	private String post_station = "";
	private BmRealUser userinfo = null;
	private List<AbroadInfo> abroadinfo = null;
	private List<ExperienceInfo> experienceinfo = null;
	private List<FamilyInfo> familyinfo = null;
	private SecUser secUser = null;
	private List<BurnFile> burnFileList = null;

	public List<BurnFile> getBurnFileList() {
		return burnFileList;
	}

	public BmRealUser getUserinfo() {
		return userinfo;
	}

	public List<AbroadInfo> getAbroadinfo() {
		return abroadinfo;
	}

	public List<ExperienceInfo> getExperienceinfo() {
		return experienceinfo;
	}

	public List<FamilyInfo> getFamilyinfo() {
		return familyinfo;
	}

	public UserSeclvChangeEvent getEvent() {
		return event;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public void setA_chk(String a_chk) {
		this.a_chk = a_chk;
	}

	public void setC_chk(String c_chk) {
		this.c_chk = c_chk;
	}

	public void setPost_station(String post_station) {
		this.post_station = post_station;
	}

	public void setP_chk(String p_chk) {
		this.p_chk = p_chk;
	}

	public void setS_chk(String s_chk) {
		this.s_chk = s_chk;
	}

	public void setB_chk(String b_chk) {
		this.b_chk = b_chk;
	}

	public void setF_chk(String f_chk) {
		this.f_chk = f_chk;
	}

	public void setO_chk(String o_chk) {
		this.o_chk = o_chk;
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

	public String getJob_code() {
		return job_code;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public ProcessJob getJob() {
		return job;
	}

	public List<UserSeclvChangeEvent> getEventList() {
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

	public String getEz_chk() {
		return ez_chk;
	}

	public void setEz_chk(String ez_chk) {
		this.ez_chk = ez_chk;
	}

	public String getEy_chk() {
		return ey_chk;
	}

	public void setEy_chk(String ey_chk) {
		this.ey_chk = ey_chk;
	}

	public SecUser getSecUser() {
		return secUser;
	}

	public void setSecUser(SecUser secUser) {
		this.secUser = secUser;
	}

	protected void getBmUserInfo(UserSeclvChangeEvent event1) {
		secUser = userService.getSecUserByUid(event.getChange_user_iidd());

		Map<String, Object> BMmap1 = new HashMap<String, Object>();
		BMmap1.put("real_user_id", event1.getChange_user_iidd());
		BMmap1.put("info_type", 2);
		userinfo = securityUserService.getUserInfoByEventCode(BMmap1);
		if (userinfo != null) {
			BMmap1.put("event_code", userinfo.getEvent_code());
			// 个人简历
			if (!userinfo.getJob_resume().equals("")) {
				experienceinfo = new ArrayList<ExperienceInfo>();
				experienceinfo = securityUserService.getUserExperience(BMmap1);
			}
			// 出国情况
			if (!userinfo.getAbroad_twice().equals("")) {
				abroadinfo = new ArrayList<AbroadInfo>();
				abroadinfo = securityUserService.getUserAbroad(BMmap1);

				burnFileList = new ArrayList<BurnFile>();
				for (int a = 0; a < abroadinfo.size(); a++) {
					String[] filelist = abroadinfo.get(a).getAbroad_file().split(CCLCMConstants.DEVIDE_SYMBOL);
					if (filelist.length > 0 && StringUtils.hasLength(filelist[0])) {
						String storPath = "";
						try {
							storPath = BMPropertyUtil.getSecUserInfoSepcialFilePath();
						} catch (Exception e) {
							e.printStackTrace();
						}

						String file_path = "";
						for (int i = 0; i < filelist.length; i++) {
							file_path = storPath + "/" + event.getUser_iidd() + "/" + filelist[i];
							burnFileList.add(new BurnFile(filelist[i], file_path));

						}
					}
				}
			}

			// 家庭成员情况
			if (!userinfo.getFamily_info().equals("")) {
				familyinfo = new ArrayList<FamilyInfo>();
				familyinfo = securityUserService.getUserFamily(BMmap1);
			}
		}
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
			process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
			ProcessRecord record = new ProcessRecord();
			record.setJob_code(job_code);
			recordList = activitiService.getProcessRecordList(record);
			eventList = securityUserService.getUSeclvChangeEventListByJobCode(job_code);
			// add by liuyaling
			event = eventList.get(0);
			listSize = recordList.size() - 1;
			for (int i = 1; i <= listSize; i++) {
				opinion_all = opinion_all + Constants.COMMON_SEPARATOR + recordList.get(i).getOpinion() + "<br>审批人："
						+ recordList.get(i).getUser_name() + "<br>审批时间：" + recordList.get(i).getOp_time_str();
			}
			// add ending
			getBmUserInfo(event);

			return SUCCESS;
		} else {
			if (StringUtils.hasLength(approved)) {// 审批结果
				if (types.equalsIgnoreCase("ADD")) {

					if (listSize == 0) {
						String other_opinion = "";
						if (a_chk.equalsIgnoreCase("yes")) {
							other_opinion = "情况是否属实：是";
						} else {
							other_opinion = "情况是否属实：否";
						}

						other_opinion = other_opinion + "<br>" + "拟安排的工作岗位：" + post_station;

						if (c_chk.equalsIgnoreCase("yes")) {
							other_opinion = other_opinion + "<br>" + "涉密等级：秘密";
						} else {
							other_opinion = other_opinion + "<br>" + "涉密等级：机密";
						}

						opinion = opinion + other_opinion;

					} else if (listSize == 1) {
						String other_opinion = "";
						if (p_chk.equalsIgnoreCase("yes")) {
							other_opinion = "个人经历审查情况：符合";
						} else {
							other_opinion = "个人经历审查情况：不符合";
						}

						if (s_chk.equalsIgnoreCase("yes")) {
							other_opinion = other_opinion + "<br>" + "政治表现审查情况：符合";
						} else {
							other_opinion = other_opinion + "<br>" + "政治表现审查情况：不符合";
						}

						if (b_chk.equalsIgnoreCase("yes")) {
							other_opinion = other_opinion + "<br>" + "品行表现审查情况：符合";
						} else {
							other_opinion = other_opinion + "<br>" + "品行表现审查情况：不符合";
						}

						if (f_chk.equalsIgnoreCase("yes")) {
							other_opinion = other_opinion + "<br>" + "与境外人员关系审查情况：符合";
						} else {
							other_opinion = other_opinion + "<br>" + "与境外人员关系审查情况：不符合";
						}

						if (o_chk.equalsIgnoreCase("yes")) {
							other_opinion = other_opinion + "<br>" + "调查及谈话等了解的情况：符合";
						} else {
							other_opinion = other_opinion + "<br>" + "调查及谈话等了解的情况：不符合";
						}
						if (ez_chk != "") {
							if (ez_chk.equalsIgnoreCase("yes")) {
								other_opinion = other_opinion + "<br>" + "审查最终结果：符合重要涉密人员要求";
							} else {
								other_opinion = other_opinion + "<br>" + "审查最终结果：不符合重要涉密人员要求";
							}
						}
						if (ey_chk != "") {
							if (ey_chk.equalsIgnoreCase("yes")) {
								other_opinion = other_opinion + "<br>" + "审查最终结果：符合一般涉密人员要求";
							} else {
								other_opinion = other_opinion + "<br>" + "审查最终结果：不符合一般涉密人员要求";
							}
						}
						opinion = opinion + "<br>" + other_opinion;
					}
				}
				String next_approver_name = basicService.getApproverName(next_approver);
				ApproverUser user = new ApproverUser(getCurUser().getUser_iidd(), getCurUser().getUser_name());
				ApproverUser approver = new ApproverUser(next_approver, next_approver_name);
				// basicService.approveJob(job_code, user, approver, approved, opinion, "");
				securityUserService.extendApproveJob(job_code, user, approver, approved, opinion, "");
				insertCommonLog("审批用户涉密等级变更任务，审批单编号[" + job_code + "]");
				return "ok";
			} else {
				job = basicService.getProcessJobByCode(job_code);
				ProcessRecord record = new ProcessRecord();
				record.setJob_code(job_code);
				recordList = activitiService.getProcessRecordList(record);
				// add by liuyaling

				listSize = recordList.size() - 1;
				for (int i = 1; i <= listSize; i++) {
					opinion_all = opinion_all + Constants.COMMON_SEPARATOR + recordList.get(i).getOpinion()
							+ "<br>审批人：" + recordList.get(i).getUser_name() + "<br>审批时间："
							+ recordList.get(i).getOp_time_str();
				}
				// add ending
				eventList = securityUserService.getUSeclvChangeEventListByJobCode(job_code);
				event = eventList.get(0);
				getBmUserInfo(event);

				if (event.getChange_type().equalsIgnoreCase("ADD")) {
					types = "ADD";
				} else {
					types = "CHANGE";
				}
				String usage_code = eventList.get(0).getUsage_code();
				process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
				try {
					List<ApproverUser> oriList = basicService.getNextApprover(job_code, job.getDept_id(),
							job.getSeclv_code(), job.getJobType().getJobTypeCode(), usage_code);
					userList = removeDuplicateList(oriList);
					if (!basicService.isSelfApprove()) {// 不允许自审批
						if ((userList != null) && (userList.size() == 1)
								&& userList.get(0).getUser_iidd().equals(job.getUser_iidd())) {
							throw new Exception("唯一的下级审批人与申请用户相同！由于系统不支持自审批，请先联系管理员添加可审批用户！");
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
					 * List<SecLevel> seclvList = userService.getCopySecLevelByUser(user.getUser_iidd()); for (SecLevel
					 * seclv : seclvList) { if (seclv.getSeclv_code() == job.getSeclv_code()) { tempList.add(user);
					 * break; } } } if (userList.size() > 0 && tempList.size() == 0) { throw new
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
