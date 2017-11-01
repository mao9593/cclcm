package hdsec.web.project.user.action;

import hdsec.web.project.common.util.ExcelImportUtil;
import hdsec.web.project.common.util.MD5;
import hdsec.web.project.exception.CellBlankException;
import hdsec.web.project.exception.CellValueWrongException;
import hdsec.web.project.user.model.SecUser;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.util.StringUtils;

/**
 * 批量导入用户 2014-5-1 下午3:42:07
 * 
 * @author renmingfei
 */
public class ImportSecUserAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private File upload = null;
	private String done = "N";
	private List<Integer> existList = new ArrayList<Integer>();
	private List<Integer> lackList = new ArrayList<Integer>();
	private List<Integer> secWrongList = new ArrayList<Integer>();
	private List<Integer> deptWrongList = new ArrayList<Integer>();
	private List<Integer> failList = new ArrayList<Integer>();
	private int successNum = 0;
	private String uploadFileName = "";
	private String item_key = "DEFAULT_PASSWORD";// 默认密码
	private String user_ppww = "";

	public void setDone(String done) {
		this.done = done;
	}

	public String getDone() {
		return done;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getExistList() {
		return existList.toString();
	}

	public String getLackList() {
		return lackList.toString();
	}

	public String getSecWrongList() {
		return secWrongList.toString();
	}

	public String getDeptWrongList() {
		return deptWrongList.toString();
	}

	public String getFailList() {
		return failList.toString();
	}

	public int getSuccessNum() {
		return successNum;
	}

	public String getItem_key() {
		return item_key;
	}

	public void setItem_key(String item_key) {
		this.item_key = item_key;
	}

	public String getUser_ppww() {
		return user_ppww;
	}

	public void setUser_ppww(String user_ppww) {
		this.user_ppww = user_ppww;
	}

	private String getCellStringValue(HSSFCell cell, boolean is_permit_blank) throws CellBlankException {
		return ExcelImportUtil.getCellStringValue(cell, is_permit_blank);
	}

	private Date formatCellDateValue(HSSFCell cell, boolean is_compatible, String pattern) throws Exception {
		return ExcelImportUtil.formatCellDateValue(cell, is_compatible, pattern);
	}

	private Integer parseCellIntegerValue(HSSFCell cell, boolean is_compatible) throws Exception {
		return ExcelImportUtil.parseCellIntegerValue(cell, is_compatible);
	}

	@Override
	public String executeFunction() throws Exception {
		if (done.equalsIgnoreCase("Y")) {
			if (!uploadFileName.endsWith("xls")) {
				throw new Exception("上传的文件类型错误，请上传EXCEL文件");
			}

			// 查询普通用户角色ID，导入用户默认角色为普通用户
			String workerRoleId = userService.getWorkerRoleId();
			InputStream myxls = new FileInputStream(upload);
			HSSFWorkbook wb = new HSSFWorkbook(myxls);
			HSSFSheet sheet = wb.getSheetAt(0);// 第一个表单
			if (sheet.getLastRowNum() > userService.getLastNumberCount()) {
				throw new Exception("用户添加已达到上限，如需添加用户，请继续购买人数！");
			}
			String user_iidd = null;// 用户登录ID
			String user_name = null;// 用户名称
			String security_name = null;// 涉密人员类别
			String dept_name = null;// 部门名称
			// String user_ppww = null;// 登录密码
			String user_sex = null;// 用户性别
			Date tempDate = null;// 日期数据
			// 循环文档，跳过第一行标题行
			for (int num = sheet.getFirstRowNum() + 1; num <= sheet.getLastRowNum(); num++) {
				SecUser secUser = new SecUser();
				// 分析每一行数据
				HSSFRow row = sheet.getRow(num);
				int column = 0;
				try {
					user_iidd = getCellStringValue(row.getCell(column++), false);
					if (userService.isUserExist(user_iidd)) {
						existList.add(num + 1);
						throw new CellValueWrongException("用户[" + user_iidd + "]已经存在，不能重复导入");
					}
					secUser.setUser_iidd(user_iidd);
					secUser.setReal_user_id(user_iidd);
					user_name = getCellStringValue(row.getCell(column++), false);
					secUser.setUser_name(user_name);
					security_name = getCellStringValue(row.getCell(column++), false);
					String security_code = userService.getSecurityCodeByName(security_name);
					if (StringUtils.hasLength(security_code)) {
						secUser.setSecurity_code(security_code);
					} else {
						secWrongList.add(num + 1);
						throw new CellValueWrongException("不存在涉密人员类别[" + security_name + "]");
					}
					dept_name = getCellStringValue(row.getCell(column++), false);
					String dept_id = userService.getDeptIdByName(dept_name);
					if (StringUtils.hasLength(dept_id)) {
						secUser.setDept_id(dept_id);
					} else {
						deptWrongList.add(num + 1);
						throw new CellValueWrongException("不存在部门[" + dept_name + "]");
					}
					user_ppww = getCellStringValue(row.getCell(column++), true);
					if (user_ppww.isEmpty()) {
						// user_ppww = MD5.getMD5Str(String.valueOf(System.currentTimeMillis()));
						String defaultPassword = userService.getSysConfigItemValue(item_key);
						setUser_ppww(MD5.getMD5Str(defaultPassword));

					} else {
						user_ppww = MD5.getMD5Str(user_ppww);
					}
					secUser.setUser_ppww(user_ppww);
					secUser.setIdCard(getCellStringValue(row.getCell(column++), true));
					secUser.setPass_num(getCellStringValue(row.getCell(column++), true));
					secUser.setBase_username(getCellStringValue(row.getCell(column++), true));
					secUser.setJob_passnum(getCellStringValue(row.getCell(column++), true));
					secUser.setJob_employtype(getCellStringValue(row.getCell(column++), true));
					user_sex = getCellStringValue(row.getCell(column++), true);
					if (user_sex.equals("男")) {
						secUser.setBase_sex("M");
					} else if (user_sex.equals("女")) {
						secUser.setBase_sex("F");
					} else {
						secUser.setBase_sex(null);
					}
					secUser.setBase_country(getCellStringValue(row.getCell(column++), true));
					secUser.setBase_nation(getCellStringValue(row.getCell(column++), true));
					secUser.setBase_nativeplace(getCellStringValue(row.getCell(column++), true));
					secUser.setBase_birthplace(getCellStringValue(row.getCell(column++), true));
					tempDate = formatCellDateValue(row.getCell(column++), true, "yyyy-MM-dd");
					secUser.setBase_birthday(tempDate == null ? null : new java.sql.Date(tempDate.getTime()));
					secUser.setBase_politics(getCellStringValue(row.getCell(column++), true));
					tempDate = formatCellDateValue(row.getCell(column++), true, "yyyy-MM-dd");
					secUser.setBase_joinpartytime(tempDate == null ? null : new java.sql.Date(tempDate.getTime()));
					secUser.setEdu_education(getCellStringValue(row.getCell(column++), true));
					secUser.setEdu_degree(getCellStringValue(row.getCell(column++), true));
					secUser.setEdu_school(getCellStringValue(row.getCell(column++), true));
					secUser.setEdu_major(getCellStringValue(row.getCell(column++), true));
					secUser.setEdu_language(getCellStringValue(row.getCell(column++), true));
					secUser.setEdu_familiarity(getCellStringValue(row.getCell(column++), true));
					secUser.setCom_residency(getCellStringValue(row.getCell(column++), true));
					secUser.setCom_police(getCellStringValue(row.getCell(column++), true));
					secUser.setCom_address(getCellStringValue(row.getCell(column++), true));
					secUser.setCom_telephone(getCellStringValue(row.getCell(column++), true));
					secUser.setCom_mobile(getCellStringValue(row.getCell(column++), true));
					secUser.setCom_email(getCellStringValue(row.getCell(column++), true));
					secUser.setJob_category(getCellStringValue(row.getCell(column++), true));
					secUser.setJob_seclevel(getCellStringValue(row.getCell(column++), true));
					secUser.setJob_adminpost(getCellStringValue(row.getCell(column++), true));
					secUser.setJob_techpost(getCellStringValue(row.getCell(column++), true));
					secUser.setJob_techtitle(getCellStringValue(row.getCell(column++), true));
					secUser.setJob_humansort(getCellStringValue(row.getCell(column++), true));
					tempDate = formatCellDateValue(row.getCell(column++), true, "yyyy-MM-dd");
					secUser.setJob_insectime(tempDate == null ? null : new java.sql.Date(tempDate.getTime()));
					secUser.setJob_workyears(parseCellIntegerValue(row.getCell(column++), true));
					secUser.setJob_passlevel(getCellStringValue(row.getCell(column++), true));
					tempDate = formatCellDateValue(row.getCell(column++), true, "yyyy-MM-dd");
					secUser.setJob_inposttime(tempDate == null ? null : new java.sql.Date(tempDate.getTime()));
					userService.addSecUser(secUser, false, workerRoleId);
					successNum++;
				} catch (CellBlankException e) {
					logger.error("检测到必填数据为空：行[" + (num + 1) + "]，列[" + column + "]");
					lackList.add(num + 1);
					continue;
				} catch (CellValueWrongException e) {
					logger.error(e.getMessage());
					continue;
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("解析用户数据出现异常：行[" + (num + 1) + "]，列[" + column + "]");
					logger.error(e.getMessage());
					failList.add(num + 1);
					continue;
				}
			}
			insertAdminLog("批量导入用户");
		}
		return SUCCESS;
	}
}
