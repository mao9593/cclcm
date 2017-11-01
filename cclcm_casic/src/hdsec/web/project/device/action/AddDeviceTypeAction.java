package hdsec.web.project.device.action;

import hdsec.web.project.device.model.DeviceType;

/**
 * 添加磁介质类型
 * 
 * @author lixiang
 * 
 */
public class AddDeviceTypeAction extends DeviceBaseAction {
	private static final long serialVersionUID = 1L;
	private Integer id = null;
	private String typename = "";
	private String content = "";
	private String add = "N";

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	@Override
	public String executeFunction() throws Exception {
		if (add.equalsIgnoreCase("Y")) {
			String str = "" + System.currentTimeMillis();
			id = Integer.parseInt(str.substring(str.length() - 4));

			if (deviceService.isTypeExistByName(typename)) {
				throw new Exception("类型名称已经存在，不能重复添加。");
			} else
				while (deviceService.isTypeExistByID(id)) {
					str = "" + System.currentTimeMillis();
					id = Integer.parseInt(str.substring(str.length() - 4));
				}
			DeviceType type = new DeviceType();
			type.setId(id);
			type.setTypename(typename);
			type.setContent(content);
			deviceService.addDeviceType(type);
			insertCommonLog("添加磁介质类型:" + typename);
			return "insert";
		} else
			return SUCCESS;
	}
}
