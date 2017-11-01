package hdsec.web.project.basic.action;

import java.util.HashMap;
import java.util.Map;

/**
 *添加代理
 * @author yueying
 *
 */
public class DelUserAprvProxyAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String proxy_user_iidd;
	
	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("proxy_user_iidd", proxy_user_iidd);
		map.put("user_iidd", getCurUser().getUser_iidd());
		basicService.deleteUserAprvProxy(map);
		return "ok";
		
	}
	
	public String getProxy_user_iidd() {
		return proxy_user_iidd;
	}
	
	public void setProxy_user_iidd(String proxy_user_iidd) {
		this.proxy_user_iidd = proxy_user_iidd;
	}
	
}
