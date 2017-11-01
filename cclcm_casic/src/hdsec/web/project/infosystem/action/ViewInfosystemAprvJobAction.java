package hdsec.web.project.infosystem.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.List;

public class ViewInfosystemAprvJobAction extends InfosystemBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ProcessJob> applyList = null;
	private String user_name = "";
	private Integer seclv_code = null;

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public List<ProcessJob> getApplyList() {
		return applyList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	@Override
	public String executeFunction() throws Exception {
		List<ProcessJob> tempList = null;
		applyList = new ArrayList<ProcessJob>();
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_REPCOM,
				user_name, seclv_code);
		applyList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_REINSTALL,
				user_name, seclv_code);
		applyList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_QUITINT,
				user_name, seclv_code);
		applyList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_USBKEY,
				user_name, seclv_code);
		applyList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_OPENPORT,
				user_name, seclv_code);
		applyList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_LOCALPRINTER,
				user_name, seclv_code);
		applyList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.BORROW_BOOK, user_name,
				seclv_code);
		applyList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.BORROW_BOOKOUT,
				user_name, seclv_code);
		applyList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_INTCOM,
				user_name, seclv_code);
		applyList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_SINCOM,
				user_name, seclv_code);
		applyList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_CHGCOM,
				user_name, seclv_code);
		applyList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_DESCOM,
				user_name, seclv_code);
		applyList.addAll(tempList);
		/*
		 * for (ProcessJob job : applyList) { String event_names = ""; ChangeDeviceEvent event =
		 * computerService.getChangeDeviceEventByJobCode(job.getJob_code()); event_names += event.getEvent_name() +
		 * "  "; job.setEvent_names(event_names); }
		 */
		return SUCCESS;
	}

}