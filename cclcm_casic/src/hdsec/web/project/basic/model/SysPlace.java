package hdsec.web.project.basic.model;

import hdsec.web.project.common.BaseDomain;

public class SysPlace extends BaseDomain {
	private String place_code;
	private String place_name;
	private String place_desc;
	
	public String getPlace_code() {
		return place_code;
	}
	
	public void setPlace_code(String place_code) {
		this.place_code = place_code;
	}
	
	public String getPlace_name() {
		return place_name;
	}
	
	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}
	
	public String getPlace_desc() {
		return place_desc;
	}
	
	public void setPlace_desc(String place_desc) {
		this.place_desc = place_desc;
	}
	
}
