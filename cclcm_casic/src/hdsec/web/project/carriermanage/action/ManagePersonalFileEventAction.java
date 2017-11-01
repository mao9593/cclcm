package hdsec.web.project.carriermanage.action;

import hdsec.web.project.carriermanage.model.PersonalFileEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagePersonalFileEventAction extends CarrierManageBaseAction {

	private static final long serialVersionUID = 1L;
	private String file_year = "";
	private String file_quarter = "";
	private String duty_entp_id = "";
	private String job_status = "";
	private List<PersonalFileEvent> eventList = null;

	public String getFile_year() {
		return file_year;
	}

	public String getFile_quarter() {
		return file_quarter;
	}

	public String getDuty_entp_id() {
		return duty_entp_id;
	}

	public void setFile_year(String file_year) {
		this.file_year = file_year;
	}

	public void setFile_quarter(String file_quarter) {
		this.file_quarter = file_quarter;
	}

	public void setDuty_entp_id(String duty_entp_id) {
		this.duty_entp_id = duty_entp_id;
	}

	public String getJob_status() {
		return job_status;
	}

	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}

	public List<PersonalFileEvent> getEventList() {
		return eventList;
	}

	public void setEventList(List<PersonalFileEvent> eventList) {
		this.eventList = eventList;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("job_status", job_status);
		map.put("file_year", file_year);
		map.put("file_quarter", file_quarter);
		map.put("duty_entp_id", duty_entp_id);
		eventList = carrierManageService.getPersonalFileEventList(map);

		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PERSONAL_FILE", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PERSONAL_FILE", 3);

		return SUCCESS;
	}
}