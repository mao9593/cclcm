package hdsec.web.project.device.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.util.ActivitiCons;
import hdsec.web.project.common.BaseDomain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 磁介质
 * 
 * @author lixiang
 * 
 */
public class EntityDevice extends BaseDomain {
	private String device_code = "";// 编号
	private String device_name = "";// 名称
	private String user_iidd = "";// 录入员ID
	private String dept_id = "";// 设备所属部门ID
	private String device_series = "";// 设备编号
	private String device_barcode = "";// 条码号
	private Date enter_time = null;// 登记时间
	private Integer med_type = null;// 介质类型(1U盘,2移动硬盘,3便携式计算机,4照相机,5录像机,6录音笔,7暂时为空[预留位RFID专用],8软盘,9磁带,10录像带,11录音带,12移动光驱,13红黑电源,14安全设备,15多功能导入装置,16硬盘)
	private Integer seclv_code = null;// 密级
	private String duty_user_iidd = "";// 责任人ID
	private String med_content = "";// 说明
	private String borrow_user_iidd = "";// 借用人ID
	private DeviceStatusEnum deviceStatus;// 状态
	private String device_model = "";// 型号 (例如PC机　DELL OPTIPLEX 360)
	private String device_detail = "";// 设备配置：比如U盘 1G\2G\4G 是否加密PC CPU/内存/硬盘
										// 内容格式由输入处限定

	private String dept_name = "";
	private String seclv_name = "";
	private String user_name = "";
	private String duty_user_name = "";
	private String borrow_user_name = "";
	private String job_code;
	private JobTypeEnum jobType = null;
	private String job_status = "";
	private String borrow_dept_iidd;
	private String borrow_dept_name;

	public String getBorrow_dept_iidd() {
		return borrow_dept_iidd;
	}

	public void setBorrow_dept_iidd(String borrow_dept_iidd) {
		this.borrow_dept_iidd = borrow_dept_iidd;
	}

	public String getBorrow_dept_name() {
		return borrow_dept_name;
	}

	public void setBorrow_dept_name(String borrow_dept_name) {
		this.borrow_dept_name = borrow_dept_name;
	}

	public String getDevice_code() {
		return device_code;
	}

	public void setDevice_code(String device_code) {
		this.device_code = device_code;
	}

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getDevice_series() {
		return device_series;
	}

	public void setDevice_series(String device_series) {
		this.device_series = device_series;
	}

	public String getDevice_barcode() {
		return device_barcode;
	}

	public void setDevice_barcode(String device_barcode) {
		this.device_barcode = device_barcode;
	}

	public Date getEnter_time() {
		return enter_time;
	}

	public void setEnter_time(Date enter_time) {
		this.enter_time = enter_time;
	}

	public String getEnter_time_str() {
		return enter_time == null ? "" : getSdf().format(enter_time);
	}

	public Integer getMed_type() {
		return med_type;
	}

	public void setMed_type(Integer med_type) {
		this.med_type = med_type;
	}

	public String getMed_type_name() {
		String name = "";
		switch (this.med_type) {
		case 1:
			name = "U盘";
			break;
		case 2:
			name = "移动硬盘";
			break;
		case 3:
			name = "便携式计算机";
			break;
		case 4:
			name = "照相机";
			break;
		case 5:
			name = "录像机";
			break;
		case 6:
			name = "录音笔";
			break;
		case 8:
			name = "软盘";
			break;
		case 9:
			name = "磁带";
			break;
		case 10:
			name = "录像带";
			break;
		case 11:
			name = "录音带";
			break;
		case 12:
			name = "移动光驱";
			break;
		case 13:
			name = "红黑电源";
			break;
		case 14:
			name = "安全设备";
			break;
		case 15:
			name = "多功能导入装置";
			break;
		case 16:
			name = "硬盘";
			break;
		}
		return name;
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getDuty_user_iidd() {
		return duty_user_iidd;
	}

	public void setDuty_user_iidd(String duty_user_iidd) {
		this.duty_user_iidd = duty_user_iidd;
	}

	public String getMed_content() {
		return med_content;
	}

	public void setMed_content(String med_content) {
		this.med_content = med_content;
	}

	public String getBorrow_user_iidd() {
		return borrow_user_iidd;
	}

	public void setBorrow_user_iidd(String borrow_user_iidd) {
		this.borrow_user_iidd = borrow_user_iidd;
	}

	public DeviceStatusEnum getDeviceStatus() {
		return deviceStatus;
	}

	public void setDevice_status(Integer device_status) {
		if (device_status == null) {
			device_status = 0;
		}
		this.deviceStatus = DeviceStatusEnum.valueOf("DS" + device_status);
	}

	public void setDeviceStatus(DeviceStatusEnum deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public String getDevice_status_name() {
		return this.deviceStatus.getName();
	}

	public Integer getDevice_status() {
		return this.deviceStatus.getKey();
	}

	public String getDevice_model() {
		return device_model;
	}

	public void setDevice_model(String device_model) {
		this.device_model = device_model;
	}

	public String getDevice_detail() {
		return device_detail;
	}

	public void setDevice_detail(String device_detail) {
		this.device_detail = device_detail;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getSeclv_name() {
		return seclv_name;
	}

	public void setSeclv_name(String seclv_name) {
		this.seclv_name = seclv_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name;
	}

	public String getBorrow_user_name() {
		return borrow_user_name;
	}

	public void setBorrow_user_name(String borrow_user_name) {
		this.borrow_user_name = borrow_user_name;
	}

	public String getJob_code() {
		return job_code;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public JobTypeEnum getJobType() {
		return jobType;
	}

	public void setJobType(JobTypeEnum jobType) {
		this.jobType = jobType;
	}

	public String getJob_status() {
		return job_status;
	}

	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}

	public String getJob_status_name() {
		String name = "";
		switch (this.job_status) {
		case ActivitiCons.APPLY_APPROVED_DEFAULT:
			name = "待审批";
			break;
		case ActivitiCons.APPLY_APPROVED_UNDER:
			name = "审批中";
			break;
		case ActivitiCons.APPLY_APPROVED_PASS:
			name = "已通过";
			break;
		case ActivitiCons.APPLY_APPROVED_REJECT:
			name = "已驳回";
			break;
		case ActivitiCons.APPLY_APPROVED_END:
			name = "已关闭";
			break;
		default:
			name = "未申请";
			break;
		}
		return name;
	}

	public EntityDevice() {
		super();
	}

	public EntityDevice(SimpleDateFormat sdf) {
		super(sdf);
	}

	public EntityDevice(String device_code, String device_name, String user_iidd, String dept_id, String device_series,
			String device_barcode, Date enter_time, Integer med_type, Integer seclv_code, String duty_user_iidd,
			String med_content, String borrow_user_iidd, String device_model, String device_detail) {
		super();
		this.device_code = device_code;
		this.device_name = device_name;
		this.user_iidd = user_iidd;
		this.dept_id = dept_id;
		this.device_series = device_series;
		this.device_barcode = device_barcode;
		this.enter_time = enter_time;
		this.med_type = med_type;
		this.seclv_code = seclv_code;
		this.duty_user_iidd = duty_user_iidd;
		this.med_content = med_content;
		this.borrow_user_iidd = borrow_user_iidd;
		this.device_model = device_model;
		this.device_detail = device_detail;
	}

}
