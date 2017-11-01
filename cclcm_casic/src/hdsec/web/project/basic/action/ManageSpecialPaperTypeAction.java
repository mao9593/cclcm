package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysUsage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManageSpecialPaperTypeAction extends BasicBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<SysUsage> usageList = new ArrayList<SysUsage>();
	private String is_sealed = "N";

	public List<SysUsage> getUsageList() {
		return usageList;
	}

	public void setUsageList(List<SysUsage> usageList) {
		this.usageList = usageList;
	}

	public String getIs_sealed() {
		return is_sealed;
	}

	public void setIs_sealed(String is_sealed) {
		this.is_sealed = is_sealed;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("is_sealed", is_sealed);
		map.put("type", "N");
		usageList = basicService.getSpecialPaperTypeList(map);
		return SUCCESS;
	}

}