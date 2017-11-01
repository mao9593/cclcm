package hdsec.web.project.disc.action;

import hdsec.web.project.disc.model.EntitySpaceCD;
import hdsec.web.project.user.model.SecLevel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 空白盘库存列表
 * 
 * @author zp
 * @author lidepeng(20150812)
 */
public class ViewSpaceCDStockAction extends DiscBaseAction {
	private static final long serialVersionUID = 1L;
	private String scope_dept_name = "";// 归属部门
	private Integer seclv_code = null;// 密级
	private List<EntitySpaceCD> items;

	public String getScope_dept_name() {
		return scope_dept_name;
	}

	public void setScope_dept_name(String scope_dept_name) {
		this.scope_dept_name = scope_dept_name;
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

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seclv_code", seclv_code);
		map.put("scope_dept_id", getCurUser().getDept_id());
		map.put("borrowspacecd_state", true);
		/* items = discService.getDeptEntitySpaceCdList(map); */

		items = discService.getDeptSpaceCdStock(map);
		return SUCCESS;
	}
}
