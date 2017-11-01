package hdsec.web.project.education.model;

import hdsec.web.project.common.BaseDomain;

/**
 * 添加保密教育参加培训用户
 * 
 * @author Lishu
 * 
 */

public class TrainingUser extends BaseDomain {
	private String user_iidd = "";// 用户ID
	private String user_name = "";// 用户姓名
	private String course_id = "";// 培训ID
	private String course_name = "";// 培训名称

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

}
