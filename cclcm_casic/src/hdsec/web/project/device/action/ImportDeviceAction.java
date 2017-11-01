package hdsec.web.project.device.action;

import hdsec.web.project.common.util.ExcelImportUtil;
import hdsec.web.project.device.model.DeviceStatusEnum;
import hdsec.web.project.device.model.EntityDevice;
import hdsec.web.project.exception.CellBlankException;
import hdsec.web.project.exception.CellValueWrongException;
import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.user.mapper.UserMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.util.StringUtils;

public class ImportDeviceAction extends DeviceBaseAction {

	private static final long serialVersionUID = 1L;
	@Resource
	private UserMapper userMapper;
	private File upload = null;
	private String done = "N";
	private List<Integer> deviceWrongList = new ArrayList<Integer>();// 磁介质名称错误
	private List<Integer> lackList = new ArrayList<Integer>();
	private List<Integer> failList = new ArrayList<Integer>();
	private List<Integer> seclvWrongList = new ArrayList<Integer>(); // 密级错误
	private List<Integer> medWrongList = new ArrayList<Integer>(); // 磁介质类型错误
	private List<Integer> userWrongList = new ArrayList<Integer>(); // 记录责任人ID错误
	private List<Integer> deptWrongList = new ArrayList<Integer>(); // 部门错误
	private int successNum = 0;
	private String uploadFileName = "";

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

	public List<Integer> getDeviceWrongList() {
		return deviceWrongList;
	}

	public void setDeviceWrongList(List<Integer> deviceWrongList) {
		this.deviceWrongList = deviceWrongList;
	}

	public List<Integer> getLackList() {
		return lackList;
	}

	public void setLackList(List<Integer> lackList) {
		this.lackList = lackList;
	}

	public List<Integer> getDeptWrongList() {
		return deptWrongList;
	}

	public void setDeptWrongList(List<Integer> deptWrongList) {
		this.deptWrongList = deptWrongList;
	}

	public List<Integer> getFailList() {
		return failList;
	}

	public void setFailList(List<Integer> failList) {
		this.failList = failList;
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

	private String getCellStringValue(HSSFCell cell, boolean is_permit_blank) throws CellBlankException {
		return ExcelImportUtil.getCellStringValue(cell, is_permit_blank);
	}

	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public List<Integer> getSeclvWrongList() {
		return seclvWrongList;
	}

	public void setSeclvWrongList(List<Integer> seclvWrongList) {
		this.seclvWrongList = seclvWrongList;
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

	@Override
	public String executeFunction() throws Exception {
		if (done.equalsIgnoreCase("Y")) {
			if (!uploadFileName.endsWith("xls")) {
				throw new Exception("上传的文件类型错误，请上传EXCEL文件");
			}
			InputStream myxls = new FileInputStream(upload);
			HSSFWorkbook wb = new HSSFWorkbook(myxls);
			HSSFSheet sheet = wb.getSheetAt(0);// 第一个表单

			String device_name = "";// 名称
			String med_type_name = "";// 介质类型名称
			Integer med_type = null;// 介质类型
			String seclv_code_name = ""; // 密级名称
			Integer seclv_code = null;// 密级
			String duty_user_iidd = "";// 责任人ID
			String dept_name = ""; // 部门名称
			String dept_id = "";// 设备所属部门ID
			String device_series = "";// 设备编号
			String device_model = "";// 型号
			String device_detail = "";// 设备配置
			String med_content = "";// 说明
			String device_code = "";
			String device_barcode = "";// 条码号

			// 循环文档，跳过第一行标题行
			for (int num = sheet.getFirstRowNum() + 1; num <= sheet.getLastRowNum(); num++) {
				// 分析每一行数据
				HSSFRow row = sheet.getRow(num);
				int column = 0;
				try {
					device_name = getCellStringValue(row.getCell(column++), false);
					if (device_name.length() > 30) {
						deviceWrongList.add(num + 1);
						throw new CellValueWrongException("磁介质[" + device_name + "]名称太长，不能重复导入");
					}
					// 判断磁介质类型是否存在
					med_type_name = getCellStringValue(row.getCell(column++), false);
					med_type = deviceService.getTypeIDByName(med_type_name);
					if (med_type == null) {
						medWrongList.add(num + 1);
						throw new CellValueWrongException("磁介质类型[" + med_type_name + "]不存在,请核对后导入");
					}
					// 判断密级是否存在
					seclv_code_name = getCellStringValue(row.getCell(column++), false);
					seclv_code = userMapper.getSeclvCodeByName(seclv_code_name);
					if (seclv_code == null) {
						seclvWrongList.add(num + 1);
						throw new CellValueWrongException("密级[" + seclv_code_name + "]不存在,请核对后导入");
					}
					// 判断责任人是否存在
					duty_user_iidd = getCellStringValue(row.getCell(column++), false);
					if (!userService.isUserExist(duty_user_iidd)) {
						userWrongList.add(num + 1);
						throw new CellValueWrongException("责任人[" + duty_user_iidd + "]不存在，请核对后导入");
					}
					// 判断部门是否存在
					dept_name = getCellStringValue(row.getCell(column++), true);
					if (StringUtils.hasLength(dept_name)) {
						dept_id = userService.getDeptIdByName(dept_name); // 获取部门ID
						if (!StringUtils.hasLength(dept_id)) {
							deptWrongList.add(num + 1);
							throw new CellValueWrongException("不存在部门[" + dept_name + "]");
						}
					}
					device_series = getCellStringValue(row.getCell(column++), true);
					device_model = getCellStringValue(row.getCell(column++), true);
					device_detail = getCellStringValue(row.getCell(column++), true);
					med_content = getCellStringValue(row.getCell(column++), true);
					device_code = "DVC-" + String.valueOf(UUID.randomUUID().toString());
					device_barcode = basicService.createEntityBarcode("DEVICE");
					// 动态库生成条码方式
					/*
					 * Map<String, String> Paras = new HashMap(); Paras.put("USERID", duty_user_iidd);
					 * Paras.put("EVENTTYPE", "5"); Paras.put("EVENTCODE", device_code); Paras.put("COUNT", "0");
					 * Map<String, String> Cmap = CreateBarcodeUtil.CreateBarcode(Paras, 4); device_barcode =
					 * Cmap.get("Barcode");
					 */
					EntityDevice device = new EntityDevice();
					device.setDevice_code(device_code);
					device.setDevice_name(device_name);
					device.setUser_iidd(getCurUser().getUser_iidd());
					device.setDept_id(dept_id);
					device.setDevice_series(device_series);
					device.setDevice_barcode(device_barcode);
					device.setEnter_time(new Date());
					device.setMed_type(med_type);
					device.setSeclv_code(seclv_code);
					device.setDuty_user_iidd(duty_user_iidd);
					device.setMed_content(med_content);
					device.setDevice_model(device_model);
					device.setDevice_detail(device_detail);
					device.setDeviceStatus(DeviceStatusEnum.DS0);
					// 生成载体生命周期记录
					CycleItem cycleitem = new CycleItem();
					cycleitem.setBarcode(device_barcode);
					cycleitem.setEntity_type("DEVICE");
					cycleitem.setOper_time(new Date());
					cycleitem.setUser_name(getCurUser().getUser_name());
					cycleitem.setDept_name(getCurUser().getDept_name());
					cycleitem.setOper("LEADIN");
					// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
					// 此处不用启动流程，job_code设置为default
					cycleitem.setJob_code("default");
					deviceService.addEntityDevice(device, cycleitem);
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
					logger.error("解析磁介质数据出现异常：行[" + (num + 1) + "]，列[" + column + "]");
					logger.error(e.getMessage());
					failList.add(num + 1);
					continue;
				}
			}
			insertCommonLog("批量导入磁介质");
		}
		return SUCCESS;
	}
}
