package hdsec.web.project.ledger.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ConfirmSuperviseAction extends LedgerBaseAction {

	private static final long serialVersionUID = 1L;
	private String event_code;
	private String type;
	private String barcode;
	private String result;
	private String comment = "";
	private String cur_user_iidd = "";

	public String getCur_user_iidd() {
		return cur_user_iidd;
	}

	public void setCur_user_iidd(String cur_user_iidd) {
		this.cur_user_iidd = cur_user_iidd;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String executeFunction() throws Exception {
		if ((type != null && !type.equals(""))) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("barcode", barcode);
			map.put("supervise_user_iidd", cur_user_iidd);
			map.put("event_code", event_code);
			map.put("destroy_time", new Date());
			map.put("comment", comment);
			if (type.equals("CD")) {
				map.put("cd_state", 4);
				ledgerService.updateSuperviseCDStateByBarcode(map);

			} else if (type.equals("PAPER")) {
				map.put("paper_state", 4);
				ledgerService.updateSupervisePaperStateByBarcode(map);

			} else if (type.equals("DEVICE")) {
				map.put("device_status", 5);
				ledgerService.updateSuperviseDeviceStateByBarcode(map);
			}
			insertCommonLog("确认监销载体[" + barcode + "]");
			result = "done";
		}
		return SUCCESS;
	}
}
