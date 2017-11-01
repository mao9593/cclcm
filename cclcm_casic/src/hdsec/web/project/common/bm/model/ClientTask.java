package hdsec.web.project.common.bm.model;

import hdsec.web.project.common.BaseDomain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 待办事项类
 * 
 * 2015-4-29
 * 
 * @author zhangzhenwei
 */
public class ClientTask {

	private int id;
	private String accept_user_iidd = "";
	private String accept_user_name = "";
	private String oper_type = "";
	private Integer message_type = null;
	private String job_code = "";
	private String message = "";
	private Date insert_time = null;
	private Date read_time = null;
	private Integer is_read = 1;
	private String title = "";
	private String url = "";
	private SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public String getRead_time() {
		return simpleDate.format(read_time);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setRead_time(Date read_time) {
		this.read_time = read_time;
	}

	public void setInsert_time(Date insert_time) {
		this.insert_time = insert_time;
	}

	public String getOper_type() {
		switch (this.oper_type) {
		case "PRINT":
			return "打印申请";
		case "BURN":
			return "刻录申请";
		case "COPY":
			return "复印申请";
		case "OUTCOPY":
			return "外来文件复印申请";
		case "LEADIN":
			return "录入申请";
		case "TRANSFER":
			return "流转申请";
		case "DEVICE":
			return "磁介质借用申请";
		case "BORROW":
			return "借阅申请";
		case "FILE_PAPER":
			return "文件归档申请";
		case "SEND_PAPER":
			return "文件外发申请";
		case "DESTROY_PAPER":
			return "文件销毁申请";
		case "DELAY_PAPER":
			return "文件延期回收申请";
		case "FILE_CD":
			return "光盘归档申请";
		case "SEND_CD":
			return "光盘外发申请";
		case "DESTROY_CD":
			return "光盘销毁申请";
		case "DELAY_CD":
			return "光盘延期回收申请";
		case "DESTROY_DEVICE":
			return "磁介质销毁申请";
		case "CHANGE":
			return "载体归属变换申请";
		case "SENDES_PAPER":
			return "文件送销申请";
		case "SENDES_CD":
			return "光盘送销申请";
		default:
			return "";
		}
	}

	public void setOper_type(String oper_type) {
		this.oper_type = oper_type;
	}

	public String getJob_code() {
		return job_code;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getIs_read() {
		return is_read;
	}

	public void setIs_read(Integer is_read) {
		this.is_read = is_read;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAccept_user_iidd() {
		return accept_user_iidd;
	}

	public void setAccept_user_iidd(String accept_user_iidd) {
		this.accept_user_iidd = accept_user_iidd;
	}

	public String getAccept_user_name() {
		return accept_user_name;
	}

	public void setAccept_user_name(String accept_user_name) {
		this.accept_user_name = accept_user_name;
	}

	public String getUrl() {
		return url;
	}

	public Integer getMessage_type() {
		return message_type;
	}

	public String getInsert_time() {

		return simpleDate.format(insert_time);
	}

	public void setMessage_type(Integer message_type) {
		this.message_type = message_type;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ClientTask() {
		super();
	}

	public ClientTask(SimpleDateFormat sdf) {
		
		// TODO Auto-generated constructor stub
	}
  
	public ClientTask(int id, String accept_user_iidd, String accept_user_name, String oper_type, Integer message_type,
			String job_code, String message, Date insert_time, Integer is_read, String title, String url, Date read_time) {
		super();
		this.id = id;
		this.accept_user_iidd = accept_user_iidd;
		this.accept_user_name = accept_user_name;
		this.oper_type = oper_type;
		this.message_type = message_type;
		this.job_code = job_code;
		this.message = message;
		this.insert_time = insert_time;
		this.is_read = is_read;
		this.title = title;
		this.url = url;
		this.read_time = read_time;

	}
}
