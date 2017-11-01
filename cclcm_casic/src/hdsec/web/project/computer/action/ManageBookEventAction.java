package hdsec.web.project.computer.action;

import hdsec.web.project.computer.model.ChangeDeviceEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManageBookEventAction extends ComputerBaseAction {

	private static final long serialVersionUID = 1L;
	private Integer event_type = null;
	private Integer seclv_code = null;
	private String job_status = "";
	private Date startTime = null;
	private Date endTime = null;
	private List<ChangeDeviceEvent> eventList = null;
	private String types = "11,12,13,14";
	private String[] event_types = null;

	public Integer getEvent_type() {
		return event_type;
	}

	public void setEvent_type(Integer event_type) {
		this.event_type = event_type;
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getJob_status() {
		return job_status;
	}

	public void setJob_status(String job_status) {
		this.job_status = job_status;
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

	public List<ChangeDeviceEvent> getEventList() {
		return eventList;
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
		if (event_type != null) {
			types = event_type.toString() + ",";
		}
		event_types = types.split(",");
		map.put("event_type", event_types);
		map.put("job_status", job_status);
		map.put("seclv_code", seclv_code);
		eventList = computerService.getComputerEventList(map);

		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "BOOK_CHANGE", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "BOOK_CHANGE", 3);

		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "BOOK_REPAIR", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "BOOK_REPAIR", 3);

		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "BOOK_DES", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "BOOK_DES", 3);

		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "BOOK_REINSTALL", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "BOOK_REINSTALL", 3);
		return SUCCESS;
	}

}