package hdsec.web.project.ledger.action;

import hdsec.web.project.basic.model.SysBarcode;
import hdsec.web.project.burn.model.BurnEvent;
import hdsec.web.project.ledger.model.EntityCD;

/**
 * 补打刻录条码
 * 
 * @author gaoxm
 */
public class ReprintBurnBarcodeAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";// 作业号
	private String cd_barcode = "";// 载体一维码
	private String cd_barcodes = "";// 所有载体条码

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getCd_barcode() {
		return cd_barcode;
	}

	public void setCd_barcode(String cd_barcode) {
		this.cd_barcode = cd_barcode;
	}

	public String getCd_barcodes() {
		return cd_barcodes;
	}

	public void setCd_barcodes(String cd_barcodes) {
		this.cd_barcodes = cd_barcodes;
	}

	@Override
	public String executeFunction() throws Exception {

		BurnEvent event = burnService.getBurnEventByBurnCode(event_code);
		String user_name = "";
		String dept_id = "";
		Integer seclv_code = null;
		String file_title = "";
		String project_code = "";
		String usage_code = "";
		Integer page_count = null;
		if (null != event) {
			user_name = event.getUser_name();
			dept_id = event.getDept_id();
			seclv_code = event.getSeclv_code();
			file_title = event.getFile_list();
			project_code = event.getProject_code();
			usage_code = event.getUsage_code();
			page_count = event.getFile_num();
		} else {
			EntityCD entityCD = ledgerService.getCDByBarcode(cd_barcode);
			user_name = entityCD.getUser_name();
			dept_id = entityCD.getDept_id();
			seclv_code = entityCD.getSeclv_code();
			file_title = entityCD.getFile_list();
			page_count = entityCD.getFile_num();
		}

		StringBuffer sb = new StringBuffer();

		SysBarcode sysBarcode = basicService.getSysBarcode(seclv_code, usage_code, project_code);
		if (sysBarcode != null) {
			Integer form = sysBarcode.getForm();
			if (form == 1) {// 一维码
				sb.append("1" + "#" + cd_barcode + "#" + cd_barcode);
			} else if (form == 2) {// 二维码(QR)
				sb.append("2"
						+ "#"
						+ cd_barcode
						+ "#"
						+ basicService.buildTwoDimenosionalBarcode("00", "00", user_name, String.valueOf(seclv_code),
								"D", "G", String.valueOf(page_count), "0000", file_title, dept_id, cd_barcode));
			} else if (form == 3) {// 二维码(PDF417)
				sb.append("3"
						+ "#"
						+ cd_barcode
						+ "#"
						+ basicService.buildTwoDimenosionalBarcode("00", "00", user_name, String.valueOf(seclv_code),
								"D", "G", String.valueOf(page_count), "0000", file_title, dept_id, cd_barcode));
			}
			cd_barcodes = sb.toString();
		}

		return SUCCESS;
	}
}
