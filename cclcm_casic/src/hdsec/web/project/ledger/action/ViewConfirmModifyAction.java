package hdsec.web.project.ledger.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.ledger.model.EventModify;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewConfirmModifyAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private String barcode = "";
	private String seclv_code = "";
	private Date startTime = null;
	private Date endTime = null;
	private int modify_status;

	private List<EventModify> eventList = null;
	private final JobTypeEnum jobType = JobTypeEnum.MODIFY_SECLV;

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public List<EventModify> getEventList() {
		return eventList;
	}

	public void setEventList(List<EventModify> eventList) {
		this.eventList = eventList;
	}

	public JobTypeEnum getJobType() {
		return jobType;
	}

	public int getModify_status() {
		return modify_status;
	}

	public void setModify_status(int modify_status) {
		this.modify_status = modify_status;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("barcode", barcode);
		map.put("seclv_code", seclv_code);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("job_status", "true");// 审批已通过
		map.put("modify_status", "0");
		eventList = ledgerService.getModifyEventList(map);
		for (EventModify event : eventList) {
			if ("paper".equalsIgnoreCase(event.getEntity_type())) {
				EntityPaper paper = ledgerService.getPaperByBarcode(event.getBarcode());
				if (null != paper) {
					event.setFile_name(paper.getFile_title());
					event.setPre_seclv_name(ledgerService.getSeclvNameByCode(event.getPre_seclv()));
					event.setTrg_seclv_name(ledgerService.getSeclvNameByCode(event.getTrg_seclv()));
				}
			} else {
				EntityCD cd = ledgerService.getCDByBarcode(event.getBarcode());
				if (null != cd) {
					event.setFile_name(cd.getFile_list());
					event.setPre_seclv_name(ledgerService.getSeclvNameByCode(event.getPre_seclv()));
					event.setTrg_seclv_name(ledgerService.getSeclvNameByCode(event.getTrg_seclv()));
				}
			}
		}
		return SUCCESS;
	}

}
