package hdsec.web.project.enter.action;

import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.enter.model.EnterEvent;
import hdsec.web.project.enter.model.SubSecDeptAdmin;
import hdsec.web.project.user.model.SecLevel;

import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 添加录入作业
 * 
 * @author gaoxm
 * 
 */
public class AddEnterEventAction extends EnterBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";// 作业流水号
	private Integer seclv_code = null;// 作业密级
	private String project_code = "";// 所属项目编号
	private String usage_code = "";// 用途编号
	private String summ = "";// 备注
	private String zipfile = ""; // 介质名称
	private String medium_serial = "";// 介质编号
	private Integer page_num = null; // 页数
	private String source = ""; // 来源
	private String place = ""; // 地点
	private Integer import_status = 0; // 录入状态(0未录入,1已录入)
	private Integer file_type = null; // 类型（1为文件,2为光盘）
	private String scope = ""; // 载体归属,PERSON个人文件,DEPT部门文件
	private Boolean deptadmin = false; // 标识是否为部门管理员

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}

	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public void setZipfile(String zipfile) {
		this.zipfile = zipfile;
	}

	public void setMedium_serial(String medium_serial) {
		this.medium_serial = medium_serial;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setPage_num(Integer page_num) {
		this.page_num = page_num;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public void setImport_status(Integer import_status) {
		this.import_status = import_status;
	}

	public void setFile_type(Integer file_type) {
		this.file_type = file_type;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public String getProject_code() {
		return project_code;
	}

	public String getUsage_code() {
		return usage_code;
	}

	public String getSumm() {
		return summ;
	}

	public String getZipfile() {
		return zipfile;
	}

	public String getMedium_serial() {
		return medium_serial;
	}

	public Integer getPage_num() {
		return page_num;
	}

	public String getSource() {
		return source;
	}

	public String getPlace() {
		return place;
	}

	public Integer getImport_status() {
		return import_status;
	}

	public Integer getFile_type() {
		return file_type;
	}

	public Boolean getDeptadmin() {
		return deptadmin;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getImportSecLevelByUser(getCurUser().getUser_iidd());
	}

	public List<SysUsage> getUsageList() {
		return basicService.getSysUsageList();
	}

	public List<SysProject> getProjectList() {
		return basicService.getSysProjectList();
	}

	public List<SubSecDeptAdmin> getAdminGroupList() {
		return basicService.getAdminGroupList(getCurUser().getUser_iidd());
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(medium_serial.trim())) {
			boolean is_used = enterService.checkBarcodeIsUsed(Integer.valueOf(file_type), medium_serial);
			if (is_used) {
				throw new Exception("您输入的条码已存在,请更换");
			}
		}
		if (StringUtils.hasLength(event_code)) {
			String user_iidd = getCurUser().getUser_iidd();
			String dept_id = getCurUser().getDept_id();
			EnterEvent event = null;
			if (scope.equals("PERSON")) {
				event = new EnterEvent(user_iidd, dept_id, event_code, seclv_code, usage_code, project_code, summ,
						zipfile, medium_serial, page_num, source, place, import_status, file_type, scope, "");
			} else {
				event = new EnterEvent(user_iidd, dept_id, event_code, seclv_code, usage_code, project_code, summ,
						zipfile, medium_serial, page_num, source, place, import_status, file_type, "DEPT", scope);
			}

			enterService.addEnterEvent(event);
			insertCommonLog("添加录入作业[" + event_code + "]");
			return "ok";
		} else {
			event_code = getCurUser().getUser_iidd() + System.currentTimeMillis();
			// deptadmin = getCurUser().is_deptAdmin();
			return SUCCESS;
		}
	}
}
