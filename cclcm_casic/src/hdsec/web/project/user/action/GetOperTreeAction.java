package hdsec.web.project.user.action;

import hdsec.web.project.user.model.OperTree;
import hdsec.web.project.user.model.SecSubsys;

import java.util.List;

/**
 * 查询操作树图
 * @author renmingfei
 *
 */
public class GetOperTreeAction extends UserBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String subsys_code = "";
	private List<SecSubsys> subsysList = null;
	private List<OperTree> operTreeList = null;
	private String config = "N";
	
	public String getSubsys_code() {
		return subsys_code;
	}
	
	public void setSubsys_code(String subsys_code) {
		this.subsys_code = subsys_code;
	}
	
	public List<SecSubsys> getSubsysList() {
		return subsysList;
	}
	
	public List<OperTree> getOperTreeList() {
		return operTreeList;
	}
	
	public void setConfig(String config) {
		this.config = config;
	}
	
	public String getConfig() {
		return config;
	}
	
	@Override
	public String executeFunction() throws Exception {
		subsysList = userService.getAllSubsysAsCon();
		if (subsysList.size() > 0 && subsys_code.equals("")) {
			setSubsys_code(subsysList.get(0).getSubsys_code());
		}
		operTreeList = userService.getOperTreeBySubsys(subsys_code);
		return SUCCESS;
	}
}
