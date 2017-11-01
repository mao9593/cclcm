package hdsec.web.project.computer.action;

import hdsec.web.project.computer.model.EntityInfoDevice;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

public class UpdateInfoDeviceAction extends ComputerBaseAction {

	private static final long serialVersionUID = 1L;
	private EntityInfoDevice device = null;
	private String device_barcode = ""; // 条码号
	private String device_series = "";// 设备编号
	private String device_version = "";// 型号
	private String brand_type = "";// 品牌类型
	private Integer device_seclv = null;// 设备密级
	private String device_usage = "";// 设备用途
	private Date purchase_time = null;// 采购时间
	private Date use_time = null;// 领用/启用时间
	private String location = "";// 安装地点
	private String serial_num = "";// 序列号
	private String cert_name = "";// 检测证书名称
	private String cert_num = "";// 证书编号
	private String memory = "";// 内存/容量
	private String type = "";
	private String oldconf_code = "";// 原保密编号
	private String company = "";// 单位
	private String detail = "";// 备注
	private Integer device_statues = null;// 状态

	public void setDevice_statues(Integer device_statues) {
		this.device_statues = device_statues;
	}

	public EntityInfoDevice getDevice() {
		return device;
	}

	public void setDevice_barcode(String device_barcode) {
		this.device_barcode = device_barcode;
	}

	public void setDevice_series(String device_series) {
		this.device_series = device_series;
	}

	public void setDevice_version(String device_version) {
		this.device_version = device_version;
	}

	public void setBrand_type(String brand_type) {
		this.brand_type = brand_type;
	}

	public void setDevice_seclv(Integer device_seclv) {
		this.device_seclv = device_seclv;
	}

	public void setDevice_usage(String device_usage) {
		this.device_usage = device_usage;
	}

	public void setPurchase_time(Date purchase_time) {
		this.purchase_time = purchase_time;
	}

	public void setUse_time(Date use_time) {
		this.use_time = use_time;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setSerial_num(String serial_num) {
		this.serial_num = serial_num;
	}

	public void setCert_name(String cert_name) {
		this.cert_name = cert_name;
	}

	public void setCert_num(String cert_num) {
		this.cert_num = cert_num;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setOldconf_code(String oldconf_code) {
		this.oldconf_code = oldconf_code;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(device_barcode)) {
			device = computerService.getInfoDeviceByBarcode(device_barcode);
			if (type.equals("UPDATE")) {
				EntityInfoDevice info = new EntityInfoDevice(device_barcode, "", getCurUser().getUser_iidd(),
						getCurUser().getUser_name(), getCurUser().getDept_id(), getCurUser().getDept_name(), "", "",
						"", "", "", device_series, device_version, brand_type, device_seclv, device_usage,
						purchase_time, use_time, null, location, serial_num, cert_name, cert_num, memory, null, null,
						detail, oldconf_code, company, device_statues);
				computerService.updateInfoDeviceByEntity(info);
				return "okupdate";
			}
			return SUCCESS;
		} else {
			throw new Exception("无法查询条码号，请重新尝试。");
		}
	}
}