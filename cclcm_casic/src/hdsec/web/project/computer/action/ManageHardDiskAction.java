package hdsec.web.project.computer.action;

import hdsec.web.project.computer.model.EntityHardDisk;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.util.StringUtils;

public class ManageHardDiskAction extends ComputerBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String conf_code = "";// 保密编号
	private String computer_barcode = "";// 计算机条码
	private String duty_dept_id = "";// 责任部门
	private String duty_entp_id = "";// 责任单位
	private String duty_user_id = "";// 责任人
	private String duty_dept_name = "";
	private String duty_user_name = "";
	private String duty_entp_name = "";
	private String retrieve_user_id = ""; // 录入人员ID
	private Date retrieve_time = null;// 录入时间
	private String summ = "";// 备注
	private List<EntityHardDisk> hdiskList = null;
	private List<String> dept_ids = null;
	private String approve_url = "";
	private Integer hdisk_status = null;
	private Date start_time = null;// 录入时间
	private Date end_time = null;// 录入时间
	private String retrieve_user_name = "";
	private String hdisk_no = "";

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

	public String getComputer_barcode() {
		return computer_barcode;
	}

	public void setComputer_barcode(String computer_barcode) {
		this.computer_barcode = computer_barcode;
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

	public String getDuty_user_id() {
		return duty_user_id;
	}

	public void setDuty_user_id(String duty_user_id) {
		this.duty_user_id = duty_user_id;
	}

	public String getDuty_dept_name() {
		return duty_dept_name;
	}

	public void setDuty_dept_name(String duty_dept_name) {
		this.duty_dept_name = duty_dept_name;
	}

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name;
	}

	public String getDuty_entp_name() {
		return duty_entp_name;
	}

	public void setDuty_entp_name(String duty_entp_name) {
		this.duty_entp_name = duty_entp_name;
	}

	public List<EntityHardDisk> getHdiskList() {
		return hdiskList;
	}

	public void setHdiskList(List<EntityHardDisk> hdiskList) {
		this.hdiskList = hdiskList;
	}

	public void setDept_ids(List<String> dept_ids) {
		this.dept_ids = dept_ids;
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

	public Integer getHdisk_status() {
		return hdisk_status;
	}

	public void setHdisk_status(Integer hdisk_status) {
		this.hdisk_status = hdisk_status;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public String getStart_time_str() {
		return start_time == null ? "" : sdf.format(start_time);
	}

	public String getEnd_time_str() {
		return end_time == null ? "" : sdf.format(end_time);
	}

	public void setApprove_url(String approve_url) {
		this.approve_url = approve_url;
	}

	public String getDept_ids() {
		String ret = "";
		for (String d_item : dept_ids) {
			ret += d_item + ",";
		}
		if (ret.endsWith(",")) {
			ret = ret.substring(0, ret.length() - 1);
		}
		return ret;
	}

	@Override
	public String executeFunction() throws Exception {

		/* duty_user_id = getCurUser().getUser_iidd(); */
		/* String web_url = getModuleName().toLowerCase() + "/" + getTitleName().toLowerCase() + ".action?type=DEPT"; */
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("conf_code", conf_code);
		map.put("computer_barcode", computer_barcode);
		map.put("duty_dept_id", duty_dept_id);
		map.put("duty_user_id", duty_user_id);
		map.put("duty_entp_id", duty_entp_id);
		map.put("start_time", start_time);
		map.put("end_time", end_time);
		map.put("retrieve_user_name", retrieve_user_name);
		map.put("hdisk_no", hdisk_no);

		String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		beginIndex = page * pageSize;
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		totalSize = computerService.getAllHardDiskSize(map);
		hdiskList = computerService.getAllHardDiskList(map);
		return SUCCESS;
	}
}