package hdsec.web.project.disc.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.disc.model.SpaceCDEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查看待分配空白盘任务列表
 * 
 * @author lishu 2015-12-15
 */
public class ViewWaitingJobListAction extends DiscBaseAction {

	private static final long serialVersionUID = 1L;
	private String apply_user_iidd = "";
	private Integer seclv_code = null;
	private List<ProcessJob> jobList = null;
	private String entity_type = "";
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";// 审批状态
	private Integer assign_status = 0;// 分发状态
	private String scope_dept_name = "";

	public String getApply_user_iidd() {
		return apply_user_iidd;
	}

	public void setApply_user_iidd(String apply_user_iidd) {
		this.apply_user_iidd = apply_user_iidd;
	}

	public JobTypeEnum getJobType() {
		return jobType;
	}

	public void setEventList(List<SpaceCDEvent> eventList) {
		this.eventList = eventList;
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getEntity_type() {
		return entity_type;
	}

	public List<ProcessJob> getJobList() {
		return jobList;
	}

	private List<SpaceCDEvent> eventList = null;
	private final JobTypeEnum jobType = JobTypeEnum.SPACECD_BORROW;

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

	public List<SpaceCDEvent> getEventList() {
		return eventList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getBurnSecLevelByUser(getCurUser().getUser_iidd());
	}

	public void setJobList(List<ProcessJob> jobList) {
		this.jobList = jobList;
	}

	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public String getJob_status() {
		return job_status;
	}

	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}

	public String getScope_dept_name() {
		return scope_dept_name;
	}

	public void setScope_dept_name(String scope_dept_name) {
		this.scope_dept_name = scope_dept_name;
	}

	public Integer getAssign_status() {
		return assign_status;
	}

	public void setAssign_status(Integer assign_status) {
		this.assign_status = assign_status;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		/*
		 * map.put("startTime", startTime); map.put("endTime", endTime);
		 */
		map.put("seclv_code", seclv_code);
		map.put("apply_user_iidd", apply_user_iidd);
		map.put("scope_dept_name", scope_dept_name);
		map.put("submitable", false);
		map.put("job_status", "true");
		map.put("assign_status", "0");
		map.put("admin_user_iidd", getCurUser().getUser_iidd());

		eventList = discService.getSpaceCDEventList(map);
		return SUCCESS;

	}
}
