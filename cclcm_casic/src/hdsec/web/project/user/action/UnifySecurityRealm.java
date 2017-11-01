package hdsec.web.project.user.action;

import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.MD5;
import hdsec.web.project.log.model.AdminOperLog;
import hdsec.web.project.log.model.UserLoginLog;
import hdsec.web.project.log.service.LogService;
import hdsec.web.project.log.service.LogServiceImpl;
import hdsec.web.project.user.model.OnlineUser;
import hdsec.web.project.user.model.SecRole;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.service.UserManager;
import hdsec.web.project.user.service.UserService;
import hdsec.web.project.user.service.UserServiceImpl;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.securityfilter.realm.SimplePrincipal;
import org.securityfilter.realm.SimpleSecurityRealmBase;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 登录处理类
 * 
 * @author renmingfei
 * 
 */
public class UnifySecurityRealm extends SimpleSecurityRealmBase {
	private Logger logger = Logger.getLogger(UnifySecurityRealm.class);
	private String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	// private String path1 =
	// Thread.currentThread().getContextClassLoader().getResource("/").getPath();
	// private ApplicationContext appContext = new
	// FileSystemXmlApplicationContext(path.substring(0,
	// path.indexOf("classes") - 1)
	// + "/application-context.xml");
	// private ApplicationContext appContext = new
	// ClassPathXmlApplicationContext("application-context.xml");
	private ApplicationContext appContext = null;
	private UserLoginLog userLoginLog = null;
	private AdminOperLog adminOperLog = null;

	@Override
	public boolean booleanAuthenticate(HttpServletRequest httpServletRequest, String user_iidd, String user_ppww) {
		logger.debug("--------------path is :" + path);
		logger.debug("------------appContext" + appContext);
		appContext = WebApplicationContextUtils.getWebApplicationContext(httpServletRequest.getServletContext());
		UserService userService = appContext.getBeansOfType(UserServiceImpl.class).get("userServiceImpl");
		LogService logService = appContext.getBeansOfType(LogServiceImpl.class).get("logServiceImpl");
		SecUser user = userService.getSecUserByUid(user_iidd);
		boolean temp = false;
		if (user == null) {
			httpServletRequest.setAttribute("errorMsg", "用户不存在");
			logger.debug("---User [" + user_iidd + "] is inexistence!---");
			return false;
		}
		if (isIpConfig(user, userService, httpServletRequest.getRemoteAddr()) == false) {
			String Msg = "";
			if (user.is_secAdmin()) {
				Msg = "安全管理员";
			} else if (user.is_sysAdmin()) {
				Msg = "系统管理员";
			} else if (user.is_audAdmin()) {
				Msg = "审计管理员";
			}
			httpServletRequest.setAttribute("errorMsg", Msg + "用户登录IP[" + httpServletRequest.getRemoteAddr() + "]不合法");
			adminOperLog = new AdminOperLog(user_iidd, user.getUser_name(), user.getDept_name(), Msg + "[" + user.getUser_iidd() + "]登录IP["
					+ httpServletRequest.getRemoteAddr() + "]不合法", "失败", new Date(), Constants.LOG_LOGON,
					httpServletRequest.getRemoteAddr(), httpServletRequest.getRemoteHost(), "admin");
			logService.addAdminOperLog(adminOperLog);
			return false;
		}

		if (userService.isUserLocked(user_iidd)) {
			httpServletRequest.setAttribute("errorMsg", "用户已被锁定，请10分钟后再尝试登录");
			logger.debug("---User [" + user_iidd + "] is locked!---");
			return false;
		}
		if (!(temp)) {// 认证
			try {
				temp = userService.authenticate(user_iidd, MD5.getMD5Str(user_ppww));
			} catch (Exception e) {
				httpServletRequest.setAttribute("errorMsg", "用户验证出现异常，请重试");
				logger.error(e.getMessage());
			}
		}

		if (temp) {// 写session
			HttpSession session = httpServletRequest.getSession(false);
			if (session != null) {
				// 如果存在，则先注销掉原来的session
				session.invalidate();
			}
			session = httpServletRequest.getSession(true);
			// 把用户信息写进session
			session.setAttribute(Constants.SESSION_USER_KEY, user);
			// 把登录时间写进session
			session.setAttribute(Constants.LOGON_TIME, new Date());
			// 把登录IP写进session
			session.setAttribute(Constants.LOGON_IP, httpServletRequest.getRemoteAddr());
			// 把用户的所有操作列表写进user
			List<String> allOperUrl = userService.getAllOperByUserOnly(user.getUser_iidd());
			user.setAllOper(allOperUrl);
			// 把用户没有的操作列表写进user
			List<String> nonOperUrl = userService.getNonOperByUserOnly(user.getUser_iidd());
			user.setNonOper(nonOperUrl);
			// 把用户的特殊角色列表写进user
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_iidd", user.getUser_iidd());
			map.put("role_type", Constants.ROLE_TYPE_SPECIAL);
			List<SecRole> specialRole = userService.getSpecialRoleByUserOnly(map);
			user.setSpecialRole(specialRole);
			// session.setAttribute(Constants.SESSION_SPEC_ROLE_KEY,
			// specialRole);
			// 把用户的特殊角色的操作列表写进user
			List<String> specialOperUrl = null;
			if ((specialRole != null) && (specialRole.size() > 0)) {
				specialOperUrl = userService.getSpecialOperByUserOnly(user.getUser_iidd());
			}
			user.setSpecialOper(specialOperUrl);
			// session.setAttribute(Constants.SESSION_SPEC_OPER_KEY,
			// specialOperUrl);
			Date date = new Date();
			OnlineUser onlineUser = new OnlineUser();
			onlineUser.setUserID(user_iidd);
			onlineUser.setUserName(user.getUser_name());
			onlineUser.setSessionID(session.getId());
			onlineUser.setDeptName(user.getDept_name());
			onlineUser.setLoginTime(date);
			onlineUser.setLoginIP(httpServletRequest.getRemoteAddr());
			onlineUser.setLastAccessTime(date);
			UserManager.addOnlineUser(onlineUser);
			if (!user_iidd.equals("admin")) {// 如果不是admin，则记录登录日志
				if (user.is_admin()) {// 如果是三员，将登录操作记入管理员操作日志
					adminOperLog = new AdminOperLog(user_iidd, user.getUser_name(), user.getDept_name(), "登录成功", "成功", new Date(),
							Constants.LOG_LOGON, httpServletRequest.getRemoteAddr(), httpServletRequest.getRemoteHost(), "admin");
					logService.addAdminOperLog(adminOperLog);
				} else {
					userLoginLog = new UserLoginLog(user.getUser_iidd(), user.getUser_name(), user.getDept_name(), "登录成功", "成功",
							new Date(), httpServletRequest.getRemoteAddr(), httpServletRequest.getRemoteHost());
					logService.addUserLoginLog(userLoginLog);
				}
				Map<String, String> map1 = new HashMap<String, String>();
				map1.put("user_iidd", user_iidd);
				map1.put("status", "0");
				userService.updateUserStatus(map1);
			}
			return true;
		} else {
			httpServletRequest.setAttribute("errorMsg", "用户密码输入错误，请重试");
			logger.debug("---Wrong password with User [" + user_iidd + "]!---");
			if (!user_iidd.equals("admin")) {// 如果不是admin，则记录登录日志
				if (user.is_admin()) {// 如果是三员，将登录操作记入管理员操作日志
					adminOperLog = new AdminOperLog(user_iidd, user.getUser_name(), user.getDept_name(), "登录失败，密码输入错误", "失败", new Date(),
							Constants.LOG_LOGON, httpServletRequest.getRemoteAddr(), httpServletRequest.getRemoteHost(), "admin");
					logService.addAdminOperLog(adminOperLog);
				} else {
					userLoginLog = new UserLoginLog(user.getUser_iidd(), user.getUser_name(), user.getDept_name(), "登录失败，密码输入错误", "失败",
							new Date(), httpServletRequest.getRemoteAddr(), httpServletRequest.getRemoteHost());
					logService.addUserLoginLog(userLoginLog);
				}
				// 密码输错次数加1
				userService.logfailTimesPlus(user.getUser_iidd(), httpServletRequest);
			}
			httpServletRequest.getSession().setAttribute(Constants.SESSION_USER_KEY, null);
			return false;
		}
	}

	// 读config中是否配置IP地址限制
	private boolean isIpConfig(SecUser user, UserService userService, String userIp) {
		String userIpArray[] = userIp.split("\\.");
		String userType = "";
		int onlineIp = Integer.parseInt(userIpArray[3]);
		String onlineStr = userIpArray[0] + "." + userIpArray[1] + "." + userIpArray[2];
		if (user.is_secAdmin()) {
			userType = "SECADMIN_LOGIN_IP";
		} else if (user.is_sysAdmin()) {
			userType = "SYSADMIN_LOGIN_IP";
		} else if (user.is_audAdmin()) {
			userType = "AUDADMIN_LOGIN_IP";
		} else {
			return true;
		}
		if ((user.is_secAdmin()) || (user.is_sysAdmin()) || (user.is_audAdmin())) {
			int existTag = userService.getIpConfigIsExist(userType);
			// 是否有ip限制
			if (existTag == 1) {
				int startuse = userService.getIpConfigStartuse(userType);
				// 是否开启ip限制状态
				if (startuse == 1) {
					// 取出ip限制段
					String limitIp = userService.getLimitIpConfig(userType);
					String limitIpArray[] = limitIp.split(",");
					boolean tag = false;
					for (String item : limitIpArray) {
						String everyOneLimitIp[] = item.split("-");
						if (everyOneLimitIp.length == 2) {
							String frontIpStr[] = everyOneLimitIp[0].split("\\.");
							String frontStr = frontIpStr[0] + "." + frontIpStr[1] + "." + frontIpStr[2];
							if (frontStr.equals(onlineStr)) {
								String laterIpStr[] = everyOneLimitIp[1].split("\\.");
								int frontIp = Integer.parseInt(frontIpStr[3]);
								int laterIp = Integer.parseInt(laterIpStr[3]);
								if ((onlineIp >= frontIp && onlineIp <= laterIp) || (onlineIp <= frontIp && onlineIp >= laterIp)) {
									tag = true;
									return true;
								}
							} else {
								continue;
							}
						}
					}
					if (tag == false) {
						return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public Principal authenticate(HttpServletRequest request, String username, String password) {
		logger.debug("---enter " + this.getClass().getSimpleName() + " authenticate()---");
		if (booleanAuthenticate(request, username, password)) {
			logger.debug("--- booleanAuthenticate returns TRUE---");
			return new SimplePrincipal(username);
		}
		return null;
	}
}
