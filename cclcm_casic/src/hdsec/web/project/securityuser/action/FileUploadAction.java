package hdsec.web.project.securityuser.action;

import hdsec.web.project.burn.model.BurnFile;
import hdsec.web.project.common.CCLCMConstants;
import hdsec.web.project.common.bm.BMPropertyUtil;
import hdsec.web.project.secmanage.model.FileListEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

public class FileUploadAction extends SecurityUserBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String flag = "";// Y:已有文件 N:新上传文件
	private String filename = "";
	private String event_code = "";
	private String filetype = "";// Y：提交
	private FileListEvent file = null;
	private List<BurnFile> userInfoFileList = null;
	private String result = "";
	private String dname = "";

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public FileListEvent getFile() {
		return file;
	}

	public void setFile(FileListEvent file) {
		this.file = file;
	}

	public List<BurnFile> getUserInfoFileList() {
		return userInfoFileList;
	}

	public void setUserInfoFileList(List<BurnFile> userInfoFileList) {
		this.userInfoFileList = userInfoFileList;
	}

	@Override
	public String executeFunction() throws Exception {
		// 展示
		if (flag.equals("Y") && !filename.isEmpty()) {
			filename = java.net.URLDecoder.decode(filename, "utf-8");
			String user_iidd = getCurUser().getUser_iidd();
			Map<String, Object> mm = new HashMap<String, Object>();
			mm.put("real_user_id", user_iidd);
			mm.put("info_type", 2);
			// List<BmRealUser> usertemp = securityUserService.getUserInfoByUserIdAndInfoType(mm);
			String[] filelist = filename.split(CCLCMConstants.DEVIDE_SYMBOL);
			if (filelist.length > 0 && StringUtils.hasLength(filelist[0])) {
				String storePath = BMPropertyUtil.getSecUserInfoSepcialFilePath();
				userInfoFileList = new ArrayList<BurnFile>();
				String file_path = "";
				for (int i = 0; i < filelist.length; i++) {
					file_path = storePath + "/" + curUser.getUser_iidd() + "/" + filelist[i];
					userInfoFileList.add(new BurnFile(filelist[i], file_path));
				}
			}
		}
		return SUCCESS;

	}
}