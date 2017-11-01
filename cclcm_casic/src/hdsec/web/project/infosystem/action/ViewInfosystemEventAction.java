package hdsec.web.project.infosystem.action;

import hdsec.web.project.computer.model.ChangeDeviceEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewInfosystemEventAction extends InfosystemBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer event_type = null;
	private Integer seclv_code = null;
	private String job_status = "";
	private Date startTime = null;
	private Date endTime = null;
	private List<ChangeDeviceEvent> eventList = null;
	private String types = "1,2,3,4,5,6,7,8,9,10";
	private String[] event_types = null;

	public List<ChangeDeviceEvent> getEventList() {
		return eventList;
	}

	public void setEvent_type(Integer event_type) {
		this.event_type = event_type;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_REPCOM", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_REPCOM", 3);

		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_REINSTALL", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_REINSTALL", 3);

		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_QUITINT", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_QUITINT", 3);

		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_USBKEY", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_USBKEY", 3);

		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_OPENPORT", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_OPENPORT", 3);

		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_LOCALPRINTER", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_LOCALPRINTER", 3);
		
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_INTCOM", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_INTCOM", 3);

		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_SINCOM", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_SINCOM", 3);

		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_CHGCOM", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_CHGCOM", 3);

		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_DESCOM", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_DESCOM", 3);

		return SUCCESS;
	}
}