package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysPlace;

import java.util.List;

/**
 *添加代理
 * @author yueying
 *
 */
public class ViewPlaceAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private List<SysPlace> places;
	
	@Override
	public String executeFunction() throws Exception {
		places = basicService.getAllPlaces();
		return SUCCESS;
	}
	
	public String getUser_name() {
		return getCurUser().getUser_name();
	}
	
	public List<SysPlace> getPlaces() {
		return places;
	}
	
	public void setPlaces(List<SysPlace> places) {
		this.places = places;
	}
	
}
