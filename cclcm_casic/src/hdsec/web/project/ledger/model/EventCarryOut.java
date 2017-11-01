package hdsec.web.project.ledger.model;

import hdsec.web.project.common.BaseDomain;

import java.util.Date;

/**
 * EventCarryout entity. @author MyEclipse Persistence Tools
 */

public class EventCarryOut extends BaseDomain implements java.io.Serializable {

	// Fields

	private Integer id;
	private String event_Code;
	private String user_Iidd;
	private String dept_Id;
	private String entity_Type;
	private String barcode;
	private String project_Code;
	private String carryout_Info;
	private String carryin_Info;
	private String approval_Code;
	private Date start_Time;
	private Date end_Time;
	private Integer seclv_Code;
	private String usage_Code;
	private String summ;
	private Date finish_Time;
	private Integer carryout_Status;
	private String job_Code;
	private String ext_Column;

	public EventCarryOut() {
	}

	public EventCarryOut(String event_Code, String user_Iidd, String dept_Id, String entity_Type, String barcode,
			String project_Code, String carryout_Info, String carryin_Info, String approval_Code, Date start_Time,
			Date end_Time, Integer seclv_Code, String usage_Code, String summ, Integer carryout_Status,
			String job_Code, String ext_Column) {
		super();
		this.event_Code = event_Code;
		this.user_Iidd = user_Iidd;
		this.dept_Id = dept_Id;
		this.entity_Type = entity_Type;
		this.barcode = barcode;
		this.project_Code = project_Code;
		this.carryout_Info = carryout_Info;
		this.carryin_Info = carryin_Info;
		this.approval_Code = approval_Code;
		this.start_Time = start_Time;
		this.end_Time = end_Time;
		this.seclv_Code = seclv_Code;
		this.usage_Code = usage_Code;
		this.summ = summ;
		this.carryout_Status = carryout_Status;
		this.job_Code = job_Code;
		this.ext_Column = ext_Column;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEvent_Code() {
		return event_Code;
	}

	public void setEvent_Code(String event_Code) {
		this.event_Code = event_Code;
	}

	public String getUser_Iidd() {
		return user_Iidd;
	}

	public void setUser_Iidd(String user_Iidd) {
		this.user_Iidd = user_Iidd;
	}

	public String getDept_Id() {
		return dept_Id;
	}

	public void setDept_Id(String dept_Id) {
		this.dept_Id = dept_Id;
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

	public String getProject_Code() {
		return project_Code;
	}

	public void setProject_Code(String project_Code) {
		this.project_Code = project_Code;
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

	public String getstart_TimeSdf() {
		return start_Time == null ? "" : getSdf().format(start_Time);
	}

	public void setStart_Time(Date start_Time) {
		this.start_Time = start_Time;
	}

	public Date getEnd_Time() {
		return end_Time;
	}

	public String getend_TimeSdf() {
		return end_Time == null ? "" : getSdf().format(end_Time);
	}

	public void setEnd_Time(Date end_Time) {
		this.end_Time = end_Time;
	}

	public Integer getSeclv_Code() {
		return seclv_Code;
	}

	public void setSeclv_Code(Integer seclv_Code) {
		this.seclv_Code = seclv_Code;
	}

	public String getUsage_Code() {
		return usage_Code;
	}

	public void setUsage_Code(String usage_Code) {
		this.usage_Code = usage_Code;
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public Date getFinish_Time() {
		return finish_Time;
	}

	public String getfinish_TimeSdf() {
		return finish_Time == null ? "" : getSdf().format(finish_Time);
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

	public String getJob_Code() {
		return job_Code;
	}

	public void setJob_Code(String job_Code) {
		this.job_Code = job_Code;
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

}