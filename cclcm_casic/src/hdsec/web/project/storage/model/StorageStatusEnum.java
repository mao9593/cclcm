package hdsec.web.project.storage.model;

/**
 * 存储介质状态枚举类
 * 2014-4-22 上午12:04:35
 * 
 * @author renmingfei
 */
public enum StorageStatusEnum {
	SS0(0, "未分配"), SS1(1, "已分配"), SS2(2, "送修处理中"), SS3(3, "已报废"), SS4(4, "待交接");
	private Integer key;
	private String name = "";
	
	private StorageStatusEnum(Integer key, String name) {
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
