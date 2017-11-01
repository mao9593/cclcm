package hdsec.web.project.education.model;

import java.util.Date;

import hdsec.web.project.common.BaseEvent;

public class ClassHour extends BaseEvent {
	private String course_id; // 主键
	private String course_name = ""; // 培训名称
	private Integer training_type = null;// 培训类型
	private String dept_id = ""; // 部门编号
	private String dept_name = ""; // 部门名称
	private String user_iidd = "";// 用户编号
	private String user_name = "";// 用户姓名
	private float class_hour_online = 0;// 学时
	private float class_hour_centraining = 0;// 学时
	private float class_hour_outtraining = 0;// 学时
	private float class_hour_total = 0;// 学时
	private float online = 0;// 学时
	private float centraining = 0;// 学时
	private float outtraining = 0;// 学时
	private float totalhour = 0;// 学时
	private String security_name = "";// 用户涉密等级

	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public Integer getTraining_type() {
		return training_type;
	}

	public void setTraining_type(Integer training_type) {
		this.training_type = training_type;
	}

	@Override
	public String getDept_id() {
		return dept_id;
	}

	@Override
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	@Override
	public String getDept_name() {
		return dept_name;
	}

	@Override
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	@Override
	public String getUser_iidd() {
		return user_iidd;
	}

	@Override
	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	@Override
	public String getUser_name() {
		return user_name;
	}

	@Override
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getSecurity_name() {
		return security_name;
	}

	public void setSecurity_name(String security_name) {
		this.security_name = security_name;
	}

	public float getClass_hour_online() {
		return class_hour_online;
	}

	public void setClass_hour_online(float class_hour_online) {
		this.class_hour_online = class_hour_online;
	}

	public float getClass_hour_centraining() {
		return class_hour_centraining;
	}

	public void setClass_hour_centraining(float class_hour_centraining) {
		this.class_hour_centraining = class_hour_centraining;
	}

	public float getClass_hour_outtraining() {
		return class_hour_outtraining;
	}

	public void setClass_hour_outtraining(float class_hour_outtraining) {
		this.class_hour_outtraining = class_hour_outtraining;
	}

	public float getClass_hour_total() {
		return class_hour_total;
	}

	public void setClass_hour_total(float class_hour_total) {
		this.class_hour_total = class_hour_total;
	}

	public float getOnline() {
		return online;
	}

	public void setOnline(float online) {
		this.online = online;
	}

	public float getCentraining() {
		return centraining;
	}

	public void setCentraining(float centraining) {
		this.centraining = centraining;
	}

	public float getOuttraining() {
		return outtraining;
	}

	public void setOuttraining(float outtraining) {
		this.outtraining = outtraining;
	}

	public float getTotalhour() {
		return totalhour;
	}

	public void setTotalhour(float totalhour) {
		this.totalhour = totalhour;
	}

	public ClassHour() {
		super();
	}

	public ClassHour(String course_id, String course_name, Integer training_type, String dept_id, String user_iidd,
			float class_hour_online, float class_hour_centraining, float class_hour_outtraining,
			float class_hour_total, Date apply_time) {

		super();
		this.course_id = course_id;
		this.course_name = course_name;
		this.training_type = training_type;
		this.dept_id = dept_id;
		this.user_iidd = user_iidd;
		this.class_hour_online = class_hour_online;
		this.class_hour_centraining = class_hour_centraining;
		this.class_hour_outtraining = class_hour_outtraining;
	}
}
