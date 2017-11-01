package hdsec.web.project.basic.action;

/**
 * 检测控制台挂载的打印机个数
 * 
 * @author gaoximin 2014-9-5
 */
public class CheckPrinterNumAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String console_code = "";
	private Integer result = 0;

	public String getConsole_code() {
		return console_code;
	}

	public void setConsole_code(String console_code) {
		this.console_code = console_code;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	@Override
	public String executeFunction() throws Exception {
		Integer num = basicService.getPrinterNumByConsole(console_code);
		setResult(num);
		return SUCCESS;
	}
}
