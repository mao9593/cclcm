package hdsec.web.project.change.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.basic.model.SysConfigItem;
import hdsec.web.project.change.model.EventChange;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 载体归属转换管理
 * 
 * @author lixiang
 * 
 */
public class ManageChangeEventListAction extends ChangeBaseAction {
	private static final long serialVersionUID = 1L;
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";// 审批状态
	private Integer change_status = 0;// 转换状态
	private String entity_type = "";
	private String change_type = "";
	private String seclv_code = "";
	private List<EventChange> eventList = null;
	private final JobTypeEnum jobType = JobTypeEnum.CHANGE;

	public Integer getChange_status() {
		return change_status;
	}

	public void setChange_status(Integer change_status) {
		this.change_status = change_status;
	}

	public String getEntity_type() {
		return entity_type;
	}

	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}

	public String getChange_type() {
		return change_type;
	}

	public void setChange_type(String change_type) {
		this.change_type = change_type;
	}

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

	public List<EventChange> getEventList() {
		return eventList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public String getActionContext() {
		return "/change/managechangeeventlist.action";
	}

	@Override
	public String executeFunction() throws Exception {
		String web_url = getModuleName().toLowerCase() + "/" + getTitleName().toLowerCase() + ".action";
		List<String> dept_ids = basicService.getAdminDeptIdList(getCurUser().getUser_iidd(), web_url);
		if (dept_ids == null || dept_ids.size() == 0) {
			throw new Exception("没有配置管理部门,请联系系统管理员进行配置");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		// 判断载体归属转换模式
		int mode = basicService.getSysConfigItemValue(SysConfigItem.KEY_DEPTTOPERSON_MODE).getStartuse();
		if (mode == 0) {// 个人确认模块开启
			map.put("mode_on", true);
		} else {
			map.put("change_type", change_type);
		}

		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("seclv_code", seclv_code);
		map.put("job_status", "true");
		map.put("change_status", change_status);
		map.put("entity_type", entity_type);
		map.put("admin_dept_ids", dept_ids);

		eventList = changeService.getChangeEventList(map);
		return SUCCESS;
	}

}
