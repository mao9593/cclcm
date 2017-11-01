package hdsec.web.project.client.action;

import hdsec.web.project.client.service.ClientService;
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
import org.springframework.util.StringUtils;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 登录与网关集成
 * 
 * @author gaoximin 2014-9-24
 */
public class JitLoginAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private UserService userService;
	@Resource
	private ClientService clientService;

	private SecUser user = null;
	private String errorMsg = "";
	private String user_iidd = "";

	public String getErrorMsg() {
		return errorMsg;
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
		logger.info("enter putUserIntoSession");
		HttpSession session = getRequest().getSession(false);
		if (null == session) {
			session = getRequest().getSession();
		}
		if (session == null) {
			throw new Exception("No session exists!");
		}
		user = (SecUser) session.getAttribute(Constants.SESSION_USER_KEY);
		if (user != null && user.getUser_iidd().equals(user_iidd)) {
			logger.debug("user[" + user_iidd + "] already in session");
		} else {
			user = userService.getSecUserByUid(user_iidd);

			if (user == null) {
				throw new Exception("---User[" + user_iidd + "] does not exist!---");
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

	/**
	 * 从网关中获取用户名拼音（useriidd）或者身份证号，供接下来的认证使用
	 */
	@Override
	public String execute() {
		try {
			HttpServletRequest request = getRequest();
			if (request.getHeader("dnname") != null) {
				String DN = new String(request.getHeader("dnname").getBytes("ISO8859-1"), "GB2312");
				logger.info("DN is:" + DN);
				if (DN != null && !DN.equals("")) {
					int dc = DN.indexOf("DC=");
					if (dc != -1) {
						String username2end = DN.substring(dc + 3);
						int dot = username2end.indexOf(',');
						if (dot != -1) {
							user_iidd = username2end.substring(0, dot);
						} else {
							user_iidd = username2end;
						}
						System.out.println("JitLogin(user):" + user_iidd);
					}
					if (user_iidd.isEmpty()) {
						int m = DN.indexOf("T=");
						String identity = "";
						if (m >= 0) {
							String use1 = DN.substring(m + 2);
							int k1 = use1.indexOf(",");
							if (k1 >= 0) {
								identity = use1.substring(0, k1);
							} else {
								identity = use1;
							}
							System.out.println("JitLogin(identity):" + identity);
							SecUser secuser = clientService.getUserByIdentity(identity);
							if (secuser != null) {
								user_iidd = secuser.getUser_iidd();
							}
						}
					}
				}
			} else {
				logger.error("None 'dnname' param exists in request");
			}
			// user_iidd = "gaoxm";
			if (StringUtils.hasLength(user_iidd)) {
				try {
					putUserIntoSession(user_iidd);
				} catch (Exception e) {
					logger.error(e.getMessage());
					e.printStackTrace();
				}
				return SUCCESS;
			} else {
				errorMsg = "用户信息不存在！";
				return "exception";
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return "error";
		}
	}
}
