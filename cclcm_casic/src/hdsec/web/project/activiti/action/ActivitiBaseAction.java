package hdsec.web.project.activiti.action;

import hdsec.web.project.activiti.service.ActivitiService;
import hdsec.web.project.basic.service.BasicService;
import hdsec.web.project.common.action.BaseAction;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.log.model.AdminOperLog;
import hdsec.web.project.log.service.LogService;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.service.UserService;

import javax.annotation.Resource;

public abstract class ActivitiBaseAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	@Resource
	protected ActivitiService activitiService;
	@Resource
	protected UserService userService;
	@Resource
	protected LogService logService;
	@Resource
	protected BasicService basicService;
	
	protected SecUser curUser = getSecUserFromSession();
	
	protected AdminOperLog adminOperLog = null;
	
	@Override
	protected String getModuleName() {
		return "activiti";
	}
	
	protected void insertAdminLog(String detail) {
		adminOperLog = new AdminOperLog(curUser.getUser_iidd(), curUser.getUser_name(), curUser.getDept_name(), detail,
				"成功", new java.util.Date(), Constants.LOG_COMMON_OPERATION, getRequest().getRemoteAddr(), getRequest()
						.getRemoteHost(), Constants.LOG_SUBSYS_ADMIN_CODE);
		logService.addAdminOperLog(adminOperLog);
	}
}
