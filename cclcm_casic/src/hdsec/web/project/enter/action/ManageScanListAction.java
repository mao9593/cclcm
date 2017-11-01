package hdsec.web.project.enter.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.enter.model.EnterEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 扫描录入管理
 * 
 * @author gaoximin 2014-10-17
 */
public class ManageScanListAction extends EnterBaseAction {
	private static final long serialVersionUID = 1L;
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";// 审批状态
	private Integer import_status = 0;// 录入状态
	private String seclv_code = "";
	private List<EnterEvent> eventList = null;
	private final JobTypeEnum jobType = JobTypeEnum.SCAN_LEADIN;
	private String self_type = "N";

	public String getSelf_type() {
		return self_type;
	}

	public void setSelf_type(String self_type) {
		this.self_type = self_type;
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
		return "/enter/managescanlist.action";
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("seclv_code", seclv_code);
		map.put("submitable", false);
		map.put("job_status", "true");
		map.put("file_type", '3');
		if (self_type.equalsIgnoreCase("N")) {
			String web_url = getModuleName().toLowerCase() + "/" + getTitleName().toLowerCase() + ".action";
			List<String> dept_ids = basicService.getAdminDeptIdList(getCurUser().getUser_iidd(), web_url);
			if (dept_ids == null || dept_ids.size() == 0) {
				throw new Exception("没有配置管理部门,请联系系统管理员进行配置");
			}

			map.put("import_status", import_status);
			map.put("admin_user_iidd", getCurUser().getUser_iidd());
			map.put("admin_dept_ids", dept_ids);
		} else {
			map.put("import_status", "1");
			map.put("user_iidd", getCurUser().getUser_iidd());
		}

		eventList = enterService.getEnterEventList(map);
		if (self_type.equalsIgnoreCase("N")) {
			return SUCCESS;
		} else {
			return "self_success";
		}

	}
}
