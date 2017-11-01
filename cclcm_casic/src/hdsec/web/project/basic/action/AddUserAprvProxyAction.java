package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysProxy;
import hdsec.web.project.common.util.Constants;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 *添加代理
 * @author yueying
 *
 */
public class AddUserAprvProxyAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private SysProxy proxy;
	private String proxy_user_iidd;
	private Date useful_life_time;
	private String proxy_type;
	private String define_value;
	
	public void setDefine_value(String define_value) {
		if (StringUtils.hasLength(define_value)) {
			this.define_value = Constants.COMMON_SEPARATOR
					+ define_value.replaceAll(" ", "").replaceAll(",", Constants.COMMON_SEPARATOR)
					+ Constants.COMMON_SEPARATOR;
		}
	}
	
	public String getDefine_value() {
		return define_value;
	}
	
	@Override
	public String executeFunction() throws Exception {
		proxy_type = define_value;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("proxy_user_iidd", proxy_user_iidd);
		map.put("useful_life_time", useful_life_time);
		map.put("proxy_type", proxy_type);
		map.put("user_iidd", getCurUser().getUser_iidd());
		basicService.addUserAprvProxy(map);
		return "ok";
		
	}
	
	public SysProxy getProxy() {
		return proxy;
	}
	
	public void setProxy(SysProxy proxy) {
		this.proxy = proxy;
	}
	
	public String getProxy_user_iidd() {
		return proxy_user_iidd;
	}
	
	public void setProxy_user_iidd(String proxy_user_iidd) {
		this.proxy_user_iidd = proxy_user_iidd;
	}
	
	public Date getUseful_life_time() {
		return useful_life_time;
	}
	
	public void setUseful_life_time(Date useful_life_time) {
		this.useful_life_time = useful_life_time;
	}
	
	public String getProxy_type() {
		return proxy_type;
	}
	
	public void setProxy_type(String proxy_type) {
		this.proxy_type = proxy_type;
	}
	
	public String getUser_name() {
		return getCurUser().getUser_name();
	}
	
}
