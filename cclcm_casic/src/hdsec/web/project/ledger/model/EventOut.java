package hdsec.web.project.ledger.model;

import hdsec.web.project.common.BaseEvent;

import java.util.Date;

public class EventOut extends BaseEvent {

	private String entity_Type;
	private String barcode;
	private String carryout_Info;
	private String carryin_Info;
	private String approval_Code;
	private Date start_Time;
	private Date end_Time;
	private Date finish_Time;
	private Integer carryout_Status;
	private String ext_Column;
	private String send_way = ""; // 外发方式 0:专人携带; 1:发机要
	private String output_confidential_num = ""; // 外发机要号

	public String getOutput_confidential_num() {
		return output_confidential_num;
	}

	public void setOutput_confidential_num(String output_confidential_num) {
		this.output_confidential_num = output_confidential_num;
	}

	public String getSend_way() {
		return send_way;
	}

	public void setSend_way(String send_way) {
		this.send_way = send_way;
	}

	public String getSend_way_str() {
		String send_way_str = "";
		if (send_way.equals("0")) {
			send_way_str = "专人携带";
		} else if (send_way.equals("1")) {
			send_way_str = "发机要";
		} else {
			send_way_str = "";
		}
		return send_way_str;
	}

	public String getEntity_Type() {
		return entity_Type;
	}

	public void setEntity_Type(String entity_Type) {
		this.entity_Type = entity_Type;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getCarryout_Info() {
		return carryout_Info;
	}

	public void setCarryout_Info(String carryout_Info) {
		this.carryout_Info = carryout_Info;
	}

	public String getCarryin_Info() {
		return carryin_Info;
	}

	public void setCarryin_Info(String carryin_Info) {
		this.carryin_Info = carryin_Info;
	}

	public String getApproval_Code() {
		return approval_Code;
	}

	public void setApproval_Code(String approval_Code) {
		this.approval_Code = approval_Code;
	}

	public Date getStart_Time() {
		return start_Time;
	}

	public void setStart_Time(Date start_Time) {
		this.start_Time = start_Time;
	}

	public Date getEnd_Time() {
		return end_Time;
	}

	public void setEnd_Time(Date end_Time) {
		this.end_Time = end_Time;
	}

	public Date getFinish_Time() {
		return finish_Time;
	}

	public void setFinish_Time(Date finish_Time) {
		this.finish_Time = finish_Time;
	}

	public Integer getCarryout_Status() {
		return carryout_Status;
	}

	public void setCarryout_Status(Integer carryout_Status) {
		this.carryout_Status = carryout_Status;
	}

	public String getExt_Column() {
		return ext_Column;
	}

	public void setExt_Column(String ext_Column) {
		this.ext_Column = ext_Column;
	}

	public String getcarryoutStatus_name() {
		String carryoutStatus_name = "";
		if (carryout_Status == 0) {
			carryoutStatus_name = "未带回";
		} else if (carryout_Status == 1) {
			carryoutStatus_name = "已带回";
		}
		return carryoutStatus_name;
	}

	public String getentityType_name() {
		String entityType_name = "";
		if (entity_Type.equals("Paper")) {
			entityType_name = "文件";
		} else {
			entityType_name = "光盘";
		}
		return entityType_name;
	}

	public String getstart_Time_str() {
		return start_Time == null ? "" : getSdf().format(start_Time);
	}

	public String getend_Time_str() {
		return end_Time == null ? "" : getSdf().format(end_Time);
	}

}
