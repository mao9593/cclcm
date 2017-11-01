package hdsec.web.project.computer.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;

import java.util.ArrayList;
import java.util.List;

public class ManageInfoDeviceAprvJobAction extends ComputerBaseAction {
	private static final long serialVersionUID = 1L;
	private List<ProcessJob> candidateList = null;
	private List<ProcessJob> applyList = null;

	public List<ProcessJob> getApplyList() {
		return applyList;
	}

	@Override
	public String executeFunction() throws Exception {
		applyList = new ArrayList<ProcessJob>();
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.INFO_DEVICE);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.INFO_OTHER);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.DEVICE_CHANGE);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.CHANGE_OTHER);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.DEVICE_DES);
		applyList.addAll(candidateList);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "INFO_DEVICE", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "INFO_OTHER", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "DEVICE_CHANGE", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "CHANGE_OTHER", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "DEVICE_DES", 1);
		return SUCCESS;
	}

}