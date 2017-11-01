package hdsec.web.project.ledger.action;

import hdsec.web.project.ledger.model.EntityPaper;

public class ViewFailRemarkAction extends LedgerBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String barcode = "";
	private EntityPaper paper = null;
	private String tag = "";

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public EntityPaper getPaper() {
		return paper;
	}

	public void setPaper(EntityPaper paper) {
		this.paper = paper;
	}

	@Override
	public String executeFunction() throws Exception {
		paper = ledgerService.getPaperByBarcode(barcode);
		return SUCCESS;
	}

}
