package hdsec.web.project.common.action;

import hdsec.web.project.common.util.Constants;
import hdsec.web.project.user.model.ActionUser;
import hdsec.web.project.user.model.ModuleEnum;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.service.UserService;
import ht304.hdsec.framework.common.action.FrameworkAction;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.mybatis.spring.MyBatisSystemException;

public abstract class BaseAction extends FrameworkAction<ActionUser, UserService> {

	private static final long serialVersionUID = 1L;
	protected static final int CHECK_PERMISSION_OK = 0;
	protected static final int CHECK_PERMISSION_TIMEOUT = 1;
	protected static final int CHECK_PERMISSION_NO = 2;
	protected Logger logger = Logger.getLogger(this.getClass());
	protected int pageSize = 15;
	protected int page = 0;
	protected int beginIndex = 1;
	protected int totalSize = 0;
	protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	protected String errorMsg = "";
	private String topPrompt = "";
	private String topHref = "";

	public String getTopPrompt() {
		return topPrompt;
	}

	public void setTopPrompt(String topPrompt) {
		this.topPrompt = topPrompt;
	}

	public String getTopHref() {
		return topHref;
	}

	public void setTopHref(String topHref) {
		this.topHref = topHref;
	}

	public String getTopPromptCancel() {
		return (String) getRequest().getSession().getAttribute("topPromptCancel");
	}

	public void setTopPromptCancel(String topPromptCancel) {
		getRequest().getSession().setAttribute("topPromptCancel", topPromptCancel);
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
	public String execute() {
		HttpServletResponse response = getResponse();
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);
		if (checkPermission() == CHECK_PERMISSION_OK) {
			logger.debug("----enter " + this.getClass().getSimpleName() + "----");
			try {
				return executeFunction();
			} catch (MyBatisSystemException mse) {
				logger.error("数据库查询出现异常:" + mse.getMessage());
				setErrorMsg("数据库查询出现异常");
				return "exception";
			} catch (Exception e) {
				dealException(e);
				return "exception";
			}
		} else if (checkPermission() == CHECK_PERMISSION_TIMEOUT) {
			logger.debug("session timeout!");
			return ERROR;
		} else {
			dealException(new Exception("没有权限！"));
			return "exception";
		}
	}

	protected SecUser getSecUserFromSession() {
		HttpServletRequest request = getRequest();
		HttpSession s = request.getSession();
		return (SecUser) s.getAttribute(Constants.SESSION_USER_KEY);
	}

	/*
	 * protected User getUserFromSession() { HttpServletRequest request = getRequest(); HttpSession s =
	 * request.getSession(); return (User) s.getAttribute(Constants.SESSION_USER_KEY); }
	 */

	protected HttpSession getSession() {
		return getRequest().getSession(false);
	}

	public abstract String executeFunction() throws Exception;

	protected int checkPermission() {
		// User user = getUserFromSession();
		SecUser secUser = getSecUserFromSession();
		if (secUser == null) {
			return CHECK_PERMISSION_TIMEOUT;
		} else if (checkPermission(getTitleName())) {
			return CHECK_PERMISSION_OK;
		} else {
			return CHECK_PERMISSION_NO;
		}
	}

	protected boolean checkPermission(String className) {
		SecUser secUser = getSecUserFromSession();
		String permission = getModuleName().toLowerCase() + "/" + className.toLowerCase() + ".action";
		return secUser.hasPermission(permission);
	}

	protected String getTitleName() {
		String className = this.getClass().getSimpleName();
		className = className.substring(0, className.lastIndexOf("Action"));
		return className;
	}

	protected void dealException(Exception e) {
		logger.error("--------------exception thrown---------------");
		logger.error(e.getMessage());
		setErrorMsg(e.getMessage());
		// e.printStackTrace();
	}

	protected abstract String getModuleName();

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public boolean getIsPrintEnable() {
		return ModuleEnum.PRINT.isModuleEnable();
	}

	public boolean getIsBurnEnable() {
		return ModuleEnum.BURN.isModuleEnable();
	}

	public boolean getIsCopyEnable() {
		return ModuleEnum.COPY.isModuleEnable();
	}

	public boolean getIsLeadinEnable() {
		return ModuleEnum.LEADIN.isModuleEnable();
	}

	public boolean getIsDeviceEnable() {
		return ModuleEnum.DEVICE.isModuleEnable();
	}

	public boolean getIsConfirmEnable() {
		return ModuleEnum.CONFIRM.isModuleEnable();
	}

	public boolean getIsTransferEnable() {
		return ModuleEnum.TRANSFER.isModuleEnable();
	}

	public boolean getIsBorrowEnable() {
		return ModuleEnum.BORROW.isModuleEnable();
	}

	public boolean getIsDelayEnable() {
		return ModuleEnum.DELAY.isModuleEnable();
	}

	public boolean getIsStorageEnable() {
		return ModuleEnum.STORAGE.isModuleEnable();
	}

	public boolean getIsChangeEnable() {
		return ModuleEnum.CHANGE.isModuleEnable();
	}

	public boolean getIsInputEnable() {
		return ModuleEnum.INPUT.isModuleEnable();
	}
}
