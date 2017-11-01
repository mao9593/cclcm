package hdsec.web.project.secmanage.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.secmanage.model.FileListEvent;
import hdsec.web.project.secmanage.model.PaperPatentEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 提交论文发表专利申请申请
 * 
 * @author gaoximin 2015-9-16
 */
public class AddPaperPatentEventAction extends SecManageBaseAction {

	private static final long serialVersionUID = 1L;

	private String contact_num = ""; // 联系电话
	private String company_code = "";// 单位代码（1,2,3,4与名称对应）
	private String company_name = "";// 单位名称（1，声光电；2，24所；3,26所；4,44所）
	private String file_type = "";// 类别（RESEARCH科研类，OTHERS其他，PATENT专利）
	private String title = "";// 标题
	private String send_company = "";// 报送单位
	private String event_code = "";
	private String next_approver = "";
	private Integer seclv_code = null;
	private String project_code = "";// 所属项目编号
	private String usage_code = "";// 用途编号
	private String summ = "";// 备注
	private final String jobType_research = JobTypeEnum.PAPER_RESEARCH.getJobTypeCode();
	private final String jobType_others = JobTypeEnum.PAPER_OTHERS.getJobTypeCode();
	private final String jobType_patent = JobTypeEnum.PAPERPATENT.getJobTypeCode();
	private String jobType = "";
	private String type = "";// 参数
	private FileListEvent file = null;

	public void setContact_num(String contact_num) {
		this.contact_num = contact_num;
	}

	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setSend_company(String send_company) {
		this.send_company = send_company;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}

	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public String getNext_approver() {
		return next_approver;
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public FileListEvent getFile() {
		return file;
	}

	public void setFile(FileListEvent file) {
		this.file = file;
	}

	public String getContact_num() {
		return contact_num;
	}

	public String getCompany_code() {
		return company_code;
	}

	public String getCompany_name() {
		return company_name;
	}

	public String getFile_type() {
		return file_type;
	}

	public String getTitle() {
		return title;
	}

	public String getSend_company() {
		return send_company;
	}

	public String getProject_code() {
		return project_code;
	}

	public String getJobType() {
		String name = "";
		if (file_type != null) {
			switch (file_type) {
			case "RESEARCH":
				name = JobTypeEnum.PAPER_RESEARCH.getJobTypeCode();
				break;
			case "OTHERS":
				name = JobTypeEnum.PAPER_OTHERS.getJobTypeCode();
				break;
			case "PATENT":
				name = JobTypeEnum.PAPERPATENT.getJobTypeCode();
				break;

			default:
				name = "";
				break;
			}
		}
		return name;
	}

	public String getUsage_code() {
		return usage_code;
	}

	public String getSumm() {
		return summ;
	}

	public String getJobType_research() {
		return jobType_research;
	}

	public String getJobType_others() {
		return jobType_others;
	}

	public String getJobType_patent() {
		return jobType_patent;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_code)) {
			String user_iidd = getCurUser().getUser_iidd();
			String dept_id = getCurUser().getDept_id();

			file = new FileListEvent();
			handleFileList(event_code, file, "");

			PaperPatentEvent event = new PaperPatentEvent(event_code, user_iidd, dept_id, seclv_code, usage_code,
					project_code, summ, contact_num, company_code, company_name, file_type, title, send_company,
					file.getFile_list(), file.getFile_num());
			if (file_type.equals("RESEARCH")) {
				event.setJobType(JobTypeEnum.valueOf(jobType_research));
			} else if (file_type.equals("OTHERS")) {
				event.setJobType(JobTypeEnum.valueOf(jobType_others));
			} else if (file_type.equals("PATENT")) {
				event.setJobType(JobTypeEnum.valueOf(jobType_patent));
			}
			secManageService.addPaperPatentEvent(event, next_approver);
			insertCommonLog("添加专利申请论文发表申请[" + event_code + "]");
			return "ok";
		} else {
			/*
			 * if (file_type.equals("RESEARCH")) { event_code = getCurUser().getUser_iidd() + "_RESEARCH_" +
			 * System.currentTimeMillis(); } else if (file_type.equals("OTHERS")) { event_code =
			 * getCurUser().getUser_iidd() + "_OTHERS_" + System.currentTimeMillis(); } else if
			 * (file_type.equals("PATENT")) { event_code = getCurUser().getUser_iidd() + "_PATENT_" +
			 * System.currentTimeMillis(); }
			 */
			event_code = getCurUser().getUser_iidd() + "_PAPERPATENT_" + System.currentTimeMillis();
			return SUCCESS;
		}
	}
}