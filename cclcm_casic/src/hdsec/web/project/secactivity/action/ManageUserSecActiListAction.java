package hdsec.web.project.secactivity.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.secactivity.model.UserSecActiEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查看用户涉密活动申请记录
 * 
 * @author gj
 */
public class ManageUserSecActiListAction extends SecuActiBaseAction {
	private static final long serialVersionUID = 1L;
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";
	private String seclv_code = "";
	private String name = "";
	private List<UserSecActiEvent> eventList = null;
	private JobTypeEnum jobType = JobTypeEnum.USERSEC_ACTIVITY;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public List<UserSecActiEvent> getEventList() {
		return eventList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public JobTypeEnum getJobType() {
		return jobType;
	}

	public String getActionContext() {
		return "/secactivity/manageusersecactilist.action";
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("seclv_code", seclv_code);
		map.put("job_status", job_status);
		map.put("name", name);
		eventList = secactivityservice.getUSecActiEventList(map);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "USERSEC_ACTIVITY", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "USERSEC_ACTIVITY", 3);
		return SUCCESS;
	}
}
