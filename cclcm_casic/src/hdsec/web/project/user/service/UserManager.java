package hdsec.web.project.user.service;

import hdsec.web.project.user.model.OnlineUser;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.casic304.util.ListUtil;

public class UserManager {
	private static final Logger logger = Logger.getLogger(UserManager.class);
	private static Map<String, OnlineUser> onlineUserList = new Hashtable<String, OnlineUser>();
	
	public static void registLastAccessUser(String sessionID) {
		OnlineUser onlineUser = onlineUserList.get(sessionID);
		if (onlineUser != null)
			onlineUser.setLastAccessTime(new Date());
	}
	
	public static Map<String, OnlineUser> getOnlineUserMap() {
		return onlineUserList;
	}
	
	public static void removeOnlineUser(String sessionID) {
		onlineUserList.remove(sessionID);
		logger.info("注销成功!");
	}
	
	public static void addOnlineUser(OnlineUser onlineUser) {
		onlineUserList.put(onlineUser.getSessionID(), onlineUser);
	}
	
	@SuppressWarnings("unchecked")
	public static List<String> getOnlineUserIDList() {
		return ListUtil.fromCollection(onlineUserList.keySet());
	}
}
