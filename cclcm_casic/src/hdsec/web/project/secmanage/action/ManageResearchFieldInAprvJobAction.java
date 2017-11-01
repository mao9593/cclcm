package hdsec.web.project.secmanage.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.secmanage.model.ResearchFieldInEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * 进入重要科研场地待审批任务
 * 
 * @author gaoximin 2015-7-22
 */
public class ManageResearchFieldInAprvJobAction extends SecManageBaseAction {
	private static final long serialVersionUID = 1L;
	private List<ProcessJob> candidateList = null;
	private List<ProcessJob> applyList = null;

	public List<ProcessJob> getApplyList() {
		return applyList;
	}

	@Override
	public String executeFunction() throws Exception {
		applyList = new ArrayList<ProcessJob>();
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.FIELDIN);
		applyList.addAll(candidateList);
		for (ProcessJob job : applyList) {
			String event_names = "";
			List<ResearchFieldInEvent> events = secManageService
					.getResearchFieldInEventListByJobCode(job.getJob_code());
			for (ResearchFieldInEvent event : events) {
				event_names += event.getField_site() + "  ";
			}
			job.setEvent_names(event_names);
		}
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "FIELDIN", 1);
		return SUCCESS;
	}

}
