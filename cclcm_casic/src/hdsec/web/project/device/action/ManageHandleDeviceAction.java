package hdsec.web.project.device.action;

import hdsec.web.project.device.model.EntityDevice;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理磁介质销毁申请列表
 * 
 * @author lixiang
 * 
 */
public class ManageHandleDeviceAction extends DeviceBaseAction {

	private static final long serialVersionUID = 1L;
	private List<EntityDevice> deviceList = null;
	private String device_barcode = "";
	private Integer seclv_code = null;
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";

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

	@Override
	public String executeFunction() throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("device_barcode", device_barcode);
		map.put("seclv_code", seclv_code);
		map.put("job_status", job_status);
		map.put("start_time", startTime);
		map.put("end_time", endTime);
		map.put("has_job_code", true);
		map.put("is_all", true);
		map.put("scope", "DEPT");
		deviceList = ledgerService.getAllDeviceLedgerList(map);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "DESTROY_DEVICE", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "DESTROY_DEVICE", 3);
		return SUCCESS;
	}

}
