package hdsec.web.project.device.model;

/**
 * 磁介质状态枚举
 * 
 * @author lixiang
 */
public enum DeviceStatusEnum {
	DS0(0, "在档可借"), DS7(7, "待借出"), DS1(1, "已借出"), DS2(2, "待归还"), DS3(3, "检查中"), DS4(4, "超期未还"), DS6(6, "已送修"), DS5(5,
			"已销毁"), DS8(8, "申请销毁"), DS9(9, "留用");
	private Integer key;
	private String name = "";

	private DeviceStatusEnum(Integer key, String name) {
		this.key = key;
		this.name = name;
	}

	public Integer getKey() {
		return key;
	}

	public String getName() {
		return name;
	}
}
