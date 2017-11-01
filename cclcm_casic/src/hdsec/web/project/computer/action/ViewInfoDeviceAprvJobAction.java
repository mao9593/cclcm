package hdsec.web.project.computer.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.List;

public class ViewInfoDeviceAprvJobAction extends ComputerBaseAction {
	private static final long serialVersionUID = 1L;
	private List<ProcessJob> applyList = null;
	private String user_name = "";
	private Integer seclv_code = null;

	public List<ProcessJob> getApplyList() {
		return applyList;
	}

	public void setApplyList(List<ProcessJob> applyList) {
		this.applyList = applyList;
	}

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

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	@Override
	public String executeFunction() throws Exception {
		List<ProcessJob> tempList = null;
		applyList = new ArrayList<ProcessJob>();
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.INFO_DEVICE, user_name,
				seclv_code);
		applyList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.INFO_OTHER, user_name,
				seclv_code);
		applyList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.DEVICE_CHANGE,
				user_name, seclv_code);
		applyList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.CHANGE_OTHER,
				user_name, seclv_code);
		applyList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.DEVICE_DES, user_name,
				seclv_code);
		applyList.addAll(tempList);
		return SUCCESS;
	}

}