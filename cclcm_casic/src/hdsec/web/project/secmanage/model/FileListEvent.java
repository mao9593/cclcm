package hdsec.web.project.secmanage.model;


/**
 * 上传附件名称和数量
 * 
 * @author guojiao
 */
public class FileListEvent {

	private String file_list = "";
	private Integer file_num = null;

	public String getFile_list() {
		return file_list;
	}

	public void setFile_list(String file_list) {
		this.file_list = file_list;
	}

	public Integer getFile_num() {
		return file_num;
	}

	public void setFile_num(Integer file_num) {
		this.file_num = file_num;
	}

	public FileListEvent() {
	}
}
