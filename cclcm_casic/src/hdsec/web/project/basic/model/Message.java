package hdsec.web.project.basic.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {
	private String MgsFromDept;// 来源部门
	private String MgsFromSys = "涉密载体系统";
	private String MgsType;// 代办事项
	private String MgsLevel;// 1 优先代办 2 普遍 3 一般
	private String MgsFunc = "审批";// 审批
	private String MgsWord;// 留言
	private String MgsFromName;// 信息来源
	private Date SentTime; // 发送时间
	private String MgsUrgent;// 一般 急件 特急
	private String MgsOperateType = "OpWin";// 弹出窗口
	private String MgsStatus;// 是否浏览 1未浏览，0
	private String MgsAccessory;// 附件 0表示没有
	private String Title;// 标题
	private String Url;// 审批地址
	private String WfUrl;// 工作流地址（不用）
	private String DEALFLAG;// 操作类型 INS 增加，UPD 修改，DEL 删除 (不用)

	public String getMgsFromDept() {
		return MgsFromDept;
	}

	public void setMgsFromDept(String mgsFromDept) {
		MgsFromDept = mgsFromDept;
	}

	public String getMgsFromSys() {
		return MgsFromSys;
	}

	public void setMgsFromSys(String mgsFromSys) {
		MgsFromSys = mgsFromSys;
	}

	public String getMgsType() {
		return MgsType;
	}

	public void setMgsType(String mgsType) {
		MgsType = mgsType;
	}

	public String getMgsLevel() {
		return MgsLevel;
	}

	public void setMgsLevel(String mgsLevel) {
		MgsLevel = mgsLevel;
	}

	public String getMgsFunc() {
		return MgsFunc;
	}

	public void setMgsFunc(String mgsFunc) {
		MgsFunc = mgsFunc;
	}

	public String getMgsWord() {
		return MgsWord;
	}

	public void setMgsWord(String mgsWord) {
		MgsWord = mgsWord;
	}

	public String getMgsFromName() {
		return MgsFromName;
	}

	public void setMgsFromName(String mgsFromName) {
		MgsFromName = mgsFromName;
	}

	public Date getSentTime() {
		return SentTime;
	}

	public void setSentTime(Date sentTime) {
		SentTime = sentTime;
	}

	public String getMgsUrgent() {
		return MgsUrgent;
	}

	public void setMgsUrgent(String mgsUrgent) {
		MgsUrgent = mgsUrgent;
	}

	public String getMgsOperateType() {
		return MgsOperateType;
	}

	public void setMgsOperateType(String mgsOperateType) {
		MgsOperateType = mgsOperateType;
	}

	public String getMgsStatus() {
		return MgsStatus;
	}

	public void setMgsStatus(String mgsStatus) {
		MgsStatus = mgsStatus;
	}

	public String getMgsAccessory() {
		return MgsAccessory;
	}

	public void setMgsAccessory(String mgsAccessory) {
		MgsAccessory = mgsAccessory;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public String getWfUrl() {
		return WfUrl;
	}

	public void setWfUrl(String wfUrl) {
		WfUrl = wfUrl;
	}

	public String getDEALFLAG() {
		return DEALFLAG;
	}

	public void setDEALFLAG(String dEALFLAG) {
		DEALFLAG = dEALFLAG;
	}

}
