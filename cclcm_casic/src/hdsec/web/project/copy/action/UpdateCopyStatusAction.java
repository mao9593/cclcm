package hdsec.web.project.copy.action;

import hdsec.web.project.basic.model.SysBarcode;
import hdsec.web.project.basic.model.SysSeclevel;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.PropertiesFileUtil;
import hdsec.web.project.common.util.TimeUtil;
import hdsec.web.project.copy.model.CopyEvent;
import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.ledger.service.LedgerService;
import hdsec.web.project.transfer.model.EventTransfer;
import hdsec.web.project.user.model.ModuleEnum;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 更新复印状态并添加纸质台帐记录
 * 
 * 2014-4-17 下午2:25:21
 * 
 * @author lixiang
 */
public class UpdateCopyStatusAction extends CopyBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code;// 作业号

	private String paper_barcode;// 载体条码
	private String user_iidd;// 申请人ID
	private String user_name;// 申请人姓名
	private String dept_id;// 申请人部门ID
	private String dept_name;// 申请人部门名称
	private String duty_user_iidd;// 责任人ID
	private String duty_user_name;// 责任人姓名
	private String duty_dept_id;// 责任人部门ID
	private String duty_dept_name;// 责任人部门名称
	private Integer seclv_code;// 密级代号
	private String file_title;// 原文件名
	private String project_code;// 所属项目
	private Integer page_count;// 页数
	private String page_size;// 纸张大小
	private Integer color;
	private Integer print_direct;// 纵横向
	private Integer print_double;// 单双面
	private Integer paper_state;// 介质状态
	private String paper_barcodes = "";// 所有载体条码
	private SysSeclevel sysSeclevel = null;// 密级信息
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");

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

		// 修改复印作业状态
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("event_code", event_code);
		map.put("copy_status", 1);

		CopyEvent event = copyService.getCopyEventByCopyCode(event_code);
		String job_code = copyService.getJobCodeByEventCode(event_code);
		user_iidd = event.getUser_iidd();
		user_name = event.getUser_name();
		dept_id = event.getDept_id();
		String dept_name = event.getDept_name();// 部门名称
		dept_name = event.getDept_name();
		duty_user_iidd = getCurUser().getUser_iidd();
		duty_user_name = getCurUser().getUser_name();
		duty_dept_id = getCurUser().getDept_id();
		duty_dept_name = getCurUser().getDept_name();
		seclv_code = event.getSeclv_code();
		String seclv_name = event.getSeclv_name();// 密级名称
		file_title = event.getFile_name();
		project_code = event.getProject_code();
		page_count = event.getPage_num();
		page_size = event.getPage_type();
		color = event.getColor();
		print_direct = event.getOrientation();
		print_double = event.getIs_double();
		String usage_code = event.getUsage_code();
		// String create_type = event.getCopy_type().equalsIgnoreCase("internal") ? "COPY" : "OUTCOPY";
		String create_type = "";
		if (event.getCopy_type().equalsIgnoreCase("internal")) {// 内部复印
			create_type = "COPY";
		} else if (event.getCopy_type().equalsIgnoreCase("external")) {// 外部复印
			create_type = "OUTCOPY";
		} else if (event.getCopy_type().equalsIgnoreCase("merge")) {// 合并复印
			create_type = "MERGECOPY";
		}
		SysBarcode sysBarcode = basicService.getSysBarcode(seclv_code, usage_code, project_code);
		String unitCode = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "unit.code");// 5S
		// 获取unit.CompanyType haojia 20160629
		String strCompayType = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "unit.CompanyType");
		int copy_num = event.getCopy_num();
		String copy_num_str = Integer.toString(copy_num);
		for (int i = 0; i < copy_num; i++) {
			StringBuffer sb = new StringBuffer();
			// if (unitCode != "" && unitCode.equalsIgnoreCase("5S")) {
			if (unitCode != "" && strCompayType.equalsIgnoreCase("CAEP")) {
				paper_barcode = basicService.createEntityBarcodeCAEP("COPY", seclv_code, dept_id);
			} else {
				// 放开旧条码规则
				paper_barcode = basicService.createEntityBarcode("COPY");
			}
			// 动态库生成条码方式
			/*
			 * Map<String, String> Paras = new HashMap(); Paras.put("USERID", user_iidd); Paras.put("EVENTTYPE", "3");
			 * Paras.put("EVENTCODE", event_code); Paras.put("COUNT", copy_num_str); Map<String, String> Cmap =
			 * CreateBarcodeUtil.CreateBarcode(Paras, 4); paper_barcode = Cmap.get("Barcode");
			 */
			if (sysBarcode != null) {
				Integer form = sysBarcode.getForm();
				// 动态库生成条码方式
				/*
				 * if (form == 1) {// 一维码 sb.append("1" + "#" + paper_barcode + "#" + paper_barcode); } else if (form ==
				 * 2) {// 二维码(QR) sb.append("2" + "#" + paper_barcode + "#" + Cmap.get("barCode2")); } else if (form ==
				 * 3) {// 二维码(PDF417) sb.append("3" + "#" + paper_barcode + "#" + Cmap.get("barCode2")); }
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
					// 动态库生成条码方式 end
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
				sb.append(";");

				paper_barcodes = paper_barcodes + sb.toString();
			}

			EntityPaper paper = null;
			EventTransfer transfer = null;

			if (!user_iidd.equals(duty_user_iidd) && ModuleEnum.TRANSFER.isModuleEnable()
					&& basicService.isConfirmOpen("COPY_CONFIRM")) {// 流转模块开启且复印交接开启的非本人复印，此时需要走流转
				paper_state = 5;// 流转中
				paper = new EntityPaper(paper_barcode, event_code, user_iidd, user_name, dept_id, dept_name,
						duty_user_iidd, duty_user_name, duty_dept_id, duty_dept_name, seclv_code, "", new Date(), "",
						"1", file_title, project_code, "", "", page_count, page_size, color, print_direct,
						print_double, paper_state, "", "", "", null, null, "", "", null, "", job_code, null, "", "",
						"", create_type, "PERSON", "", "");
				// 生成流转记录
				String transfer_code = getCurUser().getUser_iidd() + "-TRANSFER-" + System.currentTimeMillis() + i;
				transfer = new EventTransfer();
				transfer.setEvent_code(transfer_code);
				transfer.setUser_iidd(getCurUser().getUser_iidd());
				transfer.setUser_name(getCurUser().getUser_name());
				transfer.setDept_id(getCurUser().getDept_id());
				transfer.setDept_name(getCurUser().getDept_name());
				transfer.setSeclv_code(event.getSeclv_code());
				transfer.setBarcode(paper_barcode);
				transfer.setProject_code(event.getProject_code());
				transfer.setUsage_code(event.getUsage_code());
				transfer.setSumm(event.getSumm());
				transfer.setAccept_user_iidd(event.getUser_iidd());
				transfer.setAccept_user_name(event.getUser_name());
				transfer.setAccept_dept_id(event.getDept_id());
				transfer.setAccept_dept_name(event.getDept_name());
				transfer.setApply_time(new Date());
				transfer.setTransfer_status(0);
				transfer.setJob_code(event.getJob_code());
				transfer.setUsage_name(event.getUsage_name());
				transfer.setDept_name(getCurUser().getDept_name());
				transfer.setSeclv_name(event.getSeclv_name());
				transfer.setProject_name(event.getProject_name());
				transfer.setEntity_type(LedgerService.PAPER);
				transfer.setFile_name(file_title);
			} else {
				String cycle_type = event.getCycle_type();
				if (cycle_type.equalsIgnoreCase("FILE")) {// 复印归档
					paper_state = 9;
				} else if (cycle_type.equalsIgnoreCase("SEND")) {// 复印外发
					paper_state = 8;
				} else {
					paper_state = 0;// 留用
				}
				paper = new EntityPaper(paper_barcode, event_code, user_iidd, user_name, dept_id, dept_name, user_iidd,
						user_name, dept_id, dept_name, seclv_code, "", new Date(), "", "1", file_title, project_code,
						"", "", page_count, page_size, color, print_direct, print_double, paper_state, "", "", "",
						null, null, "", "", null, "", job_code, null, "", "", "", create_type, "PERSON", "", "");
				// 根据密级查询回收期限，以当前时间加回收期限得出回收提醒时间。如果为0说明不需要回收，回收提醒时间为默认空值
				sysSeclevel = basicService.getSysSecLevelByCode(seclv_code);
				paper.setExpire_time(TimeUtil.getAfterXDay(sysSeclevel.getArchive_time()));
				map.put("archive_time", sysSeclevel.getArchive_time());
			}

			// 生成载体生命周期记录
			CycleItem cycleitem = new CycleItem();
			cycleitem.setBarcode(paper_barcode);
			cycleitem.setOper_time(new Date());
			cycleitem.setUser_name(getCurUser().getUser_name());
			cycleitem.setDept_name(getCurUser().getDept_name());
			cycleitem.setOper("COPY");
			cycleitem.setEntity_type("PAPER");
			// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
			// String job_code = getJobCodeByEventCode(event.getEvent_code());
			cycleitem.setJob_code(job_code);
			// add ending

			copyService.addCopyPaperledger(map, paper, cycleitem, transfer);
			insertCommonLog("复印文件[" + paper_barcode + "]");
		}
		return SUCCESS;
	}
}