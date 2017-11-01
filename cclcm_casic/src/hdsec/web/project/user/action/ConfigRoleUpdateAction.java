package hdsec.web.project.user.action;

import hdsec.web.project.common.util.Constants;
import hdsec.web.project.user.session.SessionACL;

/**
 * 说明: 更新访问控制数据表中配置数据。
 * @author renmingfei
 *
 */
public class ConfigRoleUpdateAction extends UserBaseAction {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public String executeFunction() throws Exception {
		SessionACL sAcl = (SessionACL) getRequest().getSession().getAttribute(Constants.SESSION_ACL_KEY);
		int count = userService.updateRoleOper(sAcl.getSubSysCode(), sAcl.getRoleID(), sAcl.getModifiedOperCodeList(),
				sAcl.getAclList());
		getRequest().setAttribute(Constants.XML_HTTP_RESULT_KEY, Boolean.valueOf(count >= 0));
		return SUCCESS;
	}
	
}
