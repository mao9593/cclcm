package hdsec.web.project.publicity.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.BaseEvent;

/**
 * 添加宣传报道审查类
 * 
 * @author LS
 * 
 */
public class ReportEvent extends BaseEvent {
	private static final long serialVersionUID = 1L;
	private Integer report_type = null; // 发表形式
	private Integer report_scope = null; // 发表范围
	private String report_style = null; // 报道去向
	private String report_name = ""; // 报道（资料）名称
	private Integer file_num = 0;// 上传报道个数
	private String file_list = "";// 文件列表
	private String file_seclevel = "";// 文件密级列表
	private String storepath = "";// 文档存储路径
	private Integer apply_type = null;// 申请类型
	private String report_description = "";// 资料内容
	private String user_phone = "";// 联系电话
	private String other_style = "";// 报道的其他去向（用户手填）

	public String getReport_style() {
		return report_style;
	}

	public void setReport_style(String report_style) {
		this.report_style = report_style;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getFile_num() {
		return file_num;
	}

	public String getFile_seclevel() {
		return file_seclevel;
	}

	public void setStorepath(String storepath) {
		this.storepath = storepath;
	}

	public void setFile_num(Integer file_num) {
		this.file_num = file_num;
	}

	public String getStorepath() {
		return storepath;
	}

	public Integer getReport_scope() {
		return report_scope;
	}

	public void setReport_scope(Integer report_scope) {
		this.report_scope = report_scope;
	}

	public String getReport_name() {
		return report_name;
	}

	public void setReport_name(String report_name) {
		this.report_name = report_name;
	}

	public String getReport_description() {
		return report_description;
	}

	public void setReport_description(String report_description) {
		this.report_description = report_description;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public void setFile_list(String file_list) {
		this.file_list = file_list;
	}

	public String getFile_list() {
		return file_list;
	}

	public Integer getReport_type() {
		return report_type;
	}

	public void setReport_type(Integer report_type) {
		this.report_type = report_type;
	}

	public void setFile_seclevel(String file_seclevel) {
		this.file_seclevel = file_seclevel;
	}

	public Integer getApply_type() {
		return apply_type;
	}

	public void setApply_type(Integer apply_type) {
		this.apply_type = apply_type;
	}

	public String getOther_style() {
		return other_style;
	}

	public void setOther_style(String other_style) {
		this.other_style = other_style;
	}

	public String getApply_type_str() {
		String name = "";
		if (apply_type != null) {
			switch (apply_type) {
			case 1:
				name = "宣传报道";
				break;
			case 2:
				name = "部门投稿";
				break;
			case 3:
				name = "内网信息发布";
				break;
			case 4:
				name = "外网信息发布";
				break;
			default:
				name = "";
				break;
			}
		}
		return name;
	}

	/*
	 * public String getReport_style_name() { String name = ""; switch (this.report_style) { case "1": name = "所内涉密网";
	 * break; case "2": name = "对外宣传网"; break; case "3": name = "集团科研网"; break; case "4": name = "电科重庆"; break; case
	 * "5": name = "信息报送"; break; case "6": name = "集团公司《中国电科技》"; break; case "7": name = "上级机关或部门"; break;
	 * 
	 * case "8": name = "其他"; break; default: name = ""; break; } return name; }
	 */

	public ReportEvent() {
		super(JobTypeEnum.EVENT_REPORT);
	}

	public ReportEvent(String user_iidd, String dept_id, String event_code, Integer seclv_code, String usage_code,
			String user_phone, String project_code, String summ, Integer apply_type, Integer report_type,
			Integer report_scope, String report_style, String report_name, Integer file_num, String file_list,
			String file_seclevel, String report_description, String other_style) {
		super(JobTypeEnum.EVENT_REPORT, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);

		this.user_phone = user_phone;
		this.apply_type = apply_type;
		this.report_type = report_type;
		this.report_scope = report_scope;
		this.report_style = report_style;
		this.report_name = report_name;
		this.file_num = file_num;
		this.file_list = file_list;
		this.file_seclevel = file_seclevel;
		this.report_description = report_description;
		this.other_style = other_style;
	}
}