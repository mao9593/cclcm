package hdsec.web.project.education.action;

import hdsec.web.project.education.model.ClassHour;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理培训记录
 * 
 * @author lishu
 * 
 */
public class ManageClassHourAction extends EducationBaseAction {
	private static final long serialVersionUID = 1L;
	private Integer training_type = null;// 培训类型
	private String dept_id = ""; // 部门编号
	private String dept_name = ""; // 部门名称
	private String user_iidd = "";// 用户编号
	private String user_name = "";// 用户姓名
	private String class_hour_online = "";// 学时
	private String class_hour_centraining = "";// 学时
	private String class_hour_outtraining = "";// 学时
	private String class_hour_total = "";// 学时
	private String course_id = ""; // 部门编号
	private String course_name = ""; // 部门编号
	private List<ClassHour> hourlist = null;

	// 用户学时统计展示时对应的数据列名
	private String online = "";// 在线学习总学时
	private String centraining = "";// 集中培训总学时
	private String outtraining = "";// 外出培训学时
	private String totalhour = "";// 学时合计
	private Integer edu_time = null;

	public Integer getTraining_type() {
		return training_type;
	}

	public void setTraining_type(Integer training_type) {
		this.training_type = training_type;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getClass_hour_online() {
		return class_hour_online;
	}

	public void setClass_hour_online(String class_hour_online) {
		this.class_hour_online = class_hour_online;
	}

	public String getClass_hour_centraining() {
		return class_hour_centraining;
	}

	public void setClass_hour_centraining(String class_hour_centraining) {
		this.class_hour_centraining = class_hour_centraining;
	}

	public String getClass_hour_outtraining() {
		return class_hour_outtraining;
	}

	public void setClass_hour_outtraining(String class_hour_outtraining) {
		this.class_hour_outtraining = class_hour_outtraining;
	}

	public String getClass_hour_total() {
		return class_hour_total;
	}

	public void setClass_hour_total(String class_hour_total) {
		this.class_hour_total = class_hour_total;
	}

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

	public List<ClassHour> getHourlist() {
		return hourlist;
	}

	public void setHourlist(List<ClassHour> hourlist) {
		this.hourlist = hourlist;
	}

	public String getOnline() {
		return online;
	}

	public String getCentraining() {
		return centraining;
	}

	public String getOuttraining() {
		return outtraining;
	}

	public String getTotalhour() {
		return totalhour;
	}

	public void setOnline(String online) {
		this.online = online;
	}

	public void setCentraining(String centraining) {
		this.centraining = centraining;
	}

	public void setOuttraining(String outtraining) {
		this.outtraining = outtraining;
	}

	public void setTotalhour(String totalhour) {
		this.totalhour = totalhour;
	}

	public Integer getEdu_time() {
		return edu_time;
	}

	public void setEdu_time(Integer edu_time) {
		this.edu_time = edu_time;
	}

	public String getActionContext() {
		return "/education/manageclasshour.action";
	}

	@Override
	public String executeFunction() throws Exception {
		if (edu_time == null || edu_time == 0) {
			Calendar a = Calendar.getInstance();
			edu_time = a.get(Calendar.YEAR);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", user_iidd);
		map.put("dept_id", dept_id);
		if (edu_time != null && edu_time != 0) {
			map.put("edu_time", String.valueOf(edu_time));
			map.put("edu_time_after", String.valueOf(edu_time + 1));
		}
		hourlist = educationService.getClassHourRecordByUserId(map);
		return SUCCESS;
	}
}
