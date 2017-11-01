package hdsec.web.project.disc.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.device.model.EntityDevice;
import hdsec.web.project.disc.model.EntitySpaceCD;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;

import java.util.List;

/**
 * 查看任务详情
 * 
 * @author renmingfei
 * 
 */
public class ViewHandleJobDetailAction extends DiscBaseAction {
	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	private List<EntityPaper> paperList = null;
	private List<EntityCD> cdList = null;
	private List<EntityDevice> deviceList = null;
	private List<EntitySpaceCD> spacecdList = null;
	private String type = "";

	public List<EntitySpaceCD> getSpacecdList() {
		return spacecdList;
	}

	public void setSpacecdList(List<EntitySpaceCD> spacecdList) {
		this.spacecdList = spacecdList;
	}

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
		process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
		ProcessRecord record = new ProcessRecord();
		record.setJob_code(job_code);
		recordList = activitiService.getProcessRecordList(record);
		spacecdList = discService.getEntitySpaceCdListByJobCode(job_code);
		return SUCCESS;
	}
}
