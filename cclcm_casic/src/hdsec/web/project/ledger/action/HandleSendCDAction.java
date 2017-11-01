package hdsec.web.project.ledger.action;

import hdsec.web.project.ledger.model.EntityCD;
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
 * 光盘外发处理
 * 
 * @author lixiang
 * 
 */
public class HandleSendCDAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private List<EntityCD> cdLedgerList;
	private String send_num;
	private String cd_barcode;
	private String duty_user_name;
	private String seclv_code;
	private Date start_time;
	private Date end_time;
	private Integer cd_state;
	private String conf_code = "";
	private String user_name;
	private String dept_name;

	public List<EntityCD> getCdLedgerList() {
		return cdLedgerList;
	}

	public void setCdLedgerList(List<EntityCD> cdLedgerList) {
		this.cdLedgerList = cdLedgerList;
	}

	public String getSend_num() {
		return send_num;
	}

	public void setSend_num(String send_num) {
		this.send_num = send_num.trim();
	}

	public String getCd_barcode() {
		return cd_barcode;
	}

	public void setCd_barcode(String cd_barcode) {
		this.cd_barcode = cd_barcode.trim();
	}

	public Integer getCd_state() {
		return cd_state;
	}

	public void setCd_state(Integer cd_state) {
		this.cd_state = cd_state;
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

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name.trim();
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
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

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	@Override
	public String executeFunction() throws Exception {
		String web_url = getModuleName().toLowerCase() + "/" + getTitleName().toLowerCase() + ".action";
		List<String> dept_ids = basicService.getAdminDeptIdList(getCurUser().getUser_iidd(), web_url);
		if (dept_ids == null || dept_ids.size() == 0) {
			throw new Exception("没有配置管理部门,请联系系统管理员进行配置");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("send_num", send_num);
		map.put("cd_barcode", cd_barcode);
		map.put("duty_user_name", duty_user_name);
		map.put("seclv_code", seclv_code);
		map.put("start_time", start_time);
		map.put("end_time", end_time);
		map.put("job_status", "true");// 审批通过的cd
		map.put("cd_state", 16);// 类型为外发中
		map.put("admin_dept_ids", dept_ids);
		map.put("conf_code", conf_code);
		map.put("user_name", user_name);
		map.put("dept_name", dept_name);
		map.put("output_undertaker", getCurUser().getUser_iidd());

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
