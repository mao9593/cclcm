package hdsec.web.project.basic.model;

import hdsec.web.project.common.BaseDomain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 外发拒收类
 * 
 * @author lixiang
 */
public class RejectRecord extends BaseDomain {
	private String reject_code = "";// 拒收流水号
	private String entity_barcode = "";
	private String entity_type = "";// 载体类型CD/PAPER
	private String entity_name = "";
	private Date reject_time = null;// 拒收时间
	private String send_user_iidd = "";// 外发用户ID
	private String send_dept_id = "";// 外发用户部门ID
	private String reject_user_name = "";// 拒收用户姓名
	private String reject_dept_name = "";// 拒收用户部门
	private String reject_type = "";// 拒收类型:READ/UNREAD/COPY
	private String comment = "";// 拒收说明

	public String getReject_code() {
		return reject_code;
	}

	public void setReject_code(String reject_code) {
		this.reject_code = reject_code;
	}

	public String getEntity_barcode() {
		return entity_barcode;
	}

	public void setEntity_barcode(String entity_barcode) {
		this.entity_barcode = entity_barcode;
	}

	public String getEntity_type() {
		return entity_type;
	}

	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}

	public String getEntity_name() {
		return entity_name;
	}

	public void setEntity_name(String entity_name) {
		this.entity_name = entity_name;
	}

	public Date getReject_time() {
		return reject_time;
	}

	public String getReject_time_str() {
		return reject_time == null ? "" : getSdf().format(reject_time);
	}

	public void setReject_time(Date reject_time) {
		this.reject_time = reject_time;
	}

	public String getSend_user_iidd() {
		return send_user_iidd;
	}

	public void setSend_user_iidd(String send_user_iidd) {
		this.send_user_iidd = send_user_iidd;
	}

	public String getSend_dept_id() {
		return send_dept_id;
	}

	public void setSend_dept_id(String send_dept_id) {
		this.send_dept_id = send_dept_id;
	}

	public String getReject_user_name() {
		return reject_user_name;
	}

	public void setReject_user_name(String reject_user_name) {
		this.reject_user_name = reject_user_name;
	}

	public String getReject_dept_name() {
		return reject_dept_name;
	}

	public void setReject_dept_name(String reject_dept_name) {
		this.reject_dept_name = reject_dept_name;
	}

	public String getReject_type() {
		return reject_type;
	}

	public void setReject_type(String reject_type) {
		this.reject_type = reject_type;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public RejectRecord() {
		super();
	}

	public RejectRecord(SimpleDateFormat sdf) {
		super(sdf);
	}

	public RejectRecord(String reject_code, String entity_barcode, String entity_type, String entity_name,
			Date reject_time, String send_user_iidd, String send_dept_id, String reject_user_name,
			String reject_dept_name, String reject_type, String comment) {
		super();
		this.reject_code = reject_code;
		this.entity_barcode = entity_barcode;
		this.entity_type = entity_type;
		this.entity_name = entity_name;
		this.reject_time = reject_time;
		this.send_user_iidd = send_user_iidd;
		this.send_dept_id = send_dept_id;
		this.reject_user_name = reject_user_name;
		this.reject_dept_name = reject_dept_name;
		this.reject_type = reject_type;
		this.comment = comment;
	}

}
