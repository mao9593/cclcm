package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysConsole;
import hdsec.web.project.basic.model.SysPrinter;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.List;

/**
 * 修改打印机信息
 * 
 * @author gaoxm
 * 
 */
public class UpdatePrinterAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String printer_code = "";// 打印机名称
	private String printer_name = "";// 打印机名称
	private String printer_path = "";// 打印机路径
	private String printer_type = "";// 打印机类型（网打等）
	private String printer_brand = "";// 品牌
	private String printer_model = "";// 型号
	private String printer_color = "";// 彩打-黑白
	private String dept_id = "";// 所属部门编号外键
	private String printer_ipaddr = "";// 网络打印机IP
	private String printer_location = "";// 打印机位置（所属办公室）
	private String is_double = "";// 是否支持双面
	private String console_code = "";// 控制台编号（外键）
	private Integer seclv_code = null;// 打印机密级
	private SysPrinter printer = null;
	private String update = "N";
	
	public SysPrinter getPrinter() {
		return printer;
	}
	
	public void setPrinter_code(String printer_code) {
		this.printer_code = printer_code;
	}
	
	public void setPrinter_name(String printer_name) {
		this.printer_name = printer_name;
	}
	
	public void setPrinter_path(String printer_path) {
		this.printer_path = printer_path;
	}
	
	public void setPrinter_type(String printer_type) {
		this.printer_type = printer_type;
	}
	
	public void setPrinter_brand(String printer_brand) {
		this.printer_brand = printer_brand;
	}
	
	public void setPrinter_model(String printer_model) {
		this.printer_model = printer_model;
	}
	
	public void setPrinter_color(String printer_color) {
		this.printer_color = printer_color;
	}
	
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	
	public void setPrinter_ipaddr(String printer_ipaddr) {
		this.printer_ipaddr = printer_ipaddr;
	}
	
	public void setPrinter_location(String printer_location) {
		this.printer_location = printer_location;
	}
	
	public void setIs_double(String is_double) {
		this.is_double = is_double;
	}
	
	public void setConsole_code(String console_code) {
		this.console_code = console_code;
	}
	
	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}
	
	public void setPrinter(SysPrinter printer) {
		this.printer = printer;
	}
	
	public void setUpdate(String update) {
		this.update = update;
	}
	
	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}
	
	public List<SysConsole> getConsoleList() {
		return basicService.getSysConsoleList();
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("N")) {
			printer = basicService.getPrinterByCode(printer_code);
			return SUCCESS;
		} else {
			printer = new SysPrinter(printer_code, printer_name, printer_path, printer_type, printer_brand,
					printer_model, printer_color, dept_id, printer_ipaddr, printer_location, new Date(), is_double,
					console_code, seclv_code);
			basicService.updatePrinter(printer);
			insertAdminLog("修改打印机：编号[" + printer_code + "]");
			return "ok";
		}
	}
}
