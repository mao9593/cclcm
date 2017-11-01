package hdsec.web.project.arch.action;

import hdsec.web.project.arch.model.ArchDict;

import java.util.List;

/**
 * 字典管理
 * 
 * @author handouwang
 * 
 *         2015-7-26/
 */
public class ManageDictAction extends ArchBaseAction {
	private static final long serialVersionUID = 1L;
	private List<ArchDict> dictList = null;

	public List<ArchDict> getDictList() {
		return dictList;
	}

	@Override
	public String executeFunction() throws Exception {
		dictList = archService.getAllArchDictList();
		return SUCCESS;
	}

}
