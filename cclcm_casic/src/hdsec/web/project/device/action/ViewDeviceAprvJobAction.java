package hdsec.web.project.device.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.device.model.DeviceEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * 查看已审批借入任务列表
 * 
 * @author lixiang
 * 
 */
public class ViewDeviceAprvJobAction extends DeviceBaseAction {

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
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.DEVICE, user_name,
				seclv_code);
		jobList.addAll(tempList);
		for (ProcessJob job : jobList) {
			String event_names = "";
			List<DeviceEvent> events = deviceService.getDeviceEventListByJobCode(job.getJob_code());
			for (DeviceEvent event : events) {
				event_names += event.getSeclv_name() + event.getMed_type_name() + "  ";
			}
			job.setEvent_names(event_names);
		}
		return SUCCESS;
	}

}
