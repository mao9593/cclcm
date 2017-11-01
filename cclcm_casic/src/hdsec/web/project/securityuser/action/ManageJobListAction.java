package hdsec.web.project.securityuser.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 任务追踪查询
 * 
 * @author gaoximin 2015-8-15
 */
public class ManageJobListAction extends SecurityUserBaseAction {
	private static final long serialVersionUID = 1L;
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";
	private String user_iidd = "";// 发起人ID
	private String dept_id = "";// 发起部门ID
	private String dept_name = "";// 发起部门
	private String jobType_code = "";
	private String seclv_code = "";
	private String type = "";// 查询类型，区分所有流程、个人流程
	private String personal_user_name = getCurUser().getUser_name();
	private String personal_dept_name = getCurUser().getDept_name();
	private String user_name = "";
	private List<ProcessJob> jobList = null;
	List<JobTypeEnum> useredJobType = null;// 在用流程类型
	private String file_src = "";
	private List<String> allOper = null;
	private String researchFlag = "N";

	// private List<String> nonOper = null;

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

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getJobType_code() {
		return jobType_code;
	}

	public void setJobType_code(String jobType_code) {
		this.jobType_code = jobType_code;
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPersonal_user_name() {
		return personal_user_name;
	}

	public void setPersonal_user_name(String personal_user_name) {
		this.personal_user_name = personal_user_name;
	}

	public String getPersonal_dept_name() {
		return personal_dept_name;
	}

	public void setPersonal_dept_name(String personal_dept_name) {
		this.personal_dept_name = personal_dept_name;
	}

	public List<ProcessJob> getJobList() {
		return jobList;
	}

	public String getActionContext() {
		return "/securityuser/managejoblist.action";
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public List<JobTypeEnum> getUseredJobType() {
		return JobTypeEnum.getUsedJobTypeList();
	}

	public boolean hasPermission(String permission) {
		// 如果用户的权限列表中包含该权限字符串，则返回true
		if (allOper != null && allOper.contains(permission)) {
			return true;
		} else if (allOper != null && allOper.contains("/" + permission)) {
			return true;
		} else {
			// 数据库操作表中没有此操作记录，默认返回true;
			return false;
		}
	}

	public String getFile_src() {
		return file_src;
	}

	public void setFile_src(String file_src) {
		this.file_src = file_src;
	}

	public String getResearchFlag() {
		return researchFlag;
	}

	public void setResearchFlag(String researchFlag) {
		this.researchFlag = researchFlag;
	}

	@Override
	public String executeFunction() throws Exception {
		if (researchFlag.equals("Y")) {
			jobList = new ArrayList<ProcessJob>();
			List<ProcessJob> tempJobList = null;
			Map<String, Object> map = new HashMap<String, Object>();
			if (type.equals("all")) {
				// 保密管理员，查看所有流程
				map.put("user_iidd", user_iidd);
				map.put("dept_id", dept_id);
			} else {
				// 普通用户，查看个人流程
				map.put("user_iidd", getCurUser().getUser_iidd());
				map.put("dept_id", getCurUser().getDept_id());
			}
			map.put("seclv_code", seclv_code);
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("job_status", job_status);
			map.put("jobType_code", jobType_code);
			tempJobList = securityUserService.getJobList(map);
			if (tempJobList != null) {
				jobList.addAll(tempJobList);
				tempJobList.clear();
			}
			// 判断刻录是否与NAS集成
			String permission = "burn/managenasburnevent.action";
			if (hasPermission(permission)) {
				file_src = "nas";
			}
			researchFlag = "Y";
		}
		return SUCCESS;
	}
}
