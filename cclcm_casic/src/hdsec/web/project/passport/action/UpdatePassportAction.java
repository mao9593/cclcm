package hdsec.web.project.passport.action;

import hdsec.web.project.passport.model.EntityPassport;

import java.util.Date;

import org.springframework.util.StringUtils;

/**
 * 更新计算机台账
 * 
 * @author gaoyiming 2015-7-22
 */
public class UpdatePassportAction extends PassportBaseAction {

	private static final long serialVersionUID = 1L;
	EntityPassport passport = null;
	private String update = "N";// 是否更改计算机信息标志

	private String user_iidd = ""; // 操作人ID
	private String dept_id = "";// 操作人部门ID
	private String passport_num = "";// 护照编号
	private int passport_type;// 护照编号
	private Date borrow_time = null;// 借用时间
	private Date return_time = null;// 借用时间
	private int passport_status;// 护照编号
	private String summ = "";// 备注
	private String duty_user_iidd = ""; // 责任人ID
	private String duty_user_name = "";// 责任人名称
	private String duty_dept_id = ""; // 责任部门ID
	private String duty_dept_name = ""; // 责任部门名称
	private Date issuing_date = null;// 签发日期
	private Date ending_date = null;// 过期日期
	private String issuing_authority = "";// 签发机关
	private String passport_id = "";// 护照ID

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

	public EntityPassport getPassport() {
		return passport;
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

	public String getDuty_dept_name() {
		return duty_dept_name;
	}

	public void setDuty_dept_name(String duty_dept_name) {
		this.duty_dept_name = duty_dept_name;
	}

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getDuty_dept_id() {
		return duty_dept_id;
	}

	public void setDuty_dept_id(String duty_dept_id) {
		this.duty_dept_id = duty_dept_id;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
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

	public String getPassport_id() {
		return passport_id;
	}

	public void setPassport_id(String passport_id) {
		this.passport_id = passport_id;
	}

	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("N")) {
			if (StringUtils.hasLength(passport_id)) {
				// passport = passportService.getPassportByNum(passport_num);
				passport = passportService.getPassportById(passport_id);
				return SUCCESS;
			} else {
				throw new Exception("无法查询护照信息，请重新尝试。");
			}
		} else {
			EntityPassport passportinfo = passportService.getPassportById(passport_id);
			passport = new EntityPassport(getCurUser().getUser_iidd(), getCurUser().getDept_id(), passport_num,
					passport_type, borrow_time, return_time, passport_status, summ, duty_user_iidd, duty_user_name,
					duty_dept_id, duty_dept_name, issuing_date, ending_date, issuing_authority);
			passport.setPassport_id(Integer.parseInt(passport_id));
			passportService.updatePassportById(passport);
			insertCommonLog("修改护照信息，编号[" + passportinfo.getPassport_num() + "]");
			return "ok";
		}
	}

}
