package hdsec.web.project.device.action;

import hdsec.web.project.device.model.DeviceType;

import java.util.List;

/**
 * 磁介质类型管理
 * 
 * @author lixiang 2014-11-17
 */
public class ManageDeviceTypeAction extends DeviceBaseAction {
	private static final long serialVersionUID = 1L;
	private List<DeviceType> typeList = null;

	public List<DeviceType> getTypeList() {
		return typeList;
	}

	@Override
	public String executeFunction() throws Exception {
		typeList = deviceService.getDeviceTypeList();
		return SUCCESS;
	}
}
