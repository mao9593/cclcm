package hdsec.web.project.secmanage.action;

import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 保密处管理申请记录合并
 * 
 * @author gaoximin 2015-10-13
 */
public class ManageCheckeEventListAction extends SecManageBaseAction {
	private static final long serialVersionUID = 1L;
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";
	private String seclv_code = "";
	private String jobType_code = "";
	private List<ProcessJob> jobList = null;

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

	public String getJobType_code() {
		return jobType_code;
	}

	public void setJobType_code(String jobType_code) {
		this.jobType_code = jobType_code;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public List<ProcessJob> getJobList() {
		return jobList;
	}

	@Override
	public String executeFunction() throws Exception {
		jobList = new ArrayList<ProcessJob>();
		List<ProcessJob> tempJobList = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("dept_id", getCurUser().getDept_id());
		map.put("seclv_code", seclv_code);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("job_status", job_status);
		map.put("jobType_code", jobType_code);
		map.put("module", "secmanage_baomichu");
		tempJobList = securityUserService.getJobList(map);
		if (tempJobList != null) {
			jobList.addAll(tempJobList);
			tempJobList.clear();
		}
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PUNISH_DEPT", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PUNISH_DEPT", 3);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PUNISH_SECCHECK", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PUNISH_SECCHECK", 3);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PUNISH_RECTIFY", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PUNISH_RECTIFY", 3);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "SEC_CHECK", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "SEC_CHECK", 3);
		return SUCCESS;
	}
}
