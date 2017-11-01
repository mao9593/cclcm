package hdsec.web.project.copy.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.copy.model.CopyEvent;
import hdsec.web.project.ledger.model.EntityPaper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 查看复印作业详细
 * 
 * @author lixiang
 * 
 */
public class ViewCopyEventDetailAction extends CopyBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String op = "";
	private CopyEvent event = null;
	private ApproveProcess process = null;
	private ProcessJob job = null;
	private List<ProcessRecord> recordList = null;
	private List<EntityPaper> mergeList = null;// // 展示台账合并中被合并信息详情

	public List<EntityPaper> getMergeList() {
		return mergeList;
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

	public CopyEvent getEvent() {
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

	@Override
	public String executeFunction() throws Exception {
		event = copyService.getCopyEventByCopyCode(event_code);
		if (event == null) {
			throw new Exception("查询的作业已经被删除");
		} else {
			String job_code = copyService.getJobCodeByEventCode(event_code);
			if (StringUtils.hasLength(job_code)) {
				job = basicService.getProcessJobByCode(job_code);
				// process = basicService.getApproveProcessByKey(job.getDept_id(), job.getSeclv_code(), job.getJobType()
				// .getJobTypeCode());
				process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
				ProcessRecord record = new ProcessRecord();
				record.setJob_code(job_code);
				recordList = activitiService.getProcessRecordList(record);

				// 展示台账合并中被合并信息详情
				EntityPaper paper = ledgerService.getPaperByBarcode(event.getOriginalid());
				if (paper.getCreate_type().equals("MERGE_ENTITY")) {
					Map<String, Object> mapm = new HashMap<String, Object>();
					mapm.put("merge_state", "1");
					mapm.put("merge_code", paper.getPaper_barcode());
					mergeList = ledgerService.getMergePaperList(mapm);
				}
			}
			return SUCCESS;
		}
	}
}
