package hdsec.web.project.basic.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.basic.model.SysProxy;
import hdsec.web.project.common.util.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查看代理
 * 
 * @author yueying
 * 
 */
public class ViewUserAprvProxyAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private List<SysProxy> proxys;
	private String user_name;
	private int proxy_size;
	
	@Override
	public String executeFunction() throws Exception {
		List<SysProxy> proxyList = new ArrayList<SysProxy>();
		for (SysProxy proxy : basicService.getUserAprvProxys(getCurUser().getUser_iidd())) {
			proxy.setUser_name(userService.getUserNameByUserId(proxy.getUser_iidd()));
			proxy.setProxy_user_name(userService.getUserNameByUserId(proxy.getProxy_user_iidd()));
			proxy.setProxy_name(changeProxyTypeName(proxy.getProxy_type()));
			proxyList.add(proxy);
		}
		proxys = proxyList;
		proxy_size = proxys.size();
		return SUCCESS;
		
	}
	
	private String changeProxyTypeName(String proxy_type) {
		String name = "";
		StringBuffer sb = new StringBuffer();
		Map<String, String> map = new HashMap<String, String>();
		String types[] = proxy_type.split(Constants.COMMON_SEPARATOR_REGEX);
		for (JobTypeEnum job : JobTypeEnum.values()) {
			map.put(job.getJobTypeCode(), job.getJobTypeName());
		}
		for (String type : types) {
			if (!type.trim().isEmpty()) {
				sb.append(replaceNull(map.get(type))).append(",");
			}
		}
		name = sb.toString();
		if (name.endsWith(",")) {
			name = name.substring(0, name.length() - 1);
		}
		return name;
	}
	
	private String replaceNull(String str) {
		if (null == str) {
			return "";
		}
		return str;
	}
	
	public List<SysProxy> getProxys() {
		return proxys;
	}
	
	public void setProxys(List<SysProxy> proxys) {
		this.proxys = proxys;
	}
	
	public String getUser_name() {
		return user_name;
	}
	
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public int getProxy_size() {
		return proxy_size;
	}
	
	public void setProxy_size(int proxy_size) {
		this.proxy_size = proxy_size;
	}
}
