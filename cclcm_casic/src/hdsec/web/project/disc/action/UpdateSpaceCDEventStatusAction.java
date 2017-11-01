package hdsec.web.project.disc.action;

import hdsec.web.project.basic.model.SysSeclevel;
import hdsec.web.project.disc.model.SpaceCDEvent;
import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.transfer.model.EventTransfer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 更新空白盘借用状态并更新介质台帐记录
 * 
 * @author lishu 2015-12-16
 */
public class UpdateSpaceCDEventStatusAction extends DiscBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code;// 作业号
	private Integer file_type;// 类型
	private String user_iidd;// 申请人ID
	private String user_name;// 申请人姓名
	private String dept_name;// 申请人部门名称
	private String duty_user_iidd;// 责任人ID
	private String duty_user_name;// 责任人姓名
	private String duty_dept_name;// 责任人部门名称
	private Integer seclv_code;// 密级代号
	private String project_code;// 所属项目
	private String create_type;// 产生类型
	private String scope;// 载体归属
	private String scope_dept_id;// 载体归属部门ID
	private String scope_dept_name;// 载体归属部门名称
	private String barcode;// 空白盘条码
	private String dept_id;// 申请人部门ID
	private String duty_dept_id;// 责任人部门ID
	private Integer spacecd_state;// 介质状态
	private String apply_user_iidd = "";
	private String apply_user_name = "";

	private String medium_barcode = "";// 通用载体条码，打印条码传值使用

	private SysSeclevel sysSeclevel = null;// 密级信息

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public void setFile_type(Integer file_type) {
		this.file_type = file_type;
	}

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
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

	public String getDuty_dept_name() {
		return duty_dept_name;
	}

	public void setDuty_dept_name(String duty_dept_name) {
		this.duty_dept_name = duty_dept_name;
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

	public String getCreate_type() {
		return create_type;
	}

	public void setCreate_type(String create_type) {
		this.create_type = create_type;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getScope_dept_id() {
		return scope_dept_id;
	}

	public void setScope_dept_id(String scope_dept_id) {
		this.scope_dept_id = scope_dept_id;
	}

	public String getScope_dept_name() {
		return scope_dept_name;
	}

	public void setScope_dept_name(String scope_dept_name) {
		this.scope_dept_name = scope_dept_name;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getDuty_dept_id() {
		return duty_dept_id;
	}

	public void setDuty_dept_id(String duty_dept_id) {
		this.duty_dept_id = duty_dept_id;
	}

	public Integer getFile_type() {
		return file_type;
	}

	public String getMedium_barcode() {
		return medium_barcode;
	}

	public void setMedium_barcode(String medium_barcode) {
		this.medium_barcode = medium_barcode;
	}

	@Override
	public String executeFunction() throws Exception {
		try {
			SpaceCDEvent event = discService.getSpaceCDEventByEventCode(event_code);
			user_name = event.getUser_name();
			dept_id = event.getDept_id();
			Integer seclv_code = event.getSeclv_code();
			event.setAssign_status(1);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("event_code", event_code);
			map.put("import_status", 1);
			map.put("finish_time", new Date());
			map.put("import_user_iidd", getCurUser().getUser_iidd());
			int enter_num = event.getEnter_num();
			// 生成载体生命周期记录
			CycleItem cycleitem = new CycleItem();

			// 共用部分取值
			user_iidd = event.getUser_iidd();
			user_name = event.getUser_name();
			dept_name = event.getDept_name();
			dept_id = event.getDept_id();// 光盘载体部门申请人id
			duty_user_iidd = getCurUser().getUser_iidd();// 责任人为当前部门管理员
			duty_user_name = getCurUser().getUser_name();
			duty_dept_name = getCurUser().getDept_name();
			duty_dept_id = getCurUser().getDept_id();// 光盘载体部门责任人id
			seclv_code = event.getSeclv_code();
			scope = event.getScope();
			scope_dept_id = event.getScope_dept_id();
			scope_dept_name = event.getScope_dept_name();
			EventTransfer transfer = null;

			return SUCCESS;
		} catch (Exception e) {
			logger.error("Exception" + e.getMessage());
			throw new Exception("执行录入操作出现异常");
		}

	}
}
