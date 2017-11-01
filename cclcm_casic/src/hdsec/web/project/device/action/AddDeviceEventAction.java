package hdsec.web.project.device.action;

import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.device.model.DeviceEvent;
import hdsec.web.project.device.model.DeviceType;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.List;

/**
 * 添加磁介质借入借出作业
 * 
 * @author lixiang
 * 
 */
public class AddDeviceEventAction extends DeviceBaseAction {

	private static final long serialVersionUID = 1L;
	private String event_code = "";// 作业号
	private Integer seclv_code = null;// 介质密级
	private Integer med_type = null;// 介质类型(1U盘,2移动硬盘,3笔记本,4照相机,5录像机,6录音笔,8软盘,9磁带)
	private String result = "";

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public void setMed_type(Integer med_type) {
		this.med_type = med_type;
	}

	public String getResult() {
		return result;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getDeviceSecLevelByUser(getCurUser().getUser_iidd());
	}

	public List<SysUsage> getUsageList() {
		return basicService.getSysUsageList();
	}

	public List<SysProject> getProjectList() {
		return basicService.getSysProjectList();
	}

	public List<DeviceType> getDeviceTypeList() {
		return deviceService.getDeviceTypeList();
	}

	@Override
	public String executeFunction() throws Exception {
		event_code = getCurUser().getUser_iidd() + "-DEVICE-" + System.currentTimeMillis();
		String user_iidd = getCurUser().getUser_iidd();
		String dept_id = getCurUser().getDept_id();
		DeviceEvent event = new DeviceEvent();
		event.setUser_iidd(user_iidd);
		event.setDept_id(dept_id);
		event.setEvent_code(event_code);
		event.setSeclv_code(seclv_code);
		event.setMed_type(med_type);
		event.setApply_time(new Date());
		logger.info("event_code is:" + event_code);
		deviceService.addDeviceEvent(event);
		insertCommonLog("添加磁介质借用作业[" + event_code + "]");

		result = event_code;
		return SUCCESS;

	}
}
