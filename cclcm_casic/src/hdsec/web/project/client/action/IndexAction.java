package hdsec.web.project.client.action;

import hdsec.web.project.common.util.Constants;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.service.UserService;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 登录成功后，自动跳转的action，查询用户权限，返回导航页
 * 
 * @author renmingfei
 * 
 */
public class IndexAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private UserService userService;
	private SecUser user = null;
	private String errorMsg = "";
	private String user_iidd = "";

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public SecUser getUser() {
		return user;
	}

	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 把当前客户端的用户信息存入session 如果session中存在用户，并且与当前用户相同，则不做处理，否则把session中的用户信息更新为当前用户 2014-4-14 下午4:03:51
	 * 
	 * @author renmingfei
	 * @param user_iidd
	 * @throws Exception
	 */
	private void putUserIntoSession(String user_iidd) throws Exception {
		HttpSession session = getRequest().getSession(true);
		user = (SecUser) session.getAttribute(Constants.SESSION_USER_KEY);
		if (user != null && user.getUser_iidd().equals(user_iidd)) {
			logger.debug("user[" + user_iidd + "] already in session");
		} else {
			user = userService.getSecUserByUid(user_iidd);
			if (user == null) {
				throw new Exception("---User[" + user_iidd + "] does not exist!---");
			} else if (user.getIs_sealed().equalsIgnoreCase("Y")) {
				throw new Exception("---此用户已被删除!---");
			} else {
				user.setNeed_checkPwd(false);
				// 把用户信息写进session
				session.setAttribute(Constants.SESSION_USER_KEY, user);
				// 把登录时间写进session
				session.setAttribute(Constants.LOGON_TIME, new Date());
				// 把登录IP写进session
				session.setAttribute(Constants.LOGON_IP, getRequest().getRemoteAddr());
				// 把用户的所有操作列表写进user
				List<String> allOperUrl = userService.getAllOperByUserOnly(user.getUser_iidd());
				user.setAllOper(allOperUrl);
				// 把用户没有的操作列表写进user
				List<String> nonOperUrl = userService.getNonOperByUserOnly(user.getUser_iidd());
				user.setNonOper(nonOperUrl);
			}
		}
	}

	@Override
	public String execute() {
		try {
			putUserIntoSession(user_iidd);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			return "noOper";
		}
		return SUCCESS;
	}

}
