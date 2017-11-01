package hdsec.web.project.device.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.device.model.DeviceEvent;

import java.util.List;

/**
 * 查看借入任务详情
 * 
 * @author lixiang
 * 
 */
public class ViewDeviceJobDetailAction extends DeviceBaseAction {
	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private String is_prop = "N";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	private List<DeviceEvent> eventList = null;
	
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
	
	public List<DeviceEvent> getEventList() {
		return eventList;
	}
	
	public String getIs_prop() {
		return is_prop;
	}
	
	public void setIs_prop(String is_prop) {
		this.is_prop = is_prop;
	}
	
	@Override
	public String executeFunction() throws Exception {
		job = basicService.getProcessJobByCode(job_code);
		process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
		ProcessRecord record = new ProcessRecord();
		record.setJob_code(job_code);
		recordList = activitiService.getProcessRecordList(record);
		eventList = deviceService.getDeviceEventListByJobCode(job_code);
		return SUCCESS;
	}
}
