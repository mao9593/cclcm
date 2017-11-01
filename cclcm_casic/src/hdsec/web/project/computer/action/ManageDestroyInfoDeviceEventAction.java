package hdsec.web.project.computer.action;

import hdsec.web.project.computer.model.InfoDeviceEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查看信息设备申请记录
 * 
 * @author guojiao
 */
public class ManageDestroyInfoDeviceEventAction extends ComputerBaseAction {
	private static final long serialVersionUID = 1L;
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";
	private String seclv_code = "";
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

	public List<InfoDeviceEvent> getEventList() {
		return eventList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public String getActionContext() {
		return "/computer/manageinfodeviceevent.action";
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("seclv_code", seclv_code);
		map.put("job_status", job_status);
		map.put("event_type", 3);
		eventList = computerService.getInfoDeviceEventList(map);

		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "DEVICE_DES", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "DEVICE_DES", 3);

		return SUCCESS;
	}
}
