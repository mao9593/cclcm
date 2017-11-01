package hdsec.web.project.user.model;


public class SecCommonContact {
	private int contact_id;
	private String contact_code;
	private int contact_type;
	private int group_id;
	private String user_name;
	private String dept_name;
	private String post_name;

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public int getContact_id() {
		return contact_id;
	}

	public void setContact_id(int contact_id) {
		this.contact_id = contact_id;
	}

	public String getContact_code() {
		return contact_code;
	}

	public void setContact_code(String contact_code) {
		this.contact_code = contact_code;
	}

	public int getContact_type() {
		return contact_type;
	}

	public void setContact_type(int contact_type) {
		this.contact_type = contact_type;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getPost_name() {
		return post_name;
	}

	public void setPost_name(String post_name) {
		this.post_name = post_name;
	}
}
