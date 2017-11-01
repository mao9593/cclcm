package hdsec.web.project.ledger.action;

import org.springframework.util.StringUtils;

/**
 * 撤销光盘闭环申请
 * 
 * @author lixiang
 * 
 */
public class GiveUpHandleCDAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private String cd_id = "";
	private String type = "";
	private String device_barcode = "";

	public String getDevice_barcode() {
		return device_barcode;
	}

	public void setDevice_barcode(String device_barcode) {
		this.device_barcode = device_barcode;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setCd_id(String cd_id) {
		this.cd_id = cd_id;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(cd_id)) {
			ledgerService.giveUpHandleCDById(cd_id);
			insertCommonLog("撤销光盘闭环申请[" + cd_id + "]");
		}
		if (StringUtils.hasLength(device_barcode)) {
			ledgerService.giveUpHandleDeviceByBarcode(device_barcode);
			insertCommonLog("撤销磁介质闭环申请[" + device_barcode + "]");
		}

		return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
	}
}
