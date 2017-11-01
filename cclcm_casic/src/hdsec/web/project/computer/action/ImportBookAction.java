package hdsec.web.project.computer.action;

import hdsec.web.project.common.bm.BMCycleItem;
import hdsec.web.project.common.util.ExcelImportUtil;
import hdsec.web.project.computer.model.EntityBook;
import hdsec.web.project.exception.CellBlankException;
import hdsec.web.project.exception.CellValueWrongException;
import hdsec.web.project.user.mapper.UserMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.util.StringUtils;

public class ImportBookAction extends ComputerBaseAction {

	private static final long serialVersionUID = 1L;
	private File upload = null;
	private String done = "N";
	private int successNum = 0;
	private String uploadFileName = "";
	private String med_type = "";// 设备类型
	@Resource
	private UserMapper userMapper;

	private List<Integer> seclvWrongList = new ArrayList<Integer>(); // 密级错误
	private List<Integer> userWrongList = new ArrayList<Integer>(); // 责任人错误
	private List<Integer> deptWrongList = new ArrayList<Integer>(); // 部门错误
	private List<Integer> lackList = new ArrayList<Integer>();
	private List<Integer> failList = new ArrayList<Integer>();
	private List<Integer> statusWrongList = new ArrayList<Integer>();

	public String getMed_type() {
		return med_type;
	}

	public void setMed_type(String med_type) {
		this.med_type = med_type;
	}

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

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public int getSuccessNum() {
		return successNum;
	}

	public List<Integer> getSeclvWrongList() {
		return seclvWrongList;
	}

	public List<Integer> getUserWrongList() {
		return userWrongList;
	}

	public List<Integer> getDeptWrongList() {
		return deptWrongList;
	}

	public List<Integer> getLackList() {
		return lackList;
	}

	public List<Integer> getFailList() {
		return failList;
	}

	public List<Integer> getStatusWrongList() {
		return statusWrongList;
	}

	private String getCellStringValue(HSSFCell cell, boolean is_permit_blank) throws CellBlankException {
		return ExcelImportUtil.getCellStringValue(cell, is_permit_blank);
	}

	@Override
	public String executeFunction() throws Exception {

		if (done.equalsIgnoreCase("Y")) {
			if (!uploadFileName.endsWith("xls")) {
				throw new Exception("上传的文件类型错误，请上传EXCEL文件");
			}
			if (med_type.equals("")) {
				throw new Exception("配置管理中信息设备管理中计算机类未配置笔记本，请联系保密管理员配置");
			}
			InputStream myxls = new FileInputStream(upload);
			HSSFWorkbook wb = new HSSFWorkbook(myxls);
			HSSFSheet sheet = wb.getSheetAt(0);// 第一个表单

			String book_barcode = ""; // 条码号
			String book_code = ""; // 统一编号
			String duty_entp = ""; // 单位
			String duty_dept_id = ""; // 责任部门ID
			String duty_user_id = ""; // 责任人ID
			Integer seclv_code = null; // 设备密级
			String book_os = null; // 计算机操作系统
			String hdisk_no = "";// 硬盘序列号
			String book_mac = ""; // mac地址
			String internet_type = ""; // 联网情况
			String book_model = ""; // 名称型号
			String storage_location = ""; // 存放地点
			Integer book_status = null; // 笔记本状态（1在用 2停用 3维修 4报废 5销毁6已借出）
			// String useinfo = ""; // 使用情况
			String outsideinfo = ""; // 外出情况
			String output_point = "";// 输出情况
			String storage_secinfo = "";// 存储涉密信息情况
			String detail = "";// 备注
			String conf_code = "";// 保密编号
			String duty_dept_name = ""; // 责任部门
			String seclv_name = "";// 密级名称
			String oldconf_code = "";

			for (int num = sheet.getFirstRowNum() + 1; num <= sheet.getLastRowNum(); num++) {
				HSSFRow row = sheet.getRow(num);
				int column = 0;
				try {
					// 统一编号 单位 使用部门 保管人 密级 系统时间 硬盘序列号 MAG地址 联网情况 名称型号
					// 存放地点 使用情况 外出情况 输出情况 存储涉密信息情况 备注

					// 1、统一编号
					HSSFCell code = row.getCell(column++);
					code.setCellType(HSSFCell.CELL_TYPE_STRING);
					book_code = getCellStringValue(code, false);
					// 原保密编号
					oldconf_code = getCellStringValue(row.getCell(column++), false);
					// 2、单位：总部、24所、26所、44所
					duty_entp = getCellStringValue(row.getCell(column++), true);
					if (duty_entp.equals("总部") || duty_entp.equals("24所") || duty_entp.equals("26所")
							|| duty_entp.equals("44所")) {

					} else {
						deptWrongList.add(num + 1);
						throw new CellValueWrongException("单位[" + duty_entp + "]为空或填写错误");
					}

					// 3、责任部门：判断责任部门是否存在
					duty_dept_name = getCellStringValue(row.getCell(column++), true);
					if (StringUtils.hasLength(duty_dept_name)) {
						duty_dept_id = userService.getDeptIdByName(duty_dept_name); // 获取部门ID
						if (!StringUtils.hasLength(duty_dept_id)) {
							deptWrongList.add(num + 1);
							throw new CellValueWrongException("不存在部门[" + duty_dept_name + "]");
						}
					}
					// 4、责任人：判断责任人是否存在
					duty_user_id = getCellStringValue(row.getCell(column++), false);
					if (!userService.isUserExist(duty_user_id)) {
						userWrongList.add(num + 1);
						throw new CellValueWrongException("责任人[" + duty_user_id + "]不存在，请核对后导入");
					}
					// 5、密级：判断密级是否存在
					seclv_name = getCellStringValue(row.getCell(column++), false);
					seclv_code = userMapper.getSeclvCodeByName(seclv_name);
					if (seclv_code == null) {
						seclvWrongList.add(num + 1);
						throw new CellValueWrongException("密级[" + seclv_name + "]不存在,请核对后导入");
					}

					// 系统时间
					book_os = getCellStringValue(row.getCell(column++), true);
					// 硬盘序列号
					hdisk_no = getCellStringValue(row.getCell(column++), true);
					// MAG地址
					book_mac = getCellStringValue(row.getCell(column++), true);

					// 8、联接网络:涉密网、科研网、市政网、单机、互联网、非密专网、其他
					internet_type = getCellStringValue(row.getCell(column++), true);
					if (internet_type.equals("涉密网") || internet_type.equals("科研网") || internet_type.equals("市政网")
							|| internet_type.equals("单机") || internet_type.equals("互联网")
							|| internet_type.equals("非密专网") || internet_type.equals("其他")) {

					} else {
						deptWrongList.add(num + 1);
						throw new CellValueWrongException("联接网络[" + internet_type + "]为空或者填写错误");
					}
					// 名称型号
					book_model = getCellStringValue(row.getCell(column++), true);
					// 存放地点
					storage_location = getCellStringValue(row.getCell(column++), true);

					// 使用情况// 判断计算机状态是否正确
					String computer_status_name = getCellStringValue(row.getCell(column++), true);
					book_status = 0;
					if (computer_status_name.equals("在用")) {
						book_status = 1;
					} else if (computer_status_name.equals("停用")) {
						book_status = 2;
					} else if (computer_status_name.equals("维修")) {
						book_status = 3;
					} else if (computer_status_name.equals("报废")) {
						book_status = 4;
					} else if (computer_status_name.equals("销毁")) {
						book_status = 5;
					} else if (computer_status_name.equals("已借出")) {
						book_status = 6;
					} else {
						statusWrongList.add(num + 1);
						throw new CellValueWrongException("使用情况[" + computer_status_name + "]非法，请核实后填写");
					}

					// 外出情况
					outsideinfo = getCellStringValue(row.getCell(column++), true);
					// 输出情况
					output_point = getCellStringValue(row.getCell(column++), true);
					// 存储涉密信息情况
					storage_secinfo = getCellStringValue(row.getCell(column++), true);
					// 备注：
					detail = getCellStringValue(row.getCell(column++), true);

					// 生成计算机条码
					String dept_id = duty_dept_id;
					if (StringUtils.hasLength(dept_id)) {
						// book_barcode = basicService.createCETCEntityBarcode(dept_id);
						// 不区分份数，默认为0
						book_barcode = basicService.createNewCETCBarcode(dept_id, 0);
					} else {
						// book_barcode = basicService.createCETCEntityBarcode("00");
						// 不区分份数，默认为0
						book_barcode = basicService.createNewCETCBarcode("", 0);
					}

					// 生成保密编号流水号
					conf_code = computerService.createSecretSerial("1", med_type, duty_dept_id);

					EntityBook device = new EntityBook();
					device.setBook_barcode(book_barcode);
					device.setBook_code(book_code);
					device.setConf_code(conf_code);
					device.setDuty_entp(duty_entp);
					device.setDuty_dept_id(duty_dept_id);
					device.setDuty_user_id(duty_user_id);
					device.setSeclv_code(seclv_code);
					device.setBook_os(book_os);
					device.setHdisk_no(hdisk_no);
					device.setBook_mac(book_mac);
					device.setInternet_type(internet_type);
					device.setBook_model(book_model);
					device.setStorage_location(storage_location);
					device.setBook_status(book_status);
					device.setMed_type(med_type);
					device.setOutsideinfo(outsideinfo);
					device.setOutput_point(output_point);
					device.setStorage_secinfo(storage_secinfo);
					device.setDetail(detail);
					device.setOldconf_code(oldconf_code);

					// 生成计算机生命周期记录
					BMCycleItem cycleitem = new BMCycleItem();
					cycleitem.setBarcode(book_barcode);
					cycleitem.setEntity_type("COMPUTER");
					cycleitem.setOper_time(new Date());
					cycleitem.setUser_name(getCurUser().getUser_name());
					cycleitem.setDept_name(getCurUser().getDept_name());
					cycleitem.setOper("LEADIN");
					computerService.addBookEntity(device, cycleitem);
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
					logger.error("解析数据出现异常：行[" + (num + 1) + "]，列[" + column + "]");
					logger.error(e.getMessage());
					failList.add(num + 1);
					continue;
				}
				insertCommonLog("批量导入笔记本台账");
			}
		}
		return SUCCESS;
	}
}