package hdsec.web.project.webservice.action;

import hdsec.web.project.basic.model.ClientMsg;
import hdsec.web.project.client.model.PendingWorkItem;
import hdsec.web.project.client.service.ClientService;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class CheckUrlAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private UserService userService;
	@Resource
	protected ClientService clientService;
	private SecUser user = null;
	private List<ClientMsg> cmsList = new ArrayList<ClientMsg>();
	private List<PendingWorkItem> waitTodoList = new ArrayList<PendingWorkItem>();
	private String PID = "";
	private String operType = "";
	private String url = "";

	public String getUrl() {
		return url;
	}

	public String getPID() {
		return PID;
	}

	public void setPID(String pID) {
		PID = pID;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public UserService getUserService() {
		return userService;
	}

	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 把当前客户端的用户信息存入session 如果session中存在用户，并且与当前用户相同，则不做处理，否则把session中的用户信息更新为当前用户 2014-4-14 下午4:03:51
	 * 
	 * @author renmingfei
	 * @param user_iidd
	 * @throws Exception
	 */
	private void putUserIntoSession(String idcard) throws Exception {
		HttpSession session = getRequest().getSession(true);
		user = (SecUser) session.getAttribute(Constants.SESSION_USER_KEY);
		if (user != null && user.getIdCard().equals(idcard)) {
			logger.debug("user idcard [" + idcard + "] already in session");
		} else {
			user = userService.getSecUserByIdcard(idcard);
			if (user == null) {
				throw new Exception("---Idcard[" + idcard + "] does not exist!---");
			} else {
				user.setNeed_checkPwd(false);
				// 把用户信息写进session
				session.setAttribute(Constants.SESSION_USER_KEY, user);
				// 把登录时间写进session
				session.setAttribute(Constants.LOGON_TIME, new Date());
				// 把登录IP写进session
				session.setAttribute(Constants.LOGON_IP, getRequest().getRemoteAddr());
				// 把用户的所有操作列表写进user
				List<String> allOperUrl = userService.getAllOperByUserOnly(user.getUser_iidd());
				user.setAllOper(allOperUrl);
				// 把用户没有的操作列表写进user
				List<String> nonOperUrl = userService.getNonOperByUserOnly(user.getUser_iidd());
				user.setNonOper(nonOperUrl);
			}
		}
	}

	@Override
	public String execute() {
		try {
			putUserIntoSession(PID);
			user = userService.getSecUserByIdcard(PID);
			logger.info("--------PID=" + PID);
			logger.info("--------operType=" + operType);
			String permission = "burn/managenasburnevent.action";
			boolean nasFlag = user.hasPermission(permission);
			waitTodoList = clientService.getLeaderWorkList2(user.getUser_iidd(), nasFlag, operType);
			if (waitTodoList.size() > 0) {
				for (int i = 0; i < waitTodoList.size(); i++) {
					PendingWorkItem pwi = waitTodoList.get(i);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("accept_user_iidd", user.getUser_iidd());
					map.put("oper_type", operType);
					cmsList = clientService.getClientMessage(map);
					if (cmsList.size() > 0) {
						for (int j = 0; j < cmsList.size(); j++) {
							url = "/" + pwi.getItem_url();
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.info("---------url=" + url);
		if (url != null && url.length() > 0) {
			return SUCCESS;
		} else {
			return "isnull";
		}
	}
}
