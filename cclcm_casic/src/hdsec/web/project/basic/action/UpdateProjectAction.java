package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysProject;

import java.util.Date;

public class UpdateProjectAction extends BasicBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String project_code = "";
	private String project_name = "";
	private String project_content = "";
	private Date start_time = null;
	private Date end_time = null;
	private SysProject project = null;
	private String update = "N";
	
	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}
	
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	
	public void setProject_content(String project_content) {
		this.project_content = project_content;
	}
	
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	
	public SysProject getProject() {
		return project;
	}
	
	public void setUpdate(String update) {
		this.update = update;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("N")) {
			project = basicService.getProjectByCode(project_code);
			return SUCCESS;
		} else {
			project = new SysProject(project_code, project_name, project_content, start_time, end_time);
			basicService.updateProject(project);
			insertAdminLog("修改项目：代号[" + project_code + "],名称[" + project_name + "]");
			return "ok";
		}
	}
	
}
