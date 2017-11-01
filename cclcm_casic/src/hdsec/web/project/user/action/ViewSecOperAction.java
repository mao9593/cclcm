package hdsec.web.project.user.action;


/**
 * 功能管理：操作配置页面
 * @author renmingfei
 *
 */
public class ViewSecOperAction extends UserBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String oper_code = "";
	private String subsys_code = "";
	
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
	
	@Override
	public String executeFunction() {
		//TODO:鉴权
		return SUCCESS;
	}
}
