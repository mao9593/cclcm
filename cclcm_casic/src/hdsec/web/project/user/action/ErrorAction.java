package hdsec.web.project.user.action;

import hdsec.web.project.common.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class ErrorAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	@Override
	public String execute() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession s = request.getSession();
		if (s.getAttribute(Constants.SESSION_USER_KEY) == null) {
			return "timeout";
		} else {
			return SUCCESS;
		}
	}
}
