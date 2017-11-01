package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecDeptType;

/**
 * 删除机构类型
 * @author renmingfei
 *
 */
public class DelDeptTypeAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private String dept_type_code = null;
	
	public void setDept_type_code(String dept_type_code) {
		this.dept_type_code = dept_type_code;
	}
	
	@Override
	public String executeFunction() throws Exception {
		SecDeptType sdt = userService.getDeptTypeByCode(dept_type_code);
		if (sdt != null && sdt.getDept_count() > 0) {
			throw new Exception("机构类型在用，无法删除");
		} else {
			String dept_type_name = userService.getDeptTypeByCode(dept_type_code).getDept_type_name();
			userService.delDeptTypeByCode(dept_type_code);
			insertAdminLog("删除机构类型:" + dept_type_name);
		}
		return SUCCESS;
	}
}
