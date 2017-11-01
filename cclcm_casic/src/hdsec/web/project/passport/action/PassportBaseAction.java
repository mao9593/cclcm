package hdsec.web.project.passport.action;

import java.util.Date;

import javax.annotation.Resource;
import java.lang.String;
import hdsec.web.project.activiti.service.ActivitiService;
import hdsec.web.project.basic.service.BasicPrcManage;
import hdsec.web.project.basic.service.BasicService;
import hdsec.web.project.common.action.BaseAction;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.passport.service.PassportService;
import hdsec.web.project.log.model.AdminOperLog;
import hdsec.web.project.log.model.CommonOperLog;
import hdsec.web.project.log.service.LogService;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.service.UserService;

public abstract class PassportBaseAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;

	@Resource
	protected UserService userService;
	@Resource
	protected BasicService basicService;
	@Resource
	protected LogService logService;
	@Resource
	protected PassportService passportService;
	@Resource
	protected ActivitiService activitiService;
	@Resource
	protected BasicPrcManage basicPrcManage;

	protected SecUser curUser = getSecUserFromSession();
	protected AdminOperLog adminOperLog = null;
	protected CommonOperLog commonOperLog = null;
	
	public SecUser getCurUser() {
		return curUser;
	}

	public void insertAdminLog(String detail) {
		adminOperLog = new AdminOperLog(curUser.getUser_iidd(), curUser.getUser_name(), curUser.getDept_name(), detail,
				"成功", new Date(), Constants.LOG_OPERATION, getRequest().getRemoteAddr(), getRequest().getRemoteHost(),
				"admin");
		logService.addAdminOperLog(adminOperLog);
	}

	public void insertCommonLog(String detail) {
		commonOperLog = new CommonOperLog(curUser.getUser_iidd(), curUser.getUser_name(), curUser.getDept_name(),
				detail, "成功", new Date(), Constants.LOG_OPERATION, getRequest().getRemoteAddr(), getRequest()
						.getRemoteHost(), "admin");
		logService.addCommonOperLog(commonOperLog);
	}

	@Override
	protected String getModuleName() {
		return "passport";

	}
}