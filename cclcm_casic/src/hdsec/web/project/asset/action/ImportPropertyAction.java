package hdsec.web.project.asset.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.asset.model.EntityProperty;
import hdsec.web.project.common.util.ExcelImportUtil;
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

public class ImportPropertyAction extends AssetBaseAction {

	private File upload = null;
	private String done = "N";
	private int successNum = 0;
	private String uploadFileName = "";
	private List<Integer> failList = new ArrayList<Integer>();
	@Resource
	private UserMapper userMapper;

	public List<Integer> getFailList() {
		return failList;
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

	private String getCellStringValue(HSSFCell cell, boolean is_permit_blank)
			throws CellBlankException {
		return ExcelImportUtil.getCellStringValue(cell, is_permit_blank);
	}

	private Date formatCellDateValue(HSSFCell cell, boolean is_compatible,
			String pattern) throws Exception {
		return ExcelImportUtil
				.formatCellDateValue(cell, is_compatible, pattern);
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
			String no = "";
			String property_barcode = ""; // 资产条码
			String event_code = "";// 作业ID(外键)
			String user_iidd = "";// 采购申请人ID
			String user_name = "";// 采购人
			String dept_id = "";// 采购部门ID
			String dept_name = "";// 采购部门
			String duty_user_iidd = "";// 责任人ID
			String duty_user_name = "";// 责任人姓名
			String duty_dept_id = "";// 责任人部门名称
			String duty_dept_name = "";// 责任人部门名称
			Integer seclv_code = null;// 资产密级
			String seclv_code_name = "";// 密级名称
			String property_kind = ""; // 采购资产种类
			String property_name = ""; // 资产名称
			String property_standard = ""; // 资产规格
			String unit_price = ""; // 单价

			Integer property_status = 0;// 资产状态（0在用，1申请出库，2出库，3申请报废，4已报废，5申请入库，6申请变更）
			Date in_time = null; // 入库时间
			Date out_time = null;// 出库时间
			Date waste_time = null;// 报废时间
			String identity_code = "";// 识别码
			String property_no = "";// 资产编号
			String voucher_no = ""; // 凭证号
			String property_type = "";// 资产型号
			String factory_no = "";// 出厂编号
			Date factory_date = null; // 出厂日期
			Date use_date = null;// 启用日期
			String setup_place = "";// 安装地点
			String original_value = ""; // 原值
			String average_equity = ""; // 净值
			String supplier = "";// 国别厂家
			String summ = ""; // 备注
			for (int num = sheet.getFirstRowNum() + 1; num <= sheet
					.getLastRowNum(); num++) {
				HSSFRow row = sheet.getRow(num);
				int column = 0;
				try {
					// ①计算机名称型号：
					// 序号
					no = getCellStringValue(row.getCell(column++), true);
					user_iidd = getCellStringValue(row.getCell(column++), true);
					if (!userService.isUserExist(user_iidd)) {
						throw new CellValueWrongException("采购申请人[" + user_iidd
								+ "]不存在，请核对后导入");
					}
					user_name = userMapper.getUserNameByUserId(user_iidd);
					dept_name = getCellStringValue(row.getCell(column++), true);
					if (StringUtils.hasLength(dept_name)) {
						dept_id = userService.getDeptIdByName(dept_name); // 获取部门ID
						if (!StringUtils.hasLength(dept_id)) {
							// deptWrongList.add(num + 1);
							throw new CellValueWrongException("不存在部门["
									+ duty_dept_name + "]");
						}
					}
					duty_user_iidd = getCellStringValue(row.getCell(column++),
							true);
					if (!userService.isUserExist(duty_user_iidd)) {
						throw new CellValueWrongException("责任人id["
								+ duty_user_iidd + "]不存在，请核对后导入");
					}
					duty_user_name = userMapper.getUserNameByUserId(user_iidd);
					duty_dept_name = getCellStringValue(row.getCell(column++),
							true);
					if (StringUtils.hasLength(duty_dept_name)) {
						duty_dept_id = userService
								.getDeptIdByName(duty_dept_name); // 获取部门ID
						if (!StringUtils.hasLength(duty_dept_id)) {
							// deptWrongList.add(num + 1);
							throw new CellValueWrongException("不存在部门["
									+ duty_dept_name + "]");
						}
					}
					// ④密级：判断密级是否存在
					seclv_code_name = getCellStringValue(row.getCell(column++),
							true);
					seclv_code = userMapper.getSeclvCodeByName(seclv_code_name);
					if (seclv_code == null) {
						// seclvWrongList.add(num + 1);
						throw new CellValueWrongException("密级["
								+ seclv_code_name + "]不存在,请核对后导入");
					}
					// 资产种类
					property_kind = getCellStringValue(row.getCell(column++),
							true);
					// 资产名称
					property_name = getCellStringValue(row.getCell(column++),
							true);
					if (property_name == null || property_name.isEmpty()) {
						// seclvWrongList.add(num + 1);
						throw new CellValueWrongException("资产名称["
								+ property_name + "]不存在,请核对后导入");
					}
					// 识别码
					identity_code = getCellStringValue(row.getCell(column++),
							true);
					// 资产编号
					property_no = getCellStringValue(row.getCell(column++),
							true);
					// 凭证号
					voucher_no = getCellStringValue(row.getCell(column++), true);
					// 出厂编号
					factory_no = getCellStringValue(row.getCell(column++), true);
					// 出厂日期
					factory_date = formatCellDateValue(row.getCell(column++),
							true, "yyyy-MM-dd");
					// 启用日期
					use_date = formatCellDateValue(row.getCell(column++), true,
							"yyyy-MM-dd");
					// 单价
					unit_price = getCellStringValue(row.getCell(column++), true);
					// 规格
					property_standard = getCellStringValue(
							row.getCell(column++), true);
					// 型号
					property_type = getCellStringValue(row.getCell(column++),
							true);
					// 国别厂家
					supplier = getCellStringValue(row.getCell(column++), true);
					// 原值
					original_value = getCellStringValue(row.getCell(column++),
							true);
					// 安装地点
					setup_place = getCellStringValue(row.getCell(column++),
							true);
					// 入账时间
					in_time = formatCellDateValue(row.getCell(column++), true,
							"yyyy-MM-dd");
					// 备注
					summ = getCellStringValue(row.getCell(column++), true);
					property_barcode = basicService
							.createEntityBarcode("PROPERTY");
					EntityProperty property = new EntityProperty(
							property_barcode, event_code, user_iidd, user_name,
							dept_id, dept_name, duty_user_iidd, duty_user_name,
							duty_dept_id, duty_dept_name, seclv_code,
							property_kind, property_name, property_standard,
							supplier, unit_price, in_time, identity_code,
							property_no, voucher_no, property_type, factory_no,
							factory_date, use_date, setup_place,
							original_value, average_equity, summ);
					successNum++;
					// 生成资产生命周期记录
					CycleItem cycleitem = new CycleItem();
					cycleitem.setBarcode(property_barcode);
					cycleitem.setOper_time(new Date());
					cycleitem.setUser_name(getCurUser().getUser_name());
					cycleitem.setDept_name(getCurUser().getDept_name());
					cycleitem.setOper("PROPERTYIN");
					cycleitem.setEntity_type("PROPERTY");
					assetService.addPropertyledger(null, null, property,
							cycleitem);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("解析资产台账数据出现异常：序号：" + no + ".行[" + (num + 1)
							+ "]，列[" + column + "]");
					logger.error(e.getMessage());
					failList.add(num + 1);
					continue;
				}
			}
		}
		return SUCCESS;
	}

}
