package hdsec.web.project.print.action;

import hdsec.web.project.basic.model.SysBarcode;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.PropertiesFileUtil;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.print.model.OaPrintEvent;
import hdsec.web.project.print.model.PrintEvent;

/**
 * 打印拼图补打条码
 * 
 * @author rmf 2014-12-11
 */
public class ReprintBarcodeAction extends PrintBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";// 作业号
	private String paper_barcode = "";// 载体一维码
	private String paper_barcodes = "";// 所有载体条码
	private String print_type = "";// 打印类型标记，用于标识普通打印或特殊打印

	public void setPrint_type(String print_type) {
		this.print_type = print_type;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getPaper_barcode() {
		return paper_barcode;
	}

	public void setPaper_barcode(String paper_barcode) {
		this.paper_barcode = paper_barcode;
	}

	public String getPaper_barcodes() {
		return paper_barcodes;
	}

	public void setPaper_barcodes(String paper_barcodes) {
		this.paper_barcodes = paper_barcodes;
	}

	@Override
	public String executeFunction() throws Exception {
		String user_name = "";
		String dept_id = "";
		Integer seclv_code;
		String file_title = "";
		String project_code = "";
		String usage_code = "";
		Integer page_count;
		EntityPaper paper = null;

		if (print_type.equalsIgnoreCase("SPECIAL_PRINT")) {
			OaPrintEvent event = printService.getSpecialPrintEventByEventCode(event_code);
			user_name = event.getUser_name();
			dept_id = event.getDept_id();
			seclv_code = event.getSeclv_code();
			file_title = event.getFile_list();
			project_code = event.getProject_code();
			usage_code = event.getUsage_code();
			page_count = event.getPage_num();

		} else if (print_type.equalsIgnoreCase("merge")) {
			paper = ledgerService.getPaperByBarcode(paper_barcode);
			seclv_code = paper.getSeclv_code();
			page_count = paper.getPage_count();
			file_title = paper.getFile_title();
			user_name = paper.getUser_name();
			dept_id = paper.getDept_id();
			// 更新合并台账类型打印条码状态，是否已打印
			ledgerService.updateMergeCodeState(paper_barcode);
		} else {
			PrintEvent event = printService.getPrintEventByPrintCode(event_code);
			user_name = event.getUser_name();
			dept_id = event.getDept_id();
			seclv_code = event.getSeclv_code();
			file_title = event.getFile_title();
			project_code = event.getProject_code();
			usage_code = event.getUsage_code();
			page_count = event.getPage_count();
		}

		StringBuffer sb = new StringBuffer();

		String unitCode = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "unit.code");// 5S or 7S
		String strCompanyType = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "unit.CompanyType");
		SysBarcode sysBarcode = basicService.getSysBarcode(seclv_code, usage_code, project_code);
		if (sysBarcode != null) {
			Integer form = sysBarcode.getForm();
			if (unitCode != "" && strCompanyType.equalsIgnoreCase("CAEP")) {
				if (form == 1) {// 一维码
					sb.append("1" + "#" + paper_barcode + "#" + paper_barcode);
				} else if (form == 2) {// 二维码(QR)
					sb.append("2" + "#" + paper_barcode + "#" + paper_barcode);
				} else if (form == 3) {// 二维码(PDF417)
					sb.append("3" + "#" + paper_barcode + "#" + paper_barcode);
				}
			} else {
				if (form == 1) {// 一维码
					sb.append("1" + "#" + paper_barcode + "#" + paper_barcode);
				} else if (form == 2) {// 二维码(QR)
					sb.append("2"
							+ "#"
							+ paper_barcode
							+ "#"
							+ basicService.buildTwoDimenosionalBarcode("00", "00", user_name,
									String.valueOf(seclv_code), "Z", "F", String.valueOf(page_count), "0000",
									file_title, dept_id, paper_barcode));
				} else if (form == 3) {// 二维码(PDF417)
					sb.append("3"
							+ "#"
							+ paper_barcode
							+ "#"
							+ basicService.buildTwoDimenosionalBarcode("00", "00", user_name,
									String.valueOf(seclv_code), "Z", "F", String.valueOf(page_count), "0000",
									file_title, dept_id, paper_barcode));
				}
			}
			paper_barcodes = sb.toString();
		}

		return SUCCESS;
	}
}
