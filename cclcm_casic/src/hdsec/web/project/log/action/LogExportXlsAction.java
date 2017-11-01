package hdsec.web.project.log.action;

import hdsec.web.project.common.action.ExportXlsBaseAction;
import hdsec.web.project.log.service.LogService;

import javax.annotation.Resource;

public abstract class LogExportXlsAction extends ExportXlsBaseAction {
	private static final long serialVersionUID = 1L;
	@Resource
	protected LogService logService;
	
	@Override
	protected String getModuleName() {
		return "log";
	}
	
}
