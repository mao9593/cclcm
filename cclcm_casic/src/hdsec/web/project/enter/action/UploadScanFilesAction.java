package hdsec.web.project.enter.action;

import hdsec.web.project.basic.action.BasicBaseAction;
import hdsec.web.project.burn.model.BurnFile;
import hdsec.web.project.common.PropertyUtil;
import hdsec.web.project.enter.service.EnterService;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;

/**
 * 管理员上传扫描文件
 * 
 * @author gaoximin 2014-10-17
 */
public class UploadScanFilesAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	@Resource
	private EnterService enterService;
	private String event_code = "";// 扫描录入作业流水号
	private String storePath = "";
	private String delfilepath = "";
	private String update = "N";
	private List<BurnFile> fileList = null;

	public void setUpdate(String update) {
		this.update = update;
	}

	public List<BurnFile> getFileList() {
		return fileList;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getStorePath() {
		return storePath;
	}

	public void setStorePath(String storePath) {
		this.storePath = storePath;
	}

	public String getDelfilepath() {
		return delfilepath;
	}

	public void setDelfilepath(String delfilepath) {
		this.delfilepath = delfilepath;
	}

	private void handleFileList() throws Exception {
		storePath = PropertyUtil.getScanFileStorePath(event_code);
		File path = new File(storePath);
		fileList = new ArrayList<BurnFile>();
		if (path.isDirectory()) {
			File[] files = path.listFiles();
			for (File file : files) {
				fileList.add(new BurnFile(file.getName(), "files" + File.separator + "scan" + File.separator
						+ event_code + File.separator + file.getName()));
			}
		}
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(delfilepath)) {
			storePath = PropertyUtil.getScanFileStorePath(event_code);
			File deletePath = new File(storePath + File.separator + delfilepath);
			if (!deletePath.isDirectory()) {
				deletePath.delete();
			}
			handleFileList();
			return SUCCESS;
		} else if (update.equalsIgnoreCase("Y")) {
			String user_name = getCurUser().getUser_name();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("event_code", event_code);
			map.put("import_status", 1);
			map.put("finish_time", new Date());
			map.put("import_user_iidd", getCurUser().getUser_iidd());
			map.put("medium_serial", "");
			enterService.updateEnterEventState(map);
			insertCommonLog(user_name + "上传扫描文件");
			return "ok";
		} else {
			handleFileList();
			return SUCCESS;
		}
	}
}
