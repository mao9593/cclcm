package hdsec.web.project.arch.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.arch.model.EventArchBrw;

import java.util.List;

public class ManageAprvJobAction extends ArchBaseAction {
	private List<ProcessJob> candidateList = null;
	private List<ProcessJob> applyList = null;

	public List<ProcessJob> getApplyList() {
		return applyList;
	}

	@Override
	public String executeFunction() throws Exception {
		candidateList = basicService.getCandidateListByUserId(getCurUser()
				.getUser_iidd(), JobTypeEnum.BRWARCH);
		applyList = candidateList;// .addAll(candidateList);
		for (ProcessJob job : applyList) {
			String event_names = "";
			List<EventArchBrw> events = archService
					.getEventArchListByJobCode(job.getJob_code());
			for (EventArchBrw event : events) {
				event_names += event.getFile_title() + "  ";
			}
			job.setEvent_names(event_names);
		}
		basicService
				.setClientMsgRead(getCurUser().getUser_iidd(), "BRWARCH", 1);
		return SUCCESS;
	}

}
