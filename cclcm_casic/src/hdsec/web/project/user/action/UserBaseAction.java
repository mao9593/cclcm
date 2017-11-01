package hdsec.web.project.user.action;

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
 * user模块action的父类
 * @author renmingfei
 *
 */
public abstract class UserBaseAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Resource
	protected UserService userService;
	@Resource
	protected LogService logService;
	@Resource
	protected BasicService basicService;
	protected AdminOperLog adminOperLog = null;
	protected CommonOperLog commonOperLog = null;
	protected SecUser curUser = getSecUserFromSession();
	
	public SecUser getCurrentUser() {
		return curUser;
	}
	
	public void insertAdminLog(String detail) {
		String user_iidd = curUser.getUser_iidd();
		if (!user_iidd.equals("admin")) {
			adminOperLog = new AdminOperLog(user_iidd, curUser.getUser_name(), curUser.getDept_name(), detail, "成功",
					new Date(), Constants.LOG_OPERATION, getRequest().getRemoteAddr(), getRequest().getRemoteHost(),
					"admin");
			logService.addAdminOperLog(adminOperLog);
		}
	}
	
	public void insertCommonLog(String detail) {
		String user_iidd = curUser.getUser_iidd();
		if (!user_iidd.equals("admin")) {
			commonOperLog = new CommonOperLog(user_iidd, curUser.getUser_name(), curUser.getDept_name(), detail, "成功",
					new Date(), Constants.LOG_OPERATION, getRequest().getRemoteAddr(), getRequest().getRemoteHost(),
					"admin");
			logService.addCommonOperLog(commonOperLog);
		}
	}
	
	@Override
	protected String getModuleName() {
		return "user";
	}
}
