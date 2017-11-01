package hdsec.web.project.securityuser.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.securityuser.model.UserSeclvChangeEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户涉密等级变更待审批任务
 * 
 * @author gaoximin 2015-6-11
 */
public class ManageUSeclvChangeAprvJobAction extends SecurityUserBaseAction {
	private static final long serialVersionUID = 1L;
	private List<ProcessJob> candidateList = null;
	private List<ProcessJob> applyList = null;

	public List<ProcessJob> getApplyList() {
		return applyList;
	}

	@Override
	public String executeFunction() throws Exception {
		applyList = new ArrayList<ProcessJob>();
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.USERSECLV_ADD);
		applyList.addAll(candidateList);
		candidateList = basicService
				.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.USERSECLV_CHANGE);
		applyList.addAll(candidateList);
		for (ProcessJob job : applyList) {
			String event_names = "";
			List<UserSeclvChangeEvent> events = securityUserService
					.getUSeclvChangeEventListByJobCode(job.getJob_code());
			for (UserSeclvChangeEvent event : events) {
				event_names += event.getChange_user_name() + "  ";
			}
			job.setEvent_names(event_names);
		}
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "USERSECLV_ADD", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "USERSECLV_CHANGE", 1);
		return SUCCESS;
	}

}
