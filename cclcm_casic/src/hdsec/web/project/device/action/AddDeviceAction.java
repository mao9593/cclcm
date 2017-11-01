package hdsec.web.project.device.action;

import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.PropertiesFileUtil;
import hdsec.web.project.device.model.DeviceStatusEnum;
import hdsec.web.project.device.model.DeviceType;
import hdsec.web.project.device.model.EntityDevice;
import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 添加磁介质
 * 
 * @author lixiang
 * 
 */
public class AddDeviceAction extends DeviceBaseAction {
	private static final long serialVersionUID = 1L;
	private String device_name = "";// 名称
	private String user_iidd = "";// 录入员ID
	private String dept_id = "";// 设备所属部门ID
	private String device_series = "";// 设备编号
	private String device_barcode = "";// 条码号
	private Integer med_type = null;// 介质类型(1U盘,2移动硬盘,3笔记本,4照相机,5录像机,6录音笔,8软盘,9磁带)
	private Integer seclv_code = null;// 密级
	private String duty_user_iidd = "";// 责任人ID
	private String med_content = "";// 说明
	private String borrow_user_iidd = "";// 借用人ID
	private String device_model = "";// 型号 (例如PC机　DELL OPTIPLEX 360)
	private String device_detail = "";// 设备配置：比如U盘 1G\2G\4G 是否加密PC CPU/内存/硬盘
										// 内容格式由输入处限定
	String unitCode = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "unit.code");// 5S
	// 获取unit.CompanyType haojia 20160629
	String strCompayType = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "unit.CompanyType");

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public void setDevice_series(String device_series) {
		this.device_series = device_series;
	}

	public void setDevice_barcode(String device_barcode) {
		this.device_barcode = device_barcode;
	}

	public void setMed_type(Integer med_type) {
		this.med_type = med_type;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public void setDuty_user_iidd(String duty_user_iidd) {
		this.duty_user_iidd = duty_user_iidd;
	}

	public void setMed_content(String med_content) {
		this.med_content = med_content;
	}

	public void setBorrow_user_iidd(String borrow_user_iidd) {
		this.borrow_user_iidd = borrow_user_iidd;
	}

	public void setDevice_model(String device_model) {
		this.device_model = device_model;
	}

	public void setDevice_detail(String device_detail) {
		this.device_detail = device_detail;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public List<DeviceType> getDeviceTypeList() {
		return deviceService.getDeviceTypeList();
	}

	@Override
	public String executeFunction() throws Exception {
		if (device_name.isEmpty()) {
			return SUCCESS;
		} else {
			String device_code = "DVC-" + String.valueOf(UUID.randomUUID().toString());
			// 生成barcode
			// device_barcode = "device_barcode_" + String.valueOf(UUID.randomUUID().toString());
			// 中物5所条码
			if (unitCode != "" && strCompayType.equalsIgnoreCase("CAEP")) {
				// if (unitCode != "" && unitCode.equalsIgnoreCase("5S")) {
				device_barcode = basicService.createEntityBarcodeCAEP("DEVICE", seclv_code, dept_id);
			} else {
				device_barcode = basicService.createEntityBarcode("DEVICE");
			}
			// 动态库生成条码方式
			/*
			 * Map<String, String> Paras = new HashMap(); Paras.put("USERID", user_iidd); Paras.put("EVENTTYPE", "5");
			 * Paras.put("EVENTCODE", device_code); Paras.put("COUNT", "0"); Map<String, String> Cmap =
			 * CreateBarcodeUtil.CreateBarcode(Paras, 4); device_barcode = Cmap.get("Barcode");
			 */
			EntityDevice device = new EntityDevice(device_code, device_name, user_iidd, dept_id, device_series,
					device_barcode, new Date(), med_type, seclv_code, duty_user_iidd, med_content, borrow_user_iidd,
					device_model, device_detail);
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
			insertCommonLog("添加磁介质[" + device_barcode + "]");
			return "ok";
		}
	}
}
