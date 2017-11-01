package hdsec.web.project.client.action;

import hdsec.web.project.client.model.SysModule;

import java.util.List;

/**
 * 功能模块列表
 * @author gaoxm
 *
 */
public class ViewModuleAction extends ClientBaseAction {
	private static final long serialVersionUID = 1L;
	private List<SysModule> moduleList = null;
	
	public List<SysModule> getModuleList() {
		return moduleList;
	}
	
	@Override
	public String executeFunction() throws Exception {
		moduleList = clientService.getSysModuleList();
		return SUCCESS;
	}
}
