package hdsec.web.project.domain;

/**
 * 域同步，用户信息类
 * 
 * @author renmingfei 2015-1-9
 */
public class UserInfo {
	private String userId;
	private String userName;
	private String securityCode;
	private String pid;
	private String deptId;
	private String deptName;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	@Override
	public String toString() {
		return "userId<" + userId + ">,userName<" + userName + ">,securityCode<" + securityCode + ">,pid<" + pid + ">,deptId<" + deptId
				+ ">,deptName<" + deptName + ">";
	}

	public boolean equals(UserInfo user) {
		if (this.userId.equals(user.getUserId()) && this.userName.equals(user.getUserName()) && this.pid.equals(user.getPid())
				&& this.securityCode.equals(user.getSecurityCode())) {
			return true;
		}
		return false;
	}
}
