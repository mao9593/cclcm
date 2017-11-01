package hdsec.web.project.education.action;

import hdsec.web.project.education.model.EduTrainingRecord;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理培训记录
 * 
 * @author lishu
 * 
 */
public class ManageTrainRecordAction extends EducationBaseAction {
	private static final long serialVersionUID = 1L;
	private Integer training_type = null;// 培训类型
	private String course_name = ""; // 培训名称
	private String course_id = "";// 培训编号
	private String edu_user_iidds = "";// 参训人员编号
	private String edu_user_names = "";// 参训人员姓名
	private String location = "";// 培训地点
	private Date start_time = null;// 培训开始时间
	private Date end_time = null;// 培训结束时间
	private double class_hour = 0;// 学时
	private String training_org = ""; // 培训机构（外派）
	private String certificate_no = ""; // 证书编号
	private Date certificate_exp = null; // 证书截止日期
	private String attend_username = "";
	private List<EduTrainingRecord> recordList = null;

	public double getClass_hour() {
		return class_hour;
	}

	public void setClass_hour(double class_hour) {
		this.class_hour = class_hour;
	}

	public Integer getTraining_type() {
		return training_type;
	}

	public void setTraining_type(Integer training_type) {
		this.training_type = training_type;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStart_time_str() {
		return start_time == null ? "" : sdf.format(start_time);
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time_str() {
		return end_time == null ? "" : sdf.format(end_time);
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

	public String getAttend_username() {
		return attend_username;
	}

	public void setAttend_username(String attend_username) {
		this.attend_username = attend_username;
	}

	public List<EduTrainingRecord> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<EduTrainingRecord> recordList) {
		this.recordList = recordList;
	}

	public String getActionContext() {
		return "/education/managetrainrecord.action";
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("course_id", course_id);
		map.put("course_name", course_name);
		map.put("training_type", training_type);
		map.put("class_hour", class_hour);
		map.put("location", location);
		map.put("start_time", start_time);
		map.put("end_time", end_time);
		map.put("noonline", "noonline");
		map.put("edu_user_iidds", edu_user_iidds);
		recordList = educationService.getAllTrainingRecordList(map);
		/* System.out.println("recordList.size:" + recordList.size()); */
		return SUCCESS;
	}
}
