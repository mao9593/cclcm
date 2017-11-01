package hdsec.web.project.user.action;

import hdsec.web.project.user.model.RealUser;

import java.sql.Date;

/**
 * 修改员工信息
 * @author renmingfei
 *
 */
public class UpdateRealUserAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private String real_user_id = "";
	private RealUser realUser = null;
	private String update = "N";
	private String base_username = "";//姓名
	private String base_sex = "";//性别
	private String base_nation = "";//民族
	private Date base_birthday = null;//出生日期
	private String base_birthplace = "";//出生地
	private String base_nativeplace = "";//籍贯
	private String base_country = "";//国别
	private String base_politics = "";//政治面貌
	private Date base_joinpartytime = null;//入党时间
	private String edu_education = "";//学历
	private String edu_degree = "";//学位
	private String edu_school = "";//毕业院校
	private String edu_major = "";//专业	
	private String edu_language = "";//掌握语言
	private String edu_familiarity = "";//熟悉程度
	private String com_residency = "";//户籍所在地
	private String com_police = "";//户籍派出所
	private String com_address = "";//现住址
	private String com_telephone = "";//联系电话
	private String com_mobile = "";//手机号码
	private String com_email = "";//电子邮箱
	private String job_category = "";//涉密人员类别
	private String job_seclevel = "";//岗位密级
	private String job_adminpost = "";//行政职务
	private String job_techpost = "";//技术职务
	private String job_techtitle = "";//技术职称
	private String job_humansort = "";//人员类别
	private Date job_insectime = null;//进入涉密岗位时间
	private Integer job_workyears = null;//涉密工作年限
	private String job_employtype = "";//聘用形式
	private String job_passnum = "";//出入证号
	private String job_passlevel = "";//出入证级别
	private Date job_inposttime = null;//入岗时间
	private Date job_offposttime = null;//离岗时间
	private String job_resume = "";//个人简历
	
	public void setBase_username(String base_username) {
		this.base_username = base_username;
	}
	
	public void setBase_sex(String base_sex) {
		this.base_sex = base_sex;
	}
	
	public void setBase_nation(String base_nation) {
		this.base_nation = base_nation;
	}
	
	public void setBase_birthday(Date base_birthday) {
		this.base_birthday = base_birthday;
	}
	
	public void setBase_birthplace(String base_birthplace) {
		this.base_birthplace = base_birthplace;
	}
	
	public void setBase_nativeplace(String base_nativeplace) {
		this.base_nativeplace = base_nativeplace;
	}
	
	public void setBase_country(String base_country) {
		this.base_country = base_country;
	}
	
	public void setBase_politics(String base_politics) {
		this.base_politics = base_politics;
	}
	
	public void setBase_joinpartytime(Date base_joinpartytime) {
		this.base_joinpartytime = base_joinpartytime;
	}
	
	public void setEdu_education(String edu_education) {
		this.edu_education = edu_education;
	}
	
	public void setEdu_degree(String edu_degree) {
		this.edu_degree = edu_degree;
	}
	
	public void setEdu_school(String edu_school) {
		this.edu_school = edu_school;
	}
	
	public void setEdu_major(String edu_major) {
		this.edu_major = edu_major;
	}
	
	public void setEdu_language(String edu_language) {
		this.edu_language = edu_language;
	}
	
	public void setEdu_familiarity(String edu_familiarity) {
		this.edu_familiarity = edu_familiarity;
	}
	
	public void setCom_residency(String com_residency) {
		this.com_residency = com_residency;
	}
	
	public void setCom_police(String com_police) {
		this.com_police = com_police;
	}
	
	public void setCom_address(String com_address) {
		this.com_address = com_address;
	}
	
	public void setCom_telephone(String com_telephone) {
		this.com_telephone = com_telephone;
	}
	
	public void setCom_mobile(String com_mobile) {
		this.com_mobile = com_mobile;
	}
	
	public void setCom_email(String com_email) {
		this.com_email = com_email;
	}
	
	public void setJob_category(String job_category) {
		this.job_category = job_category;
	}
	
	public void setJob_seclevel(String job_seclevel) {
		this.job_seclevel = job_seclevel;
	}
	
	public void setJob_adminpost(String job_adminpost) {
		this.job_adminpost = job_adminpost;
	}
	
	public void setJob_techpost(String job_techpost) {
		this.job_techpost = job_techpost;
	}
	
	public void setJob_techtitle(String job_techtitle) {
		this.job_techtitle = job_techtitle;
	}
	
	public void setJob_humansort(String job_humansort) {
		this.job_humansort = job_humansort;
	}
	
	public void setJob_insectime(Date job_insectime) {
		this.job_insectime = job_insectime;
	}
	
	public void setJob_workyears(Integer job_workyears) {
		this.job_workyears = job_workyears;
	}
	
	public void setJob_employtype(String job_employtype) {
		this.job_employtype = job_employtype;
	}
	
	public void setJob_passnum(String job_passnum) {
		this.job_passnum = job_passnum;
	}
	
	public void setJob_passlevel(String job_passlevel) {
		this.job_passlevel = job_passlevel;
	}
	
	public void setJob_inposttime(Date job_inposttime) {
		this.job_inposttime = job_inposttime;
	}
	
	public void setJob_offposttime(Date job_offposttime) {
		this.job_offposttime = job_offposttime;
	}
	
	public void setJob_resume(String job_resume) {
		this.job_resume = job_resume;
	}
	
	public RealUser getRealUser() {
		return realUser;
	}
	
	public void setReal_user_id(String real_user_id) {
		this.real_user_id = real_user_id;
	}
	
	public void setUpdate(String update) {
		this.update = update;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("Y")) {//update realuser info
			realUser = new RealUser();
			realUser.setReal_user_id(real_user_id);
			realUser.setBase_username(base_username);
			realUser.setBase_sex(base_sex);
			realUser.setBase_nation(base_nation);
			realUser.setBase_birthday(base_birthday);
			realUser.setBase_birthplace(base_birthplace);
			realUser.setBase_nativeplace(base_nativeplace);
			realUser.setBase_country(base_country);
			realUser.setBase_politics(base_politics);
			realUser.setBase_joinpartytime(base_joinpartytime);
			realUser.setEdu_education(edu_education);
			realUser.setEdu_degree(edu_degree);
			realUser.setEdu_school(edu_school);
			realUser.setEdu_major(edu_major);
			realUser.setEdu_language(edu_language);
			realUser.setEdu_familiarity(edu_familiarity);
			realUser.setCom_residency(com_residency);
			realUser.setCom_police(com_police);
			realUser.setCom_address(com_address);
			realUser.setCom_telephone(com_telephone);
			realUser.setCom_mobile(com_mobile);
			realUser.setCom_email(com_email);
			realUser.setJob_category(job_category);
			realUser.setJob_seclevel(job_seclevel);
			realUser.setJob_adminpost(job_adminpost);
			realUser.setJob_techpost(job_techpost);
			realUser.setJob_techtitle(job_techtitle);
			realUser.setJob_humansort(job_humansort);
			realUser.setJob_insectime(job_insectime);
			realUser.setJob_workyears(job_workyears);
			realUser.setJob_employtype(job_employtype);
			realUser.setJob_passnum(job_passnum);
			realUser.setJob_passlevel(job_passlevel);
			realUser.setJob_inposttime(job_inposttime);
			realUser.setJob_offposttime(job_offposttime);
			realUser.setJob_resume(job_resume);
			userService.updateRealUserByReal(realUser);
			insertAdminLog("修改员工信息:" + real_user_id);
			return "update";
		} else {//enter web page for modification
			realUser = userService.getRealUserById(real_user_id);
		}
		return SUCCESS;
	}
}
