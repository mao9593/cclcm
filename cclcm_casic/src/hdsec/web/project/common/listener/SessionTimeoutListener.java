package hdsec.web.project.common.listener;

import hdsec.web.project.user.service.UserManager;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

/**
 * 会话超时监听器
 * @author renmingfei
 */
public class SessionTimeoutListener implements HttpSessionListener {
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		logger.info("session created with sessionID:" + session.getId());
		
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		logger.info("session destroyed with sessionID:" + session.getId());
		//如果当前session有用户存在则将其从在线用户表中称除
		
		UserManager.removeOnlineUser(session.getId());
	}
}
