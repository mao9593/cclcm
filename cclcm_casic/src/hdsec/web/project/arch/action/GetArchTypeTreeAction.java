package hdsec.web.project.arch.action;

import hdsec.web.project.arch.model.ArchTypeName;

import java.util.List;

/**
 * 档案类别树（含子项目号/案卷）
 * 
 * @author handouwang
 * 
 *         2015-7-28/
 */
public class GetArchTypeTreeAction extends ArchBaseAction {
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
