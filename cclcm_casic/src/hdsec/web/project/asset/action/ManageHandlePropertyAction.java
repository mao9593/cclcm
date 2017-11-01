package hdsec.web.project.asset.action;

import hdsec.web.project.asset.model.WasteEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资产变动管理
 * 
 * @author gaoximin 2015-7-30
 */
public class ManageHandlePropertyAction extends AssetBaseAction {

	private static final long serialVersionUID = 1L;
	private Integer seclv_code = null;
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";
	private Integer waste_status = null;
	private List<WasteEvent> eventList = null;
	private String type = null;
	private List<String> dept_ids = null;

	public void setType(String type) {
		this.type = type;
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

	public Integer getWaste_status() {
		return waste_status;
	}

	public void setWaste_status(Integer waste_status) {
		this.waste_status = waste_status;
	}

	public List<WasteEvent> getEventList() {
		return eventList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public String getActionContext() {
		return "asset/managehandleproperty.action";
	}

	@Override
	public String executeFunction() throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		if (type == null || type.isEmpty()) {
			map.put("user_iidd", getCurUser().getUser_iidd());
		} else if (type.equals("dept")) {
			dept_ids = basicService.getAdminDeptIdList(getCurUser()
					.getUser_iidd(), getActionContext() + "?type=dept");
			if (dept_ids == null || dept_ids.size() == 0) {
				throw new Exception("没有配置管理部门,请联系管理员进行配置");
			}
			map.put("dept_id", dept_ids);
		}
		map.put("seclv_code", seclv_code);
		map.put("job_status", job_status);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("waste_status", waste_status);
		// map.put("is_all", true);
		eventList = assetService.getWasteChangeEventList(map);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "WASTE", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "WASTE", 3);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(),
				"PROPERTYCHANGE", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(),
				"PROPERTYCHANGE", 3);
		if ("dept".equals(type)) {
			return "ok";
		}
		return SUCCESS;
	}

}
