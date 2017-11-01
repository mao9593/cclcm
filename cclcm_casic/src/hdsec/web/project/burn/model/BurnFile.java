package hdsec.web.project.burn.model;

public class BurnFile {
	private String file_name = "";
	private Integer seclv_code;
	private String seclv_name = "";
	private String file_path = "";

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getSeclv_name() {
		return seclv_name;
	}

	public void setSeclv_name(String seclv_name) {
		this.seclv_name = seclv_name;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public BurnFile() {
		super();
	}

	public BurnFile(String file_name, String file_path) {
		super();
		this.file_name = file_name;
		this.file_path = file_path;
	}

	public BurnFile(String file_name, Integer seclv_code, String seclv_name, String file_path) {
		super();
		this.file_name = file_name;
		this.seclv_code = seclv_code;
		this.seclv_name = seclv_name;
		this.file_path = file_path;
	}

}
