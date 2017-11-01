package hdsec.web.project.passport.model;

public enum PassportTypeEnum {
	DS0(0, "普通护照"), DS1(1, "外交护照"), DS2(2, "公务护照");
	private Integer key;
	private String name = "";

	private PassportTypeEnum(Integer key, String name) {
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