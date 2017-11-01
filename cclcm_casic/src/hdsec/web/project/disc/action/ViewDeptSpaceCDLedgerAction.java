package hdsec.web.project.disc.action;

import hdsec.web.project.disc.model.EntitySpaceCD;
import hdsec.web.project.user.model.SecDept;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 空白盘管理
 * 
 * @author zp
 * @author lidepeng(20150 812)
 * @author lishu 2015-12-17
 */
public class ViewDeptSpaceCDLedgerAction extends DiscBaseAction {
	private static final long serialVersionUID = 1L;
	private String barcode;
	private String seclv_code = null;
	private String spacecd_state = "";
	private Date startTime = null;
	private Date endTime = null;
	private String scope_dept_id = "";
	private List<SecDept> secAdminDeptList;
	private List<String> dept_ids;
	private String user_name = "";
	private String dept_name = "";
	private List<EntitySpaceCD> items;
	private String cd_type = "";
	private String spacecd_type = "";

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getSpacecd_state() {
		return spacecd_state;
	}

	public void setSpacecd_state(String spacecd_state) {
		this.spacecd_state = spacecd_state;
	}

	public List<EntitySpaceCD> getItems() {
		return items;
	}

	public void setItems(List<EntitySpaceCD> items) {
		this.items = items;
	}

	public String getCd_type() {
		return cd_type;
	}

	public void setCd_type(String cd_type) {
		this.cd_type = cd_type;
	}

	public void setDept_ids(List<String> dept_ids) {
		this.dept_ids = dept_ids;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getSpacecd_type() {
		return spacecd_type;
	}

	public void setSpacecd_type(String spacecd_type) {
		this.spacecd_type = spacecd_type;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getStartTime() {
		return sdf.format(startTime);
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return sdf.format(endTime);
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getScope_dept_id() {
		return scope_dept_id;
	}

	public void setScope_dept_id(String scope_dept_id) {
		this.scope_dept_id = scope_dept_id;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public List<SecDept> getSecAdminDeptList() {
		return secAdminDeptList;
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
		String web_url = getModuleName().toLowerCase() + "/" + getTitleName().toLowerCase() + ".action";
		dept_ids = basicService.getAdminDeptIdList(getCurUser().getUser_iidd(), web_url);
		if (dept_ids == null || dept_ids.size() == 0) {
			throw new Exception("没有配置管理部门,请联系系统管理员进行配置");
		}
		secAdminDeptList = basicService.getDeptAdminList(getCurUser().getUser_iidd(), web_url);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("barcode", barcode);
		map.put("spacecd_state", spacecd_state);
		map.put("seclv_code", seclv_code);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("scope", "DEPT");
		map.put("scope_dept_id", scope_dept_id);
		map.put("scope_dept_ids", dept_ids);
		map.put("user_name", user_name);
		map.put("dept_name", dept_name);
		map.put("cd_type", cd_type);
		map.put("spacecd_type", spacecd_type);
		items = discService.getDeptEntitySpaceCdList(map);
		return SUCCESS;
	}
}
