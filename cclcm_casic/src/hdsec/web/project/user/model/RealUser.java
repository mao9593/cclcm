package hdsec.web.project.user.model;

import hdsec.web.project.common.model.UserBaseDomain;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * 实际用户信息
 * @author renmingfei
 *
 */
public class RealUser extends UserBaseDomain {
	protected String real_user_id = "";//用户真实信息ID
	protected String base_username = "";//姓名
	protected String base_sex = "";//性别
	protected String base_nation = "";//民族
	protected Date base_birthday = null;//出生日期
	protected String base_birthplace = "";//出生地
	protected String base_nativeplace = "";//籍贯
	protected String base_country = "";//国别
	protected String base_politics = "";//政治面貌
	protected Date base_joinpartytime = null;//入党时间
	protected String edu_education = "";//学历
	protected String edu_degree = "";//学位
	protected String edu_school = "";//毕业院校
	protected String edu_major = "";//专业	
	protected String edu_language = "";//掌握语言
	protected String edu_familiarity = "";//熟悉程度
	protected String com_residency = "";//户籍所在地
	protected String com_police = "";//户籍派出所
	protected String com_address = "";//现住址
	protected String com_telephone = "";//联系电话
	protected String com_mobile = "";//手机号码
	protected String com_email = "";//电子邮箱
	protected String job_category = "";//涉密人员类别
	protected String job_seclevel = "";//岗位密级
	protected String job_adminpost = "";//行政职务
	protected String job_techpost = "";//技术职务
	protected String job_techtitle = "";//技术职称
	protected String job_humansort = "";//人员类别
	protected Date job_insectime = null;//进入涉密岗位时间
	protected Integer job_workyears = null;//涉密工作年限
	protected String job_employtype = "";//聘用形式
	protected String job_passnum = "";//出入证号
	protected String job_passlevel = "";//出入证级别
	protected Date job_inposttime = null;//入岗时间
	protected Date job_offposttime = null;//离岗时间
	protected String job_resume = "";//个人简历
	private Integer secUser_count = 0;//关联的安全用户数
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public String getReal_user_id() {
		return real_user_id;
	}
	
	public void setReal_user_id(String real_user_id) {
		this.real_user_id = real_user_id;
	}
	
	public String getBase_username() {
		return base_username;
	}
	
	public void setBase_username(String base_username) {
		this.base_username = base_username;
	}
	
	public String getBase_sex() {
		return base_sex;
	}
	
	public void setBase_sex(String base_sex) {
		this.base_sex = base_sex;
	}
	
	public String getBase_nation() {
		return base_nation;
	}
	
	public void setBase_nation(String base_nation) {
		this.base_nation = base_nation;
	}
	
	public Date getBase_birthday() {
		return base_birthday;
	}
	
	public void setBase_birthday(Date base_birthday) {
		this.base_birthday = base_birthday;
	}
	
	public String getBase_birthday_str() {
		return base_birthday == null ? "" : sdf.format(base_birthday);
	}
	
	public String getBase_birthplace() {
		return base_birthplace;
	}
	
	public void setBase_birthplace(String base_birthplace) {
		this.base_birthplace = base_birthplace;
	}
	
	public String getBase_nativeplace() {
		return base_nativeplace;
	}
	
	public void setBase_nativeplace(String base_nativeplace) {
		this.base_nativeplace = base_nativeplace;
	}
	
	public String getBase_country() {
		return base_country;
	}
	
	public void setBase_country(String base_country) {
		this.base_country = base_country;
	}
	
	public String getBase_politics() {
		return base_politics;
	}
	
	public void setBase_politics(String base_politics) {
		this.base_politics = base_politics;
	}
	
	public Date getBase_joinpartytime() {
		return base_joinpartytime;
	}
	
	public void setBase_joinpartytime(Date base_joinpartytime) {
		this.base_joinpartytime = base_joinpartytime;
	}
	
	public String getBase_joinpartytime_str() {
		return base_joinpartytime == null ? "" : sdf.format(base_joinpartytime);
	}
	
	public String getEdu_education() {
		return edu_education;
	}
	
	public void setEdu_education(String edu_education) {
		this.edu_education = edu_education;
	}
	
	public String getEdu_degree() {
		return edu_degree;
	}
	
	public void setEdu_degree(String edu_degree) {
		this.edu_degree = edu_degree;
	}
	
	public String getEdu_school() {
		return edu_school;
	}
	
	public void setEdu_school(String edu_school) {
		this.edu_school = edu_school;
	}
	
	public String getEdu_major() {
		return edu_major;
	}
	
	public void setEdu_major(String edu_major) {
		this.edu_major = edu_major;
	}
	
	public String getEdu_language() {
		return edu_language;
	}
	
	public void setEdu_language(String edu_language) {
		this.edu_language = edu_language;
	}
	
	public String getEdu_familiarity() {
		return edu_familiarity;
	}
	
	public void setEdu_familiarity(String edu_familiarity) {
		this.edu_familiarity = edu_familiarity;
	}
	
	public String getCom_residency() {
		return com_residency;
	}
	
	public void setCom_residency(String com_residency) {
		this.com_residency = com_residency;
	}
	
	public String getCom_police() {
		return com_police;
	}
	
	public void setCom_police(String com_police) {
		this.com_police = com_police;
	}
	
	public String getCom_address() {
		return com_address;
	}
	
	public void setCom_address(String com_address) {
		this.com_address = com_address;
	}
	
	public String getCom_telephone() {
		return com_telephone;
	}
	
	public void setCom_telephone(String com_telephone) {
		this.com_telephone = com_telephone;
	}
	
	public String getCom_mobile() {
		return com_mobile;
	}
	
	public void setCom_mobile(String com_mobile) {
		this.com_mobile = com_mobile;
	}
	
	public String getCom_email() {
		return com_email;
	}
	
	public void setCom_email(String com_email) {
		this.com_email = com_email;
	}
	
	public String getJob_category() {
		return job_category;
	}
	
	public void setJob_category(String job_category) {
		this.job_category = job_category;
	}
	
	public String getJob_seclevel() {
		return job_seclevel;
	}
	
	public void setJob_seclevel(String job_seclevel) {
		this.job_seclevel = job_seclevel;
	}
	
	public String getJob_adminpost() {
		return job_adminpost;
	}
	
	public void setJob_adminpost(String job_adminpost) {
		this.job_adminpost = job_adminpost;
	}
	
	public String getJob_techpost() {
		return job_techpost;
	}
	
	public void setJob_techpost(String job_techpost) {
		this.job_techpost = job_techpost;
	}
	
	public String getJob_techtitle() {
		return job_techtitle;
	}
	
	public void setJob_techtitle(String job_techtitle) {
		this.job_techtitle = job_techtitle;
	}
	
	public String getJob_humansort() {
		return job_humansort;
	}
	
	public void setJob_humansort(String job_humansort) {
		this.job_humansort = job_humansort;
	}
	
	public Date getJob_insectime() {
		return job_insectime;
	}
	
	public void setJob_insectime(Date job_insectime) {
		this.job_insectime = job_insectime;
	}
	
	public String getJob_insectime_str() {
		return job_insectime == null ? "" : sdf.format(job_insectime);
	}
	
	public Integer getJob_workyears() {
		return job_workyears;
	}
	
	public void setJob_workyears(Integer job_workyears) {
		this.job_workyears = job_workyears;
	}
	
	public String getJob_workyears_str() {
		return job_workyears == null ? "" : String.valueOf(job_workyears);
	}
	
	public String getJob_employtype() {
		return job_employtype;
	}
	
	public void setJob_employtype(String job_employtype) {
		this.job_employtype = job_employtype;
	}
	
	public String getJob_passnum() {
		return job_passnum;
	}
	
	public void setJob_passnum(String job_passnum) {
		this.job_passnum = job_passnum;
	}
	
	public String getJob_passlevel() {
		return job_passlevel;
	}
	
	public void setJob_passlevel(String job_passlevel) {
		this.job_passlevel = job_passlevel;
	}
	
	public Date getJob_inposttime() {
		return job_inposttime;
	}
	
	public void setJob_inposttime(Date job_inposttime) {
		this.job_inposttime = job_inposttime;
	}
	
	public String getJob_inposttime_str() {
		return job_inposttime == null ? "" : sdf.format(job_inposttime);
	}
	
	public Date getJob_offposttime() {
		return job_offposttime;
	}
	
	public void setJob_offposttime(Date job_offposttime) {
		this.job_offposttime = job_offposttime;
	}
	
	public String getJob_offposttime_str() {
		return job_offposttime == null ? "" : sdf.format(job_offposttime);
	}
	
	public String getJob_resume() {
		return job_resume;
	}
	
	public void setJob_resume(String job_resume) {
		this.job_resume = job_resume;
	}
	
	public Integer getSecUser_count() {
		return secUser_count;
	}
	
	public void setSecUser_count(Integer secUser_count) {
		this.secUser_count = secUser_count;
	}
	
	public RealUser() {
		super();
	}
	
}
