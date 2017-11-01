package hdsec.web.project.passport.model;

public enum PassportStatusEnum {
	DS0(0, "在册"), DS1(1, "借出"), DS2(2, "删除");
	private Integer key;
	private String name = "";

	private PassportStatusEnum(Integer key, String name) {
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