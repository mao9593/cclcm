package hdsec.web.project.publicity.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.burn.model.BurnFile;
import hdsec.web.project.common.CCLCMConstants;
import hdsec.web.project.common.bm.BMPropertyUtil;
import hdsec.web.project.common.util.PrefixFilenameFilter;
import hdsec.web.project.publicity.model.ReportEvent;
import hdsec.web.project.publicity.model.ReportFileEvent;
import hdsec.web.project.user.model.SecLevel;
import hdsec.web.project.user.model.UserSecurity;

import java.io.File;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 添加涉密活动申请
 * 
 * @author Lishu
 * 
 */
public class AddReportEventAction extends PublicityBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";// 作业流水号
	private Integer seclv_code = null;// 作业密级
	private String project_code = "";// 所属项目编号
	private String usage_code = "";// 用途编号
	private String summ = "";// 备注
	private Integer report_type = null; // 发表形式
	private Integer report_scope = null; // 发表范围
	private String report_style = null; // 报道去向
	private String report_name = ""; // 报道（资料）名称
	private Integer file_num = 0;// 上传报道个数
	private String file_list = "";// 文件列表
	private String file_seclevel = "";// 文件密级列表
	private String his_job_code = ""; // 包含改作业的历史任务列表
	// private Date apply_time = null;// 申请时间
	private String next_approver = "";// 下级审批人
	private String storepath = "";// 文档存储路径
	private List<ReportEvent> eventList = null;
	private List<BurnFile> fileList = null;
	private Integer apply_type = null;// 申请类型
	private String type = "";// 参数
	private String report_description = "";
	private String user_phone = "";
	private ReportFileEvent file = null;
	private String report_pic = null;// 报道是否含有图片
	private String other_style = "";// 报道的其他去向（用户手填）
	private String style = "";
	private ReportEvent event = null;

	private final String jobType_report1 = JobTypeEnum.EVENT_REPORT.getJobTypeCode();
	private final String jobType_report2 = JobTypeEnum.EVENT_REPORT2.getJobTypeCode();
	private final String jobType_report3 = JobTypeEnum.EVENT_REPORT3.getJobTypeCode();
	private final String jobType_deptreport = JobTypeEnum.EVENT_DEPTREPORT.getJobTypeCode();
	private final String jobType_intrapubl = JobTypeEnum.EVENT_INTRAPUBL.getJobTypeCode();
	private final String jobType_interpubl = JobTypeEnum.EVENT_INTERPUBL.getJobTypeCode();
	private String jobType = "";

	public void setFile_num(Integer file_num) {
		this.file_num = file_num;
	}

	public List<BurnFile> getFileList() {
		return fileList;
	}

	public String getStorepath() {
		return storepath;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getUsage_code() {
		return usage_code;
	}

	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code;
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public Integer getReport_type() {
		return report_type;
	}

	public void setReport_type(Integer report_type) {
		this.report_type = report_type;
	}

	public Integer getReport_scope() {
		return report_scope;
	}

	public void setReport_scope(Integer report_scope) {
		this.report_scope = report_scope;
	}

	public String getReport_style() {
		return report_style;
	}

	public void setReport_style(String report_style) {
		this.report_style = report_style;
	}

	public String getJobType() {
		String name = "";
		if (type != null) {
			switch (type) {
			case "report":
				name = JobTypeEnum.EVENT_REPORT.getJobTypeCode();
				break;
			case "deptreport":
				name = JobTypeEnum.EVENT_DEPTREPORT.getJobTypeCode();
				break;
			case "intrapubl":
				name = JobTypeEnum.EVENT_INTRAPUBL.getJobTypeCode();
				break;
			case "interpubl":
				name = JobTypeEnum.EVENT_INTERPUBL.getJobTypeCode();
				break;
			default:
				name = "";
				break;
			}
		}
		return name;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getJobType_dept() {
		return jobType_report1;
	}

	public String getJobType_deptreport() {
		return jobType_deptreport;
	}

	public String getJobType_intrapubl() {
		return jobType_intrapubl;
	}

	public String getJobType_interpubl() {
		return jobType_interpubl;
	}

	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}

	public String getProject_code() {
		return project_code;
	}

	public String getReport_name() {
		return report_name;
	}

	public void setReport_name(String report_name) {
		this.report_name = report_name;
	}

	public void setFile_list(String file_list) {
		this.file_list = file_list;
	}

	public String getHis_job_code() {
		return his_job_code;
	}

	public List<ReportEvent> getEventList() {
		return eventList;
	}

	public ReportFileEvent getFile() {
		return file;
	}

	public void setFile(ReportFileEvent file) {
		this.file = file;
	}

	public String getOther_style() {
		return other_style;
	}

	public void setOther_style(String other_style) {
		this.other_style = other_style;
	}

	public Integer getFile_num() {
		return file_num;
	}

	public String getFile_list() {
		return file_list;
	}

	public String getFile_seclevel() {
		return file_seclevel;
	}

	public String getNext_approver() {
		return next_approver;
	}

	public String getJobType_report() {
		return jobType_report1;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getJobType_report1() {
		return jobType_report1;
	}

	public String getJobType_report2() {
		return jobType_report2;
	}

	public String getJobType_report3() {
		return jobType_report3;
	}

	public void setHis_job_code(String his_job_code) {
		this.his_job_code = his_job_code;
	}

	public void setStorepath(String storepath) {
		this.storepath = storepath;
	}

	public void setEventList(List<ReportEvent> eventList) {
		this.eventList = eventList;
	}

	public void setFileList(List<BurnFile> fileList) {
		this.fileList = fileList;
	}

	// 获取所有密级
	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public List<SysUsage> getUsageList() {
		return basicService.getSysUsageList();
	}

	public List<SysProject> getProjectList() {
		return basicService.getSysProjectList();
	}

	public List<UserSecurity> getSecurityList() {
		return userService.getSecurityList();
	}

	public Integer getApply_type() {
		return apply_type;
	}

	public void setApply_type(Integer apply_type) {
		this.apply_type = apply_type;
	}

	public String getReport_description() {
		return report_description;
	}

	public void setReport_description(String report_description) {
		this.report_description = report_description;
	}

	public void setFile_seclevel(String file_seclevel) {
		this.file_seclevel = file_seclevel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getReport_pic() {
		return report_pic;
	}

	public void setReport_pic(String report_pic) {
		this.report_pic = report_pic;
	}

	public void handleFileList() throws Exception {
		// 从配置文件中读取宣传报道文件的存储路径
		String storePath = BMPropertyUtil.getReportStrorePath();
		// 在临时路径后加上用户ID
		String tempPath = storePath + File.separator + getCurUser().getUser_iidd();
		File path = new File(tempPath);
		PrefixFilenameFilter filenameFilter = new PrefixFilenameFilter(event_code + "-");
		if (path.isDirectory()) {
			File[] files = path.listFiles(filenameFilter);
			file.setFile_num(files.length);

			for (File file1 : files) {
				String fileName = file1.getName();
				fileName = fileName.substring(fileName.indexOf("-") + 1);

				try {
					moveFile(file1, storePath + File.separator + event_code, fileName);
					file.setFile_list(file.getFile_list() + fileName + CCLCMConstants.DEVIDE_SYMBOL);
					// file_seclevel = file_seclevel + seclv_file + CCLCMConstants.DEVIDE_SYMBOL;
					logger.debug("copy file:" + fileName);
				} finally {
					file1.delete();
				}
			}
			if (file.getFile_list().endsWith(CCLCMConstants.DEVIDE_SYMBOL)) {
				file.setFile_list(file.getFile_list().substring(0, file.getFile_list().length() - 1));
			}
			/*
			 * if (file_seclevel.endsWith(CCLCMConstants.DEVIDE_SYMBOL)) { file_seclevel = file_seclevel.substring(0,
			 * file_seclevel.length() - 1); }
			 */
		}
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_code)) {
			// 查询提交申请用户信息
			String user_iidd = getCurUser().getUser_iidd();
			String dept_id = getCurUser().getDept_id();
			file = new ReportFileEvent();
			handleFileList();
			// 判断申请类型
			String applytype = "";
			if (type.equals("report")) {
				apply_type = 1;
				applytype = "宣传报道保密审查";
			} else if (type.equals("deptreport")) {
				apply_type = 2;
				applytype = "部门投稿保密审查";
			} else if (type.equals("intrapubl")) {
				apply_type = 3;
				applytype = "内网信息发布保密审查";
			} else if (type.equals("interpubl")) {
				apply_type = 4;
				applytype = "外网信息发布保密审查";
			}
			/*
			 * file_seclevel = Constants.COMMON_SEPARATOR + file_seclevel + Constants.COMMON_SEPARATOR + report_pic +
			 * Constants.COMMON_SEPARATOR +"end"+ Constants.COMMON_SEPARATOR;
			 */
			ReportEvent event = new ReportEvent(user_iidd, dept_id, event_code, seclv_code, usage_code, user_phone,
					project_code, summ, apply_type, report_type, report_scope, report_style, report_name,
					file.getFile_num(), file.getFile_list(), file_seclevel, report_description, other_style);

			if (type.equals("report")) {

				String report_style = event.getReport_style();
				String[] style_detail = report_style.split("\\,");

				int i = 0;
				int max = 0;
				for (i = 0; i < style_detail.length; i++) {
					if (StringUtils.hasLength(style_detail[i].trim())) {
						if (Integer.parseInt(style_detail[i].trim()) > max) {
							max = Integer.parseInt(style_detail[i].trim());
							System.out.print("max:" + max);
						}
					}
				}

				if (max < 2) {
					event.setJobType(JobTypeEnum.valueOf(jobType_report1));
				} else if (max < 4 && max > 1) {
					event.setJobType(JobTypeEnum.valueOf(jobType_report2));
				} else if (max > 3) {
					event.setJobType(JobTypeEnum.valueOf(jobType_report3));
				}
			} else if (type.equals("deptreport")) {
				event.setJobType(JobTypeEnum.valueOf(jobType_deptreport));
			} else if (type.equals("intrapubl")) {
				event.setJobType(JobTypeEnum.valueOf(jobType_intrapubl));
			} else if (type.equals("interpubl")) {
				event.setJobType(JobTypeEnum.valueOf(jobType_interpubl));
			}
			// event.setJobType(JobTypeEnum.valueOf(jobType));
			publicityService.addReportEvent(event, next_approver);
			insertCommonLog("添加用户宣传报道申请作业[" + event_code + "]");
			return "ok";
		} else {
			if (type.equals("report")) {
				event_code = getCurUser().getUser_iidd() + "_REPORT_" + System.currentTimeMillis();
			} else if (type.equals("deptreport")) {
				event_code = getCurUser().getUser_iidd() + "_DEPTREPORT_" + System.currentTimeMillis();
			} else if (type.equals("intrapubl")) {
				event_code = getCurUser().getUser_iidd() + "_INTRAPUBL_" + System.currentTimeMillis();
			} else if (type.equals("interpubl")) {
				event_code = getCurUser().getUser_iidd() + "_INTERPUBL_" + System.currentTimeMillis();
			}
			return SUCCESS;
		}
	}
}
