package hdsec.web.project.ledger.action;

import java.util.HashMap;
import java.util.Map;

/**
 * 检查外带载体是否带回
 * 
 * @author fyp
 */
public class CheckOrCarryOutAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private String barcodes;
	private String result = "N";

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getBarcodes() {
		return barcodes;
	}

	public void setBarcodes(String barcodes) {
		this.barcodes = barcodes;
	}

	@Override
	public String executeFunction() throws Exception {
		String[] barcode = barcodes.split(",");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("barcodes", barcode);
		int count = ledgerService.checkOrCarryIn(map);
		if (count > 0) {
			result = "Y";
		}
		return SUCCESS;
	}

}