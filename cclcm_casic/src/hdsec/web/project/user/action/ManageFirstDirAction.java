package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecOperation;

import java.util.List;

public class ManageFirstDirAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private List<SecOperation> secOperList = null;

	public List<SecOperation> getSecOperList() {
		return secOperList;
	}

	@Override
	public String executeFunction() throws Exception {
		// TODO Auto-generated method stub
		secOperList = userService.getAllMenuOper();
		return SUCCESS;
	}

}
