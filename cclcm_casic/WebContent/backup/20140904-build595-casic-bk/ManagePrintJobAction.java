package hdsec.web.project.print.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.common.util.TimeUtil;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理打印任务
 * 
 * @author renmingfei
 * 
 */
public class ManagePrintJobAction extends PrintBaseAction {
	private static final long serialVersionUID = 1L;
	private Date startTime = TimeUtil.getBeforeXDay(5);
	private Date endTime = TimeUtil.getCurrentTimestamp();
	private String job_status = "";
	private String seclv_code = "";
	private String cycle_type = "";
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
	
	public String getCycle_type() {
		return cycle_type;
	}
	
	public void setCycle_type(String cycle_type) {
		this.cycle_type = cycle_type;
	}
	
	public List<SecLevel> getSeclvList() {
		return userService.getPrintSecLevelByUser(getCurUser().getUser_iidd());
	}
	
	public List<ProcessJob> getJobList() {
		return jobList;
	}
	
	public String getActionContext() {
		return "/print/manageprintjob.action";
	}
	
	public String getJobType_code() {
		return JobTypeEnum.PRINT_REMAIN.getJobTypeCode();
	}
	
	@Override
	public String executeFunction() throws Exception {
		jobList = new ArrayList<ProcessJob>();
		List<ProcessJob> tempJobList = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("seclv_code", seclv_code);
		map.put("job_status", job_status);
		if (cycle_type.isEmpty()) {
			/*map.put("jobType_code", JobTypeEnum.PRINT_REMAIN.getJobTypeCode());
			tempJobList = basicService.getJobList(map);
			if (tempJobList != null) {
				jobList.addAll(tempJobList);
				tempJobList.clear();
			}
			map.put("jobType_code", JobTypeEnum.PRINT_FILE.getJobTypeCode());
			tempJobList = basicService.getJobList(map);
			if (tempJobList != null) {
				jobList.addAll(tempJobList);
				tempJobList.clear();
			}
			map.put("jobType_code", JobTypeEnum.PRINT_SEND.getJobTypeCode());
			tempJobList = basicService.getJobList(map);
			if (tempJobList != null) {
				jobList.addAll(tempJobList);
				tempJobList.clear();
			}*/
			map.put("isPrintJob", true);
			tempJobList = basicService.getJobList(map);
			if (tempJobList != null) {
				jobList.addAll(tempJobList);
				tempJobList.clear();
			}
		} else {
			map.put("jobType_code", JobTypeEnum.valueOf("PRINT_" + cycle_type));
			tempJobList = basicService.getJobList(map);
			if (tempJobList != null) {
				jobList.addAll(tempJobList);
				tempJobList.clear();
			}
		}
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PRINT", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PRINT", 3);
		return SUCCESS;
	}
}
