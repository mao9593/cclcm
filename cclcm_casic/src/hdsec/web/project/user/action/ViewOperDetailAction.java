package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecOperation;

import org.springframework.util.StringUtils;

public class ViewOperDetailAction extends UserBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String oper_code = "";
	private String subsys_code = "";
	private SecOperation secOper = null;
	
	public String getOper_code() {
		return oper_code;
	}
	
	public void setOper_code(String oper_code) {
		this.oper_code = oper_code;
	}
	
	public String getSubsys_code() {
		return subsys_code;
	}
	
	public void setSubsys_code(String subsys_code) {
		this.subsys_code = subsys_code;
	}
	
	public SecOperation getSecOper() {
		return secOper;
	}
	
	@Override
	public String executeFunction() throws Exception {
		//如果操作ID和子系统编码不是都存在，则返回介绍页面
		if (!StringUtils.hasLength(oper_code) || !StringUtils.hasLength(subsys_code)) {
			return "introduce";
		}
		secOper = userService.getSecOperByCodeAndSubsys(oper_code, subsys_code);
		return SUCCESS;
	}
}
