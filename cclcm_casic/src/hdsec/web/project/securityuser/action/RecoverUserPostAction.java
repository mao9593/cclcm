package hdsec.web.project.securityuser.action;


/**
 * 启用已停用的岗位
 * 
 * @author yangjl
 * 
 */
public class RecoverUserPostAction extends SecurityUserBaseAction {

	private static final long serialVersionUID = 1L;
	private String post_id = "";
	private String type = "";

	public void setType(String type) {
		this.type = type;
	}

	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}

	@Override
	public String executeFunction() throws Exception {
		if (post_id.isEmpty()) {
			throw new Exception("参数错误，没有岗位特征值");
		} else {
			securityUserService.recoverUserPostByCode(post_id);
			insertAdminLog("启用岗位：特征值[" + post_id + "]");
		}
		return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
	}
}
