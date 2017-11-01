package hdsec.web.project.secactivity.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;

import java.util.ArrayList;
import java.util.List;

/**
 * 涉外交流审批任务
 * 
 * @author gj
 */
public class ManageSecOutExchangeAprvJobAction extends SecuActiBaseAction {
	private static final long serialVersionUID = 1L;
	private List<ProcessJob> candidateList = null;
	private List<ProcessJob> applyList = null;

	public List<ProcessJob> getApplyList() {
		return applyList;
	}

	@Override
	public String executeFunction() throws Exception {
		applyList = new ArrayList<ProcessJob>();
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.OUT_EXCHANGE);
		applyList.addAll(candidateList);

		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "OUT_EXCHANGE", 1);
		return SUCCESS;
	}
}
