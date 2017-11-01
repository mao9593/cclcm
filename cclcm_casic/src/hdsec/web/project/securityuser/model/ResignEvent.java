package hdsec.web.project.securityuser.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.BaseEvent;

import java.util.Date;

/**
 * 用户辞职申请作业类
 * 
 * @author yangjl
 * 
 */

public class ResignEvent extends BaseEvent {
	private String resign_user_iidd = ""; // 离职人ID
	private String resign_user_name = ""; // 离职人
	private String resign_dept_id = ""; // 离职部门ID
	private String resign_dept_name = ""; // 离职部门
	private Date resign_time = null; // 离职时间
	private Integer resign_status = 0; // 离职状态
	private String his_job_code = ""; // 包含该作业的历史任务列表
	private String reason = "";// 离职原因
	private String dept_id_after = ""; // 变更后部门id
	private String dept_name_after = ""; // 变更后部门名称、定部门
	private String post_id_after = ""; // 变更后岗位id
	private String post_name_after = ""; // 变更后岗位id
	private String resign_others = "";// 离职原因（其他）
	private String start_time = null;// 脱密期开始时间
	private String end_time = null;// 脱密期结束时间
	private String signname = "";
	private Date sign_time = null;
	private String post_id_before = ""; // 变更前岗位id
	private String post_name_before = ""; // 变更前岗位

	public String getPost_name_before() {
		return post_name_before;
	}

	public void setPost_name_before(String post_name_before) {
		this.post_name_before = post_name_before;
	}

	public String getPost_id_before() {
		return post_id_before;
	}

	public void setPost_id_before(String post_id_before) {
		this.post_id_before = post_id_before;
	}

	public void setDept_name_after(String dept_name_after) {
		this.dept_name_after = dept_name_after;
	}

	public String getSignname() {
		return signname;
	}

	public void setSignname(String signname) {
		this.signname = signname;
	}

	public String getSign_time() {
		return sign_time == null ? "" : getSdf().format(sign_time);
	}

	public void setSign_time(Date sign_time) {
		this.sign_time = sign_time;
	}

	public String getResign_user_iidd() {
		return resign_user_iidd;
	}

	public void setResign_user_iidd(String resign_user_iidd) {
		this.resign_user_iidd = resign_user_iidd;
	}

	public String getResign_user_name() {
		return resign_user_name;
	}

	public void setResign_user_name(String resign_user_name) {
		this.resign_user_name = resign_user_name;
	}

	public String getResign_dept_id() {
		return resign_dept_id;
	}

	public void setResign_dept_id(String resign_dept_id) {
		this.resign_dept_id = resign_dept_id;
	}

	public String getResign_dept_name() {
		return resign_dept_name;
	}

	public void setResign_dept_name(String resign_dept_name) {
		this.resign_dept_name = resign_dept_name;
	}

	public Integer getResign_status() {
		return resign_status;
	}

	public void setResign_status(Integer resign_status) {
		this.resign_status = resign_status;
	}

	public String getResign_others() {
		return resign_others;
	}

	public void setResign_others(String resign_others) {
		this.resign_others = resign_others;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	/**
	 * 
	 * 
	 public String getResign_status_name() { String name = ""; String[] status = resign_status.split(","); for (int i
	 * = 0; i < status.length; i++) { if (status[i].contains("1")) { name = name + "退休,"; } else if
	 * (status[i].contains("2")) { name = name + "调离单位,"; } else if (status[i].contains("3")) { name = name + "岗位变化,"; }
	 * else if (status[i].contains("4")) { name = name + resign_others; } } return name; }
	 */

	public String getResign_status_name() {
		if (resign_status == null) {
			return "未知";
		}
		switch (this.resign_status) {
		case 0:
			return "离职申请中";
		case 1:
			return "已离职";
		default:
			return "其他";
		}
	}

	public String getHis_job_code() {
		return his_job_code;
	}

	public void setHis_job_code(String his_job_code) {
		this.his_job_code = his_job_code;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDept_id_after() {
		return dept_id_after;
	}

	public void setDept_id_after(String dept_id_after) {
		this.dept_id_after = dept_id_after;
	}

	public String getPost_id_after() {
		return post_id_after;
	}

	public void setPost_id_after(String post_id_after) {
		this.post_id_after = post_id_after;
	}

	public String getDept_name_after() {
		return dept_name_after;
	}

	public void setDept_after_name(String dept_name_after) {
		this.dept_name_after = dept_name_after;
	}

	public String getPost_name_after() {
		return post_name_after;
	}

	public void setPost_name_after(String post_name_after) {
		this.post_name_after = post_name_after;
	}

	public String getReason_name() {
		if (reason == null) {
			return "";
		}
		switch (this.reason) {
		case "1":
			return "退休";
		case "2":
			return "调离单位";
		case "3":
			return "岗位变化";
		case "4":
			return "其他";
		default:
			return "未知";
		}
	}

	public void setResign_time(Date resign_time) {
		this.resign_time = resign_time;
	}

	public String getResign_time() {
		return resign_time == null ? "" : getSdf().format(resign_time);
	}

	public ResignEvent() {
		super(JobTypeEnum.RESIGN);
	}

	public ResignEvent(String user_iidd, String dept_id, String event_code, Integer seclv_code, String usage_code,
			String project_code, String reason, String summ, String resign_user_iidd, String resign_user_name,
			String resign_dept_id, String resign_dept_name, String signname, Date sign_time) {
		super(JobTypeEnum.RESIGN, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);
		this.resign_user_iidd = resign_user_iidd;
		this.resign_user_name = resign_user_name;
		this.resign_dept_id = resign_dept_id;
		this.resign_dept_name = resign_dept_name;
		this.reason = reason;
		this.signname = signname;
		this.sign_time = sign_time;
	}

	public ResignEvent(String user_iidd, String dept_id, String event_code, Integer seclv_code, String usage_code,
			String project_code, String reason, String summ, String resign_user_iidd, String resign_user_name,
			String resign_dept_id, String resign_dept_name) {

		super(JobTypeEnum.RESIGN, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);
		this.resign_user_iidd = resign_user_iidd;
		this.resign_user_name = resign_user_name;
		this.resign_dept_id = resign_dept_id;
		this.resign_dept_name = resign_dept_name;
		this.reason = reason;
	}

	public ResignEvent(String user_iidd, String dept_id, String event_code, Integer seclv_code, String usage_code,
			String project_code, String reason, String summ, String resign_user_iidd, String resign_user_name,
			String resign_dept_id, String resign_dept_name, String dept_id_after, String dept_name_after,
			String post_id_after, String post_name_after, String resign_others, String signname, Date sign_time,
			String post_id_before) {
		super(JobTypeEnum.RESIGN, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);
		this.resign_user_iidd = resign_user_iidd;
		this.resign_user_name = resign_user_name;
		this.resign_dept_id = resign_dept_id;
		this.resign_dept_name = resign_dept_name;
		this.reason = reason;
		this.signname = signname;
		this.sign_time = sign_time;
		this.dept_id_after = dept_id_after;
		this.dept_name_after = dept_name_after;
		this.post_id_after = post_id_after;
		this.post_name_after = post_name_after;
		this.resign_others = resign_others;
		this.post_id_before = post_id_before;
	}

	public ResignEvent(String user_iidd, String dept_id, String event_code, Integer seclv_code, String usage_code,
			String project_code, String reason, String summ, String resign_user_iidd, String start_time,
			String end_time, String resign_user_name, String resign_dept_id, String resign_dept_name,
			String dept_id_after, String dept_name_after, String post_id_after, String post_name_after,
			String resign_others, String signname, Date sign_time) {
		super(JobTypeEnum.RESIGN, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);
		this.resign_user_iidd = resign_user_iidd;
		this.resign_user_name = resign_user_name;
		this.resign_dept_id = resign_dept_id;
		this.resign_dept_name = resign_dept_name;
		this.reason = reason;
		this.signname = signname;
		this.sign_time = sign_time;
		this.start_time = start_time;
		this.end_time = end_time;
		this.dept_id_after = dept_id_after;
		this.dept_name_after = dept_name_after;
		this.post_id_after = post_id_after;
		this.post_name_after = post_name_after;
		this.resign_others = resign_others;
	}

}
