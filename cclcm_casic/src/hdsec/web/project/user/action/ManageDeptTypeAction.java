package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecDeptType;

import java.util.List;

public class ManageDeptTypeAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private List<SecDeptType> deptTypeList = null;
	
	public List<SecDeptType> getDeptTypeList() {
		return deptTypeList;
	}
	
	@Override
	public String executeFunction() throws Exception {
		deptTypeList = userService.getAllDeptType();
		return SUCCESS;
	}
	
}
