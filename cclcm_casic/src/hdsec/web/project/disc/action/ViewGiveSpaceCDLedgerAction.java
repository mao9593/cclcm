package hdsec.web.project.disc.action;

import hdsec.web.project.disc.model.EntitySpaceCD;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 已分发空白盘列表
 * 
 * @author zp
 */
public class ViewGiveSpaceCDLedgerAction extends DiscBaseAction {
	private static final long serialVersionUID = 1L;
	private String barcode;
	private Integer seclv_code = null;
	private Date startTime = null;
	private Date endTime = null;
	private List<EntitySpaceCD> items;
	private String duty_user_iidd;
	private String duty_user_name;

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name;
	}

	public String getDuty_user_iidd() {
		return duty_user_iidd;
	}

	public void setDuty_user_iidd(String duty_user_iidd) {
		this.duty_user_iidd = duty_user_iidd;
	}

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

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("barcode", barcode);
		map.put("spacecd_state", 1);
		map.put("seclv_code", seclv_code);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("duty_user_iidd", duty_user_iidd);
		map.put("duty_user_name", duty_user_name);
		items = discService.getDeptEntitySpaceCdList(map);
		return SUCCESS;
	}
}
