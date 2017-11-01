package hdsec.web.project.computer.action;

import hdsec.web.project.computer.model.EntityBook;
import hdsec.web.project.user.model.SecDept;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.util.StringUtils;

public class ManageBookAction extends ComputerBaseAction {

	private static final long serialVersionUID = 1L;
	private String book_barcode = "";
	private String conf_code = "";
	private String seclv_code = "";
	private String book_code = "";
	private Integer computer_status = null;
	private String duty_dept_id = "";
	private String duty_user_id = "";
	private String duty_dept_name = "";
	private String duty_user_name = "";
	private List<EntityBook> computerList = null;
	private List<String> dept_ids = null;
	private List<SecDept> secAdminDeptList = null;
	private List<String> depts = null;
	private String type = "";

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBook_barcode() {
		return book_barcode;
	}

	public void setBook_barcode(String book_barcode) {
		this.book_barcode = book_barcode;
	}

	public String getConf_code() {
		return conf_code;
	}

	public void setConf_code(String conf_code) {
		this.conf_code = conf_code;
	}

	public Integer getComputer_status() {
		return computer_status;
	}

	public void setComputer_status(Integer computer_status) {
		this.computer_status = computer_status;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public List<EntityBook> getComputerList() {
		return computerList;
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getBook_code() {
		return book_code;
	}

	public void setBook_code(String book_code) {
		this.book_code = book_code;
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

	public List<SecDept> getSecAdminDeptList() {
		return secAdminDeptList;
	}

	// 前台部门选择框为多选，将多选id存到list类型中在数据库进行查询
	private void getAllDept(String dept) {
		depts = new ArrayList<String>();
		String temp[] = dept.split(",");
		for (int i = 0; i < temp.length; i++) {
			depts.add(temp[i]);
		}
	}

	@Override
	public String executeFunction() throws Exception {
		if (type.equals("DEPT")) {
			String web_url = getModuleName().toLowerCase() + "/" + getTitleName().toLowerCase() + ".action?type=DEPT";
			dept_ids = basicService.getAdminDeptIdList(getCurUser().getUser_iidd(), web_url);
			if (dept_ids == null || dept_ids.size() == 0) {
				throw new Exception("没有配置管理部门,请联系系统管理员进行配置");
			}
			secAdminDeptList = basicService.getDeptAdminList(getCurUser().getUser_iidd(), web_url);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("book_barcode", book_barcode);
		map.put("conf_code", conf_code);
		map.put("seclv_code", seclv_code);
		map.put("book_code", book_code);
		map.put("book_status", computer_status);
		if (!duty_dept_id.equals("")) {
			getAllDept(duty_dept_id);
			map.put("duty_dept_id", depts);
		}
		if (type.equals("SELF")) {
			map.put("duty_user_id", getCurUser().getUser_iidd());
		} else {
			map.put("duty_user_id", duty_user_id);
		}
		map.put("scope_dept_ids", dept_ids);

		String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		beginIndex = page * pageSize;
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		totalSize = computerService.getAllBookSize(map);
		computerList = computerService.getBookList(map);
		return SUCCESS;
	}
}