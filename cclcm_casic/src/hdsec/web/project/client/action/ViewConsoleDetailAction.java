package hdsec.web.project.client.action;

import hdsec.web.project.basic.model.SysConsole;
import hdsec.web.project.common.util.Constants;

/**
 * 控制台查看详情
 * 
 * @author congxue 2015-7-15
 */
public class ViewConsoleDetailAction extends ClientBaseAction {
	private static final long serialVersionUID = 1L;
	private String console_code = "";// 编号
	private SysConsole sysConsole = null;

	public String getConsole_code() {
		return console_code;
	}

	public void setConsole_code(String console_code) {
		this.console_code = console_code;
	}

	public SysConsole getSysConsole() {
		return sysConsole;
	}

	public void setSysConsole(SysConsole sysConsole) {
		this.sysConsole = sysConsole;
	}

	@Override
	public String executeFunction() throws Exception {
		sysConsole = clientService.getConsoleByCode(console_code);
		String seclv_name = userService.getSecLevelByCode(sysConsole.getSeclv_code()).getSeclv_name();
		sysConsole.setSeclv_name(seclv_name);
		sysConsole.setConsole_type_name(changeConsoleTypeName(sysConsole.getConsole_type()));
		return SUCCESS;
	}

	private String changeConsoleTypeName(String consoleType) {
		StringBuffer sb = new StringBuffer();
		String types[] = consoleType.split(Constants.COMMON_SEPARATOR_REGEX);
		for (String type : types) {
			if ("PRINT".equals(type)) {
				sb.append(replaceNull("打印")).append(" | ");
			}
			if ("BURN".equals(type)) {
				sb.append(replaceNull("刻录")).append(" | ");
			}
			if ("TRANSFER".equals(type)) {
				sb.append(replaceNull("流转")).append(" | ");
			}
			if ("DEVICE_BR".equals(type)) {
				sb.append(replaceNull("借用磁介质")).append(" | ");
			}
			if ("DEVICE_RT".equals(type)) {
				sb.append(replaceNull("归还磁介质")).append(" | ");
			}
			if ("READ_BR".equals(type)) {
				sb.append(replaceNull("借阅文件")).append(" | ");
			}
			if ("READ_RT".equals(type)) {
				sb.append(replaceNull("归还文件")).append(" | ");
			}
			if ("FILE".equals(type)) {
				sb.append(replaceNull("归档")).append(" | ");
			}
		}
		return sb.toString();
	}

	private String replaceNull(String str) {
		if (null == str) {
			return "";
		}
		return str;
	}

}
