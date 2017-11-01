package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecTerritory;

import java.util.List;

public class ManageTerrAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private List<SecTerritory> terrList = null;
	
	public List<SecTerritory> getTerrList() {
		return terrList;
	}
	
	@Override
	public String executeFunction() throws Exception {
		terrList = userService.getAllSecTerritory();
		return SUCCESS;
	}
}
