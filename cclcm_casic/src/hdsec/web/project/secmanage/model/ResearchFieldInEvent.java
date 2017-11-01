package hdsec.web.project.secmanage.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.BaseEvent;

import java.util.Date;

/**
 * 进入重要科研场地作业
 * 
 * @author gaoximin 2015-7-21
 */
public class ResearchFieldInEvent extends BaseEvent {

	private String contact_num = ""; // 联系电话
	private String visitor_company = ""; // 进入成员单位
	private String visitor_names = ""; // 进入成员名单
	private String field_site = ""; // 拟进入科研场地地点
	private String reason = ""; // 事由
	private String belongings = ""; // 携带物品
	private String rec_user_iidd = ""; // 接待人ID
	private String rec_user_name = ""; // 接待人
	private Date enter_time = null; // 进入时间
	private Date leave_time = null; // 离开时间

	public String getContact_num() {
		return contact_num;
	}

	public void setContact_num(String contact_num) {
		this.contact_num = contact_num;
	}

	public String getVisitor_company() {
		return visitor_company;
	}

	public void setVisitor_company(String visitor_company) {
		this.visitor_company = visitor_company;
	}

	public String getVisitor_names() {
		return visitor_names;
	}

	public void setVisitor_names(String visitor_names) {
		this.visitor_names = visitor_names;
	}

	public String getField_site() {
		return field_site;
	}

	public void setField_site(String field_site) {
		this.field_site = field_site;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getBelongings() {
		return belongings;
	}

	public void setBelongings(String belongings) {
		this.belongings = belongings;
	}

	public String getRec_user_iidd() {
		return rec_user_iidd;
	}

	public void setRec_user_iidd(String rec_user_iidd) {
		this.rec_user_iidd = rec_user_iidd;
	}

	public String getRec_user_name() {
		return rec_user_name;
	}

	public void setRec_user_name(String rec_user_name) {
		this.rec_user_name = rec_user_name;
	}

	public Date getEnter_time() {
		return enter_time;
	}

	public void setEnter_time(Date enter_time) {
		this.enter_time = enter_time;
	}

	public Date getLeave_time() {
		return leave_time;
	}

	public void setLeave_time(Date leave_time) {
		this.leave_time = leave_time;
	}

	public String getEnter_time_str() {
		return enter_time == null ? "" : getSdf().format(enter_time);
	}

	public String getLeave_time_str() {
		return leave_time == null ? "" : getSdf().format(leave_time);
	}

	public ResearchFieldInEvent() {
		super(JobTypeEnum.FIELDIN);
	}

	public ResearchFieldInEvent(String user_iidd, String dept_id, String event_code, Integer seclv_code,
			String usage_code, String project_code, String summ, String contact_num, String visitor_company,
			String visitor_names, String field_site, String reason, String belongings, String rec_user_iidd,
			String rec_user_name, Date enter_time, Date leave_time) {
		super(JobTypeEnum.FIELDIN, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);

		this.contact_num = contact_num;
		this.visitor_company = visitor_company;
		this.visitor_names = visitor_names;
		this.field_site = field_site;
		this.reason = reason;
		this.belongings = belongings;
		this.rec_user_iidd = rec_user_iidd;
		this.rec_user_name = rec_user_name;
		this.enter_time = enter_time;
		this.leave_time = leave_time;

	}

}
