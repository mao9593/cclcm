package hdsec.web.project.secplace.model;

import hdsec.web.project.common.BaseDomain;

public class EntityVisitor extends BaseDomain {
	private String event_code = "";// 作业编号
	private String visitor_name = "";// 人员名称
	private String visitor_company = "";// 人员单位
	// private String visitor_post = "";// 职务
	private String certificate_type = "";// 证件类型
	private String certificate_code = "";// 证件号
	private String rfid_code = "";// RFID
	private int seclv_code;// 人员密级
	private String visitor_phone = "";// 电话
	private String visitor_belongings = "";// 携带物
	private String security_arrangement = "";// 安全措施
	private String summ = "";// 备注
	private String visitor_sex = "";// 人员性别
	private String nationality = "";// 国籍
	private String post_info = "";// 职务

	public String getVisitor_sex() {
		return visitor_sex;
	}

	public void setVisitor_sex(String visitor_sex) {
		this.visitor_sex = visitor_sex;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPost_info() {
		return post_info;
	}

	public void setPost_info(String post_info) {
		this.post_info = post_info;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getVisitor_name() {
		return visitor_name;
	}

	public void setVisitor_name(String visitor_name) {
		this.visitor_name = visitor_name;
	}

	public String getVisitor_company() {
		return visitor_company;
	}

	public void setVisitor_company(String visitor_company) {
		this.visitor_company = visitor_company;
	}

	public String getCertificate_type() {
		return certificate_type;
	}

	public void setCertificate_type(String certificate_type) {
		this.certificate_type = certificate_type;
	}

	public String getCertificate_code() {
		return certificate_code;
	}

	public void setCertificate_code(String certificate_code) {
		this.certificate_code = certificate_code;
	}

	public String getRfid_code() {
		return rfid_code;
	}

	public void setRfid_code(String rfid_code) {
		this.rfid_code = rfid_code;
	}

	public int getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(int seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getVisitor_phone() {
		return visitor_phone;
	}

	public void setVisitor_phone(String visitor_phone) {
		this.visitor_phone = visitor_phone;
	}

	public String getVisitor_belongings() {
		return visitor_belongings;
	}

	public void setVisitor_belongings(String visitor_belongings) {
		this.visitor_belongings = visitor_belongings;
	}

	public String getSecurity_arrangement() {
		return security_arrangement;
	}

	public void setSecurity_arrangement(String security_arrangement) {
		this.security_arrangement = security_arrangement;
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public EntityVisitor() {

	}

	public EntityVisitor(String event_code, String visitor_name, String visitor_company, String certificate_type,
			String certificate_code, String rfid_code, int seclv_code, String visitor_phone, String visitor_belongings,
			String security_arrangement, String summ) {
		super();
		this.event_code = event_code;
		this.visitor_name = visitor_name;
		this.visitor_company = visitor_company;
		this.certificate_type = certificate_type;
		this.certificate_code = certificate_code;
		this.rfid_code = rfid_code;
		this.seclv_code = seclv_code;
		this.visitor_phone = visitor_phone;
		this.visitor_belongings = visitor_belongings;
		this.security_arrangement = security_arrangement;
		this.summ = summ;

	}

	public EntityVisitor(String event_code, String visitor_name, String certificate_code, String visitor_sex,
			String nationality, String post_info) {
		super();
		this.event_code = event_code;
		this.visitor_name = visitor_name;
		this.certificate_code = certificate_code;
		this.visitor_sex = visitor_sex;
		this.nationality = nationality;
		this.post_info = post_info;

	}
}