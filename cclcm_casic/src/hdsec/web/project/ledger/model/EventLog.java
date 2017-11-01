package hdsec.web.project.ledger.model;

import java.util.Date;

import hdsec.web.project.common.BaseDomain;

public class EventLog extends BaseDomain {
	private int id;
	private String user_iidd;// 制作人ID
	private String event_code;
	private Date unlock_time;
	private String console_code;
	private String event_type;
	private String user_name;// 制作人姓名
	private String dept_name;// 制作人部门名称
	private String console_name;
	private String file_title;
	

	public EventLog(String user_iidd, String event_code, Date unlock_time,
			String console_code, String event_type, String user_name,
			String dept_name, String console_name, String file_title) {
		super();
		this.user_iidd = user_iidd;
		this.event_code = event_code;
		this.unlock_time = unlock_time;
		this.console_code = console_code;
		this.event_type = event_type;
		this.user_name = user_name;
		this.dept_name = dept_name;
		this.console_name = console_name;
		this.file_title = file_title;
	}

	public String getFile_title() {
		return file_title;
	}

	public void setFile_title(String file_title) {
		this.file_title = file_title;
	}

	public EventLog() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public Date getUnlock_time() {
		return unlock_time;
	}

	public String getUnlock_time_name() {
		if (null == unlock_time) {
			return "";
		}
		return getSdf().format(unlock_time);
	}

	public void setUnlock_time(Date unlock_time) {
		this.unlock_time = unlock_time;
	}

	public String getConsole_code() {
		return console_code;
	}

	public void setConsole_code(String console_code) {
		this.console_code = console_code;
	}

	public String getEvent_type() {
		return event_type;
	}

	public void setEvent_type(String event_type) {
		this.event_type = event_type;
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

	public String getConsole_name() {
		return console_name;
	}

	public void setConsole_name(String console_name) {
		this.console_name = console_name;
	}

	public String getEvent_type_name() {
		if (event_type.equals("PRINT")) {
			return "打印";
		} else if (event_type.equals("BURN")) {
			return "刻录";
		} else {
			return "";
		}
	}

}
