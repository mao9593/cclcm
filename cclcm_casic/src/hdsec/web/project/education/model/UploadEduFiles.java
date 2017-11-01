package hdsec.web.project.education.model;

import java.util.Date;

/**
 * 上传作业类
 * 
 * @author gaoyiming
 * 
 */
public class UploadEduFiles {
	private String store_location = "";// 文档存储路径
	private String file_name = "";//
	private String file_id = "";// 文件编号
	private String file_type = "";// 类型
	private String comment = "";// 备注
	private Integer pages = 0;
	private String file_sec_lv = "";
	private String upload_user = "";
	private Date upload_time = null;
	// private Integer file_category = 0;// 文件类别
	private float file_edu_hour = 0;// 学时要求
	private String course = "";
	private float total_hour = 0;// 在线学习总学时

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getStore_location() {
		return store_location;
	}

	public void setStore_location(String store_location) {
		this.store_location = store_location;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}

	public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
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

	public float getFile_edu_hour() {
		return file_edu_hour;
	}

	public void setFile_edu_hour(float file_edu_hour) {
		this.file_edu_hour = file_edu_hour;
	}

	public float getTotal_hour() {
		return total_hour;
	}

	public UploadEduFiles() {
		super();
		this.upload_time = new Date();
	}

	public UploadEduFiles(String store_location, String file_id, String file_name, String file_type,
			String upload_user, Date upload_time, Integer pages, String file_sec_lv, String comment, float file_edu_hour) {

		this.store_location = store_location;
		this.file_id = file_id;
		this.file_name = file_name;
		this.file_type = file_type;
		this.upload_user = upload_user;
		this.upload_time = upload_time;
		this.pages = pages;
		this.file_sec_lv = file_sec_lv;
		this.comment = comment;
		this.file_edu_hour = file_edu_hour;

	}
}
