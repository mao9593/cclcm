package hdsec.web.project.basic.model;

import hdsec.web.project.common.BaseDomain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 客户端消息提醒类 2014-5-4 上午9:48:57
 * 
 * @author gaoximin
 */
public class ClientMsg extends BaseDomain {
	private int id;
	private String accept_user_iidd = "";
	private String accept_user_name = "";
	private String oper_type = "";
	private Integer message_type = null;
	private String job_code = "";
	private String message = "";
	private Date insert_time = null;
	private Integer is_read = 0;

	private String user_iidd;
	private String dept_id;

	public ClientMsg(int id, String accept_user_iidd, String accept_user_name, String oper_type, Integer message_type,
			String job_code, String message, Date insert_time, Integer is_read, String user_iidd, String dept_id) {
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
		this.user_iidd = user_iidd;
		this.dept_id = dept_id;
	}

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getOper_type() {
		return oper_type;
	}

	public void setOper_type(String oper_type) {
		this.oper_type = oper_type;
	}

	public Integer getMessage_type() {
		return message_type;
	}

	public void setMessage_type(Integer message_type) {
		this.message_type = message_type;
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

	public Date getInsert_time() {
		return insert_time;
	}

	public void setInsert_time(Date insert_time) {
		this.insert_time = insert_time;
	}

	public Integer getIs_read() {
		return is_read;
	}

	public void setIs_read(Integer is_read) {
		this.is_read = is_read;
	}

	public ClientMsg() {
		super();
	}

	public ClientMsg(SimpleDateFormat sdf) {
		super(sdf);
	}

	public ClientMsg(String accept_user_iidd, String accept_user_name, String oper_type, Integer message_type,
			String job_code, String message, Date insert_time, Integer is_read) {
		super();
		this.accept_user_iidd = accept_user_iidd;
		this.accept_user_name = accept_user_name;
		this.oper_type = oper_type;
		this.message_type = message_type;
		this.job_code = job_code;
		this.message = message;
		this.insert_time = insert_time;
		this.is_read = is_read;
	}
}
