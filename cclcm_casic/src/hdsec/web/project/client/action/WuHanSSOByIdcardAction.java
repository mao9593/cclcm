package hdsec.web.project.client.action;

import hdsec.web.project.common.util.Constants;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.service.UserService;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 登录成功后，自动跳转的action，查询用户权限，返回导航页
 * 
 * @author yy
 * 
 */
public class WuHanSSOByIdcardAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private UserService userService;
	private SecUser user = null;
	private String errorMsg = "";
	private String PID = "";
	private String sessionID;
	private String WSUrl;
	private String OperationName;
	private String verifyID;

	public String getErrorMsg() {
		return errorMsg;
	}

	public SecUser getUser() {
		return user;
	}

	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 把当前客户端的用户信息存入session
	 * 如果session中存在用户，并且与当前用户相同，则不做处理，否则把session中的用户信息更新为当前用户 2014-4-14
	 * 下午4:03:51
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
				throw new Exception("---Idcard[" + idcard
						+ "] does not exist!---");
			} else {
				user.setNeed_checkPwd(false);
				// 把用户信息写进session
				session.setAttribute(Constants.SESSION_USER_KEY, user);
				// 把登录时间写进session
				session.setAttribute(Constants.LOGON_TIME, new Date());
				// 把登录IP写进session
				session.setAttribute(Constants.LOGON_IP, getRequest()
						.getRemoteAddr());
				// 把用户的所有操作列表写进user
				List<String> allOperUrl = userService.getAllOperByUserOnly(user
						.getUser_iidd());
				user.setAllOper(allOperUrl);
				// 把用户没有的操作列表写进user
				List<String> nonOperUrl = userService.getNonOperByUserOnly(user
						.getUser_iidd());
				user.setNonOper(nonOperUrl);
			}
		}
	}

	@Override
	public String execute() {
		try {
			if (checkUserInfo()) {
				putUserIntoSession(PID);
			} else {
				throw new Exception("---个人信息验证失败，请找管理员确认---");
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			return "noOper";
		}
		return SUCCESS;
	}

	private boolean checkUserInfo() throws Exception {
		if(verifyID!=null&&verifyID.equals("1")){
			/*首先接收门户系统传递的参数*/
			Service service=new Service();
			Call call=(Call)service.createCall();
			call.setTargetEndpointAddress(WSUrl);
			call.setOperationName(OperationName);//设置操作名
			/*设置入口参数*/
			call.addParameter("bizDataXML", XMLType.XSD_STRING, ParameterMode.IN);
			call.setReturnType(XMLType.XSD_STRING);
			String projectDetails="<?xml version=\"1.0\" encoding=\"GB2312\"?><root><data><sessionID>"+sessionID+"</sessionID><msg></msg></data></root>";
			/*调用门户系统WebService服务，返回结果*/
			String[] param={projectDetails};
			Object obj=call.invoke(param);//obj是代表返回一个XML格式的串
			String msg=obj.toString();
			int m=msg.indexOf("<msg>");
			int k=m+5;
			String val=msg.substring(k,k+1);
			int value=Integer.parseInt(val,10);
			if(value==1) return true;
			else throw new Exception("参数错误");
		}else{
			throw new Exception("必须通过OA登陆！");
		}
	}

	public void setPID(String PID) {
		this.PID = PID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public void setWSUrl(String wSUrl) {
		WSUrl = wSUrl;
	}

	public String getOperationName() {
		return OperationName;
	}

	public void setOperationName(String operationName) {
		OperationName = operationName;
	}

	public String getVerifyID() {
		return verifyID;
	}

	public void setVerifyID(String verifyID) {
		this.verifyID = verifyID;
	}

}
