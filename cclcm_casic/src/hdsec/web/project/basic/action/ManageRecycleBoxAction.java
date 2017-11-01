package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysRecycleBox;

import java.util.List;

/**
 * 智能回收柜列表
 * 
 * @author gaoxm
 * 
 */
public class ManageRecycleBoxAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private List<SysRecycleBox> recycleBoxList = null;
	
	public List<SysRecycleBox> getRecycleBoxList() {
		return recycleBoxList;
	}
	
	@Override
	public String executeFunction() throws Exception {
		recycleBoxList = basicService.getSysRecycleBoxList();
		return SUCCESS;
	}
}
