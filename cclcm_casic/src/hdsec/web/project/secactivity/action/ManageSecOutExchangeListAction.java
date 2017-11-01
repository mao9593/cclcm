package hdsec.web.project.secactivity.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.secactivity.model.SecOutExchangeEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查看用涉外交流工作申请记录
 * 
 * @author gj
 */
public class ManageSecOutExchangeListAction extends SecuActiBaseAction {
	private static final long serialVersionUID = 1L;
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";
	private String seclv_code = "";
	private String name = "";
	private List<SecOutExchangeEvent> eventList = null;
	private JobTypeEnum jobType = JobTypeEnum.OUT_EXCHANGE;

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

	public List<SecOutExchangeEvent> getEventList() {
		return eventList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public JobTypeEnum getJobType() {
		return jobType;
	}

	public String getActionContext() {
		return "/secactivity/managesecoutexchangelist.action";
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("seclv_code", seclv_code);
		map.put("job_status", job_status);
		eventList = secactivityservice.getSecOutExchangeEventList(map);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "OUT_EXCHANGE", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "OUT_EXCHANGE", 3);
		return SUCCESS;
	}
}
