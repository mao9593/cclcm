package hdsec.web.project.securityuser.action;

import hdsec.web.project.common.bm.BMPropertyUtil;
import hdsec.web.project.common.util.ExactFilenameFilter;

import java.io.File;

/**
 * 删除上传的文件
 * 
 * @author guojiao
 * 
 */
public class DelUploadedFileAction extends SecurityUserBaseAction {
	private static final long serialVersionUID = 1L;
	private String file_name = "";
	private String chkResult = "";
	private String type = "";

	public String getType() {
		return type;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getChkResult() {
		return chkResult;
	}

	public void setChkResult(String chkResult) {
		this.chkResult = chkResult;
	}

	@Override
	public String executeFunction() {
		String tempPath = "";
		try {
			tempPath = BMPropertyUtil.getSecUserInfoSepcialFilePath();
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		tempPath = tempPath + File.separator + getCurUser().getUser_iidd();
		File path = new File(tempPath);
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
			setChkResult("该用户的文件目录不存在");
		}

		return SUCCESS;
	}
}
