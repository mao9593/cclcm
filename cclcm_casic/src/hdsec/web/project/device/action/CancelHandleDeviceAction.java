package hdsec.web.project.device.action;

import hdsec.web.project.device.model.EntityDevice;

import org.springframework.util.StringUtils;

/**
 * 取消磁介质处理申请
 * 
 * @author lixiang
 * 
 */
public class CancelHandleDeviceAction extends DeviceBaseAction {
	private static final long serialVersionUID = 1L;
	private String device_code = "";
	private String type = "";

	public void setType(String type) {
		this.type = type;
	}

	public String getDevice_code() {
		return device_code;
	}

	public void setDevice_code(String device_code) {
		this.device_code = device_code;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(device_code)) {
			deviceService.cancelHandleDeviceByCode(device_code);
		}
		EntityDevice entityDevice = deviceService.getDeviceByCode(device_code);
		String device_barcode = "";
		if (entityDevice != null) {
			device_barcode = entityDevice.getDevice_barcode();
		}
		insertCommonLog("取消磁介质处理申请[" + device_barcode + "]");
		return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
	}
}
