package hdsec.web.project.device.action;

import hdsec.web.project.activiti.service.ActivitiService;
import hdsec.web.project.basic.service.BasicPrcManage;
import hdsec.web.project.basic.service.BasicService;
import hdsec.web.project.common.action.BaseAction;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.device.service.DeviceService;
import hdsec.web.project.ledger.service.LedgerService;
import hdsec.web.project.log.model.AdminOperLog;
import hdsec.web.project.log.model.CommonOperLog;
import hdsec.web.project.log.service.LogService;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.service.UserService;

import java.util.Date;

import javax.annotation.Resource;

public abstract class DeviceBaseAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Resource
	protected UserService userService;
	@Resource
	protected BasicService basicService;
	@Resource
	protected LogService logService;
	@Resource
	protected LedgerService ledgerService;
	@Resource
	protected DeviceService deviceService;
	@Resource
	protected ActivitiService activitiService;
	@Resource
	protected BasicPrcManage basicPrcManage;
	protected AdminOperLog adminOperLog = null;
	protected CommonOperLog commonOperLog = null;
	protected SecUser curUser = getSecUserFromSession();
	
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
		return "device";
	}
	
}
