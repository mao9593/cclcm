package hdsec.web.project.securityuser.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.securityuser.model.UserSeclvChangeEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查看用户密级变更申请记录
 * 
 * @author gaoximin 2015-6-8
 */
public class ManageUserSeclvChangeListAction extends SecurityUserBaseAction {
	private static final long serialVersionUID = 1L;
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";
	private String seclv_code = "";
	private Integer change_status = null;
	private List<UserSeclvChangeEvent> eventList = null;
	private JobTypeEnum jobType = JobTypeEnum.USERSECLV_CHANGE;
	private String type = ""; // url传递的标志,NEW为新增，其他为变更

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

	public Integer getChange_status() {
		return change_status;
	}

	public void setChange_status(Integer change_status) {
		this.change_status = change_status;
	}

	public List<UserSeclvChangeEvent> getEventList() {
		return eventList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public JobTypeEnum getJobType() {
		return jobType;
	}

	public String getActionContext() {
		return "/securityuser/manageuserseclvchangelist.action";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("seclv_code", seclv_code);
		map.put("change_status", change_status);
		map.put("job_status", job_status);
		if (type.equals("NEW")) {
			map.put("change_type", "ADD");
		} else {
			map.put("change_type", "CHANGE");
		}
		eventList = securityUserService.getUSeclvChangeEventList(map);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "USERSECLV_CHANGE", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "USERSECLV_CHANGE", 3);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "USERSECLV_ADD", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "USERSECLV_ADD", 3);
		return SUCCESS;
	}
}
