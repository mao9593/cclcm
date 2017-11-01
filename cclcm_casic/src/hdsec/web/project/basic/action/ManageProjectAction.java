package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysProject;

import java.util.List;

/**
 * 项目管理列表
 * 
 * @author renmingfei
 * 
 */
public class ManageProjectAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private List<SysProject> projectList = null;
	
	public List<SysProject> getProjectList() {
		return projectList;
	}
	
	@Override
	public String executeFunction() throws Exception {
		projectList = basicService.getSysProjectList();
		return SUCCESS;
	}
}
