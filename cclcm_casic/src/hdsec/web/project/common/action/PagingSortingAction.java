package hdsec.web.project.common.action;

import hdsec.web.project.common.model.BasePagingSorting;
import hdsec.web.project.common.model.CsvDownloadable;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.TimeUtil;
import hdsec.web.project.user.model.ActionUser;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.service.UserService;
import ht304.hdsec.framework.common.action.FrameworkPagingSortingAction;
import ht304.hdsec.framework.common.model.PagingSorting;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.context.HttpServletRequestContext;
import org.extremecomponents.table.limit.LimitFactory;
import org.extremecomponents.table.limit.TableLimitFactory;

public abstract class PagingSortingAction extends FrameworkPagingSortingAction<ActionUser, UserService> {
	
	private static final long serialVersionUID = 1L;
	
	public static final int CHECK_PERMISSION_OK = 0;
	
	public static final int CHECK_PERMISSION_TIMEOUT = 1;
	
	public static final int CHECK_PERMISSION_NO = 2;
	
	public abstract int getTotalRows();
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	protected PagingSorting getPagingSorting() {
		HttpServletRequest request = getRequest();
		Context context = new HttpServletRequestContext(request);
		final LimitFactory limitFactory = new TableLimitFactory(context);
		
		BasePagingSorting ps = new BasePagingSorting();
		ps.setPageSize(limitFactory.getCurrentRowsDisplayed(0, 15));
		ps.setBeginIndex((limitFactory.getPage() - 1) * ps.getPageSize() + 1);
		ps.setDirection(limitFactory.getSort().getSortOrder());
		ps.setEndIndex(ps.getBeginIndex() + ps.getPageSize() - 1);
		ps.setSortingName(limitFactory.getSort().getProperty());
		
		setObjectsPerPage(limitFactory.getCurrentRowsDisplayed(0, 15));
		setPage(limitFactory.getPage());
		return ps;
	}
	
	protected void downloadAsCsv(String fileName, HttpServletResponse response, String header,
			List<? extends CsvDownloadable> list) {
		response.setContentType("application/csv");
		fileName += (String.valueOf(TimeUtil.getCurrentTimestamp()).replace(':', '-').substring(0, 19));
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".csv");
		try {
			OutputStream os = response.getOutputStream();
			os.write(header.getBytes("unicode"));
			os.write("\t\r\n".getBytes("unicode"));
			for (CsvDownloadable object : list) {
				os.write(object.toCsvFormat().getBytes("unicode"));
				os.write("\t\r\n".getBytes("unicode"));
			}
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public ActionUser getActionUser() {
		HttpServletRequest request = getRequest();
		HttpSession s = request.getSession();
		return (ActionUser) s.getAttribute("actionUser");
	}
	
	protected SecUser getSecUserFromSession() {
		HttpServletRequest request = getRequest();
		HttpSession s = request.getSession();
		return (SecUser) s.getAttribute(Constants.SESSION_USER_KEY);
	}
	
	/*protected User getUserFromSession() {
		HttpServletRequest request = getRequest();
		HttpSession s = request.getSession();
		return (User) s.getAttribute(Constants.SESSION_USER_KEY);
	}*/
	
	protected HttpSession getSession() {
		return getRequest().getSession();
	}
	
	@Override
	public String execute() throws Exception {
		if (checkPermission() == CHECK_PERMISSION_OK) {
			logger.debug("----enter " + this.getClass().getSimpleName() + "----");
			try {
				return executeFunction();
			} catch (Exception e) {
				dealException(e);
				return "exception";
			}
		} else if (checkPermission() == CHECK_PERMISSION_TIMEOUT) {
			logger.debug("session timeout!");
			return ERROR;
		} else {
			//没有权限
			return ERROR;
		}
	}
	
	public abstract String executeFunction() throws Exception;
	
	public int checkPermission() {
		//ActionUser actionUser = getActionUser();
		//User user = getUserFromSession();
		SecUser secUser = getSecUserFromSession();
		if (secUser == null) {
			return CHECK_PERMISSION_TIMEOUT;
		}
		return CHECK_PERMISSION_OK;
		//boolean hasPermission = actionUser.hasPermission(this.getClass().getSimpleName());
		//return hasPermission ? CHECK_PERMISSION_OK : CHECK_PERMISSION_NO;
	}
	
	public boolean checkPermission(String permission) {
		ActionUser actionUser = getActionUser();
		if (actionUser == null) {
			return false;
		}
		return actionUser.hasPermission(permission);
	}
	
	public String getTitleName() {
		return this.getClass().getSimpleName();
	}
	
	protected String dealException(Exception e) {
		//TODO:
		e.printStackTrace();
		return e.getMessage();
	}
	
}
