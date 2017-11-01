package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecDeptLevel;

import java.util.List;

public class ViewDeptLevelAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private List<SecDeptLevel> deptLevelList = null;
	
	public List<SecDeptLevel> getDeptLevelList() {
		return deptLevelList;
	}
	
	@Override
	public String executeFunction() throws Exception {
		deptLevelList = userService.getDeptLevel();
		return SUCCESS;
	}
	
}
