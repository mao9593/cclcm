package hdsec.web.project.device.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.device.model.DeviceEvent;

import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 查看磁介质借入作业详细
 * 
 * @author lixiang
 * 
 */
public class ViewDeviceEventDetailAction extends DeviceBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String op = "";
	private DeviceEvent event = null;
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
	
	public DeviceEvent getEvent() {
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
		event = deviceService.getDeviceEventByDeviceCode(event_code);
		if (event == null) {
			throw new Exception("查询的作业已经被删除");
		} else {
			String job_code = deviceService.getJobCodeByEventCode(event_code);
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
