package hdsec.web.project.ledger.action;

import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.basic.model.SysPrinter;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EventTemp;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查看已归档光盘二次处理任务记录
 * 
 * @author guojiao
 * 
 */
public class ManageFileCdEventAction extends LedgerBaseAction {

	private static final long serialVersionUID = 1L;
	private Integer seclv_code = null;
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";
	private List<ProcessJob> jobList = null;

	public List<ProcessJob> getJobList() {
		return jobList;
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

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public List<SysPrinter> getPrinterList() {
		return basicService.getSysPrinterList();
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("seclv_code", seclv_code);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("job_status", job_status);
		map.put("entity_type", "cd");
		jobList = ledgerService.getTempJobList(map);
		for (ProcessJob job : jobList) {
			String event_names = "";
			List<EventTemp> events = ledgerService.getTempEventListByJobCode(job.getJob_code());
			for (EventTemp event : events) {
				EntityCD cd = ledgerService.getCDByBarcode(event.getBarcode());
				if (cd != null) {
					event_names += cd.getFile_list() + "  ";
				}
			}
			job.setEvent_names(event_names);
		}
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "FILECD_DESTROY", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "FILECD_DESTROY", 3);
		return SUCCESS;
	}
}
