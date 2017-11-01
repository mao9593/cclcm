package hdsec.web.project.input.action;

import hdsec.web.project.input.model.InputEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查看电子文件导入申请
 * 
 * @author guoxh 2016-10-8 18:30:28
 */
public class ViewInputListJobAction extends InputBaseAction {
	private static final long serialVersionUID = 1L;
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";
	private String seclv_code = "";
	// private List<ProcessJob> jobList = null;
	private List<InputEvent> eventList = null;

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

	public List<InputEvent> getEventList() {
		return eventList;
	}

	public void setEventList(List<InputEvent> eventList) {
		this.eventList = eventList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getBurnSecLevelByUser(getCurUser().getUser_iidd());
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("seclv_code", seclv_code);
		map.put("job_status", job_status);
		eventList = inputService.getInputEventList(map);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "MSG_INPUT", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "MSG_INPUT", 3);

		return SUCCESS;
	}

}
