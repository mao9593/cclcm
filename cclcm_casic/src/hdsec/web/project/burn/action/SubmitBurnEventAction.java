package hdsec.web.project.burn.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.burn.model.BurnEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 提交刻录申请
 * 
 * @author renmingfei
 * 
 */
public class SubmitBurnEventAction extends BurnBaseAction {
	private static final long serialVersionUID = 1L;
	private Date startTime = null;
	private Date endTime = null;
	private String seclv_code = "";
	private List<BurnEvent> eventList = null;
	private String jobType = JobTypeEnum.BURN_REMAIN.getJobTypeCode();
	
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
	
	public String getSeclv_code() {
		return seclv_code;
	}
	
	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}
	
	public List<BurnEvent> getEventList() {
		return eventList;
	}
	
	public List<SecLevel> getSeclvList() {
		return userService.getBurnSecLevelByUser(getCurUser().getUser_iidd());
	}
	
	public String getJobType() {
		return jobType;
	}
	
	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("seclv_code", seclv_code);
		map.put("submitable", true);
		// map.put("job_status", ActivitiCons.APPLY_APPROVED_REJECT);
		eventList = burnService.getBurnEventList(map);
		return SUCCESS;
	}
}
