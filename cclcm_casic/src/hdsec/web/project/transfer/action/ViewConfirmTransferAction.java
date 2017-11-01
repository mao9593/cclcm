package hdsec.web.project.transfer.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.transfer.model.EventTransfer;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 确认流转列表
 * 
 * @author lixiang
 * 
 */
public class ViewConfirmTransferAction extends TransferBaseAction {
	private static final long serialVersionUID = 1L;
	private String barcode = "";
	private String seclv_code = "";
	private Date startTime = null;
	private Date endTime = null;

	private List<EventTransfer> eventList = null;
	private final JobTypeEnum jobType = JobTypeEnum.TRANSFER;

	public JobTypeEnum getJobType() {
		return jobType;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode.trim();
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

	public List<EventTransfer> getEventList() {
		return eventList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accept_user_iidd", getCurUser().getUser_iidd());
		map.put("barcode", barcode);
		map.put("seclv_code", seclv_code);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("job_status", "true");// 审批已通过
		map.put("submitable", true);// 流转未完成
		map.put("is_transfering", true);
		eventList = transferService.getTransferEventList(map);
		for (EventTransfer event : eventList) {
			if ("paper".equalsIgnoreCase(event.getEntity_type())) {
				EntityPaper paper = ledgerService.getPaperByBarcode(event.getBarcode());
				if (null != paper) {
					event.setFile_name(paper.getFile_title());
				}
			} else {
				EntityCD cd = ledgerService.getCDByBarcode(event.getBarcode());
				if (null != cd) {
					event.setFile_name(cd.getFile_list());
				}
			}
		}
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "TRANSFER_CONFIRM", 8);
		return SUCCESS;
	}

}
