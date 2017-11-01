package hdsec.web.project.ledger.action;

import hdsec.web.project.common.PropertyUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * @author lidepeng 2015-4-8
 */
public class ConfirmDestroyPaperAction extends LedgerBaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> fileNameList = null;
	private String storePath = "";
	private String delfilepath = "";
	private String job_code = "";

	public String getStorePath() {
		return storePath;
	}

	public void setDelfilepath(String delfilepath) {
		this.delfilepath = delfilepath;
	}

	public List<String> getFileNameList() {
		return fileNameList;
	}

	private List<String> handleFileList() throws Exception {
		// 从配置文件中读取安装包的存储路径
		storePath = PropertyUtil.getUploadSendFilePath() + "/" + job_code;
		File path = new File(storePath);
		if (!path.exists()) {
			path.mkdirs();
		}
		File[] files = path.listFiles();
		fileNameList = new ArrayList<String>();
		// storePath
		for (File file : files) {
			fileNameList.add(file.getName());
		}
		return fileNameList;
	}

	public String getJob_code() {
		return job_code;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	@Override
	public String executeFunction() throws Exception {
		handleFileList();
		return SUCCESS;
	}
}
