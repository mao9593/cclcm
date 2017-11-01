package hdsec.web.project.ledger.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.transfer.action.TransferBaseAction;

import java.util.List;

/**
 * 光盘送销任务详情
 * 
 * @author lixiang 2014-10-29
 */
public class ViewSendDestroyJobDetailAction extends TransferBaseAction {
	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	private List<EntityCD> cdList = null;
	private List<EntityPaper> paperList = null;
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

	public ApproveProcess getProcess() {
		return process;
	}

	public List<ProcessRecord> getRecordList() {
		return recordList;
	}

	public List<EntityCD> getCdList() {
		return cdList;
	}

	public List<EntityPaper> getPaperList() {
		return paperList;
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
		if (type.equals("paper")) {
			paperList = ledgerService.getSendDestroyPaperListByJobCode(job_code);
		} else if (type.equals("cd")) {
			cdList = ledgerService.getSendDestroyCDListByJobCode(job_code);
		}
		return SUCCESS;
	}
}
