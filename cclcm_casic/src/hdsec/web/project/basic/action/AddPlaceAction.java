package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysPlace;

import java.util.HashMap;
import java.util.Map;

public class AddPlaceAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String place_code;
	private String place_name;
	private String place_desc;
	private SysPlace place;
	
	@Override
	public String executeFunction() throws Exception {
		if (null == place_code || "".equals(place_code))
			return SUCCESS;
		else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("place_code", place_code);
			map.put("place_name", place_name);
			map.put("place_desc", place_desc);
			basicService.addPlace(map);
			insertAdminLog("添加地点");
			return "ok";
		}
	}
	
	public String getUser_name() {
		return getCurUser().getUser_name();
	}
	
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
	
	public SysPlace getPlace() {
		return place;
	}
	
	public void setPlace(SysPlace place) {
		this.place = place;
	}
	
}
