package hdsec.web.project.enter.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.enter.model.EnterEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 个人磁介质录入录入管理
 * 
 * @author haojia
 * 
 */
public class ManageEnterDeviceListAction extends EnterBaseAction {
	private static final long serialVersionUID = 1L;
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";// 审批状态
	private Integer import_status = 0;// 录入状态
	private String seclv_code = "";
	private String user_name = "";
	private String dept_name = "";
	private List<EnterEvent> eventList = null;
	private final JobTypeEnum jobType = JobTypeEnum.LEADIN;
	private String selftype = "N";
	private String zipfile = "";

	public String getZipfile() {
		return zipfile;
	}

	public void setZipfile(String zipfile) {
		this.zipfile = zipfile;
	}

	public String getSelftype() {
		return selftype;
	}

	public void setSelftype(String selftype) {
		this.selftype = selftype;
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

	public Integer getImport_status() {
		return import_status;
	}

	public void setImport_status(Integer import_status) {
		this.import_status = import_status;
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public List<EnterEvent> getEventList() {
		return eventList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getImportSecLevelByUser(getCurUser().getUser_iidd());
	}

	public JobTypeEnum getJobType() {
		return jobType;
	}

	public String getActionContext() {
		return "/enter/manageenterdevicelist.action";
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("seclv_code", seclv_code);
		map.put("submitable", false);
		map.put("job_status", "true");
		map.put("import_status", import_status);
		map.put("file_type", '4');

		String web_url = getModuleName().toLowerCase() + "/" + "manageentercdlist.action";
		List<String> dept_ids = basicService.getAdminDeptIdList(getCurUser().getUser_iidd(), web_url);
		if (dept_ids == null || dept_ids.size() == 0) {
			throw new Exception("没有配置管理部门,请联系系统管理员进行配置");
		}
		map.put("admin_user_iidd", getCurUser().getUser_iidd());
		map.put("admin_dept_ids", dept_ids);
		// map.put("user_iidd", getCurUser().getUser_iidd());
		// map.put("zipfile", zipfile);

		eventList = enterService.getEnterEventList(map);
		return SUCCESS;
	}
}
