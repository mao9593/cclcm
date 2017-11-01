package hdsec.web.project.ledger.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.user.model.SecLevel;

import java.util.List;

import org.springframework.util.StringUtils;

public class ViewledgerInfoAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private int cd_id;
	private EntityCD cdEntity;
	private String op = "";
	private ApproveProcess process = null;
	private ProcessJob job = null;
	private List<ProcessRecord> recordList = null;
	private String supervise_user_name = "";

	public String getSupervise_user_name() {
		return supervise_user_name;
	}

	public void setSupervise_user_name(String supervise_user_name) {
		this.supervise_user_name = supervise_user_name;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public int getCd_id() {
		return cd_id;
	}

	public void setCd_id(int cd_id) {
		this.cd_id = cd_id;
	}

	public EntityCD getCdEntity() {
		return cdEntity;
	}

	public void setCdEntity(EntityCD cdEntity) {
		this.cdEntity = cdEntity;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public ApproveProcess getProcess() {
		return process;
	}

	public ProcessJob getJob() {
		return job;
	}

	public List<ProcessRecord> getRecordList() {
		return recordList;
	}

	@Override
	public String executeFunction() throws Exception {
		cdEntity = ledgerService.getOneCDLedgerById(cd_id);
		String seclv_name = " ";
		if (null != userService.getSecLevelByCode(cdEntity.getSeclv_code())) {
			seclv_name = userService.getSecLevelByCode(cdEntity.getSeclv_code()).getSeclv_name();
		}
		cdEntity.setSeclv_name(seclv_name);

		String output_undertaker_name = userService.getUserNameByUserId(cdEntity.getOutput_undertaker());
		cdEntity.setOutput_undertaker_name(output_undertaker_name);

		if ((cdEntity.getJob_code() != null) && (!cdEntity.getJob_code().equals(""))) {
			if (cdEntity.getJob_code().contains("DESTROY_CD_BYSELF"))
				supervise_user_name = userService.getUserNameByUserId(cdEntity.getSupervise_user_iidd());

			if (cdEntity.getJob_code().contains("SEND"))
				supervise_user_name = userService.getUserNameByUserId(cdEntity.getAccept_user_iidd());
		}

		String job_code = cdEntity.getJob_code();
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
