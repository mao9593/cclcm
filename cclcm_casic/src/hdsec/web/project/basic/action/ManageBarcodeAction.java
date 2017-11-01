package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysBarcode;
import hdsec.web.project.basic.model.SysConsole;
import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 条码设置
 * 
 * @author yy
 * 
 */

public class ManageBarcodeAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private List<SysBarcode> barcodes = new ArrayList<SysBarcode>();
	private String seclv_code;
	private String usage_code;
	private String project_code;
	private String console_code;
	
	@Override
	public String executeFunction() throws Exception {
		for (SysBarcode barcode : basicService.getAllSysBarcodes()) {
			if (StringUtils.hasLength(barcode.getSeclv_code())) {
				barcode.setSeclv_names(getNamesByCodes(barcode.getSeclv_code(), "seclv_codes"));
			}
			if (StringUtils.hasLength(barcode.getConsole_code())) {
				barcode.setConsole_names(getNamesByCodes(barcode.getConsole_code(), "console_codes"));
			}
			if (StringUtils.hasLength(barcode.getProject_code())) {
				barcode.setProject_names(getNamesByCodes(barcode.getProject_code(), "project_codes"));
			}
			if (StringUtils.hasLength(barcode.getUsage_code())) {
				barcode.setUsage_names(getNamesByCodes(barcode.getUsage_code(), "usage_codes"));
			}
			barcodes.add(barcode);
		}
		return SUCCESS;
	}
	
	private String getNamesByCodes(String input, String type) {
		StringBuffer sb = new StringBuffer();
		List<String> list = new ArrayList<String>();
		String[] codes = input.split(Constants.COMMON_SEPARATOR_REGEX);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("codes", codes);
		if (type.equals("seclv_codes")) {
			list = basicService.getSeclvNameByCodes(map);
		}
		if (type.equals("console_codes")) {
			list = basicService.getConsoleNameByCodes(map);
		}
		if (type.equals("project_codes")) {
			list = basicService.getProjectNameByCodes(map);
		}
		if (type.equals("usage_codes")) {
			list = basicService.getUsageNameByCodes(map);
		}
		for (String str : list) {
			sb.append(str).append(Constants.COMMON_SEPARATOR);
		}
		return deleteTwo(sb.toString());
	}
	
	private String deleteTwo(String str) {
		if (isNotNullOrEmpty(str)) {
			return str.substring(0, str.length() - 1);
		}
		return str;
	}
	
	private boolean isNotNullOrEmpty(String str) {
		if (null == str || "".equals(str)) {
			return false;
		}
		return true;
	}
	
	public List<SysBarcode> getBarcodes() {
		return barcodes;
	}
	
	public List<SecLevel> getSeclvs() {
		return userService.getSecLevel();
	}
	
	public List<SysUsage> getUsages() {
		return basicService.getSysUsageList();
	}
	
	public List<SysProject> getProjects() {
		return basicService.getSysProjectList();
	}
	
	public List<SysConsole> getConsoles() {
		return basicService.getSysConsoleList();
	}
	
	public String getSeclv_code() {
		return seclv_code;
	}
	
	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}
	
	public String getUsage_code() {
		return usage_code;
	}
	
	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code;
	}
	
	public String getProject_code() {
		return project_code;
	}
	
	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}
	
	public String getConsole_code() {
		return console_code;
	}
	
	public void setConsole_code(String console_code) {
		this.console_code = console_code;
	}
	
}
