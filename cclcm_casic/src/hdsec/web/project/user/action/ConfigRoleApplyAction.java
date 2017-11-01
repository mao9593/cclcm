package hdsec.web.project.user.action;

import hdsec.web.project.common.util.Constants;
import hdsec.web.project.user.session.SessionACL;
import hdsec.web.project.user.session.SessionOper;

public class ConfigRoleApplyAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private String addOperCodes = "";
	private String delOperCodes = "";
	
	public String getAddOperCodes() {
		return addOperCodes;
	}
	
	public void setAddOperCodes(String addOperCodes) {
		this.addOperCodes = addOperCodes;
	}
	
	public String getDelOperCodes() {
		return delOperCodes;
	}
	
	public void setDelOperCodes(String delOperCodes) {
		this.delOperCodes = delOperCodes;
	}
	
	@Override
	public String executeFunction() throws Exception {
		SessionACL sAcl = (SessionACL) getRequest().getSession().getAttribute(Constants.SESSION_ACL_KEY);
		String[] operCodeArray = addOperCodes.split(SessionOper.OPER_SEPARATOR);
		for (int i = 0, n = operCodeArray.length; i < n; i++) {
			if (!operCodeArray[i].equals("")) {
				sAcl.authorityOper(operCodeArray[i]);//授权操作
			}
		}
		operCodeArray = delOperCodes.split(SessionOper.OPER_SEPARATOR);
		for (int i = 0, n = operCodeArray.length; i < n; i++) {
			if (!operCodeArray[i].equals("")) {
				sAcl.unAuthorityOper(operCodeArray[i]);//取消授权
			}
		}
		getRequest().setAttribute(Constants.XML_HTTP_RESULT_KEY, new Boolean(true));
		return SUCCESS;
	}
}
