package hdsec.web.project.securityuser.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.common.bm.model.BmRealUser;
import hdsec.web.project.securityuser.model.AbroadInfo;
import hdsec.web.project.securityuser.model.BmUserInfoEvent;
import hdsec.web.project.securityuser.model.ExperienceInfo;
import hdsec.web.project.securityuser.model.FamilyInfo;
import hdsec.web.project.user.model.Post;
import hdsec.web.project.user.model.SecLevel;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.model.UserSecurity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 完善个人资料
 * 
 * @author guojiao
 * 
 */
public class AddUserInfoEventAction extends SecurityUserBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";// 作业流水号
	private Integer seclv_code = null;// 作业密级
	private String project_code = "";// 所属项目编号
	private String usage_code = "";// 用途编号
	private String summ = ""; // 备注
	private String next_approver = "";// 下级审批人
	private List<BmUserInfoEvent> eventList = null;
	private final String jobType = JobTypeEnum.USER_INFO.getJobTypeCode();

	private String base_username = "";// 姓名
	private String base_sex = "";// 性别
	private String base_nation = "";// 民族
	private Date base_birthday = null;// 出生日期
	private String base_nativeplace = "";// 籍贯
	private String base_country = "";// 国别
	private String base_politics = "";// 政治面貌
	private String edu_education = "";// 学历
	private String com_residency = "";// 户籍所在地
	private String com_address = "";// 现住址
	private String com_telephone = "";// 联系电话
	private String com_email = "";// 电子邮箱
	private String job_techpost = "";// 技术职务
	private String job_techtitle = "";// 技术职称
	private Date job_insectime = null;// 进入涉密岗位时间
	private String job_resume = "";// 个人简历
	private String idcard = "";// 身份证号
	private Integer marital_status = null;// 婚姻状况（0：未婚 1：已婚 2：离异）
	private String passport_id = "";// 出境护照ID
	private String passport_info = "";// 护照信息
	private String resident_card = "";// 绿卡或永久居留证信息
	private String sec_category = "";// 涉密类别
	private String abroad_twice = "";// 最近两次出国信息
	private String family_info = "";// 家庭成员信息
	private BmRealUser bmUser = null;

	private String user_promises = "本人承诺所填信息真实有效，并为此承担相应责任";
	// private String is_promises = "";
	private Integer is_abroad = null;
	private Integer info_type = 1;

	private String abroad_time = "";
	private String back_time = "";
	private String abroad_place = "";
	private String abroad_reason = "";
	private String out_customs = "";
	private String in_customs = "";
	private String abroad_content = "";
	private String abroad_file = "";

	private String family_relationship = "";
	private String family_name = "";
	private String family_nationality = "";
	private String family_borndate = "";
	private String family_politicalstatus = "";
	private String family_workplace = "";
	private String family_lifeplace = "";
	private String family_greencard = "";

	private String experience_time = "";
	private String end_time = "";
	private String experience_content = "";
	private Integer abroad_num = null;
	private Integer experience_num = null;
	private Integer family_num = null;

	private List<AbroadInfo> abroadinfo = null;
	private List<ExperienceInfo> experienceinfo = null;
	private List<FamilyInfo> familyinfo = null;
	private SecUser secUser = null;
	private String func = "";// 用户点击保存“SAVE”，以及查看保存记录变量"READ"
	private Integer saveStatue = null;
	private Integer ifedit = null;// 申请界面所有输入框是否都可以编辑，1为可编辑；2为部分可编辑（SGD需求）

	private String bmpost_id = ""; // 岗位id
	private String bmpost_name = ""; // 岗位类别
	private Integer oversea = null;// 是否有海外经历
	private Integer famliy_oversea = null;// 配偶子女是否有海外经历
	private String dept_id = ""; // 人员所属部门id
	private List<Post> postList = new ArrayList<Post>();

	private String enter_index = "";// 如果是通过Index进来的，此处为Y
	private String done = "";// 如果用户通过Index进来的，并且提交了申请，此处为Y
	private String headpath = "";// 用户头像路径

	public String getHeadpath() {
		return headpath;
	}

	public void setHeadpath(String headpath) {
		this.headpath = headpath;
	}

	public Integer getIfedit() {
		return ifedit;
	}

	public String getEnter_index() {
		return enter_index;
	}

	public void setEnter_index(String enter_index) {
		this.enter_index = enter_index;
	}

	public String getDone() {
		return done;
	}

	public void setDone(String done) {
		this.done = done;
	}

	public Integer getFamliy_oversea() {
		return famliy_oversea;
	}

	public Integer getOversea() {
		return oversea;
	}

	public void setOversea(Integer oversea) {
		this.oversea = oversea;
	}

	public void setFamliy_oversea(Integer famliy_oversea) {
		this.famliy_oversea = famliy_oversea;
	}

	public String getBmpost_id() {
		return bmpost_id;
	}

	public void setBmpost_id(String bmpost_id) {
		this.bmpost_id = bmpost_id;
	}

	public String getBmpost_name() {
		return bmpost_name;
	}

	public void setBmpost_name(String bmpost_name) {
		this.bmpost_name = bmpost_name;
	}

	public String getAbroad_file() {
		return abroad_file;
	}

	public void setAbroad_file(String abroad_file) {
		this.abroad_file = abroad_file;
	}

	public Integer getSaveStatue() {
		return saveStatue;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public void setFunc(String func) {
		this.func = func;
	}

	public SecUser getSecUser() {
		return secUser;
	}

	public Integer getAbroad_num() {
		return abroad_num;
	}

	public Integer getExperience_num() {
		return experience_num;
	}

	public Integer getFamily_num() {
		return family_num;
	}

	public List<FamilyInfo> getFamilyinfo() {
		return familyinfo;
	}

	public List<AbroadInfo> getAbroadinfo() {
		return abroadinfo;
	}

	public List<ExperienceInfo> getExperienceinfo() {
		return experienceinfo;
	}

	public void setAbroad_num(Integer abroad_num) {
		this.abroad_num = abroad_num;
	}

	public void setExperience_num(Integer experience_num) {
		this.experience_num = experience_num;
	}

	public void setFamily_num(Integer family_num) {
		this.family_num = family_num;
	}

	public void setUser_promises(String user_promises) {
		this.user_promises = user_promises;
	}

	public void setIs_abroad(Integer is_abroad) {
		this.is_abroad = is_abroad;
	}

	public void setAbroad_time(String abroad_time) {
		this.abroad_time = abroad_time;
	}

	public void setBack_time(String back_time) {
		this.back_time = back_time;
	}

	public void setAbroad_place(String abroad_place) {
		this.abroad_place = abroad_place;
	}

	public void setAbroad_reason(String abroad_reason) {
		this.abroad_reason = abroad_reason;
	}

	public void setOut_customs(String out_customs) {
		this.out_customs = out_customs;
	}

	public void setIn_customs(String in_customs) {
		this.in_customs = in_customs;
	}

	public void setAbroad_content(String abroad_content) {
		this.abroad_content = abroad_content;
	}

	public void setFamily_relationship(String family_relationship) {
		this.family_relationship = family_relationship;
	}

	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}

	public void setFamily_nationality(String family_nationality) {
		this.family_nationality = family_nationality;
	}

	public void setFamily_borndate(String family_borndate) {
		this.family_borndate = family_borndate;
	}

	public void setFamily_politicalstatus(String family_politicalstatus) {
		this.family_politicalstatus = family_politicalstatus;
	}

	public void setFamily_workplace(String family_workplace) {
		this.family_workplace = family_workplace;
	}

	public void setFamily_lifeplace(String family_lifeplace) {
		this.family_lifeplace = family_lifeplace;
	}

	public void setFamily_greencard(String family_greencard) {
		this.family_greencard = family_greencard;
	}

	public void setExperience_time(String experience_time) {
		this.experience_time = experience_time;
	}

	public void setExperience_content(String experience_content) {
		this.experience_content = experience_content;
	}

	public BmRealUser getBmUser() {
		return bmUser;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getProject_code() {
		return project_code;
	}

	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}

	public String getUsage_code() {
		return usage_code;
	}

	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code;
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public String getJobType() {
		return jobType;
	}

	public List<BmUserInfoEvent> getEventList() {
		return eventList;
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

	public String getEdu_education() {
		return edu_education;
	}

	public void setEdu_education(String edu_education) {
		this.edu_education = edu_education;
	}

	public String getCom_residency() {
		return com_residency;
	}

	public void setCom_residency(String com_residency) {
		this.com_residency = com_residency;
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

	public String getCom_email() {
		return com_email;
	}

	public void setCom_email(String com_email) {
		this.com_email = com_email;
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

	public Date getJob_insectime() {
		return job_insectime;
	}

	public void setJob_insectime(Date job_insectime) {
		this.job_insectime = job_insectime;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public Integer getMarital_status() {
		return marital_status;
	}

	public void setMarital_status(Integer marital_status) {
		this.marital_status = marital_status;
	}

	public String getPassport_id() {
		return passport_id;
	}

	public void setPassport_id(String passport_id) {
		this.passport_id = passport_id;
	}

	public String getPassport_info() {
		return passport_info;
	}

	public void setPassport_info(String passport_info) {
		this.passport_info = passport_info;
	}

	public String getResident_card() {
		return resident_card;
	}

	public void setResident_card(String resident_card) {
		this.resident_card = resident_card;
	}

	public String getSec_category() {
		return sec_category;
	}

	public void setSec_category(String sec_category) {
		this.sec_category = sec_category;
	}

	// 获取所有密级
	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public List<SysUsage> getUsageList() {
		return basicService.getSysUsageList();
	}

	public List<SysProject> getProjectList() {
		return basicService.getSysProjectList();
	}

	public List<UserSecurity> getSecurityList() {
		return userService.getSecurityList();
	}

	public String getDept_id() {
		return dept_id;
	}

	public List<Post> getPostList() {
		return postList;
		// return userService.getPostByDeptAsCon(curUser.getDept_id());
	}

	protected void ViewSpiltInfo(BmRealUser uinfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("event_code", uinfo.getEvent_code());
		// 个人简历
		if (!uinfo.getJob_resume().equals("")) {
			experienceinfo = securityUserService.getUserExperience(map);
			if (experienceinfo != null) {
				experience_num = experienceinfo.size();
			}
		}

		// 出国情况
		if (!uinfo.getAbroad_twice().equals("")) {
			abroadinfo = securityUserService.getUserAbroad(map);
			if (abroadinfo != null) {
				abroad_num = abroadinfo.size();
			}
		}

		// 家庭成员情况
		if (!uinfo.getFamily_info().equals("")) {
			familyinfo = securityUserService.getUserFamily(map);
			if (familyinfo != null) {
				family_num = familyinfo.size();
			}
		}
	}

	protected void viewBmUserInfo(String user_id, int infotype) {
		if (infotype == 2) {
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("real_user_id", user_id);
			map1.put("info_type", 3);
			List<BmRealUser> usertemp = securityUserService.getUserInfoByUserIdAndInfoType(map1);
			if (usertemp.size() != 0) {
				saveStatue = 3;
			}
		}

		Map<String, Object> mp = new HashMap<String, Object>();
		mp.put("real_user_id", user_id);
		mp.put("info_type", infotype);
		List<BmRealUser> usertemp3 = securityUserService.getUserInfoByUserIdAndInfoType(mp);
		if (usertemp3 == null) {
			return;
		}
		for (int i = 0; i < usertemp3.size(); i++) {
			if (usertemp3.get(i).getInfo_type() == infotype) {
				bmUser = usertemp3.get(i);
				ViewSpiltInfo(bmUser);
			}
		}

		// 判断用户是否有“已驳回”未通过的申请,并显示上次已驳回信息
		if (infotype == 2 && usertemp3.size() == 0) {
			mp.put("info_type", 1);
			List<BmRealUser> temp1 = securityUserService.getUserInfoByUserIdAndInfoType(mp);
			if (temp1.size() != 0) {
				for (BmRealUser tmp : temp1) {
					BmUserInfoEvent event1 = securityUserService.getUserInfoEventByEventCode(tmp.getEvent_code());
					if (event1.getJob_status().equals("false")) {
						bmUser = tmp;
						ViewSpiltInfo(bmUser);
						ifedit = 1;
						if (saveStatue == null) {
							saveStatue = 2;
						} else {
							if (saveStatue == 3) {
								saveStatue = 5;
							} else {
								saveStatue = 2;
							}
						}
					}
				}
			}
		} else {
			saveStatue = 4;
		}
	}

	@Override
	public String executeFunction() throws Exception {
		String user_iidd = getCurUser().getUser_iidd();
		String dept_id = getCurUser().getDept_id();
		postList = userService.getPostByDeptAsCon(curUser.getDept_id()); // yangjl 2015-08-23
		if (StringUtils.hasLength(event_code)) {
			if (func.equals("READ")) {
				secUser = userService.getSecUserByUid(getCurUser().getUser_iidd());
				viewBmUserInfo(user_iidd, 3);
				saveStatue = 1;
				ifedit = 1;
				return SUCCESS;
			} else {

				String[] abroad_time_str = abroad_time.split(",");
				String[] back_time_str = back_time.split(",");
				String[] abroad_place_str = abroad_place.split(",");
				String[] abroad_reason_str = abroad_reason.split(",");
				String[] out_customs_str = out_customs.split(",");
				String[] in_customs_str = in_customs.split(",");
				String[] abroad_content_str = abroad_content.split(",");
				String[] family_relationship_str = family_relationship.split(",");
				String[] family_name_str = family_name.split(",");
				String[] family_nationality_str = family_nationality.split(",");
				String[] family_borndate_str = family_borndate.split(",");
				String[] family_politicalstatus_str = family_politicalstatus.split(",");
				String[] family_workplace_str = family_workplace.split(",");
				String[] family_lifeplace_str = family_lifeplace.split(",");
				String[] family_greencard_str = family_greencard.split(",");
				String[] experience_time_str = experience_time.split(",");
				String[] end_time_str = end_time.split(",");
				String[] abroad_file_str = abroad_file.split(",");
				String[] experience_content_str = experience_content.split(",");

				int i = 0;
				// 个人简历
				if (experience_num != null) {
					for (i = 0; i < experience_num; i++) {
						ExperienceInfo experience = new ExperienceInfo(experience_time_str[i].trim(),
								end_time_str[i].trim(), experience_content_str[i].trim(), experience_num.toString(),
								event_code);
						securityUserService.addUserExperiences(experience);
					}
					job_resume = experience_num.toString();
				}
				// 出国情况
				if (abroad_num != null) {
					for (i = 0; i < abroad_num; i++) {
						AbroadInfo abroad = new AbroadInfo(abroad_time_str[i].trim(), back_time_str[i].trim(),
								abroad_place_str[i].trim(), abroad_reason_str[i].trim(), out_customs_str[i].trim(),
								in_customs_str[i].trim(), abroad_content_str[i].trim(), abroad_file_str[i].trim(),
								abroad_num.toString(), event_code);
						securityUserService.addUserAbroad(abroad);
					}
					abroad_twice = abroad_num.toString();
				}
				// 家庭成员
				if (family_num != null) {
					for (i = 0; i < family_num; i++) {
						FamilyInfo family = new FamilyInfo(family_relationship_str[i].trim(),
								family_name_str[i].trim(), family_nationality_str[i].trim(),
								family_borndate_str[i].trim(), family_politicalstatus_str[i].trim(),
								family_workplace_str[i].trim(), family_lifeplace_str[i].trim(),
								family_greencard_str[i].trim(), family_num.toString(), event_code);
						securityUserService.addUserFamily(family);
					}
					family_info = family_num.toString();
				}
				BmUserInfoEvent event = new BmUserInfoEvent(user_iidd, dept_id, event_code, seclv_code, usage_code,
						project_code, summ, "", "");
				BmRealUser tempuser = new BmRealUser(event_code, user_iidd, marital_status, passport_id, passport_info,
						resident_card, sec_category, abroad_twice, family_info, base_username, base_sex, base_nation,
						base_birthday, base_nativeplace, base_country, base_politics, edu_education, job_insectime,
						job_techpost, job_techtitle, com_residency, com_address, com_telephone, com_email, job_resume,
						idcard, user_promises, is_abroad, info_type, bmpost_id, bmpost_name, oversea, famliy_oversea);
				event.setJobType(JobTypeEnum.valueOf(jobType));
				// 界面进行保存，则只更新数据库event表，不对job进行更新
				if (func.equals("SAVE")) {
					// 界面点击保存之前将以前保存数据进行删除，只保留最后一次保存记录
					Map<String, Object> mm = new HashMap<String, Object>();
					mm.put("real_user_id", event.getUser_iidd());
					mm.put("info_type", 3);
					securityUserService.delUserInfoByUserIdAndInfoType(mm);

					// 人员信息表中增加保存数据，使用info_type=3
					tempuser.setInfo_type(3);
					securityUserService.addUserInfoToTemp(tempuser);
				} else {
					securityUserService.addUserInfoEvent(event, next_approver);
					securityUserService.addUserInfoToTemp(tempuser);
					insertCommonLog("添加用户个人信息申请作业[" + event_code + "]");
				}
			}
			if (enter_index.equals("Y")) {
				enter_index = "N";
				done = "Y";
				return "index_ok";
			} else {
				return "ok";
			}

		} else {
			// 进入申请界面，查询数据库显示人员相关数据sec_user
			secUser = userService.getSecUserByUid(getCurUser().getUser_iidd());
			// 进入申请界面，查询数据库显示人员相关数据bm_real_user
			viewBmUserInfo(user_iidd, 2);
			headpath = getHeadShot(getCurUser().getUser_iidd());

			event_code = getCurUser().getUser_iidd() + "_USERINFO_" + System.currentTimeMillis();
			return SUCCESS;
		}
	}
}
