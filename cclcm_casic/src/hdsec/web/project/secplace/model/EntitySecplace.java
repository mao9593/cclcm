package hdsec.web.project.secplace.model;

import hdsec.web.project.common.BaseDomain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EntitySecplace extends BaseDomain {

	private int secplace_id;// 场所主键
	private String secplace_name = "";// 场所名称
	private String secplace_barcode = "";// 条码号（唯一）
	private String secplace_code = "";// 场所编号（唯一）
	private int secplace_type;// 要害类别（1：部门，2：部位）
	private String conf_code = "";// 保密编号
	private int seclv_code;// 要害密级
	private String secplace_location = "";// 物理位置
	private String leader_id = "";// 主管领导
	private int secplace_status;// 状态（1：在用，2：停用）
	private String secplace_application = "";// 用途
	private Date found_time = null;// 成立时间
	private String duty_user_id = "";// 责任人id
	private String duty_dept_id = "";// 责任人id
	private String user_number;// 涉密人员数量
	private String people_protect = "";// 人防措施
	private String physical_protect = "";// 物防措施
	private String technology_protect = "";// 技防措施
	private String user_iidd = "";// 录入人员ID
	private Date enter_time = null;// 录入时间
	private String summ = "";// 备注

	private String seclv_name = "";// 密级名称
	private String leader_name = "";// 主管领导
	private String duty_user_name = "";// 责任人姓名
	private String duty_dept_name = "";// 责任部门名称
	private String user_name = "";// 录入人员姓名

	private String secplace_type_name = "";// 录入人员姓名

	public String getSecplace_type_name() {
		String name = "";
		if (secplace_type == 1) {
			name = "部门";
		} else if (secplace_type == 2) {
			name = "部位";
		} else {
			name = "未填写类型";
		}

		return name;
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

	public String getSecplace_barcode() {
		return secplace_barcode;
	}

	public void setSecplace_barcode(String secplace_barcode) {
		this.secplace_barcode = secplace_barcode;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getLeader_id() {
		return leader_id;
	}

	public void setLeader_id(String leader_id) {
		this.leader_id = leader_id;
	}

	public String getLeader_name() {
		return leader_name;
	}

	public void setLeader_name(String leader_name) {
		this.leader_name = leader_name;
	}

	public String getSeclv_name() {
		return seclv_name;
	}

	public void setSeclv_name(String seclv_name) {
		this.seclv_name = seclv_name;
	}

	public String getConf_code() {
		return conf_code;
	}

	public void setConf_code(String conf_code) {
		this.conf_code = conf_code;
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

	public Date getEnter_time() {
		return enter_time;
	}

	public void setEnter_time(Date enter_time) {
		this.enter_time = enter_time;
	}

	public int getSecplace_id() {
		return secplace_id;
	}

	public void setSecplace_id(int secplace_id) {
		this.secplace_id = secplace_id;
	}

	public String getSecplace_name() {
		return secplace_name;
	}

	public void setSecplace_name(String secplace_name) {
		this.secplace_name = secplace_name;
	}

	public String getSecplace_code() {
		return secplace_code;
	}

	public void setSecplace_code(String secplace_code) {
		this.secplace_code = secplace_code;
	}

	public int getSecplace_type() {
		return secplace_type;
	}

	public void setSecplace_type(int secplace_type) {
		this.secplace_type = secplace_type;
	}

	public int getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(int seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getSecplace_location() {
		return secplace_location;
	}

	public void setSecplace_location(String secplace_location) {
		this.secplace_location = secplace_location;
	}

	public int getSecplace_status() {
		return secplace_status;
	}

	public void setSecplace_status(int secplace_status) {
		this.secplace_status = secplace_status;
	}

	public String getSecplace_application() {
		return secplace_application;
	}

	public void setSecplace_application(String secplace_application) {
		this.secplace_application = secplace_application;
	}

	public String getFound_time() {
		return found_time == null ? "" : getSdf().format(found_time);
	}

	public void setFound_time(Date found_time) {
		this.found_time = found_time;
	}

	public String getUser_number() {
		return user_number;
	}

	public void setUser_number(String user_number) {
		this.user_number = user_number;
	}

	public String getPeople_protect() {
		return people_protect;
	}

	public void setPeople_protect(String people_protect) {
		this.people_protect = people_protect;
	}

	public String getPhysical_protect() {
		return physical_protect;
	}

	public void setPhysical_protect(String physical_protect) {
		this.physical_protect = physical_protect;
	}

	public String getTechnology_protect() {
		return technology_protect;
	}

	public void setTechnology_protect(String technology_protect) {
		this.technology_protect = technology_protect;
	}

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public EntitySecplace() {
		super();
	}

	public EntitySecplace(SimpleDateFormat sdf) {
		super(sdf);
	}

	public EntitySecplace(String secplace_barcode, String secplace_name, String secplace_code, int secplace_type,
			String conf_code, int seclv_code, String secplace_location, String leader_id, int secplace_status,
			String secplace_application, Date found_time, String duty_user_id, String user_number, String people_protect,
			String physical_protect, String technology_protect, Date enter_time, String summ, String user_iidd,
			String duty_dept_id) {
		super();
		this.secplace_barcode = secplace_barcode;
		this.conf_code = conf_code;
		this.duty_user_id = duty_user_id;
		this.enter_time = enter_time;
		this.found_time = found_time;
		this.leader_id = leader_id;
		this.people_protect = people_protect;
		this.physical_protect = physical_protect;
		this.seclv_code = seclv_code;
		this.secplace_application = secplace_application;
		this.secplace_code = secplace_code;
		this.secplace_location = secplace_location;
		this.secplace_name = secplace_name;
		this.secplace_status = secplace_status;
		this.secplace_type = secplace_type;
		this.summ = summ;
		this.technology_protect = technology_protect;
		this.user_iidd = user_iidd;
		this.user_number = user_number;
		this.duty_dept_id = duty_dept_id;

	}
}