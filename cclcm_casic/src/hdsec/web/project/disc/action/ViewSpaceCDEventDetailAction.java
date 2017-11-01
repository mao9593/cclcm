package hdsec.web.project.disc.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.disc.model.SpaceCDEvent;

import java.util.List;

public class ViewSpaceCDEventDetailAction extends DiscBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String op = "";
	private SpaceCDEvent event = null;
	private ApproveProcess process = null;
	private ProcessJob job = null;
	private List<ProcessRecord> recordList = null;
	private String current_user_iidd = "";// 当前用户id
	private String apply_user_name = "";

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

	public String getApply_user_name() {
		return apply_user_name;
	}

	public void setApply_user_name(String apply_user_name) {
		this.apply_user_name = apply_user_name;
	}

	public SpaceCDEvent getEvent() {
		return event;
	}

	public void setEvent(SpaceCDEvent event) {
		this.event = event;
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

	public String getCurrent_user_iidd() {
		return current_user_iidd;
	}

	@Override
	public String executeFunction() throws Exception {
		event = discService.getSpaceCDEventByEventCode(event_code);
		apply_user_name = userService.getUserNameByUserId(event.getApply_user_iidd());
		current_user_iidd = getCurUser().getUser_iidd();
		if (event == null) {
			throw new Exception("查询的作业已经被删除");
		} else {
			String job_code = event.getJob_code();
			job = basicService.getProcessJobByCode(job_code);

			// process = basicService.getApproveProcessByKey(job.getDept_id(), job.getSeclv_code(), job.getJobType()
			// .getJobTypeCode());
			process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
			ProcessRecord record = new ProcessRecord();
			/* record.setJob_code(job_code); */
			recordList = activitiService.getProcessRecordList(record);
			/* } */
			return SUCCESS;
		}
	}
}
