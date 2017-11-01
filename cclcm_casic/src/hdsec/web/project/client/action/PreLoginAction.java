package hdsec.web.project.client.action;

import hdsec.web.project.common.util.Constants;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.service.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.util.StringUtils;
import org.xvolks.jnative.exceptions.NativeException;
import org.xvolks.jnative.pointers.Pointer;
import org.xvolks.jnative.pointers.memory.MemoryBlockFactory;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 登录成功后，自动跳转的action，查询用户权限，返回导航页
 * 
 * @author renmingfei
 * 
 */
public class PreLoginAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final String DEST_LOGIN = "login";
	private static final String DEST_PRINT = "print";
	private static final String DEST_RETFILE = "RetFile";
	private static final String DEST_RETBURD = "RetBurd";
	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private UserService userService;

	private String erc = "";
	private SecUser user = null;
	private String errorMsg = "";
	private String user_iidd = "";
	private String dest = "login";

	public void setDest(String dest) {
		this.dest = dest;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getUser_iidd() {
		return user_iidd;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public String getErc() {
		return erc;
	}

	public void setErc(String erc) {
		this.erc = erc;
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
	private void putUserIntoSession(String user_iidd) throws Exception {
		logger.info("enter putUserIntoSession");
		HttpSession session = getRequest().getSession(false);
		if (session == null) {
			throw new Exception("No session exists!");
		}
		user = (SecUser) session.getAttribute(Constants.SESSION_USER_KEY);
		if (user != null && user.getUser_iidd().equals(user_iidd)) {
			logger.debug("user[" + user_iidd + "] already in session");
		} else {
			user = userService.getSecUserByUid(user_iidd);

			if (user == null) {
				throw new Exception("---User[" + user_iidd + "] does not exist!---");
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

	private String generateRandomCode() {
		Double rand = java.lang.Math.random();
		return (rand.toString() + "000000000000000000").substring(2, 18);
	}

	private String decryptString(String in) throws IOException {

		/*
		 * byte[] bytes = null; try { bytes = new sun.misc.BASE64Decoder().decodeBuffer(in); } catch (IOException e1) {
		 * // TODO Auto-generated catch block e1.printStackTrace(); }
		 */
		try {
			Pointer pointer = new Pointer(MemoryBlockFactory.createMemoryBlock(256));
			pointer.setMemory(new sun.misc.BASE64Decoder().decodeBuffer(in));
			return DecryptJNativeChars.decryptChars(2, 16, 16, pointer, DecryptJNativeChars.createPointer());
		} catch (IllegalAccessException | NativeException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 解密参数传输过来的随机数，与通过session取得的随机数比较，如果相同则通过验证
	 * 
	 * @author xingguiyang
	 * @return
	 * @throws IOException
	 */
	private boolean validation() throws Exception {
		logger.info("enter validation");
		HttpSession session = getRequest().getSession(false);
		if (session == null) {
			return false;
		}
		String randomCode = (String) session.getAttribute("RANDOMCODE");
		if (!StringUtils.hasLength(randomCode)) {
			return false;
		}
		if (!StringUtils.hasLength(erc)) {
			return false;
		}
		String randomCode2 = decryptString(erc.replace(' ', '+'));
		if (randomCode.equals(randomCode2)) {
			logger.warn("Validation Passed");
			session.removeAttribute("RANDOMCODE");
			return true;
		}
		return false;
	}

	/**
	 * 客户端获取随机数，供接下来的认证使用
	 */
	@Override
	public String execute() {
		try {
			if (validation()) {
				try {
					putUserIntoSession(user_iidd);
				} catch (Exception e) {
					logger.error(e.getMessage());
					e.printStackTrace();
				}
				switch (dest) {
				case DEST_LOGIN:
					return SUCCESS;
				case DEST_PRINT:
					return "print";
				case DEST_RETFILE:
					return "RetFile";
				case DEST_RETBURD:
					return "RetBurd";
				default:
					return SUCCESS;
				}
			} else {
				HttpSession session = getRequest().getSession(true);
				String randomCode = generateRandomCode();
				session.setAttribute("RANDOMCODE", randomCode);
				PrintWriter pw = null;
				try {
					pw = getResponse().getWriter();
					getResponse().setCharacterEncoding("UTF-8");
					getResponse().setContentType("text/html;charset=UTF-8");
					pw.println("<input type='hidden' name='ccc' value='" + randomCode + "'>"
							+ "<img style='margin-left:350px;margin-top:350px;' src='images/_system/hd_logo.jpg'/>");
					pw.close();
					// pw.println(randomCode);
				} catch (IOException e) {
					logger.error(e.getMessage());
					return "error";
				}
				return null;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return "error";
		}
	}

}
