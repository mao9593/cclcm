package hdsec.web.project.asset.action;

import hdsec.web.project.asset.model.EntityProperty;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资产入库申请记录
 * 
 * @author gaoximin 2015-6-27
 */
public class StoreEventListAction extends AssetBaseAction {
	private static final long serialVersionUID = 1L;
	private List<EntityProperty> propertyList = null;
	private String property_name = "";
	private Integer seclv_code = null;
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";

	public String getProperty_name() {
		return property_name;
	}

	public void setProperty_name(String property_name) {
		this.property_name = property_name;
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getStartTime() {
		return startTime == null ? "" : sdf.format(startTime);
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
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

	public List<EntityProperty> getPropertyList() {
		return propertyList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("property_name", property_name);
		map.put("seclv_code", seclv_code);
		map.put("job_status", job_status);
		map.put("property_status", '5');
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("has_job_code", true);
		// map.put("is_all", true);
		propertyList = assetService.getPropertyList(map);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "STORE", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "STORE", 3);
		return SUCCESS;
	}
}
