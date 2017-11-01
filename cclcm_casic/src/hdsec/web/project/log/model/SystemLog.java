package hdsec.web.project.log.model;

import hdsec.web.project.common.util.Constants;

import java.sql.Date;

public class SystemLog {
	private Integer id;
	private String subsys_code;
	private String subsys_name;
	private Integer source_module;
	private Integer log_type;
	private String log_detail;
	private Date log_time;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getSubsys_code() {
		return subsys_code;
	}
	
	public void setSubsys_code(String subsys_code) {
		this.subsys_code = subsys_code;
	}
	
	public String getSubsys_name() {
		return subsys_name;
	}
	
	public void setSubsys_name(String subsys_name) {
		this.subsys_name = subsys_name;
	}
	
	public Integer getSource_module() {
		return source_module;
	}
	
	public void Integer(Integer source_module) {
		this.source_module = source_module;
	}
	
	public String getSource_module_str() {
		String moduleName = "";
		switch (source_module) {
			case 1:
				moduleName = Constants.LOG_SYSTEM_SOURCE_1;
				break;
			case 2:
				moduleName = Constants.LOG_SYSTEM_SOURCE_2;
				break;
			case 3:
				moduleName = Constants.LOG_SYSTEM_SOURCE_3;
				break;
			case 4:
				moduleName = Constants.LOG_SYSTEM_SOURCE_4;
				break;
			case 5:
				moduleName = Constants.LOG_SYSTEM_SOURCE_5;
				break;
		}
		return moduleName;
	}
	
	public Integer getLog_type() {
		return log_type;
	}
	
	public void setLog_type(Integer log_type) {
		this.log_type = log_type;
	}
	
	public String getLog_type_str() {
		String typeName = "";
		switch (log_type) {
			case 1:
				typeName = Constants.LOG_SYSTEM_TYPE_1;
				break;
			case 2:
				typeName = Constants.LOG_SYSTEM_TYPE_2;
				break;
			case 3:
				typeName = Constants.LOG_SYSTEM_TYPE_3;
				break;
			case 4:
				typeName = Constants.LOG_SYSTEM_TYPE_4;
				break;
		}
		return typeName;
	}
	
	public String getLog_detail() {
		return log_detail;
	}
	
	public void setLog_detail(String log_detail) {
		this.log_detail = log_detail;
	}
	
	public Date getLog_time() {
		return log_time;
	}
	
	public void setLog_time(Date log_time) {
		this.log_time = log_time;
	}
	
	public SystemLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SystemLog(Integer id, String subsys_code, String subsys_name, Integer source_module, Integer log_type,
			String log_detail, Date log_time) {
		super();
		this.id = id;
		this.subsys_code = subsys_code;
		this.subsys_name = subsys_name;
		this.source_module = source_module;
		this.log_type = log_type;
		this.log_detail = log_detail;
		this.log_time = log_time;
	}
	
}
