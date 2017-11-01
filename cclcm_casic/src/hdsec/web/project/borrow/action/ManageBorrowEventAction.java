package hdsec.web.project.borrow.action;

import hdsec.web.project.borrow.model.EventBorrow;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManageBorrowEventAction extends BorrowBaseAction {
	private static final long serialVersionUID = 1L;
	private String barcode = "";
	private String entity_type = "";
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";// 审批状态
	private String seclv_code = "";
	private List<EventBorrow> eventList = null;
	
	public String getBarcode() {
		return barcode;
	}
	
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
	public String getEntity_type() {
		return entity_type;
	}
	
	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	
	public String getStartTime_str() {
		return startTime == null ? "" : sdf.format(startTime);
	}
	
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public Date getEndTime() {
		return endTime;
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
	
	public List<EventBorrow> getEventList() {
		return eventList;
	}
	
	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}
	
	public String getActionContext() {
		return "/borrow/manageborrowevent.action";
	}
	
	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("entity_type", entity_type);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("seclv_code", seclv_code);
		map.put("submitable", false);
		map.put("job_status", job_status);
		eventList = borrowService.getBorrowEventList(map);
		return SUCCESS;
	}
	
}
