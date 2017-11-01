package hdsec.web.project.secmanage.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;

import java.util.ArrayList;
import java.util.List;

/**
 * 外网电子邮件待审批任务
 * 
 * @author guojiao
 */
public class ManageInternetEmailAprvJobAction extends SecManageBaseAction {
	private static final long serialVersionUID = 1L;
	private List<ProcessJob> candidateList = null;
	private List<ProcessJob> applyList = null;

	public List<ProcessJob> getApplyList() {
		return applyList;
	}

	@Override
	public String executeFunction() throws Exception {
		applyList = new ArrayList<ProcessJob>();
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.INTER_EMAIL);
		applyList.addAll(candidateList);

		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "INTER_EMAIL", 1);
		return SUCCESS;
	}

}
