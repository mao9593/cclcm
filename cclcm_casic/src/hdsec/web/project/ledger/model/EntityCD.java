package hdsec.web.project.ledger.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.util.ActivitiCons;
import hdsec.web.project.common.BaseDomain;
import hdsec.web.project.common.util.TimeUtil;

import java.util.Date;

public class EntityCD extends BaseDomain {
	private Integer cd_id;// ID SEQ
	private String cd_barcode;// 载体条码
	private String event_code;// 作业ID
	private String dept_id;// 申请人部门ID
	private String dept_name;// 申请人部门名称
	private String user_iidd;// 申请人ID
	private String user_name;// 申请人姓名
	private String duty_user_iidd;// 责任人ID
	private String duty_user_name;// 责任人姓名
	private String duty_dept_id;// 责任人部门ID
	private String duty_dept_name;// 责任人部门名称
	private Integer seclv_code;// 密级代号
	private String seclv_name;// 密级名称
	private Date burn_time;// 刻录时间
	private String burn_machine;// 刻录机器
	private String burn_ipaddress;// 刻录IP
	private String cd_type;// CD类型
	private String cd_volume;// CD标卷名
	private Integer data_type;// 数据类型
	private String file_list;// 文件列表
	private Integer file_num;// 文件数量
	private String is_reburn;// 是否补刻
	private String burn_result;// 刻录结果
	private Integer burn_type = 0;// 刻录类型
	private String project_code;// 所属项目
	private Integer cd_state;// 介质状态
	private String cd_state_name;// 状态名称
	private String create_type;// 产生类型:BURN刻录LEADIN录入，默认BURN
	private String scope;// 载体归属PERSON个人文件DEPT部门文件，默认PERSON
	private String scope_name;
	private String burner_code;// 刻录机代码
	private String burner_name;// 刻录机名称
	private String mfp_code;// 一体机代码
	private String mfp_name;// 一体机名称
	private Date retrieve_time;// 回收时间
	private Integer retrieve_type;// 回收方式
	private String retrieve_user_iidd;// 回收人ID
	private String retrieve_user_name;
	private String retrieve_box_code;// 回收柜标示
	private Date destroy_time;// 销毁时间
	private String destroy_user_iidd;// 销毁人ID
	private String destroy_user_name;
	private String job_code;// 流转号
	private String project_name;// 所属项目名称
	private String job_status = "";
	private JobTypeEnum jobType = null;
	private String jobtype_code;
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
	private String fail_remark;// 刻录失败备注
	private String conf_code;// 保密编号
	private String retrieve_box_name;// 回收柜名称
	private Date expire_time = null;// 到期回收提醒时间
	private Integer expire_status = null;
	private Integer delay_days = null;
	private String barcode = "";
	private Date start_time;// 申请销毁时间
	private String cd_barcode_td;// 二维载体条码
	private String supervise_user_iidd = "";// 监销人id
	private String accept_user_iidd = "";// 监销人id
	private String src_code = "";// 录入的光盘：原光盘编号
	private String confidential_num = ""; // 机要号
	private String output_undertaker = ""; // 外发承办人(id)
	private String output_undertaker_name = ""; // 外发承办人名字
	private String carryout_user_names = ""; // 携带人名(外发或外带时1-多个携带人)
	private String send_way = ""; // 外发方式 0:专人携带; 1:发机要
	private String output_confidential_num = ""; // 外发机要号

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

	public String getSupervise_user_iidd() {
		return supervise_user_iidd;
	}

	public void setSupervise_user_iidd(String supervise_user_iidd) {
		this.supervise_user_iidd = supervise_user_iidd;
	}

	public String getCd_barcode_td() {
		return cd_barcode_td;
	}

	public void setCd_barcode_td(String cd_barcode_td) {
		this.cd_barcode_td = cd_barcode_td;
	}

	public String getStart_time() {
		return getSdf().format(start_time);
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Integer getCd_id() {
		return cd_id;
	}

	public void setCd_id(Integer cd_id) {
		this.cd_id = cd_id;
	}

	public String getCd_barcode() {
		return cd_barcode;
	}

	public void setCd_barcode(String cd_barcode) {
		this.cd_barcode = cd_barcode;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
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

	public String getBurn_time() {
		if (null == burn_time) {
			return " ";
		}
		return getSdf().format(burn_time);
	}

	public void setBurn_time(Date burn_time) {
		this.burn_time = burn_time;
	}

	public String getBurn_machine() {
		return burn_machine;
	}

	public void setBurn_machine(String burn_machine) {
		this.burn_machine = burn_machine;
	}

	public String getCd_type() {
		return cd_type;
	}

	public void setCd_type(String cd_type) {
		this.cd_type = cd_type;
	}

	public String getCd_volume() {
		return cd_volume;
	}

	public void setCd_volume(String cd_volume) {
		this.cd_volume = cd_volume;
	}

	public Integer getData_type() {
		return data_type;
	}

	public void setData_type(Integer data_type) {
		this.data_type = data_type;
	}

	public String getFile_list() {
		return file_list;
	}

	public void setFile_list(String file_list) {
		this.file_list = file_list;
	}

	public Integer getFile_num() {
		return file_num;
	}

	public void setFile_num(Integer file_num) {
		this.file_num = file_num;
	}

	public String getIs_reburn() {
		return is_reburn;
	}

	public void setIs_reburn(String is_reburn) {
		this.is_reburn = is_reburn;
	}

	public String getBurn_result() {
		return burn_result;
	}

	public void setBurn_result(String burn_result) {
		this.burn_result = burn_result;
	}

	public Integer getBurn_type() {
		return burn_type;
	}

	public void setBurn_type(Integer burn_type) {
		this.burn_type = burn_type;
	}

	public String getProject_code() {
		return project_code;
	}

	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}

	public Integer getCd_state() {
		return cd_state;
	}

	public void setCd_state(Integer cd_state) {
		this.cd_state = cd_state;
	}

	public String getBurner_code() {
		return burner_code;
	}

	public void setBurner_code(String burner_code) {
		this.burner_code = burner_code;
	}

	public String getBurner_name() {
		return burner_name;
	}

	public void setBurner_name(String burner_name) {
		this.burner_name = burner_name;
	}

	public String getMfp_code() {
		return mfp_code;
	}

	public void setMfp_code(String mfp_code) {
		this.mfp_code = mfp_code;
	}

	public String getMfp_name() {
		return mfp_name;
	}

	public void setMfp_name(String mfp_name) {
		this.mfp_name = mfp_name;
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

	public String getJob_code() {
		return job_code;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public String getSeclv_name() {
		return seclv_name;
	}

	public void setSeclv_name(String seclv_name) {
		this.seclv_name = seclv_name;
	}

	public Integer getDelay_days() {
		return delay_days;
	}

	public void setDelay_days(Integer delay_days) {
		this.delay_days = delay_days;
	}

	public String getCd_state_name() {
		if (cd_state == null) {
			return "未知";
		}
		if (EntityStateEnum.isStateAvailabel(cd_state.intValue())) {
			return EntityStateEnum.valueOf("STATE" + cd_state).getName();
		} else {
			return "未知";
		}
	}

	public void setCd_state_name(String cd_state_name) {
		this.cd_state_name = cd_state_name;
	}

	public String getBurn_ipaddress() {
		return burn_ipaddress;
	}

	public void setBurn_ipaddress(String burn_ipaddress) {
		this.burn_ipaddress = burn_ipaddress;
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

	public String getRetrieve_type_name() {
		if (retrieve_type == null) {
			return "未知";
		}
		switch (retrieve_type) {
		case 0:
			return "回收柜子";
		case 1:
			return "人工";
		default:
			return "其他";
		}
	}

	public String getData_type_name() {
		if (data_type == null) {
			return "未知";
		}
		switch (data_type) {
		case 0:
			return "数据刻录";
		case 1:
			return "镜像刻录";
		case 2:
			return "音视频刻录";
		default:
			return "未知";
		}
	}

	public String getBurn_type_name() {
		if (burn_type == null) {
			return "未知";
		}
		switch (burn_type) {
		case 0:
			return "普通刻录";
		case 1:
			return "一体机";
		default:
			return "其他";
		}
	}

	public String getIs_reburn_name() {
		if ("1".equals(is_reburn)) {
			return "是";
		} else {
			return "否";
		}
	}

	public String getBurn_result_name() {
		if (null == burn_result || "0".equals(burn_result)) {
			return "失败";
		} else {
			return "成功";
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

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
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

	public JobTypeEnum getJobType() {
		return jobType;
	}

	public void setJobType(JobTypeEnum jobType) {
		this.jobType = jobType;
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

	public String getCreate_type_name() {
		if (create_type != null && create_type.equals("LEADIN")) {
			return "录入";
		} else {
			return "刻录";
		}
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

	public EntityCD() {
		super();
	}

	public EntityCD(String cd_barcode, String event_code, String user_iidd, String user_name, String dept_id,
			String dept_name, String duty_user_iidd, String duty_user_name, String duty_dept_id, String duty_dept_name,
			Integer seclv_code, String seclv_name, Date burn_time, String burn_machine, String burn_ipaddress,
			String cd_type, String cd_volume, Integer data_type, String file_list, Integer file_num, String is_reburn,
			String burn_result, Integer burn_type, String project_code, Integer cd_state, String cd_state_name,
			String create_type, String scope, String burner_code, String burner_name, String mfp_code, String mfp_name,
			Date retrieve_time, Integer retrieve_type, String retrieve_user_iidd, String retrieve_user_name,
			String retrieve_box_code, Date destroy_time, String destroy_user_iidd, String destroy_user_name,
			String job_code, JobTypeEnum jobType, String project_name, String job_status, String scope_dept_id,
			String scope_dept_name) {

		super();
		this.cd_barcode = cd_barcode;
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
		this.burn_time = burn_time;
		this.burn_machine = burn_machine;
		this.burn_ipaddress = burn_ipaddress;
		this.cd_type = cd_type;
		this.cd_volume = cd_volume;
		this.data_type = data_type;
		this.file_list = file_list;
		this.file_num = file_num;
		this.is_reburn = is_reburn;
		this.burn_result = burn_result;
		this.burn_type = burn_type;
		this.project_code = project_code;
		this.cd_state = cd_state;
		this.cd_state_name = cd_state_name;
		this.create_type = create_type;
		this.scope = scope;
		this.burner_code = burner_code;
		this.burner_name = burner_name;
		this.mfp_code = mfp_code;
		this.mfp_name = mfp_name;
		this.retrieve_time = retrieve_time;
		this.retrieve_type = retrieve_type;
		this.retrieve_user_iidd = retrieve_user_iidd;
		this.retrieve_user_name = retrieve_user_name;
		this.retrieve_box_code = retrieve_box_code;
		this.destroy_time = destroy_time;
		this.destroy_user_iidd = destroy_user_iidd;
		this.destroy_user_name = destroy_user_name;
		this.job_code = job_code;
		this.project_name = project_name;
		this.jobType = jobType;
		this.job_status = job_status;
		this.retrieve_user_name = retrieve_user_name;
		this.destroy_user_name = destroy_user_name;
		this.scope_dept_id = scope_dept_id;
		this.scope_dept_name = scope_dept_name;
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

	public String getConf_code() {
		return conf_code;
	}

	public void setConf_code(String conf_code) {
		this.conf_code = conf_code;
	}

	public String getRetrieve_box_name() {
		return retrieve_box_name;
	}

	public void setRetrieve_box_name(String retrieve_box_name) {
		this.retrieve_box_name = retrieve_box_name;
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
}
