package hdsec.web.project.basic.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TransferMessage {
	private String sesquenceno;
	private String pid;
	private String sysId;
	private String sysName;
	private String dealLevel;
	private String wordFunc;
	private String urgent;
	private String leaveword;
	private String fromMan;
	private Date fromDate;
	private String title;
	private String linkUrl;
	private String readed;
	private String fromDept;

	public String getSesquenceno() {
		return sesquenceno;
	}

	public void setSesquenceno(String sesquenceno) {
		this.sesquenceno = sesquenceno;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public String getDealLevel() {
		return dealLevel;
	}

	public void setDealLevel(String dealLevel) {
		this.dealLevel = dealLevel;
	}

	public String getWordFunc() {
		return wordFunc;
	}

	public void setWordFunc(String wordFunc) {
		this.wordFunc = wordFunc;
	}

	public String getUrgent() {
		return urgent;
	}

	public void setUrgent(String urgent) {
		this.urgent = urgent;
	}

	public String getLeaveword() {
		return leaveword;
	}

	public void setLeaveword(String leaveword) {
		this.leaveword = leaveword;
	}

	public String getFromMan() {
		return fromMan;
	}

	public void setFromMan(String fromMan) {
		this.fromMan = fromMan;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getReaded() {
		return readed;
	}

	public void setReaded(String readed) {
		this.readed = readed;
	}

	public String getFromDept() {
		return fromDept;
	}

	public void setFromDept(String fromDept) {
		this.fromDept = fromDept;
	}

}
