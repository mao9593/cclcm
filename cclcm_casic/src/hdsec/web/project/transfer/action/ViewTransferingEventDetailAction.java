package hdsec.web.project.transfer.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.transfer.model.EventTransfer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

public class ViewTransferingEventDetailAction extends TransferBaseAction {
	private static final long serialVersionUID = 1L;
	private String id = "";
	private String event_code = "";
	private String op = "";
	private EventTransfer event = null;
	private ApproveProcess process = null;
	private ProcessJob job = null;
	private List<ProcessRecord> recordList = null;
	private EntityPaper paper = null;
	private EntityCD cd = null;
	private List<EntityPaper> mergeList = null;// // 展示台账合并中被合并信息详情

	public List<EntityPaper> getMergeList() {
		return mergeList;
	}

	public EntityCD getCd() {
		return cd;
	}

	public EntityPaper getPaper() {
		return paper;
	}

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

	public EventTransfer getEvent() {
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

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(id)) {
			event = transferService.getTransferEventByTransferId(id);
		} else {
			event = transferService.getTransferEventByTransferCode(event_code);
			System.out.println("event.transfer_comment:" + event.getTransfer_comment());
		}
		if (event == null) {
			throw new Exception("查询的作业不存在或者已经被删除");
		} else {
			String job_code = transferService.getJobCodeByEventCode(event.getEvent_code());
			if (StringUtils.hasLength(job_code)) {
				job = basicService.getProcessJobByCode(job_code);
				// process = basicService.getApproveProcessByKey(job.getDept_id(), job.getSeclv_code(), job.getJobType()
				// .getJobTypeCode());
				process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
				ProcessRecord record = new ProcessRecord();
				record.setJob_code(job_code);
				recordList = activitiService.getProcessRecordList(record);
			}

			if (event.getEntity_type().equals("Paper")) {
				paper = ledgerService.getPaperByBarcode(event.getBarcode());

				// 展示台账合并中被合并信息详情
				if (paper.getCreate_type().equals("MERGE_ENTITY")) {
					Map<String, Object> mapm = new HashMap<String, Object>();
					mapm.put("merge_state", "1");
					mapm.put("merge_code", paper.getPaper_barcode());
					mergeList = ledgerService.getMergePaperList(mapm);
				}
			}
			if (event.getEntity_type().equals("CD")) {
				cd = ledgerService.getCDByBarcode(event.getBarcode());
			}

			return SUCCESS;
		}
	}

}
