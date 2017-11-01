package hdsec.web.project.secplace.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.secplace.model.EnterSecplaceEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * <!--查看进入涉密场所已审批列表-->
 * 
 * @author liuyaling 2015-6-12
 */
public class ViewEnterSecplaceAprvJobAction extends SecplaceBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// private List<ProcessJob> assignedList = null;
	private List<ProcessJob> applyList = null;
	private String file_src;
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

	public void setApplyList(List<ProcessJob> applyList) {
		this.applyList = applyList;
	}

	public String getFile_src() {
		return file_src;
	}

	public void setFile_src(String file_src) {
		this.file_src = file_src;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	@Override
	public String executeFunction() throws Exception {
		List<ProcessJob> tempList = null;
		applyList = new ArrayList<ProcessJob>();
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.ENTER_SECPLACE,
				user_name, seclv_code);
		applyList.addAll(tempList);
		for (ProcessJob job : applyList) {
			String event_names = "";
			EnterSecplaceEvent event = secplaceService.getEnterSecplaceEventByJobCode(job.getJob_code());
			event_names += event.getFile_list() + "  ";
			job.setEvent_names(event_names);
		}

		return SUCCESS;
	}

}