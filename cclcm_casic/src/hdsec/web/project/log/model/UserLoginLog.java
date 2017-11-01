package hdsec.web.project.log.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserLoginLog {
	private Integer id;
	private String user_id;
	private String user_name;
	private String dept_name;
	private String log_detail;
	private String result;
	private Date log_time;
	private String login_ip;
	private String login_hostname;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUser_id() {
		return user_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
	
	public String getLog_detail() {
		return log_detail;
	}
	
	public void setLog_detail(String log_detail) {
		this.log_detail = log_detail;
	}
	
	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	public Date getLog_time() {
		return log_time;
	}
	
	public void setLog_time(Date log_time) {
		this.log_time = log_time;
	}
	
	public String getLogin_ip() {
		return login_ip;
	}
	
	public void setLogin_ip(String login_ip) {
		this.login_ip = login_ip;
	}
	
	public String getLogin_hostname() {
		return login_hostname;
	}
	
	public void setLogin_hostname(String login_hostname) {
		this.login_hostname = login_hostname;
	}
	
	public String getLog_time_str() {
		return log_time == null ? "" : sdf.format(log_time);
	}
	
	public UserLoginLog(String user_id, String user_name, String dept_name, String log_detail, String result,
			Date log_time, String login_ip, String login_hostname) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.dept_name = dept_name;
		this.log_detail = log_detail;
		this.result = result;
		this.log_time = log_time;
		this.login_ip = login_ip;
		this.login_hostname = login_hostname;
	}
	
	public UserLoginLog() {
		super();
	}
	
}
