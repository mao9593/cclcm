package hdsec.web.project.device.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.BaseEvent;

import java.util.Date;

/**
 * 磁介质借入借出作业类
 * 
 * @author lixiang
 * 
 */
public class DeviceEvent extends BaseEvent {
	private Integer med_type = null;// 介质类型介质类型(1U盘,2移动硬盘,3便携式计算机,4照相机,5录像机,6录音笔,7暂时为空[预留位RFID专用],8软盘,9磁带,10录像带,11录音带,12移动光驱,13红黑电源,14安全设备,15多功能导入装置,16硬盘)
	private Integer borrow_type = 0;// 借用类型（0首次申请,1申请延期）
	private String device_series = "";// 设备编号
	private String device_barcode = "";// 条码号
	private Integer borrow_date = null;// 借用时间,如:一个月
	private String place = "";// 地点
	private Date finish_time = null;// 借用时间
	private Date return_time = null;// 归还时间
	private Integer device_status = null;// 借用状态：0未交接1已交接2已归还

	public Integer getMed_type() {
		return med_type;
	}

	public void setMed_type(Integer med_type) {
		this.med_type = med_type;
	}

	public void setBorrow_type(Integer borrow_type) {
		this.borrow_type = borrow_type;
	}

	public void setDevice_series(String device_series) {
		this.device_series = device_series;
	}

	public void setDevice_barcode(String device_barcode) {
		this.device_barcode = device_barcode;
	}

	public void setBorrow_date(Integer borrow_date) {
		this.borrow_date = borrow_date;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getMed_type_name() {
		String name = "";
		if (med_type == null) {
			return "";
		} else {
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
	}

	public Integer getBorrow_type() {
		return borrow_type;
	}

	public String getBorrow_type_name() {
		return borrow_type == 1 ? "申请延期" : "首次申请";
	}

	public String getDevice_series() {
		return device_series;
	}

	public String getDevice_barcode() {
		return device_barcode;
	}

	public Integer getBorrow_date() {
		return borrow_date;
	}

	public String getPlace() {
		return place;
	}

	public DeviceEvent() {
		super(JobTypeEnum.DEVICE);
	}

	public Date getFinish_time() {
		return finish_time;
	}

	public void setFinish_time(Date finish_time) {
		this.finish_time = finish_time;
	}

	public Date getReturn_time() {
		return return_time;
	}

	public void setReturn_time(Date return_time) {
		this.return_time = return_time;
	}

	public Integer getDevice_status() {
		return device_status;
	}

	public void setDevice_status(Integer device_status) {
		this.device_status = device_status;
	}

	public String getDevice_status_name() {
		String name = "";
		if (device_status == null) {
			return "";
		} else {
			switch (this.device_status) {
			case 0:
				name = "未借入";
				break;
			case 1:
				name = "已借入";
				break;
			case 2:
				name = "已归还";
				break;
			default:
				name = "";
			}
			return name;
		}
	}

	public DeviceEvent(JobTypeEnum jobType, String event_code, String user_iidd, String dept_id, Integer seclv_code,
			String usage_code, String project_code, String summ) {
		super(JobTypeEnum.DEVICE, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);

	}

	public DeviceEvent(JobTypeEnum jobType) {
		super(JobTypeEnum.DEVICE);
	}

	public DeviceEvent(String user_iidd, String dept_id, String event_code, Integer seclv_code, String usage_code,
			String project_code, String summ, Integer med_type, Integer borrow_type, String device_series,
			String device_barcode, Integer borrow_date, String place) {
		super(JobTypeEnum.DEVICE, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);
		this.med_type = med_type;
		this.borrow_type = borrow_type;
		this.device_series = device_series;
		this.device_barcode = device_barcode;
		this.borrow_date = borrow_date;
		this.place = place;
	}

}
