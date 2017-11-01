package hdsec.web.project.ledger.model;

import hdsec.web.project.common.BaseEvent;

import java.util.Date;

/**
 * 送销作业类
 * 
 * @author renmingfei 2014-10-23
 */
public class SendDestroyEvent extends BaseEvent {
	private String entity_type;
	private String barcode;
	private int senddestroy_status;
	private String his_job_code;
	private Date finish_time = null;// 完成时间

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

	public int getSenddestroy_status() {
		return senddestroy_status;
	}

	public void setSenddestroy_status(int senddestroy_status) {
		this.senddestroy_status = senddestroy_status;
	}

	public String getHis_job_code() {
		return his_job_code;
	}

	public void setHis_job_code(String his_job_code) {
		this.his_job_code = his_job_code;
	}

	public String getSenddestroy_status_name() {
		String name = "";
		switch (this.senddestroy_status) {
		case 0:
			name = "未完成";
			break;
		case 1:
			name = "已完成";
			break;
		}
		return name;
	}

	public Date getFinish_time() {
		return finish_time;
	}

	public void setFinish_time(Date finish_time) {
		this.finish_time = finish_time;
	}

}
