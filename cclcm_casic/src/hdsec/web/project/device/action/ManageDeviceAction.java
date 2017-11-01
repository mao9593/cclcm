package hdsec.web.project.device.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.device.model.DeviceStatusEnum;
import hdsec.web.project.device.model.DeviceType;
import hdsec.web.project.device.model.EntityDevice;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理磁介质列表
 * 
 * @author lixiang
 * 
 */
public class ManageDeviceAction extends DeviceBaseAction {
	private static final long serialVersionUID = 1L;
	private Date startTime = null;
	private Date endTime = null;
	private List<EntityDevice> deviceList = null;
	private String device_code = "";
	private String device_barcode = "";
	private String device_series = "";
	private String seclv_code = "";
	private Integer med_type;
	private String device_status = "";
	// private final String jobType = JobTypeEnum.DESTROY_DEVICE.getJobTypeCode();
	private final String jobType = JobTypeEnum.DESTROY_DEVICE_BYSELF.getJobTypeCode();

	public String getDevice_code() {
		return device_code;
	}

	public void setDevice_code(String device_code) {
		this.device_code = device_code;
	}

	public String getStartTime_str() {
		return startTime == null ? "" : sdf.format(startTime);
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getEndTime_str() {
		return endTime == null ? "" : sdf.format(endTime);
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getDevice_barcode() {
		return device_barcode;
	}

	public void setDevice_barcode(String device_barcode) {
		this.device_barcode = device_barcode;
	}

	public String getDevice_series() {
		return device_series;
	}

	public void setDevice_series(String device_series) {
		this.device_series = device_series;
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public Integer getMed_type() {
		return med_type;
	}

	public void setMed_type(Integer med_type) {
		this.med_type = med_type;
	}

	public String getDevice_status() {
		return device_status;
	}

	public void setDevice_status(String device_status) {
		this.device_status = device_status;
	}

	public void setDeviceList(List<EntityDevice> deviceList) {
		this.deviceList = deviceList;
	}

	public List<EntityDevice> getDeviceList() {
		return deviceList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public List<DeviceStatusEnum> getDsList() {
		List<DeviceStatusEnum> tmpList = new ArrayList<DeviceStatusEnum>();
		tmpList.add(DeviceStatusEnum.DS0);
		tmpList.add(DeviceStatusEnum.DS1);
		tmpList.add(DeviceStatusEnum.DS2);
		tmpList.add(DeviceStatusEnum.DS3);
		tmpList.add(DeviceStatusEnum.DS4);
		tmpList.add(DeviceStatusEnum.DS5);
		tmpList.add(DeviceStatusEnum.DS6);
		tmpList.add(DeviceStatusEnum.DS7);
		return tmpList;
	}

	public String getJobType() {
		return jobType;
	}

	public List<DeviceType> getDeviceTypeList() {
		return deviceService.getDeviceTypeList();
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("device_barcode", device_barcode);
		map.put("device_series", device_series);
		map.put("seclv_code", seclv_code);
		map.put("med_type", med_type);
		map.put("device_status", device_status);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("scope", "DEPT");
		map.put("job_code_is_null", true);
		deviceList = ledgerService.getAllDeviceLedgerList(map);
		return SUCCESS;
	}
}
