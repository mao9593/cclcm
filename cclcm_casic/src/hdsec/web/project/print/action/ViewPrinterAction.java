package hdsec.web.project.print.action;

import hdsec.web.project.print.model.SysPrinter;
import hdsec.web.project.print.service.PrintService;
import hdsec.web.project.user.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * 查询打印机列表
 * @author renmingfei
 *
 */
public class ViewPrinterAction extends PrintBaseAction {
	private static final long serialVersionUID = 1L;
	@Resource
	private PrintService printService;
	private List<SysPrinter> sysPrinterList;
	private String printer_name = "";
	
	public String getPrinter_name() {
		return printer_name;
	}
	
	public void setPrinter_name(String printer_name) {
		this.printer_name = printer_name;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public List<SysPrinter> getSysPrinterList() {
		return sysPrinterList;
	}
	
	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("printer_name", printer_name);
		sysPrinterList = printService.getPrinterList(map);
		return SUCCESS;
	}
	
}
