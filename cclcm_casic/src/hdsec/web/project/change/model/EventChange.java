package hdsec.web.project.change.model;

import hdsec.web.project.common.BaseEvent;

import java.util.Date;

/**
 * 载体归属转换类
 * 
 * @author lixiang
 * 
 */
public class EventChange extends BaseEvent {

	private String entity_type = "";
	private String change_type = "";// 转换类型：toDEPT个人转部门、toPERSON部门转个人
	private String barcode = "";
	private String scope_dept_id = "";// 载体归属部门ID
	private String scope_dept_name = "";// 载体归属部门名称
	private Date finish_time = null;// 完成时间
	private Integer change_status = null;
	private String file_name = "";

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

	public String getChange_type() {
		return change_type;
	}

	public void setChange_type(String change_type) {
		this.change_type = change_type;
	}

	public String getChange_type_name() {
		String name = "";
		if (this.entity_type.equalsIgnoreCase("paper")) {
			if (this.change_type.equalsIgnoreCase("toDEPT")) {
				name = "个人文件转为部门文件";
			} else if (this.change_type.equalsIgnoreCase("toPERSON")) {
				name = "部门文件转为个人文件";
			}
		} else if (this.entity_type.equalsIgnoreCase("cd")) {
			if (this.change_type.equalsIgnoreCase("toDEPT")) {
				name = "个人光盘转为部门光盘";
			} else if (this.change_type.equalsIgnoreCase("toPERSON")) {
				name = "部门光盘转为个人光盘";
			}
		}
		return name;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
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

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public Date getFinish_time() {
		return finish_time;
	}

	public void setFinish_time(Date finish_time) {
		this.finish_time = finish_time;
	}

	public String getFinish_time_str() {
		return finish_time == null ? "" : getSdf().format(finish_time);
	}

	public Integer getChange_status() {
		return change_status;
	}

	public void setChange_status(Integer change_status) {
		this.change_status = change_status;
	}

	public String getChange_status_name() {
		String name = "";
		switch (this.change_status) {
		case 0:
			name = "未完成";
			break;
		case 1:
			name = "已完成";
			break;
		case 2:
			name = "已拒绝";
			break;
		}
		return name;
	}
}
