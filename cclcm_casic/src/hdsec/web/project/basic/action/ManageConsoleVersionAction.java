package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysConsole;

import java.util.List;

/**
 * 控制台版本管理列表
 * 
 * @author yy
 * 
 */
public class ManageConsoleVersionAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private List<SysConsole> consoleList = null;
	
	public List<SysConsole> getConsoleList() {
		return consoleList;
	}
	
	@Override
	public String executeFunction() throws Exception {
		consoleList = basicService.getSysConsoleList();
		return SUCCESS;
	}
}
