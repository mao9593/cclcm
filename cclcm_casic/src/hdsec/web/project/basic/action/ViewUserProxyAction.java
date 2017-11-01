package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysProxy;
import hdsec.web.project.burn.model.BurnEvent;
import hdsec.web.project.user.model.SecRole;

import java.util.ArrayList;
import java.util.List;

/**
 * 查看代理
 * @author yueying
 *
 */
public class ViewUserProxyAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private List<SysProxy> proxys;
	private String user_name;
	private int is_approve_admin=0;
	
	
	public int getIs_approve_admin() {
		return is_approve_admin;
	}

	public void setIs_approve_admin(int is_approve_admin) {
		this.is_approve_admin = is_approve_admin;
	}

	@Override
	public String executeFunction() throws Exception {
		List<SysProxy> proxyList = new ArrayList<SysProxy>();
		for (SysProxy proxy : basicService.getUserProxys(getCurUser().getUser_iidd())) {
			proxy.setUser_name(userService.getUserNameByUserId(proxy.getUser_iidd()));
			proxy.setProxy_user_name(userService.getUserNameByUserId(proxy.getProxy_user_iidd()));
			proxy.setProxy_name(changeProxyTypeName(proxy.getProxy_type()));
			proxyList.add(proxy);
		}
		List<SecRole> secRoles=getCurUser().getUserRoleList();
		for (SecRole secRole : secRoles) {
			if (secRole.getRole_id().equals("10")) {
				is_approve_admin=1;
				break;
			}
		}
		proxys = proxyList;
		return SUCCESS;
		
	}
	
	private String changeProxyTypeName(String type) {
		if ("BURN".equals(type)) {
			return "刻录";
		} else if ("PRINT".equals(type)) {
			return "打印";
		}else if ("APPROVE".equals(type)) {
			return "审批";
		} else {
			return "打印/刻录";
		}
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
}
