package hdsec.web.project.basic.action;

import hdsec.web.project.burn.model.BurnFile;
import hdsec.web.project.common.PropertyUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 上传常用文档
 * 
 * @author renmingfei 2014-10-15
 */
public class UploadFileAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String storePath = "";
	private String delfilepath = "";
	private List<BurnFile> fileList = null;

	public List<BurnFile> getFileList() {
		return fileList;
	}

	public String getStorePath() {
		return storePath;
	}

	public void setDelfilepath(String delfilepath) {
		this.delfilepath = delfilepath;
	}

	private void handleFileList() throws Exception {
		storePath = PropertyUtil.getDownloadFileStorePath();
		File path = new File(storePath);
		fileList = new ArrayList<BurnFile>();
		if (path.isDirectory()) {
			File[] files = path.listFiles();
			for (File file : files) {
				fileList.add(new BurnFile(file.getName(), "download" + File.separator + file.getName()));
			}
		}
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(delfilepath)) {
			storePath = PropertyUtil.getDownloadFileStorePath();
			File deletePath = new File(storePath + File.separator + delfilepath);
			if (!deletePath.isDirectory()) {
				deletePath.delete();
			}
		}
		handleFileList();
		return SUCCESS;
	}
}
