package hdsec.web.project.computer.action;

import hdsec.web.project.computer.model.InfoDeviceEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 信息设备管理新增、变更、报废申请记录合并
 * 
 * @author gaoximin 2015-10-9
 */
public class ManageInfoDeviceListAction extends ComputerBaseAction {
	private static final long serialVersionUID = 1L;
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";
	private String seclv_code = "";
	private String event_type = "";
	private List<InfoDeviceEvent> eventList = null;

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

	public String getEvent_type() {
		return event_type;
	}

	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}

	public List<InfoDeviceEvent> getEventList() {
		return eventList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("seclv_code", seclv_code);
		map.put("job_status", job_status);
		map.put("event_type", event_type);
		eventList = computerService.getInfoDeviceEventList(map);

		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "INFO_DEVICE", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "INFO_DEVICE", 3);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "DEVICE_CHANGE", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "DEVICE_CHANGE", 3);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "DEVICE_DES", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "DEVICE_DES", 3);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "INFO_OTHER", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "INFO_OTHER", 3);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "CHANGE_OTHER", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "CHANGE_OTHER", 3);
		return SUCCESS;
	}
}
