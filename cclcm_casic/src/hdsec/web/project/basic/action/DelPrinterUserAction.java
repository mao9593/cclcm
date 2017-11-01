package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysPrinter;
import hdsec.web.project.basic.model.SysPrinterUser;

import java.util.List;

public class DelPrinterUserAction extends BasicBaseAction {

	private static final long serialVersionUID = 1L;

	private String printer_code;
	private String user_iidd;
	private SysPrinter printer;
	private List<SysPrinterUser> users;

	@Override
	public String executeFunction() throws Exception {
		basicService.delPrinterUser(printer_code, user_iidd);
		printer = basicService.getPrinterByCode(printer_code);
		users = basicService.getPrintUserList(printer_code, null);
		// return SUCCESS;
		return "configprinter";
	}

	public String getPrinter_code() {
		return printer_code;
	}

	public void setPrinter_code(String printer_code) {
		this.printer_code = printer_code;
	}

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public SysPrinter getPrinter() {
		return printer;
	}

	public void setPrinter(SysPrinter printer) {
		this.printer = printer;
	}

	public List<SysPrinterUser> getUsers() {
		return users;
	}

	public void setUsers(List<SysPrinterUser> users) {
		this.users = users;
	}
}
