package hdsec.web.project.secplace.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.BaseEvent;

import java.util.Date;

public class EnterSecplaceEvent extends BaseEvent {
	// private String event_code = "";// 事件编号
	// private String user_iidd = "";// 申请人ID
	// private String dept_id = "";// 申请人部门ID
	// private Date apply_time = null; // 申请时间
	// private Integer seclv_code;// 审批单密级
	private String secplace_code = "";// 场所编号
	private String secplace_name = "";// 场所名称
	private String visit_reason = "";// 事由
	private String accompany_id = "";// 陪同人员id
	private String accompany_name = "";// 陪同人员id
	private Date enter_time = null;// 进入时间
	private Date leave_time = null;// 离开时间
	private Integer file_num = 0;// 文件数量
	private String file_list = "";// 文件列表

	public String getAccompany_name() {
		return accompany_name;
	}

	public void setAccompany_name(String accompany_name) {
		this.accompany_name = accompany_name;
	}

	public String getSecplace_name() {
		return secplace_name;
	}

	public void setSecplace_name(String secplace_name) {
		this.secplace_name = secplace_name;
	}

	public String getAccompany_id() {
		return accompany_id;
	}

	public void setAccompany_id(String accompany_id) {
		this.accompany_id = accompany_id;
	}

	public Integer getFile_num() {
		return file_num;
	}

	public void setFile_num(Integer file_num) {
		this.file_num = file_num;
	}

	public String getFile_list() {
		return file_list;
	}

	public void setFile_list(String file_list) {
		this.file_list = file_list;
	}

	public String getSecplace_code() {
		return secplace_code;
	}

	public void setSecplace_code(String secplace_code) {
		this.secplace_code = secplace_code;
	}

	public String getVisit_reason() {
		return visit_reason;
	}

	public void setVisit_reason(String visit_reason) {
		this.visit_reason = visit_reason;
	}

	public String getEnter_time() {
		return enter_time == null ? "" : getSdf().format(enter_time);
	}

	public void setEnter_time(Date enter_time) {
		this.enter_time = enter_time;
	}

	public String getLeave_time() {
		return leave_time == null ? "" : getSdf().format(leave_time);
	}

	public void setLeave_time(Date leave_time) {
		this.leave_time = leave_time;
	}

	public EnterSecplaceEvent() {
		super(JobTypeEnum.ENTER_SECPLACE);
	}

	public EnterSecplaceEvent(String event_code, String user_iidd, String dept_id, Date apply_time, int seclv_code,
			String secplace_code, String visit_reason, String accompany_id, Date enter_time, Date leave_time,
			Integer file_num, String file_list, String secplace_name) {
		super(JobTypeEnum.ENTER_SECPLACE, event_code, user_iidd, dept_id, seclv_code, "", "", "");
		this.secplace_code = secplace_code;
		this.visit_reason = visit_reason;
		this.accompany_id = accompany_id;
		this.enter_time = enter_time;
		this.leave_time = leave_time;
		this.file_num = file_num;
		this.file_list = file_list;
		this.secplace_name = secplace_name;
	}
}