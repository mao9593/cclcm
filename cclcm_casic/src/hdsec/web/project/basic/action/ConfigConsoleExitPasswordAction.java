package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysConfigItem;

/**
 * 修改密级信息
 * 
 * @author renmingfei
 * 
 */
public class ConfigConsoleExitPasswordAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String update = "N";
	private String password = "";

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	@Override
	public String executeFunction() throws Exception {
		SysConfigItem item_console_exit_password = basicService
				.getSysConfigItemValue(SysConfigItem.KEY_CONSOLE_EXIT_PASSWORD);

		SysConfigItem sysconfig = new SysConfigItem(SysConfigItem.KEY_CONSOLE_EXIT_PASSWORD,
				SysConfigItem.NAME_CONSOLE_EXIT_PASSWORD, password.toString(), SysConfigItem.TYPE_SYSTEM_CONFIG, 1);

		if (item_console_exit_password == null) {
			basicService.addSysConfigItem(sysconfig);
		}

		if (update.equalsIgnoreCase("Y")) {// 处理修改操作
			basicService.updateSysConfigItem(sysconfig);
			return "update";
		} else {
			SysConfigItem item_value_temp = basicService.getSysConfigItemValue(SysConfigItem.KEY_CONSOLE_EXIT_PASSWORD);
			setPassword(item_value_temp.getItem_value());
		}
		return SUCCESS;
	}
}
