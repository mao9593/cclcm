package hdsec.web.project.publicity.model;

import hdsec.web.project.secmanage.model.FileListEvent;

/**
 * 添加宣传报道审查类
 * 
 * @author LS
 * 
 */
public class ReportFileEvent extends FileListEvent {
	private String file_seclevel = "";// 文件密级列表
	private String report_pic = null;//报道是否含有图片
	
	
	public String getFile_seclevel() {
		return file_seclevel;
	}

	public void setFile_seclevel(String file_seclevel) {
		this.file_seclevel = file_seclevel;
	}
	
	public String getReport_pic() {
		return report_pic;
	}

	public void setReport_pic(String report_pic) {
		this.report_pic = report_pic;
	}
	
	public ReportFileEvent() {
	}
}

