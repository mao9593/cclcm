package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysExchangeBox;

import java.util.List;

/**
 * 智能交换柜列表
 * 
 * @author gaoxm
 * 
 */
public class ManageExchangeBoxAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private List<SysExchangeBox> exchangeBoxList = null;
	
	public List<SysExchangeBox> getExchangeBoxList() {
		return exchangeBoxList;
	}
	
	@Override
	public String executeFunction() throws Exception {
		exchangeBoxList = basicService.getSysExchangeBoxList();
		return SUCCESS;
	}
	
}
