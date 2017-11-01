package hdsec.web.project.computer.action;

import hdsec.web.project.computer.model.InfoType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManageInfoDeviceTypeAction extends ComputerBaseAction {

	private static final long serialVersionUID = 1L;
	private String type = "N";
	private String id = "";
	private List<InfoType> infotype1 = null;
	private List<InfoType> infotype2 = null;
	private List<InfoType> infotype3 = null;
	private List<InfoType> infotype4 = null;
	private List<InfoType> infotype5 = null;
	private List<InfoType> infotype6 = null;
	private String is_sealed = "N";

	public String getIs_sealed() {
		return is_sealed;
	}

	public void setIs_sealed(String is_sealed) {
		this.is_sealed = is_sealed;
	}

	public List<InfoType> getInfotype1() {
		return infotype1;
	}

	public List<InfoType> getInfotype2() {
		return infotype2;
	}

	public List<InfoType> getInfotype3() {
		return infotype3;
	}

	public List<InfoType> getInfotype4() {
		return infotype4;
	}

	public List<InfoType> getInfotype5() {
		return infotype5;
	}

	public List<InfoType> getInfotype6() {
		return infotype6;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String executeFunction() throws Exception {
		InfoType sort = new InfoType();
		if (type.equals("D") && !id.equals("")) {
			sort.setInfo_id(id);
			sort.setStatue(1);
			computerService.updateInfoType(sort);
		} else if (type.equals("R") && !id.equals("")) {
			sort.setInfo_id(id);
			sort.setStatue(0);
			computerService.updateInfoType(sort);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("device_type", "1");
		infotype1 = computerService.getInfoTypeList(map);
		map.put("device_type", "2");
		infotype2 = computerService.getInfoTypeList(map);
		map.put("device_type", "3");
		infotype3 = computerService.getInfoTypeList(map);
		map.put("device_type", "4");
		infotype4 = computerService.getInfoTypeList(map);
		map.put("device_type", "5");
		infotype5 = computerService.getInfoTypeList(map);
		map.put("device_type", "6");
		infotype6 = computerService.getInfoTypeList(map);
		return SUCCESS;
	}
}