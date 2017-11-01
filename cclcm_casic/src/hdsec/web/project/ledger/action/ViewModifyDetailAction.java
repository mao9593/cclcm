package hdsec.web.project.ledger.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.ledger.model.EventModify;

import java.util.List;

public class ViewModifyDetailAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	private List<EventModify> eventList = null;
	private EventModify event = null;
	private String type = "paper";
	private String event_code = "";

	public EventModify getEvent() {
		return event;
	}

	public void setEvent(EventModify event) {
		this.event = event;
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

	public List<EventModify> getEventList() {
		return eventList;
	}

	public void setEventList(List<EventModify> eventList) {
		this.eventList = eventList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	@Override
	public String executeFunction() throws Exception {
		event = ledgerService.getModifyEventByCode(event_code);
		if (event == null) {
			throw new Exception("查询的作业已经被删除");
		} else {
			String job_code = ledgerService.getModifyJobCodeByEventCode(event_code);
			job = basicService.getProcessJobByCode(job_code);
			process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
			ProcessRecord record = new ProcessRecord();
			record.setJob_code(job_code);
			recordList = activitiService.getProcessRecordList(record);
			eventList = ledgerService.getModifyEventListByJobCode(job_code);
		}
		return SUCCESS;
	}

}
