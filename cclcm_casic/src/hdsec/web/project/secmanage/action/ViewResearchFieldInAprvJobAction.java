package hdsec.web.project.secmanage.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.secmanage.model.ResearchFieldInEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * 查看进入重要科研场地审批记录
 * 
 * @author gaoximin 2015-7-22
 */
public class ViewResearchFieldInAprvJobAction extends SecManageBaseAction {
	private static final long serialVersionUID = 1L;
	private String user_name = "";
	private Integer seclv_code = null;
	private List<ProcessJob> jobList = null;

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name.trim();
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public List<ProcessJob> getJobList() {
		return jobList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	@Override
	public String executeFunction() throws Exception {
		List<ProcessJob> tempList = null;
		jobList = new ArrayList<ProcessJob>();
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.FIELDIN, user_name,
				seclv_code);
		jobList.addAll(tempList);
		for (ProcessJob job : jobList) {
			String event_names = "";
			List<ResearchFieldInEvent> events = secManageService
					.getResearchFieldInEventListByJobCode(job.getJob_code());
			for (ResearchFieldInEvent event : events) {
				event_names += event.getField_site() + "  ";
			}
			job.setEvent_names(event_names);
		}
		return SUCCESS;
	}
}
