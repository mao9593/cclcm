package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysBurner;

import java.util.List;

/**
 * 刻录机列表
 * 
 * @author renmingfei
 * 
 */
public class ManageBurnerAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private List<SysBurner> burnerList = null;
	
	public List<SysBurner> getBurnerList() {
		return burnerList;
	}
	
	@Override
	public String executeFunction() throws Exception {
		burnerList = basicService.getSysBurnerList();
		return SUCCESS;
	}
}
