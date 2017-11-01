package hdsec.web.project.ledger.action;

import hdsec.web.project.basic.model.SysBarcode;
import hdsec.web.project.ledger.model.ReprintCD;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 光盘补打条码功能
 * 
 * @author lidepeng 2015-4-9
 */
public class ReprintCDBarcodeAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	// private String sysBarcode = "";
	private String cd_barcode = "";
	private String event_code = "";
	private String create_type = "";
	private String medium_barcode = "";
	private String create_type_name = "";
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public String executeFunction() throws Exception {
		// 1 首先判断是一维码还是二维码
		// 2如果是一维码
		// 2.1如果是二维码（QR）
		// 2.2如果是二维码（PDF417）
		StringBuffer sb = new StringBuffer();

		ReprintCD event = null;
		if (create_type == "LEADIN") {
			create_type_name = "录入";
			event = enterService.getEnterEventJoinImportByCDBarcode(cd_barcode);// 录入的光盘
		} else {
			create_type_name = "刻录";
			if (event_code == "single_burn") {
				event = enterService.getEnterEventCDBarcode(cd_barcode);// 单机版导入的没有申请时间
				event.setApply_time(event.getBurn_time());
			} else {
				event = enterService.getEnterEventJoinBurnByCDBarcode(cd_barcode);// 正常刻录有申请时间
				if (event.getApply_time() == null || "".equals(event.getApply_time())) {
					event.setApply_time(event.getBurn_time());
				}
			}
		}
		String user_name = event.getUser_name();
		String dept_id = event.getDept_id();
		String dept_name = event.getDept_name();// 部门名称
		Integer seclv_code = event.getSeclv_code();
		String seclv_name = event.getSeclv_name();// 密级名称
		String file_title = event.getFile_list();
		String project_code = event.getProject_code();
		String usage_code = event.getUsage_code();
		// Integer file_num = event.getFile_num();

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
								"D", "H", String.valueOf(1), "0000", file_title, dept_id, cd_barcode));
			} else if (form == 3) {// 二维码(PDF417)
				sb.append("3"
						+ "#"
						+ cd_barcode
						+ "#"
						+ basicService.buildTwoDimenosionalBarcode("00", "00", user_name, String.valueOf(seclv_code),
								"D", "H", String.valueOf(1), "0000", file_title, dept_id, cd_barcode));
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
						sb.append(create_type_name + "-");
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
		return SUCCESS;
	}

	public String getCd_barcode() {
		return cd_barcode;
	}

	public void setCd_barcode(String cd_barcode) {
		this.cd_barcode = cd_barcode;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getCreate_type() {
		return create_type;
	}

	public void setCreate_type(String create_type) {
		this.create_type = create_type;
	}

	public String getMedium_barcode() {
		return medium_barcode;
	}

	public void setMedium_barcode(String medium_barcode) {
		this.medium_barcode = medium_barcode;
	}
}
