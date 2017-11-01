package hdsec.web.project.transfer.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.transfer.model.EventTransfer;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagePaperTransferEventAction extends TransferBaseAction {
	private static final long serialVersionUID = 1L;
	private String filename = "";
	private String seclv_code = "";
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";
	private String transfer_status = null;
	private List<EventTransfer> eventList = null;
	private JobTypeEnum jobType = JobTypeEnum.TRANSFER;
	private List<ProcessJob> jobList = null;

	public String getActionContext() {
		return "/transfer/managepapertransferevent.action";
	}

	public JobTypeEnum getJobType() {
		return jobType;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

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

	public List<EventTransfer> getEventList() {
		return eventList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	@Override
	public String executeFunction() throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("seclv_code", seclv_code);
		map.put("job_status", job_status);
		map.put("jobType_code", JobTypeEnum.TRANSFER);
		// jobList = basicService.getJobList(map);
		jobList = transferService.getJobListByPaper(map);
		for (ProcessJob job : jobList) {
			String event_names = "";
			List<EventTransfer> events = transferService.getTransferEventListByJobCode(job.getJob_code(), "paper");
			for (EventTransfer event : events) {
				EntityPaper paper = ledgerService.getPaperByBarcode(event.getBarcode());
				if (paper != null) {
					event_names += paper.getFile_title() + "  ";
				}
			}
			job.setEvent_names(event_names);
		}
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "TRANSFER", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "TRANSFER", 3);
		return SUCCESS;
	}

	public String getTransfer_status() {
		return transfer_status;
	}

	public void setTransfer_status(String transfer_status) {
		this.transfer_status = transfer_status;
	}

	public List<ProcessJob> getJobList() {
		return jobList;
	}

}
