package hdsec.web.project.computer.model;

import hdsec.web.project.common.BaseDomain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 硬盘台账
 * 
 * @author lishu 2015-11-29
 */
public class EntityHardDisk extends BaseDomain {

	private String computer_type = "";// 计算机类型：computer台式机，laptop笔记本
	private String computer_barcode = "";// 计算机/笔记本条码
	private String conf_code = ""; // 保密编号
	private String duty_user_id = ""; // 责任人ID
	private String duty_dept_id = ""; // 责任部门ID
	private String duty_entp_id = ""; // 责任单位ID
	private String hdisk_no = "";// 硬盘序列号
	private String retrieve_user_id = ""; // 录入人员ID
	private Date retrieve_time = null;// 录入时间
	private String summ = "";// 备注
	private String approve_url = "";
	private Integer hdisk_status = null;
	private String duty_user_name = ""; // 责任人ID
	private String duty_dept_name = ""; // 责任部门ID
	private String retrieve_user_name = "";

	public String getComputer_type() {
		return computer_type;
	}

	public void setComputer_type(String computer_type) {
		this.computer_type = computer_type;
	}

	public String getComputer_barcode() {
		return computer_barcode;
	}

	public void setComputer_barcode(String computer_barcode) {
		this.computer_barcode = computer_barcode;
	}

	public String getConf_code() {
		return conf_code;
	}

	public void setConf_code(String conf_code) {
		this.conf_code = conf_code;
	}

	public String getDuty_user_id() {
		return duty_user_id;
	}

	public void setDuty_user_id(String duty_user_id) {
		this.duty_user_id = duty_user_id;
	}

	public String getDuty_dept_id() {
		return duty_dept_id;
	}

	public void setDuty_dept_id(String duty_dept_id) {
		this.duty_dept_id = duty_dept_id;
	}

	public String getDuty_entp_id() {
		return duty_entp_id;
	}

	public void setDuty_entp_id(String duty_entp_id) {
		this.duty_entp_id = duty_entp_id;
	}

	public String getHdisk_no() {
		return hdisk_no;
	}

	public void setHdisk_no(String hdisk_no) {
		this.hdisk_no = hdisk_no;
	}

	public String getRetrieve_user_id() {
		return retrieve_user_id;
	}

	public void setRetrieve_user_id(String retrieve_user_id) {
		this.retrieve_user_id = retrieve_user_id;
	}

	public Date getRetrieve_time() {
		return retrieve_time;
	}

	public void setRetrieve_time(Date retrieve_time) {
		this.retrieve_time = retrieve_time;
	}

	public String getRetrieve_time_str() {
		return retrieve_time == null ? "" : getSdf().format(retrieve_time);
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public String getRetrieve_user_name() {
		return retrieve_user_name;
	}

	public void setRetrieve_user_name(String retrieve_user_name) {
		this.retrieve_user_name = retrieve_user_name;
	}

	public String getApprove_url() {
		return approve_url;
	}

	public void setApprove_url(String approve_url) {
		this.approve_url = approve_url;
	}

	public Integer getHdisk_status() {
		return hdisk_status;
	}

	public void setHdisk_status(Integer hdisk_status) {
		this.hdisk_status = hdisk_status;
	}

	public String getHdisk_status_name() {
		if (hdisk_status == null) {
			return "";
		} else {
			switch (hdisk_status) {
			case 0:
				return "在用";
			case 1:
				return "已回收";
			case 2:
				return "已销毁";

			default:
				return "";
			}
		}
	}

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name;
	}

	public String getDuty_dept_name() {
		return duty_dept_name;
	}

	public void setDuty_dept_name(String duty_dept_name) {
		this.duty_dept_name = duty_dept_name;
	}

	public String getDuty_entp_name() {
		if (duty_entp_id.equals("")) {
			return "";
		} else {
			switch (duty_entp_id) {
			case "1":
				return "总部";
			case "2":
				return "24所";
			case "3":
				return "26所";
			case "4":
				return "44所";
			default:
				return "";
			}
		}
	}

	public EntityHardDisk() {
		super();
	}

	public EntityHardDisk(SimpleDateFormat sdf) {
		super(sdf);
	}

	public EntityHardDisk(String computer_type, String computer_barcode, String conf_code, String hdisk_no,
			String duty_user_id, String duty_dept_id, String duty_entp_id, String retrieve_user_id, Date retrieve_time,
			String summ, String approve_url, Integer hdisk_status) {

		super();

		this.computer_type = computer_type;
		this.computer_barcode = computer_barcode;
		this.conf_code = conf_code;
		this.hdisk_no = hdisk_no;
		this.duty_user_id = duty_user_id;
		this.duty_dept_id = duty_dept_id;
		this.duty_entp_id = duty_entp_id;
		this.retrieve_user_id = retrieve_user_id;
		this.retrieve_time = retrieve_time;
		this.summ = summ;
		this.approve_url = approve_url;
		this.hdisk_status = hdisk_status;
	}
}
