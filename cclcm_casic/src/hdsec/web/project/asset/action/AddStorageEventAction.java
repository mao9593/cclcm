package hdsec.web.project.asset.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.asset.model.PurchaseEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 入库申请/审批通过的采购申请
 * 
 * @author gaoximin 2015-7-28
 */
public class AddStorageEventAction extends AssetBaseAction {
	private static final long serialVersionUID = 1L;
	private Date startTime = null;
	private Date endTime = null;
	private String seclv_code = "";
	private String user_iidd = "";
	private Integer store_status = null;
	private List<PurchaseEvent> eventList = null;
	private JobTypeEnum jobType = JobTypeEnum.STORE;
	private String user_name = "";

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

	public String getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public Integer getStore_status() {
		return store_status;
	}

	public void setStore_status(Integer store_status) {
		this.store_status = store_status;
	}

	public List<PurchaseEvent> getEventList() {
		return eventList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public JobTypeEnum getJobType() {
		return jobType;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getActionContext() {
		return "/asset/addstorageevent.action";
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", user_iidd);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		/* map.put("seclv_code", seclv_code); */
		map.put("job_status", "true");
		map.put("submitable", false);
		map.put("storage", true);
		eventList = assetService.getPurchaseEventList(map);
		return SUCCESS;
	}
}
