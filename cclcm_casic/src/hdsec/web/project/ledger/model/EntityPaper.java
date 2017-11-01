package hdsec.web.project.ledger.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.util.ActivitiCons;
import hdsec.web.project.common.BaseDomain;
import hdsec.web.project.common.util.TimeUtil;

import java.util.Date;

public class EntityPaper extends BaseDomain {
	private Integer paper_id;// ID SEQ
	private String paper_barcode;// 载体条码
	private String event_code;// 作业ID
	private String user_iidd;// 制作人ID
	private String user_name;// 制作人姓名
	private String dept_id;// 制作人部门ID
	private String dept_name;// 制作人部门名称
	private String duty_user_iidd;// 责任人ID
	private String duty_user_name;// 责任人姓名
	private String duty_dept_id;// 责任人部门名称
	private String duty_dept_name;// 责任人部门名称
	private Integer seclv_code;// 密级代号
	private String seclv_name;// 密级名称
	private Date print_time;// 打印时间
	private String is_reprint;// 是否补打
	private String print_result;// 打印结果
	private String file_title;// 原文件名
	private String project_code;// 所属项目
	private String project_name;// 所属项目
	private String fileID;// 文号
	private Integer page_count;// 页数
	private String page_size;// 纸张大小
	private Integer color;
	private Integer print_direct;// 纵横向
	private Integer print_double;// 单双面
	private Integer paper_state;// 介质状态
	private String paper_state_name;// 介质状态名称
	private String printer_code;// 打印机代码
	private String printer_name;// 打印机名称
	private Date retrieve_time;// 回收时间
	private Integer retrieve_type;// 回收方式
	private String retrieve_user_iidd;// 回收人ID
	private String retrieve_box_code;// 回收柜标示
	private Date destroy_time;// 销毁时间
	private String destroy_user_iidd;// 销毁人ID
	private String job_code;// 流转号
	private JobTypeEnum jobType = null;
	private String jobtype_code;
	private String job_status = "";
	private String retrieve_user_name;
	private String destroy_user_name;
	private String create_type;// 产生类型:PRINT打印COPY复印OUTCOPY外来复印LEADIN录入SPECIAL特殊打印MERGE_ENTITY台账合并，默认PRINT
	private String scope;// 载体归属PERSON个人文件DEPT部门文件，默认PERSON
	private String scope_name;
	private String scope_dept_id;// 载体归属部门ID
	private String scope_dept_name;// 载体归属部门名称
	private String applyeventcode;// 借用作业编号
	private String applyuserid;// 借用申请人ID
	private String applyusername;// 借用申请人
	private String applydeptid;// 借用申请部门ID
	private String applydeptname;// 借用申请部门
	private String borrow_status;// 借用状态
	private String send_num;// 回执单号
	private String output_user_name;// 外发接收人
	private String output_dept_name;// 外发接收部门
	private String fail_remark;// 打印失败备注
	private String retrieve_box_name;// 回收柜名称
	private String keyword_content = "";// 关键字
	private Date expire_time = null;// 到期回收提醒时间
	private Integer expire_status = null;
	private Integer delay_days = null;
	private String PID_barcode = "";// 主台账条码信息
	private String PID_pagenum = "";// 主台账中被替换的页码
	private String retrieve_pagenum = "";// 已回收的页
	private String retrieve_replace = "";// 被替换页回收情况
	private String destroy_pagenum = "";// 已销毁的页
	private int modify_num;// 密级变更数量
	private int pre_seclv;
	private int trg_seclv;
	private String barcode;
	private Date start_time;// 申请销毁时间
	private int secret_book;// 保密手册
	private String paper_barcode_td;// 载体二维条码
	private String company = "";// 单位
	private String publish_type = "";// 公文种类或刊物名称
	private String company_send = "";// 主送单位
	private String urgency_lv = "";// 紧急程度（公文分无、急件、特级，电报分无、平急、加急、特急、特提）
	private String filed_date = null;// 公文成文日期或刊物编印日期
	private String publish_limits = "";// 发布层次（分为省军级、市地级、县团级、公开发布）
	private String summ = "";// 备注
	private String send_id = "";// 外发号
	private String send_mode = "";// 外发方式
	private String box_num = "";// 盒号
	private String file_order_num = "";// 文件顺序号
	private String manage_opinion = "";// 处理意见
	private String receive_id = "";// 集团收文编号
	private String remark = "";// 备注1
	private String cyc_remarks; // 闭环备注
	private String supervise_user_iidd = "";// 监销人id
	private String accept_user_iidd = "";// 接收人信息
	private String src_code = "";// 录入的文件：原文件编号
	private String confidential_num = ""; // 机要号
	private String output_undertaker = ""; // 外发承办人(id)
	private String output_undertaker_name = ""; // 外发承办人名字
	private String carryout_user_names = ""; // 携带人名(外发或外带时1-多个携带人)
	private String send_way = ""; // 外发方式 0:专人携带; 1:发机要
	private String output_confidential_num = ""; // 外发机要号
	private String merge_code = ""; // 台账合并-条码关联
	private String merge_state = ""; // 台账合并--台账状态：0默认正常台账，1被合并台账；2合并新台账(台账展示处理 非1)
	private String mergecode_print = ""; // 台账合并-新台账是否打印.默认0，已打印1

	public String getMergecode_print() {
		return mergecode_print;
	}

	public void setMergecode_print(String mergecode_print) {
		this.mergecode_print = mergecode_print;
	}

	public String getMerge_code() {
		return merge_code;
	}

	public void setMerge_code(String merge_code) {
		this.merge_code = merge_code;
	}

	public String getMerge_state() {
		return merge_state;
	}

	public void setMerge_state(String merge_state) {
		this.merge_state = merge_state;
	}

	public String getOutput_confidential_num() {
		return output_confidential_num;
	}

	public void setOutput_confidential_num(String output_confidential_num) {
		this.output_confidential_num = output_confidential_num;
	}

	public String getSend_way() {
		return send_way;
	}

	public void setSend_way(String send_way) {
		this.send_way = send_way;
	}

	public String getSend_way_str() {
		String send_way_str = "";
		if (send_way.equals("0")) {
			send_way_str = "专人携带";
		} else if (send_way.equals("1")) {
			send_way_str = "发机要";
		} else {
			send_way_str = "";
		}
		return send_way_str;
	}

	public String getOutput_undertaker_name() {
		return output_undertaker_name;
	}

	public void setOutput_undertaker_name(String output_undertaker_name) {
		this.output_undertaker_name = output_undertaker_name;
	}

	public String getOutput_undertaker() {
		return output_undertaker;
	}

	public void setOutput_undertaker(String output_undertaker) {
		this.output_undertaker = output_undertaker;
	}

	public String getCarryout_user_names() {
		return carryout_user_names;
	}

	public void setCarryout_user_names(String carryout_user_names) {
		this.carryout_user_names = carryout_user_names;
	}

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

	public String getAccept_user_iidd() {
		return accept_user_iidd;
	}

	public void setAccept_user_iidd(String accept_user_iidd) {
		this.accept_user_iidd = accept_user_iidd;
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

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public String getSend_id() {
		return send_id;
	}

	public void setSend_id(String send_id) {
		this.send_id = send_id;
	}

	public String getSend_mode() {
		return send_mode;
	}

	public void setSend_mode(String send_mode) {
		this.send_mode = send_mode;
	}

	public String getBox_num() {
		return box_num;
	}

	public void setBox_num(String box_num) {
		this.box_num = box_num;
	}

	public String getFile_order_num() {
		return file_order_num;
	}

	public void setFile_order_num(String file_order_num) {
		this.file_order_num = file_order_num;
	}

	public String getManage_opinion() {
		return manage_opinion;
	}

	public void setManage_opinion(String manage_opinion) {
		this.manage_opinion = manage_opinion;
	}

	public String getReceive_id() {
		return receive_id;
	}

	public void setReceive_id(String receive_id) {
		this.receive_id = receive_id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCyc_remarks() {
		return cyc_remarks;
	}

	public void setCyc_remarks(String cyc_remarks) {
		this.cyc_remarks = cyc_remarks;
	}

	public String getPaper_barcode_td() {
		return paper_barcode_td;
	}

	public void setPaper_barcode_td(String paper_barcode_td) {
		this.paper_barcode_td = paper_barcode_td;
	}

	public int getSecret_book() {
		return secret_book;
	}

	public void setSecret_book(int secret_book) {
		this.secret_book = secret_book;
	}

	public String getStart_time() {
		return getSdf().format(start_time);
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public int getModify_num() {
		return modify_num;
	}

	public void setModify_num(int modify_num) {
		this.modify_num = modify_num;
	}

	public int getPre_seclv() {
		return pre_seclv;
	}

	public void setPre_seclv(int pre_seclv) {
		this.pre_seclv = pre_seclv;
	}

	public int getTrg_seclv() {
		return trg_seclv;
	}

	public void setTrg_seclv(int trg_seclv) {
		this.trg_seclv = trg_seclv;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Integer getPaper_id() {
		return paper_id;
	}

	public void setPaper_id(Integer paper_id) {
		this.paper_id = paper_id;
	}

	public String getPaper_barcode() {
		return paper_barcode;
	}

	public void setPaper_barcode(String paper_barcode) {
		this.paper_barcode = paper_barcode;
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

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getSeclv_name() {
		return seclv_name;
	}

	public void setSeclv_name(String seclv_name) {
		this.seclv_name = seclv_name;
	}

	public String getPrint_time() {
		if (null == print_time) {
			return "";
		}
		return getSdf().format(print_time);
	}

	public void setPrint_time(Date print_time) {
		this.print_time = print_time;
	}

	public String getIs_reprint() {
		return is_reprint;
	}

	public void setIs_reprint(String is_reprint) {
		this.is_reprint = is_reprint;
	}

	public String getPrint_result() {
		return print_result;
	}

	public void setPrint_result(String print_result) {
		this.print_result = print_result;
	}

	public String getFile_title() {
		return file_title;
	}

	public void setFile_title(String file_title) {
		this.file_title = file_title;
	}

	public String getProject_code() {
		return project_code;
	}

	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getFileID() {
		return fileID;
	}

	public void setFileID(String fileID) {
		this.fileID = fileID;
	}

	public Integer getPage_count() {
		return page_count;
	}

	public void setPage_count(Integer page_count) {
		this.page_count = page_count;
	}

	public String getPage_size() {
		return page_size;
	}

	public void setPage_size(String page_size) {
		this.page_size = page_size;
	}

	public Integer getColor() {
		return color;
	}

	public void setColor(Integer color) {
		this.color = color;
	}

	public Integer getPrint_direct() {
		return print_direct;
	}

	public void setPrint_direct(Integer print_direct) {
		this.print_direct = print_direct;
	}

	public Integer getPrint_double() {
		return print_double;
	}

	public void setPrint_double(Integer print_double) {
		this.print_double = print_double;
	}

	public Integer getPaper_state() {
		return paper_state;
	}

	public void setPaper_state(Integer paper_state) {
		this.paper_state = paper_state;
	}

	public String getPrinter_code() {
		return printer_code;
	}

	public void setPrinter_code(String printer_code) {
		this.printer_code = printer_code;
	}

	public String getPrinter_name() {
		return printer_name;
	}

	public void setPrinter_name(String printer_name) {
		this.printer_name = printer_name;
	}

	public String getRetrieve_time() {
		if (null == retrieve_time) {
			return "";
		}
		return getSdf().format(retrieve_time);
	}

	public void setRetrieve_time(Date retrieve_time) {
		this.retrieve_time = retrieve_time;
	}

	public Integer getRetrieve_type() {
		return retrieve_type;
	}

	public void setRetrieve_type(Integer retrieve_type) {
		this.retrieve_type = retrieve_type;
	}

	public String getRetrieve_user_iidd() {
		return retrieve_user_iidd;
	}

	public void setRetrieve_user_iidd(String retrieve_user_iidd) {
		this.retrieve_user_iidd = retrieve_user_iidd;
	}

	public String getRetrieve_box_code() {
		return retrieve_box_code;
	}

	public void setRetrieve_box_code(String retrieve_box_code) {
		this.retrieve_box_code = retrieve_box_code;
	}

	public String getDestroy_time() {
		if (null == destroy_time) {
			return "";
		}
		return getSdf().format(destroy_time);
	}

	public void setDestroy_time(Date destroy_time) {
		this.destroy_time = destroy_time;
	}

	public String getDestroy_user_iidd() {
		return destroy_user_iidd;
	}

	public void setDestroy_user_iidd(String destroy_user_iidd) {
		this.destroy_user_iidd = destroy_user_iidd;
	}

	public String getJob_code() {
		return job_code;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public JobTypeEnum getJobType() {
		return jobType;
	}

	public void setJobType(JobTypeEnum jobType) {
		this.jobType = jobType;
	}

	/*
	 * public void setJobType_code(String jobType_code) { this.jobType = JobTypeEnum.valueOf(jobType_code); }
	 */

	public String getJob_status() {
		return job_status;
	}

	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}

	public Integer getDelay_days() {
		return delay_days;
	}

	public void setDelay_days(Integer delay_days) {
		this.delay_days = delay_days;
	}

	public String getJob_status_name() {
		String name = "";
		switch (this.job_status) {
		case ActivitiCons.APPLY_APPROVED_DEFAULT:
			name = "待审批";
			break;
		case ActivitiCons.APPLY_APPROVED_UNDER:
			name = "审批中";
			break;
		case ActivitiCons.APPLY_APPROVED_PASS:
			name = "已通过";
			break;
		case ActivitiCons.APPLY_APPROVED_REJECT:
			name = "已驳回";
			break;
		case ActivitiCons.APPLY_APPROVED_END:
			name = "已关闭";
			break;
		default:
			name = "未申请";
			break;
		}
		return name;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getPaper_state_name() {
		if (paper_state == null) {
			return "未知";
		}
		if (EntityStateEnum.isStateAvailabel(paper_state.intValue())) {
			return EntityStateEnum.valueOf("STATE" + paper_state).getName();
		} else {
			return "未知";
		}
	}

	public void setPaper_state_name(String paper_state_name) {
		this.paper_state_name = paper_state_name;
	}

	public String getPrint_result_name() {
		if (null == print_result || "0".equals(print_result)) {
			return "失败";
		} else {
			return "成功";
		}
	}

	public String getIs_reprint_name() {
		if (null == is_reprint || "0".equals(is_reprint)) {
			return "否";
		} else {
			return "是";
		}
	}

	public String getPrint_direct_name() {
		if (print_direct == null) {
			return "未知";
		}
		switch (print_direct) {
		case 1:
			return "纵向";
		case 2:
			return "横向";
		default:
			return "未知";
		}
	}

	public String getPrint_double_name() {
		if (print_double == null) {
			return "未知";
		}
		switch (print_double) {
		case 1:
			return "单面";
		case 2:
			return "双面左右翻";
		case 3:
			return "双面上下翻";
		default:
			return "未知";
		}
	}

	public String getColor_name() {
		if (color == null) {
			return "未知";
		}
		switch (color) {
		case 1:
			return "黑白";
		case 2:
			return "彩色";
		default:
			return "未知";
		}
	}

	public String getRetrieve_type_name() {
		if (retrieve_type == null) {
			return "未知";
		}
		switch (retrieve_type) {
		case 0:
			return "回收柜";
		case 1:
			return "人工";
		default:
			return "未知";
		}
	}

	public String getRetrieve_user_name() {
		return retrieve_user_name;
	}

	public void setRetrieve_user_name(String retrieve_user_name) {
		this.retrieve_user_name = retrieve_user_name;
	}

	public String getDestroy_user_name() {
		return destroy_user_name;
	}

	public void setDestroy_user_name(String destroy_user_name) {
		this.destroy_user_name = destroy_user_name;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getCreate_type() {
		return create_type;
	}

	public void setCreate_type(String create_type) {
		this.create_type = create_type;
	}

	public String getCreate_type_name() {
		if (create_type != null && create_type.equals("COPY")) {
			return "复印";
		} else if (create_type != null && create_type.equals("OUTCOPY")) {
			return "外来文件复印";
		} else if (create_type != null && create_type.equals("LEADIN")) {
			return "录入";
		} else if (create_type != null && create_type.equals("REPLACEPRINT")) {
			return "被替换页";
		} else if (create_type != null && create_type.equals("SPECIAL")) {
			return "特殊打印";
		} else if (create_type != null && create_type.equals("MERGECOPY")) {
			return "合并复印";
		} else if (create_type != null && create_type.equals("MERGE_ENTITY")) {
			return "合并台账";
		} else {
			return "打印";
		}
	}

	public String getScope() {
		return scope;
	}

	public String getScope_name() {
		String scopeName = "";
		if (scope.equals("DEPT")) {
			scopeName = "部门文件";
		} else if (scope.equals("PERSON")) {
			scopeName = "个人文件";
		}
		return scopeName;
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

	public String getApplyeventcode() {
		return applyeventcode;
	}

	public void setApplyeventcode(String applyeventcode) {
		this.applyeventcode = applyeventcode;
	}

	public String getApplyuserid() {
		return applyuserid;
	}

	public void setApplyuserid(String applyuserid) {
		this.applyuserid = applyuserid;
	}

	public String getApplyusername() {
		return applyusername;
	}

	public void setApplyusername(String applyusername) {
		this.applyusername = applyusername;
	}

	public String getApplydeptid() {
		return applydeptid;
	}

	public void setApplydeptid(String applydeptid) {
		this.applydeptid = applydeptid;
	}

	public String getApplydeptname() {
		return applydeptname;
	}

	public void setApplydeptname(String applydeptname) {
		this.applydeptname = applydeptname;
	}

	public String getBorrow_status() {
		return borrow_status;
	}

	public void setBorrow_status(String borrow_status) {
		this.borrow_status = borrow_status;
	}

	public String getSend_num() {
		return send_num;
	}

	public void setSend_num(String send_num) {
		this.send_num = send_num;
	}

	public String getExpire_time() {
		if (null == expire_time) {
			return "";
		}
		return getSdf().format(expire_time);
	}

	public Date getExpire_time_original() {
		return expire_time;
	}

	public void setExpire_time(Date expire_time) {
		this.expire_time = expire_time;
	}

	public EntityPaper() {
		super();
	}

	public EntityPaper(String paper_barcode, String event_code, String user_iidd, String user_name, String dept_id,
			String dept_name, String duty_user_iidd, String duty_user_name, String duty_dept_id, String duty_dept_name,
			Integer seclv_code, String seclv_name, Date print_time, String is_reprint, String print_result,
			String file_title, String project_code, String project_name, String fileID, Integer page_count,
			String page_size, Integer color, Integer print_direct, Integer print_double, Integer paper_state,
			String paper_state_name, String printer_code, String printer_name, Date retrieve_time,
			Integer retrieve_type, String retrieve_user_iidd, String retrieve_box_code, Date destroy_time,
			String destroy_user_iidd, String job_code, JobTypeEnum jobType, String job_status,
			String retrieve_user_name, String destroy_user_name, String create_type, String scope,
			String scope_dept_id, String scope_dept_name) {
		super();
		this.paper_barcode = paper_barcode;
		this.event_code = event_code;
		this.user_iidd = user_iidd;
		this.user_name = user_name;
		this.dept_id = dept_id;
		this.dept_name = dept_name;
		this.duty_user_iidd = duty_user_iidd;
		this.duty_user_name = duty_user_name;
		this.duty_dept_id = duty_dept_id;
		this.duty_dept_name = duty_dept_name;
		this.seclv_code = seclv_code;
		this.seclv_name = seclv_name;
		this.print_time = print_time;
		this.is_reprint = is_reprint;
		this.print_result = print_result;
		this.file_title = file_title;
		this.project_code = project_code;
		this.project_name = project_name;
		this.fileID = fileID;
		this.page_count = page_count;
		this.page_size = page_size;
		this.color = color;
		this.print_direct = print_direct;
		this.print_double = print_double;
		this.paper_state = paper_state;
		this.paper_state_name = paper_state_name;
		this.create_type = create_type;
		this.scope = scope;
		this.scope_dept_id = scope_dept_id;
		this.scope_dept_name = scope_dept_name;
		this.printer_code = printer_code;
		this.printer_name = printer_name;
		this.retrieve_time = retrieve_time;
		this.retrieve_type = retrieve_type;
		this.retrieve_user_iidd = retrieve_user_iidd;
		this.retrieve_box_code = retrieve_box_code;
		this.destroy_time = destroy_time;
		this.destroy_user_iidd = destroy_user_iidd;
		this.job_code = job_code;
		this.jobType = jobType;
		this.job_status = job_status;
		this.retrieve_user_name = retrieve_user_name;
		this.destroy_user_name = destroy_user_name;
	}

	public EntityPaper(String paper_barcode, String event_code, String user_iidd, String user_name, String dept_id,
			String dept_name, String duty_user_iidd, String duty_user_name, String duty_dept_id, String duty_dept_name,
			Integer seclv_code, String seclv_name, Date print_time, String is_reprint, String print_result,
			String file_title, String project_code, String project_name, String fileID, Integer page_count,
			String page_size, Integer color, Integer print_direct, Integer print_double, Integer paper_state,
			String paper_state_name, String printer_code, String printer_name, Date retrieve_time,
			Integer retrieve_type, String retrieve_user_iidd, String retrieve_box_code, Date destroy_time,
			String destroy_user_iidd, String job_code, JobTypeEnum jobType, String job_status,
			String retrieve_user_name, String destroy_user_name, String create_type, String scope,
			String scope_dept_id, String scope_dept_name, String company, String publish_type, String company_send,
			String urgency_lv, String filed_date, String publish_limits, String summ) {
		super();
		this.paper_barcode = paper_barcode;
		this.event_code = event_code;
		this.user_iidd = user_iidd;
		this.user_name = user_name;
		this.dept_id = dept_id;
		this.dept_name = dept_name;
		this.duty_user_iidd = duty_user_iidd;
		this.duty_user_name = duty_user_name;
		this.duty_dept_id = duty_dept_id;
		this.duty_dept_name = duty_dept_name;
		this.seclv_code = seclv_code;
		this.seclv_name = seclv_name;
		this.print_time = print_time;
		this.is_reprint = is_reprint;
		this.print_result = print_result;
		this.file_title = file_title;
		this.project_code = project_code;
		this.project_name = project_name;
		this.fileID = fileID;
		this.page_count = page_count;
		this.page_size = page_size;
		this.color = color;
		this.print_direct = print_direct;
		this.print_double = print_double;
		this.paper_state = paper_state;
		this.paper_state_name = paper_state_name;
		this.create_type = create_type;
		this.scope = scope;
		this.scope_dept_id = scope_dept_id;
		this.scope_dept_name = scope_dept_name;
		this.printer_code = printer_code;
		this.printer_name = printer_name;
		this.retrieve_time = retrieve_time;
		this.retrieve_type = retrieve_type;
		this.retrieve_user_iidd = retrieve_user_iidd;
		this.retrieve_box_code = retrieve_box_code;
		this.destroy_time = destroy_time;
		this.destroy_user_iidd = destroy_user_iidd;
		this.job_code = job_code;
		this.jobType = jobType;
		this.job_status = job_status;
		this.retrieve_user_name = retrieve_user_name;
		this.destroy_user_name = destroy_user_name;
		this.company = company;
		this.publish_type = publish_type;
		this.company_send = company_send;
		this.urgency_lv = urgency_lv;
		this.filed_date = filed_date;
		this.publish_limits = publish_limits;
		this.summ = summ;
	}

	public EntityPaper(Integer paper_id, String paper_barcode, String event_code, String user_iidd, String user_name,
			String dept_id, String dept_name, String duty_user_iidd, String duty_user_name, String duty_dept_id,
			String duty_dept_name, Integer seclv_code, String seclv_name, Date print_time, String is_reprint,
			String print_result, String file_title, String project_code, String project_name, String fileID,
			Integer page_count, String page_size, Integer color, Integer print_direct, Integer print_double,
			Integer paper_state, String paper_state_name, String printer_code, String printer_name, Date retrieve_time,
			Integer retrieve_type, String retrieve_user_iidd, String retrieve_box_code, Date destroy_time,
			String destroy_user_iidd, String job_code, JobTypeEnum jobType, String jobtype_code, String job_status,
			String retrieve_user_name, String destroy_user_name, String create_type, String scope, String scope_name,
			String scope_dept_id, String scope_dept_name, String applyeventcode, String applyuserid,
			String applyusername, String applydeptid, String applydeptname, String borrow_status, String send_num,
			String output_user_name, String output_dept_name, String fail_remark, String retrieve_box_name,
			String keyword_content, Date expire_time, Integer expire_status, String company, String publish_type,
			String company_send, String urgency_lv, String filed_date, String publish_limits, String summ) {
		super();
		this.paper_id = paper_id;
		this.paper_barcode = paper_barcode;
		this.event_code = event_code;
		this.user_iidd = user_iidd;
		this.user_name = user_name;
		this.dept_id = dept_id;
		this.dept_name = dept_name;
		this.duty_user_iidd = duty_user_iidd;
		this.duty_user_name = duty_user_name;
		this.duty_dept_id = duty_dept_id;
		this.duty_dept_name = duty_dept_name;
		this.seclv_code = seclv_code;
		this.seclv_name = seclv_name;
		this.print_time = print_time;
		this.is_reprint = is_reprint;
		this.print_result = print_result;
		this.file_title = file_title;
		this.project_code = project_code;
		this.project_name = project_name;
		this.fileID = fileID;
		this.page_count = page_count;
		this.page_size = page_size;
		this.color = color;
		this.print_direct = print_direct;
		this.print_double = print_double;
		this.paper_state = paper_state;
		this.paper_state_name = paper_state_name;
		this.printer_code = printer_code;
		this.printer_name = printer_name;
		this.retrieve_time = retrieve_time;
		this.retrieve_type = retrieve_type;
		this.retrieve_user_iidd = retrieve_user_iidd;
		this.retrieve_box_code = retrieve_box_code;
		this.destroy_time = destroy_time;
		this.destroy_user_iidd = destroy_user_iidd;
		this.job_code = job_code;
		this.jobType = jobType;
		this.jobtype_code = jobtype_code;
		this.job_status = job_status;
		this.retrieve_user_name = retrieve_user_name;
		this.destroy_user_name = destroy_user_name;
		this.create_type = create_type;
		this.scope = scope;
		this.scope_name = scope_name;
		this.scope_dept_id = scope_dept_id;
		this.scope_dept_name = scope_dept_name;
		this.applyeventcode = applyeventcode;
		this.applyuserid = applyuserid;
		this.applyusername = applyusername;
		this.applydeptid = applydeptid;
		this.applydeptname = applydeptname;
		this.borrow_status = borrow_status;
		this.send_num = send_num;
		this.output_user_name = output_user_name;
		this.output_dept_name = output_dept_name;
		this.fail_remark = fail_remark;
		this.retrieve_box_name = retrieve_box_name;
		this.keyword_content = keyword_content;
		this.expire_time = expire_time;
		this.expire_status = expire_status;
		this.company = company;
		this.publish_type = publish_type;
		this.company_send = company_send;
		this.urgency_lv = urgency_lv;
		this.filed_date = filed_date;
		this.publish_limits = publish_limits;
		this.summ = summ;
	}

	public EntityPaper(Integer paper_id, String paper_barcode, String event_code, String user_iidd, String user_name,
			String dept_id, String dept_name, String duty_user_iidd, String duty_user_name, String duty_dept_id,
			String duty_dept_name, Integer seclv_code, String seclv_name, Date print_time, String is_reprint,
			String print_result, String file_title, String project_code, String project_name, String fileID,
			Integer page_count, String page_size, Integer color, Integer print_direct, Integer print_double,
			Integer paper_state, String paper_state_name, String printer_code, String printer_name, Date retrieve_time,
			Integer retrieve_type, String retrieve_user_iidd, String retrieve_box_code, Date destroy_time,
			String destroy_user_iidd, String job_code, JobTypeEnum jobType, String jobtype_code, String job_status,
			String retrieve_user_name, String destroy_user_name, String create_type, String scope, String scope_name,
			String scope_dept_id, String scope_dept_name, String applyeventcode, String applyuserid,
			String applyusername, String applydeptid, String applydeptname, String borrow_status, String send_num,
			String output_user_name, String output_dept_name, String fail_remark, String retrieve_box_name,
			String keyword_content, Date expire_time, Integer expire_status, String company, String publish_type,
			String company_send, String urgency_lv, String filed_date, String publish_limits, String summ,
			String send_id, String send_mode, String box_num, String file_order_num, String manage_opinion,
			String receive_id, String remark) {
		super();
		this.paper_id = paper_id;
		this.paper_barcode = paper_barcode;
		this.event_code = event_code;
		this.user_iidd = user_iidd;
		this.user_name = user_name;
		this.dept_id = dept_id;
		this.dept_name = dept_name;
		this.duty_user_iidd = duty_user_iidd;
		this.duty_user_name = duty_user_name;
		this.duty_dept_id = duty_dept_id;
		this.duty_dept_name = duty_dept_name;
		this.seclv_code = seclv_code;
		this.seclv_name = seclv_name;
		this.print_time = print_time;
		this.is_reprint = is_reprint;
		this.print_result = print_result;
		this.file_title = file_title;
		this.project_code = project_code;
		this.project_name = project_name;
		this.fileID = fileID;
		this.page_count = page_count;
		this.page_size = page_size;
		this.color = color;
		this.print_direct = print_direct;
		this.print_double = print_double;
		this.paper_state = paper_state;
		this.paper_state_name = paper_state_name;
		this.printer_code = printer_code;
		this.printer_name = printer_name;
		this.retrieve_time = retrieve_time;
		this.retrieve_type = retrieve_type;
		this.retrieve_user_iidd = retrieve_user_iidd;
		this.retrieve_box_code = retrieve_box_code;
		this.destroy_time = destroy_time;
		this.destroy_user_iidd = destroy_user_iidd;
		this.job_code = job_code;
		this.jobType = jobType;
		this.jobtype_code = jobtype_code;
		this.job_status = job_status;
		this.retrieve_user_name = retrieve_user_name;
		this.destroy_user_name = destroy_user_name;
		this.create_type = create_type;
		this.scope = scope;
		this.scope_name = scope_name;
		this.scope_dept_id = scope_dept_id;
		this.scope_dept_name = scope_dept_name;
		this.applyeventcode = applyeventcode;
		this.applyuserid = applyuserid;
		this.applyusername = applyusername;
		this.applydeptid = applydeptid;
		this.applydeptname = applydeptname;
		this.borrow_status = borrow_status;
		this.send_num = send_num;
		this.output_user_name = output_user_name;
		this.output_dept_name = output_dept_name;
		this.fail_remark = fail_remark;
		this.retrieve_box_name = retrieve_box_name;
		this.keyword_content = keyword_content;
		this.expire_time = expire_time;
		this.expire_status = expire_status;
		this.company = company;
		this.publish_type = publish_type;
		this.company_send = company_send;
		this.urgency_lv = urgency_lv;
		this.filed_date = filed_date;
		this.publish_limits = publish_limits;
		this.summ = summ;
		this.send_id = send_id;
		this.send_mode = send_mode;
		this.box_num = box_num;
		this.file_order_num = file_order_num;
		this.manage_opinion = manage_opinion;
		this.receive_id = receive_id;
		this.remark = remark;
	}

	public String getJobtype_code() {
		return jobtype_code;
	}

	public void setJobtype_code(String jobtype_code) {
		this.jobtype_code = jobtype_code;
		this.jobType = JobTypeEnum.valueOf(jobtype_code);
	}

	public String getOutput_user_name() {
		return output_user_name;
	}

	public void setOutput_user_name(String output_user_name) {
		this.output_user_name = output_user_name;
	}

	public String getOutput_dept_name() {
		return output_dept_name;
	}

	public void setOutput_dept_name(String output_dept_name) {
		this.output_dept_name = output_dept_name;
	}

	public String getFail_remark() {
		return fail_remark;
	}

	public void setFail_remark(String fail_remark) {
		this.fail_remark = fail_remark;
	}

	public String getRetrieve_box_name() {
		return retrieve_box_name;
	}

	public void setRetrieve_box_name(String retrieve_box_name) {
		this.retrieve_box_name = retrieve_box_name;
	}

	public String getKeyword_content() {
		return keyword_content;
	}

	public void setKeyword_content(String keyword_content) {
		this.keyword_content = keyword_content;
	}

	public Integer getExpire_status() {
		if (expire_time == null) {// 未到期
			expire_status = 0;
		} else if (expire_time.before(new Date())) {// 已到期
			expire_status = 1;
		} else if (expire_time.before(TimeUtil.getAfterXDay(2))) {// 即将到期
			expire_status = 2;
		} else {// 未到期
			expire_status = 0;
		}
		return expire_status;
	}

	public String getExpire_status_name() {
		if (expire_time == null) {
			return "未到期";
		} else if (expire_time.before(new Date())) {
			return "已到期";
		} else if (expire_time.before(TimeUtil.getAfterXDay(2))) {
			return "即将到期";
		} else {
			return "未到期";
		}
	}

	public void setExpire_status(Integer expire_status) {
		this.expire_status = expire_status;
	}

	public String getPID_barcode() {
		return PID_barcode;
	}

	public void setPID_barcode(String pID_barcode) {
		PID_barcode = pID_barcode;
	}

	public String getPID_pagenum() {
		return PID_pagenum;
	}

	public void setPID_pagenum(String pID_pagenum) {
		PID_pagenum = pID_pagenum;
	}

	public String getRetrieve_pagenum() {
		return retrieve_pagenum;
	}

	public void setRetrieve_pagenum(String retrieve_pagenum) {
		this.retrieve_pagenum = retrieve_pagenum;
	}

	public String getRetrieve_replace() {
		return retrieve_replace;
	}

	public void setRetrieve_replace(String retrieve_replace) {
		this.retrieve_replace = retrieve_replace;
	}

	public String getDestroy_pagenum() {
		return destroy_pagenum;
	}

	public void setDestroy_pagenum(String destroy_pagenum) {
		this.destroy_pagenum = destroy_pagenum;
	}

	public String getSupervise_user_iidd() {
		return supervise_user_iidd;
	}

	public void setSupervise_user_iidd(String supervise_user_iidd) {
		this.supervise_user_iidd = supervise_user_iidd;
	}
}
