package hdsec.web.project.user.action;

/**
 * hdsec的用户管理与eoms不同，hdsec不区分资源相关，默认用户管理可以看到所有部门。
 * 本部门领导也不具备修改本部门用户的权限。
 * @author renmingfei
 *
 */
public class ManageSecUserAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	
	public String getContext() {
		return "user/viewuserbydept.action";
	}
	
	@Override
	public String executeFunction() {
		//TODO:鉴权
		return SUCCESS;
	}
	
}
