package hdsec.web.project.ledger.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.ledger.model.EventTemp;

import java.util.List;

public class ViewEventTempJobDetailAction extends LedgerBaseAction {

	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	private List<EventTemp> eventList = null;

	@Override
	public String executeFunction() throws Exception {
		if (job_code.equals("")) {
			throw new Exception("查询的作业已经被删除");
		} else {
			job = basicService.getProcessJobByCode(job_code);
			if (job != null) {
				process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
				ProcessRecord record = new ProcessRecord();
				record.setJob_code(job_code);
				recordList = activitiService.getProcessRecordList(record);
				eventList = ledgerService.getTempEventListByJobCode(job_code);
			}
		}
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

	public List<EventTemp> getEventList() {
		return eventList;
	}
}
