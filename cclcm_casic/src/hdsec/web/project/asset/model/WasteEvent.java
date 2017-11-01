package hdsec.web.project.asset.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.BaseEvent;

/**
 * 资产报废、变更申请
 * 
 * @author gaoximin 2015-6-25
 */
public class WasteEvent extends BaseEvent {
	private String waste_title = ""; // 工单标题
	private String waste_code = "";// 工单编号
	private String place = ""; // 使用地点
	private String his_job_code = ""; // 包含该作业的历史任务列表

	private String event_type = "";// 业务类型（WASTE报废， PROPERTYCHANGE变更）
	private String property_id = "";// 资产ID
	private String property_place = "";// 固定资产存放单位(变更用)
	private String user_iidd_after = "";// 固定资产变更后责任人(变更用)
	private String dept_id_after = "";// 固定资产变更后的部门(变更用)
	private String depre_life = "";// 折旧年限(报废用)
	private String userd_life = "";// 使用年限(报废用)
	private String reason = "";// 设备仪器现状及报废原因(报废用)
	private String suggestion = "";// 鉴定意见及负责鉴定人签章(报废用)
	private String suggestion_waste = "";// 设备报废后处理意见(报废用)
	private Integer waste_status = 0; // 报废/变更状态（0正常，1已报废2，已变更）
	private String waste_time;// 报废/变更时间
	private String job_code = "";// 外键-所属任务

	private String property_seclvcode_name = "";// 资产密级
	private String property_barcode = ""; // 资产条码
	private String property_kind = "";// 资产类别
	private String property_name = ""; // 资产名称
	private String duty_user_name = "";// 资产当前责任人
	private String duty_dept_name = "";// 资产当前责任部门

	private String user_name_after = "";// 固定资产变更后责任人(变更用)
	private String dept_name_after = "";// 固定资产变更后的部门(变更用)

	public String getEvent_type() {
		return event_type;
	}

	public String getEvent_type_str() {
		if (event_type == null) {
			return "未知";
		}
		switch (event_type) {
		case "WASTE":
			return "资产报废";
		case "PROPERTYCHANGE":
			return "资产变更";
		default:
			return "未知";
		}
	}

	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}

	public String getProperty_id() {
		return property_id;
	}

	public void setProperty_id(String property_id) {
		this.property_id = property_id;
	}

	public String getProperty_place() {
		return property_place;
	}

	public void setProperty_place(String property_place) {
		this.property_place = property_place;
	}

	public String getUser_iidd_after() {
		return user_iidd_after;
	}

	public void setUser_iidd_after(String user_iidd_after) {
		this.user_iidd_after = user_iidd_after;
	}

	public String getDept_id_after() {
		return dept_id_after;
	}

	public void setDept_id_after(String dept_id_after) {
		this.dept_id_after = dept_id_after;
	}

	public String getDepre_life() {
		return depre_life;
	}

	public void setDepre_life(String depre_life) {
		this.depre_life = depre_life;
	}

	public String getUserd_life() {
		return userd_life;
	}

	public void setUserd_life(String userd_life) {
		this.userd_life = userd_life;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public String getSuggestion_waste() {
		return suggestion_waste;
	}

	public void setSuggestion_waste(String suggestion_waste) {
		this.suggestion_waste = suggestion_waste;
	}

	@Override
	public String getJob_code() {
		return job_code;
	}

	@Override
	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public void setWaste_time(String waste_time) {
		this.waste_time = waste_time;
	}

	public String getWaste_title() {
		return waste_title;
	}

	public void setWaste_title(String waste_title) {
		this.waste_title = waste_title;
	}

	public String getWaste_code() {
		return waste_code;
	}

	public void setWaste_code(String waste_code) {
		this.waste_code = waste_code;
	}

	public String getProperty_barcode() {
		return property_barcode;
	}

	public void setProperty_barcode(String property_barcode) {
		this.property_barcode = property_barcode;
	}

	public String getProperty_name() {
		return property_name;
	}

	public void setProperty_name(String property_name) {
		this.property_name = property_name;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getWaste_status() {
		return waste_status;
	}

	public String getWaste_status_str() {
		switch (waste_status) {
		case 0:
			return "正常";
		case 1:
			return "已报废";
		case 2:
			return "已变更";
		}
		return "未知";
	}

	public void setWaste_status(Integer waste_status) {
		this.waste_status = waste_status;
	}

	public String getWaste_time() {
		return getSdf().format(waste_time);
	}

	public String getHis_job_code() {
		return his_job_code;
	}

	public void setHis_job_code(String his_job_code) {
		this.his_job_code = his_job_code;
	}

	public String getProperty_seclvcode_name() {
		return property_seclvcode_name;
	}

	public void setProperty_seclvcode_name(String property_seclvcode_name) {
		this.property_seclvcode_name = property_seclvcode_name;
	}

	public String getProperty_kind() {
		return property_kind;
	}

	public void setProperty_kind(String property_kind) {
		this.property_kind = property_kind;
	}

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name;
	}

	public String getDuty_dept_name() {
		return duty_dept_name;
	}

	public void setDuty_dept_name(String duty_dept_name) {
		this.duty_dept_name = duty_dept_name;
	}

	public String getUser_name_after() {
		return user_name_after;
	}

	public void setUser_name_after(String user_name_after) {
		this.user_name_after = user_name_after;
	}

	public String getDept_name_after() {
		return dept_name_after;
	}

	public void setDept_name_after(String dept_name_after) {
		this.dept_name_after = dept_name_after;
	}

	public WasteEvent() {
	}

	public WasteEvent(String user_iidd, String dept_id, String event_code,
			Integer seclv_code, String usage_code, String project_code,
			String summ, String waste_title, String waste_code,
			String property_id, String property_name, String place,
			String reason) {
		super(JobTypeEnum.WASTE, event_code, user_iidd, dept_id, seclv_code,
				usage_code, project_code, summ);

		this.waste_title = waste_title;
		this.waste_code = waste_code;
		this.property_id = property_id;
		this.property_name = property_name;
		this.place = place;
		this.reason = reason;
	}

}
