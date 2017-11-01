package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecSubsys;

import java.util.List;

/**
 * 为部门关联子系统
 * @author renmingfei
 *
 */
public class ManageDeptSubsysAction extends UserBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String dept_id = "";
	private List<SecSubsys> cfgedSubsysList = null;
	private List<SecSubsys> allSubsysList = null;
	
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	
	public List<SecSubsys> getCfgedSubsysList() {
		return cfgedSubsysList;
	}
	
	public List<SecSubsys> getAllSubsysList() {
		return allSubsysList;
	}
	
	@Override
	public String executeFunction() throws Exception {
		cfgedSubsysList = userService.getSubsysListByDeptId(dept_id);
		allSubsysList = userService.getAllSubsysAsCon();
		return SUCCESS;
	}
}
