package hdsec.web.project.basic.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.user.model.SecDept;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.util.StringUtils;

/**
 * 归档cd申请
 * 
 * @author yueying
 * 
 */
public class ViewApplyCDFileAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private List<EntityCD> cdLedgerList = null;
	private String cd_barcode = "";
	private String duty_user_name = "";
	private String seclv_code = "";
	private Date start_time = null;
	private Date end_time = null;
	private String cd_state;
	private String jobType;
	private String dept_id = "";
	private String dept_name = "";
	private String conf_code = "";
	private String user_name;
	private String hid_dept_ids = "";
	private List<SecDept> secAdminDeptList;

	public String getHid_dept_ids() {
		return hid_dept_ids;
	}

	public void setHid_dept_ids(String hid_dept_ids) {
		this.hid_dept_ids = hid_dept_ids;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public List<EntityCD> getCDLedgerList() {
		return cdLedgerList;
	}

	public void setCd_barcode(String cd_barcode) {
		this.cd_barcode = cd_barcode.trim();
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public String getCd_barcode() {
		return cd_barcode;
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public String getStart_time() {

		return sdf.format(start_time);
	}

	public String getEnd_time() {
		return sdf.format(end_time);
	}

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name.trim();
	}

	public String getCd_state() {
		return cd_state;
	}

	public void setCd_state(String cd_state) {
		this.cd_state = cd_state;
	}

	public String getJobType() {
		jobType = JobTypeEnum.BURN_REMAIN.getJobTypeCode();
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

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

	public List<SecDept> getSecAdminDeptList() {
		return secAdminDeptList;
	}

	public String getConf_code() {
		return conf_code;
	}

	public void setConf_code(String conf_code) {
		this.conf_code = conf_code.trim();
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	@Override
	public String executeFunction() throws Exception {
		String web_url = getModuleName().toLowerCase() + "/" + getTitleName().toLowerCase() + ".action";
		List<String> admin_dept_ids = basicService.getAdminDeptIdList(getCurUser().getUser_iidd(), web_url);
		if (admin_dept_ids == null || admin_dept_ids.size() == 0) {
			throw new Exception("没有配置管理部门,请联系系统管理员进行配置");
		}

		for (int i = 0; i < admin_dept_ids.size(); i++) {
			hid_dept_ids = hid_dept_ids + admin_dept_ids.get(i) + ",";
		}
		secAdminDeptList = basicService.getDeptAdminList(getCurUser().getUser_iidd(), web_url);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cd_barcode", cd_barcode);
		map.put("duty_user_name", duty_user_name);
		map.put("seclv_code", seclv_code);
		map.put("start_time", start_time);
		map.put("end_time", end_time);
		map.put("cd_state", 9);// 申请归档
		map.put("job_status", "true");// 审批通过的cd
		map.put("admin_dept_ids", admin_dept_ids);
		map.put("duty_dept_id", dept_id);
		map.put("conf_code", conf_code);
		map.put("user_name", user_name);

		String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		beginIndex = page * pageSize;
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		totalSize = ledgerService.getAllCDLedgerSize(map);
		cdLedgerList = ledgerService.getAllCDLedgerList(map, rbs);

		return SUCCESS;
	}
}
