package hdsec.web.project.common.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Organization {
	private String DealFlag;//ADD DEL
	private String orgCode;
	private String orgName;
	private String orgPCode;//父部门
	
	public String getDealFlag() {
		return DealFlag;
	}
	public void setDealFlag(String dealFlag) {
		DealFlag = dealFlag;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgPCode() {
		return orgPCode;
	}
	public void setOrgPCode(String orgPCode) {
		this.orgPCode = orgPCode;
	}

}
