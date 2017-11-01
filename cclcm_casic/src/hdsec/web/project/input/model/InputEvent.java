package hdsec.web.project.input.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.BaseEvent;

import java.util.Date;

/**
 * 电子文件导入管理模块
 * 
 * @author guoxh 2016-10-8 18:31:45
 */
public class InputEvent extends BaseEvent {
	private String message_usage = "";// 信息用途
	private String personal = "";// 单位个人
	private String file_seclevel = "";// 文件密级
	private String address = "";// 网上/邮件地址
	private Integer file_num = null;// 文件数量
	private String med_type = "";// 输入介质类型（1、U盘2、光盘3、存储卡、4其他）
	private Date operate_time = null;// 操作时间
	private String cd_num = "";// 中转盘号
	private String internet_num = "";// 互联网盘号
	private Integer input_state = 0;// 输入状态
	private String file_list = "";// 文件名称
	private Date start_time = null;// 申请时间

	public String getMessage_usage() {
		return message_usage;
	}

	public void setMessage_usage(String message_usage) {
		this.message_usage = message_usage;
	}

	public String getPersonal() {
		return personal;
	}

	public void setPersonal(String personal) {
		this.personal = personal;
	}

	public String getFile_seclevel() {
		return file_seclevel;
	}

	public void setFile_seclevel(String file_seclevel) {
		this.file_seclevel = file_seclevel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getFile_num() {
		return file_num;
	}

	public void setFile_num(Integer file_num) {
		this.file_num = file_num;
	}

	public String getMed_type() {
		return med_type;
	}

	public void setMed_type(String med_type) {
		this.med_type = med_type;
	}

	public Date getOperate_time() {
		return operate_time;

	}

	public String getOperate_time_str() {
		return operate_time == null ? "" : getSdf().format(operate_time);
	}

	public void setOperate_time(Date operate_time) {
		this.operate_time = operate_time;
	}

	public String getCd_num() {
		return cd_num;
	}

	public void setCd_num(String cd_num) {
		this.cd_num = cd_num;
	}

	public String getInternet_num() {
		return internet_num;
	}

	public void setInternet_num(String internet_num) {
		this.internet_num = internet_num;
	}

	public Integer getInput_state() {
		return input_state;
	}

	public void setInput_state(Integer input_state) {
		this.input_state = input_state;
	}

	public String getFile_list() {
		return file_list;
	}

	public void setFile_list(String file_list) {
		this.file_list = file_list;
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

	public InputEvent(String personal, String file_seclevel, String address, Integer file_num, String med_type,
			Date operate_time, String cd_num, String internet_num, Integer input_state, String file_list,
			JobTypeEnum jobType, String event_code, String user_iidd, String dept_id, Integer seclv_code,
			String usage_code, String project_code, String summ) {
		super(jobType, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);

		this.personal = personal;
		this.file_seclevel = file_seclevel;
		this.address = address;
		this.file_num = file_num;
		this.med_type = med_type;
		this.operate_time = operate_time;
		this.cd_num = cd_num;
		this.internet_num = internet_num;
		this.input_state = input_state;
		this.file_list = file_list;
	}

	public InputEvent(String event_code, String personal, String file_seclevel, String address, Integer file_num,
			String med_type, String file_list, JobTypeEnum jobType, String user_iidd, String dept_id,
			Integer seclv_code, String usage_code, String project_code, String summ, String cd_num, String internet_num) {
		super(jobType, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);

		this.personal = personal;
		this.file_seclevel = file_seclevel;
		this.address = address;
		this.file_num = file_num;
		this.med_type = med_type;
		// this.input_state = input_state;
		this.file_list = file_list;
		this.cd_num = cd_num;
		this.internet_num = internet_num;
	}

	public InputEvent() {
		super();
	}

	public InputEvent(JobTypeEnum jobType) {
		super(jobType);
	}

	public InputEvent(String event_code, String user_iidd, String dept_id, Integer seclv_code, String usage_code) {
		super(event_code, user_iidd, dept_id, seclv_code, usage_code);
	}
}
