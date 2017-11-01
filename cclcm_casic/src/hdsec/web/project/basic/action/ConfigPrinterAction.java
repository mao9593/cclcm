package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysPrinter;
import hdsec.web.project.basic.model.SysPrinterGroup;
import hdsec.web.project.basic.model.SysPrinterUser;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 配置打印机--组
 * 
 * @author gaoxm
 * 
 */

public class ConfigPrinterAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String dept_id = "";// 部门ID
	private String printer_code = "";// 打印机ID
	private List<SysPrinterGroup> printerGroupList = null;
	private SysPrinter printer = null;
	private String update = "N";
	private String deptNames = "";
	private String dept_names = "";
	private String dept_ids = "";

	private String user_iidds;
	private String user_name;
	private List<SysPrinterUser> users;

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

	public String getDept_ids() {
		return dept_ids;
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
				dept_ids += item.getDept_id() + ",";
				dept_names += item.getDept_name() + ",";
			}
			if (dept_ids.endsWith(",")) {
				dept_ids = dept_ids.substring(0, dept_ids.length() - 1);
			}
			if (dept_names.endsWith(",")) {
				dept_names = dept_names.substring(0, dept_names.length() - 1);
			}
			users = basicService.getPrintUserList(printer_code, null);
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
			List<SysPrinterUser> printerUserList = basicService.getPrintUserList(printer_code, "");
			List<String> userList = new ArrayList<String>();
			// 把数据库中已经配置给当前打印机的用户存到数组
			for (SysPrinterUser printerUser : printerUserList) {
				userList.add(printerUser.getUser_iidd());
			}
			// int userNum = basicService.getUserNumByPrinter(printer_code);
			String[] users = user_iidds.split(",");
			String userDistinct = "";
			for (String user_iidd : users) {
				if (userList.contains(user_iidd)) {
					continue;
				}
				userList.add(user_iidd);
				userDistinct = userDistinct + user_iidd + ",";
			}
			// int userTotal = userNum + userList.size();
			if (userList.size() > 15) {
				throw new Exception("配置的用户总数不能超过15个");
			} else {
				basicService.addPrinterUsers(userDistinct, printer_code);
			}

		}
		insertAdminLog("配置打印机[" + printer_code + "]" + "给组[" + deptNames + "]");
		return "configprinter";
		// return "ok";
	}

	public String getUser_iidds() {
		return user_iidds;
	}

	public void setUser_iidds(String user_iidds) {
		this.user_iidds = user_iidds;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public List<SysPrinterUser> getUsers() {
		return users;
	}

	public void setUsers(List<SysPrinterUser> users) {
		this.users = users;
	}
}
