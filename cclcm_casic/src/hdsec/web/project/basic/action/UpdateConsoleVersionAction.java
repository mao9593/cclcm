package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.FileInfo;

import java.util.List;

/**
 * 设定控制台版本
 * 
 * @author yy
 * 
 */
public class UpdateConsoleVersionAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String hidConsole_code = "";// 隐藏编号
	private String hidSet_version = "";// 设定版本（强制升级或降级）
	private String hidScope = "";// 设定范围
	private boolean is_set = false;
	
	public void setHidSet_version(String hidSet_version) {
		this.hidSet_version = hidSet_version;
	}
	
	public void setHidScope(String hidScope) {
		this.hidScope = hidScope;
	}
	
	public List<FileInfo> getVersions() {
		return basicService.getFileInfosByType("CONSOLE");
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (is_set) {
			if (hidScope.equals("single")) {
				basicService.updateConsoleVersion(hidConsole_code, hidSet_version);
				insertAdminLog("设定控制台版本：编号[" + hidConsole_code + "]版本[" + hidSet_version + "]");
			} else if (hidScope.equals("all")) {
				basicService.updateConsoleVersion("", hidSet_version);
				insertAdminLog("全局设定控制台版本：版本[" + hidSet_version + "]");
			}
			return "ok";
		}
		return SUCCESS;
	}
	
	public void setIs_set(boolean is_set) {
		this.is_set = is_set;
	}
	
	public void setHidConsole_code(String hidConsole_code) {
		this.hidConsole_code = hidConsole_code;
	}
}
