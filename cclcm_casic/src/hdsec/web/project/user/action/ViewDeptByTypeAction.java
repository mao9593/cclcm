package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecDept;

import java.util.List;

/**
 * 通过机构类型查看该类型下的机构列表
 * @author renmingfei
 *
 */
public class ViewDeptByTypeAction extends UserBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String dept_type_code = null;
	private List<SecDept> deptList = null;
	
	public List<SecDept> getDeptList() {
		return deptList;
	}
	
	public void setDept_type_code(String dept_type_code) {
		this.dept_type_code = dept_type_code;
	}
	
	@Override
	public String executeFunction() throws Exception {
		deptList = userService.getSecDeptByType(dept_type_code);
		return SUCCESS;
	}
}
