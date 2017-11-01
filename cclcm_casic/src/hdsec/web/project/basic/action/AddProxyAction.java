package hdsec.web.project.basic.action;

import hdsec.web.project.user.model.ModuleEnum;

/**
 * 添加代理
 * 
 * @author yueying
 * 
 */
public class AddProxyAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	
	@Override
	public String executeFunction() throws Exception {
		return SUCCESS;
	}
	
	public String getUser_name() {
		return getCurUser().getUser_name();
	}
	
	public boolean getIsPrintEnable() {
		return ModuleEnum.PRINT.isModuleEnable();
	}
	
	public boolean getIsBurnEnable() {
		return ModuleEnum.BURN.isModuleEnable();
	}
}
