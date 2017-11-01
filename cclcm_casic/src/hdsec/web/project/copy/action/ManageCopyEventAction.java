package hdsec.web.project.copy.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.copy.model.CopyEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理复印作业列表
 * 
 * @author lixiang
 * 
 */
public class ManageCopyEventAction extends CopyBaseAction {
	private static final long serialVersionUID = 1L;
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";// 审批状态
	private String seclv_code = "";
	private List<CopyEvent> eventList = null;
	private final JobTypeEnum jobType = JobTypeEnum.COPY;

	public JobTypeEnum getJobType() {
		return jobType;
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

	public List<CopyEvent> getEventList() {
		return eventList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getCopySecLevelByUser(getCurUser().getUser_iidd());
	}

	public String getActionContext() {
		return "/copy/managecopyevent.action";
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("seclv_code", seclv_code);
		map.put("submitable", false);
		// map.put("job_status", job_status);
		if (job_status.equals("pend")) {// 查询未提交申请的作业
			map.put("job_code_empty", true);
		} else {
			map.put("job_status", job_status);
		}
		eventList = copyService.getCopyEventList(map);
		return SUCCESS;
	}

}
