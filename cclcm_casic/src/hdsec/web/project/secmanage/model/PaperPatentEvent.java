package hdsec.web.project.secmanage.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.BaseEvent;

/**
 * 论文发表专利申请
 * 
 * @author gaoximin 2015-9-17
 */
public class PaperPatentEvent extends BaseEvent {

	private String contact_num = ""; // 联系电话
	private String company_code = "";// 单位代码（62,24,26,44与名称对应）
	private String company_name = "";// 单位名称（62，声光电公司；24，24所；26,26所；44,44所）
	private String file_type = "";// 类别（RESEARCH科研类，OTHERS其他,PATENT专利）
	private String title = "";// 标题
	private String send_company = "";// 报送单位
	private String file_list = "";// 文件名称
	private Integer file_num = null;// 上传文件数量

	public String getContact_num() {
		return contact_num;
	}

	public void setContact_num(String contact_num) {
		this.contact_num = contact_num;
	}

	public String getCompany_code() {
		return company_code;
	}

	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}

	public String getCompany_name() {
		return company_name;
	}

	public String getCompany_name_str() {
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

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getFile_type() {
		return file_type;
	}

	public String getFile_type_str() {
		String name = "";
		if (file_type != null) {
			switch (file_type) {
			case "RESEARCH":
				name = "科研技术类";
				break;
			case "OTHERS":
				name = "其他（政研管理）";
				break;
			case "PATENT":
				name = "专利";
				break;
			default:
				name = "";
				break;
			}
		}
		return name;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSend_company() {
		return send_company;
	}

	public void setSend_company(String send_company) {
		this.send_company = send_company;
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

	public PaperPatentEvent() {
		super(JobTypeEnum.PAPER_RESEARCH);
	}

	public PaperPatentEvent(String event_code, String user_iidd, String dept_id, Integer seclv_code, String usage_code,
			String project_code, String summ, String contact_num, String company_code, String company_name,
			String file_type, String title, String send_company, String file_list, Integer file_num) {
		super(JobTypeEnum.PAPER_RESEARCH, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);
		this.contact_num = contact_num;
		this.company_code = company_code;
		this.company_name = company_name;
		this.file_type = file_type;
		this.title = title;
		this.send_company = send_company;
		this.file_list = file_list;
		this.file_num = file_num;
	}

}