package hdsec.web.project.burn.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.burn.model.BurnEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理刻录作业
 * 
 * @author renmingfei
 * 
 */
public class ManageBurnEventAction extends BurnBaseAction {
	private static final long serialVersionUID = 1L;
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";
	private String seclv_code = "";
	private Integer burn_status = null;
	private List<BurnEvent> eventList = null;
	private JobTypeEnum jobType = JobTypeEnum.BURN_REMAIN;
	private String proxyburn_user_iidd = "";

	public String getProxyburn_user_iidd() {
		return proxyburn_user_iidd;
	}

	public void setProxyburn_user_iidd(String proxyburn_user_iidd) {
		this.proxyburn_user_iidd = proxyburn_user_iidd;
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

	public Integer getBurn_status() {
		return burn_status;
	}

	public void setBurn_status(Integer burn_status) {
		this.burn_status = burn_status;
	}

	public List<BurnEvent> getEventList() {
		return eventList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getBurnSecLevelByUser(getCurUser().getUser_iidd());
	}

	public JobTypeEnum getJobType() {
		return jobType;
	}

	public String getActionContext() {
		return "/burn/manageburnevent.action";
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		if ((proxyburn_user_iidd != null) && (!proxyburn_user_iidd.equals(""))) {
			map.put("proxyburn_user_iidd", proxyburn_user_iidd);
			basicService.setClientMsgRead(proxyburn_user_iidd, "PROXY_BURN", 2);
			basicService.setClientMsgRead(proxyburn_user_iidd, "PROXY_BURN", 3);
		} else {
			map.put("user_iidd", getCurUser().getUser_iidd());
		}

		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("seclv_code", seclv_code);
		map.put("burn_status", burn_status);
		map.put("submitable", false);
		map.put("job_status", job_status);
		eventList = burnService.getBurnEventList(map);
		if ((proxyburn_user_iidd == null) || (proxyburn_user_iidd.equals(""))) {
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "BURN", 2);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "BURN", 3);
		}
		return SUCCESS;
	}
}
