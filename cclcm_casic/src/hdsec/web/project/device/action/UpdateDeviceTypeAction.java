package hdsec.web.project.device.action;

import hdsec.web.project.device.model.DeviceType;

/**
 * 修改磁介质类型
 * 
 * @author lixiang
 * 
 */
public class UpdateDeviceTypeAction extends DeviceBaseAction {
	private static final long serialVersionUID = 1L;
	private DeviceType type = null;
	private Integer id = null;
	private String typename = "";
	private String content = "";
	private String update = "N";

	public DeviceType getType() {
		return type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("Y")) {
			if (deviceService.isTypeExistByName(typename) && !id.equals(deviceService.getTypeIDByName(typename))) {
				throw new Exception("类型名称已经存在，不能重复添加。");
			}
			type = new DeviceType();
			type.setId(id);
			type.setTypename(typename);
			type.setContent(content);
			deviceService.updateDeviceType(type);
			insertCommonLog("修改磁介质类型:" + typename);
			return "update";
		} else {
			type = deviceService.getDeviceTypeByID(id);
		}
		return SUCCESS;
	}
}
