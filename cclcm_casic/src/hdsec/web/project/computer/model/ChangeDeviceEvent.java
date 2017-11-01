package hdsec.web.project.computer.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.BaseEvent;

import java.util.Date;

public class ChangeDeviceEvent extends BaseEvent {

	private String user_phone = "";
	private String barcode = "";
	private String event_reason = "";
	private String event_content = "";
	private Integer event_type = null;
	private Integer event_status = null;

	public String getEvent_status_name() {
		String name = "";
		if (event_status != null) {
			switch (event_status) {
			case 1:
				name = "未变更";
				break;
			case 2:
				name = "已变更";
				break;
			default:
				break;
			}
		}
		return name;
	}

	public Integer getEvent_status() {
		return event_status;
	}

	public void setEvent_status(Integer event_status) {
		this.event_status = event_status;
	}

	public String getEvent_name() {
		String name = "";
		if (event_type != null) {
			switch (event_type) {
			case 1:
				name = "新增计算机网络机";
				break;
			case 2:
				name = "新增计算机单机";
				break;
			case 3:
				name = "计算机变更";
				break;
			case 4:
				name = "计算机报废";
				break;
			case 5:
				name = "计算机维修";
				break;
			case 6:
				name = "计算机重装系统";
				break;
			case 7:
				name = "计算机退网";
				break;
			case 8:
				name = "USB-KEY申请/更新";
				break;
			case 9:
				name = "开通端口";
				break;
			case 10:
				name = "保留本地打印机";
				break;
			case 11:
				name = "笔记本变更";
				break;
			case 12:
				name = "笔记本维修";
				break;
			case 13:
				name = "笔记本报废";
				break;
			case 14:
				name = "笔记本重装系统";
				break;
			default:
				break;
			}
		}

		return name;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getEvent_reason() {
		return event_reason;
	}

	public void setEvent_reason(String event_reason) {
		this.event_reason = event_reason;
	}

	public String getEvent_content() {
		return event_content;
	}

	public void setEvent_content(String event_content) {
		this.event_content = event_content;
	}

	public Integer getEvent_type() {
		return event_type;
	}

	public void setEvent_type(Integer event_type) {
		this.event_type = event_type;
	}

	public ChangeDeviceEvent() {

	}

	public ChangeDeviceEvent(String event_code, String user_iidd, String dept_id, Date apply_time, int seclv_code,
			String useage_code, String project_code, String user_phone, String barcode, String event_reason,
			String event_content, Integer event_type, String summ) {
		super(JobTypeEnum.EVENT_INTCOM, event_code, user_iidd, dept_id, seclv_code, useage_code, project_code, summ);

		this.user_phone = user_phone;
		this.barcode = barcode;
		this.event_reason = event_reason;
		this.event_content = event_content;
		this.event_type = event_type;
	}

	public ChangeDeviceEvent(String event_code, String user_iidd, String dept_id, Integer seclv_code,
			String usage_code, String project_code, String summ, String user_phone, String barcode, Integer event_type) {

		super(JobTypeEnum.BOOK_CHANGE, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);

		this.user_phone = user_phone;
		this.barcode = barcode;
		this.event_type = event_type;
	}
}
