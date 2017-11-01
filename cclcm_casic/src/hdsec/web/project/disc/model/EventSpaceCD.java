package hdsec.web.project.disc.model;

import hdsec.web.project.common.BaseDomain;

import java.util.Date;

/**
 * EventCarryout entity. @author MyEclipse Persistence Tools
 */

public class EventSpaceCD extends BaseDomain {

	private Integer id;
	private String event_code;
	private String user_iidd;
	private String dept_id;
	private Integer seclv_code;
	private String seclv_name;
	private String summ;
	private Date apply_time;
	private String file_type;
	private String scope;
	private String scope_dept_id;
	private int enter_num;
	private String enter_code;
	private String dept_name;
	private String user_name;

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

	public EventSpaceCD() {
	}

	public EventSpaceCD(String event_code, String user_iidd, String dept_id,
			Integer seclv_code, String summ, Date apply_time, String file_type,
			String scope, String scope_dept_id, int enter_num, String enter_code) {
		super();
		this.event_code = event_code;
		this.user_iidd = user_iidd;
		this.dept_id = dept_id;
		this.seclv_code = seclv_code;
		this.summ = summ;
		this.apply_time = apply_time;
		this.file_type = file_type;
		this.scope = scope;
		this.scope_dept_id = scope_dept_id;
		this.enter_num = enter_num;
		this.enter_code = enter_code;
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

	public String getApply_timename() {
		if (null == apply_time) {
			return "";
		}
		return getSdf().format(apply_time);
	}

	public void setApply_time(Date apply_time) {
		this.apply_time = apply_time;
	}

	public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getScope_dept_id() {
		return scope_dept_id;
	}

	public void setScope_dept_id(String scope_dept_id) {
		this.scope_dept_id = scope_dept_id;
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

}