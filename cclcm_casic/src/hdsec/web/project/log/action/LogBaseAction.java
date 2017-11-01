package hdsec.web.project.log.action;

import hdsec.web.project.common.action.BaseAction;
import hdsec.web.project.log.service.LogService;

import javax.annotation.Resource;

public abstract class LogBaseAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	@Resource
	protected LogService logService;
	
	@Override
	protected String getModuleName() {
		return "log";
	}
	
}
