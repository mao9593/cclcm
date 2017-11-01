package hdsec.web.project.print.model;

import hdsec.web.project.common.BaseDomain;

import java.util.Date;

/**
 * 打印机类
 * @author renmingfei
 *
 */
public class SysPrinter extends BaseDomain {
	private String printer_code = ""; //主键
	private String printer_name = ""; //打印机名称
	private String printer_path = ""; //打印机路径
	private String printer_type = ""; //打印机类型（网打等）
	private String printer_brand = ""; //品牌
	private String printer__model = ""; //型号
	private String printer__color = ""; //彩打-黑白
	private String dept_id = ""; //所属部门编号外键
	private String printer_ipaddr = ""; //网络打印机IP
	private String printer_location = "";//打印机位置（所属办公室
	private Date create_time = null; //创建时间
	private Date delete_time = null; //删除时间
	private String is_double = ""; //是否支持双面
	private String console_code = ""; //所属控制台号(外键)
	private Integer seclv_code = null; //打印机密级
	private String is_online = ""; //是否在线Y/N
	private Date last_connect_time = null;//上次连接时间
	
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
	
	public String getPrinter_path() {
		return printer_path;
	}
	
	public void setPrinter_path(String printer_path) {
		this.printer_path = printer_path;
	}
	
	public String getPrinter_type() {
		return printer_type;
	}
	
	public void setPrinter_type(String printer_type) {
		this.printer_type = printer_type;
	}
	
	public String getPrinter_brand() {
		return printer_brand;
	}
	
	public void setPrinter_brand(String printer_brand) {
		this.printer_brand = printer_brand;
	}
	
	public String getPrinter__model() {
		return printer__model;
	}
	
	public void setPrinter__model(String printer__model) {
		this.printer__model = printer__model;
	}
	
	public String getPrinter__color() {
		return printer__color;
	}
	
	public void setPrinter__color(String printer__color) {
		this.printer__color = printer__color;
	}
	
	public String getDept_id() {
		return dept_id;
	}
	
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	
	public String getPrinter_ipaddr() {
		return printer_ipaddr;
	}
	
	public void setPrinter_ipaddr(String printer_ipaddr) {
		this.printer_ipaddr = printer_ipaddr;
	}
	
	public String getPrinter_location() {
		return printer_location;
	}
	
	public void setPrinter_location(String printer_location) {
		this.printer_location = printer_location;
	}
	
	public Date getCreate_time() {
		return create_time;
	}
	
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public Date getDelete_time() {
		return delete_time;
	}
	
	public void setDelete_time(Date delete_time) {
		this.delete_time = delete_time;
	}
	
	public String getIs_double() {
		return is_double;
	}
	
	public void setIs_double(String is_double) {
		this.is_double = is_double;
	}
	
	public String getConsole_code() {
		return console_code;
	}
	
	public void setConsole_code(String console_code) {
		this.console_code = console_code;
	}
	
	public Integer getSeclv_code() {
		return seclv_code;
	}
	
	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}
	
	public String getIs_online() {
		return is_online;
	}
	
	public void setIs_online(String is_online) {
		this.is_online = is_online;
	}
	
	public Date getLast_connect_time() {
		return last_connect_time;
	}
	
	public void setLast_connect_time(Date last_connect_time) {
		this.last_connect_time = last_connect_time;
	}
	
	public SysPrinter() {
		super();
	}
	
	public SysPrinter(String printer_code, String printer_name, String printer_path, String printer_type,
			String printer_brand, String printer__model, String printer__color, String dept_id, String printer_ipaddr,
			String printer_location, Date create_time, Date delete_time, String is_double, String console_code,
			Integer seclv_code, String is_online, Date last_connect_time) {
		super();
		this.printer_code = printer_code;
		this.printer_name = printer_name;
		this.printer_path = printer_path;
		this.printer_type = printer_type;
		this.printer_brand = printer_brand;
		this.printer__model = printer__model;
		this.printer__color = printer__color;
		this.dept_id = dept_id;
		this.printer_ipaddr = printer_ipaddr;
		this.printer_location = printer_location;
		this.create_time = create_time;
		this.delete_time = delete_time;
		this.is_double = is_double;
		this.console_code = console_code;
		this.seclv_code = seclv_code;
		this.is_online = is_online;
		this.last_connect_time = last_connect_time;
	}
	
}
