package hdsec.web.project.basic.upload;

import hdsec.web.project.basic.action.BasicBaseAction;
import hdsec.web.project.common.PropertyUtil;
import hdsec.web.project.common.util.ExactFilenameFilter;

import java.io.File;

/**
 * 删除上传的文件
 * 
 * @author renmingfei
 * 
 */
public class DelUploadedFileAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String file_name = "";
	private String chkResult = "";
	private String file_type = "";

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	public String getChkResult() {
		return chkResult;
	}

	public void setChkResult(String chkResult) {
		this.chkResult = chkResult;
	}

	@Override
	public String executeFunction() {
		if (file_type.equalsIgnoreCase("temp")) {
			if (file_name.isEmpty()) {
				setChkResult("缺少参数");
			}
			String tempPath = "";
			try {
				// mod by wx 20150501 修改删除临时文件时目录获取错误
				// tempPath = PropertyUtil.getUploadVersionFileStorePath();
				tempPath = PropertyUtil.getUploadVersionFileTempPath();
				// above
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			tempPath = tempPath + File.separator + getCurUser().getUser_iidd();
			File path = new File(tempPath);
			if (path.isDirectory()) {
				File[] files = path.listFiles();
				if (files.length == 0) {
					setChkResult("文件[" + file_name + "]不存在");
				} else {
					if (files[0].delete()) {
						setChkResult("删除成功");
					} else {
						setChkResult("删除文件[" + file_name + "]失败");
					}
				}
			} else {
				setChkResult("该用户的临时文件目录不存在");
			}
		} else {
			if (file_name.isEmpty()) {
				setChkResult("缺少参数");
			}
			String storePath = "";
			try {
				storePath = PropertyUtil.getBurnFileStorePath();
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			File path = new File(storePath);
			ExactFilenameFilter filenameFilter = new ExactFilenameFilter(file_name);
			if (path.isDirectory()) {
				File[] files = path.listFiles(filenameFilter);
				if (files.length == 0) {
					setChkResult("文件[" + file_name + "]不存在");
				} else {
					if (files[0].delete()) {
						setChkResult("删除成功");

					} else {
						setChkResult("删除文件[" + file_name + "]失败");
					}
				}
			} else {
				setChkResult("该用户的导出文件存储目录不存在");
			}
		}
		return SUCCESS;
	}
}
