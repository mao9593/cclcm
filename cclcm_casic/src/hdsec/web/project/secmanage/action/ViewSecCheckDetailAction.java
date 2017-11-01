package hdsec.web.project.secmanage.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.burn.model.BurnFile;
import hdsec.web.project.common.CCLCMConstants;
import hdsec.web.project.common.bm.BMPropertyUtil;
import hdsec.web.project.secmanage.model.ApproveHistory;
import hdsec.web.project.secmanage.model.FileListEvent;
import hdsec.web.project.secmanage.model.SecCheckEvent;
import hdsec.web.project.user.model.ApproverUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 查看部门专项保密检查任务详情
 * 
 * @author guojiao
 */
public class ViewSecCheckDetailAction extends SecManageBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String op = "";
	private SecCheckEvent event = null;
	private ApproveProcess process = null;
	private ProcessJob job = null;
	private List<ProcessRecord> recordList = null;
	private List<BurnFile> burnFileList = null;
	private List<ApproveHistory> approvehistoryList = null;

	private String contact_num = ""; // 联系电话
	private String check_content = "";// 保密检查说明
	private FileListEvent file = null;
	private String next_approver = "";// 下级审批人
	private String resubmit = "N";// 是否重新提交
	private final String jobType = JobTypeEnum.SEC_CHECK.getJobTypeCode();

	public String getJobType() {
		return jobType;
	}

	public void setResubmit(String resubmit) {
		this.resubmit = resubmit;
	}

	public void setContact_num(String contact_num) {
		this.contact_num = contact_num;
	}

	public void setCheck_content(String check_content) {
		this.check_content = check_content;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public List<ApproveHistory> getApprovehistoryList() {
		return approvehistoryList;
	}

	public List<BurnFile> getBurnFileList() {
		return burnFileList;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public SecCheckEvent getEvent() {
		return event;
	}

	public List<ProcessRecord> getRecordList() {
		return recordList;
	}

	public ProcessJob getJob() {
		return job;
	}

	public ApproveProcess getProcess() {
		return process;
	}

	@Override
	public String executeFunction() throws Exception {
		event = secManageService.getSecCheckEventByEventCode(event_code);
		if (event == null) {
			throw new Exception("查询的作业已经被删除");
		} else {
			if (resubmit.equals("N")) {// 查看
				String[] filelist = event.getFile_list().split(CCLCMConstants.DEVIDE_SYMBOL);
				if (filelist.length > 0 && StringUtils.hasLength(filelist[0])) {
					String storePath = BMPropertyUtil.getSecManageStrorePath();
					burnFileList = new ArrayList<BurnFile>();
					String file_path = "";
					for (int i = 0; i < filelist.length; i++) {
						file_path = storePath + "/" + event_code + "/" + filelist[i];
						burnFileList.add(new BurnFile(filelist[i], file_path));
					}
				}
				String job_code = secManageService.getSecCheckJobCodeByEventCode(event_code);
				if (StringUtils.hasLength(job_code)) {
					job = basicService.getProcessJobByCode(job_code);
					process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
					ProcessRecord record = new ProcessRecord();
					record.setJob_code(job_code);
					recordList = activitiService.getProcessRecordList(record);
					approvehistoryList = new ArrayList<ApproveHistory>();
					for (int i = 1; i < recordList.size(); i++) {
						String[] approvehistory = recordList.get(i).getOpinion().split("#");
						String approvecontent = "";
						if (approvehistory[1].contains("请审批")) {
							approvecontent = approvehistory[1] + "<br>申请人：" + recordList.get(i).getUser_name()
									+ "<br>重新申请时间：" + recordList.get(i).getOp_time_str();
						} else {
							approvecontent = approvehistory[1] + "<br>审批人：" + recordList.get(i).getUser_name()
									+ "<br>审批时间：" + recordList.get(i).getOp_time_str();
						}
						approvehistoryList.add(new ApproveHistory(approvehistory[0], approvecontent));
					}
				}
				return SUCCESS;
			} else {// 重新提交
				String user_iidd = getCurUser().getUser_iidd();
				String user_name = getCurUser().getUser_name();
				String job_code = secManageService.getSecCheckJobCodeByEventCode(event_code);
				// 使用定义上传附件类
				file = new FileListEvent();
				handleFileList(event_code, file, "");
				if (event.getFile_num() != 0) {
					file.setFile_num(file.getFile_num() + event.getFile_num());
					file.setFile_list(file.getFile_list() + CCLCMConstants.DEVIDE_SYMBOL + event.getFile_list());
				}
				while (file.getFile_list().startsWith(CCLCMConstants.DEVIDE_SYMBOL)) {
					file.setFile_list(file.getFile_list().substring(1));
				}

				// 1.更新event
				secManageService.updateSecCheckEvent(event_code, contact_num, check_content, file.getFile_num(),
						file.getFile_list(), new Date());
				// 2.更新流程中的信息
				ApproverUser user = new ApproverUser(user_iidd, user_name);
				String next_approver_userid = next_approver;
				String next_approver_username = "";
				for (String item_id : next_approver_userid.split(",")) {
					String username = userService.getUserNameByUserId(item_id);
					next_approver_username = next_approver_username + "," + username;
				}
				ApproverUser approver = new ApproverUser(next_approver_userid, next_approver_username);
				securityUserService.resubmitApplyJob(job_code, user, approver);
				insertCommonLog("部门专项保密检查重新提交申请[" + event_code + "]");
				return "ok";
			}
		}
	}
}
