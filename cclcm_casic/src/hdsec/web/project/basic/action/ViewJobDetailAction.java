package hdsec.web.project.basic.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.common.BaseEvent;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.transfer.model.EventTransfer;
import hdsec.web.project.transfer.service.TransferService;

import java.util.List;

import javax.annotation.Resource;

/**
 * 查看任务详情
 * 
 * @author renmingfei
 * 
 */
public class ViewJobDetailAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	private List<BaseEvent> eventList = null;
	private String transferJobType;
	private String file_src;
	@Resource
	protected TransferService transferService;

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

	public List<BaseEvent> getEventList() {
		return eventList;
	}

	@Override
	public String executeFunction() throws Exception {
		job = basicService.getProcessJobByCode(job_code);
		/*
		 * process = basicService.getApproveProcessByKey(job.getDept_id(), job.getSeclv_code(), job.getJobType()
		 * .getJobTypeCode());
		 */
		process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
		ProcessRecord record = new ProcessRecord();
		record.setJob_code(job_code);
		recordList = activitiService.getProcessRecordList(record);
		eventList = basicService.getEventListByJobCode(job_code);
		transferJobType = basicService.getJobTypeCodeByJobCode(job_code);
		return SUCCESS;
	}

	public List<EventTransfer> getTransfers() {
		List<EventTransfer> transfers = transferService.getTransferEventByJobCode(job_code);
		for (EventTransfer event : transfers) {
			if ("paper".equalsIgnoreCase(event.getEntity_type())) {
				EntityPaper paper = ledgerService.getPaperByBarcode(event.getBarcode());
				if (null != paper) {
					event.setFile_name(paper.getFile_title());
				}
			} else {
				EntityCD cd = ledgerService.getCDByBarcode(event.getBarcode());
				if (null != cd) {
					event.setFile_name(cd.getFile_list());
				}
			}
		}
		return transfers;
	}

	public String getTransferJobType() {
		return transferJobType;
	}

	public void setTransferJobType(String transferJobType) {
		this.transferJobType = transferJobType;
	}

	public String getFile_src() {
		return file_src;
	}

	public void setFile_src(String file_src) {
		this.file_src = file_src;
	}
}
