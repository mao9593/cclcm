package hdsec.web.project.copy.action;

import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.copy.model.CopyEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 添加复印作业
 * 
 * @author lixiang
 * 
 */
public class AddCopyEventAction extends CopyBaseAction {

	private static final long serialVersionUID = 1L;
	private String event_code = "";// 作业流水号
	private Integer seclv_code = null;// 作业密级
	private String project_code = "";// 所属项目编号
	private String usage_code = "";// 用途编号
	private String summ = "";// 备注
	private String originalid = ""; // 原文件编号
	private String file_name = "";// 文件名称
	private Integer copy_num = null; // 复印份数
	private Integer page_num = null; // 每份页数
	private String source = ""; // 来源
	private String place = ""; // 复印地点
	private Integer is_double = 1; // 单双面(1单面；2双面左右翻；3双面上下翻)
	private String page_type = ""; // 纸张类型
	private Integer scale; // 缩放
	private Integer orientation = 1; // 横纵向（1纵向；2横向）
	private Integer color = 1;// 颜色（1黑白；2彩色）
	private Integer copy_status = 0;// 复印状态(0未复印；1已复印)
	private Integer is_barcode = 0;// 是否已打印条码(0未打印条码；1已打印条码)

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getEvent_code() {
		return event_code;
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

	public void setOriginalid(String originalid) {
		this.originalid = originalid;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public void setCopy_num(Integer copy_num) {
		this.copy_num = copy_num;
	}

	public void setPage_num(Integer page_num) {
		this.page_num = page_num;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public void setIs_double(Integer is_double) {
		this.is_double = is_double;
	}

	public void setPage_type(String page_type) {
		this.page_type = page_type;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

	public void setOrientation(Integer orientation) {
		this.orientation = orientation;
	}

	public void setColor(Integer color) {
		this.color = color;
	}

	public Integer getCopy_status() {
		return copy_status;
	}

	public void setCopy_status(Integer copy_status) {
		this.copy_status = copy_status;
	}

	public Integer getIs_barcode() {
		return is_barcode;
	}

	public void setIs_barcode(Integer is_barcode) {
		this.is_barcode = is_barcode;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getCopySecLevelByUser(getCurUser().getUser_iidd());
	}

	public List<SysUsage> getUsageList() {
		return basicService.getSysUsageList();
	}

	public List<SysProject> getProjectList() {
		return basicService.getSysProjectList();
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_code)) {
			String user_iidd = getCurUser().getUser_iidd();
			String dept_id = getCurUser().getDept_id();
			CopyEvent event = new CopyEvent(user_iidd, dept_id, event_code, seclv_code, usage_code, project_code, summ,
					originalid, file_name, copy_num, page_num, source, place, is_double, page_type, scale, orientation,
					color, copy_status);
			copyService.addCopyEvent(event);
			insertCommonLog("添加复印作业[" + event_code + "]");
			return "ok";
		} else {
			event_code = getCurUser().getUser_iidd() + System.currentTimeMillis();
			return SUCCESS;
		}
	}
}
