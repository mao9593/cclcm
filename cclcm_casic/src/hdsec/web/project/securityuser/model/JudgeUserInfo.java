package hdsec.web.project.securityuser.model;

import hdsec.web.project.common.bm.model.BmRealUser;
import hdsec.web.project.user.model.SecUser;

public class JudgeUserInfo {
	private SecUser suser = null;
	private BmRealUser bmuser = null;
	private Integer statue = null;

	public SecUser getSuser() {
		return suser;
	}

	public void setSuser(SecUser suser) {
		this.suser = suser;
	}

	public BmRealUser getBmuser() {
		return bmuser;
	}

	public void setBmuser(BmRealUser bmuser) {
		this.bmuser = bmuser;
	}

	public String getStatue_name() {
		if (statue == null) {
			return "";
		}
		switch (statue) {
		case 1:
			return "未申请";
		case 2:
			return "已申请";
		case 3:
			return "已通过";
		default:
			return "";
		}
	}

	public Integer getStatue() {
		return statue;
	}

	public void setStatue(Integer statue) {
		this.statue = statue;
	}

	public JudgeUserInfo() {
	}

	public JudgeUserInfo(SecUser suser, BmRealUser bmuser, Integer statue) {
		this.suser = suser;
		this.bmuser = bmuser;
		this.statue = statue;
	}
}