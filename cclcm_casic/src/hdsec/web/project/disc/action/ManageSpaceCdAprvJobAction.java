package hdsec.web.project.disc.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;

import java.util.ArrayList;
import java.util.List;

/**
 * 待审批空白盘任务列表
 * 
 * @author zp
 * 
 */
public class ManageSpaceCdAprvJobAction extends DiscBaseAction {

	private static final long serialVersionUID = 1L;
	private List<ProcessJob> candidateList = null;
	private List<ProcessJob> applyList = null;

	public List<ProcessJob> getApplyList() {
		return applyList;
	}

	@Override
	public String executeFunction() throws Exception {
		applyList = new ArrayList<ProcessJob>();
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.SPACECD_BORROW);
		applyList.addAll(candidateList);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "SPACECD_BORROW", 1);
		return SUCCESS;
	}

}
