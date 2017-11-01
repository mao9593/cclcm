package hdsec.web.project.activiti.model;

import hdsec.web.project.activiti.util.ActivitiCons;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 流程任务表
 * 
 * @author renmingfei
 * 
 */
public class ProcessJob {
	private String job_code = "";// 任务流水号
	private JobTypeEnum jobType = null;// 操作类型枚举类
	private Integer seclv_code = null;// 密级编号
	private String user_iidd = "";// 申请用户ID
	private String dept_id = "";// 申请用户部门
	private String job_status = "";// 申请状态
	private Date start_time = null;// 申请时间
	private String instance_id = "";// 流程实例ID
	private String process_id = "";// 流程定义ID
	private String next_approver = "";// 下级审批人ID
	private String next_approver_name = "";// 下级审批人姓名
	private String exp_dept_id = "";// 流程异常审批部门
	private String exp_role_id = "";// 流程异常审批角色
	private String comment = "";// 任务说明
	private String accept_user_iidd = "";// 录入/流转接收人
	private String output_dept_name = "";// 输出接收单位
	private String output_user_name = "";// 输出接收人
	private Integer input_source = null;// 信息来源，1纸介质、2光盘、3U盘、4互联网
	private Integer source_unit = null;// 来源，0单位/1个人
	private String transfer_type = "";// 外发或者内部流转
	private String unit_code = "";// 单元格ID
	private String box_code = "";// 所属柜子ID
	private String pic_name = "";// 视频或者图片名称

	private String user_name = "";// 用户姓名
	private String dept_name = "";// 部门名称
	private String seclv_name = "";// 密级名称
	private String event_names = "";// 该任务下的所有event名称
	private Integer print_status; // 打印状态，0为未打印，1为打印
	private Integer print_num;// 已打印标记，1为存在已打印，0为不存在已打印
	protected SimpleDateFormat sdf = null;
	private Integer modify_status;// 更改状态
	private Integer pre_seclv = null;
	private Integer trg_seclv = null;
	private String pre_seclv_name = "";
	private String trg_seclv_name = "";
	private Integer file_read_status = 0;// 文件阅读状态（0：未阅读；1：已阅读）
	private String send_way = ""; // 外发(带)方式 0:专人携带; 1:发机要
	private String output_undertaker = ""; // 外发承办人(id)
	private String carryout_user_iidds = ""; // 携带人ID(一个或多个)
	private String carryout_user_names = ""; // 携带人名
	private String is_receipt = "N";

	public String getIs_receipt() {
		return is_receipt;
	}

	public void setIs_receipt(String is_receipt) {
		this.is_receipt = is_receipt;
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
		}
		return send_way_str;
	}

	public String getOutput_undertaker() {
		return output_undertaker;
	}

	public void setOutput_undertaker(String output_undertaker) {
		this.output_undertaker = output_undertaker;
	}

	public String getCarryout_user_iidds() {
		return carryout_user_iidds;
	}

	public void setCarryout_user_iidds(String carryout_user_iidds) {
		this.carryout_user_iidds = carryout_user_iidds;
	}

	public String getCarryout_user_names() {
		return carryout_user_names;
	}

	public void setCarryout_user_names(String carryout_user_names) {
		this.carryout_user_names = carryout_user_names;
	}

	public Integer getFile_read_status() {
		return file_read_status;
	}

	public void setFile_read_status(Integer file_read_status) {
		this.file_read_status = file_read_status;
	}

	public String getPre_seclv_name() {
		return pre_seclv_name;
	}

	public void setPre_seclv_name(String pre_seclv_name) {
		this.pre_seclv_name = pre_seclv_name;
	}

	public String getTrg_seclv_name() {
		return trg_seclv_name;
	}

	public void setTrg_seclv_name(String trg_seclv_name) {
		this.trg_seclv_name = trg_seclv_name;
	}

	public Integer getPre_seclv() {
		return pre_seclv;
	}

	public void setPre_seclv(Integer pre_seclv) {
		this.pre_seclv = pre_seclv;
	}

	public Integer getTrg_seclv() {
		return trg_seclv;
	}

	public void setTrg_seclv(Integer trg_seclv) {
		this.trg_seclv = trg_seclv;
	}

	public Integer getModify_status() {
		return modify_status;
	}

	public void setModify_status(Integer modify_status) {
		this.modify_status = modify_status;
	}

	public String getModify_status_name() {
		String modify_status_name = "";
		if (modify_status == 0) {
			modify_status_name = "未完成";
		} else if (modify_status == 1) {
			modify_status_name = "已完成";
		}
		return modify_status_name;
	}

	public Integer getPrint_num() {
		return print_num;
	}

	public void setPrint_num(Integer print_num) {
		this.print_num = print_num;
	}

	public Integer getPrint_status() {
		return print_status;
	}

	public void setPrint_status(Integer print_status) {
		this.print_status = print_status;
	}

	public SimpleDateFormat getSdf() {
		return sdf == null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") : sdf;
	}

	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAccept_user_iidd() {
		return accept_user_iidd;
	}

	public void setAccept_user_iidd(String accept_user_iidd) {
		this.accept_user_iidd = accept_user_iidd;
	}

	public String getOutput_dept_name() {
		return output_dept_name;
	}

	public void setOutput_dept_name(String output_dept_name) {
		this.output_dept_name = output_dept_name;
	}

	public String getOutput_user_name() {
		return output_user_name;
	}

	public void setOutput_user_name(String output_user_name) {
		this.output_user_name = output_user_name;
	}

	public Integer getInput_source() {
		return input_source;
	}

	public String getInput_source_name() {
		String name = "";
		switch (this.input_source) {
		case 1:
			name = "纸介质";
			break;
		case 2:
			name = "光盘";
			break;
		case 3:
			name = "U盘";
			break;
		case 4:
			name = "互联网";
			break;
		default:
			name = "其他";
			break;
		}
		return name;
	}

	public void setInput_source(Integer input_source) {
		this.input_source = input_source;
	}

	public Integer getSource_unit() {
		return source_unit;
	}

	public String getSource_unit_name() {
		String name = "";
		switch (this.input_source) {
		case 0:
			name = "单位";
			break;
		case 1:
			name = "个人";
			break;
		default:
			name = "其他";
			break;
		}
		return name;
	}

	public void setSource_unit(Integer source_unit) {
		this.source_unit = source_unit;
	}

	public String getTransfer_type() {
		return transfer_type;
	}

	public void setTransfer_type(String transfer_type) {
		this.transfer_type = transfer_type;
	}

	public String getUnit_code() {
		return unit_code;
	}

	public void setUnit_code(String unit_code) {
		this.unit_code = unit_code;
	}

	public String getBox_code() {
		return box_code;
	}

	public void setBox_code(String box_code) {
		this.box_code = box_code;
	}

	public String getPic_name() {
		return pic_name;
	}

	public void setPic_name(String pic_name) {
		this.pic_name = pic_name;
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

	public void setJobType(String jobType_code) {
		this.jobType = JobTypeEnum.valueOf(jobType_code);
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

	public Date getStart_time() {
		return start_time;
	}

	public String getStart_time_str() {
		return start_time == null ? "" : getSdf().format(start_time);
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public String getInstance_id() {
		return instance_id;
	}

	public void setInstance_id(String instance_id) {
		this.instance_id = instance_id;
	}

	public String getProcess_id() {
		return process_id;
	}

	public void setProcess_id(String process_id) {
		this.process_id = process_id;
	}

	public String getNext_approver() {
		return next_approver;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public String getNext_approver_name() {
		return next_approver_name;
	}

	public void setNext_approver_name(String next_approver_name) {
		this.next_approver_name = next_approver_name;
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
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

	public String getExp_dept_id() {
		return exp_dept_id;
	}

	public void setExp_dept_id(String exp_dept_id) {
		this.exp_dept_id = exp_dept_id;
	}

	public String getExp_role_id() {
		return exp_role_id;
	}

	public void setExp_role_id(String exp_role_id) {
		this.exp_role_id = exp_role_id;
	}

	public String getEvent_names() {
		return event_names;
	}

	public void setEvent_names(String event_names) {
		this.event_names = event_names;
	}

	public ProcessJob() {
		super();
	}

	public ProcessJob(String job_code, String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType,
			Date start_time, String job_status, String next_approver, String next_approver_name, String process_id) {
		this.job_code = job_code;
		this.user_iidd = user_iidd;
		this.dept_id = dept_id;
		this.seclv_code = seclv_code;
		this.jobType = jobType;
		this.start_time = start_time;
		this.job_status = job_status;
		this.next_approver = next_approver;
		this.next_approver_name = next_approver_name;
		this.process_id = process_id;
	}

	public ProcessJob(String job_code, String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType,
			Date start_time, String job_status, String next_approver, String next_approver_name, String process_id,
			String send_way, String carryout_user_iidds, String carryout_user_names, String output_undertaker) {
		this.job_code = job_code;
		this.user_iidd = user_iidd;
		this.dept_id = dept_id;
		this.seclv_code = seclv_code;
		this.jobType = jobType;
		this.start_time = start_time;
		this.job_status = job_status;
		this.next_approver = next_approver;
		this.next_approver_name = next_approver_name;
		this.process_id = process_id;
		this.send_way = send_way;
		this.carryout_user_iidds = carryout_user_iidds;
		this.carryout_user_names = carryout_user_names;
		this.output_undertaker = output_undertaker;

	}
}
