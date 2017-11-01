package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecSubsys;

public class UpdateSubsysAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private SecSubsys secSubsys = null;
	private String subsys_code = "";
	private String subsys_name = "";
	private String subsys_desc = "";
	private String update = "N";
	
	public SecSubsys getSecSubsys() {
		return secSubsys;
	}
	
	public void setUpdate(String update) {
		this.update = update;
	}
	
	public String getSubsys_code() {
		return subsys_code;
	}
	
	public void setSubsys_code(String subsys_code) {
		this.subsys_code = subsys_code;
	}
	
	public void setSubsys_name(String subsys_name) {
		this.subsys_name = subsys_name;
	}
	
	public void setSubsys_desc(String subsys_desc) {
		this.subsys_desc = subsys_desc;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("Y") && !subsys_code.equalsIgnoreCase("admin")) {//处理修改操作
			secSubsys = new SecSubsys();
			secSubsys.setSubsys_code(subsys_code);
			secSubsys.setSubsys_name(subsys_name);
			secSubsys.setSubsys_desc(subsys_desc);
			userService.updateSubsys(secSubsys);
			return "update";
		} else {
			secSubsys = userService.getSubsysBySubsysCode(subsys_code);
		}
		return SUCCESS;
	}
}
