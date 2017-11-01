package hdsec.web.project.publicity.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.burn.model.BurnFile;
import hdsec.web.project.common.CCLCMConstants;
import hdsec.web.project.common.bm.BMPropertyUtil;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.publicity.model.ReportEvent;
import hdsec.web.project.user.model.ApproverUser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.util.StringUtils;

/**
 * 宣传报道保密审查审批
 * 
 * @author lishu
 */
public class ApproveReportJobAction extends PublicityBaseAction {
	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	private List<ReportEvent> eventList = null;
	private String approved = "";
	private String opinion = "";
	private String next_approver = "";
	private List<ApproverUser> userList = null;
	private String history = "";
	private Integer listSize = null;
	private String opinion_all = "";
	private ReportEvent event = null;
	private List<BurnFile> burnFileList = null;
	private String report_style = "";
	private String report_style_name = "";
	private String other_style = "";

	public List<BurnFile> getBurnFileList() {
		return burnFileList;
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

	public ReportEvent getEvent() {
		return event;
	}

	public void setEvent(ReportEvent event) {
		this.event = event;
	}

	public String getOpinion() {
		return opinion;
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

	public List<ReportEvent> getEventList() {
		return eventList;
	}

	public String getReport_style() {
		return report_style;
	}

	public void setReport_style(String report_style) {
		this.report_style = report_style;
	}

	public String getApproved() {
		return approved;
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

	public void setEventList(List<ReportEvent> eventList) {
		this.eventList = eventList;
	}

	public void setUserList(List<ApproverUser> userList) {
		this.userList = userList;
	}

	public void setOpinion_all(String opinion_all) {
		this.opinion_all = opinion_all;
	}

	public void setBurnFileList(List<BurnFile> burnFileList) {
		this.burnFileList = burnFileList;
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

	public String getReport_style_name() {
		return report_style_name;
	}

	public void setReport_style_name(String report_style_name) {
		this.report_style_name = report_style_name;
	}

	public String getOther_style() {
		return other_style;
	}

	/*
	 * public void setOther_style(String other_style) { this.other_style = other_style; }
	 */

	protected void getReportInfo(ReportEvent event1) {

		/* ReportEvent report_style = publicityService.getReportStyleByEventCode(event1.getEvent_code()); */

		String report_style = event.getReport_style();
		String[] report_style_str = report_style.split("\\,");
		int i = 0;
		for (i = 0; i < report_style_str.length; i++) {
			if (Integer.parseInt(report_style_str[i].trim()) == 1) {
				report_style_name = report_style_name + "," + "所内涉密网";
			}
			if (Integer.parseInt(report_style_str[i].trim()) == 2) {
				report_style_name = report_style_name + "," + "集团科研网";
			}
			if (Integer.parseInt(report_style_str[i].trim()) == 3) {
				report_style_name = report_style_name + "," + "信息报送";
			}
			if (Integer.parseInt(report_style_str[i].trim()) == 4) {
				report_style_name = report_style_name + "," + "对外宣传网";
			}
			if (Integer.parseInt(report_style_str[i].trim()) == 5) {
				report_style_name = report_style_name + "," + "电科重庆";
			}
			if (Integer.parseInt(report_style_str[i].trim()) == 6) {
				report_style_name = report_style_name + "," + "集团公司《中国电科报》";
			}
			if (Integer.parseInt(report_style_str[i].trim()) == 7) {
				report_style_name = report_style_name + "," + "上级机关或部门";
			}
			if (Integer.parseInt(report_style_str[i].trim()) == 8) {
				report_style_name = report_style_name + "," + event.getOther_style();
			}
		}
		report_style_name = report_style_name.substring(1, report_style_name.length());
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
			eventList = publicityService.getPublReportEventListByJobCode(job_code);
			event = eventList.get(0);
			if (event.getApply_type() == 1) {
				getReportInfo(event);
			}
			// add by Ls
			String[] filelist = event.getFile_list().split(CCLCMConstants.DEVIDE_SYMBOL);
			if (filelist.length > 0 && StringUtils.hasLength(filelist[0])) {
				String storePath = BMPropertyUtil.getReportStrorePath();
				burnFileList = new ArrayList<BurnFile>();
				String file_path = "";
				for (int i = 0; i < filelist.length; i++) {
					file_path = storePath + "/" + event.getEvent_code() + "/" + filelist[i];
					burnFileList.add(new BurnFile(filelist[i], file_path));
				}
			}

			listSize = recordList.size() - 1;
			for (int i = 1; i <= listSize; i++) {
				opinion_all = opinion_all + Constants.COMMON_SEPARATOR + recordList.get(i).getOpinion() + "<br>审批人："
						+ recordList.get(i).getUser_name() + "<br>审批时间：" + recordList.get(i).getOp_time_str();
			}

			return SUCCESS;
		} else {
			if (StringUtils.hasLength(approved)) {// 审批结果
				String next_approver_name = basicService.getApproverName(next_approver);
				ApproverUser user = new ApproverUser(getCurUser().getUser_iidd(), getCurUser().getUser_name());
				ApproverUser approver = new ApproverUser(next_approver, next_approver_name);
				basicService.approveJob(job_code, user, approver, approved, opinion, "");
				/* getReportInfo(event); */
				insertCommonLog("审批用户涉密活动任务，审批单编号[" + job_code + "]");
				return "ok";
			} else {
				job = basicService.getProcessJobByCode(job_code);
				ProcessRecord record = new ProcessRecord();
				record.setJob_code(job_code);
				recordList = activitiService.getProcessRecordList(record);
				eventList = publicityService.getPublReportEventListByJobCode(job_code);
				event = eventList.get(0);
				if (event.getApply_type() == 1) {
					getReportInfo(event);
				}
				// add by Ls
				String[] filelist = event.getFile_list().split(CCLCMConstants.DEVIDE_SYMBOL);
				if (filelist.length > 0 && StringUtils.hasLength(filelist[0])) {
					String storePath = BMPropertyUtil.getReportStrorePath();
					burnFileList = new ArrayList<BurnFile>();
					String file_path = "";
					for (int i = 0; i < filelist.length; i++) {
						file_path = storePath + "/" + event.getEvent_code() + "/" + filelist[i];
						burnFileList.add(new BurnFile(filelist[i], file_path));
					}
				}
				listSize = recordList.size() - 1;
				for (int i = 1; i <= listSize; i++) {
					opinion_all = opinion_all + Constants.COMMON_SEPARATOR + recordList.get(i).getOpinion()
							+ "<br>审批人：" + recordList.get(i).getUser_name() + "<br>审批时间："
							+ recordList.get(i).getOp_time_str();

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
