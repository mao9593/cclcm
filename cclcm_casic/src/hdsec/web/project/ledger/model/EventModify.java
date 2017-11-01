package hdsec.web.project.ledger.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.BaseEvent;

import java.util.Date;
import java.util.Map;

public class EventModify extends BaseEvent {
	private String event_code;
	private String entity_type;// 载体类型(CD,PAPER)
	private String barcode; // 载体条码
	private int modify_status;// 变更状态（1已完成，0未完成）
	private int pre_seclv;// 原密级
	private int trg_seclv;// 目标密码
	private Date finish_time = null;// 完成时间
	private String file_name;
	private int pre_scope;
	private int trg_scope;
	private int pre_timelimit;
	private int trg_timelimit;
	private String ext_column;
	private String file_title = "";
	private Integer paper_id;
	private Integer cd_id;
	private Integer paper_state;// 介质状态
	private Integer cd_state;
	private String paper_state_name;// 介质状态名称
	private String cd_state_name;
	private String file_list = "";

	private String pre_seclv_name;// 原密级名称
	private String trg_seclv_name;// 目标密码名称

	public String getPre_seclv_name() {
		return pre_seclv_name;
	}

	public void setPre_seclv_name(String pre_seclv_name) {
		this.pre_seclv_name = pre_seclv_name;
	}

	public String getTrg_seclv_name() {
		return trg_seclv_name;
	}

	public void setTrg_seclv_name(String trg_seclv_name) {
		this.trg_seclv_name = trg_seclv_name;
	}

	public String getFile_list() {
		return file_list;
	}

	public void setFile_list(String file_list) {
		this.file_list = file_list;
	}

	public Integer getPaper_state() {
		return paper_state;
	}

	public void setPaper_state(Integer paper_state) {
		this.paper_state = paper_state;
	}

	public Integer getCd_state() {
		return cd_state;
	}

	public void setCd_state(Integer cd_state) {
		this.cd_state = cd_state;
	}

	public void setCd_state_name(String cd_state_name) {
		this.cd_state_name = cd_state_name;
	}

	public String getPaper_state_name() {
		if (paper_state == null) {
			return "未知";
		}
		if (EntityStateEnum.isStateAvailabel(paper_state.intValue())) {
			return EntityStateEnum.valueOf("STATE" + paper_state).getName();
		} else {
			return "未知";
		}
	}

	public String getCd_state_name() {
		if (cd_state == null) {
			return "未知";
		}
		if (EntityStateEnum.isStateAvailabel(cd_state.intValue())) {
			return EntityStateEnum.valueOf("STATE" + cd_state).getName();
		} else {
			return "未知";
		}
	}

	public Integer getCd_id() {
		return cd_id;
	}

	public void setCd_id(Integer cd_id) {
		this.cd_id = cd_id;
	}

	public void setPaper_state_name(String paper_state_name) {
		this.paper_state_name = paper_state_name;
	}

	@Override
	public String getEvent_code() {
		return event_code;
	}

	@Override
	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getEntity_type() {
		return entity_type;
	}

	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;

	}

	public String getEntity_type_name() {
		String name = "";
		switch (this.entity_type) {
		case "CD":
			name = "光盘";
			break;
		case "Paper":
			name = "文件";
			break;
		}
		return name;
	}

	public Integer getPaper_id() {
		return paper_id;
	}

	public void setPaper_id(Integer paper_id) {
		this.paper_id = paper_id;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public int getModify_status() {
		return modify_status;
	}

	public void setModify_status(int modify_status) {
		this.modify_status = modify_status;
	}

	public String getModify_status_name() {
		String name = "";
		switch (this.modify_status) {
		case 0:
			name = "未完成";
			break;
		case 1:
			name = "已完成";
			break;
		}
		return name;
	}

	public int getPre_seclv() {
		return pre_seclv;
	}

	public void setPre_seclv(int pre_seclv) {
		this.pre_seclv = pre_seclv;
	}

	public int getTrg_seclv() {
		return trg_seclv;
	}

	public void setTrg_seclv(int trg_seclv) {
		this.trg_seclv = trg_seclv;
	}

	public Date getFinish_time() {
		return finish_time;
	}

	public void setFinish_time(Date finish_time) {
		this.finish_time = finish_time;
	}

	public String getFile_title() {
		return file_title;
	}

	public void setFile_title(String file_title) {
		this.file_title = file_title;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public int getPre_scope() {
		return pre_scope;
	}

	public void setPre_scope(int pre_scope) {
		this.pre_scope = pre_scope;
	}

	public int getTrg_scope() {
		return trg_scope;
	}

	public void setTrg_scope(int trg_scope) {
		this.trg_scope = trg_scope;
	}

	public int getPre_timelimit() {
		return pre_timelimit;
	}

	public void setPre_timelimit(int pre_timelimit) {
		this.pre_timelimit = pre_timelimit;
	}

	public int getTrg_timelimit() {
		return trg_timelimit;
	}

	public void setTrg_timelimit(int trg_timelimit) {
		this.trg_timelimit = trg_timelimit;
	}

	public String getExt_column() {
		return ext_column;
	}

	public void setExt_column(String ext_column) {
		this.ext_column = ext_column;
	}

	public EventModify() {
		super(JobTypeEnum.MODIFY_SECLV);
	}

	public EventModify(String event_code, String entity_type, String barcode, int modify_status, int pre_seclv,
			int trg_seclv, Date finish_time, String file_name, String user_iidd, String dept_id, Integer seclv_code,
			String usage_code, String project_code, String summ, Map<String, String> fileTitleMap) {
		super(JobTypeEnum.MODIFY_SECLV, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);
		this.event_code = event_code;
		this.entity_type = entity_type;
		this.barcode = barcode;
		this.modify_status = modify_status;
		this.pre_seclv = pre_seclv;
		this.trg_seclv = trg_seclv;
		this.finish_time = finish_time;
		this.file_name = file_name;
	}

}
