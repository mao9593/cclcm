package hdsec.web.project.arch.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.util.ActivitiCons;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 档案借用作业类
 * 
 * @author handouwang
 * 
 *         2015-7-28/
 */
public class EventArchBrw {
	private Integer id = 0;
	private String event_code = "";// 作业号
	private String user_iidd = "";// 申请用户ID
	private String dept_id = "";// 申请用户部门
	private Integer seclv_code = null;// 作业密级
	private Integer arch_type_id = 0;// 档案类型ID
	private Integer item_id = 0;// 子项ID
	private Integer dos_id = 0;// 案卷号
	private Integer at_record_id = 0;// 档案ID
	private String barcode = "";// 档案条码
	private String file_title = "";// 文件标题
	private String usage_code = "";// 用途编号
	private Date apply_time = null;// 作业添加时间
	private Date lend_time = null;// 档案借出时间
	private Date limit_time = null;// 归还期限时间
	private Date return_time = null;// 归还时间
	private String lend_user_iidd = "";// 借出人
	private Integer borrow_status = null;// 借阅作业状态
	private String job_code = "";// 任务号
	private String his_job_code = "";// 备用
	private String project = "";// 冗余字段
	private String arch_type_name = "";// 档案类别名称
	private String item_code = "";// 子项代号
	private String dos_code = "";// 案卷号
	private String user_name = "";// 申请用户名
	private String dept_name = "";// 申请用户部门名称
	private String seclv_name = "";// 密级名称
	private String usage_name = "";// 用途名称
	private String job_status = "";// 审批状态
	private SimpleDateFormat sdf = null;// 格式化时间输出格式
	private JobTypeEnum jobType = null;// 任务类型
	private String lend_user_name = "";// 借出人
	private String arche_type = ""; // 借阅档案类型 1为电子，2为纸质
	private String arche_url = ""; // 借阅电子 url

	public String getArche_type() {
		return arche_type;
	}

	public String getArche_typename() {
		String name = "未知档案类型";
		if ("1".equals(arche_type)) {
			name = "电子档案";
		} else if ("2".equals(arche_type)) {
			name = "纸质档案";
		}
		return name;
	}

	public void setArche_type(String arche_type) {
		this.arche_type = arche_type;
	}

	public String getArche_url() {
		return arche_url;
	}

	public void setArche_url(String arche_url) {
		this.arche_url = arche_url;
	}

	public SimpleDateFormat getSdf() {
		return sdf == null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") : sdf;
	}

	public String getReturnLimit() {
		Calendar cl = Calendar.getInstance();
		cl.setTime(limit_time);
		if (cl.compareTo(Calendar.getInstance()) < 0) {
			return "<font color='red'>已到期</font>";
		} else {
			return "未到期";
		}
	}

	public String getBorrow_status_name() {
		String name = "未知";
		switch (this.borrow_status) {
		case EventStatus.WAITING:
			name = "未借入";
			break;
		case EventStatus.LENT:
			name = "已借入";
			break;
		case EventStatus.RETURN:
			name = "已归还";
			break;
		case EventStatus.RENEW:
			name = "已续借";
			break;
		case EventStatus.UNRENEW:
			name = "未续借";
			break;
		}
		return name;
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
			name = "未申请/已撤销";
			break;
		}
		return name;
	}

	public EventArchBrw() {
		jobType = JobTypeEnum.BORROW;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public Integer getArch_type_id() {
		return arch_type_id;
	}

	public void setArch_type_id(Integer arch_type_id) {
		this.arch_type_id = arch_type_id;
	}

	public Integer getItem_id() {
		return item_id;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}

	public Integer getDos_id() {
		return dos_id;
	}

	public void setDos_id(Integer dos_id) {
		this.dos_id = dos_id;
	}

	public Integer getAt_record_id() {
		return at_record_id;
	}

	public void setAt_record_id(Integer at_record_id) {
		this.at_record_id = at_record_id;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getFile_title() {
		return file_title;
	}

	public void setFile_title(String file_title) {
		this.file_title = file_title;
	}

	public String getUsage_code() {
		return usage_code;
	}

	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code;
	}

	public String getApply_time() {
		return apply_time == null ? "" : getSdf().format(apply_time);
	}

	public void setApply_time(Date apply_time) {
		this.apply_time = apply_time;
	}

	public String getLend_time() {
		return lend_time == null ? "" : getSdf().format(lend_time);
	}

	public void setLend_time(Date lend_time) {
		this.lend_time = lend_time;
	}

	public String getLimit_time() {
		return limit_time == null ? "" : getSdf().format(limit_time);
	}

	public void setLimit_time(Date limit_time) {
		this.limit_time = limit_time;
	}

	public String getReturn_time() {
		return return_time == null ? "" : getSdf().format(return_time);
	}

	public void setReturn_time(Date return_time) {
		this.return_time = return_time;
	}

	public String getLend_user_iidd() {
		return lend_user_iidd;
	}

	public void setLend_user_iidd(String lend_user_iidd) {
		this.lend_user_iidd = lend_user_iidd;
	}

	public Integer getBorrow_status() {
		return borrow_status;
	}

	public void setBorrow_status(Integer borrow_status) {
		this.borrow_status = borrow_status;
	}

	public String getJob_code() {
		return job_code;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public String getHis_job_code() {
		return his_job_code;
	}

	public void setHis_job_code(String his_job_code) {
		this.his_job_code = his_job_code;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getArch_type_name() {
		return arch_type_name;
	}

	public void setArch_type_name(String arch_type_name) {
		this.arch_type_name = arch_type_name;
	}

	public String getItem_code() {
		return item_code;
	}

	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}

	public String getDos_code() {
		return dos_code;
	}

	public void setDos_code(String dos_code) {
		this.dos_code = dos_code;
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

	public String getSeclv_name() {
		return seclv_name;
	}

	public void setSeclv_name(String seclv_name) {
		this.seclv_name = seclv_name;
	}

	public String getUsage_name() {
		return usage_name;
	}

	public void setUsage_name(String usage_name) {
		this.usage_name = usage_name;
	}

	public String getJob_status() {
		return job_status;
	}

	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}

	public JobTypeEnum getJobType() {
		return jobType;
	}

	public void setJobType(JobTypeEnum jobType) {
		this.jobType = jobType;
	}

	public String getLend_user_name() {
		return lend_user_name;
	}

	public void setLend_user_name(String lend_user_name) {
		this.lend_user_name = lend_user_name;
	}

}
