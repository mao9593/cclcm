package hdsec.web.project.user.model;

/**
 * 用户和角色关联类
 * 
 * 2014-4-11 下午7:48:51
 * 
 * @author renmingfei
 */
public class SecRoleUser {
	
	private String user_iidd = "";
	private String role_id = "";
	private String role_name = "";
	private String role_desc = "";
	private String domain = "";
	private String domain_table = "";
	private String subsys_code = "";
	private String is_proxy = "";
	private String agent = "";
	
	public String getIs_proxy() {
		return is_proxy;
	}

	public void setIs_proxy(String is_proxy) {
		this.is_proxy = is_proxy;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getSubsys_code() {
		return subsys_code;
	}
	
	public void setSubsys_code(String subsys_code) {
		this.subsys_code = subsys_code;
	}
	
	public String getRole_desc() {
		return role_desc;
	}
	
	public void setRole_desc(String role_desc) {
		this.role_desc = role_desc;
	}
	
	public String getRole_name() {
		return role_name;
	}
	
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
	public String getUser_iidd() {
		return user_iidd;
	}
	
	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}
	
	public String getRole_id() {
		return role_id;
	}
	
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	
	public String getDomain() {
		return domain;
	}
	
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	public String getDomain_table() {
		return domain_table;
	}
	
	public void setDomain_table(String domain_table) {
		this.domain_table = domain_table;
	}
	
	public SecRoleUser() {
		super();
	}
	
	public SecRoleUser(String user_iidd, String role_id) {
		super();
		this.user_iidd = user_iidd;
		this.role_id = role_id;
	}
	
	public SecRoleUser(String user_iidd, String role_id, String domain, String domain_table) {
		super();
		this.user_iidd = user_iidd;
		this.role_id = role_id;
		this.domain = domain;
		this.domain_table = domain_table;
	}
	
}