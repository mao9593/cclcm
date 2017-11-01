package hdsec.web.project.user.action;

/**
 * 进入组织机构管理页面
 * @author renmingfei
 *
 */
public class ManageDeptAction extends UserBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String dept_id = "";
	
	public String getDept_id() {
		return dept_id;
	}
	
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	
	public String getContext() {
		return "user/configdept.action";
	}
	
	@Override
	public String executeFunction() {
		//TODO:鉴权
		return SUCCESS;
	}
	
}
