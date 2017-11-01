package hdsec.web.project.user.model;

import java.io.Serializable;
import java.util.Date;

import com.casic304.util.cal.DateUtil;
import com.wholewise.common.services.accountmanager.model.User;

/**
 * 在线用户模型对象
 * 
 * @author mzwei User: mzwei Date: 2006-9-5 Time: 8:39:09
 */
public class OnlineUser implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userID; // 用户ID
	private String userName; // 用户名
	private String deptName; // 部门名称
	private String sessionID; // 会话ID
	private Date loginTime; // 登录时间
	private Date lastAccessTime; // 最后访问时间
	private String loginIP; // 登录IP
	
	public String getUserID() {
		return userID;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getDeptName() {
		return deptName;
	}
	
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	public String getSessionID() {
		return sessionID;
	}
	
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	
	public Date getLoginTime() {
		return loginTime;
	}
	
	/**
	 * 返回登录时间的标准格式字符串，格式为：yyyy-MM-dd hh:mm:ss
	 * 
	 * @return 登录时间的标准格式字符串
	 */
	public String getLoginTimeString() {
		return DateUtil.formatDate(loginTime);
	}
	
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
	public Date getLastAccessTime() {
		return lastAccessTime;
	}
	
	/**
	 * 返回最后访问时间的标准格式字符串，格式为：yyyy-MM-dd hh:mm:ss
	 * 
	 * @return 最后访问时间的标准格式字符串
	 */
	public String getLastAccessTimeString() {
		return DateUtil.formatDate(lastAccessTime);
	}
	
	public void setLastAccessTime(Date lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}
	
	public String getLoginIP() {
		return loginIP;
	}
	
	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}
	
	/*
	 * 重载Object基本方法
	 */
	
	@Override
	public int hashCode() {
		return this.userID.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			return ((User) obj).getUserId().equalsIgnoreCase(this.userID);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return this.userID + " login at " + DateUtil.formatDate(this.loginTime) + " from " + this.loginIP;
	}
}
