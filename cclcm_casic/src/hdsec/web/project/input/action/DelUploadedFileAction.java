package hdsec.web.project.input.action;

import hdsec.web.project.common.CCLCMConstants;
import hdsec.web.project.common.PropertyUtil;
import hdsec.web.project.common.util.ExactFilenameFilter;
import hdsec.web.project.common.util.MD5;
import hdsec.web.project.input.model.InputEvent;

import java.io.File;

public class DelUploadedFileAction extends InputBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String file_name = "";
	private String chkResult = "";
	private String file_type = "";

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

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
			if (event_code.isEmpty() || file_name.isEmpty()) {
				setChkResult("缺少参数");
			}
			String tempPath = "";
			try {
				tempPath = PropertyUtil.getInputFileTempPath();
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			tempPath = tempPath + File.separator + getCurUser().getUser_iidd();
			File path = new File(tempPath);
			ExactFilenameFilter filenameFilter = new ExactFilenameFilter(event_code + "-" + file_name);
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
				setChkResult("该用户的临时文件目录不存在");
			}
		} else {
			if (event_code.isEmpty() || file_name.isEmpty()) {
				setChkResult("缺少参数");
			}
			String storePath = "";
			try {
				storePath = PropertyUtil.getInputFileStorePath();
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			storePath = storePath + File.separator + event_code;
			File path = new File(storePath);
			ExactFilenameFilter filenameFilter = null;
			try {
				filenameFilter = new ExactFilenameFilter(MD5.getStringMD5(file_name));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (path.isDirectory()) {
				File[] files = path.listFiles(filenameFilter);
				if (files.length == 0) {
					setChkResult("文件[" + file_name + "]不存在");
				} else {
					if (files[0].delete()) {
						setChkResult("删除成功");
						InputEvent event = inputService.getInputEventByCode(event_code);
						int fileNum = event.getFile_num() - 1;
						String[] fileList = event.getFile_list().split(CCLCMConstants.DEVIDE_SYMBOL);
						String[] fileseclevel = event.getFile_seclevel().split(CCLCMConstants.DEVIDE_SYMBOL);
						String newFileList = "";
						String newFileSec = "";
						for (int i = 0; i < fileList.length; i++) {
							if (!file_name.trim().equals(fileList[i].trim())) {
								newFileList += fileList[i] + CCLCMConstants.DEVIDE_SYMBOL;
								newFileSec += fileseclevel[i] + CCLCMConstants.DEVIDE_SYMBOL;
							}
						}
						if (newFileList.endsWith(CCLCMConstants.DEVIDE_SYMBOL)) {
							newFileList = newFileList.substring(0, newFileList.length() - 1);
							newFileSec = newFileSec.substring(0, newFileSec.length() - 1);
						}
						if (newFileList.startsWith(CCLCMConstants.DEVIDE_SYMBOL)) {
							newFileList = newFileList.substring(1);
						}
						if (newFileSec.startsWith(CCLCMConstants.DEVIDE_SYMBOL)) {
							newFileSec = newFileSec.substring(1);
						}
						inputService.updateInputEventFileInfo(event_code, fileNum, newFileList, newFileSec);
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
