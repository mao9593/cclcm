package hdsec.web.project.basic.model;

import hdsec.web.project.common.BaseDomain;

import java.util.Date;

/**
 * 代理人信息
 * @author yueying
 *
 */
public class SysProxyAprv extends BaseDomain {
	private String proxy_user_iidd;
	private String proxy_user_name;
	private String user_iidd;
	private String user_name;
	private String proxy_type;
	private String proxy_name;
	private Date useful_life_time;
	
	public String getProxy_user_iidd() {
		return proxy_user_iidd;
	}
	
	public void setProxy_user_iidd(String proxy_user_iidd) {
		this.proxy_user_iidd = proxy_user_iidd;
	}
	
	public String getProxy_user_name() {
		return proxy_user_name;
	}
	
	public void setProxy_user_name(String proxy_user_name) {
		this.proxy_user_name = proxy_user_name;
	}
	
	public String getUser_iidd() {
		return user_iidd;
	}
	
	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}
	
	public String getUser_name() {
		return user_name;
	}
	
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public String getProxy_type() {
		return proxy_type;
	}
	
	public void setProxy_type(String proxy_type) {
		this.proxy_type = proxy_type;
	}
	
	public String getUseful_life_time() {
		if (null != useful_life_time) {
			return getSdf().format(useful_life_time);
		}
		return "";
	}
	
	public void setUseful_life_time(Date useful_life_time) {
		this.useful_life_time = useful_life_time;
	}
	
	public String getProxy_name() {
		return proxy_name;
	}
	
	public void setProxy_name(String proxy_name) {
		this.proxy_name = proxy_name;
	}
	
}
