package hdsec.web.project.enter.action;

import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.enter.model.EnterEvent;
import hdsec.web.project.enter.model.EnterProcessJob;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * 查看已审批录入任务列表 2014-5-16 上午2:39:04
 * 
 * @author gaoximin
 */
public class ViewEnterAprvJobAction extends EnterBaseAction {
	private static final long serialVersionUID = 1L;
	private String user_name = "";
	private Integer seclv_code = null;
	private List<EnterProcessJob> jobList = null;
	private String type = "";

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

	public List<EnterProcessJob> getJobList() {
		return jobList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	@Override
	public String executeFunction() throws Exception {
		List<EnterProcessJob> tempList = null;
		jobList = new ArrayList<EnterProcessJob>();
		tempList = enterService.getleadinApprovedJobByUserId(getCurUser().getUser_iidd(), user_name, seclv_code);
		jobList.addAll(tempList);
		for (ProcessJob job : jobList) {
			String event_names = "";
			List<EnterEvent> events = enterService.getEnterEventListByJobCode(job.getJob_code());
			for (EnterEvent event : events) {
				event_names += event.getZipfile() + "  ";
			}
			job.setEvent_names(event_names);
		}
		return SUCCESS;
	}

}