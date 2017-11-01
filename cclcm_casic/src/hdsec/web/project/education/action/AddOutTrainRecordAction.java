package hdsec.web.project.education.action;

import hdsec.web.project.education.model.EduTrainingRecord;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 添加外派培训记录
 * 
 * @author lishu
 * 
 */
public class AddOutTrainRecordAction extends EducationBaseAction {
	private static final long serialVersionUID = 1L;
	// private String type = "";// 类型
	private String edu_user_iidds = "";// 参训人员编号
	private String edu_user_names = "";// 参训人员姓名
	private String summ = "";// 备注
	private String location = "";// 培训地点
	private Date start_time = null;// 培训开始时间
	private Date end_time = null;// 培训结束时间
	private float class_hour = 0;// 学时
	private Integer training_type = null;// 培训类型
	private String course_name = ""; // 培训名称
	private String course_id = ""; // 培训名称
	private String training_org = ""; // 培训机构（外派）
	private String certificate_no = ""; // 证书编号
	private Date certificate_exp = null; // 证书截止日期
	private Integer listSize = null;

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

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStart_time() {
		return start_time == null ? "" : sdf.format(start_time);
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time == null ? "" : sdf.format(end_time);
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public float getClass_hour() {
		return class_hour;
	}

	public void setClass_hour(float class_hour) {
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

	public Integer getListSize() {
		return listSize;
	}

	public void setListSize(Integer listSize) {
		this.listSize = listSize;
	}

	@Override
	public String executeFunction() throws Exception {
		if (course_id.isEmpty()) {
			return SUCCESS;
		} else {
			// 新组合的培训人员id。2016.3.14
			String new_edu_user_iidds = "";
			// 获取培训名单id与name，去重。2016.3.14
			List<String> userList = new ArrayList<String>();
			String[] users_id = edu_user_iidds.split(",");
			for (String user_iidd : users_id) {
				if (!userList.contains(user_iidd)) {
					userList.add(user_iidd);
					new_edu_user_iidds = new_edu_user_iidds + "," + user_iidd;
					edu_user_names = edu_user_names + "," + userService.getUserNameByUserId(user_iidd);
				}
			}
			new_edu_user_iidds = new_edu_user_iidds.substring(1) + ",";
			edu_user_names = edu_user_names.substring(1, edu_user_names.length());
			EduTrainingRecord record = new EduTrainingRecord(course_id, course_name, training_type, location,
					class_hour, new_edu_user_iidds, edu_user_names, start_time, end_time, training_org, training_org,
					certificate_exp, summ, new Date());
			// 插入培训记录，编号不能重复
			EduTrainingRecord trainingRecord = educationService.getTrainingRecordByCourseId(record.getCourse_id());
			if (trainingRecord == null) {
				educationService.addEduTrainingRecord(record);
			} else {
				throw new Exception("培训编号已存在，请重新填写");
			}
			// 拆分User_iidd后将数据插入学时记录表
			listSize = userList.size() - 1;
			for (int i = 0; i <= listSize; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("user_iidd", userList.get(i));
				String dept_name = userService.getDeptNameByUserId(userList.get(i));
				String dept_id = userService.getDeptIdByName(dept_name);
				map.put("dept_id", dept_id);
				map.put("course_id", course_id);
				map.put("course_name", course_name);
				map.put("training_type", training_type);
				map.put("class_hour_centraining", 0);
				map.put("class_hour_online", 0);
				map.put("class_hour_outtraining", class_hour);
				map.put("class_hour_total", 0);
				map.put("apply_time", new Date());
				educationService.addClassHourRecord(map);
			}
			insertCommonLog("添加外派培训记录[" + course_id + "]");

			return "ok";
		}
	}
}
