package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.FileInfo;

import java.util.List;

/**
 * 查看版本
 * 
 * @author yueying
 * 
 */
public class ViewVersionFileAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private List<FileInfo> versions = null;
	
	@Override
	public String executeFunction() throws Exception {
		versions = basicService.getFileInfosByType("CLIENT");
		return SUCCESS;
	}
	
	public List<FileInfo> getVersions() {
		return versions;
	}
	
}
