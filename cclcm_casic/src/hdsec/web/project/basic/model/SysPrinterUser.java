package hdsec.web.project.basic.model;

import hdsec.web.project.common.BaseDomain;

/**
 * 配置打印机--用户
 * @author yy
 *
 */

public class SysPrinterUser extends BaseDomain {
	private String user_iidd = "";//用户ID
	private String user_name = "";//用户姓名
	private String printer_code = "";//打印机ID
	private String printer_name = "";//打印机名称
	
	public String getPrinter_code() {
		return printer_code;
	}
	
	public void setPrinter_code(String printer_code) {
		this.printer_code = printer_code;
	}
	
	public String getPrinter_name() {
		return printer_name;
	}
	
	public void setPrinter_name(String printer_name) {
		this.printer_name = printer_name;
	}
	
	public String getUser_iidd() {
		return user_iidd;
	}
	
	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}
	
	public String getUser_name() {
		return user_name;
	}
	
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
}
