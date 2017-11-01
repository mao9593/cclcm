package hdsec.web.project.secactivity.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.BaseEvent;

import java.util.Date;

/**
 * 添加用户涉密活动申请
 * 
 * @author gj
 * 
 */
public class UserSecActiEvent extends BaseEvent {
	private String apply_type = ""; // 申请类型
	private String contact = ""; // 联系方式
	private Integer secret_type = null; // 活动类型
	private String name = ""; // 活动名称
	private String place = ""; // 活动地点
	private String secret_leader = ""; // 活动保密主管领导(保密具体负责人)
	private String secret_director = ""; // 活动保密负责人
	private String file_list = ""; // 上传方案文件
	private String his_job_code = ""; // 包含改作业的历史任务列表
	private Date start_time = null; // 活动开始时间
	private Date end_time = null; // 活动结束时间
	private Integer file_num = null;// 文件数量
	private Integer act_selv = null;// 会议密级
	private String sponsor = "";// 主办单位
	private String organizer = "";// 承办单位
	private String act_dept_id = "";// 会务承担单位或部门ID
	private String act_dept_name = "";// 会务承担单位或部门
	private String act_user = "";// 参会人员
	private String act_selv_name = "";// 会议密级名称
	private String secret_director_name = ""; // 活动保密负责人

	public String getSecret_director_name() {
		return secret_director_name;
	}

	public void setSecret_director_name(String secret_director_name) {
		this.secret_director_name = secret_director_name;
	}

	public String getAct_selv_name() {
		return act_selv_name;
	}

	public void setAct_selv_name(String act_selv_name) {
		this.act_selv_name = act_selv_name;
	}

	public Integer getAct_selv() {
		return act_selv;
	}

	public void setAct_selv(Integer act_selv) {
		this.act_selv = act_selv;
	}

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public String getOrganizer() {
		return organizer;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}

	public String getAct_dept_id() {
		return act_dept_id;
	}

	public void setAct_dept_id(String act_dept_id) {
		this.act_dept_id = act_dept_id;
	}

	public String getAct_dept_name() {
		return act_dept_name;
	}

	public void setAct_dept_name(String act_dept_name) {
		this.act_dept_name = act_dept_name;
	}

	public String getAct_user() {
		return act_user;
	}

	public void setAct_user(String act_user) {
		this.act_user = act_user;
	}

	public Integer getFile_num() {
		return file_num;
	}

	public void setFile_num(Integer file_num) {
		this.file_num = file_num;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public String getStart_time_str() {
		return start_time == null ? "" : getSdf().format(start_time);
	}

	public String getEnd_time_str() {
		return end_time == null ? "" : getSdf().format(end_time);
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public String getApply_type() {
		return apply_type;
	}

	public void setApply_type(String apply_type) {
		this.apply_type = apply_type;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Integer getSecret_type() {
		return secret_type;
	}

	public void setSecret_type(Integer secret_type) {
		this.secret_type = secret_type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getSecret_leader() {
		return secret_leader;
	}

	public void setSecret_leader(String secret_leader) {
		this.secret_leader = secret_leader;
	}

	public String getSecret_director() {
		return secret_director;
	}

	public void setSecret_director(String secret_director) {
		this.secret_director = secret_director;
	}

	public String getFile_list() {
		return file_list;
	}

	public void setFile_list(String file_list) {
		this.file_list = file_list;
	}

	public String getHis_job_code() {
		return his_job_code;
	}

	public void setHis_job_code(String his_job_code) {
		this.his_job_code = his_job_code;
	}

	public String getSecret_type_name() {
		if (secret_type == null)
			return "";
		String name = "";
		switch (this.secret_type) {
		case 1:
			name = "重要涉密活动";
			break;
		case 2:
			name = "一般涉密活动";
			break;
		case 3:
			name = "领导参观";
			break;
		case 4:
			name = "涉密展览会";
			break;
		case 5:
			name = "其他";
			break;
		default:
			name = "";
			break;
		}
		return name;
	}

	public String getApply_type_name() {
		if (apply_type == null)
			return "";
		switch (this.apply_type) {
		case "1":
			return "涉密活动";
		case "2":
			return "涉密会议";
		case "3":
			return "外场实验";
		default:
			return "";
		}
	}

	public UserSecActiEvent() {
		super(JobTypeEnum.USERSEC_ACTIVITY);
	}

	public UserSecActiEvent(String user_iidd, String dept_id, String event_code, Integer seclv_code, String usage_code,
			String project_code, String summ, String apply_type, String contact, Integer secret_type, String name,
			String place, String secret_leader, String secret_director, Integer file_num, String file_list,
			String his_job_code, Date start_time, Date end_time, Integer act_selv, String sponsor, String organizer,
			String act_dept_id, String act_dept_name, String act_user) {
		super(JobTypeEnum.USERSEC_ACTIVITY, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);

		this.apply_type = apply_type;
		this.contact = contact;
		this.secret_type = secret_type;
		this.name = name;
		this.place = place;
		this.secret_leader = secret_leader;
		this.secret_director = secret_director;
		this.file_list = file_list;
		this.his_job_code = his_job_code;
		this.start_time = start_time;
		this.end_time = end_time;
		this.file_num = file_num;
		this.act_selv = act_selv;
		this.sponsor = sponsor;
		this.organizer = organizer;
		this.act_dept_id = act_dept_id;
		this.act_dept_name = act_dept_name;
		this.act_user = act_user;
	}
}