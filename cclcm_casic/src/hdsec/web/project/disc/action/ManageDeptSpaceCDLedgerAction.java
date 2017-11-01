package hdsec.web.project.disc.action;

import hdsec.web.project.disc.model.EntitySpaceCD;
import hdsec.web.project.disc.model.SpaceCDEvent;
import hdsec.web.project.user.model.SecDept;
import hdsec.web.project.user.model.SecLevel;
import hdsec.web.project.user.model.SecUser;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 空白盘分发
 * 
 * @author lishu 2015-12-16
 */
public class ManageDeptSpaceCDLedgerAction extends DiscBaseAction {
	private static final long serialVersionUID = 1L;
	private String barcode;
	private String seclv_code = null;
	private String spacecd_state = "0";
	private Date startTime = null;
	private Date endTime = null;
	private String scope_dept_id = "";
	private List<SecDept> secAdminDeptList;
	private List<String> dept_ids;
	private String user_name = "";
	private String dept_name = "";
	private List<EntitySpaceCD> items = null;
	private String update = "N";
	private String ids = "";// 勾选的光盘id
	private String event_code = "";
	private String borrow_event_code = "";// 光盘对应分配给的申领时间event_code
	private String spacecd_ids = "";
	private SpaceCDEvent event = null;
	private String cd_type = "";// 光盘类型
	private String spacecd_type = "";// 空白盘类型

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

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getDept_name() {
		return dept_name;
	}

	public String getSpacecd_type() {
		return spacecd_type;
	}

	public void setSpacecd_type(String spacecd_type) {
		this.spacecd_type = spacecd_type;
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

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public void setSecAdminDeptList(List<SecDept> secAdminDeptList) {
		this.secAdminDeptList = secAdminDeptList;
	}

	public void setDept_ids(List<String> dept_ids) {
		this.dept_ids = dept_ids;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getBorrow_event_code() {
		return borrow_event_code;
	}

	public void setBorrow_event_code(String borrow_event_code) {
		this.borrow_event_code = borrow_event_code;
	}

	public String getSpacecd_ids() {
		return spacecd_ids;
	}

	public void setSpacecd_ids(String spacecd_ids) {
		this.spacecd_ids = spacecd_ids;
	}

	public SpaceCDEvent getEvent() {
		return event;
	}

	public void setEvent(SpaceCDEvent event) {
		this.event = event;
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
		String web_url = "disc/viewdeptspacecdledger.action";
		dept_ids = basicService.getAdminDeptIdList(getCurUser().getUser_iidd(), web_url);
		if (dept_ids == null || dept_ids.size() == 0) {
			throw new Exception("没有配置管理部门,请联系系统管理员进行配置");
		}
		secAdminDeptList = basicService.getDeptAdminList(getCurUser().getUser_iidd(), web_url);
		if (update.equalsIgnoreCase("Y")) {
			SecUser user = getCurUser();
			spacecd_ids = ids;
			String[] spacecds = ids.split(":");
			event = discService.getSpaceCDEventByEventCode(event_code);
			if (spacecds.length == event.getEnter_num()) {
				spacecd_type = event.getSpacecd_type();
				for (String spacecd : spacecds) {
					EntitySpaceCD spaceCD = discService.getEntitySpaceCdById(spacecd);
					if (spaceCD != null) {
						barcode = spaceCD.getBarcode();
					}
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("duty_user_iidd", event.getApply_user_iidd());
					map.put("duty_user_name", event.getApply_user_name());
					map.put("duty_dept_id", event.getApply_dept_id());
					map.put("duty_dept_name", event.getApply_dept_name());
					/*
					 * map.put("duty_user_iidd", getCurUser().getUser_iidd()); map.put("duty_user_name",
					 * getCurUser().getUser_name()); map.put("duty_dept_id", getCurUser().getDept_id());
					 * map.put("duty_dept_name", getCurUser().getDept_name());
					 */
					map.put("spacecd_state", 1);
					map.put("id", spacecd);
					map.put("cd_type", cd_type);
					map.put("borrow_event_code", event_code);
					map.put("spacecd_type", spacecd_type);
					/* map.put("painting_status", 1); */
					discService.updateSpaceCdById(map);
				}
				Map<String, Object> mmp = new HashMap<String, Object>();
				mmp.put("event_code", event_code);
				discService.updateSpaceCDEventByEventCode(mmp);
				return "ok";
			} else {
				throw new Exception("勾选的空白盘数量与所需分配的光盘数量不符，请核对");
			}
		} else {

			event = discService.getSpaceCDEventByEventCode(event_code);
			if (cd_type.equals("")) {
				if (event != null) {
					cd_type = event.getCd_type();
				}
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("barcode", barcode);
			map.put("spacecd_state", spacecd_state);
			map.put("seclv_code", seclv_code);
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("scope_dept_id", scope_dept_id);
			map.put("scope_dept_ids", dept_ids);
			map.put("user_name", user_name);
			map.put("dept_name", dept_name);
			map.put("cd_type", cd_type);
			map.put("spacecd_type", spacecd_type);
			map.put("painting_status", 1);

			items = discService.getDeptEntitySpaceCdList(map);

			if (items.size() == 0) {
				throw new Exception("空白盘未喷绘 或 已分配完毕，请对未喷绘空白盘进行喷绘 或 录入新的空白盘！");
			}

			return SUCCESS;
		}
	}
}
