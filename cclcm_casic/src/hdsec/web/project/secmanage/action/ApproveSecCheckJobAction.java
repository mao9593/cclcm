package hdsec.web.project.secmanage.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.burn.model.BurnFile;
import hdsec.web.project.common.CCLCMConstants;
import hdsec.web.project.common.bm.BMPropertyUtil;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.secmanage.model.FileListEvent;
import hdsec.web.project.secmanage.model.SecCheckEvent;
import hdsec.web.project.user.model.ApproverUser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.util.StringUtils;

/**
 * 部门专项保密检查审批
 * 
 * @author guojiao
 */
public class ApproveSecCheckJobAction extends SecManageBaseAction {
	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	private List<SecCheckEvent> eventList = null;
	private String approved = "";
	private String opinion = "";
	private String next_approver = "";
	private List<ApproverUser> userList = null;
	private String history = "";
	private Integer listSize = null;
	private String opinion_all = "";
	private SecCheckEvent event = null;
	private List<BurnFile> burnFileList = null;

	private String event_code = "";
	private String add_file = "";
	private FileListEvent file = null;
	private List<BurnFile> approveFileList = null;
	private String current_step = "";
	private String opinion_history = "";

	public String getCurrent_step() {
		return current_step;
	}

	public String getOpinion_history() {
		return opinion_history;
	}

	public List<BurnFile> getApproveFileList() {
		return approveFileList;
	}

	public void setAdd_file(String add_file) {
		this.add_file = add_file;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getEvent_code() {
		return event_code;
	}

	public List<BurnFile> getBurnFileList() {
		return burnFileList;
	}

	public Integer getListSize() {
		return listSize;
	}

	public String getOpinion_all() {
		return opinion_all;
	}

	public SecCheckEvent getEvent() {
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

	public List<SecCheckEvent> getEventList() {
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
		if (history.equals("Y")) {// 查看审批
			if (job_code.length() != 0) {
				job = basicService.getProcessJobByCode(job_code);
			} else {
				job_code = secManageService.getSecCheckJobCodeByEventCode(event_code);
				job = basicService.getProcessJobByCode(job_code);
			}

			process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
			ProcessRecord record = new ProcessRecord();
			record.setJob_code(job_code);
			recordList = activitiService.getProcessRecordList(record);
			eventList = secManageService.getSecCheckListByJobCode(job_code);
			// add by liuyaling
			Map<String, Object> variables = basicPrcManage.getProcessVariables(job.getInstance_id());
			current_step = variables.get("cur_approval").toString();
			event = eventList.get(0);
			listSize = recordList.size() - 1;
			for (int i = 1; i <= listSize; i++) {
				if (recordList.get(i).getOpinion().contains("请审批")) {
					opinion_history = opinion_history + Constants.COMMON_SEPARATOR + recordList.get(i).getOpinion()
							+ "<br>申请人：" + recordList.get(i).getUser_name() + "<br>重新申请时间："
							+ recordList.get(i).getOp_time_str();
				} else {
					opinion_history = opinion_history + Constants.COMMON_SEPARATOR + recordList.get(i).getOpinion()
							+ "<br>审批人：" + recordList.get(i).getUser_name() + "<br>审批时间："
							+ recordList.get(i).getOp_time_str();
				}
			}
			// 申请时上传的文件
			String[] filelist = event.getFile_list().split(CCLCMConstants.DEVIDE_SYMBOL);
			if (filelist.length > 0 && StringUtils.hasLength(filelist[0])) {
				String storePath = BMPropertyUtil.getSecManageStrorePath();
				burnFileList = new ArrayList<BurnFile>();
				String file_path = "";
				for (int i = 0; i < filelist.length; i++) {
					file_path = storePath + "/" + event.getEvent_code() + "/" + filelist[i];
					burnFileList.add(new BurnFile(filelist[i], file_path));
				}
			}
			// 审批时上传的文件
			String[] approvefilelist = event.getApprove_file_list().split(CCLCMConstants.DEVIDE_SYMBOL);
			if (approvefilelist.length > 0 && StringUtils.hasLength(approvefilelist[0])) {
				String storePath = BMPropertyUtil.getSecManageStrorePath();
				approveFileList = new ArrayList<BurnFile>();
				String file_path = "";
				for (int i = 0; i < approvefilelist.length; i++) {
					file_path = storePath + "/" + event.getEvent_code() + "/" + "approve" + "/" + approvefilelist[i];
					approveFileList.add(new BurnFile(approvefilelist[i], file_path));
				}
			}
			// add ending
			return SUCCESS;
		} else {
			if (StringUtils.hasLength(approved)) {// 审批结果
				if (add_file.equals("Y")) {
					// 使用定义上传附件类
					file = new FileListEvent();
					handleFileList(event_code, file, "approve");
					secManageService.addApproveFile(event_code, file.getFile_list(), file.getFile_num());
				}
				String next_approver_name = basicService.getApproverName(next_approver);
				ApproverUser user = new ApproverUser(getCurUser().getUser_iidd(), getCurUser().getUser_name());
				ApproverUser approver = new ApproverUser(next_approver, next_approver_name);
				basicService.approveJob(job_code, user, approver, approved, opinion, "");
				// securityUserService.extendApproveJob(job_code, user, approver, approved, opinion, "");
				insertCommonLog("审批部门专项保密检查任务，审批单编号[" + job_code + "]");
				return "ok";
			} else {// 审批
				job = basicService.getProcessJobByCode(job_code);
				ProcessRecord record = new ProcessRecord();
				record.setJob_code(job_code);
				recordList = activitiService.getProcessRecordList(record);
				eventList = secManageService.getSecCheckListByJobCode(job_code);
				// add by liuyaling
				Map<String, Object> variables = basicPrcManage.getProcessVariables(job.getInstance_id());
				current_step = variables.get("cur_approval").toString();
				event = eventList.get(0);
				event_code = event.getEvent_code();
				listSize = recordList.size() - 1;
				Integer history_step = listSize - Integer.parseInt(current_step) + 1;
				int i = 0;
				for (i = 1; i <= history_step; i++) {
					if (recordList.get(i).getOpinion().contains("请审批")) {
						opinion_history = opinion_history + Constants.COMMON_SEPARATOR + recordList.get(i).getOpinion()
								+ "<br>申请人：" + recordList.get(i).getUser_name() + "<br>重新申请时间："
								+ recordList.get(i).getOp_time_str();
					} else {
						opinion_history = opinion_history + Constants.COMMON_SEPARATOR + recordList.get(i).getOpinion()
								+ "<br>审批人：" + recordList.get(i).getUser_name() + "<br>审批时间："
								+ recordList.get(i).getOp_time_str();
					}

				}
				for (i = history_step + 1; i <= listSize; i++) {
					opinion_all = opinion_all + Constants.COMMON_SEPARATOR + recordList.get(i).getOpinion()
							+ "<br>审批人：" + recordList.get(i).getUser_name() + "<br>审批时间："
							+ recordList.get(i).getOp_time_str();
				}

				String[] filelist = event.getFile_list().split(CCLCMConstants.DEVIDE_SYMBOL);
				if (filelist.length > 0 && StringUtils.hasLength(filelist[0])) {
					String storePath = BMPropertyUtil.getSecManageStrorePath();
					burnFileList = new ArrayList<BurnFile>();
					String file_path = "";
					for (int j = 0; j < filelist.length; j++) {
						file_path = storePath + "/" + event.getEvent_code() + "/" + filelist[j];
						burnFileList.add(new BurnFile(filelist[j], file_path));
					}
				}

				// 审批时上传的文件
				String[] approvefilelist = event.getApprove_file_list().split(CCLCMConstants.DEVIDE_SYMBOL);
				if (approvefilelist.length > 0 && StringUtils.hasLength(approvefilelist[0])) {
					String storePath = BMPropertyUtil.getSecManageStrorePath();
					approveFileList = new ArrayList<BurnFile>();
					String file_path = "";
					for (int j = 0; j < approvefilelist.length; j++) {
						file_path = storePath + "/" + event.getEvent_code() + "/" + "approve" + "/"
								+ approvefilelist[j];
						approveFileList.add(new BurnFile(approvefilelist[j], file_path));
					}
				}
				// add ending
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
