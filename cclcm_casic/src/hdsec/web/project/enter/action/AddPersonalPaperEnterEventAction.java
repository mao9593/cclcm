package hdsec.web.project.enter.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.enter.model.EnterEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 添加个人文件录入申请
 * 
 * @author lixiang
 * 
 */
public class AddPersonalPaperEnterEventAction extends EnterBaseAction {
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
	private Integer file_type = 1; // 类型（1为文件,2为光盘）
	private List<EnterEvent> eventList = null;
	private final String jobType = JobTypeEnum.LEADIN.getJobTypeCode();
	private String period = "";
	private String file_kind = "NORMAL";// 文件说明（NORMAL普通文件，BOOK科研工作手册）
	private Integer enter_num = 1; // 份数
	private String file_num = "";// 文号
	private String paper_barcode = "";// 条码
	private String company = "";// 单位
	private String publish_type = "";// 公文种类或刊物名称
	private String fileid = "";// 文号-所发XXX号文
	private String company_send = "";// 主送单位
	private String urgency_lv = "";// 紧急程度（公文分无、急件、特级，电报分无、平急、加急、特急、特提）
	private String filed_date = null;// 公文成文日期或刊物编印日期
	private String publish_limits = "";// 发布层次（分为省军级、市地级、县团级、公开发布）
	private String create_type = "";// 来源（分为打印、复印、刻录、外部接收、历史数据）
	private String create_user_name = "";// 制作人姓名
	private String is_scanned = "";// 判断是否有条码标志位
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

	public String getPaper_barcode() {
		return paper_barcode;
	}

	public void setPaper_barcode(String paper_barcode) {
		this.paper_barcode = paper_barcode;
	}

	public String getIs_scanned() {
		return is_scanned;
	}

	public void setIs_scanned(String is_scanned) {
		this.is_scanned = is_scanned;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPublish_type() {
		return publish_type;
	}

	public void setPublish_type(String publish_type) {
		this.publish_type = publish_type;
	}

	public String getFile_num() {
		return file_num;
	}

	public void setFile_num(String file_num) {
		this.file_num = file_num;
	}

	public String getFileid() {
		return fileid;
	}

	public void setFileid(String fileid) {
		this.fileid = fileid;
	}

	public String getCompany_send() {
		return company_send;
	}

	public void setCompany_send(String company_send) {
		this.company_send = company_send;
	}

	public String getUrgency_lv() {
		return urgency_lv;
	}

	public void setUrgency_lv(String urgency_lv) {
		this.urgency_lv = urgency_lv;
	}

	public String getPublish_limits() {
		return publish_limits;
	}

	public void setPublish_limits(String publish_limits) {
		this.publish_limits = publish_limits;
	}

	public String getFiled_date() {
		return filed_date;
	}

	public void setFiled_date(String filed_date) {
		this.filed_date = filed_date;
	}

	public String getCreate_type() {
		return create_type;
	}

	public void setCreate_type(String create_type) {
		this.create_type = create_type;
	}

	public String getCreate_user_name() {
		return create_user_name;
	}

	public void setCreate_user_name(String create_user_name) {
		this.create_user_name = create_user_name;
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
					period, paper_barcode, company, publish_type, fileid, company_send, urgency_lv, filed_date,
					publish_limits, create_type, create_user_name, src_code);
			event.setFile_kind(file_kind);
			event.setEnter_num(enter_num);
			event.setFile_num(file_num);
			event.setProject_code(is_scanned);
			event.setConfidential_num(confidential_num);
			enterService.addEnterEvent(event);
			insertCommonLog("添加录入作业[" + event_code + "]");
		}
		event_code = getCurUser().getUser_iidd() + System.currentTimeMillis();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("submitable", true);
		map.put("import_status", "0");
		map.put("file_type", "1");
		map.put("scope", "PERSON");
		eventList = enterService.getEnterEventList(map);
		return SUCCESS;
	}
}
