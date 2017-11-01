package hdsec.web.project.log.model;

import hdsec.web.project.common.util.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonOperLog {
	private Integer id;
	private String user_id;
	private String user_name;
	private String dept_name;
	private String log_detail;
	private String result;
	private Date log_time;
	private Integer log_type;
	private String login_ip;
	private String login_hostname;
	private String subsys_code;
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
	
	public String getLog_time_str() {
		return log_time == null ? "" : sdf.format(log_time);
	}
	
	public void setLog_time(Date log_time) {
		this.log_time = log_time;
	}
	
	public Integer getLog_type() {
		return log_type;
	}
	
	public void setLog_type(Integer log_type) {
		this.log_type = log_type;
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
	
	public String getSubsys_code() {
		return subsys_code;
	}
	
	public void setSubsys_code(String subsys_code) {
		this.subsys_code = subsys_code;
	}
	
	public String getSubsys_str() {
		String subsys_str = "";
		switch (subsys_code.toUpperCase()) {
			case "PRINT":
				subsys_str = Constants.LOG_SUBSYS_PRINT;
				break;
			case "BURN":
				subsys_str = Constants.LOG_SUBSYS_BURN;
				break;
			case "NAS":
				subsys_str = Constants.LOG_SUBSYS_NAS;
				break;
			case "NAD":
				subsys_str = Constants.LOG_SUBSYS_NAD;
				break;
			case "ADMIN":
				subsys_str = Constants.LOG_SUBSYS_ADMIN;
				break;
		}
		return subsys_str;
	}
	
	public CommonOperLog(String user_id, String user_name, String dept_name, String log_detail, String result,
			Date log_time, Integer log_type, String login_ip, String login_hostname, String subsys_code) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.dept_name = dept_name;
		this.log_detail = log_detail;
		this.result = result;
		this.log_time = log_time;
		this.log_type = log_type;
		this.login_ip = login_ip;
		this.login_hostname = login_hostname;
		this.subsys_code = subsys_code;
	}
	
	public CommonOperLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
