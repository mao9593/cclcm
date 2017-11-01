package hdsec.web.project.basic.model;

import hdsec.web.project.common.BaseDomain;

/**
 * 配置打印机--组
 * @author gaoxm
 *
 */

public class SysPrinterGroup extends BaseDomain {
	private String dept_id = "";//部门ID
	private String printer_code = "";//打印机ID
	private String dept_name = "";//部门ID
	private String printer_name = "";//打印机ID
	
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
	
	public String getDept_name() {
		return dept_name;
	}
	
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	
	public String getPrinter_name() {
		return printer_name;
	}
	
	public void setPrinter_name(String printer_name) {
		this.printer_name = printer_name;
	}
	
	public SysPrinterGroup() {
		super();
	}
	
	public SysPrinterGroup(String dept_id, String printer_code) {
		this.dept_id = dept_id;
		this.printer_code = printer_code;
	}
	
}
