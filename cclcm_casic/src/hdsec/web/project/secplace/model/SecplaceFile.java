package hdsec.web.project.secplace.model;

public class SecplaceFile {
	private String file_name = "";
	// private Integer seclv_code;
	// private String seclv_name = "";
	private String file_path = "";

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public SecplaceFile() {
		super();
	}

	public SecplaceFile(String file_name, String file_path) {
		super();
		this.file_name = file_name;
		this.file_path = file_path;
	}

}