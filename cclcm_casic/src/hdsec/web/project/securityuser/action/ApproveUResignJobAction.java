package hdsec.web.project.securityuser.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.common.bm.model.BmRealUser;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.securityuser.model.ResignEvent;
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

public class ApproveUResignJobAction extends SecurityUserBaseAction {
	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	private List<ResignEvent> eventList = null;
	private String approved = "";
	private String opinion = "";
	private String next_approver = "";
	private List<ApproverUser> userList = null;
	private String history = "";
	private BmRealUser bmUser = null;
	private SecUser resignUser = null;
	private String start_time = null;
	private String end_time = null;

	private Integer listSize = null;
	private String opinion_all = "";
	private ResignEvent event = null;
	private String paper_total = "";
	private String cd_total = "";
	private Integer all_total = null;

	private String accept_name1 = "";
	private String accept_name2 = "";
	private String accept_name3 = "";
	private String accept_name4 = "";
	private String computer_num = "";// 计算机数量
	private String device_num = "";// 信息设备数量
	private String media_num = "";// 介质数量

	public String getComputer_num() {
		return computer_num;
	}

	public String getDevice_num() {
		return device_num;
	}

	public String getMedia_num() {
		return media_num;
	}

	public Integer getAll_total() {
		return all_total;
	}

	public BmRealUser getBmUser() {
		return bmUser;
	}

	public SecUser getResignUser() {
		return resignUser;
	}

	public String getJob_code() {
		return job_code;
	}

	public ResignEvent getEvent() {
		return event;
	}

	public void setEvent(ResignEvent event) {
		this.event = event;
	}

	public void setEventList(List<ResignEvent> eventList) {
		this.eventList = eventList;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public ProcessJob getJob() {
		return job;
	}

	public List<ResignEvent> getEventList() {
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

	public String getOpinion() {
		return opinion;
	}

	public String getNext_approver() {
		return next_approver;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public void setAccept_name1(String accept_name1) {
		this.accept_name1 = accept_name1;
	}

	public void setAccept_name2(String accept_name2) {
		this.accept_name2 = accept_name2;
	}

	public void setAccept_name3(String accept_name3) {
		this.accept_name3 = accept_name3;
	}

	public void setAccept_name4(String accept_name4) {
		this.accept_name4 = accept_name4;
	}

	private void getOtherInfo(ResignEvent event1) {
		listSize = recordList.size() - 1;
		for (int i = 1; i <= listSize; i++) {
			opinion_all = opinion_all + Constants.COMMON_SEPARATOR + recordList.get(i).getOpinion() + "<br>审批人："
					+ recordList.get(i).getUser_name() + "<br>审批时间：" + recordList.get(i).getOp_time_str();
		}

		// 从real_user、sec_user表中获取用户信息
		Map<String, Object> mm = new HashMap<String, Object>();
		mm.put("real_user_id", event1.getResign_user_iidd());
		mm.put("info_type", 2);
		List<BmRealUser> usertemp = securityUserService.getUserInfoByUserIdAndInfoType(mm);
		if (usertemp.size() > 0) {
			bmUser = usertemp.get(0);
		} else {
			bmUser = null;
		}
		resignUser = userService.getSecUserByUid(event1.getUser_iidd());

		// 查询该用户个人涉密载体总数
		mm.put("user_id", event1.getResign_user_iidd());
		paper_total = securityUserService.getUserEntityPaperTotal(mm);
		cd_total = securityUserService.getUserEntityCdTotal(mm);
		all_total = Integer.valueOf(paper_total).intValue() + Integer.valueOf(cd_total).intValue();

		// 查询计算机、信息设备责任人总数
		mm.put("duty_user_id", event1.getResign_user_iidd());
		computer_num = computerService.getComputerNumByMap(mm); // 个人计算机数量查询
		device_num = computerService.getInfoDeviceNumByMap(mm); // 信息设备(非介质类)数量查询
		media_num = computerService.getMediaNumByMap(mm); // 介质数量查询
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
			eventList = securityUserService.getUResignEventListByJobCode(job_code);
			event = eventList.get(0);
			getOtherInfo(event);
			return SUCCESS;
		} else {
			if (StringUtils.hasLength(approved)) {// 审批结果

				// 如果审批为第三步，需往数据库中插入脱密期起止时间
				if (listSize == 2) {
					String other_opinion = "";
					other_opinion = other_opinion + "脱密期从" + start_time + "至" + end_time;
					opinion = opinion + "<br>" + other_opinion;
					Map<String, Object> mmp = new HashMap<String, Object>();
					mmp.put("start_time", start_time);
					mmp.put("end_time", end_time);
					mmp.put("job_code", job_code);
					securityUserService.updateResignEventByJobCode(mmp);
				}
				if (listSize == 4) {
					// 如果审批为第5步，审批意见中存储接收人
					String other_opinion1 = "";
					other_opinion1 = other_opinion1 + "<br>" + "请申请人办理交接事项：";
					other_opinion1 = other_opinion1 + "<br>" + "1、 涉密计算机（留存部门）&nbsp;&nbsp;接收人：" + accept_name1;
					other_opinion1 = other_opinion1 + "<br>" + "2、 上内网使用的“UKey”（交信息化管理部门有关人员）&nbsp;&nbsp;接收人："
							+ accept_name2;
					other_opinion1 = other_opinion1 + "<br>" + "3、涉密载体（资料）&nbsp;&nbsp;接收人：" + accept_name3;
					other_opinion1 = other_opinion1 + "<br>" + "4、其他涉密资料&nbsp;&nbsp;接收人：" + accept_name4;
					opinion = opinion + other_opinion1;

				}

				String next_approver_name = basicService.getApproverName(next_approver);
				ApproverUser user = new ApproverUser(getCurUser().getUser_iidd(), getCurUser().getUser_name());
				ApproverUser approver = new ApproverUser(next_approver, next_approver_name);
				securityUserService.extendApproveJob(job_code, user, approver, approved, opinion, "");
				insertCommonLog("审批用户离职（退休）任务，审批单编号[" + job_code + "]");
				return "ok";
			} else {
				job = basicService.getProcessJobByCode(job_code);
				ProcessRecord record = new ProcessRecord();
				record.setJob_code(job_code);
				recordList = activitiService.getProcessRecordList(record);
				eventList = securityUserService.getUResignEventListByJobCode(job_code);
				event = eventList.get(0);
				getOtherInfo(event);
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
