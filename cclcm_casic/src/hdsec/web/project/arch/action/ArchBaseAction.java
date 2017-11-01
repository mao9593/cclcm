package hdsec.web.project.arch.action;

import hdsec.web.project.activiti.service.ActivitiService;
import hdsec.web.project.arch.service.ArchService;
import hdsec.web.project.basic.service.BasicPrcManage;
import hdsec.web.project.basic.service.BasicService;
import hdsec.web.project.common.action.BaseAction;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.log.model.AdminOperLog;
import hdsec.web.project.log.model.CommonOperLog;
import hdsec.web.project.log.service.LogService;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.service.UserService;

import java.util.Date;

import javax.annotation.Resource;

/**
 * @author handouwang
 * 
 *         2015-7-26/
 */
public abstract class ArchBaseAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Resource
	protected ArchService archService;
	@Resource
	protected BasicService basicService;
	@Resource
	protected LogService logService;
	@Resource
	protected ActivitiService activitiService;
	@Resource
	protected BasicPrcManage basicPrcManage;
	@Resource
	protected UserService userService;

	protected AdminOperLog adminOperLog = null;
	protected CommonOperLog commonOperLog = null;
	protected SecUser curUser = getSecUserFromSession();

	public SecUser getCurUser() {
		return curUser;
	}

	public void insertCommonLog(String detail) {
		commonOperLog = new CommonOperLog(curUser.getUser_iidd(),
				curUser.getUser_name(), curUser.getDept_name(), detail, "成功",
				new Date(), Constants.LOG_OPERATION, getRequest()
						.getRemoteAddr(), getRequest().getRemoteHost(), "admin");
		logService.addCommonOperLog(commonOperLog);
	}

	@Override
	protected String getModuleName() {
		return "arch";
	}

}