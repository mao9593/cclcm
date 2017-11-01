package hdsec.web.project.change.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.change.model.EventChange;
import hdsec.web.project.ledger.model.EntityPaper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 查看载体归属转换作业详情
 * 
 * @author lixiang 2014-9-17
 */
public class ViewChangeEventDetailAction extends ChangeBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String type = "";
	private EventChange event = null;
	private ApproveProcess process = null;
	private ProcessJob job = null;
	private List<ProcessRecord> recordList = null;
	private String user_name = "";// 部门转个人的用户名称
	private List<EntityPaper> mergeList = null;// // 展示台账合并中被合并信息详情

	public List<EntityPaper> getMergeList() {
		return mergeList;
	}

	public String getUser_name() {
		return user_name;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public EventChange getEvent() {
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
		event = changeService.getChangeEventByCode(event_code);
		if (event == null) {
			throw new Exception("查询的作业不存在或者已经被删除");
		} else {
			String job_code = changeService.getJobCodeByEventCode(event.getEvent_code());
			if (StringUtils.hasLength(job_code)) {
				job = basicService.getProcessJobByCode(job_code);
				user_name = userService.getUserNameByUserId(job.getAccept_user_iidd());
				process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
				ProcessRecord record = new ProcessRecord();
				record.setJob_code(job_code);
				recordList = activitiService.getProcessRecordList(record);

				// 展示台账合并中被合并信息详情
				EntityPaper paper = ledgerService.getPaperByBarcode(event.getBarcode());
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
