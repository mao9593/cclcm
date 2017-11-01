package hdsec.web.project.basic.model;

import hdsec.web.project.common.BaseDomain;

/**
 * 智能回收柜
 * @author gaoxm
 *
 */

public class SysRecycleBox extends BaseDomain {
	private String box_code = "";//柜子ID
	private String box_name = "";//柜子名称
	private String box_location = "";//所在地点
	private Integer seclv_code = null;//密级
	private Integer box_type = null;//回收柜类型（0纸质、1光盘）
	private Integer current_num = 0;//当前介质数量
	private Integer box_status = null;//回收柜状态（0空、1已存、2损坏、3打开）
	
	private String seclv_name = "";
	
	//private String box_type_name = "";//回收柜类型（0纸质、1光盘）
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
	
	public Integer getBox_type() {
		return box_type;
	}
	
	public void setBox_type(Integer box_type) {
		this.box_type = box_type;
	}
	
	public String getBox_type_name() {
		String name = "";
		switch (this.box_type) {
			case 0:
				name = "纸质";
				break;
			case 1:
				name = "光盘";
				break;
		}
		return name;
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
	
	public Integer getCurrent_num() {
		return current_num;
	}
	
	public void setCurrent_num(Integer current_num) {
		this.current_num = current_num;
	}
	
	public SysRecycleBox() {
		super();
	}
	
	public SysRecycleBox(String box_code, String box_name, String box_location, Integer seclv_code, Integer box_type,
			Integer current_num, Integer box_status) {
		this.box_code = box_code;
		this.box_name = box_name;
		this.box_location = box_location;
		this.seclv_code = seclv_code;
		this.box_type = box_type;
		this.box_status = box_status;
	}
	
}
