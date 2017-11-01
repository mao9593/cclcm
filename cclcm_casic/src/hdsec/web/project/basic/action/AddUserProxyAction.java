package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.NewSecRole;
import hdsec.web.project.basic.model.SysProxy;
import hdsec.web.project.user.model.ModuleEnum;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 添加代理
 * 
 * @author yueying
 * 
 */
public class AddUserProxyAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private SysProxy proxy;
	private String proxy_user_iidd;
	private Date useful_life_time;
	private String proxy_type;

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("proxy_user_iidd", proxy_user_iidd);
		map.put("useful_life_time", useful_life_time);
		map.put("proxy_type", proxy_type);
		map.put("user_iidd", getCurUser().getUser_iidd());
		// 判断代理人是否是刻录管理员，如果是则报错，不是则添加
		if (proxy_type.equals("ALL") || proxy_type.equals("BURN")) {
			Map<String, String> maps = new HashMap<String, String>();
			maps.put("user_iidd", proxy_user_iidd);
			List<NewSecRole> secRoleList = basicService.getRoleListByUser(maps);
			for (NewSecRole role : secRoleList) {
				if (role.getRole_id().equals("15")) {
					throw new Exception("此用户为刻录管理员，不能将其设置为代理刻录人");
				}
			}
		}
		// end
		basicService.addUserProxy(map);
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

	@Override
	public boolean getIsPrintEnable() {
		return ModuleEnum.PRINT.isModuleEnable();
	}

	@Override
	public boolean getIsBurnEnable() {
		return ModuleEnum.BURN.isModuleEnable();
	}

}
