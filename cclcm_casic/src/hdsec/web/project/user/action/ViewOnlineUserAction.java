package hdsec.web.project.user.action;

import hdsec.web.project.user.model.OnlineUser;
import hdsec.web.project.user.service.UserManager;

import java.util.Map;

public class ViewOnlineUserAction extends UserBaseAction {
	
	private static final long serialVersionUID = 1L;
	
	private Map<String, OnlineUser> onlineUserMap = null;
	
	public Map<String, OnlineUser> getOnlineUserMap() {
		return onlineUserMap;
	}
	
	@Override
	public String executeFunction() throws Exception {
		onlineUserMap = UserManager.getOnlineUserMap();
		return SUCCESS;
	}
	
}
