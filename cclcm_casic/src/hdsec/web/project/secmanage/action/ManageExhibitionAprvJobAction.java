package hdsec.web.project.secmanage.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;

import java.util.ArrayList;
import java.util.List;

public class ManageExhibitionAprvJobAction extends SecManageBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ProcessJob> candidateList = null;
	private List<ProcessJob> applyList = null;

	public List<ProcessJob> getApplyList() {
		return applyList;
	}

	@Override
	public String executeFunction() throws Exception {
		applyList = new ArrayList<ProcessJob>();
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EXHIBITION);
		applyList.addAll(candidateList);

		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EXHIBITION", 1);
		return SUCCESS;
	}

}