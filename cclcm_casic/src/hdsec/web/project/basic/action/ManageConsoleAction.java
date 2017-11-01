package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysConsole;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制台管理列表
 * 
 * @author yueying
 * 
 */
public class ManageConsoleAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private List<SysConsole> consoleList = null;
	private String console_code = "";
	private String console_name = "";
	private String seclv_code;
	private String is_sealed = "N";

	public List<SysConsole> getConsoleList() {
		return consoleList;
	}

	public String getConsole_code() {
		return console_code;
	}

	public void setConsole_code(String console_code) {
		this.console_code = console_code.trim();
	}

	public String getConsole_name() {
		return console_name;
	}

	public void setConsole_name(String console_name) {
		this.console_name = console_name.trim();
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public String getIs_sealed() {
		return is_sealed;
	}

	public void setIs_sealed(String is_sealed) {
		this.is_sealed = is_sealed;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("console_code", console_code.equals("") ? "" : console_code.replace("_", "[_]"));
		map.put("console_name", console_name.equals("") ? "" : console_name.replace("_", "[_]"));
		map.put("seclv_code", seclv_code);
		map.put("is_sealed", is_sealed);
		List<SysConsole> tempConsoleList = basicService.getAllSysConsoleList(map);

		List<SysConsole> consoles = new ArrayList<SysConsole>();
		for (SysConsole console : tempConsoleList) {
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
