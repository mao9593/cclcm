package hdsec.web.project.asset.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.asset.model.WasteEvent;

import java.util.List;

/**
 * 查看任务详情
 * 
 * @author gaoxm
 * 
 */
public class ViewHandleJobDetailAction extends AssetBaseAction {
	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	private List<WasteEvent> propertyList = null;

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

	public List<WasteEvent> getPropertyList() {
		return propertyList;
	}

	@Override
	public String executeFunction() throws Exception {
		job = basicService.getProcessJobByCode(job_code);
		// process = basicService.getApproveProcessByKey(job.getDept_id(),
		// job.getSeclv_code(), job.getJobType()
		// .getJobTypeCode());
		process = basicPrcManage.getApproveProcessByInstanceId(job
				.getInstance_id());
		ProcessRecord record = new ProcessRecord();
		record.setJob_code(job_code);
		recordList = activitiService.getProcessRecordList(record);
		propertyList = assetService.getWasteEventListByJobCode(job_code);
		return SUCCESS;
	}
}
