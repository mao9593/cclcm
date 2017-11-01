package hdsec.web.project.secmanage.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.secmanage.model.FileOutMakeEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * 查看涉密文件（资料）外出制作审批记录
 * 
 * @author gaoximin 2015-7-23
 */
public class ViewFileOutMakeAprvJobAction extends SecManageBaseAction {
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
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.FILEOUTMAKE, user_name,
				seclv_code);
		jobList.addAll(tempList);
		for (ProcessJob job : jobList) {
			String event_names = "";
			List<FileOutMakeEvent> events = secManageService.getFileOutMakeEventListByJobCode(job.getJob_code());
			for (FileOutMakeEvent event : events) {
				event_names += event.getFile_name() + "  ";
			}
			job.setEvent_names(event_names);
		}
		return SUCCESS;
	}
}
