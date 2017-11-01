package hdsec.web.project.disc.action;

import hdsec.web.project.disc.model.EntitySpaceCD;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 空白盘借用列表
 * 
 * @author zp
 * @author lidepeng(20150812)
 */
public class ViewBorrowSpaceCDAction extends DiscBaseAction {
	private static final long serialVersionUID = 1L;
	private String barcode;
	private Integer seclv_code = null;
	private Date startTime = null;
	private Date endTime = null;
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

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("barcode", barcode);
		map.put("seclv_code", seclv_code);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("scope_dept", "DEPT");
		map.put("scope_dept_id", getCurUser().getDept_id());
		map.put("borrowspacecd_state", true);
		items = discService.getDeptEntitySpaceCdList(map);
		return SUCCESS;
	}
}
