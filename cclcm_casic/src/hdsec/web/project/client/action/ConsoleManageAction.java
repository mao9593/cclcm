package hdsec.web.project.client.action;

import hdsec.web.project.basic.model.SysConsole;
import hdsec.web.project.common.util.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制台列表
 * 
 * @author congxue 2015-7-13
 */
public class ConsoleManageAction extends ClientBaseAction {
	private static final long serialVersionUID = 1L;
	private List<SysConsole> consoleList = null;
	private String console_code = "";
	private String console_name = "";

	public List<SysConsole> getConsoleList() {
		return consoleList;
	}

	public void setConsoleList(List<SysConsole> consoleList) {
		this.consoleList = consoleList;
	}

	public String getConsole_code() {
		return console_code;
	}

	public void setConsole_code(String console_code) {
		this.console_code = console_code;
	}

	public String getConsole_name() {
		return console_name;
	}

	public void setConsole_name(String console_name) {
		this.console_name = console_name;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("console_code", console_code);
		map.put("console_name", console_name);
		map.put("is_sealed", "N");
		// cVSList = clientService.getCVSListByCondition(map);
		consoleList = basicService.getAllSysConsoleList(map);
		List<SysConsole> consoles = new ArrayList<SysConsole>();
		for (SysConsole console : consoleList) {
			String seclv_name = userService.getSecLevelByCode(console.getSeclv_code()).getSeclv_name();
			console.setSeclv_name(seclv_name);
			console.setConsole_type_name(changeConsoleTypeName(console.getConsole_type()));
			consoles.add(console);
		}
		consoleList = consoles;
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
