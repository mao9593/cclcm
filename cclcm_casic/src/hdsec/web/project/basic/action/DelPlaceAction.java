package hdsec.web.project.basic.action;

public class DelPlaceAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String place_code;
	
	@Override
	public String executeFunction() throws Exception {
		basicService.delPlace(place_code);
		insertAdminLog("删除地点");
		return SUCCESS;
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
	
}
