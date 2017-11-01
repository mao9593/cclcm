package hdsec.web.project.print.action;

import hdsec.web.project.basic.model.SysBarcode;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.PropertiesFileUtil;
import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.print.model.OaPrintEvent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 特殊文件管理完成更新
 * 
 * @author guojiao
 */
public class UpdateSpecialStatusAction extends PrintBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";// 作业号
	private Integer file_type = null;// 类型
	private String user_iidd = "";// 申请人ID
	private String user_name = "";// 申请人姓名
	private String dept_id = "";// 申请人部门名称
	private String dept_name = "";// 申请人部门名称
	private String duty_user_iidd = "";// 责任人ID
	private String duty_user_name = "";// 责任人姓名
	private String duty_dept_id = "";
	private String duty_dept_name = "";// 责任人部门名称
	private Integer seclv_code = null;// 密级代号
	private String usage_code = "";
	private Integer page_count = null;
	private String seclv_name = "";
	private String project_code = "";// 所属项目
	private String create_type = "";// 产生类型
	private String paper_barcode = "";// 载体条码
	// private Date print_time = null;// 产生时间
	private String file_title = "";// 原文件名
	private Integer paper_state = null;// 介质状态
	private String medium_barcode = "";// 通用载体条码，打印条码传值使用

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public void setFile_type(Integer file_type) {
		this.file_type = file_type;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getMedium_barcode() {
		return medium_barcode;
	}

	@Override
	public String executeFunction() throws Exception {
		try {
			OaPrintEvent event = printService.getSpecialPrintEventByEventCode(event_code);
			user_iidd = event.getUser_iidd();
			user_name = event.getUser_name();
			dept_id = event.getDept_id();
			dept_name = event.getDept_name();
			seclv_code = event.getFile_selv();
			seclv_name = event.getSeclv_name();
			file_title = event.getPaper_name();
			project_code = event.getProject_code();
			usage_code = event.getUsage_code();
			page_count = event.getPage_num();
			duty_user_iidd = getCurUser().getUser_iidd();
			duty_user_name = getCurUser().getUser_name();
			duty_dept_name = getCurUser().getDept_name();
			duty_dept_id = getCurUser().getDept_id();

			// 修改特殊作业状态、完成时间、管理员信息(制作人信息)
			event.setPaper_status(1);
			String unitCode = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "unit.code");// 7S
			String strCompayType = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "unit.CompanyType");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("event_code", event_code);
			map.put("paper_status", 1);
			map.put("manager_user_iidd", duty_user_iidd);
			map.put("manager_user_name", duty_user_name);
			map.put("manager_dept_iidd", duty_dept_id);
			map.put("manager_dept_name", duty_dept_name);
			int enter_num = event.getPaper_num();
			for (int i = 0; i < enter_num; i++) {
				StringBuffer sb = new StringBuffer();
				// 动态库生成条码方式
				/*
				 * Map<String, String> Paras = new HashMap(); Paras.put("USERID", user_iidd); Paras.put("EVENTTYPE",
				 * "10");// 10代表特殊打印 Paras.put("EVENTCODE", event_code); Paras.put("COUNT", String.valueOf(enter_num));
				 * Map<String, String> Cmap = CreateBarcodeUtil.CreateBarcode(Paras, 6);
				 */
				if (file_type == 1) {
					// 此处置空是为了避免以后页面再次增加预发条码时，没有改JAVA代码导致多份条码重复的严重问题
					paper_barcode = null;
					// 如果条码不存在，则生成条码
					if (paper_barcode == null || paper_barcode.equals("")) {
						// paper_barcode = basicService.createEntityBarcode("PRINTPAPER");
						// paper_barcode = Cmap.get("Barcode");
						if (unitCode != "" && strCompayType.equalsIgnoreCase("CAEP")) {
							// if (unitCode != "" && unitCode.equalsIgnoreCase("5S")) {
							paper_barcode = basicService.createEntityBarcodeCAEP("PRINTPAPER", seclv_code, dept_id);
						} else {
							paper_barcode = basicService.createEntityBarcode("PRINTPAPER");
							// paper_barcode = Cmap.get("Barcode");
							// event.setMedium_serial(paper_barcode);

						}
					}
					SysBarcode sysBarcode = basicService.getSysBarcode(seclv_code, usage_code, project_code);
					if (sysBarcode != null) {
						Integer form = sysBarcode.getForm();
						/*
						 * if (form == 1) {// 一维码 sb.append("1" + "#" + paper_barcode + "#" + paper_barcode); } else if
						 * (form == 2) {// 二维码(QR) String barcode2 = basicService.buildTwoDimenosionalBarcode("00",
						 * "00", user_name, String.valueOf(seclv_code), "Z", "D", String.valueOf(page_count), "0000",
						 * file_title, dept_id, paper_barcode); sb.append("2" + "#" + paper_barcode + "#" + barcode2); }
						 * else if (form == 3) {// 二维码(PDF417) String barcode2 =
						 * basicService.buildTwoDimenosionalBarcode("00", "00", user_name, String.valueOf(seclv_code),
						 * "Z", "D", String.valueOf(page_count), "0000", file_title, dept_id, paper_barcode);
						 * sb.append("3" + "#" + paper_barcode + "#" + barcode2); }
						 */
						if (unitCode != "" && strCompayType.equalsIgnoreCase("CAEP")) {
							// if (unitCode != "" && unitCode.equalsIgnoreCase("5S")) {
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
												String.valueOf(seclv_code), "Z", "W", String.valueOf(page_count),
												"0000", file_title, dept_id, paper_barcode));
							} else if (form == 3) {// 二维码(PDF417)
								sb.append("3"
										+ "#"
										+ paper_barcode
										+ "#"
										+ basicService.buildTwoDimenosionalBarcode("00", "00", user_name,
												String.valueOf(seclv_code), "Z", "W", String.valueOf(page_count),
												"0000", file_title, dept_id, paper_barcode));
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
						sb.append(":");
						medium_barcode = medium_barcode + sb.toString();
					}
				}
				// 生成载体生命周期记录
				CycleItem cycleitem = new CycleItem();
				cycleitem.setBarcode(paper_barcode);
				cycleitem.setOper_time(new Date());
				cycleitem.setUser_name(getCurUser().getUser_name());
				cycleitem.setDept_name(getCurUser().getDept_name());
				cycleitem.setOper("SPECIAL");
				cycleitem.setJob_code(event.getJob_code());

				create_type = "SPECIAL";

				// 入纸质载体台帐
				paper_state = 0;// 留用
				EntityPaper paper = new EntityPaper(paper_barcode, event_code, user_iidd, user_name, dept_id,
						dept_name, user_iidd, user_name, dept_id, dept_name, seclv_code, "", new Date(), "", "1",
						file_title, project_code, "", "", page_count, event.getPaper_kind(), event.getPaper_color(),
						event.getPrint_direct(), event.getPrint_double(), paper_state, "", "", "", null, null, "", "",
						null, "", "", null, "", "", "", create_type, "PERSON", "", "");

				cycleitem.setEntity_type("PAPER");
				printService.querrySpecialOpers(map, cycleitem, paper);
				insertCommonLog("特殊文件添加台账[" + paper_barcode + "]");
			}

			return SUCCESS;
		} catch (Exception e) {
			logger.error("Exception" + e.getMessage());
			throw new Exception("执行录入操作出现异常");
		}

	}
}
