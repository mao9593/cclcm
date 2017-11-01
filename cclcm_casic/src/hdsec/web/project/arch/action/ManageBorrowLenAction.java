package hdsec.web.project.arch.action;

import hdsec.web.project.arch.model.BorrowLen;

import java.util.List;

/**
 * 配置借用时间
 * 
 * @author handouwang
 * 
 *         2015-7-26/
 */
public class ManageBorrowLenAction extends ArchBaseAction {
	private static final long serialVersionUID = 1L;
	private List<BorrowLen> lenList = null;

	public List<BorrowLen> getLenList() {
		return lenList;
	}

	@Override
	public String executeFunction() throws Exception {
		lenList = archService.getUsedSeclvList();
		return SUCCESS;
	}

}
