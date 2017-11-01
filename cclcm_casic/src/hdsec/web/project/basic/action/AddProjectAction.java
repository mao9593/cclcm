package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysProject;

import java.util.Date;

/**
 * 添加项目
 * @author renmingfei
 *
 */
public class AddProjectAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String project_code = "";
	private String project_name = "";
	private String project_content = "";
	private Date start_time = null;
	private Date end_time = null;
	
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
	
	@Override
	public String executeFunction() throws Exception {
		if (project_code.isEmpty()) {
			return SUCCESS;
		} else {
			if (basicService.checkProject(project_code, project_name)) {
				throw new Exception("项目名称或代号已经存在！");
			}
			SysProject project = new SysProject(project_code, project_name, project_content, start_time, end_time);
			basicService.addSysProject(project);
			insertAdminLog("添加项目：代号[" + project_code + "],名称[" + project_name + "]");
			return "ok";
		}
	}
}
