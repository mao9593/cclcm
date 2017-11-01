package hdsec.web.project.secmanage.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.BaseEvent;

import java.util.Date;

/**
 * 保密工作违规处罚整改督查作业
 * 
 * @author gaoximin 2015-7-24
 */
public class PunishRectifyEvent extends BaseEvent {

	private String company_code = ""; // （处罚）单位代码:62,声光电公司；24,24所；26,26所；44,44所
	private String company_name = ""; // （处罚）单位名称
	private String punish_dept_id = ""; // 处罚/整改部门ID
	private String punish_dept_name = ""; // 处罚/整改部门
	private String duty_user_iidd = ""; // 责任/办理人ID
	private String duty_user_name = ""; // 责任/办理人
	private String describe = ""; // 违规/不合规事项描述
	private String advise = ""; // 处罚建议
	private String rectify_demand = ""; // 整改要求
	private String rectify_according = ""; // 整改依据
	private Date rectify_time = null; // 完成整改时间
	private Integer apply_type = null; // 申请业务类型
	private String p_dept_name = "";// 处罚/整改部门,根据ID连表查询

	public String getCompany_code() {
		return company_code;
	}

	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}

	public String getCompany_code_str() {
		String name = "";
		if (company_code != null) {
			switch (company_code) {
			case "62":
				name = "声光电公司";
				break;
			case "24":
				name = "24所";
				break;
			case "26":
				name = "26所";
				break;
			case "44":
				name = "44所";
				break;
			default:
				name = "";
				break;
			}
		}
		return name;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getPunish_dept_id() {
		return punish_dept_id;
	}

	public void setPunish_dept_id(String punish_dept_id) {
		this.punish_dept_id = punish_dept_id;
	}

	public String getPunish_dept_name() {
		return punish_dept_name;
	}

	public void setPunish_dept_name(String punish_dept_name) {
		this.punish_dept_name = punish_dept_name;
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

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getAdvise() {
		return advise;
	}

	public void setAdvise(String advise) {
		this.advise = advise;
	}

	public String getRectify_demand() {
		return rectify_demand;
	}

	public void setRectify_demand(String rectify_demand) {
		this.rectify_demand = rectify_demand;
	}

	public String getRectify_according() {
		return rectify_according;
	}

	public void setRectify_according(String rectify_according) {
		this.rectify_according = rectify_according;
	}

	public Date getRectify_time() {
		return rectify_time;
	}

	public void setRectify_time(Date rectify_time) {
		this.rectify_time = rectify_time;
	}

	public String getRectify_time_str() {
		return rectify_time == null ? "" : getSdf().format(rectify_time);
	}

	public Integer getApply_type() {
		return apply_type;
	}

	public void setApply_type(Integer apply_type) {
		this.apply_type = apply_type;
	}

	public String getApply_type_str() {
		String name = "";
		if (apply_type != null) {
			switch (apply_type) {
			case 0:
				name = "部门自查";
				break;
			case 1:
				name = "保密检查";
				break;
			case 2:
				name = "整改督查";
				break;
			default:
				name = "";
				break;
			}
		}
		return name;
	}

	public String getP_dept_name() {
		return p_dept_name;
	}

	public PunishRectifyEvent() {
		super(JobTypeEnum.PUNISH_DEPT);
	}

	public PunishRectifyEvent(String user_iidd, String dept_id, String event_code, Integer seclv_code,
			String usage_code, String project_code, String summ, String company_code, String company_name,
			String punish_dept_id, String punish_dept_name, String duty_user_iidd, String duty_user_name,
			String describe, String advise, String rectify_demand, String rectify_according, Date rectify_time,
			Integer apply_type) {
		super(JobTypeEnum.PUNISH_DEPT, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);

		this.company_code = company_code;
		this.company_name = company_name;
		this.punish_dept_id = punish_dept_id;
		this.punish_dept_name = punish_dept_name;
		this.duty_user_iidd = duty_user_iidd;
		this.duty_user_name = duty_user_name;
		this.describe = describe;
		this.advise = advise;
		this.rectify_demand = rectify_demand;
		this.rectify_according = rectify_according;
		this.rectify_time = rectify_time;
		this.apply_type = apply_type;
	}

}
