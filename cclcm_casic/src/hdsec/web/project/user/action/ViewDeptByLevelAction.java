package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecDept;

import java.util.List;

public class ViewDeptByLevelAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private Integer dept_level_code = null;
	private List<SecDept> deptList = null;
	
	public List<SecDept> getDeptList() {
		return deptList;
	}
	
	public void setDept_level_code(Integer dept_level_code) {
		this.dept_level_code = dept_level_code;
	}
	
	@Override
	public String executeFunction() throws Exception {
		deptList = userService.getSecDeptByLevel(dept_level_code);
		return SUCCESS;
	}
}
