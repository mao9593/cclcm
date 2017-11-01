package hdsec.web.project.securityuser.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.securityuser.model.ResignEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * 查看用户离职审批记录
 * 
 * @author yangjl 2015-6-23
 */
public class ViewUResignAprvJobAction extends SecurityUserBaseAction {
	private static final long serialVersionUID = 1L;
	private String user_name = "";
	private Integer seclv_code = null;
	private List<ProcessJob> jobList = null;
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

	public List<ProcessJob> getJobList() {
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
		List<ProcessJob> tempList = null;
		jobList = new ArrayList<ProcessJob>();
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.RESIGN, user_name,
				seclv_code);
		jobList.addAll(tempList);
		for (ProcessJob job : jobList) {
			String event_names = "";
			List<ResignEvent> events = securityUserService.getUResignEventListByJobCode(job.getJob_code());
			for (ResignEvent event : events) {
				event_names += event.getResign_user_name() + "  ";
			}
			job.setEvent_names(event_names);
		}
		return SUCCESS;
	}
}
