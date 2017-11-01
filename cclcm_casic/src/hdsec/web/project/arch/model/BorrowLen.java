package hdsec.web.project.arch.model;

/**
 * 借用时长类
 * 
 * @author handouwang
 * 
 *         2015-7-26/
 */
public class BorrowLen {
	private int seclv_code;
	private int length;// 单位：天
	private String seclv_name = "";

	public int getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(int seclv_code) {
		this.seclv_code = seclv_code;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getSeclv_name() {
		return seclv_name;
	}

	public void setSeclv_name(String seclv_name) {
		this.seclv_name = seclv_name;
	}

	public BorrowLen(int seclv_code, int length, String seclv_name) {
		super();
		this.seclv_code = seclv_code;
		this.length = length;
		this.seclv_name = seclv_name;
	}

	public BorrowLen() {
		super();
	}

}
