package hdsec.web.project.basic.model;

import hdsec.web.project.common.BaseDomain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 交接确认类
 * 
 * 2014-4-18 下午3:19:26
 * 
 * @author yy
 */
public class ConfirmRecord extends BaseDomain {
	private int id;
	private String apply_user_iidd = "";
	private String apply_user_name = "";
	private String apply_dept_id = "";
	private String apply_dept_name = "";
	private String confirm_user_iidd = "";
	private String confirm_user_name = "";
	private String confirm_dept_id = "";
	private String confirm_dept_name = "";
	private String entity_type = "";
	private String entity_barcode = "";
	private String entity_name = "";
	private String seclv_name = "";
	private String confirm_type = "";
	private String event_code = "";
	private Date create_time = null;
	private Date confirm_time = null;
	private String confirm_status = "N";
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getApply_user_iidd() {
		return apply_user_iidd;
	}
	
	public void setApply_user_iidd(String apply_user_iidd) {
		this.apply_user_iidd = apply_user_iidd;
	}
	
	public String getApply_user_name() {
		return apply_user_name;
	}
	
	public void setApply_user_name(String apply_user_name) {
		this.apply_user_name = apply_user_name;
	}
	
	public String getConfirm_user_iidd() {
		return confirm_user_iidd;
	}
	
	public void setConfirm_user_iidd(String confirm_user_iidd) {
		this.confirm_user_iidd = confirm_user_iidd;
	}
	
	public String getConfirm_user_name() {
		return confirm_user_name;
	}
	
	public void setConfirm_user_name(String confirm_user_name) {
		this.confirm_user_name = confirm_user_name;
	}
	
	public String getApply_dept_id() {
		return apply_dept_id;
	}
	
	public void setApply_dept_id(String apply_dept_id) {
		this.apply_dept_id = apply_dept_id;
	}
	
	public String getApply_dept_name() {
		return apply_dept_name;
	}
	
	public void setApply_dept_name(String apply_dept_name) {
		this.apply_dept_name = apply_dept_name;
	}
	
	public String getConfirm_dept_id() {
		return confirm_dept_id;
	}
	
	public void setConfirm_dept_id(String confirm_dept_id) {
		this.confirm_dept_id = confirm_dept_id;
	}
	
	public String getConfirm_dept_name() {
		return confirm_dept_name;
	}
	
	public void setConfirm_dept_name(String confirm_dept_name) {
		this.confirm_dept_name = confirm_dept_name;
	}
	
	public String getEntity_type() {
		return entity_type;
	}
	
	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}
	
	public String getEntity_barcode() {
		return entity_barcode;
	}
	
	public void setEntity_barcode(String entity_barcode) {
		this.entity_barcode = entity_barcode;
	}
	
	public String getEntity_name() {
		return entity_name;
	}
	
	public void setEntity_name(String entity_name) {
		this.entity_name = entity_name;
	}
	
	public String getSeclv_name() {
		return seclv_name;
	}
	
	public void setSeclv_name(String seclv_name) {
		this.seclv_name = seclv_name;
	}
	
	public String getConfirm_type() {
		return confirm_type;
	}
	
	public void setConfirm_type(String confirm_type) {
		this.confirm_type = confirm_type;
	}
	
	public String getEvent_code() {
		return event_code;
	}
	
	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}
	
	public Date getConfirm_time() {
		return confirm_time;
	}
	
	public void setConfirm_time(Date confirm_time) {
		this.confirm_time = confirm_time;
	}
	
	public String getConfirm_status() {
		return confirm_status;
	}
	
	public void setConfirm_status(String confirm_status) {
		this.confirm_status = confirm_status;
	}
	
	public Date getCreate_time() {
		return create_time;
	}
	
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public ConfirmRecord() {
		super();
	}
	
	public ConfirmRecord(SimpleDateFormat sdf) {
		super(sdf);
	}
	
	public ConfirmRecord(String apply_user_iidd, String apply_user_name, String apply_dept_id, String apply_dept_name,
			String confirm_user_iidd, String confirm_user_name, String confirm_dept_id, String confirm_dept_name,
			String entity_type, String entity_barcode, String entity_name, String seclv_name, String confirm_type,
			String event_code, Date create_time) {
		super();
		this.apply_user_iidd = apply_user_iidd;
		this.apply_user_name = apply_user_name;
		this.apply_dept_id = apply_dept_id;
		this.apply_dept_name = apply_dept_name;
		this.confirm_user_iidd = confirm_user_iidd;
		this.confirm_user_name = confirm_user_name;
		this.confirm_dept_id = confirm_dept_id;
		this.confirm_dept_name = confirm_dept_name;
		this.entity_type = entity_type;
		this.entity_barcode = entity_barcode;
		this.entity_name = entity_name;
		this.seclv_name = seclv_name;
		this.confirm_type = confirm_type;
		this.event_code = event_code;
		this.create_time = create_time;
	}
	
}
