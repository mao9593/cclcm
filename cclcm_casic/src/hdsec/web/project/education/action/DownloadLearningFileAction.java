package hdsec.web.project.education.action;

import hdsec.web.project.education.model.UploadEduFiles;
import hdsec.web.project.user.model.SecLevel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学习资料列表
 * 
 * 保密学习在线学习
 * 
 * @author lixiao lishu guojiao
 * 
 */
public class DownloadLearningFileAction extends EducationBaseAction {
	private static final long serialVersionUID = 1L;
	private List<String> fileNameList = null;
	private String file_name = "";// 文件名
	private String file_type = "";// 文件类型
	private List<UploadEduFiles> uploadEvent = null;
	private float online_time = 0;
	private float upload_hour = 0;

	public List<String> getFileNameList() {
		return fileNameList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public List<UploadEduFiles> getUploadEvent() {
		return uploadEvent;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	public float getOnline_time() {
		return online_time;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("file_type", file_type);
		map.put("file_name", file_name);
		uploadEvent = educationService.getLearningFileList(map);

		// 获取所有上传文件的总学时
		String tempHour=educationService.getTotalHour();
		if(tempHour != null && tempHour !=""){
			upload_hour =  Float.parseFloat(tempHour);
		}
		
		// 取得当前用户在线学习累计时长，不能超过总学时
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("user_iidd", getCurUser().getUser_iidd());
		String tempOnline = educationService.getUserClassHourOnlineByUserId(map1);
		if(tempOnline != null && tempOnline !=""){
			online_time =  Float.parseFloat(tempOnline);
		}
		if (online_time > upload_hour) {
			online_time = upload_hour;
		}

		return SUCCESS;
	}
}
