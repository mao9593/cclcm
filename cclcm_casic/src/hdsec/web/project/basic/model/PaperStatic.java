package hdsec.web.project.basic.model;

import hdsec.web.project.common.BaseDomain;

/**
 * 纸张统计
 * 
 * @author lixiang
 * 
 */

public class PaperStatic extends BaseDomain {
	private String dept_id = "";// 部门ID
	private String dept_name = "";// 部门名称
	private String printer_code = "";// 打印机ID
	private String printer_name = "";// 打印机名称
	private Integer seclv_code;// 密级代号
	private String seclv_name;// 密级名称
	private Integer page_count = 0;// 页数
	private Integer page_sum = 0;// 一级子部门总页数
	private Integer color;// 颜色（黑白/彩色）
	private Integer print_double;// 单双面
	private String color_name;
	private String print_double_name;
	private String page_size = "";// 纸张类型

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getPrinter_code() {
		return printer_code;
	}

	public void setPrinter_code(String printer_code) {
		this.printer_code = printer_code;
	}

	public String getPrinter_name() {
		return printer_name;
	}

	public void setPrinter_name(String printer_name) {
		this.printer_name = printer_name;
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

	public Integer getPage_count() {
		return page_count;
	}

	public void setPage_count(Integer page_count) {
		this.page_count = page_count;
	}

	public Integer getPage_sum() {
		return page_sum;
	}

	public void setPage_sum(Integer page_sum) {
		this.page_sum = page_sum;
	}

	public Integer getColor() {
		return color;
	}

	public void setColor(Integer color) {
		this.color = color;
	}

	public Integer getPrint_double() {
		return print_double;
	}

	public void setPrint_double(Integer print_double) {
		this.print_double = print_double;
	}

	public String getPrint_double_name() {
		switch (print_double) {
		case 1:
			return "单面";
		case 2:
			return "双面";
		case 3:
			return "双面";
		default:
			return "未知";
		}
	}

	public String getColor_name() {
		switch (color) {
		case 1:
			return "黑白";
		case 2:
			return "彩色";
		default:
			return "未知";
		}
	}

	public void setColor_name(String color_name) {
		this.color_name = color_name;
	}

	public void setPrint_double_name(String print_double_name) {
		this.print_double_name = print_double_name;
	}

	public String getPage_size() {
		return page_size;
	}

	public void setPage_size(String page_size) {
		this.page_size = page_size;
	}

	public PaperStatic() {
		super();
	}

	public PaperStatic(String dept_id, String dept_name, String printer_code, String printer_name, Integer seclv_code,
			String seclv_name, Integer page_count) {
		super();
		this.dept_id = dept_id;
		this.dept_name = dept_name;
		this.printer_code = printer_code;
		this.printer_name = printer_name;
		this.seclv_code = seclv_code;
		this.seclv_name = seclv_name;
		this.page_count = page_count;
	}
}
