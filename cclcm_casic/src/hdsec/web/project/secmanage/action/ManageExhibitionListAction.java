package hdsec.web.project.secmanage.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.secmanage.model.ExhibitionEvent;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManageExhibitionListAction extends SecManageBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";
	private List<ExhibitionEvent> eventList = null;
	private JobTypeEnum jobType = JobTypeEnum.EXHIBITION;

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

	public List<ExhibitionEvent> getEventList() {
		return eventList;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("job_status", job_status);
		eventList = secManageService.getExhibitionEventList(map);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EXHIBITION", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EXHIBITION", 3);
		return SUCCESS;
	}

}