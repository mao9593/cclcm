package hdsec.web.project.copy.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.BaseEvent;

/**
 * 复印作业类
 * 
 * @author lixiang
 * 
 */

public class CopyEvent extends BaseEvent {
	private String originalid = ""; // 原文件编号
	private String file_name = "";// 文件名称
	private Integer copy_num = null; // 复印份数
	private Integer page_num = null; // 每份页数
	private String source = ""; // 来源
	private String place = ""; // 复印地点
	private Integer is_double = 1; // 单双面(1单面；2双面左右翻；3双面上下翻)
	private String page_type = ""; // 纸张类型
	private Integer scale = null; // 缩放
	private Integer orientation = 1; // 横纵向（1纵向；2横向）
	private Integer color = 1;// 颜色（1黑白；2彩色）
	private Integer copy_status = 0;// 复印状态(0未复印；1已复印)
	private Integer is_barcode = 0;// 是否已打印条码(0未打印条码；1已打印条码)
	private String cycle_type;// 去向
	private String period = "L";// 使用状态（L 长期留用，S 短期留用）
	private String copy_type = "internal";// 复印类型（internal 内部，external 外来文件）
	private String copy_merge = "";// 是否合并复印（yes 合并复印，no 不合并复印）
	private String ori_id = "";
    private String print_eventcode = "PrintEventCode"; // 打印复印合并专用，对应打印作业号
    
	public String getOriginalid() {
		return originalid;
	}

	public void setOriginalid(String originalid) {
		this.originalid = originalid;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public Integer getCopy_num() {
		return copy_num;
	}

	public void setCopy_num(Integer copy_num) {
		this.copy_num = copy_num;
	}

	public Integer getPage_num() {
		return page_num;
	}

	public void setPage_num(Integer page_num) {
		this.page_num = page_num;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Integer getIs_double() {
		return is_double;
	}

	public String getIs_double_name() {
		switch (is_double) {
		case 1:
			return "单面";
		case 2:
			return "双面左右翻";
		case 3:
			return "双面上下翻";
		default:
			return "其他";
		}
	}

	public void setIs_double(Integer is_double) {
		this.is_double = is_double;
	}

	public String getPage_type() {
		return page_type;
	}

	public void setPage_type(String page_type) {
		this.page_type = page_type;
	}

	public Integer getScale() {
		return scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

	public Integer getOrientation() {
		return orientation;
	}

	public String getOrientation_name() {
		switch (orientation) {
		case 1:
			return "纵向";
		case 2:
			return "横向";
		default:
			return "其他";
		}
	}

	public void setOrientation(Integer orientation) {
		this.orientation = orientation;
	}

	public Integer getColor() {
		return color;
	}

	public String getColor_name() {
		switch (color) {
		case 1:
			return "黑白";
		case 2:
			return "彩色";
		default:
			return "未定义";
		}
	}

	public void setColor(Integer color) {
		this.color = color;
	}

	public CopyEvent() {
		super(JobTypeEnum.COPY);
	}

	public Integer getCopy_status() {
		return copy_status;
	}

	public void setCopy_status(Integer copy_status) {
		this.copy_status = copy_status;
	}

	public String getCopy_status_name() {
		return copy_status == 1 ? "已复印" : "未复印";
	}

	public Integer getIs_barcode() {
		return is_barcode;
	}

	public void setIs_barcode(Integer is_barcode) {
		this.is_barcode = is_barcode;
	}

	public String getIs_barcode_name() {
		return is_barcode == 1 ? "条码已打印" : "打印条码";
	}

	public String getCycle_type() {
		return cycle_type;
	}

	public String getCycle_type_name() {
		if ("REMAIN".equals(cycle_type)) {
			return "留用";
		}
		if ("FILE".equals(cycle_type)) {
			return "归档";
		}
		if ("SEND".equals(cycle_type)) {
			return "外发";
		}
		return cycle_type;
	}

	public void setCycle_type(String cycle_type) {
		this.cycle_type = cycle_type;
	}

	public String getPeriod() {
		return period;
	}

	public String getPeriod_name() {
		String period_name = "";
		period_name = period.equals("L") ? "长期留用" : "短期留用";
		return period_name;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getCopy_type() {
		return copy_type;
	}

	public void setCopy_type(String copy_type) {
		this.copy_type = copy_type;
	}

	public String getCopy_merge() {
		return copy_merge;
	}

	public void setCopy_merge(String copy_merge) {
		this.copy_merge = copy_merge;
	}

	public String getOri_id() {
		return ori_id;
	}

	public void setOri_id(String ori_id) {
		this.ori_id = ori_id;
	}

	public String getCopy_type_name() {
		if ("internal".equals(copy_type)) {
			return "内部复印";
		}
		if ("external".equals(copy_type)) {
			return "外来文件复印";
		}
		if ("merge".equals(copy_type)) {
			return "合并复印";
		}

		return copy_type;
	}

	public String getPrint_eventcode() {
		return print_eventcode;
	}

	public void setPrint_eventcode(String print_eventcode) {
		this.print_eventcode = print_eventcode;
	}

	public CopyEvent(String user_iidd, String dept_id, String event_code, Integer seclv_code, String usage_code,
			String project_code, String summ, String originalid, String file_name, Integer copy_num, Integer page_num,
			String source, String place, Integer is_double, String page_type, Integer scale, Integer orientation,
			Integer color, Integer copy_status, String print_eventcode,String temp) {
		super(JobTypeEnum.COPY, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);

		this.originalid = originalid;
		this.file_name = file_name;
		this.copy_num = copy_num;
		this.page_num = page_num;
		this.source = source;
		this.place = place;
		this.is_double = is_double;
		this.page_type = page_type;
		this.scale = scale;
		this.orientation = orientation;
		this.color = color;
		this.copy_status = copy_status;
		this.print_eventcode = print_eventcode;
	}

	public CopyEvent(String user_iidd, String dept_id, String event_code, Integer seclv_code, String usage_code,
			String project_code, String summ, String originalid, String file_name, Integer copy_num, Integer page_num,
			String source, String place, Integer is_double, String page_type, Integer scale, Integer orientation,
			Integer color, Integer copy_status) {
		super(JobTypeEnum.COPY, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);

		this.originalid = originalid;
		this.file_name = file_name;
		this.copy_num = copy_num;
		this.page_num = page_num;
		this.source = source;
		this.place = place;
		this.is_double = is_double;
		this.page_type = page_type;
		this.scale = scale;
		this.orientation = orientation;
		this.color = color;
		this.copy_status = copy_status;
	}

	public CopyEvent(String user_iidd, String dept_id, String event_code, Integer seclv_code, String usage_code,
			String project_code, String summ, String originalid, String file_name, Integer copy_num, Integer page_num,
			String source, String place, Integer is_double, String page_type, Integer scale, Integer orientation,
			Integer color, Integer copy_status, String copy_merge) {
		super(JobTypeEnum.COPY, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);

		this.originalid = originalid;
		this.file_name = file_name;
		this.copy_num = copy_num;
		this.page_num = page_num;
		this.source = source;
		this.place = place;
		this.is_double = is_double;
		this.page_type = page_type;
		this.scale = scale;
		this.orientation = orientation;
		this.color = color;
		this.copy_status = copy_status;
		this.copy_merge = copy_merge;
	}
}