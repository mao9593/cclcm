package hdsec.web.project.basic.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.device.model.EntityDevice;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;

import java.util.List;

/**
 * 查看任务详情
 * 
 * @author renmingfei
 * 
 */
public class ViewHandleJobDetailAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	private List<EntityPaper> paperList = null;
	private List<EntityCD> cdList = null;
	private List<EntityDevice> deviceList = null;
	private String type = "";

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public List<ProcessRecord> getRecordList() {
		return recordList;
	}

	public ApproveProcess getProcess() {
		return process;
	}

	public List<EntityPaper> getPaperList() {
		return paperList;
	}

	public List<EntityCD> getCdList() {
		return cdList;
	}

	public List<EntityDevice> getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(List<EntityDevice> deviceList) {
		this.deviceList = deviceList;
	}

	@Override
	public String executeFunction() throws Exception {
		job = basicService.getProcessJobByCode(job_code);
		// process = basicService.getApproveProcessByKey(job.getDept_id(), job.getSeclv_code(), job.getJobType()
		// .getJobTypeCode());
		process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
		ProcessRecord record = new ProcessRecord();
		record.setJob_code(job_code);
		recordList = activitiService.getProcessRecordList(record);
		if (job_code.contains("PAPER") || job_code.contains("MERGE_ENTITY")) {
			paperList = ledgerService.getPaperListByJobCode(job_code);
			return SUCCESS;
		} else if (job_code.contains("CD")) {
			// 查询CD列表
			cdList = ledgerService.getCDListByJobCode(job_code);
			return "handlecd";
		} else {
			// 查询Device列表
			deviceList = ledgerService.getDeviceListByJobCode(job_code);
			return "handledevice";
		}
	}
}
