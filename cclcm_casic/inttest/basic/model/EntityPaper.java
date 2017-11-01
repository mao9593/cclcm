package basic.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.util.ActivitiCons;
import hdsec.web.project.common.BaseDomain;

import java.util.Date;

public class EntityPaper extends BaseDomain {
	private Integer paper_id;// ID SEQ
	private String paper_barcode;// 载体条码
	private String event_code;// 作业ID
	private String user_iidd;// 申请人ID
	private String user_name;// 申请人姓名
	private String dept_id;
	private String dept_name;// 申请人部门名称
	private String duty_user_iidd;// 责任人ID
	private String duty_user_name;// 责任人姓名
	private String duty_dept_name;// 责任人部门名称
	private Integer seclv_code;// 密级代号
	private String seclv_name;// 密级名称
	private Date print_time;// 打印时间
	private String is_reprint;// 是否补刻
	private String print_result;// 刻录结果
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
	private String create_type;// 产生类型:PRINT打印COPY复印LEADIN录入，默认PRINT
	private String scope;// 载体归属PERSON个人文件DEPT部门文件，默认PERSON
	private String scope_dept_id;// 载体归属部门ID
	private String scope_dept_name;// 载体归属部门名称
	private String applyeventcode;// 借用作业编号
	private String applyuserid;// 借用申请人ID
	private String applyusername;// 借用借用申请人
	private String applydeptname;// 借用申请部门
	private String borrow_status;// 借用状态
	
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
	
	public void setJobType_code(String jobType_code) {
		this.jobType = JobTypeEnum.valueOf(jobType_code);
	}
	
	public String getJob_status() {
		return job_status;
	}
	
	public void setJob_status(String job_status) {
		this.job_status = job_status;
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
		switch (paper_state) {
			case 0:
				return "留用";
			case 1:
				return "已回收";
			case 2:
				return "已外发";
			case 3:
				return "已归档";
			case 4:
				return "已销毁";
			case 5:
				return "流转中";
			case 6:
				return "借阅中";
			case 7:
				return "申请销毁";
			case 8:
				return "申请外发";
			case 9:
				return "申请归档";
			case 10:
				return "待交接";
			default:
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
		if (null == is_reprint || "N".equals(is_reprint)) {
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
	
	public EntityPaper() {
		super();
	}
	
	public EntityPaper(String paper_barcode, String event_code, String user_iidd, String user_name, String dept_id,
			String dept_name, String duty_user_iidd, String duty_user_name, String duty_dept_name, Integer seclv_code,
			String seclv_name, Date print_time, String is_reprint, String print_result, String file_title,
			String project_code, String project_name, String fileID, Integer page_count, String page_size,
			Integer color, Integer print_direct, Integer print_double, Integer paper_state, String paper_state_name,
			String printer_code, String printer_name, Date retrieve_time, Integer retrieve_type,
			String retrieve_user_iidd, String retrieve_box_code, Date destroy_time, String destroy_user_iidd,
			String job_code, JobTypeEnum jobType, String job_status, String retrieve_user_name,
			String destroy_user_name, String create_type, String scope, String scope_dept_id, String scope_dept_name) {
		super();
		this.paper_barcode = paper_barcode;
		this.event_code = event_code;
		this.user_iidd = user_iidd;
		this.user_name = user_name;
		this.dept_id = dept_id;
		this.dept_name = dept_name;
		this.duty_user_iidd = duty_user_iidd;
		this.duty_user_name = duty_user_name;
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
	
	public String getJobtype_code() {
		return jobtype_code;
	}
	
	public void setJobtype_code(String jobtype_code) {
		this.jobtype_code = jobtype_code;
	}
	
}
