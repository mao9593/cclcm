package hdsec.web.project.change.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.change.model.EventChange;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查看申请记录
 * 
 * @author lixiang 2014-9-17
 */
public class ManageChangeJobAction extends ChangeBaseAction {
	private static final long serialVersionUID = 1L;
	private String filename = "";
	private String seclv_code = "";
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";
	private Integer change_status = null;
	private List<EventChange> eventList = null;
	private JobTypeEnum jobType = JobTypeEnum.CHANGE;
	private List<ProcessJob> jobList = null;

	public String getActionContext() {
		return "/change/managechangejob.action";
	}

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

	public List<EventChange> getEventList() {
		return eventList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public Integer getChange_status() {
		return change_status;
	}

	public void setChange_status(Integer change_status) {
		this.change_status = change_status;
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
		map.put("jobType_code", JobTypeEnum.CHANGE);
		jobList = changeService.getJobList(map);
		for (ProcessJob job : jobList) {
			String event_names = "";
			List<EventChange> events = changeService.getChangeEventListByJobCode(job.getJob_code());
			for (EventChange event : events) {
				event_names += event.getFile_name() + "  ";
			}
			job.setEvent_names(event_names);
		}
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "CHANGE", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "CHANGE", 3);
		return SUCCESS;
	}
}
