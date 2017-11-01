package hdsec.web.project.education.model;

import hdsec.web.project.common.BaseEvent;

import java.util.Date;

/**
 * 培训记录
 * 
 * @author lishu
 * 
 */
public class EduTrainingRecord extends BaseEvent {
	private String course_id; // 培训编号或在线学习文件公文号
	private String course_name = ""; // 培训名称
	private Integer training_type = null; // 培训类型
	private String location = ""; // 培训地点
	private double class_hour = 0; // 学时
	private String edu_user_iidds = ""; // 参加人员id
	private String edu_user_names = ""; // 参加人员名单
	private Date start_time = null; // 开始时间
	private Date end_time = null; // 结束时间
	private String training_org = ""; // 培训机构（外派）
	private String certificate_no = ""; // 证书编号
	private Date certificate_exp = null; // 证书截止日期
	private String summ = ""; // 备注
	private float class_time = 0;

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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getClass_hour() {
		return class_hour;
	}

	public void setClass_hour(double class_hour) {
		this.class_hour = class_hour;
	}

	public String getEdu_user_iidds() {
		return edu_user_iidds;
	}

	public void setEdu_user_iidds(String edu_user_iidds) {
		this.edu_user_iidds = edu_user_iidds;
	}

	public String getEdu_user_names() {
		return edu_user_names;
	}

	public void setEdu_user_names(String edu_user_names) {
		this.edu_user_names = edu_user_names;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public String getTraining_org() {
		return training_org;
	}

	public void setTraining_org(String training_org) {
		this.training_org = training_org;
	}

	public String getCertificate_no() {
		return certificate_no;
	}

	public void setCertificate_no(String certificate_no) {
		this.certificate_no = certificate_no;
	}

	public Date getCertificate_exp() {
		return certificate_exp;
	}

	public void setCertificate_exp(Date certificate_exp) {
		this.certificate_exp = certificate_exp;
	}

	public String getStart_time_str() {
		return start_time == null ? "" : getSdf().format(start_time);
	}

	public String getEnd_time_str() {
		return end_time == null ? "" : getSdf().format(end_time);
	}

	public String getCertificate_exp_str() {
		return certificate_exp == null ? "" : getSdf().format(certificate_exp);
	}

	public float getClass_time() {
		return class_time;
	}

	public void setClass_time(float class_time) {
		this.class_time = class_time;
	}

	public String getTraining_type_name() {
		String name = "";
		if (training_type != null) {
			switch (training_type) {
			case 0:
				name = "在线培训";
				break;
			case 1:
				name = "集中培训";
				break;
			case 2:
				name = "外派培训";
				break;
			}
		}

		return name;
	}

	@Override
	public String getSumm() {
		return summ;
	}

	@Override
	public void setSumm(String summ) {
		this.summ = summ;
	}

	public EduTrainingRecord() {
		super();
	}

	public EduTrainingRecord(String course_id, String course_name, Integer training_type, String location,
			double class_hour, String edu_user_iidds, String edu_user_names, Date start_time, Date end_time,
			String training_org, String certificate_no, Date certificate_exp, String summ, Date apply_time) {
		super();
		this.course_id = course_id;
		this.course_name = course_name;
		this.training_type = training_type;
		this.location = location;
		this.class_hour = class_hour;
		this.edu_user_iidds = edu_user_iidds;
		this.edu_user_names = edu_user_names;
		this.start_time = start_time;
		this.end_time = end_time;
		this.training_org = training_org;
		this.certificate_no = certificate_no;
		this.certificate_exp = certificate_exp;
		this.summ = summ;
	}
}
