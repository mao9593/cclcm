package hdsec.web.project.disc.model;

import hdsec.web.project.common.BaseDomain;

import java.util.Date;

/**
 * 
 * @author lishu 2015-12-10
 */
public class SpaceCDEvent extends BaseDomain {

	private Integer id = 0;// 编号
	private String event_code = "";// 作业号
	private String user_iidd = "";// 录入人id
	private String dept_id = "";// 录入人部门id
	private String apply_user_iidd = "";// 申请人id
	private String apply_dept_id = "";// 申请人部门id
	private String scope_dept_id = "";// 归属部门id
	private Integer seclv_code = null;// 作业密级
	private String summ = "";// 备注
	private Date apply_time = null;// 申请时间
	private String cd_type = "";// 光盘类型（CD、DVD）
	private String spacecd_type = "";// 空白盘类型，0空白盘，1中转盘
	private int enter_num = 0;// 份数
	private String enter_code = "";// 流水号
	private String dept_name = "";// 部门名称
	private String user_name = "";// 申请人姓名
	private String apply_user_name = "";// 申请人姓名
	private String apply_dept_name = "";// 申请人部门名称
	private String scope_dept_name = "";// 归属部门名称
	private String seclv_name = "";// 密级名称
	private String scope = "";
	private Integer assign_status = null;// 领用状态，0表示未领用，1表示已领用
	private String job_code = "";
	private String usage_code = null;
	private String project_code = "";

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getSeclv_name() {
		if (seclv_name != null && !seclv_name.equals("")) {
			return seclv_name;
		}
		return "未标密";
	}

	public void setSeclv_name(String seclv_name) {
		this.seclv_name = seclv_name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getProject_code() {
		return project_code;
	}

	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public Date getApply_time() {
		return apply_time;
	}

	public void setApply_time(Date apply_time) {
		this.apply_time = apply_time;
	}

	public String getApply_time_str() {
		return apply_time == null ? "" : getSdf().format(apply_time);
	}

	public String getCd_type() {
		return cd_type;
	}

	public void setCd_type(String cd_type) {
		this.cd_type = cd_type;
	}

	public String getUsage_code() {
		return usage_code;
	}

	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code;
	}

	public String getSpacecd_type_name() {
		if (spacecd_type == null) {
			return "未知";
		} else {
			switch (this.spacecd_type) {
			case "0":
				return "空白盘";
			case "1":
				return "中转盘";
			default:
				return "未知";
			}
		}
	}

	public int getEnter_num() {
		return enter_num;
	}

	public void setEnter_num(int enter_num) {
		this.enter_num = enter_num;
	}

	public String getEnter_code() {
		return enter_code;
	}

	public void setEnter_code(String enter_code) {
		this.enter_code = enter_code;
	}

	public String getSpacecd_type() {
		return spacecd_type;
	}

	public void setSpacecd_type(String spacecd_type) {
		this.spacecd_type = spacecd_type;
	}

	public String getApply_user_iidd() {
		return apply_user_iidd;
	}

	public void setApply_user_iidd(String apply_user_iidd) {
		this.apply_user_iidd = apply_user_iidd;
	}

	public String getApply_dept_id() {
		return apply_dept_id;
	}

	public void setApply_dept_id(String apply_dept_id) {
		this.apply_dept_id = apply_dept_id;
	}

	public String getScope_dept_id() {
		return scope_dept_id;
	}

	public void setScope_dept_id(String scope_dept_id) {
		this.scope_dept_id = scope_dept_id;
	}

	public String getApply_user_name() {
		return apply_user_name;
	}

	public void setApply_user_name(String apply_user_name) {
		this.apply_user_name = apply_user_name;
	}

	public String getApply_dept_name() {
		return apply_dept_name;
	}

	public void setApply_dept_name(String apply_dept_name) {
		this.apply_dept_name = apply_dept_name;
	}

	public String getScope_dept_name() {
		return scope_dept_name;
	}

	public void setScope_dept_name(String scope_dept_name) {
		this.scope_dept_name = scope_dept_name;
	}

	public Integer getAssign_status() {
		return assign_status;
	}

	public void setAssign_status(Integer assign_status) {
		this.assign_status = assign_status;
	}

	public String getJob_code() {
		return job_code;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public SpaceCDEvent() {
	}

	public SpaceCDEvent(String event_code, String user_iidd, String dept_id, String scope, String scope_dept_id,
			String scope_dept_name, Integer seclv_code, String summ, Date apply_time, String cd_type, int enter_num,
			String enter_code, String spacecd_type) {
		super();
		this.event_code = event_code;
		this.user_iidd = user_iidd;
		this.dept_id = dept_id;
		this.scope_dept_id = scope_dept_id;
		this.scope_dept_name = scope_dept_name;
		this.seclv_code = seclv_code;
		this.summ = summ;
		this.apply_time = apply_time;
		this.enter_num = enter_num;
		this.enter_code = enter_code;
		this.cd_type = cd_type;
		this.scope = scope;
		this.spacecd_type = spacecd_type;

	}

	public SpaceCDEvent(String event_code, String apply_user_iidd, String apply_dept_id, String scope_dept_id,
			String scope_dept_name, Integer seclv_code, String summ, Date apply_time, String cd_type,
			String spacecd_type, int enter_num, Integer assign_status) {
		super();
		this.event_code = event_code;
		this.apply_user_iidd = apply_user_iidd;
		this.apply_dept_id = apply_dept_id;
		this.scope_dept_id = scope_dept_id;
		this.scope_dept_name = scope_dept_name;
		this.seclv_code = seclv_code;
		this.summ = summ;
		this.apply_time = apply_time;
		this.cd_type = cd_type;
		this.spacecd_type = spacecd_type;
		this.enter_num = enter_num;
		this.assign_status = assign_status;
	}
}
