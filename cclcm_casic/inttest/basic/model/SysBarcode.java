package basic.model;

public class SysBarcode {
	private int barcode_code;
	private String barcode_name;
	private String seclv_code;
	private String usage_code;
	private String console_code;
	private String project_code;
	private String content;
	private String textcontent;
	private int bypage;
	private int form;
	private int position;
	private int pageno;
	private int perpage;
	private int cord_x;
	private int cord_y;
	private int size_x;
	private int size_y;
	private String is_default;
	private String is_sealed;
	private String seclv_names;
	private String usage_names;
	private String project_names;
	private String console_names;
	
	public int getBarcode_code() {
		return barcode_code;
	}
	
	public void setBarcode_code(int barcode_code) {
		this.barcode_code = barcode_code;
	}
	
	public String getBarcode_name() {
		return barcode_name;
	}
	
	public void setBarcode_name(String barcode_name) {
		this.barcode_name = barcode_name;
	}
	
	public String getSeclv_code() {
		return seclv_code;
	}
	
	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}
	
	public String getUsage_code() {
		return usage_code;
	}
	
	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code;
	}
	
	public String getConsole_code() {
		return console_code;
	}
	
	public void setConsole_code(String console_code) {
		this.console_code = console_code;
	}
	
	public String getProject_code() {
		return project_code;
	}
	
	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getTextcontent() {
		return textcontent;
	}
	
	public void setTextcontent(String textcontent) {
		this.textcontent = textcontent;
	}
	
	public int getBypage() {
		return bypage;
	}
	
	public void setBypage(int bypage) {
		this.bypage = bypage;
	}
	
	public int getForm() {
		return form;
	}
	
	public void setForm(int form) {
		this.form = form;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
	
	public int getPageno() {
		return pageno;
	}
	
	public void setPageno(int pageno) {
		this.pageno = pageno;
	}
	
	public int getPerpage() {
		return perpage;
	}
	
	public void setPerpage(int perpage) {
		this.perpage = perpage;
	}
	
	public int getCord_x() {
		return cord_x;
	}
	
	public void setCord_x(int cord_x) {
		this.cord_x = cord_x;
	}
	
	public int getCord_y() {
		return cord_y;
	}
	
	public void setCord_y(int cord_y) {
		this.cord_y = cord_y;
	}
	
	public int getSize_x() {
		return size_x;
	}
	
	public void setSize_x(int size_x) {
		this.size_x = size_x;
	}
	
	public int getSize_y() {
		return size_y;
	}
	
	public void setSize_y(int size_y) {
		this.size_y = size_y;
	}
	
	public String getIs_sealed() {
		return is_sealed;
	}
	
	public void setIs_sealed(String is_sealed) {
		this.is_sealed = is_sealed;
	}
	
	public String getSeclv_names() {
		return seclv_names;
	}
	
	public String getUsage_names() {
		return usage_names;
	}
	
	public String getProject_names() {
		return project_names;
	}
	
	public String getConsole_names() {
		return console_names;
	}
	
	public String getIs_default() {
		return is_default;
	}
	
	public void setIs_default(String is_default) {
		this.is_default = is_default;
	}
	
	public void setSeclv_names(String seclv_names) {
		this.seclv_names = seclv_names;
	}
	
	public void setUsage_names(String usage_names) {
		this.usage_names = usage_names;
	}
	
	public void setProject_names(String project_names) {
		this.project_names = project_names;
	}
	
	public void setConsole_names(String console_names) {
		this.console_names = console_names;
	}
	
	public String getForm_name() {
		switch (form) {
			case 1:
				return "一维码";
			case 2:
				return "二维码(QR)";
			case 3:
				return "二维码(PDF417)";
			default:
				return "无";
		}
	}
	
	public String getPosition_name() {
		switch (position) {
			case 1:
				return "左上";
			case 2:
				return "右下";
			case 3:
				return "左下";
			case 4:
				return "上居中";
			case 5:
				return "下居中";
			default:
				return "右上";
		}
	}
	
}
