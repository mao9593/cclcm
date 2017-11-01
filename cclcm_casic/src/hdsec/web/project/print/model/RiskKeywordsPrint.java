package hdsec.web.project.print.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.BaseEvent;

/**
 * 邦辰关键字结果信息类
 * 
 * @author haojia
 * 
 */

public class RiskKeywordsPrint extends BaseEvent {
	private Integer dura_tion = null; // 保密期限
	private String extent = ""; // 知悉范围
	private String file_title = ""; // 文件名
	private String st_filename = ""; // 压缩包文件名
	private String dl_filename = "";// 原文件名
	private String place = ""; // 打印地点（东区、西区）
	private String fileid = ""; // 文号-所发XXX号文
	private Integer print_count = null;// 份数
	private Integer page_count = null; // 页数
	private String page_size = ""; // 纸张
	private Integer color = null;// 颜色，0未定义；1黑白；2彩色
	private Integer print_direct = null; // 横纵向，保留暂不使用
	private Integer print_double = null; // 单双面长短篇翻页(1:单面；2:纵向;3:横向)
	private Integer print_collate = null; // 逐份逐页(0逐页;1逐份)
	private Integer file_size = null; // 文件大小
	private Integer state_flag = null; // 审批中，未审批等状态
	private Integer print_status = null;// 是否打印，0未打印1已打印2 可继续打印3超时不可打印4超时不可补打
	private Integer is_lock = null; // 0不锁定 1锁定
	private Integer illegelword = null; // 是否存在敏感词（0不存在 1存在）
	private Integer remaintimes = null; // 可打印次数
	private Integer fixednum = null; // 已补打的份数
	private String reserved = ""; // 保留
	private String cycle_type;// 去向
	private String period = "S";// 短期/长期
	private int print_type;// 打印类型 普通，拼图，替换页打印
	private int part_num;// 打印份数
	private String keyword_content = "";// 关键字
	private String PID_barcode = "";// 主台账条码信息
	private String PID_pagenum = "";// 主台账中被替换的页码
	private Integer event_is_read = 0; // 审批时该文件是否被预览
	private String policy = "";// 邦辰关键字检查结果：通过or不通过

	// 邦辰关键字结果信息相关字段
	private String tid = "";
	private String riskclass = "";
	private String hitcount = "";
	private String levelname = "";
	private String sensitvecontents = "";
	private String filename = "";
	private String filetype = "";

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getRiskclass() {
		return riskclass;
	}

	public void setRiskclass(String riskclass) {
		this.riskclass = riskclass;
	}

	public String getHitcount() {
		return hitcount;
	}

	public void setHitcount(String hitcount) {
		this.hitcount = hitcount;
	}

	public String getLevelname() {
		return levelname;
	}

	public void setLevelname(String levelname) {
		this.levelname = levelname;
	}

	public String getSensitvecontents() {
		return sensitvecontents;
	}

	public void setSensitvecontents(String sensitvecontents) {
		this.sensitvecontents = sensitvecontents;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public String getPolicy() {
		return policy;
	}

	public void setPolicy(String policy) {
		this.policy = policy;
	}

	public Integer getEvent_is_read() {
		return event_is_read;
	}

	public void setEvent_is_read(Integer event_is_read) {
		this.event_is_read = event_is_read;
	}

	public Integer getDura_tion() {
		return dura_tion;
	}

	public void setDura_tion(Integer dura_tion) {
		this.dura_tion = dura_tion;
	}

	public String getExtent() {
		return extent;
	}

	public void setExtent(String extent) {
		this.extent = extent;
	}

	public String getSt_filename() {
		return st_filename;
	}

	public void setSt_filename(String st_filename) {
		this.st_filename = st_filename;
	}

	public String getFile_title() {
		return file_title;
	}

	public void setFile_title(String file_title) {
		this.file_title = file_title;
	}

	public String getDl_filename() {
		return dl_filename;
	}

	public void setDl_filename(String dl_filename) {
		this.dl_filename = dl_filename;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getFileid() {
		return fileid;
	}

	public void setFileid(String fileid) {
		this.fileid = fileid;
	}

	public Integer getPrint_count() {
		return print_count;
	}

	public void setPrint_count(Integer print_count) {
		this.print_count = print_count;
	}

	public Integer getPage_count() {
		return page_count;
	}

	public void setPage_count(Integer page_count) {
		this.page_count = page_count;
	}

	public String getPage_size() {
		return page_size;
	}

	public void setPage_size(String page_size) {
		this.page_size = page_size;
	}

	public Integer getColor() {
		return color;
	}

	public void setColor(Integer color) {
		this.color = color;
	}

	public String getColor_name() {
		if (color == null) {
			return "未知";
		}
		switch (color) {
		case 1:
			return "黑白";
		case 2:
			return "彩色";
		default:
			return "未知";
		}
	}

	public Integer getPrint_direct() {
		return print_direct;
	}

	public void setPrint_direct(Integer print_direct) {
		this.print_direct = print_direct;
	}

	public Integer getPrint_double() {
		return print_double;
	}

	public String getPrint_double_name() {
		if (print_double == null) {
			return "未知";
		}
		switch (print_double) {
		case 1:
			return "单面";
		case 2:
			return "双面左右翻";
		case 3:
			return "双面上下翻";
		default:
			return "未知";
		}
	}

	public void setPrint_double(Integer print_double) {
		this.print_double = print_double;
	}

	public Integer getPrint_collate() {
		return print_collate;
	}

	public void setPrint_collate(Integer print_collate) {
		this.print_collate = print_collate;
	}

	public Integer getFile_size() {
		return file_size;
	}

	public void setFile_size(Integer file_size) {
		this.file_size = file_size;
	}

	public Integer getState_flag() {
		return state_flag;
	}

	public void setState_flag(Integer state_flag) {
		this.state_flag = state_flag;
	}

	public String getPrint_status_name() {
		String name = "";
		switch (this.print_status) {
		case 0:
			name = "未打印";
			break;
		case 1:
			name = "已打印";
			break;
		case 2:
			name = "打印中断";
			break;
		case 3:
			name = "超时不可打印";
			break;
		case 4:
			name = "超过不可补打";
			break;
		}
		return name;
	}

	public Integer getPrint_status() {
		return print_status;
	}

	public void setPrint_status(Integer print_status) {
		this.print_status = print_status;
	}

	public Integer getIs_lock() {
		return is_lock;
	}

	public void setIs_lock(Integer is_lock) {
		this.is_lock = is_lock;
	}

	public Integer getIllegelword() {
		return illegelword;
	}

	public void setIllegelword(Integer illegelword) {
		this.illegelword = illegelword;
	}

	public Integer getRemaintimes() {
		return remaintimes;
	}

	public void setRemaintimes(Integer remaintimes) {
		this.remaintimes = remaintimes;
	}

	public Integer getFixednum() {
		return fixednum;
	}

	public void setFixednum(Integer fixednum) {
		this.fixednum = fixednum;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}

	public RiskKeywordsPrint() {
		super(JobTypeEnum.PRINT_REMAIN);
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
		if ("S".equals(period)) {
			return "短期";
		}
		if ("L".equals(period)) {
			return "长期";
		}
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public int getPrint_type() {
		return print_type;
	}

	public String getPrint_type_name() {
		// return print_type == 2 ? "拼图打印" : "普通打印";
		if (print_type == 3) {
			return "替换页打印";
		} else if (print_type == 2) {
			return "拼图打印";
		} else if (print_type == 4) {
			return "针式打印";
		} else {
			return "普通打印";
		}
	}

	public String getPolicy_name() {
		if (policy != null) {
			if ("pass".equals(policy)) {

				return "通过";
			} else {
				return "未通过";
			}
		} else {
			return "未通过";
		}
	}

	public void setPrint_type(int print_type) {
		this.print_type = print_type;
	}

	public int getPart_num() {
		return part_num;
	}

	public void setPart_num(int part_num) {
		this.part_num = part_num;
	}

	public String getKeyword_content() {
		return keyword_content;
	}

	public void setKeyword_content(String keyword_content) {
		this.keyword_content = keyword_content;
	}

	public String getPID_barcode() {
		return PID_barcode;
	}

	public void setPID_barcode(String pID_barcode) {
		PID_barcode = pID_barcode;
	}

	public String getPID_pagenum() {
		return PID_pagenum;
	}

	public void setPID_pagenum(String pID_pagenum) {
		PID_pagenum = pID_pagenum;
	}

	public RiskKeywordsPrint(String tid, String riskclass, String hitcount, String levelname, String sensitvecontents,
			String filename, String filetype) {
		super();
		this.tid = tid;
		this.riskclass = riskclass;
		this.hitcount = hitcount;
		this.levelname = levelname;
		this.sensitvecontents = sensitvecontents;
		this.filename = filename;
		this.filetype = filetype;
	}

}
