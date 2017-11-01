package hdsec.web.project.secmanage.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;

import java.util.ArrayList;
import java.util.List;

/**
 * 论文发表专利申请审批任务
 * 
 * @author gaoximin 2015-9-17
 */
public class ManagePaperPatentAprvJobAction extends SecManageBaseAction {

	private static final long serialVersionUID = 1L;
	private List<ProcessJob> candidateList = null;
	private List<ProcessJob> applyList = null;

	public List<ProcessJob> getApplyList() {
		return applyList;
	}

	@Override
	public String executeFunction() throws Exception {
		applyList = new ArrayList<ProcessJob>();
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.PAPER_RESEARCH);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.PAPER_OTHERS);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.PAPERPATENT);
		applyList.addAll(candidateList);

		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PAPER_RESEARCH", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PAPER_OTHERS", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PAPERPATENT", 1);
		return SUCCESS;
	}

}