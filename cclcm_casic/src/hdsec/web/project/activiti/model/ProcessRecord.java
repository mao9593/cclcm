package hdsec.web.project.activiti.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 流程记录表（审计类）
 * @author renmingfei
 *
 */
public class ProcessRecord {
	private String job_code = "";//申请号
	private JobTypeEnum jobType = null;//流程类型枚举值
	private String user_iidd = "";//操作用户ID
	private String user_name = "";//操作用户姓名
	private String dept_name = "";//操作用户部门名称
	private String operation = "";//操作结果(Y、N)
	private String opinion = "";//审批意见
	private Date op_time = null;//操作时间
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public String getJob_code() {
		return job_code;
	}
	
	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}
	
	public void setJobType(String jobType_code) {
		this.jobType = JobTypeEnum.valueOf(jobType_code);
	}
	
	public void setJobType_coe(String jobType_code) {
		this.jobType = JobTypeEnum.valueOf(jobType_code);
	}
	
	public JobTypeEnum getJobType() {
		return jobType;
	}
	
	public void setJobType(JobTypeEnum jobType) {
		this.jobType = jobType;
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
	
	public String getOperation() {
		return operation;
	}
	
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public String getOpinion() {
		return opinion;
	}
	
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	
	public Date getOp_time() {
		return op_time;
	}
	
	public String getOp_time_str() {
		return op_time == null ? "" : sdf.format(op_time);
	}
	
	public void setOp_time(Date op_time) {
		this.op_time = op_time;
	}
	
	public SimpleDateFormat getSdf() {
		return sdf;
	}
	
	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}
	
	public ProcessRecord() {
		super();
	}
	
	public ProcessRecord(String job_code, JobTypeEnum jobType, String user_iidd, String user_name, String dept_name,
			String operation, String opinion, Date op_time) {
		super();
		this.job_code = job_code;
		this.jobType = jobType;
		this.user_iidd = user_iidd;
		this.user_name = user_name;
		this.dept_name = dept_name;
		this.operation = operation;
		this.opinion = opinion;
		this.op_time = op_time;
	}
	
}
