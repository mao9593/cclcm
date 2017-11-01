package hdsec.web.project.secmanage.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.secmanage.model.PunishRectifyEvent;

import java.util.ArrayList;
import java.util.List;

public class ManagePunishAprvJobAction extends SecManageBaseAction {
	private static final long serialVersionUID = 1L;
	private List<ProcessJob> candidateList = null;
	private List<ProcessJob> applyList = null;

	public List<ProcessJob> getApplyList() {
		return applyList;
	}

	@Override
	public String executeFunction() throws Exception {
		applyList = new ArrayList<ProcessJob>();
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.PUNISH_DEPT);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.PUNISH_SECCHECK);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.PUNISH_RECTIFY);
		applyList.addAll(candidateList);
		for (ProcessJob job : applyList) {
			String event_names = "";
			List<PunishRectifyEvent> events = secManageService.getPunishEventListByJobCode(job.getJob_code());
			for (PunishRectifyEvent event : events) {
				event_names += event.getDuty_user_name() + "  ";
			}
			job.setEvent_names(event_names);
		}
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PUNISH_DEPT", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PUNISH_SECCHECK", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PUNISH_RECTIFY", 1);
		return SUCCESS;
	}

}
