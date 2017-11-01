package hdsec.web.project.common;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.util.ActivitiCons;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作业类基类
 * 
 * @author renmingfei
 * 
 */
public class BaseEvent {
	private JobTypeEnum jobType = null;// 任务类型
	private String job_code = "";// 任务号
	private String id = "";
	private String event_code = "";// 作业号
	private String user_iidd = "";// 申请用户ID
	private String dept_id = "";// 申请用户部门
	private Integer seclv_code = null;// 作业密级
	private String project_code = "";// 所属项目编号
	private String usage_code = "";// 用途编号
	private Date apply_time = null;// 作业添加时间
	private String summ = "";// 备注
	private String user_name = "";// 申请用户名
	private String dept_name = "";// 申请用户部门名称
	private String seclv_name = "";// 密级名称
	private String project_name = "";// 所属项目名称
	private String usage_name = "";// 用途名称
	private String job_status = "";// 审批状态
	private SimpleDateFormat sdf = null;// 格式化时间输出格式

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

	public void setJob_type(String jobType) {
		this.jobType = JobTypeEnum.valueOf(jobType);
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

	public String getProject_code() {
		return project_code;
	}

	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}

	public Date getApply_time() {
		return apply_time;
	}

	public String getApply_time_str() {
		return apply_time == null ? "" : getSdf().format(apply_time);
	}

	public void setApply_time(Date apply_time) {
		this.apply_time = apply_time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
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

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getUsage_code() {
		return usage_code;
	}

	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code;
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

	public SimpleDateFormat getSdf() {
		return sdf == null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") : sdf;
	}

	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}

	public BaseEvent() {
		super();
		this.apply_time = new Date();
	}

	public BaseEvent(JobTypeEnum jobType) {
		super();
		this.jobType = jobType;
		this.apply_time = new Date();
	}

	public BaseEvent(JobTypeEnum jobType, String event_code, String user_iidd, String dept_id, Integer seclv_code,
			String usage_code, String project_code, String summ) {
		this(jobType);
		this.event_code = event_code;
		this.user_iidd = user_iidd;
		this.dept_id = dept_id;
		this.seclv_code = seclv_code;
		this.project_code = project_code;
		this.usage_code = usage_code;
		this.summ = summ;
	}

	public BaseEvent(String event_code, String user_iidd, String dept_id, Integer seclv_code, String usage_code) {
		super();
		this.event_code = event_code;
		this.user_iidd = user_iidd;
		this.dept_id = dept_id;
		this.seclv_code = seclv_code;
		this.usage_code = usage_code;
		this.apply_time = new Date();
	}
}
