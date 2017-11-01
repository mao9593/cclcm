package hdsec.web.project.securityuser.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.securityuser.model.ResignEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户离职待审批任务
 * 
 * @author yangjl 2015-6-23
 */
public class ManageUResignAprvJobAction extends SecurityUserBaseAction {
	private static final long serialVersionUID = 1L;
	private List<ProcessJob> candidateList = null;
	private List<ProcessJob> applyList = null;

	public List<ProcessJob> getApplyList() {
		return applyList;
	}

	@Override
	public String executeFunction() throws Exception {
		applyList = new ArrayList<ProcessJob>();
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.RESIGN);
		applyList.addAll(candidateList);
		for (ProcessJob job : applyList) {
			String event_names = "";
			List<ResignEvent> events = securityUserService.getUResignEventListByJobCode(job.getJob_code());
			for (ResignEvent event : events) {
				event_names += event.getResign_user_name() + "  ";
			}
			job.setEvent_names(event_names);
		}
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "RESIGN", 1);
		return SUCCESS;
	}

}
