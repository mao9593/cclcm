package hdsec.web.project.common.util;

import ognl.OgnlRuntime;

public class OgnlUtil {
	public void contextInitialized() {
		OgnlRuntime.setSecurityManager(null);
	}
}
