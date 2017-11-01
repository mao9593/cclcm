package hdsec.web.project.enter.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.enter.model.EnterEvent;

import java.util.List;

/**
 * 录入作业详情 2014-5-15 上午8:35:16
 * 
 * @author gaoximin
 */
public class ViewEnterJobDetailAction extends EnterBaseAction {
	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private String is_prop = "N";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	private List<EnterEvent> eventList = null;
	private String type = "";

	public String getJob_code() {
		return job_code;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public ProcessJob getJob() {
		return job;
	}

	public List<ProcessRecord> getRecordList() {
		return recordList;
	}

	public ApproveProcess getProcess() {
		return process;
	}

	public List<EnterEvent> getEventList() {
		return eventList;
	}

	public String getIs_prop() {
		return is_prop;
	}

	public void setIs_prop(String is_prop) {
		this.is_prop = is_prop;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String executeFunction() throws Exception {
		job = basicService.getProcessJobByCode(job_code);
		process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
		ProcessRecord record = new ProcessRecord();
		record.setJob_code(job_code);
		recordList = activitiService.getProcessRecordList(record);
		eventList = enterService.getEnterEventListByJobCode(job_code);
		return SUCCESS;
	}
}