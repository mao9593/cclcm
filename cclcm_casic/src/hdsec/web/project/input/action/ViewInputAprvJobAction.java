package hdsec.web.project.input.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.input.model.InputEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.List;

public class ViewInputAprvJobAction extends InputBaseAction {
	private static final long serialVersionUID = 1L;
	private String user_name = "";
	private Integer seclv_code = null;
	private List<ProcessJob> jobList = null;

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
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

	public List<ProcessJob> getJobList() {
		return jobList;
	}

	public void setJobList(List<ProcessJob> jobList) {
		this.jobList = jobList;
	}

	@Override
	public String executeFunction() throws Exception {
		List<ProcessJob> tempList = null;
		jobList = new ArrayList<ProcessJob>();
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.MSG_INPUT, user_name,
				seclv_code);
		jobList.addAll(tempList);
		for (ProcessJob job : jobList) {
			String event_names = "";
			List<InputEvent> events = inputService.getInputEventListByJobCode(job.getJob_code());
			for (InputEvent event : events) {
				event_names += event.getFile_list() + "  ";
			}
			job.setEvent_names(event_names);
		}
		return SUCCESS;
	}

}
