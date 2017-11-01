package hdsec.web.project.common.extension;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.commons.digester.Digester;
import org.apache.log4j.Logger;
import org.securityfilter.config.SecurityConfig;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * 登录认证相关类
 * 
 * @author renmingfei
 * 
 */
public class SecurityConfigExtension extends SecurityConfig {
	protected Logger logger = Logger.getLogger(this.getClass());

	public SecurityConfigExtension(boolean validating) {
		super(validating);
	}

	public void loadConfig(URL[] configURLs) throws IOException, SAXException {
		logger.info("-----------enter " + this.getClass().getSimpleName() + "-------------");
		setSecurityConstraints(new ArrayList());

		Digester digester = new Digester();

		// only register the DTDs if we will be validating
		registerLocalDTDs(digester);

		// realms
		digester.addObjectCreate("securityfilter-config/realm", null, "className");
		digester.addSetProperty("securityfilter-config/realm/realm-param", "name", "value");
		digester.addSetNext("securityfilter-config/realm", "addRealm", "java.lang.Object");

		// auth method, realm name
		digester.addCallMethod("securityfilter-config/login-config/auth-method", "setAuthMethod", 0);
		digester.addCallMethod("securityfilter-config/login-config/realm-name", "setRealmName", 0);

		// login, error, logout, and default pages
		digester.addCallMethod("securityfilter-config/login-config/form-login-config/form-login-page", "setLoginPage",
				0);
		digester.addCallMethod("securityfilter-config/login-config/form-login-config/form-error-page", "setErrorPage",
				0);
		digester.addCallMethod("securityfilter-config/login-config/form-login-config/form-logout-page",
				"setLogoutPage", 0);
		digester.addCallMethod("securityfilter-config/login-config/form-login-config/form-default-page",
				"setDefaultPage", 0);

		// persistent login manager
		digester.addObjectCreate("securityfilter-config/login-config/form-login-config/remember-me", null, "className");
		digester.addSetProperty("securityfilter-config/login-config/form-login-config/remember-me/remember-me-param",
				"name", "value");
		digester.addSetNext("securityfilter-config/login-config/form-login-config/remember-me",
				"setPersistentLoginManager",
				"org.securityfilter.authenticator.persistent.PersistentLoginManagerInterface");

		// security-constraint
		digester.addObjectCreate("securityfilter-config/security-constraint",
				"org.securityfilter.config.SecurityConstraint");
		digester.addSetNext("securityfilter-config/security-constraint", "addSecurityConstraint",
				"org.securityfilter.config.SecurityConstraint");

		// auth-constraint
		digester.addObjectCreate("securityfilter-config/security-constraint/auth-constraint",
				"org.securityfilter.config.AuthConstraint");
		digester.addSetNext("securityfilter-config/security-constraint/auth-constraint", "setAuthConstraint",
				"org.securityfilter.config.AuthConstraint");
		digester.addCallMethod("securityfilter-config/security-constraint/auth-constraint/role-name", "addRole", 0);

		// web-resource-collection
		digester.addObjectCreate("securityfilter-config/security-constraint/web-resource-collection",
				"org.securityfilter.config.WebResourceCollection");
		digester.addSetNext("securityfilter-config/security-constraint/web-resource-collection",
				"addWebResourceCollection", "org.securityfilter.config.WebResourceCollection");
		digester.addCallMethod("securityfilter-config/security-constraint/web-resource-collection/url-pattern",
				"addURLPattern", 0);
		digester.addCallMethod("securityfilter-config/security-constraint/web-resource-collection/http-method",
				"addHttpMethod", 0);

		// 修改用来读取模块化配置
		digester.setUseContextClassLoader(true);
		digester.setValidating(isValidating());

		for (int i = 0; i < configURLs.length; i++) {
			InputSource input = new InputSource(configURLs[i].openStream());
			digester.push(this);
			digester.parse(input);
		}

	}

}
