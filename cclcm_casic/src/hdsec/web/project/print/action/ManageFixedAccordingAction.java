package hdsec.web.project.print.action;

import hdsec.web.project.print.model.FixAccording;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 中物院机关定密依据管理库
 * 
 * @author guojiao
 * 
 */

public class ManageFixedAccordingAction extends PrintBaseAction {
	private static final long serialVersionUID = 1L;
	private List<FixAccording> fixList1 = null;
	private List<FixAccording> fixList2 = null;
	private List<FixAccording> fixList3 = null;

	public List<FixAccording> getFixList1() {
		return fixList1;
	}

	public List<FixAccording> getFixList2() {
		return fixList2;
	}

	public List<FixAccording> getFixList3() {
		return fixList3;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", "1");
		fixList1 = printService.getFixAccordingByType(map);
		map.put("type", "2");
		fixList2 = printService.getFixAccordingByType(map);
		map.put("type", "3");
		fixList3 = printService.getFixAccordingByType(map);

		return SUCCESS;
	}

}
