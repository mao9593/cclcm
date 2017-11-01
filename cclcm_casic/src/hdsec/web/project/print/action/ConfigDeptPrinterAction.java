package hdsec.web.project.print.action;

import hdsec.web.project.basic.model.SysPrinterGroup;

import java.util.List;

/**
 * 配置部门默认打印机
 * 
 * @author lixiang
 */
public class ConfigDeptPrinterAction extends PrintBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String dept_id = "";
	private String printer_code = "";
	private String config = "N";
	
	public String getDept_id() {
		return dept_id;
	}
	
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	
	public String getPrinter_code() {
		return printer_code;
	}
	
	public void setPrinter_code(String printer_code) {
		this.printer_code = printer_code;
	}
	
	public void setConfig(String config) {
		this.config = config;
	}
	
	public String getConfig() {
		return config;
	}
	
	public String getDept_name() {
		return userService.getDeptNameByDeptId(dept_id);
	}
	
	public List<SysPrinterGroup> getPrinterGroupList() {
		return basicService.getPrinterGroupList(null, dept_id);
	}
	
	public boolean getIsEmpty() {
		List<SysPrinterGroup> list = getPrinterGroupList();
		return list == null || list.size() == 0;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (config.equalsIgnoreCase("Y")) {
			printService.configDeptPrinter(dept_id, printer_code);
			insertAdminLog("配置部门默认打印机：" + userService.getDeptNameByDeptId(dept_id));
		}
		printer_code = printService.getDeptPrinter(dept_id);
		return SUCCESS;
	}
}
