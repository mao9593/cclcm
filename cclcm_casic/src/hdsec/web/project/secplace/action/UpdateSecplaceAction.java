package hdsec.web.project.secplace.action;

import hdsec.web.project.secplace.model.EntitySecplace;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 更新涉密场所信息
 * 
 * @author liuyaling 2015-5-13
 */
public class UpdateSecplaceAction extends SecplaceBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	EntitySecplace secplace = null;

	private String update = "N";// 是否更改计算机信息标志

	private String secplace_id = "";// 场所主键
	private String secplace_barcode = "";// 条码号（唯一）
	private String secplace_name = "";// 场所名称
	private String secplace_code = "";// 场所编号（唯一）
	private int secplace_type;// 要害类别（1：部门，2：部位）
	private String conf_code = "";// 保密编号
	private int seclv_code;// 要害密级
	private String secplace_location = "";// 物理位置
	private String leader_id = "";// 主管领导
	private String leader_name = "";// 主管领导
	private int secplace_status;// 状态（1：在用，2：停用）
	private String secplace_application = "";// 用途
	private Date found_time = null;// 成立时间
	private String duty_user_id = "";// 责任人id
	private String duty_user_name = "";// 责任人姓名
	private String duty_dept_id = "";// 责任人id
	private String duty_dept_name = "";// 责任人姓名
	private String user_number;// 涉密人员数量
	private String people_protect = "";// 人防措施
	private String physical_protect = "";// 物防措施
	private String technology_protect = "";// 技防措施
	private String user_iidd = "";// 录入人员ID
	private Date enter_time = null;// 录入时间

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public EntitySecplace getSecplace() {
		return secplace;
	}

	public void setSecplace(EntitySecplace secplace) {
		this.secplace = secplace;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
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

	public String getSecplace_barcode() {
		return secplace_barcode;
	}

	public void setSecplace_barcode(String secplace_barcode) {
		this.secplace_barcode = secplace_barcode;
	}

	public String getLeader_id() {
		return leader_id;
	}

	public void setLeader_id(String leader_id) {
		this.leader_id = leader_id;
	}

	public String getLeader_name() {
		return leader_name;
	}

	public void setLeader_name(String leader_name) {
		this.leader_name = leader_name;
	}

	public String getConf_code() {
		return conf_code;
	}

	public void setConf_code(String conf_code) {
		this.conf_code = conf_code;
	}

	public String getDuty_user_id() {
		return duty_user_id;
	}

	public void setDuty_user_id(String duty_user_id) {
		this.duty_user_id = duty_user_id;
	}

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name;
	}

	public Date getEnter_time() {
		return enter_time;
	}

	public void setEnter_time(Date enter_time) {
		this.enter_time = enter_time;
	}

	private String summ = "";// 备注

	public String getSecplace_id() {
		return secplace_id;
	}

	public void setSecplace_id(String secplace_id) {
		this.secplace_id = secplace_id;
	}

	public String getSecplace_name() {
		return secplace_name;
	}

	public void setSecplace_name(String secplace_name) {
		this.secplace_name = secplace_name;
	}

	public String getSecplace_code() {
		return secplace_code;
	}

	public void setSecplace_code(String secplace_code) {
		this.secplace_code = secplace_code;
	}

	public int getSecplace_type() {
		return secplace_type;
	}

	public void setSecplace_type(int secplace_type) {
		this.secplace_type = secplace_type;
	}

	public int getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(int seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getSecplace_location() {
		return secplace_location;
	}

	public void setSecplace_location(String secplace_location) {
		this.secplace_location = secplace_location;
	}

	public int getSecplace_status() {
		return secplace_status;
	}

	public void setSecplace_status(int secplace_status) {
		this.secplace_status = secplace_status;
	}

	public String getSecplace_application() {
		return secplace_application;
	}

	public void setSecplace_application(String secplace_application) {
		this.secplace_application = secplace_application;
	}

	public Date getFound_time() {
		return found_time;
	}

	public void setFound_time(Date found_time) {
		this.found_time = found_time;
	}

	public String getUser_number() {
		return user_number;
	}

	public void setUser_number(String user_number) {
		this.user_number = user_number;
	}

	public String getPeople_protect() {
		return people_protect;
	}

	public void setPeople_protect(String people_protect) {
		this.people_protect = people_protect;
	}

	public String getPhysical_protect() {
		return physical_protect;
	}

	public void setPhysical_protect(String physical_protect) {
		this.physical_protect = physical_protect;
	}

	public String getTechnology_protect() {
		return technology_protect;
	}

	public void setTechnology_protect(String technology_protect) {
		this.technology_protect = technology_protect;
	}

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	@Override
	public String executeFunction() throws Exception {
		System.out.println(update);
		if (update.equalsIgnoreCase("N")) {
			// device = deviceService.getDeviceByCode(device_code);
			if (StringUtils.hasLength(secplace_barcode)) {
				secplace = secplaceService.getSecplaceByBarcode(secplace_barcode);
				return SUCCESS;
			} else {
				throw new Exception("无法查询条码号，请重新尝试。");
			}
		} else {
			secplace = new EntitySecplace(secplace_barcode, secplace_name, secplace_code, secplace_type, conf_code,
					seclv_code, secplace_location, leader_id, secplace_status, secplace_application, found_time,
					duty_user_id, user_number, people_protect, physical_protect, technology_protect, new Date(), summ,
					user_iidd, duty_dept_id);
			secplaceService.updateSecplace(secplace);
			insertCommonLog("修改涉密场所：条码号[" + secplace_barcode + "]");
			return "ok";
		}
	}

}