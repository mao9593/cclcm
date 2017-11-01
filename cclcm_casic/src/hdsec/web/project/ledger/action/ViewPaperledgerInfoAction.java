package hdsec.web.project.ledger.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.ledger.model.EntityPaper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

public class ViewPaperledgerInfoAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private String paper_id = "";
	private String op = "";
	private EntityPaper paperEntity = null;
	private ApproveProcess process = null;
	private ProcessJob job = null;
	private List<ProcessRecord> recordList = null;
	private String supervise_user_name = "";
	private String time_flag = ""; // 用于标识时间属性,科研工作手册借用台账查看详情时(time_flag="USETIME")为"启用时间",其他为"制作时间"
	private List<EntityPaper> mergeList = null;// // 展示台账合并中被合并信息详情

	public List<EntityPaper> getMergeList() {
		return mergeList;
	}

	public String getTime_flag() {
		return time_flag;
	}

	public void setTime_flag(String time_flag) {
		this.time_flag = time_flag;
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

	public String getPaper_id() {
		return paper_id;
	}

	public void setPaper_id(String paper_id) {
		this.paper_id = paper_id;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public EntityPaper getPaperEntity() {
		return paperEntity;
	}

	public void setPaperEntity(EntityPaper paperEntity) {
		this.paperEntity = paperEntity;
	}

	public String getSupervise_user_name() {
		return supervise_user_name;
	}

	public void setSupervise_user_name(String supervise_user_name) {
		this.supervise_user_name = supervise_user_name;
	}

	@Override
	public String executeFunction() throws Exception {
		paperEntity = ledgerService.getOnePaperLedgerById(paper_id);
		if ((paperEntity.getJob_code() != null) && (!paperEntity.getJob_code().equals(""))) {
			if (paperEntity.getJob_code().contains("DESTROY_PAPER_BYSELF"))
				supervise_user_name = userService.getUserNameByUserId(paperEntity.getSupervise_user_iidd());

			if (paperEntity.getJob_code().contains("SEND"))
				supervise_user_name = userService.getUserNameByUserId(paperEntity.getAccept_user_iidd());
		}

		String seclv_name = " ";
		if (null != userService.getSecLevelByCode(paperEntity.getSeclv_code())) {
			seclv_name = userService.getSecLevelByCode(paperEntity.getSeclv_code()).getSeclv_name();
		}
		paperEntity.setSeclv_name(seclv_name);

		String output_undertaker_name = userService.getUserNameByUserId(paperEntity.getOutput_undertaker());
		paperEntity.setOutput_undertaker_name(output_undertaker_name);
		String job_code = paperEntity.getJob_code();
		if (StringUtils.hasLength(job_code)) {
			job = basicService.getProcessJobByCode(job_code);
			// process = basicService.getApproveProcessByKey(job.getDept_id(), job.getSeclv_code(), job.getJobType()
			// .getJobTypeCode());
			if (job != null) {
				process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
				ProcessRecord record = new ProcessRecord();
				record.setJob_code(job_code);
				recordList = activitiService.getProcessRecordList(record);
			}
		}
		// 展示台账合并中被合并信息详情
		if (paperEntity.getCreate_type().equals("MERGE_ENTITY")) {
			Map<String, Object> mapm = new HashMap<String, Object>();
			mapm.put("merge_state", "1");
			mapm.put("merge_code", paperEntity.getPaper_barcode());
			mergeList = ledgerService.getMergePaperList(mapm);
		}

		return SUCCESS;
	}
}
