package hdsec.web.project.transfer.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.ledger.service.LedgerService;
import hdsec.web.project.transfer.model.EventTransfer;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubmitPaperTransferEventAction extends TransferBaseAction {
	private static final long serialVersionUID = 1L;
	private Date startTime = null;
	private Date endTime = null;
	private String seclv_code = "";
	private List<EventTransfer> eventList;
	private final String jobType = JobTypeEnum.TRANSFER.getJobTypeCode();
	
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
	
	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}
	
	public String getJobType() {
		return jobType;
	}
	
	private String changeUsageTypeName(String type) {
		if ("TRANSFER".equals(type)) {
			return "流转";
		} else {
			return "其他";
		}
	}
	
	@Override
	public String executeFunction() throws Exception {
		List<EventTransfer> events = new ArrayList<>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("seclv_code", seclv_code);
		map.put("submitable", true);
		map.put("entity_type", LedgerService.PAPER);
		map.put("is_transfering", false);
		// map.put("job_status", ActivitiCons.APPLY_APPROVED_REJECT);
		for (EventTransfer event : transferService.getTransferEventList(map)) {
			event.setUsage_name(changeUsageTypeName(event.getUsage_code()));
			events.add(event);
		}
		eventList = events;
		return SUCCESS;
	}
	
	public List<EventTransfer> getEventList() {
		return eventList;
	}
}
