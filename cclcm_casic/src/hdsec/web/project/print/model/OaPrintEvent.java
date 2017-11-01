package hdsec.web.project.print.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.BaseEvent;

/**
 * oa特殊打印类
 * 
 * @author guojiao
 * 
 */
public class OaPrintEvent extends BaseEvent {
	private String paper_name = "";// 文件名称
	private Integer page_num = null; // 页数
	private Integer paper_num = null; // 份数
	private String make_type = ""; // 制作类别（系统管理员->运行管理下进行“特殊打印类型管理”配置）
	private Integer file_selv = null;// 文件密级
	private Integer print_direct = null;// 纵横向
	private Integer print_double = null;// 单双面（1、单面2、双面）
	private Integer paper_color = null;// 色彩(1、黑白2、彩色)
	private String paper_kind = "";// 纸张类型
	private Integer file_num = null;// 文件数量
	private String file_list = "";// 文件列表
	private Integer paper_status = null; // 文件状态(0未录入,1已录入)
	private String manager_user_name = ""; // 管理员名称
	private String manager_user_iidd = "";// 管理员ID
	private String manager_dept_name = ""; // 管理员名称
	private String manager_dept_iidd = "";// 管理员ID
	private String comment = "";// 备注
	private String file_sname = "";// 文件密级名称
	private String make_type_str = "";

	public String getMake_type() {
		return make_type;
	}

	public Integer getPrint_direct() {
		return print_direct;
	}

	public Integer getPrint_double() {
		return print_double;
	}

	public Integer getPaper_color() {
		return paper_color;
	}

	public String getFile_sname() {
		return file_sname;
	}

	public void setFile_sname(String file_sname) {
		this.file_sname = file_sname;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPaper_name() {
		return paper_name;
	}

	public void setPaper_name(String paper_name) {
		this.paper_name = paper_name;
	}

	public Integer getPage_num() {
		return page_num;
	}

	public void setPage_num(Integer page_num) {
		this.page_num = page_num;
	}

	public Integer getPaper_num() {
		return paper_num;
	}

	public void setPaper_num(Integer paper_num) {
		this.paper_num = paper_num;
	}

	public String getMake_type_str() {
		return make_type_str;
	}

	public void setMake_type(String make_type) {
		this.make_type = make_type;
	}

	public Integer getFile_selv() {
		return file_selv;
	}

	public void setFile_selv(Integer file_selv) {
		this.file_selv = file_selv;
	}

	public String getPrint_direct_str() {
		if (print_direct == null) {
			return "";
		}
		switch (print_direct) {
		case 1:
			return "纵向";
		case 2:
			return "横向";
		default:
			return "";
		}
	}

	public void setPrint_direct(Integer print_direct) {
		this.print_direct = print_direct;
	}

	public String getPrint_double_str() {
		if (print_double == null) {
			return "";
		}
		switch (print_double) {
		case 1:
			return "单面";
		case 2:
			return "双面左右翻";
		case 3:
			return "双面上下翻";
		default:
			return "";
		}
	}

	public void setPrint_double(Integer print_double) {
		this.print_double = print_double;
	}

	public String getPaper_color_str() {
		if (paper_color == null) {
			return "";
		}
		switch (paper_color) {
		case 1:
			return "黑白";
		case 2:
			return "彩色";
		default:
			return "";
		}
	}

	public String getPaper_status_str() {
		if (paper_status == null) {
			return "";
		}
		switch (paper_status) {
		case 0:
			return "未打印";
		case 1:
			return "已打印";
		default:
			return "";
		}
	}

	public void setPaper_color(Integer paper_color) {
		this.paper_color = paper_color;
	}

	public String getPaper_kind() {
		return paper_kind;
	}

	public void setPaper_kind(String paper_kind) {
		this.paper_kind = paper_kind;
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

	public Integer getPaper_status() {
		return paper_status;
	}

	public void setPaper_status(Integer paper_status) {
		this.paper_status = paper_status;
	}

	public String getManager_user_name() {
		return manager_user_name;
	}

	public void setManager_user_name(String manager_user_name) {
		this.manager_user_name = manager_user_name;
	}

	public String getManager_user_iidd() {
		return manager_user_iidd;
	}

	public void setManager_user_iidd(String manager_user_iidd) {
		this.manager_user_iidd = manager_user_iidd;
	}

	public String getManager_dept_name() {
		return manager_dept_name;
	}

	public void setManager_dept_name(String manager_dept_name) {
		this.manager_dept_name = manager_dept_name;
	}

	public String getManager_dept_iidd() {
		return manager_dept_iidd;
	}

	public void setManager_dept_iidd(String manager_dept_iidd) {
		this.manager_dept_iidd = manager_dept_iidd;
	}

	public OaPrintEvent() {
		super(JobTypeEnum.SPECIAL_PRINT);
	}

	public OaPrintEvent(String user_iidd, String dept_id, String event_code, Integer seclv_code, String usage_code,
			String project_code, String summ, String paper_name, Integer page_num, Integer paper_num, String make_type,
			Integer file_selv, Integer print_direct, Integer print_double, Integer paper_color, String paper_kind,
			Integer file_num, String file_list, String comment) {

		super(JobTypeEnum.SPECIAL_PRINT, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);

		this.paper_name = paper_name;
		this.page_num = page_num;
		this.paper_num = paper_num;
		this.make_type = make_type;
		this.file_selv = file_selv;
		this.print_direct = print_direct;
		this.print_double = print_double;
		this.paper_color = paper_color;
		this.paper_kind = paper_kind;
		this.file_num = file_num;
		this.file_list = file_list;
		this.comment = comment;
	}
}
