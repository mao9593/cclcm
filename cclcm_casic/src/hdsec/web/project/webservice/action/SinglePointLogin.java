package hdsec.web.project.webservice.action;

import hdsec.web.project.basic.service.BasicService;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.service.UserService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.codehaus.xfire.client.Client;
import org.springframework.util.StringUtils;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 3部单点登录集成
 * 
 * @author xingguiyang 2014-11-17
 */

public class SinglePointLogin extends ActionSupport {
	@Resource
	private BasicService basicService;
	@Resource
	private UserService userService;
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(this.getClass());
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
	 * 获取三部发送的Token参数，通过调用接口函数，来获取用户编码，通过用户编码获得本系统数据库中对应的user_iidd
	 */
	@Override
	public String execute() throws Exception {

		try {
			logger.debug("start into single point login");
			HttpServletRequest request = getRequest();
			if (request.getParameter("Token") != null && request.getParameter("Token") != "") {
				String url = "http://10.82.1.123:8081/axis/services/AuthenticateService?wsdl";
				Client client = new Client(new URL(url));
				Object[] result = client.invoke("getUserCodeFromToken", new Object[] { request.getParameter("Token") });
				System.out.println(result[0].toString());
				String user_code = result[0].toString();
				user_iidd = basicService.getUserIDByExtCode(user_code);
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

			} else {
				errorMsg = "Token 参数传递出错！";
				return "error";
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			return "error";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			logger.debug("end single point");
			return "error";
		}

	}

}
