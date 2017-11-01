package hdsec.web.project.enter.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.enter.model.EnterEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 添加扫描录入申请
 * 
 * @author gaoximin 2014-10-15
 */
public class AddScanEventAction extends EnterBaseAction {
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
	private Integer file_type = 3; // 类型（1为文件,2为光盘,3电子文档）
	private List<EnterEvent> eventList = null;
	private final String jobType = JobTypeEnum.SCAN_LEADIN.getJobTypeCode();
	private String period = "L";
	private String file_kind = "NORMAL";// 文件说明（NORMAL普通文件，BOOK科研工作手册）
	private Integer enter_num = 1; // 份数
	private String paper_event_code = "";// 从录入申请记录中传递的文件录入event_code
	private EnterEvent paper_event = null;// 根据传递的event_code查询作业信息
	private String src_code = "";

	public String getSrc_code() {
		return src_code;
	}

	public void setSrc_code(String src_code) {
		this.src_code = src_code;
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

	public List<EnterEvent> getEventList() {
		return eventList;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getFile_kind() {
		return file_kind;
	}

	public void setFile_kind(String file_kind) {
		this.file_kind = file_kind;
	}

	public Integer getEnter_num() {
		return enter_num;
	}

	public void setEnter_num(Integer enter_num) {
		this.enter_num = enter_num;
	}

	public String getPaper_event_code() {
		return paper_event_code;
	}

	public void setPaper_event_code(String paper_event_code) {
		this.paper_event_code = paper_event_code;
	}

	public EnterEvent getPaper_event() {
		return paper_event;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_code)) {
			String user_iidd = getCurUser().getUser_iidd();
			String dept_id = getCurUser().getDept_id();
			EnterEvent event = new EnterEvent(user_iidd, dept_id, event_code, seclv_code, usage_code, project_code,
					summ, zipfile, medium_serial, page_num, source, place, import_status, file_type, "PERSON", "",
					period, src_code);
			event.setFile_kind(file_kind);
			event.setEnter_num(enter_num);
			enterService.addEnterEvent(event);
			insertCommonLog("添加扫描录入作业[" + event_code + "]");
		}
		if (StringUtils.hasLength(paper_event_code)) {
			paper_event = enterService.getEnterEventByEnterCode(paper_event_code);
		}
		event_code = getCurUser().getUser_iidd() + System.currentTimeMillis();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("submitable", true);
		map.put("import_status", "0");
		map.put("file_type", "3");
		map.put("scope", "PERSON");
		eventList = enterService.getEnterEventList(map);
		return SUCCESS;
	}
}
