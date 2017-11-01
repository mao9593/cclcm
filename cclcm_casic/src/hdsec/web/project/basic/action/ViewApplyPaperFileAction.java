package hdsec.web.project.basic.action;

import hdsec.web.project.ledger.model.EntityPaper;
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
 * 归档paper申请
 * 
 * @author yueying
 * 
 */
public class ViewApplyPaperFileAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private List<EntityPaper> paperLedgerList;
	private String paper_barcode;
	private String duty_user_name;
	private String seclv_code;
	private Date start_time;
	private Date end_time;
	private String paper_state;
	private String dept_id = "";
	private String dept_name = "";
	private List<SecDept> secAdminDeptList;
	private String user_name;
	private String hid_dept_ids = "";

	public String getHid_dept_ids() {
		return hid_dept_ids;
	}

	public void setHid_dept_ids(String hid_dept_ids) {
		this.hid_dept_ids = hid_dept_ids;
	}

	public List<EntityPaper> getPaperLedgerList() {
		return paperLedgerList;
	}

	public void setPaperLedgerList(List<EntityPaper> paperLedgerList) {
		this.paperLedgerList = paperLedgerList;
	}

	public String getPaper_barcode() {
		return paper_barcode;
	}

	public void setPaper_barcode(String paper_barcode) {
		this.paper_barcode = paper_barcode.trim();
	}

	public String getPaper_state() {
		return paper_state;
	}

	public void setPaper_state(String paper_state) {
		this.paper_state = paper_state;
	}

	public String getDuty_user_name() {
		return duty_user_name;
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
		map.put("paper_barcode", paper_barcode);
		map.put("duty_user_name", duty_user_name);
		map.put("seclv_code", seclv_code);
		map.put("start_time", start_time);
		map.put("end_time", end_time);
		map.put("paper_state", 9);// 申请归档
		map.put("job_status", "true");// 审批通过的paper
		map.put("admin_dept_ids", admin_dept_ids);
		map.put("duty_dept_id", dept_id);
		map.put("user_name", user_name);

		String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		beginIndex = page * pageSize;
		RowBounds rbs = new RowBounds(beginIndex, pageSize);

		// if (paper_barcode != null && !"".equals(paper_barcode)) {
		totalSize = ledgerService.getAllPaperLedgerSize(map);
		paperLedgerList = ledgerService.getAllPaperLedgerList(map, rbs);
		// }
		return SUCCESS;
	}
}
