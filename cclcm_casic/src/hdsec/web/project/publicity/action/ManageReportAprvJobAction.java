package hdsec.web.project.publicity.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.publicity.model.ReportEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * 宣传报道审批任务
 * 
 * @author LS
 */
public class ManageReportAprvJobAction extends PublicityBaseAction {
	private static final long serialVersionUID = 1L;
	private List<ProcessJob> candidateList = null;
	private List<ProcessJob> applyList = null;

	public List<ProcessJob> getApplyList() {
		return applyList;
	}

	@Override
	public String executeFunction() throws Exception {
		applyList = new ArrayList<ProcessJob>();
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_REPORT);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_REPORT2);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_REPORT3);
		applyList.addAll(candidateList);
		candidateList = basicService
				.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_DEPTREPORT);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_INTRAPUBL);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_INTERPUBL);
		applyList.addAll(candidateList);
		for (ProcessJob job : applyList) {
			String event_names = "";
			List<ReportEvent> events = publicityService.getPublReportEventListByJobCode(job.getJob_code());
			for (ReportEvent event : events) {
				event_names += event.getUser_name() + "  ";
			}
			job.setEvent_names(event_names);
		}
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_REPORT", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_REPORT2", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_REPORT3", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_DEPTREPORT", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_INTRAPUBL", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_INTERPUBL", 1);
		return SUCCESS;
	}
}
