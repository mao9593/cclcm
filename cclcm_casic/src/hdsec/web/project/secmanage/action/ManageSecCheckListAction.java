package hdsec.web.project.secmanage.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.secmanage.model.SecCheckEvent;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 部门专项保密检查申请记录
 * 
 * @author guojiao
 */
public class ManageSecCheckListAction extends SecManageBaseAction {
	private static final long serialVersionUID = 1L;
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";
	private List<SecCheckEvent> eventList = null;
	private JobTypeEnum jobType = JobTypeEnum.SEC_CHECK;
	private String rec_user_name = "";

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

	public List<SecCheckEvent> getEventList() {
		return eventList;
	}

	public JobTypeEnum getJobType() {
		return jobType;
	}

	public String getRec_user_name() {
		return rec_user_name;
	}

	public void setRec_user_name(String rec_user_name) {
		this.rec_user_name = rec_user_name;
	}

	public String getActionContext() {
		return "/secmanage/managesecchecklist.action";
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("job_status", job_status);
		eventList = secManageService.getSecCheckEventList(map);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "SEC_CHECK", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "SEC_CHECK", 3);
		return SUCCESS;
	}
}
