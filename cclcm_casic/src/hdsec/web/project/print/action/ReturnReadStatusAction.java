package hdsec.web.project.print.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.print.model.PrintEvent;
import hdsec.web.project.user.model.ApproverUser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 审批任务
 * 
 * @author renmingfei
 * 
 */
public class ReturnReadStatusAction extends PrintBaseAction {
	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private ProcessJob job_temp = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	private List<PrintEvent> eventList = null;
	private String approved = "";
	private String opinion = "";
	private String next_approver = "";
	private List<ApproverUser> userList = null;
	private Integer file_read_status = null;
	private String chkResult = "";

	public String getChkResult() {
		return chkResult;
	}

	public void setChkResult(String chkResult) {
		this.chkResult = chkResult;
	}

	public ProcessJob getJob_temp() {
		return job_temp;
	}

	public void setJob_temp(ProcessJob job_temp) {
		this.job_temp = job_temp;
	}

	public Integer getFile_read_status() {
		return file_read_status;
	}

	public void setFile_read_status(Integer file_read_status) {
		this.file_read_status = file_read_status;
	}

	public String getJob_code() {
		return job_code;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public List<PrintEvent> getEventList() {
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

		job_temp = basicService.getProcessJobByCode(job_code);
		int status = 0;
		status = job_temp.getFile_read_status();
		setFile_read_status(status);

		if (file_read_status == 1) {
			setChkResult("1");
		}
		if (file_read_status == 0) {
			setChkResult("0");
		}
		return SUCCESS;
	}
}
