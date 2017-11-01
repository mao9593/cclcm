package hdsec.web.project.user.action;

import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.MD5;
import hdsec.web.project.user.model.OnlineUser;
import hdsec.web.project.user.model.SecRole;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.service.UserManager;
import hdsec.web.project.user.service.UserService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 锁定会话
 * @author renmingfei
 *
 */
public class HoldSessionAction extends ActionSupport {
	private Logger logger = Logger.getLogger(this.getClass());
	private static final long serialVersionUID = 1L;
	@Resource
	protected UserService userService;
	private String user_iidd = "";
	private String password = "";
	private String checkpwd = "N";
	private String done = "N";
	private String msg = "";
	private String errorMsg = "";
	
	public String getUser_iidd() {
		return user_iidd;
	}
	
	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getDone() {
		return done;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setCheckpwd(String checkpwd) {
		this.checkpwd = checkpwd;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	//	private HttpSession getSession() {
	//		return getRequest().getSession(false);
	//	}
	
	private HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	
	@Override
	public String execute() {
		//		Cookie[] cs = getRequest().getCookies();
		//		for (Cookie it : cs) {
		//			logger.info("name:" + it.getName() + ".value:" + it.getValue() + ".period:" + it.getMaxAge());
		//		}
		//logger.info("sessionid:" + getRequest().getSession(true).getId());
		try {
			if (checkpwd.equalsIgnoreCase("Y")) {
				if (userService.authenticate(user_iidd, MD5.getMD5Str(password))) {
					SecUser user = userService.getSecUserByUid(user_iidd);
					HttpSession session = getRequest().getSession(true);
					//把用户信息写进session
					session.setAttribute(Constants.SESSION_USER_KEY, user);
					//把用户的所有操作列表写进user
					List<String> allOperUrl = userService.getAllOperByUserOnly(user.getUser_iidd());
					user.setAllOper(allOperUrl);
					//把用户没有的操作列表写进user
					List<String> nonOperUrl = userService.getNonOperByUserOnly(user.getUser_iidd());
					user.setNonOper(nonOperUrl);
					//把用户的特殊角色列表写进user
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("user_iidd", user.getUser_iidd());
					map.put("role_type", Constants.ROLE_TYPE_SPECIAL);
					List<SecRole> specialRole = userService.getSpecialRoleByUserOnly(map);
					user.setSpecialRole(specialRole);
					//session.setAttribute(Constants.SESSION_SPEC_ROLE_KEY, specialRole);
					//把用户的特殊角色的操作列表写进user
					List<String> specialOperUrl = null;
					if (specialRole != null && specialRole.size() > 0) {
						specialOperUrl = userService.getSpecialOperByUserOnly(user.getUser_iidd());
					}
					user.setSpecialOper(specialOperUrl);
					//session.setAttribute(Constants.SESSION_SPEC_OPER_KEY, specialOperUrl);
					Date date = new Date();
					OnlineUser onlineUser = new OnlineUser();
					onlineUser.setUserID(user_iidd);
					onlineUser.setUserName(user.getUser_name());
					onlineUser.setSessionID(session.getId());
					onlineUser.setDeptName(user.getDept_name());
					onlineUser.setLoginTime(date);
					onlineUser.setLoginIP(getRequest().getRemoteAddr());
					onlineUser.setLastAccessTime(date);
					UserManager.addOnlineUser(onlineUser);
					logger.info("sessionid:" + session.getId());
					ServletActionContext.getResponse().addCookie(new Cookie("JSESSIONID", session.getId()));
					done = "Y";
				} else {
					msg = "密码错误";
				}
			}
		} catch (Exception e) {
			logger.error("--------------exception thrown---------------");
			logger.error(e.getMessage());
			setErrorMsg(e.getMessage());
			//e.printStackTrace();
		}
		return SUCCESS;
	}
}
