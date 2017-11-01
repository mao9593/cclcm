package hdsec.web.project.ledger.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.BaseEvent;

/**
 * 其他申请表存放类
 * 
 * @author guojiao
 * 
 */
public class EventTemp extends BaseEvent {
	private String page_num = "";
	private String scope_dept_id = "";
	private String scope_dept_name = "";
	private String entity_type = "";
	private String barcode = "";
	private String file_name = "";
	private String statue = "";

	public String getStatue() {
		return statue;
	}

	public void setStatue(String statue) {
		this.statue = statue;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
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
		case "cd":
			name = "光盘";
			break;
		case "paper":
			name = "文件";
			break;
		}
		return name;
	}

	public String getPage_num() {
		return page_num;
	}

	public void setPage_num(String page_num) {
		this.page_num = page_num;
	}

	public String getScope_dept_id() {
		return scope_dept_id;
	}

	public void setScope_dept_id(String scope_dept_id) {
		this.scope_dept_id = scope_dept_id;
	}

	public String getScope_dept_name() {
		return scope_dept_name;
	}

	public void setScope_dept_name(String scope_dept_name) {
		this.scope_dept_name = scope_dept_name;
	}

	public EventTemp() {
		super();
	}

	public EventTemp(JobTypeEnum jobType, String event_code, String user_iidd, String dept_id, Integer seclv_code,
			String usage_code, String project_code, String summ, String entity_type, String barcode, String file_name) {
		super(jobType, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);

		this.entity_type = entity_type;
		this.barcode = barcode;
		this.file_name = file_name;

	}
}
