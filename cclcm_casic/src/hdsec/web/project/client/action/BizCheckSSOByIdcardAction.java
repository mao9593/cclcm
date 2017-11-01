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
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 登录成功后，自动跳转的action，查询用户权限，返回导航页
 * 
 * @author renmingfei
 * 
 */
public class BizCheckSSOByIdcardAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private UserService userService;
	private SecUser user = null;
	private String errorMsg = "";
	private String PID = "";
	private String sessionID;
	private String WSUrl;
	private String verifySSO = "";
	private String userID = "";
	private String userName = "";

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPID() {
		return PID;
	}

	public String getSessionID() {
		return sessionID;
	}

	public String getWSUrl() {
		return WSUrl;
	}

	public String getVerifySSO() {
		return verifySSO;
	}

	public String getUserID() {
		return userID;
	}

	public void setUser(SecUser user) {
		this.user = user;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public void setVerifySSO(String verifySSO) {
		logger.info("verifySSO:" + verifySSO);
		this.verifySSO = verifySSO;
	}

	public void setUserID(String userID) {
		logger.info("userID:" + userID);
		this.userID = userID;
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
	private void putUserIntoSession(String idcard) throws Exception {
		HttpSession session = getRequest().getSession(true);
		user = (SecUser) session.getAttribute(Constants.SESSION_USER_KEY);
		if (user != null && user.getIdCard().equals(idcard)) {
			logger.debug("user idcard [" + idcard + "] already in session");
		} else {
			user = userService.getSecUserByIdcard(idcard);
			if (user == null) {
				throw new Exception("---Idcard[" + idcard + "] does not exist!---");
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
			logger.info("PID:" + PID);
			logger.info("verifySSO:" + verifySSO);
			logger.info("sessionID:" + sessionID);
			logger.info("userName:" + userName);
			logger.info("userID:" + userID);
			if ((!PID.trim().equals("")) && checkUserInfo()) {
				putUserIntoSession(PID.trim());
			} else {
				throw new Exception("---个人信息验证失败，请找管理员确认---");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			errorMsg = "信息验证失败，无法登录";
			// errorMsg = e.getMessage();
			return "noOper";
		}
		return SUCCESS;
	}

	private boolean checkUserInfo() throws Exception {
		Service service = new Service();
		Call call = (Call) service.createCall();
		call.setTargetEndpointAddress(WSUrl);
		call.setOperationName("runBiz");// 设置操作名

		/* 设置入口参数 */
		call.addParameter("packageName", XMLType.XSD_STRING, ParameterMode.IN);
		call.addParameter("unitId", XMLType.XSD_STRING, ParameterMode.IN);
		call.addParameter("processName", XMLType.XSD_STRING, ParameterMode.IN);
		call.addParameter("bizDataXML", XMLType.XSD_STRING, ParameterMode.IN);
		call.setReturnType(XMLType.XSD_STRING);
		String projectDetails = "<?xml version=\"1.0\" encoding=\"GB2312\"?>" + "<root>" + "<data>" + "<sessionID>"
				+ sessionID + "</sessionID>" + "<userID>" + userID + "</userID>" + "<userName>" + userName
				+ "</userName>" + "<PID>" + PID + "</PID>" + "<verifySSO>" + verifySSO + "</verifySSO>" + "<msg></msg>"
				+ "</data>" + "</root>";
		/* 调用门户系统WebService服务，返回结果 */
		String[] param = { "common", "0", "biz.bizCheckSSO", projectDetails };
		Object obj = call.invoke(param);// obj是代表返回一个XML格式的串
		String msg = obj.toString();
		int m = msg.indexOf("<msg>");
		int k = m + 5;
		String valu = msg.substring(k, k + 1);
		int value = Integer.parseInt(valu, 10);
		if (1 == value) {
			return true;
		}
		return false;
	}

	public void setPID(String PID) {
		logger.info("PID:" + PID);
		this.PID = PID;
	}

	public void setSessionID(String sessionID) {
		logger.info("sessionID:" + sessionID);
		this.sessionID = sessionID;
	}

	public void setWSUrl(String wSUrl) {
		logger.info("WSUrl:" + wSUrl);
		WSUrl = wSUrl;
	}

}
