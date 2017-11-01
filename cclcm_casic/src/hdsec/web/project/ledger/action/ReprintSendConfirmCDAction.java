package hdsec.web.project.ledger.action;

import java.util.HashMap;
import java.util.Map;

public class ReprintSendConfirmCDAction extends LedgerBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String cd_barcode = "";
	public String getCd_barcode() {
		return cd_barcode;
	}
	public void setCd_barcode(String cd_barcode) {
		this.cd_barcode = cd_barcode;
	}
	public String executeFunction() throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cd_barcode", cd_barcode);
		ledgerService.reprintsendconfirmcd(map);
		return SUCCESS;
	}
}
