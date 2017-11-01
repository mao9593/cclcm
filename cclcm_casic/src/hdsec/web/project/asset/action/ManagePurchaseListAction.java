package hdsec.web.project.asset.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.asset.model.PurchaseEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查看资产管理采购记录
 * 
 * @author gaoximin 2015-6-27
 */
public class ManagePurchaseListAction extends AssetBaseAction {
	private static final long serialVersionUID = 1L;
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";
	private String seclv_code = "";
	private Integer store_status = null;
	private List<PurchaseEvent> eventList = null;
	private JobTypeEnum jobType = JobTypeEnum.PURCHASE;

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
		map.put("store_status", store_status);
		map.put("job_status", job_status);
		map.put("submitable", false);
		eventList = assetService.getPurchaseEventList(map);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PURCHASE",
				2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PURCHASE",
				3);
		return SUCCESS;
	}
}
