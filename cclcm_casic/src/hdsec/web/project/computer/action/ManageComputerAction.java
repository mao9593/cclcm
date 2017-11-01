package hdsec.web.project.computer.action;

import hdsec.web.project.computer.model.EntityComputer;
import hdsec.web.project.device.model.DeviceType;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.util.StringUtils;

public class ManageComputerAction extends ComputerBaseAction {

	private static final long serialVersionUID = 1L;
	private String conf_code = "";
	private String seclv_code = "";
	private String computer_code = "";
	private Integer computer_status = null;
	private String duty_dept_id = "";
	private String duty_user_id = "";
	private String duty_dept_name = "";
	private String duty_user_name = "";
	private List<EntityComputer> computerList = null;
	private List<String> dept_ids = null;
	private String type = "S";
	private List<String> depts = null;

	public String getConf_code() {
		return conf_code;
	}

	public void setConf_code(String conf_code) {
		this.conf_code = conf_code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public List<DeviceType> getComputerTypeList() {
		return computerService.getDeviceTypeList();
	}

	public List<EntityComputer> getComputerList() {
		return computerList;
	}

	public void setComputerList(List<EntityComputer> computerList) {
		this.computerList = computerList;
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getComputer_code() {
		return computer_code;
	}

	public void setComputer_code(String computer_code) {
		this.computer_code = computer_code;
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

	// 前台部门选择框为多选，将多选id存到list类型中在数据库进行查询
	private void getAllDept(String dept) {
		depts = new ArrayList<String>();
		String temp[] = dept.split(",");
		for (int i = 0; i < temp.length; i++) {
			depts.add(temp[i]);
		}
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
		if (type.equalsIgnoreCase("SELF") || type.equalsIgnoreCase("S")) {
			duty_user_id = getCurUser().getUser_iidd();
		} else if (type.equalsIgnoreCase("DEPT")) {
			String web_url = getModuleName().toLowerCase() + "/" + getTitleName().toLowerCase() + ".action?type=DEPT";
			dept_ids = basicService.getAdminDeptIdList(getCurUser().getUser_iidd(), web_url);
			if (dept_ids == null || dept_ids.size() == 0) {
				throw new Exception("没有配置管理部门,请联系系统管理员进行配置");
			}
		} else if (type.equalsIgnoreCase("D")) {
			String web_url = getModuleName().toLowerCase() + "/" + getTitleName().toLowerCase() + ".action?type=D";
			dept_ids = basicService.getAdminDeptIdList(getCurUser().getUser_iidd(), web_url);
			if (dept_ids == null || dept_ids.size() == 0) {
				throw new Exception("没有配置管理部门,请联系系统管理员进行配置");
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("conf_code", conf_code);
		map.put("seclv_code", seclv_code);
		map.put("computer_code", computer_code);
		map.put("computer_status", computer_status);
		if (!duty_dept_id.equals("")) {
			getAllDept(duty_dept_id);
			map.put("duty_dept_id", depts);
		}
		map.put("duty_user_id", duty_user_id);
		map.put("scope_dept_ids", dept_ids);

		String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		beginIndex = page * pageSize;
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		totalSize = computerService.getAllComputerSize(map);
		computerList = computerService.getAllComputerList(map);
		return SUCCESS;
	}
}