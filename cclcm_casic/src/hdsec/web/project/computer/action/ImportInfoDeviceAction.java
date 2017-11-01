package hdsec.web.project.computer.action;

import hdsec.web.project.common.bm.BMCycleItem;
import hdsec.web.project.common.util.ExcelImportUtil;
import hdsec.web.project.computer.model.EntityInfoDevice;
import hdsec.web.project.computer.model.InfoType;
import hdsec.web.project.exception.CellBlankException;
import hdsec.web.project.exception.CellValueWrongException;
import hdsec.web.project.user.mapper.UserMapper;
import hdsec.web.project.user.model.SecUser;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.util.StringUtils;

public class ImportInfoDeviceAction extends ComputerBaseAction {

	private static final long serialVersionUID = 1L;
	private File upload = null;
	private String done = "N";
	private int successNum = 0;
	private String uploadFileName = "";
	@Resource
	private UserMapper userMapper;

	private List<Integer> typeWrongList = new ArrayList<Integer>();// 计算机名称错误
	private List<Integer> seclvWrongList = new ArrayList<Integer>(); // 密级错误
	private List<Integer> userWrongList = new ArrayList<Integer>(); // 责任人ID错误
	private List<Integer> deptWrongList = new ArrayList<Integer>(); // 部门ID错误
	private List<Integer> lackList = new ArrayList<Integer>();
	private List<Integer> failList = new ArrayList<Integer>();
	private List<Integer> computermodelWrongList = new ArrayList<Integer>();
	private List<Integer> statusWrongList = new ArrayList<Integer>();

	private List<InfoType> infotype2 = null;
	private List<InfoType> infotype3 = null;
	private List<InfoType> infotype4 = null;
	private List<InfoType> infotype5 = null;
	private List<InfoType> infotype6 = null;

	public List<InfoType> getInfotype2() {
		return infotype2;
	}

	public List<InfoType> getInfotype3() {
		return infotype3;
	}

	public List<InfoType> getInfotype4() {
		return infotype4;
	}

	public List<InfoType> getInfotype5() {
		return infotype5;
	}

	public List<InfoType> getInfotype6() {
		return infotype6;
	}

	public List<Integer> getStatusWrongList() {
		return statusWrongList;
	}

	public void setStatusWrongList(List<Integer> statusWrongList) {
		this.statusWrongList = statusWrongList;
	}

	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public List<Integer> getComputermodelWrongList() {
		return computermodelWrongList;
	}

	public void setComputermodelWrongList(List<Integer> computermodelWrongList) {
		this.computermodelWrongList = computermodelWrongList;
	}

	public List<Integer> getComputercodeWrongList() {
		return computercodeWrongList;
	}

	public void setComputercodeWrongList(List<Integer> computercodeWrongList) {
		this.computercodeWrongList = computercodeWrongList;
	}

	public List<Integer> getConfcodeWrongList() {
		return confcodeWrongList;
	}

	public void setConfcodeWrongList(List<Integer> confcodeWrongList) {
		this.confcodeWrongList = confcodeWrongList;
	}

	private List<Integer> computercodeWrongList = new ArrayList<Integer>();
	private List<Integer> confcodeWrongList = new ArrayList<Integer>();

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

	public List<Integer> getTypeWrongList() {
		return typeWrongList;
	}

	public void setTypeWrongList(List<Integer> typeWrongList) {
		this.typeWrongList = typeWrongList;
	}

	public List<Integer> getSeclvWrongList() {
		return seclvWrongList;
	}

	public void setSeclvWrongList(List<Integer> seclvWrongList) {
		this.seclvWrongList = seclvWrongList;
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

	private String getCellStringValue(HSSFCell cell, boolean is_permit_blank) throws CellBlankException {
		return ExcelImportUtil.getCellStringValue(cell, is_permit_blank);
	}

	private Date formatCellDateValue(HSSFCell cell, boolean is_compatible, String pattern) throws Exception {
		return ExcelImportUtil.formatCellDateValue(cell, is_compatible, pattern);
	}

	@Override
	public String executeFunction() throws Exception {

		if (done.equalsIgnoreCase("Y")) {
			if (!uploadFileName.endsWith("xls")) {
				throw new Exception("上传的文件类型错误，请上传EXCEL文件");
			}
			InputStream myxls = new FileInputStream(upload);
			HSSFWorkbook wb = new HSSFWorkbook(myxls);
			HSSFSheet sheet = wb.getSheetAt(0);// 第一个表单

			String device_barcode = "";
			String duty_user_id = "";// 责任人ID
			String duty_dept_id = "";// 责任人部门ID
			String duty_dept_name = "";// 责任人部门
			// String conf_code = "";// 保密编号
			String device_series = "";// 设备编号
			String device_usage = "";// 设备用途
			String device_version = "";// 型号
			// String serial_num = "";// 序列号
			String brand_type = "";// 品牌类型
			Integer device_seclv = null;// 设备密级
			String device_code_name = "";// 密级名称
			Date purchase_time = null;// 采购时间
			Date use_time = null;// 启用时间
			// Date destroy_time = null;// 报废时间
			String location = "";// 安装地点
			String cert_name = "";// 检测证书名称
			String cert_num = "";// 证书编号
			String memory = "";// 内存
			Integer device_type = null;// 设备类型
			String device_type_name = "";
			InfoType info_type = null;// 详细设备
			String oldconf_code = "";// 原保密编号
			String company = "";// 单位

			for (int num = sheet.getFirstRowNum() + 1; num <= sheet.getLastRowNum(); num++) {
				HSSFRow row = sheet.getRow(num);
				int column = 0;
				try {
					// ①判断设备类型是否为空或不合法
					device_type_name = getCellStringValue(row.getCell(column++), false);
					if (device_type_name.length() < 1) {
						typeWrongList.add(num + 1);
						throw new CellValueWrongException("设备类型[" + device_type_name + "]为空，请填写对应信息");
					}
					if (device_type_name.equals("办公自动化设备")) {
						device_type = 4;
					} else if (device_type_name.equals("外部设备")) {
						device_type = 3;
					} else if (device_type_name.equals("安全产品")) {
						device_type = 5;
					} else if (device_type_name.equals("介质")) {
						device_type = 6;
					} else if (device_type_name.equals("网络设备")) {
						device_type = 2;
					} else {
						typeWrongList.add(num + 1);
						throw new CellValueWrongException("设备类型[" + device_type_name + "]不合法，请填写对应信息");
					}

					// ②判断子设备类型是否为空或超长
					String name = getCellStringValue(row.getCell(column++), false);
					if (name.length() < 1) {
						typeWrongList.add(num + 1);
						throw new CellValueWrongException("子设备类型[" + name + "]为空，请填写对应信息");
					}
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("info_type", name);
					info_type = computerService.getInfoTypeByID(map);
					if (info_type == null) {
						typeWrongList.add(num + 1);
						throw new CellValueWrongException("子设备类型[" + info_type + "]不合法，请填写对应信息");
					}

					// ③判断责任人是否存在
					duty_user_id = getCellStringValue(row.getCell(column++), false);
					if (!userService.isUserExist(duty_user_id)) {
						userWrongList.add(num + 1);
						throw new CellValueWrongException("责任人[" + duty_user_id + "]不存在，请核对后导入");
					}
					SecUser dutyuser = userService.getSecUserByUid(duty_user_id);

					// ④判断责任部门是否存在
					duty_dept_name = getCellStringValue(row.getCell(column++), true);
					if (StringUtils.hasLength(duty_dept_name)) {
						duty_dept_id = userService.getDeptIdByName(duty_dept_name); // 获取部门ID
						if (!StringUtils.hasLength(duty_dept_id)) {
							deptWrongList.add(num + 1);
							throw new CellValueWrongException("不存在部门[" + duty_dept_name + "]");
						}
					}

					// ⑤判断密级是否存在
					device_code_name = getCellStringValue(row.getCell(column++), false);
					device_seclv = userMapper.getSeclvCodeByName(device_code_name);
					if (device_seclv == null) {
						seclvWrongList.add(num + 1);
						throw new CellValueWrongException("密级[" + device_code_name + "]不存在,请核对后导入");
					}

					// 单位：总部、24所、26所、44所
					company = getCellStringValue(row.getCell(column++), true);
					if (company.equals("总部") || company.equals("24所") || company.equals("26所") || company.equals("44所")) {

					} else {
						deptWrongList.add(num + 1);
						throw new CellValueWrongException("单位[" + company + "]为空或填写错误");
					}

					oldconf_code = getCellStringValue(row.getCell(column++), true);
					device_series = getCellStringValue(row.getCell(column++), true);
					device_usage = getCellStringValue(row.getCell(column++), true);
					brand_type = getCellStringValue(row.getCell(column++), true);
					device_version = getCellStringValue(row.getCell(column++), true);
					// serial_num = getCellStringValue(row.getCell(column++), true);
					purchase_time = formatCellDateValue(row.getCell(column++), true, "yyyy-MM-dd");
					use_time = formatCellDateValue(row.getCell(column++), true, "yyyy-MM-dd");
					// destroy_time = formatCellDateValue(row.getCell(column++), true, "yyyy-MM-dd");
					location = getCellStringValue(row.getCell(column++), true);
					cert_name = getCellStringValue(row.getCell(column++), true);
					cert_num = getCellStringValue(row.getCell(column++), true);
					memory = getCellStringValue(row.getCell(column++), true);
					String statue = getCellStringValue(row.getCell(column++), true);
					Integer device_statue = null;
					if (statue.equals("在用")) {
						device_statue = 1;
					} else if (statue.equals("停用")) {
						device_statue = 2;
					} else if (statue.equals("维修")) {
						device_statue = 3;
					} else if (statue.equals("报废")) {
						device_statue = 4;
					} else if (statue.equals("销毁")) {
						device_statue = 6;
					} else {
						seclvWrongList.add(num + 1);
						throw new CellValueWrongException("设备状态[" + statue + "]不存在,请核对后导入");
					}
					String detail = getCellStringValue(row.getCell(column++), true);

					// 生成中电条码
					if (StringUtils.hasLength(duty_dept_id)) {
						// device_barcode = basicService.createCETCEntityBarcode(duty_dept_id);
						// 不区分份数，默认为0
						device_barcode = basicService.createNewCETCBarcode(duty_dept_id, 0);
					} else {
						// device_barcode = basicService.createCETCEntityBarcode("00");
						// 不区分份数，默认为0
						device_barcode = basicService.createNewCETCBarcode("", 0);
					}

					// 生成保密编号
					String ser_num = computerService.createSecretSerial(device_type.toString(), info_type.getInfo_id(),
							duty_dept_id);

					// 将合法数据SET并插入数据库
					EntityInfoDevice device = new EntityInfoDevice();
					device.setDevice_barcode(device_barcode);
					device.setUser_iidd(getCurUser().getUser_iidd());
					device.setUser_name(getCurUser().getUser_name());
					device.setDept_id(getCurUser().getDept_id());
					device.setDept_name(getCurUser().getDept_name());
					device.setDuty_user_id(duty_user_id);
					device.setDuty_user_name(dutyuser.getUser_name());
					device.setDuty_dept_id(duty_dept_id);
					device.setDuty_dept_name(duty_dept_name);
					device.setConf_code(ser_num);
					device.setDevice_seclv(device_seclv);
					device.setDevice_series(device_series);
					device.setDevice_type(device_type);
					device.setDevice_usage(device_usage);
					device.setDevice_version(device_version);
					device.setBrand_type(brand_type);
					device.setPurchase_time(purchase_time);
					device.setUse_time(use_time);
					// device.setDestroy_time(destroy_time);
					device.setLocation(location);
					// device.setSerial_num(serial_num);
					device.setCert_name(cert_name);
					device.setCert_num(cert_num);
					device.setMemory(memory);
					device.setInfo_id(info_type.getInfo_id());
					device.setDevice_statues(device_statue);
					device.setCompany(company);
					device.setOldconf_code(oldconf_code);
					device.setDetail(detail);

					// 生成计算机生命周期记录
					BMCycleItem cycleitem = new BMCycleItem();
					cycleitem.setBarcode(device_barcode);
					cycleitem.setEntity_type("DEVICE");
					cycleitem.setOper_time(new Date());
					cycleitem.setUser_name(getCurUser().getUser_name());
					cycleitem.setDept_name(getCurUser().getDept_name());
					cycleitem.setOper("LEADIN");
					computerService.addInfoDevice(device, cycleitem);
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
				insertCommonLog("批量导入信息设备:" + device_barcode);
			}
		}
		return SUCCESS;
	}
}