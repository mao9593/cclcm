package hdsec.web.project.device.action;

import hdsec.web.project.device.model.EntityDevice;
import hdsec.web.project.user.model.SecUser;

/**
 * 归还磁介质
 * 
 * @author lixiang
 * 
 */
public class ReturnDeviceAction extends DeviceBaseAction {
	private static final long serialVersionUID = 1L;
	private String device_code = "";
	private String result = "";

	public String getDevice_code() {
		return device_code;
	}

	public void setDevice_code(String device_code) {
		this.device_code = device_code;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String executeFunction() throws Exception {
		SecUser user = getCurUser();
		EntityDevice entityDevice = deviceService.getDeviceByCode(device_code);
		String device_barcode = "";
		if (entityDevice != null) {
			device_barcode = entityDevice.getDevice_barcode();
		}
		deviceService.confirmDeviceRT(device_code, user);
		insertCommonLog("归还磁介质[" + device_barcode + "]");
		setResult("归还已确认");
		return SUCCESS;
	}

}
