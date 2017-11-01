package hdsec.web.project.computer.model;

public enum ComputerStatusEnum {
	DS0(0, "在用"), DS1(1, "维修"), DS2(2, "待报废"), DS3(3, "已报废"), DS4(4, "停用"), DS5(5, "已回收"), DS6(6, "已销毁");
	private Integer key;
	private String name = "";

	private ComputerStatusEnum(Integer key, String name) {
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