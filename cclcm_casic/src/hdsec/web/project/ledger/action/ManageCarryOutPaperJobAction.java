package hdsec.web.project.ledger.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.ledger.model.EventCarryOut;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

/**
 * 查看外带文件申请记录
 * 
 * @author fyp
 */
public class ManageCarryOutPaperJobAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private String filename = "";
	private String seclv_code = "";
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";
	private List<EventCarryOut> eventList = null;
	private JobTypeEnum jobType = JobTypeEnum.CARRYOUT_PAPER;
	private List<ProcessJob> jobList = null;

	public JobTypeEnum getJobType() {
		return jobType;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getStartTime_str() {
		return startTime == null ? "" : sdf.format(startTime);
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getEndTime_str() {
		return endTime == null ? "" : sdf.format(endTime);
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getJob_status() {
		return job_status;
	}

	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}

	public List<EventCarryOut> getEventList() {
		return eventList;
	}

	public void setEventList(List<EventCarryOut> eventList) {
		this.eventList = eventList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public List<ProcessJob> getJobList() {
		return jobList;
	}

	public String getJobType_code() {
		return JobTypeEnum.CHANGE.getJobTypeCode();
	}

	@Override
	public String executeFunction() throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("seclv_code", seclv_code);
		map.put("job_status", job_status);

		map.put("jobType_code", JobTypeEnum.CARRYOUT_PAPER);
		totalSize = ledgerService.getCarryOutJobSize(map);
		beginIndex = page * pageSize;
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		jobList = ledgerService.getCarryOutJobList(map, rbs);
		for (ProcessJob job : jobList) {
			String event_names = "";
			List<EventCarryOut> events = ledgerService.getEventCarryOutListByJobCode(job.getJob_code());
			for (EventCarryOut event : events) {
				EntityPaper paper = ledgerService.getPaperByBarcode(event.getBarcode());
				event_names += paper.getFile_title() + "  ";
			}
			job.setEvent_names(event_names);
		}
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "CARRYOUT_PAPER", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "CARRYOUT_PAPER", 3);
		return SUCCESS;
	}
}
