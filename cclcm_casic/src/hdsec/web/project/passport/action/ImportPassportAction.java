package hdsec.web.project.passport.action;

import hdsec.web.project.common.util.ExcelImportUtil;
import hdsec.web.project.exception.CellBlankException;
import hdsec.web.project.exception.CellValueWrongException;
import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.passport.model.EntityPassport;

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

public class ImportPassportAction extends PassportBaseAction {

	/**
	 * 护照台账导入
	 * 
	 * @author gaoyiming
	 */
	private static final long serialVersionUID = 1L;
	private File upload = null;
	private String done = "N";
	private int successNum = 0;
	private String uploadFileName = "";

	private List<Integer> passportWrongList = new ArrayList<Integer>();// 护照名称错误
	private List<Integer> typeWrongList = new ArrayList<Integer>(); // 类型错误
	private List<Integer> medWrongList = new ArrayList<Integer>(); // 设备类型错误
	private List<Integer> userWrongList = new ArrayList<Integer>(); // 责任人ID错误
	private List<Integer> deptWrongList = new ArrayList<Integer>(); // 部门ID错误
	private List<Integer> lackList = new ArrayList<Integer>();
	private List<Integer> failList = new ArrayList<Integer>();
	private List<Integer> statusWrongList = new ArrayList<Integer>();

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getDone() {
		return done;
	}

	public void setDone(String done) {
		this.done = done;
	}

	public int getSuccessNum() {
		return successNum;
	}

	public void setSuccessNum(int successNum) {
		this.successNum = successNum;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public List<Integer> getPassportWrongList() {
		return passportWrongList;
	}

	public void setPassportWrongList(List<Integer> passportWrongList) {
		this.passportWrongList = passportWrongList;
	}

	public List<Integer> getTypeWrongList() {
		return typeWrongList;
	}

	public void setTypeWrongList(List<Integer> typeWrongList) {
		this.typeWrongList = typeWrongList;
	}

	public List<Integer> getMedWrongList() {
		return medWrongList;
	}

	public void setMedWrongList(List<Integer> medWrongList) {
		this.medWrongList = medWrongList;
	}

	public List<Integer> getUserWrongList() {
		return userWrongList;
	}

	public void setUserWrongList(List<Integer> userWrongList) {
		this.userWrongList = userWrongList;
	}

	public List<Integer> getDeptWrongList() {
		return deptWrongList;
	}

	public void setDeptWrongList(List<Integer> deptWrongList) {
		this.deptWrongList = deptWrongList;
	}

	public List<Integer> getLackList() {
		return lackList;
	}

	public void setLackList(List<Integer> lackList) {
		this.lackList = lackList;
	}

	public List<Integer> getFailList() {
		return failList;
	}

	public void setFailList(List<Integer> failList) {
		this.failList = failList;
	}

	public List<Integer> getStatusWrongList() {
		return statusWrongList;
	}

	public void setStatusWrongList(List<Integer> statusWrongList) {
		this.statusWrongList = statusWrongList;
	}

	private String getCellStringValue(HSSFCell cell, boolean is_permit_blank) throws CellBlankException {
		return ExcelImportUtil.getCellStringValue(cell, is_permit_blank);
	}

	private Date formatCellDateValue(HSSFCell cell, boolean is_compatible, String pattern) throws Exception {
		return ExcelImportUtil.formatCellDateValue(cell, is_compatible, pattern);
	}

	/*
	 * private Integer getCellIntegerValue(HSSFCell cell, boolean is_permit_blank) throws CellBlankException { return
	 * ExcelImportUtil.getCellIntegerValue(cell, is_permit_blank); }
	 */
	@Override
	public String executeFunction() throws Exception {

		if (done.equalsIgnoreCase("Y")) {
			if (!uploadFileName.endsWith("xls")) {
				throw new Exception("上传的文件类型错误，请上传EXCEL文件");
			}
			InputStream myxls = new FileInputStream(upload);
			HSSFWorkbook wb = new HSSFWorkbook(myxls);
			HSSFSheet sheet = wb.getSheetAt(0);// 第一个表单

			String user_iidd = ""; // 操作人ID
			String dept_id = "";// 操作人部门ID
			String passport_num = "";// 护照编号
			Integer passport_type;// 护照类型
			String passport_type_name = "";// 护照类型名称
			Date borrow_time = null;// 借用时间
			Date return_time = null;// 归还时间
			Integer passport_status;// 护照状态
			String passport_status_name = "";// 护照状态名称
			String summ = "";// 备注
			String duty_user_iidd = ""; // 责任人ID
			String duty_user_name = "";// 责任人名称
			String duty_dept_id = ""; // 责任部门ID
			String duty_dept_name = "";// 责任部门名称
			Date issuing_date = null;// 签发日期
			Date ending_date = null;// 结束日期
			String issuing_authority = "";// 签发机关

			for (int num = sheet.getFirstRowNum() + 1; num <= sheet.getLastRowNum(); num++) {
				HSSFRow row = sheet.getRow(num);
				int column = 0;
				try {
					// 判断护照编号是否为空或超长
					passport_num = getCellStringValue(row.getCell(column++), false);
					if (passport_num.length() < 1) {
						passportWrongList.add(num + 1);
						throw new CellValueWrongException("护照编号[" + passport_num + "]为空，请填写对应信息");
					}

					// 判断护照类型是否存在
					passport_type_name = getCellStringValue(row.getCell(column++), true);

					if (passport_type_name.equals("普通护照")) {
						passport_type = 0;
					} else if (passport_type_name.equals("外交护照")) {
						passport_type = 1;
					} else if (passport_type_name.equals("公务护照")) {
						passport_type = 2;
					} else if (passport_type_name.equals("港澳通行证")) {
						passport_type = 3;
					} else if (passport_type_name.equals("大陆居民来往台湾地区通行证")) {
						passport_type = 4;
					} else {
						typeWrongList.add(num + 1);
						throw new CellValueWrongException("护照状态[" + passport_type_name + "]非法，请核实后填写");
					}

					// 判断护照状态是否正确
					passport_status_name = getCellStringValue(row.getCell(column++), true);

					if (passport_status_name.equals("在册")) {
						passport_status = 0;
					} else if (passport_status_name.equals("借出")) {
						passport_status = 1;
					} else if (passport_status_name.equals("删除")) {
						passport_status = 2;
					} else {
						statusWrongList.add(num + 1);
						throw new CellValueWrongException("护照状态[" + passport_status_name + "]非法，请核实后填写");
					}

					// 判断责任人是否存在
					duty_user_iidd = getCellStringValue(row.getCell(column++), false);
					if (!userService.isUserExist(duty_user_iidd)) {
						userWrongList.add(num + 1);
						throw new CellValueWrongException("责任人[" + duty_user_iidd + "]不存在，请核对后导入");
					}
					// 判断责任部门是否存在
					duty_dept_name = getCellStringValue(row.getCell(column++), true);
					if (StringUtils.hasLength(duty_dept_name)) {
						duty_dept_id = userService.getDeptIdByName(duty_dept_name); // 获取部门ID
						if (!StringUtils.hasLength(duty_dept_id)) {
							deptWrongList.add(num + 1);
							throw new CellValueWrongException("不存在部门[" + duty_dept_name + "]");
						}
					}

					summ = getCellStringValue(row.getCell(column++), true);
					issuing_date = formatCellDateValue(row.getCell(column++), true, "yyyy-MM-dd");
					ending_date = formatCellDateValue(row.getCell(column++), true, "yyyy-MM-dd");
					issuing_authority = getCellStringValue(row.getCell(column++), true);

					duty_user_name = userService.getUserNameByUserId(duty_user_iidd);

					EntityPassport passport = new EntityPassport();
					passport.setBorrow_time(borrow_time);
					passport.setDept_id(getCurUser().getDept_id());
					passport.setDuty_dept_id(duty_dept_id);
					passport.setDuty_dept_name(duty_dept_name);
					passport.setDuty_user_iidd(duty_user_iidd);
					passport.setDuty_user_name(duty_user_name);
					passport.setPassport_num(passport_num);
					passport.setPassport_status(passport_status);
					passport.setPassport_type(passport_type);
					passport.setReturn_time(return_time);
					passport.setSumm(summ);
					passport.setUser_iidd(getCurUser().getUser_iidd());
					passport.setIssuing_date(issuing_date);
					passport.setEnding_date(ending_date);
					passport.setIssuing_authority(issuing_authority);

					// 生成护照生命周期记录
					CycleItem cycleitem = new CycleItem();
					cycleitem.setEntity_type("PASSPORT");
					cycleitem.setOper_time(new Date());
					cycleitem.setUser_name(getCurUser().getUser_name());
					cycleitem.setDept_name(getCurUser().getDept_name());
					cycleitem.setOper("LEADIN");
					passportService.addEntityPassport(passport, cycleitem);
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
					logger.error("解析护照数据出现异常：行[" + (num + 1) + "]，列[" + column + "]");
					logger.error(e.getMessage());
					failList.add(num + 1);
					continue;
				}

			}
			insertCommonLog("批量导入护照");

		}
		return SUCCESS;

	}
}
