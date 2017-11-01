package hdsec.web.project.basic.model;

import hdsec.web.project.common.BaseDomain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SysProject extends BaseDomain {
	private String project_code = "";
	private String project_name = "";
	private String project_content = "";
	private Date start_time = null;
	private Date end_time = null;
	
	public String getProject_code() {
		return project_code;
	}
	
	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}
	
	public String getProject_name() {
		return project_name;
	}
	
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	
	public String getProject_content() {
		return project_content;
	}
	
	public void setProject_content(String project_content) {
		this.project_content = project_content;
	}
	
	public Date getStart_time() {
		return start_time;
	}
	
	public String getStart_time_str() {
		return start_time == null ? "" : getSdf().format(start_time);
	}
	
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	
	public Date getEnd_time() {
		return end_time;
	}
	
	public String getEnd_time_str() {
		return end_time == null ? "" : getSdf().format(end_time);
	}
	
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	
	public SysProject() {
		super(new SimpleDateFormat("yyyy-MM-dd"));
	}
	
	public SysProject(String project_code, String project_name, String project_content, Date start_time, Date end_time) {
		super(new SimpleDateFormat("yyyy-MM-dd"));
		this.project_code = project_code;
		this.project_name = project_name;
		this.project_content = project_content;
		this.start_time = start_time;
		this.end_time = end_time;
	}
	
	public SysProject(String project_code, String project_name, String project_content) {
		super(new SimpleDateFormat("yyyy-MM-dd"));
		this.project_code = project_code;
		this.project_name = project_name;
		this.project_content = project_content;
	}
	
}
