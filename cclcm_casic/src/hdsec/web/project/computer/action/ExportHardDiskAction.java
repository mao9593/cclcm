package hdsec.web.project.computer.action;

import hdsec.web.project.common.bm.BMCommonExportAction;
import hdsec.web.project.user.model.SecUser;

import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ExportHardDiskAction extends BMCommonExportAction {

	private static final long serialVersionUID = 1L;
	private String computer_barcode = "";
	private String computer_code = "";
	private Integer hdisk_status = null;
	private String duty_dept_id = "";
	private String duty_user_id = "";
	private String conf_code = "";
	private String retrieve_user_id = ""; // 录入人员ID
	private Date retrieve_time = null;// 录入时间
	private String summ = "";// 备注
	private String approve_url = "";
	private String dept_ids = "";
	private String type = "N";

	private final String filename = "硬盘台账记录-" + sdf1.format(new Date()) + ".xls";
	private final String sheetName = "硬盘台账记录";
	private final String[] titles = { "序号", "计算机类型", "计算机条码", "保密编号", "责任人", "责任部门", "责任单位", "硬盘序列号", "回收人 ", "回收时间",
			"审批单链接", "硬盘状态", "备注" };

	public String getComputer_barcode() {
		return computer_barcode;
	}

	public void setComputer_barcode(String computer_barcode) {
		this.computer_barcode = computer_barcode;
	}

	public String getComputer_code() {
		return computer_code;
	}

	public void setComputer_code(String computer_code) {
		this.computer_code = computer_code;
	}

	public Integer getHdisk_status() {
		return hdisk_status;
	}

	public void setHdisk_status(Integer hdisk_status) {
		this.hdisk_status = hdisk_status;
	}

	public String getDuty_dept_id() {
		return duty_dept_id;
	}

	public void setDuty_dept_id(String duty_dept_id) {
		this.duty_dept_id = duty_dept_id;
	}

	public String getDuty_user_id() {
		return duty_user_id;
	}

	public void setDuty_user_id(String duty_user_id) {
		this.duty_user_id = duty_user_id;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDept_ids() {
		return dept_ids;
	}

	public String getFilename() {
		return filename;
	}

	public String getSheetName() {
		return sheetName;
	}

	public String[] getTitles() {
		return titles;
	}

	public void setCurUser(SecUser curUser) {
		this.curUser = curUser;
	}

	protected SecUser curUser = getSecUserFromSession();

	public SecUser getCurUser() {
		return curUser;
	}

	public void setDept_ids(String dept_ids) {
		this.dept_ids = dept_ids;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		/*
		 * duty_user_id = getCurUser().getUser_iidd();
		 */
		map.put("conf_code", conf_code);
		map.put("computer_barcode", computer_barcode);
		map.put("hdisk_status", hdisk_status);
		map.put("duty_dept_id", duty_dept_id);
		map.put("duty_user_id", duty_user_id);
		OutputStream os = createFile(filename);
		exportFileBM(os, map, sheetName, titles, "harddisk");
		return SUCCESS;
	}

}