package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysPrinter;
import hdsec.web.project.basic.model.SysPrinterGroup;

import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 配置打印机--组
 * @author gaoxm
 *
 */

public class ConfigPrinterGroupAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String dept_id = "";//部门ID
	private String printer_code = "";//打印机ID
	private List<SysPrinterGroup> printerGroupList = null;
	private SysPrinter printer = null;
	private String update = "N";
	private String deptNames = "";
	private String dept_names = "";
	
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
	
	public void setUpdate(String update) {
		this.update = update;
	}
	
	public SysPrinter getPrinter() {
		return printer;
	}
	
	public void setPrinter(SysPrinter printer) {
		this.printer = printer;
	}
	
	public String getDeptNames() {
		return deptNames;
	}
	
	public String getDept_names() {
		return dept_names;
	}
	
	public List<SysPrinterGroup> getPrinterGroupList() {
		return printerGroupList;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("N")) {
			printer = basicService.getPrinterByCode(printer_code);
			printerGroupList = basicService.getPrinterGroupList(printer_code, null);
			for (SysPrinterGroup item : printerGroupList) {
				dept_names += item.getDept_name() + ",";
			}
			if (dept_names.endsWith(",")) {
				dept_names = dept_names.substring(0, dept_names.length() - 1);
			}
			return SUCCESS;
		} else {
			basicService.updatePrinterGroup(printer_code, dept_id);
			for (String deptid : dept_id.split(",")) {
				if (StringUtils.hasLength(dept_id.trim())) {
					deptNames += userService.getDeptNameByDeptId(deptid);
				}
			}
			if (deptNames.endsWith(",")) {
				deptNames = deptNames.substring(0, deptNames.length() - 1);
			}
		}
		insertAdminLog("配置打印机[" + printer_code + "]" + "给组[" + deptNames + "]");
		return "ok";
	}
}
