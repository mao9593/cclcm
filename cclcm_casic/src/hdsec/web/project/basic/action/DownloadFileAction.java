package hdsec.web.project.basic.action;

import hdsec.web.project.burn.model.BurnFile;
import hdsec.web.project.common.PropertyUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 常用文档下载
 * 
 * @author renmingfei 2014-8-4
 */
public class DownloadFileAction extends BasicBaseAction {

	private static final long serialVersionUID = 1L;
	private List<BurnFile> fileList = null;

	public List<BurnFile> getFileList() {
		return fileList;
	}

	@Override
	public String executeFunction() throws Exception {
		String basePath = PropertyUtil.getDownloadFileStorePath();
		File path = new File(basePath);
		fileList = new ArrayList<BurnFile>();
		if (path.isDirectory()) {
			File[] files = path.listFiles();
			for (File file : files) {
				fileList.add(new BurnFile(file.getName(), "download" + File.separator + file.getName()));
			}
		}
		return SUCCESS;
	}
}
