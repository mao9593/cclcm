package hdsec.web.project.infosystem.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;

import java.util.ArrayList;
import java.util.List;

public class ManageInfosystemAprvJobAction extends InfosystemBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ProcessJob> candidateList = null;
	// private List<ProcessJob> assignedList = null;
	private List<ProcessJob> applyList = null;

	public List<ProcessJob> getCandidateList() {
		return candidateList;
	}

	public List<ProcessJob> getApplyList() {
		return applyList;
	}

	@Override
	public String executeFunction() throws Exception {
		applyList = new ArrayList<ProcessJob>();
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_REPCOM);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_REINSTALL);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_QUITINT);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_USBKEY);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_OPENPORT);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(),
				JobTypeEnum.EVENT_LOCALPRINTER);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.BORROW_BOOK);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.BORROW_BOOKOUT);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_INTCOM);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_SINCOM);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_CHGCOM);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_DESCOM);
		applyList.addAll(candidateList);
		/*
		 * for (ProcessJob job : applyList) { String event_names = ""; ChangeDeviceEvent event =
		 * computerService.getChangeDeviceEventByJobCode(job.getJob_code()); if (event == null) { throw new
		 * Exception("审批记录对应的申请不存在，请与管理员联系"); } else { event_names += event.getEvent_name() + "  ";
		 * job.setEvent_names(event_names); }
		 * 
		 * }
		 */
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_REPCOM", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_REINSTALL", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_QUITINT", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_USBKEY", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_OPENPORT", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_LOCALPRINTER", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "BORROW_BOOK", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "BORROW_BOOKOUT", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_INTCOM", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_SINCOM", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_CHGCOM", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_DESCOM", 1);

		return SUCCESS;
	}

}