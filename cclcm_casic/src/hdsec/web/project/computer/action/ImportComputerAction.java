package hdsec.web.project.computer.action;

import hdsec.web.project.common.bm.BMCycleItem;
import hdsec.web.project.common.util.ExcelImportUtil;
import hdsec.web.project.computer.model.EntityComputer;
import hdsec.web.project.computer.model.InfoType;
import hdsec.web.project.exception.CellBlankException;
import hdsec.web.project.exception.CellValueWrongException;
import hdsec.web.project.user.mapper.UserMapper;

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

public class ImportComputerAction extends ComputerBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File upload = null;
	private String done = "N";
	private int successNum = 0;
	private String uploadFileName = "";
	@Resource
	private UserMapper userMapper;

	private List<Integer> computerWrongList = new ArrayList<Integer>();// 计算机名称错误
	private List<Integer> seclvWrongList = new ArrayList<Integer>(); // 密级错误
	private List<Integer> codeWrongList = new ArrayList<Integer>(); // 编号类错误：统一编号 原保密编号
	private List<Integer> userWrongList = new ArrayList<Integer>(); // 责任人错误
	private List<Integer> deptWrongList = new ArrayList<Integer>(); // 部门错误
	private List<Integer> entpWrongList = new ArrayList<Integer>(); // 部门错误
	private List<Integer> internettypeWrongList = new ArrayList<Integer>(); // 网络类型错误
	private List<Integer> medtypeWrongList = new ArrayList<Integer>(); // 网络类型错误
	private List<Integer> hdisknoWrongList = new ArrayList<Integer>(); // 输出端口
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

	public List<Integer> getComputerWrongList() {
		return computerWrongList;
	}

	public List<Integer> getSeclvWrongList() {
		return seclvWrongList;
	}

	public List<Integer> getCodeWrongList() {
		return codeWrongList;
	}

	public List<Integer> getUserWrongList() {
		return userWrongList;
	}

	public List<Integer> getDeptWrongList() {
		return deptWrongList;
	}

	public List<Integer> getInternettypeWrongList() {
		return internettypeWrongList;
	}

	public List<Integer> getHdisknoWrongList() {
		return hdisknoWrongList;
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

	public List<Integer> getEntpWrongList() {
		return entpWrongList;
	}

	public List<Integer> getMedtypeWrongList() {
		return medtypeWrongList;
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

			String computer_barcode = ""; // 条码号
			String computer_code = ""; // 设备编号
			Integer seclv_code = null; // 设备密级
			String duty_user_id = ""; // 责任人ID
			String duty_dept_id = ""; // 责任部门ID
			String duty_entp_id = ""; // 责任单位ID
			Integer internet_type = null; // 网络类型
			String computer_name = "";// 计算机名称
			String med_type = null;// 设备型号
			String hdisk_no = "";// 硬盘序列号
			Integer computer_status = null; // 计算机状态（1在用，2停用，3维修，4报废，5销毁，6申请维修，7申请报废）
			String computer_os = ""; // 计算机操作系统
			Date install_time = null; // 安装时间
			String key_code = "";// key编号
			String computer_ip = ""; // ip地址
			String computer_mac = ""; // mac地址
			String output_point = "";// 输出端口
			String storage_area = ""; // 存放区域
			String storage_location = ""; // 物理位置
			String user_iidd = ""; // 录入人员ID
			Date enter_time = null;// 录入时间
			String summ = "";// 说明
			String computer_gateway = "";// 网关
			String mark_code = "";// 掩码
			String vlan_num = "";// VLAN
			String switch_num = "";// 交换机
			String switch_point = "";// 端口号
			String seclv_code_name = "";
			String duty_dept_name = "";
			String computer_status_name = "";

			for (int num = sheet.getFirstRowNum() + 1; num <= sheet.getLastRowNum(); num++) {
				HSSFRow row = sheet.getRow(num);
				int column = 0;
				try {
					// ①计算机名称型号：
					// 判断计算机名称是否为空或超长
					computer_name = getCellStringValue(row.getCell(column++), false);
					if (computer_name.length() < 1) {
						computerWrongList.add(num + 1);
						throw new CellBlankException("计算机[" + computer_name + "]为空，请填写对应信息");
					}

					if (computer_name.length() > 90) {
						computerWrongList.add(num + 1);
						throw new CellValueWrongException("计算机[" + computer_name + "]名称太长，不能重复导入");
					}

					// ②统一编号
					// 判断设备编号是否填写
					HSSFCell code = row.getCell(column++);
					code.setCellType(HSSFCell.CELL_TYPE_STRING);
					computer_code = getCellStringValue(code, false);
					if (computer_code.length() < 1) {
						codeWrongList.add(num + 1);
						throw new CellBlankException("统一编号[" + computer_code + "]为空，请填写对应信息");
					}

					// ③原保密编号
					// 判断保密编号是否填写
					HSSFCell oldconf = row.getCell(column++);
					oldconf.setCellType(HSSFCell.CELL_TYPE_STRING);
					String oldconf_code = getCellStringValue(oldconf, false);
					if (oldconf_code.length() < 1) {
						codeWrongList.add(num + 1);
						throw new CellBlankException("原保密编号[" + oldconf_code + "]为空，请填写对应信息");
					}

					// ④密级：判断密级是否存在
					seclv_code_name = getCellStringValue(row.getCell(column++), false);
					seclv_code = userMapper.getSeclvCodeByName(seclv_code_name);
					if (seclv_code == null) {
						seclvWrongList.add(num + 1);
						throw new CellValueWrongException("密级[" + seclv_code_name + "]不存在,请核对后导入");
					}
					// ⑤责任人：判断责任人是否存在
					duty_user_id = getCellStringValue(row.getCell(column++), false);
					if (!userService.isUserExist(duty_user_id)) {
						userWrongList.add(num + 1);
						throw new CellValueWrongException("责任人[" + duty_user_id + "]不存在，请核对后导入");
					}
					// ⑥责任部门：判断责任部门是否存在
					duty_dept_name = getCellStringValue(row.getCell(column++), true);
					if (StringUtils.hasLength(duty_dept_name)) {
						duty_dept_id = userService.getDeptIdByName(duty_dept_name); // 获取部门ID
						if (!StringUtils.hasLength(duty_dept_id)) {
							deptWrongList.add(num + 1);
							throw new CellValueWrongException("不存在部门[" + duty_dept_name + "]");
						}
					}

					// 7、单位：总部、24所、26所、44所
					duty_entp_id = getCellStringValue(row.getCell(column++), true);
					String duty_entp_name = "";
					if (duty_entp_id.equals("总部")) {
						duty_entp_name = "1";
					} else if (duty_entp_id.equals("24所")) {
						duty_entp_name = "2";
					} else if (duty_entp_id.equals("26所")) {
						duty_entp_name = "3";
					} else if (duty_entp_id.equals("44所")) {
						duty_entp_name = "4";
					} else {
						entpWrongList.add(num + 1);
						throw new CellBlankException("单位[" + duty_entp_id + "]为空或填写错误");
					}

					// 8、联接网络:涉密网、科研网、市政网、单机、互联网、非密专网、其他
					String internet_type_name = getCellStringValue(row.getCell(column++), true);
					if (internet_type_name.equals("涉密网")) {
						internet_type = 1;
					} else if (internet_type_name.equals("科研网")) {
						internet_type = 2;
					} else if (internet_type_name.equals("市政网")) {
						internet_type = 3;
					} else if (internet_type_name.equals("单机")) {
						internet_type = 4;
					} else if (internet_type_name.equals("互联网")) {
						internet_type = 5;
					} else if (internet_type_name.equals("非密专网")) {
						internet_type = 6;
					} else if (internet_type_name.equals("其他")) {
						internet_type = 7;
					} else {
						internettypeWrongList.add(num + 1);
						throw new CellBlankException("联接网络[" + internet_type + "]为空或者填写错误");
					}
					// 9、设备类型
					String med_type_name = getCellStringValue(row.getCell(column++), true);
					if (med_type_name.length() < 1) {
						medtypeWrongList.add(num + 1);
						throw new CellBlankException("设备类型[" + med_type + "]为空,请填写");
					}

					Map<String, Object> map = new HashMap<String, Object>();
					map.put("info_type", med_type_name);
					InfoType typemed = null;
					typemed = computerService.getInfoTypeByID(map);
					if (typemed != null) {
						med_type = typemed.getInfo_id();
					} else {
						medtypeWrongList.add(num + 1);
						throw new CellBlankException("设备类型[" + med_type_name + "]填写错误");
					}

					// 10、 操作系统
					computer_os = getCellStringValue(row.getCell(column++), true);

					// 11、操作系统 安装日期
					install_time = formatCellDateValue(row.getCell(column++), true, "yyyy-MM-dd");

					// 12、硬盘序列号
					hdisk_no = getCellStringValue(row.getCell(column++), true);

					// 13、key编号
					key_code = getCellStringValue(row.getCell(column++), true);

					// 14、IP
					computer_ip = getCellStringValue(row.getCell(column++), true);

					// 15、MAC
					computer_mac = getCellStringValue(row.getCell(column++), true);

					// 16、输出端口
					output_point = getCellStringValue(row.getCell(column++), true);

					// 17、放置区域
					storage_area = getCellStringValue(row.getCell(column++), true);
					// 18、放置地点
					storage_location = getCellStringValue(row.getCell(column++), true);
					// 19、使用情况// 判断计算机状态是否正确
					computer_status_name = getCellStringValue(row.getCell(column++), true);

					computer_status = 0;
					if (computer_status_name.equals("在用")) {
						computer_status = 1;
					} else if (computer_status_name.equals("停用")) {
						computer_status = 2;
					} else if (computer_status_name.equals("维修")) {
						computer_status = 3;
					} else if (computer_status_name.equals("报废")) {
						computer_status = 4;
					} else if (computer_status_name.equals("销毁")) {
						computer_status = 5;
					} else if (computer_status_name.equals("申请维修")) {
						computer_status = 6;
					} else if (computer_status_name.equals("申请报废")) {
						computer_status = 7;
					} else {
						statusWrongList.add(num + 1);
						throw new CellValueWrongException("使用情况[" + computer_status_name + "]非法，请核实后填写");
					}
					// 备注：
					summ = getCellStringValue(row.getCell(column++), true);

					// 安全产品安装情况
					String software_type_name = getCellStringValue(row.getCell(column++), true);
					String software = "";
					if (!software_type_name.equals("")) {
						String[] typename = software_type_name.split(",");
						Map<String, Object> map1 = new HashMap<String, Object>();
						InfoType softtype = null;
						for (int i = 0; i < typename.length; i++) {
							map1.put("info_type", typename[i]);
							map1.put("product_type", "1");
							map1.put("statue", "0");
							softtype = computerService.getInfoTypeByID(map1);
							if (softtype != null) {
								software += softtype.getInfo_id() + ",";
							} else {
								medtypeWrongList.add(num + 1);
								throw new CellBlankException("类型[" + typename[i] + "]填写错误");
							}
						}
					}
					// 掩码
					mark_code = getCellStringValue(row.getCell(column++), true);
					// 网关
					computer_gateway = getCellStringValue(row.getCell(column++), true);
					// VLAN
					vlan_num = getCellStringValue(row.getCell(column++), true);
					// 交换机IP
					switch_num = getCellStringValue(row.getCell(column++), true);
					// 交换机端口
					switch_point = getCellStringValue(row.getCell(column++), true);

					// 生成计算机条码
					String dept_id = duty_dept_id;
					if (StringUtils.hasLength(dept_id)) {
						// computer_barcode = basicService.createCETCEntityBarcode(dept_id);
						// 不区分份数，默认为0
						computer_barcode = basicService.createNewCETCBarcode(dept_id, 0);
					} else {
						// computer_barcode = basicService.createCETCEntityBarcode("00");
						// 不区分份数，默认为0
						computer_barcode = basicService.createNewCETCBarcode("", 0);
					}

					EntityComputer computer = new EntityComputer();
					computer.setComputer_barcode(computer_barcode);
					computer.setComputer_name(computer_name);
					computer.setComputer_code(computer_code);
					computer.setOldconf_code(oldconf_code);
					computer.setSeclv_code(seclv_code);
					computer.setDuty_user_id(duty_user_id);
					computer.setDuty_dept_id(duty_dept_id);
					computer.setDuty_entp_id(duty_entp_name);
					computer.setInternet_type(internet_type);
					computer.setMed_type(med_type);
					computer.setComputer_os(computer_os);
					computer.setInstall_time(install_time);
					computer.setHdisk_no(hdisk_no);
					computer.setKey_code(key_code);
					computer.setComputer_ip(computer_ip);
					computer.setComputer_mac(computer_mac);
					computer.setOutput_point(output_point);
					computer.setStorage_area(storage_area);
					computer.setStorage_location(storage_location);
					computer.setComputer_status(computer_status);
					computer.setSoftware_type(software);
					computer.setSumm(summ);
					computer.setUser_iidd(user_iidd);
					enter_time = new Date();
					computer.setEnter_time(enter_time);
					computer.setComputer_gateway(computer_gateway);
					computer.setMark_code(mark_code);
					computer.setVlan_num(vlan_num);
					computer.setSwitch_num(switch_num);
					computer.setSwitch_point(switch_point);

					// 生成保密编号流水号
					String conf = computerService.createSecretSerial("1", med_type, duty_dept_id);
					computer.setConf_code(conf);

					// 生成计算机生命周期记录
					BMCycleItem cycleitem = new BMCycleItem();
					cycleitem.setBarcode(computer_barcode);
					cycleitem.setEntity_type("COMPUTER");
					cycleitem.setOper_time(new Date());
					cycleitem.setUser_name(getCurUser().getUser_name());
					cycleitem.setDept_name(getCurUser().getDept_name());
					cycleitem.setOper("LEADIN");
					computerService.addEntityComputer(computer, cycleitem);
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
					logger.error("解析计算机数据出现异常：行[" + (num + 1) + "]，列[" + column + "]");
					logger.error(e.getMessage());
					failList.add(num + 1);
					continue;
				}
				insertCommonLog("批量导入计算机台账");
			}
		}
		return SUCCESS;
	}
}