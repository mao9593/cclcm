package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysPrinter;
import hdsec.web.project.user.model.SecLevel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 打印机列表
 * 
 * @author gaoxm
 * 
 */

public class ManagePrinterAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private List<SysPrinter> printerList = null;
	private String printer_name = "";
	private String seclv_code;
	private String console_code = "";
	private String console_name = "";

	public List<SysPrinter> getPrinterList() {
		return printerList;
	}

	public String getPrinter_name() {
		return printer_name;
	}

	public void setPrinter_name(String printer_name) {
		this.printer_name = printer_name.trim();
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

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("printer_name", printer_name.equals("") ? "" : printer_name.replace("_", "[_]"));
		map.put("seclv_code", seclv_code);
		map.put("console_code", console_code.equals("") ? "" : console_code.replace("_", "[_]"));
		map.put("console_name", console_name.equals("") ? "" : console_name.replace("_", "[_]"));
		printerList = basicService.getSysPrinterList(map);
		return SUCCESS;

	}
}
