package hdsec.web.project.device.action;

import hdsec.web.project.device.model.EntityDevice;
import hdsec.web.project.user.model.SecLevel;
import hdsec.web.project.user.model.SecUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 可分配的磁介质列表
 * 
 * @author lixiang
 * 
 */
public class DistributeDeviceAction extends DeviceBaseAction {
	private static final long serialVersionUID = 1L;
	private List<EntityDevice> deviceList = null;
	private String device_code = "";
	private String device_barcode = "";
	private String seclv_code = "";
	private Integer med_type;
	private String device_status = "";
	private String event_code = "";
	private String is_submit = "N";

	public String getIs_submit() {
		return is_submit;
	}

	public void setIs_submit(String is_submit) {
		this.is_submit = is_submit;
	}

	public String getDevice_code() {
		return device_code;
	}

	public void setDevice_code(String device_code) {
		this.device_code = device_code;
	}

	public String getDevice_barcode() {
		return device_barcode;
	}

	public void setDevice_barcode(String device_barcode) {
		this.device_barcode = device_barcode;
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

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	@Override
	public String executeFunction() throws Exception {
		if (is_submit.equalsIgnoreCase("Y")) {
			SecUser user = getCurUser();
			EntityDevice entityDevice = deviceService.getDeviceByCode(device_code);
			String device_barcode = "";
			if (entityDevice != null) {
				device_barcode = entityDevice.getDevice_barcode();
			}
			deviceService.confirmDeviceBR(event_code, device_code, user);
			insertCommonLog("借出磁介质[" + device_barcode + "]");
			return "ok";
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("seclv_code", seclv_code);
			map.put("med_type", med_type);
			map.put("device_status", "0");
			map.put("has_job_code", false);
			map.put("scope", "DEPT");
			deviceList = ledgerService.getAllDeviceLedgerList(map);
			return SUCCESS;
		}
	}
}
