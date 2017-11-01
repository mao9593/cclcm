package hdsec.web.project.copy.action;

import hdsec.web.project.basic.model.SysBarcode;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.PropertiesFileUtil;
import hdsec.web.project.copy.model.CopyEvent;
import hdsec.web.project.ledger.model.EntityPaper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 补打复印条码
 * 
 * @author lixiang
 */
public class ReprintBarcodeAction extends CopyBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";// 作业号
	private String paper_barcode = "";// 载体一维码
	private String paper_barcodes = "";// 所有载体条码
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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

		CopyEvent event = copyService.getCopyEventByCopyCode(event_code);
		EntityPaper entitypaper = ledgerService.getPaperByBarcode(paper_barcode);
		String user_name = event.getUser_name();
		String dept_id = event.getDept_id();
		String dept_name = event.getDept_name();// 部门名称
		Integer seclv_code = event.getSeclv_code();
		String seclv_name = event.getSeclv_name();// 密级名称
		String file_title = event.getFile_name();
		String project_code = event.getProject_code();
		String usage_code = event.getUsage_code();
		Integer page_count = event.getPage_num();
		StringBuffer sb = new StringBuffer();
		int copy_num = event.getCopy_num();// 份数

		String unitCode = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "unit.code");// 5S
		String strCompanyType = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "unit.CompanyType");
		SysBarcode sysBarcode = basicService.getSysBarcode(seclv_code, usage_code, project_code);

		/*
		 * if (unitCode != "" && strCompanyType.equalsIgnoreCase("CAEP")) { paper_barcode =
		 * basicService.createEntityBarcodeCAEP("COPY", seclv_code, dept_id); } else { // 放开旧条码规则 paper_barcode =
		 * basicService.createEntityBarcode("COPY"); }
		 */

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
					// + basicService.getBarcodeCompare(paper_barcode));
				} else if (form == 3) {// 二维码(PDF417)
					sb.append("3"
							+ "#"
							+ paper_barcode
							+ "#"

							+ basicService.buildTwoDimenosionalBarcode("00", "00", user_name,
									String.valueOf(seclv_code), "Z", "F", String.valueOf(page_count), "0000",
									file_title, dept_id, paper_barcode));

					// + basicService.getBarcodeCompare(paper_barcode));
				}
			}
			String textcontent = sysBarcode.getTextcontent();// 查看条码组成
			sb.append("#");
			if (textcontent != null && textcontent != "") {
				String[] texts = textcontent.split("\\|");
				for (String text : texts) {
					if (text.equalsIgnoreCase("tm")) {
						sb.append(paper_barcode + "-");
					} else if (text.equalsIgnoreCase("bm")) {
						sb.append(dept_name + "-");
					} else if (text.equalsIgnoreCase("yh")) {
						sb.append(user_name + "-");
					} else if (text.equalsIgnoreCase("mj")) {
						sb.append(seclv_name + "-");
					} else if (text.equalsIgnoreCase("lx")) {
						sb.append("复印-");
					} else if (text.equalsIgnoreCase("rq")) {
						Date time = event.getApply_time();
						String times = sdf.format(time);
						sb.append(times + "-");
					} else if (text.equalsIgnoreCase("ys")) {
						sb.append(page_count + "页-");
					} else if (text.equalsIgnoreCase("fs")) {
						sb.append(copy_num + "份-");
					}
				}
				// 去掉最后的“-”
				int len = sb.length();
				sb.deleteCharAt(len - 1);
			}
			paper_barcodes = sb.toString();
		}

		return SUCCESS;
	}
}
