package hdsec.web.project.basic.action;

import java.util.HashMap;
import java.util.Map;

/**
 *添加代理
 * @author yueying
 *
 */
public class DelUserProxyAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String proxy_user_iidd;
	private String proxy_type;
	
	public String getProxy_type() {
		return proxy_type;
	}

	public void setProxy_type(String proxy_type) {
		this.proxy_type = proxy_type;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("proxy_user_iidd", proxy_user_iidd);
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("proxy_type", proxy_type);
		basicService.deleteUserProxy(map);
		Map<String, String> map_proxy = new HashMap<String, String>();
		map_proxy.put("user_iidd", proxy_user_iidd);
		map_proxy.put("role_id", "10");
		map_proxy.put("is_proxy", "is_proxy");
		map_proxy.put("agent", getCurUser().getUser_iidd());
		userService.deleteProxyInfo(map_proxy);
		return "ok";
		
	}
	
	public String getProxy_user_iidd() {
		return proxy_user_iidd;
	}
	
	public void setProxy_user_iidd(String proxy_user_iidd) {
		this.proxy_user_iidd = proxy_user_iidd;
	}
	
}
