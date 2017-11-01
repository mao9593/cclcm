package hdsec.web.project.computer.action;

import hdsec.web.project.computer.model.EntityHardDisk;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 更新计算机台账
 * 
 * @author liuyaling 2015-5-5
 */
public class UpdateHardDiskAction extends ComputerBaseAction {

	private static final long serialVersionUID = 1L;
	EntityHardDisk hdisk = null;
	private String update = "N";// 是否更改计算机信息标志
	private String duty_user_id = ""; // 责任人ID
	private String duty_dept_id = ""; // 责任部门ID
	private String duty_entp_id = ""; // 责任单位ID
	private String computer_type = "";// 计算机类型：computer台式机，laptop笔记本
	private String computer_barcode = ""; // 条码号
	private String hdisk_no = "";// 硬盘序列号
	private String conf_code = ""; // 保密编号
	private String retrieve_user_id = ""; // 录入人员ID
	private Date retrieve_time = null;// 录入时间
	private String summ = "";// 备注
	private String approve_url = "";
	private Integer hdisk_status = null;
	private String duty_user_name = ""; // 责任人ID
	private String duty_dept_name = ""; // 责任部门ID
	private String duty_entp_name = ""; // 责任单位ID
	private String retrieve_user_name = "";

	public EntityHardDisk getHdisk() {
		return hdisk;
	}

	public void setHdisk(EntityHardDisk hdisk) {
		this.hdisk = hdisk;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
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

	public String getHdisk_no() {
		return hdisk_no;
	}

	public void setHdisk_no(String hdisk_no) {
		this.hdisk_no = hdisk_no;
	}

	public String getConf_code() {
		return conf_code;
	}

	public void setConf_code(String conf_code) {
		this.conf_code = conf_code;
	}

	public String getRetrieve_user_id() {
		return retrieve_user_id;
	}

	public void setRetrieve_user_id(String retrieve_user_id) {
		this.retrieve_user_id = retrieve_user_id;
	}

	public String getRetrieve_user_name() {
		return retrieve_user_name;
	}

	public void setRetrieve_user_name(String retrieve_user_name) {
		this.retrieve_user_name = retrieve_user_name;
	}

	public Date getRetrieve_time() {
		return retrieve_time;
	}

	public void setRetrieve_time(Date retrieve_time) {
		this.retrieve_time = retrieve_time;
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
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
		return duty_entp_name;
	}

	public void setDuty_entp_name(String duty_entp_name) {
		this.duty_entp_name = duty_entp_name;
	}

	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("N")) {
			// device = deviceService.getDeviceByCode(device_code);
			if (StringUtils.hasLength(hdisk_no)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("hdisk_no", hdisk_no);
				hdisk = computerService.getHardDiskByMap(map);

				return SUCCESS;
			} else {
				throw new Exception("无法查询条码号，请重新尝试。");
			}
		} else {
			hdisk = new EntityHardDisk(computer_type, computer_barcode, conf_code, hdisk_no, duty_user_id,
					duty_dept_id, duty_entp_id, retrieve_user_id, retrieve_time, summ, approve_url, hdisk_status);

			computerService.updateHardDisk(hdisk);
			insertCommonLog("修改硬盘：条码号[" + hdisk_no + "]");
			return "ok";
		}
	}
}