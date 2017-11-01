package basic.xml;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
	private String DealFlag;//ADD DEL
	private String userID;
	private String operatorName;//用户姓名
	private String PID;//身份证
	private String secret;//密级 1机密，2秘密，3内部，4非密
	private String orgID;//组织机构名称
	private String orgCode;
	
	public String getDealFlag() {
		return DealFlag;
	}
	public void setDealFlag(String dealFlag) {
		DealFlag = dealFlag;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getPID() {
		return PID;
	}
	public void setPID(String pID) {
		PID = pID;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getOrgID() {
		return orgID;
	}
	public void setOrgID(String orgID) {
		this.orgID = orgID;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
}
