package hdsec.web.project.ledger.action;

import hdsec.web.project.basic.mapper.BasicMapper;
import hdsec.web.project.basic.model.SysSeclevel;
import hdsec.web.project.common.util.ExcelImportUtil;
import hdsec.web.project.common.util.StringUtil;
import hdsec.web.project.common.util.TimeUtil;
import hdsec.web.project.exception.CellBlankException;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.ledger.model.EntityStateEnum;
import hdsec.web.project.ledger.service.LedgerService;
import hdsec.web.project.user.model.SecDept;
import hdsec.web.project.user.model.SecLevel;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.util.StringUtils;

/**
 * 纸质文件总台账列表
 * 
 * @author yueying
 * 
 */
public class ViewPaperledgerAction extends LedgerBaseAction {

	private static final long serialVersionUID = 1L;
	@Resource
	protected LedgerService ledgerService;
	@Resource
	private BasicMapper basicMapper;
	private List<EntityPaper> paperLedgerList;
	private String paper_barcode = "";
	private String duty_user_name = "";
	private String duty_user_iidd = "";
	private String seclv_code = "";
	private Date start_time;
	private Date end_time;
	private String file_title = "";
	private String paper_state = "";
	private String dept_id = "";
	private String dept_name = "";
	private String create_type = "";
	private String researchFlag = "N";
	private String keyword_content = "";
	private Integer expire_status = null;
	private String user_name = "";
	private String duty_dept_id = "";
	private String duty_dept_name = "";
	private String dept_iidd = "";

	private String chooseChildDept = "no";
	private Integer page_count = null;// 页数
	private String imFlag = "N";
	private String uploadFileName = "";
	private File upload = null;

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setImFlag(String imFlag) {
		this.imFlag = imFlag;
	}

	public String getChooseChildDept() {
		return chooseChildDept;
	}

	public void setChooseChildDept(String chooseChildDept) {
		this.chooseChildDept = chooseChildDept;
	}

	public List<String> dept_ids = new ArrayList<String>();

	public List<EntityPaper> getPaperLedgerList() {
		return paperLedgerList;
	}

	public void setPaperLedgerList(List<EntityPaper> paperLedgerList) {
		this.paperLedgerList = paperLedgerList;
	}

	public String getDuty_dept_id() {
		return duty_dept_id;
	}

	public void setDuty_dept_id(String duty_dept_id) {
		this.duty_dept_id = duty_dept_id;
	}

	public String getDuty_dept_name() {
		return duty_dept_name;
	}

	public void setDuty_dept_name(String duty_dept_name) {
		this.duty_dept_name = duty_dept_name;
	}

	public String getPaper_barcode() {
		return paper_barcode;
	}

	public void setPaper_barcode(String paper_barcode) {
		this.paper_barcode = paper_barcode.trim();
	}

	public String getPaper_state() {
		return paper_state;
	}

	public void setPaper_state(String paper_state) {
		this.paper_state = paper_state;
	}

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name.trim();
	}

	public String getDuty_user_iidd() {
		return duty_user_iidd;
	}

	public void setDuty_user_iidd(String duty_user_iidd) {
		this.duty_user_iidd = duty_user_iidd;
	}

	public String getFile_title() {
		return file_title;
	}

	public void setFile_title(String file_title) {
		this.file_title = file_title.trim();
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public String getStart_time() {
		return sdf.format(start_time);
	}

	public String getEnd_time() {
		return sdf.format(end_time);
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public List<EntityStateEnum> getStateList() {
		return EntityStateEnum.getEntityStateList();
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getCreate_type() {
		return create_type;
	}

	public void setCreate_type(String create_type) {
		this.create_type = create_type;
	}

	public String getResearchFlag() {
		return researchFlag;
	}

	public void setResearchFlag(String researchFlag) {
		this.researchFlag = researchFlag;
	}

	public String getKeyword_content() {
		return keyword_content;
	}

	public void setKeyword_content(String keyword_content) {
		this.keyword_content = keyword_content.trim();
	}

	public Integer getExpire_status() {
		return expire_status;
	}

	public void setExpire_status(Integer expire_status) {
		this.expire_status = expire_status;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getDept_iidd() {
		return dept_iidd;
	}

	public void setDept_iidd(String dept_iidd) {
		this.dept_iidd = dept_iidd;
	}

	private Date formatCellDateValue(HSSFCell cell, boolean is_compatible, String pattern) throws Exception {
		return ExcelImportUtil.formatCellDateValue(cell, is_compatible, pattern);
	}

	private String getCellStringValue(HSSFCell cell, boolean is_permit_blank) throws CellBlankException {
		return ExcelImportUtil.getCellStringValue(cell, is_permit_blank);
	}

	private Integer parseCellIntegerValue(HSSFCell cell, boolean is_compatible) throws Exception {
		return ExcelImportUtil.parseCellIntegerValue(cell, is_compatible);
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(dept_id)) {
			researchFlag = "Y";
		}

		String dept_cs = "";
		if (!duty_dept_id.equals("") && duty_dept_id != null) {
			SecDept secDept = userService.getSecDeptById(duty_dept_id);
			dept_cs = secDept.getDept_cs();

		}

		if (researchFlag.equals("Y")) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("paper_barcode", paper_barcode);
			map.put("duty_user_name", duty_user_name);
			map.put("duty_user_iidd", duty_user_iidd);

			map.put("start_time", start_time);
			map.put("end_time", end_time);
			map.put("chooseChildDept", chooseChildDept);
			map.put("file_title", file_title);
			map.put("paper_state", paper_state);
			map.put("create_type", create_type);
			// map.put("keyword_content", keyword_content);
			map.put("expire_status", expire_status);
			map.put("expire_time", new Date());
			map.put("user_name", user_name);
			map.put("dept_CS", dept_cs);
			// map.put("duty_dept_id", duty_dept_id);
			map.put("dept_iidd", dept_iidd);
			map.put("coming_expire_time", TimeUtil.getAfterXDay(2));
			if (StringUtils.hasLength(dept_id)) {
				List<String> deptIds = StringUtil.stringArrayToList(dept_id.split(","));
				getDeptId(deptIds);
				map.put("dept_ids", dept_ids);
			}
			if (StringUtils.hasLength(seclv_code)) {
				map.put("seclv_codes", seclv_code.split(","));
			}
			String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
			if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
				page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
			}
			beginIndex = page * pageSize;
			RowBounds rbs = new RowBounds(beginIndex, pageSize);
			// totalSize = ledgerService.getAllPaperLedgerSize(map);
			// List<EntityPaper> listPaperLedger = ledgerService.getAllPaperLedgerList(map, rbs);

			// 2016.10.20,文件总台账查询单独用的方法，不与其他查询一起。
			totalSize = ledgerService.getSecAllPaperLedgerSize(map);
			List<EntityPaper> listPaperLedger = ledgerService.getSecAllPaperLedgerList(map, rbs);

			// 如果该文件被替换，则打印方式改为“替换页打印”
			List<String> paperBarcodeList = ledgerService.getPIDBarcode();
			List<EntityPaper> paper = new ArrayList<EntityPaper>();
			for (int i = 0; i < listPaperLedger.size(); i++) {
				EntityPaper entity_paper = listPaperLedger.get(i);
				if (paperBarcodeList.contains(entity_paper.getPaper_barcode())) {
					entity_paper.setCreate_type("REPLACEPRINT");
					paper.add(entity_paper);
				} else {
					paper.add(entity_paper);
				}
			}
			paperLedgerList = paper;

			researchFlag = "Y";
		} else {
			researchFlag = "WARING";
		}

		if (imFlag.equalsIgnoreCase("Y")) {
			if (!uploadFileName.endsWith("xls")) {
				throw new Exception("上传的文件类型错误，请上传导出EXCEL文件");
			}
			// 读取UserOperationLog.xls文件，逐行添加进数据库历史表
			InputStream myxls = new FileInputStream(upload);
			HSSFWorkbook wb = new HSSFWorkbook(myxls);
			HSSFSheet sheet = wb.getSheetAt(0);// 第一个表单

			String paper_barcode = null;// 文件条码
			String user_name = null;// 用户名
			String user_iidd = null;
			String dept_name = null;// 部门
			String dept_id = null;
			Date print_time = null;// 打印时间
			String printer_name = null;// 打印机名称
			String page_size = null;// 纸张大小
			Integer color = null;// 颜色
			Integer print_double = null;// 单双面

			String seclv_name = null;// 密级
			Integer seclv_code = null;
			String print_result_name = null;// 打印结果
			Integer seclv_code_temp = null;
			String file_title = null;// 文件名
			String event_code = "single_print";// 作业ID
			Date expire_time = null;// 到期回收提醒时间

			// 循环文档，跳过第一行标题行
			EntityPaper entityPaper = null;
			Map<String, Object> map = new HashMap<String, Object>();
			int paper_num = 0;
			for (int num = sheet.getFirstRowNum() + 1; num <= sheet.getLastRowNum(); num++) {
				entityPaper = new EntityPaper();
				// 分析每一行数据
				HSSFRow row = sheet.getRow(num);
				int column = 1;
				try {
					paper_barcode = getCellStringValue(row.getCell(7), true);
					map.put("paper_barcode", paper_barcode);
					paper_num = ledgerService.getAllPaperLedgerSize(map);
					if (paper_num == 0) {
						entityPaper.setPaper_barcode(paper_barcode);
						user_iidd = getCellStringValue(row.getCell(column++), true); // 1 2
						entityPaper.setUser_iidd(user_iidd);
						user_name = getCellStringValue(row.getCell(column++), true); // 2 3
						entityPaper.setUser_name(user_name);
						entityPaper.setDuty_user_name(user_name);
						entityPaper.setDuty_user_iidd(user_iidd);
						print_time = formatCellDateValue(row.getCell(column++), true, "yyyy-MM-dd HH:mm:ss"); // 3 4
						entityPaper.setPrint_time(print_time == null ? null : new Date(print_time.getTime()));
						column = column + 1;// 5
						file_title = getCellStringValue(row.getCell(column++), true);// 5 6
						entityPaper.setFile_title(file_title);
						seclv_code_temp = parseCellIntegerValue(row.getCell(column++), true); // 6 7
						switch (seclv_code_temp) {
						case 1:
							seclv_code = 1;// 机密
							break;
						case 2:
							seclv_code = 3;// 秘密
							break;
						case 3:
							seclv_code = 5;// 内部
						case 4:
							seclv_code = 6;// 公开
							break;
						}
						entityPaper.setSeclv_code(seclv_code);
						seclv_name = userService.getSecLevelByCode(seclv_code).getSeclv_name();
						entityPaper.setSeclv_name(seclv_name);
						column = column + 1; // 8
						printer_name = getCellStringValue(row.getCell(column++), true);
						entityPaper.setPrinter_name(printer_name);
						print_result_name = getCellStringValue(row.getCell(column++), true);// 9 10
						if (print_result_name.equals("打印成功")) {
							entityPaper.setPrint_result("1");
						} else {
							entityPaper.setPrint_result("0");
						}
						page_count = parseCellIntegerValue(row.getCell(column++), true);// 10 11
						entityPaper.setPage_count(page_count);
						entityPaper.setEvent_code(event_code);
						SysSeclevel sysSeclevel = basicMapper.getSysSecLevelByCode(seclv_code);
						if (sysSeclevel.getArchive_time() > 0) {
							expire_time = TimeUtil.getAfterXDay(sysSeclevel.getArchive_time());
						}
						entityPaper.setExpire_time(expire_time);
						dept_name = userService.getDeptNameByUserId(user_iidd);
						entityPaper.setDept_name(dept_name);
						entityPaper.setDuty_dept_name(dept_name);
						dept_id = ledgerService.getDept_id(dept_name);
						entityPaper.setDept_id(dept_id);
						entityPaper.setDuty_dept_id(dept_id);
						column = column + 3;// 14
						page_size = getCellStringValue(row.getCell(column++), true);// 14 15
						entityPaper.setPage_size(page_size);
						color = parseCellIntegerValue(row.getCell(column++), true);// 15 16
						entityPaper.setColor(color);
						print_double = parseCellIntegerValue(row.getCell(column), true);
						entityPaper.setPrint_double(print_double);

						ledgerService.addEntityPaper(entityPaper);

					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("解析用户数据出现异常：行[" + (num + 1) + "]，列[" + column + "]");
					logger.error(e.getMessage());
					continue;
				}
			}
			insertAdminLog("批量导入用户操作日志");
		}

		return SUCCESS;
	}

	public List<String> getList(String id) {
		List<String> str = new ArrayList<String>();
		str = ledgerService.getDeptIdByParentId(id);
		return str;
	}

	/**
	 * 根据父节点的id获得所有子节点的id以及子节点的子节点id
	 * 
	 * @param ids
	 */
	public void getDeptId(List<String> ids) {
		if (ids != null || ids.size() > 0) {
			for (String id : ids) {
				dept_ids.add(id);
				List<String> list = getList(id);
				getDeptId(list);
			}
		}
		return;
	}
}
