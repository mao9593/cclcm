package hdsec.web.project.print.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.print.model.OaPrintEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 特殊文件管理
 * 
 * @author guojiao
 * 
 */
public class ManageSpecialPrintListAction extends PrintBaseAction {
	private static final long serialVersionUID = 1L;
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";// 审批状态
	private Integer paper_status = 0;// 录入状态
	private String seclv_code = "";
	private String user_id = "";
	private String dept_id = "";
	private String paper_name = "";
	private List<OaPrintEvent> eventList = null;
	private final JobTypeEnum jobType = JobTypeEnum.SPECIAL_PRINT;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Integer getPaper_status() {
		return paper_status;
	}

	public String getPaper_name() {
		return paper_name;
	}

	public void setPaper_status(Integer paper_status) {
		this.paper_status = paper_status;
	}

	public void setPaper_name(String paper_name) {
		this.paper_name = paper_name;
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

	public List<OaPrintEvent> getEventList() {
		return eventList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public JobTypeEnum getJobType() {
		return jobType;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	@Override
	public String executeFunction() throws Exception {
		String web_url = getModuleName().toLowerCase() + "/" + getTitleName().toLowerCase() + ".action";
		List<String> dept_ids = basicService.getAdminDeptIdList(getCurUser().getUser_iidd(), web_url);
		if (dept_ids == null || dept_ids.size() == 0) {
			throw new Exception("没有配置管理部门,请联系系统管理员进行配置");
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("file_selv", seclv_code);// 此处密级筛选查询文件密级，不查询作业密级
		map.put("submitable", false);
		map.put("job_status", "true");
		map.put("paper_status", 0);
		map.put("paper_name", paper_name);
		map.put("user_iidd", user_id);
		map.put("dept_id", dept_id);
		map.put("admin_user_iidd", getCurUser().getUser_iidd());
		map.put("admin_dept_ids", dept_ids);

		eventList = printService.getSpecialEventList(map);
		return SUCCESS;
	}
}
