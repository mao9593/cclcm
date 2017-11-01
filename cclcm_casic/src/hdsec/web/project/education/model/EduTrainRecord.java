package hdsec.web.project.education.model;

import java.util.Date;

public class EduTrainRecord {
	private int course_id;  //主键
	private String course_name = ""; //培训名称
    private int train_type ; //培训类型
    private String location = ""; //培训地点
    private String class_hour = ""; //学时
    private String attend_userid = ""; //参加人员id
    private String attend_username = ""; //参加人员名单
    private Date start_time = null; //开始时间
    private Date end_time = null; //结束时间
    private String train_org = ""; //培训机构（外派）
    private String certificate_no = ""; //证书编号
    private String certificate_endtime = ""; //证书截止日期
    private String summ = ""; //备注
    
    
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public int getTrain_type() {
		if ("1".equals(train_type)) {
			return 1;
		}
		if ("2".equals(train_type)) {
			return 2;
		}
		
		return train_type;
	}
	public void setTrain_type(int train_type) {
		this.train_type = train_type;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getClass_hour() {
		return class_hour;
	}
	public void setClass_hour(String class_hour) {
		this.class_hour = class_hour;
	}
	public String getAttend_userid() {
		return attend_userid;
	}
	public void setAttend_userid(String attend_userid) {
		this.attend_userid = attend_userid;
	}
	public String getAttend_username() {
		return attend_username;
	}
	public void setAttend_username(String attend_username) {
		this.attend_username = attend_username;
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
	public String getTrain_org() {
		return train_org;
	}
	public void setTrain_org(String train_org) {
		this.train_org = train_org;
	}
	public String getCertificate_no() {
		return certificate_no;
	}
	public void setCertificate_no(String certificate_no) {
		this.certificate_no = certificate_no;
	}
	public String getCertificate_endtime() {
		return certificate_endtime;
	}
	public void setCertificate_endtime(String certificate_endtime) {
		this.certificate_endtime = certificate_endtime;
	}
	public String getSumm() {
		return summ;
	}
	public void setSumm(String summ) {
		this.summ = summ;
	}
    

}
