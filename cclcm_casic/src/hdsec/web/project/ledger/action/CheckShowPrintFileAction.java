package hdsec.web.project.ledger.action;


/**
 * 检测该密级文件是否有权限预览
 * 
 * @author lixiang
 */
public class CheckShowPrintFileAction extends LedgerBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String seclv_code = "";
	private String filestorename = "";
	private String pagecount = "";
	private String result = "";
	
	public String getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getFilestorename() {
		return filestorename;
	}

	public void setFilestorename(String filestorename) {
		this.filestorename = filestorename;
	}

	public String getPagecount() {
		return pagecount;
	}

	public void setPagecount(String pagecount) {
		this.pagecount = pagecount;
	}

	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	@Override
	public String executeFunction() throws Exception {
		Boolean is_paper_audit = ledgerService.checkShowPrintFile(seclv_code);
		result= is_paper_audit + "#" + filestorename + "#" + pagecount;
		return SUCCESS;
	}
}
