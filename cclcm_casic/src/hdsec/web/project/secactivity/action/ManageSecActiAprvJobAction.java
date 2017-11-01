package hdsec.web.project.secactivity.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户涉密活动审批任务
 * 
 * @author gj
 */
public class ManageSecActiAprvJobAction extends SecuActiBaseAction {
	private static final long serialVersionUID = 1L;
	private List<ProcessJob> candidateList = null;
	private List<ProcessJob> applyList = null;

	public List<ProcessJob> getApplyList() {
		return applyList;
	}

	@Override
	public String executeFunction() throws Exception {
		applyList = new ArrayList<ProcessJob>();
		candidateList = basicService
				.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.USERSEC_ACTIVITY);
		applyList.addAll(candidateList);

		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "USERSEC_ACTIVITY", 1);
		return SUCCESS;
	}
}
