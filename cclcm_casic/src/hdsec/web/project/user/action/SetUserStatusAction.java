package hdsec.web.project.user.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 更改用户状态
 * 
 * @author renmingfei
 * 
 */
public class SetUserStatusAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private String user_iidds = "";
	private String status = "";
	private String setupResult = "";

	public String getUser_iidds() {
		return user_iidds;
	}

	public void setUser_iidds(String user_iidds) {
		this.user_iidds = user_iidds;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSetupResult() {
		return setupResult;
	}

	public void setSetupResult(String setupResult) {
		this.setupResult = setupResult;
	}

	@Override
	public String executeFunction() {
		if (StringUtils.hasLength(user_iidds) && StringUtils.hasLength(status)) {
			// 设置用户状态
			Map<String, String> map = new HashMap<String, String>();
			map.put("status", status);
			String[] userIds = user_iidds.split("#");
			try {
				for (int i = 0; i < userIds.length; i++) {
					map.put("user_iidd", userIds[i]);
					userService.updateUserStatus(map);
				}

				String oper = status.equals("1") ? "禁用" : "激活";
				insertAdminLog(oper + "用户:" + user_iidds);
				setSetupResult("设置成功!");
			} catch (Exception e) {
				dealException(e);
				setSetupResult("设置出错,请重试!");
			}
		} else {
			setSetupResult("缺少参数，无法完成设置!");
		}
		return SUCCESS;
	}
}
