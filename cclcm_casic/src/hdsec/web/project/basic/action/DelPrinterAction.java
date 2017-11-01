package hdsec.web.project.basic.action;

/**
 * 删除打印机
 * 
 * @author gaoxm
 * 
 */

public class DelPrinterAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String printer_code = "";

	public void setPrinter_code(String printer_code) {
		this.printer_code = printer_code;
	}

	@Override
	public String executeFunction() throws Exception {
		if (printer_code.isEmpty()) {
			throw new Exception("参数错误，没有打印机编号");
		} else {
			basicService.delPrinterByCode(printer_code);
			insertAdminLog("删除打印机：编号[" + printer_code + "]");
		}
		return SUCCESS;
	}
}
