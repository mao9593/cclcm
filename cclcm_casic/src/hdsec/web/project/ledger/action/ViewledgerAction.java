package hdsec.web.project.ledger.action;

import hdsec.web.project.basic.mapper.BasicMapper;
import hdsec.web.project.basic.model.SysSeclevel;
import hdsec.web.project.common.util.ExcelImportUtil;
import hdsec.web.project.common.util.StringUtil;
import hdsec.web.project.common.util.TimeUtil;
import hdsec.web.project.exception.CellBlankException;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityStateEnum;
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
 * 光盘总台账列表
 * 
 * @author yueying
 * 
 */
public class ViewledgerAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	@Resource
	private BasicMapper basicMapper;
	private List<EntityCD> cdLedgerList = null;
	private String chooseChildDept = "no";// 是否选择子部门
	private String cd_barcode = "";
	private String duty_user_name = "";
	private String duty_user_iidd = "";
	private String seclv_code = "";
	private Date start_time = null;
	private Date end_time = null;
	private String cd_state = "";
	private Integer data_type = null;
	private String dept_id = "";
	private String dept_name = "";
	private String create_type = "";
	private String researchFlag = "N";
	private String conf_code = "";
	private Integer expire_status = null;
	private String user_name = "";
	private String duty_dept_id = "";
	private String duty_dept_name = "";
	private String dept_iidd = "";
	public List<String> dept_ids = new ArrayList<String>();
	private String imFlag = "N";
	private String uploadFileName = "";
	private File upload = null;

	public String getChooseChildDept() {
		return chooseChildDept;
	}

	public void setChooseChildDept(String chooseChildDept) {
		this.chooseChildDept = chooseChildDept;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public List<EntityCD> getCDLedgerList() {
		return cdLedgerList;
	}

	public void setCd_barcode(String cd_barcode) {
		this.cd_barcode = cd_barcode.trim();
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getCd_barcode() {
		return cd_barcode;
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public Integer getData_type() {
		return data_type;
	}

	public void setData_type(Integer data_type) {
		this.data_type = data_type;
	}

	public List<EntityCD> getCdLedgerList() {
		return cdLedgerList;
	}

	public String getStart_time() {

		return sdf.format(start_time);
	}

	public String getEnd_time() {
		return sdf.format(end_time);
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

	public String getCd_state() {
		return cd_state;
	}

	public void setCd_state(String cd_state) {
		this.cd_state = cd_state;
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

	public String getConf_code() {
		return conf_code;
	}

	public void setConf_code(String conf_code) {
		this.conf_code = conf_code.trim();
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

	public void setImFlag(String imFlag) {
		this.imFlag = imFlag;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUpload(File upload) {
		this.upload = upload;
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
			List<EntityCD> entityList = new ArrayList<EntityCD>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cd_barcode", cd_barcode);
			map.put("duty_user_name", duty_user_name);
			map.put("duty_user_iidd", duty_user_iidd);

			map.put("chooseChildDept", chooseChildDept);

			map.put("start_time", start_time);
			map.put("end_time", end_time);
			map.put("data_type", data_type);
			map.put("cd_state", cd_state);
			map.put("create_type", create_type);
			map.put("conf_code", conf_code);
			map.put("expire_status", expire_status);
			map.put("expire_time", new Date());
			map.put("user_name", user_name);
			map.put("dept_CS", dept_cs);
			// map.put("duty_dept_id", duty_dept_id);
			map.put("dept_iidd", dept_iidd);
			map.put("dept_name", dept_name);
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
			/*
			 * totalSize = ledgerService.getAllCDLedgerSize(map); List<EntityCD> cds =
			 * ledgerService.getAllCDLedgerList(map, rbs);
			 */

			// 2016.12.05,光盘总台账查询单独用的方法，不与其他查询一起。
			totalSize = ledgerService.getSecAllCDLedgerSize(map);
			List<EntityCD> cds = ledgerService.getSecAllCDLedgerList(map, rbs);

			if ((null != cds && totalSize != 0) || totalSize > cds.size()) {
				cds = ledgerService.getAllCDLedgerList(map);
			}
			// List<EntityCD> cds = ledgerService.getAllCDLedgerList(map);
			for (EntityCD entity : cds) {
				entityList.add(entity);
			}
			cdLedgerList = entityList;
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

			String cd_barcode = null;// 任务号
			String user_name = null;// 用户名
			String user_iidd = null;
			String dept_name = null;// 部门
			String dept_id = null;
			Date burn_time = null;// 刻录时间
			String burn_machine = null;// 刻录终端
			String burn_ipaddress = null;// 终端IP
			// 驱动类型
			String cd_type = null;// 光盘类型
			String seclv_name = null;// 密级
			Integer seclv_code = null;
			// 卷标//用途
			String burn_result_name = null;// 刻录结果
			Integer file_num = null;// 文件数量
			String file_list = null;// 文件列表
			// 文件大小//文件路径//IO类型//刻录人//所属部门
			String cd_state_name = null;// 光盘状态
			String event_code = "single_burn";// 作业ID
			Date expire_time = null;// 到期回收提醒时间

			// 循环文档，跳过第一行标题行
			EntityCD entityCd = null;
			Map<String, Object> map = new HashMap<String, Object>();
			int cd_num = 0;
			for (int num = sheet.getFirstRowNum() + 1; num <= sheet.getLastRowNum(); num++) {
				entityCd = new EntityCD();
				// 分析每一行数据
				HSSFRow row = sheet.getRow(num);
				int column = 1;
				try {
					cd_state_name = getCellStringValue(row.getCell(21), true);
					if (cd_state_name.equals("未回收")) {
						cd_barcode = getCellStringValue(row.getCell(column++), true);
						map.put("cd_barcode", cd_barcode);
						cd_num = ledgerService.getAllCDLedgerSize(map);
						if (cd_num == 0) {
							entityCd.setCd_barcode(cd_barcode);
							user_iidd = getCellStringValue(row.getCell(column++), true);
							entityCd.setUser_iidd(user_iidd);
							user_name = getCellStringValue(row.getCell(column++), true);
							entityCd.setUser_name(user_name);
							entityCd.setDuty_user_name(user_name);
							// user_iidd = ledgerService.getUser_iidd(user_name);
							// entityCd.setUser_iidd(user_iidd);
							entityCd.setDuty_user_iidd(user_iidd);
							dept_name = getCellStringValue(row.getCell(column++), true);
							entityCd.setDept_name(dept_name);
							entityCd.setDuty_dept_name(dept_name);
							dept_id = ledgerService.getDept_id(dept_name);
							entityCd.setDept_id(dept_id);
							entityCd.setDuty_dept_id(dept_id);
							burn_time = formatCellDateValue(row.getCell(column++), true, "yyyy-MM-dd HH:mm:ss");
							entityCd.setBurn_time(burn_time == null ? null : new Date(burn_time.getTime()));
							burn_machine = getCellStringValue(row.getCell(column++), true);
							entityCd.setBurn_machine(burn_machine);
							burn_ipaddress = getCellStringValue(row.getCell(column), true);
							entityCd.setBurn_ipaddress(burn_ipaddress);
							column = column + 2;
							cd_type = getCellStringValue(row.getCell(column++), true);
							entityCd.setCd_type(cd_type);
							column = column + 1;
							// seclv_code = parseCellIntegerValue(row.getCell(column++), true);

							seclv_name = getCellStringValue(row.getCell(column++), true);
							seclv_code = ledgerService.getSeclv_code(seclv_name);
							entityCd.setSeclv_code(seclv_code);
							column = column + 3;
							burn_result_name = getCellStringValue(row.getCell(column++), true);
							if (burn_result_name.equals("失败")) {
								entityCd.setBurn_result("0");
							} else {
								entityCd.setBurn_result("1");
							}
							column = column + 1;
							file_num = parseCellIntegerValue(row.getCell(column++), true);
							entityCd.setFile_num(file_num);
							file_list = getCellStringValue(row.getCell(column), true);
							if (file_list.contains(";")) {
								file_list = file_list.replace(";", ":");
							}
							entityCd.setFile_list(file_list);
							entityCd.setEvent_code(event_code);
							SysSeclevel sysSeclevel = basicMapper.getSysSecLevelByCode(seclv_code);
							if (sysSeclevel.getArchive_time() > 0) {
								expire_time = TimeUtil.getAfterXDay(sysSeclevel.getArchive_time());
							}
							entityCd.setExpire_time(expire_time);
							ledgerService.addEntityCD(entityCd);
						}

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

	public String getDept_iidd() {
		return dept_iidd;
	}

	public void setDept_iidd(String dept_iidd) {
		this.dept_iidd = dept_iidd;
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
