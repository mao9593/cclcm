package hdsec.web.project.device.action;

import hdsec.web.project.device.model.DeviceType;

/**
 * 删除磁介质类型
 * 
 * @author lixiang
 * 
 */
public class DelDeviceTypeAction extends DeviceBaseAction {
	private static final long serialVersionUID = 1L;
	private Integer id = null;
	private String typename = "";

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String executeFunction() throws Exception {
		DeviceType type = deviceService.getDeviceTypeByID(id);
		if (type == null) {
			throw new Exception("该类型不存在，或者已经被删除。");
		} else if (deviceService.isTypeInUse(id)) {
			typename = type.getTypename();
			throw new Exception("系统中有在用的磁介质为[" + typename + "]，无法删除。");
		} else {
			typename = type.getTypename();
			deviceService.delDeviceTypeByID(id);
			insertCommonLog("删除磁介质类型:" + typename);
		}
		return SUCCESS;
	}
}
