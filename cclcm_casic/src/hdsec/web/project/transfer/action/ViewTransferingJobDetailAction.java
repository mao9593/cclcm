package hdsec.web.project.transfer.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.transfer.model.EventTransfer;

import java.util.List;

public class ViewTransferingJobDetailAction extends TransferBaseAction {
	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	private List<EventTransfer> eventList = null;
	private String type;
	
	@Override
	public String executeFunction() throws Exception {
		job = basicService.getProcessJobByCode(job_code);
		process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
		ProcessRecord record = new ProcessRecord();
		record.setJob_code(job_code);
		recordList = activitiService.getProcessRecordList(record);
		eventList = transferService.getTransferEventListByJobCode(job_code, type);
		return SUCCESS;
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
	
	public ApproveProcess getProcess() {
		return process;
	}
	
	public List<ProcessRecord> getRecordList() {
		return recordList;
	}
	
	public List<EventTransfer> getEventList() {
		return eventList;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
}
