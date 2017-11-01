package hdsec.web.project.user.model;

public class ApproverUser {
	protected String user_iidd = "";
	protected String user_name = "";
	
	public String getUser_iidd() {
		return user_iidd;
	}
	
	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}
	
	public String getUser_name() {
		return user_name;
	}
	
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public ApproverUser() {
		super();
	}
	
	public ApproverUser(String user_iidd, String user_name) {
		super();
		this.user_iidd = user_iidd;
		this.user_name = user_name;
	}
	
}
