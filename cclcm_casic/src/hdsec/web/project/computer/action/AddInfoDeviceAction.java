package hdsec.web.project.computer.action;

import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.common.bm.BMCycleItem;
import hdsec.web.project.computer.model.EntityInfoDevice;
import hdsec.web.project.user.model.SecLevel;
import hdsec.web.project.user.model.UserSecurity;

import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

public class AddInfoDeviceAction extends ComputerBaseAction {

	private static final long serialVersionUID = 1L;
	private String device_code = "";// 设备条码编号
	private String user_iidd = "";// 录入员ID
	private String user_name = "";// 录入员姓名
	private String duty_user_id = "";// 责任人ID
	private String duty_user_name = "";// 责任人
	private String duty_dept_id = "";// 责任人部门ID
	private String duty_dept_name = "";// 责任人部门
	private String device_usage = "";// 联系电话
	private String conf_code = "";// 保密编号
	private String device_series = "";// 设备编号
	private String device_name = "";// 设备名称
	private String brand_type = "";// 品牌类型
	private Integer device_seclv = null;// 设备密级
	private Date enter_time = null;// 登记时间
	private Date use_time = null;// 启用时间
	private Date destroy_time = null;// 报废时间
	private String location = "";// 安装地点
	private String cert_name = "";// 检测证书名称
	private String cert_num = "";// 证书编号
	private String memory = "";// 内存
	private Integer device_type = null;// 设备类型（1: 办公自动化设备2:外部设备3:安全产品4:介质5:其他）
	private String info_id = null;// 详细设备ID
	private String summ = "";// 备注
	private Date purchase_time = null;
	private String device_version = null;
	private Integer device_statues = null;// 状态

	public void setDevice_statues(Integer device_statues) {
		this.device_statues = device_statues;
	}

	public String getDevice_version() {
		return device_version;
	}

	public void setDevice_version(String device_version) {
		this.device_version = device_version;
	}

	public Date getPurchase_time() {
		return purchase_time;
	}

	public void setPurchase_time(Date purchase_time) {
		this.purchase_time = purchase_time;
	}

	public String getDevice_code() {
		return device_code;
	}

	public void setDevice_code(String device_code) {
		this.device_code = device_code;
	}

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getDuty_user_id() {
		return duty_user_id;
	}

	public void setDuty_user_id(String duty_user_id) {
		this.duty_user_id = duty_user_id;
	}

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name;
	}

	public String getDuty_dept_id() {
		return duty_dept_id;
	}

	public void setDuty_dept_id(String duty_dept_id) {
		this.duty_dept_id = duty_dept_id;
	}

	public String getDuty_dept_name() {
		return duty_dept_name;
	}

	public void setDuty_dept_name(String duty_dept_name) {
		this.duty_dept_name = duty_dept_name;
	}

	public String getDevice_usage() {
		return device_usage;
	}

	public void setDevice_usage(String device_usage) {
		this.device_usage = device_usage;
	}

	public String getConf_code() {
		return conf_code;
	}

	public void setConf_code(String conf_code) {
		this.conf_code = conf_code;
	}

	public String getDevice_series() {
		return device_series;
	}

	public void setDevice_series(String device_series) {
		this.device_series = device_series;
	}

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public String getBrand_type() {
		return brand_type;
	}

	public void setBrand_type(String brand_type) {
		this.brand_type = brand_type;
	}

	public Integer getDevice_seclv() {
		return device_seclv;
	}

	public void setDevice_seclv(Integer device_seclv) {
		this.device_seclv = device_seclv;
	}

	public Date getEnter_time() {
		return enter_time;
	}

	public void setEnter_time(Date enter_time) {
		this.enter_time = enter_time;
	}

	public Date getUse_time() {
		return use_time;
	}

	public void setUse_time(Date use_time) {
		this.use_time = use_time;
	}

	public Date getDestroy_time() {
		return destroy_time;
	}

	public void setDestroy_time(Date destroy_time) {
		this.destroy_time = destroy_time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCert_name() {
		return cert_name;
	}

	public void setCert_name(String cert_name) {
		this.cert_name = cert_name;
	}

	public String getCert_num() {
		return cert_num;
	}

	public void setCert_num(String cert_num) {
		this.cert_num = cert_num;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public Integer getDevice_type() {
		return device_type;
	}

	public void setDevice_type(Integer device_type) {
		this.device_type = device_type;
	}

	public String getInfo_id() {
		return info_id;
	}

	public void setInfo_id(String info_id) {
		this.info_id = info_id;
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	// 获取所有密级
	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public List<SysUsage> getUsageList() {
		return basicService.getSysUsageList();
	}

	public List<SysProject> getProjectList() {
		return basicService.getSysProjectList();
	}

	public List<UserSecurity> getSecurityList() {
		return userService.getSecurityList();
	}

	@Override
	public String executeFunction() throws Exception {
		if (device_type == null) {
			return SUCCESS;
		} else {
			if (StringUtils.hasLength(duty_dept_id)) {
				// device_code = basicService.createCETCEntityBarcode(duty_dept_id);
				// 不区分份数，默认为0
				device_code = basicService.createNewCETCBarcode(duty_dept_id, 0);
			} else {
				// device_code = basicService.createCETCEntityBarcode("00");
				// 不区分份数，默认为0
				device_code = basicService.createNewCETCBarcode("", 0);
			}

			// 生成保密编号
			String num = computerService.createSecretSerial(device_type.toString(), info_id, duty_dept_id);

			EntityInfoDevice infoDevice = new EntityInfoDevice(device_code, null, user_iidd, user_name, getCurUser()
					.getDept_id(), getCurUser().getDept_name(), duty_user_id, duty_user_name, duty_dept_id,
					duty_dept_name, num, device_series, device_version, brand_type, device_seclv, device_usage,
					purchase_time, use_time, destroy_time, location, null, cert_name, cert_num, memory, device_type,
					info_id, summ, "", "", device_statues);
			BMCycleItem cycleitem = new BMCycleItem();
			cycleitem.setBarcode(device_code);
			cycleitem.setEntity_type("DEVICE");
			cycleitem.setOper_time(new Date());
			cycleitem.setUser_name(getCurUser().getUser_name());
			cycleitem.setDept_name(getCurUser().getDept_name());
			cycleitem.setOper("ADD");
			computerService.addInfoDevice(infoDevice, cycleitem);
			return "ok";
		}
	}
}