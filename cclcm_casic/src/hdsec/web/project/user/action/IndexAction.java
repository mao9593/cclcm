package hdsec.web.project.user.action;

import hdsec.web.project.common.menu.MenuButton;
import hdsec.web.project.common.menu.MenuConfig;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.MD5;
import hdsec.web.project.user.model.ConfigItem;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.service.UserService;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.casic.panelbar.PanelBar;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 登录成功后，自动跳转的action，查询用户权限，返回导航页
 * 
 * @author renmingfei
 * 
 */
public class IndexAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Resource
	private UserService userService;
	private PanelBar panelBar = null;
	private MenuConfig menuConfig = null;
	private int status = 0;
	private int menuId = 0;
	private SecUser user = null;
	private String errorMsg = "";
	private String loginPrompt = "";
	private String alarmText = "";
	private String system_version = "";

	// private boolean firstLogin = true;// 标示当前动作是登录还是点击了一级目录。true表示登录

	public String getErrorMsg() {
		return errorMsg;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		// this.firstLogin = false;
		this.menuId = menuId;
	}

	public SecUser getUser() {
		return user;
	}

	public String getSystem_version() {
		return system_version;
	}

	public String getLoginPrompt() {
		// 如果是系统管理员
		if (user.is_sysAdmin()) {
			// 判断是否需要提示日志存储空间已满
			ConfigItem item_space_full = userService.getConfigItemValue(ConfigItem.KEY_SPACE_FULL);
			int space_full = Integer.parseInt(item_space_full.getItem_value());
			if (space_full == 1) {
				loginPrompt = "审计日志磁盘存储空间已超过预设阀值。";
			}
			userService.updateConfigItem(new ConfigItem(ConfigItem.KEY_SPACE_FULL, "0", 1));
			return loginPrompt;
		} else {
			return "";
		}
	}

	public String getAlarmText() {
		// 如果是系统管理员
		if (user.is_sysAdmin()) {
			// 判断是否需要提示日志存储空间将满
			ConfigItem item_space_used = userService.getConfigItemValue(ConfigItem.KEY_SPACE_USED);
			float space_used = Float.parseFloat(item_space_used.getItem_value());
			ConfigItem item_alarm_percent = userService.getConfigItemValue(ConfigItem.KEY_ALARM_PERCENT);
			float alarm_percent = Float.parseFloat(item_alarm_percent.getItem_value());
			if (space_used >= alarm_percent) {
				alarmText = "磁盘存储空间将满，请及时清理";
			}
			return alarmText;
		} else {
			return "";
		}
	}

	@Override
	public String execute() {
		status = 1;
		HttpServletRequest request = ServletActionContext.getRequest();
		user = (SecUser) request.getSession().getAttribute(Constants.SESSION_USER_KEY);
		if (user == null) {
			return ERROR;
		}
		// 判断是否需要修改密码
		if (!user.getUser_iidd().equals("admin") && user.isNeed_checkPwd()) {
			ConfigItem item_valid_days = userService.getConfigItemValue(ConfigItem.KEY_VALID_DAYS);
			if (item_valid_days.getStartuse() == 1) {// 如果启用了密码有效期的检查
				int valid_days = Integer.parseInt(item_valid_days.getItem_value());
				Integer days = userService.getPwdUpdateDays(user.getUser_iidd());
				if (days == null || days > valid_days) {
					return "changepwd";
				}
			}
		}
		// add by wx 20150501 如果用户登录密码和默认密码一致，强制用户进行密码修改
		if (!user.is_admin() && user.isNeed_checkPwd()) {
			String default_password = userService.getDefaultPwd("DEFAULT_PASSWORD");
			String default_password_md5;
			try {
				// 获取用户默认密码值
				default_password_md5 = MD5.getMD5Str(default_password);
			} catch (Exception e) {
				errorMsg = "MD5 加密接口处理异常";
				return "noOper";// 无任何操作权限
			}

			// 获取当前用户MD5加密值
			String strCurUserPwd = userService.getCurUserPwd(user.getUser_iidd());
			// 和当前用户的MD5密码值进行对比
			if (default_password_md5.equals(strCurUserPwd)) {
				return "changepwd";
			}
		}
		// above
		// SecUser secUser = (SecUser)
		// request.getSession().getAttribute("secUser");
		Collection<String> operCodes = userService.getAllOperCodeByUserId(user.getUser_iidd());
		menuConfig = MenuConfig.getInstance(request, operCodes, userService);
		if (menuConfig != null) {
			try {
				PanelBar pb = null;
				/*
				 * 自动定位到一级目录中的第二项 if ((menuConfig.getMenus() != null) &&
				 * (menuConfig.getMenus().size() > 1) && firstLogin) { menuId =
				 * 1; pb = menuConfig.getPanelBar(menuId, "mainFrame", "panel_",
				 * request); }
				 */
				pb = menuConfig.getPanelBar(menuId, "mainFrame", "panel_", request);
				if (pb != null) {
					panelBar = pb;
				} else {
					errorMsg = "无任何权限，请联系管理员！";
					return "noOper";// 无任何操作权限
				}
			} catch (Exception e) {
				errorMsg = e.getMessage();
				return "noOper";
			}
		} else {
			errorMsg = "没有任何权限，请联系管理员！";
			return "noOper";
		}
		// 查询当前系统版本号
		// ConfigItem item_system_version =
		// userService.getConfigItemValue(ConfigItem.KEY_SYSTEM_VERSION);
		// system_version = item_system_version.getItem_value();
		return SUCCESS;
	}

	public String getSpecCSS() throws Exception {
		return panelBar.GenerateStyleSheet();
	}

	public String getSpecScript() throws Exception {
		return panelBar.GenerateJavaScript(0);
	}

	public String getMenuPanel() throws Exception {
		return panelBar.GeneratePanelBar(0);
	}

	public List<MenuButton> getMenus() {
		return menuConfig.getMenus();
	}
}
