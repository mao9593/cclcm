package hdsec.web.project.education.action;

import hdsec.web.project.common.bm.BMPropertyUtil;
import hdsec.web.project.education.model.UploadEduFiles;
import hdsec.web.project.user.model.SecLevel;
import hdsec.web.project.user.model.SecUser;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 上传保密教育学习材料
 * 
 * @author lishu
 * 
 */
public class UploadLearningFileAction extends EducationBaseAction {
	private static final long serialVersionUID = 1L;

	private String delfilepath = "";
	private String store_location = "";// 文档存储路径
	private String file_id = "";// 文件编号
	private String file_name = "";// 文件名
	private String file_type = "";// 文件类型
	private String submitFlag = ""; // 是否
	private float file_edu_hour = 0;// 文件对应最大学时
	private String file_sec_lv = "";// 文件密级
	private String upload_user = "";// 上传用户
	private Date upload_time = null;// 上传时间
	private String comment = "";// 备注
	private Integer pages = null;// 文件页数
	private List<String> fileNameList = null;
	private List<UploadEduFiles> uploadEvent = null;

	/**
	 * private Integer file_category = null;// 文件类别 private String storePath = "";
	 * 
	 */

	public List<String> getFileNameList() {
		return fileNameList;
	}

	public String getDelfilepath() {
		return delfilepath;
	}

	public void setDelfilepath(String delfilepath) {
		this.delfilepath = delfilepath;
	}

	public String getStore_location() {
		return store_location;
	}

	public void setStore_location(String store_location) {
		this.store_location = store_location;
	}

	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
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

	public String getSubmitFlag() {
		return submitFlag;
	}

	public void setSubmitFlag(String submitFlag) {
		this.submitFlag = submitFlag;
	}

	public float getFile_edu_hour() {
		return file_edu_hour;
	}

	public void setFile_edu_hour(float file_edu_hour) {
		this.file_edu_hour = file_edu_hour;
	}

	public String getFile_sec_lv() {
		return file_sec_lv;
	}

	public void setFile_sec_lv(String file_sec_lv) {
		this.file_sec_lv = file_sec_lv;
	}

	public String getUpload_user() {
		return upload_user;
	}

	public void setUpload_user(String upload_user) {
		this.upload_user = upload_user;
	}

	public Date getUpload_time() {
		return upload_time;
	}

	public void setUpload_time(Date upload_time) {
		this.upload_time = upload_time;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public void setFileNameList(List<String> fileNameList) {
		this.fileNameList = fileNameList;
	}

	public List<UploadEduFiles> getUploadEvent() {
		return uploadEvent;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public SecUser getSecUser() {
		return userService.getSecUserByUid(getCurUser().getUser_iidd());
	}

	private List<String> handleFileList() throws Exception {
		// 从配置文件中读取文档的存储路径
		store_location = BMPropertyUtil.getLearningFileStorePath();
		File path = new File(store_location);
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

	@Override
	public String executeFunction() throws Exception {

		// 1、删除文件,并根据文件名(PK)删除数据库中记录 删除
		if (StringUtils.hasLength(delfilepath)) {
			// 1.1 删除文件
			store_location = BMPropertyUtil.getLearningFileStorePath();
			File deletePath = new File(store_location + "\\" + delfilepath);
			if (!deletePath.isDirectory()) {
				deletePath.delete();
			}
			// 1.2 删数据库
			educationService.delUploadFileByName(delfilepath);
		}

		// 2、遍历文件夹下文件，获取文件列表
		handleFileList();

		// 3、如果是添加文件，写入数据库
		if (submitFlag.equalsIgnoreCase("add")) {
			// 先判断同名文件是否存在
			int count = educationService.getCountByFileName(file_name);
			if (count > 0) {
				throw new Exception("同名文件已存在，请修改文件名后重新上传");
			} else {
				// 1、设置上传的文件属性
				UploadEduFiles upEvent = new UploadEduFiles(store_location, file_id, file_name, file_type, upload_user,
						upload_time, pages, file_sec_lv, comment, file_edu_hour);

				/*
				 * upEvent.setSec_lv(sec_lv); upEvent.setUpload_user(upload_user); upEvent.setFile_edu_hour(class_time);
				 */
				// // 2、根据文件中参数计算出页数
				// // upEvent.setPages(32);
				educationService.UploadLearningFile(upEvent);
			}
		}

		// 4、读取数据库中字段信息
		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("user_iidd", "user_iidd");
		uploadEvent = educationService.getLearningFileList(map);

		return SUCCESS;
	}

	public void setUploadEvent(List<UploadEduFiles> uploadEvent) {
		this.uploadEvent = uploadEvent;
	}
}
