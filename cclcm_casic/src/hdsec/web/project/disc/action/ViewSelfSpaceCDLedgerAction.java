package hdsec.web.project.disc.action;

import hdsec.web.project.disc.model.EntitySpaceCD;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 空白盘管理
 * 
 * @author zp
 */

/**
 * 个人空白盘台帐
 * 
 * @author lishu 2015-12-16
 */
public class ViewSelfSpaceCDLedgerAction extends DiscBaseAction {
	private static final long serialVersionUID = 1L;
	private String barcode;
	private Integer seclv_code = null;
	private Date startTime = null;
	private Date endTime = null;
	private String user_name = "";
	private String cd_type = "";
	private String spacecd_type = "";

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	private List<EntitySpaceCD> items;

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public List<EntitySpaceCD> getItems() {
		return items;
	}

	public void setItems(List<EntitySpaceCD> items) {
		this.items = items;
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
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

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public String getCd_type() {
		return cd_type;
	}

	public void setCd_type(String cd_type) {
		this.cd_type = cd_type;
	}

	public String getSpacecd_type() {
		return spacecd_type;
	}

	public void setSpacecd_type(String spacecd_type) {
		this.spacecd_type = spacecd_type;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("barcode", barcode);
		map.put("spacecd_state", 1);
		map.put("seclv_code", seclv_code);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("user_name", user_name);
		map.put("duty_user_iidd", getCurUser().getUser_iidd());
		map.put("cd_type", cd_type);
		map.put("spacecd_type", spacecd_type);
		items = discService.getSelfEntitySpaceCdList(map);
		return SUCCESS;
	}
}
