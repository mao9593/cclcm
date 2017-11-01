package hdsec.web.project.secmanage.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.BaseEvent;
import hdsec.web.project.common.util.Constants;

/**
 * 涉密文件（资料）外出制作作业
 * 
 * @author gaoximin 2015-7-23
 */
public class FileOutMakeEvent extends BaseEvent {

	private String kind = ""; // 申请种类（0印刷，1胶装，2特殊制作，3其他）多选时用|分隔
	private String file_name = ""; // 文件（资料）名称
	private Integer file_count = null; // 装订份数
	private Integer file_page = null; // 每份页数
	private Integer file_seclv_code = null; // 文件密级
	private String reason = ""; // 申请理由
	private String accompany_iidd = ""; // 陪同人员ID
	private String accompany_name = ""; // 陪同人员
	private String make_company = ""; // 制作单位

	private String file_seclv_name = "";// 文件密级名称

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getKind_str() {
		String str = "";
		for (String temp : kind.split(Constants.COMMON_SEPARATOR)) {
			if (temp.equals("0")) {
				str += "印刷" + " ";
			}
			if (temp.equals("1")) {
				str += "胶装" + " ";
			}
			if (temp.equals("2")) {
				str += "特殊制作" + " ";
			}
			if (temp.equals("3")) {
				str += "其他";
			}
		}
		return str;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public Integer getFile_count() {
		return file_count;
	}

	public void setFile_count(Integer file_count) {
		this.file_count = file_count;
	}

	public Integer getFile_page() {
		return file_page;
	}

	public void setFile_page(Integer file_page) {
		this.file_page = file_page;
	}

	public Integer getFile_seclv_code() {
		return file_seclv_code;
	}

	public void setFile_seclv_code(Integer file_seclv_code) {
		this.file_seclv_code = file_seclv_code;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getAccompany_iidd() {
		return accompany_iidd;
	}

	public void setAccompany_iidd(String accompany_iidd) {
		this.accompany_iidd = accompany_iidd;
	}

	public String getAccompany_name() {
		return accompany_name;
	}

	public void setAccompany_name(String accompany_name) {
		this.accompany_name = accompany_name;
	}

	public String getMake_company() {
		return make_company;
	}

	public void setMake_company(String make_company) {
		this.make_company = make_company;
	}

	public String getFile_seclv_name() {
		return file_seclv_name;
	}

	public void setFile_seclv_name(String file_seclv_name) {
		this.file_seclv_name = file_seclv_name;
	}

	public FileOutMakeEvent() {
		super(JobTypeEnum.FILEOUTMAKE);
	}

	public FileOutMakeEvent(String user_iidd, String dept_id, String event_code, Integer seclv_code, String usage_code,
			String project_code, String summ, String kind, String file_name, Integer file_count, Integer file_page,
			Integer file_seclv_code, String reason, String accompany_iidd, String accompany_name, String make_company) {
		super(JobTypeEnum.FILEOUTMAKE, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);

		this.kind = kind;
		this.file_name = file_name;
		this.file_count = file_count;
		this.file_page = file_page;
		this.file_seclv_code = file_seclv_code;
		this.reason = reason;
		this.accompany_iidd = accompany_iidd;
		this.accompany_name = accompany_name;
		this.make_company = make_company;

	}

}
