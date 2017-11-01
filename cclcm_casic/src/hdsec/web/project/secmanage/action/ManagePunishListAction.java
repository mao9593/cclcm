package hdsec.web.project.secmanage.action;

import hdsec.web.project.secmanage.model.PunishRectifyEvent;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 保密工作违规处罚整改督查申请记录
 * 
 * @author gaoximin 2015-7-24
 */
public class ManagePunishListAction extends SecManageBaseAction {
	private static final long serialVersionUID = 1L;
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";
	private String seclv_code = "";
	private Integer change_status = null;
	private List<PunishRectifyEvent> eventList = null;
	private String duty_user_name = "";

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

	public List<PunishRectifyEvent> getEventList() {
		return eventList;
	}

	// public List<SecLevel> getSeclvList() {
	// return userService.getSecLevel();
	// }

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name;
	}

	public String getActionContext() {
		return "/secmanage/managepunishlist.action";
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("job_status", job_status);
		map.put("duty_user_name", duty_user_name);
		eventList = secManageService.getPunishEventList(map);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PUNISH_DEPT", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PUNISH_DEPT", 3);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PUNISH_SECCHECK", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PUNISH_SECCHECK", 3);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PUNISH_RECTIFY", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PUNISH_RECTIFY", 3);
		return SUCCESS;
	}
}
