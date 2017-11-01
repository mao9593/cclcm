package hdsec.web.project.computer.action;

import hdsec.web.project.computer.model.InfoType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetInfoTypeAction extends ComputerBaseAction {

	private static final long serialVersionUID = 1L;
	private List<InfoType> infotype = null;
	// private String type = "";
	private Integer info = null;

	public void setInfo(Integer info) {
		this.info = info;
	}

	public List<InfoType> getInfotype() {
		return infotype;
	}

	@Override
	public String executeFunction() throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("device_type", info);
			map.put("statue", "0");
			if (info == 5) {
				map.put("product_type", "2");
			}
			infotype = computerService.getInfoTypeList(map);
			// a = infotype.get(0).getInfo_type();
		} catch (Exception e) {
			logger.error("Exception occurs in GetInfoTypeAction...");
			logger.error(e.getMessage());
		}
		return SUCCESS;
	}
}