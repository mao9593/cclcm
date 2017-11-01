package hdsec.web.project.device.action;

import hdsec.web.project.device.model.DeviceEvent;
import hdsec.web.project.device.model.EntityDevice;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查看磁介质借入申请列表
 * 
 * @author lixiang
 * 
 */
public class ViewDeviceEventListAction extends DeviceBaseAction {
	
	private static final long serialVersionUID = 1L;
	private List<EntityDevice> deviceList = null;
	private String device_barcode = "";
	private Integer seclv_code = null;
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";
	private Integer distribute_state = 0;// 0未分配 1已分配
	private String user_name = "";
	private List<DeviceEvent> eventList = null;
	
	public String getdevice_barcode() {
		return device_barcode;
	}
	
	public void setdevice_barcode(String device_barcode) {
		this.device_barcode = device_barcode;
	}
	
	public Integer getSeclv_code() {
		return seclv_code;
	}
	
	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}
	
	public String getStartTime() {
		return startTime == null ? "" : sdf.format(startTime);
	}
	
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public String getEndTime() {
		return endTime == null ? "" : sdf.format(endTime);
	}
	
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public String getJob_status() {
		return job_status;
	}
	
	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}
	
	public String getDevice_barcode() {
		return device_barcode;
	}
	
	public void setDevice_barcode(String device_barcode) {
		this.device_barcode = device_barcode;
	}
	
	public List<EntityDevice> getDeviceList() {
		return deviceList;
	}
	
	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}
	
	public void setDeviceList(List<EntityDevice> deviceList) {
		this.deviceList = deviceList;
	}
	
	public List<DeviceEvent> getEventList() {
		return eventList;
	}
	
	public void setEventList(List<DeviceEvent> eventList) {
		this.eventList = eventList;
	}
	
	public Integer getDistribute_state() {
		return distribute_state;
	}
	
	public void setDistribute_state(Integer distribute_state) {
		this.distribute_state = distribute_state;
	}
	
	public String getUser_name() {
		return user_name;
	}
	
	public void setUser_name(String user_name) {
		this.user_name = user_name.trim();
	}
	
	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("seclv_code", seclv_code);
		map.put("submitable", false);
		//map.put("job_status", "true");
		map.put("user_name", user_name);
		if (distribute_state != null) {
			if (distribute_state == 0) {
				map.put("undistribute", true);
			} else if (distribute_state == 1) {
				map.put("distributed", true);
			}
		}
		eventList = deviceService.getDeviceEventList(map);
		return SUCCESS;
	}
	
}
