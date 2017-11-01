package hdsec.web.project.asset.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.asset.model.WasteEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资产报废作业列表
 * 
 * @author gaoximin 2015-6-27
 */
public class WasteEventListAction extends AssetBaseAction {
	private static final long serialVersionUID = 1L;
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";
	private String seclv_code = "";
	private Integer waste_status = null;
	private List<WasteEvent> eventList = null;

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
		return "/asset/managepurchaselist.action";
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("seclv_code", seclv_code);
		map.put("waste_status", waste_status);
		map.put("job_status", job_status);
		eventList = assetService.getWasteEventList(map);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "WASTE", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "WASTE", 3);
		return SUCCESS;
	}
}
