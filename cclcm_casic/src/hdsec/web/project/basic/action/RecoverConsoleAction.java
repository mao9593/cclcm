package hdsec.web.project.basic.action;

/**
 * 启用已停用的控制台
 * 
 * @author lixiang
 * 
 */
public class RecoverConsoleAction extends BasicBaseAction {

	private static final long serialVersionUID = 1L;
	private String console_code;
	private String type = "";

	public void setType(String type) {
		this.type = type;
	}

	public void setConsole_code(String console_code) {
		this.console_code = console_code;
	}

	@Override
	public String executeFunction() throws Exception {
		if (console_code.isEmpty()) {
			throw new Exception("参数错误，没有控制台代号");
		} else {
			basicService.recoverConsoleByCode(console_code);
			insertAdminLog("启用控制台：代号[" + console_code + "]");
		}
		return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
	}
}
