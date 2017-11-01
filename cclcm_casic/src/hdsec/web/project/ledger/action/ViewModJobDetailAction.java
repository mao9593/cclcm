package hdsec.web.project.ledger.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.ledger.model.EventModify;

import java.util.List;

public class ViewModJobDetailAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	private List<EventModify> eventList = null;
	private String is_flag = "";

	public String getJob_code() {
		return job_code;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
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

	public String getIs_flag() {
		return is_flag;
	}

	public void setIs_flag(String is_flag) {
		this.is_flag = is_flag;
	}

	@Override
	public String executeFunction() throws Exception {
		job = basicService.getProcessJobByCode(job_code);
		process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
		ProcessRecord record = new ProcessRecord();
		record.setJob_code(job_code);
		recordList = activitiService.getProcessRecordList(record);
		eventList = ledgerService.getModifyEventListByJobCode(job_code);
		for (int i = 0; i < eventList.size(); i++) {
			if (eventList.get(i).getEntity_type().equals("Paper")) {
				EntityPaper paper = ledgerService.getPaperByBarcode(eventList.get(i).getBarcode());
				eventList.get(i).setPaper_id(paper.getPaper_id());
			} else {
				EntityCD cd = ledgerService.getCDByBarcode(eventList.get(i).getBarcode());
				eventList.get(i).setCd_id(cd.getCd_id());
			}
		}

		return SUCCESS;
	}
}
