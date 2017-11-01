package hdsec.web.project.passport.model;

import hdsec.web.project.common.BaseDomain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 护照台账
 * 
 * @author gaoyiming 2015-7-10
 */
public class EntityPassport extends BaseDomain {

	private Integer passport_id = null;
	private String user_iidd = ""; // 操作人ID
	private String dept_id = "";// 操作人部门ID
	private String passport_num = "";// 护照编号
	private Integer passport_type = null;// 护照类型
	private Date borrow_time = null;// 借用时间
	private Date return_time = null;// 归还时间
	private Integer passport_status = null;// 护照编号
	private String summ = "";// 备注
	private String duty_user_iidd = ""; // 责任人ID
	private String duty_user_name = "";// 责任人名称
	private String duty_dept_id = ""; // 责任部门ID
	private String duty_dept_name = ""; // 责任部门名称
	private String passport_type_name = "";// 护照类型名称
	private String passport_status_name = "";// 护照状态名称
	private Date issuing_date = null;// 签发日期
	private Date ending_date = null;// 结束日期
	private String issuing_authority = "";// 签发机关

	public void setPassport_id(Integer passport_id) {
		this.passport_id = passport_id;
	}

	public void setPassport_type(Integer passport_type) {
		this.passport_type = passport_type;
	}

	public void setPassport_status(Integer passport_status) {
		this.passport_status = passport_status;
	}

	public int getPassport_id() {
		return passport_id;
	}

	public void setPassport_id(int passport_id) {
		this.passport_id = passport_id;
	}

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getPassport_num() {
		return passport_num;
	}

	public void setPassport_num(String passport_num) {
		this.passport_num = passport_num;
	}

	public int getPassport_status() {
		return passport_status;
	}

	public void setPassport_status(int passport_status) {
		this.passport_status = passport_status;
	}

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name;
	}

	public String getDuty_dept_id() {
		return duty_dept_id;
	}

	public void setDuty_dept_id(String duty_dept_id) {
		this.duty_dept_id = duty_dept_id;
	}

	public String getDuty_dept_name() {
		return duty_dept_name;
	}

	public void setDuty_dept_name(String duty_dept_name) {
		this.duty_dept_name = duty_dept_name;
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public int getPassport_type() {
		return passport_type;
	}

	public void setPassport_type(int passport_type) {
		this.passport_type = passport_type;
	}

	public String getDuty_user_iidd() {
		return duty_user_iidd;
	}

	public void setDuty_user_iidd(String duty_user_iidd) {
		this.duty_user_iidd = duty_user_iidd;
	}

	public Date getReturn_time() {
		return return_time;
	}

	public void setReturn_time(Date return_time) {
		this.return_time = return_time;
	}

	public Date getBorrow_time() {
		return borrow_time;
	}

	public void setBorrow_time(Date borrow_time) {
		this.borrow_time = borrow_time;
	}

	public String getPassport_type_name() {
		String name = "";
		switch (passport_type) {
		case 0:
			name = "普通护照";
			break;
		case 1:
			name = "外交护照";
			break;
		case 2:
			name = "公务护照";
			break;
		case 3:
			name = "港澳通行证";
			break;
		case 4:
			name = "大陆居民来往台湾地区通行证";
			break;
		default:
			name = "";
			break;
		}
		return name;
	}

	public void setPassport_type_name(String passport_type_name) {
		this.passport_type_name = passport_type_name;
	}

	public String getPassport_status_name() {
		String name = "";
		switch (passport_status) {
		case 0:
			name = "在册";
			break;
		case 1:
			name = "借出";
			break;
		case 2:
			name = "删除";
			break;
		default:
			name = "";
			break;
		}
		return name;
	}

	public void setPassport_status_name(String passport_status_name) {
		this.passport_status_name = passport_status_name;
	}

	public String getIssuing_date() {
		// return issuing_date;
		return issuing_date == null ? "" : getSdf().format(issuing_date);
	}

	public void setIssuing_date(Date issuing_date) {
		this.issuing_date = issuing_date;
	}

	public String getEnding_date() {
		// return ending_date;
		return ending_date == null ? "" : getSdf().format(ending_date);
	}

	public void setEnding_date(Date ending_date) {
		this.ending_date = ending_date;
	}

	public String getIssuing_authority() {
		return issuing_authority;
	}

	public void setIssuing_authority(String issuing_authority) {
		this.issuing_authority = issuing_authority;
	}

	public String getIssuing_date_str() {
		return issuing_date == null ? "" : sdf.format(issuing_date);
	}

	public String getEnding_date_str() {
		return ending_date == null ? "" : sdf.format(ending_date);
	}

	public EntityPassport() {
		super();
	}

	public EntityPassport(SimpleDateFormat sdf) {
		super(sdf);
	}

	public EntityPassport(String user_iidd, String dept_id, String passport_num, Integer passport_type,
			Date borrow_time, Date return_time, Integer passport_status, String summ, String duty_user_iidd,
			String duty_user_name, String duty_dept_id, String duty_dept_name, Date issuing_date, Date ending_date,
			String issuing_authority) {

		this.borrow_time = borrow_time;
		this.dept_id = dept_id;
		this.duty_dept_id = duty_dept_id;
		this.duty_dept_name = duty_dept_name;
		this.duty_user_iidd = duty_user_iidd;
		this.duty_user_name = duty_user_name;
		this.passport_num = passport_num;
		this.passport_status = passport_status;
		this.passport_type = passport_type;
		this.return_time = return_time;
		this.summ = summ;
		this.user_iidd = user_iidd;
		this.issuing_date = issuing_date;
		this.ending_date = ending_date;
		this.issuing_authority = issuing_authority;

	}

}