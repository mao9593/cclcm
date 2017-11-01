package hdsec.web.project.ledger.action;

import java.util.HashMap;
import java.util.Map;

/**
 * 预台帐打印标记
 * 
 * @author zp
 * 
 */
public class ExpectSignPrintFailAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private String paper_id = "";
	private String paper_barcode = "";

	public String getPaper_id() {
		return paper_id;
	}

	public void setPaper_id(String paper_id) {
		this.paper_id = paper_id;
	}

	public void setPaper_barcode(String paper_barcode) {
		this.paper_barcode = paper_barcode;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paper_id", paper_id);
		ledgerService.ExpectsignPaperfail(map);
		insertCommonLog("标记文件[" + paper_barcode + "]打印失败");
		return "ok";
	}

}
