package hdsec.web.project.ledger.action;

import hdsec.web.project.basic.model.SysBarcode;
import hdsec.web.project.common.PropertyUtil;
import hdsec.web.project.copy.model.CopyEvent;
import hdsec.web.project.enter.model.EnterEvent;
import hdsec.web.project.print.model.PrintEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文件补打条码
 * 
 * @author chenrongrong 2015-4-9
 */
public class ReprintPaperBarcodeAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";// 作业号
	private String paper_barcode = "";// 文件条码号
	private String medium_barcode = "";// 载体条码
	private String create_type_name = "";
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	// private String sysBarcode = null;

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

	public void setCreate_type_name(String create_type_name) {
		this.create_type_name = create_type_name;
	}

	@Override
	public String executeFunction() throws Exception {
		StringBuffer sb = new StringBuffer();

		if (create_type_name.equals("打印")) {
			PrintEvent event = printService.getPrintEventByPrintCode(event_code);
			String user_name = event.getUser_name();// 用户名
			String dept_id = event.getDept_id();
			String dept_name = event.getDept_name();// 部门名称
			Integer seclv_code = event.getSeclv_code();
			String seclv_name = event.getSeclv_name();// 密级名称
			String file_title = event.getFile_title();
			String project_code = event.getProject_code();
			String usage_code = event.getUsage_code();
			Integer page_count = event.getPage_count();// 页数
			int print_count = event.getPage_count();// 份数

			SysBarcode sysBarcode = basicService.getSysBarcode(seclv_code, usage_code, project_code);
			if (sysBarcode != null) {
				Integer form = sysBarcode.getForm();
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
				} else if (form == 3) {// 二维码(PDF417)
					sb.append("3"
							+ "#"
							+ paper_barcode
							+ "#"
							+ basicService.buildTwoDimenosionalBarcode("00", "00", user_name,
									String.valueOf(seclv_code), "Z", "W", String.valueOf(page_count), "0000",
									file_title, dept_id, paper_barcode));
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
							sb.append("打印-");
						} else if (text.equalsIgnoreCase("rq")) {
							Date time = event.getApply_time();
							String times = sdf.format(time);
							sb.append(times + "-");
						} else if (text.equalsIgnoreCase("ys")) {
							sb.append(page_count + "页-");
						} else if (text.equalsIgnoreCase("fs")) {
							sb.append(print_count + "份-");
						}
					}
					// 去掉最后的“-”
					int len = sb.length();
					sb.deleteCharAt(len - 1);
				}
				medium_barcode = sb.toString();
			}

		} else if (create_type_name.equals("复印") || create_type_name.equals("外来文件复印")) {
			CopyEvent event = copyService.getCopyEventByCopyCode(event_code);
			String user_name = event.getUser_name();// 用户名
			String dept_id = event.getDept_id();
			String dept_name = event.getDept_name();// 部门名称
			Integer seclv_code = event.getSeclv_code();
			String seclv_name = event.getSeclv_name();// 密级名称
			String file_title = event.getFile_name();
			String project_code = event.getProject_code();
			String usage_code = event.getUsage_code();
			Integer page_count = event.getPage_num();// 页数
			int copy_num = event.getCopy_num();// 份数

			SysBarcode sysBarcode = basicService.getSysBarcode(seclv_code, usage_code, project_code);
			if (sysBarcode != null) {
				Integer form = sysBarcode.getForm();
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
				} else if (form == 3) {// 二维码(PDF417)
					sb.append("3"
							+ "#"
							+ paper_barcode
							+ "#"
							+ basicService.buildTwoDimenosionalBarcode("00", "00", user_name,
									String.valueOf(seclv_code), "Z", "W", String.valueOf(page_count), "0000",
									file_title, dept_id, paper_barcode));
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
				medium_barcode = sb.toString();
			}
		} else {// 录入
			EnterEvent event = enterService.getEnterEventByEnterCode(event_code);
			String user_name = event.getUser_name();// 用户名
			String dept_id = event.getDept_id();
			String dept_name = event.getDept_name();// 部门名称
			Integer seclv_code = event.getSeclv_code();
			String seclv_name = event.getSeclv_name();// 密级名称
			String file_title = event.getZipfile();
			String project_code = event.getProject_code();
			String usage_code = event.getUsage_code();
			Integer page_count = event.getPage_num();// 页数
			// int copy_num = event.getCopy_num();// 份数

			SysBarcode sysBarcode = basicService.getSysBarcode(seclv_code, usage_code, project_code);
			if (sysBarcode != null) {
				Integer form = sysBarcode.getForm();
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
				} else if (form == 3) {// 二维码(PDF417)
					sb.append("3"
							+ "#"
							+ paper_barcode
							+ "#"
							+ basicService.buildTwoDimenosionalBarcode("00", "00", user_name,
									String.valueOf(seclv_code), "Z", "W", String.valueOf(page_count), "0000",
									file_title, dept_id, paper_barcode));
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

	public String buildCETCTwoDimenosionalBarcode(String parent_dept_name, String entity_barcode,
			String curr_dept_name, String duty_name, String type, String file_name, String seclv_name, String src_from,
			String apply_time) throws Exception {
		StringBuffer sb = new StringBuffer();
		// 版本标识 14
		sb.append(PropertyUtil.getUnitBarcode());
		sb.append("^");
		// 条码编号
		sb.append(entity_barcode);
		sb.append("^");
		// 成员单位
		sb.append(new String(parent_dept_name.getBytes("ISO-8859-1"), "UTF-8"));
		sb.append("^");
		// 部门
		sb.append(curr_dept_name);
		sb.append("^");
		// 申请人
		sb.append(duty_name);
		sb.append("^");
		// 载体类型 含纸介质，光盘，磁介质，其他介质
		sb.append(type);
		sb.append("^");
		// 文件名称
		if (file_name.length() > 50) {
			file_name = file_name.substring(0, 50) + "...等";
		}
		sb.append(file_name);
		sb.append("^");
		// 密级4 绝密，机密，秘密，内部，非密
		sb.append(seclv_name);
		sb.append("^");
		// 制作时间
		sb.append(apply_time);
		sb.append("^");
		// 来源
		sb.append(src_from);
		sb.append("^|");
		return sb.toString();
	}

}
