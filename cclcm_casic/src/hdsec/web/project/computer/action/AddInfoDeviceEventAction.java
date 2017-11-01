package hdsec.web.project.computer.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.computer.model.EntityDeviceTemp;
import hdsec.web.project.computer.model.InfoDeviceEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

public class AddInfoDeviceEventAction extends ComputerBaseAction {

	private static final long serialVersionUID = 1L;
	private String device_barcode = "";// 设备条码编号
	private String event_code = "";// 作业流水号
	private Integer seclv_code = null;// 作业密级
	private String project_code = "";// 所属项目编号
	private String usage_code = "";// 用途编号
	private String summ = "";// 备注
	private String duty_user_id = "";
	private String duty_user_name = "";
	private String duty_dept_id = "";
	private String duty_dept_name = "";
	private Integer device_type = null;
	private String info_id = "";
	private String contact_num = "";
	private String his_job_code = "";
	private String next_approver = "";// 下级审批人
	private String jobType_DEVICE = JobTypeEnum.INFO_DEVICE.getJobTypeCode();
	private String jobType_OTHER = JobTypeEnum.INFO_OTHER.getJobTypeCode();
	private String jobType = "";

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
	private String oldconf_code = "";// 原保密编号
	private String company = "";// 单位
	private Integer device_statues = null;// 状态

	public void setDevice_statues(Integer device_statues) {
		this.device_statues = device_statues;
	}

	public String getJobType() {
		return jobType;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getJobType_DEVICE() {
		return jobType_DEVICE;
	}

	public String getJobType_OTHER() {
		return jobType_OTHER;
	}

	public void setOldconf_code(String oldconf_code) {
		this.oldconf_code = oldconf_code;
	}

	public void setDevice_barcode(String device_barcode) {
		this.device_barcode = device_barcode;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
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

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getHis_job_code() {
		return his_job_code;
	}

	public void setHis_job_code(String his_job_code) {
		this.his_job_code = his_job_code;
	}

	public String getNext_approver() {
		return next_approver;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}

	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public void setDuty_user_id(String duty_user_id) {
		this.duty_user_id = duty_user_id;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name;
	}

	public void setDuty_dept_id(String duty_dept_id) {
		this.duty_dept_id = duty_dept_id;
	}

	public void setDuty_dept_name(String duty_dept_name) {
		this.duty_dept_name = duty_dept_name;
	}

	public void setDevice_type(Integer device_type) {
		this.device_type = device_type;
	}

	public void setInfo_id(String info_id) {
		this.info_id = info_id;
	}

	public void setContact_num(String contact_num) {
		this.contact_num = contact_num;
	}

	// 获取所有密级
	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_code)) {
			// 查询用户信息
			String user_iidd = getCurUser().getUser_iidd();
			String dept_id = getCurUser().getDept_id();

			// 生成条码
			if (StringUtils.hasLength(duty_dept_id)) {
				// device_barcode = basicService.createCETCEntityBarcode(duty_dept_id);
				// 不区分份数，默认为0
				device_barcode = basicService.createNewCETCBarcode(duty_dept_id, 0);
			} else {
				// device_barcode = basicService.createCETCEntityBarcode("00");
				// 不区分份数，默认为0
				device_barcode = basicService.createNewCETCBarcode("", 0);
			}
			// 信息设备详细属性更新到临时表中
			EntityDeviceTemp temp = new EntityDeviceTemp(event_code, "", device_series, device_version, brand_type,
					device_seclv, device_usage, purchase_time, use_time, location, serial_num, cert_name, cert_num,
					memory, device_type, info_id, "", oldconf_code, company, device_statues);

			// 新增作业添加
			InfoDeviceEvent device = new InfoDeviceEvent(device_barcode, user_iidd, dept_id, event_code, seclv_code,
					usage_code, project_code, summ, duty_user_id, duty_user_name, duty_dept_id, duty_dept_name,
					device_type, info_id, contact_num, his_job_code);
			if (device_type == 5 || device_type == 6) {
				device.setJobType(JobTypeEnum.valueOf(jobType_DEVICE));
			} else {
				device.setJobType(JobTypeEnum.valueOf(jobType_OTHER));
			}
			device.setEvent_type(1);
			computerService.addInfoDeviceTemp(temp);
			computerService.addInfoDeviceEvent(device, next_approver);
			insertCommonLog("添加信息设备申请[" + event_code + "]");
			return "ok";
		} else {
			event_code = getCurUser().getUser_iidd() + "_INFODEVICE_" + System.currentTimeMillis();

			return SUCCESS;
		}
	}
}