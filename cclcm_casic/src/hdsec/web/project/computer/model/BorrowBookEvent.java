package hdsec.web.project.computer.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.BaseEvent;

import java.util.Date;

/**
 * 笔记本借用类
 * 
 * @author guojiao
 */
public class BorrowBookEvent extends BaseEvent {
	private String contact_num = "";
	private Integer book_selv = null;
	private Date start_time = null;
	private Date end_time = null;
	private String place = "";
	private String devieinfo = "";
	private String other_info = "";
	private String judge = "";
	private String book_selv_name = "";
	private Date sign_time = null;
	private String signname = "";
	private String associat_entity = "";
	private String mobilephone_num = ""; // 七院笔记本借用表单-手机号
	private String disc_num = ""; // 七院笔记本借用表单-光盘编号
	private String security_methord = ""; // 七院笔记本借用表单-保密措施
	private String disc_file = ""; // 七院笔记本借用表单-刻录文件名

	public String getDisc_file() {
		return disc_file;
	}

	public void setDisc_file(String disc_file) {
		this.disc_file = disc_file;
	}

	public String getSecurity_methord() {
		return security_methord;
	}

	public void setSecurity_methord(String security_methord) {
		this.security_methord = security_methord;
	}

	public String getDisc_num() {
		return disc_num;
	}

	public void setDisc_num(String disc_num) {
		this.disc_num = disc_num;
	}

	public String getMobilephone_num() {
		return mobilephone_num;
	}

	public void setMobilephone_num(String mobilephone_num) {
		this.mobilephone_num = mobilephone_num;
	}

	public String getAssociat_entity() {
		return associat_entity;
	}

	public void setAssociat_entity(String associat_entity) {
		this.associat_entity = associat_entity;
	}

	public Date getSign_time() {
		return sign_time;
	}

	public void setSign_time(Date sign_time) {
		this.sign_time = sign_time;
	}

	public String getSignname() {
		return signname;
	}

	public void setSignname(String signname) {
		this.signname = signname;
	}

	public String getSign_time_str() {
		return sign_time == null ? "" : getSdf().format(sign_time);
	}

	public String getStart_time_str() {
		return start_time == null ? "" : getSdf().format(start_time);
	}

	public String getEnd_time_str() {
		return end_time == null ? "" : getSdf().format(end_time);
	}

	public String getBook_selv_name() {
		return book_selv_name;
	}

	public void setBook_selv_name(String book_selv_name) {
		this.book_selv_name = book_selv_name;
	}

	public String getDevieinfo() {
		return devieinfo;
	}

	public void setDevieinfo(String devieinfo) {
		this.devieinfo = devieinfo;
	}

	public String getJudge() {
		return judge;
	}

	public void setJudge(String judge) {
		this.judge = judge;
	}

	public String getContact_num() {
		return contact_num;
	}

	public void setContact_num(String contact_num) {
		this.contact_num = contact_num;
	}

	public Integer getBook_selv() {
		return book_selv;
	}

	public void setBook_selv(Integer book_selv) {
		this.book_selv = book_selv;
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

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getOther_info() {
		return other_info;
	}

	public void setOther_info(String other_info) {
		this.other_info = other_info;
	}

	public BorrowBookEvent() {

	}

	public BorrowBookEvent(String event_code, String user_iidd, String dept_id, Integer seclv_code, String usage_code,
			String project_code, String summ, String contact_num, Date start_time, Date end_time, Integer book_selv,
			String place, String other_info, String judge, String devieinfo, Date sign_time, String signname) {

		super(JobTypeEnum.BORROW_BOOK, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);

		this.contact_num = contact_num;
		this.book_selv = book_selv;
		this.start_time = start_time;
		this.end_time = end_time;
		this.place = place;
		this.other_info = other_info;
		this.judge = judge;
		this.devieinfo = devieinfo;
		this.sign_time = sign_time;
		this.signname = signname;
	}
}