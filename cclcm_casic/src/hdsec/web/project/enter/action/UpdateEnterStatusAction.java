package hdsec.web.project.enter.action;

import hdsec.web.project.basic.model.SysBarcode;
import hdsec.web.project.basic.model.SysConfigItem;
import hdsec.web.project.basic.model.SysSeclevel;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.PropertiesFileUtil;
import hdsec.web.project.common.util.TimeUtil;
import hdsec.web.project.device.model.DeviceStatusEnum;
import hdsec.web.project.device.model.EntityDevice;
import hdsec.web.project.enter.model.EnterEvent;
import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.ledger.service.LedgerService;
import hdsec.web.project.transfer.model.EventTransfer;
import hdsec.web.project.user.model.ModuleEnum;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 更新录入状态并添加介质台帐记录 2014-4-15 上午7:23:53
 * 
 * @author gaoxm
 */
public class UpdateEnterStatusAction extends EnterBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code;// 作业号
	private Integer file_type;// 类型

	// 文件、光盘公用部分
	private String user_iidd;// 申请人ID
	private String user_name;// 申请人姓名
	private String dept_name;// 申请人部门名称
	private String duty_user_iidd;// 责任人ID
	private String duty_user_name;// 责任人姓名
	private String duty_dept_name;// 责任人部门名称
	private Integer seclv_code;// 密级代号
	private String project_code;// 所属项目
	private String create_type;// 产生类型
	private String scope;// 载体归属
	private String scope_dept_id;// 载体归属部门ID
	private String scope_dept_name;// 载体归属部门名称

	// 文件载体
	private Integer page_count;// 页数
	private String paper_barcode;// 载体条码
	private Date print_time;// 产生时间
	private String file_title;// 原文件名
	private Integer paper_state;// 介质状态

	// 光盘载体
	private String cd_barcode;// 载体条码
	private String dept_id;// 申请人部门ID
	private String duty_dept_id;// 责任人部门ID
	private String file_list;// 文件列表
	private Integer cd_state;// 介质状态

	// 光盘载体
	private String device_barcode;// 载体条码

	private String medium_barcode = "";// 通用载体条码，打印条码传值使用

	private SysSeclevel sysSeclevel = null;// 密级信息
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
	private SysBarcode barcode;

	public String getDevice_barcode() {
		return device_barcode;
	}

	public void setDevice_barcode(String device_barcode) {
		this.device_barcode = device_barcode;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public void setFile_type(Integer file_type) {
		this.file_type = file_type;
	}

	public String getPaper_barcode() {
		return paper_barcode;
	}

	public void setPaper_barcode(String paper_barcode) {
		this.paper_barcode = paper_barcode;
	}

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getDuty_user_iidd() {
		return duty_user_iidd;
	}

	public void setDuty_user_iidd(String duty_user_iidd) {
		this.duty_user_iidd = duty_user_iidd;
	}

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name;
	}

	public String getDuty_dept_name() {
		return duty_dept_name;
	}

	public void setDuty_dept_name(String duty_dept_name) {
		this.duty_dept_name = duty_dept_name;
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public Date getPrint_time() {
		return print_time;
	}

	public void setPrint_time(Date print_time) {
		this.print_time = print_time;
	}

	public String getFile_title() {
		return file_title;
	}

	public void setFile_title(String file_title) {
		this.file_title = file_title;
	}

	public String getProject_code() {
		return project_code;
	}

	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}

	public Integer getPage_count() {
		return page_count;
	}

	public void setPage_count(Integer page_count) {
		this.page_count = page_count;
	}

	public Integer getPaper_state() {
		return paper_state;
	}

	public void setPaper_state(Integer paper_state) {
		this.paper_state = paper_state;
	}

	public String getCreate_type() {
		return create_type;
	}

	public void setCreate_type(String create_type) {
		this.create_type = create_type;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getScope_dept_id() {
		return scope_dept_id;
	}

	public void setScope_dept_id(String scope_dept_id) {
		this.scope_dept_id = scope_dept_id;
	}

	public String getScope_dept_name() {
		return scope_dept_name;
	}

	public void setScope_dept_name(String scope_dept_name) {
		this.scope_dept_name = scope_dept_name;
	}

	public String getCd_barcode() {
		return cd_barcode;
	}

	public void setCd_barcode(String cd_barcode) {
		this.cd_barcode = cd_barcode;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getDuty_dept_id() {
		return duty_dept_id;
	}

	public void setDuty_dept_id(String duty_dept_id) {
		this.duty_dept_id = duty_dept_id;
	}

	public String getFile_list() {
		return file_list;
	}

	public void setFile_list(String file_list) {
		this.file_list = file_list;
	}

	public Integer getCd_state() {
		return cd_state;
	}

	public void setCd_state(Integer cd_state) {
		this.cd_state = cd_state;
	}

	public Integer getFile_type() {
		return file_type;
	}

	public String getMedium_barcode() {
		return medium_barcode;
	}

	public void setMedium_barcode(String medium_barcode) {
		this.medium_barcode = medium_barcode;
	}

	@Override
	public String executeFunction() throws Exception {
		try {
			EnterEvent event = enterService.getEnterEventByEnterCode(event_code);
			String user_name = event.getUser_name();
			String user_iidd = event.getUser_iidd();
			String dept_id = event.getDept_id();
			String dept_name = event.getDept_name();// 部门名称
			Integer seclv_code = event.getSeclv_code();
			String seclv_name = event.getSeclv_name();// 密级名称
			String file_title = event.getZipfile();
			String project_code = event.getProject_code();
			String usage_code = event.getUsage_code();
			Integer page_count = event.getPage_num();
			String company = event.getCompany();// 单位
			String publish_type = event.getPublish_type();// 公文种类或刊物名称
			String company_send = event.getCompany_send();// 主送单位
			String urgency_lv = event.getUrgency_lv();// 紧急程度（公文分无、急件、特级，电报分无、平急、加急、特急、特提）
			String filed_date = event.getFiled_date();// 公文成文日期或刊物编印日期
			String publish_limits = event.getPublish_limits();// 发布层次（分为省军级、市地级、县团级、公开发布）
			String summ = event.getSumm();// 备注
			String fileID = event.getFileid();// 文号
			String src_code = event.getSrc_code();// 原文件/光盘/磁介质编号
			String confidential_num = event.getConfidential_num(); // 录入文件机要号
			// StringBuffer sb = new StringBuffer();
			// 修改录入作业状态、完成时间、录入人
			event.setImport_status(1);
			String unitCode = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "unit.code");// 5S
			// 获取unit.CompanyType haojia 20160629
			String strCompayType = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "unit.CompanyType");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("event_code", event_code);
			map.put("import_status", 1);
			map.put("finish_time", new Date());
			map.put("import_user_iidd", getCurUser().getUser_iidd());
			int enter_num = event.getEnter_num();
			// 生成载体生命周期记录
			CycleItem cycleitem = new CycleItem();
			for (int i = 0; i < enter_num; i++) {
				StringBuffer sb = new StringBuffer();
				// 动态库生成条码方式
				/*
				 * Map<String, String> Paras = new HashMap(); Paras.put("USERID", user_iidd); Paras.put("EVENTTYPE",
				 * "4"); Paras.put("EVENTCODE", event_code); Paras.put("COUNT", String.valueOf(enter_num)); Map<String,
				 * String> Cmap = CreateBarcodeUtil.CreateBarcode(Paras, 6);
				 */
				if (file_type == 1) {// 文件
					// 此处置空是为了避免以后页面再次增加预发条码时，没有改JAVA代码导致多份条码重复的严重问题
					paper_barcode = event.getMedium_serial();
					// paper_barcode = null;
					// 如果条码不存在，则生成条码
					if (paper_barcode == null || paper_barcode.equals("")) {
						if (unitCode != "" && strCompayType.equalsIgnoreCase("CAEP")) {
							// if (unitCode != "" && unitCode.equalsIgnoreCase("5S")) {
							paper_barcode = basicService.createEntityBarcodeCAEP("ENTERPAPER", seclv_code, dept_id);
						} else {
							paper_barcode = basicService.createEntityBarcode("ENTERPAPER");
							// paper_barcode = Cmap.get("Barcode");
							// event.setMedium_serial(paper_barcode);
							event.setMedium_serial("");
						}
					}
					SysBarcode sysBarcode = basicService.getSysBarcode(seclv_code, usage_code, project_code);
					if (sysBarcode != null) {
						Integer form = sysBarcode.getForm();
						// 动态库生成条码方式
						/*
						 * if (form == 1) {// 一维码 sb.append("1" + "#" + paper_barcode + "#" + paper_barcode); } else if
						 * (form == 2) {// 二维码(QR) sb.append("2" + "#" + paper_barcode + "#" + Cmap.get("barCode2")); }
						 * else if (form == 3) {// 二维码(PDF417) sb.append("3" + "#" + paper_barcode + "#" +
						 * Cmap.get("barCode2")); }
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
						sb.append(";");
						medium_barcode = medium_barcode + sb.toString();
					}
					cycleitem.setBarcode(paper_barcode);
					// map.put("medium_serial", paper_barcode);
					map.put("medium_serial", "");
				} else if (file_type == 2) {// 光盘
					cd_barcode = event.getMedium_serial();
					// 如果条码不存在，则生成条码
					if (cd_barcode == null || cd_barcode.equals("")) {
						if (unitCode != "" && strCompayType.equalsIgnoreCase("CAEP")) {
							// if (unitCode != "" && unitCode.equalsIgnoreCase("5S")) {
							cd_barcode = basicService.createEntityBarcodeCAEP("ENTERCD", seclv_code, dept_id);
						} else {
							cd_barcode = basicService.createEntityBarcode("ENTERCD");
							// cd_barcode = Cmap.get("Barcode"); // 动态库生成条码方式
							event.setMedium_serial(cd_barcode);
							// event.setMedium_serial("");
						}
					}
					SysBarcode sysBarcode = basicService.getSysBarcode(seclv_code, usage_code, project_code);
					if (sysBarcode != null) {
						Integer form = sysBarcode.getForm();
						// 动态库生成条码方式
						/*
						 * if (form == 1) {// 一维码 sb.append("1" + "#" + cd_barcode + "#" + cd_barcode); } else if (form
						 * == 2) {// 二维码(QR) sb.append("2" + "#" + cd_barcode + "#" + Cmap.get("barCode2")); } else if
						 * (form == 3) {// 二维码(PDF417) sb.append("3" + "#" + cd_barcode + "#" + Cmap.get("barCode2")); }
						 */
						if (unitCode != "" && strCompayType.equalsIgnoreCase("CAEP")) {
							// if (unitCode != "" && unitCode.equalsIgnoreCase("5S")) {
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
												String.valueOf(seclv_code), "D", "H", String.valueOf(page_count),
												"0000", file_title, dept_id, cd_barcode));
							} else if (form == 3) {// 二维码(PDF417)
								sb.append("3"
										+ "#"
										+ cd_barcode
										+ "#"
										+ basicService.buildTwoDimenosionalBarcode("00", "00", user_name,
												String.valueOf(seclv_code), "D", "H", String.valueOf(page_count),
												"0000", file_title, dept_id, cd_barcode));
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
						sb.append(";");
						medium_barcode = medium_barcode + sb.toString();
					}
					cycleitem.setBarcode(cd_barcode);
					map.put("medium_serial", cd_barcode);
					// map.put("medium_serial", "");
				} else if (file_type == 4) {// 个人磁介质
					device_barcode = event.getMedium_serial();
					// 如果条码不存在，则生成条码
					if (device_barcode == null || device_barcode.equals("")) {
						if (unitCode != "" && strCompayType.equalsIgnoreCase("CAEP")) {
							// if (unitCode != "" && unitCode.equalsIgnoreCase("5S")) {
							device_barcode = basicService.createEntityBarcodeCAEP("DEVICE", seclv_code, dept_id);
						} else {
							device_barcode = basicService.createEntityBarcode("ENTERCD");
							// cd_barcode = Cmap.get("Barcode"); // 动态库生成条码方式
							event.setMedium_serial(device_barcode);
							// event.setMedium_serial("");
						}
					}
					SysBarcode sysBarcode = basicService.getSysBarcode(seclv_code, usage_code, project_code);
					if (sysBarcode != null) {
						Integer form = sysBarcode.getForm();
						// 动态库生成条码方式
						/*
						 * if (form == 1) {// 一维码 sb.append("1" + "#" + cd_barcode + "#" + cd_barcode); } else if (form
						 * == 2) {// 二维码(QR) sb.append("2" + "#" + cd_barcode + "#" + Cmap.get("barCode2")); } else if
						 * (form == 3) {// 二维码(PDF417) sb.append("3" + "#" + cd_barcode + "#" + Cmap.get("barCode2")); }
						 */
						if (unitCode != "" && strCompayType.equalsIgnoreCase("CAEP")) {
							// if (unitCode != "" && unitCode.equalsIgnoreCase("5S")) {
							if (form == 1) {// 一维码
								sb.append("1" + "#" + device_barcode + "#" + device_barcode);
							} else if (form == 2) {// 二维码(QR)
								sb.append("2" + "#" + device_barcode + "#" + device_barcode);
							} else if (form == 3) {// 二维码(PDF417)
								sb.append("3" + "#" + device_barcode + "#" + device_barcode);
							}
						} else {
							if (form == 1) {// 一维码
								sb.append("1" + "#" + device_barcode + "#" + device_barcode);
							} else if (form == 2) {// 二维码(QR)
								sb.append("2"
										+ "#"
										+ device_barcode
										+ "#"
										+ basicService.buildTwoDimenosionalBarcode("00", "00", user_name,
												String.valueOf(seclv_code), "D", "H", String.valueOf(page_count),
												"0000", file_title, dept_id, device_barcode));
							} else if (form == 3) {// 二维码(PDF417)
								sb.append("3"
										+ "#"
										+ device_barcode
										+ "#"
										+ basicService.buildTwoDimenosionalBarcode("00", "00", user_name,
												String.valueOf(seclv_code), "D", "H", String.valueOf(page_count),
												"0000", file_title, dept_id, device_barcode));
							}
						}
						String textcontent = sysBarcode.getTextcontent();// 查看条码组成
						sb.append("#");
						if (textcontent != null && textcontent != "") {
							String[] texts = textcontent.split("\\|");
							for (String text : texts) {
								if (text.equalsIgnoreCase("tm")) {
									sb.append(device_barcode + "-");
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
						sb.append(";");
						medium_barcode = medium_barcode + sb.toString();
					}
					cycleitem.setBarcode(device_barcode);
					map.put("medium_serial", device_barcode);
					// map.put("medium_serial", "");
				}
				// 生成载体生命周期记录
				// cycleitem.setBarcode(event.getMedium_serial());
				cycleitem.setOper_time(new Date());
				cycleitem.setUser_name(getCurUser().getUser_name());
				cycleitem.setDept_name(getCurUser().getDept_name());
				cycleitem.setOper("LEADIN");
				// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
				String job_code = enterService.getJobCodeByEventCode(event_code);
				cycleitem.setJob_code(job_code);
				// add ending

				// 共用部分取值
				user_iidd = event.getUser_iidd();
				user_name = event.getUser_name();
				dept_name = event.getDept_name();
				dept_id = event.getDept_id();// 光盘载体部门申请人id
				duty_user_iidd = getCurUser().getUser_iidd();// 责任人为当前部门管理员
				duty_user_name = getCurUser().getUser_name();
				duty_dept_name = getCurUser().getDept_name();
				duty_dept_id = getCurUser().getDept_id();// 光盘载体部门责任人id
				seclv_code = event.getSeclv_code();
				project_code = event.getProject_code();
				page_count = event.getPage_num();
				create_type = "LEADIN";
				scope = event.getScope();
				scope_dept_id = event.getScope_dept_id();
				scope_dept_name = event.getScope_dept_name();

				EventTransfer transfer = null;

				// 根据密级查询回收期限，以当前时间加回收期限得出回收提醒时间。如果为0说明不需要回收，回收提醒时间为默认空值
				sysSeclevel = basicService.getSysSecLevelByCode(seclv_code);
				map.put("archive_time", sysSeclevel.getArchive_time());
				map.put("period", event.getPeriod());
				map.put("expire_time", TimeUtil.getAfterXDay(sysSeclevel.getArchive_time()));
				// 查询载体短期留用时间,短期留用回收提醒时间为当前时间加上设置的留用时间
				SysConfigItem sysconfig = basicService.getSysConfigItemValue("IMPORT_SHORT_DAYS");
				map.put("expire_time_short", TimeUtil.getAfterXDay(Integer.parseInt(sysconfig.getItem_value())));

				if (file_type == 1) {
					// 入纸质载体台帐
					EntityPaper paper = null;
					file_title = event.getZipfile();
					if (event.getScope().equals("PERSON") && !event.getUser_iidd().equals(duty_user_iidd)
							&& ModuleEnum.TRANSFER.isModuleEnable() && basicService.isConfirmOpen("ENTER_CONFIRM")) {// 需要流转
						paper_state = 5;// 流转中
						paper = new EntityPaper(paper_barcode, event_code, user_iidd, user_name, dept_id, dept_name,
								duty_user_iidd, duty_user_name, duty_dept_id, duty_dept_name, seclv_code, "",
								new Date(), "", "1", file_title, project_code, "", "", page_count, null, null, null,
								null, paper_state, "", "", "", null, null, "", "", null, "", "", null, "", "", "",
								create_type, scope, scope_dept_id, scope_dept_name);
						// 生成流转记录
						transfer = new EventTransfer();
						String transfer_code = getCurUser().getUser_iidd() + "-TRANSFER-" + System.currentTimeMillis();
						transfer.setEvent_code(transfer_code);
						transfer.setUser_iidd(getCurUser().getUser_iidd());
						transfer.setUser_name(getCurUser().getUser_name());
						transfer.setDept_id(getCurUser().getDept_id());
						transfer.setDept_name(getCurUser().getDept_name());
						transfer.setSeclv_code(event.getSeclv_code());
						// transfer.setBarcode(event.getMedium_serial());
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
					} else {
						paper_state = 0;// 留用
						paper = new EntityPaper(paper_barcode, event_code, user_iidd, user_name, dept_id, dept_name,
								user_iidd, user_name, dept_id, dept_name, seclv_code, "", new Date(), "", "1",
								file_title, project_code, "", "", page_count, null, null, null, null, paper_state, "",
								"", "", null, null, "", "", null, "", "", null, "", "", "", create_type, scope,
								scope_dept_id, scope_dept_name, company, publish_type, company_send, urgency_lv,
								filed_date, publish_limits, summ);
					}

					cycleitem.setEntity_type("PAPER");

					enterService.querryEnterOpers("PAPER", map, paper, null, cycleitem, transfer, event.getScope());

					if ((src_code != null) && (!src_code.equals(""))) {
						enterService.updateSrccodeByBarcode(src_code, paper_barcode, "PAPER");
					}
					if (!confidential_num.isEmpty()) {
						enterService.updateConfidentialnumByBarcode(confidential_num, paper_barcode, "PAPER");
					}
					insertCommonLog("录入文件[" + paper_barcode + "]");
					// return SUCCESS;
				} else if (file_type == 2) {
					// 入光盘载体台帐
					EntityCD cd = null;
					file_list = event.getZipfile();
					if (event.getScope().equals("PERSON") && !event.getUser_iidd().equals(duty_user_iidd)
							&& ModuleEnum.TRANSFER.isModuleEnable() && basicService.isConfirmOpen("ENTER_CONFIRM")) {// 需要流转
						cd_state = 5;// 流转中
						cd = new EntityCD(cd_barcode, event_code, user_iidd, user_name, dept_id, dept_name,
								duty_user_iidd, duty_user_name, duty_dept_id, duty_dept_name, seclv_code, "",
								new Date(), "", "", "", "", null, file_list, null, "N", "1", null, project_code,
								cd_state, "", create_type, scope, "", "", "", "", null, null, "", "", "", null, "", "",
								"", null, "", "", scope_dept_id, scope_dept_name);
						// 生成流转记录
						transfer = new EventTransfer();
						String transfer_code = getCurUser().getUser_iidd() + "-TRANSFER-" + System.currentTimeMillis();
						transfer.setEvent_code(transfer_code);
						transfer.setUser_iidd(getCurUser().getUser_iidd());
						transfer.setUser_name(getCurUser().getUser_name());
						transfer.setDept_id(getCurUser().getDept_id());
						transfer.setDept_name(getCurUser().getDept_name());
						transfer.setSeclv_code(event.getSeclv_code());
						// transfer.setBarcode(event.getMedium_serial());
						transfer.setBarcode(cd_barcode);
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
						transfer.setEntity_type(LedgerService.DISK);
					} else {
						cd_state = 0;// 留用
						cd = new EntityCD(cd_barcode, event_code, user_iidd, user_name, dept_id, dept_name, user_iidd,
								user_name, dept_id, dept_name, seclv_code, "", new Date(), "", "", "", "", null,
								file_list, null, "N", "1", null, project_code, cd_state, "", create_type, scope, "",
								"", "", "", null, null, "", "", "", null, "", "", "", null, "", "", scope_dept_id,
								scope_dept_name);
					}

					cycleitem.setEntity_type("CD");
					enterService.querryEnterOpers("CD", map, null, cd, cycleitem, transfer, event.getScope());

					if ((src_code != null) && (!src_code.equals(""))) {
						enterService.updateSrccodeByBarcode(src_code, cd_barcode, "CD");
					}
					if (!confidential_num.isEmpty()) {
						enterService.updateConfidentialnumByBarcode(confidential_num, cd_barcode, "CD");
					}
					insertCommonLog("录入光盘[" + cd_barcode + "]");
					// return SUCCESS;
				} else if (file_type == 4) {
					String device_code = "DVC-" + String.valueOf(UUID.randomUUID().toString());
					String device_series = src_code;
					String device_name = file_list = event.getZipfile();

					EntityDevice device = new EntityDevice(device_code, device_name, user_iidd, dept_id, device_series,
							device_barcode, new Date(), 0, seclv_code, user_iidd, summ, "", "", "");

					device.setDeviceStatus(DeviceStatusEnum.DS9); // 个人磁介质的留用状态为9，申请销毁状态为8，已销毁状态为5

					// 生成载体生命周期记录
					CycleItem cycleitem1 = new CycleItem();
					cycleitem1.setBarcode(device_barcode);
					cycleitem1.setEntity_type("DEVICE");
					cycleitem1.setOper_time(new Date());
					cycleitem1.setUser_name(getCurUser().getUser_name());
					cycleitem1.setDept_name(getCurUser().getDept_name());
					cycleitem1.setOper("LEADIN");
					// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
					// 此处不用启动流程，job_code设置为default
					String job_code_4 = enterService.getJobCodeByEventCode(event_code);
					cycleitem1.setJob_code(job_code_4);

					enterService.addEntityDevice(device, cycleitem1, map);
					if (!confidential_num.isEmpty()) {
						enterService.updateConfidentialnumByBarcode(confidential_num, device_code, "DEVICE");
					}
					insertCommonLog("录入个人磁介质[" + device_barcode + "]");

				}
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("Exception" + e.getMessage());
			throw new Exception("执行录入操作出现异常");
		}

	}
}
