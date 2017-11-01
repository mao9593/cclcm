package hdsec.web.project.securityuser.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;

import java.util.ArrayList;
import java.util.List;

/**
 * BM待审批任务
 * 
 * @author gaoximin 2015-10-14
 */
public class ManageAprvJobAction extends SecurityUserBaseAction {
	private static final long serialVersionUID = 1L;
	private List<ProcessJob> candidateList = null;
	private List<ProcessJob> applyList = null;
	private String module = "";// 空默认为人员模块，其他根据参数判定模块（保密处管理、其他保密管理）

	public List<ProcessJob> getApplyList() {
		return applyList;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	@Override
	public String executeFunction() throws Exception {
		applyList = new ArrayList<ProcessJob>();
		if (module.equals("")) {
			// 涉密人员
			candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.USER_INFO);
			applyList.addAll(candidateList);

			candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.USERSECLV_ADD);
			applyList.addAll(candidateList);
			candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.USERSECLV_CHANGE);
			applyList.addAll(candidateList);

			candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.RESIGN);
			applyList.addAll(candidateList);

			candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.SECUSER_ABROAD);
			applyList.addAll(candidateList);

			candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.SECUSER_ENTRUST);
			applyList.addAll(candidateList);
			// 置消息为已读
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "USER_INFO", 1);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "USERSECLV_ADD", 1);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "USERSECLV_CHANGE", 1);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "RESIGN", 1);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "SECUSER_ABROAD", 1);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "SECUSER_ENTRUST", 1);
		} else if (module.equals("secmanageBMC")) {
			// 保密处管理
			candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.SEC_CHECK);
			applyList.addAll(candidateList);
			candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.PUNISH_DEPT);
			applyList.addAll(candidateList);
			candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.PUNISH_SECCHECK);
			applyList.addAll(candidateList);
			candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.PUNISH_RECTIFY);
			applyList.addAll(candidateList);
			// 置消息为已读
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "SEC_CHECK", 1);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PUNISH_DEPT", 1);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PUNISH_SECCHECK", 1);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PUNISH_RECTIFY", 1);
		} else if (module.equals("secmanage")) {
			// 其他保密管理
			candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.FIELDIN);
			applyList.addAll(candidateList);
			candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.FILEOUTMAKE);
			applyList.addAll(candidateList);
			candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.USERSEC_ACTIVITY);
			applyList.addAll(candidateList);
			candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.ENTER_SECPLACE);
			applyList.addAll(candidateList);
			candidateList = basicService
					.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.OUT_EXCHANGE);
			applyList.addAll(candidateList);

			// 置消息为已读
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "FIELDIN", 1);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "FILEOUTMAKE", 1);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "USERSEC_ACTIVITY", 1);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "ENTER_SECPLACE", 1);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "OUT_EXCHANGE", 1);
		} else if (module.equals("publicity")) {
			// 宣传报道及提供资料管理
			candidateList = basicService
					.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_REPORT);
			applyList.addAll(candidateList);
			candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.EVENT_REPORT2);
			applyList.addAll(candidateList);
			candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.EVENT_REPORT3);
			applyList.addAll(candidateList);
			candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.EVENT_DEPTREPORT);
			applyList.addAll(candidateList);
			candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.EVENT_INTRAPUBL);
			applyList.addAll(candidateList);
			candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.EVENT_INTERPUBL);
			applyList.addAll(candidateList);
			candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.MATERIAL);
			applyList.addAll(candidateList);
			candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.INTER_EMAIL);
			applyList.addAll(candidateList);
			candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EXHIBITION);
			applyList.addAll(candidateList);
			candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.PAPER_RESEARCH);
			applyList.addAll(candidateList);
			candidateList = basicService
					.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.PAPER_OTHERS);
			applyList.addAll(candidateList);
			applyList.addAll(candidateList);
			candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.PAPERPATENT);
			applyList.addAll(candidateList);
			// 置消息为已读
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_REPORT", 1);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_REPORT2", 1);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_REPORT3", 1);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_DEPTREPORT", 1);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_INTRAPUBL", 1);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_INTERPUBL", 1);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "MATERIAL", 1);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "INTER_EMAIL", 1);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EXHIBITION", 1);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PAPER_RESEARCH", 1);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PAPER_OTHERS", 1);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PAPERPATENT", 1);
		}

		return SUCCESS;
	}

}
