package hdsec.web.project.basic.model;

import hdsec.web.project.common.BaseDomain;

/**
 * 智能交换柜
 * @author gaoxm
 *
 */

public class SysExchangeBox extends BaseDomain {
	private String box_code = "";//柜子ID
	private String box_name = "";//柜子名称
	private String box_location = "";//所在地点
	private Integer seclv_code = null;//密级
	private Integer box_status = null;//回收柜状态（0空、1已存、2损坏、3打开）
	
	private String seclv_name = "";
	
	//private String box_status_name = "";//回收柜状态（0空、1已存、2损坏、3打开）
	
	public String getBox_code() {
		return box_code;
	}
	
	public void setBox_code(String box_code) {
		this.box_code = box_code;
	}
	
	public String getBox_name() {
		return box_name;
	}
	
	public void setBox_name(String box_name) {
		this.box_name = box_name;
	}
	
	public String getBox_location() {
		return box_location;
	}
	
	public void setBox_location(String box_location) {
		this.box_location = box_location;
	}
	
	public Integer getSeclv_code() {
		return seclv_code;
	}
	
	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}
	
	public Integer getBox_status() {
		return box_status;
	}
	
	public String getBox_status_name() {
		String name = "";
		switch (this.box_status) {
			case 0:
				name = "空";
				break;
			case 1:
				name = "已存";
				break;
			case 2:
				name = "损坏";
				break;
			case 3:
				name = "打开";
				break;
		}
		return name;
	}
	
	public void setBox_status(Integer box_status) {
		this.box_status = box_status;
	}
	
	public String getSeclv_name() {
		return seclv_name;
	}
	
	public void setSeclv_name(String seclv_name) {
		this.seclv_name = seclv_name;
	}
	
	public SysExchangeBox() {
		super();
	}
	
	public SysExchangeBox(String box_code, String box_name, String box_location, Integer seclv_code, Integer box_status) {
		this.box_code = box_code;
		this.box_name = box_name;
		this.box_location = box_location;
		this.seclv_code = seclv_code;
		this.box_status = box_status;
	}
	
}
