package hdsec.web.project.enter.action;

import hdsec.web.project.enter.model.EnterEvent;
import hdsec.web.project.user.model.SecDept;
import hdsec.web.project.user.model.SecLevel;

import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 修改录入作业
 * 
 * @author gaoxm
 * 
 */
public class UpdateEnterEventAction extends EnterBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";// 作业流水号
	private Integer seclv_code = null;// 作业密级
	private String summ = "";// 备注
	private String zipfile = ""; // 压缩包文件名
	private String medium_serial = "";// 介质编号
	private Integer page_num = null; // 页数
	private String source = ""; // 来源
	private Integer file_type = null; // 类型（1为文件,2为光盘）
	private String scope = ""; // 载体归属,PERSON个人文件,DEPT部门文件
	private String update = "N";
	private EnterEvent event = null;
	private String scope_dept_id = "";
	private List<SecDept> deptAdminList;
	private String period = "L";
	private Integer enter_num = 1;
	private String src_code = ""; // 原始光盘编号
	private String confidential_num = "";

	public String getConfidential_num() {
		return confidential_num;
	}

	public void setConfidential_num(String confidential_num) {
		this.confidential_num = confidential_num;
	}

	public String getSrc_code() {
		return src_code;
	}

	public void setSrc_code(String src_code) {
		this.src_code = src_code;
	}

	public Integer getEnter_num() {
		return enter_num;
	}

	public void setEnter_num(Integer enter_num) {
		this.enter_num = enter_num;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
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

	public void setPage_num(Integer page_num) {
		this.page_num = page_num;
	}

	public void setEvent(EnterEvent event) {
		this.event = event;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setFile_type(Integer file_type) {
		this.file_type = file_type;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public EnterEvent getEvent() {
		return event;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getImportSecLevelByUser(getCurUser().getUser_iidd());
	}

	public List<SecDept> getDeptAdminList() {
		return deptAdminList;
	}

	public String getScope_dept_id() {
		return scope_dept_id;
	}

	public void setScope_dept_id(String scope_dept_id) {
		this.scope_dept_id = scope_dept_id;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(medium_serial.trim())) {
			boolean is_used = enterService.checkBarcodeIsUsedByOthers(Integer.valueOf(file_type), medium_serial,
					event_code);
			if (is_used) {
				throw new Exception("您输入的条码已存在,请更换");
			}
		}
		if (StringUtils.hasLength(event_code)) {
			event = enterService.getEnterEventByEnterCode(event_code);
			if (event != null) {
				if (scope.equals("PERSON")) {
					if (file_type == 1) {// 个人文件
						if (update.equals("Y")) {
							event.setSeclv_code(seclv_code);
							event.setSumm(summ);
							event.setZipfile(zipfile);
							event.setMedium_serial(medium_serial);
							event.setPage_num(page_num);
							event.setSource(source);
							event.setPeriod(period);
							event.setEnter_num(enter_num);
							event.setSrc_code(src_code);
							event.setConfidential_num(confidential_num);
							enterService.updateEnterEvent(event);
							insertCommonLog("修改个人文件录入申请[" + event_code + "]");
							// return "ok";
							return "personalpaper";
						} else
							return "personalpaper";
					} else {// 个人光盘
						if (update.equals("Y")) {
							event.setSeclv_code(seclv_code);
							event.setSumm(summ);
							event.setZipfile(zipfile);
							event.setMedium_serial(medium_serial);
							event.setSource(source);
							event.setPeriod(period);
							event.setSrc_code(src_code);
							event.setConfidential_num(confidential_num);
							enterService.updateEnterEvent(event);
							insertCommonLog("修改个人光盘录入申请[" + event_code + "]");
							// return "ok";
							return "personalcd";
						} else
							return "personalcd";
					}
				} else {
					if (file_type == 1) {// 部门文件
						// String web_url = getModuleName().toLowerCase() + "/" + getTitleName().toLowerCase() +
						// ".action";
						String web_url = "enter/adddeptpaperenterevent.action";
						deptAdminList = basicService.getDeptAdminList(getCurUser().getUser_iidd(), web_url);
						if (deptAdminList == null || deptAdminList.size() == 0) {
							throw new Exception("没有配置管理部门,请联系系统管理员进行配置");
						}
						if (update.equals("Y")) {
							event.setSeclv_code(seclv_code);
							event.setSumm(summ);
							event.setZipfile(zipfile);
							event.setMedium_serial(medium_serial);
							event.setPage_num(page_num);
							event.setSource(source);
							event.setScope_dept_id(scope_dept_id);
							event.setEnter_num(enter_num);
							event.setSrc_code(src_code);
							event.setConfidential_num(confidential_num);
							enterService.updateEnterEvent(event);
							insertCommonLog("修改部门文件录入申请[" + event_code + "]");
							// return "ok";
							return "deptpaper";
						} else
							return "deptpaper";
					} else {// 部门光盘
							// String web_url = getModuleName().toLowerCase() + "/" + getTitleName().toLowerCase() +
							// ".action";
						String web_url = "enter/adddeptcdenterevent.action";
						deptAdminList = basicService.getDeptAdminList(getCurUser().getUser_iidd(), web_url);
						if (deptAdminList == null || deptAdminList.size() == 0) {
							throw new Exception("没有配置管理部门,请联系系统管理员进行配置");
						}
						if (update.equals("Y")) {
							event.setSeclv_code(seclv_code);
							event.setSumm(summ);
							event.setZipfile(zipfile);
							event.setMedium_serial(medium_serial);
							event.setSource(source);
							event.setScope_dept_id(scope_dept_id);
							event.setSrc_code(src_code);
							event.setConfidential_num(confidential_num);
							enterService.updateEnterEvent(event);
							insertCommonLog("修改部门光盘录入申请[" + event_code + "]");
							// return "ok";
							return "deptcd";
						} else
							return "deptcd";
					}
				}
			} else
				throw new Exception("申请信息为空，请重新尝试。");
		} else
			throw new Exception("作业流水号为空，请重新尝试。");
	}
}
