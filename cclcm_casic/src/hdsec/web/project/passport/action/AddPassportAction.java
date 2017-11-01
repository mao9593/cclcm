package hdsec.web.project.passport.action;

import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.passport.model.EntityPassport;

import java.util.Date;

public class AddPassportAction extends PassportBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String user_iidd = ""; // 操作人ID
	private String dept_id = "";// 操作人部门ID
	private String passport_num = "";// 护照编号
	private int passport_type;// 护照类型
	private Date borrow_time = null;// 借用时间
	private Date return_time = null;// 借用时间
	private int passport_status;// 护照状态
	private String summ = "";// 备注
	private String duty_user_iidd = ""; // 责任人ID
	private String duty_user_name = "";// 责任人名称
	private String duty_dept_id = ""; // 责任部门ID
	private String duty_dept_name = ""; // 责任部门名称
	private Date issuing_date = null;// 签发日期
	private Date ending_date = null;// 结束日期
	private String issuing_authority = "";// 签发机关

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public int getPassport_type() {
		return passport_type;
	}

	public void setPassport_type(int passport_type) {
		this.passport_type = passport_type;
	}

	public Date getBorrow_time() {
		return borrow_time;
	}

	public void setBorrow_time(Date borrow_time) {
		this.borrow_time = borrow_time;
	}

	public Date getReturn_time() {
		return return_time;
	}

	public void setReturn_time(Date return_time) {
		this.return_time = return_time;
	}

	public int getPassport_status() {
		return passport_status;
	}

	public void setPassport_status(int passport_status) {
		this.passport_status = passport_status;
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public String getDuty_user_iidd() {
		return duty_user_iidd;
	}

	public void setDuty_user_iidd(String duty_user_iidd) {
		this.duty_user_iidd = duty_user_iidd;
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

	public Date getIssuing_date() {
		return issuing_date;
	}

	public void setIssuing_date(Date issuing_date) {
		this.issuing_date = issuing_date;
	}

	public Date getEnding_date() {
		return ending_date;
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

	@Override
	public String executeFunction() throws Exception {

		if (passport_num.isEmpty()) {
			return SUCCESS;
		} else {

			EntityPassport passport = new EntityPassport(user_iidd, dept_id, passport_num, passport_type, borrow_time,
					return_time, passport_status, summ, duty_user_iidd, duty_user_name, duty_dept_id, duty_dept_name,
					issuing_date, ending_date, issuing_authority);
			CycleItem cycleitem = new CycleItem();
			cycleitem.setBarcode(passport_num);
			cycleitem.setEntity_type("passport");
			cycleitem.setOper_time(new Date());
			cycleitem.setUser_name(getCurUser().getUser_name());
			cycleitem.setDept_name(getCurUser().getDept_name());
			cycleitem.setOper("LEADIN");

			passportService.addEntityPassport(passport, cycleitem);
			insertCommonLog("添加护照信息[" + passport_num + "]");

			return "ok";
		}
	}

}
