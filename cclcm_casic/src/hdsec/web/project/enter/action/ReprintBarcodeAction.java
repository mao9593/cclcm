package hdsec.web.project.enter.action;

import hdsec.web.project.basic.model.SysBarcode;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.PropertiesFileUtil;
import hdsec.web.project.enter.model.EnterEvent;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 补打录入条码
 * 
 * @author lixiang
 */
public class ReprintBarcodeAction extends EnterBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";// 作业号
	private String medium_barcode = "";// 载体条码
	private String paper_barcode;// 文件条码号
	private String cd_barcode;// 光盘条码号
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getMedium_barcode() {
		return medium_barcode;
	}

	public void setMedium_barcode(String medium_barcode) {
		this.medium_barcode = medium_barcode;
	}

	public String getPaper_barcode() {
		return paper_barcode;
	}

	public void setPaper_barcode(String paper_barcode) {
		this.paper_barcode = paper_barcode;
	}

	public String getCd_barcode() {
		return cd_barcode;
	}

	public void setCd_barcode(String cd_barcode) {
		this.cd_barcode = cd_barcode;
	}

	@Override
	public String executeFunction() throws Exception {

		EnterEvent event = enterService.getEnterEventByEnterCode(event_code);
		EntityPaper entitypaper = ledgerService.getPaperByBarcode(paper_barcode);
		EntityCD entitycd = ledgerService.getCDByBarcode(cd_barcode);
		Integer file_type = event.getFile_type();
		String user_name = event.getUser_name();
		String dept_id = event.getDept_id();
		String dept_name = event.getDept_name();// 部门名称
		Integer seclv_code = event.getSeclv_code();
		String seclv_name = event.getSeclv_name();// 密级名称
		String file_title = event.getZipfile();
		String project_code = event.getProject_code();
		String usage_code = event.getUsage_code();
		Integer page_count = event.getPage_num();

		StringBuffer sb = new StringBuffer();
		int enter_num = event.getEnter_num();// 份数

		String unitCode = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "unit.code");// 5S
		String strCompanyType = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "unit.CompanyType");
		if (file_type == 1) {// 文件
			// String paper_barcode = event.getMedium_serial();

			if (paper_barcode == null || paper_barcode.equals("")) {
				if (unitCode != "" && strCompanyType.equalsIgnoreCase("CAEP")) {
					paper_barcode = basicService.createEntityBarcodeCAEP("ENTERPAPER", seclv_code, dept_id);
				} else {
					paper_barcode = basicService.createEntityBarcode("ENTERPAPER");
					// paper_barcode = Cmap.get("Barcode");
					// event.setMedium_serial(paper_barcode);
				}
			}

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
										String.valueOf(seclv_code), "Z", "W", String.valueOf(page_count), "0000",
										file_title, dept_id, paper_barcode));
						/* + basicService.getBarcodeCompare(paper_barcode)); */
					} else if (form == 3) {// 二维码(PDF417)
						sb.append("3"
								+ "#"
								+ paper_barcode
								+ "#"

								+ basicService.buildTwoDimenosionalBarcode("00", "00", user_name,
										String.valueOf(seclv_code), "Z", "W", String.valueOf(page_count), "0000",
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
							sb.append("录入-");
						} else if (text.equalsIgnoreCase("rq")) {
							Date time = event.getApply_time();
							String times = sdf.format(time);
							sb.append(times + "-");
						} else if (text.equalsIgnoreCase("ys")) {
							sb.append(page_count + "页-");
						} else if (text.equalsIgnoreCase("fs")) {
							sb.append(enter_num + "份-");
						}
					}
					// 去掉最后的“-”
					int len = sb.length();
					sb.deleteCharAt(len - 1);
				}

				medium_barcode = sb.toString();
			}
		} else {// 光盘
			// String cd_barcode = event.getMedium_serial();

			if (cd_barcode == null || cd_barcode.equals("")) {
				if (unitCode != "" && strCompanyType.equalsIgnoreCase("CAEP")) {
					cd_barcode = basicService.createEntityBarcodeCAEP("ENTERCD", seclv_code, dept_id);
				} else {
					cd_barcode = basicService.createEntityBarcode("ENTERCD");
					// cd_barcode = Cmap.get("Barcode"); // 动态库生成条码方式
					// event.setMedium_serial(cd_barcode);
					// event.setMedium_serial("");
				}
			}

			SysBarcode sysBarcode = basicService.getSysBarcode(seclv_code, usage_code, project_code);
			if (sysBarcode != null) {
				Integer form = sysBarcode.getForm();

				if (unitCode != "" && strCompanyType.equalsIgnoreCase("CAEP")) {
					if (form == 1) {// 一维码
						sb.append("1" + "#" + cd_barcode + "#" + cd_barcode);
					} else if (form == 2) {// 二维码(QR)
						sb.append("2" + "#" + cd_barcode + "#" + cd_barcode);
					} else if (form == 3) {// 二维码(PDF417)
						sb.append("3" + "#" + cd_barcode + "#" + cd_barcode);
					}
				} else {
					if (form == 1) {// 一维码
						sb.append("1" + "#" + cd_barcode + "#" + cd_barcode);
					} else if (form == 2) {// 二维码(QR)
						sb.append("2"
								+ "#"
								+ cd_barcode
								+ "#"
								+ basicService.buildTwoDimenosionalBarcode("00", "00", user_name,
										String.valueOf(seclv_code), "D", "H", String.valueOf(page_count), "0000",
										file_title, dept_id, cd_barcode));

						// basicService.getBarcodeCompare(cd_barcode));
					} else if (form == 3) {// 二维码(PDF417)
						sb.append("3"
								+ "#"
								+ cd_barcode
								+ "#"
								+ basicService.buildTwoDimenosionalBarcode("00", "00", user_name,
										String.valueOf(seclv_code), "D", "H", String.valueOf(page_count), "0000",
										file_title, dept_id, cd_barcode));

						// basicService.getBarcodeCompare(cd_barcode));
					}
				}
				String textcontent = sysBarcode.getTextcontent();// 查看条码组成
				sb.append("#");
				if (textcontent != null && textcontent != "") {
					String[] texts = textcontent.split("\\|");
					for (String text : texts) {
						if (text.equalsIgnoreCase("tm")) {
							sb.append(cd_barcode + "-");
						} else if (text.equalsIgnoreCase("bm")) {
							sb.append(dept_name + "-");
						} else if (text.equalsIgnoreCase("yh")) {
							sb.append(user_name + "-");
						} else if (text.equalsIgnoreCase("mj")) {
							sb.append(seclv_name + "-");
						} else if (text.equalsIgnoreCase("lx")) {
							sb.append("录入-");
						} else if (text.equalsIgnoreCase("rq")) {
							Date time = event.getApply_time();
							String times = sdf.format(time);
							sb.append(times + "-");
						}
					}
					// 去掉最后的“-”
					int len = sb.length();
					sb.deleteCharAt(len - 1);
				}
				medium_barcode = sb.toString();
			}
		}

		return SUCCESS;
	}
}
