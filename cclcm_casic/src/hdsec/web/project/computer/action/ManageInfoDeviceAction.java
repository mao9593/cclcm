package hdsec.web.project.computer.action;

import hdsec.web.project.computer.model.ComputerStatusEnum;
import hdsec.web.project.computer.model.EntityInfoDevice;
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

public class ManageInfoDeviceAction extends ComputerBaseAction {

	private static final long serialVersionUID = 1L;
	private String device_barcode = "";// 设备条码编号
	private String duty_user_id = "";// 责任人ID
	private String duty_dept_id = "";// 责任部门ID
	private String conf_code = "";// 保密编号
	private String device_series = "";// 设备编号
	private Integer device_type = null;// 设备类型（1: 办公自动化设备2:外部设备3:安全产品4:介质5:其他）
	private String info_id = "";// 详细设备ID
	private Integer device_statues = null;// 设备状态
	private Integer device_seclv = null;// 设备密级
	private List<EntityInfoDevice> deviceList = null;

	private String duty_user_name = "";// 责任人
	private String duty_dept_name = "";// 责任部门ID
	private List<String> dept_ids = null;
	private String type = "";
	private List<SecDept> secAdminDeptList = null;
	private List<String> depts = null;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDevice_barcode() {
		return device_barcode;
	}

	public void setDevice_barcode(String device_barcode) {
		this.device_barcode = device_barcode;
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

	public String getDevice_series() {
		return device_series;
	}

	public void setDevice_series(String device_series) {
		this.device_series = device_series;
	}

	public Integer getDevice_type() {
		return device_type;
	}

	public void setDevice_type(Integer device_type) {
		this.device_type = device_type;
	}

	public String getInfo_id() {
		return info_id;
	}

	public void setInfo_id(String info_id) {
		this.info_id = info_id;
	}

	public String getDuty_dept_id() {
		return duty_dept_id;
	}

	public void setDuty_dept_id(String duty_dept_id) {
		this.duty_dept_id = duty_dept_id;
	}

	public Integer getDevice_statues() {
		return device_statues;
	}

	public void setDevice_statues(Integer device_statues) {
		this.device_statues = device_statues;
	}

	public Integer getDevice_seclv() {
		return device_seclv;
	}

	public void setDevice_seclv(Integer device_seclv) {
		this.device_seclv = device_seclv;
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

	public List<EntityInfoDevice> getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(List<EntityInfoDevice> deviceList) {
		this.deviceList = deviceList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public List<ComputerStatusEnum> getDsList() {
		List<ComputerStatusEnum> tmpList = new ArrayList<ComputerStatusEnum>();
		for (ComputerStatusEnum item : ComputerStatusEnum.values()) {
			tmpList.add(item);
		}
		return tmpList;
	}

	private void getAllDept(String dept) {
		depts = new ArrayList<String>();
		String temp[] = dept.split(",");
		for (int i = 0; i < temp.length; i++) {
			depts.add(temp[i]);
		}
	}

	public List<SecDept> getSecAdminDeptList() {
		return secAdminDeptList;
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
		map.put("device_barcode", device_barcode);
		map.put("conf_code", conf_code);
		map.put("device_series", device_series);
		map.put("device_type", device_type);
		map.put("info_id", info_id);
		map.put("device_statues", device_statues);
		map.put("device_seclv", device_seclv);
		if (type.equals("SELF")) {
			map.put("duty_user_id", getCurUser().getUser_iidd());
			deviceList = computerService.getInfoDeviceList(map);
		} else {
			map.put("duty_user_id", duty_user_id);
			if (!duty_dept_id.equals("")) {
				getAllDept(duty_dept_id);
				map.put("duty_dept_id", depts);
			}
			map.put("scope_dept_ids", dept_ids);

			String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
			if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
				page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
			}
			beginIndex = page * pageSize;
			RowBounds rbs = new RowBounds(beginIndex, pageSize);
			totalSize = computerService.getAllInfoDeviceSize(map);
			deviceList = computerService.getInfoDeviceList(map);
		}

		return SUCCESS;
	}
}