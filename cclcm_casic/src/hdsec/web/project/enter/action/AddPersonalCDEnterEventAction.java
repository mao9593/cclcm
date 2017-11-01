package hdsec.web.project.enter.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.enter.model.EnterEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 添加个人光盘录入申请
 * 
 * @author lixiang
 * 
 */
public class AddPersonalCDEnterEventAction extends EnterBaseAction {
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
	private Integer file_type = 2; // 类型（1为文件,2为光盘）
	private List<EnterEvent> eventList = null;
	private final String jobType = JobTypeEnum.LEADIN.getJobTypeCode();
	private String period = "";
	private String src_code = "";
	private String confidential_num = ""; // 机要号

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

	public List<EnterEvent> getEventList() {
		return eventList;
	}

	public String getJobType() {
		return jobType;
	}

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

	public List<SecLevel> getSeclvList() {
		return userService.getImportSecLevelByUser(getCurUser().getUser_iidd());
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
			boolean is_used = enterService.checkBarcodeIsUsed(Integer.valueOf(file_type), medium_serial);
			if (is_used) {
				throw new Exception("您输入的条码已存在,请更换");
			}
		}
		if (StringUtils.hasLength(event_code)) {
			String user_iidd = getCurUser().getUser_iidd();
			String dept_id = getCurUser().getDept_id();
			EnterEvent event = new EnterEvent(user_iidd, dept_id, event_code, seclv_code, usage_code, project_code,
					summ, zipfile, medium_serial, page_num, source, place, import_status, file_type, "PERSON", "",
					period, src_code);
			event.setConfidential_num(confidential_num);
			enterService.addEnterEvent(event);
			insertCommonLog("添加录入作业[" + event_code + "]");
		}
		event_code = getCurUser().getUser_iidd() + System.currentTimeMillis();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("submitable", true);
		map.put("import_status", '0');
		map.put("file_type", '2');
		map.put("scope", "PERSON");
		eventList = enterService.getEnterEventList(map);
		return SUCCESS;
	}
}
