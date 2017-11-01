package hdsec.web.project.enter.model;

import hdsec.web.project.activiti.model.ProcessJob;

public class EnterProcessJob extends ProcessJob {
	private Integer file_type = null;

	public Integer getFile_type() {
		return file_type;
	}

	public String getFile_type_name() {
		String file_type_name = "";
		if (file_type == 1) {
			file_type_name = "文件";
		} else if (file_type == 2) {
			file_type_name = "光盘";
		} else if (file_type == 3) {
			file_type_name = "扫描电子文件";
		} else if (file_type == 4) {
			file_type_name = "磁介质";
		} else {
			file_type_name = "未知类型";
		}
		return file_type_name;
	}

	public void setFile_type(Integer file_type) {
		this.file_type = file_type;
	}

}
