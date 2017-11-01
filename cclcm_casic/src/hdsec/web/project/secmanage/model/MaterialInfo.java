package hdsec.web.project.secmanage.model;

public class MaterialInfo {

	private String file_name = "";
	private String file_selv = "";

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_selv() {
		return file_selv;
	}

	public void setFile_selv(String file_selv) {
		this.file_selv = file_selv;
	}

	public MaterialInfo() {

	}

	public MaterialInfo(String file_name, String file_selv) {
		this.file_name = file_name;
		this.file_selv = file_selv;
	}
}
