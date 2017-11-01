package hdsec.web.project.common.extension;

import hdsec.web.project.common.util.Constants;
import hdsec.web.project.user.model.OnlineUser;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.service.UserManager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.securityfilter.authenticator.AuthenticatorFactory;
import org.securityfilter.config.SecurityConstraint;
import org.securityfilter.config.WebResourceCollection;
import org.securityfilter.filter.SecurityFilter;
import org.securityfilter.filter.URLPattern;
import org.securityfilter.filter.URLPatternFactory;
import org.springframework.core.io.Resource;

/**
 * 说明:
 *
 */
public class SecurityFilterExtension extends SecurityFilter {
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		try {
			String configFile = config.getInitParameter(CONFIG_FILE_KEY);
			if (configFile == null) {
				configFile = DEFAULT_CONFIG_FILE;
			}
			boolean validate = false;
			SecurityConfigExtension securityConfig = new SecurityConfigExtension(validate);
			Resource[] resources = ServletResourcePatternResovlerAndLoader.getResourceArray(configFile);
			URL[] configURLs = new URL[resources.length];
			for (int i = 0; i < resources.length; i++) {
				configURLs[i] = resources[i].getURL();
			}
			
			securityConfig.loadConfig(configURLs);
			
			// get the realm
			realm = securityConfig.getRealm();
			
			// create an Authenticator
			authenticator = AuthenticatorFactory.createAuthenticator(config, securityConfig);
			
			// create pattern list
			patternFactory = new URLPatternFactory();
			patternList = new ArrayList<URLPattern>();
			int order = 1;
			List constraints = securityConfig.getSecurityConstraints();
			for (Iterator cIter = constraints.iterator(); cIter.hasNext();) {
				SecurityConstraint constraint = (SecurityConstraint) cIter.next();
				for (Iterator rIter = constraint.getWebResourceCollections().iterator(); rIter.hasNext();) {
					WebResourceCollection resourceCollection = (WebResourceCollection) rIter.next();
					for (Iterator pIter = resourceCollection.getURLPatterns().iterator(); pIter.hasNext();) {
						URLPattern pattern = patternFactory.createURLPattern((String) pIter.next(), constraint,
								resourceCollection, order++);
						patternList.add(pattern);
					}
				}
			}
			Collections.sort(patternList);
			
		} catch (java.io.IOException ioe) {
			System.err.println("unable to parse input: " + ioe);
		} catch (org.xml.sax.SAXException se) {
			System.err.println("unable to parse input: " + se);
		} catch (Exception e) {
			System.err.println("error: " + e);
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,
			ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession();
		//User user = (User) session.getAttribute(Constants.SESSION_USER_KEY);
		SecUser user = (SecUser) session.getAttribute(Constants.SESSION_USER_KEY);
		if (user != null) {
			UserManager.registLastAccessUser(session.getId());
		} else {
			String sessionID = session.getId();
			if (sessionID != null) {
				Map<String, OnlineUser> onlineUsers = UserManager.getOnlineUserMap();
				if (onlineUsers.containsKey(sessionID)) {
					onlineUsers.remove(sessionID);//移除该session
					logger.info(">>>>>>>>>>>>sessionID，超时了<<<<<<<<<<<");
				}
			}
		}
		super.doFilter(request, response, filterChain);
	}
}
