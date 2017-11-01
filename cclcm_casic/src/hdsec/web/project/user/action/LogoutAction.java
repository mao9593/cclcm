package hdsec.web.project.user.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 注销action,把用户信息从session中删除
 * @author renmingfei
 *
 */
public class LogoutAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	@Override
	public String execute() {
		ServletActionContext.getResponse().setHeader("Pragma", "no-cache");
		ServletActionContext.getResponse().setHeader("Cache-Control", "no-cache");
		ServletActionContext.getResponse().setDateHeader("Expires", 0L);
		//销毁session,同时触发SessionTimeoutListener监听器
		ServletActionContext.getRequest().getSession().invalidate();
		return SUCCESS;
	}
	
}
