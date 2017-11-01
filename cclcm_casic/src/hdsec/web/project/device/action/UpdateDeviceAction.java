package hdsec.web.project.device.action;

import hdsec.web.project.device.model.DeviceType;
import hdsec.web.project.device.model.EntityDevice;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 修改磁介质信息
 * 
 * @author lixiang
 * 
 */
public class UpdateDeviceAction extends DeviceBaseAction {

	private static final long serialVersionUID = 1L;
	private String device_code = "";// 主键ID
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
	private EntityDevice device = null;
	private String update = "N";

	public EntityDevice getDevice() {
		return device;
	}

	public void setDevice_code(String device_code) {
		this.device_code = device_code;
	}

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

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public List<DeviceType> getDeviceTypeList() {
		return deviceService.getDeviceTypeList();
	}

	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("N")) {
			// device = deviceService.getDeviceByCode(device_code);
			if (StringUtils.hasLength(device_barcode)) {
				device = deviceService.getDeviceByBarcode(device_barcode);
				return SUCCESS;
			} else {
				throw new Exception("无法查询条码号，请重新尝试。");
			}
		} else {
			device = new EntityDevice(device_code, device_name, user_iidd, dept_id, device_series, device_barcode,
					new Date(), med_type, seclv_code, duty_user_iidd, med_content, borrow_user_iidd, device_model,
					device_detail);
			deviceService.updateDevice(device);
			insertCommonLog("修改磁介质：条码号[" + device_barcode + "]");
			return "ok";
		}
	}

}
