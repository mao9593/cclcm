package hdsec.web.project.ledger.action;

public class ViewDeptPaperLedgerLoaderAction extends LedgerBaseAction {
	private String queryString;

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	@Override
	public String executeFunction() throws Exception {
		String urlString = (getRequest().getRequestURL() + "").replaceAll("loader", "")
				+ "?jumpLoader=N"
				+ (getRequest().getQueryString() == null || getRequest().getQueryString() == "" ? "" : "&"
						+ getRequest().getQueryString());
		setQueryString(urlString);
		return SUCCESS;
	}

}
