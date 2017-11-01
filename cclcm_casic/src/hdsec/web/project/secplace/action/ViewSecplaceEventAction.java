package hdsec.web.project.secplace.action;

import hdsec.web.project.secplace.model.SecplaceEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查看涉密场所申请列表
 * 
 * @author liuyaling 2015-6-11
 */
public class ViewSecplaceEventAction extends SecplaceBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String secplace_name = "";
	private String user_iidd = "";
	private String secplace_code = "";
	private int secplace_type;
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";
	private List<SecplaceEvent> eventList = null;

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getBurnSecLevelByUser(getCurUser().getUser_iidd());
	}

	public String getJob_status() {
		return job_status;
	}

	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}

	public String getSecplace_name() {
		return secplace_name;
	}

	public void setSecplace_name(String secplace_name) {
		this.secplace_name = secplace_name;
	}

	public String getSecplace_code() {
		return secplace_code;
	}

	public void setSecplace_code(String secplace_code) {
		this.secplace_code = secplace_code;
	}

	public int getSecplace_type() {
		return secplace_type;
	}

	public void setSecplace_type(int secplace_type) {
		this.secplace_type = secplace_type;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public List<SecplaceEvent> getEventList() {
		return eventList;
	}

	public void setEventList(List<SecplaceEvent> eventList) {
		this.eventList = eventList;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("secplace_name", secplace_name);
		map.put("job_status", job_status);
		map.put("secplace_type", secplace_type);
		eventList = secplaceService.getSecplaceEventList(map);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_SECPLACE", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_SECPLACE", 3);
		return SUCCESS;
	}

}