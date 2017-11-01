package hdsec.web.project.transfer.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.transfer.model.EventTransfer;

import java.util.List;

import org.springframework.util.StringUtils;

public class ViewCDTransferingEventDetailAction extends TransferBaseAction {
	private static final long serialVersionUID = 1L;
	private String id = "";
	private String event_code = "";
	private String op = "";
	private EventTransfer event = null;
	private ApproveProcess process = null;
	private ProcessJob job = null;
	private List<ProcessRecord> recordList = null;
	
	public String getEvent_code() {
		return event_code;
	}
	
	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}
	
	public void setOp(String op) {
		this.op = op;
	}
	
	public String getOp() {
		return op;
	}
	
	public EventTransfer getEvent() {
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
	
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(id)) {
			event = transferService.getTransferEventByTransferId(id);
		} else {
			event = transferService.getTransferEventByTransferCode(event_code);
		}
		if (event == null) {
			throw new Exception("查询的作业不存在或者已经被删除");
		} else {
			String job_code = transferService.getJobCodeByEventCode(event.getEvent_code());
			if (StringUtils.hasLength(job_code)) {
				job = basicService.getProcessJobByCode(job_code);
				// process = basicService.getApproveProcessByKey(job.getDept_id(), job.getSeclv_code(), job.getJobType()
				// .getJobTypeCode());
				process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
				ProcessRecord record = new ProcessRecord();
				record.setJob_code(job_code);
				recordList = activitiService.getProcessRecordList(record);
			}
			return SUCCESS;
		}
	}
}
