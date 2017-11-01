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
import hdsec.web.project.securityuser.model.BmUserInfoEvent;
import hdsec.web.project.securityuser.model.ExperienceInfo;
import hdsec.web.project.securityuser.model.FamilyInfo;
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

public class ApproveUserInfoJobAction extends SecurityUserBaseAction {
	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	private List<BmUserInfoEvent> eventList = null;
	private String approved = "";
	private String opinion = "";
	private String next_approver = "";
	private List<ApproverUser> userList = null;
	private String history = "";
	private Integer listSize = null;
	private String opinion_all = "";
	private BmUserInfoEvent event = null;
	private BmRealUser userinfo = null;
	private List<ExperienceInfo> experienceinfo = null;
	private List<FamilyInfo> familyinfo = null;
	private List<AbroadInfo> abroadinfo = null;
	private List<BurnFile> burnFileList = null;
	private SecUser secUser = null;
	private Integer count = null;
	private String headpath = "";// 用户头像路径

	public String getHeadpath() {
		return headpath;
	}

	public Integer getCount() {
		return count;
	}

	public SecUser getSecUser() {
		return secUser;
	}

	public List<BurnFile> getBurnFileList() {
		return burnFileList;
	}

	public BmRealUser getUserinfo() {
		return userinfo;
	}

	public List<ExperienceInfo> getExperienceinfo() {
		return experienceinfo;
	}

	public List<FamilyInfo> getFamilyinfo() {
		return familyinfo;
	}

	public List<AbroadInfo> getAbroadinfo() {
		return abroadinfo;
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

	public BmUserInfoEvent getEvent() {
		return event;
	}

	public void setEvent(BmUserInfoEvent event) {
		this.event = event;
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

	public List<BmUserInfoEvent> getEventList() {
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

	protected void getUserInfoSpecial(BmUserInfoEvent event1) {

		secUser = userService.getSecUserByUid(event1.getUser_iidd());

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("event_code", event1.getEvent_code());
		map.put("real_user_id", event1.getUser_iidd());
		map.put("info_type", 1);
		userinfo = securityUserService.getUserInfoByEventCode(map);

		if (userinfo != null) {
			// 个人简历
			if (!userinfo.getJob_resume().equals("")) {
				experienceinfo = new ArrayList<ExperienceInfo>();
				experienceinfo = securityUserService.getUserExperience(map);
			}
			// 出国情况
			if (!userinfo.getAbroad_twice().equals("")) {
				abroadinfo = new ArrayList<AbroadInfo>();
				abroadinfo = securityUserService.getUserAbroad(map);

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
				familyinfo = securityUserService.getUserFamily(map);
			}
		}

		// 个人头像
		headpath = getHeadShot(event1.getUser_iidd());

		listSize = recordList.size() - 1;
		for (int i = 1; i <= listSize; i++) {
			opinion_all = opinion_all + Constants.COMMON_SEPARATOR + recordList.get(i).getOpinion() + "<br>审批人："
					+ recordList.get(i).getUser_name() + "<br>审批时间：" + recordList.get(i).getOp_time_str();
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
			eventList = securityUserService.getUserInfoEventListByJobCode(job_code);
			event = eventList.get(0);
			getUserInfoSpecial(event);
			return SUCCESS;
		} else {
			if (StringUtils.hasLength(approved)) {// 审批结果
				String next_approver_name = basicService.getApproverName(next_approver);
				ApproverUser user = new ApproverUser(getCurUser().getUser_iidd(), getCurUser().getUser_name());
				ApproverUser approver = new ApproverUser(next_approver, next_approver_name);
				// basicService.approveJob(job_code, user, approver, approved, opinion, "");
				securityUserService.extendApproveJob(job_code, user, approver, approved, opinion, "");
				insertCommonLog("审批用户信息完善任务，审批单编号[" + job_code + "]");
				return "ok";
			} else {
				job = basicService.getProcessJobByCode(job_code);
				ProcessRecord record = new ProcessRecord();
				record.setJob_code(job_code);
				recordList = activitiService.getProcessRecordList(record);
				eventList = securityUserService.getUserInfoEventListByJobCode(job_code);
				event = eventList.get(0);
				getUserInfoSpecial(event);
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
