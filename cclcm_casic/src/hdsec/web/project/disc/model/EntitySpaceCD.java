package hdsec.web.project.disc.model;

import hdsec.web.project.activiti.util.ActivitiCons;
import hdsec.web.project.common.BaseDomain;

import java.util.Date;

public class EntitySpaceCD extends BaseDomain {
	private Integer id;// ID SEQ
	private String event_code = "";// 流水号
	private String barcode = "";// 载体条码
	private String dept_id = "";// 录入人部门ID
	private String dept_name = "";// 录入人部门名称
	private String user_iidd = "";// 录入人ID
	private String user_name = "";// 录入人姓名
	private String duty_user_iidd = "";// 责任人ID
	private String duty_user_name = "";// 责任人姓名
	private String duty_dept_id = "";// 责任人部门ID
	private String duty_dept_name = "";// 责任人部门名称
	private Date leadin_time = null;// 录入时间
	private Integer seclv_code = null;// 密级代号
	private String seclv_name = "";// 密级名称
	private String cd_type = "";// CD类型
	private String spacecd_type = "";// CD类型
	private String project_code = "";// 所属项目
	private String file_list = "";// 文件列表
	private Integer file_num = null;// 文件数量
	private Integer spacecd_state = 0;// 介质状态
	private String spacecd_state_name = "";// 状态名称
	private String create_type = "";// 产生类型:LEADIN录入
	private String scope = "";// 载体归属PERSON个人文件DEPT部门文件，默认PERSON
	private String job_code = "";// 流转号
	private String scope_dept_id = "";// 载体归属部门ID
	private String scope_dept_name = "";// 载体归属部门名称
	private String comment = "";// 中转备注
	private String summ = ""; // 备注
	private String ext_code = "";// 预留字段
	private String job_status = "";
	private Integer total_num = 0;
	private Integer painting_status = null;
	private String painting_status_name = "";
	private String pdf417code = "";

	public String getJob_status() {
		return job_status;
	}

	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}

	public Integer getPainting_status() {
		return painting_status;
	}

	public void setPainting_status(Integer painting_status) {
		this.painting_status = painting_status;
	}

	public String getPainting_status_name() {
		if (painting_status == null) {
			return "出现错误";
		} else {
			switch (this.painting_status) {
			case 0:
				return "未喷绘";
			case 1:
				return "已喷绘";
			default:
				return "未知";
			}
		}
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

	public String getPdf417code() {
		return pdf417code;
	}

	public void setPdf417code(String pdf417code) {
		this.pdf417code = pdf417code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getSpacecd_state_name() {
		if (spacecd_state == null) {
			return "出现错误";
		} else {
			switch (this.spacecd_state) {
			case 0:
				return "未使用";
			case 1:
				return "已分发";
			default:
				return "未知";
			}
		}
	}

	public void setSpacecd_state_name(String spacecd_state_name) {
		this.spacecd_state_name = spacecd_state_name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
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

	public Date getLeadin_time() {
		return leadin_time;
	}

	public String getLeadin_timename() {
		if (null == leadin_time) {
			return "";
		}
		return getSdf().format(leadin_time);
	}

	public void setLeadin_time(Date leadin_time) {
		this.leadin_time = leadin_time;
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getSeclv_name() {
		if (seclv_name != null && !seclv_name.equals("")) {
			return seclv_name;
		}
		return "未标密";
	}

	public void setSeclv_name(String seclv_name) {
		this.seclv_name = seclv_name;
	}

	public String getCd_type() {
		return cd_type;
	}

	public void setCd_type(String cd_type) {
		this.cd_type = cd_type;
	}

	public String getProject_code() {
		return project_code;
	}

	public void setProject_code(String project_code) {
		this.project_code = project_code;
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

	public String getFile_numname() {
		if (file_list != null && !file_list.equals("")) {
			return file_list.split("[|]").length + "";
		}
		return "";
	}

	public void setFile_num(Integer file_num) {
		this.file_num = file_num;
	}

	public Integer getSpacecd_state() {
		return spacecd_state;
	}

	public void setSpacecd_state(Integer spacecd_state) {
		this.spacecd_state = spacecd_state;
	}

	public String getCreate_type() {
		return create_type;
	}

	public String getCreate_typename() {
		if (create_type.equals("LEADIN")) {
			return "录入";
		}
		return "其他";
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

	public String getJob_code() {
		return job_code;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public String getExt_code() {
		return ext_code;
	}

	public void setExt_code(String ext_code) {
		this.ext_code = ext_code;
	}

	public String getSpacecd_type() {
		return spacecd_type;
	}

	public void setSpacecd_type(String spacecd_type) {
		this.spacecd_type = spacecd_type;
	}

	public Integer getTotal_num() {
		return total_num;
	}

	public void setTotal_num(Integer total_num) {
		this.total_num = total_num;
	}

	public String getScope_name() {
		String scopeName = "";
		if (scope.equals("DEPT")) {
			scopeName = "部门光盘";
		} else if (scope.equals("PERSON")) {
			scopeName = "个人光盘";
		}
		return scopeName;
	}

	public String getSpacecd_type_name() {
		if (spacecd_type == null) {
			return "未分配";
		} else {
			switch (this.spacecd_type) {
			case "0":
				return "空白盘";
			case "1":
				return "中转盘";
			default:
				return "未分配";
			}
		}
	}

	public EntitySpaceCD() {
	}

	public EntitySpaceCD(String event_code, String barcode, String pdf417code, String dept_id, String dept_name,
			String user_iidd, String user_name, String duty_user_iidd, String duty_user_name, String duty_dept_id,
			String duty_dept_name, Date leadin_time, Integer seclv_code, String cd_type, String project_code,
			String file_list, Integer file_num, Integer spacecd_state, String create_type, String scope,
			String job_code, String scope_dept_id, String scope_dept_name, String comment, String summ,
			Integer painting_status) {
		super();
		this.event_code = event_code;
		this.barcode = barcode;
		this.pdf417code = pdf417code;
		this.dept_id = dept_id;
		this.dept_name = dept_name;
		this.user_iidd = user_iidd;
		this.user_name = user_name;
		this.duty_user_iidd = duty_user_iidd;
		this.duty_user_name = duty_user_name;
		this.duty_dept_id = duty_dept_id;
		this.duty_dept_name = duty_dept_name;
		this.leadin_time = leadin_time;
		this.seclv_code = seclv_code;
		this.cd_type = cd_type;
		this.project_code = project_code;
		this.file_list = file_list;
		this.file_num = file_num;
		this.spacecd_state = spacecd_state;
		this.create_type = create_type;
		this.scope = scope;
		this.job_code = job_code;
		this.scope_dept_id = scope_dept_id;
		this.scope_dept_name = scope_dept_name;
		this.comment = comment;
		this.summ = summ;
		this.painting_status = painting_status;
	}

}
