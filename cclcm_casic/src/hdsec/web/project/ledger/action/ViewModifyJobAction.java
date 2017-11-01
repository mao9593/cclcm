package hdsec.web.project.ledger.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.ledger.model.EventModify;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.List;

public class ViewModifyJobAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private String user_name = "";
	private Integer seclv_code = null;
	private List<ProcessJob> jobList = null;

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public List<ProcessJob> getJobList() {
		return jobList;
	}

	public void setJobList(List<ProcessJob> jobList) {
		this.jobList = jobList;
	}

	@Override
	public String executeFunction() throws Exception {
		List<ProcessJob> tempList = null;
		jobList = new ArrayList<ProcessJob>();
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.MODIFY_SECLV,
				user_name, seclv_code);
		jobList.addAll(tempList);
		for (ProcessJob job : jobList) {
			String event_names = "";
			List<EventModify> events = ledgerService.getModifyEventListByJobCode(job.getJob_code());
			for (EventModify event : events) {
				if (event.getEntity_type().equalsIgnoreCase("Paper")) {
					EntityPaper paper = ledgerService.getPaperByBarcode(event.getBarcode());
					if (paper != null) {
						event_names += paper.getFile_title() + "  ";
					}
				} else {
					EntityCD cd = ledgerService.getCDByBarcode(event.getBarcode());
					if (cd != null) {
						event_names += cd.getFile_list() + "  ";
					}
				}
			}
			job.setEvent_names(event_names);
		}
		return SUCCESS;
	}
}
