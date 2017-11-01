package hdsec.web.project.common.bm.model;

import hdsec.web.project.user.model.RealUser;

import java.sql.Date;

public class BmRealUser extends RealUser {
	protected String event_code = "";// 作业编号
	protected String real_user_id = "";
	protected Integer marital_status = null;// 婚姻状况（0：未婚 1：已婚 2：离异）
	protected String passport_id = "";// 出境护照ID
	protected String passport_info = "";// 护照信息
	protected String resident_card = "";// 绿卡或永久居留证信息
	protected String sec_category = "";// 涉密类别
	protected String abroad_twice = "";// 最近两次出国信息
	protected String family_info = "";// 家庭成员信息
	protected String idcard = "";// 身份证号
	protected Integer is_abroad = null;// 是否出国
	protected String user_promises = "";// 本人承诺
	protected Integer info_type = null;// 是否出国
	protected String bmpost_id = ""; // 岗位id
	protected String bmpost_name = ""; // 岗位类别
	protected Integer oversea = null;// 是否有海外经历
	protected Integer famliy_oversea = null;// 配偶子女是否有海外经历
	protected Integer user_statue = null;// 人员状态（1、在岗；2、退休；3、离职）

	public String getUser_statue_name() {
		if (user_statue == null) {
			return "";
		}
		String name = "";
		switch (this.user_statue) {
		case 1:
			name = "在岗";
			break;
		case 2:
			name = "退休";
			break;
		case 3:
			name = "离职";
			break;
		default:
			name = "";
			break;
		}
		return name;
	}

	public Integer getUser_statue() {
		return user_statue;
	}

	public void setUser_statue(Integer user_statue) {
		this.user_statue = user_statue;
	}

	public Integer getFamliy_oversea() {
		return famliy_oversea;
	}

	public Integer getOversea() {
		return oversea;
	}

	public String getOversea_name() {
		if (oversea == null) {
			return "";
		}
		String name = "";
		switch (this.oversea) {
		case 1:
			name = "无";
			break;
		case 2:
			name = "有工作经历";
			break;
		case 3:
			name = "有学习经历";
			break;
		default:
			name = "";
			break;
		}
		return name;
	}

	public void setOversea(Integer oversea) {
		this.oversea = oversea;
	}

	public String getFamliy_oversea_name() {
		if (famliy_oversea == null) {
			return "";
		}
		String name = "";
		switch (this.famliy_oversea) {
		case 1:
			name = "无";
			break;
		case 2:
			name = "有工作经历";
			break;
		case 3:
			name = "有学习经历";
			break;
		default:
			name = "";
			break;
		}
		return name;
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

	public String getBase_politics_name() {
		if (base_politics == null) {
			return "";
		}
		String name = "";
		switch (this.base_politics) {
		case "1":
			name = "中共党员";
			break;
		case "2":
			name = "共青团员";
			break;
		case "3":
			name = "群众";
			break;
		default:
			name = "";
			break;
		}
		return name;
	}

	public String getEdu_education_name() {
		if (edu_education == null) {
			return "";
		}
		String name = "";
		switch (this.edu_education) {
		case "1":
			name = "博士";
			break;
		case "2":
			name = "硕士";
			break;
		case "3":
			name = "本科";
			break;
		case "4":
			name = "大专";
			break;
		case "5":
			name = "高中";
			break;
		case "6":
			name = "中专";
			break;
		case "7":
			name = "初中及以下";
			break;
		default:
			name = "";
			break;
		}
		return name;
	}

	public String getBase_sex_name() {
		return base_sex.equals("M") ? "男" : "女";
	}

	public String getIs_abroad_name() {
		if (is_abroad == null) {
			return "";
		}
		switch (is_abroad) {
		case 1:
			return "有出国（境）经历";
		case 2:
			return "无出国（境）经历";
		default:
			return "";
		}
	}

	public Integer getIs_abroad() {
		return is_abroad;
	}

	public void setIs_abroad(Integer is_abroad) {
		this.is_abroad = is_abroad;
	}

	public String getUser_promises() {
		return user_promises;
	}

	public void setUser_promises(String user_promises) {
		this.user_promises = user_promises;
	}

	public Integer getInfo_type() {
		return info_type;
	}

	public void setInfo_type(Integer info_type) {
		this.info_type = info_type;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	@Override
	public String getReal_user_id() {
		return real_user_id;
	}

	@Override
	public void setReal_user_id(String real_user_id) {
		this.real_user_id = real_user_id;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
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

	public String getAbroad_twice() {
		return abroad_twice;
	}

	public void setAbroad_twice(String abroad_twice) {
		this.abroad_twice = abroad_twice;
	}

	public String getFamily_info() {
		return family_info;
	}

	public void setFamily_info(String family_info) {
		this.family_info = family_info;
	}

	public Integer getMarital_status() {
		return marital_status;
	}

	public String getMarital_status_str() {
		if (marital_status == null)
			return "";
		String name = "";
		switch (this.marital_status) {
		case 0:
			name = "未婚";
			break;
		case 1:
			name = "已婚";
			break;
		case 2:
			name = "离异";
			break;
		case 3:
			name = "丧偶";
			break;
		default:
			name = "";
			break;
		}
		return name;
	}

	public void setMarital_status(Integer marital_status) {
		this.marital_status = marital_status;
	}

	public BmRealUser() {
		super();
	}

	public BmRealUser(String event_code, String real_user_id, Integer marital_status, String passport_id,
			String passport_info, String resident_card, String sec_category, String abroad_twice, String family_info,
			String base_username, String base_sex, String base_nation, Date base_birthday, String base_nativeplace,
			String base_country, String base_politics, String edu_education, Date job_insectime, String job_techpost,
			String job_techtitle, String com_residency, String com_address, String com_telephone, String com_email,
			String job_resume, String idcard, String user_promises, Integer is_abroad, Integer info_type,
			String bmpost_id, String bmpost_name, Integer oversea, Integer famliy_oversea) {

		this.event_code = event_code;
		this.real_user_id = real_user_id;
		this.marital_status = marital_status;
		this.passport_id = passport_id;
		this.passport_info = passport_info;
		this.resident_card = resident_card;
		this.sec_category = sec_category;
		this.abroad_twice = abroad_twice;
		this.family_info = family_info;
		this.base_username = base_username;
		this.base_sex = base_sex;
		this.base_nation = base_nation;
		this.base_birthday = base_birthday;
		this.base_nativeplace = base_nativeplace;
		this.base_country = base_country;
		this.base_politics = base_politics;
		this.edu_education = edu_education;
		this.job_insectime = job_insectime;
		this.job_techpost = job_techpost;
		this.job_techtitle = job_techtitle;
		this.com_residency = com_residency;
		this.com_address = com_address;
		this.com_telephone = com_telephone;
		this.com_email = com_email;
		this.job_resume = job_resume;
		this.idcard = idcard;
		this.user_promises = user_promises;
		this.is_abroad = is_abroad;
		this.info_type = info_type;
		this.bmpost_id = bmpost_id;
		this.bmpost_name = bmpost_name;
		this.oversea = oversea;
		this.famliy_oversea = famliy_oversea;
	}
}
