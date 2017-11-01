package hdsec.web.project.basic.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;

import java.util.List;

/**
 * 查看待关闭错误任务详情
 * 
 * @author lixiang
 * 
 */
public class ViewCloseJobDetailAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	
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
	
	@Override
	public String executeFunction() throws Exception {
		job = basicService.getProcessJobByCode(job_code);
		process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
		ProcessRecord record = new ProcessRecord();
		record.setJob_code(job_code);
		recordList = activitiService.getProcessRecordList(record);
		return SUCCESS;
	}
}
