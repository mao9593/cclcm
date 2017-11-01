package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecSubsys;

/**
 * 删除子系统
 * @author renmingfei
 *
 */
public class DelSubsysAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private String subsys_code = "";
	
	public void setSubsys_code(String subsys_code) {
		this.subsys_code = subsys_code;
	}
	
	@Override
	public String executeFunction() throws Exception {
		SecSubsys subsys = userService.getSubsysBySubsysCode(subsys_code);
		if (subsys != null && (subsys.getDept_count() > 0 || subsys.getRole_count() > 0 || subsys.getOper_count() > 1)) {
			//TODO: deal exception
		} else if (subsys_code.equalsIgnoreCase("admin")) {
			//TODO: deal exception  admin 不允许删
		} else {
			userService.delSubsysByCode(subsys_code);
		}
		return SUCCESS;
	}
}
