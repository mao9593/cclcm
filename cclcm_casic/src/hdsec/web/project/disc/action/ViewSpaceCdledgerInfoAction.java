package hdsec.web.project.disc.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.disc.model.EntitySpaceCD;

import java.util.List;

import org.springframework.util.StringUtils;

public class ViewSpaceCdledgerInfoAction extends DiscBaseAction {
	private static final long serialVersionUID = 1L;
	private String id = "";
	private String op = "";
	private EntitySpaceCD entity = null;
	private ApproveProcess process = null;
	private ProcessJob job = null;
	private List<ProcessRecord> recordList = null;
	
	public ApproveProcess getProcess() {
		return process;
	}
	
	public ProcessJob getJob() {
		return job;
	}
	
	public List<ProcessRecord> getRecordList() {
		return recordList;
	}
	
	public String getOp() {
		return op;
	}
	
	public void setOp(String op) {
		this.op = op;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EntitySpaceCD getEntity() {
		return entity;
	}

	public void setEntity(EntitySpaceCD entity) {
		this.entity = entity;
	}

	@Override
	public String executeFunction() throws Exception {
		entity = discService.getEntitySpaceCdById(id);
		String job_code = entity.getJob_code();
		if (StringUtils.hasLength(job_code)) {
			job = basicService.getProcessJobByCode(job_code);
			process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
			ProcessRecord record = new ProcessRecord();
			record.setJob_code(job_code);
			recordList = activitiService.getProcessRecordList(record);
		}
		return SUCCESS;
	}
}
