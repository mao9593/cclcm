package hdsec.web.project.basic.action;

import hdsec.web.project.common.PropertyUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 上传下载安装包文件
 * @author gaoximin
 *
 */
public class UploadSetupFilesAction extends BasicBaseAction{
	private static final long serialVersionUID = 1L;
	private List<String> fileNameList = null;
	private String storePath="";
	private String delfilepath="";

	
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
		storePath = PropertyUtil.getUploadSetupFileStorePath();
		File path = new File(storePath);
		if (!path.exists()) {
			path.mkdirs();
		}
		File[] files = path.listFiles();
		fileNameList = new ArrayList<String>();
		//storePath
		for (File file : files) {
			fileNameList.add(file.getName());
		}
		return fileNameList;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(delfilepath)) {
			storePath = PropertyUtil.getUploadSetupFileStorePath();
			File deletePath = new File(storePath+"\\"+delfilepath);
			if(!deletePath.isDirectory()){
				deletePath.delete();
			}
		}
		handleFileList();
		
		return SUCCESS;
	}
}
