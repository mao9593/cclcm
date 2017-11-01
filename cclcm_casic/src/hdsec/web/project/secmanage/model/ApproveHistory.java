package hdsec.web.project.secmanage.model;

public class ApproveHistory {
	private String approvename = "";
	private String approvecontent = "";

	public String getApprovename() {
		return approvename;
	}

	public void setApprovename(String approvename) {
		this.approvename = approvename;
	}

	public String getApprovecontent() {
		return approvecontent;
	}

	public void setApprovecontent(String approvecontent) {
		this.approvecontent = approvecontent;
	}

	public ApproveHistory(String approvename, String approvecontent) {
		this.approvename = approvename;
		this.approvecontent = approvecontent;
	}
}