package hdsec.web.project.arch.action;

import hdsec.web.project.arch.model.ArchValue;
import hdsec.web.project.basic.model.SysBarcode;

/**
 * 补打条码
 * 
 * @author handouwang
 * 
 *         2015-7-29/
 */
public class ReprintBarcodeAction extends ArchBaseAction {
	private static final long serialVersionUID = 1L;
	private int id;// 档案ID
	private String barcode = "";// 载体一维码
	private String paper_barcodes = "";// 所有载体条码

	public void setId(int id) {
		this.id = id;
	}

	public String getPaper_barcodes() {
		return paper_barcodes;
	}

	public void setPaper_barcodes(String paper_barcodes) {
		this.paper_barcodes = paper_barcodes;
	}

	public String getDept_id() {
		String dept_id = getCurUser().getDept_id();
		if (dept_id.length() >= 4) {
			return dept_id.substring(0, 4);
		} else {
			int l = 4 - dept_id.length();
			return "0000".substring(0, l).concat(dept_id);
		}
	}

	@Override
	public String executeFunction() throws Exception {
		ArchValue arch = archService.getArchValueById(id + "");
		String user_name = getCurUser().getUser_name();
		String dept_id = getDept_id();
		Integer seclv_code = userService.getSeclvCodeByName(arch
				.getSeclv_code());
		String file_title = arch.getFile_title();
		Integer page_count = Integer.parseInt(arch.getPage_num());
		barcode = arch.getBarcode();
		StringBuffer sb = new StringBuffer();

		SysBarcode sysBarcode = basicService.getDefaultSysBarcode();
		if (sysBarcode != null) {
			Integer form = sysBarcode.getForm();
			if (form == 1) {// 一维码
				sb.append("1" + "#" + barcode + "#" + barcode);
			} else if (form == 2) {// 二维码(QR)
				sb.append("2"
						+ "#"
						+ barcode
						+ "#"
						+ basicService.buildTwoDimenosionalBarcode("00", "00",
								user_name, String.valueOf(seclv_code), "D",
								"H", String.valueOf(page_count), "0000",
								file_title, dept_id, barcode));
			} else if (form == 3) {// 二维码(PDF417)
				sb.append("3"
						+ "#"
						+ barcode
						+ "#"
						+ basicService.buildTwoDimenosionalBarcode("00", "00",
								user_name, String.valueOf(seclv_code), "D",
								"H", String.valueOf(page_count), "0000",
								file_title, dept_id, barcode));
			}
			paper_barcodes = sb.toString();
		}

		return SUCCESS;
	}
}
