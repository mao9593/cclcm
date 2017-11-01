package hdsec.web.project.secmanage.action;

import hdsec.web.project.secmanage.model.PaperPatentEvent;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 论文发表专利申请列表
 * 
 * @author gaoximin 2015-9-17
 */
public class ManagePaperPatentListAction extends SecManageBaseAction {

	private static final long serialVersionUID = 1L;
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";
	private List<PaperPatentEvent> eventList = null;

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

	public List<PaperPatentEvent> getEventList() {
		return eventList;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("job_status", job_status);
		eventList = secManageService.getPaperPatentEventList(map);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PAPER_RESEARCH", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PAPER_RESEARCH", 3);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PAPER_OTHERS", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PAPER_OTHERS", 3);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PAPERPATENT", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PAPERPATENT", 3);
		return SUCCESS;
	}

}