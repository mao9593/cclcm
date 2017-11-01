package hdsec.web.project.ledger.action;

import java.util.HashMap;
import java.util.Map;

public class ReprintSendConfirmAction extends LedgerBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String paper_barcode = "";
	
	public String getPaper_barcode() {
		return paper_barcode;
	}

	public void setPaper_barcode(String paper_barcode) {
		this.paper_barcode = paper_barcode;
	}

	public String executeFunction() throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paper_barcode", paper_barcode);
		ledgerService.reprintsendconfirm(map);
		return SUCCESS;
	}
}
