package hdsec.web.project.device.action;

import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.device.model.DeviceEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 修改借入磁介质作业
 * 
 * @author lixiang
 * 
 */
public class UpdateDeviceEventAction extends DeviceBaseAction {

	private static final long serialVersionUID = 1L;
	private String event_code = "";// 作业流水号
	private Integer seclv_code = null;// 作业密级
	private String project_code = "";// 所属项目编号
	private String usage_code = "";// 用途编号
	private String summ = "";// 备注
	private Integer med_type = null;// 介质类型(1U盘,2移动硬盘,3笔记本,4照相机,5录像机,6录音笔,8软盘,9磁带)
	private Integer borrow_type = 0;// 借用类型（0首次申请,1申请延期）
	private String device_series = "";// 设备编号
	private String device_barcode = "";// 条码号
	private Integer borrow_date = null;// 借用时间,如:一个月
	private String place = "";// 地点
	private String update = "N";
	private DeviceEvent event = null;

	public void setUpdate(String update) {
		this.update = update;
	}

	public DeviceEvent getEvent() {
		return event;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}

	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public void setMed_type(Integer med_type) {
		this.med_type = med_type;
	}

	public void setBorrow_type(Integer borrow_type) {
		this.borrow_type = borrow_type;
	}

	public void setDevice_series(String device_series) {
		this.device_series = device_series;
	}

	public void setDevice_barcode(String device_barcode) {
		this.device_barcode = device_barcode;
	}

	public void setBorrow_date(Integer borrow_date) {
		this.borrow_date = borrow_date;
	}

	public void setPlace(String place) {
		this.place = place;
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

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_code)) {
			event = deviceService.getDeviceEventByDeviceCode(event_code);
			if (update.equals("Y")) {
				if (event != null) {
					String user_iidd = getCurUser().getUser_iidd();
					String dept_id = getCurUser().getDept_id();
					DeviceEvent event = new DeviceEvent(user_iidd, dept_id, event_code, seclv_code, usage_code,
							project_code, summ, med_type, borrow_type, device_series, device_barcode, borrow_date,
							place);
					deviceService.updateDeviceEvent(event);
					insertCommonLog("修改借入申请[" + event_code + "]");
					return "ok";
				} else {
					throw new Exception("无法查询作业信息，请重新尝试。");
				}
			} else {
				if (event != null) {
					return SUCCESS;
				} else {
					throw new Exception("申请信息为空，请重新尝试。");
				}
			}
		} else {
			throw new Exception("作业流水号为空，请重新尝试。");
		}
	}
}
