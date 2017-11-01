package hdsec.web.project.basic.action;

import hdsec.web.project.activiti.service.ActivitiService;
import hdsec.web.project.basic.service.BasicPrcManage;
import hdsec.web.project.basic.service.BasicService;
import hdsec.web.project.borrow.service.BorrowService;
import hdsec.web.project.burn.service.BurnService;
import hdsec.web.project.client.service.ClientService;
import hdsec.web.project.common.action.BaseAction;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.enter.service.EnterService;
import hdsec.web.project.ledger.service.LedgerService;
import hdsec.web.project.log.model.AdminOperLog;
import hdsec.web.project.log.model.CommonOperLog;
import hdsec.web.project.log.service.LogService;
import hdsec.web.project.print.service.PrintService;
import hdsec.web.project.transfer.service.TransferService;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.service.UserService;

import java.util.Date;

import javax.annotation.Resource;

public abstract class BasicBaseAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Resource
	protected UserService userService;
	@Resource
	protected LogService logService;
	@Resource
	protected BasicService basicService;
	@Resource
	protected LedgerService ledgerService;
	@Resource
	protected ActivitiService activitiService;
	@Resource
	protected BurnService burnService;
	@Resource
	protected TransferService transferService;
	@Resource
	protected BorrowService borrowService;
	@Resource
	protected BasicPrcManage basicPrcManage;
	@Resource
	protected PrintService printService;
	@Resource
	protected ClientService clientService;
	@Resource
	protected EnterService enterService;
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
		return "basic";
	}

}
