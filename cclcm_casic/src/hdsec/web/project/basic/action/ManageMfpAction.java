package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysMfp;

import java.util.List;

/**
 * 一体机列表
 * 
 * @author renmingfei
 * 
 */
public class ManageMfpAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private List<SysMfp> mfpList = null;
	
	public List<SysMfp> getMfpList() {
		return mfpList;
	}
	
	@Override
	public String executeFunction() throws Exception {
		mfpList = basicService.getSysMfpList();
		return SUCCESS;
	}
}
