package hdsec.web.project.user.action;

/**
 * 删除机构
 * @author renmingfei
 *
 */
public class DelSecDeptAction extends UserBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String dept_id;
	
	public String getDept_id() {
		return dept_id;
	}
	
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	
	@Override
	public String executeFunction() throws Exception {
		String dept_name = userService.getDeptNameByDeptId(dept_id);
		userService.delSecDeptByIdWithSub(dept_id);
		insertAdminLog("删除部门:" + dept_name);
		return SUCCESS;
	}
}
