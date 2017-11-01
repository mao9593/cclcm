package hdsec.web.project.enter.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.BaseEvent;

import java.util.Date;

/**
 * 录入作业模块
 * 
 * @author gaoxm
 * 
 */
public class EnterEvent extends BaseEvent {
	private String zipfile = ""; // 介质名称
	private String medium_serial = "";// 介质编号
	private Integer page_num = null; // 页数
	private String source = ""; // 来源
	private String place = ""; // 地点
	private Integer import_status = 0; // 录入状态(0未录入,1已录入)
	private Integer file_type = null; // 类型（1为文件,2为光盘,3电子文档）
	private String scope = "PERSON"; // 载体归属,PERSON个人文件,DEPT部门文件，默认PERSON
	private String scope_dept_id = "";
	private String scope_dept_name = ""; // 介质名称
	private String period = "L";// 使用状态（L 长期留用，S 短期留用）
	private String file_kind = "NORMAL";// 文件说明（NORMAL普通文件，BOOK科研工作手册）
	private Integer enter_num = 1; // 份数
	private Date finish_time = null;// 完成时间
	private String import_user_name = "";
	private String import_user_iidd = "";
	private String file_num = null;
	/* by gym */
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
	private String src_code = "";
	/* by haojia */
	private String job_code = "";
	private String confidential_num = ""; // 机要号

	public String getConfidential_num() {
		return confidential_num;
	}

	public void setConfidential_num(String confidential_num) {
		this.confidential_num = confidential_num;
	}

	@Override
	public String getJob_code() {
		return job_code;
	}

	@Override
	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public String getSrc_code() {
		return src_code;
	}

	public void setSrc_code(String src_code) {
		this.src_code = src_code;
	}

	public String getCreate_user_name() {
		return create_user_name;
	}

	public String getFile_num() {
		return file_num;
	}

	public void setFile_num(String file_num) {
		this.file_num = file_num;
	}

	public void setCreate_user_name(String create_user_name) {
		this.create_user_name = create_user_name;
	}

	public String getCreate_type() {
		return create_type;
	}

	public void setCreate_type(String create_type) {
		this.create_type = create_type;
	}

	public String getPublish_limits() {
		return publish_limits;
	}

	public void setPublish_limits(String publish_limits) {
		this.publish_limits = publish_limits;
	}

	public String getUrgency_lv() {
		return urgency_lv;
	}

	public void setUrgency_lv(String urgency_lv) {
		this.urgency_lv = urgency_lv;
	}

	public String getFiled_date() {
		return filed_date;
	}

	public void setFiled_date(String filed_date) {
		this.filed_date = filed_date;
	}

	public String getCompany_send() {
		return company_send;
	}

	public void setCompany_send(String company_send) {
		this.company_send = company_send;
	}

	public String getFileid() {
		return fileid;
	}

	public void setFileid(String fileid) {
		this.fileid = fileid;
	}

	public String getPublish_type() {
		return publish_type;
	}

	public void setPublish_type(String publish_type) {
		this.publish_type = publish_type;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPaper_barcode() {
		return paper_barcode;
	}

	public void setPaper_barcode(String paper_barcode) {
		this.paper_barcode = paper_barcode;
	}

	public String getZipfile() {
		return zipfile;
	}

	public void setZipfile(String zipfile) {
		this.zipfile = zipfile;
	}

	public String getMedium_serial() {
		return medium_serial;
	}

	public void setMedium_serial(String medium_serial) {
		this.medium_serial = medium_serial;
	}

	public Integer getPage_num() {
		return page_num;
	}

	public void setPage_num(Integer page_num) {
		this.page_num = page_num;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Integer getImport_status() {
		return import_status;
	}

	public String getImport_status_name() {
		return import_status == 1 ? "已录入" : "未录入";
	}

	public void setImport_status(Integer import_status) {
		this.import_status = import_status;
	}

	public Integer getFile_type() {
		return file_type;
	}

	public String getFile_type_name() {
		String file_type_name = "";
		if (file_type == 1) {
			file_type_name = "文件";
		} else if (file_type == 2) {
			file_type_name = "光盘";
		} else if (file_type == 3) {
			file_type_name = "电子文档";
		} else if (file_type == 4) {
			file_type_name = "磁介质";
		}
		return file_type_name;
	}

	public void setFile_type(Integer file_type) {
		this.file_type = file_type;
	}

	public String getScope() {
		return scope;
	}

	public String getScope_name() {
		return scope.equals("PERSON") ? "个人" : "部门";
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public EnterEvent() {
		super(JobTypeEnum.LEADIN);
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

	public String getPeriod() {
		return period;
	}

	public String getPeriod_name() {
		String period_name = "";
		period_name = period.equals("L") ? "长期留用" : "短期留用";
		return period_name;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getFile_kind() {
		return file_kind;
	}

	public String getFile_kind_name() {
		String file_kind_name = "";
		if (file_kind.equals("NORMAL")) {
			file_kind_name = "普通文件";
		} else if (file_kind.equals("BOOK")) {
			file_kind_name = "科研工作手册";
		}
		return file_kind_name;
	}

	public void setImport_user_name(String import_user_name) {
		this.import_user_name = import_user_name;
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

	public String getFinish_time() {
		return getSdf().format(finish_time);
	}

	public void setFinish_time(Date finish_time) {
		this.finish_time = finish_time;
	}

	public String getImport_user_name() {
		return import_user_name;
	}

	public String getImport_user_iidd() {
		return import_user_iidd;
	}

	public void setImport_user_iidd(String import_user_iidd) {
		this.import_user_iidd = import_user_iidd;
	}

	public EnterEvent(String user_iidd, String dept_id, String event_code, Integer seclv_code, String usage_code,
			String project_code, String summ, String zipfile, String medium_serial, Integer page_num, String source,
			String place, Integer import_status, Integer file_type, String scope, String scope_dept_id) {
		super(JobTypeEnum.LEADIN, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);

		this.zipfile = zipfile;
		this.medium_serial = medium_serial;
		this.page_num = page_num;
		this.source = source;
		this.place = place;
		this.import_status = import_status;
		this.file_type = file_type;
		this.scope = scope;
		this.scope_dept_id = scope_dept_id;
	}

	public EnterEvent(String user_iidd, String dept_id, String event_code, Integer seclv_code, String usage_code,
			String project_code, String summ, String zipfile, String medium_serial, Integer page_num, String source,
			String place, Integer import_status, Integer file_type, String scope, String scope_dept_id, String period,
			String src_code) {
		super(JobTypeEnum.LEADIN, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);

		this.zipfile = zipfile;
		this.medium_serial = medium_serial;
		this.page_num = page_num;
		this.source = source;
		this.place = place;
		this.import_status = import_status;
		this.file_type = file_type;
		this.scope = scope;
		this.scope_dept_id = scope_dept_id;
		this.period = period;
		this.src_code = src_code;
	}

	public EnterEvent(String user_iidd, String dept_id, String event_code, Integer seclv_code, String usage_code,
			String project_code, String summ, String zipfile, String medium_serial, Integer page_num, String source,
			String place, Integer import_status, Integer file_type, String scope, String scope_dept_id, String period,
			String paper_barcode, String company, String publish_type, String fileid, String company_send,
			String urgency_lv, String filed_date, String publish_limits, String create_type, String create_user_name,
			String src_code) {
		super(JobTypeEnum.LEADIN, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);
		this.zipfile = zipfile;
		this.medium_serial = medium_serial;
		this.page_num = page_num;
		this.source = source;
		this.place = place;
		this.import_status = import_status;
		this.file_type = file_type;
		this.scope = scope;
		this.scope_dept_id = scope_dept_id;
		this.period = period;
		this.paper_barcode = paper_barcode;
		this.company = company;
		this.publish_type = publish_type;
		this.fileid = fileid;
		this.company_send = company_send;
		this.urgency_lv = urgency_lv;
		this.filed_date = filed_date;
		this.publish_limits = publish_limits;
		this.create_type = create_type;
		this.create_user_name = create_user_name;
		this.src_code = src_code;
	}
}
