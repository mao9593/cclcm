package hdsec.web.project.transfer.model;

import hdsec.web.project.common.BaseEvent;

import java.util.Date;

public class EventTransfer extends BaseEvent {

	private String entity_type;
	private String barcode;
	private String accept_user_iidd;
	private String accept_user_name;
	private String accept_dept_id;
	private String accept_dept_name;
	private int transfer_status;
	private String his_job_code;
	private String event_code;
	private Date finish_time = null;// 完成时间
	private String file_name;
	private String transfer_comment;

	public String getTransfer_comment() {
		return transfer_comment;
	}

	public void setTransfer_comment(String transfer_comment) {
		this.transfer_comment = transfer_comment;
	}

	public String getEntity_type() {
		return entity_type;
	}

	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}

	public String getEntity_type_name() {
		String name = "";
		switch (this.entity_type) {
		case "CD":
			name = "光盘";
			break;
		case "Paper":
			name = "文件";
			break;
		}
		return name;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getAccept_user_iidd() {
		return accept_user_iidd;
	}

	public void setAccept_user_iidd(String accept_user_iidd) {
		this.accept_user_iidd = accept_user_iidd;
	}

	public String getAccept_dept_id() {
		return accept_dept_id;
	}

	public void setAccept_dept_id(String accept_dept_id) {
		this.accept_dept_id = accept_dept_id;
	}

	public int getTransfer_status() {
		return transfer_status;
	}

	public void setTransfer_status(int transfer_status) {
		this.transfer_status = transfer_status;
	}

	public String getHis_job_code() {
		return his_job_code;
	}

	public void setHis_job_code(String his_job_code) {
		this.his_job_code = his_job_code;
	}

	public String getAccept_dept_name() {
		return accept_dept_name;
	}

	public void setAccept_dept_name(String accept_dept_name) {
		this.accept_dept_name = accept_dept_name;
	}

	public String getTransfer_status_name() {
		String name = "";
		switch (this.transfer_status) {
		case 0:
			name = "未完成";
			break;
		case 1:
			name = "已完成";
			break;
		case 2:
			name = "待交接";
			break;
		case 3:
			name = "已拒绝";
			break;
		}
		return name;
	}

	public String getAccept_user_name() {
		return accept_user_name;
	}

	public void setAccept_user_name(String accept_user_name) {
		this.accept_user_name = accept_user_name;
	}

	@Override
	public String getEvent_code() {
		return event_code;
	}

	@Override
	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public Date getFinish_time() {
		return finish_time;
	}

	public void setFinish_time(Date finish_time) {
		this.finish_time = finish_time;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

}
