package hdsec.web.project.arch.action;

import hdsec.web.project.arch.model.ArchTypeName;

import java.util.List;

public class GetTreeAction extends ArchBaseAction {
	private static final long serialVersionUID = 1L;
	private List<ArchTypeName> typeList = null;

	public List<ArchTypeName> getTypeList() {
		return typeList;
	}

	@Override
	public String executeFunction() throws Exception {
		typeList = archService.getArchTypeTree();
		return SUCCESS;
	}
}