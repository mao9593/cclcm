package hdsec.web.project.asset.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.asset.model.EntityProperty;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 已出库的资产台账列表
 * 
 * @author gaoximin 2015-6-27
 */
public class AddStoreEventAction extends AssetBaseAction {
	private static final long serialVersionUID = 1L;
	private Date startTime = null;
	private Date endTime = null;
	private String property_name = "";
	private String duty_user_name = "";
	private String property_barcode = "";
	private List<EntityProperty> propertyList = null;
	private final String jobType = JobTypeEnum.STORE.getJobTypeCode();

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

	public String getProperty_barcode() {
		return property_barcode;
	}

	public void setProperty_barcode(String property_barcode) {
		this.property_barcode = property_barcode;
	}

	public String getJobType() {
		return jobType;
	}

	public List<EntityProperty> getPropertyList() {
		return propertyList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public String getActionContext() {
		return "/asset/manageproperty.action";
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("duty_user_name", duty_user_name);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("property_name", property_name);
		map.put("property_barcode", property_barcode);
		map.put("property_status", '2');
		propertyList = assetService.getPropertyList(map);
		return SUCCESS;
	}
}
