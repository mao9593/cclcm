package hdsec.web.project.print.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.print.model.PrintEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 修改打印草稿
 * 
 * @author renmingfei
 * 
 */

public class DraftPrintEventAction extends PrintBaseAction {
	private static final long serialVersionUID = 1L;
	private String file_title = "";
	private Date startTime = null;
	private Date endTime = null;
	private List<PrintEvent> eventList = null;
	private final String jobType = JobTypeEnum.PRINT_REMAIN.getJobTypeCode();
	
	public String getJobType() {
		return jobType;
	}
	
	public String getFile_title() {
		return file_title;
	}
	
	public void setFile_title(String file_title) {
		this.file_title = file_title.trim();
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
	
	public List<PrintEvent> getEventList() {
		return eventList;
	}
	
	public List<SecLevel> getSeclvList() {
		return userService.getPrintSecLevelByUser(getCurUser().getUser_iidd());
	}
	
	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("file_title", file_title);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("pendingDraft", true);
		eventList = printService.getPrintEventList(map);
		return SUCCESS;
	}
	
}
