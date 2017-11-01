package hdsec.web.project.basic.action;

/**
 * 删除项目
 * @author renmingfei
 *
 */
public class DelProjectAction extends BasicBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String project_code = "";
	
	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (project_code.isEmpty()) {
			throw new Exception("参数错误，没有项目代号");
		} else {
			basicService.delProjectByCode(project_code);
			insertAdminLog("删除项目：代号[" + project_code + "]");
		}
		return SUCCESS;
	}
}
