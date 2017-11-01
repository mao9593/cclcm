package hdsec.web.project.asset.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.asset.model.EntityProperty;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 部门资产台账列表
 * 
 * @author gaoximin 2015-8-6
 */
public class ManageDeptPropertyAction extends AssetBaseAction {
	private static final long serialVersionUID = 1L;
	private Date startTime = null;
	private Date endTime = null;
	private String property_name = "";
	private String duty_user_name = "";
	private Integer property_status = null;
	private List<EntityProperty> propertyList = null;
	private List<String> dept_ids = null;
	private final String jobType = JobTypeEnum.WASTE.getJobTypeCode();

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

	public String getProperty_name() {
		return property_name;
	}

	public void setProperty_name(String property_name) {
		this.property_name = property_name;
	}

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name;
	}

	public Integer getProperty_status() {
		return property_status;
	}

	public void setProperty_status(Integer property_status) {
		this.property_status = property_status;
	}

	public List<EntityProperty> getPropertyList() {
		return propertyList;
	}

	public String getJobType() {
		return jobType;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public String getActionContext() {
		return "asset/managedeptproperty.action";
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("duty_dept_name", getCurUser().getDept_name());
		dept_ids = basicService.getAdminDeptIdList(getCurUser().getUser_iidd(),
				getActionContext());
		if (dept_ids == null || dept_ids.size() == 0) {
			throw new Exception("没有配置管理部门,请联系管理员进行配置");
		}
		map.put("duty_dept_id", dept_ids);
		map.put("duty_user_name", duty_user_name);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("property_name", property_name);
		map.put("property_status", property_status);
		propertyList = assetService.getPropertyList(map);
		return SUCCESS;
	}
}
