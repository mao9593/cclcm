package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecSubsys;

import org.springframework.util.StringUtils;

/**
 * 添加子系统
 * @author renmingfei
 *
 */
public class AddSubsysAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private String subsys_code = "";
	private String subsys_name = "";
	private String subsys_desc = "";
	private String oper_code_prefix = "";
	
	public void setSubsys_code(String subsys_code) {
		this.subsys_code = subsys_code;
	}
	
	public void setSubsys_name(String subsys_name) {
		this.subsys_name = subsys_name;
	}
	
	public void setSubsys_desc(String subsys_desc) {
		this.subsys_desc = subsys_desc;
	}
	
	public void setOper_code_prefix(String oper_code_prefix) {
		this.oper_code_prefix = oper_code_prefix;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(subsys_code)) {
			SecSubsys secSubsys = new SecSubsys();
			secSubsys.setSubsys_code(subsys_code);
			secSubsys.setSubsys_name(subsys_name);
			secSubsys.setSubsys_desc(subsys_desc);
			secSubsys.setOper_code_prefix(oper_code_prefix);
			userService.addSubsys(secSubsys);
			return "insert";
		}
		return SUCCESS;
	}
}
