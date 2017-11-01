package hdsec.web.project.publicity.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.publicity.model.ReportEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * 查看用户涉密活动审批记录
 * 
 * @author LS
 */

public class ViewReportAprvJobAction extends PublicityBaseAction {
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
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_REPORT,
				user_name, seclv_code);
		jobList.addAll(tempList);

		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_REPORT2,
				user_name, seclv_code);
		jobList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_REPORT3,
				user_name, seclv_code);
		jobList.addAll(tempList);
		/* jobList.addAll(tempList); */
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_DEPTREPORT,
				user_name, seclv_code);
		jobList.addAll(tempList);

		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_INTRAPUBL,
				user_name, seclv_code);
		jobList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_INTERPUBL,
				user_name, seclv_code);
		jobList.addAll(tempList);
		for (ProcessJob job : jobList) {
			String event_names = "";
			List<ReportEvent> events = publicityService.getPublReportEventListByJobCode(job.getJob_code());
			for (ReportEvent event : events) {
				event_names += event.getUser_name() + "  ";
			}
			job.setEvent_names(event_names);
		}
		return SUCCESS;
	}
}
